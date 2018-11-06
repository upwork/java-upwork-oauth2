package com.Upwork.api.Routers.Organization;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Organization.Teams;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
	Teams.class
})
public class TeamsTest extends Helper {
	@Test public void getList() throws Exception {
		Teams teams = new Teams(client);
    	JSONObject json = teams.getList();
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void getUsersInTeam() throws Exception {
		Teams teams = new Teams(client);
    	JSONObject json = teams.getUsersInTeam("1234");
        
        assertTrue(json instanceof JSONObject);
	}
}