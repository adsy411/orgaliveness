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

public class MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabManager extends TestBase
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
		
		//Select all and Click on Move Move Back to Approved
		Thread.sleep(3000);
		RequestRegularFunctions MoveAllbacktoOrdered = new RequestRegularFunctions();
		MoveAllbacktoOrdered.MoveBackAlltoApproved();
		Thread.sleep(20000);
		
		//Select all and Click on Move Move Back to Request
		Thread.sleep(3000);
		RequestRegularFunctions MoveAllbacktoRequest = new RequestRegularFunctions();
		MoveAllbacktoRequest.MoveBackAlltoRequestTab();
		Thread.sleep(20000);
				
		//Select all and Click on Move Move Back to Request
		Thread.sleep(3000);
		RequestRegularFunctions RejectAll = new RequestRegularFunctions();
		RejectAll.RejectAll();
		Thread.sleep(20000);
				
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}