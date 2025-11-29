package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;
import com.amazon.framework.pages.SigninPage;

public class TC002 extends AmazonBase{
	
	@Test
	@Parameters({"number" , "password"})
	public void invalidPasswordTest(String number, String password) {
		HomePage home = new HomePage(driver, wait);
		AmazonBase base = home.clickSigninButton()
			.enterUserId(number)
			.clickContinueButton()
			.enterPassword(password)
			.clickSigninButton();
		if(base instanceof SigninPage) {
			String error = ((SigninPage) base).getDisplayedErrorMessage();
			System.out.println(error);
			Assert.assertEquals(error, "Your password is incorrect");
		}
		else {
			Assert.fail("Expected to stay on SigninPage, but navigated to HomePage!");
		}
	}
}
