
//CReated by Kagiso Raphatelo April 2017
package datadriven;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@SuppressWarnings("deprecation")
public class readWriteexcel {
	ArrayList<String> dropDarr;
	//public static WebDriver drv = new FirefoxDriver();
	@Test(dataProvider = "mobiUs")
	public void reloanApplication(String Uname,String psw) throws InterruptedException{
		
		//Login Finchoice mobi site
		WebDriver drv = new FirefoxDriver();
		drv.manage().window().maximize();
		drv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		drv.get("https://uat-testing.finchoice.mobi/");
		drv.findElement(By.xpath("//*[@data-testid='CustomerLandingLink']")).click();
		drv.findElement(By.xpath("//*[@data-testid='Username']")).sendKeys(Uname);
		drv.findElement(By.xpath("//*[@data-testid='Password']")).sendKeys(psw);
		drv.findElement(By.id("chkShwPwd")).click(); // should in future code random check 
		//Thread.sleep(3000);
		drv.findElement(By.xpath("//*[@data-testid='Login']")).click();
		Assert.assertEquals("https://uat-testing.finchoice.mobi/Reloan/CustomerLanding",drv.getCurrentUrl());
		
		
		drv.findElement(By.xpath("//*[@data-testid='CustomerLandingReloan3']")).click();
		List<WebElement> sl = new Select(drv.findElement(By.xpath("//*[@data-testid='LoanAmountSelect']"))).getOptions();
		dropDarr = new ArrayList<String>();

		   for(int i=1;i<sl.size();i++){
		   //getting all the data from dropdown
		   String dvalues =sl.get(i).getText();
		   //Storing each value in array list
		   dropDarr.add(dvalues);
		   }
		   
		   //generating random integer value
		   Random rnd = new Random();
		      int randomNumber = rnd.nextInt(sl.size()- 0) + 0;
		      
		      //getting random data from array List
		      String randomAmount=dropDarr.get(randomNumber);
		      
		      //passing random data into dropdown field
		   drv.findElement(By.xpath("//*[@data-testid='LoanAmountSelect']")).sendKeys(randomAmount);
		   
		   
		   drv.findElement(By.xpath("//*[@data-testid='QuoteNext']")).click();
		   drv.findElement(By.xpath("//*[@data-testid='QuickQuoteApply']")).click();
		   drv.findElement(By.xpath("//*[@data-testid='PersonalDetailsConfirm']")).click();
		   drv.findElement(By.xpath("//*[@data-testid='AffordabilityConfirm']")).click();
		   drv.findElement(By.xpath("//*[@data-testid='SummaryConfirm']")).click(); 
		   drv.findElement(By.name("Product:Decline")).click();
		   drv.findElement(By.xpath("//*[@data-testid='TermsAccept']")).click();
	       drv.close();
		}
		

	@DataProvider (name = "mobiUs")
	public Object[][] mobiUser() throws Exception {
		File f = new File("C:/Users/kraphate/workspace/test1.xls");
		Workbook w = Workbook.getWorkbook(f);
		Sheet s = w.getSheet("Sheet1");
		int rows = s.getRows();
		int columns = s.getColumns();
		String mobiData[][] = new String[rows][columns];
		System.out.println(rows);
		System.out.println(columns);

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				Cell k = s.getCell(c,r);
				mobiData [r][c] = k.getContents();
				System.out.println(mobiData[r][c]);
			}
		}
		return mobiData;

	}
}
