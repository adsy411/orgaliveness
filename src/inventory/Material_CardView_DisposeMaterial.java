package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;
import pageLibrary.Library;

public class Material_CardView_DisposeMaterial extends TestBase
{
	@Test(priority = 1)
	public void CardViewCompleteMaterialDisposal() throws Exception
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
		String materialName = ExcelUtils.getCellData(12, 0);
		String materialQuantity = ExcelUtils.getCellData(12, 3);
		int rowNumber = 12;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
				
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String disposeMaterialQuantity = ExcelUtils.getCellData(2, 0); 
		
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to Disposed materials page and take the count of disposed materials.
		InventoryRegularFunctions DisposedMaterialsPage = new InventoryRegularFunctions();
		DisposedMaterialsPage.DisposedMaterialsPageNavigation();
		
		//Take the count materials of already disposed material
		int materialsCountDisposeListBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to materials page.
		InventoryRegularFunctions MaterialsPage = new InventoryRegularFunctions();
		MaterialsPage.MaterialPageNavigation();
		Thread.sleep(1000);
				
		//Verify dispose material quantity is equal to material quantity
		if(Double.parseDouble(disposeMaterialQuantity) == Double.parseDouble(materialQuantity))
		{
			//Navigation to dispose modal
			InventoryRegularFunctions CardViewDisposeMaterial = new InventoryRegularFunctions();
			CardViewDisposeMaterial.CardViewDisposeMaterial(disposeMaterialQuantity,materialQuantity,materialName);
		}
		else
			Assert.fail("Unable to dispose a material completely as dispose quantity is not equal to material quantity");
		
		//Verify the materials count when a material is disposed.
		Thread.sleep(1000);
		int materialsCountAfterDispose = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		if(materialsCountAfterDispose == (materialsCountBefore-1))
			Reporter.log("After material disposal -"+materialName+" the material count is decreased by 1.");
		else 
			Assert.fail("After material disposal - "+materialName+" the material count is not decreased.");
	
		//Navigation to Disposed materials page.
		DisposedMaterialsPage.DisposedMaterialsPageNavigation();
		
		//Take the count materials of disposed material
		int materialsCountDisposeListAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the count increased in disposed materials list
		Assert.assertTrue("After material disposal, the materials count is not increased in disposed materials list.",(materialsCountDisposeListAfter == materialsCountDisposeListBefore + 1 ));
		Reporter.log("After material disposal, the materials count is increased in disposed materials list.");
		Utills.captureScreenshot("Disposed_Materials_Page_After_Disposal_"+TodayDate.Date());
		
		//Verify the material in disposed material Page
		DisposedMaterialsPage.VerifyDisposedMaterial(materialName);
			
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void CardView_ValidationScenario_DisposeMaterial() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
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
		String materialName = ExcelUtils.getCellData(13, 0);
		String materialQuantity = ExcelUtils.getCellData(13, 3);
		int rowNumber = 13;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
				
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String disposeMaterialQuantity = ExcelUtils.getCellData(2, 8); 
		
		//Verify dispose material quantity is greater to material quantity
		if(Double.parseDouble(disposeMaterialQuantity) > Double.parseDouble(materialQuantity) && Double.parseDouble(disposeMaterialQuantity)>0)
		{
			//Navigation to dispose modal
			InventoryRegularFunctions CardViewDisposeMaterial = new InventoryRegularFunctions();
			CardViewDisposeMaterial.CardViewDisposeMaterial(disposeMaterialQuantity,materialQuantity,materialName);
		}
		else
			Assert.fail("Dispose quantity - "+disposeMaterialQuantity+" is not valid to test validation scenario");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void CardViewPartialMaterialDisposal() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
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
		String materialName = ExcelUtils.getCellData(14, 0);
		String materialQuantity = ExcelUtils.getCellData(14, 3);
		int rowNumber = 14;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
				
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String disposeMaterialQuantity = ExcelUtils.getCellData(2, 3); 
		
		//Verify dispose modal exist or not
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		if(Double.parseDouble(disposeMaterialQuantity) < Double.parseDouble(materialQuantity) && Double.parseDouble(disposeMaterialQuantity)>0)
		{
			InventoryRegularFunctions CardViewDisposeMaterial = new InventoryRegularFunctions();
			CardViewDisposeMaterial.CardViewDisposeMaterial(disposeMaterialQuantity,materialQuantity,materialName);
		}
		else
			Assert.fail("Unable to dispose a material partially for Dispose quantity - "+disposeMaterialQuantity);	
		
		//Verify the materials count when a material is disposed.
		int materialsCountAfterDispose = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(materialsCountAfterDispose == materialsCountBefore)
			Reporter.log("After partial material disposal -"+materialName+" the material count remains same.");
		else 
			softAssertion.fail("After partial material disposal - "+materialName+" the material count Varies.");		
		
		//Verify the disposed material in material list.
		double disposedMaterialQuantity = (Double.parseDouble(materialQuantity)) - (Double.parseDouble(disposeMaterialQuantity));
		String materialListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
			
		//Verify the disposed material displayed at the top of the page.
		Assert.assertTrue("Partially Disposed material not displayed at the top of the materials page.",materialListMaterialName.equals(materialName));
		Reporter.log("Partially Disposed material displayed at the top of the materials page");
				
		//Navigation to material detail page to take the material quantity.
		InventoryRegularFunctions MaterialDetailPageNavigation = new InventoryRegularFunctions();
		MaterialDetailPageNavigation.MaterialDetailPageNavigation(materialName);
				
		//Verify whether the material quantity is reduced after disposal.
		String materialQty = getWebElement("Inventory.Material.UpdateQuantity").getAttribute("value");
		System.out.println(materialQty);
		boolean expectedMaterialQuantity = disposedMaterialQuantity == (Double.parseDouble(materialQty));
		System.out.println(expectedMaterialQuantity);
		Assert.assertTrue("After partial disposal of a material - "+materialName+" , the material quantity is not reduced.",expectedMaterialQuantity);
		Reporter.log("After partial disposal of a material - "+materialName+" the material quantity is reduced to "+materialQty);	
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}		
}
