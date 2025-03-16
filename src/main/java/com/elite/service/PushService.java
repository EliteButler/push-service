package com.elite.service;

import com.elite.models.request_response.DeviceRegisterRequest;
import com.elite.models.request_response.DeviceRegisterResponse;
import com.elite.models.request_response.PushRequest;
import com.elite.models.request_response.PushResponse;

public class PushService {
	public PushResponse sendPush(PushRequest pushReq) {
		return new PushResponse();
	}
	
	public DeviceRegisterResponse registerDevice(DeviceRegisterRequest registrationReq) {
		return new DeviceRegisterResponse();
	}
}
