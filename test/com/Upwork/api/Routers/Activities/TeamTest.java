package com.Upwork.api.Routers.Activities;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Activities.Team;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
    Team.class
})
public class TeamTest extends Helper {
	@Test public void getList() throws Exception {
        // DEPRECATED
    }

	@Test public void getSpecificList() throws Exception {
		// DEPRECATED
	}

	@Test public void addActivity() throws Exception {
        // DEPRECATED
    }

	@Test public void updateActivity() throws Exception {
        // DEPRECATED
    }

	@Test public void archiveActivity() throws Exception {
        // DEPRECATED
    }

	@Test public void unarchiveActivity() throws Exception {
        // DEPRECATED
    }

	@Test public void updateBatch() throws Exception {
        // DEPRECATED
    }

}
