package com.Upwork.api.Routers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Metadata;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
    Metadata.class
})
public class MetadataTest extends Helper {
	@Test public void getCategories() throws Exception {
		// DEPRECATED
	}
	
	@Test public void getCategoriesV2() throws Exception {
		// DEPRECATED
	}
	
	@Test public void getSkills() throws Exception {
		// DEPRECATED
	}
	
	@Test public void getSkillsV2() throws Exception {
		// DEPRECATED
	}

	@Test public void getSpecialties() throws Exception {
        // DEPRECATED
    }

	@Test public void getRegions() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getTests() throws Exception {
        // DEPRECATED
    }
	
	@Test public void getReasons() throws Exception {
        // DEPRECATED
    }
}
