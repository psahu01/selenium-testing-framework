package com.adobe.aem.selenium.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * 
 * @author psahu
 *
 */
// TODO complete this class
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

	@Override
	public WebDriver getDriver(Capabilities capabilities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Capabilities> T getDesiredCapabilities() {
		// TODO Auto-generated method stub
		return null;
	}
}
