package com.amazon.framework.seleniumbase;

import java.util.List;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.amazon.framework.utils.Browser;
import com.amazon.framework.utils.Locators;

public interface SeleniumAPI {
	
	/**
	 * This method launches chrome browser and navigates to the given url
	 * @author Saravanan
	 * @param url
	 * @return 
	 * @exception NullPointerException
	 */
	RemoteWebDriver setBrowser(String url);
	
	/**
	 * This method launches the specific browser and navigates to the given url
	 * @author Saravanan
	 * @param browserName
	 * @param url
	 * @return 
	 * @exception NullPointerException
	 */
	RemoteWebDriver setBrowser(Browser browserName, String url);
	
	/**
	 * This method is used to close the current browser window
	 * @author Saravanan
	 */
	void close();
	
	/**
	 * This method is used to close all windows opened by WebDriver
	 * @author Saravanan
	 */
	void quit();
	
	/**
	 * This method is used to find any web element with in the page and return it
	 * @author Saravanan
	 * @param type
	 * @param value
	 * @return WebElement
	 * @exception NoSuchElementException
	 */
	WebElement element(Locators type, String value);
	
	
	/**
	 * Thiw method is used to find list of web elements in the web page and return it
	 * @author Saravanan
	 * @param type
	 * @param value
	 * @return List<WebElement>
	 */
	List<WebElement> elements(Locators type, String value);
	
	/**
	 * This method is used to switch between windows
	 * @author Saravanan
	 * @param i
	 * @exception NoSuchWindowException
	 */
	void switchToWindow(int i);
	
	/**
	 * This method is used to select the dropdown with the given value
	 * @author Saravanan
	 * @param ele
	 * @param value
	 */
	void selectValue(WebElement ele, String value);
	
	/**
	 * This method is used to select the dropdown with the given text
	 * @author Saravanan
	 * @param ele
	 * @param text
	 */
	void selectText(WebElement ele, String text);
	
	/**
	 * This method is used to select the drop-down with the given index
	 * @author Saravanan
	 * @param ele
	 * @param position
	 */
	void selectIndex(WebElement ele, int position);
	
	/**
	 * This method is used to Deselect the drop-down with the given value
	 * @author Saravanan
	 * @param ele
	 * @param value
	 */
	void deSelectValue(WebElement ele, String value);
	
	/**
	 * This method is used to Deselect the drop-down with the given text
	 * @author Saravanan
	 * @param ele
	 * @param text
	 */
	void deSelectText(WebElement ele, String text);
	
	/**
	 * This method is used to Deselect the drop-down with the given index
	 * @author Saravanan
	 * @param ele
	 * @param position
	 */
	void deSelectIndex(WebElement ele, int position);
	
	/**
	 * This method is used to Deselect all the options in a multi-select dropdown
	 * @author Saravanan
	 * @param ele
	 */
	void deSelectAll(WebElement ele);
	
	/**
	 * This method is used to return the List of all available options
	 * @author Saravanan
	 * @param ele
	 * @return List<WebElement>
	 */
	List<WebElement> getAllOptions(WebElement ele);
	
	/**
	 * This method is used to return First selected option in drop-down
	 * @author Saravanan
	 * @param ele
	 * @return String
	 */
	String getFirstSelectedOption(WebElement ele);
	
	/**
	 * This method waits for the element to be enabled and clear the existing value and type the data
	 * @author Saravanan
	 * @param ele
	 * @param data
	 */
	void type(WebElement ele, String data);
	
	
	/**
	 * This method waits for the element to be enabled and append the data
	 * @author Saravanan
	 * @param ele
	 * @param message
	 */
	void appendText(WebElement ele, String data);
	
	
	/**
	 * This method waits for the element is clickable and then click
	 * @author Saravanan
	 * @param ele
	 */
	void click(WebElement ele);
	
	/**
	 * This method is used to return the current page title
	 * @author Saravanan
	 * @return String
	 */
	String getTitle();
	
	/**
	 * This method is used to return the current page URL
	 * @author Saravanan
	 * @return String
	 */
	String getURL();
	
	/**
	 * This method is used to check whether the element is displayed on the page or not
	 * @author Saravanan
	 * @param ele
	 * @return boolean
	 */
	boolean isDisplayed(WebElement ele);
	
	/**
	 * This method is used to check whether the element is enabled on the page or not
	 * @author Saravanan
	 * @param ele
	 * @return boolean
	 */
	boolean isEnabled(WebElement ele);
	
	/**
	 * This method is used to check whether the element is selected on the dropdown or not
	 * @author Saravanan
	 * @param ele
	 * @return
	 */
	boolean isSelected(WebElement ele);
	
	/**
	 * This method waits for the element to be clickable and double click it
	 * @author Saravanan
	 * @param ele
	 */
	void doubleClick(WebElement ele);
	
	/**
	 * This method waits for the element to be clickable and right click it
	 * @author Saravanan
	 * @param ele
	 */
	void rightClick(WebElement ele);
	
	/**
	 * This method is used to move the mouse to the element
	 * @author Saravanan
	 * @param ele
	 */
	void mouseOverOnElement(WebElement ele);
	
	/**
	 * This method waits for the element to be clickable, then click and hold it
	 * @author Saravanan
	 * @param ele
	 */
	void clickAndHold(WebElement ele);
	
	/**
	 * This method is used to release the mouse click
	 * @author Saravanan
	 */
	void release();
	
	/**
	 * This method is used to refresh the current page
	 * @author Saravanan
	 */
	void refreshCurrentPage();
	
	/**
	 * This method is used to navigate to the previous page
	 * @author Saravanan
	 */
	void gotoPreviousPage();
	
	/**
	 * This method is used to navigate to the next page
	 * @author Saravanan
	 */
	void gotoNextPage();
	
	
}
