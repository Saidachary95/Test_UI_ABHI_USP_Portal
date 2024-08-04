package com.ABHIUSP.utilities;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ABHIUSP.DriverFactory.DriverFactory;

public class CommonObjects {

	public WebDriver driver;

	public static Logger logger = LogManager.getLogger(CommonObjects.class);

//	public DriverFactory DriverFactory = new DriverFactory();
	/*
	 * Constructor
	 */
	public CommonObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Enter Text Methods $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	/***
	 * Enter Text Method. Below method common for all enter text by tagname = input,
	 * locator = placeholder Login Username and password
	 */
	public WebElement enterTextByPlaceholder(String value) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[@placeholder='" + value + "']";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		logger.info(element.getText());
		element.click();
		element.sendKeys(Keys.HOME);
		element.sendKeys(Keys.CONTROL + "A");
		element.sendKeys(Keys.DELETE);
		Thread.sleep(500);
		return element;

	}

	/***
	 * Enter Text Method.
	 * 
	 */
	public WebElement enterTextByContainsText(String value) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[contains(text(),'"+value+"')]//parent::*[@class='input-container']//child::input";
		System.err.println(" X path  : " + xpath);
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		element.click();
		element.sendKeys(Keys.HOME);
		element.sendKeys(Keys.CONTROL + "A");
		element.sendKeys(Keys.DELETE);
		return element;

	}

	/**
	 * Below method we using enter only for pincode bcz of conflit
	 */
	public WebElement enterPincode(String value) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[text()='" + value + " ']//parent::*[@class='input-container']//child::input";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		element.click();
		element.sendKeys(Keys.HOME);
		element.sendKeys(Keys.CONTROL + "A");
		element.sendKeys(Keys.DELETE);
		return element;

	}

//	$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Click on Button Methods $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$//

	/***
	 * Click on buy now button select insurance type
	 * 
	 * @throws InterruptedException
	 */
	public WebElement clickOnProductType(String value) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[contains(text(),'" + value + "')]//parent::div//child::button";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		Thread.sleep(3000);
		element.click();
		return element;

	}

	/***
	 * CLick on button by Locator class
	 * 
	 */
	public WebElement clickOnButtonByClass(String buttonName) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[@class='" + buttonName + "']";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		logger.info(element.getText());
		Thread.sleep(3000);
		element.click();
		Thread.sleep(3000);

		return element;
	}

	/***
	 * Click on Button Method. This method is created to click on button by contain
	 * text()
	 */
	public WebElement clickOnButtonByContainText(String buttonName) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[contains(text(),'" + buttonName + "')]";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		logger.info(element.getText());
//		appyWaitForElement().until(ExpectedConditions.visibilityOf(element));
		Thread.sleep(3000);
		element.click();
		return element;
	}

//	$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Verify text Methods $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$//

	/***
	 * Verify text Method : This method is created for verify text by contains
	 * text()
	 */

	public WebElement verifyTextByContainText(String value) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[contains(text(),'" + value + "')]";
		System.err.println("Created xpath is : " + xpath);
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
//		appyWaitForElement().until(ExpectedConditions.visibilityOf(element));
		String actualText = element.getText();
		System.err.println(" Toast Message : " + actualText);
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		Assert.assertEquals(actualText, value);
		return element;

	}

	/**
	 * Verify Toast message
	 */
	public WebElement verifyToastMessage(String value) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[@class='container-2']//*[contains(text(),'" + value + "')]";
		System.err.println("Created xpath is : " + xpath);
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		String actualText = element.getText();
		System.err.println(" Toast Message : " + actualText);
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		Assert.assertEquals(actualText, value);
		return element;

	}

//	$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Select Options From drop down Methods   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ //

	/*
	 * below method for select option for sum insured
	 */
	public WebElement selectSumInsured(String value) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();

//		 =  "//*[contains(text(),'Insured Member Details')]//following::select[@class=\"inputField_Select ng-untouched ng-pristine ng-invalid ng-star-inserted\"]";

		String xpath = "//*[contains(text(),'" + value
				+ " ')]//parent::div//select[@class='inputField_Select ng-untouched ng-pristine ng-valid ng-star-inserted']";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		return element;
	}

	/***
	 * select insure member
	 * 
	 * @return //label[@for='Spouse']
	 * @throws InterruptedException
	 */
	public WebElement selectInsuredMember(String value) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[contains(text(),'" + value + "')]";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		Thread.sleep(2000);
		element.click();
		return element;

	}

	/***
	 * Select options from drop down
	 * @return 
	 * @throws InterruptedException 
	 * 
	 */

	public Select selectOptionsFromDropDown(String value, String option) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[contains(text(),'" + value + "')]//parent::*[@class='input-container']//child::select";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		Select sel = new Select(element);
		Thread.sleep(3000);
		sel.selectByVisibleText(option);
		return sel;

	}

	public String getTextOfField(String value) 
	{
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[contains(text(),'" + value + "')]//parent::*[@class='input-container']//child::select";
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].style.border='3px solid blue'", element);
		String fieldValue = element.getText();
		return fieldValue;
	}
	
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Click on Check boxes and Radio buttons   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	/*
	 * Click On Check boxes Below Method common for all check boxes
	 * 
	 */
	public void clickOnCheckBoxes(String value) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "//*[@class='checkboxes']//parent::*//*[@type='" + value + "']";
//		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		List<WebElement> elements = DriverFactory.getDriver().findElements(By.xpath(xpath));

		for (int i = 0; i < elements.size(); i++) {

			WebElement element = elements.get(i);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			js.executeScript("arguments[0].style.border='3px solid blue'", element);
			Thread.sleep(2000);
			element.click();

		}

	}

	public void clickOnRadioButton(String value) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		String xpath = "[class*='" + value + "'] input[type='radio']";
		List<WebElement> elements = DriverFactory.getDriver().findElements(By.xpath(xpath));

		for (int i = 0; i < elements.size(); i++) {
			WebElement element = elements.get(i);
			js.executeScript("arguments[0].style.border='3px solid blue'", elements);
			if (element.isSelected()) {

			}
		}

	}

// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	public void selectOption(WebElement element, String option) {

		Select sel = new Select(element);
		sel.selectByVisibleText(option);

	}

//	public static WebDriverWait appyWaitForElement() {
//		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), ConstantsValues.EXPLICIT_WAIT);
//		return wait;
//	}

}
