package com.amazon.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC001 extends AmazonBase{
	
	@Test
	@Parameters({"email" , "password"})
	public void validLoginTest(String email, String password) {
		HomePage home = new HomePage(driver, wait);
		Assert.assertTrue(home.isAmazonLogoDisplayed(), "Home page was not displayed successfully!");
		home.clickSigninButton()
			.waitUntilPageLoaded()
			.signin(email, password);
		String message = home.getWelcomeMessage();
		System.out.println("Welcome Message:" + message);
		Assert.assertTrue(message.contains("saravanan"));
	}

}
