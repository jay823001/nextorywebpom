package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import common.BasePage;

public class PaymentPage extends BasePage{

	public PaymentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//div[@class='paymentBtn']/div[@class='paymentBtn__label'])[1]")
	private WebElement AdyenDropDown;
	
	@FindBy(xpath = "(//div[@class='paymentBtn']/div[@class='paymentBtn__label'])[2]")
	private WebElement TrustlyDropDown;
	
	@FindBy(xpath = "(//div[@class='paymentBtn']/div[@class='paymentBtn__label'])[3]")
	private WebElement SofortDropDown;
	
	@FindBy(xpath = "(//div[@class='paymentBtn']/div[@class='paymentBtn__label'])[4]")
	private WebElement GiropayDropDown;
	
	@FindBy(xpath = "//input[@id='encryptedCardNumber']")
	private WebElement cardNumber;
	
	@FindBy(xpath = "//input[@id = 'encryptedExpiryDate']")
	private WebElement expiryDate;
	
	@FindBy(xpath = "//input[@id = 'encryptedSecurityCode']")
	private WebElement securityCode;
	
	@FindBy(xpath = "//a[@class='paymentOptionSummary__link']")
	private WebElement GoBack;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement Submit;
	
	@FindBy(xpath = "//div[@class = 'paymentOptionSummary__text']/span")
	private List<WebElement> payText;
	
	@FindBy(xpath = "//div[@class = 'bank_img_container']/img")
	private List<WebElement> TrustlyBanks;
	
	@FindBy(xpath = "//a[@class='button core_action_button button_next'][contains(text(),'Fortsätt')]")
	private WebElement Forsatt;
	
	@FindBy(xpath = "//input[@name='loginid']")
	private WebElement accountNumber;
	
	@FindBy(xpath = "//div[@class = 'form_item message'][2]/span[2]")
	private WebElement otp;
	
	@FindBy(xpath = "//input[@name = 'challenge_response']")
	private WebElement enterOtp;
	
	@FindBy(xpath = "//a[@class='button core_action_button button_next'][contains(text(),'Anslut konto')]")
	private WebElement finishPayment;
	
	@FindBy(xpath = "//input[@id = 'BackendFormLOGINNAMEUSERID']")
	private WebElement sofort_userid;
	
	@FindBy(xpath = "//input[@id = 'BackendFormUSERPIN']")
	private WebElement sofort_pin;
	
	@FindBy(xpath = "//select[@id='MultipaysSessionSenderCountryId']")
	private WebElement sofort_banklist;
	
	@FindBy(xpath = "//input[@id = 'BankCodeSearch']")
	private WebElement searchBank;
	
	@FindBy(xpath = "//button[@class='button-right primary has-indicator']")
	private WebElement sofortsearchSubmit;
	
	@FindBy(xpath = "//input[@id = 'account-2']")
	private WebElement sofortRadio;
	
	@FindBy(xpath  = "//input[@id = 'BackendFormTan']")
	private WebElement sofortTan;
	
	@FindBy(xpath = "//input[@type = 'checkbox']")
	private WebElement TanCheckbox;
	
	@FindBy(xpath = "//input[@id = 'giropay.bic-selection']")
	private WebElement giropay_Bankname;
	
	@FindBy(xpath = "//ul[@id = 'giropaysuggestionlist']")
	private WebElement giropay_BankList;
	
	@FindBy(xpath = "//input[@id = 'mainSubmit']")
	private WebElement giropay_submit;
	
	@FindBy(xpath = "//input[@id = 'mainBack']")
	private WebElement giropay_Back;
	
	@FindBy(xpath = "//input[@name = 'sc']")
	private WebElement giropay_conf_sc;
	
	@FindBy(xpath = "//input[@name = 'extensionSc']")
	private WebElement giropay_conf_sc_ext;
	
	@FindBy(xpath = "//input[@name = 'customerName1']")
	private WebElement giropay_conf_custName;
	
	@FindBy(xpath = "//input[@name = 'customerIBAN']")
	private WebElement giropay_conf_custIBAN;
	
	@FindBy(xpath = "//input[@type = 'submit']")
	private WebElement giropay_conf_submit;
	
	@FindBy(xpath = "//table/tbody/tr")
	private List<WebElement> giropay_status_codes;
	
	@FindBy(xpath = "(//div[@class = 'formInput'])[1]/span[2]")
	private WebElement adyen_invalidCard_error;
	
