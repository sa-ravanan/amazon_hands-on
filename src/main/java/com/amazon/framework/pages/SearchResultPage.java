package com.amazon.framework.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.utils.Locators;

public class SearchResultPage extends AmazonBase{
	
	public SearchResultPage(RemoteWebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	By productTitles = By.xpath("//div[@data-cy='title-recipe']/child::a");
	By productPrices = By.xpath("//span[@class='a-price-whole']");
	By noResultFoundMessage = By.xpath("//span[.='No results for your search query']");
	By nextPageButton = By.linkText("Next");
	By previousPageButton = By.linkText("Previous");
	By customerRating = By.xpath("//i[@data-cy='reviews-ratings-slot']/child::span");

	public SearchResultPage waitUntilPageLoaded() {
		wait.until(driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete"));
		return this;
	}
	
	public boolean isNoResultFound() {
		WebElement message = element(noResultFoundMessage);
		return isDisplayed(message);
	}
	
	public SearchResultPage selectBrand(String brandName) {
		WebElement brand = element(Locators.XPath, "(//div[@id='brandsRefinements'])//span[contains(text(),'" + brandName + "')]");
		click(brand);
		brand = element(Locators.XPath, "(//div[@id='brandsRefinements'])//span[contains(text(),'" + brandName + "')]");
		wait.until(ExpectedConditions.visibilityOf(brand));
		return this;
		
	}
	
	public List<String> getAllProductTitles(){
		List<String> allTitles = new ArrayList<String>();
		List<WebElement> titles = elements(productTitles);
		for(WebElement productTitle : titles) {
			allTitles.add(productTitle.getText().trim());
		}
		return allTitles;
		
	}
	
	public List<Integer> getAllProductPrice(){
		List<Integer> allPrices = new ArrayList<Integer>();
		List<WebElement> prices = elements(productPrices);
		for(WebElement productPrice : prices) {
			int price = Integer.parseInt(productPrice.getText().replace(",", ""));
			allPrices.add(price);
		}
		return allPrices;
		
	}
	
	public ProductPage selectProductByIndex(int i) {
		List<WebElement> titles = elements(productTitles);
		WebElement product = titles.get(i);
		click(product);
		switchToWindow(1);
		return new ProductPage(driver, wait);
		
	}
	
	public ProductPage selectProductByTitle(String title) {
		List<WebElement> titles = elements(productTitles);
		for(WebElement productTitle : titles) {
			String productName = productTitle.getText().toLowerCase();
			if(productName.contains(title)) {
				click(productTitle);
				switchToWindow(1);
				break;
			}
		}
		return new ProductPage(driver, wait);
		
	}
	
	public ProductPage selectProductByPrice(int price) {
		List<WebElement> prices = elements(productPrices);
		for(WebElement productPrice : prices) {
			int pri = Integer.parseInt(productPrice.getText().replace(",", ""));
			if(price == pri) {
				click(productPrice);
				switchToWindow(1);
				break;
			}
		}
		return new ProductPage(driver, wait);
		
	}
	
	public SearchResultPage goToNextPage() {
		WebElement button = element(nextPageButton);
		click(button);
		waitUntilPageLoaded();
		return this;
		
	}
	
	public SearchResultPage goToPreviousPage() {
		WebElement button = element(previousPageButton);
		click(button);
		waitUntilPageLoaded();
		return this;
		
	}
	

}
