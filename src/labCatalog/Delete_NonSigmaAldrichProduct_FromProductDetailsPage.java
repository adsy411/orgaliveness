package labCatalog;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Utills;
import pageLibrary.Library;
public class Delete_NonSigmaAldrichProduct_FromProductDetailsPage extends TestBase {

@Test
public void Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabOwner_and_DeleteProduct() throws Exception {
	
	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedDeleteSuccessMessage = " Catalog deleted successfully!";
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
	
	//Generate a Random number to search in the Product Name
	Library TodayDate = new Library();
	String randomNumber = TodayDate.Date();
	String ProductName = "NON Sigma-Aldrich_"+randomNumber;
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
	/*Capturing the Final Product Count after adding New Product to Lab catalog from the Pagination area
	String finalcount = getWebElement("LabCatalog_ProductsCount").getText();
	String FinalNewCount = finalcount.split(" ")[0];
	int FinalIntCount = Integer.parseInt(FinalNewCount);
	System.out.println("FinalCount From Top:"+FinalIntCount);*/
	//Adding 1 to the Initial Count and Verifying if its equal to the Final Count and also verifying the Success Message 
	int EndCount =  IntCount+1;
	if(ExpectedSuccessMessage.equals(Successmessage)&&EndCount == newcoin)
	{
		System.out.println("PASS...................................");
		Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog Successfully as Lab Owner");
		Reporter.log("New Non Sigma-Aldrich Product Added to Catalog as Lab Owner Successfully");
	}
	else
	{
		System.out.println("FAIL....................");
		System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed");
		Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed");
	}
		Thread.sleep(5000);
		//Click on the Delete Icon in the Grid view
		//getWebElement("DeleteProdcut_CatalogPageGridView").click();
		Thread.sleep(5000);
		//Click on the OK button in Confirmation modal
		//getWebElement("DeleteProdcut_OKconfirmationbutton").click();
		//impliciteWait(10);
		//Capture the Success message for further comparison and validation
		//String DeleteSuccessmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
		//System.out.println("Delete Successmessage:"+DeleteSuccessmessage+".");
		//System.out.println("Expected Delete SuccessMessage:"+ExpectedDeleteSuccessMessage+".");
		
		//Click on the Request Icon in the Product details page
		getWebElement("DeleteProdcut_ProductsDetailsPage").click();
		Thread.sleep(5000);
		getWebElement("DeleteProdcut_OKconfirmationbutton").click();
		Thread.sleep(5000);
		//Capture the Success message for further comparison and validation
		String DeleteSuccessmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
		System.out.println("Requested Successmessage:"+DeleteSuccessmessage+".");
		System.out.println("Expected Requested SuccessMessage:"+ExpectedDeleteSuccessMessage+".");
		impliciteWait(10);
		
		driver.navigate().back();
		Thread.sleep(3000);
		//Get Final Product Count after adding New Product to Lab catalog
		String newcoDel = getWebElement("LabCatalog_ProductsCountPagination").getText();
		//System.out.println(newcoDel);
		String newcoDelete = newcoDel.split(" ")[0];
		int newcoDelete1 = Integer.parseInt(newcoDelete);
		System.out.println("Finalcount After deleting the Product :"+newcoDelete1);
		//Adding 1 to the Final Count and Verifying if its equal to the Initial Count and also verifying the Success Message 
		int EndCountDel =  newcoDelete1+1;
		System.out.println("Initial Count:"+newcoin);
		System.out.println("Final Count:"+newcoDelete1);
			if(ExpectedDeleteSuccessMessage.equals(DeleteSuccessmessage) && EndCountDel == newcoin)
			{
				System.out.println("PASS...................................");
				Reporter.log("Lab Catalog, Non Sigma-Aldrich Product "+ProductName+" Deleted from Lab Catalog Successfully as Lab Owner");
				Reporter.log("Lab Catalog, Non Sigma-Aldrich Product Deleted Successfully as Lab Owner");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Deleting Non Sigma-Aldrich product from Lab catalog as Lab Owner Failed");
				Reporter.log("Deleting Non Sigma-Aldrich product from Lab catalog as Lab Owner Failed");
			}
	//Logout
	Reporter.log("Lab Owner logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();
}

@Test
public void Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabManager_and_DeleteProduct() throws Exception {

	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedDeleteSuccessMessage = "Success! Catalog deleted successfully!";
	//Login to application as lab Owner
	init();
	Reporter.log("Login to Application as lab Owner");
	getWebElement("Enotebook.clicklogin.username").click();
	getWebElement("Enotebook.login.username").sendKeys("aqa1@mailinator.com");
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
	
	//Generate a Random number to search in the Product Name
	Library TodayDate = new Library();
	String randomNumber = TodayDate.Date();
	String ProductName = "NON Sigma-Aldrich_"+randomNumber;
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
		Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog as Lab manager Successfully");
		Reporter.log("New Non Sigma-Aldrich Product Added to Catalog as Lab manager Successfully");
	}
	else
	{
		System.out.println("FAIL....................");
		System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed");
		Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed");
	}
		Thread.sleep(5000);
		//Click on the Delete Icon in the Grid view
		getWebElement("DeleteProdcut_CatalogPageGridView").click();
		Thread.sleep(5000);
		//Click on the OK button in Confirmation modal
		getWebElement("DeleteProdcut_OKconfirmationbutton").click();
		impliciteWait(10);
		//Capture the Success message for further comparison and validation
		String DeleteSuccessmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
		System.out.println("Delete Successmessage:"+DeleteSuccessmessage+".");
		System.out.println("Expected Delete SuccessMessage:"+ExpectedDeleteSuccessMessage+".");
		//Get Final Product Count after adding New Product to Lab catalog
		String newcoDel = getWebElement("LabCatalog_ProductsCountPagination").getText();
		//System.out.println(newcoDel);
		String newcoDelete = newcoDel.split(" ")[0];
		int newcoDelete1 = Integer.parseInt(newcoDelete);
		System.out.println("Finalcount After deleting the Product :"+newcoDelete1);
		//Adding 1 to the Final Count and Verifying if its equal to the Initial Count and also verifying the Success Message 
			int EndCountDel =  newcoDelete1+1;
		System.out.println("Initial Count:"+newcoin);
		System.out.println("Final Count:"+newcoDelete1);
			if(ExpectedDeleteSuccessMessage.equals(DeleteSuccessmessage) && EndCountDel == newcoin)
			{
				System.out.println("PASS...................................");
				Reporter.log("Lab Catalog, Non Sigma-Aldrich Product "+ProductName+" Deleted from Lab Catalog as Lab manager Successfully");
				Reporter.log("Lab Catalog Non Sigma-Aldrich Product Deleted as Lab manager Successfully");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Deleting Non Sigma-Aldrich product from Lab catalog Failed as Lab manager");
				Reporter.log("Deleting Non Sigma-Aldrich product from Lab catalog Failed as Lab manager");
			}
	//Logout
	Reporter.log("Lab Manager logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();
}

@Test
public void Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabMember_and_DeleteProduct() throws Exception {

	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedDeleteSuccessMessage = "Success! Catalog deleted successfully!";
	//Login to application as lab Owner
	init();
	Reporter.log("Login to Application as lab Owner");
	getWebElement("Enotebook.clicklogin.username").click();
	getWebElement("Enotebook.login.username").sendKeys("aqa2@mailinator.com");
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
	
	//Generate a Random number to search in the Product Name
	Library TodayDate = new Library();
	String randomNumber = TodayDate.Date();
	String ProductName = "NON Sigma-Aldrich_"+randomNumber;
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
		Reporter.log("New Non Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog as Lab Member Successfully");
		Reporter.log("New Non Sigma-Aldrich Product Added to Catalog as Lab Member Successfully");
	}
	else
	{
		System.out.println("FAIL....................");
		System.out.println("Adding New Non Sigma-Aldrich Product to Catalog Failed as Lab Member");
		Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed as Lab Member");
	}
		Thread.sleep(5000);
		//Click on the Delete Icon in the Grid view
		getWebElement("DeleteProdcut_CatalogPageGridView").click();
		Thread.sleep(5000);
		//Click on the OK button in Confirmation modal
		getWebElement("DeleteProdcut_OKconfirmationbutton").click();
		impliciteWait(10);
		//Capture the Success message for further comparison and validation
		String DeleteSuccessmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
		System.out.println("Delete Successmessage:"+DeleteSuccessmessage+".");
		System.out.println("Expected Delete SuccessMessage:"+ExpectedDeleteSuccessMessage+".");
		//Get Final Product Count after adding New Product to Lab catalog
		String newcoDel = getWebElement("LabCatalog_ProductsCountPagination").getText();
		//System.out.println(newcoDel);
		String newcoDelete = newcoDel.split(" ")[0];
		int newcoDelete1 = Integer.parseInt(newcoDelete);
		System.out.println("Finalcount After deleting the Product :"+newcoDelete1);
		//Adding 1 to the Final Count and Verifying if its equal to the Initial Count and also verifying the Success Message 
			int EndCountDel =  newcoDelete1+1;
		System.out.println("Initial Count:"+newcoin);
		System.out.println("Final Count:"+newcoDelete1);
			if(ExpectedDeleteSuccessMessage.equals(DeleteSuccessmessage) && EndCountDel == newcoin)
			{
				System.out.println("PASS...................................");
				Reporter.log("Lab Catalog Non Sigma-Aldrich Product "+ProductName+" Deleted from Lab Catalog as Lab Member Successfully");
				Reporter.log("Lab Catalog, Non Sigma-Aldrich Product Deleted as Lab Member Successfully");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Deleting Non Lab catalog Product Failed as Lab Member");
				Reporter.log("Deleting Non Sigma-Aldrich product from Lab catalog Failed as Lab Member");
			}
	//Logout
	Reporter.log("Lab Member logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();	
	}
	
}