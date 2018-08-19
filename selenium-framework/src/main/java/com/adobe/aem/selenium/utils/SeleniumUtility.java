package com.adobe.aem.selenium.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumUtility {

	public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
	}

	public static void moveToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.pause(Duration.ofMillis(1000)).moveToElement(element).pause(Duration.ofMillis(500)).build().perform();
	}

	public static String alertPopup(WebDriver driver) {
		if (isAlertPresent(driver))
			return driver.switchTo().alert().getText();
		else
			return null;
	}

	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException exception) {
			return false;
		}
	}

	public static void alertPopup(WebDriver driver, String action) {
		if (isAlertPresent(driver)) {
			Alert alert = driver.switchTo().alert();
			switch (action) {
			case "accept":
				alert.accept();
				break;
			case "dismiss":
				alert.dismiss();
				break;
			}
		}
	}

	public static void lazyClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.pause(Duration.ofMillis(1000)).click(element).pause(Duration.ofMillis(500));
	}

	public static void lazyClick(WebDriver driver, WebElement element, long timeInMilli) {
		Actions action = new Actions(driver);
		action.pause(Duration.ofMillis(timeInMilli)).click(element).pause(Duration.ofMillis(500));
	}

	public static void switchToIframe(WebDriver driver, String iframe) {
		try {
			driver.switchTo().frame(iframe);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + iframe + e.getStackTrace());
		} catch (Throwable e) {
			System.out.println("Unable to navigate to frame with id " + iframe + e.getStackTrace());
		}
	}

	public static void switchToNewTab(WebDriver driver) {
		for (String browserTab : driver.getWindowHandles()) {
			driver.switchTo().window(browserTab);
		}
	}

	public static void clearAndSendKeys(WebElement element, String input) {
		element.clear();
		element.sendKeys(input);
	}

	public static void lazySendKeys(WebDriver driver, WebElement element, String input) {
		Actions action = new Actions(driver);
		action.pause(Duration.ofSeconds(1)).sendKeys(element, input).build().perform();
	}

	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste
		// operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		WaitHelper.sleep(WaitHelper.TINY_WAIT);
	}

	public static void closeAllButMainWindow(WebDriver driver) {
		String mainWindow = driver.getWindowHandle();

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(mainWindow)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
	}
}
