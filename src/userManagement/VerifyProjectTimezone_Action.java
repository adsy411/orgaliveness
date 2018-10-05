package userManagement;

import java.util.ArrayList;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class VerifyProjectTimezone_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
    public void VerifyProjectTimezone() throws Exception {
    	
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
    	getWebElement("SideBarResearch").click();
    	Thread.sleep(1000);
    	getWebElement("SideBarResearchProject").click();
    	Thread.sleep(3000);
    	getWebElement("CreateNewProjectsButton").click();
    	Thread.sleep(5000);
		getWebElement("NewProjectTitle").sendKeys("NewProjectone");
		Thread.sleep(2000);
		getWebElement("CreateButton").click();
    	
		/*Thread.sleep(5000);
		String ProName = driver.findElement(By.id("cardProjectForm:projectCardRepeat:0:title")).getText();
		System.out.println("Project Name is "+ProName);
		
		Reporter.log("Click on User Settings and go to My Profile");
		Thread.sleep(3000);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Thread.sleep(2000);
    	String ProNameActivity = driver.findElement(By.xpath("//*[@id='activity-model:activityStreamByUser_data']/tr[1]/td/p[2]")).getText();
    	System.out.println("Material Name in Activity is "+ProNameActivity);
    	
    	Thread.sleep(2000);
    	Assert.assertTrue(driver.getPageSource().contains(ProName));*/
    	
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
		
		Thread.sleep(5000);
		String ProName = driver.findElement(By.id("cardProjectForm:projectCardRepeat:0:title")).getText();
		System.out.println("Project Name is "+ProName);
		
		Reporter.log("Click on User Settings and go to My Profile");
		Thread.sleep(3000);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Thread.sleep(2000);
    	String ProNameActivity = driver.findElement(By.xpath("//*[@id='activity-model:activityStreamByUser_data']/tr[1]/td/p[2]")).getText();
    	System.out.println("Material Name in Activity is "+ProNameActivity);
    	
    	Thread.sleep(2000);
    	Assert.assertTrue(driver.getPageSource().contains(ProName));
	    
	    Thread.sleep(3000);
    	Assert.assertTrue(driver.getPageSource().contains(ESTDateTimeGoogle));
    	
    	Thread.sleep(2000);
    	getWebElement("ProfileSettings").click();
    	Thread.sleep(3000);
    	getWebElement("EditProfile").click();
    	
    	Thread.sleep(3000);
    	lib.Select("UserProfileEditTimezone", 8);
    	Thread.sleep(2000);
    	getWebElement("EditProfileSave").click();
    	Thread.sleep(5000);
    	getWebElement("EditProfileCancel").click();
    	
    	Thread.sleep(5000);
    	getWebElement("SideBarResearch").click();
    	Thread.sleep(1000);
    	getWebElement("SideBarResearchProject").click();
    	Thread.sleep(2000);
    	getWebElement("ProjectNameLink").click();
    	Thread.sleep(2000);
    	getWebElement("ProjSettings").click();
    	Thread.sleep(1000);
    	getWebElement("ProjectDelete").click();
    	Thread.sleep(2000);
    	getWebElement("DeleteProjectDeleteButton").click();
    	
    	Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(3000);
		login.Logout();
    	
    	
    	
    	
	}
}
