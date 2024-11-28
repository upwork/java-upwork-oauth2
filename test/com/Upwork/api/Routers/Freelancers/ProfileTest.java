package com.Upwork.api.Routers.Freelancers;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Freelancers.Profile;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
    Profile.class
})
public class ProfileTest extends Helper {
	@Test public void getSpecific() throws Exception {
        // DEPRECATED
    }

	@Test public void getSpecificBrief() throws Exception {
        // DEPRECATED
    }
}
