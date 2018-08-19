package com.adobe.aem.selenium.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 
 * @author psahu
 *
 */
public class InitializeFireFoxDriver implements InitializeDriver {

	private DesiredCapabilities capabilities;
	private FirefoxOptions firefoxOptions;
	private FirefoxProfile firefoxProfile;
	private FirefoxBinary firefoxBinary;

	public InitializeFireFoxDriver() {
		firefoxProfile = getFirefoxProfile();
		firefoxOptions = getFirefoxOptions();
		firefoxBinary = getFirefoxBinary();
		capabilities = getDesiredCapabilities();
	}

	public FirefoxDriver getDriver() {
		return getDriver(getDesiredCapabilities());
	}

	public FirefoxDriver getDriver(FirefoxOptions firefoxOptions) {
		return getDriver(addToCapability(firefoxOptions));
	}

	public FirefoxDriver getDriver(Capabilities capabilities) {
		return new FirefoxDriver(capabilities);
	}

	public FirefoxOptions getFirefoxOptions() {
		return null == firefoxOptions ? new FirefoxOptions() : firefoxOptions;
	}

	public FirefoxProfile getFirefoxProfile() {
		return null == firefoxProfile ? new FirefoxProfile() : firefoxProfile;
	}

	public FirefoxBinary getFirefoxBinary() {
		return null == firefoxBinary ? new FirefoxBinary() : firefoxBinary;
	}

	public void setHeadless(boolean flag) {
		if (flag) {
			firefoxBinary.addCommandLineOptions("-headless");
		}
		addToCapability(firefoxBinary);
	}

	public DesiredCapabilities getDesiredCapabilities() {
		if (null == capabilities) {
			String downloadFilePath = System.getProperty("downloaded-files", "target/downloaded-files");
			firefoxProfile.setAcceptUntrustedCertificates(false);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
			firefoxProfile.setPreference("browser.download.dir", downloadFilePath);
			firefoxProfile.setPreference("browser.download.downloadDir", downloadFilePath);
			firefoxProfile.setPreference("browser.download.defaultFolder", downloadFilePath);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/anytext,text/plain,text/html,application/plain,application/pdf");
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			// TODO Need to find out a way to run firefox in headless mode
			// currently not supported for Win
			// setHeadless(Boolean.getBoolean("headless"));
			addToCapability(firefoxProfile);
		}
		return capabilities;
	}

	private DesiredCapabilities addToCapability(FirefoxOptions firefoxOptions) {
		capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
		return capabilities;
	}

	private DesiredCapabilities addToCapability(FirefoxProfile firefoxProfile) {
		capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
		return capabilities;
	}

	private DesiredCapabilities addToCapability(FirefoxBinary firefoxBinary) {
		capabilities.setCapability(FirefoxDriver.BINARY, firefoxBinary);
		return capabilities;
	}
}
