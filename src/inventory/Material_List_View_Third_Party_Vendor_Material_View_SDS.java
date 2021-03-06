package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Material_List_View_Third_Party_Vendor_Material_View_SDS extends TestBase
{
	Library TodayDate = new Library();
	@Test(priority = 1)
	public void ListView_ThirdPartyVendorMaterial_View_PDF_OR_Image_Type_SDS_File() throws Exception
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
		int rowNumber = 100;
		
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
		
		//Navigation to materials page to add third party vendor Vendor material with SDS file.
		inventoryRegularFunctions.Add_Third_Party_Vendor_Material_WithSDS(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to list view
		Reporter.log("Clcik on List View icon to view the materials as a list."); 
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		Reporter.log("List view displayed successfully in materials page");
		
		//View the uploaded SDS file from list View Page
		Reporter.log("Click on SDS icon of a third party vendor material - "+materialName+" to view the "+fileType+" type SDS file from list view of materials Page.");
		getWebElement("Inventory.ListView.SDSIcon").click();
		Thread.sleep(3000);
		
		String pageName = "materials page";
		//Verify SDS Functionality
		inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_PDF_Image_Type_SDS(materialName, pageName, fileType);
		
		//Set the view to card view
		Thread.sleep(1000);
		getWebElement("Inventory.CardView").click();
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void ListView_ThirdPartyVendorMaterial_View_Excel_Type_SDS_File() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 101;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		if(fileName.contains("Excel"))
		{
			fileName = fileName+".xls";
			fileType = "Excel";
		}
		else
			Assert.fail("File type is Invalid to verify the download scenario of an SDS file.");
		
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();

		//Delete the file if it is found in the path
		inventoryRegularFunctions.Delete_File(fileName);
		
		//Login in to application
		init();
		inventoryRegularFunctions.UserLogin(userName,password);		
		
		//Navigation to materials page to add third party vendor Vendor material with SDS file.
		inventoryRegularFunctions.Add_Third_Party_Vendor_Material_WithSDS(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to list view
		Reporter.log("Clcik on List View icon to view the materials as a list."); 
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		Reporter.log("List view displayed successfully in materials page");
		
		//View the uploaded SDS file from Card View Page
		Reporter.log("Click on SDS icon of a third party vendor material - "+materialName+" to view the "+fileType+" type SDS file from list view of materials Page.");
		getWebElement("Inventory.ListView.SDSIcon").click();
		Thread.sleep(8000);

		//Verify SDS Functionality
		inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_Download_File_Type_SDS(materialName, fileName, fileType);
		
		//Set the view to card view
		Thread.sleep(1000);
		getWebElement("Inventory.CardView").click();
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
