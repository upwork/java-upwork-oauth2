package com.Upwork.api.Routers.Hr;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Hr.Contracts;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
    Contracts.class
})
public class ContractsTest extends Helper {
	@Test public void suspendContract() throws Exception {
        // DEPRECATED
    }
	
	@Test public void restartContract() throws Exception {
        // DEPRECATED
    }
	
	@Test public void endContract() throws Exception {
        // DEPRECATED
    }
}
