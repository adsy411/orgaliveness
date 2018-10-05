package inventory;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Material_CardView_AddBarcode extends TestBase
{
	@Test(priority = 1)
	public void CardViewAddBarcode() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 10;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);

		//Fetch the material details from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String barcodeName = ExcelUtils.getCellData(2, 6);
		
		//Adding barcode
		InventoryRegularFunctions cardViewAddBarcode = new InventoryRegularFunctions();
		cardViewAddBarcode.Card_View_Add_Barcode(barcodeName);
		
		softAssertion.assertAll();
	}
	
	//Logout from an application.
	@Test(priority = 2)
	public void Logout() throws Exception
	{
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
	}
}
