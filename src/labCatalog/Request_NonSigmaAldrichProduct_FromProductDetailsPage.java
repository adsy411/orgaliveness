package labCatalog;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Utills;
import pageLibrary.Library;
//import java.util.List<WebElement>;
public class Request_NonSigmaAldrichProduct_FromProductDetailsPage extends TestBase {

@Test
public void Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabOwner_and_RequestProduct_FromProductDetailsPage() throws Exception {

	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedRequestSuccessMessage = "Request added successfully!";
	//Login to application as lab Owner
	init();
	Reporter.log("Login to Application as lab Owner");
	getWebElement("Enotebook.clicklogin.username").click();
	getWebElement("Enotebook.login.username").sendKeys("aqauser@mailinator.com");//("avd@mailinator.com");
	getWebElement("Enotebook.login.password").sendKeys("admin123");
	Thread.sleep(2000);
	getWebElement("Enotebook.login.loginButton").click();
	
		//Navigate to Request Page to take the Initial Count
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_Request").click();
		impliciteWait(6);
		
		//Get Initial Product Count Before Requesting New Product From Product Details page
		java.util.List<WebElement> allLinksInForm = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedCountInitial = allLinksInForm.size();
		System.out.println("Request Initial Count:"+RequestedCountInitial);
		
		//Navigate to lab catalog list
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_LabCatalog").click();
		impliciteWait(6);
		
		//Get initial Product Count Before adding a new Non Sigma Aldrich Product to Lab Catalog
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
	
		//Generate a Random number to Add in the Product Name
		Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String ProductName = randomNumber;
		//Enter all the Product details in the Application
		getWebElement("AddNewProdcut_ProductName").sendKeys(ProductName);
		getWebElement("AddNewProdcut_CatalogNumber").sendKeys("CatalogNo"+randomNumber);
		getWebElement("AddNewProdcut_Quantity").sendKeys("50");
		getWebElement("AddNewProdcut_UoM").sendKeys("kg");
		getWebElement("AddNewProdcut_VendorDropdown").sendKeys("Add Vendor");
		getWebElement("AddNewProdcut_NewVendor").sendKeys("Vendor"+randomNumber);
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
		impliciteWait(10);
		Utills.captureScreenshot("AddNONSAProductConfirmation"+randomNumber);
		//Click on the OK button in Confirmation modal
		getWebElement("AddNewProdcut_OKconfirmationbutton").click();
		impliciteWait(10);
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
				Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog as Lab Owner Successfully");
				Reporter.log("New Non Sigma-Aldrich Product Added to Catalog as Lab Owner Successfully");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed as Lab Owner");
				Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed as Lab Owner");
			}
	
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab owner");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(ProductName);
		impliciteWait(40);
		Thread.sleep(5000);	
		
		//Navigate to Product details page by clicking on the Product Name
		driver.findElement(By.linkText(ProductName)).click();
		impliciteWait(40);
		Thread.sleep(5000);
		
				//Click on the Request Icon in the Product details page
				getWebElement("RequestProdcut_ProductsDetailsPage").click();
				Thread.sleep(1000);
				
				//Capture the Success message for further comparison and validation
				String RequestedSuccessmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
				System.out.println("Requested Successmessage:"+RequestedSuccessmessage+".");
				System.out.println("Expected Requested SuccessMessage:"+ExpectedRequestSuccessMessage+".");
				impliciteWait(10);
				driver.navigate().back();
				Thread.sleep(3000);
				
					//Navigate to Request Page to take the Initial Count
					Reporter.log("Navigate to lab catalog list");
					getWebElement("SideBar_Inventory_EHS_Group").click();
					impliciteWait(3);
					getWebElement("SubMenu_Request").click();
					impliciteWait(6);
					
					//Get Final Product Count after Requesting Product from Product details page 
					java.util.List<WebElement> allLinksInForm1 = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
					int RequestedCountFinal = allLinksInForm1.size();
		
