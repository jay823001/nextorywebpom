package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class SubscriptionDetails extends BasePage{

	public SubscriptionDetails(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//a[@class = 'accountNavigation__link'])[2]")
	private WebElement SubscriptionDetails;
	
	@FindBy(xpath = "//h2[@class='accountInfo__heading']")
	private WebElement subs;
	
	@FindBy(xpath = "//p[@class = 'accountInfo__description']")
	private WebElement account_text;
	
	@FindBy(xpath = "//a[@class='btn btn--primary']")
	private WebElement changeSubs;
	
	public String getSubscriptionDetails() {
		
		waitTillElementIsVisible(subs);
		return subs.getText().trim();
	}
	
	public void clickToGetSubDetails() {
		
		waitTillElementIsVisible(SubscriptionDetails);
		waitTillElementIsClickable(SubscriptionDetails);
		SubscriptionDetails.click();
	}
	
	public String getAccountText() {
		
		waitTillElementIsVisible(account_text);
		return account_text.getText().trim();
	}
	
	public void clickToChangeSub() {
		
		waitTillElementIsVisible(changeSubs);
		waitTillElementIsClickable(changeSubs);
		changeSubs.click();
	}

}
