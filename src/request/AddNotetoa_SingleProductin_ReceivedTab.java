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

public class AddNotetoa_SingleProductin_ReceivedTab extends TestBase
{
	@Test
	public void AddNotesinReceivedTab() throws Exception
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
		QuickOrderProduct.QuickOrderAddNewSigmaAldrichRequest(1);
		
		//Approve Product
		RequestRegularFunctions approveProduct = new RequestRegularFunctions();
		approveProduct.MovetoApproved();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		
		//Ordered Product
		RequestRegularFunctions OrderProduct = new RequestRegularFunctions();
		OrderProduct.MovetoOrdered();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
				
		//Move Product to Inventory
		RequestRegularFunctions ReceiveProduct = new RequestRegularFunctions();
		ReceiveProduct.MovetoInventory();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		
		//Navigate to Inventory
		RequestRegularFunctions NavigatetoRequestpage = new RequestRegularFunctions();
		NavigatetoRequestpage.RequestPageNavigation();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		getWebElement("RequestPage_ReceivedTab").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		
		//Add Note
		RequestRegularFunctions AddNote = new RequestRegularFunctions();
		String Tabname = "Received";
		AddNote.AddNotetoSingleProduct(Tabname);
		
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}