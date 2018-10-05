package inventory;

import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;

public class Disposed_Material_Reactivation extends TestBase
{
	@Test(priority = 1)
	public void DisposedMaterial_Reactivation_Detail_Page() throws Exception
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
		String materialName = ExcelUtils.getCellData(4, 0);
		String materialQuantity = ExcelUtils.getCellData(4, 3);
		int rowNumber = 4;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);;
				
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
		
		//Navigation to disposed material detail page.
		impliciteWait(2);
		InventoryRegularFunctions DisposedMaterialDetailPage = new InventoryRegularFunctions();
		DisposedMaterialDetailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String quantity = ExcelUtils.getCellData(4, 11);
				
		//Re-activation of disposed material
		getWebElement("Inventory.Material.UpdateQuantity").click();
		getWebElement("Inventory.Material.UpdateQuantity").clear();
		getWebElement("Inventory.Material.UpdateQuantity").sendKeys(quantity);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(3000);
		getWebElement("Inventory.OkButton").click();
		impliciteWait(2);
		
		//Verification of success message after reactivation of material.
		String ActualMaterialUpdateSuccessMessage = getWebElement("Inventory.UpdateMaterialDetails.SuccessMessage").getText();
		String ExpectedMaterialUpdateSuccessMessage = "Success! Material updated";
		softAssertion.assertTrue(ActualMaterialUpdateSuccessMessage.equals(ExpectedMaterialUpdateSuccessMessage), "After updation of material, Success Message displayed as"+ActualMaterialUpdateSuccessMessage);
		Reporter.log("After updation of material, Success Message displayed as - "+ActualMaterialUpdateSuccessMessage);
		Utills.captureScreenshot("Dispose_Material_Reactivation_Success_Message_"+TodayDate.Date());
		
		//Verify the count of materials when a material is reactivated.
		int materialsCountAfterReactivation = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Assert.assertTrue("After Re-activation of disposed material, the materials count is not increased.", materialsCountAfterReactivation == (materialsCountAfterDispose+1));
		Reporter.log("After Re-activation of disposed material, the materials count is increased.");
		
		//Verify the reactivated material displayed in material list.
		String reactivatedMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Reactivated material - "+materialName+" not displayed at the top of materials page.",reactivatedMaterialName.equals(materialName));
		Reporter.log("Reactivated material - "+materialName+" displayed at the top of the page.");
	
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void DisposedMaterial_Reactivation_Card_View() throws Exception
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
		String materialName = ExcelUtils.getCellData(37, 0);
		String materialQuantity = ExcelUtils.getCellData(37, 3);
		int rowNumber = 37;
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String updateQuantity = ExcelUtils.getCellData(2, 11);		
				
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
		
		//Updating the disposed material quantity in card view page
		DisposedMaterialsPage.Update_Quantity_Disposed_Material_Page(updateQuantity);
		
		//Take the count materials disposed material
		int materialsCountDisposeListAfterReactivation = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the count increased in disposed materials list
		Assert.assertTrue("After material disposal, the materials count is not decreased in disposed materials list.",(materialsCountDisposeListAfterReactivation == materialsCountDisposeListAfter - 1 ));
		Reporter.log("After reactivation of disposed material, the materials count is decreased by 1 in disposed materials list.");
		
		//Navigation to materials page.
		MaterialsPage.MaterialPageNavigation();
		Thread.sleep(1000);
		
		//Verify the count of materials when a material is reactivated.
		int materialsCountAfterReactivation = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Assert.assertTrue("After Re-activation of disposed material, the materials count is not increased.", materialsCountAfterReactivation == (materialsCountAfterDispose+1));
		Reporter.log("After Re-activation of disposed material, the materials count is increased.");
		
		//Verify the reactivated material displayed in material list.
		String reactivatedMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Reactivated material - "+materialName+" not displayed at the top of materials page.",reactivatedMaterialName.equals(materialName));
		Reporter.log("Reactivated material - "+materialName+" displayed at the top of the page.");
	
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}	
}
