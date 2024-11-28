package com.Upwork.api.Routers.Organization;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Organization.Teams;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Teams.class
})
public class TeamsTest extends Helper {
	@Test public void getList() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getUsersInTeam() throws Exception {
        // DEPRECATED
    }
}
