package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC004 extends AmazonBase{

	@Test
	public void emptyUserIdTest() {
		HomePage home = new HomePage(driver, wait);
		String errorMessage = home.clickSigninButton()
			.clickContinueButton()
			.getDisplayedErrorMessage();
		System.out.println("Error Message: " + errorMessage);
		Assert.assertNotNull(errorMessage);
		Assert.assertTrue(errorMessage.contains("Enter your mobile number or email"));
	}
}
