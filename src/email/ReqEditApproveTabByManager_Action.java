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

public class ReqEditApproveTabByManager_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void ReqEditApproveTabByManager() throws Exception{
		
		Reporter.log("Login to Application with Specific User One");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("manager2142@amail.club");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Navigate to Request Page");
		Thread.sleep(3000);
		getWebElement("SideBarInventoryAndRequest").click();
		Thread.sleep(2000);
		getWebElement("SubmoduleRequest").click();
		
		Reporter.log("Click on Approved Tab and Increment Product Count");
		Thread.sleep(3000);
		getWebElement("ApprovedTabButton").click();
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
		
		Reporter.log("Open Owner Email Inbox and Verify the Count Updated mail");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(2));
		driver.get("https://www.mailinator.com/");
		Thread.sleep(5000);
		getWebElement("MailinatorEmailField").sendKeys("devtest2142@mailinator.com");
		Thread.sleep(2000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(3000);
		driver.switchTo().frame("msg_body");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Count updated"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Move to Application Tab");
		Thread.sleep(3000);
		driver.switchTo().window(tabs1.get(0));
	    
	    Reporter.log("From Approve Tab Edit Product Price");
	    Thread.sleep(3000);
	    getWebElement("ApproveEditPriceIcon").click();
	    Thread.sleep(2000);
	    getWebElement("ApproveEditPriceField").sendKeys("30");
	    Thread.sleep(2000);
	    getWebElement("ApproveEditPriceRightTickButton").click();
	    
	    Thread.sleep(3000);
		driver.switchTo().window(tabs.get(1));
		
		Reporter.log("Open Member Email Inbox and Verify the Price updated mail");
		Thread.sleep(10000);
		getWebElement("TempMailInbox2").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Price updated"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Thread.sleep(3000);
		driver.switchTo().window(tabs1.get(2));
		
		Reporter.log("Open Owner Email Inbox and Verify the Price updated mail");
		Thread.sleep(3000);
		getWebElement("MailinatorPublicInbox").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(3000);
		driver.switchTo().frame("msg_body");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Price updated"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Move to Application Tab");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		
		Reporter.log("From Approve Tab Edit Notes");
		Thread.sleep(3000);
		getWebElement("ApproveEditNoteIcon").click();
		Thread.sleep(2000);
	    getWebElement("ApproveAddNotes2").click();
	    Thread.sleep(2000);
	    getWebElement("ApproveEditNoteField").click();
	    Thread.sleep(2000);
	    getWebElement("ApproveEditNoteField").clear();
	    Thread.sleep(2000);
	    getWebElement("ApproveEditNoteField").sendKeys("Note2");
	    Thread.sleep(2000);
	    getWebElement("ApproveAddNotesSaveButton").click();
	    
	    Thread.sleep(3000);
		driver.switchTo().window(tabs.get(1));
		
		Reporter.log("Open Member Email Inbox and Verify the Note has been added/edited mail");
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
		
		Thread.sleep(3000);
		driver.switchTo().window(tabs1.get(2));
		
		Reporter.log("Open Owner Email Inbox and Verify the Note has been added/edited mail");
		Thread.sleep(3000);
		getWebElement("MailinatorPublicInbox").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(3000);
		driver.switchTo().frame("msg_body");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Note has been added/edited"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Move to Application Tab");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	    
		
		
	}

}
