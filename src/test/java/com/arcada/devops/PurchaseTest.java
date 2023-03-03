package com.arcada.devops;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseTest {
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
	public void purchaseTest() {
		driver.get(App.BASE_URL);
		driver.manage().window().setSize(new Dimension(1386, 1080));

		// Login
		LoginTest.login(driver, helper);

		// Add to cart
		helper.getData("add-to-cart-sauce-labs-backpack").click();
		helper.getCSS("#item_0_title_link > .inventory_item_name").click();
		helper.getData("add-to-cart-sauce-labs-bike-light").click();
		helper.getData("back-to-products").click();
		helper.getData("add-to-cart-sauce-labs-fleece-jacket").click();
		driver.findElement(By.linkText("3")).click();
		helper.getData("remove-sauce-labs-bike-light").click();
		{
			String cartSize = helper.getCSS(".shopping_cart_badge").getText();
			assertEquals("2", cartSize);
		}

		// Checkout
		helper.getData("checkout").click();
		helper.getData("firstName").sendKeys("John");
		helper.getData("lastName").sendKeys("Doe");
		helper.getData("postalCode").sendKeys("12345");
		helper.getData("continue").click();
		helper.getCSS(".cart_item:nth-child(3) > .cart_quantity").click();
		helper.getCSS(".cart_item:nth-child(3) > .cart_quantity").click();
		helper.getData("finish").click();
		{
			List<WebElement> elements = helper.getAllCSS(".complete-header");
			assertTrue(elements.size() > 0);
		}
		helper.getData("back-to-products").click();
	}
}
