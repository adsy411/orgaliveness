package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Dashboard_Verify_Materials_Count extends TestBase
{
	@Test(priority = 1)
	public void Dashboard_Verify_Materials_Count_After_Add_OR_Delete_Material() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
				
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		//Take the count of materials in dash-board page before addition of new material.
		int dashboardPageMaterialsCountBeforeAdd = Integer.parseInt(getWebElement("Inventory.Dashboard.MaterialsCount").getText());
		
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(44, 0);
		int rowNumber = 44;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(2000);
		
		//Navigation to dash-board page.
		InventoryRegularFunctions dashboardPageNavigation = new InventoryRegularFunctions();
		dashboardPageNavigation.DashboardPageNavigation();
		Thread.sleep(3000);		
		
		//Take the count of materials in dash-board page.
		int dashboardPageMaterialsCountAfterAdd = Integer.parseInt(getWebElement("Inventory.Dashboard.MaterialsCount").getText());
		
		//Verify after addition of new material, the material count increased in dash-board page.
		if(dashboardPageMaterialsCountAfterAdd == (dashboardPageMaterialsCountBeforeAdd + 1))
			Reporter.log("After addition of new material the material count increased in Dashboard page.");
		else
			softAssertion.fail("After addition of new material, material count is not increased in Dashboard page.");
		Utills.captureScreenshot("Dashboard_Page_Material_Count_After"+TodayDate.Date());	
		
		//Navigation to Materials Page
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		Thread.sleep(1000);
		
		//Navigation to dispose modal
		getWebElement("Inventory.CardView.DisposeMaterial").click();
		Thread.sleep(1000);
		
		//Verify dispose modal exist or not
		InventoryRegularFunctions verifyDeleteModal = new InventoryRegularFunctions();
		verifyDeleteModal.VerifyDisposeModal_CardView(materialName);
			
		//Deletes a material
		getWebElement("Inventory.CardView.DisposeModal.DeleteRadioButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.DisposeButton").click();
		impliciteWait(2);
			
		//Verification of Success message after deletion of material.
		String ActualMaterialDeletionSuccessMessage = getWebElement("Inventory.DeleteMaterialSuccessMessage").getText();
		String ExpectedMaterialDeletionSuccessMessage = "Material Deleted";
		if(ActualMaterialDeletionSuccessMessage.equals(ExpectedMaterialDeletionSuccessMessage))
			Reporter.log("After material deletion, the Success Message displayed successfully as - "+ActualMaterialDeletionSuccessMessage);
		else 
			softAssertion.fail("After material deletion, the Success Message not displayed.");
		Utills.captureScreenshot("Material_Delete_Success_Message_F_"+TodayDate.Date());
		
		//Navigation to dash-board page.
		dashboardPageNavigation.DashboardPageNavigation();
		Thread.sleep(2000);		
		
		//Take the count of materials in dash-board page.
		int dashboardPageMaterialsCountAfterDelete = Integer.parseInt(getWebElement("Inventory.Dashboard.MaterialsCount").getText());
		
		//Verify after addition of new material, the material count increased in dash-board page.
		if(dashboardPageMaterialsCountAfterDelete == (dashboardPageMaterialsCountAfterAdd - 1))
			Reporter.log("After deletion of material, the material count decreased in Dashboard page.");
		else
			softAssertion.fail("After deletion of material, material count is not decreased in Dashboard page.");
		Utills.captureScreenshot("Dashboard_Page_Material_Count_After"+TodayDate.Date());	
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
