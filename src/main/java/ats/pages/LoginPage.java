package ats.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ats.utilities.LoggerUtil;
import ats.utilities.WaitUtil;

public class LoginPage {
	
	private WebDriver driver; // WebDriver instance received from BaseTest
	
	/* -------------------- Locators -------------------- */
	private By loginMenu = By.id("customer_menu_top"); // Login menu (top right customer menu)
	private By username = By.id("loginFrm_loginname"); // Username input field
	private By password = By.id("loginFrm_password"); // Password input field
	private By loginBtn = By.cssSelector("button[title='Login']"); // Login button
	
	/* -------------------- Constructor -------------------- */
	public LoginPage(WebDriver driver) //It receives driver from test class. This driver will be used to perform actions on web elements
	{
		this.driver = driver;
	}
	
	/* -------------------- Action Method -------------------- */
	public void login(String user, String pass)
	{
		WaitUtil.waitForElementClickable(driver, loginMenu).click(); // Click on login menu
		WaitUtil.waitForElementVisible(driver, username).sendKeys(user); // Enter username
		WaitUtil.waitForElementVisible(driver, password).sendKeys(pass); // Enter password
		WaitUtil.waitForElementVisible(driver, loginBtn).click(); // Click login button
		LoggerUtil.info("Login Completed..."); // Log message for tracking in log file
	}
	    
}
