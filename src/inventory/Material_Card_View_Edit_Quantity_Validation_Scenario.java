package inventory;

import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Material_Card_View_Edit_Quantity_Validation_Scenario extends TestBase
{
	@Test(priority = 1)
	public void Material_CardView_Edit_Quantity_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 42;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);	
		Thread.sleep(1000);
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Validation_Scenario");
		String invalidQuantity = ExcelUtils.getCellData(16, 0);
		
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(invalidQuantity);
		getWebElement("Inventory.AddCatalogNumber").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(2000);
		
		//Updating the material quantity
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		getWebElement("Inventory.CardView.EditQuantity").click();
		getWebElement("Inventory.CardView.EditQuantity").clear();
		getWebElement("Inventory.CardView.EditQuantity").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		Thread.sleep(1000);
		getWebElement("Inventory.CardView.EditQuantity.OkButton").click();
		Thread.sleep(3000);
		
		String expectedInvalidQtyValidationMsg = "Warning! Quantity should be a numeric value.";
		String actualInvalidQtyValidationMsg =  getWebElement("Inventory.CardView.EditQuantity.SuccessMessage").getText();
		Utills.captureScreenshot("Material_Quantity_Edit_Validation_Message_"+TodayDate.Date());
		if(expectedInvalidQtyValidationMsg.equalsIgnoreCase(actualInvalidQtyValidationMsg))
			Reporter.log("Validation message displayed successfully for edit quantity field when it contains invalid value.");
		else
			softAssertion.fail("Validation message not displayed for edit quantity field when it contains invalid value. The message displayed as  - "+actualInvalidQtyValidationMsg);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
