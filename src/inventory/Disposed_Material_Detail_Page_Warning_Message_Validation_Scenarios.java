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

public class Disposed_Material_Detail_Page_Warning_Message_Validation_Scenarios extends TestBase
{
	@Test(priority = 1)
	public void Dispose_Warning_Message_DisposedMaterial_Detail_Page() throws Exception
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
		String materialName = ExcelUtils.getCellData(38, 0);
		String materialQuantity = ExcelUtils.getCellData(38, 3);
		int rowNumber = 38;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
				
		int materialsCountBeforeDisposal = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to Disposed materials page and take the count of disposed materials.
		InventoryRegularFunctions DisposedMaterialsPage = new InventoryRegularFunctions();
		DisposedMaterialsPage.DisposedMaterialsPageNavigation();
		
		//Take the count materials of already disposed material
		int materialsCountDisposeListBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to materials page.
		InventoryRegularFunctions MaterialsPage = new InventoryRegularFunctions();
		MaterialsPage.MaterialPageNavigation();
		Thread.sleep(1000);
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String disposeMaterialQuantity = ExcelUtils.getCellData(2, 0); 
		
		//Dispose a material only when dispose quantity is equal to material quantity
		if(Double.parseDouble(disposeMaterialQuantity) == Double.parseDouble(materialQuantity))
		{
			InventoryRegularFunctions CardViewDisposeMaterial = new InventoryRegularFunctions();
			CardViewDisposeMaterial.CardViewDisposeMaterial(disposeMaterialQuantity,materialQuantity,materialName);
		}
		else
			Assert.fail("To reactivate a dispose material, Dispose material quantity should be equal to material quantity.");
		
		//Verify the count of materials when a material is disposed.
		int materialsCountAfterDispose = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(materialsCountAfterDispose == (materialsCountBeforeDisposal-1))
			Reporter.log("After dispose of material -"+materialName+" the materials count is decreased by 1.");
		else 
			Assert.fail("After dispose of material - "+materialName+" the materials count is not decreased.");	
		
		//Navigation to Disposed materials page.
		DisposedMaterialsPage.DisposedMaterialsPageNavigation();
		
		//Take the count materials disposed material
		int materialsCountDisposeListAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the count increased in disposed materials list
		Assert.assertTrue("After material disposal, the materials count is not increased in disposed materials list.",(materialsCountDisposeListAfter == materialsCountDisposeListBefore + 1 ));
		Reporter.log("After material disposal, the materials count is increased in disposed materials list.");
		Utills.captureScreenshot("Disposed_Materials_Page_After_Disposal_"+TodayDate.Date());
		
		//Verify the material in disposed material Page
		DisposedMaterialsPage.VerifyDisposedMaterial(materialName);
		Thread.sleep(2000);
		
		//Navigation to disposed material detail page.
		impliciteWait(2);
		InventoryRegularFunctions DisposedMaterialDetailPage = new InventoryRegularFunctions();
		DisposedMaterialDetailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
			
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(3000);
		getWebElement("Inventory.OkButton").click();
		Thread.sleep(2000);
		
		//Verification of Warning message in list view when Third party vendor material does not contains SDS file.
		Utills.captureScreenshot("Detail_Page_Dispose_Disposed_Material_Warning_Message_"+TodayDate.Date());
		String expectedDetailPageDisposeWarningMsg = "Warning! Material Already Disposed";
		String actualDetailPageDisposeWarningValidationMsg = getWebElement("Inventory.DetailPage.WarningMessage").getText();
		if(expectedDetailPageDisposeWarningMsg.equalsIgnoreCase(actualDetailPageDisposeWarningValidationMsg))
			Reporter.log("Detail Page - Warning message displayed successfully when user tried to dispose an disposed material.");
		else
			softAssertion.fail("Detail Page - Warning message not displayed when user tried to dispose an disposed material. Expected warning message is - "+expectedDetailPageDisposeWarningMsg+". The actual message displayed as  - "+actualDetailPageDisposeWarningValidationMsg);
	
		//Close the warning message
		getWebElement("Inventory.CardView.WarningMessage.CloseIcon").click();
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}		
}
