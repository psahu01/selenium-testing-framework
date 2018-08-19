package com.adobe.aem.selenium.utils;

import org.testng.asserts.SoftAssert;

public class CustomeSoftAssert {

	private static SoftAssert softAssert;

	public static SoftAssert getSoftAssert() {
		if (null == softAssert) {
			softAssert = new SoftAssert();
		}
		return softAssert;
	}

	public static void assertAll() {
		getSoftAssert().assertAll();
	}
}