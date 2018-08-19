package com.adobe.aem.selenium.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/**
 * @author psahu
 * 
 */

public class WaitHelper {

	public static final int TINY_WAIT = Integer.getInteger("wait.tinyInSecs", 5);
	public static final int MEDIUM_WAIT = Integer.getInteger("wait.mediumInSecs", 10);
	public static final int LONG_WAIT = Integer.getInteger("wait.longInSecs", 25);
	public static final int POOL_TIME = Integer.getInteger("wait.poolTimeInMilliSecs", 2000);

	/**
	 * Method will wait for the element for specified time and return true if
	 * element is displayed on the screen.
	 * 
	 * @param driver
	 * @param awaitedElement
	 * @return
	 */
	public static Wait<WebDriver> getTinyWait(WebDriver driver) {
		return getWait(driver, TINY_WAIT);
	}

	public static Wait<WebDriver> getMediumWait(WebDriver driver) {
		return getWait(driver, MEDIUM_WAIT);
	}

	public static Wait<WebDriver> getLongWait(WebDriver driver) {
		return getWait(driver, LONG_WAIT);
	}

	private static Wait<WebDriver> getWait(WebDriver driver, int waitTimeInSeconds) {
		return new FluentWait<WebDriver>(driver).withTimeout(waitTimeInSeconds, TimeUnit.SECONDS)
				.pollingEvery(POOL_TIME, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class).ignoring(StaleElementReferenceException.class);
	}

	public static boolean fluentWait(WebDriver driver, final WebElement awaitedElement, int waitTimeInSeconds) {
		Wait<WebDriver> wait = getWait(driver, waitTimeInSeconds);

		Boolean flag = (Boolean) wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return awaitedElement.isEnabled() || awaitedElement.isDisplayed();
			}
		});
		return flag;
	};

	/**
	 * Method will wait for the element for specified time and return true if
	 * element is displayed on the screen.
	 * 
	 * @param driver
	 * @param awaitedElement
	 * @return
	 */
	public static boolean fluentWait(WebDriver driver, final By awaitedElement, int waitTimeInSeconds) {
		Wait<WebDriver> wait = getWait(driver, waitTimeInSeconds);

		Boolean flag = wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(awaitedElement).isEnabled()
						|| driver.findElement(awaitedElement).isDisplayed();
			}
		});
		return flag;
	};

	/**
	 * 
	 * @param driver
	 * @param awaitedElement
	 * @return
	 */
	public static boolean waitForElement(WebDriver driver, By by) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void sleep(int timeOutInSecs) {
		try {
			Thread.sleep(timeOutInSecs * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}