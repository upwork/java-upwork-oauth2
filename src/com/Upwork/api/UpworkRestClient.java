/**
 * Copyright 2009 Upwork
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
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

@ClassPreamble (
	author = "Maksym Novozhylov <mnovozhilov@upwork.com>",
	date = "5/31/2014",
	currentRevision = 2,
	lastModified = "11/01/2018",
	lastModifiedBy = "Maksym Novozhylov",
	reviewers = {"Yiota Tsakiri"}
)
public class UpworkRestClient {
    final static int HTTP_RESPONSE_503 = 503;
    
    /**
     * Executes the given requests.
     * 
     * @param   request Request
     * @throws  JSONException
     * @return  {@link JSONObject}
     * */
   	public static JSONObject executeRequest(HttpRequest request) throws JSONException {
    	JSONObject json = null;
        HttpResponse response = null;

        try {
            // No matter what, do not throw for non 2xx responses
            request.setThrowExceptionOnExecuteError(false);
            // Process the request normally
            response = request.execute();
            if(response.getStatusCode() == 200) {
                if (response.getContent() != null) {
                    json = new JSONObject(response.parseAsString());
                }
            } else {
            	json = UpworkRestClient.genError(response);
            }
        }catch (IOException e) {
            json = UpworkRestClient.genError(HTTP_RESPONSE_503, "Exception: IOException");
        } catch (JSONException e) {
            json = UpworkRestClient.genError(HTTP_RESPONSE_503, "Exception: JSONException");  
        } catch (Exception e) {
            json = UpworkRestClient.genError(HTTP_RESPONSE_503, "Exception: Exception " + e.toString());
        } finally {
            if (response != null) {
                try {
                    response.disconnect();
                } catch (IOException e) {
                    json = genIOError(e);
                }
            }
        }
        
        return json;
    }

    /**
     * Generates errors as JSONObject.
     *
     * @param e IOException
     * @return JSONObject
     * @throws JSONException
     */
    static JSONObject genIOError(IOException e) throws JSONException {
   	    return genError(HTTP_RESPONSE_503, "Exception: IOException " + e.toString());
    }
    
    /**
     * Generate error as JSONObject
     * 
     * @param   code Error code
     * @param   message Error message
     * @throws  JSONException
     * @return  {@link JSONObject}
     * */
    private static JSONObject genError(Integer code, String message) throws JSONException {
        // TODO: HTTP-Status (404, etc), for now return status line
        return new JSONObject("{error: {code: \"" + code.toString() + "\", message: \"" + message + "\"}}");
    }
    
    /**
     * Generate error as JSONObject
     *
     * @param response HttpResponse
     * @throws  JSONException
     * @return  {@link JSONObject}
     * */
    private static JSONObject genError(HttpResponse response) throws JSONException {
        final HttpHeaders headers = response.getHeaders();
        String code = headers.getFirstHeaderStringValue("X-Upwork-Error-Code");
        String message = headers.getFirstHeaderStringValue("X-Upwork-Error-Message");
    	
    	if (code == null) {
    		code = Integer.toString(response.getStatusCode());
    	}
    	
    	if (message == null) {
    		message = response.getStatusMessage();
    	}

    	return new JSONObject("{error: {code: \"" + code + "\", message: \"" + message + "\"}}");
    }
}
