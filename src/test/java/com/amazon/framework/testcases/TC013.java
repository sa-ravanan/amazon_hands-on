package com.amazon.framework.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC013 extends AmazonBase{

	@Test
	@Parameters({"productName1"})
	public void pressEnterKeyTest(String productName) {
		HomePage home = new HomePage(driver, wait);
		Assert.assertTrue(home.isAmazonLogoDisplayed(), "Home page was not displayed successfully.");
		
		List<String> productTitles = home.searchProduct(productName)
			.waitUntilPageLoaded()
			.getAllProductTitles();
		int productCount = productTitles.size();
		
		int count = 0;
		for(String title : productTitles) {
			if(title.toLowerCase().contains("cycle")) {
				count++;
			}
		}
		
		int percentage = (int)(((double) count / productCount) * 100);
		System.out.println("Match percentage: " + percentage + "%");
		Assert.assertTrue(percentage >= 65, "Majority of the displayed results are not relevant to the entered keyword");
	}
}
