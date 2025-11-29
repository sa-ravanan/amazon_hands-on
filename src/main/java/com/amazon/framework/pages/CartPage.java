package com.amazon.framework.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.framework.amazonbase.AmazonBase;

public class CartPage extends AmazonBase{

	public CartPage(RemoteWebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}

	By cartItems = By.xpath("//ul[@data-name='Active Items']/div[@data-bundleitem='absent']");
	By cartNames = By.xpath("(//ul[@data-name='Active Items']/div[@data-bundleitem='absent'])//span[@class='a-truncate-cut']");
	By itemDecrement = By.xpath("//button[@data-a-selector='decrement']");
	By itemIncrement = By.xpath("//button[@data-a-selector='increment']");
	By deleteItems = By.xpath("//input[@value='Delete']");


	public CartPage waitUntilPageLoaded() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
		return this;
		
	}
	
	public int getCartItemCount() {
		List<WebElement> cartItemElements = elements(cartItems);
		if(cartItemElements.isEmpty()) {
			return 0;
		}
		else {
			return cartItemElements.size();
		}
	}

	public List<String> getCartItemNames() {
		List<WebElement> cartItemElements = elements(cartNames);
		List<String> itemNames = new ArrayList<String>();
		for(WebElement element : cartItemElements) {
			itemNames.add(getText(element));
		}
		return itemNames;

	}

	public int getItemQuantity(int itemNumber) {
		List<WebElement> elements = elements(cartItems);
		WebElement ele = elements.get(itemNumber);
		return Integer.parseInt(ele.getAttribute("data-quantity"));

	}

	public CartPage decreaseItemCount(int itemNumber) {
		int oldQuantity = getItemQuantity(itemNumber);
		dynamicClick(itemDecrement, itemNumber);
		wait.until(driver -> {
			try {
				int newQuantity = getItemQuantity(itemNumber);
				return newQuantity < oldQuantity;
			} catch (Exception e) {
				return false;
			}
		});
		return this;

	}

	public CartPage increaseItemCount(int itemNumber) {
		int oldQuantity = getItemQuantity(itemNumber);
		dynamicClick(itemIncrement, itemNumber);
		wait.until(driver -> {
			try {
				int newQuantity = getItemQuantity(itemNumber);
				return newQuantity > oldQuantity;
			} catch (Exception e) {
				return false;
			}
		});
		return this;

	}

	public CartPage deleteItem(int itemNumber) {
		dynamicClick(deleteItems, itemNumber);
		refreshCurrentPage();
		return this;

	}

}
