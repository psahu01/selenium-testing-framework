package com.adobe.aem.selenium.test;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.adobe.aem.selenium.driver.DefaultDriverFactory;
import com.adobe.aem.selenium.listners.AEMWebDriverEventListener;

@Deprecated
public class BaseTestDeprecated {

	private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private WebDriver webDriver;
	private EventFiringWebDriver eventFiringWebDriver;

	protected DefaultDriverFactory driverFactory;

	@BeforeSuite(alwaysRun = true)
	public void instantiateDriverObject() {
		System.out.println("DriverFactory");
		driverFactory = new DefaultDriverFactory();
	}

	// @AfterMethod(alwaysRun = true)
	// public void clearCookies() throws Exception {
	// getDriver().manage().deleteAllCookies();
	// }

	@AfterTest(alwaysRun = true)
	public void quitDriver() {
		logger.info("/***********************************Quiting Driver instance*******************************/");
		if (webDriver != null) {
			webDriver.quit();
		}
	}

	/**
	 * Extend this method if you want to start driver with some other
	 * capabilities other that default capabilities.
	 * 
	 * @return {@link WebDriver}
	 */
	protected WebDriver getDriver() {
		if (null == webDriver) {
			System.out.println("New driver");
			webDriver = driverFactory.getInitializeDriver().getDriver();
			logDriverInfo();
		}
		windowMaximized(webDriver);
		return webDriver;
	}

	/**
	 * Extend this method if you want to register your own EventFiringWebDriver
	 * 
	 * @return {@link EventFiringWebDriver}
	 */
	protected EventFiringWebDriver getAEMEventFiringWebDriver(WebDriver webDriver) {
		return registerWebDriverEventListener(new AEMWebDriverEventListener(), webDriver);
	}

	/**
	 * It is recommended to keep one eventListner for the whole test cycle, if
	 * you want to register another EventFiringWebDriver, first unregister the
	 * {@link EventFiringWebDriver} instance and register with new one. The
	 * reason for this is to keep things simple and linear. However, technically
	 * its possible to register multiple
	 * 
	 * {@link WebDriverEventListener} on with {@link EventFiringWebDriver}
	 */
	protected <T extends WebDriverEventListener> EventFiringWebDriver registerWebDriverEventListener(T eventListener,
			WebDriver driver) {
		if (eventFiringWebDriver == null) {
			eventFiringWebDriver = new EventFiringWebDriver(driver);
		}
		return eventFiringWebDriver.register(eventListener);
	}

	/**
	 * Unregister {@link WebDriverEventListener}
	 * 
	 * return {@link WebDriverEventListener} silently if its null.
	 */
	protected <T extends WebDriverEventListener> EventFiringWebDriver unRegisterWebDriverEventListener(
			T eventListener) {
		// return eventFiringDriver != null ?
		// eventFiringDriver.unregister(eventListener) : eventFiringDriver;
		if (eventFiringWebDriver != null) {
			return eventFiringWebDriver.unregister(eventListener);
		} else {
			logger.error(" Nothing to unregister, returning silently since EventFiringWebDriver is null.");
		}
		return eventFiringWebDriver;
	}

	private static void windowMaximized(WebDriver driver) {
		driver.manage().window().maximize();
	}

	private void logDriverInfo() {
		Capabilities capabilities = driverFactory.getInitializeDriver().getDesiredCapabilities();
		logger.info("Browser                  : " + capabilities.getBrowserName());
		logger.info("Browser Version          : " + capabilities.getVersion());
		logger.info("Plateform                : " + capabilities.getPlatform());
	}
}
