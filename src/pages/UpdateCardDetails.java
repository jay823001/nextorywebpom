package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class UpdateCardDetails extends BasePage{

	public UpdateCardDetails(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//a[@class = 'accountNavigation__link'])[3]")
	private WebElement CardDetails;
	
	@FindBy(xpath = "//p[@class='accountInfo__description']")
	private WebElement accountText;
	
	@FindBy(xpath = "//a[@class='btn btn--secondary']")
	private WebElement updateCard;
	
	public void clickToGetCardDetails() {
		
		waitTillElementIsVisible(CardDetails);
		waitTillElementIsClickable(CardDetails);
		CardDetails.click();
	}
	
	public String getAccountText() {
		
		waitTillElementIsVisible(accountText);
		return accountText.getText().trim();
	}
	
	public void clickToUpdateCard() {
		
		waitTillElementIsVisible(updateCard);
		waitTillElementIsClickable(updateCard);
		updateCard.click();
	}
}
