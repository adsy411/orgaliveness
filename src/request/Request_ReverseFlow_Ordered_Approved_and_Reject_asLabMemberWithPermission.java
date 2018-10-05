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

public class Request_ReverseFlow_Ordered_Approved_and_Reject_asLabMemberWithPermission extends TestBase
{
	@Test
	public void CompleteReverseRequestFlow() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(3, 6);
		String password = ExcelUtils.getCellData(3, 7);
			
		//Login in to application
		init();
		RequestRegularFunctions login = new RequestRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Quick Order Add New Request
		RequestRegularFunctions QuickOrderProduct = new RequestRegularFunctions();
		QuickOrderProduct.QuickOrderAddNewSigmaAldrichRequest(1);
		
		//Select Single Product
		Thread.sleep(3000);
		RequestRegularFunctions SelectProduct = new RequestRegularFunctions();
		SelectProduct.SelectProduct();
				
		//Approve Single Product
		Thread.sleep(3000);
		RequestRegularFunctions ApproveProduct = new RequestRegularFunctions();
		ApproveProduct.MovetoApproved();
		
		//Order Single Product
		Thread.sleep(3000);
		RequestRegularFunctions OrderProduct = new RequestRegularFunctions();
		OrderProduct.MovetoOrdered();
		
		//Move to Approved Single Product
		Thread.sleep(3000);
		RequestRegularFunctions MoveitBacktoApproved = new RequestRegularFunctions();
		MoveitBacktoApproved.MoveBacktoApproved();
		
		//Move to MoveBack to Request Single Product
		Thread.sleep(3000);
		RequestRegularFunctions MoveitBacktoRequest = new RequestRegularFunctions();
		MoveitBacktoRequest.MoveBacktoRequest();
				
		//Move to MoveBack to Request Single Product
		Thread.sleep(3000);
		RequestRegularFunctions MoveitBackandReject = new RequestRegularFunctions();
		MoveitBackandReject.Reject();
				
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}