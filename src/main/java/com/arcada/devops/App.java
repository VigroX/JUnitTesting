package com.arcada.devops;

public class App 
{
	public static final String BASE_URL = System.getProperty("baseURL", "https://www.saucedemo.com/");
	public static final String USERNAME = System.getProperty("username", "standard_user");
	public static final String PASSWORD = System.getProperty("password", "secret_sauce");
	public static final Boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "false"));

	private App() {}
}
