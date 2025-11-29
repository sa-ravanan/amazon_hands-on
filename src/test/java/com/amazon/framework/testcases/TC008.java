package com.amazon.framework.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;

public class TC008 extends AmazonBase{

	@Test
	@Parameters({"productName"})
	public void validSearchProductTest(String productName) {
		HomePage home = new HomePage(driver, wait);
	    List<String> productTitles = home.typeProduct(productName)
			.clickSearchButton()
			.getAllProductTitles();
	    String title = getTitle();
	    Assert.assertTrue(title.contains(productName));
	    int productTitleCount = productTitles.size();
	    int count = 0;
		String[] keywords = {"phone", "mobile", "iphone", "4g" , "5g" , "4gb" , "6gb" , "8gb"};
	    for(String productTitle : productTitles) {
	    	for(String keyword : keywords) {
	    		if(productTitle.toLowerCase().contains(keyword)) {
	    			count++;
	    			break;
	    		}
	    	}
	    }
	    System.out.println("Total products displayed on search result page: " + productTitleCount);
	    System.out.println("Total products relevant to the search keyword: " + count);
	    boolean result = false;
	    if((productTitleCount - count) <= 5) {
	    	result = true;
	    }
	    Assert.assertTrue(result);
	}
}
