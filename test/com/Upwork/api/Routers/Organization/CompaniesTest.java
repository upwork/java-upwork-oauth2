package com.Upwork.api.Routers.Organization;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Organization.Companies;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Companies.class
})
public class CompaniesTest extends Helper {
	@Test public void getList() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getSpecific() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getTeams() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getUsers() throws Exception {
        // DEPRECATED
    }
}
