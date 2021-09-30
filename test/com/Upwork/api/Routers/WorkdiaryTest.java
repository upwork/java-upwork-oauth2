package com.Upwork.api.Routers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Workdiary;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Workdiary.class
})
public class WorkdiaryTest extends Helper {
	@Test public void getByContract() throws Exception {
		Workdiary workdiary = new Workdiary(client);
    	JSONObject json = workdiary.getByContract("1234", "date", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
}
