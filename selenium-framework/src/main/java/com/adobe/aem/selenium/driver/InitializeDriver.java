package com.adobe.aem.selenium.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

/**
 * Interface to be used as a standard for adding new Driver support.
 * 
 * @author psahu
 *
 */
public interface InitializeDriver {

	/**
	 * Create a new {@link WebDriver} object
	 * 
	 * @return WebDriver
	 */
	WebDriver getDriver();

	/**
	 * Create a new {@link WebDriver} object
	 * 
	 * @param capabilities
	 * @return WebDriver
	 */
	WebDriver getDriver(Capabilities capabilities);

	/**
	 * Set capability to run test in headless mode.
	 * 
	 * Check the respective driver support site to confirm if headless mode is
	 * supported
	 * 
	 * @param flag
	 */
	void setHeadless(boolean flag);

	/**
	 * Add {@link Capabilities} other than default Capabilities added be
	 * getDesiredCapabilites implementation
	 * 
	 * @param capabilities
	 * @return Capabilities
	 */
	default <T extends Capabilities> Capabilities addToCapability(T capabilities) {
		return getDesiredCapabilities().merge(capabilities);
	}

	/**
	 * Add default {@link Capabilities} with which driver should be started.
	 * 
	 * @return Capabilities
	 */
	<T extends Capabilities> T getDesiredCapabilities();

}
