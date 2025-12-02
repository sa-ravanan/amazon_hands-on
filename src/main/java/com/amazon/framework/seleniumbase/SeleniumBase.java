package com.amazon.framework.seleniumbase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.framework.utils.Browser;
import com.amazon.framework.utils.Locators;

public class SeleniumBase implements SeleniumAPI{
	
	int timeOuts = 5;
	int maxWaitTime = 5;
	
	protected RemoteWebDriver driver;
	protected WebDriverWait wait = null;

	public RemoteWebDriver setBrowser(Browser browserName, String url) {
		try {
			switch(browserName) {
				case Chrome :
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					driver = new ChromeDriver(options);
					break;
				case Edge :
					driver = new EdgeDriver();
					break;
				case Firefox :
					driver = new FirefoxDriver();
					break;
				default :
					System.out.println("Unsupported Browser: " + browserName + " -Launching Chrome by default.");
					ChromeOptions option = new ChromeOptions();
					option.addArguments("--disable-notifications");
					driver = new ChromeDriver(option);
					break;
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOuts));
			driver.get(url);
			wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
			System.out.println(browserName + ": Browser launched Successfully: ");
			return driver;
		} catch (Exception e) {
			System.out.println("Failed to launch the Browser: " + e.getMessage());
			return null;
		}
		
	}
	
	public RemoteWebDriver setBrowser(String url) {
		return setBrowser(Browser.Chrome, url);
		
	}

	public void close() {
		if(driver != null) {
			try {
				driver.close();
				System.out.println("Browser window closed successfully.");
			} catch (NoSuchSessionException e) {
				System.out.println("Driver session is already closed or invalid!: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Unexpected error while closing browser: " + e.getMessage());
			}
		}
		else {
			System.out.println("No active driver session to close");
		}
		
		
	}

	public void quit() {
		if(driver != null) {
			try {
				driver.quit();
				driver = null;
				System.out.println("Browser session quit successfully and driver set to null.");
			} catch (NoSuchSessionException e) {
				System.out.println("Driver session is already closed or invalid!: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Unexpected error while closing browser: " + e.getMessage());
			}
		}
		else {
			System.out.println("No active driver session to quit");
		}
		
	}

	public WebElement element(Locators type, String value) {
		WebElement ele = null;
		switch(type) {
			case Id :
				ele = driver.findElement(By.id(value));
				break;
			case Name :
				ele = driver.findElement(By.name(value));
				break;
			case ClassName :
				ele = driver.findElement(By.className(value));
				break;
			case TagName :
				ele = driver.findElement(By.tagName(value));
				break;
			case LinkText :
				ele = driver.findElement(By.linkText(value));
				break;
			case PartialLinkText :
				ele = driver.findElement(By.partialLinkText(value));
				break;
			case XPath :
				ele = driver.findElement(By.xpath(value));
				break;
			case CssSelector :
				ele = driver.findElement(By.cssSelector(value));
				break;
			default :
				System.out.println("Locators is not defined");
		}
		return ele;
	}
	
	public WebElement element(By locator) {
		WebElement element = driver.findElement(locator);
		return element;
		
	}
	
	public List<WebElement> elements(Locators type, String value) {
		List<WebElement> ele = new ArrayList<WebElement>();
		switch(type) {
			case Id :
				ele.addAll(driver.findElements(By.id(value)));
				break;
			case Name :
				ele.addAll(driver.findElements(By.name(value)));
				break;
			case ClassName :
				ele.addAll(driver.findElements(By.className(value)));
				break;
			case TagName :
				ele.addAll(driver.findElements(By.tagName(value)));
				break;
			case LinkText :
				ele.addAll(driver.findElements(By.linkText(value)));
				break;
			case PartialLinkText :
				ele.addAll(driver.findElements(By.partialLinkText(value)));
				break;
			case XPath :
				ele.addAll(driver.findElements(By.xpath(value)));
				break;
			case CssSelector :
				ele.addAll(driver.findElements(By.cssSelector(value)));
				break;
			default :
				System.out.println("Locators is not defined");
		}
		return ele;
	}
	
	public List<WebElement> elements(By locators) {
		List<WebElement> ele = driver.findElements(locators);
		return ele;
		
	}

	public void switchToWindow(int i) {
		try {
			if(driver != null) {
				Set<String> windows = driver.getWindowHandles();
				List<String> windowList = new LinkedList<String>(windows);
				driver.switchTo().window(windowList.get(i));
			}
			else {
				System.out.println("No window present in the broswer to switch");
			}
		} catch (Exception e) {
			System.out.println("Problem occur while switching windows: " + e.getMessage());
		}
	}
	
	public WebElement isElementInteractable(WebElement ele) {
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(ele));
		} catch (StaleElementReferenceException e) {
			System.out.println("Stale element: The element is no longer attached to the DOM : " + e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println("Element is not interactable: " + e.getMessage());
			return null;
		}
		
	}
	
	
	public void selectValue(WebElement ele, String value) {
		WebElement element = isElementInteractable(ele);
		new Select(element).selectByValue(value);
		
	}

	public void selectText(WebElement ele, String text) {
		WebElement element = isElementInteractable(ele);
		new Select(element).selectByVisibleText(text);
	}

	public void selectIndex(WebElement ele, int position) {
		WebElement element = isElementInteractable(ele);
		new Select(element).selectByIndex(position);
		
	}

	public void deSelectValue(WebElement ele, String value) {
		WebElement element = isElementInteractable(ele);
		new Select(element).deselectByValue(value);
		
	}

	public void deSelectText(WebElement ele, String text) {
		WebElement element = isElementInteractable(ele);
		new Select(element).deselectByVisibleText(text);
		
	}

	public void deSelectIndex(WebElement ele, int position) {
		WebElement element = isElementInteractable(ele);
		new Select(element).deselectByIndex(position);
		
	}

	public void deSelectAll(WebElement ele) {
		WebElement element = isElementInteractable(ele);
		new Select(element).deselectAll();
		
	}

	public List<WebElement> getAllOptions(WebElement ele) {
		return new Select(ele).getOptions();
	}

	public String getFirstSelectedOption(WebElement ele) {
		return new Select(ele).getFirstSelectedOption().getText();
	}

	public void type(WebElement ele, String data) {
		WebElement element = isElementInteractable(ele);
		element.clear();
		element.sendKeys(data);
		
	}
	
	public void typeAndEnter(WebElement ele, String data) {
		WebElement element = isElementInteractable(ele);
		element.clear();
		element.sendKeys(data, Keys.ENTER);
	}

	public void appendText(WebElement ele, String data) {
		WebElement element = isElementInteractable(ele);
		element.sendKeys(" " + data);
		
	}

	public void click(WebElement ele) {
		WebElement element = isElementInteractable(ele);
		element.click();
		
	}
	
	public void dynamicClick(By locator, int index) {
		int attempt = 0;
		while (attempt < 3) {
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
				List<WebElement> elements = elements(locator);
				wait.until(ExpectedConditions.elementToBeClickable(elements.get(index)));
				click(elements.get(index));
				break;
			} catch (StaleElementReferenceException e) {
				attempt++;
				System.out.println("Retrying click due to stale element...Attempt: " + attempt);
			} 
		}
	}
	
	public String getText(WebElement ele) {
		WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
		return element.getText();
	}
	
	public String getAttributeValue(WebElement ele, String Attribute) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			return element.getAttribute(Attribute);
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return null;
		} catch(Exception e) {
			System.out.println("Element not found: " + e.getMessage());
			return null;
		}
	}

	public String getTitle() {
		try {
			if(driver == null) {
				System.out.println("No active driver session. Cannot retrieve title.");
				return null;
			}
			else {
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("Failed to get the page title: " + e.getMessage());
			return null;
		}
		
	}

	public String getURL() {
		try {
			if(driver == null) {
				System.out.println("No active driver session. Cannot retrieve URL.");
				return null;
			}
			else {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("Failed to get the page URL: " + e.getMessage());
			return null;
		}
		
	}

	public boolean isDisplayed(WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		
	}

	public boolean isEnabled(WebElement ele) {
		return ele.isEnabled();
		
	}

	public boolean isSelected(WebElement ele) {
		return ele.isSelected();
		
	}

	public void doubleClick(WebElement ele) {
		WebElement element = isElementInteractable(ele);
		new Actions(driver).doubleClick(element).perform();
		
	}

	public void rightClick(WebElement ele) {
		WebElement element = isElementInteractable(ele);
		new Actions(driver).contextClick(element).perform();
	}

	public void mouseOverOnElement(WebElement ele) {
		WebElement element = isElementInteractable(ele);
		new Actions(driver).moveToElement(element).perform();
		
	}

	public void clickAndHold(WebElement ele) {
		WebElement element = isElementInteractable(ele);
		new Actions(driver).clickAndHold(element).perform();
		
	}

	public void release() {
		new Actions(driver).release().perform();
		
	}
	
	public void refreshCurrentPage() {
		try {
			if(driver != null) {
				driver.navigate().refresh();
			}
			else {
				System.out.println("No active driver session to refresh");
			}
		} catch (Exception e) {
			System.out.println("Failed to refresh the page: " + e.getMessage());
		}
	}
	
	public void gotoPreviousPage() {
		try {
			if(driver != null) {
				driver.navigate().back();
			}
			else {
				System.out.println("No active driver session!..");
			}
		} catch (Exception e) {
			System.out.println("Failed to navigate the previous page: " + e.getMessage());
		}
	}
	
	public void gotoNextPage() {
		try {
			if(driver != null) {
				driver.navigate().forward();
			}
			else {
				System.out.println("No active driver session!..");
			}
		} catch (Exception e) {
			System.out.println("Failed to navigate the next page: " + e.getMessage());
		}
	}
	
}
