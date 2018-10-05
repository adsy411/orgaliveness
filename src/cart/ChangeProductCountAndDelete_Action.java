package cart;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ChangeProductCountAndDelete_Action extends TestBase {
	
	@Test
    public void ChangeProductCountAndDelete() throws Exception {
    	
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
		//getWebElement("CartpopupCountField").clear();
		Thread.sleep(2000);
		getWebElement("CartpopupCountField1").click();
		getWebElement("CartpopupCountField1").sendKeys("1");
		Thread.sleep(2000);
		getWebElement("PopoutCounttext").click();
		//driver.navigate().refresh();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		getWebElement("HeaderCartIcon").click();
		Thread.sleep(1000);
		explicitWaitForElementUntilClickable("ViewCheckoutbutton");
		getWebElement("ViewCheckoutbutton").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		getWebElement("CartpageCounteditIcon").click();
		Thread.sleep(1000);
		getWebElement("CartpageCounteditField").click();
		Thread.sleep(1000);
		getWebElement("CartpageCounteditField").sendKeys(Keys.NUMPAD0);
		Thread.sleep(1000);
		getWebElement("CartpageCounteditFieldTickButton").click();
		
		Thread.sleep(10000);
		getWebElement("DeleteFirstProdIconFromCartPage").click();
		Thread.sleep(2000);
		getWebElement("ConfirmationRemoveButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
		
		
	}

}
