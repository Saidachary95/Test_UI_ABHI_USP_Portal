package com.ABHIUSP.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { ".\\src\\test\\resources\\FeatureFiles\\EnhancedFamilyFloater.feature"

}, dryRun = false, monochrome = true, glue = { "com.ABHIUSP.hooks", "com.ABHIUSP.stepDef" }, plugin = { "pretty",
		"html:target\\cucumber-reports\\CucumberTestReport.html", "html:target\\cucumber-reports\\cucumber-pretty", })
public class TestRunner extends AbstractTestNGCucumberTests {

}


