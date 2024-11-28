package com.Upwork.api.Routers.Hr.Clients;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Hr.Clients.Offers;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Offers.class
})
public class OffersTest extends Helper {
	@Test public void getList() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getSpecific() throws Exception {
        // DEPRECATED
    }
	
	@Test public void makeOffer() throws Exception {
        // DEPRECATED
    }
}
