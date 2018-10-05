package inventory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Storage_Error_Message_Validation_Scenario extends TestBase
{
	@Test(priority = 2)
	public void AddMaterial_With_SubStorage() throws Exception
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
		
		///Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		Reporter.log("Click on Add Material to navigate Storage modal");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Click on Location modal
		getWebElement("Inventory.AddMaterial.AddLocation").click();
		Thread.sleep(3000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify validation message for location name field when it contains null value
		getWebElement("Storage.AddNewLocation").click();
		Thread.sleep(1000);
		getWebElement("Storage.AddLocation.SaveButton").click();
		Thread.sleep(1000);
		String expectedLocationNameValidationMsg = "Name is required";
		String actualLocationNameValidationMsg = getWebElement("Storage.LocationName.ErrorMessage").getText().trim();
		Utills.captureScreenshot("Add_Material_Material_Name_Validation_Message_"+TodayDate.Date());
		if(expectedLocationNameValidationMsg.equalsIgnoreCase(actualLocationNameValidationMsg))
			Reporter.log("Validation message displayed successfully for Location Name field when it contains null value.");
		else
			softAssertion.fail("Validation message not displayed when Location name field is empty. The message displayed as  - "+actualLocationNameValidationMsg);
		
		String locationName = TodayDate.Date();
		getWebElement("Storage.AddNewLocation").click();
		//Creation of new location.
		StorageRegularFunctions addLocation = new StorageRegularFunctions();
		addLocation.CreateLocation(locationName, "25");
		
		//Get the index of level 1 location 
   		int level1StorageIndex = verifyStorage.getLevel1StorageIndex(locationName);
   		driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]/div[1]//li[1]/a")).click();
   		Thread.sleep(2000);
   		int storageIndex = level1StorageIndex - 1;
     	driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+storageIndex+":addSubLocationForBuilding']//a[contains(text(),'Save')]")).click();
		Thread.sleep(1000);
		String actualLevel2LocationNameValidationMsg = driver.findElement(By.xpath("//input[@id='add-location:buildingLoop:"+storageIndex+":addSubLocationForBuilding:stgName']//following-sibling::div")).getText().trim();
		String expectedLevel2LocationNameValidationMsg = "Name is required";
		//Verify validation message for material name field when it contains space
		Utills.captureScreenshot("Add_Material_Material_Name_Validation_Message_"+TodayDate.Date());
		if(expectedLevel2LocationNameValidationMsg.equalsIgnoreCase(actualLevel2LocationNameValidationMsg))
			Reporter.log("Validation message displayed successfully for Level 2 Location Name field when it contains null value.");
		else
			softAssertion.fail("Validation message not displayed when Level 2 Location Name field is empty. The message displayed as  - "+actualLevel2LocationNameValidationMsg);
	}
}
