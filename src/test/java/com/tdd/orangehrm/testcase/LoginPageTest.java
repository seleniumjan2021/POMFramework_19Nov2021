package com.tdd.orangehrm.testcase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tdd.orangehrm.helper.base.TestBase;
import com.tdd.orangehrm.helper.config.TD;
import com.tdd.orangehrm.helper.javaScript.JavaScriptHelper;
import com.tdd.orangehrm.helper.lib.Library;
import com.tdd.orangehrm.helper.wait.WaitHelper;
import com.tdd.orangehrm.pages.LoginPage;

public class LoginPageTest extends TestBase {
	public LoginPage login;
	public boolean status = false;
	public WaitHelper wait;
	public JavaScriptHelper jsHelper;
	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200;

	@Test(description = "Application Logo Verification")
	public void verifyLogo() {
		objectDeclaration();
		status = wait.waitForElementVisible(login.orangeLogo, TD.standardWait, 500);
		Assert.assertTrue(status, "Failed! Logo Presence");
	}

	@Test(description = "Login with valid credentials")
	public void loginIntoApplicationValidCred() {
		objectDeclaration();
		login.loginOrganeHRM(brow.getConfigProperty().getProperty("admin_user"),
				brow.getConfigProperty().getProperty("admin_password"));
		status = wait.waitForElementVisible(login.welcome, TD.oneSecWait, 500);
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
		if (wait.waitForElementVisible(login.invalidPassMsg, TD.standardWait, 500)) {
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

	@Test(description = "Verify the Application Page Title")
	public void verifyPageTitle() {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, TD.pageTitle);
	}

	@Test(description = "Verify the social links are not broken! ")
	public void verifySocialLinksAreNotBroken() {
		objectDeclaration();
		jsHelper.scrollIntoView(login.socialLink);
		List<WebElement> links = driver.findElements(By.xpath("//*[@id='social-icons']/a"));
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url == null && url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				((HttpURLConnection) huc).setRequestMethod("HEAD");
				huc.connect();
				respCode = ((HttpURLConnection) huc).getResponseCode();
				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
					Assert.fail();
				} else {
					System.out.println(url + " is a valid link");
					Assert.assertTrue(true);
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void objectDeclaration() {
		login = new LoginPage(driver);
		wait = new WaitHelper(driver);
		lib = new Library(driver);
		jsHelper = new JavaScriptHelper(driver);
	}

}
