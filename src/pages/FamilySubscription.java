package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class FamilySubscription extends BasePage{

	public FamilySubscription(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//div[@class =  'checkbox'])[1]")
	private WebElement Familj2;
	
	@FindBy(xpath = "(//div[@class =  'checkbox'])[2]")
	private WebElement Familj3;
	
	@FindBy(xpath = "//div[@class =  'checkbox checkbox--active']")
	private WebElement Familj4;
	
	@FindBy(xpath = "//a[@class='btn btn--primary btn--arrow']")
	private WebElement Forsatt;
	
	@FindBy(xpath = "//div[@class = 'amountPicker__item'][1]/div[1]/p")
	private List<WebElement> Family2Text;
	
	@FindBy(xpath = "//div[@class = 'amountPicker__item'][2]/div[1]/p")
	private List<WebElement> Family3Text;
	
	@FindBy(xpath = "//div[@class = 'amountPicker__item'][3]/div[1]/p")
	private List<WebElement> Family4Text;
	
	public void clickforFamily2() {
		
		waitTillElementIsVisible(Familj2);
		waitTillElementIsClickable(Familj2);
		Familj2.click();
	}
	
	public void clickforFamily3() {
		
		waitTillElementIsVisible(Familj3);
		waitTillElementIsClickable(Familj3);
		Familj3.click();
	}
	
	public void clickforFamily4() {
		
		waitTillElementIsVisible(Familj4);
		waitTillElementIsClickable(Familj4);
		Familj4.click();
	}
	
	public void clickToContinue() {
		
		waitTillElementIsVisible(Forsatt);
		waitTillElementIsClickable(Forsatt);
		Forsatt.click();
	}
	
	public String getFamily2Text(int index) {
		
		String text = "";
		for(int i=0;i<Family2Text.size();i++) {
			
			if(i == index) {
				text=Family2Text.get(i).getText();
				break;
			}
		}
		return text;
	}
	
	public String getFamily3Text(int index) {
		
		String text = "";
		for(int i=0;i<Family3Text.size();i++) {
			
			if(i == index) {
				text=Family3Text.get(i).getText();
				break;
			}
		}
		return text;
	}
	
	public String getFamily4Text(int index) {
		
		String text = "";
		for(int i=0;i<Family4Text.size();i++) {
			
			if(i == index) {
				text=Family4Text.get(i).getText();
				break;
			}
		}
		return text;
	}
}
