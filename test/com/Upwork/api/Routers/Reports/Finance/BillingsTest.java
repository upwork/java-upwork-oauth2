package com.Upwork.api.Routers.Reports.Finance;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Reports.Finance.Billings;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Billings.class
})
public class BillingsTest extends Helper {
	@Test public void getByFreelancer() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByFreelancersTeam() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByFreelancersCompany() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByBuyersTeam() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getByBuyersCompany() throws Exception {
        // DEPRECATED
    }
}
