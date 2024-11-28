package com.Upwork.api.Routers.Activities;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Activities.Engagement;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
    Engagement.class
})
public class EngagementTest extends Helper {
	@Test public void getSpecific() throws Exception {
        // DEPRECATED
    }
	
	@Test public void assign() throws Exception {
        // DEPRECATED
    }
	
	@Test public void assignToEngagement() throws Exception {
        // DEPRECATED
    }
}
