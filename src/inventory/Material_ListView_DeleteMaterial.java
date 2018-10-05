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

public class Material_ListView_DeleteMaterial extends TestBase
{
	@Test
	public void ListViewDeleteMaterial() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 23;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(23, 0);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(2000);
		
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to list view
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		Reporter.log("List view displayed successfully in materials page");
		
		//Navigation to dispose modal
		getWebElement("Inventory.ListView.DisposeIcon").click();
		Thread.sleep(3000);
		
		//Verify dispose modal exist or not
		InventoryRegularFunctions verifyDeleteModal = new InventoryRegularFunctions();
		verifyDeleteModal.VerifyDisposeModal_CardView(materialName);
		
		getWebElement("Inventory.CardView.DisposeModal.DeleteRadioButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.DisposeButton").click();
		impliciteWait(2);
			
		//Verification of Success message after deletion of material.
		String ActualMaterialDeletionSuccessMessage = getWebElement("Inventory.DeleteMaterialSuccessMessage").getText();
		String ExpectedMaterialDeletionSuccessMessage = "Material Deleted";
		if(ActualMaterialDeletionSuccessMessage.equals(ExpectedMaterialDeletionSuccessMessage))
			Reporter.log("After material deletion, the Success Message displayed successfully as - "+ActualMaterialDeletionSuccessMessage);
		else 
			softAssertion.fail("After material deletion, the Success Message not displayed.");
		Utills.captureScreenshot("Material_Delete_Success_Message_F_"+TodayDate.Date());
		
		//Verify materials page exists or not after disposal
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After material disposal, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After material disposal, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		int materialsCountAfterDelete = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Assert.assertTrue("After deletion of material - "+materialName+" the materials count is not decreased.",materialsCountAfterDelete == (materialsCountBefore-1));
		Reporter.log("After deletion of material -"+materialName+" the materials count is decreased by "+(materialsCountAfterDelete-materialsCountBefore));		
		
		Thread.sleep(1000);
		getWebElement("Inventory.CardView").click();
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}


