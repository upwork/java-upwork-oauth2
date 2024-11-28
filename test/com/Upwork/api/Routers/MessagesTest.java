package com.Upwork.api.Routers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.Upwork.api.Routers.Helper;
import com.Upwork.api.Routers.Messages;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({
    Messages.class
})
public class MessagesTest extends Helper {
	@Test public void getRooms() throws Exception {
		// DEPRECATED
	}
	
	@Test public void getRoomDetails() throws Exception {
		// DEPRECATED
	}

	@Test public void getRoomMessages() throws Exception {
		// DEPRECATED
	}

	@Test public void getRoomByOffer() throws Exception {
		// DEPRECATED
	}

	@Test public void getRoomByApplication() throws Exception {
		// DEPRECATED
	}

	@Test public void getRoomByContract() throws Exception {
		// DEPRECATED
	}
	
	@Test public void createRoom() throws Exception {
		// DEPRECATED
	}
	
	@Test public void sendMessageToRoom() throws Exception {
		// DEPRECATED
	}
	
	@Test public void sendMessageToRooms() throws Exception {
		// DEPRECATED
	}

	@Test public void updateRoomSettings() throws Exception {
		// DEPRECATED
	}

	@Test public void updateRoomMetadata() throws Exception {
		// DEPRECATED
	}
}
