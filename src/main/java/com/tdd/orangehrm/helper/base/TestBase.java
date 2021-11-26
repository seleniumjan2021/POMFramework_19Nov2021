package com.tdd.orangehrm.helper.base;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

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
	
	@BeforeSuite
	public void setup(ITestContext testContext) {
		
		Map<String, String> params = testContext.getCurrentXmlTest().getAllParameters();
		String executionLocation = params.get("environment");
		
		if(executionLocation.isEmpty()) {
			log.info("Before Suite Exceution Enviroment: local");
			
		}else {
			log.info("Before Suite Exceution Enviroment: "+executionLocation);
			log.info("Project path is : "+ ResourceHelper.getProjectPath());
			log.info("Automation Reports: "+ResourceHelper.getReportPath());
			log.info("Screenshots :" +ResourceHelper.getImagePath());
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
			log.info("Image available on :" +ResourceHelper.getImagePath());
		}
		if(driver != null) {
			driver.quit();
			log.info("Browser is closing....");
		}
	}
	
	@BeforeMethod
	public void objectDeclaration() {
		wait = new WaitHelper(driver);
		lib = new Library(driver);
	}
}
