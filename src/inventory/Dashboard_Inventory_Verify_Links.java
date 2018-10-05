package inventory;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Dashboard_Inventory_Verify_Links extends TestBase
{
	@Test(priority = 1)
	public void Dashboard_Page_Inventory_Verify_Add_Add_New_Icon_Functionality() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
				
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		//Click on "Add New" icon under "Inventory" section in dash-board page.
		getWebElement("Inventory.Dashboard.AddNewMaterialIcon").click();
		Thread.sleep(2000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed when user navigated from dashboard page.",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material page displayed successfully when user navigated from dashboard page.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Dashboard_Page_Inventory_Verify_View_In_Inventory_Link_Functionality() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
				
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		//Click on "View In Inventory" link under "Inventory" section in dash-board page.
		getWebElement("Inventory.Dashboard.ViewInInventoryLink").click();
		Thread.sleep(2000);
		
		//Verify materials page exists when clicked on "View In Inventory" link in dash-board page.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("The materials page not displayed when clicked on 'View In Inventory' link in dashboard page.", materialsPageName.equals("Materials"));
		Reporter.log("The materials page displayed successfully, when clicked on 'View In Inventory' link in dashboard page.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}		
}
