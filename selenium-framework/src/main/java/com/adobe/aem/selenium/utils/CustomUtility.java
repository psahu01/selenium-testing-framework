package com.adobe.aem.selenium.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomUtility {

	public static String makePostRequest(String url, String jsonInput) {
		String entireOutput = "";
		HttpURLConnection conn = null;
		BufferedReader br = null;
		try {
			URL postUrl = new URL(url);
			conn = (HttpURLConnection) postUrl.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(jsonInput.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("Failed : HTTP error code : " + conn.getResponseCode());
				return null;
			}
			System.out.println("Posted data to service " + url);
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			int i = 0;
			while ((output = br.readLine()) != null) {
				entireOutput += (i == 0 ? (output) : ("\n" + output));
				i++;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null)
					conn.disconnect();
				if (br != null)
					br.close();
			} catch (Exception e) {
				System.out.println("Exception in makeGetRequest(" + url + ") while flushing memory\n" + e.toString());
				e.printStackTrace();
				return null;
			}
		}
		return entireOutput;
	}
}
