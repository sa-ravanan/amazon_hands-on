package com.amazon.framework.testcases;

import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.amazon.framework.amazonbase.AmazonBase;
import com.amazon.framework.pages.HomePage;
import com.amazon.framework.pages.SearchResultPage;

public class TC015 extends AmazonBase{

	@Test
	@Parameters({"productName" , "brandName"})
	public void verifyBrandFilterOption(String productName, String brandName) {
		SoftAssert soft = new SoftAssert();
		HomePage home = new HomePage(driver, wait);
		soft.assertTrue(home.isAmazonLogoDisplayed(), "Home page is not diaplyed successfully.");

		SearchResultPage result = home.typeProduct(productName)
				.clickSearchButton();
		List<String> initialProducts = result.waitUntilPageLoaded()
				.getAllProductTitles();
		int initialCount = initialProducts.size();

		List<String> filteredProducts = result.selectBrand(brandName)
				.getAllProductTitles();
		int filterCount = filteredProducts.size();

		soft.assertTrue(initialCount >= filterCount, "Filtered results should not be more than initial results");
		
		int count = 0;
		for(String product : filteredProducts) {
			if(product.toLowerCase().contains(brandName.toLowerCase())) {
				count++;
			}
		}
		System.out.println("filteredProducts: " + filterCount);
		System.out.println("Products that met filtered criteria: " + count);
		int percentage = (int) (((double) count / filterCount) * 100);
		System.out.println("Percentage of relevant products that met filter criteria: " + percentage + "%");
		soft.assertTrue(percentage >= 80 , "Most of the products does not met the filtered criteria");
		
		soft.assertAll();

	}
}
