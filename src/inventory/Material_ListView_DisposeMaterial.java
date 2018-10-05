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
	
public class Material_ListView_DisposeMaterial extends TestBase
{
	@Test
	public void ListViewDisposeMaterial() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();

		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 47;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(47, 0);
		String materialQuantity = ExcelUtils.getCellData(47, 3);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
				
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String disposeMaterialQuantity = ExcelUtils.getCellData(2, 0); 
		
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to list view
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		Reporter.log("List view displayed successfully in materials page");
		
		//Navigation to dispose modal
		getWebElement("Inventory.ListView.DisposeIcon").click();
		Thread.sleep(3000);
		
		//Verify dispose modal exist or not
		InventoryRegularFunctions verifyDisposeModal = new InventoryRegularFunctions();
		verifyDisposeModal.VerifyDisposeModal_CardView(materialName);
		
		//Dispose a material
		if(Double.parseDouble(disposeMaterialQuantity) <= Double.parseDouble(materialQuantity)&& Double.parseDouble(disposeMaterialQuantity)>0)
		{
			getWebElement("Inventory.CardView.DisposeQuantity").click();
			getWebElement("Inventory.CardView.DisposeQuantity").clear();
			getWebElement("Inventory.CardView.DisposeQuantity").sendKeys(disposeMaterialQuantity);
			getWebElement("Inventory.CardView.DisposeModal.DisposeRadioButton").click();
			Thread.sleep(1000);
			getWebElement("Inventory.DisposeButton").click();
			impliciteWait(2);
		
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
			
			//Verify the count of materials when a material is disposed.
			int materialsCountAfterDispose = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
			
			if(Double.parseDouble(disposeMaterialQuantity) < Double.parseDouble(materialQuantity))
			{
				if(materialsCountAfterDispose == materialsCountBefore)
					Reporter.log("After dispose of material -"+materialName+" the material count remains same.");
				else 
					Reporter.log("After dispose of material - "+materialName+" the count of materials varies.");			
			}
			if(Double.parseDouble(disposeMaterialQuantity) == Double.parseDouble(materialQuantity))
			{
				if(materialsCountAfterDispose == (materialsCountBefore-1))
					Reporter.log("After dispose of material -"+materialName+" the material count is decreased by 1.");
				else 
					Reporter.log("After dispose of material - "+materialName+" the material count is not decreased.");		
			}
		}
		else if(Double.parseDouble(disposeMaterialQuantity) > Double.parseDouble(materialQuantity))
		{
			getWebElement("Inventory.CardView.DisposeQuantity").click();
			getWebElement("Inventory.CardView.DisposeQuantity").clear();
			getWebElement("Inventory.CardView.DisposeQuantity").sendKeys(disposeMaterialQuantity);
			getWebElement("Inventory.CardView.DisposeModal.DisposeRadioButton").click();
			getWebElement("Inventory.DisposeButton").click();
			Thread.sleep(1000);
			
			String disposeQuantityValidationMessage = getWebElement("Inventory.DisposeMaterial.QuantityValidation").getText();
			Assert.assertTrue("Validation message displayed as - '"+disposeQuantityValidationMessage+"' when dispose quantity greater than material quantity", disposeQuantityValidationMessage.equals("Quantity should be less than the actual quantity"));
			Reporter.log("Validation message displayed successfull for dispose quantity greater than material quantity as - "+disposeQuantityValidationMessage);
			getWebElement("Inventoy.DisposeModal.CancelButton").click();
			Thread.sleep(1000);
		}
		else
			Assert.fail("Dispose quantity is not valid");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
