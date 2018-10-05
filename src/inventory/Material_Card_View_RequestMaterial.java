package inventory;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Material_Card_View_RequestMaterial extends TestBase
{
	Library TodayDate = new Library();
	SoftAssert softAssertion= new SoftAssert();
	
	@Test(priority = 1)
	public void CardViewRequestMaterial() throws Exception
	{	
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
			
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);		
		
		int rowNumber = 9;
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);	
	
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Click on Request icon of an material from card view page
		getWebElement("Inventory.CardView.RequestMaterial").click();
		
		//Wait until the loading icon disappear
		explicitWaitUntilElementIsInvisible("Inventory.LoadingSymbol");
		
		//Requesting the material from card view page
		InventoryRegularFunctions materialRequest = new InventoryRegularFunctions();
		materialRequest.MaterialsPage_MaterialRequestModal(materialName);
		
		softAssertion.assertAll();
	}
	
	//Logout from an application.
	@Test(priority = 2)
	public void Logout() throws Exception
	{
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}	
}
