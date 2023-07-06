package com.Upwork.api;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.http.GenericUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
        Config.class,
        OAuthClient.class
})
public class OAuthClientTest {
    @Spy
    private final Properties properties = new Properties();

    private final String accessToken = "testAccessToken";
    private final String refreshToken = "testRefreshToken";

    private final AuthorizationCodeTokenRequest authzCodeTokenRequestMock = PowerMockito
            .mock(AuthorizationCodeTokenRequest.class);
    private final RefreshTokenRequest refreshTokenRequestMock = PowerMockito.mock(RefreshTokenRequest.class);
    private final ClientCredentialsTokenRequest ccTokenRequestMock = PowerMockito
            .mock(ClientCredentialsTokenRequest.class);

    private OAuthClient getMockedClientCodeAuthzGrant(String grantType) throws Exception {
        when(properties.getProperty("clientId")).thenReturn("key");
        when(properties.getProperty("clientSecret")).thenReturn("secret");
        when(properties.getProperty("redirectUri")).thenReturn("https://redirectUri");
        when(properties.getProperty("grantType")).thenReturn(grantType);

        final FileInputStream fileInputStreamMock = PowerMockito.mock(FileInputStream.class);
        PowerMockito.whenNew(FileInputStream.class).withArguments(Matchers.anyString()).thenReturn(fileInputStreamMock);

        // Client Credentials Grant
        ClientCredentialsTokenRequest clientCredentialsTokenRequestMock = PowerMockito.mock(ClientCredentialsTokenRequest.class);
        PowerMockito.whenNew(ClientCredentialsTokenRequest.class).withAnyArguments().thenReturn(ccTokenRequestMock);
        when(ccTokenRequestMock.setClientAuthentication(Matchers.any())).thenReturn(ccTokenRequestMock);
        when(ccTokenRequestMock.setRequestInitializer(Matchers.any())).thenCallRealMethod();

        // Code Authorizations Grant
        AuthorizationCodeFlow authorizationCodeFlowMock = PowerMockito.mock(AuthorizationCodeFlow.class);
        PowerMockito.whenNew(AuthorizationCodeFlow.class).withAnyArguments().thenReturn(authorizationCodeFlowMock);
        when(authorizationCodeFlowMock.newAuthorizationUrl()).thenReturn(new AuthorizationCodeRequestUrl("https://auth_host/token", "key"));
        when(authorizationCodeFlowMock.newTokenRequest(Matchers.anyString())).thenReturn(authzCodeTokenRequestMock);

        when(authzCodeTokenRequestMock.setRedirectUri(Matchers.anyString())).thenReturn(authzCodeTokenRequestMock);
        when(authzCodeTokenRequestMock.setRequestInitializer(Matchers.any())).thenCallRealMethod();

        PowerMockito.whenNew(RefreshTokenRequest.class).withAnyArguments().thenReturn(refreshTokenRequestMock);
        when(refreshTokenRequestMock.setClientAuthentication(Matchers.any(ClientParametersAuthentication.class))).thenReturn(refreshTokenRequestMock);
        when(refreshTokenRequestMock.setRequestInitializer(Matchers.any())).thenCallRealMethod();

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        when(authzCodeTokenRequestMock.execute()).thenReturn(tokenResponse);
        when(refreshTokenRequestMock.execute()).thenReturn(tokenResponse);
        when(ccTokenRequestMock.execute()).thenReturn(tokenResponse);

        Config config = new Config(properties);

        OAuthClient client = new OAuthClient(config) {
            @Override
            public void setTokenResponse(TokenResponse tokenResponse, CredentialRefreshListener refreshListener) throws IOException {
                // No op in tests to avoid initialization of Credentials
            }
        };

        return client;
    }

    @Test
    public void getAuthorizationUrl() throws Exception {
        OAuthClient client = getMockedClientCodeAuthzGrant("code_authorization");
        String state = "xyz";
        String authzUrl = client.getAuthorizationUrl(state);
        GenericUrl genericUrl = new GenericUrl(authzUrl);

        assertEquals("scheme", "https", genericUrl.getScheme());
        assertEquals("host", "auth_host", genericUrl.getHost());
        assertEquals("path", "/token", genericUrl.getRawPath());

        Map<String, Object> expectedParams = new HashMap<String, Object>();
        expectedParams.put("response_type", "code");
        expectedParams.put("client_id", "key");
        expectedParams.put("redirect_uri", "https://redirectUri");
        expectedParams.put("state", state);

        assertEquals("query params size", expectedParams.size(), genericUrl.size());
        for (Map.Entry<String, Object> entry : expectedParams.entrySet()) {
            assertEquals("query param " + entry.getKey(), entry.getValue(), genericUrl.getFirst(entry.getKey()));
        }
    }

    @Test
    public void getTokenResponseByCode() throws Exception {
        OAuthClient client = getMockedClientCodeAuthzGrant("code_authorization");
        TokenResponse tokenResponse = client.getTokenResponseByCode("code", null);
        assertEquals(accessToken, tokenResponse.getAccessToken());
        assertEquals(refreshToken, tokenResponse.getRefreshToken());
    }

    @Test
    public void getTokenResponseByRefreshToken() throws Exception {
        OAuthClient client = getMockedClientCodeAuthzGrant("code_authorization");
        TokenResponse tokenResponse = client.getTokenResponseByRefreshToken("refreshToken", null);
        assertEquals(accessToken, tokenResponse.getAccessToken());
        assertEquals(refreshToken, tokenResponse.getRefreshToken());
    }

    @Test
    public void getClientCredentialsAccessToken() throws Exception {
        OAuthClient client = getMockedClientCodeAuthzGrant("client_credentials");
        TokenResponse tokenResponse = client.getClientCredentialsAccessToken(null);
        assertEquals(accessToken, tokenResponse.getAccessToken());
    }
}
