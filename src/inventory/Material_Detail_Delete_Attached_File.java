package inventory;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Material_Detail_Delete_Attached_File extends TestBase
{
	@Test(priority = 1)
	public void Material_Detail_Page_Delete_Attached_File() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 112;
		
		//Fetch the material details
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String uploadFileName = ExcelUtils.getCellData(rowNumber, 20);
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
		
		//Delete the attached file
		Thread.sleep(2000);
		Reporter.log("Delete the attached file from material detail page of material - "+materialName);
		inventoryRegularFunctions.Material_Detail_Page_Delete_Attached_File();
		
		//Verification of no attachments message in material detail page.
		Utills.captureScreenshot("Detail_Page_No_Attachments_Message_"+TodayDate.Date());
		Reporter.log("Verify after file deletion, No attachments message displayed in Attachments Section.");
		String expectedDetailPageNoAttachmentsMsg = "There are no attachments";
		String actualDetailPageNoAttachmentsMsg = getWebElement("Inventory.DetailPage.Attachments.Section.Message").getText().trim();
		if(expectedDetailPageNoAttachmentsMsg.equalsIgnoreCase(actualDetailPageNoAttachmentsMsg))
			Reporter.log("'"+actualDetailPageNoAttachmentsMsg+"' message displayed successfully when there is no files attached to a material.");
		else
			softAssertion.fail("The expected message not displayed. The actual message displayed is - "+actualDetailPageNoAttachmentsMsg);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();
	}
}
