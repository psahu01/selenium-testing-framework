package com.adobe.aem.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * A factory for producing {@link WebDriver} and {@link EventFiringWebDriver}.
 * Implement this interface to write custom WebDriver or EventFiringWebDriver
 * initialization.
 * 
 * @author psahu
 *
 */
public interface DriverFactory {

	InitializeDriver getInitializeDriver();
	WebDriver getWebDriver();
	void quitDriver();
	EventFiringWebDriver getAEMEventFiringWebDriver(WebDriver webDriver);
	<T extends WebDriverEventListener> EventFiringWebDriver registerWebDriverEventListener(T eventListener, WebDriver driver);
	<T extends WebDriverEventListener> EventFiringWebDriver unRegisterWebDriverEventListener(T eventListener);
}
