package inventory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
//import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
//import utills.Utills;
import utills.Utills;

public class AddSigmaVendorMaterial extends TestBase
{
	@Test
	public void AddSigmaAldrichVendorMaterial() throws Exception
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
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_SigmaVendorMaterial");
		String catalogNumber = ExcelUtils.getCellData(1, 1);
		
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Click on Add Material to add the sigma vandor material.");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date()); 
 
		//Verify Auto Complete modal exist
		Reporter.log("Enter the Sigma vendor material details and add it to Inventory");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		Thread.sleep(7000);
		
		WebElement verifyAutoCompleteTable = getWebElement("Inventory.AddMaterial.VerifyAutoCompleteTable");
		Assert.assertTrue("Auto complete modal not displayed for the entered catalog number -"+catalogNumber,verifyAutoCompleteTable.isDisplayed());
		Utills.captureScreenshot("Add_Material_Auto_Complete_Modal_"+TodayDate.Date());

		//Entering the material details
		WebElement materialFound = null;
		try
		{
			materialFound = driver.findElement(By.xpath("//tr[@data-item-group='Sigma-Aldrich'][1]//td[contains(text(),'"+catalogNumber+"')]"));
			materialFound.isDisplayed();
			Reporter.log("Catalog Number - "+catalogNumber+" displayed in Auto suggestion list.");
		}
		catch(NoSuchElementException e) 
		{
			Assert.fail("Sigma Vendor Material does not exist in auto complete modal for catalog number -"+catalogNumber);
			Utills.captureScreenshot("Add_Material_Auto_Complete_Modal_Fail"+TodayDate.Date());	
		}
			
		materialFound.click();
		Thread.sleep(3000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(3000);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.",addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		Reporter.log("Add Material confirmation modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());
		getWebElement("Inventory.OkButton").click();
		impliciteWait(5);
		
		//Verification of Success message after addition of new material.
		Thread.sleep(2000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			Reporter.log("Success Message displayed successfully after addition of new material as - "+ActualSuccessMessage);
		else 
			softAssertion.fail("Success Message not displayed after addition of new material.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of sigma vendor material, the materials page not displayed.", materialsPageName.equals("Materials"));
		Reporter.log("After addition of sigma vendor material, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(MaterialsCountAfter == (materialsCountBefore+1))
			Reporter.log("After addition of sigma vendor material using auto complete the material count is increased by "+(MaterialsCountAfter-materialsCountBefore));
		else 
			Assert.fail("After addition of sigma vendor material using auto complete the material count is not increased.");					
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}