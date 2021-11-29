package com.tdd.orangehrm.testcase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.browser.BrowserConfig;
import com.tdd.orangehrm.helper.config.TD;
import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.wait.WaitHelper;
import com.tdd.orangehrm.pages.LoginPage;

import io.reactivex.rxjava3.internal.util.ListAddBiConsumer;

public class LoginPageTest extends TestBase {
	public LoginPage login;	
	public BrowserConfig browse;
	public boolean status = false;
	@BeforeMethod
	public void objectDeclaration() {
		login = new LoginPage(driver);
		wait = new WaitHelper(driver);
		lib = new Library(driver);
		browse = new BrowserConfig();
	}
	
	@Test(priority = 0, description = "Logo Verification")
	public void verifyLogo() {
		
		status = wait.waitForElementVisible(login.orangeLogo, 10);
		Assert.assertTrue(status, "Failed! Logo Presence");
	}

	@Test(priority = 1, description = "Login with valid credentials")
	public void loginIntoApplicationValidCred() {
		login.loginOrganeHRM(browse.getConfigProperty().getProperty("admin_user"),browse.getConfigProperty().getProperty("admin_password"));
		status = wait.waitForElementVisible(login.welcome, 5);
		Assert.assertTrue(status, "Failed!! Login");
		lib.clickOnElement(login.welcome);
		lib.clickOnElement(login.logoutBtn);
	}
	
	@Test(priority = 2, description = "Login with Invalid Credentails")
	public void loginIntoAppplicationInvalidCred() {
		login.loginOrganeHRM(TD.adminUsername, TD.invalidPassword);
		if(wait.waitForElementVisible(login.invalidPassMsg,TD.standardWait)) {
			String invalidMessge = login.invalidPassMsg.getText();
			Assert.assertEquals(invalidMessge, "Invalid credentials");
		}else {
			Assert.fail();
		}
		
	}
	
	@Test(priority = 3 , description = "Verify Forget password link")
	public void verifyForgetPassword() {
		List<WebElement> loginPagelinks =  driver.findElements(By.tagName("href"));
		for(WebElement el : loginPagelinks) {
			if(el.getText().contains("Forgot your")) {
				Assert.assertEquals(el.getText(), "Forgot your password?");
			}
			else {
				Assert.fail();
			}
		}
		
	}
	
}
