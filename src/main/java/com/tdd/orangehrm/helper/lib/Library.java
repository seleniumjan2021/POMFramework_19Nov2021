package com.tdd.orangehrm.helper.lib;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tdd.orangehrm.helper.logger.LoggerHelper;
/**
 * @author ashu
 *
 */
public class Library {
	private static Logger log = LoggerHelper.getLogger(Library.class);
	private static WebDriver driver;
	
	public Library(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnElement(WebElement element) {
		try {
			element.click();
			log.info(element.toString() + " got clicked.");
		}catch (Exception e) {
			log.info(element.toString() + " element is not clicked. Error "+e.toString());
		}
	}
	
	/**
	 * This method will set value to the any edit field
	 * @param element
	 * @param vText
	 */
	public void setValueOnElement(WebElement element , String vText) {
		try {
			element.sendKeys(vText);
			log.info(vText + " value set to " +element.toString());
		} catch (Exception e) {
			log.info(vText + " value is not set to " +element.toString()+"Error: "+e.toString());
		}
	}
	
	/**
	 * This method will clear the existing value(if any) from the field. 
	 * @param element
	 */
	public void clearValueFromEditField(WebElement element) {
		try {
			element.clear();
			log.info(element.toString() + " value is cleared.");
		} catch (Exception e) {
			log.info(element.toString() + " value not cleared.Errror : " +e.toString());
		}

	}
	
	
}
