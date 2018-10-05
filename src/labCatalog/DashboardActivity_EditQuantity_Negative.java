package labCatalog;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Utills;
import pageLibrary.Library;
//import java.util.List<WebElement>;
public class DashboardActivity_EditQuantity_Negative extends TestBase {

@Test
public void VerifyDashboardActivityfor_EditQuantity_Negative() throws Exception {

	//Generate a Random number to search in the Product Name
			Library TodayDate = new Library();
			String randomNumber = TodayDate.Date();
			String ProductName = randomNumber;
			
			//LOGIN TEST DATA
			String ExpectedSuccessMessage = "Success! Catalog added successfully!";
			String ProdQuantity = "50";
			String UpdatedNewProdQuantity = "25.0";
			String UoM = "kg";
			String Type = "Chemical";
			String CAS = randomNumber+"97";
			String URL = "www.nonsa.com";
			String Brand = "MERCK";
			String BoilingPoint = "500";
			String FlashingPoint = "50";
			String MeltingPoint = "1000";
			String MinimumCount = "1";
			String MaximumCount = "2";
			String Description = "Non Sigma Aldrich Product";
			
			//Login to application as lab Owner
			init();
			Reporter.log("Login to Application as lab Owner");
			getWebElement("Enotebook.clicklogin.username").click();
			getWebElement("Enotebook.login.username").sendKeys("aqauser@mailinator.com");
			getWebElement("Enotebook.login.password").sendKeys("admin123");
			Thread.sleep(2000);
			getWebElement("Enotebook.login.loginButton").click();
			
			//Navigate to lab catalog list
			Reporter.log("Navigate to lab catalog list");
			getWebElement("SideBar_Inventory_EHS_Group").click();
			impliciteWait(3);
			getWebElement("SubMenu_LabCatalog").click();
			impliciteWait(6);
			
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
			
			//Enter all the Product details in the Application
			getWebElement("AddNewProdcut_ProductName").sendKeys(ProductName);
			getWebElement("AddNewProdcut_CatalogNumber").sendKeys("CatalogNo"+randomNumber);
			getWebElement("AddNewProdcut_Quantity").sendKeys(ProdQuantity);
			getWebElement("AddNewProdcut_UoM").sendKeys(UoM);
			getWebElement("AddNewProdcut_VendorDropdown").sendKeys("Add Vendor");
			getWebElement("AddNewProdcut_NewVendor").sendKeys("Vendor"+randomNumber);
			getWebElement("AddNewProdcut_Type").sendKeys(Type);
			getWebElement("AddNewProdcut_NextStep").click();
			getWebElement("AddNewProdcut_CAS_Number").sendKeys(CAS);
			getWebElement("AddNewProdcut_URL").sendKeys(URL);
			getWebElement("AddNewProdcut_FlashingPoint").sendKeys(FlashingPoint);
			getWebElement("AddNewProdcut_MinimumCount").sendKeys(MinimumCount);
			getWebElement("AddNewProdcut_Brand").sendKeys(Brand);
			getWebElement("AddNewProdcut_BoilingPoint").sendKeys(BoilingPoint);
			getWebElement("AddNewProdcut_MeltingPoint").sendKeys(MeltingPoint);
			getWebElement("AddNewProdcut_MinimumQuantity").sendKeys(MaximumCount);
			getWebElement("AddNewProdcut_Description").sendKeys(Description);
			getWebElement("AddNewProdcut_AddtoCatalog").click();
			impliciteWait(10);
			Utills.captureScreenshot("AddNONSAProductConfirmation"+randomNumber);
			//Click on the OK button in Confirmation modal
			getWebElement("AddNewProdcut_OKconfirmationbutton").click();
			impliciteWait(40);
			System.out.println("ExpectedSuccessMessage"+ExpectedSuccessMessage);
			//Capture the Success message for further comparision and validation
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
			impliciteWait(40);
			
			//Navigate to edit lab catalog page
			driver.findElement(By.linkText(ProductName)).click();
			impliciteWait(40);
			impliciteWait(5);
			
			getWebElement("EditCatalogProduct_Quantity").clear();
			getWebElement("EditCatalogProduct_Quantity").sendKeys(UpdatedNewProdQuantity);
			
			impliciteWait(5);
			getWebElement("EditCatalogProduct_UpdateProductDetailsButton").click();
			Utills.captureScreenshot("EditAllFieldsNONSAProductConfirmation"+randomNumber);
			impliciteWait(40);
			
			//Navigate to Dashboard page
			//Logout
			//Reporter.log("Lab Owner logout of the Application and Browser is Closed");
			Thread.sleep(5000);
			LoginPage login = new LoginPage();
			login.Logout();	
			//Login to application as lab Owner
			init();
			//Reporter.log("Login to Application as lab Owner");
			getWebElement("Enotebook.clicklogin.username").click();
			getWebElement("Enotebook.login.username").sendKeys("aqauser@mailinator.com");
			getWebElement("Enotebook.login.password").sendKeys("admin123");
			Thread.sleep(2000);
			getWebElement("Enotebook.login.loginButton").click();
			
			//getWebElement("SubMenu_DashboardGroup").click();
			impliciteWait(40);
			java.util.List<WebElement> allLinksInForm1 = driver.findElements(By.xpath("//p[@class='activity-owner']"));
			String DashboardActivity = allLinksInForm1.get(0).getText();
			System.out.println("RequestedCountFinal"+DashboardActivity);
			
			//Verify the Dashboard Activity for Increase in Quantity
			String Dprod=DashboardActivity.split("of")[1].trim();
			String DprodNew=Dprod.split("\n")[0].trim();
			Assert.assertEquals(DprodNew,ProductName,"Dashboard Activity successful");
			System.out.println(DashboardActivity);
			Utills.captureScreenshot("EditAllFieldsNONSAProductConfirmation"+randomNumber);
	
			
	//Logout
	Reporter.log("Lab Owner logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login2 = new LoginPage();
	login2.Logout();	
	}
}