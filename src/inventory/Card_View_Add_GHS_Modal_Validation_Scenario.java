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

public class Card_View_Add_GHS_Modal_Validation_Scenario extends TestBase
{
	@Test(priority = 1)
	public void AddGHSSymbol_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 57;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Clicks on More link
		getWebElement("Inventory.CardView.MoreLink").click();
		Thread.sleep(2000);
		Utills.captureScreenshot("GHS_Icon_Before_Addition_Of_GHS_Symbol"+TodayDate.Date());
		
		//Clicks on ADD GHS Symbol icon
		getWebElement("Inventory.CardView.AddGHSSymbol").click();
		Thread.sleep(2000);
		impliciteWait(5);
		
		//Verify GHS modal displayed
		String GHSModalName = getWebElement("Inventory.GHS.VerifySafetyModal").getText();
		Assert.assertTrue("Safety Modal not displayed.",GHSModalName.equalsIgnoreCase(GHSModalName));
		Reporter.log("Add Safety Image modal displayed.");	
		
		//Clicks on Add button without selecting the GHS symbol
		getWebElement("Inventory.GHS.ADDButton").click();
		Thread.sleep(1000);
		
		//Verify the validation message when GHS symbol not selected.
		String actualGHSValidationMessage = getWebElement("Inventory.GHS.Modal.ValidationMessage").getText().trim();
		String expectedGHSValidationMessage = "No SDS file Please upload SDS file.";
		softAssertion.assertTrue(actualGHSValidationMessage.equalsIgnoreCase(expectedGHSValidationMessage),"Validation message not displayed when user tries to add GHS symbol without selecting it.");
		Reporter.log("Validation message displayed as - '"+actualGHSValidationMessage+"' when user tries to Add GHS symbol without selecting it.");	
		Utills.captureScreenshot("GHS_Validation_Message"+TodayDate.Date());	
		
		//Close the GHS modal
		getWebElement("Inventory.GHS.Modal.CancelButton").click();
		Thread.sleep(2000);
		
		//Verify materials page displayed after closing GHS modal
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		softAssertion.assertTrue(materialsPageName.equals("Materials"),"After closing GHS modal, Materials page not displayed");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
