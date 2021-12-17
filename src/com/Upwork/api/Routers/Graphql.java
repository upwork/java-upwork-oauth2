/**
 * Copyright 2021 Upwork
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
 */

package com.Upwork.api.Routers;

import java.util.HashMap;

import com.Upwork.ClassPreamble;
import com.Upwork.api.OAuthClient;

import org.json.JSONException;
import org.json.JSONObject;

@ClassPreamble(
        author = "Maksym Novozhylov <mnovozhilov@upwork.com>",
        date = "12/16/2021",
        currentRevision = 1,
        lastModified = "12/16/2021",
        lastModifiedBy = "Maksym Novozhylov",
        reviewers = { "Yiota Tsakiri" }
)
public final class Graphql {

    final static String ENTRY_POINT = "graphql";

    private OAuthClient oClient = null;

    public Graphql(OAuthClient client) {
        oClient = client;
        oClient.setEntryPoint(ENTRY_POINT);
    }

    /**
     * Execute GraphQL request
     * 
     * @throws JSONException If error occurred
     * @return {@link JSONObject}
     */
    public JSONObject Execute(HashMap<String, String> params) throws JSONException {
        return oClient.post("", params);
    }
}
