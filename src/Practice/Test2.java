package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test2 {

	public static void main(String[] args) {
		
			
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver_latest.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://104.155.208.232:82/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		/*int x = driver.findElements(By.tagName("iframe")).size();
		System.out.println(x);
		driver.switchTo().frame(0);
		int y = driver.findElements(By.tagName("iframe")).size();
		System.out.println(y);
		driver.switchTo().frame(0);*/
		driver.findElement(By.xpath("//a[@class='sc-dVhcbM jGlyXt sc-bdVaJa jPEMFQ']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		WebElement element = driver.findElement(By.xpath("//a[@class='sc-gkFcWv bLSVbD sc-bdVaJa ebrZQc']"));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		element.click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(text(),'Guld')]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		WebElement element4 = driver.findElement(By.xpath("//a[@class='sc-hUfwpO jPPrxk sc-bdVaJa ebrZQc']"));
		Actions action3 = new Actions(driver);
		action3.moveToElement(element4).perform();
		element4.click();		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		JavascriptExecutor js4 = (JavascriptExecutor) driver;
		js4.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		WebElement element1 = driver.findElement(By.xpath("//a[contains(text(),'Skapa konto')]"));
		Dimension y = element4.getSize();
		System.out.print(y);
		/*WebDriverWait wait2 = new WebDriverWait(driver,10);
		wait2.until(ExpectedConditions.visibilityOf(element1));*/
		Actions action1 = new Actions(driver);
		action1.moveToElement(element1).perform();
		element1.click();
		//driver.findElement(By.xpath("//a[contains(text(),'Skapa konto')]")).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='E-postadress']")).sendKeys("test_jan24_06@test.se");
		driver.findElement(By.xpath("//input[@placeholder='Upprepa e-postadress']")).sendKeys("test_jan24_06@test.se");
		driver.findElement(By.xpath("//input[@placeholder='Lösenord']")).sendKeys("password");
		driver.findElement(By.xpath("//small[@class='sc-kkbgRg bjmqI']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@name='scroll-to-payment']//h3[2]")).click();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		WebElement element2 = driver.findElement(By.xpath("//button[contains(text(),'Använd Mobilt BankID')]"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(element2).perform();
		element2.click();
		//driver.findElement(By.xpath("//button[contains(text(),'Använd Mobilt BankID')]")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		//ExpectedConditions.frameToBeAvailableAndSwitchToIt(0);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		driver.findElement(By.xpath("//div[@class = 'core_SelectMethod_Country core_SelectMethod_Country_SE open']/a[1]/span[1]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'Fortsätt')]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name = 'loginid']")).sendKeys("1111100000");
		driver.findElement(By.xpath("//a[contains(text(),'Fortsätt')]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String otp = driver.findElement(By.xpath("//div[@class = 'form_item message'][2]/span[2]")).getText();
		System.out.println(otp);
		driver.findElement(By.xpath("//input[@name = 'challenge_response']")).sendKeys(otp);
		driver.findElement(By.xpath("//a[contains(text(),'Fortsätt')]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@class = 'button core_action_button button_next']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'Mitt konto')]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		/*JavascriptExecutor js5 = (JavascriptExecutor) driver;
		js5.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		WebElement element5 = driver.findElement(By.xpath("//span[contains(text(),'Logga ut')]"));
		Actions action6 = new Actions(driver);
		action6.moveToElement(element5).perform();
		element5.click();*/
		String sub = driver.findElement(By.xpath("//div[@class = 'sc-cmIlrE hgRTLD']//span")).getText();
		System.out.println(sub);
}

}
