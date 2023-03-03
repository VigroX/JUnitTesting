package com.arcada.devops;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumHelper {	
	private WebDriver driver;

	public SeleniumHelper() {
	}

	public SeleniumHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getID(String id) {
		return driver.findElement(By.id(id));
	}

	public WebElement getCSS(String cssSelector) {
		return driver.findElement(By.cssSelector(cssSelector));
	}

	public List<WebElement> getAllCSS(String cssSelector) {
		return driver.findElements(By.cssSelector(cssSelector));
	}

	public WebElement getData(String data) {
		return getCSS("*[data-test=\"" + data + "\"]");
	}

	public List<WebElement> getAllData(String data) {
		return getAllCSS("*[data-test=\"" + data + "\"]");
	}
}
