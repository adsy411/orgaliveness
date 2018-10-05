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

public class Material_Card_View_Add_GHS_Symbol extends TestBase
{
	@Test(priority = 1)
	public void CardViewAddGHSSymbol() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 48;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Get the GHS symbol name from excel
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Dispose_Delete_Barcode_Request");
		String GHSSymbolName = ExcelUtils.getCellData(2, 13);
		
		//Clicks on More link
		getWebElement("Inventory.CardView.MoreLink").click();
		Thread.sleep(2000);
		Utills.captureScreenshot("GHS_Icon_Before_Addition_Of_GHS_Symbol"+TodayDate.Date());
		
		//Add the GHS Symbol
		InventoryRegularFunctions addGHSSymbol = new InventoryRegularFunctions();
		addGHSSymbol.Add_GHS_Symbol_Card_View(GHSSymbolName);
		Thread.sleep(2000);
		Utills.captureScreenshot("GHS_Icon_After_Addition_Of_GHS_Symbol"+TodayDate.Date());
		
		//Verify the added GHS symbol in card view page
		String cardViewAddedGHSSymbolName = getWebElement("Inventory.GHS.AddedGHSSymbolName").getAttribute("title");
		Assert.assertTrue("Card View - GHS symbol - "+GHSSymbolName+" not attached to a third party vendor material - "+materialName,cardViewAddedGHSSymbolName.equalsIgnoreCase(GHSSymbolName));
		Reporter.log("Card View - GHS symbol - "+GHSSymbolName+" attached successfully to a third party vendor material - "+materialName);	
		
		//Navigation to material detail page to check the expiration date
		getWebElement("Inventory.MaterialDetailNavigation").click();
		impliciteWait(5);
		Thread.sleep(1000);
		
		//Verify the added GHS symbol in material detail page
		String detailPageGHSSymbolName = getWebElement("Inventory.DetailPage.AddedGHSSymbolName").getAttribute("title");
		Assert.assertTrue("Material Detail Page - GHS symbol - "+GHSSymbolName+" not attached to a third party vendor material - "+materialName,detailPageGHSSymbolName.equalsIgnoreCase(GHSSymbolName));
		Reporter.log("Material Detail Page - GHS symbol - "+GHSSymbolName+" attached successfully to a third party vendor material - "+materialName);	
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
