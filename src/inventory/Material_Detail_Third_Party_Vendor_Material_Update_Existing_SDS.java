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

public class Material_Detail_Third_Party_Vendor_Material_Update_Existing_SDS extends TestBase
{
	Library TodayDate = new Library();
	@Test(priority = 1)
	public void Material_Detail_ThirdPartyVendorMaterial_Update_Existing_SDS_File() throws Exception
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
		int rowNumber = 104;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String updateSDSfileName = ExcelUtils.getCellData(rowNumber, 21);
		String pageName = "material detail page";
		String fileType = null;
		String fileType1 = null;
		String updateSDSfileName1 = ExcelUtils.getCellData(rowNumber, 21);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		
		if(fileName.contains("PDF"))
			fileType1 = "PDF";
		else if(fileName.contains("Image"))
			fileType1 = "Image";
		else if(fileName.contains("Excel"))
			fileType1 = "Excel";
		else
			Assert.fail("File type is not valid.");
		
		if(updateSDSfileName.contains("PDF"))
			fileType = "PDF";
		else if(updateSDSfileName.contains("Image"))
			fileType = "Image";
		else if(updateSDSfileName.contains("Excel"))
		{
			fileType = "Excel";
			updateSDSfileName1 = updateSDSfileName1+".xls";
			
			//Delete the file if it is found in the path
			inventoryRegularFunctions.Delete_File(updateSDSfileName1);
		}
		else
			softAssertion.fail("File type is Invalid.");
		
		//Navigation to materials page to add third party vendor Vendor material with SDS file.
		inventoryRegularFunctions.Add_Third_Party_Vendor_Material_WithSDS(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to material detail page. Verify material detail page exists or not.
		inventoryRegularFunctions.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//View the uploaded SDS file from material detail page.
		Reporter.log("Click on SDS icon of a third party vendor material - "+materialName+" to view the "+fileType1+" type SDS file from material detail Page.");
		getWebElement("Inventory.DetailPage.ViewSDS.Icon").click();
		Thread.sleep(3000);
		
		//Verify SDS view Functionality
		if(fileName.contains("PDF") || fileName.contains("Image"))
			inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_PDF_Image_Type_SDS(materialName, pageName, fileType1);
		else if(fileName.contains("Excel"))
			inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_Download_File_Type_SDS(materialName, fileName, fileType1);
		else
			softAssertion.fail("File type is Invalid.");
		
		//Update the existing SDS file from material detail page.
		Reporter.log("Upload the "+fileType+" SDS file from material detail page to update the existing SDS file.");
		inventoryRegularFunctions.Material_Detail_Upload_SDS_File(updateSDSfileName);
		Thread.sleep(2000);
		
		//Verify the update modal
		String actualUpdateProductModalName = getWebElement("Inventory.DetailPage.UpdateProduct.ModalTitle").getText().trim();
		Assert.assertTrue("Update Product modal not displayed while updating the existing SDS file", actualUpdateProductModalName.equalsIgnoreCase("Update Product"));
		Reporter.log("Update product modal displayed successfully while updating the existing SDS of third party vendor material.");
		Utills.captureScreenshot("Update_Product_SDS_File_"+TodayDate.Date());
		
		//verify the text in the update product modal
		String actualUpdateProductModalContent = getWebElement("Inventory.DetailPage.UpdateProduct.ModalContent").getText().trim();
		String expectedUpdateProductModalContent = "Attaching a safety file will overwrite your current safety file. Would you like to proceed?";
		softAssertion.assertTrue(actualUpdateProductModalContent.equalsIgnoreCase(expectedUpdateProductModalContent),"Contenet Displayed is not proper in update product modal. the contenet displayed as - "+actualUpdateProductModalContent);
		
		//Click on Update button
		getWebElement("Inventory.DetailPage.UpdateProduct.UpdateButton").click();
		Thread.sleep(4000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Screen_"+TodayDate.Date());
		
		String expectedSDSSuccessMessage = "Success! SDS file uploaded successfully to product";
		String actualSDSSuccessMessage = getWebElement("Inventory.DetailPage.UpdateProduct.SDS.SuccessMessage").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Success message displayed successfully after uploading SDS file in material detail page.");
		else
			softAssertion.fail("Success Message not displayed as expected. The message displayed as - "+actualSDSSuccessMessage);
		
		//View the uploaded SDS file from material detail page.
		Reporter.log("Click on SDS icon of a third party vendor material - "+materialName+" to view the updated SDS file from material detail Page.");
		getWebElement("Inventory.DetailPage.ViewSDS.Icon").click();
		Thread.sleep(5000);
		
		//verify the uploaded SDS file
		if(updateSDSfileName.contains("PDF") || updateSDSfileName.contains("Image"))
			inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_PDF_Image_Type_SDS(materialName, pageName, fileType);
		else if(updateSDSfileName.contains("Excel"))
			inventoryRegularFunctions.ThirdParty_Vendor_Material_Verify_Download_File_Type_SDS(materialName, updateSDSfileName1, fileType);
		else
			softAssertion.fail("File type is Invalid to verify the download scenario of an SDS file.");	
		
		//Logout from an application.
		Thread.sleep(1000);
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();		
	}
}
