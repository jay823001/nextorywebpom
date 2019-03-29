package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generics.Property;

public class BasePage {
	
	public Logger log = Logger.getLogger(this.getClass());
	public WebDriver driver;
	public long timeout;
	public String configFile;
	
	public BasePage(WebDriver driver) {
		
		this.driver = driver;
		configFile = AutomationConstants.CONFIG_PATH + AutomationConstants.CONFIG_FILE;//./config/config.properties
		timeout = Long.parseLong(Property.getPropertyValue(configFile, "EXPLICIT"));
		
	}
	
	public void waitTillElementIsVisible(WebElement element) 
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitTillElementIsClickable(WebElement element)
	{
		//System.out.println(timeout);
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void verifyURLis(String expectedUrl) 
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.urlToBe(expectedUrl));
	}

	public void verifyURLhas(String expectedUrl) 
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.urlContains(expectedUrl));
	}

	public void moveToElement(WebElement element) 
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	public void scrollToMaximum()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
	}
	
	public void scrollToClick(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		
	}
	
}
