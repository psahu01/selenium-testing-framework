package com.adobe.aem.selenium.driver;

import org.openqa.selenium.WebDriver;

public interface InitializeDriver {

	public WebDriver getDriver();

	public void setHeadless(boolean flag);
}
