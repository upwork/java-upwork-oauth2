package com.Upwork.api.Routers.Hr;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Hr.Engagements;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Engagements.class
})
public class EngagementsTest extends Helper {
	@Test public void getList() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getSpecific() throws Exception {
        // DEPRECATED
    }
}
