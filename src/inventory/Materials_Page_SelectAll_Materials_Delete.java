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

public class Materials_Page_SelectAll_Materials_Delete extends TestBase 
{
	@Test
	public void MaterialsPageSelectAllMaterialDelete() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 63;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Take the count of materials
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to materials page.
		InventoryRegularFunctions MaterialsPage = new InventoryRegularFunctions();
		MaterialsPage.MaterialPageNavigation();
		Thread.sleep(1000);
		
		//Selecting all the materials
		getWebElement("Inventory.MultipleMaterial.SelectIcon").click();
		Thread.sleep(2000);
		Assert.assertTrue("Materials not selected in material card view page",getWebElement("Inventory.MaterialsPage.MultipleMaterial.VerifySelect").isDisplayed());
		Reporter.log("All materials selected successfully in material card view page");
		Utills.captureScreenshot("Material_SelectAll_"+TodayDate.Date());
		
		//Deleting all the materials present in the list
		getWebElement("Inventory.MultipleMaterial.DisposeIcon").click();
		Thread.sleep(2000);
			
		//Verify the dispose modal
		String disposeModalTitle = getWebElement("Inventory.SelectAll.Dispose.ModalName").getText().trim();
		Assert.assertTrue("Dispose modal not displayed",disposeModalTitle.equals("Dispose Material"));
		Reporter.log("Dispose modal displayed.");
		Utills.captureScreenshot("Dispose_Modal_"+TodayDate.Date());
		
		//Verify the radio button text in dispose modal
		String disposeModalDisposeRadioButtonText = getWebElement("Inventory.SelectAll.DisposeModal.DeleteRadioButtonText").getText().trim();
		softAssertion.assertTrue(disposeModalDisposeRadioButtonText.equalsIgnoreCase("Delete"),"Delete Radio button text not displayed");
		
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
		Thread.sleep(1000);
		explicitWaitUntilElementIsInvisible("Inventory.LoadingSymbol");
		Thread.sleep(2000);
		
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
		
		if(materialsCountAfterDeletion == 0)
			Reporter.log(materialsCountBefore+" materials deleted successfully. After materials deletion the material count is "+materialsCountAfterDeletion);
		else 
			softAssertion.fail("After material deletion the material count is not Zero. The count displayed as - "+materialsCountAfterDeletion);
	
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}

}
