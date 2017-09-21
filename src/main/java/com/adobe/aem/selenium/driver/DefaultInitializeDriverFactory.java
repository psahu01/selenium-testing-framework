package com.adobe.aem.selenium.driver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

/**
 * Initialize centrally handles Selenium WebDriver creation
 * 
 * @author psahu
 *
 */
public class DefaultInitializeDriverFactory implements InitializeDriverFactory {
	private WebDriver sharedDriver;
	private Map<String, InitializeDriver> driversMap;

	public DefaultInitializeDriverFactory() {
		driversMap = new HashMap<String, InitializeDriver>();
		defaultDrivers();
	}

	public DefaultInitializeDriverFactory(Map<String, InitializeDriver> drivers) {
		this();
		driversMap.putAll(drivers);
	}

	public void addInitializeDriver(String className, InitializeDriver value) {
		driversMap.put(className, value);
	}

	public void closeDriver() {
		if (sharedDriver != null) {
			sharedDriver.quit();
		}
	}

	public WebDriver getSharedDriver(String initializeDriverName) {
		sharedDriver = driversMap.get(initializeDriverName).getDriver();
		return sharedDriver;
	}

	public InitializeDriver getInitializeDriver(String initializeDriverName) {
		return driversMap.get(initializeDriverName);
	}

	private void defaultDrivers() {
		addInitializeDriver(InitializeFireFoxDriver.driverName(), new InitializeFireFoxDriver());
		addInitializeDriver(InitializeChromeDriver.driverName(), new InitializeChromeDriver());
		addInitializeDriver(InitializeIEDriver.driverName(), new InitializeFireFoxDriver());
	}
}