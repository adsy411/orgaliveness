package inventory;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
//import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;

public class Materials_Page_Filter_Search_Location_Name extends TestBase
{
	@Test(priority = 1)
	public void MaterialsPage_Filter_Search_Location_Name() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 66;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String locationName = ExcelUtils.getCellData(rowNumber, 5);
		
		//Navigation to materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Adding third party Vendor material
		InventoryRegularFunctions addMaterialWithStorage = new InventoryRegularFunctions();
		addMaterialWithStorage.Add_Material_With_Storage(rowNumber, locationName);
		Thread.sleep(1000);
		
		//Perform filter search for location name
		InventoryRegularFunctions locationNameFilterSearch = new InventoryRegularFunctions();
		locationNameFilterSearch.Verify_Location_Name_Filter_Search(locationName);
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		String pagination = getWebElement("Inventory.Pagination").getText();
		int pageCount = Integer.parseInt(pagination.split("Page 1 of ")[1]);
		int materialscount = 0;
		
		//Check whether materials exists
		Assert.assertTrue("After filter search for location name - "+locationName+ ", materials not displayed in searched materials page", materialsCountAfter > 0);

		//Verify the location name till last page
		for(int i=1;i<=pageCount;i++)
		{
			List<WebElement> locationsName = driver.findElements(By.xpath("//span[@id='pageForm:materialList']//div[@class='col-xs-12 col-sm-12 col-md-6 col-lg-7']/label"));
			
			//Check whether the materials displayed based on searched location.
			for(int j=0; j<locationsName.size(); j++)
			{
				String actualLocationName = locationsName.get(j).getText().trim();
				if(actualLocationName.equals(locationName))
					materialscount = materialscount +1;
			}
			
			//check whether the pagination exists
			int currentPage = Integer.parseInt((pagination.split("Page ")[1]).split(" ")[0]);
			if(currentPage < pageCount)
			{
				//Scroll down the page
				JavascriptExecutor js = (JavascriptExecutor) driver;  
				js.executeScript("window.scrollBy(0,1000)");
				Thread.sleep(2000);
				
				getWebElement("Inventory.NextPage").click();
				Thread.sleep(5000);
				
				//Scroll up the page
				JavascriptExecutor js1 = (JavascriptExecutor) driver;  
				js1.executeScript("window.scrollBy(0,-1000)");
				Thread.sleep(2000);
			}
			else
				break;
		}
		
		Assert.assertTrue("The materials displayed is not proper for location name filter search", (materialscount == materialsCountAfter));
		Reporter.log("The materials assigned to location - "+locationName+" displayed in searched result page after location filter search");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
		
	}
}
