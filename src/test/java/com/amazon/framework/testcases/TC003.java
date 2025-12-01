package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC003 extends AmazonBase{

	@Test
	@Parameters({"number"})
	public void invalidUserIdTest(String number) {
		HomePage home = new HomePage(driver, wait);
		String errorMessage = home.clickSigninButton()
			.enterUserId(number)
			.clickContinueButton()
			.getDisplayedErrorMessage();
		
		Assert.assertTrue(errorMessage.contains("Invalid"));
		System.out.println(errorMessage);
	}
}
