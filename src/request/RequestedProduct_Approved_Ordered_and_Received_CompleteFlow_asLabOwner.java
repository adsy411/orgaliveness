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

public class RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabOwner extends TestBase
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
		
		//Move to Inventory Single Product
		Thread.sleep(3000);
		RequestRegularFunctions MoveInventory = new RequestRegularFunctions();
		MoveInventory.MovetoInventory();
		
		//Order Single Product
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