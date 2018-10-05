package labCatalog;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.RequestConstants;
import utills.Utills;

public class LabCatalogRegularFunctions extends TestBase
{
	SoftAssert softAssertion= new SoftAssert();
	Library TodayDate = new Library();
	
	//Request New Products/ Quick Order
	public boolean RequestPageNavigation() throws Exception
	{
		//Navigation to Request Page
		Reporter.log("Navigate to lab Inventory Requests Page");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_Request").click();
		impliciteWait(6);
							
		//Verify materials page exists or not.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("Requests page displayed",materialsPageName.equals("Requests"));
		Reporter.log("Requests page displayed successfully.");
		Utills.captureScreenshot("Requests_Page_NavigationPass_"+TodayDate.Date());
		return true;
	}	
	
	//Navigate to Request Page to take the Initial Count
	public int RequestPageProductCount() throws Exception
	{	
		Thread.sleep(3000);
		//Get Initial Product Count Before Requesting New Product From Lab catalog
		java.util.List<WebElement> allLinksInForm = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedCount = allLinksInForm.size();
		Reporter.log("Request Count:"+RequestedCount);
		Utills.captureScreenshot("RequestinitialCount"+TodayDate.Date());
		Thread.sleep(3000);
		return RequestedCount;
	}
	
	//Navigate to Request Page to Verify if Reject Option is available
	public boolean RequestPageVerifyRejectOption() throws Exception
	{	
		Thread.sleep(3000);
		Utills.captureScreenshot("RequestPageRejectLink"+TodayDate.Date());
		//Get Initial Product Count Before Requesting New Product From Lab catalog
		boolean rejectalllink; 
		try
		{	
			getWebElement("RequestPage_RejectAlllink").isDisplayed();
			Reporter.log("Reject All Link is getting Displayed - Logged in as Lab Owner/Manager");
			Utills.captureScreenshot("RequestPageRejectLink"+TodayDate.Date());
			rejectalllink = true;
		}
		catch(Exception e)
		{
			Reporter.log("Reject All Link is NOT getting Displayed - Logged in as Lab Member");
			rejectalllink = false;
		}
		
		Thread.sleep(3000);
		return rejectalllink;
	}
	//Navigation to materials page	
	public boolean MaterialPageNavigation() throws Exception
	{
		//Navigation to Materials Page
		Reporter.log("Click on Inventory and Request then navigate to Material");
		impliciteWait(5);
		getWebElement("Inventory.NavigationBarInventoryAndRequest").click();
		impliciteWait(2);
		getWebElement("Inventory.NavigationMaterials").click();
							
		//Verify materials page exists or not.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("Materials page not displayed",materialsPageName.equals("Materials"));
		Reporter.log("Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_Pass_"+TodayDate.Date());
		return true;
	}
	
	//Navigation to materials page	
	public int MaterialCount() throws Exception
	{
		//Get initial Product Count
		String count = getWebElement("LabCatalog_ProductsCount").getText();
		String NewCount = count.split(" ")[0];
		int MaterialsCount = Integer.parseInt(NewCount);
		//Reporter.log("Lab Catalog initial Product count Fetched Successfully");
		System.out.println("InitialCount:"+MaterialsCount);
		Utills.captureScreenshot("MaterialCount"+TodayDate.Date());
		return MaterialsCount;
	}
	
	//Add Sigma Aldrich Product to Lab catalog
	public boolean AddSigmaAldrichProduct() throws Exception
	{
		//Test Data
		String ExpectedSuccessMessage = "Success! Catalog added successfully!";
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"QuickOrder_RequestNewProduct");
		String ProductName = ExcelUtils.getCellData(1, 0);
		
		//Navigate to lab catalog Page
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
		/*Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String NewCount1 = randomNumber.split(" ")[0];
		System.out.println("Random Number:"+randomNumber);
		System.out.println("NewCount4chars Number:"+NewCount1);
		String NewCount4chars = NewCount1.substring(NewCount1.length()-5);
		System.out.println("NewCount4chars4:"+NewCount4chars);*/
		
