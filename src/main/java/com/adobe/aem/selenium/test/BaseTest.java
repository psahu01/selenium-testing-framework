package com.adobe.aem.selenium.test;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.selenium.driver.DefaultInitializeDriverFactory;
import com.adobe.aem.selenium.driver.InitializeChromeDriver;
import com.adobe.aem.selenium.driver.InitializeDriver;
import com.adobe.aem.selenium.driver.InitializeFireFoxDriver;
import com.adobe.aem.selenium.driver.InitializeIEDriver;

public class BaseTest implements BaseTestInterface {

	private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private WebDriver driver;

	public void quitDriver() {
		logger.info("/***********************************Quiting Driver instance*******************************/");
		driver.quit();
	}

	protected void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	/**
	 * This method for producing {@link WebDriver}. By default this method calls
	 * {@link InitializeDriver}'s getDriver() method Override this method if you
	 * want to use other options. e.g.
	 * 
	 * ChromeOptions chromeOptions = new ChromeOptions(); 
	 * chromeOptions.if(init instanceof InitializeChromeDriver) { 
	 * 		driver = ((InitializeChromeDriver) init).getDriver(chromeOptions);
	 */
	public WebDriver initializeDriver(String browser) {
		if (driver == null) {

			InitializeDriver init = new DefaultInitializeDriverFactory().getInitializeDriver(browser);

			if (init instanceof InitializeChromeDriver) {
				driver = ((InitializeChromeDriver) init).getDriver();
			} else if (init instanceof InitializeFireFoxDriver) {
				driver = ((InitializeFireFoxDriver) init).getDriver();
			} else if (init instanceof InitializeIEDriver) {
				driver = ((InitializeIEDriver) init).getDriver();
			} else {
				driver = init.getDriver();
			}
			logger.info("/**********************************Selenium Driver Initialized**************************/");
		}
		logDriverInfo();
		return driver;
	}

	public <T extends WebDriverEventListener> EventFiringWebDriver registerEventFiringWebDriver(T eventListner, WebDriver driver) {
		return new EventFiringWebDriver(driver).register(eventListner);
	}

	private void logDriverInfo() {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		logger.info("Browser                  : " + capabilities.getBrowserName());
		logger.info("Browser Version          : " + capabilities.getVersion());
		logger.info("Plateform                : " + capabilities.getPlatform());
	}
}
