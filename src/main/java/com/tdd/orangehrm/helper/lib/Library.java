package com.tdd.orangehrm.helper.lib;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.logger.LoggerHelper;
/**
 * @author ashu
 *
 */
public class Library {
	private static Logger log = LoggerHelper.getLogger(Library.class);
	private static WebDriver driver;
	public Actions action;
	
	public Library(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnElement(WebElement element) throws IOException {
		try {
			element.click();
			log.info(element.toString() + " got clicked.");
		    TestBase.logInfoExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + " element clicked");
		}catch (Exception e) {
			log.info(element.toString() + " element is not clicked. Error "+e.toString());
			TestBase.logFailExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + " element is not clicked" +e.toString());
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
			 TestBase.logInfoExtentReport("In Class " +getClass().getSimpleName()+ " " +element.getText() + " has set the value " +vText);
		} catch (Exception e) {
			log.info(vText + " value is not set to " +element.toString()+"Error: "+e.toString());
			TestBase.logFailExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + "has not set the value :" +vText + "Error is :"+e.toString());
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
			TestBase.logInfoExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + " field is cleared ");
		} catch (Exception e) {
			log.info(element.toString() + " value not cleared.Errror : " +e.toString());
			TestBase.logFailExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + "field is not cleared .Error is :"+e.toString());
		}

	}
	
	public void moveOverToElement(WebElement element) {
		action = new Actions(driver);
		try {
			action.moveToElement(element).build().perform();
			log.info("Move hovering to the element : "+element.toString() + " successful");
			TestBase.logInfoExtentReport("In Class " +getClass().getSimpleName()+ " mouse hover is successful on " +element.toString());
		} catch (Exception e) {
			log.info("Failed to do Move hovering to the element : "+element.toString() + "Error :" +e.toString());
			TestBase.logFailExtentReport("In Class " +getClass().getSimpleName()+ " mouse hover is failed on " +element.toString()+e.toString());
		}
	}
	
}
