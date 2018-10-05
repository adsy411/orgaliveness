package request;


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

public class RequestRegularFunctions extends TestBase
{
	SoftAssert softAssertion= new SoftAssert();
	Library TodayDate = new Library();
	
	//Request New Products/ Quick Order
	public boolean RequestPageNavigation() throws Exception
	{
		//Navigation to Request Page
		explicitWaitForElementUntilClickable("SideBar_Inventory_EHS_Group");
		Reporter.log("Navigate to lab Inventory Requests Page");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		explicitWaitForElementUntilClickable("SubMenu_Request");
		getWebElement("SubMenu_Request").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
							
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
		//Thread.sleep(3000);
		//Get Initial Product Count Before Requesting New Product From Lab catalog
		List<WebElement> allLinksInForm = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedCount = allLinksInForm.size();
		Reporter.log("Request Count:"+RequestedCount);
		Utills.captureScreenshot("RequestinitialCount"+TodayDate.Date());
		//Thread.sleep(2000);
		return RequestedCount;
	}
	
	//Navigate to Request Page to Verify if Reject Option is available
	public boolean RequestPageVerifyRejectOption() throws Exception
	{	
		explicitWaitForElementUntilClickable("RequestPage_RejectAlllink");
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
		
		Thread.sleep(2000);
		return rejectalllink;
	}
	//Navigation to materials page	
	public boolean MaterialPageNavigation() throws Exception
	{
		//Navigation to Materials Page
		Reporter.log("Click on Inventory and Request then navigate to Material");
		explicitWaitForElementUntilClickable("Inventory.NavigationBarInventoryAndRequest");
		getWebElement("Inventory.NavigationBarInventoryAndRequest").click();
		explicitWaitForElementUntilClickable("Inventory.NavigationMaterials");
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
	public boolean QuickOrderAddNewSigmaAldrichRequest(int multiple) throws Exception
	{
		//Navigation to Request Page
		Reporter.log("Navigate to lab Inventory Requests Page");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		explicitWaitForElementUntilClickable("SubMenu_Request");
		getWebElement("SubMenu_Request").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");

		//Verify Request page exists or not.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("Requests page displayed",materialsPageName.equals("Requests"));
		Reporter.log("Requests page displayed successfully.");
		Utills.captureScreenshot("Requests_Page_NavigationPass_"+TodayDate.Date());
		//Get Initial Count of Products in the Request page
		explicitWaitForElementUntilClickable("RequestNewproductButton");
		
		//Get Initial Product Count Before Requesting New Product From Lab catalog
		java.util.List<WebElement> allLinksInForm = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedInitialCount = allLinksInForm.size();
		Utills.captureScreenshot("RequestPageInitial"+TodayDate.Date());
		
		
		//Click on Request New Button
		getWebElement("RequestNewproductButton").click();
		explicitWaitForElementUntilClickable("NewRequest_AddNewItemButton");
		for (int i=1;i<=multiple;i++)
		{
			//Thread.sleep(2000);
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"QuickOrder_RequestNewProduct");
			String ProductName = ExcelUtils.getCellData(i, 0);
			String Part1 = "//input[@id='addNewProducts:quickOrderTable:";
			String Part2 = ":product_name_input']";
			int RowNum = i-1; 
			String RowNumber = Integer.toString(RowNum);
			driver.findElement(By.xpath(Part1+RowNumber+Part2)).click();
			//Thread.sleep(2000);
			//getWebElement("NewRequest_Productname").click();
			driver.findElement(By.xpath(Part1+RowNumber+Part2)).sendKeys(ProductName);
			if(driver.findElements(By.xpath("//tr[@class='ui-autocomplete-group ui-widget-header']")).size()!= 0){
				System.out.println("Element is Present");
				//getWebElement("NewRequest_Productname").click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(Part1+RowNumber+Part2)).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath(Part1+RowNumber+Part2)).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath(Part1+RowNumber+Part2)).sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				Utills.captureScreenshot("RequestQuickOrderProduct"+TodayDate.Date());
				
			}
			else{
				getWebElement("NewRequest_CancelButton").click();
				explicitWaitForElementUntilClickable("RequestNewproductButton");
				getWebElement("RequestNewproductButton").click();
				ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"QuickOrder_RequestNewProduct");
				String ProductNameNew = ExcelUtils.getCellData(2, 0);
				driver.findElement(By.xpath(Part1+RowNumber+Part2)).click();
				driver.findElement(By.xpath(Part1+RowNumber+Part2)).sendKeys(ProductNameNew);
				if(driver.findElements(By.xpath("//tr[@class='ui-autocomplete-group ui-widget-header']")).size()!= 0){
					System.out.println("Element is Present");
					driver.findElement(By.xpath(Part1+RowNumber+Part2)).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath(Part1+RowNumber+Part2)).sendKeys(Keys.ARROW_DOWN);
					driver.findElement(By.xpath(Part1+RowNumber+Part2)).sendKeys(Keys.ARROW_DOWN);
					driver.findElement(By.xpath(Part1+RowNumber+Part2)).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					Utills.captureScreenshot("RequestQuickorderprod"+TodayDate.Date());
					
				}
				System.out.println("Element is Populated");
				
			}
			if (multiple>1 && i!= multiple)
			{
				
				getWebElement("NewRequest_AddNewItemButton").click();
				Thread.sleep(2000);
			}
			
		}
		
		getWebElement("NewRequest_SaveButton").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		//Get Final Product Count After Requesting New Product From Quick Order
		//Thread.sleep(2000);
		java.util.List<WebElement> allLinksInForm1 = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedFinalCount = allLinksInForm1.size();
		Thread.sleep(2000);
		Utills.captureScreenshot("RequestPage"+TodayDate.Date());
		if(RequestedFinalCount == RequestedInitialCount+multiple)
		{
			Reporter.log("Requested Product Quick Order Successfully");
		}
		else
		{
			Reporter.log("Requested Product Quick Order Fail");
		}
		return true;
	}
	
	//Select Single Product
	public void SelectProduct() throws Exception
	{
		//Thread.sleep(2000);
		String Part1 = "//label[@for='";
		String Part2 = "Checkbox']";
		String RowNumber = "0"; 
		driver.findElement(By.xpath(Part1+RowNumber+Part2)).click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("Select Product"+TodayDate.Date());
		Reporter.log("Product Selected");
	}
	
	//Approve Single Product
	public void MovetoApproved() throws Exception
	{
		//explicitWaitUntilElementIsInvisible("LoadingSymbol");
		String ApprovePart1 = "//a[@id='pendingForm:pendingTable:";
		String ApprovePart2 = ":approve']";
		String ApproveRowNumber = "0"; 
		driver.findElement(By.xpath(ApprovePart1+ApproveRowNumber+ApprovePart2)).click();
		/*String LocatorApprove = "//a[@id='pendingForm:pendingTable:0:approve']";
		driver.findElement(By.xpath(LocatorApprove)).click();*/
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("Approved"+TodayDate.Date());
		Reporter.log("Product Approved");
	}
	
	//Approve All Products
	public void MoveAlltoApproved() throws Exception
	{
		//explicitWaitForElementUntilClickable("");
		//explicitWaitUntilElementIsInvisible("LoadingSymbol");
		getWebElement("RequestPage_RequestTab_SelectAllCheckbox").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		getWebElement("Approve_All").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("Approved"+TodayDate.Date());
		Reporter.log("All Products Approved");
	}
	
	//Order Single Product
	public void MovetoOrdered() throws Exception
	{
		//Thread.sleep(10000); 
		String OrderPart1 = "//a[@id='approvedTabForm:approvedTable:";
		String OrderPart2 = ":orderSingle']";
		String OrderRowNumber = "0"; 
		driver.findElement(By.xpath(OrderPart1+OrderRowNumber+OrderPart2)).click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("Ordered"+TodayDate.Date());
		Reporter.log("Product Ordered");
	}
	
	//Order Single Product
	public void MoveAlltoOrdered() throws Exception
	{
		getWebElement("RequestPage_ApprovedTab_SelectAllCheckbox").click();
		Thread.sleep(2000);
		getWebElement("Move_All_toOrder").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("Ordered"+TodayDate.Date());
		Reporter.log("All Products Ordered");
	}
		
	//Move to Inventory
	public void MovetoInventory() throws Exception
	{
		//Thread.sleep(10000);
		String InventoryPart1 = "//a[@id='orderedTabForm:orderedTable:";
		String InventoryPart2 = ":moveToInvenSingle']";
		String InventoryRowNumber = "0"; 
		driver.findElement(By.xpath(InventoryPart1+InventoryRowNumber+InventoryPart2)).click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Reporter.log("Product Moved to Inventory");
	}
	
	//Move to Inventory
	public void MoveAlltoInventory() throws Exception
	{
		getWebElement("RequestPage_OrderedTab_SelectAllCheckbox").click();
		Thread.sleep(2000);
		getWebElement("Move_All_toInventory").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Reporter.log("All Products Moved to Inventory");
	}
		
	//Order Single Product Move Back to Approved
	public void MoveBacktoApproved() throws Exception
	{
		//Thread.sleep(2000); 
		String MovetoApprovedPart1 = "//a[@id='orderedTabForm:orderedTable:";
		String MovetoApprovedPart2 = ":revertSingle']";
		String MovetoApprovedRowNumber = "0"; 
		driver.findElement(By.xpath(MovetoApprovedPart1+MovetoApprovedRowNumber+MovetoApprovedPart2)).click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("MovetoApproved"+TodayDate.Date());
		Reporter.log("Selected product Moved back to Approved from Ordered tab");
	}
	
	//Move All Products Back to Approved
	public void MoveBackAlltoApproved() throws Exception
	{
		//Thread.sleep(2000); 
		getWebElement("RequestPage_OrderedTab_SelectAllCheckbox").click();
		Thread.sleep(2000);
		getWebElement("Move_backto_Approved").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("MovetoApproved"+TodayDate.Date());
		Reporter.log("All products Moved back to Approved from Ordered tab");
	}
	//Order Single Product Move Back to Request
	public void MoveBacktoRequest() throws Exception
	{
		//Thread.sleep(2000); 
		String MoveBacktoRequestPart1 = "//a[@id='approvedTabForm:approvedTable:";
		String MoveBacktoRequestPart2 = ":requests']";
		String MoveBacktoRequestRowNumber = "0"; 
		driver.findElement(By.xpath(MoveBacktoRequestPart1+MoveBacktoRequestRowNumber+MoveBacktoRequestPart2)).click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("MoveBacktoRequest"+TodayDate.Date());
		Reporter.log("Selected product Moved back to Requests tab from Approved tab");
	}
	
	//Move All Products Back to Request Tab
	public void MoveBackAlltoRequestTab() throws Exception
	{
		//Thread.sleep(2000); 
		getWebElement("RequestPage_ApprovedTab_SelectAllCheckbox").click();
		Thread.sleep(2000);
		getWebElement("Move_backto_Requested").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("MovetoRequest"+TodayDate.Date());
		Reporter.log("All products Moved back to Request tab from Approved tab");
	}
	
	//Order Single Product Move Back and reject
	public void Reject() throws Exception
	{
		//Thread.sleep(2000); 
		String RejectPart1 = "//a[@id='pendingForm:pendingTable:";
		String RejectPart2 = ":reject']";
		String RejectRowNumber = "0"; 
		driver.findElement(By.xpath(RejectPart1+RejectRowNumber+RejectPart2)).click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		Utills.captureScreenshot("MoveBacktoRequest"+TodayDate.Date());
		Reporter.log("Selected product Rejected from Request tab");
	}
		
		//Order Single Product Move Back and reject 
		public void RejectAll() throws Exception
	{
			//Thread.sleep(2000); 
			getWebElement("RequestPage_RequestTab_SelectAllCheckbox").click();
			Thread.sleep(2000);
			getWebElement("RejectAll").click();
			explicitWaitUntilElementIsInvisible("LoadingSymbol");
			Utills.captureScreenshot("Rejectall"+TodayDate.Date());
			Reporter.log("All products Rejected from Request tab");
	}
		
	
	//Add Note to Requested Product
	public void AddNotetoSingleProduct(String Tabname) throws Exception
	{
		String ProductTab = null ;
		String Part1;
		String Part2;
		String Part3;
		String NewPart1;
		String NewPart2;
		String ProductRowNumbers;
		String SaveNewPart1;
		String SaveNewPart2;
		String SaveProductRowNumbers;
		String ClickNewPart1;
		String ClickNewPart2;
		String ClickProductRowNumbers;
		
		 switch (Tabname) 
	        {
	            case "Requested":  ProductTab = "1";
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Part1 = "(//a[@class='text-primary moreLink";
		    		Part2 = "'])[";
		    		Part3 = "]";
		    		String ProductRowNumber = "0"; 
		    		
		    		driver.findElement(By.xpath(Part1+ProductRowNumber+Part2+ProductTab+Part3)).click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		NewPart1 = "//div[@class='inputDescOne";
		    		NewPart2 = "']//div[@align='center']";
		    		ProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(NewPart1+ProductRowNumbers+NewPart2)).click();
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		ClickNewPart1 = "//textarea[@id='pendingForm:pendingTable:";
		    		ClickNewPart2 = ":notesInput']";
		    		ClickProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(ClickNewPart1+ClickProductRowNumbers+ClickNewPart2)).click();
		    		driver.findElement(By.xpath(ClickNewPart1+ClickProductRowNumbers+ClickNewPart2)).sendKeys("Automation Testing Note Added in Requested Tab");
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		SaveNewPart1 = "//div[@class='inputDesc";
		    		SaveNewPart2 = "']//button[@title='Save']";
		    		SaveProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(SaveNewPart1+SaveProductRowNumbers+SaveNewPart2)).click();
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Reporter.log("Note added successfully to the selected product in Request tab");
		    		break;
		    		
	            case "Approved":  ProductTab = "2";
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol"); 
	            	Part1 = "(//a[@class='text-primary moreLink";
		            Part2 = "'])[";
		            Part3 = "]";
		            ProductRowNumber = "0"; 
		    		driver.findElement(By.xpath(Part1+ProductRowNumber+Part2+ProductTab+Part3)).click();
		            //driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[7]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[5]/a[1]/i[1]")).click();
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		NewPart1 = "//div[@class='inputDescOneApprove";
		    		NewPart2 = "']//div[@align='center']";
		    		ProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(NewPart1+ProductRowNumbers+NewPart2)).click();
		    		
		    		Thread.sleep(2000);//textarea[@id='approvedTabForm:approvedTable:0:notesInput']
		    		ClickNewPart1 = "//textarea[@id='approvedTabForm:approvedTable:";
		    		ClickNewPart2 = ":notesInput']";
		    		ClickProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(ClickNewPart1+ClickProductRowNumbers+ClickNewPart2)).click();
		    		driver.findElement(By.xpath(ClickNewPart1+ClickProductRowNumbers+ClickNewPart2)).sendKeys("Automation Testing Note Added in Approved Tab");
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");//div[@class='inputDescApprove0']//button[@title='Save']
		    		SaveNewPart1 = "//div[@class='inputDescApprove";
		    		SaveNewPart2 = "']//button[@title='Save']";
		    		SaveProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(SaveNewPart1+SaveProductRowNumbers+SaveNewPart2)).click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
	            //driver.findElement(By.cssSelector('body.page-header-fixed.page-sidebar-closed.page-sidebar-closed-hide-logo.page-content-white:nth-child(2) div.page-wrapper:nth-child(2) div.page-container:nth-child(12) div.page-content-wrapper div.page-content.project-listing-wrapper.product-cart-wrapper.requested-wrapper.requested-wrapper-new:nth-child(3) div.row div.col-lg-8:nth-child(1) div.tab-content div.tab-pane.active:nth-child(2) div.row:nth-child(3) div.col-xs-12.col-md-12.col-lg-12 div.portlet.light.bordered:nth-child(1) div.row div.col-lg-11.product-box div.portlet-title div.row div.col-xs-12.col-sm-6.col-md-6.col-lg-7.text-right:nth-child(2) ul.pager.wizard li:nth-child(5) a.text-primary.moreLink0:nth-child(1) > i.fa.fa-sticky-note'))).cl
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		Reporter.log("Note added successfully to the selected product in Approved Tab");
		    		break;
		    		
	            case "Ordered":  ProductTab = "3";
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol"); 
		            Part1 = "(//a[@class='text-primary moreLink";
		            Part2 = "'])[";
		            Part3 = "]";
		            ProductRowNumber = "0"; 
		    		driver.findElement(By.xpath(Part1+ProductRowNumber+Part2+ProductTab+Part3)).click();
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		NewPart1 = "//div[@class='inputDescOneOrder";
		    		NewPart2 = "']//div[@align='center']";
		    		ProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(NewPart1+ProductRowNumbers+NewPart2)).click();
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		ClickNewPart1 = "//textarea[@id='orderedTabForm:orderedTable:";
		    		ClickNewPart2 = ":notesInput']";
		    		ClickProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(ClickNewPart1+ClickProductRowNumbers+ClickNewPart2)).click();
		    		driver.findElement(By.xpath(ClickNewPart1+ClickProductRowNumbers+ClickNewPart2)).sendKeys("Automation Testing Note Added in Ordered Tab");
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		SaveNewPart1 = "//div[@class='inputDescOrder";
		    		SaveNewPart2 = "']//button[@title='Save']";
		    		SaveProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(SaveNewPart1+SaveProductRowNumbers+SaveNewPart2)).click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		Reporter.log("Note added successfully to the selected product in Ordered Tab");
		    		break;
		    		
	            case "Received":  ProductTab = "4";
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol"); 
		            Part1 = "//html//div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[3]/a[1]/i[1]";
		            driver.findElement(By.xpath(Part1)).click();
		            		            
		            explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		NewPart1 = "//div[@class='inputDescOneReceived";
		    		NewPart2 = "']//div[@align='center']";
		    		ProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(NewPart1+ProductRowNumbers+NewPart2)).click();
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		ClickNewPart1 = "//textarea[@id='receivedTabForm:receivedTable:";
		    		ClickNewPart2 = ":notesInput']";
		    		ClickProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(ClickNewPart1+ClickProductRowNumbers+ClickNewPart2)).click();
		    		driver.findElement(By.xpath(ClickNewPart1+ClickProductRowNumbers+ClickNewPart2)).sendKeys("Automation Testing Note Added in Received Tab");
		    		
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		SaveNewPart1 = "//div[@class='inputDescReceived";
		    		SaveNewPart2 = "']//button[@title='Save']";
		    		SaveProductRowNumbers = "0"; 
		    		driver.findElement(By.xpath(SaveNewPart1+SaveProductRowNumbers+SaveNewPart2)).click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		Reporter.log("Note added successfully to the selected product in Received Tab");
		    		break;
	            default: ProductTab = "1";
	                     break;
	        }
	}
	
	//Add Notes to all the products
	public void AddNotetoAllProducts(String Tabname) throws Exception
	{
		switch (Tabname) 
	        {
	            case "Requested":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_RequestTab_SelectAllCheckbox").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_RequestTab_NotesIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("AddNotestoAllTextArea").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("AddNotestoAllTextArea").sendKeys("Automation Testing Note Added in Request Tab for all the Products");
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		getWebElement("AddNotestoAllSaveButton").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Reporter.log("Note added successfully to All products in Request tab");
		    		break;
		    		
	            case "Approved":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_ApprovedTab_SelectAllCheckbox").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_ApprovedTab_NotesIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("AddNotestoAllTextArea").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("AddNotestoAllTextArea").sendKeys("Automation Testing Note Added in Approved Tab for all the Products");
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		getWebElement("AddNotestoAllSaveButton").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Reporter.log("Note added successfully to All products in Approved tab");
		    		break;
		    		
	            case "Ordered": 
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_OrderedTab_SelectAllCheckbox").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_OrderedTab_NotesIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("AddNotestoAllTextArea").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("AddNotestoAllTextArea").sendKeys("Automation Testing Note Added in Ordered Tab for all the Products");
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		getWebElement("AddNotestoAllSaveButton").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Reporter.log("Note added successfully to All products in Ordered tab");
		    		break;
		    		
	            case "Received": 
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_ReceivedTab_SelectAllCheckbox").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_ReceivedTab_NotesIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("AddNotestoAllTextArea").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("AddNotestoAllTextArea").sendKeys("Automation Testing Note Added in Received Tab for all the Products");
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		getWebElement("AddNotestoAllSaveButton").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Reporter.log("Note added successfully to All products in Received tab");
		    		break;
	        }
	}
	
	//Download all the products
	public void DownloadAllProducts(String Tabname) throws Exception
	{
		switch (Tabname) 
	        {
	            case "Requested":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_RequestTab_SelectAllCheckbox").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestedTabDownloadIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestedTabExporttoExcel").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		Reporter.log("Downloaded successfully All products in Request tab");
		    		break;
		    		
	            case "Approved":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_ApprovedTab_SelectAllCheckbox").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("ApprovedTabDownloadIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("ApprovedTabExporttoExcel").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		Reporter.log("Downloaded successfully All products in Approved tab");
		    		break;
		    		
	            case "Ordered": 
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_OrderedTab_SelectAllCheckbox").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("OrderedTabDownloadIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("OrderedTabExporttoExcel").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		Reporter.log("Downloaded successfully All products in Ordered tab");
		    		break;
		    		
	            case "Received": 
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestPage_ReceivedTab_SelectAllCheckbox").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("ReceivedTabDownloadIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("ReceivedTabExporttoExcel").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		Reporter.log("Downloaded successfully All products in Received tab");
		    		break;
	        }
	}
    
	//Add Notes to all the products
	public void AddAllProductstoCart(String Tabname) throws Exception
	{
		switch (Tabname) 
	        {
	            case "Requested":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("Request_AddAlltoCartIcon_RequestTab").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Reporter.log("All Eligible products in Request tab are added to Cart");
		    		driver.navigate().refresh();
		    		break;
		    		
	            case "Approved":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("Request_AddAlltoCartIcon_ApprovedTab").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Utills.captureScreenshot("NotesAdded"+TodayDate.Date());
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		Reporter.log("All Eligible products in Approved tab are added to Cart");
		    		driver.navigate().refresh();
		    		break;
	        }
	}
	
	//Edit Price
	public void EditPriceRequestFlow(String Tabname) throws Exception
	{
		switch (Tabname) 
	        {
	            case "Requested":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol"); 
		            driver.findElement(By.xpath("(//form[@id='pendingForm']//i[@class='fa fa-pencil'])[1]")).click();
		            explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestTab_EditPrice_TextField").sendKeys("10");
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("RequestTab_EditPrice_SaveIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		if(getWebElement("RequestTab_EditedPrice").getText() == "10") {
		    			Utills.captureScreenshot("EditPrice"+TodayDate.Date());
			    		Reporter.log("Price Edited successfully in Request tab");
		    		}
		    		break;
		    		
	            case "Approved":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		            getWebElement("ApprovedTab_EditPrice_PenIcon").click();
		            explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("ApprovedTab_EditPrice_TextField").sendKeys("10");
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("ApprovedTab_EditPrice_SaveIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		if(getWebElement("ApprovedTab_EditedPrice").getText() == "10") {
		    			Utills.captureScreenshot("EditPrice"+TodayDate.Date());
			    		Reporter.log("Price Edited successfully in Approved tab");
		    		}
		    		break;
		    		
	            case "Ordered": 
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol"); 
		            getWebElement("OrderedTab_EditPrice_PenIcon").click();
		            explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("OrderedTab_EditPrice_TextField").sendKeys("10");
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("OrderedTab_EditPrice_SaveIcon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		if(getWebElement("OrderedTab_EditedPrice").getText() == "10") {
		    			Utills.captureScreenshot("EditPrice"+TodayDate.Date());
			    		Reporter.log("Price Edited successfully in Ordered tab");
		    		}
		    		break;
	        }
	}
	
	public void EditCountRequestFlow(String Tabname) throws Exception
	{
		switch (Tabname) 
	        {
	            case "Requested":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol"); 
	            	getWebElement("RequestTab_EditCount").sendKeys("50");
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
	            	getWebElement("MconnectedLab_Icon").click();
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		if(getWebElement("RequestTab_EditCount").getText() == "150") {
		    			System.out.println("Pass");
		    			Utills.captureScreenshot("EditCount"+TodayDate.Date());
			    		Reporter.log("Count Edited successfully in Request tab");
		    		}
		    		else {
		    			System.out.println("Pass");
		    		}
		    		driver.navigate().refresh();
		    		break;
		    		
	            case "Approved":  
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
	            	getWebElement("ApprovedTab_EditCount").sendKeys("50");
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
	            	getWebElement("MconnectedLab_Icon").click();
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		if(getWebElement("ApprovedTab_EditCount").getText() == "150") {
		    			System.out.println("Pass");
		    			Utills.captureScreenshot("EditCount"+TodayDate.Date());
			    		Reporter.log("Count Edited successfully in Approved tab");
		    		}
		    		else {
		    			System.out.println("Pass");
		    		}
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		driver.navigate().refresh();
		    		break;
		    		
	            case "Ordered": 
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
	            	getWebElement("OrderedTab_EditCount").sendKeys("50");
	            	explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		getWebElement("MconnectedLab_Icon").click();
		    		explicitWaitUntilElementIsInvisible("LoadingSymbol");
		    		if(getWebElement("OrderedTab_EditCount").getText() == "150") {
		    			System.out.println("Pass");
		    			Utills.captureScreenshot("EditCount"+TodayDate.Date());
			    		Reporter.log("Count Edited successfully in Ordered tab");
		    		}
		    		else {
		    			System.out.println("Pass");
		    		}
		    		driver.navigate().refresh();
		    		break;
	        }
	}
	//Verify dash-board page exists or not.
	public boolean DashboardPageNavigation() throws Exception
	{
		Reporter.log("Navigation to dashboard page.");
		impliciteWait(5);
		getWebElement("Inventory.NavigationBar.Dashboard").click();
		explicitWaitUntilElementIsInvisible("LoadingSymbol");

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
		Thread.sleep(5000);
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
	
		
	public String[] VerifyGHSSymbol(String GHSSymbolNames) throws Exception
	{
		GHSSymbolNames = GHSSymbolNames.trim();
		
		//Check how many GHS symbol exist.
		int GHSSymbolCount = 1;
		for(int i=0; i<GHSSymbolNames.length();i++)
		{
			 char coma = GHSSymbolNames.charAt(i);
			 if(coma == ',')
				 GHSSymbolCount = GHSSymbolCount + 1;
		}
		
		String[] GHSSymbolNamesOutput = new String[2];
		String GHSsymbolNamesNotExist = null;
		String GHSSymbolNamesExist = null;
		if(GHSSymbolCount>1)
		{
			String[] GHSSymbolName = new String[GHSSymbolCount];
			
			for(int i=0;i<GHSSymbolName.length;i++)
			{
				GHSSymbolName[i] = GHSSymbolNames.split(",")[0].trim();
				boolean symbolExist = VerifyGHSSymbol_Exist(GHSSymbolName[i]);
			
				if(symbolExist == true)
				{
					if(GHSSymbolNamesExist == null)
						GHSSymbolNamesOutput[0] = GHSSymbolName[i];
					else
						GHSSymbolNamesOutput[0] = GHSSymbolNamesExist+","+GHSSymbolName[i];
				}
				else
				{
					if(GHSsymbolNamesNotExist == null)
						GHSSymbolNamesOutput[1] = GHSSymbolName[i];
					else
						GHSSymbolNamesOutput[1] = GHSsymbolNamesNotExist+","+GHSSymbolName[i];
				}
			}
		}
		else if(GHSSymbolCount == 1)
		{
			boolean symbolExist = VerifyGHSSymbol_Exist(GHSSymbolNames);
			if(symbolExist == true)
			{
				GHSSymbolNamesOutput[0] = GHSSymbolNames;
				GHSSymbolNamesOutput[1] = GHSsymbolNamesNotExist;
			}
			else
			{
				GHSSymbolNamesOutput[1]= GHSSymbolNames;
				GHSSymbolNamesOutput[0] = GHSsymbolNamesNotExist;		
			}
		}
		
		return GHSSymbolNamesOutput;
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
