package com.ABHIUSP.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.ABHIUSP.DriverFactory.DriverFactory;
import com.ABHIUSP.utilities.CommonObjects;
import com.ABHIUSP.utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class hooks {

	public static Logger logger = LogManager.getLogger(hooks.class);
	public static CommonObjects objCom = new CommonObjects(DriverFactory.getDriver());

	@Before()
	public void launchBrowser() {
				
		String browserName = ConfigReader.getProperty("Browser");
		DriverFactory.initiate_browser(browserName);
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {

			// Take a screenshot...
			try {
				String screenShotName = scenario.getName().replaceAll(" ", "_");
				final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
						.getScreenshotAs(OutputType.BYTES);

				scenario.attach(screenshot, "image/png", screenShotName); // ... and embed it in the report.
				Thread.sleep(5000);
//				DriverFactory.close_app();
			} catch (Exception e) {

				logger.info(e.getMessage());
			}

		} else {
//			DriverFactory.close_app();

		}

	}

}
