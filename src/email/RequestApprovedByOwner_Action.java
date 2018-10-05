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

public class RequestApprovedByOwner_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void RequestApprovedByOwner() throws Exception{
		
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
		getWebElement("SubmoduleRequest").click();
		
		Reporter.log("Add Product Request and Reject the Product");
		Thread.sleep(3000);
		getWebElement("AddRequestButton").click();
		Thread.sleep(1000);
		getWebElement("RequestNewProductsCatalogField").sendKeys("743");
		Thread.sleep(3000);
		getWebElement("RequestNewProductsCatalogField").sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		getWebElement("RequestNewProductsCatalogField").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("RequestNewProductsSaveButton").click();
		Thread.sleep(8000);
		getWebElement("RequestApproveFromLineItem").click();
		
		Reporter.log("Open Manager Email Inbox and Verify the Product Rejected mail");
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
		Assert.assertTrue(driver.getPageSource().contains("Requested product approved"));
		
		Reporter.log("Similarly Open Member Email Inbox and Verify the Product Rejected mail");
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
		Assert.assertTrue(driver.getPageSource().contains("Requested product approved"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Move to application Tab");
		Thread.sleep(2000);
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	    }
	    
	    Thread.sleep(3000);
	    getWebElement("ApproveMoveToRequestLineItem").click();
	    Thread.sleep(3000);
	    
	    Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();

		
	}
}
