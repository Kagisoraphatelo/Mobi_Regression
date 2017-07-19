package datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

public class testing {
	@Test()
	public void logit() {
		//WebDriver driver = new FirefoxDriver();
		 WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver.exe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://uat-testing.finchoice.mobi/");
		driver.findElement(By.xpath("//*[@data-testid='CustomerLandingLink']")).click();
		driver.findElement(By.xpath("//*[@data-testid='Username']")).sendKeys("0828721798");
		driver.findElement(By.xpath("//*[@data-testid='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@data-testid='Login']")).click();
		
		/*driver.findElement(By.partialLinkText("Login to your ")).click();
		driver.findElement(By.id("UserName")).sendKeys(uname);
		driver.findElement(By.id("Password")).sendKeys(psw);*/
		driver.close();
	}

}
