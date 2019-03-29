package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test5 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver_latest.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://104.155.208.232:82/register/email");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("testetstetst");
		driver.findElement(By.xpath("//input[@name = 'repeatemail']")).sendKeys("test_error@gmail.com");
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("password");
		driver.findElement(By.xpath("//small[@class = 'sc-kkbgRg bjmqI']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Fortsätt')]")).click();
		Alert alert = driver.switchTo().alert();
		String message = driver.switchTo().alert().getText();
		System.out.println(message);
		alert.accept();
	}

}
