package labCatalog;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class DashboardActivity_Request_SigmaAldrichProduct extends TestBase {

@Test
public void VerifyDashboardActivityfor_SigmaAldrichProductRequest() throws Exception {

	//LOGIN TEST DATA
		String ExpectedSuccessMessage = "Success! Catalog added successfully!";
		String ExpectedRequestSuccessMessage = "Success! Request added successfully!";
		//Login to application as lab Owner
		init();
		Reporter.log("Login to Application as lab Owner");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("aqauser@mailinator.com");
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
		Thread.sleep(1000);
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
				getWebElement("AddNewProdcut_ProductName").click();
				Thread.sleep(2000);
				getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
				getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ARROW_DOWN);
				getWebElement("AddNewProdcut_ProductName").sendKeys(Keys.ENTER);
			}
			else{
				getWebElement("AddNewProdcut_ProductName").click();
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
				Reporter.log("New Sigma-Aldrich Product Added to Catalog Successfully by Lab Owner");
			}
			else
			{
				System.out.println("FAIL....................");
				System.out.println("Adding New Sigma-Aldrich Product to Catalog Failed");
				Reporter.log("Adding New Sigma-Aldrich Product to Catalog Failed");
				Reporter.log("Adding New Sigma-Aldrich Product to Catalog as Lab Owner Failed");
			}
			Thread.sleep(5000);
			//Click on the Request Icon in the Grid view
			getWebElement("RequestProdcut_CatalogPageGridView").click();
			//Thread.sleep(5000);
			impliciteWait(20);
			//Capture the Success message for further comparison and validation
			String RequestedSuccessmessage = getWebElement("RequestedProdcut_SuccessConfirmation").getText();
			System.out.println("Requested Successmessage:"+RequestedSuccessmessage+".");
			System.out.println("Expected Requested SuccessMessage:"+ExpectedRequestSuccessMessage+".");
			
			//Navigate to lab catalog list
			Reporter.log("Navigate to lab catalog list");
			getWebElement("SideBar_Inventory_EHS_Group").click();
			impliciteWait(3);
			getWebElement("SubMenu_Request").click();
			impliciteWait(6);
			
			//Get Final Product Count after adding New Product to Lab catalog
			java.util.List<WebElement> allLinksInForm1 = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
			int RequestedCountFinal = allLinksInForm1.size();
			
			//Adding 1 to the Final Count and Verifying if its equal to the Initial Count and also verifying the Success Message 
			int EndCountRequest =  RequestedCountInitial+1;
			System.out.println("Request Initial Count:"+RequestedCountInitial);
			System.out.println("Request Final Count:"+RequestedCountFinal);
				if(ExpectedRequestSuccessMessage.equals(RequestedSuccessmessage) && EndCountRequest == RequestedCountFinal)
				{
					System.out.println("PASS...................................");
					Reporter.log("Lab Catalog Sigma-Aldrich Product "+ProductFullName+" is Requested from Lab Catalog as Lab Owner Successfully");
					Reporter.log("Lab Catalog Sigma-Aldrich Product is Requested as Lab Owner Successfully");
				}
				else
				{
					System.out.println("FAIL....................");
					System.out.println("Requesting Sigma-Aldrich Product from Lab catalog Failed as Lab Owner");
					Reporter.log("Requesting Sigma-Aldrich Product from Lab Catalog Failed as Lab Owner");
				}
			
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
			java.util.List<WebElement> allLinksInForm11 = driver.findElements(By.xpath("//p[@class='activity-owner']"));
			String DashboardActivity = allLinksInForm11.get(0).getText();
			System.out.println("RequestedCountFinal"+DashboardActivity);
			
			//Verify the Dashboard Activity for Increase in Quantity
			String Dprod=DashboardActivity.split("of")[1].trim();
			String DprodNew=Dprod.split("-")[0].trim();
			Assert.assertEquals(DprodNew,ProductFullName,"Dashboard Activity successful");
			System.out.println(DashboardActivity);
			Utills.captureScreenshot("EditAllFieldsNONSAProductConfirmation"+randomNumber);
	/*
			//getWebElement("SubMenu_DashboardGroup").click();
			impliciteWait(40);
			java.util.List<WebElement> allLinksInForm12 = driver.findElements(By.xpath("//p[@class='activity-owner']"));
			String DashboardActivity1 = allLinksInForm12.get(1).getText();
			System.out.println("RequestedCountFinal"+DashboardActivity);
			
			//Verify the Dashboard Activity for Increase in Quantity
			String Dprod1=DashboardActivity1.split(",")[1].trim();
			String DprodNew1=Dprod1.split("\n")[0].trim();
			Assert.assertEquals(DprodNew1,ProductFullName,"Dashboard Activity successful");
			System.out.println(DashboardActivity1);
			Utills.captureScreenshot("EditAllFieldsNONSAProductConfirmation"+randomNumber);
			*/
	//Logout
	Reporter.log("Lab Owner logout of the Application and Browser is Closed");
	Thread.sleep(5000);
	LoginPage login2 = new LoginPage();
	login2.Logout();	
	}
}