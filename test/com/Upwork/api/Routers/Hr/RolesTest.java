package com.Upwork.api.Routers.Hr;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Hr.Roles;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
    Roles.class
})
public class RolesTest extends Helper {
	@Test public void getAll() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getBySpecificUser() throws Exception {
        // DEPRECATED
    }
}
