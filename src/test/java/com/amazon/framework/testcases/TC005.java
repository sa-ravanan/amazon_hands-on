package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;
import com.amazon.framework.pages.SigninPage;

public class TC005 extends AmazonBase{

	@Test
	@Parameters({"email"})
	public void emptyPasswordTest(String email) {
		HomePage home = new HomePage(driver, wait);
		AmazonBase base = home.clickSigninButton()
			.enterUserId(email)
			.clickContinueButton()
			.clickSigninButton();
		
		if(base instanceof SigninPage) {
			String errorMessage = ((SigninPage) base).getDisplayedErrorMessage();
			System.out.println("Error Message: " + errorMessage);
			Assert.assertNotNull(errorMessage);
			Assert.assertTrue(errorMessage.contains("Enter your password"));
		}
		else if(base instanceof HomePage) {
			System.out.println("Expected to stay on the SigninPage ,but navigated to HomePage");
			Assert.fail();
		}
		else {
			System.out.println("Some unexpected thing happened!...");
			Assert.fail();
		}
	}
}
