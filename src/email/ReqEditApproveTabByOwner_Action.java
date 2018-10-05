package email;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ReqEditApproveTabByOwner_Action extends TestBase {

	@SuppressWarnings("deprecation")
	@Test
	public void ReqEditApproveTabByOwner() throws Exception{
		
		Reporter.log("Login to Application with Specific User One");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtest2142@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Navigate to Request Page");
		Thread.sleep(3000);
		getWebElement("SideBarInventoryAndRequest").click();
		Thread.sleep(1000);
		getWebElement("SubModuleRequest").click();
		Thread.sleep(8000);
		getWebElement("RequestApproveFromLineItem").click();
		Thread.sleep(5000);
		getWebElement("ApproveCountIncrement").click();
		
		Reporter.log("Open Manager Email Inbox and Verify the Count Updated mail");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys("manager2142");
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Count updated"));
		
		Reporter.log("Open Member Email Inbox and Verify the Count Updated mail");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		getWebElement("AddInbox1").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys("member2142");
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Count updated"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Move to Application Tab");
		Thread.sleep(2000);
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
	    
	    Reporter.log("From Approve Tab Edit Product Price");
	    Thread.sleep(3000);
	    getWebElement("ApproveEditPriceIcon").click();
	    Thread.sleep(2000);
	    getWebElement("ApproveEditPriceField").sendKeys("20");
	    Thread.sleep(2000);
	    getWebElement("ApproveEditPriceRightTickButton").click();
	    
	    Thread.sleep(3000);
		driver.switchTo().window(tabs.get(1));
		
		Reporter.log("Open Manager Email Inbox and Verify the Price updated mail");
		Thread.sleep(10000);
		getWebElement("TempMailInbox3").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Price updated"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Open Member Email Inbox and Verify the Price updated mail");
		Thread.sleep(3000);
		getWebElement("TempMailInbox2").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Price updated"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Move to Application Tab");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		
		Reporter.log("From Approve Tab Add Notes");
	    Thread.sleep(3000);
	    getWebElement("ApproveEditNoteIcon").click();
	    Thread.sleep(2000);
	    getWebElement("ApproveAddNotes").click();
	    Thread.sleep(1000);
	    getWebElement("ApproveAddNotes1").click();
	    Thread.sleep(2000);
	    getWebElement("ApproveAddNotes1").sendKeys("Note1");
	    Thread.sleep(2000);
	    getWebElement("ApproveAddNotesSaveButton").click();
	    
	    Thread.sleep(3000);
		driver.switchTo().window(tabs.get(1));
		
		Reporter.log("Open Manager Email Inbox and Verify the Note has been added/edited mail");
		Thread.sleep(10000);
		getWebElement("TempMailInbox2").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Note has been added/edited"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Open Member Email Inbox and Verify the Note has been added/edited mail");
		Thread.sleep(3000);
		getWebElement("TempMailInbox3").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Note has been added/edited"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Move to Application");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
	    
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	    
		
		
	}
}
