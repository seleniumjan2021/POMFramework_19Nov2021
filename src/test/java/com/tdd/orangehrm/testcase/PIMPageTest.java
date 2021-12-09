package com.tdd.orangehrm.testcase;

import java.io.IOException;

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
	public SoftAssert soft = new SoftAssert();
	
	
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
	public void createEmployee() throws InterruptedException {
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
		soft.assertEquals(status, "First Name Field Label is not visible");
		status = wait.waitForElementVisible(pim.employeeIdLabel, 1);
		soft.assertEquals(status, "EmployeeID Label is not visible");
		status = wait.waitForElementVisible(pim.photographLabel, 1);
		soft.assertEquals(status, "Photograph Label is not visible");
		status = wait.waitForElementVisible(pim.createLoginDetailLabel, 1);
		soft.assertEquals(status, "Create Login Detail Label is not visible");
		lib.clearValueFromEditField(pim.firstName);
		lib.setValueOnElement(pim.firstName, TD.fName);
		lib.clearValueFromEditField(pim.lastName);
		lib.setValueOnElement(pim.lastName, TD.lName);
		lib.clearValueFromEditField(pim.empID);
		lib.setValueOnElement(pim.empID, TD.empCode);
		lib.setValueOnElement(pim.chooseFile, "/Users/ashu/eclipse-workspace/OrangeHRM/src/test/resources/tony.jpeg");
		
		try {
			lib.clickOnElement(pim.btnSave);
			soft.assertAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
       //Steps for Deletion of an that Employee which has been created. TC Complete
	}
	
	public void objectDeclaration() {
		home = new HomePage(driver);
		pim = new PIMPage(driver);
		login = new LoginPage(driver);
		wait = new WaitHelper(driver);
		lib = new Library(driver);
	}
}
