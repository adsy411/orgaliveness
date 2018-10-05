package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.junit.Assert;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;
public class Material_Detail_Update_Material_Details extends TestBase
{	
	@Test
	public void MaterialDetailUpdateDetails() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
				
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 21;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
				
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String addMaterialName = ExcelUtils.getCellData(21, 0);
			
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
			
		//Navigation to material detail page. Verify material detail page exists or not.
		InventoryRegularFunctions detailPage = new InventoryRegularFunctions();
		detailPage.MaterialDetailPageNavigation(addMaterialName);
		impliciteWait(2);
		
		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Update_Material");
		String materialTitle = ExcelUtils.getCellData(1, 0);
		//String description = ExcelUtils.getCellData(1, 1);
		String materialName = ExcelUtils.getCellData(1, 2);
		String quantity = ExcelUtils.getCellData(1, 3);
		String casNumber = ExcelUtils.getCellData(1, 5);
		String lotNumber = ExcelUtils.getCellData(1, 6);
		String manufactredDate = ExcelUtils.getCellData(1, 7);
		String expirationDate = ExcelUtils.getCellData(1, 8);
		String url = ExcelUtils.getCellData(1, 9);
		String boilingPoint = ExcelUtils.getCellData(1, 10);
		String meltingPoint = ExcelUtils.getCellData(1, 11);
		String flashingPoint = ExcelUtils.getCellData(1, 12);
		String materialType = ExcelUtils.getCellData(1, 13);
					
		// Updating material title.
		getWebElement("Inventoy.MaterialNameEditIcon").click();
		getWebElement("Inventory.MaterialTitleModal").isDisplayed();
		Assert.assertTrue("Material title modal not displayed",getWebElement("Inventory.MaterialTitleModal").getText().equals("Enter Material Title*"));
		getWebElement("Inventory.MaterialTitleEdit").click();
		getWebElement("Inventory.MaterialTitleEdit").clear();
		getWebElement("Inventory.MaterialTitleEdit").sendKeys(materialTitle);
		getWebElement("Inventory.MaterialTitleSet").click();	
		Thread.sleep(1000);
				
		//Verification of success message
		String materialTitleSuccessMessage = getWebElement("Inventory.MaterialTitleUpdate.SuccessMessage").getText();
		if(materialTitleSuccessMessage.equals("Material name updated successfully"))
			Reporter.log("Success message displayed as - '"+materialTitleSuccessMessage+"' after updating material title in material detail page.");
		else
			softAssertion.fail("Success message not displayed after updating material title in material detail page.");
		Utills.captureScreenshot("Material_Title_Success_Message"+TodayDate.Date());
	
		//Verify material title updated
		String updatedMaterialTitle = getWebElement("Inventory.MateriDetail.MaterialTitle").getText();
		Assert.assertTrue("Material title not updated as - "+materialTitle+" in material detail page.",updatedMaterialTitle.equals(materialTitle));
		Reporter.log("Material title updated successfully as '"+updatedMaterialTitle+"' in material detail page.");	

		//Updating material description
		
		//Updating material details
		getWebElement("Inventory.MaterialDetail.MaterialName").click();
		getWebElement("Inventory.MaterialDetail.MaterialName").clear();
		getWebElement("Inventory.MaterialDetail.MaterialName").sendKeys(materialName);
		getWebElement("Inventory.Material.UpdateQuantity").clear();
		getWebElement("Inventory.Material.UpdateQuantity").sendKeys(quantity);
			
		//Navigation to other details modal
		getWebElement("Inventory.OtherDetailsTab").click();
		impliciteWait(3);
		getWebElement("Inventory.MaterialDetail.CASNumber").clear();
		getWebElement("Inventory.MaterialDetail.CASNumber").sendKeys(casNumber);
		getWebElement("Inventory.MaterialDetail.LotNumber").clear();
		getWebElement("Inventory.MaterialDetail.LotNumber").sendKeys(lotNumber);
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").clear();
		getWebElement("Inventory.MaterialDetail.ManufacturedDate").sendKeys(manufactredDate);
		getWebElement("Inventory.MaterialDetail.ExpirationDate").clear();
		getWebElement("Inventory.MaterialDetail.ExpirationDate").sendKeys(expirationDate);
		getWebElement("Inventory.MaterialDetail.URL").clear();
		getWebElement("Inventory.MaterialDetail.URL").sendKeys(url);
		getWebElement("Inventory.MaterialDetail.BoilingPoint").clear();
		getWebElement("Inventory.MaterialDetail.BoilingPoint").sendKeys(boilingPoint);
		getWebElement("Inventory.MaterialDetail.MeltingPoint").clear();
		getWebElement("Inventory.MaterialDetail.MeltingPoint").sendKeys(meltingPoint);
		getWebElement("Inventory.MaterialDetail.FlashingPoint").clear();
		getWebElement("Inventory.MaterialDetail.FlashingPoint").sendKeys(flashingPoint);
				
