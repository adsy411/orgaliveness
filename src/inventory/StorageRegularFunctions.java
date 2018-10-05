package inventory;


import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.Utills;

public class StorageRegularFunctions extends TestBase
{
	SoftAssert softAssertion= new SoftAssert();
	Library TodayDate = new Library();
	
	//Verify Storage Modal
	public void VerifyStorageModal() throws Exception
	{
		String modalHeading = getWebElement("Storage.ModalHeading").getText();
		Assert.assertTrue("Storage modal not displayed",modalHeading.equals("Add Material to Location"));
		Reporter.log("Storage modal displayed successfully");
		Utills.captureScreenshot("Storage_Modal_"+TodayDate.Date());
	}
	
	//Create a storage
	public void CreateLocation(String locationName,String temperature) throws Exception
	{
		//Verify add new location modal exists or not
		getWebElement("Storage.AddNewLocation").click();
		impliciteWait(2);
		Assert.assertTrue("Add new location modal not displayed",getWebElement("Storage.AddLocationModal").isDisplayed());
		System.out.println("Add new location modal displayed");
		Utills.captureScreenshot("Add_Location_Modal_"+TodayDate.Date());
		
		//Enter the values to add the location
		getWebElement("Storage.AddLocationName").click();
		getWebElement("Storage.AddLocationName").sendKeys(locationName);
		getWebElement("Storage.AddLocationTemperature").click();
		getWebElement("Storage.AddLocationTemperature").sendKeys(temperature);
		getWebElement("Storage.AddLocation.SaveButton").click();
		Thread.sleep(3000);
		impliciteWait(2);
		
		//Verify success message
		Utills.captureScreenshot("Add_location_Success_Message_"+TodayDate.Date());
		String addLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText();
		if(addLocationSuccessMessage.equals("Success! Location Added."))
			Reporter.log("Success message displayed successfully after addition of new location - "+locationName);
		else
			softAssertion.fail("Success message not displayed after addition of new location - "+locationName);
			
		//verify the newly added storage in a list
		boolean locationDisplayed = VerifyLocation(locationName);
		Assert.assertTrue("Newly created location - "+locationName+" not displayed in locations list.",(locationDisplayed == true));
		Reporter.log("Newly created location - "+locationName+" displayed in the locations list.");
	}
	
	public Boolean VerifyLocation(String locationName) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationExist();
		
