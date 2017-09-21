package com.adobe.aem.selenium.driver;

import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * 
 * @author psahu
 *
 */
public class InitializeIEDriver implements InitializeDriver {

	public InternetExplorerDriver getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHeadless(boolean flag) {
	}

	public static String driverName() {
		return "ie";
	}
}
