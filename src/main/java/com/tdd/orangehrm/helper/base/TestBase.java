package com.tdd.orangehrm.helper.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tdd.orangehrm.helper.browser.BrowserConfig;
import com.tdd.orangehrm.helper.extent.ExtentManager;
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
		extent = ExtentManager.getReportInstance();
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
	@Parameters("Browser")
	@BeforeMethod
	public void beforeClass(ITestResult result , String browserName) {		
		test = extent.createTest(getClass().getSimpleName()+": "+result.getMethod().getMethodName());
		driver = brow.getDriver(browserName);
		log.info("Driver initialization......");
		wait = new WaitHelper(driver);
		wait.setImplicitWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info("Maximizing Browser..");
		driver.get(brow.getConfigProperty().getProperty("URL"));
		log.info("Launching URL......");
	}
	
	@AfterTest
	public void afterTest() {
		extent.flush();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException {
		
		try {
			if(result.getStatus() == ITestResult.FAILURE) {
				log.info(result.getName() + "Test Failed...");
				test.log(Status.FAIL, result.getName() + " Test Failed... Error Details:" + result.getThrowable());
				addScreenShotToReport(driver);
			}else if(result.getStatus() == ITestResult.SUCCESS) {
				log.info(result.getName() + "Test Passed...");
				test.log(Status.PASS, result.getName() + " Test Pass");
			}else if(result.getStatus() == ITestResult.SKIP) {
				log.info(result.getName() + "Test Skipped...");
				test.log(Status.SKIP, result.getName() + " Test Skipped.." +result.getThrowable());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(result.getName()+ "throws an exception. Exception details " +e.toString());
		}
		finally {
			if(driver != null) {
				driver.quit();
				Thread.sleep(3000);
				log.info("**** finally driver quit, after executing method :" + result.getMethod().getMethodName());
			}
			log.info(result.getName() + "Finished...");
		}
		
	}
	
	public String takeScreenShotOnFailure(String fileName, WebDriver driver) throws IOException {
		if(driver == null) {
			log.info("driver is null .. not able to capture screenshot");
			return null;
		}		
		if(fileName == "") {
			fileName = "Blank";
		}		
		File destFile = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date);
		System.out.println(time);
		String newTimeFormat = time.replaceAll("/", "_").replaceAll(" ", "_").replaceAll(":", "_");
		System.out.println(newTimeFormat);
		//Taking the screenshot here
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//Manipulating the name and storing back to destFile
		try {
		destFile = new File(ResourceHelper.getScreenshotPath()+fileName+"_"+newTimeFormat+".png");
		//Copies srcFile to destFile
		FileUtils.copyFile(srcFile, destFile);
		Thread.sleep(2000);
		test.info("Taking Screenshot to " +destFile.toString());
		}catch (Exception e) {
			log.info("Taking screenshot error : " +e.toString());
		}
		return destFile.toString();
	}
	
	public static void addScreenShotToReport(WebDriver driver) throws IOException {
		TestBase tb = new TestBase();
		String imagePath = tb.takeScreenShotOnFailure("On_Failure", driver);
		test.addScreenCaptureFromPath(imagePath);
	}
	
	public static void logInfoExtentReport(String msg) {
		test.log(Status.INFO, msg);
	}
	public static void logFailExtentReport(String msg) {
		test.log(Status.FAIL, msg);
	}
	public static void logPassExtentReport(String msg) {
		test.log(Status.PASS, msg);
	}
	
}
