package labCatalog;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Utills;

public class Enable_Editing_Of_The_Lab_Catalog_By_The_Lab_Member_Action extends TestBase {
	
	@Test
	public void Edit_Lab_Catalog_Product_Created_By_Lab_Owner() throws Exception {
	
		//LOGIN TEST DATA
		String ExpectedSuccessMessage = "Success! Catalog added successfully!";
		//String productName = "Test_Material_1";
		
		//Login to application as lab member
		init();
		Reporter.log("Login to Application as lab member");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("labmember@20mm.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab catalog list
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_LabCatalog").click();
				
		//Get initial Product Count
		String count = getWebElement("LabCatalog_ProductsCount").getText();
		String NewCount = count.split(" ")[0];
		int IntCount = Integer.parseInt(NewCount);
		//Reporter.log("Lab Catalog initial Product count Fetched Successfully");
		System.out.println("InitialCount:"+IntCount);
		
		//Click on Add New Product Button and verify if SDS section is displayed on screen
		getWebElement("Add_New_Product").click();
		impliciteWait(5);
		if(getWebElement("AddNewProdcut_SDS_Upload_Area").isDisplayed())
		{
			System.out.println("PASS: Add New Product Area is Displayed and the SDS file attachment area is also Displayed by default");
			Reporter.log("Add New Product Area is Displayed and the SDS file attachment area is also Displayed by default");
		}
		
		//Generate a Random number to search in the Product Name
		Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String ProductName = randomNumber;
		//Enter all the Product details in the Application
		getWebElement("AddNewProdcut_ProductName").sendKeys(ProductName);
		getWebElement("AddNewProdcut_CatalogNumber").sendKeys("CatalogNo"+randomNumber);
		getWebElement("AddNewProdcut_Quantity").sendKeys("50");
		Thread.sleep(4000);
		Library SelectList = new Library();
		Thread.sleep(2000);
		if(SelectList.VerifySelectList("AddNewProdcut_UoM","kg") == true)
			SelectList.SelectByValue("AddNewProdcut_UoM","kg");
		else
			Assert.fail("The UOM - kg does not exist");
		Thread.sleep(1000);
		
		//Check whether the vendor exists or not.
		if(SelectList.VerifySelectList("AddNewProdcut_VendorDropdown","Non SA Vendor") == true)
		{
			SelectList.SelectByVisibleText("AddNewProdcut_VendorDropdown","Non SA Vendor");
			Thread.sleep(1000);
		}
		
		//Creates a new vendor
		else
		{
			SelectList.SelectByVisibleText("AddNewProdcut_VendorDropdown","Add Vendor");
			Thread.sleep(1000);
			getWebElement("AddNewProdcut_VendorDropdown").sendKeys("Non SA Vendor");
			Thread.sleep(1000);
		}
		getWebElement("AddNewProdcut_Type").sendKeys("Chemical");
		getWebElement("AddNewProdcut_NextStep").click();
		getWebElement("AddNewProdcut_CAS_Number").sendKeys(randomNumber+"97");
		getWebElement("AddNewProdcut_URL").sendKeys("www.nonsa.com");
		getWebElement("AddNewProdcut_FlashingPoint").sendKeys("32");
		getWebElement("AddNewProdcut_MinimumCount").sendKeys("1");
		getWebElement("AddNewProdcut_Brand").sendKeys("MERCK");
		getWebElement("AddNewProdcut_BoilingPoint").sendKeys("900");
		getWebElement("AddNewProdcut_MeltingPoint").sendKeys("2000");
		getWebElement("AddNewProdcut_MinimumQuantity").sendKeys("2");
		getWebElement("AddNewProdcut_Description").sendKeys("Non Sigma Aldrich Product");
		getWebElement("AddNewProdcut_AddtoCatalog").click();
		impliciteWait(30);
		Utills.captureScreenshot("AddNONSAProductConfirmation"+randomNumber);
		//Click on the OK button in Confirmation modal
		getWebElement("AddNewProdcut_OKconfirmationbutton").click();
		impliciteWait(30);
		System.out.println("ExpectedSuccessMessage"+ExpectedSuccessMessage);
		//Capture the Success message for further comparison and validation
		String Successmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
		Utills.captureScreenshot("AddNONSAProduct"+randomNumber);
		System.out.println("Successmessage"+Successmessage);
		
		//Get Final Product Count after adding New Product to Lab catalog
		String newco = getWebElement("LabCatalog_ProductsCountPagination").getText();
		//System.out.println(newco);
		String newco1 = newco.split(" ")[0];
		int newcoin = Integer.parseInt(newco1);
		System.out.println("Finalcount from Pagination:"+newcoin);
		
		//Adding 1 to the Initial Count and Verifying if its equal to the Final Count and also verifying the Success Message 
		int EndCount =  IntCount+1;
		if(ExpectedSuccessMessage.equals(Successmessage)&&EndCount == newcoin)
		{
			System.out.println("PASS...................................");
			Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog Successfully");
			Reporter.log("New Non Sigma-Aldrich Product Added to Catalog Successfully");
		}
		else
		{
			System.out.println("FAIL....................");
			System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed");
			Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed");
		}
		
		
		
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab owner");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(ProductName);
		impliciteWait(5);
		
		//Navigate to edit lab catalog page
		driver.findElement(By.linkText(ProductName)).click();
		impliciteWait(40);
		
		//Update lab catalog product
		Reporter.log("Edit the lab catalog product and save");
		
		getWebElement("EditCatalogProduct_Quantity").clear();
		impliciteWait(2);
		getWebElement("EditCatalogProduct_Quantity").sendKeys("100");
		getWebElement("EditCatalogProduct_UpdateProductDetailsButton").click();
		impliciteWait(10);
		
		//Validate success message
		String actualSuccessMessage = getWebElement("EditLabCatalogSuccessMsg").getText();
		Assert.assertEquals(actualSuccessMessage, "Success! Catalog updated successfully!","Lab member failed to update lab catalog created by lab owner!");
		
		//Logout
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
	
	@Test
	public void Edit_Lab_Catalog_Product_Created_By_Lab_Member() throws Exception {
	
		//LOGIN TEST DATA
		String ExpectedSuccessMessage = "Success! Catalog added successfully!";
		//String productName = "Test_Material_2";
		
		//Login to application as lab member
		init();
		Reporter.log("Login to Application as lab member");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("labmember@20mm.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab catalog list
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_LabCatalog").click();
				
		//Get initial Product Count
		String count = getWebElement("LabCatalog_ProductsCount").getText();
		String NewCount = count.split(" ")[0];
		int IntCount = Integer.parseInt(NewCount);
		//Reporter.log("Lab Catalog initial Product count Fetched Successfully");
		System.out.println("InitialCount:"+IntCount);
		
		//Click on Add New Product Button and verify if SDS section is displayed on screen
		getWebElement("Add_New_Product").click();
		impliciteWait(5);
		if(getWebElement("AddNewProdcut_SDS_Upload_Area").isDisplayed())
		{
			System.out.println("PASS: Add New Product Area is Displayed and the SDS file attachment area is also Displayed by default");
			Reporter.log("Add New Product Area is Displayed and the SDS file attachment area is also Displayed by default");
		}
		
		//Generate a Random number to search in the Product Name
		Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String ProductName = randomNumber;
		//Enter all the Product details in the Application
		getWebElement("AddNewProdcut_ProductName").sendKeys(ProductName);
		getWebElement("AddNewProdcut_CatalogNumber").sendKeys("CatalogNo"+randomNumber);
		getWebElement("AddNewProdcut_Quantity").sendKeys("50");
		Thread.sleep(4000);
		Library SelectList = new Library();
		Thread.sleep(2000);
		if(SelectList.VerifySelectList("AddNewProdcut_UoM","kg") == true)
			SelectList.SelectByValue("AddNewProdcut_UoM","kg");
		else
			Assert.fail("The UOM - kg does not exist");
		Thread.sleep(1000);
		
		//Check whether the vendor exists or not.
		if(SelectList.VerifySelectList("AddNewProdcut_VendorDropdown","Non SA Vendor") == true)
		{
			SelectList.SelectByVisibleText("AddNewProdcut_VendorDropdown","Non SA Vendor");
			Thread.sleep(1000);
		}
		
		//Creates a new vendor
		else
		{
			SelectList.SelectByVisibleText("AddNewProdcut_VendorDropdown","Add Vendor");
			Thread.sleep(1000);
			getWebElement("AddNewProdcut_VendorDropdown").sendKeys("Non SA Vendor");
			Thread.sleep(1000);
		}
		getWebElement("AddNewProdcut_Type").sendKeys("Chemical");
		getWebElement("AddNewProdcut_NextStep").click();
		getWebElement("AddNewProdcut_CAS_Number").sendKeys(randomNumber+"97");
		getWebElement("AddNewProdcut_URL").sendKeys("www.nonsa.com");
		getWebElement("AddNewProdcut_FlashingPoint").sendKeys("32");
		getWebElement("AddNewProdcut_MinimumCount").sendKeys("1");
		getWebElement("AddNewProdcut_Brand").sendKeys("MERCK");
		getWebElement("AddNewProdcut_BoilingPoint").sendKeys("900");
		getWebElement("AddNewProdcut_MeltingPoint").sendKeys("2000");
		getWebElement("AddNewProdcut_MinimumQuantity").sendKeys("2");
		getWebElement("AddNewProdcut_Description").sendKeys("Non Sigma Aldrich Product");
		getWebElement("AddNewProdcut_AddtoCatalog").click();
		impliciteWait(30);
		Utills.captureScreenshot("AddNONSAProductConfirmation"+randomNumber);
		//Click on the OK button in Confirmation modal
		getWebElement("AddNewProdcut_OKconfirmationbutton").click();
		impliciteWait(30);
		System.out.println("ExpectedSuccessMessage"+ExpectedSuccessMessage);
		//Capture the Success message for further comparison and validation
		String Successmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
		Utills.captureScreenshot("AddNONSAProduct"+randomNumber);
		System.out.println("Successmessage"+Successmessage);
		
		//Get Final Product Count after adding New Product to Lab catalog
		String newco = getWebElement("LabCatalog_ProductsCountPagination").getText();
		//System.out.println(newco);
		String newco1 = newco.split(" ")[0];
		int newcoin = Integer.parseInt(newco1);
		System.out.println("Finalcount from Pagination:"+newcoin);
		
		//Adding 1 to the Initial Count and Verifying if its equal to the Final Count and also verifying the Success Message 
		int EndCount =  IntCount+1;
		if(ExpectedSuccessMessage.equals(Successmessage)&&EndCount == newcoin)
		{
			System.out.println("PASS...................................");
			Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog Successfully");
			Reporter.log("New Non Sigma-Aldrich Product Added to Catalog Successfully");
		}
		else
		{
			System.out.println("FAIL....................");
			System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed");
			Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed");
		}
		
		
		
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab owner");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(ProductName);
		impliciteWait(5);
		
		//Navigate to edit lab catalog page
		driver.findElement(By.linkText(ProductName)).click();
		impliciteWait(40);
		
		//Update lab catalog product
		Reporter.log("Edit the lab catalog product and save");
		getWebElement("EditCatalogProduct_Quantity").clear();
		impliciteWait(2);
		getWebElement("EditCatalogProduct_Quantity").sendKeys("100");
		getWebElement("EditCatalogProduct_UpdateProductDetailsButton").click();
		impliciteWait(10);
		
		//Validate success message
		String actualSuccessMessage = getWebElement("EditLabCatalogSuccessMsg").getText();
		Assert.assertEquals(actualSuccessMessage, "Success! Catalog updated successfully!","Lab member failed to update lab catalog created by lab owner!");
		
		//Logout
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
	
	@Test
	public void Edit_Lab_Catalog_Product_Created_By_Lab_Manager() throws Exception {
	
		//LOGIN TEST DATA
		String ExpectedSuccessMessage = "Success! Catalog added successfully!";
		
		//Login to application as lab member
		init();
		Reporter.log("Login to Application as lab member");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("labmember@20mm.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab catalog list
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_LabCatalog").click();
				
		//Get initial Product Count
		String count = getWebElement("LabCatalog_ProductsCount").getText();
		String NewCount = count.split(" ")[0];
		int IntCount = Integer.parseInt(NewCount);
		//Reporter.log("Lab Catalog initial Product count Fetched Successfully");
		System.out.println("InitialCount:"+IntCount);
		
		//Click on Add New Product Button and verify if SDS section is displayed on screen
		getWebElement("Add_New_Product").click();
		impliciteWait(5);
		if(getWebElement("AddNewProdcut_SDS_Upload_Area").isDisplayed())
		{
			System.out.println("PASS: Add New Product Area is Displayed and the SDS file attachment area is also Displayed by default");
			Reporter.log("Add New Product Area is Displayed and the SDS file attachment area is also Displayed by default");
		}
		
		//Generate a Random number to search in the Product Name
		Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String ProductName = randomNumber;
		//Enter all the Product details in the Application
		getWebElement("AddNewProdcut_ProductName").sendKeys(ProductName);
		getWebElement("AddNewProdcut_CatalogNumber").sendKeys("CatalogNo"+randomNumber);
		getWebElement("AddNewProdcut_Quantity").sendKeys("50");
		Thread.sleep(4000);
		Library SelectList = new Library();
		Thread.sleep(2000);
		if(SelectList.VerifySelectList("AddNewProdcut_UoM","kg") == true)
			SelectList.SelectByValue("AddNewProdcut_UoM","kg");
		else
			Assert.fail("The UOM - kg does not exist");
		Thread.sleep(1000);
		
		//Check whether the vendor exists or not.
		if(SelectList.VerifySelectList("AddNewProdcut_VendorDropdown","Non SA Vendor") == true)
		{
			SelectList.SelectByVisibleText("AddNewProdcut_VendorDropdown","Non SA Vendor");
			Thread.sleep(1000);
		}
		
		//Creates a new vendor
		else
		{
			SelectList.SelectByVisibleText("AddNewProdcut_VendorDropdown","Add Vendor");
			Thread.sleep(1000);
			getWebElement("AddNewProdcut_VendorDropdown").sendKeys("Non SA Vendor");
			Thread.sleep(1000);
		}
		getWebElement("AddNewProdcut_Type").sendKeys("Chemical");
		getWebElement("AddNewProdcut_NextStep").click();
		getWebElement("AddNewProdcut_CAS_Number").sendKeys(randomNumber+"97");
		getWebElement("AddNewProdcut_URL").sendKeys("www.nonsa.com");
		getWebElement("AddNewProdcut_FlashingPoint").sendKeys("32");
		getWebElement("AddNewProdcut_MinimumCount").sendKeys("1");
		getWebElement("AddNewProdcut_Brand").sendKeys("MERCK");
		getWebElement("AddNewProdcut_BoilingPoint").sendKeys("900");
		getWebElement("AddNewProdcut_MeltingPoint").sendKeys("2000");
		getWebElement("AddNewProdcut_MinimumQuantity").sendKeys("2");
		getWebElement("AddNewProdcut_Description").sendKeys("Non Sigma Aldrich Product");
		getWebElement("AddNewProdcut_AddtoCatalog").click();
		impliciteWait(30);
		Utills.captureScreenshot("AddNONSAProductConfirmation"+randomNumber);
		//Click on the OK button in Confirmation modal
		getWebElement("AddNewProdcut_OKconfirmationbutton").click();
		impliciteWait(30);
		System.out.println("ExpectedSuccessMessage"+ExpectedSuccessMessage);
		//Capture the Success message for further comparison and validation
		String Successmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
		Utills.captureScreenshot("AddNONSAProduct"+randomNumber);
		System.out.println("Successmessage"+Successmessage);
		
		//Get Final Product Count after adding New Product to Lab catalog
		String newco = getWebElement("LabCatalog_ProductsCountPagination").getText();
		//System.out.println(newco);
		String newco1 = newco.split(" ")[0];
		int newcoin = Integer.parseInt(newco1);
		System.out.println("Finalcount from Pagination:"+newcoin);
		
		//Adding 1 to the Initial Count and Verifying if its equal to the Final Count and also verifying the Success Message 
		int EndCount =  IntCount+1;
		if(ExpectedSuccessMessage.equals(Successmessage)&&EndCount == newcoin)
		{
			System.out.println("PASS...................................");
			Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog Successfully");
			Reporter.log("New Non Sigma-Aldrich Product Added to Catalog Successfully");
		}
		else
		{
			System.out.println("FAIL....................");
			System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed");
			Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed");
		}
		
		
		
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab owner");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(ProductName);
		impliciteWait(5);
		
		//Navigate to edit lab catalog page
		driver.findElement(By.linkText(ProductName)).click();
		impliciteWait(40);
		
		//Update lab catalog product
		Reporter.log("Edit the lab catalog product and save");
		getWebElement("EditCatalogProduct_Quantity").clear();
		impliciteWait(2);
		getWebElement("EditCatalogProduct_Quantity").sendKeys("100");
		getWebElement("EditCatalogProduct_UpdateProductDetailsButton").click();
		impliciteWait(10);
		
		//Validate success message
		String actualSuccessMessage = getWebElement("EditLabCatalogSuccessMsg").getText();
		Assert.assertEquals(actualSuccessMessage, "Success! Catalog updated successfully!","Lab member failed to update lab catalog created by lab owner!");
		
		//Logout
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
}