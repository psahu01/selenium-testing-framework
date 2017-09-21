package com.adobe.aem.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public interface BaseTestInterface {

	public WebDriver initializeDriver(String browser);

	public void quitDriver();

	public <T extends WebDriverEventListener> EventFiringWebDriver registerEventFiringWebDriver(T eventListner, WebDriver driver);
}
