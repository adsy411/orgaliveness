package request;

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

public class Export_AllProductsin_ApprovedTab extends TestBase
{
	@Test
	public void ExportAllProductsinApprovedTab() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 3);
		String password = ExcelUtils.getCellData(2, 4);
			
		//Login in to application
		init();
		RequestRegularFunctions login = new RequestRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Quick Order Add multiple New Request
		RequestRegularFunctions QuickOrderProduct = new RequestRegularFunctions();
		QuickOrderProduct.QuickOrderAddNewSigmaAldrichRequest(2);
		
		//Approve All Products
		RequestRegularFunctions approveAllProduct = new RequestRegularFunctions();
		approveAllProduct.MoveAlltoApproved();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		
		//Download All Products
		RequestRegularFunctions Download = new RequestRegularFunctions();
		String Tabname = "Approved";
		Download.DownloadAllProducts(Tabname);
				
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}