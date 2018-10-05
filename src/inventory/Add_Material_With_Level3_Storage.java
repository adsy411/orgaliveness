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

public class Add_Material_With_Level3_Storage extends TestBase
{
	@Test(priority = 1)
	public void AddMaterial_With_SubStorage() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);	
		int rowNumber = 80;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
		String storageName = ExcelUtils.getCellData(rowNumber, 5);
		String temperature = "25";
		
		String[] locationsName = storageName.split("/");
		if(locationsName.length ==3)
			System.out.println("The input contains all 3 level storage details.");
		else
			Assert.fail("The input does not contain sub-storage names.");
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Entering the material details in Add Material Modal
		Reporter.log("Enter the material details and add it to Inventory");
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMateriName").sendKeys(materialName);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		
		getWebElement("Inventory.Quantity").click();
		Thread.sleep(1000);
		getWebElement("Inventory.Quantity").sendKeys(quantity);
		
		//Verify the provided UOM exists in list
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.UOM",uom) == true)
			SelectList.SelectByValue("Inventory.UOM",uom);
		else
			Assert.fail("The UOM - "+uom+" does not exist");
		Thread.sleep(1000);
		
		//Check whether the vendor exists or not.
		if(SelectList.VerifySelectList("Inventory.Vendor",vendorName) == true)
		{
			SelectList.SelectByVisibleText("Inventory.Vendor",vendorName);
			Thread.sleep(1000);
		}
		//Creates a new vendor
		else
		{
			Reporter.log("The Vendor - "+vendorName+" provided in test data does not exist. A new vendor is created");
			SelectList.SelectByVisibleText("Inventory.Vendor","Add Vendor");
			Thread.sleep(1000);
			getWebElement("Inventory.AddVendor").sendKeys(vendorName);
			Thread.sleep(1000);
		}
		
		//Click on Location modal
		getWebElement("Inventory.AddMaterial.AddLocation").click();
		Thread.sleep(3000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//creates a new level 1 storage
		boolean locationFound = verifyStorage.VerifyLocation(locationsName[0]);
   		if(locationFound == false)
   		{
   			StorageRegularFunctions addLocation = new StorageRegularFunctions();
   			addLocation.CreateLocation(locationsName[0], temperature);
		}
   		
   		//Get the index of level 1 location 
   		int level1StorageIndex = verifyStorage.getLevel1StorageIndex(locationsName[0]);
   		Assert.assertTrue("Storage - "+locationsName[0]+" does not exist", level1StorageIndex >= 1);	
   		
   		//Expands the storage
   		verifyStorage.level1StorageExpand(level1StorageIndex);
   		
   		//verify level 2 sub-storage exists or not
   		boolean level2LocationFound = verifyStorage.VerifyLevel2SubLocationExist(locationsName[1], level1StorageIndex);
		System.out.println(level2LocationFound);
		
		//Verify level 2 sub-Storage already exists
		Assert.assertTrue("Unable to create a level 2 sub-location - "+locationsName[1]+", as the location already exist in a storage list",(level2LocationFound == false));
   		
		//creates a level 2 sub-storage
		verifyStorage.level2SubStorageCreation(locationsName[1], temperature, level1StorageIndex);
   		
		//Get the index of level 2 location 
   		int level2StorageIndex = verifyStorage.getLevel2StorageIndex(locationsName[1], level1StorageIndex);
   		Assert.assertTrue("Storage - "+locationsName[1]+" does not exist", level2StorageIndex >= 1);	
		
   		//verify level 3 sub-storage exists or not
   		boolean level3LocationFound = verifyStorage.VerifyLevel3SubLocationExist(locationsName[2], level2StorageIndex);
				
		//Verify level 2 sub-Storage already exists
		Assert.assertTrue("Unable to create a level 3 sub-location - "+locationsName[2]+", as the location already exist in a storage list",(level3LocationFound == false));
   		
		//creates a level 3 sub-storage
	//	verifyStorage.level3SubStorageCreation(locationsName[2], temperature, level2StorageIndex);
   		
		
		
		
		//Selects the newly created location
		StorageRegularFunctions selectLocation = new StorageRegularFunctions();
		selectLocation.SelectLevel2SubLocation(locationsName[1],level1StorageIndex);
		
		Thread.sleep(1000);
		getWebElement("Storage.SaveButton").click();
		Thread.sleep(5000);
		
		//Verify the location displayed in add material modal
		String selectedLocationName = getWebElement("Inventory.AddMaterial.AddLocation").getAttribute("value");
		Assert.assertTrue("Location value not displayed in add material modal",selectedLocationName.equals(storageName));
		Reporter.log("Location displayed in add material modal");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(3000);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.", addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		getWebElement("Inventory.OkButton").click();
		impliciteWait(3);
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());
	
		//Verification of Success message after addition of new material.
		Thread.sleep(2000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			System.out.println("Success Message displayed successfully after addition of new material as - "+ActualSuccessMessage);
		else 
			softAssertion.fail("Success Message not displayed after addition of new material.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material, the materials page not displayed", materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
				
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(MaterialsCountAfter == (materialsCountBefore+1))
			System.out.println("After addition of new material the material count is increased by "+(MaterialsCountAfter-materialsCountBefore));
		else 
			softAssertion.fail("After addition of new material the material count is not increased.");
		
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Newly created material - "+materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equals(materialName));
		
		//Verify material assigned to location 
		Thread.sleep(2000);
		String assignedLocation = getWebElement("Inventory.CardView.LocationName").getAttribute("title");
		Assert.assertTrue("Location - "+storageName+" not assigned to a material",assignedLocation.equals(storageName));
		Reporter.log("Location - "+storageName+" assigned successfully to a material - "+materialName);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
