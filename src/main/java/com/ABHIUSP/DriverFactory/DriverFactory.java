package com.ABHIUSP.DriverFactory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import com.ABHIUSP.utilities.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	/**
	 * Use thread local to avoid conflict at the time of parallel testing
	 */
	
	public static final Logger logger = LogManager.getLogger(DriverFactory.class);
	public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

	public static WebDriver initiate_browser(String browser) {

		try {
			logger.info(" >>>>>> Browser value is : " + browser);

			if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				threadLocal.set(new ChromeDriver(chromeOptions));

				logger.info(browser + " browser opened ");

			} else if (browser.equalsIgnoreCase("ChromeHeadless")) {

				/*
				 * With Headless mode.
				 * 
				 */
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				WebDriverManager.chromedriver().setup();
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--allow-insecure-localhost");
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				threadLocal.set(new ChromeDriver(options));

			} else if (browser.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				threadLocal.set(new EdgeDriver());
				logger.info(browser + "Broser opned ");
			}

		} catch (Exception e) {
			logger.warn(e.getMessage());
		}

		getDriver().manage().window().maximize();
		String app_Url = ConfigReader.getProperty("Url");
		getDriver().get(app_Url);

		/*
		 * Get the ConstantsValues from class whic is available in utilities package..
		 */
//		getDriver().manage().timeouts().pageLoadTimeout(ConstantsValues.PAGE_lOAD_TIMEOUT, TimeUnit.SECONDS);
//		getDriver().manage().timeouts().implicitlyWait(ConstantsValues.IMPLICITY_WAIT, TimeUnit.SECONDS);
		logger.info("Application URL is : " + app_Url);
		getDriver().get(app_Url);

		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return threadLocal.get();
	}

	public static void close_app() {
		try {
			Thread.sleep(3000);
			getDriver().quit();
			logger.info(" Browser Closed ");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}

	}

}
