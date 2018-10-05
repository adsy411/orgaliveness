package labCatalog;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.RequestConstants;
import utills.Utills;
import pageLibrary.Library;
//import java.util.List<WebElement>;
public class Request_SigmaAldrichProduct_FromProductDetailsPage extends TestBase {

@Test
public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner_and_RequestProduct_FromProductDetailsPage() throws Exception {
	SoftAssert softAssertion= new SoftAssert();
	//Library TodayDate = new Library();
	
	//Fetch the user name and password
	ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
	String userName = ExcelUtils.getCellData(2, 0);
	String password = ExcelUtils.getCellData(2, 1);
		
	//Login in to application
	init();
	LabCatalogRegularFunctions login = new LabCatalogRegularFunctions();
	login.UserLogin(userName,password);			
	
	//Navigate to lab catalog Page
			Reporter.log("Navigate to lab catalog list");
			getWebElement("SideBar_Inventory_EHS_Group").click();
			impliciteWait(3);
			getWebElement("SubMenu_LabCatalog").click();
			impliciteWait(6);
	
	getWebElement("Cardview_ProductNameLink").click();
	Thread.sleep(5000);
	//Click on the Request Icon in the details view view
	driver.findElement(By.xpath("//a[@title='Request Item']")).click();
	Thread.sleep(3000);
			impliciteWait(20);
			driver.findElement(By.xpath("//button[contains(text(),'Request')]")).click();
			Thread.sleep(5000);
			impliciteWait(20);
			driver.navigate().refresh();
	
			//Logout from an application.
			LabCatalogRegularFunctions logout = new LabCatalogRegularFunctions();
			logout.UserLogout();
			driver.quit();
			softAssertion.assertAll();	
}
@Test
public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager_and_RequestProduct_FromProductDetailsPage() throws Exception {
	SoftAssert softAssertion= new SoftAssert();
	//Library TodayDate = new Library();
	
	//Fetch the user name and password
	ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
	String userName = ExcelUtils.getCellData(2, 3);
	String password = ExcelUtils.getCellData(2, 4);
		
	//Login in to application
	init();
	LabCatalogRegularFunctions login = new LabCatalogRegularFunctions();
	login.UserLogin(userName,password);			
	
	//Navigate to lab catalog Page
	Reporter.log("Navigate to lab catalog list");
	getWebElement("SideBar_Inventory_EHS_Group").click();
	impliciteWait(3);
	getWebElement("SubMenu_LabCatalog").click();
	impliciteWait(6);

getWebElement("Cardview_ProductNameLink").click();
Thread.sleep(5000);
//Click on the Request Icon in the details view view
driver.findElement(By.xpath("//a[@title='Request Item']")).click();
Thread.sleep(13000);
	impliciteWait(20);
	driver.findElement(By.xpath("//button[contains(text(),'Request')]")).click();
	Thread.sleep(5000);
	impliciteWait(20);
	driver.navigate().refresh();
	
			//Logout from an application.
			LabCatalogRegularFunctions logout = new LabCatalogRegularFunctions();
			logout.UserLogout();
			driver.quit();
			softAssertion.assertAll();	
}
@Test
public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember_and_RequestProduct_FromProductDetailsPage() throws Exception {
	SoftAssert softAssertion= new SoftAssert();
	//Library TodayDate = new Library();
	
	//Fetch the user name and password
	ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
	String userName = ExcelUtils.getCellData(2, 6);
	String password = ExcelUtils.getCellData(2, 7);
		
	//Login in to application
	init();
	LabCatalogRegularFunctions login = new LabCatalogRegularFunctions();
	login.UserLogin(userName,password);			
	
	//Navigate to lab catalog Page
	Reporter.log("Navigate to lab catalog list");
	getWebElement("SideBar_Inventory_EHS_Group").click();
	
	getWebElement("SubMenu_LabCatalog").click();
	impliciteWait(6);

getWebElement("Cardview_ProductNameLink").click();
Thread.sleep(5000);
//Click on the Request Icon in the details view view
driver.findElement(By.xpath("//a[@title='Request Item']")).click();
Thread.sleep(13000);
//explicitWaitForElement("RequestButton_Requestmodal");
while(getWebElement("RequestCountModal").isDisplayed() ) {
	driver.findElement(By.xpath("//button[contains(text(),'Request')]")).click();
	Thread.sleep(5000);
	impliciteWait(20);
}
	driver.navigate().refresh();
	
			//Logout from an application.
			LabCatalogRegularFunctions logout = new LabCatalogRegularFunctions();
			logout.UserLogout();
			driver.quit();
			softAssertion.assertAll();	
}
}