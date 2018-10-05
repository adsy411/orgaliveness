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
public class DeleteSigmaAldrichProductFromLabCatalog_Old extends TestBase {

@Test
public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner_and_DeleteProduct() throws Exception {
	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedDeleteSuccessMessage = "Success! Catalog deleted successfully!";
	//Login to application as lab Owner
	init();
	Reporter.log("Login to Application as lab Owner");
	getWebElement("Enotebook.clicklogin.username").click();
	getWebElement("Enotebook.login.username").sendKeys("aqauser@mailinator.com");//("avd@mailinator.com");
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
	String NewCount1 = randomNumber.split(" ")[0];
	System.out.println("Random Number:"+randomNumber);
	System.out.println("NewCount4chars Number:"+NewCount1);
	String NewCount4chars = NewCount1.substring(NewCount1.length()-5);
	System.out.println("NewCount4chars4:"+NewCount4chars);
	for (int i=1;i<NewCount4chars.length(); i = i+1)
	{
		char singlechar = NewCount4chars.charAt(i);
		String stringValueOf = String.valueOf(singlechar);
		System.out.println("singlechar:"+NewCount4chars.charAt(i));
		getWebElement("AddNewProdcut_ProductName").sendKeys(stringValueOf);
		impliciteWait(20);
	}
	
	Thread.sleep(15000);
	impliciteWait(20);
	
	if(driver.findElements(By.xpath("//tr[@class='ui-autocomplete-group ui-widget-header']")).size()!= 0){
		System.out.println("Element is Present");
		//getWebElement("AddNewProdcut_ProductName").click();
		Thread.sleep(15000);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ENTER);
	}
	else{
		//getWebElement("AddNewProdcut_ProductName").click();
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.BACK_SPACE);
		Thread.sleep(15000);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ENTER);
		//getWebElement("AddNewSAProdcut_AutoComplete").sendKeys(Keys.BACK_SPACE);
		System.out.println("Element is Populated");
	}
	Thread.sleep(5000);
	impliciteWait(20);
	String ProductFullName = getWebElement("AddNewProdcut_ProductName").getAttribute("value");
	System.out.println("ProductFullName : "+ProductFullName);
	getWebElement("AddNewProdcut_AddtoCatalog").click();
	impliciteWait(10);
	Utills.captureScreenshot("AddSigma-AldrichProductConfirmation"+randomNumber);
	//Click on the OK button in Confirmation modal
	getWebElement("AddNewProdcut_OKconfirmationbutton").click();
	impliciteWait(10);
	System.out.println("ExpectedSuccessMessage"+ExpectedSuccessMessage);
	//Capture the Success message for further comparison and validation
	String Successmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
	Utills.captureScreenshot("AddSigma-AldrichProduct"+randomNumber);
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
		Reporter.log("New Sigma-Aldrich Product "+ProductFullName+" Added to Lab Catalog Successfully");
		Reporter.log("New Sigma-Aldrich Product Added to Catalog Successfully by Lab Owner");
	}
	else
	{
		System.out.println("FAIL....................");
		System.out.println("Adding New Sigma-Aldrich Product to Catalog Failed");
		Reporter.log("Adding New Sigma-Aldrich Product to Catalog Failed");
		Reporter.log("Adding New Sigma-Aldrich Product to Catalog as Lab Owner Failed");
	}
	//Delete Product Script
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
			Reporter.log("Lab Catalog, Sigma-Aldrich Product "+ProductFullName+" Deleted from Lab Catalog Successfully as Lab Owner");
			Reporter.log("Lab Catalog, Sigma-Aldrich Product Deleted Successfully as Lab Owner");
		}
		else
		{
			System.out.println("FAIL....................");
			System.out.println("Deleting Sigma-Aldrich Product from Lab Catalog as Lab Owner Failed");
			Reporter.log("Deleting Sigma-Aldrich Product from Lab catalog as Lab Owner Failed");
		}
	//Logout
	Reporter.log("Lab Owner logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();
			
}

