package com.adobe.aem.selenium;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.SystemClock;

public class AemElementLocator extends AjaxElementLocator {

	/**
	 * Use this constructor in order to process custom annotations.
	 *
	 * @param context
	 *            The context to use when finding the element
	 * @param timeOutInSeconds
	 *            How long to wait for the element to appear. Measured in
	 *            seconds.
	 * @param annotations
	 *            AbstractAnnotations class implementation
	 */
	public AemElementLocator(SearchContext context, int timeOutInSeconds, AbstractAnnotations annotations) {
		this(new SystemClock(), context, timeOutInSeconds, annotations);
	}

	public AemElementLocator(Clock clock, SearchContext context, int timeOutInSeconds,
			AbstractAnnotations annotations) {
		super(context, timeOutInSeconds, annotations);
	}

	/**
	 * Main constructor.
	 *
	 * @param searchContext
	 *            The context to use when finding the element
	 * @param field
	 *            The field representing this element
	 * @param timeOutInSeconds
	 *            How long to wait for the element to appear. Measured in
	 *            seconds.
	 */
	public AemElementLocator(SearchContext searchContext, Field field, int timeOutInSeconds) {
		this(new SystemClock(), searchContext, field, timeOutInSeconds);
	}

	public AemElementLocator(Clock clock, SearchContext searchContext, Field field, int timeOutInSeconds) {
		// TODO replace annotations by custom annotations
		this(clock, searchContext, timeOutInSeconds, new Annotations(field));
	}

	/**
	 * Overriding the isElementUsable to perform action on visible items only
	 *
	 * <pre>
	 * {@code
	 *   return element.isDisplayed();
	 * }
	 * </pre>
	 *
	 * @param element
	 *            The element to use
	 * @return Whether or not it meets your criteria for "found"
	 */
	protected boolean isElementUsable(WebElement element) {
		return (element.isDisplayed() && element.isEnabled());
	}
}
