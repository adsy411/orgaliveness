package inventory;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Add_Material_Auto_Complete_Existing_Lab_Catalog_Product extends TestBase
{
	@Test
	public void Add_Material_Auto_Complete_Existing_Product() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		int rowNumber = 1;
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
					
		//Login in to application
		init();
		inventoryRegularFunctions.UserLogin(userName,password);				
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		
		//Adding third party Vendor material.
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
				
		//Adding the existing lab catalog product.
		inventoryRegularFunctions.Add_Material_Using_Existing_Product_Details(materialName, catalogNumber);
		
		//Logout from an application.
		inventoryRegularFunctions.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}