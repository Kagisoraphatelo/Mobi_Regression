package datadriven;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class dataDriven {
	
	WebDriver driver;
	
	@Test(dataProvider = "logiData")
	public void logIn(String uname, String psw) {
		driver = new FirefoxDriver();
		/*driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:/newVer/chromedriver.exe");*/
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://uat-testing.finchoice.mobi/");
		driver.findElement(By.xpath("//*[@data-testid='CustomerLandingLink']")).click();
		driver.findElement(By.xpath("//*[@data-testid='Username']")).sendKeys(uname);
		driver.findElement(By.xpath("//*[@data-testid='Password']")).sendKeys(psw);
		driver.findElement(By.xpath("//*[@data-testid='Login']")).click();
		
		driver.close();
	}

	@DataProvider(name = "logiData")
	public Object[][] passData() {

		Object[][] data = new Object[3][2];

		data[0][0] = "0828345011"; //row
		data[0][1] = "123456";

		data[1][0] = "0829219834";//row
		data[1][1] = "123456";

		data[2][0] = "0838661209";//row
		data[2][1] = "123456";

		
		/*System.out.println(data[0][0]);
		System.out.println(data[0][1]);*/
		return data;

	}
	/*@AfterMethod
	public void quitWebdriver(){
		driver.quit();
	}*/
}