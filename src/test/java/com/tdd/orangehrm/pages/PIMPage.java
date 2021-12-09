package com.tdd.orangehrm.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.logger.LoggerHelper;

public class PIMPage {
	private static Logger log = LoggerHelper.getLogger(PIMPage.class);
	public static WebDriver driver;
	public Library lib = new Library(driver);

	public PIMPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Here contains all the locator belongs to LoginPage

	@FindBy(id = "menu_pim_addEmployee")
	public WebElement addEmployee;

	@FindBy(xpath = "//h1")
	public WebElement addEmployee_header;

	@FindBy(xpath = "//label[@class ='hasTopFieldHelp']")
	public WebElement firstNameLabel;

	@FindBy(xpath = "//label[@for ='employeeId']")
	public WebElement employeeIdLabel;

	@FindBy(xpath = "//label[@for ='photofile']")
	public WebElement photographLabel;

	@FindBy(xpath = "//label[@for ='chkLogin']")
	public WebElement createLoginDetailLabel;

	@FindBy(id = "firstName")
	public WebElement firstName;
	
	@FindBy(id = "lastName")
	public WebElement lastName;
	
	@FindBy(id = "employeeId")
	public WebElement empID;
	
	@FindBy(id = "photofile")
	public WebElement chooseFile;
	
	@FindBy(id = "btnSave")
	public WebElement btnSave;

}
