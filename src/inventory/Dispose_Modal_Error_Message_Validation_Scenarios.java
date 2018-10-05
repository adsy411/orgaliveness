package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Dispose_Modal_Error_Message_Validation_Scenarios extends TestBase
{
	@Test(priority = 1)
	public void Card_View_DisposeModal_Error_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(33, 0);
		int rowNumber = 33;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Validation_Scenario");
		String disposeMaterialQuantity = ExcelUtils.getCellData(25, 0); 
					
		//Navigation to dispose modal from card view page
		getWebElement("Inventory.CardView.DisposeMaterial").click();
		Thread.sleep(3000);
		
		//Verify dispose modal exist or not
		InventoryRegularFunctions verifyDisposeModal = new InventoryRegularFunctions();
		verifyDisposeModal.VerifyDisposeModal_CardView(materialName);
		
		getWebElement("Inventory.CardView.DisposeQuantity").click();
		getWebElement("Inventory.CardView.DisposeQuantity").clear();
		getWebElement("Inventory.CardView.DisposeQuantity").sendKeys(disposeMaterialQuantity);
		getWebElement("Inventory.CardView.DisposeModal.DisposeRadioButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.DisposeButton").click();
		Thread.sleep(1000);
		
		//Verification of validation message when dispose quantity is greater than material quantity.
		Utills.captureScreenshot("Card_View_Dispose_Modal_Dispose_Qty_Validation_Message_"+TodayDate.Date());
		String expectedCardViewDisposeQuantityValidationMsg = "Quantity should be less than the actual quantity";
		String actualCardViewDisposeQuantityValidationMsg = getWebElement("Inventory.DisposeMaterial.QuantityValidation").getText();
		if(expectedCardViewDisposeQuantityValidationMsg.equalsIgnoreCase(actualCardViewDisposeQuantityValidationMsg))
			Reporter.log("Card View - Dispose Modal - Validation message displayed successfully for dispose quantity field when dispose quantity greater than material quantity.");
		else
			softAssertion.fail("Card View - Dispose Modal -  Validation message not displayed for dispose quantity field when dispose quantity greater than material quantity. Expected message is - "+expectedCardViewDisposeQuantityValidationMsg+". The actual message displayed as  - "+actualCardViewDisposeQuantityValidationMsg);
		
		//Closing the dispose modal
		getWebElement("Inventoy.DisposeModal.CancelButton").click();
		Thread.sleep(2000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Material_Detail_DisposeModal_Error_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(34, 0);
		int rowNumber = 34;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Validation_Scenario");
		String disposeMaterialQuantity = ExcelUtils.getCellData(25, 0); 
		
		//Navigation to material detail page.
		impliciteWait(2);
		InventoryRegularFunctions MaterialDetailPage = new InventoryRegularFunctions();
		MaterialDetailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Verify dispose modal exist or not
		getWebElement("Inventory.Detail.Dispose").click();
		Thread.sleep(3000);
		InventoryRegularFunctions verifyDisposeModal = new InventoryRegularFunctions();
		verifyDisposeModal.VerifyDisposeModal_DetailPage(materialName);
		
		//Dispose a material with quantity greater than material quantity
		getWebElement("Inventory.Detail.DisposeQuantity").click();
		getWebElement("Inventory.Detail.DisposeQuantity").clear();
		getWebElement("Inventory.Detail.DisposeQuantity").sendKeys(disposeMaterialQuantity);
		getWebElement("Inventory.DisposeRadioButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.DisposeButton").click();
		Thread.sleep(1000);
		
		//Verification of validation message when dispose quantity is greater than material quantity.
		Utills.captureScreenshot("Detail_Page_Dispose_Modal_Dispose_Qty_Validation_Message_"+TodayDate.Date());
		String expectedMaterialDetailDisposeQuantityValidationMsg = "Quantity should be less than the actual quantity!";
		String actualMaterialDetailDisposeQuantityValidationMsg = getWebElement("Inventory.DetailPage.DisposeModal.DisposeQty.ValidationMessage").getText();
		if(expectedMaterialDetailDisposeQuantityValidationMsg.equalsIgnoreCase(actualMaterialDetailDisposeQuantityValidationMsg))
			Reporter.log("Card View - Dispose Modal - Validation message displayed successfully for dispose quantity field when dispose quantity greater than material quantity.");
		else
			softAssertion.fail("Card View - Dispose Modal -  Validation message not displayed for dispose quantity field when dispose quantity greater than material quantity. Expected message is - "+expectedMaterialDetailDisposeQuantityValidationMsg+". The actual message displayed as  - "+actualMaterialDetailDisposeQuantityValidationMsg);
		
		//Closing the dispose modal
		getWebElement("Inventory.DetailPage.DisposeModal.CloseIcon").click();
		Thread.sleep(2000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}
}
