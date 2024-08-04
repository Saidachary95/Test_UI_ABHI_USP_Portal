package com.ABHIUSP.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

	public static Properties prop;
	public static Logger logger = LogManager.getLogger(ConfigReader.class);

	/**
	 * This method is used to load the properties from config.properties file
	 * 
	 * @return it returns Properties prop object
	 */

	public static String getProperty(String getPropertyValue) {

		try {
			File filePath = new File(".\\src\\test\\resources\\config.properties");
			FileInputStream iputStream = new FileInputStream(filePath);
			prop = new Properties();
//			InputStream ip = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(iputStream);
//			logger.info(" >>>>>> User Successfully loaded Config file. ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
//			logger.warn(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
//			logger.warn(e.getMessage());
		}

		return prop.getProperty(getPropertyValue);
	}

}