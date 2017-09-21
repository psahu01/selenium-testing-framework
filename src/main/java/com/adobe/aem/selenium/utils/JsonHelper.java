package com.adobe.aem.selenium.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

public class JsonHelper {

	private static Gson gson = new Gson();

	public static <T> T getObject(Reader reader, Class<T> testClass) {
		return gson.fromJson(reader, testClass);
	}

	public static <T> T getObject(String json, Class<T> testClass) {
		return gson.fromJson(json, testClass);
	}
	
	public static <T> T getObject(InputStream inputStream, Class<T> testClass) {
		Reader reader = new InputStreamReader(inputStream);
		return gson.fromJson(reader, testClass);
	}
}
