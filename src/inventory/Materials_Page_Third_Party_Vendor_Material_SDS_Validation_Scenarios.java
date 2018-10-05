package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Materials_Page_Third_Party_Vendor_Material_SDS_Validation_Scenarios extends TestBase
{
	@Test(priority = 1)
	public void Materials_Page_Third_Party_Vendor_Material_No_SDS_Warning_Message_Validation_Scenario() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 35;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);	
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(5000);
		
		//Clicks on SDS icon.
		getWebElement("Inventory.CardView.SDSIcon").click();
		Thread.sleep(2000);
		
		//Verification of Warning message in card view when Third party vendor material does not contains SDS file.
		Utills.captureScreenshot("Card_View_Third_party_Vendor_Material_SDS_Warning_Message_"+TodayDate.Date());
		String expectedCardViewSDSWarningMsg = "Warning! It looks like you haven't added a safety data sheet to this material! Attach an SDS to this material and we will save it for next you add the material to your inventory.";
		String actualCardViewSDSWarningValidationMsg = getWebElement("Inventory.CardView.SDS.WarningMessage").getText();
		if(expectedCardViewSDSWarningMsg.equalsIgnoreCase(actualCardViewSDSWarningValidationMsg))
			Reporter.log("Card View - SDS - Warning message displayed successfully for SDS When third party material does not contain SDS file.");
		else
			softAssertion.fail("Card View - SDS -  Warning message not displayed for SDS When third party material does not contain SDS file. Expected warning message is - "+expectedCardViewSDSWarningMsg+". The actual message displayed as  - "+actualCardViewSDSWarningValidationMsg);
		
		//Close the warning message
		getWebElement("Inventory.CardView.WarningMessage.CloseIcon").click();
		Thread.sleep(1000);
		
		Reporter.log("-------------------------------------------------------------------------------------------------------------");
		Reporter.log("Verify the validation Scenario for List view of materials Page");
		
		//Navigation to list view
		getWebElement("Inventory.ListView").click();
		Thread.sleep(2000);
		Reporter.log("List view displayed successfully in materials page");
		
		//Clicks on SDS icon.
		getWebElement("Inventory.ListView.SDSIcon").click();
		Thread.sleep(1000);
		
		//Verification of Warning message in list view when Third party vendor material does not contains SDS file.
		Utills.captureScreenshot("List_View_Third_party_Vendor_Material_SDS_Warning_Message_"+TodayDate.Date());
		String expectedListViewSDSWarningMsg = "Warning! It looks like you haven't added a safety data sheet to this material! Attach an SDS to this material and we will save it for next you add the material to your inventory.";
		String actualListViewSDSWarningValidationMsg = getWebElement("Inventory.CardView.SDS.WarningMessage").getText();
		if(expectedListViewSDSWarningMsg.equalsIgnoreCase(actualListViewSDSWarningValidationMsg))
			Reporter.log("List View - SDS - Warning message displayed successfully for SDS When third party material does not contain SDS file.");
		else
			softAssertion.fail("List View - SDS -  Warning message not displayed for SDS When third party material does not contain SDS file. Expected warning message is - "+expectedListViewSDSWarningMsg+". The actual message displayed as  - "+actualListViewSDSWarningValidationMsg);
		
		//Close the warning message
		getWebElement("Inventory.CardView.WarningMessage.CloseIcon").click();
		Thread.sleep(1000);
		
		//Clicks on card view
		getWebElement("Inventory.CardView").click();
		Thread.sleep(1000);
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		
		softAssertion.assertAll();	
	}
}
