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

public class Materials_Page_Card_Color_Verification extends TestBase
{
	@Test(priority = 1)
	public void Verify_Card_Color_Min_Count_Null() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 58;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);		
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
	
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Navigate to Add Material modal, enter the material details and add it to Inventory");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Entering the material details in Add Material Modal
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
			SelectList.SelectByVisibleText("Inventory.Vendor","Add Vendor");
			Thread.sleep(1000);
			getWebElement("Inventory.AddVendor").sendKeys(vendorName);
			Thread.sleep(1000);
		}
		
		//Adds materials to inventory
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(4000);
		impliciteWait(3);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.", addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		getWebElement("Inventory.OkButton").click();
		impliciteWait(5);
		Thread.sleep(1000);
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());

		//Verification of Success message after addition of new material.
		Thread.sleep(2000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			System.out.println("Success Message displayed as - "+ActualSuccessMessage+" after addition of new material");
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
		Reporter.log("Newly created material - "+materialName+" displayed at the top of the page.");
	
		//Verify the card color when min and max count is not set
		try
		{
			getWebElement("Inventory.Material.Card.Color.Green").isDisplayed();
			Reporter.log("Green Card color displayed for material - "+materialName+" When min count value is not set.");
		}
		catch (Exception e) 
		{
			softAssertion.fail("Green Card color not displayed for material - "+materialName+" When min count value is not set.");
		}
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Verify_Card_Color_MaterialCount_GreaterThan_MinCount() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 59;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);		
		
		//Adding third party Vendor material 
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Adding third party Vendor material 
		AddThirdPartyMaterial AddMaterial1 = new AddThirdPartyMaterial();
		AddMaterial1.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Verify the card color when material count is greater than min count.
		try
		{
			getWebElement("Inventory.Material.Card.Color.Green").isDisplayed();
			Reporter.log("Green Card color displayed for material - "+materialName+" When materials count is greater than min count.");
		}
		catch (Exception e) 
		{
			softAssertion.fail("Green Card color not displayed for material - "+materialName+" When materials count is greater than min count.");
		}
		
		///Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void Verify_Card_Color_MaterialCount_EqualTo_MinCount() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 60;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);		
		
		//Adding third party Vendor material 
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Verify the card color when material count is equal to min count.
		try
		{
			getWebElement("Inventory.Material.Card.Color.Yellow").isDisplayed();
			Reporter.log("Yellow Card color displayed for material - "+materialName+" When materials count is equal to min count.");
		}
		catch (Exception e) 
		{
			softAssertion.fail("Yellow Card color not displayed for material - "+materialName+" When materials count is equal to min count.");
		}
		
		///Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 4)
	public void Verify_Card_Color_MaterialCount_LessThan_MinCount() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 61;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);		
		
		//Adding third party Vendor material 
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Verify the card color when material count is equal to min count.
		try
		{
			getWebElement("Inventory.Material.Card.Color.Red").isDisplayed();
			Reporter.log("Red Card color displayed for material - "+materialName+" When materials count is less than min count.");
		}
		catch (Exception e) 
		{
			softAssertion.fail("Red Card color not displayed for material - "+materialName+" When materials count is less than min count.");
		}
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
