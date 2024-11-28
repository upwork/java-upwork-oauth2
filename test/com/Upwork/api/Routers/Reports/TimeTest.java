package com.Upwork.api.Routers.Reports;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Reports.Time;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Time.class
})
public class TimeTest extends Helper {
	@Test public void getByTeamFull() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByTeamLimited() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByAgency() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByCompany() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByFreelancerLimited() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByFreelancerFull() throws Exception {
        // DEPRECATED
    }
}
