package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import common.BasePage;

public class KontoDetails extends BasePage{

	public KontoDetails(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "(//a[@class = 'accountNavigation__link'])[1]")
	private WebElement KontoDetails;
	
	@FindBy(xpath = "//input[@name = 'email']")
	private WebElement email;
	
	@FindBy(xpath = "//input[@name = 'password']")
	private WebElement password;
	
	@FindBy(xpath = "//input[@name = 'firstName']")
	private WebElement firstName;
	
	@FindBy(xpath = "//input[@name = 'lastName']")
	private WebElement lastName;
	
	@FindBy(xpath = "//input[@name = 'address']")
	private WebElement address;
	
	@FindBy(xpath = "//input[@name = 'zipCode']")
	private WebElement zipCode;
	
	@FindBy(xpath = "//input[@name = 'city']")
	private WebElement city;
	
	@FindBy(xpath = "//input[@name = 'phoneNumber']")
	private WebElement phoneNumber;
	
	@FindBy(xpath = "//a[@class='accountInfo__link']")
	private WebElement cancelSubscription;
	
	@FindBy(xpath = "//button[@type = 'submit']")
	private WebElement Save;
	
	@FindBy(xpath = "//a[@class = 'accountInfo__link']")
	private WebElement changeSubscription;
	
	@FindBy(xpath = "//a[@class = 'btn btn--primary']")
	private WebElement GoBack;
	
	@FindBy(xpath = "//a[@class = 'btn btn--secondary']")
	private WebElement Continue;
	
	@FindBy(xpath = "//select[@name = 'reason']")
	private WebElement reasonSelect;
	
	@FindBy(xpath = "//input[@type = 'text']")
	private WebElement textReason;
	
	@FindBy(xpath = "//button[@class='btn btn--secondary']")
	private WebElement LogOut;
	
	public void clickForKonto() {
		
		waitTillElementIsVisible(KontoDetails);
		waitTillElementIsClickable(KontoDetails);
		KontoDetails.click();
	}
	
	public String getEmail() {
		
		waitTillElementIsVisible(email);
		return email.getAttribute("value").trim();
	}
	
	public void setEmail(String mail) {
		
		waitTillElementIsVisible(email);
		email.sendKeys(Keys.chord(Keys.CONTROL+"a"+Keys.DELETE));
		email.sendKeys(mail);
	}
	
	public void setPassword(String pass) {
		
		waitTillElementIsVisible(password);
		password.clear();
		password.sendKeys(pass);
	}
	
	public String getFirstName() {
		
		waitTillElementIsVisible(firstName);
		return firstName.getAttribute("value").trim();
	}
	
	public void setFirstName(String fname) {
		
		waitTillElementIsVisible(firstName);
		//firstName.clear();
		firstName.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
		firstName.sendKeys(fname);
	}
	
	public String getLastName() {
		
		waitTillElementIsVisible(lastName);
		return lastName.getAttribute("value").trim();
	}
	
	public void setLastName(String lname) {
		
		waitTillElementIsVisible(lastName);
		//lastName.clear();
		lastName.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
		lastName.sendKeys(lname);
	}
	
	public String getAddress() {
		
		waitTillElementIsVisible(address);
		return address.getAttribute("value").trim();
	}
	
	public void setAddress(String adr) {
		
		waitTillElementIsVisible(address);
		address.clear();;
		address.sendKeys(adr);
	}
	
	public String getzipCode() {
		
		waitTillElementIsVisible(zipCode);
		return zipCode.getAttribute("value").trim();
	}
	
	public void setzipCode(String zcode) {
		
		waitTillElementIsVisible(zipCode);
		zipCode.clear();
		zipCode.sendKeys(zcode);
	}
	
	public String getCity() {
		
		waitTillElementIsVisible(city);
		return city.getAttribute("value").trim();
	}
	
	public void setCity(String cityname) {
		
		waitTillElementIsVisible(city);
		city.clear();
		city.sendKeys(cityname);
	}
	
	public String getPhNo() {
		
		waitTillElementIsVisible(phoneNumber);
		return phoneNumber.getAttribute("value").trim();
	}
	
	public void setPhNo(String pnumber) {
		
		waitTillElementIsVisible(phoneNumber);
		phoneNumber.clear();
		phoneNumber.sendKeys(pnumber);
	}
	
	public void cancelSubscription() {
		
		waitTillElementIsVisible(cancelSubscription);
		waitTillElementIsClickable(cancelSubscription);
		cancelSubscription.click();
	}
	
	public void clickToSave() {
		
		waitTillElementIsVisible(Save);
		waitTillElementIsClickable(Save);
		Save.click();
	}
	
	public void selectCancelReason(int opt,String reason) {
		
		Select select = new Select(reasonSelect);
		List <WebElement> options = select.getOptions();
		
		for(int i = 1;i<options.size();i++) {
			
			WebElement index = options.get(i);
			if(i == opt) {
				
				index.click();
				break;
			}else if(i == 3 && i == 6) {
				
				index.click();
				waitTillElementIsVisible(textReason);
				textReason.clear();
				textReason.sendKeys(reason);
			}else {
				
				log.info("Invalid Option");
			}
		}
	}
	
	public void clickToChangeSub() {
		
		waitTillElementIsVisible(changeSubscription);
		waitTillElementIsClickable(changeSubscription);
		changeSubscription.click();
	}
	
	public void clickToGoBack() {
		
		waitTillElementIsVisible(GoBack);
		waitTillElementIsClickable(GoBack);
		GoBack.click();
	}
	
	public void clickToCancel() {
		
		waitTillElementIsVisible(Continue);
		waitTillElementIsClickable(Continue);
		Continue.click();
	}
	
	public void clickToLogOut() {
		
		waitTillElementIsVisible(LogOut);
		waitTillElementIsClickable(LogOut);
		LogOut.click();
	}
}
