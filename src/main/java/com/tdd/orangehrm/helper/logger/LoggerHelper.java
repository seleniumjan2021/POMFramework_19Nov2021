package com.tdd.orangehrm.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.tdd.orangehrm.helper.resources.ResourceHelper;


public class LoggerHelper {

private static boolean root = false;


public static <T> Logger getLogger(Class<T> cls) {
	if(root) {
		return Logger.getLogger(cls);
	}
	 PropertyConfigurator.configure(ResourceHelper.getPropertiesFilePath()+"log4jConfig.properties");
	 root = true; 
	 return Logger.getLogger(cls);
}

public static void main(String[] args) {
	Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	log.info("Logger is configured");
	log.info("Logger is testing");
}

}
