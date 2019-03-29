package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test3 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver_latest.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://104.155.208.232:82/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'Logga in')]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test_jan25_01@test.se");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Logga in')]"));
		boolean login = element.isEnabled();
		if(login)
			element.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement e =  driver.findElement(By.xpath(".//*[@class= 'sc-bSbAYC dNOwjw'][2]/div[1]/h2/span"));
		Actions a = new Actions(driver);
		a.moveToElement(e).perform();
		String x = e.getText();
		System.out.println(x);
	}
}
