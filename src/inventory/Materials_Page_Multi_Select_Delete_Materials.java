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

public class Materials_Page_Multi_Select_Delete_Materials extends TestBase
{
	@Test
	public void MultiSelectDeleteMaterials() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 65;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		int materialsCount = 3;
		/*//Adding third party Vendor materials
		for(materialsCount=0;materialsCount<3;materialsCount++)
		{		
			AddThirdPartyMaterial AddMaterial1 = new AddThirdPartyMaterial();
			AddMaterial1.AddThirdPartyVendorMaterial(rowNumber);
			Thread.sleep(1000);
		}
		*/
		InventoryRegularFunctions MaterialsPage1 = new InventoryRegularFunctions();
		MaterialsPage1.MaterialPageNavigation();
		
		//Take the count of materials
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to materials page.
		InventoryRegularFunctions MaterialsPage = new InventoryRegularFunctions();
		MaterialsPage.MaterialPageNavigation();
		Thread.sleep(1000);
		
		//Selects multiple materials
		MaterialsPage.Select_Multiple_Materials(materialsCount);
		
		//Verify materials selected
		MaterialsPage.Verify_MultiSelected_Materials(materialsCount);
		
		//Deletion of selected materials
		getWebElement("Inventory.MultipleMaterial.DisposeIcon").click();
		Thread.sleep(3000);
			
		//Verify the dispose modal
		String disposeModalTitle = getWebElement("Inventory.SelectAll.Dispose.ModalName").getText().trim();
		Assert.assertTrue("Dispose modal not displayed",disposeModalTitle.equals("Dispose Material"));
		Reporter.log("Dispose modal displayed.");
		Utills.captureScreenshot("Dispose_Modal_"+TodayDate.Date());
		
		//Verify the radio button text in dispose modal
		String disposeModalDeleteRadioButtonText = getWebElement("Inventory.SelectAll.DisposeModal.DeleteRadioButtonText").getText().trim();
		softAssertion.assertTrue(disposeModalDeleteRadioButtonText.equalsIgnoreCase("Delete"),"Delete Radio button text not displayed");
		
		//Selects the delete radio button
		getWebElement("Inventory.SelectAll.DisposeModal.DeleteRadioButton").click();
		Thread.sleep(1000);
		
		//Clicks on delete Button
		getWebElement("Inventory.SelectAll.DisposeModal.DeleteButton").click();
		Thread.sleep(2000);
		
		//Verify the confirmation modal
		String confirmationModalTitle = getWebElement("Inventory.SelectAll.Verify.ConfirmationModalTitle").getText().trim();
		Assert.assertTrue("Confirmation modal not displayed",confirmationModalTitle.equals("Confirmation"));
		
		//Verify the text in confirmation modal
		String actualConfirmationModalText = getWebElement("Inventory.SelectAll.Verify.ConfirmationModalText").getText().trim();
		String expectedConfirmationModalText = "Are you sure you want to delete the selected materials? There will be no way to recover your materials after deleting.";
		softAssertion.assertTrue(actualConfirmationModalText.equalsIgnoreCase(expectedConfirmationModalText),"Text displayed in confirmation modal is not proper. The text displayed as -"+actualConfirmationModalText);
		Utills.captureScreenshot("Delete_Confirmation_Modal_"+TodayDate.Date());
		
		//Clicks on OK button
		getWebElement("Inventory.OkButton").click();
		Thread.sleep(4000);
		impliciteWait(5);
		
		//Verify the success message
		String ActualDeleteSuccessMessage = getWebElement("Inventory.MaterialsPage.SuccessMessage").getText();
		String ExpectedDeleteSuccessMessage = "Success! Material has been deleted successfully!";
		if(ActualDeleteSuccessMessage.equals(ExpectedDeleteSuccessMessage))
			Reporter.log("After materials deletion, the Success Message displayed successfully."+ActualDeleteSuccessMessage);
		else 
			softAssertion.fail("After material deletion, the Success Message not displayed."+ActualDeleteSuccessMessage);
		Utills.captureScreenshot("Material_Delete_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after deletion
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After material disposal, the materials page not displayed", materialsPageName.equals("Materials"));
		
		///Verify the materials count when a materials are deleted
		int materialsCountAfterDeletion = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		if(materialsCountAfterDeletion == materialsCountBefore - materialsCount)
			Reporter.log(materialsCount+" materials deleted successfully.");
		else 
			softAssertion.fail("After material deletion the material count is not decreased. The count displayed as - "+materialsCountAfterDeletion);
	
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
