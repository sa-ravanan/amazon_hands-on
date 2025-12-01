package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC006 extends AmazonBase{
	
	@Test
	@Parameters({"email" , "password"})
	public void passwordMaskTest(String email, String password) {
		HomePage home = new HomePage(driver, wait);
		boolean result = home.clickSigninButton()
			.enterUserId(email)
			.clickContinueButton()
			.enterPassword(password)
			.verifyPasswordMasking();
		System.out.println("Result:" + result);
		Assert.assertTrue(result, "Password is not masked!");
		System.out.println("Password masking verified successfully.");
	}
}
