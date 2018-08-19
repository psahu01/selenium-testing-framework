package com.adobe.aem.selenium.driver;

public enum DriverType implements DriverSetup{
	
	FIREFOX{
		public InitializeDriver getInitializeDriver() {
			return new InitializeFireFoxDriver();
		}
	},
	CHROME{
		public InitializeDriver getInitializeDriver() {
			return new InitializeChromeDriver();
		}		
	},
	IE{
		public InitializeDriver getInitializeDriver(){
			return new InitializeIEDriver();
		}
	}
}