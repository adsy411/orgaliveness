package inventory;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Add_Bulk_Of_Materials extends TestBase
{
	@Test(priority = 2)
	public void Add_Materials_In_Bulk() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		int rowNumber = 76;
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);

		//Login in to application
		init();
		inventoryRegularFunctions.UserLogin(userName,password);	

		//Adding bulk of materials
		inventoryRegularFunctions.Add_Materials_In_Bulk(rowNumber);
		
		//Logout from an application.
		inventoryRegularFunctions.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
