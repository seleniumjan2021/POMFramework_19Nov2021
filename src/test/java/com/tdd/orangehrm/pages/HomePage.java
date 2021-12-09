package com.tdd.orangehrm.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.logger.LoggerHelper;

public class HomePage {

	private static Logger log = LoggerHelper.getLogger(HomePage.class);
	public static WebDriver driver;
	public Library lib = new Library(driver);

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "menu_pim_viewPimModule")
	public WebElement PIM;

	@FindBy(id = "menu_admin_viewAdminModule")
	public WebElement admin;

	@FindBy(id = "menu_leave_viewLeaveModule")
	public WebElement leave;
	
	@FindBy(id = "menu_time_viewTimeModule")
	public WebElement time;
	
	@FindBy(id = "menu_recruitment_viewRecruitmentModule")
	public WebElement recruit;
	
	@FindBy(id = "menu_pim_viewMyDetails")
	public WebElement myDetail;
	
	@FindBy(id = "menu__Performance")
	public WebElement performance;
	
	@FindBy(id = "menu_dashboard_index")
	public WebElement dashboard;
	
	@FindBy(id = "menu_directory_viewDirectory")
	public WebElement viewDir;
	
	@FindBy(id = "menu_maintenance_purgeEmployee")
	public WebElement maintenance;
	
	@FindBy(id = "menu_buzz_viewBuzz")
	public WebElement buzz;
	
	@FindBy(id = "MP_link //marketplace")
	public WebElement marketPlace;
	
	@FindBy(xpath = "//a[contains(@class,'help')]/span")
	public WebElement help;
}
