package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class HomePage extends BasePage{
	
	@FindBy(xpath = "//a[@class = 'brand__logo']")
	private WebElement Nextory_Logo;
	
	@FindBy(xpath = "//a[@class='btn btn--primary']")
	private WebElement TryforFree;
	
	@FindBy(xpath = "//a[@class='navigation__link'][contains(text(),'Logga in')]")
	private WebElement Login;
	
	@FindBy(xpath = "//a[@class='footerDefault__navigationItem'][contains(text(),'Logga in')]")
	private WebElement Login_footer;
	
	@FindBy(xpath = "//a[contains(text(),'Kampanjkod')]")
	private WebElement CampaignPage;
	
	@FindBy(xpath = "//a[contains(text(),'Skapa konto')]")
	private WebElement RegistrationPage;
	
	@FindBy(xpath = "//a[@class='navigation__link'][contains(text(),'Presentkort')]")
	private WebElement PresentKort;
	
	@FindBy(xpath = "//a[@class='footerDefault__navigationItem'][contains(text(),'Presentkort')]")
	private WebElement PresentKort_footer;
	
	@FindBy(xpath = "//button[@type='button']")
	private WebElement CookieButton;
	
	@FindBy(xpath = "//a[@class='headerDefault__navigationIcon']")
	private WebElement Search_MittKonto;
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnHeaderLogo() {
		
		waitTillElementIsVisible(Nextory_Logo);
		waitTillElementIsClickable(Nextory_Logo);
		Nextory_Logo.click();
	}
	
	public void clickToRegister() {
		
		waitTillElementIsVisible(TryforFree);
		waitTillElementIsClickable(TryforFree);
		TryforFree.click();
	}
	
	public void clickToLogin() {
		
		waitTillElementIsVisible(Login);
		waitTillElementIsClickable(Login);
		Login.click();
	}
	
	public void clickToLoginfromfooter() {
		
		waitTillElementIsVisible(Login_footer);
		waitTillElementIsClickable(Login_footer);
		scrollToMaximum();
		Login_footer.click();
	}
	
	public void clickToRegisterCampaign() {
		
		waitTillElementIsVisible(CampaignPage);
		waitTillElementIsClickable(CampaignPage);
		scrollToMaximum();
		CampaignPage.click();
	}
	
	public void clickToRegisterFromFooter() {
		
		waitTillElementIsVisible(RegistrationPage);
		waitTillElementIsClickable(RegistrationPage);
		scrollToMaximum();
		RegistrationPage.click();
	}
	
	public void clickToRegisterGiftcard() {
		
		waitTillElementIsVisible(PresentKort);
		waitTillElementIsClickable(PresentKort);
		PresentKort.click();
	}
	
	public void clickToRegisterGiftcardFooter() {
		
		waitTillElementIsVisible(PresentKort_footer);
		waitTillElementIsClickable(PresentKort_footer);
		scrollToMaximum();
		PresentKort_footer.click();
	}
	
	public void clickToacceptCookies() {
		
		waitTillElementIsVisible(CookieButton);
		waitTillElementIsClickable(CookieButton);
		CookieButton.click();
	}
	
	public void clickToSearch() {
		
		waitTillElementIsVisible(Search_MittKonto);
		moveToElement(Search_MittKonto);
		waitTillElementIsClickable(Search_MittKonto);
		Search_MittKonto.click();
	}
}
