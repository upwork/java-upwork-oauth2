package com.Upwork.api.Routers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Workdays;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Workdiary.class
})
public class WorkdaysTest extends Helper {
	@Test public void getByCompany() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByContract() throws Exception {
        // DEPRECATED
    }
}
