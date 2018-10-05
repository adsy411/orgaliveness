package cart;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ValidateCardPurchaseOrderNumberField_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
    public void ValidateCardPurchaseOrderNumberField() throws Exception {
    	
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
		Thread.sleep(1000);
		explicitWaitForElementUntilClickable("ViewCheckoutbutton");
		getWebElement("ViewCheckoutbutton").click();
		
		Thread.sleep(5000);
		getWebElement("CardRadioButton").click();
	
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(2000);
		getWebElement("PurchaseButton").click();
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,-1000)");

		Thread.sleep(3000);
		String Message = driver.findElement(By.xpath("//*[@id='cardNumberMsg']")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("Card number is required", Message);
		
		Thread.sleep(3000);
		getWebElement("CardNumberField").sendKeys("345612347890123");
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(2000);
		getWebElement("PurchaseButton").click();
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,-1000)");

		Thread.sleep(3000);
		String Message1 = driver.findElement(By.xpath("//*[@id='cvvMsg']")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("CVV is required", Message1);
		
		Thread.sleep(3000);
		getWebElement("CVVNumberField").sendKeys("1234");
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(2000);
		getWebElement("PurchaseButton").click();
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,-1000)");

		Thread.sleep(3000);
		String Message2 = driver.findElement(By.id("cardNameMsg")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("Card name is required", Message2);
		
		Thread.sleep(3000);
		getWebElement("CardNameField").sendKeys("Devtest");
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(2000);
		getWebElement("PurchaseButton").click();
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,-1000)");

		Thread.sleep(3000);
		String Message3 = driver.findElement(By.xpath("//*[@id='cardExpiryMsg']")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("Card expiry is required", Message3);
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(2000);
		getWebElement("PurchaseOrderRadioButton").click();
		
		Thread.sleep(2000);
		getWebElement("PurchaseButton").click();
		
		Thread.sleep(3000);
		String Message4 = driver.findElement(By.id("poNumberMsg")).getText();
		Thread.sleep(3000);
		Assert.assertEquals("Purchase order number is required", Message4);
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,-1000)");
		
		Thread.sleep(2000);
		getWebElement("DeleteFirstProdIconFromCartPage").click();
		Thread.sleep(2000);
		getWebElement("ConfirmationRemoveButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
	}
}
