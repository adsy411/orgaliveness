package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Add_Third_Party_Vendor_Material_Update_Existing_SDS_File extends TestBase
{
	Library TodayDate = new Library();
	@Test(priority = 1)
	public void Add_Third_Party_Vendor_Material_Update_Existing_Material_SDS_File() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 107;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String fileName = ExcelUtils.getCellData(rowNumber, 21);
		String fileType = null;
		
		if(fileName.contains("PDF"))
			fileType = "PDF";
		else if(fileName.contains("Image"))
			fileType = "Image";
		else if(fileName.contains("Excel"))
			fileType = "Excel";
		else
			Assert.fail("File type is not valid.");
		
		//Login in to application
		init();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		inventoryRegularFunctions.UserLogin(userName,password);	
		
		//Adding a material with SDS file
		inventoryRegularFunctions.Add_Third_Party_Vendor_Material_WithSDS(rowNumber);
		
		//Adding a material with SDS file
		inventoryRegularFunctions.Add_Material_Update_Existing_Material_SDS_File(materialName, catalogNumber, fileName);
		
		//Verify the SDS file attached to a material
		if(fileName.contains("PDF") || fileName.contains("Image"))
		{
			//View the uploaded SDS file from Card View Page
			Reporter.log("Click on SDS icon of a third party vendor material - "+materialName+" to view the "+fileType+" type SDS file from card view of materials Page.");
			getWebElement("Inventory.CardView.SDSIcon").click();
			Thread.sleep(3000);
			
			String pageName = "materials page";
			//Verify SDS Functionality
			inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_PDF_Image_Type_SDS(materialName, pageName,fileType);
		}
		else if(fileName.contains("Excel"))
		{
			String filename1 = fileName+".xls";

			//Delete the file if it is found in the path
			inventoryRegularFunctions.Delete_File(filename1);
			
			//View the uploaded SDS file from Card View Page
			Reporter.log("Click on SDS icon of a third party vendor material - "+materialName+" to view the "+fileType+" type SDS file from card view of materials Page.");
			getWebElement("Inventory.CardView.SDSIcon").click();
			Thread.sleep(8000);
			
			//Verify SDS Functionality
			inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_Download_File_Type_SDS(materialName, filename1, fileType);
		}
		else
			Assert.fail("File type is Invalid to verify the download scenario of an SDS file.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
