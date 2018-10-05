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

public class QuickAdd_Multiple_SigmaAldrichProducttoRequestTab_asLabOwner extends TestBase
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
		RequestRegularFunctions login = new RequestRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Quick Order Add New Request
		RequestRegularFunctions QuickOrderProduct = new RequestRegularFunctions();
		QuickOrderProduct.QuickOrderAddNewSigmaAldrichRequest(2);
		
		//Verify Reject Product Link
		RequestRegularFunctions rejectalllinkexist = new RequestRegularFunctions();
		boolean Rejectall = rejectalllinkexist.RequestPageVerifyRejectOption();
		if(Rejectall)
		{
			Reporter.log("Reject All Link is getting Displayed - Logged in as Lab Owner");
		}
		else
		{
			Reporter.log("Reject All Link is NOT getting Displayed - Logged in as other user");
		}
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}