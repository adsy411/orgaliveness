package inventory;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Material_Detail_Error_Message_Validation_Scenario extends TestBase
{
	@Test(priority = 1)
	public void MaterialDetail_Error_Message_Validation_Scenario() throws Exception
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
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String addmaterialName = ExcelUtils.getCellData(18, 0);
		int rowNumber = 18;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to material detail page. Verify material detail page exists or not.
		InventoryRegularFunctions detailPage = new InventoryRegularFunctions();
		detailPage.MaterialDetailPageNavigation(addmaterialName);
		impliciteWait(2);
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Update_Material");
		String materialName = ExcelUtils.getCellData(1, 2);
		String quantity = ExcelUtils.getCellData(1, 3);
		String manufacturedDate = ExcelUtils.getCellData(1, 7);
		String expirationDate = ExcelUtils.getCellData(1, 8);
		String boilingPoint = ExcelUtils.getCellData(1, 10);
		String meltingPoint = ExcelUtils.getCellData(1, 11);
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Validation_Scenario");
		String greaterQuantity = ExcelUtils.getCellData(15, 0);
		String invalidQuantity = ExcelUtils.getCellData(16, 0);
		String negativeQuantity = ExcelUtils.getCellData(17, 0);
		String manufactureDate = ExcelUtils.getCellData(15, 1);
		String expiredDate = ExcelUtils.getCellData(15, 2);
		String invalidBoilPoint = ExcelUtils.getCellData(15, 3);
		String invalidMeltPoint = ExcelUtils.getCellData(15, 4);
		String invalidFlashPoint = ExcelUtils.getCellData(15, 5);
		String greaterBoilPoint = ExcelUtils.getCellData(16, 3);
		String greaterMeltPoint = ExcelUtils.getCellData(16, 4);
		String greaterFlashPoint = ExcelUtils.getCellData(16, 5);
		
		//Verify validation message when material title field contains null value.
		getWebElement("Inventoy.MaterialNameEditIcon").click();
		getWebElement("Inventory.MaterialTitleModal").isDisplayed();
		Assert.assertTrue("Material title modal not displayed",getWebElement("Inventory.MaterialTitleModal").getText().equals("Enter Material Title*"));
		getWebElement("Inventory.MaterialTitleEdit").click();
		getWebElement("Inventory.MaterialTitleEdit").clear();
		getWebElement("Inventory.MaterialTitleSet").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("Material_Detail_Material_Title_Validation_Message_"+TodayDate.Date());
		String expectedMaterialTitleValidationMsg = "Material title should not be empty";
		String actualMaterialTitleValidationMsg = getWebElement("Inventory.MaterialDetail.MaterialTitleValidationMessage").getText();
		if(expectedMaterialTitleValidationMsg.equalsIgnoreCase(actualMaterialTitleValidationMsg))
			Reporter.log("Validation message displayed successfully for material title field when it contains null value.");
		else
			softAssertion.fail("Validation message not displayed when material title contains null value. Expected message is - "+expectedMaterialTitleValidationMsg+". The actual message displayed as  - "+actualMaterialTitleValidationMsg);
		
		getWebElement("Inventory.MaterialTitleCancel").click();
		
		//Verify validation message when material name field contains null value.
		getWebElement("Inventory.MaterialDetail.MaterialName").click();
		getWebElement("Inventory.MaterialDetail.MaterialName").clear();
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("Material_Detail_Material_Name_Validation_Message_"+TodayDate.Date());
		String expectedMaterialNameValidationMsg = "Material Name is required";
		String actualMaterialNameValidationMsg = getWebElement("Inventory.MaterialDetail.MaterialNameValidationMessage").getText();
		if(expectedMaterialNameValidationMsg.equalsIgnoreCase(actualMaterialNameValidationMsg))
			Reporter.log("Validation message displayed successfully for material name field when it contains null value.");
		else
			softAssertion.fail("Validation message not displayed when material name contains null value. Expected message is - "+expectedMaterialNameValidationMsg+". The actual message displayed as  - "+actualMaterialNameValidationMsg);
	
		//Verify validation message for quantity field when it contains null value.
		getWebElement("Inventory.MaterialDetail.MaterialName").click();
		getWebElement("Inventory.MaterialDetail.MaterialName").clear();
		getWebElement("Inventory.MaterialDetail.MaterialName").sendKeys(negativeQuantity);
		getWebElement("Inventory.Material.UpdateQuantity").click();
		getWebElement("Inventory.Material.UpdateQuantity").clear();
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedQtyValidationMsg = "Quantity is required";
		String actualQtyValidationMsg = getWebElement("Inventory.MaterialDetail.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Qty_Null_Validation_Message_"+TodayDate.Date());
		if(expectedQtyValidationMsg.equalsIgnoreCase(actualQtyValidationMsg))
			Reporter.log("Validation message displayed successfully for quantity field when it contains null value.");
		else
			softAssertion.fail("Validation message not displayed when quantity field is empty. Expected message is - "+expectedQtyValidationMsg+". The actual message displayed as  - "+actualQtyValidationMsg);
		
		//Zero quantity scenario will not come. When 0 is entered dispose modal will come.
		
		//Verify validation message when quantity field contains negative value.
		getWebElement("Inventory.MaterialDetail.MaterialName").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		getWebElement("Inventory.Material.UpdateQuantity").click();
		getWebElement("Inventory.Material.UpdateQuantity").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedNegativeQtyValidationMsg = "Quantity should not be negative";
		String actualNegativeQtyValidationMsg = getWebElement("Inventory.MaterialDetail.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Qty_Negative_Validation_Message_"+TodayDate.Date());
		if(expectedNegativeQtyValidationMsg.equalsIgnoreCase(actualNegativeQtyValidationMsg))
			Reporter.log("Validation message displayed successfully for quantity field when it contains a negative value.");
		else
			softAssertion.fail("Validation message not displayed when qunatity field contains negative value. Expected message is - "+expectedNegativeQtyValidationMsg+". The message displayed as  - "+actualNegativeQtyValidationMsg);	
		
		//Verify validation message when quantity field contains greater than 9999999.
		getWebElement("Inventory.MaterialDetail.MaterialName").sendKeys(materialName);
		Thread.sleep(1000);
		getWebElement("Inventory.Material.UpdateQuantity").click();
		getWebElement("Inventory.Material.UpdateQuantity").clear();
		getWebElement("Inventory.Material.UpdateQuantity").sendKeys(greaterQuantity);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedGreaterQtyValidationMsg = "Quantity should be less than or equal to 9999999";
		String actualGreaterQtyValidationMsg = getWebElement("Inventory.MaterialDetail.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Greater_Qty_Validation_Message_"+TodayDate.Date());
		if(expectedGreaterQtyValidationMsg.equalsIgnoreCase(actualGreaterQtyValidationMsg))
			Reporter.log("Validation message displayed successfully for quantity field when it contains more than 9999999.");
		else
			softAssertion.fail("Validation message not displayed when qunatity is more than 9999999. Expected message is - "+expectedGreaterQtyValidationMsg+". The message displayed as  - "+actualGreaterQtyValidationMsg);
		
		//Verify validation message when quantity field contains invalid value.
		getWebElement("Inventory.MaterialDetail.MaterialName").sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
		getWebElement("Inventory.Material.UpdateQuantity").click();
		getWebElement("Inventory.Material.UpdateQuantity").clear();
		getWebElement("Inventory.Material.UpdateQuantity").sendKeys(Keys.chord(Keys.CONTROL, "v"));
		//getWebElement("Inventory.Material.UpdateQuantity").sendKeys(invalidQuantity);
		Thread.sleep(1000);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedInvalidQtyValidationMsg = "Quantity should be a numeric value";
		String actualInvalidQtyValidationMsg = getWebElement("Inventory.MaterialDetail.QuantityValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Invalid_Qty_Validation_Message_"+TodayDate.Date());
		if(expectedInvalidQtyValidationMsg.equalsIgnoreCase(actualInvalidQtyValidationMsg))
			Reporter.log("Validation message displayed successfully for quantity field when it contains invalid value.");
		else
			softAssertion.fail("Validation message not displayed when qunatity field contains invalid value. Expected message is - "+expectedInvalidQtyValidationMsg+". The message displayed as  - "+actualInvalidQtyValidationMsg);
		
		getWebElement("Inventory.Material.UpdateQuantity").click();
		getWebElement("Inventory.Material.UpdateQuantity").clear();
		getWebElement("Inventory.Material.UpdateQuantity").sendKeys("45");
		//Navigation to other details modal
		getWebElement("Inventory.OtherDetailsTab").click();
		impliciteWait(3);
		
		/*//Verify validation message for Manufactured Date field when it contains invalid value
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").click();
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").clear();
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").sendKeys(manufactureDate);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedManufactureDateValidationMsg = "Please provide a valid date";
		String actualManufactureDateValidationMsg = getWebElement("Inventory.MaterialDetail.ManufacturedDateValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Manufacture_Date_Validation_Message_"+TodayDate.Date());
		if(expectedManufactureDateValidationMsg.equalsIgnoreCase(actualManufactureDateValidationMsg))
			Reporter.log("Validation message displayed successfully for maufactured date field when it contains invalid value.");
		else
			softAssertion.fail("Validation message not displayed when maufactured date field contains invalid value. Expected message is - "+expectedManufactureDateValidationMsg+". The message displayed as  - "+actualManufactureDateValidationMsg);
		
		//Verify validation message for Expiration Date field when it contains invalid value
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").click();
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").clear();
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").sendKeys(manufacturedDate);
		getWebElement("Inventory.MaterialDetail.ExpirationDate").click();
		getWebElement("Inventory.MaterialDetail.ExpirationDate").clear();
		getWebElement("Inventory.MaterialDetail.ExpirationDate").sendKeys(expiredDate);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedExpireDateValidationMsg = "Please provide a valid date";
		String actualExpireDateValidationMsg = getWebElement("Inventory.MaterialDetail.ExpiredDateValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Expire_Date_Validation_Message_"+TodayDate.Date());
		if(expectedExpireDateValidationMsg.equalsIgnoreCase(actualExpireDateValidationMsg))
			Reporter.log("Validation message displayed successfully for expiration date field when it contains invalid value.");
		else
			softAssertion.fail("Validation message not displayed when expiration date field contains invalid value. Expected message is - "+expectedExpireDateValidationMsg+". The message displayed as  - "+actualExpireDateValidationMsg);
		*/
		//Verify validation message for Boiling Point field when it contains invalid value
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").click();
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").clear();
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").sendKeys(manufacturedDate);
		getWebElement("Inventory.MaterialDetail.ExpirationDate").click();
		getWebElement("Inventory.MaterialDetail.ExpirationDate").clear();
		getWebElement("Inventory.MaterialDetail.ExpirationDate").sendKeys(expirationDate);
		Thread.sleep(1000);
		getWebElement("Inventory.MaterialDetail.BoilingPoint").click();
		getWebElement("Inventory.MaterialDetail.BoilingPoint").clear();
		getWebElement("Inventory.MaterialDetail.BoilingPoint").sendKeys(invalidBoilPoint);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedBoilingPointValidationMsg = "Please provide a valid temperature!";
		String actualBoilingPointValidationMsg = getWebElement("Inventory.MaterialDetail.BoilingPointValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Boiling_Point_Validation_Message_"+TodayDate.Date());
		if(expectedBoilingPointValidationMsg.equalsIgnoreCase(actualBoilingPointValidationMsg))
			Reporter.log("Validation message displayed successfully for Boiling Point field when it contains invalid value.");
		else
			softAssertion.fail("Validation message not displayed when Boiling Point field contains invalid value. Expected message is - "+expectedBoilingPointValidationMsg+". The message displayed as  - "+actualBoilingPointValidationMsg);
		
		//Verify validation message for Boiling Point field when it contains value greater than -400.
		getWebElement("Inventory.MaterialDetail.BoilingPoint").click();
		getWebElement("Inventory.MaterialDetail.BoilingPoint").clear();
		getWebElement("Inventory.MaterialDetail.BoilingPoint").sendKeys(greaterBoilPoint);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedMaxBoilingPointValidationMsg = "The temperature boiling point input should be over -400 degree Celsius!";
		String actualMaxBoilingPointValidationMsg = getWebElement("Inventory.MaterialDetail.BoilingPointValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Boiling_Point_Validation_Message_"+TodayDate.Date());
		if(expectedMaxBoilingPointValidationMsg.equalsIgnoreCase(actualMaxBoilingPointValidationMsg))
			Reporter.log("Validation message displayed successfully for Boiling Point field when it contains a value greater than -400.");
		else
			softAssertion.fail("Validation message not displayed when Boiling Point field contains a value greater than -400.  Expected message is - "+expectedMaxBoilingPointValidationMsg+". The message displayed as  - "+actualMaxBoilingPointValidationMsg);
		
		//Verify validation message for Melting Point field when it contains invalid value.
		getWebElement("Inventory.MaterialDetail.BoilingPoint").click();
		getWebElement("Inventory.MaterialDetail.BoilingPoint").clear();
		getWebElement("Inventory.MaterialDetail.BoilingPoint").sendKeys(boilingPoint);
		getWebElement("Inventory.MaterialDetail.MeltingPoint").click();
		getWebElement("Inventory.MaterialDetail.MeltingPoint").clear();
		getWebElement("Inventory.MaterialDetail.MeltingPoint").sendKeys(invalidMeltPoint);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedMeltingPointValidationMsg = "Please provide a valid temperature!";
		String actualMeltingPointValidationMsg = getWebElement("Inventory.MaterialDetail.MeltingPointValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Melting_Point_Validation_Message_"+TodayDate.Date());
		if(expectedMeltingPointValidationMsg.equalsIgnoreCase(actualMeltingPointValidationMsg))
			Reporter.log("Validation message displayed successfully for Melting Point field when it contains invalid value.");
		else
			softAssertion.fail("Validation message not displayed when Melting Point field contains invalid value. Expected message is - "+expectedMeltingPointValidationMsg+".  The message displayed as  - "+actualMeltingPointValidationMsg);
		
		//Verify validation message for Melting Point field when it contains value greater than -400.
		getWebElement("Inventory.MaterialDetail.MeltingPoint").click();
		getWebElement("Inventory.MaterialDetail.MeltingPoint").clear();
		getWebElement("Inventory.MaterialDetail.MeltingPoint").sendKeys(greaterMeltPoint);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedMaxMeltingPointValidationMsg = "The temperature melting point input should be over -400 degree Celsius!";
		String actualMaxMeltingPointValidationMsg = getWebElement("Inventory.MaterialDetail.MeltingPointValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Melting_Point_Validation_Message_"+TodayDate.Date());
		if(expectedMaxMeltingPointValidationMsg.equalsIgnoreCase(actualMaxMeltingPointValidationMsg))
			Reporter.log("Validation message displayed successfully for Melting Point field when it contains a value greater than -400.");
		else
			softAssertion.fail("Validation message not displayed when Melting Point field contains a value greater than -400. The message displayed as  - "+actualMaxMeltingPointValidationMsg);
					
		//Verify validation message for Flashing Point field when it contains invalid value
		getWebElement("Inventory.MaterialDetail.MeltingPoint").click();
		getWebElement("Inventory.MaterialDetail.MeltingPoint").clear();
		getWebElement("Inventory.MaterialDetail.MeltingPoint").sendKeys(meltingPoint);
		getWebElement("Inventory.MaterialDetail.FlashingPoint").click();
		getWebElement("Inventory.MaterialDetail.FlashingPoint").clear();
		getWebElement("Inventory.MaterialDetail.FlashingPoint").sendKeys(invalidFlashPoint);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedFlashingPointValidationMsg = "Please provide a valid temperature!";
		String actualFlashingPointValidationMsg = getWebElement("Inventory.MaterialDetail.FlashingPointValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Flashing_Point_Validation_Message_"+TodayDate.Date());
		if(expectedFlashingPointValidationMsg.equalsIgnoreCase(actualFlashingPointValidationMsg))
			Reporter.log("Validation message displayed successfully for Flashing Point field when it contains invalid value.");
		else
			softAssertion.fail("Validation message not displayed when Flashing Point field contains invalid value. Expected message is - "+expectedFlashingPointValidationMsg+". The message displayed as  - "+actualFlashingPointValidationMsg);
		
		//Verify validation message for Flashing Point field when it contains value greater than -400.
		getWebElement("Inventory.MaterialDetail.FlashingPoint").click();
		getWebElement("Inventory.MaterialDetail.FlashingPoint").clear();
		getWebElement("Inventory.MaterialDetail.FlashingPoint").sendKeys(greaterFlashPoint);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(1000);
		String expectedMaxFlashingPointValidationMsg = "The temperature flashing point input should be over -400 degree Celsius!";
		String actualMaxFlashingPointValidationMsg = getWebElement("Inventory.MaterialDetail.FlashingPointValidationMessage").getText();
		Utills.captureScreenshot("Material_Detail_Flashing_Point_Validation_Message_"+TodayDate.Date());
		if(expectedMaxFlashingPointValidationMsg.equalsIgnoreCase(actualMaxFlashingPointValidationMsg))
			Reporter.log("Validation message displayed successfully for Flashing Point field when it contains a value greater than -400.");
		else
			softAssertion.fail("Validation message not displayed when Flashing Point field contains a value greater than -400. Expected message is - "+expectedMaxFlashingPointValidationMsg+". The message displayed as  - "+actualMaxFlashingPointValidationMsg);
	
		//Verification of no attachments message in material detail page.
		Utills.captureScreenshot("Detail_Page_No_Attachments_Message_"+TodayDate.Date());
		String expectedDetailPageNoAttachmentsMsg = "There are no attachments";
		String actualDetailPageNoAttachmentsMsg = getWebElement("Inventory.DetailPage.Attachments.Section.Message").getText().trim();
		if(expectedDetailPageNoAttachmentsMsg.equalsIgnoreCase(actualDetailPageNoAttachmentsMsg))
			Reporter.log("'"+actualDetailPageNoAttachmentsMsg+"' message displayed successfully when there is no files attached to a material");
		else
			softAssertion.fail("The expected message not displayed. The actual message displayed is - "+actualDetailPageNoAttachmentsMsg);
	
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}	
}
