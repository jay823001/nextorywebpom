/*package registrationflows;

import org.testng.annotations.Test;

import common.SuperTestScript;
import pages.HomePage;
import pages.LoginPage;
import pages.MittKonto;
import pages.UserCancellation;

public class UserCancelFlow  extends SuperTestScript{
	
	public String email;
	public String password;
	
	public UserCancelFlow() {
		
		loginrequired = true;
		logoutrequired = true;
	}
	
	@Test(enabled = true,priority = 1,groups = {"UserCancelFlowsPositive","All"})
	public void UserCancelFlowPositive() {
		
		email = "test_jan23_12@test.se";
		password = "password";
		
		HomePage home = new HomePage(driver);
		home.clicktologin();
		
		LoginPage login = new LoginPage(driver);
		login.enterEmail(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		MittKonto konto = new MittKonto(driver);
		konto.CancelSub();
		
		UserCancellation cancel = new UserCancellation(driver);
		cancel.cancelSubscription();
		cancel.selectReason(1);
		cancel.EndSubscription();
		
		home.clickkonto();
		konto.LogOut();		
	}
}
*/