package com.adobe.aem.selenium.listners;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static com.adobe.aem.selenium.test.BaseTest.getDriver;

public class CustomTestNGListner extends TestListenerAdapter {

	private static Logger logger = LoggerFactory.getLogger(CustomTestNGListner.class);

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("********** Starting Test Case " + result.getName()
				+ " **************");
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(
				"********** Test Case " + result.getName() + " : PASS **************");
	}

	@Override
	public void onTestFailure(ITestResult failingTest) {
		super.onTestFailure(failingTest);
		logger.error("********** Test Case " + failingTest.getInstanceName() + "." + failingTest.getName()
				+ " : FAILED **************");
		try {
			WebDriver driver = getDriver();
			String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
			String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_"
					+ failingTest.getName() + ".png";
			File screenshot = new File(screenshotAbsolutePath);
			if (createFile(screenshot)) {
				try {
					writeScreenshotToFile(driver, screenshot);
				} catch (ClassCastException weNeedToAugmentOurDriverObject) {
					writeScreenshotToFile(new Augmenter().augment(driver), screenshot);
				}
				logger.debug("Written screenshot to " + screenshotAbsolutePath);
			} else {
				logger.debug("Unable to create " + screenshotAbsolutePath);
			}
		} catch (Exception ex) {
			logger.error("Unable to capture screenshot...");
			ex.printStackTrace();
		}
	}

	private boolean createFile(File screenshot) {
		boolean fileCreated = false;

		if (screenshot.exists()) {
			fileCreated = true;
		} else {
			File parentDirectory = new File(screenshot.getParent());
			if (parentDirectory.exists() || parentDirectory.mkdirs()) {
				try {
					fileCreated = screenshot.createNewFile();
				} catch (IOException errorCreatingScreenshot) {
					errorCreatingScreenshot.printStackTrace();
				}
			}
		}

		return fileCreated;
	}

	private void writeScreenshotToFile(WebDriver driver, File screenshot) {
		try {
			FileOutputStream screenshotStream = new FileOutputStream(screenshot);
			screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			screenshotStream.close();
		} catch (IOException unableToWriteScreenshot) {
			logger.error("Unable to write " + screenshot.getAbsolutePath());
			unableToWriteScreenshot.printStackTrace();
		}
	}
}