package com.adobe.aem.selenium.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.adobe.aem.selenium.driver.DefaultDriverFactory;
import com.adobe.aem.selenium.driver.DriverFactory;
import com.adobe.aem.selenium.listners.CustomTestNGListner;

@Listeners({ CustomTestNGListner.class })
public class BaseTest {

	private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static List<DriverFactory> webDriverThreadPool = Collections
			.synchronizedList(new ArrayList<DriverFactory>());
	private static ThreadLocal<DriverFactory> driverFactory;

	@BeforeSuite(alwaysRun = true)
	public static void instantiateDriverObject() {
		driverFactory = new ThreadLocal<DriverFactory>() {
			@Override
			protected DriverFactory initialValue() {
				DriverFactory driverFactory = new DefaultDriverFactory();
				webDriverThreadPool.add(driverFactory);
				return driverFactory;
			}
		};
	}

	public void clearCookies() {
		getDriver().manage().deleteAllCookies();
	}

	@AfterSuite(alwaysRun = true)
	public void quitDriver() {
		logger.info("/***********************************Quiting Driver instance*******************************/");
		for (DriverFactory driverFactory : webDriverThreadPool) {
			driverFactory.quitDriver();
		}

	}

	public static WebDriver getDriver() {
		return driverFactory.get().getWebDriver();
	}

	public static EventFiringWebDriver getAEMEventFiringWebDriver(WebDriver webDriver) {
		return driverFactory.get().getAEMEventFiringWebDriver(webDriver);
	}

	public static void windowMaximized(WebDriver driver) {
		driver.manage().window().maximize();
	}

	private static void logDriverInfo() {
		Capabilities capabilities = driverFactory.get().getInitializeDriver().getDesiredCapabilities();
		logger.info("Browser                  : " + capabilities.getBrowserName());
		logger.info("Browser Version          : " + capabilities.getVersion());
		logger.info("Plateform                : " + capabilities.getPlatform());
	}
}