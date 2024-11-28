package com.Upwork.api.Routers.Reports.Finance;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Reports.Finance.Accounts;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
	Accounts.class
})
public class AccountsTest extends Helper {
	@Test public void getOwned() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getSpecific() throws Exception {
        // DEPRECATED
    }
}
