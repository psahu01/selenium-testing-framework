package com.adobe.aem.selenium.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {

	public static ExpectedCondition<Boolean> invisibilityOf(WebElement element) {
		return new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver webDriver) {
				return isInvisible(element);
			}

			@Override
			public String toString() {
				return "invisibility of " + element;
			}
		};
	}

	private static boolean isInvisible(final WebElement element) {
		try {
			return !element.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException ignored) {
			// Ignoring both StaleElementReference and NoSuchElement exception
			return true;
		}
	}
}
