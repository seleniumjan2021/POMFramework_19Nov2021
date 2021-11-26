package com.tdd.orangehrm.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.wait.WaitHelper;
import com.tdd.orangehrm.pages.LoginPage;

public class LoginPageTest extends TestBase {
	public LoginPage login;	
	
	@BeforeMethod
	public void objectDeclaration() {
		login = new LoginPage(driver);
		wait = new WaitHelper(driver);
		lib = new Library(driver);
	}
	
	@Test(priority = 0, description = "Logo Verification")
	public void verifyLogo() {
		
		boolean logoVisiblity = wait.waitForElementVisible(login.orangeLogo, 10);
		Assert.assertTrue(logoVisiblity, "Failed! Logo Presence");
	}

	@Test(priority = 1, description = "Login with valid credentials")
	public void loginIntoApplication() {
		login.loginOrganeHRM("Admin", "admin123");
		boolean loginStatus = wait.waitForElementVisible(login.welcome, 5);
		Assert.assertTrue(loginStatus, "Failed!! Login");
	}
	
}
