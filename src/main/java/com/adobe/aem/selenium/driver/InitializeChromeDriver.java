package com.adobe.aem.selenium.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 
 * @author psahu
 *
 */
public class InitializeChromeDriver implements InitializeDriver {

	private ChromeOptions chromeOptions;

	public ChromeDriver getDriver() {
		return getDriver(getChromeOptions());
	}

	public ChromeDriver getDriver(ChromeOptions chromeOptions) {
		return new ChromeDriver(chromeOptions);
	}

	public ChromeDriver getDriver(Capabilities capabilities) {
		return new ChromeDriver(capabilities);
	}

	public ChromeDriver getDriver(IChromeOptions iChromeOptions) {
		return getDriver(iChromeOptions.getChromeOptions());
	}

	public ChromeOptions getChromeOptions() {
		if (chromeOptions == null) {
			chromeOptions = new ChromeOptions();
		}
		// Just added one argument for example
		// chromeOptions.addArguments("--start-maximized");
		return chromeOptions;
	}

	public void setHeadless(boolean flag) {
		chromeOptions = getChromeOptions();
		if (flag) {
			chromeOptions.addArguments("--headless");
		}
	}
	
	public static String driverName() {
		return "chrome";
	}
}
