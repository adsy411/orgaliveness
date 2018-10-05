package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class CL1456_Quantity_Field_Validation extends TestBase
{
	@Test(priority = 1)
	public void AddMaterial_ValidationMesaageScenarios() throws Exception
	{
		//SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		init();
				
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
	
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Validation_Scenario");
		String materialName = ExcelUtils.getCellData(2, 0);
		String catalogNumber = ExcelUtils.getCellData(2, 1);
		String vendorName = ExcelUtils.getCellData(2, 2);
		String quantity = ExcelUtils.getCellData(2, 3);
		String uom = ExcelUtils.getCellData(2, 4);
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		impliciteWait(2);
				
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material page displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		
		
		
		
		
		
		//Verify Validation messages in Add Material Modal
		Reporter.log("Enter the material details and add it to Inventory");
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMateriName").sendKeys(materialName);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
				
		//Check whether the vendor exists or not.
			Library SelectList = new Library();
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
				Thread.sleep(2000);
				getWebElement("Inventory.AddVendor").sendKeys(vendorName);
				Thread.sleep(1000);
				impliciteWait(1);
			}
				
			getWebElement("Inventory.Quantity").sendKeys(quantity);
		
			//Verify the provided UOM exists in list
			if(SelectList.VerifySelectList("Inventory.UOM",uom) == true)
			{
				SelectList.SelectByValue("Inventory.UOM",uom);
				Thread.sleep(1000);
			}
			else
			{
				Reporter.log("The UOM - "+uom+" does not exist");
			}
			
			getWebElement("Inventory.AddToInventoryButton").click();
			Thread.sleep(5000);
			String quantityFieldValidationMessage = getWebElement("Inventory.AddMaterial.QuantityErrorMessage").getText();
			if(quantityFieldValidationMessage.equals("Quantity should be less than or equal to 9999999"))
			{
				Reporter.log("Validation message displayed successfully in add material page for quantity field which contains quantity more than 9999999");
				Utills.captureScreenshot("Add_Material_Quantity_Field_Validation_Message_"+TodayDate.Date());
			}
			else 
			{
				Reporter.log("Validation message not displayed in add material page for quantity field which contains quantity more than 9999999");
				Utills.captureScreenshot("Add_Material_Quantity_Field_Validation_Message_"+TodayDate.Date());
			}
		}
		
		/*Logout from an application.
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();	*/
		
	@Test(priority = 2)
	public void MaterialCardViewPage_ValidationMessageScenarios() throws Exception
	{
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		init();
				
		//Login in to the application
		Reporter.log("Login to Application with specific user");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").click();
		getWebElement("Enotebook.login.username").sendKeys(userName);
		getWebElement("Enotebook.login.password").click();
		getWebElement("Enotebook.login.password").sendKeys(password);
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		impliciteWait(5);
		
		//Verify dashboard page exists or not.
		boolean dashboardPageExist = false;
		String DashboardPageName = getWebElement("Inventory.PageHeading").getText();
		if(DashboardPageName.equals("Dashboard"))
		{
			Reporter.log("User logged in successfully in to an application");
			Utills.captureScreenshot("Dashboard_Page_Pass"+TodayDate.Date());
			dashboardPageExist = true;
		}
		else 
		{
			Reporter.log("User not logged in successfully in to an application.");
			Utills.captureScreenshot("Dashboard_Page_Fail"+TodayDate.Date());
			dashboardPageExist = false;
		}
	
		//Takes the input from excel and stores it in a variable.	
				ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
				String materialName = ExcelUtils.getCellData(3, 0);
				String catalogNumber = ExcelUtils.getCellData(3, 1);
				
				int rowNumber = 4;	
		//Adding third party Vendor material
		boolean materialAdded = false;
		if(dashboardPageExist == true)
		{
			//Adding third party Vendor material
			AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
			AddMaterial.AddThirdPartyVendorMaterial(rowNumber);;
			
			materialAdded = true;
			Thread.sleep(1000);
		}
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Update_Material");
		String updateQuantity = ExcelUtils.getCellData(3, 3);
		
		if(materialAdded == true)
		{
			getWebElement("Inventory.CardView.EditQuantityIcon").click();
			Thread.sleep(2000);
			getWebElement("Inventory.CardView.EditQuantity").click();
			getWebElement("Inventory.CardView.EditQuantity").clear();
			getWebElement("Inventory.CardView.EditQuantity").sendKeys(updateQuantity);
			Thread.sleep(1000);
			getWebElement("Inventory.CardView.EditQuantity.OkButton").click();
			Thread.sleep(2000);
			String quantityFieldValidationMessage =  getWebElement("Inventory.CardView.QuantityField.ValidationMessage").getText();
			
			if(quantityFieldValidationMessage.equals("Warning! Quantity should be lesser than 9999999!"))
			{
				Reporter.log("Validation message displayed successfully in materials page for quantity field which contains quantity more than 9999999");
				Utills.captureScreenshot("Materials_Page_Quantity_Field_Validation_Message_"+TodayDate.Date());
			}
			else 
			{
				Reporter.log("Validation message not displayed in materials page for quantity field which contains quantity more than 9999999");
				Utills.captureScreenshot("Materials_page_Quantity_Field_Validation_Message_"+TodayDate.Date());
			}		
		}
		
		//Logout from an application.
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();	
	}
	
	@Test(priority = 3)
	public void MaterialDetailPage_ValidationMessageScenarios() throws Exception
	{
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		init();
				
		//Login in to the application
		Reporter.log("Login to Application with specific user");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").click();
		getWebElement("Enotebook.login.username").sendKeys(userName);
		getWebElement("Enotebook.login.password").click();
		getWebElement("Enotebook.login.password").sendKeys(password);
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		impliciteWait(5);
		
		//Verify dashboard page exists or not.
		boolean dashboardPageExist = false;
		String DashboardPageName = getWebElement("Inventory.PageHeading").getText();
		if(DashboardPageName.equals("Dashboard"))
		{
			Reporter.log("User logged in successfully in to an application");
			Utills.captureScreenshot("Dashboard_Page_Pass"+TodayDate.Date());
			dashboardPageExist = true;
		}
		else 
		{
			Reporter.log("User not logged in successfully in to an application.");
			Utills.captureScreenshot("Dashboard_Page_Fail"+TodayDate.Date());
			dashboardPageExist = false;
		}
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String addMaterialName = ExcelUtils.getCellData(3, 0);
		String catalogNumber = ExcelUtils.getCellData(3, 1);
		
		
//Adding third party Vendor material
boolean materialAdded = false;
int rowNumber = 4;
if(dashboardPageExist == true)
{
	//Adding third party Vendor material
	AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
	AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
	
	materialAdded = true;
	Thread.sleep(1000);
}
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Update_Material");
		String updateQuantity = ExcelUtils.getCellData(3, 3);
		String materialName = ExcelUtils.getCellData(3, 2);
		
		//Navigation to material detail page.
		boolean detailPageExist = false;
		if(materialAdded == true)
		{
			Thread.sleep(1000);
			getWebElement("Inventory.MaterialDetailNavigation").click();
			impliciteWait(2);
							
			//Verify material detail page exists or not.
			String materialDetailPageName = getWebElement("Inventory.PageHeading").getText();
			if(materialDetailPageName.equals(materialName))
			{
				Reporter.log("User redirected to Material detail page of material -"+materialName);
				detailPageExist = true;
				Utills.captureScreenshot("Material_Detail_P_"+TodayDate.Date());
			}	
			else
			{
				Reporter.log("User not redirected to Material detail page of material -"+materialName);
				detailPageExist = false;
				Utills.captureScreenshot("Material_Detail_F_"+TodayDate.Date());
			}		
		}
						
		// Updating quantity
		if (detailPageExist == true) 
		{
			getWebElement("Inventory.Material.UpdateQuantity").click();
			getWebElement("Inventory.Material.UpdateQuantity").clear();
			getWebElement("Inventory.Material.UpdateQuantity").sendKeys(updateQuantity);
			getWebElement("Inventory.UpdateMaterialDetailsButton").click();
			Thread.sleep(1000);
			String quantityFieldValidationMessage = getWebElement("Inventory.DetailPage.QuantityField.ValidationMessage").getText();
			
			if(quantityFieldValidationMessage.equals("Quantity should be less than or equal to 9999999"))
			{
				Reporter.log("Validation message displayed successfully in material detail page for quantity field which contains quantity more than 9999999");
				Utills.captureScreenshot("Material_Detail_Page_Quantity_Field_Validation_Message_"+TodayDate.Date());
			}
			else 
			{
				Reporter.log("Validation message not displayed in material detail page for quantity field which contains quantity more than 9999999");
				Utills.captureScreenshot("Material_Detail_page_Quantity_Field_Validation_Message_"+TodayDate.Date());
			}		
		}
		
		//Logout from an application.
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();	
	}
}
