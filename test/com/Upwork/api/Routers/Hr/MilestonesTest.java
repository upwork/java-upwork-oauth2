package com.Upwork.api.Routers.Hr;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Hr.Milestones;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Milestones.class
})
public class MilestonesTest extends Helper {
	@Test public void getActiveMilestone() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getSubmissions() throws Exception {
        // DEPRECATED
    }
	
	@Test public void create() throws Exception {
        // DEPRECATED
    }
	
	@Test public void edit() throws Exception {
        // DEPRECATED
    }
	
	@Test public void activate() throws Exception {
        // DEPRECATED
    }
	
	@Test public void approve() throws Exception {
        // DEPRECATED
    }
	
	@Test public void delete() throws Exception {
        // DEPRECATED
    }
}
