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

public class Export_AllProductsin_ReceivedTab extends TestBase
{
	@Test
	public void ExportAllProductsinReceivedTab() throws Exception
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
		
		//Quick Order Add New Request
		RequestRegularFunctions QuickOrderProduct = new RequestRegularFunctions();
		QuickOrderProduct.QuickOrderAddNewSigmaAldrichRequest(4);
		
		//Approve All Products
		RequestRegularFunctions approveallProduct = new RequestRegularFunctions();
		approveallProduct.MoveAlltoApproved();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		
		//Order All Products
		RequestRegularFunctions OrderallProduct = new RequestRegularFunctions();
		OrderallProduct.MoveAlltoOrdered();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		
		//Order All Products
		RequestRegularFunctions MoveallProductToInventory = new RequestRegularFunctions();
		MoveallProductToInventory.MoveAlltoInventory();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		
		//Navigate to Request page
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions NavigatetoRequestpage = new RequestRegularFunctions();
		NavigatetoRequestpage.RequestPageNavigation();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		getWebElement("RequestPage_ReceivedTab").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		
		//Download All Products
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions Download = new RequestRegularFunctions();
		String Tabname = "Received";
		Download.DownloadAllProducts(Tabname);		
		
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}