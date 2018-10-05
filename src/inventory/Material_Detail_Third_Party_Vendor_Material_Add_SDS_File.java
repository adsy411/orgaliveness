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

public class Material_Detail_Third_Party_Vendor_Material_Add_SDS_File extends TestBase
{
	Library TodayDate = new Library();
	@Test(priority = 1)
	public void Material_Detail_Third_Party_Vendor_Material_Add_PDF_OR_Image_Type_SDS_File() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		inventoryRegularFunctions.UserLogin(userName,password);			
		int rowNumber = 105;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		
		if(fileName.contains("PDF"))
			fileType = "PDF";
		else if(fileName.contains("Image"))
			fileType = "Image";
		else
			Assert.fail("File type is not valid.");
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		
		//Navigation to material detail page. Verify material detail page exists or not.
		inventoryRegularFunctions.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Upload the PDF file type SDS file from material detail page
		Reporter.log("Upload the "+fileType+" SDS file from Material Detail Page");
		inventoryRegularFunctions.Material_Detail_Upload_SDS_File(fileName);
		Thread.sleep(2000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Screen_"+TodayDate.Date());
		String expectedSDSSuccessMessage = "Success! SDS file uploaded successfully to product";
		String actualSDSSuccessMessage = getWebElement("Inventory.DetailPage.UpdateProduct.SDS.SuccessMessage").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Success message displayed successfully after uploading SDS file in material detail page.");
		else
			softAssertion.fail("Success Message not displayed as expected. The message displayed as - "+actualSDSSuccessMessage);
		
		//View the uploaded SDS file from material detail page.
		Reporter.log("Click on SDS icon of a third party vendor material - "+materialName+" to view the updated SDS file of type "+fileType+" from material detail Page.");
		getWebElement("Inventory.DetailPage.ViewSDS.Icon").click();
		Thread.sleep(5000);
		
		String pageName = "material detail page";
		//Verify SDS Functionality
		inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_PDF_Image_Type_SDS(materialName, pageName, fileType);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();		
	}
	
	@Test(priority = 2)
	public void Material_Detail_Third_Party_Vendor_Material_Add_Excel_Type_SDS_File() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);	
		int rowNumber = 106;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		String SDSfileName = null;
		
		if(fileName.contains("Excel"))
		{
			SDSfileName = fileName+".xls";
			fileType = "Excel";
		}
		else
			Assert.fail("File type is Invalid to verify the download scenario of an SDS file.");
		
		//Delete the file if it is found in the path
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		inventoryRegularFunctions.Delete_File(SDSfileName);
		
		//Login in to application
		init();
		inventoryRegularFunctions.UserLogin(userName,password);		
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		
		//Navigation to material detail page. Verify material detail page exists or not.
		inventoryRegularFunctions.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Upload the PDF file type SDS file from material detail page
		Reporter.log("Upload the "+fileType+" SDS file from Material Detail Page");
		inventoryRegularFunctions.Material_Detail_Upload_SDS_File(fileName);
		Thread.sleep(2000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Screen_"+TodayDate.Date());
		String expectedSDSSuccessMessage = "Success! SDS file uploaded successfully to product";
		String actualSDSSuccessMessage = getWebElement("Inventory.DetailPage.UpdateProduct.SDS.SuccessMessage").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Success message displayed successfully after uploading SDS file in material detail page.");
		else
			softAssertion.fail("Success Message not displayed as expected. The message displayed as - "+actualSDSSuccessMessage);
		
		//View the uploaded SDS file from material detail page.
		Reporter.log("Click on SDS icon of a third party vendor material - "+materialName+" to view the updated SDS file of type "+fileType+" from material detail Page.");
		getWebElement("Inventory.DetailPage.ViewSDS.Icon").click();
		Thread.sleep(7000);
		
		//Verify SDS Functionality
		inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_Download_File_Type_SDS(materialName, SDSfileName, fileType);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();		
	}
}