	@FindBy(xpath = "(//div[@class = 'formSelect'])/span[2]")
	private WebElement adyen_expiry_error;
	
	@FindBy(xpath = "(//div[@class = 'formInput'])[2]/span[2]")
	private WebElement adyen_cvv_error;
	
	@FindBy(xpath = "//div[@id = 'core_message_content']")
	private WebElement trustly_error;
	
	@FindBy(xpath = "//div[@class = 'message_text']")
	private WebElement trustly_invalid_otp;
	
	public void adyenDropDown() {
		
		waitTillElementIsVisible(AdyenDropDown);
		waitTillElementIsClickable(AdyenDropDown);
		AdyenDropDown.click();
	}
	
	public void trustlyDropDown() {
		
		waitTillElementIsVisible(TrustlyDropDown);
		waitTillElementIsClickable(TrustlyDropDown);
		TrustlyDropDown.click();
	}
	
	public void sofortDropDown() {
		
		waitTillElementIsVisible(SofortDropDown);
		waitTillElementIsClickable(SofortDropDown);
		SofortDropDown.click();
	}
	
	public void giropayDropDown() {
		
		waitTillElementIsVisible(GiropayDropDown);
		waitTillElementIsClickable(GiropayDropDown);
		GiropayDropDown.click();
	}
	
	public void clickOnSubmit() {
		
		waitTillElementIsVisible(Submit);
		waitTillElementIsClickable(Submit);
		Submit.click();
	}
	
	public void enterCardNumber(String cardnum) {
		
		waitTillElementIsVisible(cardNumber);
		cardNumber.sendKeys(cardnum);
		
	}
	
	public void enterexpiryDate(String date) {
		
		waitTillElementIsVisible(expiryDate);
		expiryDate.sendKeys(date);
	}
	
	public void entercvv(String cvv) {
		
		waitTillElementIsVisible(securityCode);
		securityCode.sendKeys(cvv);
	}
	
	public void GoBack() {
		
		waitTillElementIsVisible(GoBack);
		waitTillElementIsClickable(GoBack);
		scrollToClick(GoBack);
		//GoBack.click();
	}
	
	public String getPaymentSummary(int index) {
		
		return payText.get(index).getText();
	}
	
	public void selectTrustlyBank(String bankName) {
		
		for(int i = 0;i<TrustlyBanks.size();i++) {
			
			if((TrustlyBanks.get(i).getAttribute("alt")).trim().equals(bankName.trim())) {
				
				TrustlyBanks.get(i).click();
				break;
			}
		}
	}
	
	public void clickToContinueTrustly() {
		
		waitTillElementIsVisible(Forsatt);
		waitTillElementIsClickable(Forsatt);
		Forsatt.click();
	}
	
	public void enterAccountNumber(String accountNum) {
		
		waitTillElementIsVisible(accountNumber);
		accountNumber.sendKeys(accountNum);
	}
	
	public String getOtp() {
		
		waitTillElementIsVisible(otp);
		return otp.getText();
	}
	
	public void enterOtp(String otp) {
		
		waitTillElementIsVisible(enterOtp);
		enterOtp.sendKeys(otp);
	}
	
	public void clickToCompletePayment() {
		
		waitTillElementIsVisible(finishPayment);
		waitTillElementIsClickable(finishPayment);
		finishPayment.click();
	}
	
	public void sofortEnterUserId(String userid) {
		
		waitTillElementIsVisible(sofort_userid);
		sofort_userid.clear();
		sofort_userid.sendKeys(userid);
	}
	
	public void sofortEnterPin(String pin) {
		
		waitTillElementIsVisible(sofort_pin);
		sofort_pin.clear();
		sofort_pin.sendKeys(pin);
	}
	
	public void selectBankSofort(String bankname) {
		
		waitTillElementIsVisible(sofort_banklist);
		Select select = new Select(sofort_banklist);
		List <WebElement> ls = select.getOptions();
		for(int i = 0;i<ls.size();i++) {
			
			if(ls.get(i).getText().trim().equals(bankname.trim())) {
				ls.get(i).click();
				break;
			}
		}
	}
	
	public void enterBankName(String search) {
		
		waitTillElementIsVisible(searchBank);
		searchBank.clear();
		searchBank.sendKeys(search);
	}
	
	public void sofortSubmitSearch() {
		
		waitTillElementIsVisible(sofortsearchSubmit);
		if(sofortsearchSubmit.isEnabled())
			sofortsearchSubmit.click();
	}
	
