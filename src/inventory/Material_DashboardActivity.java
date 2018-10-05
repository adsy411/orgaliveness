package inventory;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Material_DashboardActivity extends TestBase
{
	Library TodayDate = new Library();
	
	@Test(priority = 1)
	public void AddMaterialDashboardActivityLog() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(30, 0);
		String quantity = ExcelUtils.getCellData(30, 3);
		String uom = ExcelUtils.getCellData(30, 4);
		int rowNumber = 30;
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
	
		//Navigation to Materials Page. Adding third party Vendor material
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);	
		
		//Navigation to dash-board page.
		InventoryRegularFunctions dashboardPageNavigation = new InventoryRegularFunctions();
		dashboardPageNavigation.DashboardPageNavigation();
		Thread.sleep(3000);	
			
		//Verify the material added log
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,250)");
		String dashboardActivityLog = getWebElement("Inventory.Dashboard.Log").getText();
		String expectedAddMaterialLog = null;
		if(quantity.contains("."))
			expectedAddMaterialLog = labUserName+" "+quantity+" "+uom+" of "+materialName+" Added.";
		else
			expectedAddMaterialLog = labUserName+" "+quantity+".0 "+uom+" of "+materialName+" Added.";
		
		if(dashboardActivityLog.contains(expectedAddMaterialLog))
			Reporter.log("Success! Material added log displayed in dashboard page.");
		else
			softAssertion.fail("Material added log not displayed in dashboard page.");
		
		Utills.captureScreenshot("Activity_Log_Dashboard_Page_"+TodayDate.Date());		
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	/*
	@Test(priority = 2)
	public void MaterialCardView_RequestMaterial_DashboardActivityLog() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"AddMaterial");
		String materialName = ExcelUtils.getCellData(1, 0);
		String quantity = ExcelUtils.getCellData(1, 3);
		String uom = ExcelUtils.getCellData(1, 4);
		
		//Requesting a material.
		Material_Card_View_RequestMaterial cardViewRequestMaterial = new Material_Card_View_RequestMaterial();
		cardViewRequestMaterial.CardViewRequestMaterial();
		
		//Navigation to dash-board page.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		InvenoryRegularFunctions dashboardPageNavigation = new InvenoryRegularFunctions();
		dashboardPageNavigation.DashboardPageNavigation();
		Thread.sleep(2000);	
		
		//Verify the material requested log
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,250)");
		String dashboardActivityLog = getWebElement("Inventory.Dashboard.Log").getText();
		String expectedMaterialRequestLog = null;
		if(quantity.contains("."))
			expectedMaterialRequestLog = labUserName+" 1 Unit of "+materialName+" - "+quantity+" "+uom+" Requested"; 
		else
			expectedMaterialRequestLog = labUserName+" 1 Unit of "+materialName+" - "+quantity+".0 "+uom+" Requested";
		
		if(dashboardActivityLog.contains(expectedMaterialRequestLog))
			Reporter.log("Success! Material requested log displayed in dashboard page.");
		else
			softAssertion.fail("Material requested log not displayed in dashboard page."+dashboardActivityLog);
		Utills.captureScreenshot("Material_card_View_Request_Log_Dashboard_Page_Pass_"+TodayDate.Date());		
		
		//Logout from an application.
		InvenoryRegularFunctions logout = new InvenoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}	*/

	@Test(priority = 2)
	public void MaterialDetail_RequestMaterial_DashboardActivityLog() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(20, 0);
		String quantity = ExcelUtils.getCellData(1, 3);
		String uom = ExcelUtils.getCellData(1, 4);
		//String minimumCount = ExcelUtils.getCellData(1, 15);
		//String maximumCount = ExcelUtils.getCellData(1, 16);
		//double count = Double.parseDouble(maximumCount) - 1;
		
		//Requesting a material.
		Material_detail_Request_Material DetailRequestMaterial = new Material_detail_Request_Material();
		DetailRequestMaterial.MaterialDetailRequestMaterial();
		
		//Navigation to dash-board page.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		InventoryRegularFunctions dashboardPageNavigation = new InventoryRegularFunctions();
		dashboardPageNavigation.DashboardPageNavigation();
		Thread.sleep(2000);	
		
		//Verify the material requested log
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,250)");
		String dashboardActivityLog = getWebElement("Inventory.Dashboard.Log").getText();
		String expectedMaterialRequestLog = null;
		if(quantity.contains("."))
			expectedMaterialRequestLog = labUserName+" 1 Unit of "+materialName+" - "+quantity+" "+uom+" Requested"; 
		else
			expectedMaterialRequestLog = labUserName+" 1 Unit of "+materialName+" - "+quantity+".0 "+uom+" Requested";
		
		if(dashboardActivityLog.contains(expectedMaterialRequestLog))
			Reporter.log("Success! Material requested log displayed in dashboard page.");
		else
			softAssertion.fail("Material requested log not displayed in dashboard page."+dashboardActivityLog);
		Utills.captureScreenshot("Material_card_View_Request_Log_Dashboard_Page_Pass_"+TodayDate.Date());		
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void MaterialDetail_AssignLocation_DashboardActivityLog() throws Exception
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
		String materialName = ExcelUtils.getCellData(37, 0);
		String locationName = ExcelUtils.getCellData(37, 5);
		String temperature = "25";
		int rowNumber = 37;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		
		//Navigation to material detail page. Verify material detail page exists or not.
		InventoryRegularFunctions detailPage = new InventoryRegularFunctions();
		detailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Updating material location
		getWebElement("Inventory.MaterialDetail.Location").click();
		Thread.sleep(3000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify storage exits
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
		Assert.assertTrue("Unable to create a location - "+locationName+", as the location already exist in a storage list",(locationFound == false));
		
		//Creation of new location.
		StorageRegularFunctions addLocation = new StorageRegularFunctions();
		addLocation.CreateLocation(locationName, temperature);
		
		//Selects the newly created location
		StorageRegularFunctions selectLocation = new StorageRegularFunctions();
		selectLocation.SelectLocation(locationName);
		
		getWebElement("Storage.SaveButton").click();
		Thread.sleep(4000);
		
		//Verify the location displayed in material detail page
		String selectedLocationName = getWebElement("Inventory.MaterialDetail.Location").getAttribute("value");
		Assert.assertTrue("Location value not displayed in add material modal",selectedLocationName.equals(locationName));
		Reporter.log("Location displayed in material detail page.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(5000);
		
		getWebElement("Inventory.OkButton").click();
		Thread.sleep(2000);
		
		//Verification of success message after updating material details.
		String updatematerialSuccessMessage = getWebElement("Inventory.UpdateMaterialDetails.SuccessMessage").getText();
		if(updatematerialSuccessMessage.equals("Success! Material updated"))
			Reporter.log("The success message displayed successfully as - '"+updatematerialSuccessMessage+"' after updating material details.");
		else
			softAssertion.fail("The success message not displayed after updating material details");
		Utills.captureScreenshot("Update_Material_Success_Message_"+TodayDate.Date());
			
		//Navigation to dash-board page.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		InventoryRegularFunctions dashboardPageNavigation = new InventoryRegularFunctions();
		dashboardPageNavigation.DashboardPageNavigation();
		Thread.sleep(2000);	
		
		//Verify the material requested log
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,250)");
		String dashboardActivityLog = getWebElement("Inventory.Dashboard.Log").getText();
		String expectedMaterialRequestLog = labUserName+" moved "+materialName+" from Unassigned to "+locationName; 
		
		if(dashboardActivityLog.contains(expectedMaterialRequestLog))
			Reporter.log("Success! location log of a material displayed in dashboard page.");
		else
			softAssertion.fail("Storage log not displayed in dashboard page."+dashboardActivityLog);
		Utills.captureScreenshot("Material_card_View_Request_Log_Dashboard_Page_Pass_"+TodayDate.Date());		
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority=5)
	public void Material_Quantity_Updated_DashboardActivityLog() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
				
		Material_CardView_Edit_Quantity editQuantity = new Material_CardView_Edit_Quantity();
		editQuantity.MaterialCardViewPageEditQuantity();
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String updateQuantity = ExcelUtils.getCellData(2, 11);
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(15, 0);
		String quantity = ExcelUtils.getCellData(1, 3);
		String uom = ExcelUtils.getCellData(1, 4);
		
		double qty =0;
		if(Double.parseDouble(updateQuantity) > Double.parseDouble(quantity))
			qty = Double.parseDouble(updateQuantity) - Double.parseDouble(quantity);
		else
			qty = Double.parseDouble(quantity) - Double.parseDouble(updateQuantity);
		
		//Navigation to dash-board page.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		InventoryRegularFunctions dashboardPageNavigation = new InventoryRegularFunctions();
		dashboardPageNavigation.DashboardPageNavigation();
		Thread.sleep(2000);	
		
		//Verify the material requested log
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,250)");
		String dashboardActivityLog = getWebElement("Inventory.Dashboard.Log").getText();
		
		String expectedMaterialRequestLog = null;
		if(Double.parseDouble(updateQuantity) > Double.parseDouble(quantity))
			expectedMaterialRequestLog = labUserName+" added "+qty+" "+uom+" to "+materialName; 
		else
			expectedMaterialRequestLog = labUserName+" consumed "+qty+" "+uom+" of "+materialName; 
		
		if(dashboardActivityLog.contains(expectedMaterialRequestLog))
			Reporter.log("Success! Quantity changed log of a material displayed in dashboard page.");
		else
			softAssertion.fail("Quantity changed log not displayed in dashboard page."+dashboardActivityLog);
		Utills.captureScreenshot("Material_card_View_Request_Log_Dashboard_Page_Pass_"+TodayDate.Date());		
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	
	@Test(priority=6)
	public void Material_Quantity_Consumed_DashboardActivityLog() throws Exception
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
				
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String quantity = ExcelUtils.getCellData(45, 3);
		String uom = ExcelUtils.getCellData(45, 4);
		String materialName = ExcelUtils.getCellData(45, 0);
		int rowNumber = 45;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);		
		Thread.sleep(1000);

		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String updateQuantity = ExcelUtils.getCellData(5, 11);
		
		//Updating the material quantity
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		getWebElement("Inventory.CardView.EditQuantity").click();
		getWebElement("Inventory.CardView.EditQuantity").clear();
		getWebElement("Inventory.CardView.EditQuantity").sendKeys(updateQuantity);
		Thread.sleep(1000);
		getWebElement("Inventory.CardView.EditQuantity.OkButton").click();
		Thread.sleep(3000);
		
		String quantityFieldValidationMessage =  getWebElement("Inventory.CardView.EditQuantity.SuccessMessage").getText();	
		if(quantityFieldValidationMessage.equals("Success! Material quantity updated!"))
			Reporter.log("Success message displayed successfully in materials page when quantity is updated.");
		else 
			softAssertion.fail("Success message not displayed in materials page when quantity is updated.");
		Utills.captureScreenshot("Materials_page_Edit_Quantity_Success_Message_"+TodayDate.Date());
		
		double qty =0;
		if(Double.parseDouble(updateQuantity) > Double.parseDouble(quantity))
			qty = Double.parseDouble(updateQuantity) - Double.parseDouble(quantity);
		else
			qty = Double.parseDouble(quantity) - Double.parseDouble(updateQuantity);
		
		//Navigation to dash-board page.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		InventoryRegularFunctions dashboardPageNavigation = new InventoryRegularFunctions();
		dashboardPageNavigation.DashboardPageNavigation();
		Thread.sleep(2000);	
		
		//Verify the material requested log
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,250)");
		String dashboardActivityLog = getWebElement("Inventory.Dashboard.Log").getText();
		
		String expectedMaterialRequestLog = null;
		if(Double.parseDouble(updateQuantity) > Double.parseDouble(quantity))
			expectedMaterialRequestLog = labUserName+" added "+qty+" "+uom+" to "+materialName; 
		else
			expectedMaterialRequestLog = labUserName+" consumed "+qty+" "+uom+" of "+materialName; 
		
		if(dashboardActivityLog.contains(expectedMaterialRequestLog))
			Reporter.log("Success! Quantity changed log of a material displayed in dashboard page.");
		else
			softAssertion.fail("Quantity changed log not displayed in dashboard page."+dashboardActivityLog);
		Utills.captureScreenshot("Material_card_View_Request_Log_Dashboard_Page_Pass_"+TodayDate.Date());		
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
