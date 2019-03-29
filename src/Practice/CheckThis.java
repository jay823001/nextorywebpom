package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import common.SuperTestScript;
import pages.HomePage;
import pages.KontoDetails;
import pages.OrderDetails;
import pages.PaymentPage;
import pages.RegistrationPage;
import pages.SubscriptionDetails;
import pages.SubscriptionPage;
import pages.UpdateCardDetails;
import pages.UserDetails;
import pages.FamilySubscription;

public class CheckThis extends SuperTestScript{
	
	@Test(enabled = true)
	public void FreeTrial() {
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforFamily();;
		sub.clickToContinue();
		
		FamilySubscription fam = new FamilySubscription(driver);
		fam.clickforFamily2();
		fam.clickToContinue();
		
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail("test_mar18_017@test.se");
		reg.confirmMail("test_mar18_017@test.se");
		reg.enterPassword("password");
		reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropDown();
		
		WebDriverWait wait = new WebDriverWait(driver,40);
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.enterCardNumber("5555444433331111");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		pay.enterexpiryDate("1020");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));
		pay.entercvv("737");
		driver.switchTo().defaultContent();
		//pay.GoBack();
		//pay.clickOnSubmit();
		//String s = pay.getPaymentSummary();
		//log.info(s);
		
		/*driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		log.info(driver.getCurrentUrl());*/
		
		UserDetails ud = new UserDetails(driver);
		ud.enterFirstName();
		ud.enterLastname();
		ud.enterPhoneNumber();
		ud.clickToContinue();
		ud.clickOnMittKonto();
		
		KontoDetails konto = new KontoDetails(driver);
		konto.clickForKonto();
		String mail = konto.getEmail();
		log.info(mail);
		konto.setEmail("testmar1802@test.se");
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
		String subsc = suba.getSubscriptionDetails();
		log.info(subsc);
		String actxt = suba.getAccountText();
		log.info(actxt);
		
		home.clickToSearch();
		
		UpdateCardDetails card = new UpdateCardDetails(driver);
		card.clickToGetCardDetails();
		String cardText = card.getAccountText();
		log.info(cardText);
		//card.clickToUpdateCard();
		
		home.clickToSearch();
		
		OrderDetails order = new OrderDetails(driver);
		order.clickTogetOrderDetails();
		order.clickOnFakutra();
		String totalamount = order.getTotalAmount();
		log.info(totalamount);
		String momAmount = order.getMomAmount();
		log.info(momAmount);
	}
	
}
