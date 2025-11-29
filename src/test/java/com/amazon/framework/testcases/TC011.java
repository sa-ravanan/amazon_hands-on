package com.amazon.framework.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC011 extends AmazonBase{
	
	@Test
	@Parameters({"partialName" , "completeName"})
	public void partialInputTest(String partialName, String completeName) {
		HomePage home = new HomePage(driver, wait);
		boolean result = home.isAmazonLogoDisplayed();
		Assert.assertTrue(result);
		
		List<String> partialResult = home.typeProduct(partialName)
			.clickSearchButton()
			.waitUntilPageLoaded()
			.getAllProductTitles();
		int partialCount = partialResult.size();
		
		List<String> completeResult = home.typeProduct(completeName)
			.clickSearchButton()
			.waitUntilPageLoaded()
			.getAllProductTitles();
		
		int count = 0;
		for(String partial : partialResult) {
			for(String complete : completeResult) {
				if(partial.toLowerCase().contains(complete.toLowerCase())) {
					count++;
					break;
				}
			}
		}
		System.out.println("partialInputResultCount: " + partialCount);
		System.out.println("Count: " + count);
		
		double matchPercentage = ((double) count / partialCount) * 100;
		int matchingPercentage = (int) matchPercentage;
		System.out.println("Match percentage: " + matchingPercentage);
		Assert.assertTrue(matchPercentage > 50, "Partial result does not display the relevant results.");
				
	}
}
