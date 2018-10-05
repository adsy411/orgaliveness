package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Material_ListView_AddBarcode extends TestBase
{
	@Test
	public void ListViewAddBarcode() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();

		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 46;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(2000);
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String addBarcode = ExcelUtils.getCellData(4, 6);

		//Navigation to list page
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		getWebElement("Inventory.ListView.BarcodeIcon").click();
		Thread.sleep(2000);
		
		//Verify barcode modal exist or not
		String barcodeModalTitle = getWebElement("Inventory.BarcodeModalTitle").getText();
		Assert.assertTrue("Barcode modal not displayed", barcodeModalTitle.equals("Add or View existing Barcode"));
		Reporter.log("Barcode modal displayed successfully.");
		Utills.captureScreenshot("Material_Barcode_Modal_"+TodayDate.Date());
		
		// Adding barcode to a material
		getWebElement("Inventory.CardView.AddBarcode").click();
		getWebElement("Inventory.CardView.AddBarcode").sendKeys(addBarcode);
		getWebElement("Inventory.Detail.AddBarcodeButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.AddBarcodeOkButton").click();
		impliciteWait(2);
			
		//Verification of Success message after adding barcode to a material.
		String ActualBarcodeSuccessMessage = getWebElement("Inventory.AddBacodeSuccessMessage").getText();
		String ExpectedBarcodeSuccessMessage = "Success! Material Custom Barcode updated";
		if(ActualBarcodeSuccessMessage.equals(ExpectedBarcodeSuccessMessage))
			Reporter.log("After adding barcode to a material, the Success Message displayed successfully as - "+ActualBarcodeSuccessMessage+" in material card view page");
		else 
			softAssertion.fail("After adding barcode to a material, the message displayed in material card View Page as - "+ActualBarcodeSuccessMessage);
		Utills.captureScreenshot("Material_Barcode_Success_Message_"+TodayDate.Date());
		
		Thread.sleep(1000);
		getWebElement("Inventory.CardView").click();
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