		//Adding 1 to the Final Count and Verifying if its equal to the Initial Count and also verifying the Success Message 
		int EndCountRequest =  RequestedCountInitial+1;
		System.out.println("Request Initial Count:"+RequestedCountInitial);
		System.out.println("Request Final Count:"+RequestedCountFinal);
			if(ExpectedRequestSuccessMessage.equals(RequestedSuccessmessage) && EndCountRequest == RequestedCountFinal)
			{
				System.out.println("PASS...................................");
				Reporter.log("Lab Catalog Product "+ProductName+" is Requested from Product details page as Lab Owner Successfully");
				Reporter.log("Lab Catalog Product is Requested as Lab Owner Successfully from Product details page");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Requesting Non Lab catalog Product from Product details page Failed as Lab Owner");
				Reporter.log("Requesting Lab Catalog Product from Product details page Failed as Lab Owner");
			}
	//Logout
	Reporter.log("Lab Owner logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();	
	}

@Test
public void Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabManager_and_RequestProduct_FromProductDetailsPage() throws Exception {

	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedRequestSuccessMessage = "Request added successfully!";
	
	//Login to application as lab Owner
	init();
	Reporter.log("Login to Application as lab Owner");
	getWebElement("Enotebook.clicklogin.username").click();
	getWebElement("Enotebook.login.username").sendKeys("aqa1@mailinator.com");//("mcc@mailinator.com");
	getWebElement("Enotebook.login.password").sendKeys("admin123");
	Thread.sleep(2000);
	getWebElement("Enotebook.login.loginButton").click();
	
		//Navigate to Request Page to take the Initial Count
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_Request").click();
		impliciteWait(6);
		
		//Get Initial Product Count Before Requesting New Product From Lab catalog
		java.util.List<WebElement> allLinksInForm = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedCountInitial = allLinksInForm.size();
		System.out.println("Request Initial Count:"+RequestedCountInitial);
		
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
			
		//Generate a Random number to search in the Product Name
		Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String ProductName = randomNumber;
		//Enter all the Product details in the Application
		getWebElement("AddNewProdcut_ProductName").sendKeys(ProductName);
		getWebElement("AddNewProdcut_CatalogNumber").sendKeys("CatalogNo"+randomNumber);
		getWebElement("AddNewProdcut_Quantity").sendKeys("50");
		getWebElement("AddNewProdcut_UoM").sendKeys("kg");
		getWebElement("AddNewProdcut_VendorDropdown").sendKeys("Add Vendor");
		getWebElement("AddNewProdcut_NewVendor").sendKeys("Vendor"+randomNumber);
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
		impliciteWait(10);
		Utills.captureScreenshot("AddNONSAProductConfirmation"+randomNumber);
		//Click on the OK button in Confirmation modal
		getWebElement("AddNewProdcut_OKconfirmationbutton").click();
		impliciteWait(10);
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
				Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog as Lab Manager Successfully");
				Reporter.log("New Non Sigma-Aldrich Product Added to Catalog as Lab Manager Successfully");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed as Lab Manager");
				Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed as Lab Manager");
			}
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab owner");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(ProductName);
		impliciteWait(40);
		Thread.sleep(5000);	
		//Navigate to Product details page
		driver.findElement(By.linkText(ProductName)).click();
		impliciteWait(40);
		Thread.sleep(5000);
		//Click on the Request Icon in the Product details page
		getWebElement("RequestProdcut_ProductsDetailsPage").click();
		Thread.sleep(1000);
			
			//Capture the Success message for further comparison and validation
			String RequestedSuccessmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
			System.out.println("Requested Successmessage:"+RequestedSuccessmessage+".");
			System.out.println("Expected Requested SuccessMessage:"+ExpectedRequestSuccessMessage+".");
			impliciteWait(10);
			
			driver.navigate().back();
			Thread.sleep(3000);
		
			//Navigate to Request Page to take the Initial Count
			Reporter.log("Navigate to lab catalog list");
			getWebElement("SideBar_Inventory_EHS_Group").click();
			impliciteWait(3);
			getWebElement("SubMenu_Request").click();
			impliciteWait(6);
			
			//Get Final Product Count after Requesting Product from Product details page 
		java.util.List<WebElement> allLinksInForm1 = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedCountFinal = allLinksInForm1.size();
		
