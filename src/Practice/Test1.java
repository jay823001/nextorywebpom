package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Test1 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver_latest.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://104.155.208.232:82/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@class='sc-dVhcbM jGlyXt sc-bdVaJa jPEMFQ']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		driver.findElement(By.xpath("//a[@class='sc-gkFcWv bLSVbD sc-bdVaJa ebrZQc']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(text(),'Guld')]")).click();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		driver.findElement(By.xpath("//a[@class='sc-hUfwpO jPPrxk sc-bdVaJa ebrZQc']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'Skapa konto')]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='E-postadress']")).sendKeys("test_jan22_001@test.se");
		driver.findElement(By.xpath("//input[@placeholder='Upprepa e-postadress']")).sendKeys("test_jan22_001@test.se");
		driver.findElement(By.xpath("//input[@placeholder='Lösenord']")).sendKeys("password");
		driver.findElement(By.xpath("//small[@class='sc-kkbgRg bjmqI']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@name='scroll-to-payment']//h3[2]")).click();
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		//WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Använd Mobilt BankID')]"));
		driver.findElement(By.xpath("//button[contains(text(),'Använd Mobilt BankID')]")).click();
		/*Actions action = new Actions(driver);
		Action click = action.moveToElement(element).click().build();
		click.perform();*/
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		/*Screen s = new Screen();*/
		
	}

}
