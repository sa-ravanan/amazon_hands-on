package com.amazon.framework.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;
import com.amazon.framework.pages.ProductPage;
import com.amazon.framework.pages.SearchResultPage;
import com.amazon.framework.utils.Locators;

public class Practice extends AmazonBase{
	
	@BeforeTest
	public void setExcel() {
		fileName = "Amazon_Login";
	}
	
	@Test(dataProvider = "loginDetails")
	public void loginTest(String userId, String password) {
		HomePage home = new HomePage(driver, wait);
		home.clickSigninButton()
			.enterUserId(userId)
			.clickContinueButton()
			.enterPassword(password)
			.clickSigninButton();
		String welcomeMessage = element(Locators.XPath, "//span[.='Hello, saravanan']").getText();
		Assert.assertEquals(welcomeMessage, "Hello, saravanan");
	}
	
	@Test
	public void changeLanguage() {
		HomePage home = new HomePage(driver, wait);
		home.typeProduct("Phones")
			.clickSearchButton();
		wait.until(ExpectedConditions.titleContains("Phones"));
		List<WebElement> allProduct = driver.findElements(By.xpath("//div[@data-cy='title-recipe']"));
		List<WebElement> iPhoneProduct = new ArrayList<WebElement>();
		for(WebElement product : allProduct) {
			String text = product.getText().toLowerCase();
			if(text.contains("iphone")) {
				iPhoneProduct.add(product);
				System.out.println(product.getText());
			}
		}
		click(iPhoneProduct.get(0));
		switchToWindow(1);
	}
	
	@Test
	public void selectProduct() throws InterruptedException {
		HomePage home = new HomePage(driver, wait);
		home.clickSigninButton()
			.signin("8220515632", "Sarav$10925");
		home.typeProduct("Watches")
			.clickSearchButton();
		
		SearchResultPage result = new SearchResultPage(driver, wait);
		result.selectBrand("Titan");
		wait.until(ExpectedConditions.visibilityOf(element(Locators.LinkText, "Next")));
		List<String> allTitles = result.getAllProductTitles();
		for(String title : allTitles) {
			System.out.println(title);
		}
		result.selectProductByTitle("analog gold dial men's watch");
		
		ProductPage product = new ProductPage(driver, wait);
		product.clickAddToCartButton();
	}
	
	@Test
	public void chooseLanguage() {
		HomePage home = new HomePage(driver, wait);
		home.clickSigninButton()
			.signin("8220515632", "Sarav$1092");
		String actualMessage = home.getWelcomeMessage();
		System.out.println("Actual Message: " + actualMessage );
		Assert.assertTrue(actualMessage.contains("saravanan"));
	}
	
	@Test
	public void cartTest(){ 
		SoftAssert soft = new SoftAssert();
		HomePage home = new HomePage(driver, wait);
		home.clickSigninButton()
			.signin("8220515632", "Sarav$10925");
		String actualMessage = home.getWelcomeMessage();
		soft.assertTrue(actualMessage.contains("saravanan"));
		int quantity = home.clickCartButton()
			.increaseItemCount(1)
			.increaseItemCount(1)
			.increaseItemCount(1)
			.decreaseItemCount(1)
			.getItemQuantity(1);
		soft.assertEquals(quantity, 3);
		soft.assertAll();
	}
	
	@Test(dataProvider = "loginDetails")
	public void excelLogin(String[] data) {
		HomePage home = new HomePage(driver, wait);
		home.clickSigninButton()
			.signin(data[0], data[1]);
		String message = home.getWelcomeMessage();
		Assert.assertNotNull(message, "Incorrect Password");
		Assert.assertTrue(message.toLowerCase().contains("saravanan"), "Login failed!");
		
	}
	
	

}