		//If locations exist then verify the particular location present in a list.
		Boolean locationFound = false;
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText();
				location = location.trim();
			
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					locationFound = true;
					break;
				}
				else
					locationFound = false;
			}
		}
		else
			locationFound = false;	
		return locationFound;
	}	
	
	
	public Boolean VerifyLocationExist() throws Exception
	{
		Boolean locationsExist = false;
		try
		{
			if(getWebElement("Storage.AddMaterial.Verify.LocationsExist").isDisplayed())
				locationsExist = true;
		}
		catch(NoSuchElementException e) 
		{
			locationsExist = false;
		}
		return locationsExist;
	}
	
	public int getLevel1StorageIndex(String locationName) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationExist();
		
		//If locations exist then verify the particular location present in a list.
		int level1StorageIndex = 0;
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText().trim();
			
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					level1StorageIndex = i+1;
					break;
				}
			}
		}
		return level1StorageIndex;
	}	
	
	public Boolean SelectLocation(String locationName) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationExist();
		
		//If locations exist then verify the particular location present in a list.
		Boolean locationselected = false;
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText();
				location = location.trim();
				
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					locationselected = true;
					driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+(i+1)+"]/div[1]")).click();
					Thread.sleep(1000);
					
					//verify the material selected
					if(driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+(i+1)+"]/div[1][@style='background-color: rgb(213, 242, 245);']")).isDisplayed())
						Reporter.log("Location - "+locationName+" selected successfully in storage modal.");
					else if(driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+(i+1)+"]/div[1][@style='background-color: rgb(255, 255, 255);']")).isDisplayed())
						Assert.fail("Location -"+locationName+" not selected in storage modal.");
					else
						Assert.fail("Location -"+locationName+" not selected in storage modal.");
					Utills.captureScreenshot("Location_Selection_"+TodayDate.Date());
					break;
				}
			}
		}
		else
			Assert.fail("Location does not exist. Unable to select a location");
		
		return locationselected;
	}	
	
	public Boolean VerifyLocationsExist_MaterialsPage() throws Exception
	{
		Boolean locationsExist = false;
		try
		{
			if(getWebElement("Storage.CardView.Verify.LocationsExist").isDisplayed())
				locationsExist = true;
		}
		catch(NoSuchElementException e) 
		{
			locationsExist = false;
		}
		return locationsExist;
	}
	
	public void level1StorageExpand(int level1StorageIndex) throws Exception
	{
		driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']/h4/a")).click();
		Thread.sleep(2000);
	}
	
	public Boolean VerifyLocation_MaterialsPage(String locationName) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationsExist_MaterialsPage();
		
		//If locations exist then verify the particular location present in a list.
		Boolean locationFound = false;
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText();
				location = location.trim();
				
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					locationFound = true;
					break;
				}
				else
					locationFound = false;
			}
		}
		else
			locationFound = false;	
		return locationFound;
	}	
	
	//Create a storage
	public void CreateLocation_MaterialsPage(String locationName,String temperature) throws Exception
	{	
		//Verify add new location modal exists or not
		getWebElement("Storage.AddNewLocation").click();
		impliciteWait(2);
		Assert.assertTrue("Add new location modal not displayed",getWebElement("Storage.AddLocationModal.MaterialsPage").isDisplayed());
		Reporter.log("Add new location modal displayed");
		Utills.captureScreenshot("Add_Location_Modal_"+TodayDate.Date());
			
		//Enter the values to add the location
		getWebElement("Storage.AddLocationName.MaterialsPage").click();
		getWebElement("Storage.AddLocationName.MaterialsPage").sendKeys(locationName);
		getWebElement("Storage.AddLocationTemperature.MaterialsPage").click();
		getWebElement("Storage.AddLocationTemperature.MaterialsPage").sendKeys(temperature);
		getWebElement("Storage.AddLocation.SaveButton.MaterialsPage").click();
		Thread.sleep(3000);
		impliciteWait(2);
			
		//Verify success message
		Utills.captureScreenshot("Add_location_Success_Message_"+TodayDate.Date());
		String addLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText();
		if(addLocationSuccessMessage.equals("Success! Location Added."))
			Reporter.log("Success message displayed successfully after addition of new location - "+locationName);
		else
			softAssertion.fail("Success message not displayed after addition of new location - "+locationName);
				
		//verify the newly added storage in a list
		boolean locationDisplayed = VerifyLocation_MaterialsPage(locationName);
		Assert.assertTrue("Newly created location - "+locationName+" not displayed in locations list.",(locationDisplayed == true));
		Reporter.log("Newly created location -"+locationName+" displayed in the locations list.");
	}
	
	public Boolean SelectLocation_MaterialsPage(String locationName) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationsExist_MaterialsPage();
		
		//If locations exist then verify the particular location present in a list.
		Boolean locationselected = false;
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText();
				location = location.trim();
				
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					locationselected = true;
					driver.findElement(By.xpath("//span[@id='add-location-list:buildingList']/div["+(i+1)+"]/div[1]")).click();
					Thread.sleep(1000);
					
					//verify the material selected
					if(driver.findElement(By.xpath("//span[@id='add-location-list:buildingList']/div["+(i+1)+"]/div[1][@style='background-color: rgb(213, 242, 245);']")).isDisplayed())
						Reporter.log("Location - "+locationName+" selected successfully in storage modal.");
					else if(driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+(i+1)+"]/div[1][@style='background-color: rgb(255, 255, 255);']")).isDisplayed())
						Assert.fail("Location - "+locationName+" not selected in storage modal");
					else
						Assert.fail("Location - "+locationName+" not selected in storage modal");
					
					Utills.captureScreenshot("Location_Selection_"+TodayDate.Date());
					break;
				}
			}
		}
		else
			Assert.fail("Location - "+locationName+" does not exist. Unable to select a location");
		
		return locationselected;
	}
	
	public String AddMaterialPage_DeleteLocation(String locationName) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationExist();
		String locationDeletedTime = null;
		
		//If locations exist then verify the particular location present in a list.
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText().trim();
				
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+(i+1)+"]/div[1]//li//i[@class='fa fa-times']")).click();
					Thread.sleep(3000);
					
					//Verify delete location confirmation modal displayed
					String actualDeleteConfirmationMsg = getWebElement("Storage.DeleteConfirmationModal").getText().trim();
					String expectedDeleteConfirmationMsg = "Are you sure you want to delete this Location?";
					Assert.assertTrue("Location Deletion confirmation modal not displayed",(actualDeleteConfirmationMsg.equalsIgnoreCase(expectedDeleteConfirmationMsg)));
					Reporter.log("Confirmation modal displayed to delete a storage");
					Utills.captureScreenshot("Delete_Location_Confirm_Modal"+TodayDate.Date());
					
					Thread.sleep(1000);
					InventoryRegularFunctions locationDeletionTime = new InventoryRegularFunctions();
					locationDeletedTime = locationDeletionTime.Date_For_MaterialLog();
					getWebElement("Inventory.OkButton").click();
					Thread.sleep(3000);
						
					//Verification of success message after location deletion
					Utills.captureScreenshot("Delete_location_Success_Message_"+TodayDate.Date());
					String delteLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText().trim();
					if(delteLocationSuccessMessage.equals("Success! Location Deleted."))
						Reporter.log("Success message displayed successfully after deletion of a location - "+locationName);
					else
						softAssertion.fail("Success message not displayed after deletion of a location - "+locationName);
					
					//verify the deleted location displayed in a storage list
					boolean locationDisplayed = VerifyLocation(locationName);
					Assert.assertTrue("Deleted location - "+locationName+" not displayed in Storage list.",(locationDisplayed == false));
					Reporter.log("Deleted location - "+locationName+" not displayed in the Storage list.");	
					
					break;
				}
			}
		}
		else
			Assert.fail("Location does not exist. Unable to delete a location");
		
		return locationDeletedTime;
	}
	
	public void AddMaterialPage_EditLocation(String locationName, String temperature, String updateLocationName, String updateTemperature) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationExist();
		
		//If locations exist then verify the particular location present in a list.
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText().trim();
				
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					//Clicks on edit icon of a location
					driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+(i+1)+"]/div[1]//li//i[@class='fa fa-pencil']")).click();
					Thread.sleep(2000);
					
					Assert.assertTrue("Edit location modal not displayed",driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+i+":editBuilding']")).isDisplayed());
					Reporter.log("Edit location modal displayed.");
					Utills.captureScreenshot("Edit_Location_Modal_"+TodayDate.Date());
					Thread.sleep(1000);
					
					//Enter the values to edit the location
					WebElement editLocationName = driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+i+":editBuilding']//label[text()=' Location name ']/following-sibling::input"));
					editLocationName.click();
					editLocationName.clear();
					editLocationName.sendKeys(updateLocationName);
					
					WebElement editTemperature = driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+i+":editBuilding']//label[contains(text(),'Temperature')]/following-sibling::input"));
					editTemperature.click();
					editTemperature.clear();
					editTemperature.sendKeys(updateTemperature);
					
					driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+i+":editBuilding']//a[text()='Save']")).click();
					Thread.sleep(3000);
				
					//Verify success message
					Utills.captureScreenshot("Update_location_Success_Message_"+TodayDate.Date());
					String updateLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText();
					if(updateLocationSuccessMessage.equals("Success! Location Updated."))
						Reporter.log("Success message displayed successfully after updation of location");
					else
						softAssertion.fail("Success message not displayed after updation of location.");
						
					//verify the Updated location in a storage list
					boolean locationDisplayed = VerifyLocation(updateLocationName);
					Assert.assertTrue("Updated location - "+updateLocationName+" not displayed in Storage list.",(locationDisplayed == true));
					Reporter.log("Updated location - "+updateLocationName+" displayed in the Storage list.");
				
					break;
				}
			}
		}
		else
			Assert.fail("Location does not exist. Unable to update a location");
	}
	
	public void AddMaterialPageStorageSearch(String locationName) throws Exception
	{
		//Clicks on Location Search text-box
		getWebElement("Storage.Search").click();
		
		//Enters the storage name
		getWebElement("Storage.Search").sendKeys(locationName);
		Thread.sleep(2000);
		
		//Verify auto complete displayed 
		Assert.assertTrue("Auto suggestions not displayed for Location Name - "+locationName,getWebElement("Storage.Search.AutoCompleteList").isDisplayed());
		
		//Verify the searched location in auto-complete list
		boolean locationNameExist = false;
		int i=0;
		List<WebElement> storageAutoComplete = driver.findElements(By.xpath("//span[@id='add-location:locationFilter:searchLocation_panel']/ul/li"));
		for(i=0;i<storageAutoComplete.size();i++)
		{
			String autoCompleteListLocationName = storageAutoComplete.get(i).getText().trim();
			if(autoCompleteListLocationName.equalsIgnoreCase(locationName))
			{
				locationNameExist = true;
				break;
			}
			else
				locationNameExist = false;
		}
		
		Assert.assertTrue("Location name - "+locationName+" not displayed in Auto suggestion list",(locationNameExist == true));
		Reporter.log("Location displayed in Auto suggestion list");
		
		//Selects the location name from auto complete list
		driver.findElement(By.xpath("//span[@id='add-location:locationFilter:searchLocation_panel']/ul/li["+(i+1)+"]")).click();
		Thread.sleep(2000);
		
		//Verify only one location displayed after search
		List<WebElement> storageList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div"));
		int storageListSize = storageList.size();
		if(storageListSize < 1)
			softAssertion.fail("Locations not dispalyed after search");
		else if(storageListSize == 1)
		{
			//Verify the Searched location displayed after search
			String storageNameAfterSearch = getWebElement("Storage.AddMaterialPage.FirstStorageName").getText().trim();
			Assert.assertTrue("Searched location not displayed.",storageNameAfterSearch.equalsIgnoreCase(locationName));
			Reporter.log("Searched Location - "+locationName+" displayed successfully.");
		}
		else
			softAssertion.fail("More than one location displayed after search");
	}
	
	public void AddMaterialPage_SubStorage_Search(String subLocationName, String storageName) throws Exception
	{
		//Clicks on Location Search text-box
		getWebElement("Storage.Search").click();
		
		//Enters the storage name
		getWebElement("Storage.Search").sendKeys(subLocationName);
		Thread.sleep(2000);
		
		//Verify auto complete displayed 
		Assert.assertTrue("Auto suggestions not displayed for sub-location Name - "+subLocationName,getWebElement("Storage.Search.AutoCompleteList").isDisplayed());
		
		//Verify the searched location in auto-complete list
		boolean locationNameExist = false;
		int i=0;
		List<WebElement> storageAutoComplete = driver.findElements(By.xpath("//span[@id='add-location:locationFilter:searchLocation_panel']/ul/li"));
		for(i=0;i<storageAutoComplete.size();i++)
		{
			String autoCompleteListLocationName = storageAutoComplete.get(i).getText().trim();
			if(autoCompleteListLocationName.equalsIgnoreCase(storageName))
			{
				locationNameExist = true;
				break;
			}
			else
				locationNameExist = false;
		}
		
		Assert.assertTrue("Location - '"+subLocationName+"' not displayed in Auto suggestion list",(locationNameExist == true));
		Reporter.log("Location displayed in Auto suggestion list");
		
		//Selects the location name from auto complete list
		driver.findElement(By.xpath("//span[@id='add-location:locationFilter:searchLocation_panel']/ul/li["+(i+1)+"]")).click();
		Thread.sleep(4000);
		
		//Verify only one location displayed after search
		List<WebElement> storageList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div"));
		int storageListSize = storageList.size();
		if(storageListSize < 1)
			softAssertion.fail("Locations not dispalyed after search");
		else if(storageListSize == 1)
		{
			//Verify the Searched location displayed after search
			String storageNameAfterSearch = getWebElement("Storage.AddMaterialPage.FirstStorageName").getText().trim();
			Assert.assertTrue("Searched Sub-Location - '"+subLocationName+"' not displayed.",storageNameAfterSearch.equalsIgnoreCase(subLocationName));
			Reporter.log("Searched Sub-Location - "+subLocationName+" displayed successfully.");
		}
		else
			softAssertion.fail("More than one location displayed after search");
	}
	
	//Create a storage
	public void AddMaterialPageCreateLocation(String locationName,String temperature) throws Exception
	{
		//Verify add new location modal exists or not
		getWebElement("Storage.AddNewLocation").click();
		impliciteWait(2);
		Assert.assertTrue("Add new location modal not displayed",getWebElement("Storage.AddLocationModal").isDisplayed());
		Utills.captureScreenshot("Add_Location_Modal_"+TodayDate.Date());
		
		//Enter the values to add the location
		getWebElement("Storage.AddLocationName").click();
		getWebElement("Storage.AddLocationName").sendKeys(locationName);
		getWebElement("Storage.AddLocationTemperature").click();
		getWebElement("Storage.AddLocationTemperature").sendKeys(temperature);
		getWebElement("Storage.AddLocation.SaveButton").click();
		Thread.sleep(3000);
		impliciteWait(2);
		
		//Verify success message
		Utills.captureScreenshot("Add_location_Success_Message_"+TodayDate.Date());
		String addLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText();
		if(addLocationSuccessMessage.equals("Success! Location Added."))
			System.out.println("Success message displayed successfully after addition of new location - "+locationName);
		else
			softAssertion.fail("Success message not displayed after addition of new location - "+locationName);
			
		//verify the newly added storage in a list
		boolean locationDisplayed = VerifyLocation(locationName);
		Assert.assertTrue("Newly created location - "+locationName+" not displayed in locations list.",(locationDisplayed == true));
	}
	
	public void MaterialsPageStorageSearch(String locationName) throws Exception
	{
		//Clicks on Location Search text-box
		getWebElement("Storage.MaterialsPage.Search").click();
		
		//Enters the storage name
		getWebElement("Storage.MaterialsPage.Search").sendKeys(locationName);
		Thread.sleep(2000);
		
		//Verify auto complete displayed 
		Assert.assertTrue("Auto suggestions not displayed for Location Name - "+locationName,getWebElement("Storage.MaterialsPage.Search.AutoCompleteList").isDisplayed());
		
		//Verify the searched location in auto-complete list
		boolean locationNameExist = false;
		int i=0;
		List<WebElement> storageAutoComplete = driver.findElements(By.xpath("//span[@id='add-location-list:locationFilter:searchLocation_panel']/ul/li"));
		for(i=0;i<storageAutoComplete.size();i++)
		{
			String autoCompleteListLocationName = storageAutoComplete.get(i).getText().trim();
			if(autoCompleteListLocationName.equalsIgnoreCase(locationName))
			{
				locationNameExist = true;
				break;
			}
			else
				locationNameExist = false;
		}
		
		Assert.assertTrue("Location name - "+locationName+" not displayed in Auto suggestion list",(locationNameExist == true));
		Reporter.log("Location displayed in Auto suggestion list");
		
		//Selects the location name from auto complete list
		driver.findElement(By.xpath("//span[@id='add-location-list:locationFilter:searchLocation_panel']/ul/li["+(i+1)+"]")).click();
		Thread.sleep(3000);
		
		//Verify only one location displayed after search
		List<WebElement> storageList = driver.findElements(By.xpath("//span[@id='add-location-list:buildingList']/div"));
		int storageListSize = storageList.size();
		if(storageListSize < 1)
			softAssertion.fail("Locations not dispalyed after search");
		else if(storageListSize == 1)
		{
			//Verify the Searched location displayed after search
			String storageNameAfterSearch = getWebElement("Storage.MaterialsPage.FirstStorageName").getText().trim();
			Assert.assertTrue("Searched location not displayed.",storageNameAfterSearch.equalsIgnoreCase(locationName));
			Reporter.log("Searched Location - "+locationName+" displayed successfully.");
		}
		else
			softAssertion.fail("More than one location displayed after search");
	}
	
	public String MaterialsPage_DeleteLocation(String locationName) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationsExist_MaterialsPage();
		String locationDeletedTime = null;
		
		//If locations exist then verify the particular location present in a list.
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText().trim();
				
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					driver.findElement(By.xpath("//span[@id='add-location-list:buildingList']/div["+(i+1)+"]/div[1]//li//i[@class='fa fa-times']")).click();
					Thread.sleep(3000);
					
					//Verify delete location confirmation modal displayed
					String actualDeleteConfirmationMsg = getWebElement("Storage.DeleteConfirmationModal").getText().trim();
					String expectedDeleteConfirmationMsg = "Are you sure you want to delete this Location?";
					Assert.assertTrue("Location Deletion confirmation modal not displayed",(actualDeleteConfirmationMsg.equalsIgnoreCase(expectedDeleteConfirmationMsg)));
					Reporter.log("Confirmation modal displayed to delete a storage");
					Utills.captureScreenshot("Delete_Location_Confirm_Modal"+TodayDate.Date());
					
					Thread.sleep(1000);
					InventoryRegularFunctions locationDeletionTime = new InventoryRegularFunctions();
					locationDeletedTime = locationDeletionTime.Date_For_MaterialLog();
					getWebElement("Inventory.OkButton").click();
					Thread.sleep(3000);
						
					//Verification of success message after location deletion
					Utills.captureScreenshot("Delete_location_Success_Message_"+TodayDate.Date());
					String delteLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText().trim();
					if(delteLocationSuccessMessage.equals("Success! Location Deleted."))
						Reporter.log("Success message displayed successfully after deletion of a location - "+locationName);
					else
						softAssertion.fail("Success message not displayed after deletion of a location - "+locationName);
					
					//verify the deleted location displayed in a storage list
					Thread.sleep(3000);
					boolean locationDisplayed = VerifyLocation_MaterialsPage(locationName);
					Assert.assertTrue("Deleted location - "+locationName+" not displayed in Storage list.",(locationDisplayed == false));
					Reporter.log("Deleted location - "+locationName+" not displayed in the Storage list.");	
					
					break;
				}
			}
		}
		else
			Assert.fail("Location does not exist. Unable to delete a location");
		
		return locationDeletedTime;
	}
	
	public void MaterialsPage_EditLocation(String locationName, String temperature, String updateLocationName, String updateTemperature) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLocationsExist_MaterialsPage();
		
		//If locations exist then verify the particular location present in a list.
		if(locationsExist == true)
		{
			List<WebElement> locationsList = driver.findElements(By.xpath("//div[@id='location-wrapper']//div[@class='col-xs-12 col-sm-3 col-md-7 col-lg-7']//span[1]//span")); 
			for(int i = 0; i<locationsList.size(); i++)
			{
				String location = locationsList.get(i).getText().trim();
				
				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					//Clicks on edit icon of a location
					driver.findElement(By.xpath("//span[@id='add-location-list:buildingList']/div["+(i+1)+"]/div[1]//li//i[@class='fa fa-pencil']")).click();
					Thread.sleep(2000);
					
					Assert.assertTrue("Edit location modal not displayed",driver.findElement(By.xpath("//form[@id='add-location-list:buildingLoop:"+i+":editBuilding']")).isDisplayed());
					Reporter.log("Edit location modal displayed.");
					Utills.captureScreenshot("Edit_Location_Modal_"+TodayDate.Date());
					Thread.sleep(1000);
					
					//Enter the values to edit the location
					WebElement editLocationName = driver.findElement(By.xpath("//form[@id='add-location-list:buildingLoop:"+i+":editBuilding']//label[text()=' Location name ']/following-sibling::input"));
					editLocationName.click();
					editLocationName.clear();
					editLocationName.sendKeys(updateLocationName);
					
					WebElement editTemperature = driver.findElement(By.xpath("//form[@id='add-location-list:buildingLoop:"+i+":editBuilding']//label[contains(text(),'Temperature')]/following-sibling::input"));
					editTemperature.click();
					editTemperature.clear();
					editTemperature.sendKeys(updateTemperature);
					
					driver.findElement(By.xpath("//form[@id='add-location-list:buildingLoop:"+i+":editBuilding']//a[text()='Save']")).click();
					Thread.sleep(3000);
				
					//Verify success message
					Utills.captureScreenshot("Update_location_Success_Message_"+TodayDate.Date());
					String updateLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText();
					if(updateLocationSuccessMessage.equals("Success! Location Updated."))
						Reporter.log("Success message displayed successfully after updation of location from "+locationName+"to "+updateLocationName);
					else
						softAssertion.fail("Success message not displayed after updation of location");
						
					//verify the Updated location in a storage list
					boolean locationDisplayed = VerifyLocation_MaterialsPage(updateLocationName);
					Assert.assertTrue("Updated location - "+updateLocationName+" not displayed in Storage list.",(locationDisplayed == true));
					Reporter.log("Updated location - "+updateLocationName+" displayed in the Storage list.");
				
					break;
				}
			}
		}
		else
			Assert.fail("Location does not exist. Unable to update a location");
	}
	
	public void level2SubStorageCreation(String locationName, String temperature, int level1StorageIndex) throws Exception
	{
		driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]/div[1]//li[1]/a")).click();
		impliciteWait(2);
		int storageIndex = level1StorageIndex - 1;
		//Enter the values to add the location
		driver.findElement(By.id("add-location:buildingLoop:"+storageIndex+":addSubLocationForBuilding:stgName")).click();
		driver.findElement(By.id("add-location:buildingLoop:"+storageIndex+":addSubLocationForBuilding:stgName")).sendKeys(locationName);
		driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+storageIndex+":addSubLocationForBuilding']//label[contains(text(),'Temperature')]/following-sibling::input")).click();
		driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+storageIndex+":addSubLocationForBuilding']//label[contains(text(),'Temperature')]/following-sibling::input")).sendKeys(temperature);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+storageIndex+":addSubLocationForBuilding']//a[contains(text(),'Save')]")).click();
		Thread.sleep(2000);
		impliciteWait(2);
		
		//Verify success message
		Utills.captureScreenshot("Add_Sub-location_Success_Message_"+TodayDate.Date());
		String addLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText();
		if(addLocationSuccessMessage.equals("Success! Sub-Location Added."))
			Reporter.log("Success message displayed successfully after creation of sub-location - "+locationName);
		else
			softAssertion.fail("Success message not displayed after creation of sub-location - "+locationName);
			
		//verify the newly added storage in a list
		boolean level2SublocationDisplayed = VerifyLevel2SubLocationExist(locationName, level1StorageIndex);
		Assert.assertTrue("Newly created leve 2 sub-location - "+locationName+" not displayed in locations list.",(level2SublocationDisplayed == true));
		Reporter.log("Newly created level 2 sub-location - "+locationName+" displayed in the locations list.");
	}
	
	public Boolean VerifyLevel2SubLocationExist(String locationName,int level1StorageIndex) throws Exception
	{
		//Verify locations exist in storage modal
		boolean level2SubLocationsExist = VerifyLevel2SubLocationsExist(level1StorageIndex);
		
		//If locations exist then verify the particular location present in a list.
		Boolean level2SubLocationFound = false;
		if(level2SubLocationsExist == true)
		{
			List<WebElement> level2SublocationsList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div[@class='panel panel-default']/div[1]//span/a/span")); 
			for(int i = 0; i<level2SublocationsList.size(); i++)
			{
				String location = level2SublocationsList.get(i).getText().trim();

				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					level2SubLocationFound = true;
					break;
				}
				else
					level2SubLocationFound = false;
			}
		}
		else
			level2SubLocationFound = false;	
		return level2SubLocationFound;
	}
	
	public Boolean VerifyLevel2SubLocationsExist(int level1StorageIndex) throws Exception
	{
		Boolean level2SubLocationsExist = false;
		try
		{
			if(driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span[1]/div[1]")).isDisplayed())
				level2SubLocationsExist = true;
		}
		catch(NoSuchElementException e) 
		{
			level2SubLocationsExist = false;
		}
		return level2SubLocationsExist;
	}
	
	public Boolean SelectLevel2SubLocation(String locationName,int level1StorageIndex) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLevel2SubLocationExist(locationName,level1StorageIndex);
		
		//If locations exist then verify the particular location present in a list.
		Boolean locationselected = false;
		if(locationsExist == true)
		{
			List<WebElement> level2SublocationsList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div[@class='panel panel-default']/div[1]//span/a")); 
			for(int i = 0; i<level2SublocationsList.size(); i++)
			{
				String location = level2SublocationsList.get(i).getText().trim();

				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					locationselected = true;
					driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div["+(i+1)+"]/div[1]")).click();
					Thread.sleep(1000);
					
					//verify the material selected
					if(driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div["+(i+1)+"]/div[1][@style='background-color: rgb(213, 242, 245);']")).isDisplayed())
						Reporter.log("Sub-Location - "+locationName+" selected successfully in storage modal.");
					else if(driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div["+(i+1)+"]/div[1][@style='background-color: rgb(255, 255, 255);']")).isDisplayed())
						Assert.fail("Location -"+locationName+" not selected in storage modal.");
					else
						Assert.fail("Sub-Location -"+locationName+" not selected in storage modal.");
					Utills.captureScreenshot("Location_Selection_"+TodayDate.Date());
					break;
				}
			}
		}
		else
			Assert.fail("Sub-Location does not exist. Unable to select a location");
		
		return locationselected;
	}	
	
	public int getLevel2StorageIndex(String locationName, int level1StorageIndex) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLevel2SubLocationExist(locationName,level1StorageIndex);
		
		//If locations exist then verify the particular location present in a list.
		int level2StorageIndex = 0;
		if(locationsExist == true)
		{
			List<WebElement> level2SublocationsList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div[@class='panel panel-default']/div[1]//span/a/span")); 
			for(int i = 0; i<level2SublocationsList.size(); i++)
			{
				String location = level2SublocationsList.get(i).getText().trim();

				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					level2StorageIndex = i+1;
					break;
				}
			}
		}
		return level2StorageIndex;
	}
	
	public void AddMaterialPage_Edit_Level2_SubLocation(String locationName, String temperature, String updateLocationName, String updateTemperature, int level1StorageIndex) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLevel2SubLocationExist(locationName,level1StorageIndex);
		
		//If locations exist then verify the particular location present in a list.
		if(locationsExist == true)
		{
			List<WebElement> level2SublocationsList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div[@class='panel panel-default']/div[1]//span/a")); 
			for(int i = 0; i<level2SublocationsList.size(); i++)
			{
				String location = level2SublocationsList.get(i).getText().trim();

				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					//Clicks on edit icon of a location
					driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div["+(i+1)+"]/div[1]//li//i[@class='fa fa-pencil']")).click();
					Thread.sleep(2000);
					
					int storageIndex = level1StorageIndex - 1;
					
					//Enter the values to edit the location
					WebElement editLocationName = driver.findElement(By.id("add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":editLocationForRoom:stgName"));
					editLocationName.click();
					editLocationName.clear();
					editLocationName.sendKeys(updateLocationName);
					
					
					WebElement editTemperature = driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":editLocationForRoom']//label[contains(text(),'Temperature')]/following-sibling::input"));
					editTemperature.click();
					editTemperature.clear();
					editTemperature.sendKeys(updateTemperature);
					driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":editLocationForRoom']//a[contains(text(),'Save')]")).click();
					Thread.sleep(3000);
				
					//Verify success message
					Utills.captureScreenshot("Update_location_Success_Message_"+TodayDate.Date());
					String updateLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText();
					if(updateLocationSuccessMessage.equals("Success! Sub-Location Updated."))
						Reporter.log("Success message displayed successfully after updation of Sub-location");
					else
						softAssertion.fail("Success message not displayed after updation of Sub-location.");
					
					//Expands the storage
			   		level1StorageExpand(level1StorageIndex);
			   		Thread.sleep(2000);
			   		
					//verify the Updated location in a storage list
					boolean locationDisplayed = VerifyLevel2SubLocationExist(updateLocationName,level1StorageIndex);
					Assert.assertTrue("Updated Sub-location - "+updateLocationName+" not displayed in Storage list.",(locationDisplayed == true));
					Reporter.log("Updated Sub-location - "+updateLocationName+" displayed in the Storage list.");
					Utills.captureScreenshot("Updated_level2_Sublocation"+TodayDate.Date());
					break;
				}
			}
		}
		else
			Assert.fail("Sub-Location does not exist. Unable to update a location");
	}
	
	public void AddMaterialPage_Delete_Level2_SubLocation(String locationName, String temperature, String delteLevel2StorageName, int level1StorageIndex) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLevel2SubLocationExist(locationName,level1StorageIndex);
		
		//If locations exist then verify the particular location present in a list.
		if(locationsExist == true)
		{
			List<WebElement> level2SublocationsList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div[@class='panel panel-default']/div[1]//span/a")); 
			for(int i = 0; i<level2SublocationsList.size(); i++)
			{
				String location = level2SublocationsList.get(i).getText().trim();

				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					//Clicks on edit icon of a location
					driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div["+(i+1)+"]/div[1]//li//i[@class='fa fa-times']")).click();
					Thread.sleep(3000);
					
					//Verify delete location confirmation modal displayed
					String actualDeleteConfirmationMsg = getWebElement("Storage.DeleteConfirmationModal").getText().trim();
					String expectedDeleteConfirmationMsg = "Are you sure you want to delete this Location?";
					Assert.assertTrue("Location Deletion confirmation modal not displayed",(actualDeleteConfirmationMsg.equalsIgnoreCase(expectedDeleteConfirmationMsg)));
					Reporter.log("Confirmation modal displayed to delete a sub-storage");
					Utills.captureScreenshot("Delete_Location_Confirm_Modal"+TodayDate.Date());
					
					Thread.sleep(1000);
					getWebElement("Inventory.OkButton").click();
					Thread.sleep(3000);
						
					//Verification of success message after location deletion
					Utills.captureScreenshot("Delete_location_Success_Message_"+TodayDate.Date());
					String delteLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText().trim();
					if(delteLocationSuccessMessage.equals("Success! Sub-Location Deleted."))
						Reporter.log("Success message displayed successfully after deletion of a sub-location - "+locationName);
					else
						softAssertion.fail("Success message not displayed after deletion of a sub-location - "+locationName);
					
					//Expands the storage
			   		level1StorageExpand(level1StorageIndex);
			   		Thread.sleep(2000);
			   		
					//verify the deleted Sub-location displayed in a storage list
					boolean locationDisplayed = VerifyLevel2SubLocationExist(locationName,level1StorageIndex);
					Assert.assertTrue("Deleted location - '"+locationName+"' not displayed in Storage list.",(locationDisplayed == false));
					Reporter.log("Deleted sub-location - '"+locationName+"' not displayed in the Storage list.");	
					Utills.captureScreenshot("Deleted_level2_Sublocation"+TodayDate.Date());
					break;
				}
			}
		}
		else
			Assert.fail("Location does not exist. Unable to delete a location");
	}
	
	
	public Boolean VerifyLevel3SubLocationExist(String locationName,int level1StorageIndex) throws Exception
	{
		//Verify locations exist in storage modal
		boolean level2SubLocationsExist = VerifyLevel2SubLocationsExist(level1StorageIndex);
		
		//If locations exist then verify the particular location present in a list.
		Boolean level2SubLocationFound = false;
		if(level2SubLocationsExist == true)
		{
			List<WebElement> level2SublocationsList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span/div[@class='panel panel-default']/div[1]//span/a/span")); 
			for(int i = 0; i<level2SublocationsList.size(); i++)
			{
				String location = level2SublocationsList.get(i).getText().trim();

				//Check whether the location exist. 
				if(location.equalsIgnoreCase(locationName))
				{
					level2SubLocationFound = true;
					break;
				}
				else
					level2SubLocationFound = false;
			}
		}
		else
			level2SubLocationFound = false;	
		return level2SubLocationFound;
	}
	
	public Boolean VerifyLevel3SubLocationsExist(int level1StorageIndex) throws Exception
	{
		Boolean level2SubLocationsExist = false;
		try
		{
			if(driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1StorageIndex+"]//div[@id='sub-location-wrapper']/span[1]/div[1]")).isDisplayed())
				level2SubLocationsExist = true;
		}
		catch(NoSuchElementException e) 
		{
			level2SubLocationsExist = false;
		}
		return level2SubLocationsExist;
	}
	
	public void AddMaterialPage_Move_Level2_SubLocation(String movingStorageName, String level2MovingLocationName, int level1MovingLocationIndex,String level1GettingSubLocationParentLocationName, int level1GettingSubLocationParentLocationIndex) throws Exception
	{
		//Verify locations exist in storage modal
		boolean locationsExist = VerifyLevel2SubLocationExist(level2MovingLocationName,level1MovingLocationIndex);
		
		//If locations exist then verify the particular location present in a list.
		if(locationsExist == true)
		{
			List<WebElement> level2SublocationsList = driver.findElements(By.xpath("//span[@id='add-location:buildingList']/div["+level1MovingLocationIndex+"]//div[@id='sub-location-wrapper']/span/div[@class='panel panel-default']/div[1]//span/a")); 
			for(int i = 0; i<level2SublocationsList.size(); i++)
			{
				String actualLevel2locationName = level2SublocationsList.get(i).getText().trim();

				//Check whether the location exist. 
				if(actualLevel2locationName.equalsIgnoreCase(level2MovingLocationName))
				{
					//Clicks on move icon of a location
					driver.findElement(By.xpath("//span[@id='add-location:buildingList']/div["+level1MovingLocationIndex+"]//div[@id='sub-location-wrapper']/span/div["+(i+1)+"]/div[1]//li//i[@class='fa fa-map-marker']")).click();
					Thread.sleep(2000);

					int storageIndex = level1MovingLocationIndex - 1;
					
					//get the location name from move modal
					List<WebElement> moveModalStorageNameList = driver.findElements(By.xpath("//form[@id='add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":moveRoomLocation']//label[contains(text(),'Current')]/following-sibling::span"));
					String[] moveModalStorageNames = new String[moveModalStorageNameList.size()];
					for(int j = 0; j<moveModalStorageNameList.size(); j++)
					{
						moveModalStorageNames[j]= moveModalStorageNameList.get(i).getText().trim();					
					}
					
					//check the location name displayed is proper in move modal
					String actualMoveModalStorageName = moveModalStorageNames[0]+"/"+moveModalStorageNames[1];
					softAssertion.assertTrue(actualMoveModalStorageName.equals(movingStorageName),"The moving storage name displayed is not proper in move modal.");
					
					//Enter the values to move the location
					WebElement moveLocationName = driver.findElement(By.id("add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":moveRoomLocation:moveLocation_input"));
					moveLocationName.click();
					moveLocationName.clear();
					moveLocationName.sendKeys(level1GettingSubLocationParentLocationName);
					
					Thread.sleep(3000);
				
					WebElement moveModalAutoComplete = driver.findElement(By.xpath("//span[@id='add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":moveRoomLocation:moveLocation_panel']/ul/li[1]"));
					
					//Verify auto complete displayed 
					Assert.assertTrue("Auto suggestions not displayed for location Name - "+level1GettingSubLocationParentLocationName,moveModalAutoComplete.isDisplayed());
					
					//Verify the searched location in auto-complete list
					boolean locationNameExist = false;
					int k=0;
					List<WebElement> moveModalAutoCompleteList = driver.findElements(By.xpath("//span[@id='add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":moveRoomLocation:moveLocation_panel']/ul/li"));
					for(k=0;k<moveModalAutoCompleteList.size();k++)
					{
						String autoCompleteListLocationName = moveModalAutoCompleteList.get(k).getText().trim();
						if(autoCompleteListLocationName.equalsIgnoreCase(level1GettingSubLocationParentLocationName))
						{
							locationNameExist = true;
							break;
						}
						else
							locationNameExist = false;
					}
					
					Assert.assertTrue("Location - '"+level1GettingSubLocationParentLocationName+"' not displayed in Auto suggestion list",(locationNameExist == true));
					Reporter.log("Location displayed in Auto suggestion list of movement modal.");
					
					//Selects the location name from auto complete list
					driver.findElement(By.xpath("//span[@id='add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":moveRoomLocation:moveLocation_panel']/ul/li["+(k+1)+"]")).click();
					Thread.sleep(3000);
					
					//click on save button
					driver.findElement(By.xpath("//form[@id='add-location:buildingLoop:"+storageIndex+":roomLoop:"+i+":moveRoomLocation']//a[contains(text(),'Save')]")).click();
					Thread.sleep(3000);
					
					//Verify success message
					Utills.captureScreenshot("Move_location_Success_Message_"+TodayDate.Date());
					String updateLocationSuccessMessage = getWebElement("Storage.AddLocationSucessMessage").getText();
					if(updateLocationSuccessMessage.equals("Success! Sub-Location Moved."))
						Reporter.log("Success message displayed successfully after sub-storage movement.");
					else
						softAssertion.fail("Success message not displayed after sub-storage movement.");
					
					//Expands the storage
			   		level1StorageExpand(level1MovingLocationIndex);
			   		Thread.sleep(2000);
			   		
					//verify the moved Sub-location displayed still displayed in old storage
					boolean locationDisplayed = VerifyLevel2SubLocationExist(level2MovingLocationName,level1MovingLocationIndex);
					Assert.assertTrue("Moved Sub-location - '"+level2MovingLocationName+"' still displayed under old Storage."+moveModalStorageNames[0],(locationDisplayed == false));
					Utills.captureScreenshot("Moved_level2_Sublocation"+TodayDate.Date());
					
					//collapse the expanded storage
			   		level1StorageExpand(level1MovingLocationIndex);
			   		Thread.sleep(2000);
			   		
					//Expands the storage
			   		level1StorageExpand(level1GettingSubLocationParentLocationIndex);
			   		Thread.sleep(2000);
			   		
					//verify the moved sub-location displayed or not.
					boolean locationDisplayed1 = VerifyLevel2SubLocationExist(level2MovingLocationName,level1GettingSubLocationParentLocationIndex);
					Assert.assertTrue("Moved Sub-location - "+level2MovingLocationName+" not displayed under location -"+level1GettingSubLocationParentLocationName,(locationDisplayed1 == true));
					Reporter.log("Moved Sub-location - "+level2MovingLocationName+" displayed under location -"+level1GettingSubLocationParentLocationName);
					Utills.captureScreenshot("Moved_level2_Sublocation_"+TodayDate.Date());
					break;
				}
			}
		}
		else
			Assert.fail("Sub-Location does not exist. Unable to move a location");
	}
}
