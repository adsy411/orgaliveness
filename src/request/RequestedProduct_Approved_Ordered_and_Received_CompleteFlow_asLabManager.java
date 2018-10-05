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

public class RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabManager extends TestBase
{
	@Test
	public void CompleteRequestFlow() throws Exception
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
		
		//Navigate to Materials Page
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions MaterialPageNavigation = new RequestRegularFunctions();
		MaterialPageNavigation.MaterialPageNavigation();
		
		//Get Initial Materials Count
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions MaterialInitialCount = new RequestRegularFunctions();
		int InitialCount = MaterialInitialCount.MaterialCount();
		
		//Quick Order Add New Request
		RequestRegularFunctions QuickOrderProduct = new RequestRegularFunctions();
		QuickOrderProduct.QuickOrderAddNewSigmaAldrichRequest(1);
		
		//Select Single Product
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions SelectProduct = new RequestRegularFunctions();
		SelectProduct.SelectProduct();
				
		//Approve Single Product
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions ApproveProduct = new RequestRegularFunctions();
		ApproveProduct.MovetoApproved();
		
		//Order Single Product
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions OrderProduct = new RequestRegularFunctions();
		OrderProduct.MovetoOrdered();
		
		//Move to Inventory Single Product
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions MoveInventory = new RequestRegularFunctions();
		MoveInventory.MovetoInventory();
		
		//Order Single Product
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		RequestRegularFunctions MaterialFinalCount = new RequestRegularFunctions();
		int FinalCount = MaterialFinalCount.MaterialCount();
		//Verify Material Count Product Link
		if(FinalCount>InitialCount)
		{
			Reporter.log("Material added to Inventory materials");
		}
		else
		{
			Reporter.log("Material Not added to Inventory materials");
		}
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}