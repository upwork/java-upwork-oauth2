package com.Upwork.test_api;

import com.Upwork.api.OAuthClient;
import com.Upwork.api.Routers.Graphql;
import com.Upwork.api.Routers.Organization.Users;
import com.google.api.client.auth.oauth2.TokenResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

/**
 * Hello world! Test Upwork API
 *
 */
public class App {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        OAuthClient client = new OAuthClient(null);

        // (replace with your flow) Read existent token pair
        // If you store tokenResponse in a data storage, you may want to override
        // Here we create a TokenResponse object as an example
        TokenResponse existentTokenResponse = new TokenResponse();
        existentTokenResponse.setAccessToken("ACCESS-TOKEN-HERE");
        existentTokenResponse.setRefreshToken("REFRESH-TOKEN-HERE");
        existentTokenResponse.setExpiresInSeconds((long) 86399);
        existentTokenResponse.setTokenType("Bearer");
        if (existentTokenResponse.getAccessToken() == "ACCESS-TOKEN-HERE") {
            System.out.println("Request new token pair");

            // authorize application and get access token
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Copy paste the following url in your browser : ");
            System.out.println(client.getAuthorizationUrl("xyz"));
            System.out.println("2. Grant access ");
            System.out.println("3. Copy paste the code parameter here :");
            String code = scanner.nextLine();
            scanner.close();

            TokenResponse tokenResponse = client.getTokenResponseByCode(code, null);
            // (replace with your flow) Save tokenResponse for future use, e.g. in
            // DataStoreFactory
            System.out.println(tokenResponse.getAccessToken());
        } else {
            System.out.println("Apply existing token pair");
            client.setTokenResponse(existentTokenResponse, null);
        }

        // If you need to force the token refresh for what ever reason, you can use:
        // TokenResponse refreshedTokenResponse =
        // client.getTokenResponseByRefreshToken(tokenResponse.getRefreshToken(), null);
        // System.out.println(refreshedTokenResponse.getAccessToken());

        JSONObject json1 = null;
        try {
            // Get info of authenticated user
            Users users = new Users(client);
            json1 = users.getMyInfo();

            // get my uid
            String myId = null;
            try {
                JSONObject user = json1.getJSONObject("user");
                myId = user.getString("id");
                System.out.println(myId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject json2 = null;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("query", "{\n" +
                "             user {\n" +
                "               id\n" +
                "               nid\n" +
                "             }\n" +
                "             organization {\n" +
                "               id\n" +
                "             }\n" +
                "          }");
        try {
            // Set org UID
            // client.setOrgUidHeader("1234567890"); // Organization UID (optional)
            // Get info of authenticated user using GraphQL query
            Graphql graphql = new Graphql(client);
            json2 = graphql.Execute(params);

            // get my uid
            String myUid = null;
            try {
                JSONObject data = json2.getJSONObject("data");
                myUid = data.getJSONObject("user").getString("id");
                System.out.println(myUid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
