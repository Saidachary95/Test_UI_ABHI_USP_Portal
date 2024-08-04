package com.ABHIUSP.stepDef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ABHIUSP.DriverFactory.DriverFactory;
import com.ABHIUSP.utilities.CommonObjects;

import TestPractice.TestReadExcel;
import io.cucumber.java.en.*;

public class ProductJourney {
	public static Logger logger = LogManager.getLogger(ProductJourney.class);
	public static CommonObjects objComm = new CommonObjects(DriverFactory.getDriver());

	public static String baseSheetName = "ProductList";
	public static String actualProductType = null;

//	String excelFileLocation = getCellData("ProductList", "ActtiveHealthEnhanced", "ProductTestDataFilepath");

	public void excution(String productName, String TestCaseID) {

		List<String> listSize = new ArrayList<String>();

		for (int i = 0; i < listSize.size(); i++) {
			if (productName.equalsIgnoreCase("Active") && TestCaseID.equalsIgnoreCase("TC_003")) {

			}
		}

	}

	@Then("User selected productItem as {string}.")
	public void user_selected_product_item_as(String productItem) {
		try {

			objComm.clickOnProductType(productItem);
			logger.info(" >>>>>>  User clicked on : " + productItem);

		} catch (Exception e) {
			logger.warn(e.getMessage());
			Assert.fail(" User Not able to click on Product Item " + productItem);
		}
	}

	@Then("User selected {string} for test case {string}.")
	public void user_selected_for_test_case(String value, String TetsCaseID) throws IOException, InterruptedException {

		String excelValue = TestReadExcel.getDataFromMap("Activ Health Enhanced", "Insured Details", TetsCaseID, value);
		try {

			if (value.equalsIgnoreCase("Select Policy Type")) {
				objComm.selectOptionsFromDropDown(value, excelValue);
//				String policyType = objComm.getTextOfField(value);
//				System.out.println(" Selected Value : " + policyType);
			} else {
				objComm.selectOptionsFromDropDown(value, excelValue);
				logger.info(" >>>>> User selected " + excelValue);

			}

		} catch (Exception e) {
			logger.warn(e.getMessage());
			Assert.fail(" User Not able to click on Product Item " + value);
		}

	}

	@Then("User entered {string} for test case {string}.")
	public void user_entered_for_test_case(String value, String TetsCaseID) throws IOException {
		String excelValue = TestReadExcel.getDataFromMap("Activ Health Enhanced", "Insured Details", TetsCaseID, value);
		try {
			WebElement element = objComm.enterTextByContainsText(value);
			Thread.sleep(3000);
			element.sendKeys(excelValue);
			element.sendKeys(Keys.ENTER);
			logger.info(" >>>>> User Entered : " + excelValue);

		} catch (Exception e) {
			logger.warn(e.getMessage());
			Assert.fail(" User Not able to enter" + value);
		}
	}

	@Then("User selected {string} as {string} for test case {string}.")
	public void user_selected_as_for_test_case(String InsuredMamber, String value, String TetsCaseID)
	{
		
		
	}
}