@Test
public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager_and_DeleteProduct() throws Exception {

	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedDeleteSuccessMessage = "Success! Catalog deleted successfully!";
	//Login to application as lab Owner
	init();
	Reporter.log("Login to Application as lab Owner");
	getWebElement("Enotebook.clicklogin.username").click();
	getWebElement("Enotebook.login.username").sendKeys("aqa1@mailinator.com");//("avd@mailinator.com");
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
	String NewCount1 = randomNumber.split(" ")[0];
	System.out.println("Random Number:"+randomNumber);
	System.out.println("NewCount4chars Number:"+NewCount1);
	String NewCount4chars = NewCount1.substring(NewCount1.length()-5);
	System.out.println("NewCount4chars4:"+NewCount4chars);
	for (int i=1;i<NewCount4chars.length(); i = i+1)
	{
		char singlechar = NewCount4chars.charAt(i);
		String stringValueOf = String.valueOf(singlechar);
		System.out.println("singlechar:"+NewCount4chars.charAt(i));
		getWebElement("AddNewProdcut_ProductName").sendKeys(stringValueOf);
		impliciteWait(20);
	}
	
	Thread.sleep(15000);
	impliciteWait(20);
	
	if(driver.findElements(By.xpath("//tr[@class='ui-autocomplete-group ui-widget-header']")).size()!= 0){
		System.out.println("Element is Present");
		//getWebElement("AddNewProdcut_ProductName").click();
		Thread.sleep(15000);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ENTER);
	}
	else{
		//getWebElement("AddNewProdcut_ProductName").click();
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.BACK_SPACE);
		Thread.sleep(15000);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ENTER);
		//getWebElement("AddNewSAProdcut_AutoComplete").sendKeys(Keys.BACK_SPACE);
		System.out.println("Element is Populated");
	}
	Thread.sleep(5000);
	impliciteWait(20);
	String ProductFullName = getWebElement("AddNewProdcut_ProductName").getAttribute("value");
	System.out.println("ProductFullName : "+ProductFullName);
	getWebElement("AddNewProdcut_AddtoCatalog").click();
	impliciteWait(10);
	Utills.captureScreenshot("AddSigma-AldrichProductConfirmation"+randomNumber);
	//Click on the OK button in Confirmation modal
	getWebElement("AddNewProdcut_OKconfirmationbutton").click();
	impliciteWait(10);
	System.out.println("ExpectedSuccessMessage"+ExpectedSuccessMessage);
	//Capture the Success message for further comparison and validation
	String Successmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
	Utills.captureScreenshot("AddSigma-AldrichProduct"+randomNumber);
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
		Reporter.log("New Sigma-Aldrich Product "+ProductFullName+" Added to Lab Catalog Successfully");
		Reporter.log("New Sigma-Aldrich Product Added to Catalog Successfully by Lab Manager");
	}
	else
	{
		System.out.println("FAIL....................");
		System.out.println("Adding New Sigma-Aldrich Product to Catalog Failed");
		Reporter.log("Adding New Sigma-Aldrich Product to Catalog Failed");
		Reporter.log("Adding New Sigma-Aldrich Product to Catalog as Lab Manager Failed");
	}
	//Delete Product Script
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
			Reporter.log("Lab Catalog, Sigma-Aldrich Product "+ProductFullName+" Deleted from Lab Catalog Successfully as Lab Manager");
			Reporter.log("Lab Catalog, Sigma-Aldrich Product Deleted Successfully as Lab Manager");
		}
		else
		{
			System.out.println("FAIL....................");
			System.out.println("Deleting Sigma-Aldrich Product from Lab Catalog as Lab Manager Failed");
			Reporter.log("Deleting Sigma-Aldrich Product from Lab catalog as Lab Manager Failed");
		}
	//Logout
	Reporter.log("Lab Manager logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();
}

