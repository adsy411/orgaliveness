package cart;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ValidationMessageCountFieldCartPage_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
    public void ValidationMessageCountFieldCartPage() throws Exception {
    	
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
		getWebElement("ViewCheckoutbutton").click();
		
		Thread.sleep(10000);
		getWebElement("CartpageCounteditIcon").click();
		Thread.sleep(2000);
		getWebElement("CartpageCounteditField").clear();
		Thread.sleep(2000);
		getWebElement("CartpageCounteditFieldTickButton").click();
		
		Thread.sleep(3000);
		String Message = driver.findElement(By.xpath("//*[@id='reviewcartform']/div/div/div[2]/div/div/div[2]/div[3]/div/div/div/ul/li[4]/div")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("Count should be greater than 0", Message);
		
		Thread.sleep(2000);
		getWebElement("CartpageCounteditField").click();
		Thread.sleep(2000);
		getWebElement("CartpageCounteditField").sendKeys("0");
		Thread.sleep(2000);
		getWebElement("CartpageCounteditFieldTickButton").click();
		
		Thread.sleep(3000);
		String Message1 = driver.findElement(By.xpath("//*[@id='reviewcartform']/div/div/div[2]/div/div/div[2]/div[3]/div/div/div/ul/li[4]/div")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("Count should be greater than 0", Message1);
		
		Thread.sleep(2000);
		getWebElement("DeleteFirstProdIconFromCartPage").click();
		Thread.sleep(2000);
		getWebElement("ConfirmationRemoveButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
	}
}
