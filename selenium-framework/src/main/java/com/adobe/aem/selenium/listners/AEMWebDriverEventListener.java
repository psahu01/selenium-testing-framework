package com.adobe.aem.selenium.listners;

/**
 * This class is designed for enabling logging.
 * 
 * @author psahu
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AEMWebDriverEventListener extends AbstractWebDriverEventListener {
	private static Logger logger = LoggerFactory.getLogger(AEMWebDriverEventListener.class);

	public void beforeAlertAccept(WebDriver driver) {
		// Do nothing.
	}

	public void afterAlertAccept(WebDriver driver) {
		// Do nothing.
	}

	public void afterAlertDismiss(WebDriver driver) {
		// Do nothing.
	}

	public void beforeAlertDismiss(WebDriver driver) {
		// Do nothing.
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		logger.debug("Navigating to " + url);
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		logger.debug("Page load is: " + js.executeScript("return document.readyState").toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		// Do nothing.
	}

	public void afterNavigateBack(WebDriver driver) {
		// Do nothing.
	}

	public void beforeNavigateForward(WebDriver driver) {
		// Do nothing.
	}

	public void afterNavigateForward(WebDriver driver) {
		// Do nothing.
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// Do nothing.
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// Do nothing.
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// Do nothing.
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// Do nothing.
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		String[] locatorString = element.toString().split("> ");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='1px solid red'", element);
		logger.debug("Click on element " + element.getTagName() + "[" + locatorString[locatorString.length - 1]
				+ " at location (" + element.getLocation().getX() + "," + element.getLocation().getY() + ")");
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		// Do Nothing
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		StringBuilder sb = new StringBuilder();
		for (CharSequence c : keysToSend) {
			sb.append(c);
		}
		String[] locatorString = element.toString().split("> ");
		logger.debug(
				"Writing " + sb + " on element " + element.getTagName() + "[" + locatorString[locatorString.length - 1]
						+ " at location (" + element.getLocation().getX() + "," + element.getLocation().getY() + ")");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// Do nothing.
	}

	public void beforeScript(String script, WebDriver driver) {
		// Do nothing
	}

	public void afterScript(String script, WebDriver driver) {
		// Do nothing
	}

	public void onException(Throwable throwable, WebDriver driver) {
		// Do nothing
	}
}
