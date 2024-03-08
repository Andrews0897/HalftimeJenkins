package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import BaseClass.BaseClass;
import Locators.LoginLocators;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage extends BaseClass {
	
	LoginLocators loginLocators;

	@Given("I am on the login page")
	public void i_am_on_the_login_page() {

		browserLaunch("chrome");
		url("https://hti-uat.engage2serve.com");

	}

	@When("I enter valid username and password")
	public void i_enter_valid_username_and_password() throws InterruptedException {

		loginLocators = new LoginLocators();

		loginLocators.loginPage("htu-e2s@yopmail.com", "welcome@123");

	}

	@When("I click on the login button")
	public void i_click_on_the_login_button() {
		
		fluentclick(loginLocators.getLoginButton());

	}

}
