/**
 * Copyright 2014 Upwork
 *
 * Licensed under the Upwork's API Terms of Use;
 * you may not use this file except in compliance with the Terms.
 * You may obtain a copy of the Terms at
 * 
 *    https://developers.upwork.com/api-tos.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author: Maksym Novozhylov <mnovozhilov@upwork.com>
 */

package com.Upwork.api;

import com.Upwork.ClassPreamble;
import com.google.api.client.auth.oauth2.*;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

@ClassPreamble(
    author = "Maksym Novozhylov <mnovozhilov@upwork.com>",
    date = "10/31/2018",
    currentRevision = 1,
    lastModified = "11/01/2018",
    lastModifiedBy = "Maksym Novozhylov",
    reviewers = { "Yiota Tsakiri" }
)
public class OAuthClient {
    private static final int METHOD_GET = 1;
    private static final int METHOD_POST = 2;
    private static final int METHOD_PUT = 3;
    private static final int METHOD_DELETE = 4;

    private static final String OVERLOAD_PARAM = "http_method";
    private static final String DATA_FORMAT = "json";
    private static final String UPWORK_BASE_URL = "https://www.upwork.com/";
    private static final String UPWORK_GQL_ENDPOINT = "https://api.upwork.com/graphql";

    private static final String UPWORK_LIBRARY_USER_AGENT = "Github Upwork API Java Client";

    private static final String TOKEN_SERVER_URL = UPWORK_BASE_URL + "api/v3/oauth2/token";
    private static final String AUTHORIZATION_SERVER_URL = UPWORK_BASE_URL + "ab/account-security/oauth2/authorize";

    /** Global instance of the HTTP transport. */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpRequestFactory HTTP_REQUEST_FACTORY = HTTP_TRANSPORT.createRequestFactory();

    private static String clientId = null;
    private static String clientSecret = null;
    private static String redirectUri = null;
    private static String entryPoint = "api";
    private static String tenantId = null;

    private final AuthorizationCodeFlow authorizationCodeFlow;
    private volatile Credential credential;

    /**
     * Constructor
     *
     * @param properties Config properties
     */
    public OAuthClient(Config properties) {
        if (properties == null) {
            properties = new Config(null);
        }

        clientId = properties.getProperty("clientId");
        clientSecret = properties.getProperty("clientSecret");
        redirectUri = properties.getProperty("redirectUri");

        authorizationCodeFlow = new AuthorizationCodeFlow(
                BearerToken.authorizationHeaderAccessMethod(),
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GenericUrl(TOKEN_SERVER_URL),
                new ClientParametersAuthentication(clientId, clientSecret),
                clientId,
                AUTHORIZATION_SERVER_URL);
    }

    /**
     * Returns authorization URL.
     *
     * @param state An opaque value used by the client to maintain state between the
     *              request and
     *              callback, as mentioned in <a href=
     *              "http://tools.ietf.org/html/rfc6749#section-3.1.2.2">Registration
     *              Requirements</a>,
     *              or {@code null} for none
     * @return URL for authorizing application
     */
    public String getAuthorizationUrl(String state) {
        return authorizationCodeFlow.newAuthorizationUrl()
                .setState(state)
                .setRedirectUri(redirectUri)
                .build();
    }

    /**
     * Returns TokensResponse containing access and refresh tokens.
     * Also sets the returned tokens into this client instance to be used for
     * requests.
     *
     * @param code Authorization code, which was got after authorization
     * @return TokensResponse containing access and refresh tokens
     * @throws TokenResponseException In case tokens cannot be obtained
     */
    public TokenResponse getTokenResponseByCode(String code, CredentialRefreshListener refreshListener)
            throws TokenResponseException, IOException {
        TokenResponse tokenResponse = authorizationCodeFlow.newTokenRequest(code)
                .setRedirectUri(redirectUri)
                .setRequestInitializer(httpRequest -> httpRequest.getHeaders().setUserAgent(UPWORK_LIBRARY_USER_AGENT))
                .execute();
        setTokenResponse(tokenResponse, refreshListener);
        return tokenResponse;
    }

    /**
     * Returns TokensResponse containing access and refresh tokens.
     * Also sets the returned tokens into this client instance to be used for
     * requests.
     *
     * @param refreshToken Refresh token used to obtain new access token
     * @return TokensResponse containing access and refresh tokens
     * @throws TokenResponseException In case tokens cannot be obtained
     */
    public TokenResponse getTokenResponseByRefreshToken(String refreshToken, CredentialRefreshListener refreshListener)
            throws TokenResponseException, IOException {
        TokenResponse tokenResponse = new RefreshTokenRequest(HTTP_TRANSPORT, JSON_FACTORY,
                new GenericUrl(TOKEN_SERVER_URL), refreshToken)
                        .setClientAuthentication(new ClientParametersAuthentication(clientId, clientSecret))
                        .setRequestInitializer(
                                httpRequest -> httpRequest.getHeaders().setUserAgent(UPWORK_LIBRARY_USER_AGENT))
                        .execute();
        setTokenResponse(tokenResponse, refreshListener);
        return tokenResponse;
    }

