package com.amazon.framework.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC010 extends AmazonBase{

	@Test
	@Parameters({"productName"})
	public void checkCaseSensitiveSearchBoxTest(String productName) {
		HomePage home = new HomePage(driver, wait);
		boolean result = home.isAmazonLogoDisplayed();
		Assert.assertTrue(result);
		
		List<String> resultMixed = home.typeProduct(productName)
			.clickSearchButton()
			.waitUntilPageLoaded()
			.getAllProductTitles();
		
		List<String> resultLower = home.typeProduct(productName.toLowerCase())
				.clickSearchButton()
				.waitUntilPageLoaded()
				.getAllProductTitles();
		
		List<String> resultUpper = home.typeProduct(productName.toUpperCase())
				.clickSearchButton()
				.waitUntilPageLoaded()
				.getAllProductTitles();
		Assert.assertFalse(resultMixed.isEmpty());
		Assert.assertFalse(resultLower.isEmpty());
		Assert.assertFalse(resultUpper.isEmpty());
		
		int lowerMatch = 0;
		for(String mixed : resultMixed) {
			for(String lower : resultLower) {
				if(mixed.equalsIgnoreCase(lower)) {
					lowerMatch++;
					break;
				}
			}
		}
		
		int upperMatch = 0;
		for(String mixed : resultMixed) {
			for(String upper : resultUpper) {
				if(mixed.equalsIgnoreCase(upper)) {
					upperMatch++;
					break;
				}
			}
		}
		
		double similarity = (double)((lowerMatch + upperMatch) / 2) / resultMixed.size();
		System.out.println("Similarity between mixed and upper, lower: " + (similarity*100) + "%");
		Assert.assertTrue(similarity > 0.85, "Search result differ significantly across cases!");
		
		
	}
}
