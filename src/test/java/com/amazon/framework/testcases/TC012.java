package com.amazon.framework.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC012 extends AmazonBase{

	@Test
	@Parameters({"productName"})
	public void searchButtonTest(String productName) {
		HomePage home = new HomePage(driver, wait);
		Assert.assertTrue(home.isAmazonLogoDisplayed(), "Home page was not displayed successfully.");
		
		List<String> allTitles = home.typeProduct(productName)
			.clickSearchButton()
			.waitUntilPageLoaded()
			.getAllProductTitles();
		
		int titlesCount = allTitles.size();
		int count = 0;
		for(String title : allTitles) {
			if(title.toLowerCase().contains("cycle")) {
				count++;
			}
		}
		
		int percentage = (int)(((double) count / titlesCount) * 100);
		System.out.println("Relevant results percentage: " + percentage);
		Assert.assertTrue(percentage >= 80, "Majority of the products are not relevant to the entered keyword");
	}
}
