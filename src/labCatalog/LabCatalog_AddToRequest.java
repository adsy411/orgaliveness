package labCatalog;
import static org.junit.Assert.assertArrayEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class LabCatalog_AddToRequest extends TestBase {
	
	@Test
	public void Edit_Lab_Catalog_Product_Created_By_Lab_Owner() throws Exception {
		//LOGIN TEST DATA
		String labMemUserID = "labmember@20mm.eu";
		String labMemPassword = "admin123";
		
		//Login to application as lab member
		init();
		Reporter.log("Login to Application as lab member");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(labMemUserID);
		getWebElement("Enotebook.login.password").sendKeys(labMemPassword);
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to requests
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_Requests").click();
		Thread.sleep(2000);
		
		//Get initial request count
		java.util.List<WebElement> requests = getWebElement("RequestPage").findElements(By.className("portlet light bordered"));
		int initialReqCount = requests.size();
		
		//Navigate to lab catalog
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_LabCatalog").click();
		
		//Search lab catalog product
		getWebElement("LabCatalogListSearch").click();
		Thread.sleep(2000);
		
		//Verify the product searched is available in lab catalog list or not
		String catalogCountText = getWebElement("Pagination").getText().split(" ")[3];
		int catalogCount = Integer.parseInt(catalogCountText);
		if (catalogCount == 0) {
			System.out.println("");
		}
	}
	
}