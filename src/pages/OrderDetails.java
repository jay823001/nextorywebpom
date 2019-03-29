package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class OrderDetails extends BasePage{

	public OrderDetails(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//a[@class = 'accountNavigation__link'])[5]")
	private WebElement orderDetails;
	
	@FindBy(xpath = "//a[@class='metaTable__value metaTable__value--link']")
	private WebElement Fakturanum;
	
	@FindBy(xpath = "//div[@id='newUI']//div[3]//div[2]//div[2]")
	private WebElement totalAmount;
	
	@FindBy(xpath = "//div[@class='accountInfo']//div[3]//div[1]//div[2]")
	private WebElement momAmount;
	
	@FindBy(xpath = "//a[@class='btn btn--secondary']")
	private WebElement OrderHist;
	
	public void clickTogetOrderDetails() {
		
		waitTillElementIsVisible(orderDetails);
		waitTillElementIsClickable(orderDetails);
		orderDetails.click();
	}
	
	public void clickOnFakutra() {
		
		waitTillElementIsVisible(Fakturanum);
		waitTillElementIsClickable(Fakturanum);
		Fakturanum.click();
	}
	
	public String getTotalAmount() {
		
		waitTillElementIsVisible(totalAmount);
		return totalAmount.getText().trim();
	}
	
	public String getMomAmount() {
		
		waitTillElementIsVisible(momAmount);
		return momAmount.getText().trim();
	}
	
	public void GoBackOrderHist() {
		
		waitTillElementIsVisible(OrderHist);
		waitTillElementIsClickable(OrderHist);
		OrderHist.click();
	}
	
	

}
