package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import org.junit.Assert;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Lab_Member_Detail_Page_DisposeMaterial extends TestBase
{
	@Test(priority = 1)
	public void LabMember_MaterialDetail_CompleteMaterialDisposal() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 6);
		String password = ExcelUtils.getCellData(2, 7);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
				
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(5, 0);
		String materialQuantity = ExcelUtils.getCellData(5, 3);
		int rowNumber = 5;
		
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
		
		//Verify Disposal method 
		if(getWebElement("Inventory.DisposalMethodSection").isDisplayed()
					&& getWebElement("Inventory.DisposeRadioButton").isDisplayed()
					&& getWebElement("Inventory.DeleteRadioButton").isDisplayed())	
			Assert.fail("Disposal method displayed for lab member");
		else
				Reporter.log("Success! Disposal method not displayed for lab member");

		//Dispose a material
		if(Double.parseDouble(disposeMaterialQuantity) == Double.parseDouble(materialQuantity))
		{
			getWebElement("Inventory.Detail.DisposeQuantity").click();
			getWebElement("Inventory.Detail.DisposeQuantity").clear();
			getWebElement("Inventory.Detail.DisposeQuantity").sendKeys(disposeMaterialQuantity);
			
			Thread.sleep(1000);
			getWebElement("Inventory.DisposeButton").click();
			impliciteWait(2);
		}
		else
			Assert.fail("Unable to dispose a material completely as dispose quantity is not equal to material quantity");	
				
		//Verification of Success message after dispose of material.
		String ActualDisposeSuccessMessage = getWebElement("Inventory.DisposeSuccessMessage").getText();
		String ExpectedDisposeSuccessMessage = "Success! Material Disposed";
		
		if(ActualDisposeSuccessMessage.equals(ExpectedDisposeSuccessMessage))
			Reporter.log("After material disposal, the Success Message displayed successfully."+ActualDisposeSuccessMessage);
		else 
			softAssertion.fail("After material disposal, the Success Message not displayed."+ActualDisposeSuccessMessage);
		Utills.captureScreenshot("Material_Dispose_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after disposal
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After material disposal, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After material disposal, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
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
	public void LabMember_MaterialDetail_PartialMaterialDisposal() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 6);
		String password = ExcelUtils.getCellData(2, 7);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
				
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(6, 0);
		String materialQuantity = ExcelUtils.getCellData(6, 3);
		int rowNumber = 6;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
			
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String disposeMaterialQuantity = ExcelUtils.getCellData(2, 3);  
		
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
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
		
		//Verify Disposal method 
		if(getWebElement("Inventory.DisposalMethodSection").isDisplayed()
					&& getWebElement("Inventory.DisposeRadioButton").isDisplayed()
					&& getWebElement("Inventory.DeleteRadioButton").isDisplayed())	
			Assert.fail("Disposal method displayed for lab member");
		else
				Reporter.log("Success! Disposal method not displayed for lab member");

		//Dispose a material
		if(Double.parseDouble(disposeMaterialQuantity) < Double.parseDouble(materialQuantity) && Double.parseDouble(disposeMaterialQuantity)>0)
		{
			getWebElement("Inventory.Detail.DisposeQuantity").click();
			getWebElement("Inventory.Detail.DisposeQuantity").clear();
			getWebElement("Inventory.Detail.DisposeQuantity").sendKeys(disposeMaterialQuantity);
			
			Thread.sleep(1000);
			getWebElement("Inventory.DisposeButton").click();
			impliciteWait(2);
		}
		else
			Assert.fail("Unable to dispose a material partially. Dispose quantity should be less than material quantity");	
		
		//Verification of Success message after dispose of material.
		String ActualDisposeSuccessMessage = getWebElement("Inventory.DisposeSuccessMessage").getText();
		String ExpectedDisposeSuccessMessage = "Success! Material Disposed";
				
		if(ActualDisposeSuccessMessage.equals(ExpectedDisposeSuccessMessage))
			Reporter.log("After material disposal, the Success Message displayed successfully."+ActualDisposeSuccessMessage);
		else 
			softAssertion.fail("After material disposal, the Success Message not displayed."+ActualDisposeSuccessMessage);
		Utills.captureScreenshot("Material_Dispose_Success_Message_"+TodayDate.Date());
				
		//Verify materials page exists or not after disposal
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After material disposal, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After material disposal, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
				
		//Verify the materials count when a material is disposed.
		Thread.sleep(1000);
		int materialsCountAfterDispose = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
				
		if(materialsCountAfterDispose == materialsCountBefore)
			Reporter.log("After partial material disposal-"+materialName+" the material count remains same.");
		else 
			softAssertion.fail("After partial dispose of material - "+materialName+" the count of materials varies.");	
		
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
		Assert.assertTrue("After partial disposal of a material - "+materialName+" , the material quantity is not reduced.",disposedMaterialQuantity == (Double.parseDouble(materialQty)));
		Reporter.log("After partial disposal of a material - "+materialName+" the material quantity is reduced to "+materialQty);	
	
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}	
}
