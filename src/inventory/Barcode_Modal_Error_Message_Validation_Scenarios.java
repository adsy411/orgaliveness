package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Barcode_Modal_Error_Message_Validation_Scenarios extends TestBase
{
	@Test(priority = 1)
	public void Card_View_BarcodeModal_Error_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 31;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to barcode modal from card view page
		getWebElement("Inventory.CardView.BarcodeIcon").click();
		Thread.sleep(5000);
		
		//Verification of barcode modal
		InventoryRegularFunctions barcode = new InventoryRegularFunctions();
		barcode.VerifyBarcodeModal_CardView();
	
		//Verify the error message in barcode modal of card view page.
		getWebElement("Inventory.Detail.AddBarcodeButton").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("Card_View_Barcode_Modal_Barcode_Value_Validation_Message_"+TodayDate.Date());
		String expectedCardViewBarcodeNameValidationMsg = "Add new barcode input field is blank.Please provide a valid Input.";
		String actualCardViewBarcodeNameValidationMsg = getWebElement("Inventory.CardView.BarcodeModal.BarcodeName.ValidationMessage").getText();
		if(expectedCardViewBarcodeNameValidationMsg.equalsIgnoreCase(actualCardViewBarcodeNameValidationMsg))
			Reporter.log("Card View - Barcode Modal - Validation message displayed successfully for Barcode name field when it contains null value.");
		else
			softAssertion.fail("Card View - Barcode Modal -  Validation message not displayed when Barcode name contains null value. Expected message is - "+expectedCardViewBarcodeNameValidationMsg+". The actual message displayed as  - "+actualCardViewBarcodeNameValidationMsg);
		
		//Closing the barcode modal
		getWebElement("Inventory.CardView.BarcodeModal.CloseIcon").click();
		Thread.sleep(2000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Material_Detail_BarcodeModal_Error_Message_Validation_Scenario() throws Exception
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
		String materialName = ExcelUtils.getCellData(32, 0);
		int rowNumber = 32;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to material detail page.
		impliciteWait(2);
		InventoryRegularFunctions MaterialDetailPage = new InventoryRegularFunctions();
		MaterialDetailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Navigation to barcode modal
		getWebElement("Inventory.Detail.BarcodeIcon").click();
		Thread.sleep(2000);
		
		//Verification of barcode modal
		InventoryRegularFunctions barcode = new InventoryRegularFunctions();
		barcode.VerifyBarcodeModal_DetailPage();
		
		//Verify the error message in barcode modal from material detail page.
		getWebElement("Inventory.Detail.AddBarcodeButton").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("Material_detail_Barcode_Modal_Barcode_Value_Validation_Message_"+TodayDate.Date());
		String expectedDetailPageBarcodeNameValidationMsg = "Barcode should not be empty";
		String actualDetailPageBarcodeNameValidationMsg = getWebElement("Inventory.DetailPage.BarcodeModal.BarcodeName.ValidationMessage").getText();
		if(expectedDetailPageBarcodeNameValidationMsg.equalsIgnoreCase(actualDetailPageBarcodeNameValidationMsg))
			Reporter.log("Material Detail - Barcode Modal - Validation message displayed successfully for Barcode name field when it contains null value.");
		else
			softAssertion.fail("Material Detail - Barcode Modal -  Validation message not displayed when Barcode name contains null value. Expected message is - "+expectedDetailPageBarcodeNameValidationMsg+". The actual message displayed as  - "+actualDetailPageBarcodeNameValidationMsg);
		
		//Closing the barcode modal
		getWebElement("Inventory.DetailPage.BarcodeModal.CloseIcon").click();
		Thread.sleep(2000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
		
	}
}
