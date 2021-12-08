package com.tdd.orangehrm.helper.extent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.logger.LoggerHelper;
import com.tdd.orangehrm.helper.resources.ResourceHelper;

public class ExtentManager {

	private static Logger log = LoggerHelper.getLogger(ExtentManager.class);
	private static ExtentReports extent;
	private static ExtentSparkReporter report;
	private static TestBase base = new TestBase();

	public static ExtentReports getReportInstance() {
		
		// File Name formatting of your Report
		String reportPath = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date);
		System.out.println(time);
		String newTimeFormat = time.replaceAll("/", "_").replaceAll(" ", "_").replaceAll(":", "_");
		System.out.println(newTimeFormat);
		reportPath = ResourceHelper.getReportPath() +"OrangeHRM"+ "_" + newTimeFormat + ".html";
		System.out.println(reportPath);

		// Declaration of Extent Classs
		report = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();

		if (StringUtils.equalsIgnoreCase(base.applicationName, "OrangeHRM")) {
			report.config().setReportName("OrangeHRM Automation Execution Report");
			report.config().setDocumentTitle("OrangeHRM Regression Test");
			extent.setSystemInfo("Orange App Version", "4.8");
		} else if (StringUtils.equalsIgnoreCase(base.applicationName, "AppleHRM")) {
			report.config().setReportName("AppleHRM Automation Execution Report");
			report.config().setDocumentTitle("AppleHRM Regression Test");
			extent.setSystemInfo("Apple App Version", "4.8");
		} else {
			report.config().setReportName("OrangeHRM Automation Execution Report");
			report.config().setDocumentTitle("OrangeHRM EndtoEnd Test");
		}
		extent.setSystemInfo("Environment" ,"Production");
		extent.setSystemInfo("Author", "Ashutosh Kumar");
		extent.setSystemInfo("Java Version", "JDK 8");
		report.config().setTheme(Theme.STANDARD);
		report.config().setEncoding("utf-8");
		extent.attachReporter(report);
		return extent;
	}

}
