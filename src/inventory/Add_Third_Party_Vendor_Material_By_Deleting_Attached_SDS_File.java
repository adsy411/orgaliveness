package inventory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Add_Third_Party_Vendor_Material_By_Deleting_Attached_SDS_File extends TestBase
{
	@Test(priority = 1)
	public void Verify_Deleted_SDS_File_Attached_To_New_Material() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 109;
		
		//Launch the browser and go to home page.
		init();
		
		//Login in to application
		inventoryRegularFunctions.UserLogin(userName,password);
		
		//Adding a material by deleting SDS file
		inventoryRegularFunctions.Add_Third_Party_Vendor_Material_By_Deleting_Attached_SDS(rowNumber);
		
		//Verify the deleted SDS displayed for material
		Thread.sleep(3000);
		getWebElement("Inventory.CardView.SDSIcon").click();
		Thread.sleep(2000);
		
		//Verification of Warning message in card view when Third party vendor material does not contains SDS file.
		Utills.captureScreenshot("Card_View_Third_party_Vendor_Material_SDS_Warning_Message_"+TodayDate.Date());
		String expectedCardViewSDSWarningMsg = "Warning! It looks like you haven't added a safety data sheet to this material! Attach an SDS to this material and we will save it for next you add the material to your inventory.";
		String actualCardViewSDSWarningValidationMsg = getWebElement("Inventory.CardView.SDS.WarningMessage").getText();
		if(expectedCardViewSDSWarningMsg.equalsIgnoreCase(actualCardViewSDSWarningValidationMsg))
			Reporter.log("Deleted SDS file not attached to a material.");
		else
			softAssertion.fail("Card View - SDS -  Warning message not displayed for SDS When third party material does not contain SDS file. Expected warning message is - "+expectedCardViewSDSWarningMsg+". The actual message displayed as  - "+actualCardViewSDSWarningValidationMsg);
		
		//Close the warning message
		getWebElement("Inventory.CardView.WarningMessage.CloseIcon").click();
		Thread.sleep(1000);

		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
