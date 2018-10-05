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

public class Material_List_View_Sigma_Material_View_SDS extends TestBase
{
	Library TodayDate = new Library();
	@Test(priority = 1)
	public void ListView_SigmaMaterial_ViewSDS() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
		int rowNumber = 5;

		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_SigmaVendorMaterial");
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		
		//Navigation to materials page to add Sigma-Aldrich Vendor material
		AddSigmaMaterial AddMaterial = new AddSigmaMaterial();
		AddMaterial.AddSigmaAldrichVendorMaterial(catalogNumber);	
		Thread.sleep(1000);
	
		//Clicks on More link
		getWebElement("Inventory.CardView.MoreLink").click();
		Thread.sleep(3000);
		Utills.captureScreenshot("Card_View_Material_Details_Expansion_"+TodayDate.Date());
		
		//Verify the catalog number
		String ActualCatalogNumber = getWebElement("Inventory.CardView.CatalogNumber").getAttribute("title").trim();
		String[] cardViewActualCatalogNumber = ActualCatalogNumber.split("-");
		if(catalogNumber.equalsIgnoreCase(cardViewActualCatalogNumber[0]))
			Reporter.log("Material - '"+catalogNumber+"' added successfully in to Inventory.");
		else
			Assert.fail("Catalog number displayed is not proper.");
		
		//Navigation to list view
		Reporter.log("Clcik on List View icon to view the materials as a list."); 
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		Reporter.log("List view displayed successfully in materials page");
		
		Reporter.log("Click on SDS icon from List View of a Sigma material - "+catalogNumber+" to view the SDS file from Sigma Aldrich website.");
		getWebElement("Inventory.ListView.SDSIcon").click();
		Thread.sleep(3000);
		
		//Verify SDS Functionality
		InventoryRegularFunctions verifySDS = new InventoryRegularFunctions();
		verifySDS.VerifySDS(catalogNumber);
		
		//Set the view to card view
		Thread.sleep(1000);
		getWebElement("Inventory.CardView").click();
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
