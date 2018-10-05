package request;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class AddNotetoa_SingleProductin_ApprovedTab extends TestBase {
	@Test
	public void AddNotesinApprovedTab() throws Exception
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
		
		//Add Note
        /*WebElement A=driver.findElement(By.cssSelector("body.page-header-fixed.page-sidebar-closed.page-sidebar-closed-hide-logo.page-content-white:nth-child(2) div.page-wrapper:nth-child(2) div.page-container:nth-child(12) div.page-content-wrapper div.page-content.project-listing-wrapper.product-cart-wrapper.requested-wrapper.requested-wrapper-new:nth-child(3) div.row div.col-lg-8:nth-child(1) div.tab-content div.tab-pane.active:nth-child(2) div.row:nth-child(3) div.col-xs-12.col-md-12.col-lg-12 div.portlet.light.bordered:nth-child(1) div.row div.col-lg-11.product-box div.portlet-title div.row div.col-xs-12.col-sm-6.col-md-6.col-lg-7.text-right:nth-child(2) ul.pager.wizard li:nth-child(5) a.text-primary.moreLink0:nth-child(1) > i.fa.fa-sticky-note"));
        A.click();*/
		
		//driver.findElement(By.
        RequestRegularFunctions AddNote = new RequestRegularFunctions();
		String Tabname = "Approved";
		AddNote.AddNotetoSingleProduct(Tabname);
		
		//Logout from an application.
		RequestRegularFunctions logout = new RequestRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}