@Test
public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember_and_DeleteProduct() throws Exception {

	//LOGIN TEST DATA
	String ExpectedSuccessMessage = "Success! Catalog added successfully!";
	String ExpectedDeleteSuccessMessage = "Success! Catalog deleted successfully!";
	//Login to application as lab Owner
	init();
	Reporter.log("Login to Application as lab Owner");
	getWebElement("Enotebook.clicklogin.username").click();
	getWebElement("Enotebook.login.username").sendKeys("aqa2@mailinator.com");//("avd@mailinator.com");
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
	String NewCount1 = randomNumber.split(" ")[0];
	System.out.println("Random Number:"+randomNumber);
	System.out.println("NewCount4chars Number:"+NewCount1);
	String NewCount4chars = NewCount1.substring(NewCount1.length()-5);
	System.out.println("NewCount4chars4:"+NewCount4chars);
	for (int i=1;i<NewCount4chars.length(); i = i+1)
	{
		char singlechar = NewCount4chars.charAt(i);
		String stringValueOf = String.valueOf(singlechar);
		System.out.println("singlechar:"+NewCount4chars.charAt(i));
		getWebElement("AddNewProdcut_ProductName").sendKeys(stringValueOf);
		impliciteWait(20);
	}
	
	Thread.sleep(5000);
	impliciteWait(20);
	
	if(driver.findElements(By.xpath("//tr[@class='ui-autocomplete-group ui-widget-header']")).size()!= 0){
		System.out.println("Element is Present");
		//getWebElement("AddNewProdcut_ProductName").click();
		Thread.sleep(2000);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ENTER);
	}
	else{
		//getWebElement("AddNewProdcut_ProductName").click();
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
		getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ENTER);
		//getWebElement("AddNewSAProdcut_AutoComplete").sendKeys(Keys.BACK_SPACE);
		System.out.println("Element is Populated");
	}
	Thread.sleep(5000);
	impliciteWait(20);
	String ProductFullName = getWebElement("AddNewProdcut_ProductName").getAttribute("value");
	System.out.println("ProductFullName : "+ProductFullName);
	getWebElement("AddNewProdcut_AddtoCatalog").click();
	impliciteWait(10);
	Utills.captureScreenshot("AddSigma-AldrichProductConfirmation"+randomNumber);
	//Click on the OK button in Confirmation modal
	getWebElement("AddNewProdcut_OKconfirmationbutton").click();
	impliciteWait(10);
	System.out.println("ExpectedSuccessMessage"+ExpectedSuccessMessage);
	//Capture the Success message for further comparison and validation
	String Successmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
	Utills.captureScreenshot("AddSigma-AldrichProduct"+randomNumber);
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
		Reporter.log("New Sigma-Aldrich Product "+ProductFullName+" Added to Lab Catalog Successfully");
		Reporter.log("New Sigma-Aldrich Product Added to Catalog Successfully by Lab Member");
	}
	else
	{
		System.out.println("FAIL....................");
		System.out.println("Adding New Sigma-Aldrich Product to Catalog Failed");
		Reporter.log("Adding New Sigma-Aldrich Product to Catalog Failed");
		Reporter.log("Adding New Sigma-Aldrich Product to Catalog as Lab Member Failed");
	}
	//Delete Product Script
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
			Reporter.log("Lab Catalog, Sigma-Aldrich Product "+ProductFullName+" Deleted from Lab Catalog Successfully as Lab Member");
			Reporter.log("Lab Catalog, Sigma-Aldrich Product Deleted Successfully as Lab Member");
		}
		else
		{
			System.out.println("FAIL....................");
			System.out.println("Deleting Sigma-Aldrich Product from Lab Catalog as Lab Owner Failed");
			Reporter.log("Deleting Sigma-Aldrich Product from Lab catalog as Lab Owner Failed");
		}
	//Logout
	Reporter.log("Lab Member logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login = new LoginPage();
	login.Logout();
	}
	
}