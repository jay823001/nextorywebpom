package registrationflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.SuperTestScript;
import generics.AddDate;
import generics.Property;
import pages.FamilySubscription;
import pages.HomePage;
import pages.KontoDetails;
import pages.OrderDetails;
import pages.PaymentPage;
import pages.RegistrationPage;
import pages.SubscriptionDetails;
import pages.SubscriptionPage;
import pages.UpdateCardDetails;
import pages.UserDetails;

public class FreeTrialFlow_AllCountries extends SuperTestScript{
	
	public static String newEmail;
	public static String confirm;
	public static String newPwd;
	public static String CardNumber;
	public static String cvc;
	SimpleDateFormat sd = new SimpleDateFormat("ddMMMyy_HHmmss"); 
	
		
	@Test(enabled = true,priority = 1, groups = { "FreeTrialFlow_SE", "All" },dataProvider="userData_SE",
			dataProviderClass=common.DataProviderComponent.class)
	public void FreeTrialFlow_SE(String subs,String email,String password,String gateway,String CardNumber,String cvc,String month,String year,
			String bankName,String personnum) {
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		FamilySubscription fam = new FamilySubscription(driver);
		Reporter.log("Registration for FreeTrailFlow in Swedish Country site Starts......");
		log.info("Validating the FreeTrailPeriod for 14 Days.....");
		Assert.assertTrue(sub.getTrailDate().trim().contains(AddDate.addingDays(14).toString().trim()));
		log.info("Validation of FreeTrailPeriod is completed.");
		if(subs.equals("Silver")) {
			Reporter.log("Free Trail Flow For "+subs+"Subscription Starts........");
			log.info("FreeTrialFlow Continues for Subscription"+subs+"........");
			sub.clickforSilver();
			log.info("Validating the Subscription Price.....");
			log.info(subs+":"+sub.getSubsciptionPrice().trim());
			Assert.assertEquals(sub.getSubsciptionPrice().trim(), "139 kr");
			log.info("Validation of Subscription Price is Completed.");
			log.info("Validating the allowed number of profiles for the subscription....");
			log.info("Profiles allowed for "+subs+":"+sub.getProfileNum().trim());
			Assert.assertEquals(sub.getProfileNum().trim(), "1");
			log.info("Validation of max allowed profiles is completed");
			sub.clickToContinue();
		}else if(subs.equals("Guld")) {
			Reporter.log("Free Trail Flow For "+subs+"Subscription Starts........");
			log.info("FreeTrialFlow Continues for Subscription"+subs+"........");
			sub.clickforGold();
			log.info("Validating the Subscription Price.....");
			log.info(subs+":"+sub.getSubsciptionPrice().trim());
			Assert.assertEquals(sub.getSubsciptionPrice().trim(), "169 kr");
			log.info("Validation of Subscription Price is Completed.");
			log.info("Validating the allowed number of profiles for the subscription....");
			log.info("Profiles allowed for "+subs+":"+sub.getProfileNum().trim());
			Assert.assertEquals(sub.getProfileNum().trim(), "1");
			log.info("Validation of max allowed profiles is completed");
			sub.clickToContinue();
		}else {
			
			if(subs.equals("Familj 2")) {
				Reporter.log("Free Trail Flow For "+subs+"Subscription Starts.....");
				log.info("FreeTrialFlow Continues for Subscription"+subs+"......");
				sub.clickforFamily();
				Assert.assertEquals(sub.getProfileNum().trim(), "2-4");
				sub.clickToContinue();
				fam.clickforFamily2();
				log.info("Validation of number of profiles for "+subs+" subscription.......");
				log.info("No. of profiles for "+subs+": "+fam.getFamily2Text(0));
				log.info("Validation of max allowed profiles is completed");
				log.info("Validation of subscription price starts.....");
				log.info("Subscription price for "+subs+":"+fam.getFamily2Text(2));
				Assert.assertTrue(fam.getFamily2Text(2).trim().contains("199 kr"));
				log.info("Validation of Subscription Price is completed.");
				fam.clickToContinue();
			}else if(subs.equals("Familj 3")) {
				Reporter.log("Free Trail Flow For "+subs+"Subscription Starts.....");
				log.info("FreeTrialFlow Continues for Subscription"+subs+"......");
				sub.clickforFamily();
				Assert.assertEquals(sub.getProfileNum().trim(), "2-4");
				sub.clickToContinue();
				fam.clickforFamily3();
				log.info("Validation of number of profiles for "+subs+" subscription.......");
				log.info("No. of profiles for "+subs+": "+fam.getFamily3Text(0));
				log.info("Validation of max allowed profiles is completed");
				log.info("Validation of subscription price starts.....");
				log.info("Subscription price for "+subs+":"+fam.getFamily3Text(2));
				Assert.assertTrue(fam.getFamily3Text(2).trim().contains("239 kr"));
				log.info("Validation of Subscription Price is completed.");
				fam.clickToContinue();
			}else {
				Reporter.log("Free Trail Flow For "+subs+"Subscription Starts.....");
				log.info("FreeTrialFlow Continues for Subscription"+subs+"......");
				sub.clickforFamily();
				Assert.assertEquals(sub.getProfileNum().trim(), "2-4");
				sub.clickToContinue();
				fam.clickforFamily4();
				log.info("Validation of number of profiles for "+subs+" subscription.......");
				log.info("No. of profiles for "+subs+": "+fam.getFamily4Text(0));
				log.info("Validation of max allowed profiles is completed");
				log.info("Validation of subscription price starts.....");
				log.info("Subscription price for "+subs+":"+fam.getFamily4Text(2));
				Assert.assertTrue(fam.getFamily4Text(2).trim().contains("279 kr"));
				log.info("Validation of Subscription Price is completed.");
				fam.clickToContinue();
			}
		}
		
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(email);
		reg.confirmMail(email);
		reg.enterPassword(password);
		reg.agreeTerms();
		reg.clickToContinue();
		
		Reporter.log("Payment Flow Starts for "+subs+"subscription....");
		log.info("Payment Starts.....");
		PaymentPage pay = new PaymentPage(driver);
		if(gateway.equals("Adyen")) {
			
			pay.adyenDropDown();
			log.info("Validation of payment Summary text in "+gateway);
			if(subs.equals("Silver")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Silver"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("139 kr"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
			}else if(subs.equals("Guld")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Guld"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("169 kr"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
			}else {
				if(subs.equals("Familj 2")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familj, 2"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("199 kr"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
				}else if(subs.equals("Familj 3")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familj, 3"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("239 kr"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
				}else {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familj, 4"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("279 kr"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
				}
			}
			
			WebDriverWait wait = new WebDriverWait(driver,40);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
			pay.enterCardNumber(CardNumber);
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
			pay.enterexpiryDate(month+year);
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));
			pay.entercvv(cvc);
			driver.switchTo().defaultContent();
			pay.clickOnSubmit();
		}else if(gateway.equals("Trustly")) {
			
			pay.trustlyDropDown();
			log.info("Validation of payment Summary text in "+gateway);
			if(subs.equals("Silver")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Silver"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("139 kr"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
			}else if(subs.equals("Guld")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Guld"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("169 kr"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
			}else {
				if(subs.equals("Familj 2")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familj, 2"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("199 kr"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
				}else if(subs.equals("Familj 3")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familj, 3"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("239 kr"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
				}else {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familj, 4"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("279 kr"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains(AddDate.addingDays(14).toString().trim()));
				}
			}
			pay.clickOnSubmit();
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
			pay.selectTrustlyBank(bankName);
			pay.clickToContinueTrustly();
			pay.enterAccountNumber(personnum);
			pay.clickToContinueTrustly();
			String s = pay.getOtp();
			pay.enterOtp(s.trim());
			pay.clickToContinueTrustly();
			pay.clickToCompletePayment();
		}
		
		Reporter.log("Payment flow is Completed for" +subs);
		log.info("Entry of first Name and last Name by the user");
		UserDetails ud = new UserDetails(driver);
		ud.enterFirstName();
		ud.enterLastname();
		ud.enterPhoneNumber();
		ud.clickToContinue();
		ud.clickOnMittKonto();
		
		Reporter.log("Validation of Konto Details Started...");
		KontoDetails konto = new KontoDetails(driver);
		konto.clickForKonto();
		//String mail = konto.getEmail();
		log.info("Validation of email...");
		Assert.assertEquals(konto.getEmail().trim(),email);
		//konto.setEmail("testmar1802@test.se");
		log.info("Validation of email Details completed");
		konto.setFirstName("test");
		konto.setLastName("test");
		konto.setAddress("Marathalli");
		konto.setPhNo("9846584659");
		konto.setzipCode("560043");
		konto.setCity("Bangalore");
		konto.clickToSave();
		
		home.clickToSearch();
		
		SubscriptionDetails suba = new SubscriptionDetails(driver);
		suba.clickToGetSubDetails();
		log.info("Validation of Subscription in Konto....");
		Assert.assertTrue(suba.getSubscriptionDetails().trim().contains(subs.trim()));
		log.info("Validation of NextRunDate in Konto");
		Assert.assertTrue(suba.getAccountText().trim().contains(AddDate.addingDays(14).toString().trim()));
		
		home.clickToSearch();
		
		UpdateCardDetails card = new UpdateCardDetails(driver);
		card.clickToGetCardDetails();
		Assert.assertTrue(suba.getAccountText().trim().contains(AddDate.addingDays(14).toString().trim()));
		
		home.clickToSearch();
		
		OrderDetails order = new OrderDetails(driver);
		order.clickTogetOrderDetails();
		order.clickOnFakutra();
		order.GoBackOrderHist();
		
		home.clickToSearch();
		konto.clickToLogOut();
	}
	
	@Test(enabled = true,priority = 2,groups= {"FreeTrialNegative_SE","All"})
	public void FreeTrailFlowSE_RegistrationPage_EmptyEmailPassword() {
		
		newEmail = "";
		confirm = "";
		newPwd = "";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.clickToContinue();
		
		log.info("Validating the error for Empty Email.....");
		String expected_EmptyEmail = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_email");
		Assert.assertEquals(reg.getErrorEmptyEmail().trim(), expected_EmptyEmail.trim());
		log.info("Error is displayed correctly");
		
		log.info("Validating the error for Empty Confirm Email.....");
		String expected_Emptyconfirm = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_confirm_email");
		Assert.assertEquals(reg.getErrorEmptyConfirm().trim(), expected_Emptyconfirm);
		log.info("Error is displayed correctly");
		
		log.info("Validating the error for Empty Password.....");
		String expected_Emptypass = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_password");
		Assert.assertEquals(reg.getErrorEmptyPassword().trim(), expected_Emptypass);
		log.info("Error is displayed correctly");
				
	}
	
	@Test(enabled = true,priority = 3,groups = {"FreeTrialNegative_SE","All"})
	public void FreeTrialFlowSE_RegistrationPage_UncheckBox() {
		
		newEmail = "testreg_checkBox@test.se";
		confirm = "testreg_checkBox@test.se";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.clickToContinue();
		
		log.info("Validating the error for CheckBox.....");
		String Expected_error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "check_box");
		Assert.assertEquals(reg.getErrorCheckBox().trim(), Expected_error);
		log.info("Error is displayed correctly");
		
	}
	
	@Test(enabled = true,priority = 4,groups = {"FreeTrialNegative_SE","All"})
	public void FreeTrailFlowSE_wrongEmail() {
		
		newEmail = "testreg_wrongemail@test.se";
		confirm = "testreg_wrongemail1@test.se";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.clickToContinue();
		
		log.info("Validating the error for wrong confirm email.....");
		String Expected_Error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "wrong_email");
		Assert.assertEquals(reg.getErrorEmptyConfirm(), Expected_Error);
		log.info("Error is displayed properly");
	}
	
	@Test(enabled = true,priority = 5,groups = {"FreeTrialNegative_SE","All"})
	public void FreeTrailFlow_Registration_InvalidPassword() {
		
		newEmail = "testreg_invalidpass@test.se";
		confirm = "testreg_invalidpass@test.se";
		newPwd = "pas";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.clickToContinue();
		
		log.info("Validating the error for password invalid....");
		String Expected_Error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_password");
		Assert.assertEquals(reg.getErrorEmptyPassword().trim(), Expected_Error.trim());
		log.info("Error is displayed correctly");
	}
	
	@Test(enabled = true,priority = 6,groups = {"FreeTrialNegative_SE","All"})
	public void FreeTrailFlowSE_invalidCardNumber() {
		
		newEmail = "testadyen_invalidcard@test.se";
		confirm = "testadyen_invalidcard@test.se";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropDown();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.enterCardNumber("5555 4857 5948 8675");
		driver.switchTo().defaultContent();
		pay.clickOnSubmit();
		
		log.info("Validating the error for invalid card number Adyen.....");
		String Expected_Error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_card_num");
		Assert.assertEquals(pay.getAdyenInvalidCardError().trim(), Expected_Error);
		log.info("Error is displayed correctly");
	
	}
	
	@Test(enabled=true,priority=7,groups = {"FreeTrialNegative_SE","All"})
	public void FreeTrialFlow_InvalidCardExpiry() {
		
		newEmail = "testadyen_invalidexpiry@test.se";
		confirm = "testadyen_invalidexpiry@test.se";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropDown();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.enterCardNumber("5555 4444 3333 1111");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		pay.enterexpiryDate("0517");
		driver.switchTo().defaultContent();
		pay.clickOnSubmit();
		
		log.info("Validating the error for wrong expiry date....");
		String Expected_error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_month");
		Assert.assertEquals(pay.getAdyenInvalidExpiryError().trim(), Expected_error.trim());
		log.info("Error is displayed correctly");
	}
	
	@Test(enabled=true,priority=8,groups = {"FreeTrialNegative_SE","All"})
	public void FreeTrialFlow_InvalidCvv() {
		
		newEmail = "testadyen_invalidexpiry@test.se";
		confirm = "testadyen_invalidexpiry@test.se";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropDown();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.enterCardNumber("5555 4444 3333 1111");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		pay.enterexpiryDate("1020");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));
		pay.entercvv("7869");
		driver.switchTo().defaultContent();
		pay.clickOnSubmit();
		
		log.info("Validating the error for invalid cvv Adyen....");
		String Expected_error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_cvv");
		Assert.assertEquals(pay.getAdyenInvalidcvvError().trim(), Expected_error.trim());
		log.info("Error is displayed correctly");
		
	}
	
	@Test(enabled=true,priority=9,groups = {"FreeTrialNegative_SE","All"})
	public void FreeTrailFlow_InvalidAccountNumber() {
		
		newEmail = "testtrustly_invalidaccnum@test.se";
		confirm = "testtrustly_invalidaccnum@test.se";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.trustlyDropDown();
		pay.clickOnSubmit();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.selectTrustlyBank("SEB");
		pay.clickToContinueTrustly();
		pay.enterAccountNumber("3849867486779");
		pay.clickToContinueTrustly();
		
		log.info("Validating the error message for invalid account Number Trustly.....");
		String Expected_Error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_account_number");
		Assert.assertEquals(pay.getTrustlyError().trim(), Expected_Error.trim());
		log.info("Error is displayed correctly");
	}
	
	@Test(enabled=true,priority=10,groups= {"FreeTrialNegative_SE","All"})
	public void FreeTrailFlow_Payment_emptyOtp() {
		
		newEmail = "testtrustly_nullotp@test.se";
		confirm = "testtrustly_nullotp@test.se";
		newPwd = "password";
		
		String otp = "";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.trustlyDropDown();
		pay.clickOnSubmit();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.selectTrustlyBank("SEB");
		pay.clickToContinueTrustly();
		pay.enterAccountNumber("1111111111");
		pay.clickToContinueTrustly();
		pay.enterOtp(otp);
		pay.clickToContinueTrustly();
		
		log.info("Validating the error message for empty Otp Trustly.....");
		String ExpectedError = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_otp");
		Assert.assertEquals(pay.getTrustlyError().trim(), ExpectedError.trim());
		log.info("Error is displayed correctly");
		
	}
	
	@Test(enabled=true,priority=11,groups= {"FreeTrialNegative_SE","All"})
	public void FreeTrailFlow_Payment_invalidOtp() {
		
		
		newEmail = "testtrustly_invalidotp_"+sd.format(new Date()) +"@test.se";
		//confirm = "testtrustly_invalidotp@test.se";
		newPwd = "password";
		
		String otp = "";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(newEmail);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.trustlyDropDown();
		pay.clickOnSubmit();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.selectTrustlyBank("SEB");
		pay.clickToContinueTrustly();
		pay.enterAccountNumber("1111111111");
		pay.clickToContinueTrustly();
		pay.enterOtp("124457");
		pay.clickToContinueTrustly();
		
		log.info("Validating the error Message for Invalid Otp Trustly....");
		String ExpectedError = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "wrong_otp");
		Assert.assertEquals(pay.getTrustlyError_invalidOtp(), ExpectedError.trim());
		log.info("Error is displayed correctly");
		
		log.info("Completing the flow by Entering correct otp");
		driver.findElement(By.xpath("//div[@class = 'action_buttons']/a")).click();
		pay.enterOtp(pay.getOtp());
		pay.clickToContinueTrustly();
		pay.clickToCompletePayment();
		
	}
}
