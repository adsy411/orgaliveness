package labCatalog;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.Reporter;
//import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
//import utills.Utills;
import utills.RequestConstants;

public class AddNew_SigmaAldrichProductto_LabCatalog extends TestBase
{
	@Test
	public void QuickAdd_SigmaAldrichProducttoRequestTab() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
			
		//Login in to application
		init();
		LabCatalogRegularFunctions login = new LabCatalogRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Quick Order Add New SA Product DeleteProduct
		LabCatalogRegularFunctions AddNewProd = new LabCatalogRegularFunctions();
		AddNewProd.AddSigmaAldrichProduct();
		
		//Quick Order Add New SA Product DeleteProduct
		LabCatalogRegularFunctions DeleteProd = new LabCatalogRegularFunctions();
		DeleteProd.DeleteProduct();
				
		//Logout from an application.
		LabCatalogRegularFunctions logout = new LabCatalogRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}