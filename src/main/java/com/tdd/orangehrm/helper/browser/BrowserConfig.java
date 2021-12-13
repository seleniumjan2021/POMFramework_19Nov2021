package com.tdd.orangehrm.helper.browser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.tdd.orangehrm.helper.logger.LoggerHelper;
import com.tdd.orangehrm.helper.resources.ResourceHelper;

public class BrowserConfig {

	@SuppressWarnings("unused")
	private static Logger log = LoggerHelper.getLogger(BrowserConfig.class);
	public WebDriver driver;

	// P->F->p->l
	public Properties getConfigProperty() {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(ResourceHelper.getProjectPath() + "src/main/java/com/tdd"
					+ "/orangehrm/helper/config/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public WebDriver getDriver() {

		if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			if (getConfigProperty().get("browser").toString().contains("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						ResourceHelper.getResourcePath() + "driver/chromedriver 2");
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--test-type");
				option.addArguments("--disable-pop-blocking");
				option.addArguments("allow-file-access-from-files");
				option.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				DesiredCapabilities chrome = DesiredCapabilities.chrome();
				chrome.setJavascriptEnabled(true);
				option.setCapability(ChromeOptions.CAPABILITY, chrome);
				return new ChromeDriver(option);
			} else if (getConfigProperty().get("browser").toString().contains("firefox")) {
				System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath() + "driver/geckodriver 2");
				DesiredCapabilities firefox = DesiredCapabilities.firefox();
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				profile.setAssumeUntrustedCertificateIssuer(true);
				firefox.setCapability(FirefoxDriver.PROFILE, profile);
				firefox.setCapability("marionette", true);
				FirefoxOptions firefoxOptions = new FirefoxOptions(firefox);
				return new FirefoxDriver(firefoxOptions);
			}
		} else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			if (getConfigProperty().get("browser").toString().contains("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						ResourceHelper.getResourcePath() + "driver/chromedriver.exe");
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--test-type");
				option.addArguments("--disable-pop-blocking");
				option.addArguments("allow-file-access-from-files");
				option.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				DesiredCapabilities chrome = DesiredCapabilities.chrome();
				chrome.setJavascriptEnabled(true);
				option.setCapability(ChromeOptions.CAPABILITY, chrome);
				return new ChromeDriver(option);
			} else if (getConfigProperty().get("browser").toString().contains("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						ResourceHelper.getResourcePath() + "driver/geckodriver 2.exe");
				DesiredCapabilities firefox = DesiredCapabilities.firefox();
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				profile.setAssumeUntrustedCertificateIssuer(true);
				firefox.setCapability(FirefoxDriver.PROFILE, profile);
				firefox.setCapability("marionette", true);
				FirefoxOptions firefoxOptions = new FirefoxOptions(firefox);
				return new FirefoxDriver(firefoxOptions);
			}
		}
		return driver;

	}
}
