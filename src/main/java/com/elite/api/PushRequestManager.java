package com.elite.api;

import java.lang.runtime.ObjectMethods;

import com.elite.models.request_response.PushRequest;
import com.elite.models.request_response.PushResponse;
import com.elite.service.PushService;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PushRequestManager implements HttpHandler {
	
	private static HttpString METHOD_POST = new HttpString("POST");
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final PushService pushService = new PushService();

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		if (exchange.isInIoThread()) {
			exchange.dispatch(() -> {
				routeRequest(exchange);
			});
		} else {
			routeRequest(exchange);
		}
	}

	// ensures no exception is thrown making main clean
	public void routeRequest(HttpServerExchange exchange) {
		//todo:- validate API key
		System.out.println(exchange.getRelativePath());
		if (exchange.getRequestMethod().equals(METHOD_POST) && exchange.getRequestPath().equals("/push")) {
			exchange.getRequestReceiver().receiveFullBytes((ex, data) -> {
				try {
					PushRequest request = objectMapper.readValue(data, PushRequest.class);
					PushResponse response = pushService.sendPush(request);
					exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
					exchange.getResponseSender().send(objectMapper.writeValueAsString(response));
					exchange.endExchange();
					return;
				} catch (Exception e) {
					e.printStackTrace();
					exchange.setResponseCode(500);
					exchange.getResponseSender().send("completed, but failed");
					exchange.endExchange();
					return;
				}
			});
		}
		
		if (exchange.getRequestMethod().equals(METHOD_POST) && exchange.getRequestPath() ==  "/register") {
			// should accept device ID & corresponding meta.
		}
		exchange.endExchange();
		return;
	}

}
