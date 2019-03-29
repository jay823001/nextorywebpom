package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import common.BasePage;

public class UserCancellation extends BasePage{

	public UserCancellation(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='sc-cxZfpC eCjVii']")
	private WebElement changeSubscription;
	
	@FindBy(xpath = "//a[@class='sc-jIyBzM hSOQNF']")
	private WebElement Cancel;
	
	@FindBy(xpath = "//div[@class = 'sc-dlyikq dujifR']/span[2]")
	private WebElement Exit;
	
	@FindBy(xpath = "//form[@class='sc-bwzfXH kKMpdu']/select")
	private WebElement dropDown;
	
	@FindBy(xpath = "//input[@value='Avsluta konto']")
	private WebElement Cancel_End;
	
	@FindBy(xpath = "//a[@class='sc-iBmynh gRcxmv']")
	private WebElement Avbryt;
	
	@FindBy(xpath = "//input[@name='annaninput']")
	private WebElement others;
	
	@FindBy(xpath = "//a[@class='sc-bdVaJa kCkJlj']")
	private WebElement Reactivate;
	
	public void changeSubscription() {
		
		waitTillElementIsVisible(changeSubscription);
		waitTillElementIsClickable(changeSubscription);
		changeSubscription.click();
	}
	
	public void c_cancellation() {
		
		waitTillElementIsVisible(Cancel);
		waitTillElementIsClickable(Cancel);
		Cancel.click();
	}
	
	public void cancelSubscription() {
		
		waitTillElementIsVisible(Exit);
		waitTillElementIsClickable(Exit);
		moveToElement(Exit);
		Exit.click();
	}
	
	public void selectReason(int index) {
		
		Select select = new Select(dropDown);
		List <WebElement> options = select.getOptions();
		
		for(int i = 0;i<options.size();i++) {
			
			WebElement opt = options.get(i);
			if(i == index) {
				
				opt.click();
				if(index == 5)
					others.sendKeys("TestReason");
				break;
			}
		}
	}
	
	public void EndSubscription() {
		
		waitTillElementIsVisible(Cancel_End);
		waitTillElementIsClickable(Cancel_End);
		Cancel_End.click();
	}
	
	public void GoBack() {
		
		waitTillElementIsVisible(Avbryt);
		waitTillElementIsClickable(Avbryt);
		Avbryt.click();
	}
	
	public void GoToKontoPage() {
		
		waitTillElementIsVisible(Reactivate);
		waitTillElementIsClickable(Reactivate);
		Reactivate.click();
		
	}
}
