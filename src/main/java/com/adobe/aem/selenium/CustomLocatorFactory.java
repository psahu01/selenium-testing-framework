package com.adobe.aem.selenium;
//package com.adobe.aem.seleninum;
//
//import java.lang.reflect.Field;
//
//import org.openqa.selenium.SearchContext;
//import org.openqa.selenium.support.pagefactory.Annotations;
//import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
//import org.openqa.selenium.support.pagefactory.ElementLocator;
//import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
//
//public class CustomLocatorFactory<T extends DefaultElementLocator, E extends Annotations> implements ElementLocatorFactory {
//
//	Class<T> test;
//	private SearchContext searchContext;
//	private int timeOutInSeconds;
//
//	public CustomLocatorFactory(SearchContext searchContext, int timeOutInSeconds, Class<T> clazz) {
//		this.searchContext = searchContext;
//		this.timeOutInSeconds = timeOutInSeconds;]
//		this.test = clazz;
//	}
//
//	@Override
//	public T createLocator(Field field) {
////		return (T) new AemElemementLocator(searchContext, );
//		
//	}
//}