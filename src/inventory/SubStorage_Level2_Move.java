package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class SubStorage_Level2_Move extends TestBase
{
	@Test(priority = 1)
	public void Add_Material_Page_Move_Level2_SubLocation() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();

		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Fetch the storage details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String level1StorageName = ExcelUtils.getCellData(12, 6);
		String level1StorageTemperature = ExcelUtils.getCellData(12, 7);
		String level2MovingLocationName = ExcelUtils.getCellData(25, 6);
		String level2StorageTemperature = ExcelUtils.getCellData(25, 7);
		String level1GettingSubLocationParentLocationName = ExcelUtils.getCellData(13, 6);
		String level1MoveParentLocationTemperature = ExcelUtils.getCellData(13, 7);
		
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
		Reporter.log("Navigation to Storage Modal to perform sub-Storage Movement functionality.");
		
		//Click on Location modal
		getWebElement("Inventory.AddMaterial.AddLocation").click();
		Thread.sleep(3000);
		
		//Verify Location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify Location already exists in storage modal
		boolean locationFound1 = verifyStorage.VerifyLocation(level1GettingSubLocationParentLocationName);
		
		//Creates a new storage if location does not exists in storage modal
		if(locationFound1 == false)
		{
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation(level1GettingSubLocationParentLocationName, level1MoveParentLocationTemperature);
		}
		
		//Verify Location already exists in storage modal
		boolean locationFound = verifyStorage.VerifyLocation(level1StorageName);
		
		//Creates a new storage if location does not exists in storage modal
		if(locationFound == false)
		{
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation(level1StorageName, level1StorageTemperature);
		}
		
		//Get the index of level 1 location 
   		int level1MovingLocationIndex = verifyStorage.getLevel1StorageIndex(level1StorageName);
   		Assert.assertTrue("Storage - "+level1StorageName+" does not exist", level1MovingLocationIndex >= 1);	
   		
   		//Expands the storage
   		verifyStorage.level1StorageExpand(level1MovingLocationIndex);
   		
		//verify level 2 sub-storage exists or not
   		boolean level2LocationFound = verifyStorage.VerifyLevel2SubLocationExist(level2MovingLocationName, level1MovingLocationIndex);
		
   		//creates a level 2 sub-storage
   		if(level2LocationFound == false)
   		{
   			StorageRegularFunctions addLevel2SubLocation = new StorageRegularFunctions();
   			addLevel2SubLocation.level2SubStorageCreation(level2MovingLocationName, level2StorageTemperature, level1MovingLocationIndex);
		}
   		
   		//Get the index of move location 
   		int level1GettingSubLocationParentLocationIndex = verifyStorage.getLevel1StorageIndex(level1GettingSubLocationParentLocationName);
   		Assert.assertTrue("Storage - "+level1GettingSubLocationParentLocationName+" does not exist", level1GettingSubLocationParentLocationIndex >= 1);
   		
   		String movingStorageName = level1StorageName+"/"+level2MovingLocationName;
   		
   		//Storage movement 
   		StorageRegularFunctions moveLevel2SubLocation = new StorageRegularFunctions();
   		moveLevel2SubLocation.AddMaterialPage_Move_Level2_SubLocation(movingStorageName, level2MovingLocationName, level1MovingLocationIndex, level1GettingSubLocationParentLocationName, level1GettingSubLocationParentLocationIndex);
	
   		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(5000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
