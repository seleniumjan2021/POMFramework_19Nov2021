package com.tdd.orangehrm.helper.resources;


import org.apache.log4j.Logger;

import com.tdd.orangehrm.helper.logger.LoggerHelper;

/**
 * @author ashu
 *
 */
public class ResourceHelper {

	private static Logger log = LoggerHelper.getLogger(ResourceHelper.class);

	/**
	 * This method will return the project path
	 * @return String
	 */
	public static String getProjectPath() {
		String basePath = System.getProperty("user.dir")+"/";
		return basePath;
	}

	/**
	 * This method will return the resource path
	 * @return String
	 */
	public static String getResourcePath() {
		String basePath = System.getProperty("user.dir")+"/";
		String resourcePath = "src/test/resources/";
		return basePath+resourcePath;
	}
	
	/**
	 * This method will return the images path
	 * @return
	 */
	public static String getScreenshotPath() {
		return getResourcePath()+"screenshot/";
	}
	
	/**
	 * This method will return the properties file path
	 * @return
	 */
	public static String getPropertiesFilePath() {
		return getResourcePath()+"propertiesFile/";
	}
	
	/**
	 * This method will return the Automation report path
	 * @return
	 */
	public static String getReportPath() {
		return getProjectPath()+"AutomationReport/";
	}
	
	public static void main(String[] args) {
		getProjectPath();
	}

}
