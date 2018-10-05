package cart;

import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class RemoveProductFromCartPopup_Action extends TestBase {
	
	@Test
    public void RemoveProductFromCartPopup() throws Exception {
    	
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
		explicitWaitForElementUntilClickable("DeleteIconFromCartPopup");
		getWebElement("DeleteIconFromCartPopup").click();
		Thread.sleep(5000);
		getWebElement("ConfirmationYesButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
		
		
	}
}
