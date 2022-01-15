package com.tdd.orangehrm.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.logger.LoggerHelper;



/**
 * @author ashu
 *
 */
public class JavaScriptHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method will execute java script
	 * 
	 * @param script
	 * @return
	 */
	public Object executeScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(script);
	}

	/**
	 * This method will execute Java script with multiple arguments
	 * 
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(script, args);
	}

	/**
	 * This method will scroll till element
	 * 
	 * @param element
	 */
	public boolean scrollToElement(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info("scroll to WebElement " + element.toString() + " .Is this element display? +" + element.isDisplayed());
		return element.isDisplayed();
	}

	/**
	 * Scroll till element and click
	 * 
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element) {
		boolean isElementDisplay = scrollToElement(element);
		if (isElementDisplay) {
			element.click();
			log.info("element is clicked: " + element.toString());
		} else {
			log.info("Failed to click element: " + element.toString());
		}

	}

	/**
	 * Scroll till element view
	 * 
	 * @param element
	 */
	public boolean scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		log.info("scroll till element " + element.toString() + " .Is this element display? +" + element.isDisplayed());
		TestBase.logInfoExtentReport(
				"scroll till element " + element.toString() + " .Is this element display? +" + element.isDisplayed());
		return element.isDisplayed();
	}

	/**
	 * Scroll till element view and click
	 * 
	 * @param element
	 */
	public void scrollIntoViewAndClick(WebElement element) {
		boolean isElementDisplay = scrollIntoView(element);
		if (isElementDisplay) {
			element.click();
			log.info("element is clicked: " + element.toString());
		} else {
			log.info("Failed to click element: " + element.toString());
		}
	}

	/**
	 * This method will scroll down vertically
	 */
	public void scrollDownVertically() {
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
		log.info("scrolling down vertically...");
	}

	/**
	 * This method will scroll up vertically
	 */
	public void scrollUpVertically() {
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
		log.info("scrolling up vertically...");
	}

	/**
	 * This method will scroll till given pixel (e.g=1500)
	 * 
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBY(0," + pixel + ")");
	}

	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBY(0,-" + pixel + ")");
	}

	/**
	 * This method will zoom screen by 100%
	 */
	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
		log.info("Java Script method 'zoomInBy100Percentage' Zoom in by 100 %");
	}

	/**
	 * This method will zoom screen by given %
	 */
	public void zoomInByPercentage(int number) {
		executeScript("document.body.style.zoom='" + number + "%'");
		log.info("Java Script method 'zoomInByPercentage' Zoom in by " + number + " %");
	}

	/**
	 * This method will click on element
	 * 
	 * @param element
	 */
	public void clickElement(WebElement element) {
		executeScript("arguments[0].click();", element);
	}
}
