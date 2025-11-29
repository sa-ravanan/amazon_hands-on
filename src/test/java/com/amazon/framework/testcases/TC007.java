package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC007 extends AmazonBase{
	@Test
	@Parameters({"productName"})
	public void typeIntoSeachBoxTest(String productName) {
		HomePage home = new HomePage(driver, wait);
		String text = home.typeProduct(productName)
			.getSearchBoxText();
		System.out.println("Product name: " + text);
		Assert.assertEquals(text, "Mobile");
	}
}
