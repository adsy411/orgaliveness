package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Material_Detail_DeleteMaterial extends TestBase
{
	@Test(priority = 1)
	public void MaterialDetailDeleteMaterial() throws Exception
	{		
		SoftAssert softAssertion= new SoftAssert();
		int rowNumber = 17;
	
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
								
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);	
		Thread.sleep(1000);
	
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());

		//Navigation to material detail page.
		impliciteWait(2);
		InventoryRegularFunctions MaterialDetailPage = new InventoryRegularFunctions();
		MaterialDetailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
			
		//Deletion of a material
		InventoryRegularFunctions materialDeletion = new InventoryRegularFunctions();
		materialDeletion.Detail_Page_Delete_Material(materialName);
		
		//Verify the count of materials when a material is deleted.
		int materialsCountAfterDeletion = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Assert.assertTrue("After deletion of material - "+materialName+" the material count is not decreased.", materialsCountAfterDeletion == (materialsCountBefore-1));
		Reporter.log("Material - '"+materialName+"' deleted successfully from material detail Page.");
		
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