package com.tdd.orangehrm.helper.base;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.util.concurrent.ExecutionList;
import com.tdd.orangehrm.helper.browser.BrowserConfig;
import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.logger.LoggerHelper;
import com.tdd.orangehrm.helper.resources.ResourceHelper;
import com.tdd.orangehrm.helper.wait.WaitHelper;

public class TestBase {
	
    private static Logger log = LoggerHelper.getLogger(TestBase.class); 
	public static WebDriver driver;
	public BrowserConfig brow = new BrowserConfig();
	public WaitHelper wait ;
	public Library lib;
	public String executionLocation = "";
	public String applicationName = "";
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void setup(ITestContext testContext) {
		
		Map<String, String> params = testContext.getCurrentXmlTest().getAllParameters();
	    executionLocation = params.get("environment");
	    applicationName	  = params.get("appName");
		
		if(executionLocation.isEmpty()) {
			log.info("Before Suite Exceution Enviroment: local");
			
		}else {
			log.info("Before Suite Exceution Enviroment: "+executionLocation);
			log.info("Project path is : "+ ResourceHelper.getProjectPath());
			log.info("Automation Reports: "+ResourceHelper.getReportPath());
			log.info("Screenshots :" +ResourceHelper.getScreenshotPath());
			log.info("Properties: "+ResourceHelper.getPropertiesFilePath());
		}
		driver = brow.getDriver();
		log.info("Driver initialization......");
		driver.get((String) brow.getConfigProperty().get("URL"));
		log.info("Application Launch successfully");
		driver.manage().window().maximize();
		log.info("Maximizing Window");
		wait = new WaitHelper(driver);
		wait.setImplicitWait(10, TimeUnit.SECONDS);
	}
	
	
	@AfterSuite
	public void tearDown(ITestContext testContext) {
		Map<String, String> params = testContext.getCurrentXmlTest().getAllParameters();
		String executionLocation = params.get("environment");
		if(executionLocation.isEmpty()) {
			log.info("After Suite Exceution Enviroment: local");
		}else {
			log.info("Execution completed on Enviroment: "+executionLocation);
			log.info("Project path is : "+ ResourceHelper.getProjectPath());
			log.info("Automation Reports generated on : "+ResourceHelper.getReportPath());
			log.info("Image available on :" +ResourceHelper.getScreenshotPath());
		}
		if(driver != null) {
			driver.quit();
			log.info("Browser is closing....");
		}
	}
	
	
	public String takeScreenShotOnFailure(String testcaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateNam
		String destination = ResourceHelper.getScreenshotPath()+testcaseName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(src, finalDestination);
		return destination;
	}
	
	
	
	
}
