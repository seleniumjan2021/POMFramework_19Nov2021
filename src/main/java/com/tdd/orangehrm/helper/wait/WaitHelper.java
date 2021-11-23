package com.tdd.orangehrm.helper.wait;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tdd.orangehrm.helper.logger.LoggerHelper;

public class WaitHelper {

	private static Logger log = LoggerHelper.getLogger(WaitHelper.class);
	private static WebDriver driver;
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	public static void setImplicitWait(long timeout, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
		log.info("Implicit wait has been set to: " +timeout);
	}

	
	/**
	 * This method will explicitly wait for element to appear in a desired time.
	 * @param element
	 * @param timeoutInSeconds
	 * @return
	 */
	public static boolean waitForElementVisible(WebElement element , int timeoutInSeconds) {
		boolean display = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(NoSuchFrameException.class);
			wait.ignoring(ElementNotVisibleException.class);
			wait.ignoring(StaleElementReferenceException.class);
			wait.ignoring(NoSuchWindowException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		}catch (Exception e) {
		 log.info(element.toString() + "element is not visible after "+timeoutInSeconds+" seconds." );
		}
		return display;
	}
	/**
	 * This method will explicitly wait for element to be clickable in a desired time.
	 * @param element
	 * @param timeoutInSeconds
	 * @return
	 */
	public static boolean waitForElementClickable(WebElement element , int timeoutInSeconds) {
		boolean clickable = false;
		try {
			log.info("waiting for : "+element+" : "+timeoutInSeconds + "seconds to be clickable");
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return element.isDisplayed();
		}catch (Exception e) {
		 log.info(element.toString() + "element is not visible after "+timeoutInSeconds+" seconds." );
		}
		return clickable;
	}
	
}
