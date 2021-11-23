package com.tdd.orangehrm.helper.base;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.tdd.orangehrm.helper.logger.LoggerHelper;
import com.tdd.orangehrm.helper.resources.ResourceHelper;

public class TestBase {
	
    private static Logger log = LoggerHelper.getLogger(TestBase.class); 
	public static WebDriver driver;
	
	@BeforeSuite
	public void beforeSuite(ITestContext testContext) {
		Map<String, String> params = testContext.getCurrentXmlTest().getAllParameters();
		String executionLocation = params.get("environment");
		
		if(executionLocation.isEmpty()) {
			log.info("Before Suite Exceution Enviroment: local");
			
		}else {
			log.info("Before Suite Exceution Enviroment: "+executionLocation);
			log.info("Project path is : "+ ResourceHelper.getProjectPath());
			log.info("Automation Reports: "+ResourceHelper.getReportPath());
			log.info("Image :" +ResourceHelper.getImagePath());
			log.info("Properties: "+ResourceHelper.getPropertiesFilePath());
		}
	}
	
	
	@AfterSuite
	public void afterSuite(ITestContext testContext) {
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
	}
}
