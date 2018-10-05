package inventory;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Storage_Delete extends TestBase
{
	@Test(priority = 1)
	public void Add_Material_Page_Delete_Location() throws Exception
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
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		System.out.println("Add Material page displayed successfully.");
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(2, 0);
		String temperature = ExcelUtils.getCellData(2, 1);
		
		//Click on Location modal
		getWebElement("Inventory.AddMaterial.AddLocation").click();
		Thread.sleep(3000);
		
		//Verify Location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify Location already exists in storage modal
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
		
		//Creates a new storage if location does not exists in storage modal
		if(locationFound == false)
		{
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation(locationName, temperature);
		}
		
		//Deletion of Location from Storage List
		StorageRegularFunctions deleteLocation = new StorageRegularFunctions();
		deleteLocation.AddMaterialPage_DeleteLocation(locationName);
		Thread.sleep(3000);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(4000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Material_Card_View_Page_Delete_Location() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		int rowNumber = 71;
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Fetch the storage details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(3, 0);
		
		//Fetch the material Details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
	
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Adding a material with storage
		materialsPageNavigation.Add_Material_With_Storage(rowNumber, locationName);
		
		//Navigation to storage modal
		getWebElement("Inventory.CardView.LocationIcon").click();
		Thread.sleep(5000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
				
		//Verify location exists in storage list.
		boolean locationFound = verifyStorage.VerifyLocation_MaterialsPage(locationName);
		Assert.assertTrue("Unable to delete the storage as location - "+locationName+" not found in storage modal.",locationFound == true);
		
		//Deletion of Location from Storage List
		StorageRegularFunctions deleteLocation = new StorageRegularFunctions();
		deleteLocation.MaterialsPage_DeleteLocation(locationName);
		Thread.sleep(3000);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(4000);
		
		//Verify the material assigned with 'unassigned' 
		String assignedLocation = getWebElement("Inventory.CardView.LocationName").getText();
		if(assignedLocation.equals("(unassigned)"))
			Reporter.log("After location deletion, the storage is unassigned for material - "+materialName);
		else
			softAssertion.fail("After location deletion, the storage is not unassigned to a material - "+materialName);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void Material_List_View_Page_Delete_Location() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		int rowNumber = 82;
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Fetch the storage details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(4, 0);
		
		//Fetch the material Details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
	
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Adding a material with storage
		materialsPageNavigation.Add_Material_With_Storage(rowNumber, locationName);
		
		//Navigation to list page
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		
		//Navigation to storage modal
		getWebElement("Inventory.ListView.LocationIcon").click();
		Thread.sleep(5000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
				
		//Verify location exists in storage list.
		boolean locationFound = verifyStorage.VerifyLocation_MaterialsPage(locationName);
		
		Assert.assertTrue("Unable to delete the storage as location - "+locationName+" not found in storage modal.",locationFound == true);
		
		//Deletion of Location from Storage List
		StorageRegularFunctions deleteLocation = new StorageRegularFunctions();
		deleteLocation.MaterialsPage_DeleteLocation(locationName);
		Thread.sleep(3000);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(5000);
		
		//Verify the material assigned with 'unassigned'
		getWebElement("Inventory.ListView.ExpandIcon").click();
		Thread.sleep(4000);
		String assignedLocation = getWebElement("Inventory.ListView.LocationFieldValue").getText();
		String expectedLocationName = "(unassigned)";
		if(assignedLocation.equals(expectedLocationName))
			Reporter.log("After location deletion, the storage is unassigned for a material - "+materialName);
		else
			softAssertion.fail("After location deletion, the storage is not unassigned for a material - "+materialName);
		
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
	public void Material_Detail_Page_Delete_Location() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		int rowNumber = 83;
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Fetch the Storage details.
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(5, 0);
		
		//Fetch the material Details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
	
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Adding a material with storage
		materialsPageNavigation.Add_Material_With_Storage(rowNumber, locationName);
		
		//Navigation to material detail page. 
		getWebElement("Inventory.MaterialDetailNavigation").click();
		Thread.sleep(2000);
		
		//Navigation to storage modal
		getWebElement("Inventory.MaterialDetail.Location").click();
		Thread.sleep(3000);
		Reporter.log("Material detail page displayed successfully.");
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify Location already exists in storage modal
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
		Assert.assertTrue("Unable to delete the storage as location - "+locationName+" not found in storage modal.",locationFound == true);
		
		//Deletion of Location from Storage List
		StorageRegularFunctions deleteLocation = new StorageRegularFunctions();
		deleteLocation.AddMaterialPage_DeleteLocation(locationName);
		Thread.sleep(3000);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(4000);
		
		//Navigation to Materials Page. Verify materials page exists or not.
		materialsPageNavigation.MaterialPageNavigation();
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		
		//Verify the material assigned with 'unassigned' 
		String assignedLocation = getWebElement("Inventory.CardView.LocationName").getText();
		if(assignedLocation.equals("(unassigned)"))
			Reporter.log("After location deletion, the storage is unassigned for material - "+materialName);
		else
			softAssertion.fail("After location deletion, the storage is not unassigned to a material - "+materialName);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 5)
	public void Materials_Page_Select_All_Delete_Location() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
				
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 84;
		
		//Fetch the Storage details.
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(6, 0);
		String temperature = ExcelUtils.getCellData(6, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Get the materials count
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Creation of new material if materials count is zero
		if(materialsCountBefore <= 0)
		{
			//Adding third party Vendor material
			AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
			AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
			Thread.sleep(2000);
		}
		
		//Selecting all the materials
		getWebElement("Inventory.MultipleMaterial.SelectIcon").click();
		Thread.sleep(2000);
		Assert.assertTrue("Materials not selected in material card view page",getWebElement("Inventory.MaterialsPage.MultipleMaterial.VerifySelect").isDisplayed());
		Utills.captureScreenshot("Material_SelectAll_"+TodayDate.Date());
		
		//Navigation to storage modal
		getWebElement("Inventory.MultipleMaterial.LocationIcon").click();
		Thread.sleep(5000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
			
		//Verify storage exits
		boolean locationFound = verifyStorage.VerifyLocation_MaterialsPage(locationName);
		
		//Adding new location
		if(locationFound == false)
		{
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation_MaterialsPage(locationName, temperature);
		}
		
		//Selecting the location
		StorageRegularFunctions selectLocation = new StorageRegularFunctions();
		selectLocation.SelectLocation_MaterialsPage(locationName);
		
		//Updating material location
		Thread.sleep(1000);
		getWebElement("Storage.SaveButton.MaterialsPage").click();
		Thread.sleep(5000);
		
		//Take the material count after assigning the storage
		Thread.sleep(5000);
		int materialsCountAfterAssigningStorage = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		String pagination1 = getWebElement("Inventory.Pagination").getText();
		int pageCount1 = Integer.parseInt(pagination1.split("Page 1 of ")[1]);
		int materialsPageMaterialsCount = 0;
		
		//Verify the location name till last page
		for(int k=1;k<=pageCount1;k++)
		{
			List<WebElement> locationsName = driver.findElements(By.xpath("//span[@id='pageForm:materialList']//div[@class='col-xs-12 col-sm-12 col-md-6 col-lg-7']/label"));
			
			//Check whether the materials displayed based on searched location.
			for(int l=0; l<locationsName.size(); l++)
			{
				String actualLocationName = locationsName.get(l).getText().trim();
				if(actualLocationName.equals(locationName))
					materialsPageMaterialsCount = materialsPageMaterialsCount +1;
			}
			
			//check whether the pagination exists
			int currentPage1 = Integer.parseInt((pagination1.split("Page ")[1]).split(" ")[0]);
			if(currentPage1 < pageCount1)
			{
				//Scroll down the page
				JavascriptExecutor js = (JavascriptExecutor) driver;  
				js.executeScript("window.scrollBy(0,1000)");
				Thread.sleep(2000);
				
				getWebElement("Inventory.NextPage").click();
				Thread.sleep(5000);
				
				//Scroll up the page
				JavascriptExecutor js1 = (JavascriptExecutor) driver;  
				js1.executeScript("window.scrollBy(0,-1000)");
				Thread.sleep(2000);
			}
			else
				break;
		}
		
		softAssertion.assertTrue((materialsPageMaterialsCount == materialsCountAfterAssigningStorage),"All materials is not assigned to location - "+locationName);
		Reporter.log("All materials assigned to location - "+locationName);
		
		//Refreshing the page.
		driver.navigate().refresh();
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Selecting all the materials
		getWebElement("Inventory.MultipleMaterial.SelectIcon").click();
		Thread.sleep(2000);
		Assert.assertTrue("Materials not selected in material card view page",getWebElement("Inventory.MaterialsPage.MultipleMaterial.VerifySelect").isDisplayed());
		Reporter.log("All materials selected successfully in material card view page");
		Utills.captureScreenshot("Material_SelectAll_"+TodayDate.Date());
		
		//Navigation to storage modal
		getWebElement("Inventory.MultipleMaterial.LocationIcon").click();
		Thread.sleep(5000);
		
		//Verify location modal exists or not.
		verifyStorage.VerifyStorageModal();
			
		//Verify storage exits
		boolean locationFound1 = verifyStorage.VerifyLocation_MaterialsPage(locationName);
		Assert.assertTrue("Unable to delete the storage as location - "+locationName+" not found in storage modal.",locationFound1 == true);
		
		//Deletion of Location from Storage List
		StorageRegularFunctions deleteLocation = new StorageRegularFunctions();
		deleteLocation.MaterialsPage_DeleteLocation(locationName);
		Thread.sleep(3000);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(7000);

		//Take the material count after deleting the storage
		Thread.sleep(2000);
		int materialsCountAfterDeletionOfStorage = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		String pagination = getWebElement("Inventory.Pagination").getText();
		int pageCount = Integer.parseInt(pagination.split("Page 1 of ")[1]);
		int materialscount = 0;
		
		//Verify the location name till last page
		for(int i=1;i<=pageCount;i++)
		{
			List<WebElement> locationsName = driver.findElements(By.xpath("//span[@id='pageForm:materialList']//div[@class='col-xs-12 col-sm-12 col-md-6 col-lg-7']/label"));
			
			//Check whether the materials displayed based on searched location.
			for(int j=0; j<locationsName.size(); j++)
			{
				String actualLocationName = locationsName.get(j).getText().trim();
				if(actualLocationName.equals("(unassigned)"))
					materialscount = materialscount +1;
			}
			
			//check whether the pagination exists
			int currentPage = Integer.parseInt((pagination.split("Page ")[1]).split(" ")[0]);
			if(currentPage < pageCount)
			{
				//Scroll down the page
				JavascriptExecutor js = (JavascriptExecutor) driver;  
				js.executeScript("window.scrollBy(0,1000)");
				Thread.sleep(2000);
				
				getWebElement("Inventory.NextPage").click();
				Thread.sleep(5000);
				
				//Scroll up the page
				JavascriptExecutor js1 = (JavascriptExecutor) driver;  
				js1.executeScript("window.scrollBy(0,-1000)");
				Thread.sleep(2000);
			}
			else
				break;
		}
		
		softAssertion.assertTrue((materialscount == materialsCountAfterDeletionOfStorage),"The storage is not unassigned from all the materials.");
		Reporter.log("All materials are unassigned from storage.");
				
		//Logout from an application.
		Thread.sleep(1000);
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
