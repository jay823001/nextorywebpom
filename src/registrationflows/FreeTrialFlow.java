/*package registrationflows;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.SuperTestScript;
import generics.AddDate;
import generics.Excel;
import generics.Property;
import pages.FamilySubscription;
import pages.HomePage;
import pages.HomeStep1Page;
import pages.LoginPage;
import pages.MittKonto;
import pages.NewSubscriptionPage;
import pages.PaymentPage;
import pages.RegistrationPage;
import pages.SubscriptionStep2;
import pages.UserDetails;


public class FreeTrialFlow extends SuperTestScript
{
	
	public static String newEmail;
	public static String confirm;
	public static String newPwd;
	public static String cardNumber;
	public static String cvc;
	public static String fn;
	public static String ln;
	public static String cellNum;
	public static String month;
	public static String year;
	public static String adminUn;
	public static String adminPwd;
	public static String adminUrl;
	public static String subscription;
	public static String subsPrice;
	public static String pricePerMonth;
	public static String gateway;
	public static String personnummer;
	public static String bankName;
	public static String currency_country;
	
	SoftAssert soft = new SoftAssert();
	
	public FreeTrialFlow() {
		
		loginrequired = false;
		logoutrequired = false;
	}
	@Test(enabled = true,priority = 1, groups = { "FreeTrialPositive", "All" })
	public void FreeTrialflow_NewRegistration_SilverSubscription() throws InterruptedException, FindFailed, EncryptedDocumentException, InvalidFormatException, IOException {
		
		gateway = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 3);
		newEmail = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		confirm = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		newPwd = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 2);
		cardNumber = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 4);
		cvc = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 5);
		month = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 6);
		year = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 7);
		bankName = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 8);
		personnummer = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 9);
		subscription = "Silver";
		subsPrice = "139";
		currency_country = "kr/månad";
		
				
		log.info("Free Trial Registration Flow Starts");
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Silversubscription();
		sub.ContinueSubscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		PaymentPage pay = new PaymentPage(driver);
		System.out.println(gateway);
		
		if(gateway.trim().equalsIgnoreCase("ADYEN")) {
		
			pay.adyenDropdown();
			pay.enterCardDetails(cardNumber);
			pay.ExpiryMonth();
			pay.selectMonth(month);
			pay.ExpiryYear();
			pay.selectYear(year);
			pay.entercvv(cvc);
			pay.clickOnSubmit();
		}else {
			
			pay.trustlyDropDown();
			pay.clickOnSubmitTrustly();
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
			if(bankName.contains("Danske")) {
				pay.DanskeBank();
			}
			pay.clickToContinue();
			pay.enterAccount(personnummer);
			pay.clickToContinue();
			String otp = pay.getOtp();
			pay.enterOtp(otp);
			pay.clickToContinue();
			pay.finishPayment();
		}
		
		UserDetails user = new UserDetails(driver);
		user.enterFirstName();
		user.enterLastname();
		user.enterPhoneNumber();
		user.clickToContinue();
		user.clickOnMittKonto();
		
		Excel.shiftingRowsDown(INPUT_PATH, "ExistingEmail", 2);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 1, newEmail);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 2, newPwd);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 3, subscription);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 4, "FREE TRIAL");
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 6, cardNumber);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 7, month);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 8, year);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 9, cvc);
		
		Excel.shiftingRowsUp(INPUT_PATH, "newEmail", 1);
		
		MittKonto detail = new MittKonto(driver);
		home.clickkonto();
		String SubscriptionDetails = driver.findElement(By.xpath("//span[text()='kr/månad']")).getText();
		String sub1 [] = SubscriptionDetails.split(" ");
		
		String subscription_name = sub1 [0].toString();
		Assert.assertEquals(subscription_name, subscription);
		log.info("New User Registered with Subscription: "+subscription_name);
		
		String subscription_amount = sub1 [1].toString();
		Assert.assertEquals(subscription_amount, subsPrice);
		String currency = sub1 [2].toString();
		Assert.assertEquals(currency, "kr/månad");
		log.info("Subscription Amount is: "+subscription_amount+currency);
		
		String nextrundate = driver.findElement(By.xpath("//p[@class='sc-kQsIoO fqcVWP']/strong")).getText();
		
		
		String ExpectedDate = AddDate.addingDays(14);
		String currentDate = AddDate.currentDate();
		log.info("Current Date is: " +currentDate);
				
		Assert.assertEquals(nextrundate.trim(), ExpectedDate.trim());
		log.info("Next Run Date is :" +nextrundate);
		
		String ordernumber = driver.findElement(By.xpath("//div//section//ul//li[1]")).getText();
		log.info("OrderNumber created is "+ordernumber);
		
		String subscribeddate = driver.findElement(By.xpath("//div//section//ul//li[2]")).getText();
		Assert.assertEquals(subscribeddate.trim(), currentDate.trim());
		log.info("Subscribed Date is :" +currentDate);
		
		String Recentpayment = driver.findElement(By.xpath("//div//section//ul//li[3]")).getText();
		Assert.assertEquals(Recentpayment.trim(),"Prova Nextory Gratis i 14 dagar: 0 kr");
		log.info("Free Trial New Registration is Completed");
		
		detail.LogOut();
		
		
	}
	
	@Test(enabled = true,priority = 2, groups = { "FreeTrialPositive", "All" })
	public void FreeTrialFlow_NewRegistration_GoldSubscription() throws Exception, InvalidFormatException, IOException {
		
		gateway = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 3);
		newEmail = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		confirm = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		newPwd = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 2);
		cardNumber = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 4);
		cvc = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 5);
		month = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 6);
		year = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 7);
		bankName = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 8);
		personnummer = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 9);
		subscription = "Guld";
		subsPrice = "169";
		currency_country = "kr/månad";
		
		log.info("Free Trial Registration Flow Starts");
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Guldsubscription();
		sub.ContinueSubscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		PaymentPage pay = new PaymentPage(driver);
		System.out.println(gateway);
		
		if(gateway.trim().equalsIgnoreCase("ADYEN")) {
		
			pay.adyenDropdown();
			pay.enterCardDetails(cardNumber);
			pay.ExpiryMonth();
			pay.selectMonth(month);
			pay.ExpiryYear();
			pay.selectYear(year);
			pay.entercvv(cvc);
			pay.clickOnSubmit();
		}else {
			
			pay.trustlyDropDown();
			pay.clickOnSubmitTrustly();
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
			if(bankName.contains("Danske")) {
				pay.DanskeBank();
			}
			pay.clickToContinue();
			pay.enterAccount(personnummer);
			pay.clickToContinue();
			String otp = pay.getOtp();
			pay.enterOtp(otp);
			pay.clickToContinue();
			pay.finishPayment();
		}
		
		UserDetails user = new UserDetails(driver);
		user.enterFirstName();
		user.enterLastname();
		user.enterPhoneNumber();
		user.clickToContinue();
		user.clickOnMittKonto();
		
		Excel.shiftingRowsDown(INPUT_PATH, "ExistingEmail", 2);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 1, newEmail);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 2, newPwd);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 3, subscription);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 4, "FREE TRIAL");
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 6, cardNumber);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 7, month);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 8, year);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 9, cvc);
		
		Excel.shiftingRowsUp(INPUT_PATH, "newEmail", 1);
		
		MittKonto detail = new MittKonto(driver);
		home.clickkonto();
		String SubscriptionDetails = driver.findElement(By.xpath("//span[text()='kr/månad']")).getText();
		String sub1 [] = SubscriptionDetails.split(" ");
		
		String subscription_name = sub1 [0].toString();
		Assert.assertEquals(subscription_name, subscription);
		log.info("New User Registered with Subscription: "+subscription_name);
		
		String subscription_amount = sub1 [1].toString();
		Assert.assertEquals(subscription_amount, subsPrice);
		String currency = sub1 [2].toString();
		Assert.assertEquals(currency, "kr/månad");
		log.info("Subscription Amount is: "+subscription_amount+currency);
		
		String nextrundate = driver.findElement(By.xpath("//p[@class='sc-kQsIoO fqcVWP']/strong")).getText();
		
		
		String ExpectedDate = AddDate.addingDays(14);
		String currentDate = AddDate.currentDate();
		log.info("Current Date is: " +currentDate);
				
		Assert.assertEquals(nextrundate.trim(), ExpectedDate.trim());
		log.info("Next Run Date is :" +nextrundate);
		
		String ordernumber = driver.findElement(By.xpath("//div//section//ul//li[1]")).getText();
		log.info("OrderNumber created is "+ordernumber);
		
		String subscribeddate = driver.findElement(By.xpath("//div//section//ul//li[2]")).getText();
		Assert.assertEquals(subscribeddate.trim(), currentDate.trim());
		log.info("Subscribed Date is :" +currentDate);
		
		String Recentpayment = driver.findElement(By.xpath("//div//section//ul//li[3]")).getText();
		Assert.assertEquals(Recentpayment.trim(),"Prova Nextory Gratis i 14 dagar: 0 kr");
		log.info("Free Trial New Registration is Completed");
		
		detail.LogOut();
		driver.close();
	}
	
	@Test(enabled=true,priority=3,groups = {"FreeTrialPositive","All"})
	public void FreeTrialFlow_NewRegistration_Family2Subscription() throws Throwable, InvalidFormatException, IOException {
		
		gateway = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 3);
		newEmail = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		confirm = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		newPwd = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 2);
		cardNumber = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 4);
		cvc = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 5);
		month = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 6);
		year = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 7);
		bankName = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 8);
		personnummer = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 9);
		
		subscription = "Familj2";
		subsPrice = "199";
		currency_country = "kr/månad";
		
		log.info("Free Trial Registration Flow Starts");
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family2Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		PaymentPage pay = new PaymentPage(driver);
		System.out.println(gateway);
		
		if(gateway.trim().equalsIgnoreCase("ADYEN")) {
		
			pay.adyenDropdown();
			pay.enterCardDetails(cardNumber);
			pay.ExpiryMonth();
			pay.selectMonth(month);
			pay.ExpiryYear();
			pay.selectYear(year);
			pay.entercvv(cvc);
			pay.clickOnSubmit();
		}else {
			
			pay.trustlyDropDown();
			pay.clickOnSubmitTrustly();
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
			if(bankName.contains("Danske")) {
				pay.DanskeBank();
			}
			pay.clickToContinue();
			pay.enterAccount(personnummer);
			pay.clickToContinue();
			String otp = pay.getOtp();
			pay.enterOtp(otp);
			pay.clickToContinue();
			pay.finishPayment();
		}
		
		UserDetails user = new UserDetails(driver);
		user.enterFirstName();
		user.enterLastname();
		user.enterPhoneNumber();
		user.clickToContinue();
		user.clickOnMittKonto();
		
		Excel.shiftingRowsDown(INPUT_PATH, "ExistingEmail", 2);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 1, newEmail);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 2, newPwd);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 3, subscription);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 4, "FREE TRIAL");
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 6, cardNumber);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 7, month);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 8, year);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 9, cvc);
		
		Excel.shiftingRowsUp(INPUT_PATH, "newEmail", 1);
		
		MittKonto detail = new MittKonto(driver);
		home.clickkonto();
		String SubscriptionDetails = driver.findElement(By.xpath("//span[text()='kr/månad']")).getText();
		System.out.println(SubscriptionDetails);
		String sub1 [] = SubscriptionDetails.split(" ");
		
		String subscription_name = sub1 [0].toString()+sub1[1].toString();
		Assert.assertEquals(subscription_name, subscription);
		log.info("New User Registered with Subscription: "+subscription_name);
		
		String subscription_amount = sub1 [3].toString();
		Assert.assertEquals(subscription_amount, subsPrice);
		String currency = sub1 [4].toString();
		Assert.assertEquals(currency, "kr/månad");
		log.info("Subscription Amount is: "+subscription_amount+currency);
		
		String nextrundate = driver.findElement(By.xpath("//p[@class='sc-kQsIoO fqcVWP']/strong")).getText();
		
		
		String ExpectedDate = AddDate.addingDays(14);
		String currentDate = AddDate.currentDate();
		log.info("Current Date is: " +currentDate);
				
		Assert.assertEquals(nextrundate.trim(), ExpectedDate.trim());
		log.info("Next Run Date is :" +nextrundate);
		
		String ordernumber = driver.findElement(By.xpath("//div//section//ul//li[1]")).getText();
		log.info("OrderNumber created is "+ordernumber);
		
		String subscribeddate = driver.findElement(By.xpath("//div//section//ul//li[2]")).getText();
		Assert.assertEquals(subscribeddate.trim(), currentDate.trim());
		log.info("Subscribed Date is :" +currentDate);
		
		String Recentpayment = driver.findElement(By.xpath("//div//section//ul//li[3]")).getText();
		Assert.assertEquals(Recentpayment.trim(),"Prova Nextory Gratis i 14 dagar: 0 kr");
		log.info("Free Trial New Registration is Completed");
		
		detail.LogOut();
		driver.close();
		
	}
	
	@Test(enabled = true,priority=4,groups = {"FreeTrialPositive","All"})
	public void FreeTrialFlow_NewRegistration_Family3Subscription() throws Throwable, InvalidFormatException, IOException {
		
		gateway = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 3);
		newEmail = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		confirm = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		newPwd = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 2);
		cardNumber = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 4);
		cvc = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 5);
		month = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 6);
		year = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 7);
		bankName = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 8);
		personnummer = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 9);
			
			subscription = "Familj3";
			subsPrice = "239";
			currency_country = "kr/månad";
			
			log.info("Free Trial Registration Flow Starts");
			
			HomePage home = new HomePage(driver);
			home.clicktoregister();
			
			HomeStep1Page step1 = new HomeStep1Page(driver);
			step1.clicktoproceed();
			
			NewSubscriptionPage sub = new NewSubscriptionPage(driver);
			sub.Familysubscription();
			sub.ContinueSubscription();
			
			FamilySubscription fam = new FamilySubscription(driver);
			fam.Family3Subscription();
				
			SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
			sub2.ClicktoProceed();
			
			RegistrationPage reg = new RegistrationPage(driver);
			reg.enterEmail(newEmail);
			reg.confirmEmail(confirm);
			reg.enterPassword(newPwd);
			reg.agreeTerms();
			reg.clickToSubmit();
			
			PaymentPage pay = new PaymentPage(driver);
			System.out.println(gateway);
			
			if(gateway.trim().equalsIgnoreCase("ADYEN")) {
			
				pay.adyenDropdown();
				pay.enterCardDetails(cardNumber);
				pay.ExpiryMonth();
				pay.selectMonth(month);
				pay.ExpiryYear();
				pay.selectYear(year);
				pay.entercvv(cvc);
				pay.clickOnSubmit();
			}else {
				
				pay.trustlyDropDown();
				pay.clickOnSubmitTrustly();
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
				if(bankName.contains("Danske")) {
					pay.DanskeBank();
				}
				pay.clickToContinue();
				pay.enterAccount(personnummer);
				pay.clickToContinue();
				String otp = pay.getOtp();
				pay.enterOtp(otp);
				pay.clickToContinue();
				pay.finishPayment();
			}
			
			UserDetails user = new UserDetails(driver);
			user.enterFirstName();
			user.enterLastname();
			user.enterPhoneNumber();
			user.clickToContinue();
			user.clickOnMittKonto();
			
			Excel.shiftingRowsDown(INPUT_PATH, "ExistingEmail", 2);
			Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 1, newEmail);
			Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 2, newPwd);
			Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 3, subscription);
			Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 4, "FREE TRIAL");
			Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 6, cardNumber);
			Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 7, month);
			Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 8, year);
			Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 9, cvc);
			
			Excel.shiftingRowsUp(INPUT_PATH, "newEmail", 1);
			
			MittKonto detail = new MittKonto(driver);
			home.clickkonto();
			String SubscriptionDetails = driver.findElement(By.xpath("//span[text()='kr/månad']")).getText();
			System.out.println(SubscriptionDetails);
			String sub1 [] = SubscriptionDetails.split(" ");
			
			String subscription_name = sub1 [0].toString()+sub1[1].toString();
			Assert.assertEquals(subscription_name, subscription);
			log.info("New User Registered with Subscription: "+subscription_name);
			
			String subscription_amount = sub1 [3].toString();
			Assert.assertEquals(subscription_amount, subsPrice);
			String currency = sub1 [4].toString();
			Assert.assertEquals(currency, "kr/månad");
			log.info("Subscription Amount is: "+subscription_amount+currency);
			
			String nextrundate = driver.findElement(By.xpath("//p[@class='sc-kQsIoO fqcVWP']/strong")).getText();
			
			
			String ExpectedDate = AddDate.addingDays(14);
			String currentDate = AddDate.currentDate();
			log.info("Current Date is: " +currentDate);
					
			Assert.assertEquals(nextrundate.trim(), ExpectedDate.trim());
			log.info("Next Run Date is :" +nextrundate);
			
			String ordernumber = driver.findElement(By.xpath("//div//section//ul//li[1]")).getText();
			log.info("OrderNumber created is "+ordernumber);
			
			String subscribeddate = driver.findElement(By.xpath("//div//section//ul//li[2]")).getText();
			Assert.assertEquals(subscribeddate.trim(), currentDate.trim());
			log.info("Subscribed Date is :" +currentDate);
			
			String Recentpayment = driver.findElement(By.xpath("//div//section//ul//li[3]")).getText();
			Assert.assertEquals(Recentpayment.trim(),"Prova Nextory Gratis i 14 dagar: 0 kr");
			log.info("Free Trial New Registration is Completed");
			
			detail.LogOut();
			driver.close();
	}
	
	@Test(enabled = true,priority = 5,groups = {"FreeTrialPositive","All"})
	public void FreeTrialFlow_NewRegistration_Family4Subscription() throws Throwable, InvalidFormatException, IOException {
		
		gateway = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 3);
		newEmail = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		confirm = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		newPwd = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 2);
		cardNumber = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 4);
		cvc = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 5);
		month = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 6);
		year = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 7);
		bankName = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 8);
		personnummer = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 9);
		
		subscription = "Familj4";
		subsPrice = "279";
		currency_country = "kr/månad";
		
		log.info("Free Trial Registration Flow Starts");
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family4Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		PaymentPage pay = new PaymentPage(driver);
		System.out.println(gateway);
		
		if(gateway.trim().equalsIgnoreCase("ADYEN")) {
		
			pay.adyenDropdown();
			pay.enterCardDetails(cardNumber);
			pay.ExpiryMonth();
			pay.selectMonth(month);
			pay.ExpiryYear();
			pay.selectYear(year);
			pay.entercvv(cvc);
			pay.clickOnSubmit();
		}else {
			
			pay.trustlyDropDown();
			pay.clickOnSubmitTrustly();
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
			if(bankName.contains("Danske")) {
				pay.DanskeBank();
			}
			pay.clickToContinue();
			pay.enterAccount(personnummer);
			pay.clickToContinue();
			String otp = pay.getOtp();
			pay.enterOtp(otp);
			pay.clickToContinue();
			pay.finishPayment();
		}
		
		UserDetails user = new UserDetails(driver);
		user.enterFirstName();
		user.enterLastname();
		user.enterPhoneNumber();
		user.clickToContinue();
		user.clickOnMittKonto();
		
		Excel.shiftingRowsDown(INPUT_PATH, "ExistingEmail", 2);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 1, newEmail);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 2, newPwd);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 3, subscription);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 4, "FREE TRIAL");
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 6, cardNumber);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 7, month);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 8, year);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 9, cvc);
		
		Excel.shiftingRowsUp(INPUT_PATH, "newEmail", 1);
		
		MittKonto detail = new MittKonto(driver);
		home.clickkonto();
		String SubscriptionDetails = driver.findElement(By.xpath("//span[text()='kr/månad']")).getText();
		System.out.println(SubscriptionDetails);
		String sub1 [] = SubscriptionDetails.split(" ");
		
		String subscription_name = sub1 [0].toString()+sub1[1].toString();
		Assert.assertEquals(subscription_name, subscription);
		log.info("New User Registered with Subscription: "+subscription_name);
		
		String subscription_amount = sub1 [3].toString();
		Assert.assertEquals(subscription_amount, subsPrice);
		String currency = sub1 [4].toString();
		Assert.assertEquals(currency, "kr/månad");
		log.info("Subscription Amount is: "+subscription_amount+currency);
		
		String nextrundate = driver.findElement(By.xpath("//p[@class='sc-kQsIoO fqcVWP']/strong")).getText();
		
		
		String ExpectedDate = AddDate.addingDays(14);
		String currentDate = AddDate.currentDate();
		log.info("Current Date is: " +currentDate);
				
		Assert.assertEquals(nextrundate.trim(), ExpectedDate.trim());
		log.info("Next Run Date is :" +nextrundate);
		
		String ordernumber = driver.findElement(By.xpath("//div//section//ul//li[1]")).getText();
		log.info("OrderNumber created is "+ordernumber);
		
		String subscribeddate = driver.findElement(By.xpath("//div//section//ul//li[2]")).getText();
		Assert.assertEquals(subscribeddate.trim(), currentDate.trim());
		log.info("Subscribed Date is :" +currentDate);
		
		String Recentpayment = driver.findElement(By.xpath("//div//section//ul//li[3]")).getText();
		Assert.assertEquals(Recentpayment.trim(),"Prova Nextory Gratis i 14 dagar: 0 kr");
		log.info("Free Trial New Registration is Completed");
		
		detail.LogOut();
		driver.close();
		
	}
	
	@Test(enabled = true,priority = 6,groups = {"FreeTrialNegative","All"})
	public void FreeTrialFlow_RegistrationPage_EmptyEmailPassword() {
		
		newEmail = "";
		confirm = "";
		newPwd = "";
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		log.info("PASSING EMPTY EMAIL AND PASSWORD AND VALIDATING THE ERROR MESSAGES");
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		//reg.agreeTerms();
		reg.clickToSubmit();
		
		log.info("Validating error message for Empty Email....");
		String actual_empty_email = driver.findElement(By.xpath("(//span[@class='sc-jKmXuR cNGNrU'])[1]")).getText();
		String expected_empty_email = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_email");
		//System.out.println(expected_empty_email);
		Assert.assertEquals(actual_empty_email, expected_empty_email);
		
		log.info("Validating error message for Empty confirm Email....");
		String actual_confirm_email = driver.findElement(By.xpath("(//span[@class='sc-jKmXuR cNGNrU'])[2]")).getText();
		String expected_confirm_email = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_confirm_email");
		//String expected_confirm_email = "Vänligen fyll i en giltig e-postadress";
		Assert.assertEquals(actual_confirm_email, expected_confirm_email);
		
		log.info("Validating error message for Empty Password.....");
		String actual_password = driver.findElement(By.xpath("(//span[@class='sc-jKmXuR cNGNrU'])[3]")).getText();
		String expected_password = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_password");
		Assert.assertEquals(actual_password, expected_password);
		
		log.info("Validating error message for checkbox....");
		String actual_checkbox = driver.findElement(By.xpath("//span[@class='sc-hRmvpr hWnjTY']")).getText();
		String expected_checkbox = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "check_box");
		Assert.assertEquals(actual_checkbox, expected_checkbox);
		
		driver.close();
				
	}
	
	@Test(enabled=true,priority=7,groups= {"FreeTrialNegative","All"})
	public void FreeTrialFlow_Registration_WrongEmail() {
		
		newEmail = "tetstystdus@test.se";
		confirm = "test_error@test.se";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		log.info("Passing Invalid Email and Validating Error Message");
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		String actual_message = driver.findElement(By.xpath("//span[@class = 'sc-jKmXuR cNGNrU']")).getText();
		String expected_message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE,"wrong_email");
		
		Assert.assertEquals(actual_message, expected_message);
		driver.close();
	}
	
	@Test(enabled=true,priority=8,groups = {"FreeTrialNegative","All"})
	public void FreeTrialFlow_Registration_InvalidPassword() {
		
		newEmail = "test_error@test.se";
		confirm = "test_error@test.se";
		newPwd = "pas";
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		log.info("Passing Invalid Password and Validating Error Message");
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		String actual_message = driver.findElement(By.xpath("//span[@class='sc-jKmXuR cNGNrU']")).getText();
		String expected_message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_password");
		Assert.assertEquals(actual_message, expected_message);
		
		driver.close();
		
	}
	
	@Test(enabled = true,priority=9,groups = {"FreeTrialNegative","All"})
	public void FreeTrial_Payment_Adyen_EmptyCardDetails() {
		
		int i = 111;
		newEmail = "test_error"+i+"@test.se";
		confirm = "test_error"+i+"@test.se";
		newPwd = "password";
		cardNumber = "";
		month = "";
		year = "";
		cvc = "";
		i = i+1;
		
		HomePage home =  new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		log.info("PASSING EMPTY CARD DETAILS AND VALIDATING ERRORS....");
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropdown();
		pay.enterCardDetails(cardNumber);
		pay.entercvv(cvc);
		pay.clickOnSubmit();
		
		log.info("Validating Error for empty card number..");
		String actual_message = driver.findElement(By.xpath("//form[@class = 'sc-bwzfXH kKMpdu']/div[1]/div[1]/div[1]/span[1]")).getText();
		String expected_message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_card_num");
		Assert.assertEquals(actual_message, expected_message);
		
		log.info("Validating Error for empty cvv number...");
		String actual_cvv = driver.findElement(By.xpath("//form[@class = 'sc-bwzfXH kKMpdu']/div[1]/div[4]/div[1]/div[1]/span[1]")).getText();
		String expected_cvv = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_cvv");
		Assert.assertEquals(actual_cvv, expected_cvv);
		
		log.info("Validating Error for empty Month.....");
		String actual_month = driver.findElement(By.xpath("//form[@class = 'sc-bwzfXH kKMpdu']/div[1]/div[2]/div[1]/div[1]/span[1]")).getText();
		String expected_month = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_month");
		Assert.assertEquals(actual_month, expected_month);
		
		log.info("Validating Error for empty Year.....");
		String actual_year = driver.findElement(By.xpath("//form[@class = 'sc-bwzfXH kKMpdu']/div[1]/div[3]/div[1]/span[1]")).getText();
		String expected_year = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_year");
		Assert.assertEquals(actual_year, expected_year);
		
	}
	
	@Test(enabled=true,priority=10,groups= {"FreeTrialNegative","All"})
	public void FreeTrialFlow_PaymentPage_InvalidCardNum() {
		
		int i = 222;
		newEmail = "test_error"+i+"@test.se";
		confirm = "test_error"+i+"@test.se";
		newPwd = "password";
		cardNumber = "5555 4444 3333 0000";
		month = "08";
		year = "2018";
		cvc = "737";
		i = i+1;
		
		HomePage home =  new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		log.info("PASSING INVALID CARD DETAILS AND VALIDATING ERRORS....");
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropdown();
		pay.enterCardDetails(cardNumber);
		pay.ExpiryMonth();
		pay.selectMonth(month);
		pay.ExpiryYear();
		pay.selectYear(year);
		pay.entercvv(cvc);
		pay.clickOnSubmit();
		
		log.info("Validating Error Message for Invalid card number");
		String actual_Message = driver.findElement(By.xpath("//span[@class = 'sc-jKmXuR cNGNrU']")).getText();
		String expected_Message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_card_num");
		Assert.assertEquals(actual_Message, expected_Message);
		
	}
	
	@Test(enabled=true,priority=11,groups= {"FreeTrialNegative","All"})
	public void FreeTrialFlow_PaymentPage_InvalidCvv() {
		
		int i = 333;
		newEmail = "test_error"+i+"@test.se";
		confirm = "test_error"+i+"@test.se";
		newPwd = "password";
		cardNumber = "5555 4444 3333 1111";
		month = "08";
		year = "2018";
		cvc = "73";
		i = i+1;
		
		HomePage home =  new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		log.info("PASSING INVALID CVV DETAILS AND VALIDATING ERRORS....");
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropdown();
		pay.enterCardDetails(cardNumber);
		pay.ExpiryMonth();
		pay.selectMonth(month);
		pay.ExpiryYear();
		pay.selectYear(year);
		pay.entercvv(cvc);
		pay.clickOnSubmit();
		
		log.info("Validating Error Message for Invalid CVV number");
		String actual_Message = driver.findElement(By.xpath("//span[@class = 'sc-jKmXuR cNGNrU']")).getText();
		String expected_Message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_cvv");
		Assert.assertEquals(actual_Message, expected_Message);
	}
	
	@Test(enabled=true,priority=12,groups= {"FreeTrialNegative","All"})
	public void FreeTrialFlow_PaymentPage_InvalidcardDetais() {
		
		int i = 444;
		newEmail = "test_error"+i+"@test.se";
		confirm = "test_error"+i+"@test.se";
		newPwd = "password";
		cardNumber = "5555 4444 3333 1111";
		month = "09";
		year = "2016";
		cvc = "658";
		i = i+1;
		
		HomePage home =  new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		log.info("PASSING INVALID CARD DETAILS AND VALIDATING ERRORS....");
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropdown();
		pay.enterCardDetails(cardNumber);
		pay.ExpiryMonth();
		pay.selectMonth(month);
		pay.ExpiryYear();
		pay.selectYear(year);
		pay.entercvv(cvc);
		pay.clickOnSubmit();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		String actual_Message = driver.findElement(By.xpath("//form[@class = 'sc-bwzfXH kKMpdu']/div[1]/span")).getText();
		String expected_Message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_card_details");
		Assert.assertEquals(actual_Message, expected_Message);
	}
	
	@Test(enabled=true,priority=13,groups= {"FreeTrialNegative","All"})
	public void FreeTrialFlow_PaymentPage_TrustlyAccountNumber() {
		
		int i = 555;
		newEmail = "test_error"+i+"@test.se";
		confirm = "test_error"+i+"@test.se";
		newPwd = "password";
		bankName = "Danske";
		personnummer = "384986748";
		i=i+1;
		
		HomePage home =  new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		log.info("PASSING INVALID ACCOUNT DETAILS AND VALIDATING ERRORS....");
		PaymentPage pay = new PaymentPage(driver);
		pay.trustlyDropDown();
		pay.clickOnSubmitTrustly();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
		if(bankName.contains("Danske")) {
			pay.DanskeBank();
		}
		pay.clickToContinue();
		pay.enterAccount(personnummer);
		pay.clickToContinue();
		String actual_Message = driver.findElement(By.xpath("//div[@id = 'core_message_content']")).getText();
		String expected_Message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "invalid_account_number");
		Assert.assertEquals(actual_Message, expected_Message);
	}
	
	@Test(enabled=true,priority=14,groups = {"FreeTrialNegative","All"})
	public void FreeTrialFlow_Payment_EmptyOtp() {
		
		int i = 666;
		newEmail = "test_error"+i+"@test.se";
		confirm = "test_error"+i+"@test.se";
		newPwd = "password";
		bankName = "Danske";
		personnummer = "1111111111";
		String otp = "";
		i = i+1;
		
		HomePage home =  new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		log.info("PASSING INVALID OTP AND VALIDATING ERRORS....");
		PaymentPage pay = new PaymentPage(driver);
		pay.trustlyDropDown();
		pay.clickOnSubmitTrustly();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
		if(bankName.contains("Danske")) {
			pay.DanskeBank();
		}
		pay.clickToContinue();
		pay.enterAccount(personnummer);
		pay.clickToContinue();
		pay.enterOtp(otp);
		pay.clickToContinue();
		String actual_Message = driver.findElement(By.xpath("//div[@id = 'core_message_content']")).getText();
		String expected_Message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "empty_otp");
		Assert.assertEquals(actual_Message, expected_Message);
		
	}
	
	@Test(enabled=true,priority=15,groups= {"FreeTrialNegative","All"})
	public void FreeTrialFlow_Payment_InvalidOtp() {
		
		int i = 777;
		newEmail = "test_error"+i+"@test.se";
		confirm = "test_error"+i+"@test.se";
		newPwd = "password";
		bankName = "Danske";
		personnummer = "1111111111";
		String otp = "123456";
		i=i+1;
		
		HomePage home =  new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page step1 = new HomeStep1Page(driver);
		step1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Familysubscription();
		sub.ContinueSubscription();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.Family3Subscription();
			
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		log.info("PASSING INVALID OTP AND VALIDATING ERRORS....");
		PaymentPage pay = new PaymentPage(driver);
		pay.trustlyDropDown();
		pay.clickOnSubmitTrustly();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
		if(bankName.contains("Danske")) {
			pay.DanskeBank();
		}
		pay.clickToContinue();
		pay.enterAccount(personnummer);
		pay.clickToContinue();
		pay.enterOtp(otp);
		pay.clickToContinue();
		String actual_Message = driver.findElement(By.xpath("//div[@class = 'message_text']")).getText();
		String expected_Message = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_SE, "wrong_otp");
		Assert.assertEquals(actual_Message, expected_Message);
		log.info("Completing Flow By Entering correct Otp");
		driver.findElement(By.xpath("//div[@class = 'action_buttons']/a")).click();
		otp = pay.getOtp();
		pay.enterOtp(otp);
		pay.clickToContinue();
		pay.finishPayment();
	}
	
	@Test(enabled=true,priority=16,groups= {"FreeTrialNegative","All"})
	public void FreeTrial_VisitorToFreeTrial() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		gateway = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 3);
		newEmail = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		confirm = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 1);
		newPwd = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 2);
		cardNumber = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 4);
		cvc = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 5);
		month = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 6);
		year = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 7);
		bankName = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 8);
		personnummer = Excel.getCellValue(INPUT_PATH, "NewEmail", 1, 9);
		
		log.info("Registration Flow for Visitor Started.....");
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page home1 = new HomeStep1Page(driver);
		home1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Guldsubscription();
		sub.ContinueSubscription();
		
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropdown();
		pay.clickOnNextoryLogo();
		
		home.Loggout();
		home.clicktologin();
		
		LoginPage login = new LoginPage(driver);
		login.enterEmail(newEmail);
		login.enterPassword(newPwd);
		login.clickOnLogin();
		home1.clicktoproceed();
		sub.Guldsubscription();
		sub.ContinueSubscription();
		sub2.ClicktoProceed();
		driver.findElement(By.xpath("//a[@class='sc-bdVaJa ebrZQc']")).click();
		pay.adyenDropdown();
		pay.enterCardDetails(cardNumber);
		pay.ExpiryMonth();
		pay.selectMonth(month);
		pay.ExpiryYear();
		pay.selectYear(year);
		pay.entercvv(cvc);
		pay.clickOnSubmit();
		log.info("Registration completed from visitor to FreeTrialMember");
		Excel.shiftingRowsDown(INPUT_PATH, "ExistingEmail", 2);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 1, newEmail);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 2, newPwd);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 3, subscription);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 4, "FREE TRIAL");
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 6, cardNumber);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 7, month);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 8, year);
		Excel.setExcelData(INPUT_PATH, "ExistingEmail", 2, 9, cvc);
		
		Excel.shiftingRowsUp(INPUT_PATH, "newEmail", 1);
	}
	
	@Test(enabled=false,priority=17,groups= {"FreeTrialNegative","All"})
	public void FreeTrial_TrustlyCancellation() {
		
		gateway = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 3);
		newEmail = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 1);
		confirm = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 1);
		newPwd = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 2);
		cardNumber = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 4);
		cvc = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 5);
		month = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 6);
		year = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 7);
		bankName = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 8);
		personnummer = Excel.getCellValue(INPUT_PATH, "NewEmail", 7, 9);
		
		HomePage home = new HomePage(driver);
		home.clicktoregister();
		
		HomeStep1Page home1 = new HomeStep1Page(driver);
		home1.clicktoproceed();
		
		NewSubscriptionPage sub = new NewSubscriptionPage(driver);
		sub.Guldsubscription();
		sub.ContinueSubscription();
		
		SubscriptionStep2 sub2 = new SubscriptionStep2(driver);
		sub2.ClicktoProceed();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmEmail(confirm);
		reg.enterPassword(newPwd);
		reg.agreeTerms();
		reg.clickToSubmit();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.trustlyDropDown();
		pay.clickOnSubmitTrustly();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to first frame
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//Switch to nested frame
		if(bankName.contains("Danske")) {
			pay.DanskeBank();
		}
		pay.clickToContinue();
		pay.enterAccount(personnummer);
		pay.clickToContinue();
		driver.findElement(By.xpath("//div[@id = 'core_order_cancel']")).click();
		Alert alert = driver.switchTo().alert();
		
	}
}
*/