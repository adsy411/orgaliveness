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

public class Add_Material_Page_SDS_Validation_Scenario extends TestBase
{
	@Test(priority = 1)
	public void Add_Material_Page_SDS_File_Deletion_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 108;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		
		if(fileName.contains("PDF"))
			fileType = "PDF";
		else if(fileName.contains("Image"))
			fileType = "Image";
		else if(fileName.contains("Excel"))
			fileType = "Excel";	
		
		//Launch the browser and go to home page.
		init();
		
		//Login in to application
		inventoryRegularFunctions.UserLogin(userName,password);
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(4000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		Reporter.log("Upload the "+fileType+" SDS file from Add Material Modal");
		
		//Clicks on upload button to upload SDS file
		getWebElement("Inventory.AddMaterial.UploadSDS").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("SDS_File_Upload_Modal_"+TodayDate.Date());
		
		//Adding the SDS file
		inventoryRegularFunctions.Add_Material_Page_Upload_SDS(fileName, fileType);
		
		//Delete the SDS file
		inventoryRegularFunctions.Add_Material_Page_Delete_SDS_File();
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Add_Material_Page_Upload_Invalid_SDS_File_Warning_Message_Validation_Scenario() throws Exception
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
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		
		if(fileName.contains("Exe"))
			fileType = "Exe";	
		else
			Assert.fail("Unable to verify validation scenario as the file type is not exe");
		
		//Launch the browser and go to home page.
		init();
		
		//Login in to application
		inventoryRegularFunctions.UserLogin(userName,password);
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(4000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		Reporter.log("Upload the "+fileType+" SDS file from Add Material Modal");
		
		//Clicks on upload button to upload SDS file
		getWebElement("Inventory.AddMaterial.UploadSDS").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("SDS_File_Upload_Modal_"+TodayDate.Date());
		
		//Adding the SDS file
		String fileUploadPath = System.getProperty("user.dir")+"\\src\\testData\\Inventory_Attachments\\"+fileName;
		Runtime.getRuntime().exec(fileUploadPath);
		Thread.sleep(5000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Screen_"+TodayDate.Date());
		
		String expectedSDSSuccessMessage = "Warning! Cannot upload .exe or .sh extensions";
		String actualSDSSuccessMessage = getWebElement("Inventory.AddMaterial.SDSUpload.Message").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Warning message displayed successfully when exe type SDS file is uploaded in Add Material Modal");
		else
			softAssertion.fail("Warning Message not displayed as expected. The message displayed as - "+actualSDSSuccessMessage);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void Add_Material_Page_Upload_Greater_Size_SDS_File_Warning_Message_Validation_Scenario() throws Exception
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
		
		//Launch the browser and go to home page.
		init();
		
		//Login in to application
		inventoryRegularFunctions.UserLogin(userName,password);
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(4000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		Reporter.log("Upload the SDS file with file size greater than 15MB from Add Material Modal.");
		
		//Clicks on upload button to upload SDS file
		getWebElement("Inventory.AddMaterial.UploadSDS").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("SDS_File_Upload_Modal_"+TodayDate.Date());
		
		//Adding the SDS file
		String fileUploadPath = System.getProperty("user.dir")+"\\src\\testData\\Inventory_Attachments\\"+fileName;
		Runtime.getRuntime().exec(fileUploadPath);
		Thread.sleep(5000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Greater_File_Size_Screen_"+TodayDate.Date());
		
		String expectedSDSSuccessMessage = "Warning! File size is greater than 15MB";
		String actualSDSSuccessMessage = getWebElement("Inventory.AddMaterial.SDSUpload.Message").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Warning message displayed successfully when SDS file is uploaded with file size greater than 15MB in Add Material Modal");
		else
			softAssertion.fail("Warning Message not displayed when SDS file is uploaded with file size greater than 15MB. The message displayed as - "+actualSDSSuccessMessage);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
