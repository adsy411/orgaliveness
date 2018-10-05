package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Material_Detail_Material_Log extends TestBase
{
	SoftAssert softAssertion= new SoftAssert();
	int rowNumber = 81;
	
	@Test(priority = 1)
	public void userLogin() throws Exception
	{
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Material_Added_Log() throws Exception
	{
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String materialQuantity = ExcelUtils.getCellData(rowNumber, 3);
		String materialUOM = ExcelUtils.getCellData(rowNumber, 4);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		Reporter.log("\n");
		Reporter.log("--------- Verify the Material Added Log in Material Activity Modal -----------------------------");

		//Verify the material added log by adding new material
		InventoryRegularFunctions addMaterialLogVerification = new InventoryRegularFunctions();
		addMaterialLogVerification.Verify_Add_Material_Log(materialName, rowNumber, materialQuantity, materialUOM, labUserName);
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void Material_Requested_Log() throws Exception
	{
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String materialUOM = ExcelUtils.getCellData(rowNumber, 4);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Material Requested Log in Material Activity Modal  ------ ");

		//Verify the material requested log by requesting a material from material detail page.
		InventoryRegularFunctions materialrequestedLogVerification = new InventoryRegularFunctions();
		materialrequestedLogVerification.Verify_Material_Requested_Log(materialName, materialUOM, labUserName);
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 4)
	public void Material_Quantity_Updated_Log() throws Exception
	{
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String materialUOM = ExcelUtils.getCellData(rowNumber, 4);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Material Quantity Updated Log in Material Activity Modal  ------");
		
		//Verify the material quantity updated log by updating material quantity a material from material card view page.
		InventoryRegularFunctions materialQuantityUpdatedLogVerification = new InventoryRegularFunctions();
		materialQuantityUpdatedLogVerification.Verify_Material_Quantity_Updated_Log(materialName, materialUOM, labUserName);
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 5)
	public void Material_Quantity_Consumed_Log() throws Exception
	{	
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String materialUOM = ExcelUtils.getCellData(rowNumber, 4);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Material Quantity Consumed Log in Material Activity Modal  ------");
		
		//Verify the material quantity updated log by updating material quantity a material from material card view page.
		InventoryRegularFunctions materialQuantityUpdatedLogVerification = new InventoryRegularFunctions();
		materialQuantityUpdatedLogVerification.Verify_Material_Quantity_Consumed_Log(materialName, materialUOM, labUserName);
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 6)
	public void Partial_Material_Disposed_Log() throws Exception
	{
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String materialUOM = ExcelUtils.getCellData(rowNumber, 4);
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String disposeMaterialQuantity = ExcelUtils.getCellData(2, 3);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Partial Material Disposed Log in Material Activity Modal  ------");
		
		//Verify the material disposed log by disposing a material from material detail page.
		InventoryRegularFunctions materialDisposedLogVerification = new InventoryRegularFunctions();
		materialDisposedLogVerification.Verify_Partial_Material_Disposed_Log(materialName, disposeMaterialQuantity, materialUOM, labUserName);
	
		softAssertion.assertAll();
	}
	
	@Test(priority = 7)
	public void Location_Assigned_Log() throws Exception
	{	
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Storage");
		String locationName = ExcelUtils.getCellData(10, 6);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Location assigned Log in Material Activity Modal  ------");
		
		//Verify the location assigned log by assigning location to a material from material detail page.
		InventoryRegularFunctions locationAssignedLogVerification = new InventoryRegularFunctions();
		locationAssignedLogVerification.Verify_location_Assigned_Log(materialName, locationName, labUserName);
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 8)
	public void Location_Changed_Log() throws Exception
	{	
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String locationName = ExcelUtils.getCellData(rowNumber, 5);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Location Changed Log in Material Activity Modal  ------");
		
		//Verify the location assigned log by assigning another location to a material from material detail page.
		InventoryRegularFunctions locationChangedLogVerification = new InventoryRegularFunctions();
		locationChangedLogVerification.Verify_location_Changed_Log(materialName, locationName, labUserName);
		
		softAssertion.assertAll();
	}	
	
	@Test(priority = 9)
	public void Location_Unassigned_Log() throws Exception
	{	
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String locationName = ExcelUtils.getCellData(rowNumber, 5);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Location Unassigned Log in Material Activity Modal  ------");
		
		//Verify the location assigned log by assigning location to a material from material detail page.
		InventoryRegularFunctions locationUnassignedLogVerification = new InventoryRegularFunctions();
		locationUnassignedLogVerification.Verify_location_Unassigned_Log(materialName, locationName, labUserName);
		
		softAssertion.assertAll();
	}	
	
	@Test(priority = 10)
	public void Complete_Material_Disposed_Log() throws Exception
	{	
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String materialUOM = ExcelUtils.getCellData(rowNumber, 4);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Complete Material Disposed Log in Material Activity Modal  ------");
		
		//Verify the complete material disposed log by disposing a material from materials page.
		InventoryRegularFunctions materialDisposedLogVerification = new InventoryRegularFunctions();
		materialDisposedLogVerification.Verify_Complete_Material_Disposed_Log(materialName, materialUOM, labUserName);
	
		softAssertion.assertAll();
	}	
	
	@Test(priority = 11)
	public void Disposed_Material_Reactivation_Log() throws Exception
	{
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String materialUOM = ExcelUtils.getCellData(rowNumber, 4);
		String materialQuantity = ExcelUtils.getCellData(rowNumber, 3);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Get the user name after login in to application.
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText();
		
		Reporter.log("\n");
		Reporter.log("------  Verify the Disposed Material Reactivation Log in Material Activity Modal  ------");
		
		//Verify the complete material disposed log by disposing a material from materials page.
		InventoryRegularFunctions materialDisposedLogVerification = new InventoryRegularFunctions();
		materialDisposedLogVerification.Verify_Disposed_Material_Reactivation_Log(materialName, materialQuantity, materialUOM, labUserName);

		softAssertion.assertAll();
	}
	
	@Test(priority = 12)
	public void userLogout() throws Exception
	{
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}
		
		
}