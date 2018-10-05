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

public class Materials_Page_SelectAll_Assign_New_Storage extends TestBase
{
	@Test(priority = 1)
	public void Materials_Page_SelectAll_Assign_New_Location() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		int rowNumber = 43;
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Fetch the storage details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String locationName = ExcelUtils.getCellData(rowNumber, 5);
		String temperature = "35";
		
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
		Reporter.log("All materials selected successfully in material card view page");
		Utills.captureScreenshot("Material_SelectAll_"+TodayDate.Date());
		
		//Navigation to storage modal
		getWebElement("Inventory.MultipleMaterial.LocationIcon").click();
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
		Thread.sleep(7000);
		
		//Verification of success message after assigning location to material
		String expectedAssignLocationSuccessMessage = "Success! Material location updated!";
		String actualAssignLocationSuccessMessage = getWebElement("Inventory.MaterialsPage.SuccessMessage").getText().trim();
		if(expectedAssignLocationSuccessMessage.equalsIgnoreCase(actualAssignLocationSuccessMessage))
			Reporter.log("The success message displayed successfully as - '"+actualAssignLocationSuccessMessage+"' after assigning location to a material using multi-select functionality in materials page.");
		else
			softAssertion.fail("The success message not displayed after assigning location to a material using multi-select functionality in materials page. Expected message is - "+expectedAssignLocationSuccessMessage+". Actual Message displayed is - "+actualAssignLocationSuccessMessage);
		
		Utills.captureScreenshot("Assign_Location_Success_Message_"+TodayDate.Date());	
		
		//Take the material count after assigning storage
		Thread.sleep(2000);
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
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
				if(actualLocationName.equals(locationName))
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
		
		Assert.assertTrue("Materials displayed is not proper for location name filter search", (materialscount == materialsCountAfter));
		Reporter.log("All materials assigned to location - "+locationName+" after assigning location to all materials using multiselect functionality.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
