package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import utills.Utills;

public class TestBase extends Utills {

	public static Properties repository = new Properties();
	public File f;
	public FileInputStream FI;


	public void init() throws Exception {
		loadpropertiesFile();
		SelectBrowser(repository.getProperty("Browser"));
		driver.get(repository.getProperty("url"));
	}

	public void loadpropertiesFile() throws Exception {

		f = new File(System.getProperty("user.dir")
				+ "\\src\\config\\config.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\PageObjects.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\CommercePageObjects.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\ResearchPageObjects.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\StorageManage.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\InventoryPageObjects.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\UserManagement.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\RequestPageObjects.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\LabdriveObjects.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\pageObjects\\EquipmentPageObjects.properties");
		FI = new FileInputStream(f);
		repository.load(FI);
		
	}
	
public static WebElement getLocator(String locator) throws Exception{
		
		String locatorType = locator.split("~")[0];
		String locatorValue = locator.split("~")[1];
		
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))
			|| (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
				return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
				return driver.findElement(By.className(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else throw new Exception("Unknow locator type " +locatorType+ "'");
	}
	
		public WebElement getWebElement(String locator) throws Exception{
			
			return getLocator(repository.getProperty(locator));
			
		}
	
		public void closeBrowser(){
			driver.quit();
		}

}
