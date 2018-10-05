package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Add_Material_Error_Message_Validation_Scenario extends TestBase
{
	@Test(priority = 1)
	public void AddMaterial_Error_Message_Validation_Scenario() throws Exception
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
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(1, 0);
		String vendorName = ExcelUtils.getCellData(1, 2);
		String quantity = ExcelUtils.getCellData(1, 3);
		String materialManufactredDate = ExcelUtils.getCellData(1, 8);
		String materialExpirationDate = ExcelUtils.getCellData(1, 9);
		String boilingPoint = ExcelUtils.getCellData(1, 12);
		String meltingPoint = ExcelUtils.getCellData(1, 13);
		String flashingPoint = ExcelUtils.getCellData(1, 14);
		String maximumCount = ExcelUtils.getCellData(1, 16);
		String materialsCount = ExcelUtils.getCellData(1, 19);
		String materialType = ExcelUtils.getCellData(1, 17);
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Validation_Scenario");
		String zeroQuantity = ExcelUtils.getCellData(2, 0);
		String greaterQuantity = ExcelUtils.getCellData(3, 0);
		String invalidQuantity = ExcelUtils.getCellData(4, 0);
		String negativeQuantity = ExcelUtils.getCellData(5, 0);
		String manufactredDate = ExcelUtils.getCellData(2, 1);
		String expiredDate = ExcelUtils.getCellData(2, 2);
		String boilPoint = ExcelUtils.getCellData(2, 3);
		String meltPoint = ExcelUtils.getCellData(2, 4);
		String flashPoint = ExcelUtils.getCellData(2, 5);
		String zeroMinCount = ExcelUtils.getCellData(2, 6);
		String greaterMinCount = ExcelUtils.getCellData(3, 6);
		String minCount = ExcelUtils.getCellData(4, 6);
		String doubleMinCount = ExcelUtils.getCellData(5, 6);
		String negativeMinCount = ExcelUtils.getCellData(6, 6);
		String zeroMaxCount = ExcelUtils.getCellData(2, 7);
		String greaterMaxCount = ExcelUtils.getCellData(3, 7);
		String lesserMaxCount = ExcelUtils.getCellData(4, 7);
		String zeroMaterialCount = ExcelUtils.getCellData(2, 8);
		String greaterMaterialCount = ExcelUtils.getCellData(3, 8);
		String invalidMaterialCount = ExcelUtils.getCellData(4, 8);
		String negativeMaterialCount = ExcelUtils.getCellData(5, 8);
		String floatMaterialCount = ExcelUtils.getCellData(6, 8);
		String existingUOM = ExcelUtils.getCellData(2, 9);
		String greaterUOMCount = ExcelUtils.getCellData(3, 9);
		InventoryRegularFunctions uomDate = new InventoryRegularFunctions();
		String uom = uomDate.Date();
		Library SelectList = new Library();
		
		//Navigation to Add material modal and Verify Validation messages in Add Material Modal
		InventoryRegularFunctions addMaterialModalNavigation = new InventoryRegularFunctions();
		addMaterialModalNavigation.AddMaterialModalNavigation();
		Reporter.log("Verify the validation messages in Add Material Page");
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for material name field when it contains null value
		Reporter.log("Verification of validation message for material name field when it contains null value.");
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedMNValidationMsg = "Name is required";
		String actualMNValidationMsg = getWebElement("Inventory.AddMaterial.MaterialNameValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Material_Name_Validation_Message_"+TodayDate.Date());
		if(expectedMNValidationMsg.equalsIgnoreCase(actualMNValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualMNValidationMsg+"'");
		else
			softAssertion.fail("Validation message not displayed when material name field is empty. The message displayed as  - "+actualMNValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for catalog field when it contains null value
		Reporter.log("Verification of validation message for catalog field when it contains null value.");
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMateriName").sendKeys(materialName);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedCatalogValidationMsg = "Catalog is required";
		String actualCatalogValidationMsg = getWebElement("Inventory.AddMaterial.CatalogValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Catalog_Validation_Message_"+TodayDate.Date());
		if(expectedCatalogValidationMsg.equalsIgnoreCase(actualCatalogValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualCatalogValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when catalog field is empty. The message displayed as  - "+actualCatalogValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for quantity field when it contains null value.
		Reporter.log("Verification of validation message for quantity field when it contains null value.");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(negativeQuantity);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedQtyValidationMsg = "Quantity is required";
		String actualQtyValidationMsg = getWebElement("Inventory.AddMaterial.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Qty_Null_Validation_Message_"+TodayDate.Date());
		if(expectedQtyValidationMsg.equalsIgnoreCase(actualQtyValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualQtyValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when quantity field is empty. The message displayed as  - "+actualQtyValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message when quantity field contains value Zero(0).
		Reporter.log("Verification of validation message for Quantity field when it contains a value Zero(0).");
		getWebElement("Inventory.Quantity").click();
		getWebElement("Inventory.Quantity").sendKeys(zeroQuantity);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedZeroQtyValidationMsg = "Quantity should be greater than 0";
		String actualZeroQtyValidationMsg = getWebElement("Inventory.AddMaterial.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Qty_Zero_Validation_Message_"+TodayDate.Date());
		if(expectedZeroQtyValidationMsg.equalsIgnoreCase(actualZeroQtyValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualZeroQtyValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when qunatity is zero. The message displayed as  - "+actualZeroQtyValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message when quantity field contains negative value.
		Reporter.log("Verification of validation message for Quantity field when it contains negative value.");
		getWebElement("Inventory.AddCatalogNumber").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		getWebElement("Inventory.Quantity").click();
		getWebElement("Inventory.Quantity").clear();
		getWebElement("Inventory.Quantity").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedNegativeQtyValidationMsg = "Quantity should be greater than 0";
		String actualNegativeQtyValidationMsg = getWebElement("Inventory.AddMaterial.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Qty_Negative_Validation_Message_"+TodayDate.Date());
		if(expectedNegativeQtyValidationMsg.equalsIgnoreCase(actualNegativeQtyValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualNegativeQtyValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when qunatity field contains negative value. The message displayed as  - "+actualNegativeQtyValidationMsg);	
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message when quantity field contains greater than 9999999.
		Reporter.log("Verification of validation message for Quantity field when it contains a value greater than 9999999.");
		getWebElement("Inventory.Quantity").click();
		getWebElement("Inventory.Quantity").clear();
		getWebElement("Inventory.Quantity").sendKeys(greaterQuantity);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedGreaterQtyValidationMsg = "Quantity should be less than or equal to 9999999";
		String actualGreaterQtyValidationMsg = getWebElement("Inventory.AddMaterial.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Greater_Qty_Validation_Message_"+TodayDate.Date());
		if(expectedGreaterQtyValidationMsg.equalsIgnoreCase(actualGreaterQtyValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualGreaterQtyValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when qunatity is more than 9999999. The message displayed as  - "+actualGreaterQtyValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
			
		//Verify validation message when quantity field contains invalid value.
		Reporter.log("Verification of validation message for Quantity field when it contains invalid value.");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(invalidQuantity);
		getWebElement("Inventory.AddCatalogNumber").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		getWebElement("Inventory.Quantity").click();
		getWebElement("Inventory.Quantity").clear();
		getWebElement("Inventory.Quantity").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedInvalidQtyValidationMsg = "Quantity should be a numeric value.";
		String actualInvalidQtyValidationMsg = getWebElement("Inventory.AddMaterial.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Invalid_Qty_Validation_Message_"+TodayDate.Date());
		if(expectedInvalidQtyValidationMsg.equalsIgnoreCase(actualInvalidQtyValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualInvalidQtyValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when qunatity field contains invalid value. The message displayed as  - "+actualInvalidQtyValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for UOM is not selected.
		Reporter.log("Verification of validation message for UOM field when UOM is not selected");
		getWebElement("Inventory.Quantity").click();
		getWebElement("Inventory.Quantity").clear();
		getWebElement("Inventory.Quantity").sendKeys(quantity);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedUOMValidationMsg = "Unit is required";
		String actualUOMValidationMsg = getWebElement("Inventory.AddMaterial.UOMValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_UOM_Validation_Message_"+TodayDate.Date());
		if(expectedUOMValidationMsg.equalsIgnoreCase(actualUOMValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualUOMValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when UOM field contains null value. The message displayed as  - "+actualUOMValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for UOM field when UOM field contains blank value.
		Reporter.log("Verification of validation message for UOM field when it contains null value while creation of new UOM.");
		if(SelectList.VerifySelectList("Inventory.UOM","Add Unit of Measure") == true)
			SelectList.SelectByVisibleText("Inventory.UOM","Add Unit of Measure");
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedUOMBlankValidationMsg = "Unit field cannot be empty";
		String actualUOMBlankValidationMsg = getWebElement("Inventory.AddMaterial.UOMValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_UOM_Blank_Validation_Message_"+TodayDate.Date());
		if(expectedUOMBlankValidationMsg.equalsIgnoreCase(actualUOMBlankValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualUOMBlankValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when UOM field contains null value. The message displayed as  - "+actualUOMBlankValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for UOM field when UOM field value limit exceeds
		Reporter.log("Verification of validation message for UOM field when it exceeds the limit while creation of new UOM.");
		getWebElement("Inventory.AddMateriaPage.AddUOM").click();
		getWebElement("Inventory.AddMateriaPage.AddUOM").clear();
		getWebElement("Inventory.AddMateriaPage.AddUOM").sendKeys(greaterUOMCount);
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedUOMLimitExceedsValidationMsg = "Unit of Measure must be 10 or fewer characters";
		String actualUOMLimitExceedsValidationMsg = getWebElement("Inventory.AddMaterial.UOMValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_UOM_Limit_Exceeds_Validation_Message_"+TodayDate.Date());
		if(expectedUOMLimitExceedsValidationMsg.equalsIgnoreCase(actualUOMLimitExceedsValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualUOMLimitExceedsValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when UOM field when UOM field value limit exceeds. The message displayed as  - "+actualUOMLimitExceedsValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for UOM field when UOM field contains blank value.
		Reporter.log("Verification of validation message for UOM field when it contains null value while creation of new UOM.");
		getWebElement("Inventory.AddMateriaPage.AddUOM").click();
		getWebElement("Inventory.AddMateriaPage.AddUOM").clear();
		Thread.sleep(1000);
		getWebElement("Inventory.AddMaterial.SaveUOM.Icon").click();
		Thread.sleep(1000);
		String expectedUOMFiledBlankValidationMsg = "Unit field cannot be empty";
		String actualUOMFieldBlankValidationMsg = getWebElement("Inventory.AddMaterial.UOMValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_UOM_Blank_Validation_Message_"+TodayDate.Date());
		if(expectedUOMFiledBlankValidationMsg.equalsIgnoreCase(actualUOMFieldBlankValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualUOMFieldBlankValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when UOM field contains null value. The message displayed as  - "+actualUOMFieldBlankValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for UOM field when UOM field value limit exceeds
		Reporter.log("Verification of validation message for UOM field when it exceeds the limit while creation of new UOM.");
		getWebElement("Inventory.AddMateriaPage.AddUOM").click();
		getWebElement("Inventory.AddMateriaPage.AddUOM").clear();
		getWebElement("Inventory.AddMateriaPage.AddUOM").sendKeys(greaterUOMCount);
		Thread.sleep(1000);
		getWebElement("Inventory.AddMaterial.SaveUOM.Icon").click();
		Thread.sleep(1000);
		String expectedUOMFieldLimitExceedsValidationMsg = "Unit of Measure must be 10 or fewer characters";
		String actualUOMFieldLimitExceedsValidationMsg = getWebElement("Inventory.AddMaterial.UOMValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_UOM_Limit_Exceeds_Validation_Message_"+TodayDate.Date());
		if(expectedUOMFieldLimitExceedsValidationMsg.equalsIgnoreCase(actualUOMFieldLimitExceedsValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualUOMFieldLimitExceedsValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when UOM field when UOM field value limit exceeds. The message displayed as  - "+actualUOMFieldLimitExceedsValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for UOM field when existing UOM is added
		Reporter.log("Verification of validation message for UOM field when existing UOM is recreated.");
		getWebElement("Inventory.AddMateriaPage.AddUOM").click();
		getWebElement("Inventory.AddMateriaPage.AddUOM").clear();
		getWebElement("Inventory.AddMateriaPage.AddUOM").sendKeys(existingUOM);
		Thread.sleep(1000);
		getWebElement("Inventory.AddMaterial.SaveUOM.Icon").click();
		Thread.sleep(1000);
		String expectedUOMExistsValidationMsg = "Unit already present";
		String actualUOMExistsValidationMsg = getWebElement("Inventory.AddMaterial.UOMValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_UOM_Exists_Validation_Message_"+TodayDate.Date());
		if(expectedUOMExistsValidationMsg.equalsIgnoreCase(actualUOMExistsValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualUOMExistsValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when user tries to create an existing UOM. The message displayed as  - "+actualUOMExistsValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for UOM field when UOM is not saved
		Reporter.log("Verification of validation message for UOM field when UOM value is not saved.");
		getWebElement("Inventory.AddMateriaPage.AddUOM").click();
		getWebElement("Inventory.AddMateriaPage.AddUOM").clear();
		getWebElement("Inventory.AddMateriaPage.AddUOM").sendKeys(uom);
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedUOMSaveValidationMsg = "Save Unit To Proceed.";
		String actualUOMSaveValidationMsg = getWebElement("Inventory.AddMaterial.UOMValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_UOM_Save_Validation_Message_"+TodayDate.Date());
		if(expectedUOMSaveValidationMsg.equalsIgnoreCase(actualUOMSaveValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualUOMSaveValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when user tries to create an existing UOM without saving it. The message displayed as  - "+actualUOMSaveValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Vendor field when vendor not selected
		Reporter.log("Verification of validation message for Vendor field when Vendor is not selected.");
		getWebElement("Inventory.AddMateriaPage.AddUOM").click();
		getWebElement("Inventory.AddMateriaPage.AddUOM").clear();
		getWebElement("Inventory.AddMateriaPage.AddUOM").sendKeys(uom);
		Thread.sleep(1000);
		getWebElement("Inventory.AddMaterial.SaveUOM.Icon").click();
		Thread.sleep(2000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedVendorValidationMsg = "Please select Vendor";
		String actualVendorValidationMsg = getWebElement("Inventory.AddMaterial.VendorValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Vendor_Validation_Message_"+TodayDate.Date());
		if(expectedVendorValidationMsg.equalsIgnoreCase(actualVendorValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualVendorValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when vendor field contains null value. The message displayed as  - "+actualVendorValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
			
		//Verify validation message for Vendor field when it contains empty value for vendor name.
		Reporter.log("Verification of validation message for Vendor field when it contains null value while creation of new Vendor.");
		SelectList.SelectByVisibleText("Inventory.Vendor","Add Vendor");
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedAddVendorValidationMsg = "Vendor field cannot be empty";
		String actualAddVendorValidationMsg = getWebElement("Inventory.AddMaterial.VendorValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Vendor_Empty_Validation_Message_"+TodayDate.Date());
		if(expectedAddVendorValidationMsg.equalsIgnoreCase(actualAddVendorValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualAddVendorValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when vendor field contains null value. The message displayed as  - "+actualAddVendorValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		 
		//Verify validation message for Vendor field when user adds a vendor without saving it to the list.
		Reporter.log("Verification of validation message for Vendor field when Vendor is not saved to the list.");
		getWebElement("Inventory.AddVendor").click();
		getWebElement("Inventory.AddVendor").sendKeys(vendorName);
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedVendorSaveValidationMsg = "Save vendor to Proceed.";
		String actualVendorSaveValidationMsg = getWebElement("Inventory.AddMaterial.VendorValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Vendor_Exists_Validation_Message_"+TodayDate.Date());
		if(expectedVendorSaveValidationMsg.equalsIgnoreCase(actualVendorSaveValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualVendorSaveValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when Vendor is not saved to the list. The message displayed as  - "+actualVendorSaveValidationMsg);	
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Vendor field when it contains empty value for vendor name.
		Reporter.log("Verification of validation message for Vendor field when it contains null value while creation of new Vendor to the list.");
		getWebElement("Inventory.AddVendor").click();
		getWebElement("Inventory.AddVendor").clear();
		Thread.sleep(1000);
		getWebElement("Inventory.AddMaterial.SaveVendor.Icon").click();
		Thread.sleep(1000);
		String expectedAddVendorFieldValidationMsg = "Vendor field cannot be empty.";
		String actualAddVendorFieldValidationMsg = getWebElement("Inventory.AddMaterial.VendorValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Vendor_Empty_Validation_Message_"+TodayDate.Date());
		if(expectedAddVendorFieldValidationMsg.equalsIgnoreCase(actualAddVendorFieldValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualAddVendorFieldValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when vendor field contains null value. The message displayed as  - "+actualAddVendorFieldValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		 
		//Verify validation message for Vendor field when user creates an existing vendor.
		getWebElement("Inventory.AddVendor").click();
		getWebElement("Inventory.AddVendor").clear();
		getWebElement("Inventory.AddVendor").sendKeys(vendorName);
		Thread.sleep(1000);
		getWebElement("Inventory.AddMaterial.SaveVendor.Icon").click();
		Thread.sleep(1000);
		String expectedVendorExistsValidationMsg = "Vendor already present";
		String actualVendorExistsValidationMsg = getWebElement("Inventory.AddMaterial.VendorValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Vendor_Exists_Validation_Message_"+TodayDate.Date());
		if(expectedVendorExistsValidationMsg.equalsIgnoreCase(actualVendorExistsValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualVendorExistsValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when existing vendor is used for vendor creation. The message displayed as  - "+actualVendorExistsValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Add a new vendor
		getWebElement("Inventory.AddVendor").click();
		getWebElement("Inventory.AddVendor").sendKeys(TodayDate.Date());
		Thread.sleep(1000);
		getWebElement("Inventory.AddMaterial.SaveVendor.Icon").click();
		Thread.sleep(2000);
		
		/*
		  //Verify validation message for Type field when existing type is added
		//Verify the provided material type exists in list
		if(SelectList.VerifySelectList("Inventory.MaterialType","Add Type") == true)
			SelectList.SelectByVisibleText("Inventory.MaterialType","Add Type");
		else
			softAssertion.fail("The Material Type - 'Add Type' does not exist");
		Thread.sleep(3000);
		driver.findElement(By.id("addNewMaterialPageForm:other")).click();
		driver.findElement(By.id("addNewMaterialPageForm:other")).sendKeys(materialType);
		//getWebElement("Inventory.AddMateriaPage.AddType").click();
		//getWebElement("Inventory.AddMateriaPage.AddType").sendKeys(materialType);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String actualTypeExistsValidationMsg = getWebElement("Inventory.AddMaterial.MaterialTypeValidationMessage").getText().trim();
		String expectedTypeExistsValidationMsg = "Category type already present";
		Utills.captureScreenshot("Add_Material_Type_Exists_Validation_Message_"+TodayDate.Date());
		if(expectedTypeExistsValidationMsg.equalsIgnoreCase(actualTypeExistsValidationMsg))
			Reporter.log("Validation message displayed successfully for type field when user tries to create existing type.");
		else
			softAssertion.fail("Validation message not displayed for type field when user tries to create existing type. The message displayed as  - "+actualTypeExistsValidationMsg);
		
		//Verify validation message for Count field when count contains a value zero
		getWebElement("Inventory.AddMateriaPage.AddType").click();
		getWebElement("Inventory.AddMateriaPage.AddType").sendKeys(TodayDate.Date());
		Thread.sleep(2000);*/
		
		//Selects the material Type
		if(SelectList.VerifySelectList("Inventory.MaterialType",materialType) == true)
			SelectList.SelectByVisibleText("Inventory.MaterialType",materialType);
		else
			softAssertion.fail("The Material Type - '"+materialType+"' does not exist");
		Thread.sleep(2000);
		
		//Verify validation message for Count field when count contains a value zero
		Reporter.log("Verification of validation message for Count field when it contains a value zero(0).");
		getWebElement("Inventory.AddMaterial.Count").click();
		getWebElement("Inventory.AddMaterial.Count").clear();
		getWebElement("Inventory.AddMaterial.Count").sendKeys(zeroMaterialCount);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedCountZeroValidationMsg = "Count should be greater than 0";
		String actualCountZeroValidationMsg = getWebElement("Inventory.AddMaterial.CountValidationMessage").getText().trim();
		Utills.captureScreenshot("Add_Material_Count_Zero_Validation_Message_"+TodayDate.Date());
		if(expectedCountZeroValidationMsg.equalsIgnoreCase(actualCountZeroValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualCountZeroValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when count contains value zero. The message displayed as  - "+actualCountZeroValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Count field when count contains a value greater than 100
		Reporter.log("Verification of validation message for Count field when it contains a value greater than 100.");
		getWebElement("Inventory.AddMaterial.Count").click();
		getWebElement("Inventory.AddMaterial.Count").clear();
		getWebElement("Inventory.AddMaterial.Count").sendKeys(greaterMaterialCount);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedCountGreaterValidationMsg = "Count should be less than or equal to 100";
		String actualCountGreaterValidationMsg = getWebElement("Inventory.AddMaterial.CountValidationMessage").getText().trim();
		Utills.captureScreenshot("Add_Material_Count_Greater_Validation_Message_"+TodayDate.Date());
		if(expectedCountGreaterValidationMsg.equalsIgnoreCase(actualCountGreaterValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualCountGreaterValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when count contains value greater than 100. The message displayed as  - "+actualCountGreaterValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Count field when count contains invalid value
		Reporter.log("Verification of validation message for Count field when it contains invalid value.");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").clear();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(invalidMaterialCount);
		getWebElement("Inventory.AddCatalogNumber").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		getWebElement("Inventory.AddMaterial.Count").click();
		getWebElement("Inventory.AddMaterial.Count").clear();
		getWebElement("Inventory.AddMaterial.Count").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedInvalidCountValidationMsg = "Count should be a numeric value.";
		String actualInvalidCountValidationMsg = getWebElement("Inventory.AddMaterial.CountValidationMessage").getText().trim();
		Utills.captureScreenshot("Add_Material_Invalid_Count_Validation_Message_"+TodayDate.Date());
		if(expectedInvalidCountValidationMsg.equalsIgnoreCase(actualInvalidCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualInvalidCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when count contains invalid value. The message displayed as  - "+actualInvalidCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Count field when count contains negative value
		Reporter.log("Verification of validation message for Count field when it contains negative value.");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").clear();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(negativeMaterialCount);
		getWebElement("Inventory.AddCatalogNumber").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		getWebElement("Inventory.AddMaterial.Count").click();
		getWebElement("Inventory.AddMaterial.Count").clear();
		getWebElement("Inventory.AddMaterial.Count").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedNegativeCountValidationMsg = "Count should be greater than 0";
		String actualNegativeCountValidationMsg = getWebElement("Inventory.AddMaterial.CountValidationMessage").getText().trim();
		Utills.captureScreenshot("Add_Material_Negative_Count_Validation_Message_"+TodayDate.Date());
		if(expectedNegativeCountValidationMsg.equalsIgnoreCase(actualNegativeCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualNegativeCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when count contains negative value. The message displayed as  - "+actualNegativeCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Count field when count contains float value
		Reporter.log("Verification of validation message for Count field when it contains float value.");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").clear();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(floatMaterialCount);
		getWebElement("Inventory.AddCatalogNumber").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		Thread.sleep(2000);
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMaterial.Count").click();
		getWebElement("Inventory.AddMaterial.Count").clear();
		getWebElement("Inventory.AddMaterial.Count").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedFloatCountValidationMsg = "Count should be a positive integer";
		String actualFloatCountValidationMsg = getWebElement("Inventory.AddMaterial.CountValidationMessage").getText().trim();
		Utills.captureScreenshot("Add_Material_Count_Float_Value_Validation_Message_"+TodayDate.Date());
		if(expectedFloatCountValidationMsg.equalsIgnoreCase(actualFloatCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualFloatCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when count contains float value. The message displayed as  - "+actualFloatCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		getWebElement("Inventory.AddMaterial.Count").click();
		getWebElement("Inventory.AddMaterial.Count").clear();
		getWebElement("Inventory.AddMaterial.Count").sendKeys(materialsCount);
		Thread.sleep(1000);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(invalidQuantity);
		getWebElement("Inventory.AddCatalogNumber").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		
		//Clicks on "Other Details" tab.
		getWebElement("Inventory.OtherDetailsTab").click();
		impliciteWait(2);
		
		/*//Verify validation message for Manufactured Date field when it contains invalid value
		Reporter.log("Verification of validation message for Manufacture Date field when it contains invalid value.");
		getWebElement("Inventory.AddManufacturedDate").click();
		getWebElement("Inventory.AddManufacturedDate").sendKeys(manufactredDate);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedManufactureDateValidationMsg = "Please provide a valid date";
		String actualManufactureDateValidationMsg = getWebElement("Inventory.AddMaterial.ManufacturedDateValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Manufacture_Date_Validation_Message_"+TodayDate.Date());
		if(expectedManufactureDateValidationMsg.equalsIgnoreCase(actualManufactureDateValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualManufactureDateValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when maufactured date field contains invalid value. The message displayed as  - "+actualManufactureDateValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Expiration Date field when it contains invalid value
		Reporter.log("Verification of validation message for Expiration Date field when it contains invalid value.");
		getWebElement("Inventory.AddManufacturedDate").click();
		getWebElement("Inventory.AddManufacturedDate").clear();
		getWebElement("Inventory.AddManufacturedDate").sendKeys(materialManufactredDate);
		getWebElement("Inventory.AddExpiredDate").click();
		getWebElement("Inventory.AddExpiredDate").sendKeys(expiredDate);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedExpireDateValidationMsg = "Please provide a valid date";
		String actualExpireDateValidationMsg = getWebElement("Inventory.AddMaterial.ExpiredDateValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Expire_Date_Validation_Message_"+TodayDate.Date());
		if(expectedExpireDateValidationMsg.equalsIgnoreCase(actualExpireDateValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualExpireDateValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not dispalyed when expiration date field contains invalid value. The message displayed as  - "+actualExpireDateValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		 */
		//Verify validation message for Boiling Point field when it contains invalid value
		Reporter.log("Verification of validation message for Boiling Point field when it contains invalid value.");
		getWebElement("Inventory.AddManufacturedDate").click();
		getWebElement("Inventory.AddManufacturedDate").clear();
		getWebElement("Inventory.AddManufacturedDate").sendKeys(materialManufactredDate);
		getWebElement("Inventory.AddExpiredDate").click();
		getWebElement("Inventory.AddExpiredDate").clear();
		getWebElement("Inventory.AddExpiredDate").sendKeys(materialExpirationDate);
		getWebElement("Inventory.AddBoilingPoint").click();
		getWebElement("Inventory.AddBoilingPoint").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedBoilingPointValidationMsg = "Please provide a valid temperature!";
		String actualBoilingPointValidationMsg = getWebElement("Inventory.AddMaterial.BoilingPointValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Boiling_Point_Validation_Message_"+TodayDate.Date());
		if(expectedBoilingPointValidationMsg.equalsIgnoreCase(actualBoilingPointValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualBoilingPointValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when Boiling Point field contains invalid value. The message displayed as  - "+actualBoilingPointValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Boiling Point field when it contains value greater than -400.
		Reporter.log("Verification of validation message for Boiling Point field when it contains a value lesser than -400.");
		getWebElement("Inventory.AddBoilingPoint").click();
		getWebElement("Inventory.AddBoilingPoint").clear();
		getWebElement("Inventory.AddBoilingPoint").sendKeys(boilPoint);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedMaxBoilingPointValidationMsg = "The temperature boiling point input should be over -400 degree Celsius!";
		String actualMaxBoilingPointValidationMsg = getWebElement("Inventory.AddMaterial.BoilingPointValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Boiling_Point_Validation_Message_"+TodayDate.Date());
		if(expectedMaxBoilingPointValidationMsg.equalsIgnoreCase(actualMaxBoilingPointValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualMaxBoilingPointValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when Boiling Point field contains a value greater than -400. The message displayed as  - "+actualMaxBoilingPointValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Melting Point field when it contains invalid value.
		Reporter.log("Verification of validation message for Melting Point field when it contains invalid value.");
		getWebElement("Inventory.AddBoilingPoint").click();
		getWebElement("Inventory.AddBoilingPoint").clear();
		getWebElement("Inventory.AddBoilingPoint").sendKeys(boilingPoint);
		getWebElement("Inventory.AddMeltingPoint").click();
		getWebElement("Inventory.AddMeltingPoint").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedMeltingPointValidationMsg = "Please provide a valid temperature!";
		String actualMeltingPointValidationMsg = getWebElement("Inventory.AddMaterial.MeltingPointValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Melting_Point_Validation_Message_"+TodayDate.Date());
		if(expectedMeltingPointValidationMsg.equalsIgnoreCase(actualMeltingPointValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualMeltingPointValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when Melting Point field contains invalid value. The message displayed as  - "+actualMeltingPointValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Melting Point field when it contains value greater than -400.
		Reporter.log("Verification of validation message for Melting Point field when it contains a value lesser than -400.");
		getWebElement("Inventory.AddMeltingPoint").click();
		getWebElement("Inventory.AddMeltingPoint").clear();
		getWebElement("Inventory.AddMeltingPoint").sendKeys(meltPoint);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedMaxMeltingPointValidationMsg = "The temperature melting point input should be over -400 degree Celsius!";
		String actualMaxMeltingPointValidationMsg = getWebElement("Inventory.AddMaterial.MeltingPointValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Melting_Point_Validation_Message_"+TodayDate.Date());
		if(expectedMaxMeltingPointValidationMsg.equalsIgnoreCase(actualMaxMeltingPointValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualMaxMeltingPointValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when Melting Point field contains a value greater than -400. The message displayed as  - "+actualMaxMeltingPointValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
					
		//Verify validation message for Flashing Point field when it contains invalid value
		Reporter.log("Verification of validation message for Flashing Point field when it contains invalid value.");
		getWebElement("Inventory.AddMeltingPoint").click();
		getWebElement("Inventory.AddMeltingPoint").clear();
		getWebElement("Inventory.AddMeltingPoint").sendKeys(meltingPoint);
		getWebElement("Inventory.FlashingPoint").click();
		getWebElement("Inventory.FlashingPoint").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		//getWebElement("Inventory.FlashingPoint").sendKeys(flashPoint);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedFlashingPointValidationMsg = "Please provide a valid temperature!";
		String actualFlashingPointValidationMsg = getWebElement("Inventory.AddMaterial.FlashingPointValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Flashing_Point_Validation_Message_"+TodayDate.Date());
		if(expectedFlashingPointValidationMsg.equalsIgnoreCase(actualFlashingPointValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualFlashingPointValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when Flashing Point field contains invalid value. The message displayed as  - "+actualFlashingPointValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Flashing Point field when it contains value greater than -400.
		Reporter.log("Verification of validation message for Flashing Point field when it contains a value lesser than -400.");
		getWebElement("Inventory.FlashingPoint").click();
		getWebElement("Inventory.FlashingPoint").clear();
		getWebElement("Inventory.FlashingPoint").sendKeys(flashPoint);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedMaxFlashingPointValidationMsg = "The temperature flashing point input should be over -400 degree Celsius!";
		String actualMaxFlashingPointValidationMsg = getWebElement("Inventory.AddMaterial.FlashingPointValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Flashing_Point_Validation_Message_"+TodayDate.Date());
		if(expectedMaxFlashingPointValidationMsg.equalsIgnoreCase(actualMaxFlashingPointValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualMaxFlashingPointValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when Flashing Point field contains a value greater than -400. The message displayed as  - "+actualMaxFlashingPointValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Minimum Count field when it contains a value zero.
		Reporter.log("Verification of validation message for Minimum Count field when it contains a value Zero(0).");
		getWebElement("Inventory.FlashingPoint").click();
		getWebElement("Inventory.FlashingPoint").clear();
		getWebElement("Inventory.FlashingPoint").sendKeys(flashingPoint);
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").sendKeys(zeroMinCount);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedMinCountValidationMsg = "Minimum Count should be greater than 0";
		String actualMinCountValidationMsg = getWebElement("Inventory.AddMaterial.MinCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedMinCountValidationMsg.equalsIgnoreCase(actualMinCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualMinCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when minimum count field contains a value zero(0). The message displayed as  - "+actualMinCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Minimum Count field when it contains invalid value.
		Reporter.log("Verification of validation message for Minimum Count field when it contains a invalid value.");
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").clear();
		getWebElement("Inventory.MinimumCount").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedInvalidMinCountValidationMsg = "Minimum Count should be an integer value";
		String actualInvalidMinCountValidationMsg = getWebElement("Inventory.AddMaterial.MinCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Invalid_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedInvalidMinCountValidationMsg.equalsIgnoreCase(actualInvalidMinCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualInvalidMinCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when minimum count field contains invalid value. The message displayed as  - "+actualInvalidMinCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for Minimum Count field when it contains a value greater than 9999999.
		Reporter.log("Verification of validation message for Minimum Count field when it a value greater than 9999999.");
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").clear();
		getWebElement("Inventory.MinimumCount").sendKeys(greaterMinCount);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedgreaterMinCountValidationMsg = "Minimum Count should be less than or equal to 9999999";
		String actualgreaterMinCountValidationMsg = getWebElement("Inventory.AddMaterial.MinCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Invalid_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedgreaterMinCountValidationMsg.equalsIgnoreCase(actualgreaterMinCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualgreaterMinCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when minimum count field contains a value greater than 9999999. The message displayed as  - "+actualgreaterMinCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
					
		//Verify validation message for Minimum Count field when only maximum count value is provided
		Reporter.log("Verification of validation message for Minimum Count field when only maximum count field value is provided.");
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").clear();
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").sendKeys(maximumCount);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedMinimumCountValidationMsg = "Minimum Count must be provided with Maximum Count";
		String actualMinimumCountValidationMsg = getWebElement("Inventory.AddMaterial.MinCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Invalid_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedMinimumCountValidationMsg.equalsIgnoreCase(actualMinimumCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualMinimumCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when only maximum count field value is provided and minimum count field contains null value. The message displayed as  - "+actualMinimumCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
				
		//Verify validation message for maximum Count field when it contains a value zero.
		Reporter.log("Verification of validation message for Maximum Count field when it contains a value zero(0).");
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").clear();
		getWebElement("Inventory.MinimumCount").sendKeys(minCount);
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").clear();
		getWebElement("Inventory.MaximumCount").sendKeys(zeroMaxCount);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedMaxCountValidationMsg = "Maximum Count should be greater than 0";
		String actualMaxCountValidationMsg = getWebElement("Inventory.AddMaterial.MaxCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Max_Count_Validation_Message_"+TodayDate.Date());
		if(expectedMaxCountValidationMsg.equalsIgnoreCase(actualMaxCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualMaxCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when maximum count field contains a value zero(0). The message displayed as  - "+actualMaxCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
					
		//Verify validation message for maximum Count field when it contains invalid value.
		Reporter.log("Verification of validation message for Maximum Count field when it contains invalid value.");
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").clear();
		getWebElement("Inventory.MaximumCount").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedInvalidMaxCountValidationMsg = "Maximum Count should be an integer value";
		String actualInvalidMaxCountValidationMsg = getWebElement("Inventory.AddMaterial.MaxCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Invalid_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedInvalidMaxCountValidationMsg.equalsIgnoreCase(actualInvalidMaxCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualInvalidMaxCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when maximum count field contains invalid value. The message displayed as  - "+actualInvalidMaxCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
			
		//Verify validation message for maximum Count field when it contains a value greater than 9999999.
		Reporter.log("Verification of validation message for Maximum Count field when it contains a value greater than 9999999.");
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").clear();
		getWebElement("Inventory.MaximumCount").sendKeys(greaterMaxCount);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedgreaterMaxCountValidationMsg = "Maximum Count should be less than or equal to 9999999";
		String actualgreaterMaxCountValidationMsg = getWebElement("Inventory.AddMaterial.MaxCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedgreaterMaxCountValidationMsg.equalsIgnoreCase(actualgreaterMaxCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualgreaterMaxCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when maximum count field contains a value greater than 9999999. The message displayed as  - "+actualgreaterMaxCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
		
		//Verify validation message for maximum Count field when it contains a value less than minimum count.
		Reporter.log("Verification of validation message for Maximum Count field when it contains a value less than minimum count value.");
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").clear();
		getWebElement("Inventory.MaximumCount").sendKeys(lesserMaxCount);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedLesserMaxCountValidationMsg = "Maximum Count must be greater than Minimum Count";
		String actualLesserMaxCountValidationMsg = getWebElement("Inventory.AddMaterial.MaxCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Less_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedLesserMaxCountValidationMsg.equalsIgnoreCase(actualLesserMaxCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualLesserMaxCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when when maximum count value is lesser than minimum count value. The message displayed as  - "+actualLesserMaxCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
					
		//Verify validation message for Minimum Count field when it contains a float/double value.
		Reporter.log("Verification of validation message for Minimum Count field when it contains float/double value.");
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").clear();
		getWebElement("Inventory.FlashingPoint").click();
		getWebElement("Inventory.FlashingPoint").sendKeys(doubleMinCount);
		getWebElement("Inventory.FlashingPoint").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		Thread.sleep(1000);
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").clear();
		getWebElement("Inventory.MinimumCount").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedDoubleMinCountValidationMsg = "Minimum Count should be an integer value";
		String actualDoubleMinCountValidationMsg = getWebElement("Inventory.AddMaterial.MinCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Invalid_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedDoubleMinCountValidationMsg.equalsIgnoreCase(actualDoubleMinCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualDoubleMinCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when minimum count field contains float/double value. The message displayed as  - "+actualDoubleMinCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
					
		//Verify validation message for maximum Count field when it contains a double value.
		Reporter.log("Verification of validation message for Maximum Count field when it contains float/double value.");
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").clear();
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").clear();
		getWebElement("Inventory.MaximumCount").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedDoubleMaxCountValidationMsg = "Maximum Count should be an integer value";
		String actualDoubleMaxCountValidationMsg = getWebElement("Inventory.AddMaterial.MaxCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Double_Max_Count_Validation_Message_"+TodayDate.Date());
		if(expectedDoubleMaxCountValidationMsg.equalsIgnoreCase(actualDoubleMaxCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualDoubleMaxCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when maximum count field contains float/double value. The message displayed as  - "+actualDoubleMaxCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
					
		//Verify validation message for Minimum Count field when it contains a negative value.
		Reporter.log("Verification of validation message for Minimum Count field when it contains negative value.");
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").clear();
		getWebElement("Inventory.FlashingPoint").click();
		getWebElement("Inventory.FlashingPoint").clear();
		getWebElement("Inventory.FlashingPoint").sendKeys(negativeMinCount);
		getWebElement("Inventory.FlashingPoint").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		Thread.sleep(1000);
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").clear();
		getWebElement("Inventory.MinimumCount").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedNegativeMinCountValidationMsg = "Minimum Count should be greater than 0";
		String actualNegativeMinCountValidationMsg = getWebElement("Inventory.AddMaterial.MinCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Negative_Min_Count_Validation_Message_"+TodayDate.Date());
		if(expectedNegativeMinCountValidationMsg.equalsIgnoreCase(actualNegativeMinCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualNegativeMinCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when minimum count field contains negative value. The message displayed as  - "+actualNegativeMinCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
					
		//Verify validation message for maximum Count field when it contains a negative value.
		Reporter.log("Verification of validation message for Maximum Count field when it contains negative value.");
		getWebElement("Inventory.MinimumCount").click();
		getWebElement("Inventory.MinimumCount").clear();
		getWebElement("Inventory.MaximumCount").click();
		getWebElement("Inventory.MaximumCount").clear();
		getWebElement("Inventory.MaximumCount").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(1000);
		String expectedNegativeMaxCountValidationMsg = "Maximum Count should be greater than 0";
		String actualNegativeMaxCountValidationMsg = getWebElement("Inventory.AddMaterial.MaxCountValidationMessage").getText();
		Utills.captureScreenshot("Add_Material_Negative_Max_Count_Validation_Message_"+TodayDate.Date());
		if(expectedNegativeMaxCountValidationMsg.equalsIgnoreCase(actualNegativeMaxCountValidationMsg))
			Reporter.log("Validation message displayed successfully as - '"+actualNegativeMaxCountValidationMsg+"'.");
		else
			softAssertion.fail("Validation message not displayed when maximum count field contains negative value. The message displayed as  - "+actualNegativeMaxCountValidationMsg);
		Reporter.log("------------------------------------------------------------------------------------------------------------------------------------");
					
		//Logout from an application.
		Reporter.log("Logout from an application and close the browser.");
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
