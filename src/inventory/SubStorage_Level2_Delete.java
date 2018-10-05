package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class SubStorage_Level2_Delete extends TestBase
{
	@Test(priority = 1)
	public void AddMaterialPage_Delete_Level2_SubLocation() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
	
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		Reporter.log("Navigation to Add Material Modal.");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		System.out.println("Add Material page displayed successfully.");
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String level1StorageName = ExcelUtils.getCellData(7, 6);
		String level1StorageTemperature = ExcelUtils.getCellData(7, 7);
		String level2StorageName = ExcelUtils.getCellData(23, 6);
		String level2StorageTemperature = ExcelUtils.getCellData(23, 7);
		String delteLevel2StorageName = ExcelUtils.getCellData(22, 0);
		
		//Click on Location modal
		getWebElement("Inventory.AddMaterial.AddLocation").click();
		Thread.sleep(3000);
		
		//Verify Location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify Location already exists in storage modal
		boolean locationFound = verifyStorage.VerifyLocation(level1StorageName);
		
		//Creates a new storage if location does not exists in storage modal
		if(locationFound == false)
		{
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation(level1StorageName, level1StorageTemperature);
		}
		
		//Get the index of level 1 location 
   		int level1StorageIndex = verifyStorage.getLevel1StorageIndex(level1StorageName);
   		Assert.assertTrue("Storage - "+level1StorageName+" does not exist", level1StorageIndex >= 1);	
   		
   		//Expands the storage
   		verifyStorage.level1StorageExpand(level1StorageIndex);
   		
		//verify level 2 sub-storage exists or not
   		boolean level2LocationFound = verifyStorage.VerifyLevel2SubLocationExist(level2StorageName, level1StorageIndex);
		
   		//creates a level 2 sub-storage
   		if(level2LocationFound == false)
   		{
   			StorageRegularFunctions addLevel2SubLocation = new StorageRegularFunctions();
   			addLevel2SubLocation.level2SubStorageCreation(level2StorageName, level2StorageTemperature, level1StorageIndex);
		}
   		
   		//Delete the level 2 sub-Storage
   		StorageRegularFunctions editLevel2SubLocation = new StorageRegularFunctions();
   		editLevel2SubLocation.AddMaterialPage_Delete_Level2_SubLocation(level2StorageName, level2StorageTemperature, delteLevel2StorageName, level1StorageIndex);
   		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(4000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
