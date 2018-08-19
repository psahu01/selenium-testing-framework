package com.adobe.aem.selenium.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Function;

/**
 * @author psahu
 * 
 */

public class ElementHelper {
	/**
	 * Method will locate element on screen and return true if the element is
	 * displayed and enabled on the page.
	 *
	 */
	public static boolean isPageElementPresent(WebDriver driver, WebElement element) {
		try {
			WaitHelper.fluentWait(driver, element, WaitHelper.TINY_WAIT);
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method will locate element on screen and return true if the element is
	 * displayed and enabled on the page.
	 *
	 */
	public static boolean isPageElementPresent(WebDriver driver, By by) {
		try {
			WaitHelper.fluentWait(driver, by, WaitHelper.TINY_WAIT);
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method will locate page element on screen and return true if the Page is
	 * displayed.
	 *
	 */
	public static boolean isPageLoaded(WebDriver driver, WebElement element) {
		try {
			WaitHelper.fluentWait(driver, element, WaitHelper.MEDIUM_WAIT);
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method will locate page element on screen and return true if the Page is
	 * displayed.
	 *
	 */
	public static boolean isPageLoaded(WebDriver driver, By by) {
		try {
			WaitHelper.fluentWait(driver, driver.findElement(by), WaitHelper.MEDIUM_WAIT);
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method will find element during runtime with wait
	 */
	public static WebElement findElementWithWait(WebDriver driver, final By locator) {
		WebElement element = WaitHelper.getMediumWait(driver).until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}

	public static WebElement findElementOnScreenWithTextUsingXPath(WebDriver driver, String text) {
		return WaitHelper.getMediumWait(driver)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[. = '" + text + "']")));
	}

	public static WebElement findElementInsideElement(WebDriver driver, WebElement element, By by) {
		return element.findElement(by);
	}

	public static WebElement findElementInsideWithTextUsingXPath(WebDriver driver, WebElement element, String text) {
		return element.findElement(By.xpath("//*[. = '" + text + "']"));
	}

	public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
		return WaitHelper.getMediumWait(driver).until(ExpectedConditions.visibilityOf(element));
	}

	public static List<WebElement> waitForVisibility(WebDriver driver, List<WebElement> element) {
		return WaitHelper.getMediumWait(driver).until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static WebElement waitForVisibility(WebDriver driver, By locater) {
		return WaitHelper.getLongWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locater));
	}

	public static List<WebElement> getElementList(WebDriver driver, By locater) {
		return WaitHelper.getLongWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locater));
	}

	public static boolean waitForInvisibility(WebDriver driver, WebElement element) {
		return WaitHelper.getTinyWait(driver).until(CustomExpectedConditions.invisibilityOf(element));
	}
}