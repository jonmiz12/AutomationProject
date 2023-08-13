package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	public static String readProperty(String key) {
		
		String value="";
		try (InputStream input = new FileInputStream("./src/test/resources/data/configuration.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {}
		return value;
	}

	public static void runCommandFromFile (String fileName) throws IOException {
		String filePath = "utils/ExecutableDirs/"+fileName;

		Process process = Runtime.getRuntime().exec(filePath);
	}
}
