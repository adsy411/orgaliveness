package inventory;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Material_Detail_Download_Attached_Files extends TestBase
{
	@Test(priority = 1)
	public void Material_Detail_Download_Attached_File() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 120;
		
		//Fetch the material details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String uploadFileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		
		if(fileName.contains("PDF"))
		{
			fileType = "PDF";
			fileName = fileName+".pdf";
		}
		else if(fileName.contains("Image"))
		{
			fileType = "Image";
			fileName = fileName+".png";
			
		}
		else if(fileName.contains("Excel"))
		{
			fileName = fileName+".xls";
			fileType = "Excel";
		}
		else
			Assert.fail("File type is not valid.");
		
		//Delete the file if it is found in the path
		inventoryRegularFunctions.Delete_File(fileName);
		Thread.sleep(1000);
		
		//Login in to application
		init();
		inventoryRegularFunctions.UserLogin(userName,password);	
		
		//Get the user name after login in to application.
		String labUserName = inventoryRegularFunctions.Get_LoggedIn_UserName();
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(3000);
		
		//Navigation to material detail page.
		impliciteWait(2);
		inventoryRegularFunctions.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Upload the file
		Reporter.log("Attach the "+fileType+" type file from material detail page.");
		String fileUploadedDateAndTime = inventoryRegularFunctions.Material_Detail_Page_File_Upload(uploadFileName);
		
		//Verification of file upload success message in material detail page.
		inventoryRegularFunctions.Material_Detail_Page_File_Upload_Success_Message_Verification();
		
		//Verify the attached file displayed
		Thread.sleep(2000);
		inventoryRegularFunctions.Material_Detail_Page_Verify_Uploaded_FileName(uploadFileName, labUserName, fileUploadedDateAndTime, materialName, fileType);
		
		//Download the attached file
		Reporter.log("Download the attached "+fileType+" type file under Attachments Section of material - "+materialName);
		getWebElement("Inventory.DetailPage.Attachments.Download.Icon").click();
		Thread.sleep(5000);
		
		//Verify the downloaded attached file.
		inventoryRegularFunctions.Material_Detail_Verify_Downloaded_Attachment_File(fileName, fileType);
		
		//Logout from an application.
		Thread.sleep(2000);
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}

}
