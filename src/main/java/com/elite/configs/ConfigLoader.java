package com.elite.configs;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	private Properties applicationProperties = new Properties();
	
	public void loadConfig() {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
			if (input == null) {
				throw new Exception("input is null");
			}
			applicationProperties.load(input);
        } catch (Exception e) {
        	e.printStackTrace();
            throw new RuntimeException("Failed to load config", e);
        }
	}
	
	public String get(String configName) {
		if (!applicationProperties.containsKey(configName)) {
			return null;
		}
		return (String) applicationProperties.get(configName);
	}
}
