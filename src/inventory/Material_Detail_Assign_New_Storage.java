package inventory;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import utills.ExcelUtils;
import utills.InventoryConstants;

import org.junit.Assert;
import org.testng.Reporter;
import testBase.TestBase;
import utills.Utills;

public class Material_Detail_Assign_New_Storage extends TestBase
{
	@Test(priority = 1)
	public void MaterialDetail_Assign_New_Location() throws Exception
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
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(29, 0);
		String locationName = ExcelUtils.getCellData(29, 5);
		String temperature = "35";
		int rowNumber = 29;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to material detail page. Verify material detail page exists or not.
		InventoryRegularFunctions detailPage = new InventoryRegularFunctions();
		detailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Navigation to storage modal
		getWebElement("Inventory.MaterialDetail.Location").click();
		Thread.sleep(3000);

		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify storage exits
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
		Assert.assertTrue("Unable to create a location - "+locationName+", as the location already exist in a storage list",(locationFound == false));
		
		//Creation of new location.
		StorageRegularFunctions addLocation = new StorageRegularFunctions();
		addLocation.CreateLocation(locationName, temperature);
		
		//Selecting the newly added location
		StorageRegularFunctions selectLocation = new StorageRegularFunctions();
		selectLocation.SelectLocation(locationName);
		Thread.sleep(1000);
		
		//Updating material location
		getWebElement("Storage.SaveButton").click();
		Thread.sleep(4000);
		
		//Verify the location displayed for location field in material detail page
		String selectedLocationName = getWebElement("Inventory.MaterialDetail.Location").getAttribute("value");
		Assert.assertTrue("Location value not displayed in Material detail page.",selectedLocationName.equals(locationName));
		Reporter.log("Location displayed in material detail page.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(5000);
		
		getWebElement("Inventory.OkButton").click();
		Thread.sleep(2000);
		
		//Verification of success message after updating material details.
		String updatematerialSuccessMessage = getWebElement("Inventory.UpdateMaterialDetails.SuccessMessage").getText();
		if(updatematerialSuccessMessage.equals("Success! Material updated"))
			Reporter.log("The success message displayed successfully as - '"+updatematerialSuccessMessage+"' after updating material details.");
		else
			softAssertion.fail("The success message not displayed after updating material details");
		Utills.captureScreenshot("Update_Material_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after updation of material details.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After updation of material details, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After updation of material details, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Updated material - "+materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equals(materialName));
		Reporter.log("Updated material  - "+materialName+" displayed at the top of the page.");
		
		//Verify location assigned to a material.
		Thread.sleep(2000);
		String assignedLocation = getWebElement("Inventory.CardView.LocationName").getText();
		Assert.assertTrue("Location - "+locationName+" not assigned to a material",assignedLocation.equals(locationName));
		Reporter.log("Location - "+locationName+" assigned successfully to a material - "+materialName);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();		
	}
}
