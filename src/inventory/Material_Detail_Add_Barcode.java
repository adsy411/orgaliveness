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

public class Material_Detail_Add_Barcode extends TestBase
{
	@Test
	public void MaterialDetailAddBarcode() throws Exception
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
						
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(28, 0);
		int rowNumber = 28;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);	
		Thread.sleep(1000);

		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String addBarcode = ExcelUtils.getCellData(3, 6);
		
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
		
		//Adding barcode to a material
		getWebElement("Inventory.Detail.AddBarcode").click();
		getWebElement("Inventory.Detail.AddBarcode").sendKeys(addBarcode);
		getWebElement("Inventory.Detail.AddBarcodeButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.AddBarcodeOkButton").click();
		impliciteWait(2);		
			
		//Verification of Success message after adding barcode to a material.
		String ActualBarcodeSuccessMessage = getWebElement("Inventory.AddBacodeSuccessMessage").getText();
		String ExpectedBarcodeSuccessMessage = "Material Custom Barcode updated successfully";
		if(ActualBarcodeSuccessMessage.equals(ExpectedBarcodeSuccessMessage))
			Reporter.log("After adding barcode to a material, the Success Message displayed successfully as - "+ActualBarcodeSuccessMessage+" in material detail page");	
		else
			softAssertion.fail("After adding barcode to a material, the Success Message not displayed in material detail page."+ActualBarcodeSuccessMessage);
		Utills.captureScreenshot("Material_Barcode_Success_Message_F_"+TodayDate.Date());
		
		//Verify material detail page is displayed or not after addition of barcode 
		String PageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of barcode, User not redirected to Material detail page of material -"+materialName,PageName.equals(materialName));
		Reporter.log("After addition of barcode, User redirected to Material detail page of material -"+materialName);
		Utills.captureScreenshot("Detail_Page_"+TodayDate.Date());
		
		Thread.sleep(2000);
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}
}
