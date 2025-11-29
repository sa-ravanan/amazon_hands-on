package com.amazon.framework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.framework.amazonbase.AmazonBase;

public class ProductPage extends AmazonBase{

	public ProductPage(RemoteWebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");
	By buyNowButton = By.id("buy-now-button");
	By seeFullViewButton = By.linkText("Click to see full view");
	
	public ProductPage waitUntilPageLoaded() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
		return this;
		
	}
	
	public ProductPage clickAddToCartButton() {
		WebElement button = element(addToCartButton);
		click(button);
		return this;
	}
	
	public void clickBuyNowButton() {
		WebElement button = element(buyNowButton);
		click(button);
		
	}
	
	public void clickToSeeFullViewButton() {
		WebElement button = element(seeFullViewButton);
		click(button);
	}
	
}
