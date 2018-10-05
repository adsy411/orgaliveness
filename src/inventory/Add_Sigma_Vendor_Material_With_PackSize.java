package inventory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Add_Sigma_Vendor_Material_With_PackSize extends TestBase
{
	@Test
	public void Add_Sigma_Material_With_PackSize() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		int rowNumber = 3;
		
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
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String packSize = ExcelUtils.getCellData(rowNumber, 19);
		
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
		Thread.sleep(5000);
		
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
		
		//Verify the provided pack-size exists in list
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.AddMaterialPage.PackSize",packSize) == true)
			//Select a particular pack-size
			SelectList.SelectByValue("Inventory.AddMaterialPage.PackSize",packSize);
		else
			Assert.fail("The PackSize - "+packSize+" does not exist in the list.");
		Thread.sleep(2000);
		
		//Get the material name
		String materialName = getWebElement("Inventory.AddMateriName").getAttribute("value").trim();
		
		//After selecting pack-size, verify the quantity and UOM got changed in Add Material Page
		
		//Add the material to Inventory
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(2000);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.",addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		Reporter.log("Add Material confirmation modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());
		getWebElement("Inventory.OkButton").click();
		impliciteWait(7);
		
		//Verification of Success message after addition of new material.
		Thread.sleep(3000);
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
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Assert.assertTrue("After addition of sigma vendor material using auto complete the material count is not increased.", MaterialsCountAfter == (materialsCountBefore+1));

		//Verify the material displayed at the top
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Added Material - "+materialName+" not displayed at the top of materials page.", materialName.equalsIgnoreCase(actualMaterialName));
		
		//Clicks on More link
		getWebElement("Inventory.CardView.MoreLink").click();
		Thread.sleep(3000);
		Utills.captureScreenshot("Card_View_Material_Details_Expansion_"+TodayDate.Date());
		
		//Verify the catalog number
		String cardViewActualCatalogNumber = getWebElement("Inventory.CardView.CatalogNumber").getAttribute("title").trim();
		String expectedCatalogNumber = catalogNumber+"-"+packSize;
		if(cardViewActualCatalogNumber.equalsIgnoreCase(expectedCatalogNumber))
			Reporter.log("Material - "+materialName+" added successfully with packsize - "+packSize+" in to Inventory.");
		else
			softAssertion.fail("Catalog number displayed is not proper.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}
