package com.adobe.aem.selenium.utils;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestAssert {
	
	private static SoftAssert softAssert;
	
	public static SoftAssert getSoftAsset(){
		if(null == softAssert){
			softAssert = new SoftAssert();
		}
		return softAssert;
	}
	
//	public static void main(String [] arg){
//		getSoftAsset().assertTrue(false, "true assertion failed");
//		getSoftAsset().assertEquals(true, false, "equal assertion failed");
//		
//		getSoftAsset().assertAll();
//	}
	
	public static void assertAllAsserts(){
		System.out.println("This is test");
		getSoftAsset().assertAll();
	}
} 
