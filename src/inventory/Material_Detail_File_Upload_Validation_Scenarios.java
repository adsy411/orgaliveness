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

public class Material_Detail_File_Upload_Validation_Scenarios extends TestBase
{
	@Test(priority = 1)
	public void Material_Detail_Page_Attach_Invalid_File_Warning_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 113;
		
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
		
		//Upload the Exe file from material detail page
		Reporter.log("Attach the "+fileType+" type file from material detail page.");
		inventoryRegularFunctions.Material_Detail_Page_File_Upload(fileName);
		
		//Verification of file deletion success message in material detail page.
		Utills.captureScreenshot("Detail_Page_File_Upload_Message_"+TodayDate.Date());
		String expectedFileUploadSuccessMessage = "Warning! Please make sure your file extension should not be in exe or sh !";
		String actualFileUploadSuccessMessage = getWebElement("Inventory.DetailPage.WarningMessage").getText().trim();
		if(expectedFileUploadSuccessMessage.equalsIgnoreCase(actualFileUploadSuccessMessage))
			Reporter.log("'"+actualFileUploadSuccessMessage+"' Warning message displayed successfully when exe type file is attached to a material");
		else
			softAssertion.fail("Warning Message not displayed when exe type file is attached to a material. The actual message displayed is - "+actualFileUploadSuccessMessage);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Material_Detail_Page_Attach_File_Greater_Size_Warning_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 114;
		
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
		
		//Upload the file with file size greater than 15MB from material detail page
		Reporter.log("Attach the file with file size greater than 15MB from material detail page.");
		inventoryRegularFunctions.Material_Detail_Page_File_Upload(fileName);
		
		//Verification of file deletion success message in material detail page.
		Utills.captureScreenshot("Detail_Page_Greater_File_Size_Upload_Message_"+TodayDate.Date());
		String expectedFileUploadSuccessMessage = "Warning! File size greater than 15MB , can not be uploaded!";
		String actualFileUploadSuccessMessage = getWebElement("Inventory.DetailPage.WarningMessage").getText().trim();
		if(expectedFileUploadSuccessMessage.equalsIgnoreCase(actualFileUploadSuccessMessage))
			Reporter.log("'"+actualFileUploadSuccessMessage+"' Warning message displayed successfully when a file size greater than 15MB.");
		else
			softAssertion.fail("Warning Message not displayed when a file size greater than 15MB. The actual message displayed is - "+actualFileUploadSuccessMessage);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
