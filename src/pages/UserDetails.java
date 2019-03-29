package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class UserDetails extends BasePage{
	
	@FindBy(xpath = "//input[@name = 'firstName']")
	private WebElement firstname;
	
	@FindBy(xpath = "//input[@name = 'lastName']")
	private WebElement lastname;
	
	@FindBy(xpath = "//input[@name = 'phoneNumber']")
	private WebElement cellphone;
	
	@FindBy(xpath = "//button[@type = 'submit']")
	private WebElement Forsatt;
	
	@FindBy(xpath = "//a[@class = 'btn btn--secondary']")
	private WebElement MittKonto;
	
	public UserDetails(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName() {
		
		waitTillElementIsVisible(firstname);
		firstname.clear();
		firstname.sendKeys("test firstname");
	}
	
	public void enterLastname() {
		
		waitTillElementIsVisible(lastname);
		lastname.clear();
		lastname.sendKeys("test lastname");
	}
	
	public void enterPhoneNumber() {
		
		waitTillElementIsVisible(cellphone);
		cellphone.clear();
		cellphone.sendKeys("87619809874");
	}
	
	public void clickToContinue() {
		
		waitTillElementIsVisible(Forsatt);
		waitTillElementIsClickable(Forsatt);
		Forsatt.click();
	}
	
	public void clickOnMittKonto() {
		
		waitTillElementIsVisible(MittKonto);
		waitTillElementIsClickable(MittKonto);
		MittKonto.click();
	}
}
