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

public class Material_Card_View_Assign_New_Location extends TestBase
{
	@Test(priority = 1)
	public void Material_Card_View_Page_Assign_New_Location() throws Exception
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
		String materialName = ExcelUtils.getCellData(39, 0);
		String locationName = ExcelUtils.getCellData(39, 5);
		String temperature = "35";
		int rowNumber = 39;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(2000);
		
		//Navigation to storage modal
		getWebElement("Inventory.CardView.LocationIcon").click();
		Thread.sleep(5000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
			
		//Verify storage exits
		boolean locationFound = verifyStorage.VerifyLocation_MaterialsPage(locationName);
			
		Assert.assertTrue("Unable to create location as the location already exist in a list",(locationFound == false));
		
		//Adding new location
		StorageRegularFunctions addLocation = new StorageRegularFunctions();
		addLocation.CreateLocation_MaterialsPage(locationName, temperature);
		
		//Selecting the newly added location
		StorageRegularFunctions selectLocation = new StorageRegularFunctions();
		selectLocation.SelectLocation_MaterialsPage(locationName);
		
		//Updating material location
		Thread.sleep(1000);
		getWebElement("Storage.SaveButton.MaterialsPage").click();
		Thread.sleep(3000);
		
		//Verification of success message after assigning location to material
		String expectedAssignLocationSuccessMessage = "Success! Material location updated!";
		String actualAssignLocationSuccessMessage = getWebElement("Inventory.MaterialsPage.SuccessMessage").getText();
		if(actualAssignLocationSuccessMessage.equalsIgnoreCase(expectedAssignLocationSuccessMessage))
			Reporter.log("The success message displayed successfully as - '"+actualAssignLocationSuccessMessage+"' after assigning location to a material from card view page.");
		else
			softAssertion.fail("The success message not displayed after assigning location to a material from card view page. Expected message is - "+expectedAssignLocationSuccessMessage+". Actual Message displayed is - "+actualAssignLocationSuccessMessage);
		
		Utills.captureScreenshot("Assign_Location_Success_Message_"+TodayDate.Date());	
		
		//Verify material assigned to location 
		Thread.sleep(3000);
		String assignedLocation = getWebElement("Inventory.CardView.LocationName").getText();
		if(assignedLocation.equals(locationName))
			Reporter.log("Card View - Location '"+locationName+"' assigned successfully to a material - "+materialName);
		else
			softAssertion.fail("Card View - Location - "+locationName+" not assigned to a material - "+materialName);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