		for (int i=1;i<ProductName.length(); i = i+1)
		{
			char singlechar = ProductName.charAt(i);
			String stringValueOf = String.valueOf(singlechar);
			System.out.println("singlechar:"+ProductName.charAt(i));
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
		/*else{
			getWebElement("AddNewProdcut_ProductName").click();
			getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.BACK_SPACE);
			Thread.sleep(2000);
			getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
			getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
			getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ENTER);
			//getWebElement("AddNewSAProdcut_AutoComplete").sendKeys(Keys.BACK_SPACE);
			System.out.println("Element is Populated");
		}*/
		Thread.sleep(5000);
		impliciteWait(20);
		//String ProductFullName = getWebElement("AddNewProdcut_ProductName").getAttribute("value");
		//System.out.println("ProductFullName : "+ProductFullName);
		getWebElement("AddNewProdcut_AddtoCatalog").click();
		impliciteWait(10);
		Utills.captureScreenshot("AddSigma-AldrichProductConfirmation"+TodayDate.Date());
		//Click on the OK button in Confirmation modal
		getWebElement("AddNewProdcut_OKconfirmationbutton").click();
		impliciteWait(10);
		System.out.println("ExpectedSuccessMessage"+ExpectedSuccessMessage);
		//Capture the Success message for further comparison and validation
		String Successmessage = getWebElement("Prodcut_SuccessConfirmation").getText();
		Utills.captureScreenshot("AddSigma-AldrichProduct"+TodayDate.Date());
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
			Reporter.log("New Sigma-Aldrich Product "+ProductName+" Added to Lab Catalog Successfully");
			Reporter.log("New Sigma-Aldrich Product Added to Catalog Successfully");
		}
		else
		{
			System.out.println("FAIL....................");
			System.out.println("Adding New Sigma-Aldrich Product to Catalog Failed");
			Reporter.log("Adding New Sigma-Aldrich Product to Catalog Failed");
			Reporter.log("Adding New Sigma-Aldrich Product to Catalog Failed");
		}
		return true;
	}
	//Navigation to materials page	
	public boolean DeleteProduct() throws Exception
	{
		//Test Data		
		String ExpectedDeleteSuccessMessage = "Success! Catalog deleted successfully!";
		//Get initial Product Count
		String count = getWebElement("LabCatalog_ProductsCount").getText();
		String NewCount = count.split(" ")[0];
		int IntCount = Integer.parseInt(NewCount);
		//Reporter.log("Lab Catalog initial Product count Fetched Successfully");
		System.out.println("InitialCount:"+IntCount);
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
		System.out.println("Initial Count:"+IntCount);
		System.out.println("Final Count:"+newcoDelete1);
			if(ExpectedDeleteSuccessMessage.equals(DeleteSuccessmessage) && EndCountDel == IntCount-1)
			{
				System.out.println("PASS...................................");
				Reporter.log("Lab Catalog, Sigma-Aldrich Product Deleted from Lab Catalog Successfully as Lab Owner");
				Reporter.log("Lab Catalog, Sigma-Aldrich Product Deleted Successfully as Lab Owner");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Deleting Sigma-Aldrich Product from Lab Catalog as Lab Owner Failed");
				Reporter.log("Deleting Sigma-Aldrich Product from Lab catalog as Lab Owner Failed");
			}
			
		Reporter.log("Adding New Sigma-Aldrich Product is Deleted from the Catalog for avoiding coflict on addingthe product again");
		Utills.captureScreenshot("ProductDeleted"+TodayDate.Date());
		return true;
	}
	
	
		
	//Verify dash-board page exists or not.
	public boolean DashboardPageNavigation() throws Exception
	{
		Reporter.log("Navigation to dashboard page.");
		impliciteWait(5);
		getWebElement("Inventory.NavigationBar.Dashboard").click();
		Thread.sleep(1000);

		String PageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("Dashboard page not displayed.",PageName.equals("Dashboard"));
		Reporter.log("Dashboard page displayed successfully");
		Utills.captureScreenshot("Dashboard_Page_Pass"+TodayDate.Date());
		return true;
	}

	//Login in to the application
	public boolean UserLogin(String userName, String password) throws Exception
	{				
		Reporter.log("Login to Application Successful");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").click();
		getWebElement("Enotebook.login.username").sendKeys(userName);
		getWebElement("Enotebook.login.password").click();
		getWebElement("Enotebook.login.password").sendKeys(password);
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		impliciteWait(5);
				
		//Verify dash-board page exists or not.
		String PageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("User not logged in successfully in to an application and \"+PageName+\" page displayed.", PageName.equals("Dashboard"));
		Reporter.log("User logged in successfully in to an application and "+PageName+" page displayed.");
		Utills.captureScreenshot("Dashboard_Page_Pass"+TodayDate.Date());
		return true;
	}
	
	//Logout from an application
	public void UserLogout() throws Exception
	{
		Thread.sleep(2000);
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(2000);
		getWebElement("User.LogoutLink").click();
		Thread.sleep(2000);
		String homePage = getWebElement("User.HomePage").getText();
		if(homePage.equalsIgnoreCase("LOGIN"))
			Reporter.log("User logged out successfully from an application");
		else
			Assert.fail("Unsuccessfull. User not logged out from an application.");		
	}
	
	//Navigation to material detail page. Verify material detail page exists or not.
	public boolean MaterialDetailPageNavigation(String materialName) throws Exception
	{
		Reporter.log("Navigation to material detail page.");
		getWebElement("Inventory.MaterialDetailNavigation").click();
		impliciteWait(5);
		Thread.sleep(1000);
		String PageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("User not redirected to Material detail page of material -"+materialName,PageName.equals(materialName));
		Reporter.log("User redirected to Material detail page of material -"+materialName);
		Utills.captureScreenshot("Detail_Page_"+TodayDate.Date());
		return true;
	}

	public boolean VerifyGHSSymbol_Exist(String GHSSymbolName) throws Exception
	{
		List<WebElement> GHSSymbolNames = driver.findElements(By.xpath("//html//form[@id='safetyForm']//tr//td[3]"));
		boolean GHSSymbolExist = false;
		for(int i=0;i<GHSSymbolNames.size();i++)
		{
			String SymbolName = GHSSymbolNames.get(i).getText().trim();
			if(SymbolName.equalsIgnoreCase(GHSSymbolName))
			{
				GHSSymbolExist = true;
				break;
			}
			else
				GHSSymbolExist = false;
		}
		return GHSSymbolExist;
	}
}
