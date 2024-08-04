package com.ABHIUSP.stepDef;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ABHIUSP.DriverFactory.DriverFactory;
import com.ABHIUSP.utilities.CommonObjects;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class LoginSteps {
	public static Logger logger = LogManager.getLogger(LoginSteps.class);
	public CommonObjects objComm = new CommonObjects(DriverFactory.getDriver());

	@When("Agent launched the browser and entered url.")
	public void agent_launched_the_browser_and_entered_url() {

		logger.info(" >>>>>>>>>> User Sucessfully launched browser and entered Url ");

	}

	@Then("Verify agent landed on {string} page.")
	public void verify_agent_landed_on_page(String value) {

		try {
			objComm.verifyTextByContainText(value);
			logger.info(" >>>>>>>>>> User Landed on dashboard  " + value);
		} catch (Exception e) {

			logger.warn(e.getMessage());
			Assert.fail(" >>>>>>>>>> User Not able to landed on dash board " + value);
		}
	}

	@Then("Agent entered {string}.")
	public void agent_entered(String value, DataTable dataTable) {

		try {
			List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

			for (Map<String, String> data : list) {

				for (String key : data.keySet()) {
					logger.info(" Key is : " + key);
					String val = data.get(key); // this method will give the value of that key
					System.err.println(val);
					logger.info(" Value is : " + val);
					WebElement element = objComm.enterTextByPlaceholder(key);
					element.sendKeys(val);
				}
			}
			logger.info(" >>>>>>>>>> User entered :  " + value);

		} catch (Exception e) {
			logger.warn(" User entered Date of birth : " + value);
			Assert.fail(" >>>>>>>>>> User Not able to enter" + value);
		}

	}

	@Then("Agent clicked on button {string}.")
	public void agent_clicked_on_button(String value) {

		try {
			objComm.clickOnButtonByClass(value);
			logger.info(" >>>>>>>>>> User clicked on " + value);
		} catch (Exception e) {
			logger.warn(" User entered Date of birth : " + value);
			Assert.fail(" >>>>>>>>>> User Not able click on " + value);
		}

	}

	@Then("Verify the {string}.")
	public void verify_the(String value) {
		try {
			objComm.verifyTextByContainText(value);
			logger.info(" >>>>>>>>>> User verified : " + value);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			Assert.fail(" >>>>>>>>>> User not able to verify " + value);
		}

	}

}
