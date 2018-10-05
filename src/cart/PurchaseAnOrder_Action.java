package cart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class PurchaseAnOrder_Action extends TestBase {
	
	@Test
    public void PurchaseAnOrder() throws Exception {
    	
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
		getWebElement("CartSearch").sendKeys(Keys.ARROW_UP);
		Thread.sleep(1000);
		getWebElement("CartSearch").sendKeys(Keys.ENTER);
		
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Thread.sleep(5000);
		explicitWaitForElementUntilClickable("AddProductToCart1");
		getWebElement("AddProductToCart1").click();
		Thread.sleep(2000);
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		getWebElement("HeaderCartIcon").click();
		Thread.sleep(1000);
		explicitWaitForElementUntilClickable("ViewCheckoutbutton");
		getWebElement("ViewCheckoutbutton").click();
		
		Thread.sleep(10000);
		String ProdName = driver.findElement(By.xpath("//*[@id='reviewcartform']/div/div/div[2]/div/div/div[1]/div/div[1]/p/a")).getText();
		
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(2000);
		getWebElement("PurchaseOrderNumberField").sendKeys("4567890");
		Thread.sleep(2000);
		getWebElement("PurchaseButton").click();
		
		Thread.sleep(8000);
		getWebElement("GoToOrdersLink").click();
		
		Thread.sleep(2000);
		Assert.assertTrue(driver.getPageSource().contains(ProdName));
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
	}
}
