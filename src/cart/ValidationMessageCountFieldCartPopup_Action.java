package cart;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ValidationMessageCountFieldCartPopup_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
    public void ValidationMessageCountFieldCartPopup() throws Exception {
    	
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Thread.sleep(5000);
		getWebElement("SideBarInventoryAndRequest").click();
		Thread.sleep(1000);
		getWebElement("SubmoduleRequest").click();
		Thread.sleep(3000);
		getWebElement("CartSearch").sendKeys("Phenol");
		Thread.sleep(3000);
		getWebElement("CartSearch").sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
getWebElement("CartSearch").sendKeys(Keys.ENTER);
		
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Thread.sleep(2000);
		getWebElement("AddProductToCart1").click();
		Thread.sleep(2000);
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		getWebElement("HeaderCartIcon").click();
		Thread.sleep(2000);
		explicitWaitForElementUntilClickable("ViewCheckoutbutton");
		getWebElement("CartpopupCountField").click();
		getWebElement("CartpopupCountField").clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='headerForm:headerFormPanel']/div/div/div/div[2]/div[1]/div[1]/table/tbody/tr[2]/td[4]")).click();
		
		Thread.sleep(3000);
		String Message = driver.findElement(By.xpath("//*[@id='alert_placeholder']/div/div/ul/li[2]")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("Warning! Quantity should not be blank", Message);

		Thread.sleep(3000);
		getWebElement("CartpopupCountField").click();
		getWebElement("CartpopupCountField").clear();
		Thread.sleep(2000);
		getWebElement("CartpopupCountField").sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		getWebElement("CartpopupCountField").sendKeys("0");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='headerForm:headerFormPanel']/div/div/div/div[2]/div[1]/div[1]/table/tbody/tr[2]/td[4]")).click();
		
		Thread.sleep(3000);
		String Message1 = driver.findElement(By.xpath("//*[@id='alert_placeholder']/div/div/ul/li[2]")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("Warning! Quantity should not be 0", Message1);
		
		Thread.sleep(2000);
		getWebElement("DeleteIconFromCartPopup").click();
		Thread.sleep(2000);
		getWebElement("ConfirmationYesButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
	}
}
