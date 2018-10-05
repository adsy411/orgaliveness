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

public class MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabOwner extends TestBase
{
	@Test
	public void CompleteRequestFlow() throws Exception
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
		
		//Navigate to Materials Page
		Thread.sleep(3000);
		RequestRegularFunctions MaterialPageNavigation = new RequestRegularFunctions();
		MaterialPageNavigation.MaterialPageNavigation();
		
		//Get Initial Materials Count
		Thread.sleep(3000);
		RequestRegularFunctions MaterialInitialCount = new RequestRegularFunctions();
		int InitialCount = MaterialInitialCount.MaterialCount();
		
		//Quick Order Add Multiple Products to Request
		RequestRegularFunctions QuickOrderProduct = new RequestRegularFunctions();
		QuickOrderProduct.QuickOrderAddNewSigmaAldrichRequest(2);
		
		//Select All and Click on Approve All Products
		Thread.sleep(3000);
		RequestRegularFunctions SelectAllProduct = new RequestRegularFunctions();
		SelectAllProduct.MoveAlltoApproved();
		Thread.sleep(20000);
		
		//Select all and Click on Order All Products
		Thread.sleep(3000);
		RequestRegularFunctions OrderAllProduct = new RequestRegularFunctions();
		OrderAllProduct.MoveAlltoOrdered();
		Thread.sleep(20000);
		
		//Select all and Click on Move All to Inventory
		Thread.sleep(3000);
		RequestRegularFunctions MoveAlltoInventory = new RequestRegularFunctions();
		MoveAlltoInventory.MoveAlltoInventory();
		Thread.sleep(20000);
		
		//Material Final Count
		Thread.sleep(3000);
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