	public void sofortSelectRadio() {
		
		waitTillElementIsVisible(sofortRadio);
		waitTillElementIsClickable(sofortRadio);
		sofortRadio.click();
	}
	
	public void sofortEnterTan(String tannum) {
		
		waitTillElementIsVisible(sofortTan);
		sofortTan.clear();
		sofortTan.sendKeys(tannum);
	}
	
	public void sofortTanCheckBox() {
		
		waitTillElementIsVisible(TanCheckbox);
		waitTillElementIsClickable(TanCheckbox);
		TanCheckbox.click();
	}
	
	public void giropayEnterBankName(String bankName) {
		
		waitTillElementIsVisible(giropay_Bankname);
		giropay_Bankname.clear();
		giropay_Bankname.sendKeys(bankName);
	}
	
	public void giropayClickMainSubmit() {
		
		waitTillElementIsVisible(giropay_submit);
		waitTillElementIsClickable(giropay_submit);
		giropay_submit.click();
	}
	
	public void giropayBankSelection(String bankName) {
		
		waitTillElementIsVisible(giropay_BankList);
		List <WebElement> options = giropay_BankList.findElements(By.tagName("li"));
		
		for(WebElement option : options) {
			
			if(option.getText().trim().equals(bankName.trim())){
				
				option.click();
				break;
			}
		}
	}
	
	public void giropay_GoBack() {
		
		waitTillElementIsVisible(giropay_Back);
		waitTillElementIsClickable(giropay_Back);
		giropay_Back.click();
	}
	
	public String giropay_statusCodes(String status) {
		String sc = null;
		for(WebElement code : giropay_status_codes.subList(1,3)) {
			List <WebElement> information = code.findElements(By.tagName("td"));
			if(status.equals("Success") && information.get(2).getText().equals("giropay-Zahlung erfolgreich")) {
				sc = information.get(0).getText()+";"+information.get(1).getText();
				break;
			}else if(status.equals("Abort") && information.get(2).getText().trim().equals("Mögliche extensionScs für Abbruch.")) {
				sc = information.get(0).getText()+";"+information.get(1).getText();
				break;
			}else if(status.equals("Unknown") && information.get(2).getText().equals("Status der Transaktion unbekannt")) {
				sc = information.get(0).getText()+";"+information.get(1).getText();
				break;
			}else if(status.equals("Unregister") && information.get(2).getText().equals("Bank ist nicht für giropay freigeschaltet")){
				sc = information.get(0).getText()+";"+information.get(1).getText();
				break;
			}
		}
		return sc;
	}
	
	public void giropay_enterSC(String sc) {
		
		waitTillElementIsVisible(giropay_conf_sc);
		giropay_conf_sc.clear();
		giropay_conf_sc.sendKeys(sc);
	}
	
	public void giropay_enterexSC(String exsc) {
		
		waitTillElementIsVisible(giropay_conf_sc_ext);
		giropay_conf_sc_ext.clear();
		giropay_conf_sc_ext.sendKeys(exsc);
	}
	
	public void giropay_enterCName(String cname) {
		
		waitTillElementIsVisible(giropay_conf_custName);
		giropay_conf_custName.clear();
		giropay_conf_custName.sendKeys(cname);
	}
	
	public void giropay_enterCIBAN(String ciban) {
		
		waitTillElementIsVisible(giropay_conf_custIBAN);
		giropay_conf_custIBAN.clear();
		giropay_conf_custIBAN.sendKeys(ciban);
	}
	
	public void giropay_conf_submit() {
		
		waitTillElementIsVisible(giropay_conf_submit);
		waitTillElementIsClickable(giropay_conf_submit);
		giropay_conf_submit.click();;
	}
	
	public String getAdyenInvalidCardError() {
		
		waitTillElementIsVisible(adyen_invalidCard_error);
		return adyen_invalidCard_error.getText();
	}
	
	public String getAdyenInvalidExpiryError() {
		
		waitTillElementIsVisible(adyen_expiry_error);
		return adyen_expiry_error.getText();
	}
	
	public String getAdyenInvalidcvvError() {
		
		waitTillElementIsVisible(adyen_cvv_error);
		return adyen_cvv_error.getText();
	}
	
	public String getTrustlyError() {
		
		waitTillElementIsVisible(trustly_error);
		return trustly_error.getText();
	}
	
	public String getTrustlyError_invalidOtp() {
		
		waitTillElementIsVisible(trustly_invalid_otp);
		return trustly_invalid_otp.getText();
	}
}
