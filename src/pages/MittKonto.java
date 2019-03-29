package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class MittKonto extends BasePage{
	
	@FindBy(xpath = "//input[@name = 'email']")
	private WebElement emailAddress;
	
	@FindBy(xpath = "//input[@name = 'password']")
	private WebElement password;
	
	@FindBy(xpath = "//input[@name = 'firstname']")
	private WebElement firstname;
	
	@FindBy(xpath = "//input[@name = 'lastname']")
	private WebElement lastname;
	
	@FindBy(xpath = "//input[@name = 'address']")
	private WebElement address;
	
	@FindBy(xpath = "//input[@name = 'postcode']")
	private WebElement postcode;
	
	@FindBy(xpath = "//input[@name = 'city']")
	private WebElement city;
	
	@FindBy(xpath = "//input[@name = 'cellphone']")
	private WebElement cellphone;
	
	@FindBy(xpath = "//button[contains(text(),'Spara uppgifter')]")
	private WebElement update;
	
	@FindBy(xpath = "//a[contains(text(),'Ändra abonnemang')]")
	private WebElement changesub;
	
	@FindBy(xpath = "//a[contains(text(),'Ändra betalningsuppgifter')]")
	private WebElement cardupdate;
	
	@FindBy(xpath = "//a[contains(text(),'Avsluta abonnemang')]")
	private WebElement cancelsub;
	
	@FindBy(xpath = "//span[contains(text(),'Logga ut')]")
	private WebElement logout;
	
	@FindBy(xpath = "//a[contains(text(),'Mitt konto')]")
	private WebElement MittKonto;
	
	/*@FindBy(xpath = "//span[text()='kr/månad']")
	private WebElement SubscriptionDetails;
	
	@FindBy(xpath = "//p[@class='sc-kQsIoO fqcVWP']/strong")
	private WebElement NextRunDate;*/
	
	public MittKonto(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmail() {
		
		waitTillElementIsVisible(emailAddress);
		emailAddress.clear();
		emailAddress.sendKeys("naniee435@gmail.com");
	}
	
	public void enterpassword() {
		
		waitTillElementIsVisible(password);
		password.clear();
		password.sendKeys("password123");
	}
	
	public void enterFirstName() {
		
		waitTillElementIsVisible(firstname);
		firstname.clear();
		firstname.sendKeys("Narasimharao");
	}
	
	public void enterLastName() {
		
		waitTillElementIsVisible(lastname);
		lastname.clear();
		lastname.sendKeys("Grandhi");
	}
	
	public void enterAddress() {
		
		waitTillElementIsVisible(address);
		address.clear();
		address.sendKeys("Bangalore");
	}
	
	public void enterPostcode() {
		
		waitTillElementIsVisible(postcode);
		postcode.clear();
		postcode.sendKeys("560057");
	}
	
	public void enterCity() {
		
		scrollToMaximum();
		moveToElement(city);
		waitTillElementIsVisible(city);
		city.clear();
		city.sendKeys("Bangalore");
	}
	
	public void enterCellPhone() {
		
		waitTillElementIsVisible(cellphone);
		cellphone.clear();
		cellphone.sendKeys("87650945478");
	}
	
	public void updateDetails() {
		
		waitTillElementIsVisible(update);
		waitTillElementIsClickable(update);
		update.click();
	}
	
	public void ChangeSub() {
		
		waitTillElementIsVisible(changesub);
		waitTillElementIsClickable(changesub);
		changesub.click();
	} 
	
	public void CardUpdate() {
		
		waitTillElementIsVisible(cardupdate);
		waitTillElementIsClickable(cardupdate);
		cardupdate.click();
	}
	
	public void CancelSub() {
		
		scrollToClick(cancelsub);
		/*waitTillElementIsVisible(cancelsub);
		waitTillElementIsClickable(cancelsub);
		cancelsub.click();*/
	}
	
	public void LogOut() {
		
		scrollToMaximum();
		moveToElement(logout);
		waitTillElementIsVisible(logout);
		waitTillElementIsClickable(logout);
		logout.click();
	}
	
	public void clickMittKonto() {
		
		waitTillElementIsVisible(MittKonto);
		waitTillElementIsClickable(MittKonto);
		MittKonto.click();
	}
	
	/*public String getSubscriptionDetails() {
		
		moveToElement(SubscriptionDetails);
		waitTillElementIsVisible(SubscriptionDetails);
		return SubscriptionDetails.getText();
		
	}
	
	public String getNextRunDate() {
		
		moveToElement(NextRunDate);
		waitTillElementIsVisible(NextRunDate);
		return NextRunDate.getText();
	}*/
	

}
