package com.elite;

import com.elite.api.PushRequestManager;
import com.elite.configs.ConfigLoader;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;

/**
 * Hello world!
 *
 */
public class PushServiceApplication {
	public static void main(String[] args) {
		ConfigLoader cfgLoader = new ConfigLoader();
		try {
			cfgLoader.loadConfig();
		} catch (Exception e) {
			System.out.println("failed to load config, e: " + e.toString());
			return;
		}
		int port = Integer.valueOf(cfgLoader.get("server.port"));
		int numOfConcurrentRequests = Integer.valueOf(cfgLoader.get("server.concurrent_thread_count"));

		HttpHandler pushRequestHandler = new PushRequestManager();

		Undertow server = Undertow.builder()
				.addHttpListener(port, cfgLoader.get("server.host"))
				.setWorkerThreads(numOfConcurrentRequests)
				.setHandler(pushRequestHandler)
				.build();
		server.start();
	}
}
