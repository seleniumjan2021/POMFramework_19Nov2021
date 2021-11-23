package com.tdd.orangehrm.helper.browser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tdd.orangehrm.helper.logger.LoggerHelper;
import com.tdd.orangehrm.helper.resources.ResourceHelper;

public class Browser {

	private static Logger log = LoggerHelper.getLogger(Browser.class);
	
	
	//P->F->p->l
	private Properties getConfigProperty() {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(ResourceHelper.getProjectPath()+"src/main/java/com/tdd"
					+ "/orangehrm/helper/config/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
}
