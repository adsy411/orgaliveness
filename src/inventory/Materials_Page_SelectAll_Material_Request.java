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

public class Materials_Page_SelectAll_Material_Request extends TestBase 
{
	@Test
	public void MaterialsPageSelectAllMaterialRequest() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 26;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Selecting all the materials
		getWebElement("Inventory.MultipleMaterial.SelectIcon").click();
		Thread.sleep(2000);
		Assert.assertTrue("Materials not selected in material card view page",getWebElement("Inventory.MaterialsPage.MultipleMaterial.VerifySelect").isDisplayed());
		Reporter.log("All materials selected successfully in material card view page");
		Utills.captureScreenshot("Material_SelectAll_"+TodayDate.Date());
			
		//Requesting all the materials present in the list
		getWebElement("Inventory.MultipleMaterial.RequestIcon").click();
		explicitWaitUntilElementIsInvisible("Inventory.LoadingSymbol");
		Thread.sleep(2000);
			
		//Verification of Success message after requesting a new material.
		String ActualRequestMaterialSuccessMessage = getWebElement("Inventory.RequestSuccessMessage").getText();
		String ExpectedRequestMaterialSuccessMessage = "Success! Material Requested";
		if(ActualRequestMaterialSuccessMessage.equals(ExpectedRequestMaterialSuccessMessage))
			Reporter.log("After requesting all materials, the Success Message displayed successfully as - '"+ActualRequestMaterialSuccessMessage+"' in material card view page.");
		else
			softAssertion.fail("After requesting all materials, the Success Message not displayed in material card view page.");
		Utills.captureScreenshot("Material_Request_Success_Message_"+TodayDate.Date());
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
