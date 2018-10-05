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

public class Material_Detail_Sigma_Material_View_SDS extends TestBase
{
	Library TodayDate = new Library();
	@Test(priority = 1)
	public void MaterialDetail_SigmaMaterial_ViewSDS() throws Exception
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
		int rowNumber = 6;
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_SigmaVendorMaterial");
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
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
		
		//Navigation to material detail page. Verify material detail page exists or not.
		InventoryRegularFunctions detailPage = new InventoryRegularFunctions();
		detailPage.MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Reporter.log("Click on SDS icon from material detail page of a Sigma material - "+catalogNumber+" to view the SDS file from Sigma Aldrich website.");
		getWebElement("Inventory.DetailPage.ViewSDS.Icon").click();
		Thread.sleep(3000);
		
		//Verify SDS Functionality
		InventoryRegularFunctions verifySDS = new InventoryRegularFunctions();
		verifySDS.VerifySDS(catalogNumber);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