    /**
     * Sets up access and refresh tokens to be used for requests with this client.
     * If refresh token is provided, it will be used to automatically refresh access
     * tokens when requests are made
     * if they are expired or will expire soon (in less than 1 minute).
     *
     * @param tokenResponse   TokenResponse contains access and refresh tokens and
     *                        access token expiry time
     * @param refreshListener CredentialRefreshListener which is called when tokens
     *                        are automatically refreshed
     */
    public void setTokenResponse(TokenResponse tokenResponse, CredentialRefreshListener refreshListener)
            throws IOException {
        Credential.Builder builder = new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setTokenServerUrl(new GenericUrl(TOKEN_SERVER_URL))
                .setClientAuthentication(new ClientParametersAuthentication(clientId, clientSecret));
        if (refreshListener != null) {
            builder.setRefreshListeners(Collections.singletonList(refreshListener));
        }

        Credential credential = builder.build();
        credential.setFromTokenResponse(tokenResponse);

        Long expiresIn = credential.getExpiresInSeconds();
        // check if token will expire in a minute
        if (credential.getAccessToken() == null || expiresIn != null && expiresIn <= 60) {
            credential.refreshToken();
        }

        this.credential = credential;
    }

    /**
     * Setup entry point for the request(s)
     *
     * @param ep Entry point
     */
    public final void setEntryPoint(String ep) {
        entryPoint = ep;
    }

    /**
     * Setup X-Upwork-API-TenantId header
     *
     * @param uid Organization UID (aka Tenant ID)
     *
     */
    public final void setOrgUidHeader(String uid) {
        tenantId = uid;
    }

    /**
     * Send signed OAuth GET request without parameters
     *
     * @param url Relative URL
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    public JSONObject get(String url) throws JSONException {
        return sendGetRequest(url, METHOD_GET, null);
    }

    /**
     * Send signed OAuth GET request
     *
     * @param url    Relative URL
     * @param params Hash of parameters
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    public JSONObject get(String url, HashMap<String, String> params) throws JSONException {
        return sendGetRequest(url, METHOD_GET, params);
    }

    /**
     * Send signed OAuth POST request
     *
     * @param url    Relative URL
     * @param params Hash of parameters
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    public JSONObject post(String url, HashMap<String, String> params) throws JSONException {
        return sendPostRequest(url, METHOD_POST, params);
    }

    /**
     * Send signed OAuth PUT request
     *
     * @param url Relative URL
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    public JSONObject put(String url) throws JSONException {
        return sendPostRequest(url, METHOD_PUT, new HashMap<String, String>());
    }

    /**
     * Send signed OAuth PUT request
     *
     * @param url    Relative URL
     * @param params Hash of parameters
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    public JSONObject put(String url, HashMap<String, String> params) throws JSONException {
        return sendPostRequest(url, METHOD_PUT, params);
    }

    /**
     * Send signed OAuth DELETE request without parameters
     *
     * @param url Relative URL
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    public JSONObject delete(String url) throws JSONException {
        return sendPostRequest(url, METHOD_DELETE, null);
    }

    /**
     * Send signed OAuth DELETE request
     *
     * @param url    Relative URL
     * @param params Hash of parameters
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    public JSONObject delete(String url, HashMap<String, String> params) throws JSONException {
        return sendPostRequest(url, METHOD_DELETE, params);
    }

    /**
     * Send signed GET OAuth request
     *
     * @param url    Relative URL
     * @param type   Type of HTTP request (HTTP method)
     * @param params Hash of parameters
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    private JSONObject sendGetRequest(String url, Integer type, HashMap<String, String> params) throws JSONException {
        String fullUrl = getFullUrl(url);
        GenericUrl genericUrl = new GenericUrl(fullUrl);
        if (params != null) {
            genericUrl.putAll(params);
        }
        try {
            HttpRequest request = HTTP_REQUEST_FACTORY.buildGetRequest(genericUrl);
            request.getHeaders().setUserAgent(UPWORK_LIBRARY_USER_AGENT);
            credential.intercept(request);
            return UpworkRestClient.executeRequest(request);
        } catch (IOException e) {
            return UpworkRestClient.genIOError(e);
        }
    }

    /**
     * Send signed POST OAuth request
     *
     * @param url    Relative URL
     * @param type   Type of HTTP request (HTTP method)
     * @param params Hash of parameters
     * @throws JSONException If JSON object is invalid or request was abnormal
     * @return {@link JSONObject} JSON Object that contains data from response
     */
    private JSONObject sendPostRequest(String url, Integer type, HashMap<String, String> params) throws JSONException {
        String fullUrl = getFullUrl(url);

        switch (type) {
            case METHOD_PUT:
            case METHOD_DELETE:
                // assign overload value
                String oValue;
                if (type == METHOD_PUT) {
                    oValue = "put";
                } else {
                    oValue = "delete";
                }
                params.put(OVERLOAD_PARAM, oValue);
            case METHOD_POST:
                break;
            default:
                throw new RuntimeException("Wrong http method requested");
        }

        try {
            HttpRequest request = HTTP_REQUEST_FACTORY.buildPostRequest(
                    new GenericUrl(fullUrl),
                    new JsonHttpContent(JSON_FACTORY, params));
            if (tenantId != null && tenantId != "") {
                request.getHeaders().set("X-Upwork-API-TenantId", tenantId);
            }
            request.getHeaders().setUserAgent(UPWORK_LIBRARY_USER_AGENT);
            credential.intercept(request);
            return UpworkRestClient.executeRequest(request);
        } catch (IOException e) {
            return UpworkRestClient.genIOError(e);
        }
    }

    /**
     * Build absolute URL
     *
     * @param url Relative URL
     * @return Absolute URL
     */
    private final String getFullUrl(String url) {
        return (entryPoint == "graphql")
                ? UPWORK_GQL_ENDPOINT
                : (UPWORK_BASE_URL + entryPoint + url +
                        ((entryPoint == "api") ? ("." + DATA_FORMAT) : ""));
    }
}
