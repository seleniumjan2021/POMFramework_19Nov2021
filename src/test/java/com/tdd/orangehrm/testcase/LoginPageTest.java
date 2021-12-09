package com.tdd.orangehrm.testcase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.config.TD;
import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.wait.WaitHelper;
import com.tdd.orangehrm.pages.LoginPage;

public class LoginPageTest extends TestBase {
	public LoginPage login;
	public boolean status = false;
	public WaitHelper wait;

	@Test(description = "Logo Verification")
	public void verifyLogo() {
		objectDeclaration();
		status = wait.waitForElementVisible(login.orangeLogo, 10);
		Assert.assertTrue(status, "Failed! Logo Presence");
	}

	@Test(description = "Login with valid credentials")
	public void loginIntoApplicationValidCred() {
		objectDeclaration();
		login.loginOrganeHRM(brow.getConfigProperty().getProperty("admin_user"),
				brow.getConfigProperty().getProperty("admin_password"));
		status = wait.waitForElementVisible(login.welcome, 1);
		Assert.assertTrue(status, "Failed!! Login");
		try {
			lib.clickOnElement(login.welcome);
			lib.clickOnElement(login.logoutBtn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(description = "Login with Invalid Credentails")
	public void loginIntoAppplicationInvalidCred() {
		objectDeclaration();
		login.loginOrganeHRM(TD.adminUsername, TD.invalidPassword);
		if (wait.waitForElementVisible(login.invalidPassMsg, TD.standardWait)) {
			String invalidMessge = login.invalidPassMsg.getText();
			Assert.assertEquals(invalidMessge, "Invalid credentials");
		} else {
			Assert.fail();
		}

	}

	@Test(description = "Verify Forget password link")
	public void verifyForgetPassword() {
		List<WebElement> loginPagelinks = driver.findElements(By.tagName("href"));
		for (WebElement el : loginPagelinks) {
			if (el.getText().contains("Forgot your")) {
				Assert.assertEquals(el.getText(), "Forgot your password?");
			} else {
				Assert.fail();
			}
		}

	}

	public void objectDeclaration() {
		login = new LoginPage(driver);
		wait = new WaitHelper(driver);
		lib = new Library(driver);
	}

}
