package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class RegistrationPage extends BasePage{

	public RegistrationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name = 'email']")
	private WebElement email;
	
	@FindBy(xpath = "//input[@name='confirmemail']")
	private WebElement confirmemail;
	
	@FindBy(xpath = "//input[@name = 'password']")
	private WebElement password;
	
	@FindBy(xpath = "//div[@class='checkbox']")
	private WebElement checkBox;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement Forsatt;
	
	@FindBy(xpath = "(//div[@class = 'formInput'])[1]/div/div/span")
	private WebElement EmptyEmail_error;
	
	@FindBy(xpath = "(//div[@class = 'formInput'])[2]/div/div/span")
	private WebElement Emptyconfirm_error;
	
	@FindBy(xpath = "(//div[@class = 'formInput'])[3]/div/div/span")
	private WebElement EmptyPassword_error;
	
	@FindBy(xpath = "//span[@class='text-has-error']")
	private WebElement error_CheckBox;
	
	public void enterEmail(String mail) {
		
		waitTillElementIsVisible(email);
		email.sendKeys(mail);
	}
	
	public void confirmMail(String mail) {
		
		waitTillElementIsVisible(confirmemail);
		confirmemail.sendKeys(mail);
	}
	
	public void enterPassword(String pass) {
		
		waitTillElementIsVisible(password);
		password.sendKeys(pass);
	}
	
	public void agreeTerms() {
		
		waitTillElementIsVisible(checkBox);
		waitTillElementIsClickable(checkBox);
		scrollToMaximum();
		checkBox.click();
	}
	
	public void clickToContinue() {
		
		waitTillElementIsVisible(Forsatt);
		waitTillElementIsClickable(Forsatt);
		Forsatt.click();
	}
	
	public String getErrorEmptyEmail() {
		
		waitTillElementIsVisible(EmptyEmail_error);
		return EmptyEmail_error.getText();
	}
	
	public String getErrorEmptyConfirm() {
		
		waitTillElementIsVisible(Emptyconfirm_error);
		return Emptyconfirm_error.getText();
	}
	
	public String getErrorEmptyPassword() {
		
		waitTillElementIsVisible(EmptyPassword_error);
		return EmptyPassword_error.getText();
	}
	
	public String getErrorCheckBox() {
		
		waitTillElementIsVisible(error_CheckBox);
		return error_CheckBox.getText();
	}
}
