package Locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class LoginLocators extends BaseClass{
	
	public LoginLocators() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//input[@name='usrnm'])[1]")
	private WebElement userName;
	
	@FindBy(xpath = "//input[@placeholder='Enter your password']")
	private WebElement userPassword;
	
	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getUserPassword() {
		return userPassword;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	
	public void loginPage(String userName, String userPassword) throws InterruptedException {

			fluWaitToFillTextBox(getUserName(), userName);
			Thread.sleep(2000);
			fluWaitToFillTextBox(getUserPassword(), userPassword);

		}
		
	
	
	

}
