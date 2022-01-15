package com.tdd.orangehrm.helper.wait;

import java.time.Duration;
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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.logger.LoggerHelper;

public class WaitHelper {

	private static Logger log = LoggerHelper.getLogger(WaitHelper.class);
	private static WebDriver driver;
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
		log.info("Implicit wait has been set to: " +timeout);
	}

	
	/**
	 * This method will explicitly wait for element to appear in a desired time.
	 * @param element
	 * @param timeoutInSeconds
	 * @return
	 */
	public boolean waitForElementVisible(WebElement element , int timeoutInSeconds) {
		boolean display = false;
		try {
//			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//			wait.ignoring(NoSuchElementException.class);
//			wait.ignoring(NoSuchFrameException.class);
//			wait.ignoring(ElementNotVisibleException.class);
//			wait.ignoring(StaleElementReferenceException.class);
//			wait.ignoring(NoSuchWindowException.class);
//			wait.until(ExpectedConditions.visibilityOf(element));
			driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
			TestBase.logInfoExtentReport("In Class " +getClass().getSimpleName()+ " " +element.getText() + "element is visible after "+timeoutInSeconds+" seconds.");
			return element.isDisplayed();	
		}catch (Exception e) {
		 
			log.info(element.toString() + "element is not visible after "+timeoutInSeconds+" seconds." );
			TestBase.logFailExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + "element is not visible after "+timeoutInSeconds+" seconds.");
		}
		return display;
	}
	/**
	 * This method will explicitly wait for element to be clickable in a desired time.
	 * @param element
	 * @param timeoutInSeconds
	 * @return
	 */
	public  boolean waitForElementClickable(WebElement element , int timeoutInSeconds) {
		boolean clickable = false;
		try {
			log.info("waiting for : "+element+" : "+timeoutInSeconds + "seconds to be clickable");
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.pollingEvery(Duration.ofMillis(200));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			TestBase.logInfoExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + "element is clickable after "+timeoutInSeconds+" seconds.");
			return element.isDisplayed();
		}catch (Exception e) {
		 log.info(element.toString() + "element is not clickable after "+timeoutInSeconds+" seconds." );
		 TestBase.logFailExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + "element is not clickable after "+timeoutInSeconds+" seconds.");
		}
		return clickable; 
	}
	
	public boolean waitForElementVisible(WebElement element , int timeoutInSeconds , int pollingEveryMilliSecond) {
		boolean display = false;
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.ignoring(NoSuchElementException.class)
			.ignoring(NoSuchFrameException.class)
			.ignoring(ElementNotVisibleException.class)
			.ignoring(StaleElementReferenceException.class)
			.ignoring(NoSuchWindowException.class)
			.withTimeout(Duration.ofSeconds(timeoutInSeconds))
			.pollingEvery(Duration.ofMillis(pollingEveryMilliSecond));
			wait.until(ExpectedConditions.visibilityOf(element));
			TestBase.logInfoExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + "element is visible after "+timeoutInSeconds+" seconds.");
			return element.isDisplayed();	
		}catch (Exception e) {
		 
			log.info(element.toString() + "element is not visible after "+timeoutInSeconds+" seconds." );
			TestBase.logFailExtentReport("In Class " +getClass().getSimpleName()+ " " +element.toString() + "element is not visible after "+timeoutInSeconds+" seconds.");
		}
		return display;
	}
}
