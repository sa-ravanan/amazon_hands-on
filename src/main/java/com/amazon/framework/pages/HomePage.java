package com.amazon.framework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.utils.Locators;

public class HomePage extends AmazonBase{
	
	public HomePage(RemoteWebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	By amazonLogo = By.id("nav-logo");
	By locationButton = By.id("nav-global-location-popover-link");
	By pincodeField = By.xpath("//input[@aria-label='or enter an Indian pincode']");
	By applyButton = By.xpath("//span[.='Apply']");
	By category = By.xpath("//select[@id='searchDropdownBox']");
	By searchTextBox = By.id("twotabsearchtextbox");
	By searchSubmitButton = By.xpath("//input[@value='Go']");
	By languageLink = By.xpath("//a[contains(@aria-label, 'Choose a language for shopping in Amazon India.')]");
	By accountsAndListLink = By.xpath("(//a[@data-nav-role='signin'])[1]");
	By signinButton = By.xpath("(//span[text()='Sign in'])[1]");
	By saveLanguageButton = By.xpath("(//input[@type='submit'])[2]");
	By cartButton = By.id("nav-cart-count-container");
	By welcomeMessage = By.xpath("//div[@class='nav-line-1-container']/child::span");
	
	
	public HomePage waitUntilPageLoaded() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
		return this;
		
	}
	
	
	public boolean isAmazonLogoDisplayed() {
		WebElement logo = element(amazonLogo);
		return isDisplayed(logo);
		
	}
	
	public HomePage enterPincode(String pincode) {
		WebElement locationBtn = element(locationButton);
		click(locationBtn);
		WebElement inputField = element(pincodeField);
		type(inputField, pincode);
		WebElement applyBtn = element(applyButton);
		click(applyBtn);
		return this;
	}
	
	public HomePage selectCategory(String value) {
		WebElement option = element(category);
		selectText(option, value);
		return this;
	}
	
	public String getSelectedCategory() {
		WebElement option = element(category);
		return getFirstSelectedOption(option);
		
	}
	
	public boolean isSearchBoxPlaceholderDisplayed() {
		WebElement inputField = element(searchTextBox);
		String value = getAttributeValue(inputField, "placeholder");
		if(value!= null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getSearchBoxPlaceholder() {
		WebElement inputField = element(searchTextBox);
		return getAttributeValue(inputField, "placeholder");
	}
	
	public HomePage typeProduct(String text) {
		WebElement inputField = element(searchTextBox);
		type(inputField, text);
		return this;
	}
	
	public SearchResultPage searchProduct(String text) {
		WebElement inputField = element(searchTextBox);
		typeAndEnter(inputField, text);
		return new SearchResultPage(driver, wait);
	}
	
	public String getSearchBoxText() {
		WebElement inputField = element(searchTextBox);
		String result = getAttributeValue(inputField, "value");
		return result;
	}
	
	public SearchResultPage clickSearchButton() {
		WebElement button = element(searchSubmitButton);
		click(button);
		return new SearchResultPage(driver, wait);
		
	}
	
	public SigninPage clickSigninButton() {
		WebElement link = element(accountsAndListLink);
		WebElement button = element(signinButton);
		mouseOverOnElement(link);
		click(button);
		return new SigninPage(driver, wait);
	}
	
	public HomePage clickLanguageLink() {
		WebElement link = element(languageLink);
		click(link);
		return this;
	}
	
	public HomePage chooseLanguage(String language) {
		WebElement lanOption = element(Locators.XPath, "//span[.='" + language + "']");
		click(lanOption);
		return this;
		
	}
	
	public HomePage clickSaveLanguageButton() {
		WebElement button = element(saveLanguageButton);
		click(button);
		return this;
	}
	
	public HomePage changeLanguage(String language) {
		clickLanguageLink().chooseLanguage(language).clickSaveLanguageButton();
		return this;
		
	}
	
	public CartPage clickCartButton() {
		WebElement button = element(cartButton);
		click(button);
		return new CartPage(driver, wait);
		
	}
	
	public String getWelcomeMessage() {
		try {
			WebElement message = element(welcomeMessage);
			return getText(message);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
}