		//Adding 1 to the Final Count and Verifying if its equal to the Initial Count and also verifying the Success Message 
		int EndCountRequest =  RequestedCountInitial+1;
		System.out.println("Request Initial Count:"+RequestedCountInitial);
		System.out.println("Request Final Count:"+RequestedCountFinal);
			if(ExpectedRequestSuccessMessage.equals(RequestedSuccessmessage) && EndCountRequest == RequestedCountFinal)
			{
				System.out.println("PASS...................................");
				Reporter.log("Lab Catalog Product "+ProductName+" is Requested from Product details page as Lab Manager Successfully");
				Reporter.log("Lab Catalog Product is Requested as Lab Manager Successfully from Product details page");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Requesting Non Lab catalog Product Failed as Lab Manager from Product details page");
				Reporter.log("Requesting Lab Catalog Product Failed as Lab Manager from Product details page");
			}
	//Logout
	Reporter.log("Lab Manager logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();	
	}
@Test
public void Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabMember_and_RequestProduct_FromProductDetailsPage() throws Exception {

	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedRequestSuccessMessage = "Request added successfully!";
	//Login to application as lab Owner
	init();
	Reporter.log("Login to Application as lab Owner");
	getWebElement("Enotebook.clicklogin.username").click();
	getWebElement("Enotebook.login.username").sendKeys("aqa2@mailinator.com");//("aavi@mailinator.com");
	getWebElement("Enotebook.login.password").sendKeys("admin123");
	Thread.sleep(2000);
	getWebElement("Enotebook.login.loginButton").click();
	
	//Navigate to Request Page to take the Initial Count
	Reporter.log("Navigate to lab catalog list");
	getWebElement("SideBar_Inventory_EHS_Group").click();
	impliciteWait(3);
	getWebElement("SubMenu_Request").click();
	impliciteWait(6);
	
	//Get Initial Product Count Before Requesting New Product From Lab catalog
	java.util.List<WebElement> allLinksInForm = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
	int RequestedCountInitial = allLinksInForm.size();
	System.out.println("Request Initial Count:"+RequestedCountInitial);
	
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
	
	//Generate a Random number to search in the Product Name
	Library TodayDate = new Library();
	String randomNumber = TodayDate.Date();
	String ProductName = randomNumber;
	//Enter all the Product details in the Application
	getWebElement("AddNewProdcut_ProductName").sendKeys(ProductName);
	getWebElement("AddNewProdcut_CatalogNumber").sendKeys("CatalogNo"+randomNumber);
	getWebElement("AddNewProdcut_Quantity").sendKeys("50");
	getWebElement("AddNewProdcut_UoM").sendKeys("kg");
	getWebElement("AddNewProdcut_VendorDropdown").sendKeys("Add Vendor");
	getWebElement("AddNewProdcut_NewVendor").sendKeys("Vendor"+randomNumber);
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
	impliciteWait(10);
	Utills.captureScreenshot("AddNONSAProductConfirmation"+randomNumber);
	//Click on the OK button in Confirmation modal
	getWebElement("AddNewProdcut_OKconfirmationbutton").click();
	impliciteWait(10);
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
		Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog as Lab Member Successfully");
		Reporter.log("New Non Sigma-Aldrich Product Added to Catalog as Lab Member Successfully");
	}
	else
	{
		System.out.println("FAIL....................");
		System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed as Lab Member");
		Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed as Lab Member");
	}
	//Search product in lab catalog
			Reporter.log("Select the lab catalog product created by Lab owner");
			getWebElement("LabCatalog_SearchBox").click();
			impliciteWait(5);
			getWebElement("LabCatalog_SearchBox").sendKeys(ProductName);
			impliciteWait(40);
			Thread.sleep(5000);	
			//Navigate to edit lab catalog page
			driver.findElement(By.linkText(ProductName)).click();
			impliciteWait(40);
			Thread.sleep(5000);
			//Click on the Request Icon in the Product details page
			getWebElement("RequestProdcut_ProductsDetailsPage").click();
			Thread.sleep(1000);
			//impliciteWait(1);
			//Capture the Success message for further comparison and validation
			String RequestedSuccessmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
			System.out.println("Requested Successmessage:"+RequestedSuccessmessage+".");
			System.out.println("Expected Requested SuccessMessage:"+ExpectedRequestSuccessMessage+".");
			impliciteWait(10);
			
			driver.navigate().back();
			Thread.sleep(3000);
		
			//Navigate to Request Page to take the Initial Count
			Reporter.log("Navigate to lab catalog list");
			getWebElement("SideBar_Inventory_EHS_Group").click();
			impliciteWait(3);
			getWebElement("SubMenu_Request").click();
			impliciteWait(6);
			
		//Get Final Product Count after Requesting Product from Product details page 
		java.util.List<WebElement> allLinksInForm1 = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedCountFinal = allLinksInForm1.size();
		
		//Adding 1 to the Final Count and Verifying if its equal to the Initial Count and also verifying the Success Message 
		int EndCountRequest =  RequestedCountInitial+1;
		System.out.println("Request Initial Count:"+RequestedCountInitial);
		System.out.println("Request Final Count:"+RequestedCountFinal);
			if(ExpectedRequestSuccessMessage.equals(RequestedSuccessmessage) && EndCountRequest == RequestedCountFinal)
			{
				System.out.println("PASS...................................");
				Reporter.log("Lab Catalog Product "+ProductName+" is Requested from Product details page as Lab Member Successfully");
				Reporter.log("Lab Catalog Product is Requested as Lab Member Successfully from Product details page");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Requesting Non Lab catalog Product Failed as Lab Member from Product details page");
				Reporter.log("Requesting Lab Catalog Product Failed as Lab Member from Product details page");
			}
	//Logout
	Reporter.log("Lab Member logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();	
	}
}