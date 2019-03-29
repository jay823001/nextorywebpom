package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class test_bflow {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver_latest.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://104.155.208.232:82/register/subscription/b");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class = 'subscriptionTable__header']/div[3]/div[2]/div")).click();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class =  'checkbox'])[1]")).click();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("testbflow_jan31@test.se");
		driver.findElement(By.xpath("//input[@name='confirmemail']")).sendKeys("testbflow_jan31@test.se");
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("password");
		driver.findElement(By.xpath("//div[@class='checkbox']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='formLayout']//div[1]//a[1]//div[1]//div[1]")).click();
		driver.findElement(By.xpath("//input[@placeholder='Kortnummer']")).sendKeys("5555444433331111");
		Select select = new Select (driver.findElement(By.xpath("//select[@name = 'month']")));
		select.selectByValue("08");
		Select select1 = new Select (driver.findElement(By.xpath("//select[@name = 'year']")));
		select1.selectByValue("2018");
		driver.findElement(By.xpath("//input[@name = 'cvv']")).sendKeys("737");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}

}
