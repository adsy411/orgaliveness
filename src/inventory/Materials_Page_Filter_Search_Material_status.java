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

public class Materials_Page_Filter_Search_Material_status extends TestBase
{
	@Test(priority = 1)
	public void Filter_Search_Material_Status_Running_Low() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 50;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Verify the filter modal
		InventoryRegularFunctions VerifyFilterModal = new InventoryRegularFunctions();
		VerifyFilterModal.Verify_Material_Status_Filter_Search();
		
		//Select the running low filter
		Reporter.log("'Running low' and 'minimum quantity' material Status types displayed in filter modal");
		getWebElement("Inventory.FilterSearch.MaterialsStatus.RunningLow").click();
		
		//Clicks on Apply button
		getWebElement("Inventory.FilterSearch.ApplyButton").click();
		Thread.sleep(7000);
		
		//Verify materials page displayed after filter search
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After filter search, Materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After filter search, Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		String pagination = getWebElement("Inventory.Pagination").getText();
		int pageCount = Integer.parseInt(pagination.split("Page 1 of ")[1]);
		int materialscount = 0;
		
		//Check whether materials exists
		Assert.assertTrue("After filter search for 'running low' material status, materials not displayed in searched materials page", materialsCountAfter > 0);
	
		//verify the material displayed based on filter search
		String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
		Assert.assertTrue("After filter search for 'running low' material status, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equalsIgnoreCase(materialName));
		Reporter.log("After filter search for 'running low' material status, the material - '"+materialName+"' displayed in searched materials page.");
		
		//Verify the list contains only running low materials till last page
		for(int i=1;i<=pageCount;i++)
		{
			List<WebElement> materialsCardName = driver.findElements(By.xpath("//div[@class='custom-col-toggle-child bs-callout bs-callout-warning card-view-wrapper no-padding']"));
			materialscount = materialscount + materialsCardName.size();
					
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
		
		Assert.assertTrue("The materials displayed is not proper for running low - material status filter search", (materialscount == materialsCountAfter));
		Reporter.log("The materials which are running low displayed in search result page after filter search for material status - 'Running Low'.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Filter_Search_Material_Status_Minimum_Quantity() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 51;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Verify material status section in filter modal
		InventoryRegularFunctions VerifyFilterModal = new InventoryRegularFunctions();
		VerifyFilterModal.Verify_Material_Status_Filter_Search();
		
		//Select the running low filter
		Reporter.log("'Running low' and 'minimum quantity' material Status types displayed in filter modal");
		getWebElement("Inventory.FilterSearch.MaterialsStatus.MinimumQuantity").click();
		
		//Clicks on Apply button
		getWebElement("Inventory.FilterSearch.ApplyButton").click();
		Thread.sleep(7000);
		
		//Verify materials page displayed after filter search
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After filter search, Materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After filter search, Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		String pagination = getWebElement("Inventory.Pagination").getText();
		int pageCount = Integer.parseInt(pagination.split("Page 1 of ")[1]);
		int materialscount = 0;
		
		//Check whether materials exists
		Assert.assertTrue("After filter search for 'Minimum Quantity' material status, materials not displayed in searched materials page", materialsCountAfter > 0);
	
		//verify the material displayed based on filter search
		String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
		Assert.assertTrue("After filter search for 'Minimum Quantity' material status, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equalsIgnoreCase(materialName));
		Reporter.log("After filter search for 'Minimum Quantity' material status, the material - '"+materialName+"' displayed in searched materials page.");
		
		//Verify the list contains only running low materials till last page
		for(int i=1;i<=pageCount;i++)
		{
			List<WebElement> materialsCardName = driver.findElements(By.xpath("//div[@class='custom-col-toggle-child bs-callout bs-callout-danger card-view-wrapper no-padding']"));
			materialscount = materialscount + materialsCardName.size();
					
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
		
		Assert.assertTrue("The materials displayed is not proper for Minimum Quantity - material status filter search", (materialscount == materialsCountAfter));
		Reporter.log("The materials which are running low displayed in search result page after filter search for material status - 'Minimum Quantity'.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void Filter_Search_All_Material_Status_Types() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber1 = 52;
		int rowNumber2 = 53;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		//Adding third party Vendor material - for running low purpose
		AddThirdPartyMaterial AddMaterial1 = new AddThirdPartyMaterial();
		AddMaterial1.AddThirdPartyVendorMaterial(rowNumber1);
		Thread.sleep(1000);
		
		//Adding third party Vendor material - for minimum quantity purpose
		AddThirdPartyMaterial AddMaterial2 = new AddThirdPartyMaterial();
		AddMaterial2.AddThirdPartyVendorMaterial(rowNumber2);
		Thread.sleep(1000);
		
		//Verify the material status section in filter modal
		InventoryRegularFunctions VerifyFilterModal = new InventoryRegularFunctions();
		VerifyFilterModal.Verify_Material_Status_Filter_Search();
		
		//Select the running low filter
		Reporter.log("'Running low' and 'minimum quantity' material Status types displayed in filter modal");
		getWebElement("Inventory.FilterSearch.MaterialsStatus.RunningLow").click();
		Thread.sleep(1000);
		
		//Select the running low filter
		Reporter.log("'Running low' and 'minimum quantity' material Status types displayed in filter modal");
		getWebElement("Inventory.FilterSearch.MaterialsStatus.MinimumQuantity").click();
		
		//Clicks on Apply button
		getWebElement("Inventory.FilterSearch.ApplyButton").click();
		Thread.sleep(7000);
		
		//Verify materials page displayed after filter search
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After filter search, Materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After filter search, Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		String pagination = getWebElement("Inventory.Pagination").getText();
		int pageCount = Integer.parseInt(pagination.split("Page 1 of ")[1]);
		int RunningLowMaterialscount = 0;
		int MinimumQuantityMaterialsCount = 0;
		
		//Check whether materials exists
		Assert.assertTrue("After filter search for 'running low' material status, materials not displayed in searched materials page", materialsCountAfter > 0);
	
		//Verify the list contains only running low materials till last page
		for(int i=1;i<=pageCount;i++)
		{
			List<WebElement> materialsCardNameRunningLow = driver.findElements(By.xpath("//div[@class='custom-col-toggle-child bs-callout bs-callout-warning card-view-wrapper no-padding']"));
			RunningLowMaterialscount = RunningLowMaterialscount + materialsCardNameRunningLow.size();
			
			List<WebElement> materialsCardNameMinQuantity = driver.findElements(By.xpath("//div[@class='custom-col-toggle-child bs-callout bs-callout-danger card-view-wrapper no-padding']"));
			MinimumQuantityMaterialsCount = MinimumQuantityMaterialsCount + materialsCardNameMinQuantity.size();
			
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
		
		int verifiedMaterialsCount = MinimumQuantityMaterialsCount + RunningLowMaterialscount;
		Assert.assertTrue("The materials displayed is not proper material status (Running Low and Minimum Quantity) filter search", (verifiedMaterialsCount == materialsCountAfter));
		Reporter.log("After filter search for both 'Running Low' and 'Minimum Quantity' - material status, the running low materials displayed is  -"+RunningLowMaterialscount+" and minimum quantity materials dispalyed is "+MinimumQuantityMaterialsCount);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
