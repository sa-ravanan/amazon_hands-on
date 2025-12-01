package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC009 extends AmazonBase{

	@Test
	@Parameters({"invalidProductName"})
	public void invalidSearchProductTest(String productName) {
		HomePage home = new HomePage(driver, wait);
		boolean result = home.typeProduct(productName)
			.clickSearchButton()
			.isNoResultFound();
		
		Assert.assertTrue(result);
	}
}
