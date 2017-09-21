package com.adobe.aem.selenium.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 
 * @author psahu
 *
 */
public class InitializeFireFoxDriver implements InitializeDriver {

	public FirefoxDriver getDriver() {
		return new FirefoxDriver();
	}

	public FirefoxDriver getDriver(Capabilities capabilities) {
		return new FirefoxDriver(capabilities);
	}

	@Override
	public void setHeadless(boolean flag) {
	}

	public static String driverName() {
		return "firefox";
	}
}
