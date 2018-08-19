package com.adobe.aem.selenium.driver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 
 * @author psahu
 *
 */
public class InitializeChromeDriver implements InitializeDriver {
	// TODO Add loggers
	private ChromeOptions chromeOptions;
	private DesiredCapabilities capabilities;

	public InitializeChromeDriver() {
		capabilities = getDesiredCapabilities();
		chromeOptions = getChromeOptions();
	}

	public ChromeDriver getDriver() {
		return getDriver(getDesiredCapabilities());
	}

	public ChromeDriver getDriver(ChromeOptions chromeOptions) {
		return getDriver(addToCapability(chromeOptions));
	}

	public ChromeDriver getDriver(Capabilities capabilities) {
		return new ChromeDriver(capabilities);
	}

	public void setHeadless(boolean flag) {
		if (flag) {
			getChromeOptions().addArguments("--headless");
		}
		addToCapability(chromeOptions);
	}

	public ChromeOptions getChromeOptions() {
		chromeOptions = null == chromeOptions ? new ChromeOptions() : chromeOptions;
		return chromeOptions;
	}

	public DesiredCapabilities getDesiredCapabilities() {
		if (null == capabilities) {
			capabilities = DesiredCapabilities.chrome();
			Map<String, Object> chromePreferences = new HashMap<String, Object>();
			chromePreferences.put("profile.default_content_settings.popups", 0);
			chromePreferences.put("download.default_directory",
					System.getProperty("downloadFilePath", "target/download-files"));
			chromePreferences.put("profile.password_manager_enabled", "false");
			setHeadless(Boolean.getBoolean("headless"));
			getChromeOptions().addArguments("--start-maximized");
			capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			capabilities.setCapability("chrome.prefs", chromePreferences);
			addToCapability(getChromeOptions());
		}
		return capabilities;
	}

	private Capabilities addToCapability(ChromeOptions options) {
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return capabilities;
	}
}
