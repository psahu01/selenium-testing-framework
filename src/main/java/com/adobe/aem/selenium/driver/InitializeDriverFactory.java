package com.adobe.aem.selenium.driver;

import org.openqa.selenium.WebDriver;

/**
 * 
 * A factory for producing {@link WebDriver}s or {@link InitializeDriver}. It is
 * expected that a new WebDriver or new InitializeDriver will be returned per
 * call.
 * 
 * {@author psahu}
 */
public interface InitializeDriverFactory {
	/**
	 * When a new {@link WebDriver} is required, this method will be called.
	 *
	 * @param browser
	 *            name
	 * @return A WebDriver object.
	 */
	// TODO need to decide whether to keep browser as param or not
	public WebDriver getSharedDriver(String driverName);

	/**
	 * When a new {@link InitializeDriver} is required, this method will be
	 * called.
	 *
	 * @param browser
	 *            name
	 * @return A InitializeDriver object.
	 */
	public InitializeDriver getInitializeDriver(String driverName);
}
