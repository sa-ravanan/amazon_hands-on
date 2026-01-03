package com.amazon.framework.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.framework.amazonbase.AmazonBase;

public class SigninPage extends AmazonBase{

	public SigninPage(RemoteWebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}

	By userIdField = By.name("email");
	By continueButton = By.xpath("//input[@type='submit']");
	By passwordField = By.name("password");
	By forgotPasswordLink = By.xpath("//a[contains(.,' Forgot password?')]");
	By changeUserIdLink = By.xpath("//a[contains(.,'Change')]");
	By signinButton = By.id("signInSubmit");
	By errorMessage = By.xpath("//i[@class='a-icon a-icon-alert']/following-sibling::div");

	public SigninPage waitUntilPageLoaded() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
		return this;
		
	}
	
	public SigninPage enterUserId(String userId) {
		WebElement inputField = element(userIdField);
		type(inputField, userId);
		return this;
	}

	public SigninPage clickContinueButton() {
		WebElement button = element(continueButton);
		click(button);
		return this;
	}

	public SigninPage enterPassword(String password) {
		WebElement inputField = element(passwordField);
		type(inputField, password);
		return this;

	}

	public AmazonBase clickSigninButton() {
		WebElement button = element(signinButton);
		click(button);
		List<WebElement> messages = elements(errorMessage);
		for(WebElement error : messages) {
			if(isDisplayed(error)) {
				return new SigninPage(driver, wait);
			}
		}
		return new HomePage(driver, wait);

	}

	public SigninPage clickChangeUserIdLink() {
		WebElement button = element(changeUserIdLink);
		click(button);
		return this;
	}

	public SigninPage clickForgotPasswordLink() {
		WebElement button = element(forgotPasswordLink);
		click(button);
		return this;
	}

	public AmazonBase signin(String userId, String password) {
		enterUserId(userId);
		clickContinueButton();
		enterPassword(password);
		return clickSigninButton();
	}
	
	public String getDisplayedErrorMessage() {
		List<WebElement> messages = elements(errorMessage);
		for(WebElement error : messages) {
			if(isDisplayed(error)) {
				String message = error.getText();
				return message;
			}
		}
		return null;
	}
	
	public boolean verifyPasswordMasking() {
		WebElement inputField = element(passwordField);
		String result = getAttributeValue(inputField, "type");
		System.out.println("Attribute type: " + result);
		if(result.contains("password")) {
			return true;
		}
		else {
			return false;
		}
	}

}
