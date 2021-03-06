package com.tdd.orangehrm.pages;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.logger.LoggerHelper;

public class LoginPage {
	
	private static Logger log = LoggerHelper.getLogger(LoginPage.class); 
	public static WebDriver driver;
	public Library lib  = new Library(driver); 
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Here contains all the locator belongs to LoginPage
	
	@FindBy(xpath = "//img[contains(@src,'logo')]")
	public WebElement orangeLogo;
	
	@FindBy(id = "txtUsername")
	public WebElement userNameField;
	
	@FindBy(id = "txtPassword")
	public WebElement passwordField;
	
	@FindBy(id = "btnLogin")
	public WebElement loginBtn;
	
	@FindBy(id = "forgotPasswordLink")
	public WebElement forgetPassLink;
	
	@FindBy(id = "welcome")
	public WebElement welcome;
	
	@FindBy(xpath = "//a[contains(@href,'logout')]")
	public WebElement logoutBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Invalid')]")
	public WebElement invalidPassMsg;
	
	@FindBy(xpath = "//*[@id='footer']/div[1]")
	public WebElement copyrights;
	
	@FindBy(id = "social-icons")
	public WebElement socialLink;
	
	//Methods of LoginPage
	public void loginOrganeHRM(String username , String password){
		 lib.clearValueFromEditField(userNameField);
		 lib.setValueOnElement(userNameField, username);
		 lib.clearValueFromEditField(passwordField);
		 lib.setValueOnElement(passwordField, password);
		 try {
			lib.clickOnElement(loginBtn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
