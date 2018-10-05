package inventory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Materials_Page_Filter_Search_Material_Type extends TestBase
{
	@Test(priority = 1)
	public void MaterialsPage_Filter_Search_Material_Type() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 49;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialType = ExcelUtils.getCellData(rowNumber, 17);
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Click on Filter icon
		getWebElement("Inventory.Filter").click();
		Thread.sleep(3000);
		
		//Verify Material Type filter section displayed in filter modal
		Assert.assertTrue("Material Type filter section not displayed in filter modal", getWebElement("Inventory.FilterSearch.Verify.MaterialTypeField").isDisplayed());
		Reporter.log("Material Type filter section displayed in filter modal");
		
		getWebElement("Inventory.FilterSearch.MaterialType").click();
		getWebElement("Inventory.FilterSearch.MaterialType").sendKeys(materialType);
		Thread.sleep(2000);
		
		//Verify auto suggestions displayed
		Assert.assertTrue("Auto suggestions not displayed for Material Type search field in filter modal",getWebElement("Inventory.FilterSearch.AutoComplete.MaterialType").isDisplayed());
		Reporter.log("Auto suggestions displayed in Material Type search field");
		
		boolean materialTypeExist = false;
		int i=0;
		List<WebElement> materialTypeAutoSuggestions =  driver.findElements(By.xpath("//span[@id='materialPageForm:filterName_panel']/ul/li"));
		for(i=0;i<materialTypeAutoSuggestions.size();i++)
		{
			String materialTypeName = materialTypeAutoSuggestions.get(i).getText().trim();
			if(materialType.equalsIgnoreCase(materialTypeName))
			{
				materialTypeExist = true;
				break;
			}
			else
				materialTypeExist = false;
		}
		
		Assert.assertTrue("Material Type - "+materialType+" not displayed in Auto suggestion list",(materialTypeExist == true));
		Reporter.log("Material Type - "+materialType+" displayed in Auto suggestion list");
		
		//selects the material type from auto complete list
		driver.findElement(By.xpath("//span[@id='materialPageForm:filterName_panel']/ul/li["+(i+1)+"]")).click();
		Thread.sleep(2000);
		
		//Check the selected filter displayed 
		String filterName = getWebElement("Inventory.FilterSearch.MaterialType.FilteredName").getText();
		Assert.assertTrue("Selected Material type - "+materialType+" not added to the filter list",materialType.toLowerCase().contains(filterName.toLowerCase()));
		Reporter.log("Selected Material type - "+materialType+" added to the filter list");
		
		//Clicks on Apply button
		getWebElement("Inventory.FilterSearch.ApplyButton").click();
		Thread.sleep(7000);
		
		//Verify materials page displayed after filter search
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After filter search, Materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After filter search, Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//verify the material displayed based on filter search
		String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
		Assert.assertTrue("After filter search for material type, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equals(materialName));
		Reporter.log("After filter search for material type, the material - '"+materialName+"' displayed in searched materials page.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}