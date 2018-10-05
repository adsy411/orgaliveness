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

public class Material_Detail_Third_Party_Vendor_Material_SDS_Validation_Scenarios extends TestBase
{
	@Test(priority = 1)
	public void Detail_Page_Third_Party_Vendor_Material_No_SDS_Warning_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 36;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(3000);
		
		//Navigation to material detail page.
		impliciteWait(2);
		InventoryRegularFunctions MaterialDetailPage = new InventoryRegularFunctions();
		MaterialDetailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Thread.sleep(2000);
		//Clicks on SDS icon.
		getWebElement("Inventory.DetailPage.SDSIcon").click();
		Thread.sleep(1000);
		
		//Verification of Warning message in list view when Third party vendor material does not contains SDS file.
		Utills.captureScreenshot("Detail_Page_Third_party_Vendor_Material_SDS_Warning_Message_"+TodayDate.Date());
		String expectedDetailPageSDSWarningMsg = "Warning! It looks like you haven't added a safety data sheet to this material! Attach an SDS to this material and we will save it for next you add the material to your inventory.";
		String actualDetailPageSDSWarningValidationMsg = getWebElement("Inventory.DetailPage.WarningMessage").getText();
		if(expectedDetailPageSDSWarningMsg.equalsIgnoreCase(actualDetailPageSDSWarningValidationMsg))
			Reporter.log("Detail Page - SDS - Warning message displayed successfully for SDS When third party material does not contain SDS file.");
		else
			softAssertion.fail("Detail Page - SDS -  Warning message not displayed for SDS When third party material does not contain SDS file. Expected warning message is - "+expectedDetailPageSDSWarningMsg+". The actual message displayed as  - "+actualDetailPageSDSWarningValidationMsg);
	
		//Close the warning message
		getWebElement("Inventory.CardView.WarningMessage.CloseIcon").click();
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Material_Detail_Page_Upload_Invalid_SDS_File_Warning_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 110;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		
		if(fileName.contains("Exe"))
			fileType = "Exe";	
		else
			Assert.fail("Unable to verify validation scenario as the file type is not exe.");
		
		//Launch the browser and go to home page.
		init();
		
		//Login in to application
		inventoryRegularFunctions.UserLogin(userName,password);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to material detail page.
		impliciteWait(2);
		InventoryRegularFunctions MaterialDetailPage = new InventoryRegularFunctions();
		MaterialDetailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		Thread.sleep(2000);
		
		//Upload the Exe file type SDS file from material detail page
		Reporter.log("Upload the "+fileType+" SDS file from Material Detail Page");
		inventoryRegularFunctions.Material_Detail_Upload_SDS_File(fileName);
		Thread.sleep(3000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Screen_"+TodayDate.Date());
		String expectedSDSSuccessMessage = "Warning! Cannot upload .exe or .sh extensions";
		String actualSDSSuccessMessage = getWebElement("Inventory.DetailPage.SDS.Warning.Message").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Warning message displayed successfully when exe type SDS file is uploaded in Material Detail Page.");
		else
			softAssertion.fail("Warning Message not displayed as expected when exe type SDS file is uploaded. The message displayed as - "+actualSDSSuccessMessage);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void Material_Detail_Page_Upload_Greater_Size_SDS_File_Warning_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 111;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Launch the browser and go to home page.
		init();
		
		//Login in to application
		inventoryRegularFunctions.UserLogin(userName,password);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to material detail page.
		InventoryRegularFunctions MaterialDetailPage = new InventoryRegularFunctions();
		MaterialDetailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		Thread.sleep(2000);
		
		//Upload the Exe file type SDS file from material detail page
		Reporter.log("Upload the SDS file with file size greater than 15MB from Material Detail Page");
		inventoryRegularFunctions.Material_Detail_Upload_SDS_File(fileName);
		Thread.sleep(3000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Screen_"+TodayDate.Date());
		String expectedSDSSuccessMessage = "Warning! File size is greater than 15MB";
		String actualSDSSuccessMessage = getWebElement("Inventory.DetailPage.SDS.Warning.Message").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Warning message displayed successfully when SDS file is uploaded with file size greater than 15MB in Material detail page.");
		else
			softAssertion.fail("Warning Message not displayed when SDS file is uploaded with file size greater than 15MB. The message displayed as - "+actualSDSSuccessMessage);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
