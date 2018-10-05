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

public class Materials_Page_Multi_Select_Dispose_Materials extends TestBase 
{
	@Test
	public void MultiSelectDisposeMaterials() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 64;
		
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
		
		//Navigation to Disposed materials page and take the count of disposed materials.
		InventoryRegularFunctions DisposedMaterialsPage = new InventoryRegularFunctions();
		DisposedMaterialsPage.DisposedMaterialsPageNavigation();
		
		//Take the count materials of already disposed material
		int materialsCountDisposeListBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to materials page.
		InventoryRegularFunctions MaterialsPage = new InventoryRegularFunctions();
		MaterialsPage.MaterialPageNavigation();
		Thread.sleep(1000);
		
		//Selects multiple materials
		MaterialsPage.Select_Multiple_Materials(materialsCount);
		
		//Verify materials selected
		MaterialsPage.Verify_MultiSelected_Materials(materialsCount);
		
		//Disposing the selected materials
		getWebElement("Inventory.MultipleMaterial.DisposeIcon").click();
		Thread.sleep(3000);
			
		//Verify the dispose modal
		String disposeModalTitle = getWebElement("Inventory.SelectAll.Dispose.ModalName").getText().trim();
		Assert.assertTrue("Dispose modal not displayed",disposeModalTitle.equals("Dispose Material"));
		Reporter.log("Dispose modal displayed.");
		Utills.captureScreenshot("Dispose_Modal_"+TodayDate.Date());
		
		//Verify the radio button text in dispose modal
		String disposeModalDisposeRadioButtonText = getWebElement("Inventory.SelectAll.DisposeModal.DisposeRadioButtonText").getText().trim();
		softAssertion.assertTrue(disposeModalDisposeRadioButtonText.equalsIgnoreCase("Dispose"),"Dispose Radio button text not displayed");
		
		//Selects the dispose radio button
		getWebElement("Inventory.SelectAll.DisposeModal.DisposeRadioButton").click();
		Thread.sleep(1000);
		
		//Clicks on Dispose Button
		getWebElement("Inventory.SelectAll.DisposeModal.DisposeButton").click();
		Thread.sleep(2000);
		
		//Verify the confirmation modal
		String confirmationModalTitle = getWebElement("Inventory.SelectAll.Verify.ConfirmationModalTitle").getText().trim();
		Assert.assertTrue("Confirmation modal not displayed",confirmationModalTitle.equals("Confirmation"));
		
		//Verify the text in confirmation modal
		String actualConfirmationModalText = getWebElement("Inventory.SelectAll.Verify.ConfirmationModalText").getText().trim();
		String expectedConfirmationModalText = "Are you sure you want to dispose the selected materials? Disposed materials can be viewed using the Material List filters";
		softAssertion.assertTrue(actualConfirmationModalText.equalsIgnoreCase(expectedConfirmationModalText),"Text displayed in confirmation modal is not proper. The text displayed as -"+actualConfirmationModalText);
		Utills.captureScreenshot("Dispose_Confirmation_Modal_"+TodayDate.Date());
		
		//Clicks on OK button
		getWebElement("Inventory.OkButton").click();
		Thread.sleep(4000);
		impliciteWait(5);
		
		//Verify the success message
		String ActualDisposeSuccessMessage = getWebElement("Inventory.MaterialsPage.SuccessMessage").getText();
		String ExpectedDisposeSuccessMessage = "Warning! Material has been disposed successfully!";
		if(ActualDisposeSuccessMessage.equals(ExpectedDisposeSuccessMessage))
			Reporter.log("After materials disposal, the Success Message displayed successfully."+ActualDisposeSuccessMessage);
		else 
			softAssertion.fail("After material disposal, the Success Message not displayed."+ActualDisposeSuccessMessage);
		Utills.captureScreenshot("Material_Dispose_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after disposal
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After material disposal, the materials page not displayed", materialsPageName.equals("Materials"));
		
		//Verify the materials count when a material is disposed.
		int materialsCountAfterDispose = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		if(materialsCountAfterDispose == materialsCountBefore - materialsCount)
			System.out.println("After material disposal the material count is decreased.");
		else 
			softAssertion.fail("After material disposal the material count is not decreased. The count displayed as - "+materialsCountAfterDispose);
	
		//Navigation to Disposed materials page.
		DisposedMaterialsPage.DisposedMaterialsPageNavigation();
		Thread.sleep(2000);
		
		//Take the count materials of disposed material
		int materialsCountDisposeListAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the count increased in disposed materials list
		Assert.assertTrue("After material disposal, the materials count is not increased in disposed materials list.",(materialsCountDisposeListAfter == materialsCountDisposeListBefore + materialsCount));
		Reporter.log(materialsCount+" materials disposed successfully and displayed in disposed materials list.");
		Utills.captureScreenshot("Disposed_Materials_Page_After_Disposal_"+TodayDate.Date());
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
