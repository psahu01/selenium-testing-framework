package com.adobe.aem.selenium.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * This class is for centrally managing Property Files. It will include all the
 * functions for loading different property files.
 * 
 * @author psahu
 *
 */
public class PropertyHelper {

	private static Properties properties;

	public static Properties getProperty() {
		if (properties == null)
			properties = new Properties();

		return properties;
	}

	public static Properties getProperty(InputStream propertyFile) {
		getProperty();
		try {
			properties.load(propertyFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static Properties getProperty(URL propertyFile) {
		getProperty();
		try (InputStream inStream = new FileInputStream(propertyFile.getPath())) {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static Properties getProperty(String propertFile) {
		getProperty();
		try (InputStream inputStream = new FileInputStream(propertFile)) {

		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
