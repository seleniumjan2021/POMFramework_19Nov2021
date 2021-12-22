package com.tdd.orangehrm.testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.config.TD;
import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.wait.WaitHelper;
import com.tdd.orangehrm.pages.HomePage;
import com.tdd.orangehrm.pages.LoginPage;
import com.tdd.orangehrm.pages.PIMPage;

public class PIMPageTest extends TestBase {
	public LoginPage login;	
	public PIMPage pim;
	public HomePage home;
	public boolean status = false;
	public SoftAssert soft;
	
	
	@Test(description = "Navigate to Add Employee")
	public void navigateToAddEmployee() {
		objectDeclaration();
		login.loginOrganeHRM(brow.getConfigProperty().getProperty("admin_user"),brow.getConfigProperty().getProperty("admin_password"));
		lib.moveOverToElement(home.PIM);
		try {
			lib.clickOnElement(pim.addEmployee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		status = wait.waitForElementVisible(pim.addEmployee_header, 1);
		Assert.assertTrue(status, "Failed!! Navigation to Add Employee");
	}
	
	@Test(description = "Create Employee")
	public void createEmployee() throws InterruptedException, IOException {
		objectDeclaration();
		login.loginOrganeHRM(brow.getConfigProperty().getProperty("admin_user"),brow.getConfigProperty().getProperty("admin_password"));
		lib.moveOverToElement(home.PIM);
		try {
			lib.clickOnElement(pim.addEmployee);
			Thread.sleep(2000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		status = wait.waitForElementVisible(pim.firstNameLabel, 1);
		soft.assertTrue(status, "First Name Field Label is not visible");
		status = wait.waitForElementVisible(pim.employeeIdLabel, 1);
		soft.assertTrue(status, "EmployeeID Label is not visible");
		status = wait.waitForElementVisible(pim.photographLabel, 1);
		soft.assertTrue(status, "Photograph Label is not visible");
		status = wait.waitForElementVisible(pim.createLoginDetailLabel, 1);
		soft.assertTrue(status, "Create Login Detail Label is not visible");
		lib.clearValueFromEditField(pim.firstName);
		lib.setValueOnElement(pim.firstName, TD.fName);
		lib.clearValueFromEditField(pim.lastName);
		lib.setValueOnElement(pim.lastName, TD.lName);
		lib.clearValueFromEditField(pim.empID);
		lib.setValueOnElement(pim.empID, TD.empCode);
		lib.setValueOnElement(pim.chooseFile, TD.profileImage);
		lib.clickOnElement(pim.btnSave);
		String name = pim.addEmployee_header.getText();
		Assert.assertEquals(name, TD.fName+" "+TD.lName , "Failed to create employee");
		lib.clickOnElement(pim.employeeList);
		lib.clearValueFromEditField(pim.empSearchID);
		lib.setValueOnElement(pim.empSearchID, TD.empCode);
		lib.clickOnElement(pim.searchBtn);
		WebElement el = driver.findElement(By.xpath("//td/a[contains(text(),'"+TD.empCode+"')]"));
		if(wait.waitForElementVisible(el, TD.oneSecWait)) {
			WebElement checkbox = driver.findElement(By.xpath("//a[contains(text(),'"+TD.empCode+"')]/parent::td/preceding::td/input"));
			lib.clickOnElement(checkbox);
			lib.clickOnElement(pim.delBtn);
			lib.clickOnElement(pim.delOkBtn);
			//wait.waitForElementVisible(pim.successfullyDeletedSnackbar, TD.oneSecWait);
			lib.clearValueFromEditField(pim.empSearchID);
			lib.setValueOnElement(pim.empSearchID, TD.empCode);
			lib.clickOnElement(pim.searchBtn);
			status = wait.waitForElementVisible(pim.noRecordFoundMsg, TD.oneSecWait+1);
			soft.assertTrue(status);
		}
		
		soft.assertAll();	
		
       //Steps for Deletion of an that Employee which has been created. TC Complete
	}
	
	public void objectDeclaration() {
		home = new HomePage(driver);
		pim = new PIMPage(driver);
		login = new LoginPage(driver);
		wait = new WaitHelper(driver);
		lib = new Library(driver);
		soft = new SoftAssert();
	}
}
