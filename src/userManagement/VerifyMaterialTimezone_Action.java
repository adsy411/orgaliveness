package userManagement;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class VerifyMaterialTimezone_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
    public void VerifyMaterialTimezone() throws Exception {
    	
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
    	
		Thread.sleep(5000);
    	getWebElement("ProfileSettings").click();
    	Thread.sleep(3000);
    	getWebElement("EditProfile").click();
    	
    	Thread.sleep(3000);
    	Library lib = new Library();
    	lib.Select("UserProfileEditTimezone", 9);
    	Thread.sleep(2000);
    	getWebElement("EditProfileSave").click();
    	Thread.sleep(10000);
    	getWebElement("EditProfileCancel").click();
    	
    	Thread.sleep(5000);
    	getWebElement("SideBarInventoryAndRequest").click();
    	Thread.sleep(1000);
    	getWebElement("NavigationMaterial").click();
    	Thread.sleep(3000);
    	getWebElement("AddNewMaterialButton").click();
    	Thread.sleep(2000);
		getWebElement("MaterialNameField").sendKeys("Trizma");
		Thread.sleep(5000);
		getWebElement("MaterialNameField").sendKeys(Keys.ARROW_DOWN);
		getWebElement("MaterialNameField").sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		getWebElement("AddToInventoryButton").click();
		Thread.sleep(5000);
		getWebElement("ConfirmationofAddMaterial").click();
    	
    	Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.google.com/");
    	Thread.sleep(2000);
    	driver.findElement(By.id("lst-ib")).sendKeys("EST time zone");
    	Thread.sleep(2000);
    	driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
    	
    	Thread.sleep(3000);
    	String ESTDateTimeGoogle = driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div/div/div/div[1]")).getText();
    	System.out.println("Date and Time in Google is "+ESTDateTimeGoogle);
    	
    	Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		
		Thread.sleep(2000);
		String MatName = driver.findElement(By.xpath("//*[@id='test']/div/div/div[1]/div[1]/div[1]/div[1]/a/span")).getText();
		System.out.println("Material Name is "+MatName);
		
		Reporter.log("Click on User Settings and go to My Profile");
		Thread.sleep(3000);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
    	
		Thread.sleep(2000);
    	String MatNameActivity = driver.findElement(By.xpath("//*[@id='activity-model:activityStreamByUser_data']/tr[1]/td/p[2]")).getText();
    	System.out.println("Material Name in Activity is "+MatNameActivity);
    	
    	Thread.sleep(2000);
    	Assert.assertTrue(driver.getPageSource().contains(MatName));
	    
	    Thread.sleep(3000);
    	Assert.assertTrue(driver.getPageSource().contains(ESTDateTimeGoogle));
	    
	    Thread.sleep(2000);
    	getWebElement("ProfileSettings").click();
    	Thread.sleep(3000);
    	getWebElement("EditProfile").click();
    	
    	Thread.sleep(5000);
    	lib.Select("UserProfileEditTimezone", 8);
    	Thread.sleep(2000);
    	getWebElement("EditProfileSave").click();
    	Thread.sleep(5000);
    	getWebElement("EditProfileCancel").click();
    	
    	Thread.sleep(5000);
    	getWebElement("SideBarInventoryAndRequest").click();
    	Thread.sleep(1000);
    	getWebElement("NavigationMaterial").click();
    	Thread.sleep(3000);
    	getWebElement("FirstMaterialDelete").click();
    	Thread.sleep(2000);
    	getWebElement("MaterialDisposeButton").click();
    	
    	Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(3000);
		login.Logout();
    	
	}
}
