package inventory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Add_Material_Auto_Complete_Update_Existing_Sigma_Vendor_Product extends TestBase
{
	@Test
	public void AddMaterialUpdateExistingSigmaVendorProduct() throws Exception
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
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_SigmaVendorMaterial");
		String catalogNumber = ExcelUtils.getCellData(2, 1);
		String quantity = ExcelUtils.getCellData(2, 3);
		String uom = ExcelUtils.getCellData(2, 4);
		String casNumber = ExcelUtils.getCellData(2, 6);
		String lotNumber = ExcelUtils.getCellData(2, 7);
		String manufactredDate = ExcelUtils.getCellData(2, 8);
		String brand = ExcelUtils.getCellData(2, 10);
		String url = ExcelUtils.getCellData(2, 11);
		String boilingPoint = ExcelUtils.getCellData(2, 12);
		String meltingPoint = ExcelUtils.getCellData(2, 13);
		String flashingPoint = ExcelUtils.getCellData(2, 14);
		String minimumCount = ExcelUtils.getCellData(2, 15);
		String maximumCount = ExcelUtils.getCellData(2, 16);
		String materialType = ExcelUtils.getCellData(2, 17);
		String description = ExcelUtils.getCellData(2, 18);
		
		//Adding Sigma Vendor material
		AddSigmaMaterial AddMaterial = new AddSigmaMaterial();
		AddMaterial.AddSigmaAldrichVendorMaterial(catalogNumber);
				
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
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material page displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date()); 
 
		//Verify Auto Complete modal exist
		Reporter.log("Enter the material details and add it to Inventory");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		Thread.sleep(5000);
			
		WebElement verifyAutoCompleteTable = getWebElement("Inventory.AddMaterial.VerifyAutoCompleteTable");
		Assert.assertTrue("Auto complete modal not displayed for the entered catalog number -"+catalogNumber,verifyAutoCompleteTable.isDisplayed());
		Utills.captureScreenshot("Add_Material_Auto_Complete_Modal_"+TodayDate.Date());

		//Entering the material details
		WebElement materialFound = null;
		try
		{
			materialFound = driver.findElement(By.xpath("//tr[@data-item-group='Lab Catalog'][1]/td[contains(text(),'"+catalogNumber+"')]"));
			materialFound.isDisplayed();
		}
		catch(NoSuchElementException e) 
		{
			Assert.fail("Material does not exist in auto complete modal");
			Utills.captureScreenshot("Add_Material_Auto_Complete_Modal_Fail"+TodayDate.Date());	
		}
		
		materialFound.click();
		Thread.sleep(3000);
		getWebElement("Inventory.Quantity").clear();
		getWebElement("Inventory.Quantity").sendKeys(quantity);
			
		//Verify the provided UOM exists in list
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.UOM",uom) == true)
			SelectList.SelectByValue("Inventory.UOM",uom);
		else
			Assert.fail("The UOM - "+uom+" does not exist");
		
		//Verify the provided material type exists in list
		if(SelectList.VerifySelectList("Inventory.MaterialType",materialType) == true)
			SelectList.SelectByVisibleText("Inventory.MaterialType",materialType);
		else
			softAssertion.fail("The Material Type - "+materialType+" does not exist");
		
		Thread.sleep(1000);
		
		//Clicks on "Other Details" tab.
		getWebElement("Inventory.OtherDetailsTab").click();
		impliciteWait(2);
		
		getWebElement("Inventory.AddCasNumber").click();
		getWebElement("Inventory.AddCasNumber").sendKeys(casNumber);
		getWebElement("Inventory.AddLotNumber").sendKeys(lotNumber);
		getWebElement("Inventory.AddManufacturedDate").sendKeys(manufactredDate);
		getWebElement("Inventory.AddBrand").sendKeys(brand);
		getWebElement("Inventory.AddURL").sendKeys(url);
		getWebElement("Inventory.AddBoilingPoint").clear();
		getWebElement("Inventory.AddBoilingPoint").sendKeys(boilingPoint);
		getWebElement("Inventory.AddMeltingPoint").clear();
		getWebElement("Inventory.AddMeltingPoint").sendKeys(meltingPoint);
		getWebElement("Inventory.FlashingPoint").clear();
		getWebElement("Inventory.FlashingPoint").sendKeys(flashingPoint);
		getWebElement("Inventory.MinimumCount").sendKeys(minimumCount);
		getWebElement("Inventory.MaximumCount").sendKeys(maximumCount);
		getWebElement("Inventory.Description").click();
		getWebElement("Inventory.Description").sendKeys(description);
					
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(3000);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddLabCatalogMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.", addMaterialConfirmationModal.equals("Add Material"));
		Reporter.log("Add Material confirmation modal displayed successfully");
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());
		getWebElement("Inventory.UpdateButton").click();
		impliciteWait(5);
		
		//Verification of Success message after addition of new material.
		Thread.sleep(2000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			Reporter.log("Success Message displayed successfully after addition of new material using existing sigma vendor product details as - "+ActualSuccessMessage);
		else 
			softAssertion.fail("Success Message not displayed after addition of new material using existing sigma vendor product details.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material using existing sigma vendor product details, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After addition of new material using existing sigma vendor product details, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
				
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(MaterialsCountAfter == (materialsCountBefore+1))
			Reporter.log("After addition of new material the material using existing sigma vendor product details, count is increased by "+(MaterialsCountAfter-materialsCountBefore));
		else 
			softAssertion.fail("After addition of new material using existing sigma vendor product details, the material count is not increased.");
		
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}