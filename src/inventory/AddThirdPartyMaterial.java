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

public class AddThirdPartyMaterial extends TestBase
{
	@Test
	public String AddThirdPartyVendorMaterial(int rowNumber) throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
		String casNumber = ExcelUtils.getCellData(rowNumber, 6);
		String lotNumber = ExcelUtils.getCellData(rowNumber, 7);
		String manufactredDate = ExcelUtils.getCellData(rowNumber, 8);
		String expirationDate = ExcelUtils.getCellData(rowNumber, 9);
		String brand = ExcelUtils.getCellData(rowNumber, 10);
		String url = ExcelUtils.getCellData(rowNumber, 11);
		String boilingPoint = ExcelUtils.getCellData(rowNumber, 12);
		String meltingPoint = ExcelUtils.getCellData(rowNumber, 13);
		String flashingPoint = ExcelUtils.getCellData(rowNumber, 14);
		String minimumCount = ExcelUtils.getCellData(rowNumber, 15);
		String maximumCount = ExcelUtils.getCellData(rowNumber, 16);
		String materialType = ExcelUtils.getCellData(rowNumber, 17);
		String description = ExcelUtils.getCellData(rowNumber, 18);
	
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		inventoryRegularFunctions.MaterialPageNavigation();
		
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
			inventoryRegularFunctions.Add_Material_Page_Add_New_Vendor(vendorName);
		
		//Verify the provided material type exists in list
		if(SelectList.VerifySelectList("Inventory.MaterialType",materialType) == true)
			SelectList.SelectByVisibleText("Inventory.MaterialType",materialType);
		else
			softAssertion.fail("The Material Type - "+materialType+" does not exist");
		
		//Clicks on "Other Details" tab.
		Thread.sleep(1000);
		getWebElement("Inventory.OtherDetailsTab").click();
		impliciteWait(2);
		
		getWebElement("Inventory.AddCasNumber").click();
		getWebElement("Inventory.AddCasNumber").sendKeys(casNumber);
		getWebElement("Inventory.AddLotNumber").sendKeys(lotNumber);
		getWebElement("Inventory.AddManufacturedDate").sendKeys(manufactredDate);
		getWebElement("Inventory.AddExpiredDate").sendKeys(expirationDate);
		getWebElement("Inventory.AddBrand").sendKeys(brand);
		getWebElement("Inventory.AddURL").sendKeys(url);
		getWebElement("Inventory.AddBoilingPoint").sendKeys(boilingPoint);
		getWebElement("Inventory.AddMeltingPoint").sendKeys(meltingPoint);
		getWebElement("Inventory.FlashingPoint").sendKeys(flashingPoint);
		getWebElement("Inventory.MinimumCount").sendKeys(minimumCount);
		getWebElement("Inventory.MaximumCount").sendKeys(maximumCount);
		Thread.sleep(1000);
		getWebElement("Inventory.Description").click();
		getWebElement("Inventory.Description").sendKeys(description);
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(3000);
		impliciteWait(3);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.", addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		
		//This date function is for log modal purpose
		InventoryRegularFunctions date = new InventoryRegularFunctions();
		String materialAddedDate = date.Date_For_MaterialLog();
		
		//Clicks on Ok button.
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
		Assert.assertTrue(materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equals(materialName));
		Reporter.log("Material - '"+materialName+"' created and displayed successfully in materials page.");
		
		return materialAddedDate;
	}
}
