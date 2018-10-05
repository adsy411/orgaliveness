package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Storage_Search  extends TestBase
{
	@Test(priority = 1)
	public void AddMaterialPage_Location_Search() throws Exception
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
		Reporter.log("Click on Add Material and navigate to storage modal.");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		System.out.println("Add Material page displayed successfully.");
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(2, 9);
		
		//Click on Location modal
		getWebElement("Inventory.AddMaterial.AddLocation").click();
		Thread.sleep(3000);
		
		//Verify Location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		impliciteWait(1);
		
		//Verify searching location exists in storage list.
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
		
		//Creation of new location.
		if(locationFound == false)
		{	
			String temperature = "25";
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.AddMaterialPageCreateLocation(locationName, temperature);
			impliciteWait(3);
		}	
		
		//Search for storage
		verifyStorage.AddMaterialPageStorageSearch(locationName);
		impliciteWait(1);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(5000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void MaterialsCardViewPage_Location_Search() throws Exception
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
		
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(materialsCountBefore < 0)
		{
			//Adding third party Vendor material
			int rowNumber = 68;
			AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
			AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
			Thread.sleep(2000);
		}
		
		//Navigation to storage modal
		getWebElement("Inventory.CardView.LocationIcon").click();
		Thread.sleep(5000);
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(3, 9);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
				
		//Verify searching location exists in storage list.
		boolean locationFound = verifyStorage.VerifyLocation_MaterialsPage(locationName);
		
		//Creation of new location.
		if(locationFound == false)
		{	
			String temperature = "25";
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation_MaterialsPage(locationName, temperature);
			impliciteWait(3);
		}	
		
		//Search for storage
		verifyStorage.MaterialsPageStorageSearch(locationName);
		impliciteWait(1);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(5000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void MaterialsListViewPage_Location_Search() throws Exception
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
		
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(materialsCountBefore < 0)
		{
			//Adding third party Vendor material
			int rowNumber = 69;
			AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
			AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
			Thread.sleep(2000);
		}
		
		//Navigation to list page
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		
		//Navigation to storage modal
		getWebElement("Inventory.ListView.LocationIcon").click();
		Thread.sleep(5000);
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(4, 9);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
				
		//Verify searching location exists in storage list.
		boolean locationFound = verifyStorage.VerifyLocation_MaterialsPage(locationName);
		
		//Creation of new location.
		if(locationFound == false)
		{	
			String temperature = "25";
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation_MaterialsPage(locationName, temperature);
			impliciteWait(3);
		}	
		
		//Search for storage
		verifyStorage.MaterialsPageStorageSearch(locationName);
		impliciteWait(1);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(3000);
		
		//Clicks on card view
		getWebElement("Inventory.CardView").click();
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 4)
	public void MaterialDetailPage_Location_Search() throws Exception
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
		
		//Check whether materials exists or not
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(materialsCountBefore < 0)
		{
			//Adding third party Vendor material
			int rowNumber = 70;
			AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
			AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
			Thread.sleep(2000);
		}
		
		//Navigation to material detail page. 
		getWebElement("Inventory.MaterialDetailNavigation").click();
		Thread.sleep(2000);
		
		//Navigation to storage modal
		getWebElement("Inventory.MaterialDetail.Location").click();
		Thread.sleep(3000);

		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(5, 9);
		
		//Verify searching location exists in storage list.
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
		
		//Creation of new location.
		if(locationFound == false)
		{	
			String temperature = "25";
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation(locationName, temperature);
			impliciteWait(3);
		}	
		
		//Search for storage
		verifyStorage.AddMaterialPageStorageSearch(locationName);
		impliciteWait(1);
		
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
