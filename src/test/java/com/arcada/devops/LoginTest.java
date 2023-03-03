package com.arcada.devops;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	private WebDriver driver;
	private SeleniumHelper helper;

	@Before
	public void setUp() {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("incognito");
		if (App.HEADLESS)
			options.addArguments("headless");

		driver = WebDriverManager.edgedriver().capabilities(options).create();
		helper = new SeleniumHelper(driver);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void loginTest() {
		driver.get(App.BASE_URL);
		driver.manage().window().setSize(new Dimension(1236, 932));

		login(driver, helper);
	}

	public static void login(WebDriver driver, SeleniumHelper helper) {
		helper.getID("login_button_container").click();
		helper.getData("username").sendKeys(App.USERNAME);
		helper.getData("password").sendKeys(App.PASSWORD);
		helper.getData("login-button").click();
		{
			List<WebElement> elements = helper.getAllData("error");
			assertEquals(0, elements.size());
		}
	}
}
