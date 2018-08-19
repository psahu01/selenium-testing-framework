package com.adobe.aem.selenium.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.selenium.listners.AEMWebDriverEventListener;

/**
 * Default DriverFactory, which provides driver factory. This class is designed
 * to be used for getting WebDriver, EvnetFiringWebDriver and deciding which
 * driver to initialize on the basis of system property browser.
 * 
 * @author psahu
 *
 */
public class DefaultDriverFactory implements DriverFactory {

	private static Logger logger = LoggerFactory.getLogger(DefaultDriverFactory.class);

	private InitializeDriver initializeDriver;
	private DriverType selectedDriverType;
	private WebDriver webDriver;
	private EventFiringWebDriver eventFiringWebDriver;

	private final DriverType defaultDriverType = DriverType.CHROME;
	private final String browser = System.getProperty("browser", defaultDriverType.toString()).toUpperCase();

	public final InitializeDriver getInitializeDriver() {
		if (null == initializeDriver) {
			getDriverType();
			instantiateInitializeDriver();
		}
		return initializeDriver;
	}

	/**
	 * Extend this method if you want to start driver with some other
	 * capabilities other that default capabilities.
	 * 
	 * @return {@link WebDriver}
	 */
	public WebDriver getWebDriver() {
		if (null == webDriver) {
			if (Boolean.getBoolean("executeOnGrid")) {
				DesiredCapabilities capabilities = getInitializeDriver().getDesiredCapabilities();
				try {
					webDriver = new RemoteWebDriver(new URL("http://" + System.getProperty("hub.hostname", "localhost")
							+ ":" + System.getProperty("hub.port", "4444") + "/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else {
				webDriver = getInitializeDriver().getDriver();
			}
		}
		return webDriver;
	}

	public void quitDriver() {
		if (null != webDriver) {
			webDriver.quit();
		}
	}

	public EventFiringWebDriver getAEMEventFiringWebDriver(WebDriver webDriver) {
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
	public <T extends WebDriverEventListener> EventFiringWebDriver registerWebDriverEventListener(T eventListener,
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
	public <T extends WebDriverEventListener> EventFiringWebDriver unRegisterWebDriverEventListener(T eventListener) {
		// return eventFiringDriver != null ?
		// eventFiringDriver.unregister(eventListener) : eventFiringDriver;
		if (eventFiringWebDriver != null) {
			return eventFiringWebDriver.unregister(eventListener);
		} else {
			logger.error(" Nothing to unregister, returning silently since EventFiringWebDriver is null.");
		}
		return eventFiringWebDriver;
	}

	private void instantiateInitializeDriver() {
		initializeDriver = selectedDriverType.getInitializeDriver();
	}

	private void getDriverType() {
		DriverType driverType = defaultDriverType;
		try {
			driverType = DriverType.valueOf(browser);
		} catch (IllegalArgumentException ignored) {
			System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
		} catch (NullPointerException ignored) {
			System.err.println("No driver specified, defaulting to '" + driverType + "'...");
		}
		selectedDriverType = driverType;
	}
}
