package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC014 extends AmazonBase{

	@Test
	public void placeHolderTest() {
		HomePage home = new HomePage(driver, wait);
		Assert.assertTrue(home.isAmazonLogoDisplayed());
		
		Assert.assertTrue(home.isSearchBoxPlaceholderDisplayed(), "Search box placeholder is not displayed on the screen");
		
		String placeholderText = home.getSearchBoxPlaceholder();
		System.out.println("Placeholder text: " + placeholderText);
		Assert.assertEquals(placeholderText, "Search Amazon.in", "Placeholder does not match the expected value.");
	}
}
