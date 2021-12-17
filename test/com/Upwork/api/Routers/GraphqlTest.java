package com.Upwork.api.Routers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Auth;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
    Graphql.class
})
public class GraphqlTest extends Helper {
	@Test public void Execute() throws Exception {
        Graphql graphql = new Graphql(client);
    	JSONObject json = graphql.Execute(new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
    }
}
