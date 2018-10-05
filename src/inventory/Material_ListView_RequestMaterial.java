package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Material_ListView_RequestMaterial extends TestBase
{
	@Test
	public void ListViewRequestMaterial() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 24;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(2000);
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Navigation to list view
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		Reporter.log("List view displayed successfully in materials page");
		
		//Click on Request icon
		getWebElement("Inventory.ListView.RequestIcon").click();
		Thread.sleep(1000);
		
		//Requesting a material from list view
		InventoryRegularFunctions materialRequest = new InventoryRegularFunctions();
		materialRequest.MaterialsPage_MaterialRequestModal(materialName);
		
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
