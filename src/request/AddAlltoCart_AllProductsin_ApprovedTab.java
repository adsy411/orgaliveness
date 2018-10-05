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

public class AddAlltoCart_AllProductsin_ApprovedTab extends TestBase
{
	@Test
	public void AddAllProducttoCartinApprovedTab() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
			
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
		
		//Add All to Cart
		RequestRegularFunctions AddAlltoCart = new RequestRegularFunctions();
		String Tabname = "Approved";
		AddAlltoCart.AddAllProductstoCart(Tabname);
		
		
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}