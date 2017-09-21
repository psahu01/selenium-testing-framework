package com.adobe.aem.selenium;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class AemElementLocatorFactory implements ElementLocatorFactory {

	private SearchContext searchContext;
	private int timeOutInSeconds;

	public AemElementLocatorFactory(SearchContext searchContext, int timeOutInSeconds) {
		this.searchContext = searchContext;
		this.timeOutInSeconds = timeOutInSeconds;
	}

	@Override
	public ElementLocator createLocator(Field field) {
		return new AemElementLocator(searchContext, field, timeOutInSeconds);
	}
}
