package com.amazon.framework.amazonbase;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.amazon.framework.seleniumbase.SeleniumBase;
import com.amazon.framework.utils.ExcelData;
import com.amazon.framework.utils.Locators;

public class AmazonBase extends SeleniumBase{
	
	String url = "https://www.amazon.in";
	protected String fileName  = "";
	
	@DataProvider(name = "loginDetails", indices = {1 , 2})
	public String[][] loginData() throws IOException {
		String[][] irctcLoginData = ExcelData.irctcLoginData(fileName);
		return irctcLoginData;
	}

	@BeforeMethod
	public void startApp() {
		setBrowser(url);
		
		List<WebElement> continueButton = elements(Locators.XPath, "//button[.='Continue shopping']");
		if(!continueButton.isEmpty()) {
			WebElement button = continueButton.get(0);
			if(isDisplayed(button)) {
				click(button);
			}
		}
		else {
			System.out.println("Continue shopping popup did not appear this time.");
		}
	}

	//@AfterMethod
	public void tearDown() {
		quit();
	}
}