		//Verify the provided material type exists in list
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.MaterialDetail.MaterialType",materialType) == true)
		{
			SelectList.SelectByVisibleText("Inventory.MaterialDetail.MaterialType",materialType);
			Thread.sleep(1000);
		}
		else
		{
			softAssertion.fail("The Material Type - "+materialType+" does not exist");
			getWebElement("Inventory.MaterialDetail.MaterialType").click();
			Utills.captureScreenshot("Material_Type_F_"+TodayDate.Date());
		}
			
		Thread.sleep(1000);
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(5000);
			
		//Verification of Update material confirmation modal
		//getWebElement("Inventory.MaterialDetail.UpdateMaterialModal").isDisplayed();
		String updateMaterialModal = getWebElement("Inventory.MD.UpdateMaterialConfirmModal").getText();
		Assert.assertTrue("Update material confiramtion modal not displayed",updateMaterialModal.equals("Update Material"));
		Reporter.log("Update material confirmation modal displayed");
		
		//Verify the details in confirmation modal
		String confirmModalCASNumber = getWebElement("Inventory.MD.UpdateMaterialConfirmModal.CASLabel").getText()
				+getWebElement("Inventory.MD.UpdateMaterialConfirmModal.CASNumber").getText();
		String confirmModalMeltingPoint = getWebElement("Inventory.MD.UpdateMaterialConfirmModal.MeltingPointLabel").getText()
				+getWebElement("Inventory.MD.UpdateMaterialConfirmModal.MeltingPoint").getText();
		String confirmModalBoilingPoint = getWebElement("Inventory.MD.UpdateMaterialConfirmModal.BoilingPointLabel").getText()
				+getWebElement("Inventory.MD.UpdateMaterialConfirmModal.BoilingPoint").getText();
		String confirmModalFlashingPoint = getWebElement("Inventory.MD.UpdateMaterialConfirmModal.FlashingPointLabel").getText()
				+getWebElement("Inventory.MD.UpdateMaterialConfirmModal.FlashingPoint").getText();
					
		System.out.println(confirmModalCASNumber+" "+confirmModalMeltingPoint+" "+confirmModalBoilingPoint+" "+confirmModalFlashingPoint);
		
		Thread.sleep(1000);
		
		if(confirmModalCASNumber.equals("CAS:"+casNumber) 
						&& confirmModalMeltingPoint.equals("Melting Point:"+meltingPoint)
						&& confirmModalBoilingPoint.equals("Boiling Point:"+boilingPoint)
						&& confirmModalFlashingPoint.equals("Freezing Point:"+flashingPoint))
			Reporter.log("The cas number, boiling point, melting point and flashing point displayed in update material confirmation modal.");
		else	
			softAssertion.fail("The cas number, boiling point, melting point and flashing point not displayed in update material confirmation modal.");
		Utills.captureScreenshot("Update_Material_Confirm_Modal_"+TodayDate.Date());
		
		getWebElement("Inventory.MD.UpdateMaterialConfirmModal.UpdateButton").click();
		Thread.sleep(2000);
			
		//Verification of success message after updating material details.
		String updatematerialSuccessMessage = getWebElement("Inventory.UpdateMaterialDetails.SuccessMessage").getText();
		if(updatematerialSuccessMessage.equals("Success! Material updated"))
			Reporter.log("The success message displayed successfully as - '"+updatematerialSuccessMessage+"' after updating material details.");
		else
			softAssertion.fail("The success message not displayed after updating material details");
		Utills.captureScreenshot("Update_Material_Success_Message_"+TodayDate.Date());
			
		//Verify after updation of material, materials page exists or not.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After updating material details, Materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After updating material details, Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
				
		//Verify the updated material displayed at the top of the page
		String listMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("After updation, Material - "+materialName+" not displayed at the top of materials page.",listMaterialName.equals(materialName));
		Reporter.log("After updation, Material - '"+materialName+"' displayed at the top of materials page.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
