package inventory;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class InventoryRegularFunctions extends TestBase
{
	SoftAssert softAssertion= new SoftAssert();
	Library TodayDate = new Library();
	
	//Navigation to materials page
	public boolean MaterialPageNavigation() throws Exception
	{
		//Navigation to Materials Page
		Reporter.log("Click on Inventory and Navigate to Materials Page.");
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
	
	public void verifyMaterialsPage() throws Exception
	{
		//Verify materials page exists or not.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("Materials page not displayed",materialsPageName.equals("Materials"));
		Reporter.log("Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_Pass_"+TodayDate.Date());
	}
	
	//Navigation to Add material Modal
	public void AddMaterialModalNavigation() throws Exception
	{
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material page displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
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
		Reporter.log("Login to Application with specific user");
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
		Reporter.log("Logout from an application.");
		getWebElement("Inventory.UserIcon").click();
		explicitWaitForElement("User.LogoutLink");
		getWebElement("User.LogoutLink").click();
		
		explicitWaitForElement("User.HomePage");
		String homePage = getWebElement("User.HomePage").getText();
		if(homePage.equalsIgnoreCase("LOGIN"))
			Reporter.log("User logged out successfully from an application");
		else
			Assert.fail("Unsuccessfull. User not logged out from an application.");		
	}
	
	public String Get_LoggedIn_UserName() throws Exception
	{
		getWebElement("Inventory.UserIcon").click();
		Thread.sleep(1000);
		String labUserName = getWebElement("Inventory.UserName").getText().trim();
		
		return labUserName;
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
		Reporter.log("User redirected to Material detail page of material - "+materialName);
		Utills.captureScreenshot("Detail_Page_"+TodayDate.Date());
		return true;
	}

	//Dispose a material from card view
	public String CardViewDisposeMaterial(String disposeMaterialQuantity, String materialQuantity,String materialName) throws Exception
	{
		//Verify dispose modal exist or not
		getWebElement("Inventory.CardView.DisposeMaterial").click();
		Thread.sleep(3000);
			
		VerifyDisposeModal_CardView(materialName);
		
		//Dispose a material
		getWebElement("Inventory.CardView.DisposeQuantity").click();
		getWebElement("Inventory.CardView.DisposeQuantity").clear();
		getWebElement("Inventory.CardView.DisposeQuantity").sendKeys(disposeMaterialQuantity);
		getWebElement("Inventory.CardView.DisposeModal.DisposeRadioButton").click();
		Thread.sleep(1000);
		String disposeMaterialDate = Date_For_MaterialLog();
		getWebElement("Inventory.DisposeButton").click();
		impliciteWait(2);
		
		//Verification of validation message when disposed quantity is greater than material quantity.
		if(Double.parseDouble(disposeMaterialQuantity) > Double.parseDouble(materialQuantity))
		{
			String disposeQuantityValidationMessage = getWebElement("Inventory.DisposeMaterial.QuantityValidation").getText();
			Assert.assertTrue("Validation message displayed as - '"+disposeQuantityValidationMessage+"' when dispose quantity greater than material quantity", disposeQuantityValidationMessage.equals("Quantity should be less than the actual quantity"));
			Reporter.log("Validation message displayed successfully for dispose quantity greater than material quantity as - "+disposeQuantityValidationMessage);
			getWebElement("Inventoy.DisposeModal.CancelButton").click();
			Thread.sleep(1000);
		}
		
		//Verification of Success message after dispose of material. Verify materials page exists or not after disposal
		else if(Double.parseDouble(disposeMaterialQuantity) <= Double.parseDouble(materialQuantity))
		{
			String ActualDisposeSuccessMessage = getWebElement("Inventory.DisposeSuccessMessage").getText();
			String ExpectedDisposeSuccessMessage = "Success! Material Disposed";
			if(ActualDisposeSuccessMessage.equals(ExpectedDisposeSuccessMessage))
				Reporter.log("After material disposal, the Success Message displayed successfully."+ActualDisposeSuccessMessage);
			else 
				softAssertion.fail("After material disposal, the Success Message not displayed."+ActualDisposeSuccessMessage);
			Utills.captureScreenshot("Material_Dispose_Success_Message_"+TodayDate.Date());
			
			//Verify materials page exists or not after disposal
			String materialsPageName = getWebElement("Inventory.PageHeading").getText();
			Assert.assertTrue("After material disposal, the materials page not displayed", materialsPageName.equals("Materials"));
			Reporter.log("After material disposal, the materials page displayed successfully.");
			Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
					
		}
		
		return disposeMaterialDate;
	}
	
	public void DisposedMaterialsPageNavigation() throws Exception
	{
		getWebElement("Inventory.Filter").click();
		explicitWaitForElement("Inventory.Filter.ShowDisposedMaterials");
		explicitWaitForElementUntilClickable("Inventory.Filter.ShowDisposedMaterials");
		Thread.sleep(2000);
		
		//Clicks on Show Disposed Materials check-box
		if(!getWebElement("Inventory.Filter.ShowDisposedMaterials").isSelected())
		{
			Reporter.log("Show Disposed Materials checkbox is unchecked in filter modal.");
			getWebElement("Inventory.Filter.ShowDisposedMaterials").click();	
			getWebElement("Inventory.Filter.Apply").click();
		}
		else
			Assert.fail("Show Disposed Materials checkbox is checked in filter modal.");
		
		//Verify disposed materials page exists or not.
		explicitWaitUntilElementIsInvisible("Inventory.LoadingSymbol");
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("Disposed Materials page not displayed after filter search.", materialsPageName.equals("Materials"));
		Reporter.log("Disposed Materials page displayed successfully after filter search.");
		Utills.captureScreenshot("Disposed_Materials_Page_"+TodayDate.Date());			
	}

	public void VerifyDisposeModal_CardView(String materialName) throws Exception
	{
		explicitWaitForElement("Inventory.DisposeModalName");
		String disposeModalTitle = getWebElement("Inventory.DisposeModalName").getText();
		Assert.assertTrue("Dispose modal not displayed",disposeModalTitle.equals("Dispose Material"));
		Reporter.log("Dispose modal displayed successfully.");
		
		String cardViewDisposeModalMaterialName = getWebElement("Inventory.CardView.DisposeModal.MaterialName").getText();
		if(materialName.equals(cardViewDisposeModalMaterialName))
			System.out.println("Material name displayed in dispose modal");
		else
			softAssertion.fail("Material name not displayed in dispose modal");
		Utills.captureScreenshot("Dispose_Modal_"+TodayDate.Date());		
	}
	
	public void VerifyDisposeModal_DetailPage(String materialName) throws Exception
	{
		String disposeModalTitle = getWebElement("Inventory.DisposeModalName").getText();
		Assert.assertTrue("Dispose modal not displayed",disposeModalTitle.equals("Dispose Material"));
		Reporter.log("Dispose modal displayed successfully.");
		
		String DetailPageDisposeModalMaterialName = getWebElement("Inventory.MaterialDetail.DisposeModal.MaterialName").getText();
		if(materialName.equals(DetailPageDisposeModalMaterialName))
			System.out.println("Material name displayed in dispose modal");
		else
			softAssertion.fail("Material name not displayed in dispose modal");
		Utills.captureScreenshot("Dispose_Modal_"+TodayDate.Date());		
	}
	
	public void VerfityQuantity(String quantity)throws Exception
	{
		int digitCount = 0;
		for(int j=0; j<quantity.length()-1;j++)
		{
			char c = quantity.charAt(j);
		    if(c == ' ') 
		    	Assert.fail("Dispose quantity cantains an empty space");
		    else if(quantity.charAt(j) == '.')
		    	digitCount = digitCount+1;
		    else if(Character.isDigit(c))
		    	digitCount = digitCount+1;
		    else if(Character.isLetter(c)) 
		    	Assert.fail("Quantity contains an alphabet");
		    else
		    	Assert.fail("Quantity contains an Specisl character");
		}
	}
	
	public void VerifyDisposedMaterial(String materialName) throws Exception
	{
		String disposedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Recently Disposed Material - "+materialName+" not displayed at the top of disposed materials page.", disposedListMaterialName.equals(materialName));
		Reporter.log("Recently Disposed Material - "+materialName+" displayed at the top of disposed materials page.");
		Utills.captureScreenshot("Disposed_Material_"+TodayDate.Date());	
	}

	public void VerifyBarcodeModal_CardView() throws Exception
	{
		String barcodeModalTitle = getWebElement("Inventory.BarcodeModalTitle").getText();
		Assert.assertTrue("Barcode modal not displayed", barcodeModalTitle.equals("Add or View existing Barcode"));
		Reporter.log("Barcode modal displayed successfully.");
		Utills.captureScreenshot("Material_Card_View_Barcode_Modal_"+TodayDate.Date());
	}
	
	public void VerifyBarcodeModal_DetailPage() throws Exception
	{
		String barcodeModalTitle = getWebElement("Inventory.MaterialDetail.BarcodeModalTitle").getText();
		Assert.assertTrue("Barcode modal not displayed", barcodeModalTitle.equals("Add or View existing Barcode"));
		Reporter.log("Barcode modal displayed successfully.");
		Utills.captureScreenshot("Material_Detail_Barcode_Modal_"+TodayDate.Date());
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
	
	public void Add_GHS_Symbol_Card_View(String GHSSymbolName) throws Exception
	{
		//Clicks on ADD GHS Symbol icon
		getWebElement("Inventory.CardView.AddGHSSymbol").click();
		Thread.sleep(2000);
		impliciteWait(5);
		
		String GHSModalName = getWebElement("Inventory.GHS.VerifySafetyModal").getText();
		Assert.assertTrue("Safety Modal not displayed. Unable to add GHS symbol to Third Party Vendor material.",GHSModalName.equalsIgnoreCase(GHSModalName));
		Reporter.log("Add Safety Image modal displayed to add the GHS symbol");	
		
		boolean symbolExists = false;
		int i = 0;
		List<WebElement> GHSSymbolNames = driver.findElements(By.xpath("//html//form[@id='safetyForm']//tr//td[3]"));
		for(i=0;i<GHSSymbolNames.size();i++)
		{
			String SymbolName = GHSSymbolNames.get(i).getText().trim();
			if(SymbolName.equalsIgnoreCase(GHSSymbolName))
			{
				symbolExists = true;
				break;
			}
			else
				symbolExists = false;
		}
		
		//Selects the GHS symbol
		boolean symbolSelected = false;
		if(symbolExists == true)
		{
			Reporter.log("The given GHS symbol - "+GHSSymbolName+" exist in the Safety Modal.");
			driver.findElement(By.xpath("//form[@id='safetyForm']//tr["+(i+1)+"]//span[@class='labelauty-checked-image']")).click();
			Thread.sleep(1000);
				
			//Verify the GHS Symbol is selected or not.
			try
			{	
				driver.findElement(By.xpath("//form[@id='safetyForm']//tr["+(i+1)+"]//label[@aria-checked='true']")).isDisplayed();
				Reporter.log("GHS Symbol Selected successfully");
				symbolSelected = true;
			}
			catch(NoSuchElementException e)
			{
				Assert.fail("GHS Symbol not selected");
				symbolSelected = false;
			}			
		}
		else
			Assert.fail("The given GHS symbol - "+GHSSymbolName+" does not exist in the Safety Modal.");
		
		if(symbolSelected == true)
		{
			getWebElement("Inventory.GHS.ADDButton").click();
			Thread.sleep(1000);
			String expectedSuccessMessage = "Success! GHS symbol Added.";
			String actualSuccessMessage = getWebElement("Inventory.MaterialsPage.SuccessMessage").getText().trim();
			if(actualSuccessMessage.equalsIgnoreCase(expectedSuccessMessage))
				Reporter.log("Success message displayed successfully after adding GHS symbol to a material as - "+actualSuccessMessage);
			else
				softAssertion.fail("Success message not displayed after adding GHS symbol to a material. The actual message displayed as - "+actualSuccessMessage);
		}	
	}
	
	public void Verify_Material_Status_Filter_Search() throws Exception
	{
		//Click on Filter icon
		getWebElement("Inventory.Filter").click();
		Thread.sleep(3000);
		
		//Verify Material Type filter section displayed in filter modal
		String materialStatus = getWebElement("Inventory.FilterSearch.MaterialsStatusField").getText().trim();
		Assert.assertTrue("Material Status filter section not displayed in filter modal", materialStatus.equalsIgnoreCase("Material Status"));
		Reporter.log("Material Status filter section displayed in filter modal");
		
		//Verify the status types in filter modal
		List<WebElement> statusTypes = driver.findElements(By.xpath("Inventory.FilterSearch.MaterialsStatusFilterTypes"));
		for(int i =0; i<statusTypes.size(); i++) 
		{ 
			if(i==0)
			{
				String statusName = statusTypes.get(i).getText().trim(); 
				Assert.assertTrue("Material Status - 'Running Low' not displayed", statusName.equalsIgnoreCase("Running Low"));
			}
			else if(i==1)
			{
				String statusName = statusTypes.get(i).getText().trim(); 
				Assert.assertTrue("Material Status - 'Minimum Quantity' not displayed", statusName.equalsIgnoreCase("Minimum Quantity"));
			}
			{
				String statusName = statusTypes.get(i).getText().trim();
				Assert.fail("Invalid material status - "+statusName+" dispalyed in filter modal");
			}
		}
	}
	
	public void Verify_Expire_Date_Filter_Search() throws Exception
	{
		//Click on Filter icon
		getWebElement("Inventory.Filter").click();
		Thread.sleep(2000);
		
		//Verify Expire Date filter section displayed in filter modal
		String expirationDateField = getWebElement("Inventory.FilterSearch.ExpirationDateField").getText().trim();
		Assert.assertTrue("Material Status filter section not displayed in filter modal", expirationDateField.equalsIgnoreCase("Expiration Date"));
		
		//Verify the expiration date types in filter modal
		List<WebElement> expirationDateFilterTypes = driver.findElements(By.xpath("Inventory.FilterSearch.ExpirationDateTypes"));
		for(int i =0; i<expirationDateFilterTypes.size(); i++) 
		{ 
			switch(i)
			{
				case 0: 
						String expirationDateTypeName1 = expirationDateFilterTypes.get(i).getText().trim(); 
						Assert.assertTrue("Expiration date filter type - 'Expired' not displayed", expirationDateTypeName1.equalsIgnoreCase("Expired"));
						break;
				case 1:	
						String expirationDateTypeName2 = expirationDateFilterTypes.get(i).getText().trim(); 
						Assert.assertTrue("Expiration date filter type - 'Not Expired' not displayed", expirationDateTypeName2.equalsIgnoreCase("Not Expired"));
						break;
				case 2:
						String expirationDateTypeName3 = expirationDateFilterTypes.get(i).getText().trim(); 
						Assert.assertTrue("Expiration date filter type - 'Expiring in next 30 Days' not displayed", expirationDateTypeName3.equalsIgnoreCase("Expiring in next 30 Days"));
						break;
				default:
						String expirationDateTypeName = expirationDateFilterTypes.get(i).getText().trim();
						Assert.fail("Invalid Expiration data filter type - "+expirationDateTypeName+" dispalyed in filter modal");
						break;
			}
		}
		Reporter.log("Expiration date filter section displayed in filter modal");
	}
	
	//Selects the multiple material
	public void Select_Multiple_Materials(int materialsCount) throws Exception
	{
		
		List <WebElement> allCheckbox = driver.findElements(By.xpath("//span[@id='pageForm:materialList']//span[@class='labelauty-checked-image']"));
		int checkboxSize = allCheckbox.size();
		if(checkboxSize%2 ==0)
			checkboxSize = checkboxSize-1;
		
		int leftSelect = 0;
		for(int selectCount = 1; selectCount<=materialsCount; selectCount++)
		{
			if(selectCount%2 ==0)
			{	
				int selectMaterial = (checkboxSize/2)+2;
				allCheckbox.get(selectMaterial-1).click();
				Thread.sleep(2000);
				checkboxSize = checkboxSize +2;
			}
			else
			{
				allCheckbox.get(leftSelect).click();
				Thread.sleep(2000);
				leftSelect++;
			}
		}
		Utills.captureScreenshot("Material_Select_"+TodayDate.Date());
	}
	
	//Verify Multiple selected materials
	public void Verify_MultiSelected_Materials(int materialsCount) throws Exception
	{
		List <WebElement> verifycheckboxSelected = driver.findElements(By.xpath("//span[@id='pageForm:materialList']//label[@aria-checked='true']"));
		int selectedCheckboxSize = verifycheckboxSelected.size();
		if(selectedCheckboxSize%2 ==0)
			selectedCheckboxSize = selectedCheckboxSize-1;
		
		int verifyleftSelected = 0;
		for(int selectedCount = 1; selectedCount<=materialsCount; selectedCount++)
		{
			if(selectedCount%2 ==0)
			{	
				try
				{
					int selectedMaterial = (selectedCheckboxSize/2)+2;
					verifycheckboxSelected.get(selectedMaterial-1).isDisplayed();
					selectedCheckboxSize = selectedCheckboxSize +2;
				}
				catch (Exception e) 
				{
					softAssertion.fail("Material not selected");
				}
				Thread.sleep(1000);
				
			}
			else
			{
				try
				{
					verifycheckboxSelected.get(verifyleftSelected).isDisplayed();
					verifyleftSelected++;
				}
				catch (Exception e) 
				{
					softAssertion.fail("Material not selcted");
				}
				Thread.sleep(1000);
			}
		}	
	}
	
	public void Add_Material_With_Storage(int rowNumber, String locationName) throws Exception
	{
		//Navigation to "Add Material" modal
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
		String casNumber = ExcelUtils.getCellData(rowNumber, 6);
		String lotNumber = ExcelUtils.getCellData(rowNumber, 7);
		String manufactredDate = ExcelUtils.getCellData(rowNumber, 8);
		String expirationDate = ExcelUtils.getCellData(rowNumber, 9);
		String brand = ExcelUtils.getCellData(rowNumber, 10);
		String url = ExcelUtils.getCellData(rowNumber, 11);
		String boilingPoint = ExcelUtils.getCellData(rowNumber, 12);
		String meltingPoint = ExcelUtils.getCellData(rowNumber, 13);
		String flashingPoint = ExcelUtils.getCellData(rowNumber, 14);
		String minimumCount = ExcelUtils.getCellData(rowNumber, 15);
		String maximumCount = ExcelUtils.getCellData(rowNumber, 16);
		String materialType = ExcelUtils.getCellData(rowNumber, 17);
		String description = ExcelUtils.getCellData(rowNumber, 18);
		
		//String locationName = "test";
		String temperature = "25";
		
		//Entering the material details in Add Material Modal
		Reporter.log("Enter the material details and add it to Inventory");
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMateriName").sendKeys(materialName);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		
		getWebElement("Inventory.Quantity").click();
		Thread.sleep(1000);
		getWebElement("Inventory.Quantity").sendKeys(quantity);
		
		//Verify the provided UOM exists in list
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.UOM",uom) == true)
			SelectList.SelectByValue("Inventory.UOM",uom);
		else
			Assert.fail("The UOM - "+uom+" does not exist");
		Thread.sleep(1000);
		
		//Check whether the vendor exists or not.
		if(SelectList.VerifySelectList("Inventory.Vendor",vendorName) == true)
		{
			SelectList.SelectByVisibleText("Inventory.Vendor",vendorName);
			Thread.sleep(1000);
		}
		//Creates a new vendor
		else
		{
			Reporter.log("The Vendor - "+vendorName+" provided in test data does not exist. A new vendor is created");
			SelectList.SelectByVisibleText("Inventory.Vendor","Add Vendor");
			Thread.sleep(1000);
			getWebElement("Inventory.AddVendor").sendKeys(vendorName);
			Thread.sleep(1000);
		}
		
		//Click on Location modal
		getWebElement("Inventory.AddMaterial.AddLocation").click();
		Thread.sleep(3000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify location already exists in storage list.
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
	
		//Creation of new location.
		if(locationFound == false)
		{	
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation(locationName, temperature);
		}
		
		//Selects the location in storage modal
		StorageRegularFunctions selectLocation = new StorageRegularFunctions();
		selectLocation.SelectLocation(locationName);
		
		Thread.sleep(1000);
		getWebElement("Storage.SaveButton").click();
		Thread.sleep(5000);
		
		//Verify the location displayed in add material modal
		String selectedLocationName = getWebElement("Inventory.AddMaterial.AddLocation").getAttribute("value");
		Assert.assertTrue("Location value not displayed in add material modal",selectedLocationName.equals(locationName));
		Reporter.log("Location displayed in add material modal");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Verify the provided material type exists in list
		if(SelectList.VerifySelectList("Inventory.MaterialType",materialType) == true)
			SelectList.SelectByVisibleText("Inventory.MaterialType",materialType);
		else
			softAssertion.fail("The Material Type - "+materialType+" does not exist");
		
		//Clicks on "Other Details" tab.
		Thread.sleep(1000);
		getWebElement("Inventory.OtherDetailsTab").click();
		impliciteWait(2);
		
		getWebElement("Inventory.AddCasNumber").click();
		getWebElement("Inventory.AddCasNumber").sendKeys(casNumber);
		getWebElement("Inventory.AddLotNumber").sendKeys(lotNumber);
		getWebElement("Inventory.AddManufacturedDate").sendKeys(manufactredDate);
		getWebElement("Inventory.AddExpiredDate").sendKeys(expirationDate);
		getWebElement("Inventory.AddBrand").sendKeys(brand);
		getWebElement("Inventory.AddURL").sendKeys(url);
		getWebElement("Inventory.AddBoilingPoint").sendKeys(boilingPoint);
		getWebElement("Inventory.AddMeltingPoint").sendKeys(meltingPoint);
		getWebElement("Inventory.FlashingPoint").sendKeys(flashingPoint);
		getWebElement("Inventory.MinimumCount").sendKeys(minimumCount);
		getWebElement("Inventory.MaximumCount").sendKeys(maximumCount);
		Thread.sleep(1000);
		getWebElement("Inventory.Description").click();
		getWebElement("Inventory.Description").sendKeys(description);
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(4000);
		impliciteWait(3);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.", addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		getWebElement("Inventory.OkButton").click();
		impliciteWait(5);
		Thread.sleep(1000);
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());

		//Verification of Success message after addition of new material.
		Thread.sleep(2000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			System.out.println("Success Message displayed as - "+ActualSuccessMessage+" after addition of new material");
		else 
			softAssertion.fail("Success Message not displayed after addition of new material.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material, the materials page not displayed", materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());	
		
		//Verify the material exists
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		Thread.sleep(2000);
		
		//Verify material assigned to location 
		String assignedLocation = getWebElement("Inventory.CardView.LocationName").getText();
		if(assignedLocation.equals(locationName))
			System.out.println("Location '"+locationName+"' assigned successfully to a material - "+materialName);
		else
			softAssertion.fail("Location - "+locationName+" not assigned to a material - "+materialName);
	}
	
	public void Verify_Location_Name_Filter_Search(String locationName) throws Exception
	{
		//Click on Filter icon
		getWebElement("Inventory.Filter").click();
		Thread.sleep(3000);
		
		//Verify Material Type filter section displayed in filter modal
		getWebElement("Inventory.FilterSearch.LocationNameFilterSection").isDisplayed();
		Reporter.log("Location Name filter section displayed in filter modal");
		
		//Enter the location Name
		getWebElement("Inventory.FilterSearch.LocationName").click();
		getWebElement("Inventory.FilterSearch.LocationName").sendKeys(locationName);
		Thread.sleep(4000);
		
		//Verify auto suggestions displayed
		Assert.assertTrue("Auto suggestions not displayed for Location Name search field in filter modal",getWebElement("Inventory.FilterSearch.AutoComplete.LocationName").isDisplayed());
		Reporter.log("Auto suggestions displayed in Location Name search field");
		
		boolean locationNameExist = false;
		int i=0;
		List<WebElement> LocationNameAutoSuggestions =  driver.findElements(By.xpath("//span[@id='materialPageForm:filterLocation_panel']/ul/li"));
		for(i=0;i<LocationNameAutoSuggestions.size();i++)
		{
			String autoCompleteListLocationName = LocationNameAutoSuggestions.get(i).getText().trim();
			if(autoCompleteListLocationName.equalsIgnoreCase(locationName))
			{
				locationNameExist = true;
				break;
			}
			else
				locationNameExist = false;
		}
		
		Assert.assertTrue("Location name - "+locationName+" not displayed in Auto suggestion list",(locationNameExist == true));
		Reporter.log("Location name - "+locationName+" displayed in Auto suggestion list");
		
		//Selects the location name from auto complete list
		driver.findElement(By.xpath("//span[@id='materialPageForm:filterLocation_panel']/ul/li["+(i+1)+"]")).click();
		Thread.sleep(2000);
		
		//Check the selected filter displayed 
		String filterName = getWebElement("Inventory.FilterSearch.LocationName.FilteredName").getText();
		Assert.assertTrue("Selected Location Name - "+locationName+" not added to the filter list",locationName.toLowerCase().contains(filterName.toLowerCase()));
		Reporter.log("Selected Location Name - "+locationName+" added to the filter list");
		
		//Clicks on Apply button
		getWebElement("Inventory.FilterSearch.ApplyButton").click();
		Thread.sleep(7000);
		
		//Verify materials page displayed after filter search
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After filter search, Materials page not displayed", materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
	}
	
	public void Card_View_Add_Barcode(String barcodeName) throws Exception
	{
		//Adding barcode to the material from card view page
		getWebElement("Inventory.CardView.BarcodeIcon").click();
		Thread.sleep(5000);
			
		//Verify barcode modal exist or not
		String barcodeModalTitle = getWebElement("Inventory.BarcodeModalTitle").getText();
		Assert.assertTrue("Barcode modal not displayed", barcodeModalTitle.equals("Add or View existing Barcode"));
		Reporter.log("Barcode modal displayed successfully.");
		Utills.captureScreenshot("Material_Barcode_Modal_"+TodayDate.Date());
		
		// Adding barcode to a material
		getWebElement("Inventory.CardView.AddBarcode").click();
		getWebElement("Inventory.CardView.AddBarcode").sendKeys(barcodeName);
		getWebElement("Inventory.Detail.AddBarcodeButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.AddBarcodeOkButton").click();
		impliciteWait(4);
			
		//Verification of Success message after adding barcode to a material.
		String ActualBarcodeSuccessMessage = getWebElement("Inventory.AddBacodeSuccessMessage").getText();
		String ExpectedBarcodeSuccessMessage = "Success! Material Custom Barcode updated";
		if(ActualBarcodeSuccessMessage.equals(ExpectedBarcodeSuccessMessage))
			Reporter.log("After adding barcode to a material, the Success Message displayed successfully as - "+ActualBarcodeSuccessMessage+" in material card view page");
		else 
			softAssertion.fail("After adding barcode to a material, the message displayed in material card View Page as - "+ActualBarcodeSuccessMessage);
		
		//Verify materials page displayed after add barcode functionality
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of barcode, Materials page not displayed", materialsPageName.equals("Materials"));
		
		Utills.captureScreenshot("Material_Barcode_Success_Message_"+TodayDate.Date());
	}
	
	public void Material_Name_Search(String materialName) throws Exception
	{
		getWebElement("Inventory.MaterialSearch").click();
		getWebElement("Inventory.MaterialSearch").sendKeys(materialName);
		Thread.sleep(10000);
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		String pagination = getWebElement("Inventory.Pagination").getText();
		int pageCount = Integer.parseInt(pagination.split("Page 1 of ")[1]);
		int materialscount =0;
		
		if(materialsCountAfter>0)
		{
			for(int j=1;j<=pageCount;j++)
			{
				List<WebElement> materialsName = driver.findElements(By.xpath("//span[@id='pageForm:materialList']/div//a/span"));
				for(int i =0; i<materialsName.size(); i++) 
				{ 
					String elementText = materialsName.get(i).getText(); 
					Assert.assertTrue("Invalid materials found in search result page",elementText.toLowerCase().contains(materialName.toLowerCase()));
					materialscount = materialscount+1;
				}	
				//check whether the pagination exists
				int currentPage = Integer.parseInt((pagination.split("Page ")[1]).split(" ")[0]);
				if(currentPage<pageCount)
				{
					getWebElement("Inventory.NextPage").click();
					Thread.sleep(2000);
				}
				else
					break;
			}	
			System.out.println(materialscount);
		}
		else
			Assert.fail("Materials not displayed for searched material name - "+materialName);

		//Verify the count displayed is correct
		Utills.captureScreenshot("Material_Search_"+TodayDate.Date());
		Assert.assertTrue("Invalid materials found in search result page when searched for material name - "+materialName, materialsCountAfter == materialscount);
		Reporter.log(materialscount+" materials displayed -  in search result page after search for material name - "+materialName);		
	}
	
	public void Catalog_Number_Search(String catalogNumber) throws Exception
	{
		getWebElement("Inventory.MaterialSearch").click();
		getWebElement("Inventory.MaterialSearch").sendKeys(catalogNumber);
		Thread.sleep(10000);
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		String pagination = getWebElement("Inventory.Pagination").getText();
		int pageCount = Integer.parseInt(pagination.split("Page 1 of ")[1]);
		int materialscount =0;
		
		if(materialsCountAfter>0)
		{
			for(int j=1;j<=pageCount;j++)
			{
				List<WebElement> catalogNumbers = driver.findElements(By.xpath("//span[@id='pageForm:materialList']//label[contains(text(),'Catalog# ')]/following-sibling::p"));
				System.out.println(catalogNumbers.size());
				for(int i =0; i<catalogNumbers.size(); i++) 
				{ 
					String elementText = catalogNumbers.get(i).getAttribute("title").trim();
					System.out.println("elementText = "+elementText);
					Assert.assertTrue("Invalid materials found in search result page",elementText.toLowerCase().contains(catalogNumber.toLowerCase()));
					materialscount = materialscount+1;
				}
				//check whether the pagination exists
				int currentPage = Integer.parseInt((pagination.split("Page ")[1]).split(" ")[0]);
				if(currentPage<pageCount)
				{
					getWebElement("Inventory.NextPage").click();
					Thread.sleep(2000);
				}
				else
					break;
			}	
			System.out.println(materialscount);
		}
		else
			Assert.fail("Materials not displayed for searched Catalog Number - "+catalogNumber);

		//Verify the count displayed is correct
		Utills.captureScreenshot("Material_Search_"+TodayDate.Date());
		Assert.assertTrue("Invalid materials found in search result page when searched for Catalog Number - "+catalogNumber, materialsCountAfter == materialscount);
		Reporter.log(materialscount+" materials displayed in search result page after search for Catalog Number - "+catalogNumber);		
	}
	
	public void CAS_Number_Search(String casNumber) throws Exception
	{
		getWebElement("Inventory.MaterialSearch").click();
		getWebElement("Inventory.MaterialSearch").sendKeys(casNumber);
		Thread.sleep(10000);
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		String pagination = getWebElement("Inventory.Pagination").getText();
		int pageCount = Integer.parseInt(pagination.split("Page 1 of ")[1]);
		int materialscount =0;
		
		if(materialsCountAfter>0)
		{
			for(int j=1;j<=pageCount;j++)
			{
				List<WebElement> materialsName = driver.findElements(By.xpath("//span[@id='pageForm:materialList']//label[contains(text(),'CAS # ')]/following-sibling::p"));
				for(int i =0; i<materialsName.size(); i++) 
				{ 
					String elementText = materialsName.get(i).getAttribute("title").trim();
					Assert.assertTrue("Invalid materials found in search result page",elementText.toLowerCase().contains(casNumber.toLowerCase()));
					materialscount = materialscount+1;
				}	
				//check whether the pagination exists
				int currentPage = Integer.parseInt((pagination.split("Page ")[1]).split(" ")[0]);
				if(currentPage<pageCount)
				{
					getWebElement("Inventory.NextPage").click();
					Thread.sleep(2000);
				}
				else
					break;
			}	
			System.out.println(materialscount);
		}
		else
			Assert.fail("Materials not displayed for searched CAS Number - "+casNumber);

		//Verify the count displayed is correct
		Utills.captureScreenshot("Material_Search_"+TodayDate.Date());
		Assert.assertTrue("Invalid materials found in search result page when searched for CAS Number - "+casNumber, materialsCountAfter == materialscount);
		Reporter.log(materialscount+" material(s) displayed in search result page after search for CAS Number - "+casNumber);		
	}
	
	public void MaterialsPage_MaterialRequestModal(String materialName) throws Exception
	{
		Reporter.log("Navigation to request modal from materials page.");
		//Verify the request modal
		String requestModalTitle = getWebElement("Inventory.MaterialsPage.RequestModal.Title").getText().trim();
		Assert.assertTrue("Request modal not dispalyed. Unable to request a material.", requestModalTitle.equalsIgnoreCase("Request Count"));
		Reporter.log("Request modal dispalyed successfully.");
		
		//Verify the product name in request modal
		String requestModalProductName = getWebElement("Inventory.MaterialsPage.RequestModal.ProductName").getText().trim();
		softAssertion.assertTrue(requestModalProductName.contains(materialName), 
				"Product name not displayed in request modal");
		Reporter.log("Product name displayed successfully in Request modal");
		
		//clicks on Request button
		Utills.captureScreenshot("Materials_Page_Request_Modal_"+TodayDate.Date());
		getWebElement("Inventory.MaterialsPage.RequestModal.RequestButton").click();
		
		//Wait until the loading icon disappear
		explicitWaitUntilElementIsInvisible("Inventory.LoadingSymbol");
		
		//Verification of Success message after requesting a new material.
		Utills.captureScreenshot("Material_Request_Success_Message_"+TodayDate.Date());	
		String ActualRequestMaterialSuccessMessage = getWebElement("Inventory.RequestSuccessMessage").getText();
		String ExpectedRequestMaterialSuccessMessage = "Success! Material Requested";
				
		if(ActualRequestMaterialSuccessMessage.equals(ExpectedRequestMaterialSuccessMessage))
			Reporter.log("After material request, the Success Message displayed successfully as - '"+ExpectedRequestMaterialSuccessMessage+"' in material card view page");
		else
			softAssertion.fail("After material request, the Success Message not displayed in material card view page. Actual message displayed as - "+ActualRequestMaterialSuccessMessage);
	}
	
	public String[] MaterialDetailPage_MaterialRequestModal(String materialName) throws Exception
	{
		Reporter.log("Navigation to request modal from material detail page.");
		
		//Verify the request modal
		String requestModalTitle = getWebElement("Inventory.MaterialDetailPage.RequestModal.Title").getText().trim();
		Assert.assertTrue("Request modal not dispalyed. Unable to request a material.", requestModalTitle.equalsIgnoreCase("Request Count"));
		Reporter.log("Request modal dispalyed successfully.");
		
		//Verify the product name in request modal
		String requestModalProductName = getWebElement("Inventory.MaterialDetailPage.RequestModal.ProductName").getText().trim();
		if(requestModalProductName.contains(materialName))
			Reporter.log("Product name displayed successfully in Request modal");
		else
			softAssertion.fail("Product name not displayed in request modal");
		
		//Stores the request count in a variable.
		String request[] = new String[2];
		request[0] = getWebElement("Inventory.MaterialDetailPage.RequestModal.Count").getAttribute("value");
		
		//clicks on Request button
		Utills.captureScreenshot("Material_Detail_Page_Request_Modal_"+TodayDate.Date());
		InventoryRegularFunctions date = new InventoryRegularFunctions();
		request[1] = date.Date_For_MaterialLog();
		getWebElement("Inventory.MaterialDetailPage.RequestModal.RequestButton").click();
		Thread.sleep(5000);
		
		//Verification of Success message after requesting a new material.
		Utills.captureScreenshot("Material_Request_Success_Message_"+TodayDate.Date());
		String ActualRequestMaterialSuccessMessage = getWebElement("Inventory.RequestSuccessMessage").getText();
		String ExpectedRequestMaterialSuccessMessage = "Success! Material Requested!";
		if(ActualRequestMaterialSuccessMessage.equals(ExpectedRequestMaterialSuccessMessage))
			Reporter.log("After material request, the Success Message displayed successfully as - "+ActualRequestMaterialSuccessMessage+" in material detail page");
		else
			softAssertion.fail("After material request, the Success Message not displayed in material detail page.The message displayed as - "+ActualRequestMaterialSuccessMessage);
	
		return request;
	}
	
	public void MaterialDetailPage_Verfy_MaterialLogModal() throws Exception
	{
		String modalTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.ModalTitle").getText().trim();
		if(modalTitle.equalsIgnoreCase("Material Activity"))
			System.out.println("Material Log modal displayed successfully.");
		else
			Assert.fail("Material Log modal not displayed.");
	}
	
	public String Date_For_MaterialLog() throws Exception
	{
		DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm a");
		LocalDateTime now = LocalDateTime.now();
		String Date = DateFormat.format(now);
		return Date;
	}
	
	public String Material_Detail_Page_Material_Disposal(String materialName, String materialQuantity, String disposeMaterialQuantity) throws Exception
	{
		Reporter.log("Dispose a material from material detail page.");
		
		//Verify dispose modal exist or not
		getWebElement("Inventory.Detail.Dispose").click();
		Thread.sleep(3000);
		VerifyDisposeModal_DetailPage(materialName);
		
		//Dispose a material
		getWebElement("Inventory.Detail.DisposeQuantity").click();
		getWebElement("Inventory.Detail.DisposeQuantity").clear();
		getWebElement("Inventory.Detail.DisposeQuantity").sendKeys(disposeMaterialQuantity);
		getWebElement("Inventory.DisposeRadioButton").click();
		Thread.sleep(1000);
		String disposeMaterialDate = Date_For_MaterialLog();
		getWebElement("Inventory.DisposeButton").click();
		impliciteWait(2);
		
		//Verification of Success message after dispose of material.
		String ActualDisposeSuccessMessage = getWebElement("Inventory.DisposeSuccessMessage").getText();
		String ExpectedDisposeSuccessMessage = "Success! Material Disposed";
				
		if(ActualDisposeSuccessMessage.equals(ExpectedDisposeSuccessMessage))
			Reporter.log("After material disposal, the Success Message displayed successfully."+ActualDisposeSuccessMessage);
		else 
			softAssertion.fail("After material disposal, the Success Message not displayed."+ActualDisposeSuccessMessage);
		Utills.captureScreenshot("Material_Dispose_Success_Message_"+TodayDate.Date());
				
		//Verify materials page exists or not after disposal
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After material disposal, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After material disposal, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		return disposeMaterialDate;
	}
	
	public void Verify_Add_Material_Log(String materialName, int rowNumber, String materialQuantity, String materialUOM, String labUserName) throws Exception
	{
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		String materialAddedDate = AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Reporter.log("Navigate to Material Log modal and verify the material added log.");
		
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		InventoryRegularFunctions verifyMaterialLogModal= new InventoryRegularFunctions();
		verifyMaterialLogModal.MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Material added log
		String actualMaterialAddedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedMaterialAddedLogTitle = "Material Added by "+labUserName;
		String actualMaterialAddedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedMaterialAddedLogDescription = null;
		if(materialQuantity.contains("."))
			expectedMaterialAddedLogDescription = materialQuantity+" "+materialUOM+" of "+materialName+" Added.";
		else
			expectedMaterialAddedLogDescription = materialQuantity+".0 "+materialUOM+" of "+materialName+" Added.";
		
		String actualMaterialAddedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		
		Utills.captureScreenshot("Material_Added_Log_"+TodayDate.Date());
		if(actualMaterialAddedLogTitle.equalsIgnoreCase(expectedMaterialAddedLogTitle) &&
				actualMaterialAddedLogDescription.equalsIgnoreCase(expectedMaterialAddedLogDescription) &&
				materialAddedDate.equalsIgnoreCase(actualMaterialAddedLogDate))
			Reporter.log("Material added log displayed in Material Activity modal.");
		else
			softAssertion.fail("Material added log not displayed in Material Activity modal.");
		
		//close the material log modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}

	public void Verify_Material_Requested_Log(String materialName, String materialUOM, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		//Verify the material exists
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		Thread.sleep(2000);
		
		//Get the material quantity
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		String materialQuantity = getWebElement("Inventory.CardView.EditQuantity").getAttribute("value");
	
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Requesting a material from material detail page.
		getWebElement("Inventory.Detail.RequestIcon").click();
		Thread.sleep(3000);
		
		//Requesting the material.
		String request[] = new String[2];
		request = MaterialDetailPage_MaterialRequestModal(materialName);
		Reporter.log(materialName+" requested successfully from material detail page.");
				
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.navigate().refresh();
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		Reporter.log("Navigate to Material Activity modal and verify the material requested log.");
		
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Material requested log
		String actualMaterialRequestedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedMaterialRequestedLogTitle = "Material Requested by "+labUserName;
		String actualMaterialRequestedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedMaterialRequestedLogDescription = null;
		if(materialQuantity.contains("."))
			expectedMaterialRequestedLogDescription = request[0]+" Units of "+materialName+" - "+materialQuantity+" "+materialUOM+" Requested";
		else
			expectedMaterialRequestedLogDescription = request[0]+" Units of "+materialName+" - "+materialQuantity+".0 "+materialUOM+" Requested";
		
		String actualMaterialRequestedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		String expectedMaterialRequestedLogDate = request[1];
		
		Utills.captureScreenshot("Material_Requested_Log_"+TodayDate.Date());
		if(actualMaterialRequestedLogTitle.equalsIgnoreCase(expectedMaterialRequestedLogTitle) &&
				actualMaterialRequestedLogDescription.equalsIgnoreCase(expectedMaterialRequestedLogDescription) &&
				actualMaterialRequestedLogDate.equalsIgnoreCase(expectedMaterialRequestedLogDate))
			Reporter.log("Material Requested log displayed in Material Activity modal.");
		else
			softAssertion.fail("Material Requested log not displayed in Material Activity modal.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public void Verify_Partial_Material_Disposed_Log(String materialName, String disposeMaterialQuantity, String materialUOM, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		//Take the materials count, before disposing a material
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the material exists
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		Thread.sleep(2000);
		
		//Get the material quantity
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		String materialQuantity = getWebElement("Inventory.CardView.EditQuantity").getAttribute("value");
		
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Partially disposing a material from material detail page.
		String expectedMaterialDisposedLogDate = null;
		if(Double.parseDouble(disposeMaterialQuantity) < Double.parseDouble(materialQuantity) && Double.parseDouble(disposeMaterialQuantity)>0)
			expectedMaterialDisposedLogDate = Material_Detail_Page_Material_Disposal(materialName, materialQuantity, disposeMaterialQuantity);
		else
			Assert.fail("Unable to dispose a material partially. Dispose quantity should be less than material quantity");	
		
		//Verify the materials count when a material is disposed.
		Thread.sleep(1000);
		int materialsCountAfterDispose = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
				
		if(materialsCountAfterDispose == materialsCountBefore)
			System.out.println("After partial material disposal-"+materialName+" the material count remains same.");
		else 
			softAssertion.fail("After partial dispose of material - "+materialName+" the count of materials varies.");	
		
		//Verify the disposed material displayed at the top of the page.
		String materialListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Partially Disposed material not displayed at the top of the materials page.",materialListMaterialName.equals(materialName));
						
		//Navigation to material detail page to Verify the material disposed log.
		MaterialDetailPageNavigation(materialName);

		Reporter.log("Navigate to Material Activity modal and verify the material disposed log.");
		
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Material disposed log
		String actualMaterialDisposedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedMaterialDisposedLogTitle = "Material Disposed by "+labUserName;
		String actualMaterialDisposedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedMaterialDisposedLogDescription = null;
		if(disposeMaterialQuantity.contains("."))
			expectedMaterialDisposedLogDescription = disposeMaterialQuantity+" "+materialUOM+" of "+materialName+" Disposed.";
		else
			expectedMaterialDisposedLogDescription = disposeMaterialQuantity+".0 "+materialUOM+" of "+materialName+" Disposed.";

		String actualMaterialDisposedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		
		Utills.captureScreenshot("Material_Disposed_Log_"+TodayDate.Date());
		if(actualMaterialDisposedLogTitle.equalsIgnoreCase(expectedMaterialDisposedLogTitle) &&
				actualMaterialDisposedLogDescription.equalsIgnoreCase(expectedMaterialDisposedLogDescription) &&
				actualMaterialDisposedLogDate.equalsIgnoreCase(expectedMaterialDisposedLogDate))
			Reporter.log("Partial Material Disposed log displayed in Material Activity modal.");
		else
			softAssertion.fail("Partial Material Disposed log not displayed in Material Activity modal.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public void Verify_location_Assigned_Log(String materialName, String locationName, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		//Verify the material exists
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Reporter.log("Navigate to Storage modal from material detail page.");
		//Navigation to storage modal
		getWebElement("Inventory.MaterialDetail.Location").click();
		Thread.sleep(3000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify storage exits
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
		if(locationFound == false)
		{
			//Creation of new location.
			String temperature = "35";
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation(locationName, temperature);
		}

		//Selecting the location
		StorageRegularFunctions selectLocation = new StorageRegularFunctions();
		selectLocation.SelectLocation(locationName);
		Thread.sleep(1000);
		
		//Updating material location
		getWebElement("Storage.SaveButton").click();
		Thread.sleep(4000);
		
		//Verify the location displayed for location field in material detail page
		String selectedLocationName = getWebElement("Inventory.MaterialDetail.Location").getAttribute("value");
		Assert.assertTrue("Location value not displayed in Material detail page.",selectedLocationName.equals(locationName));
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(5000);
		
		String expectedLocationAssignedLogDate = Date_For_MaterialLog();
		getWebElement("Inventory.OkButton").click();
		Thread.sleep(2000);

		//Verify materials page exists or not after updation of material details.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After updation of material details, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("Materials page displayed successfully after updation of storage to material");
		
		String actualMaterialNameAfterUpdationOfStorage = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Updated material - "+materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialNameAfterUpdationOfStorage, actualMaterialNameAfterUpdationOfStorage.equals(materialName));
		
		//Navigation to material detail page to Verify the location log.
		MaterialDetailPageNavigation(materialName);
		
		Reporter.log("Navigate to Material Activity modal and verify the location assigned log.");
		
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Location Assigned log
		String actualLocationAssignedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedLocationAssignedLogTitle = "Location Changed by "+labUserName;
		String actualLocationAssignedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedLocationAssignedLogDescription = "Unassigned changed to "+locationName;
		String actualLocationAssignedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		
		Utills.captureScreenshot("Location_Assigned_Log_"+TodayDate.Date());
		if(actualLocationAssignedLogTitle.equalsIgnoreCase(expectedLocationAssignedLogTitle) &&
				actualLocationAssignedLogDescription.equalsIgnoreCase(expectedLocationAssignedLogDescription) &&
				actualLocationAssignedLogDate.equalsIgnoreCase(expectedLocationAssignedLogDate))
			Reporter.log("Location assigned log displayed in Material Activity modal.");
		else
			softAssertion.fail("Location assigned log not displayed in Material Activity modal.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public void Verify_location_Unassigned_Log(String materialName, String locationName, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		//Verify the material exists
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		
		//Verify material assigned to location 
		String assignedLocation = getWebElement("Inventory.CardView.LocationName").getText();
		Assert.assertTrue("Location - "+locationName+" not assigned to a material - "+materialName,assignedLocation.equalsIgnoreCase(locationName));

		Reporter.log("Navigate to Storage modal from materials page to delete the storage.");
		
		//Navigation to storage modal
		getWebElement("Inventory.CardView.LocationIcon").click();
		Thread.sleep(5000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify location exists in storage list.
		boolean locationFound = verifyStorage.VerifyLocation_MaterialsPage(locationName);
		
		//Creation of new location.
		if(locationFound == false)
		{	
			String temperature = "35";
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation_MaterialsPage(locationName, temperature);
			impliciteWait(3);
		}	
		
		//Deletion of Location from Storage List
		StorageRegularFunctions deleteLocation = new StorageRegularFunctions();
		String expectedLocationUnassignedLogDate = deleteLocation.MaterialsPage_DeleteLocation(locationName);
		Thread.sleep(3000);
		
		//Close the storage modal
		getWebElement("Storage.CloseIcon").click();
		Thread.sleep(4000);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(1000);
		
		//Verify the material exists after deletion of storage
		String actualMaterialNameAfterDeletionOfStorage = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialNameAfterDeletionOfStorage, actualMaterialNameAfterDeletionOfStorage.equalsIgnoreCase(materialName));
		
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Reporter.log("Navigate to Material Activity modal and verify the location Unassigned log.");
		
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Location Un-Assigned log
		String actualLocationUnassignedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedLocationUnassignedLogTitle = "Location Changed by "+labUserName;
		String actualLocationUnassignedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedLocationUnassignedLogDescription = locationName+" changed to Unassigned";
		String actualLocationUnassignedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		
		Utills.captureScreenshot("Location_Assigned_Log_"+TodayDate.Date());
		if(actualLocationUnassignedLogTitle.equalsIgnoreCase(expectedLocationUnassignedLogTitle) &&
				actualLocationUnassignedLogDescription.equalsIgnoreCase(expectedLocationUnassignedLogDescription) &&
				actualLocationUnassignedLogDate.equalsIgnoreCase(expectedLocationUnassignedLogDate))
			Reporter.log("Location Unassigned log displayed in Material Activity modal.");
		else
			softAssertion.fail("Location Unassigned log not displayed in Material Activity modal.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public void Verify_location_Changed_Log(String materialName, String locationName, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		//Verify the material exists
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		//Get the existing location name.
		String assignedLocationName = getWebElement("Inventory.MaterialDetail.Location").getAttribute("value");
				
		Reporter.log("Navigate to Storage modal from material detail page.");
		//Navigation to storage modal
		getWebElement("Inventory.MaterialDetail.Location").click();
		Thread.sleep(3000);
		
		//Verify location modal exists or not.
		StorageRegularFunctions verifyStorage = new StorageRegularFunctions();
		verifyStorage.VerifyStorageModal();
		
		//Verify storage exits
		boolean locationFound = verifyStorage.VerifyLocation(locationName);
		if(locationFound == false)
		{
			//Creation of new location.
			String temperature = "35";
			StorageRegularFunctions addLocation = new StorageRegularFunctions();
			addLocation.CreateLocation(locationName, temperature);
		}

		//Selecting the location
		StorageRegularFunctions selectLocation = new StorageRegularFunctions();
		selectLocation.SelectLocation(locationName);
		Thread.sleep(1000);
		
		//Updating material location
		getWebElement("Storage.SaveButton").click();
		Thread.sleep(4000);
		
		//Verify the location displayed for location field in material detail page
		String selectedLocationName = getWebElement("Inventory.MaterialDetail.Location").getAttribute("value");
		Assert.assertTrue("Location value not displayed in Material detail page.",selectedLocationName.equals(locationName));
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		getWebElement("Inventory.UpdateMaterialDetailsButton").click();
		Thread.sleep(5000);
		
		String expectedLocationAssignedLogDate = Date_For_MaterialLog();
		getWebElement("Inventory.OkButton").click();
		Thread.sleep(2000);

		//Verify materials page exists or not after updation of material details.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After updation of material details, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("Materials page displayed successfully after updation of storage to material");
		
		String actualMaterialNameAfterUpdationOfStorage = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue("Updated material - "+materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialNameAfterUpdationOfStorage, actualMaterialNameAfterUpdationOfStorage.equals(materialName));
		
		//Navigation to material detail page to Verify the location log.
		MaterialDetailPageNavigation(materialName);
		
		Reporter.log("Navigate to Material Activity modal and verify the location Changed log.");
		
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Location Assigned log
		String actualLocationAssignedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedLocationAssignedLogTitle = "Location Changed by "+labUserName;
		String actualLocationAssignedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedLocationAssignedLogDescription = assignedLocationName+" changed to "+locationName;
		String actualLocationAssignedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		
		Utills.captureScreenshot("Location_Assigned_Log_"+TodayDate.Date());
		if(actualLocationAssignedLogTitle.equalsIgnoreCase(expectedLocationAssignedLogTitle) &&
				actualLocationAssignedLogDescription.equalsIgnoreCase(expectedLocationAssignedLogDescription) &&
				actualLocationAssignedLogDate.equalsIgnoreCase(expectedLocationAssignedLogDate))
			Reporter.log("Location assigned log displayed in Material Activity modal after changing the location.");
		else
			softAssertion.fail("Location assigned log not displayed in Material Activity modal after chaning the location.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public void Verify_Material_Quantity_Updated_Log(String materialName, String materialUOM, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		Thread.sleep(2000);
		
		//Get the material quantity
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		String materialQuantity = getWebElement("Inventory.CardView.EditQuantity").getAttribute("value");
		double quantity = Double.parseDouble(materialQuantity);
		quantity = quantity + 1;
		String updateMaterialQuantity = String.valueOf(quantity);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//Verify the consume quantity value is greater than the existing quantity.
		Assert.assertTrue("Unable to update material quantity as update quantity - "+updateMaterialQuantity+" is not valid.",
				(Double.parseDouble(updateMaterialQuantity) > Double.parseDouble(materialQuantity)
				&& Double.parseDouble(updateMaterialQuantity) > 0));
		
		Reporter.log("Update "+updateMaterialQuantity+" "+materialUOM+" quantity of a material - "+materialName);
		
		//Updating the material quantity
		String expectedMaterialQuantityUpdatedLogDate = Material_Card_View_Edit_Material_Quantity(materialName,updateMaterialQuantity);
		
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Reporter.log("Navigate to Material Activity modal and verify the Material quantity updated log.");
		
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Material Quantity Updated log
		String actualMaterialQuantityUpdatedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedMaterialQuantityUpdatedLogTitle = "Quantity Updated by "+labUserName;
		String actualMaterialQuantityUpdatedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		String actualMaterialQuantityUpdatedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedMaterialQuantityUpdatedLogDescription = null;
		
		if(materialQuantity.contains(".") && updateMaterialQuantity.contains("."))
			expectedMaterialQuantityUpdatedLogDescription = materialQuantity+" "+materialUOM+" updated to "+updateMaterialQuantity+" "+materialUOM;
		else if(!materialQuantity.contains(".") && !updateMaterialQuantity.contains("."))	
			expectedMaterialQuantityUpdatedLogDescription = materialQuantity+".0 "+materialUOM+" updated to "+updateMaterialQuantity+".0 "+materialUOM;
		else if(!materialQuantity.contains("."))
		{
			if(updateMaterialQuantity.contains("."))
				expectedMaterialQuantityUpdatedLogDescription = materialQuantity+".0 "+materialUOM+" updated to "+updateMaterialQuantity+" "+materialUOM;
			else if(!updateMaterialQuantity.contains("."))
				expectedMaterialQuantityUpdatedLogDescription = materialQuantity+".0 "+materialUOM+" updated to "+updateMaterialQuantity+".0 "+materialUOM;
		}
		else if(!updateMaterialQuantity.contains("."))
		{
			if(materialQuantity.contains("."))
				expectedMaterialQuantityUpdatedLogDescription = materialQuantity+" "+materialUOM+" updated to "+updateMaterialQuantity+".0 "+materialUOM;
			else if(!materialQuantity.contains("."))
				expectedMaterialQuantityUpdatedLogDescription = materialQuantity+".0 "+materialUOM+" updated to "+updateMaterialQuantity+".0 "+materialUOM;
		}
		
		Utills.captureScreenshot("Material_Quantity_Updated_Log_"+TodayDate.Date());
		if(actualMaterialQuantityUpdatedLogTitle.equalsIgnoreCase(expectedMaterialQuantityUpdatedLogTitle) &&
				actualMaterialQuantityUpdatedLogDescription.equalsIgnoreCase(expectedMaterialQuantityUpdatedLogDescription) &&
				actualMaterialQuantityUpdatedLogDate.equalsIgnoreCase(expectedMaterialQuantityUpdatedLogDate))
			Reporter.log("Material Quantity updated log displayed in Material Activity modal.");
		else
			softAssertion.fail("Material Quantity updated log not displayed in Material Activity modal.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public String Material_Card_View_Edit_Material_Quantity(String materialName,String updateQuantity)throws Exception
	{
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		
		//Updating the material quantity
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		getWebElement("Inventory.CardView.EditQuantity").click();
		getWebElement("Inventory.CardView.EditQuantity").clear();
		getWebElement("Inventory.CardView.EditQuantity").sendKeys(updateQuantity);
		Thread.sleep(1000);
		String MaterialQuantityUpdatedTime = Date_For_MaterialLog();
		getWebElement("Inventory.CardView.EditQuantity.OkButton").click();
		Thread.sleep(4000);
		
		String quantityFieldValidationMessage =  getWebElement("Inventory.CardView.EditQuantity.SuccessMessage").getText();	
		if(quantityFieldValidationMessage.equals("Success! Material quantity updated!"))
			Reporter.log("Success message displayed successfully in materials page when quantity is updated.");
		else 
			softAssertion.fail("Success message not displayed in materials page when quantity is updated.");
		Utills.captureScreenshot("Materials_page_Edit_Quantity_Success_Message_"+TodayDate.Date());
		
		return MaterialQuantityUpdatedTime;
	}
	
	public void Verify_Material_Quantity_Consumed_Log(String materialName, String materialUOM, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		Thread.sleep(2000);
		
		//Get the material quantity
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		String materialQuantity = getWebElement("Inventory.CardView.EditQuantity").getAttribute("value");
		double quantity = Double.parseDouble(materialQuantity);
		quantity = quantity - 1;
		String consumeMaterialQuantity = String.valueOf(quantity);
		
		//Refreshing the page.
		driver.navigate().refresh();
		Thread.sleep(2000);
				
		//Verify the consume quantity value is greater than the existing quantity.
		Assert.assertTrue("Unable to consume materil quantity as consume quantity is not valid.",
				(Double.parseDouble(consumeMaterialQuantity) < Double.parseDouble(materialQuantity))
				&& Double.parseDouble(consumeMaterialQuantity) > 0);
		Reporter.log("Consume "+consumeMaterialQuantity+" "+materialUOM+" quantity of a material - "+materialName);
		
		//Updating the material quantity
		String expectedMaterialQuantityConsumedLogDate = Material_Card_View_Edit_Material_Quantity(materialName,consumeMaterialQuantity);
		
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Reporter.log("Navigate to Material Activity modal and verify the Material Quantity Consumed log.");
		
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Material Quantity Updated log
		String actualMaterialQuantityConsumedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedMaterialQuantityConsumedLogTitle = "Material Consumed by "+labUserName;
		String actualMaterialQuantityConsumedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		String actualMaterialQuantityConsumedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedMaterialQuantityConsumedLogDescription = null;
		
		if(materialQuantity.contains(".") && consumeMaterialQuantity.contains("."))
			expectedMaterialQuantityConsumedLogDescription = materialQuantity+" "+materialUOM+" reduced to "+consumeMaterialQuantity+" "+materialUOM;
		else if(!materialQuantity.contains(".") && !consumeMaterialQuantity.contains("."))	
			expectedMaterialQuantityConsumedLogDescription = materialQuantity+".0 "+materialUOM+" reduced to "+consumeMaterialQuantity+".0 "+materialUOM;
		else if(!materialQuantity.contains("."))
		{
			if(consumeMaterialQuantity.contains("."))
				expectedMaterialQuantityConsumedLogDescription = materialQuantity+".0 "+materialUOM+" reduced to "+consumeMaterialQuantity+" "+materialUOM;
			else if(!consumeMaterialQuantity.contains("."))
				expectedMaterialQuantityConsumedLogDescription = materialQuantity+".0 "+materialUOM+" reduced to "+consumeMaterialQuantity+".0 "+materialUOM;
		}
		else if(!consumeMaterialQuantity.contains("."))
		{
			if(materialQuantity.contains("."))
				expectedMaterialQuantityConsumedLogDescription = materialQuantity+" "+materialUOM+" reduced to "+consumeMaterialQuantity+".0 "+materialUOM;
			else if(!materialQuantity.contains("."))
				expectedMaterialQuantityConsumedLogDescription = materialQuantity+".0 "+materialUOM+" reduced to "+consumeMaterialQuantity+".0 "+materialUOM;
		}
		
		Utills.captureScreenshot("Material_Quantity_Consumed_Log_"+TodayDate.Date());
		if(actualMaterialQuantityConsumedLogTitle.equalsIgnoreCase(expectedMaterialQuantityConsumedLogTitle) &&
				actualMaterialQuantityConsumedLogDescription.equalsIgnoreCase(expectedMaterialQuantityConsumedLogDescription) &&
				actualMaterialQuantityConsumedLogDate.equalsIgnoreCase(expectedMaterialQuantityConsumedLogDate))
			Reporter.log("Material Quantity Consumed log displayed in Material Activity modal.");
		else
			softAssertion.fail("Material Quantity Consumed log not displayed in Material Activity modal.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public void Verify_Complete_Material_Disposed_Log(String materialName, String materialUOM, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		//Take the materials count, before disposing a material
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the material exists
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		Thread.sleep(2000);
		
		//Get the material quantity
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		String materialQuantity = getWebElement("Inventory.CardView.EditQuantity").getAttribute("value");
		String disposeMaterialQuantity = materialQuantity;
		
		//Navigation to Disposed materials page and take the count of disposed materials.
		DisposedMaterialsPageNavigation();
		
		//Take the count materials of already disposed material
		int materialsCountDisposeListBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to materials page.
		MaterialPageNavigation();
		Thread.sleep(1000);
		
		String expectedMaterialDisposedLogDate = null;
		//Navigation to dispose modal and completely disposing a material
		expectedMaterialDisposedLogDate = CardViewDisposeMaterial(disposeMaterialQuantity,materialQuantity,materialName);
		
		//Refreshing the page.
		driver.navigate().refresh();
		
		//Verify the materials count when a material is disposed.
		Thread.sleep(1000);
		int materialsCountAfterDispose = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		if(materialsCountAfterDispose == (materialsCountBefore-1))
			System.out.println("After material disposal -"+materialName+" the material count is decreased by 1.");
		else 
			softAssertion.fail("After material disposal - "+materialName+" the material count is not decreased.");
	
		//Navigation to Disposed materials page.
		DisposedMaterialsPageNavigation();
		
		//Take the count materials of disposed material
		int materialsCountDisposeListAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the count increased in disposed materials list
		Assert.assertTrue("After material disposal, the materials count is not increased in disposed materials list.",(materialsCountDisposeListAfter == materialsCountDisposeListBefore + 1 ));
		Utills.captureScreenshot("Disposed_Materials_Page_After_Disposal_"+TodayDate.Date());
		
		//Verify the material in disposed material Page
		VerifyDisposedMaterial(materialName);
			
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Reporter.log("Navigate to disposed material detail page to verify Dispose log in Material Activity modal.");		
			
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Material disposed log
		String actualMaterialDisposedLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedMaterialDisposedLogTitle = "Material Disposed by "+labUserName;
		String actualMaterialDisposedLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedMaterialDisposedLogDescription = null;
		if(disposeMaterialQuantity.contains("."))
			expectedMaterialDisposedLogDescription = disposeMaterialQuantity+" "+materialUOM+" of "+materialName+" Disposed.";
		else
			expectedMaterialDisposedLogDescription = disposeMaterialQuantity+".0 "+materialUOM+" of "+materialName+" Disposed.";

		String actualMaterialDisposedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		
		Utills.captureScreenshot("Material_Disposed_Log_"+TodayDate.Date());
		if(actualMaterialDisposedLogTitle.equalsIgnoreCase(expectedMaterialDisposedLogTitle) &&
				actualMaterialDisposedLogDescription.equalsIgnoreCase(expectedMaterialDisposedLogDescription) &&
				actualMaterialDisposedLogDate.equalsIgnoreCase(expectedMaterialDisposedLogDate))
			Reporter.log("Material Disposed log displayed in Material Activity modal of Disposed material detail page.");
		else
			softAssertion.fail("Material Disposed log not displayed in Material Activity modal of Disposed material detail page.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public void Verify_Disposed_Material_Reactivation_Log(String materialName, String materialQuantity, String materialUOM, String labUserName) throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		//Take the materials count, before reactivating a disposed material
		int materialsCountBeforeReactivation = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to Disposed materials page and take the count of disposed materials.
		DisposedMaterialsPageNavigation();
		
		//Take the count materials of already disposed material
		int materialsCountDisposeListBeforeReactivation = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Thread.sleep(1000);
		
		//Verify the material exists
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in disposed materials page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equalsIgnoreCase(materialName));
		Thread.sleep(2000);
		
		//Updating the material quantity
		String expectedDisposedMaterialReactivationLogDate = Update_Quantity_Disposed_Material_Page(materialQuantity);
		
		//Take the count materials disposed material
		int materialsCountDisposeListAfterReactivation = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the count decreased in disposed materials list
		softAssertion.assertTrue((materialsCountDisposeListAfterReactivation == materialsCountDisposeListBeforeReactivation - 1 ),"After material disposal, the materials count is not decreased in disposed materials list.");
		
		//Navigation to materials page.
		MaterialPageNavigation();
		Thread.sleep(1000);
		
		//Verify the materials count when a material is reactivated.
		int materialsCountAfterReactivation = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		softAssertion.assertTrue(materialsCountAfterReactivation == (materialsCountBeforeReactivation+1),"After material - "+materialName+" reactivation the material count is not increased by 1.");
		
		//Navigation to material detail page. Verify material detail page exists or not.
		MaterialDetailPageNavigation(materialName);
		impliciteWait(2);
		
		Reporter.log("Navigate to material detail page to verify Disposed Material Reactivation log in Material Activity modal.");		
			
		//Click on Material log icon
		getWebElement("Inventory.Detail.MaterialLogIcon").click();
		Thread.sleep(3000);
		
		//Verify the material log modal.
		MaterialDetailPage_Verfy_MaterialLogModal();
		
		//Verify Material disposed log
		String actualDisposedMaterialReactivationLogTitle = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogTitle").getText().trim();
		String expectedDisposedMaterialReactivationLogTitle = "Quantity Updated by "+labUserName;
		String actualDisposedMaterialReactivationLogDescription = getWebElement("Inventory.MaterialDetailPage.MaterialLog.LogDescription").getText().trim();
		String expectedDisposedMaterialReactivationLogDescription = null;
		if(materialQuantity.contains("."))
			expectedDisposedMaterialReactivationLogDescription = "0.0 "+materialUOM+" updated to "+materialQuantity+" "+materialUOM;
		else
			expectedDisposedMaterialReactivationLogDescription = "0.0 "+materialUOM+" updated to "+materialQuantity+".0 "+materialUOM;
		String actualMaterialDisposedLogDate = getWebElement("Inventory.MaterialDetailPage.MaterialLog.Date").getText().trim();
		
		Utills.captureScreenshot("Material_Disposed_Log_"+TodayDate.Date());
		if(actualDisposedMaterialReactivationLogTitle.equalsIgnoreCase(expectedDisposedMaterialReactivationLogTitle) &&
				actualDisposedMaterialReactivationLogDescription.equalsIgnoreCase(expectedDisposedMaterialReactivationLogDescription) &&
				actualMaterialDisposedLogDate.equalsIgnoreCase(expectedDisposedMaterialReactivationLogDate))
			Reporter.log("Disposed Material Reactivation log displayed in Material Activity modal.");
		else
			softAssertion.fail("Disposed Material Reactivation log not displayed in Material Activity modal.");
		
		//close the modal
		getWebElement("Inventory.MaterialDetailPage.MaterialLog.CloseIcon").click();
		Thread.sleep(3000);
		
		//Refreshing the page.
		driver.navigate().refresh();
	}
	
	public String Update_Quantity_Disposed_Material_Page(String materialQuantity) throws Exception
	{
		//Updating the disposed material quantity in card view page
		Reporter.log("Updating the material quantity of Disposed material.");
		getWebElement("Inventory.CardView.EditQuantityIcon").click();
		Thread.sleep(2000);
		getWebElement("Inventory.CardView.EditQuantity").click();
		getWebElement("Inventory.CardView.EditQuantity").clear();
		getWebElement("Inventory.CardView.EditQuantity").sendKeys(materialQuantity);
		Thread.sleep(1000);
		String disposedMaterialReactivationDate = Date_For_MaterialLog();
		getWebElement("Inventory.CardView.EditQuantity.OkButton").click();
		Thread.sleep(4000);
		
		//Verification of success message after updating material quantity.
		String quantityFieldValidationMessage =  getWebElement("Inventory.CardView.EditQuantity.SuccessMessage").getText();	
		if(quantityFieldValidationMessage.equalsIgnoreCase("Success! Material Reactivated! Moved to active materials."))
			Reporter.log("Success message displayed successfully in disposed materials page for quantity updation of disposed material.");
		else 
			softAssertion.fail("Success message not displayed in disposed materials page for quantity updation of disposed material.");
		Utills.captureScreenshot("Materials_page_Edit_Quantity_Success_Message_"+TodayDate.Date());
	
		return disposedMaterialReactivationDate;
	}
	
	public String Date() throws Exception
	{
		DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String Date = DateFormat.format(now);
		Date = Date.replaceAll("[^a-zA-Z0-9_-]", "");
		return Date;
	}
	
	public void Card_View_Delete_Material(String materialName) throws Exception
	{
		//Verify the material exists
		String actualMaterialNameBeforeDeletion = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed in materials page. Actual Material Displayed is - "+actualMaterialNameBeforeDeletion, actualMaterialNameBeforeDeletion.equalsIgnoreCase(materialName));
		Thread.sleep(2000);
		
		//Takes the count of materials
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Navigation to dispose modal
		Reporter.log("Navigation to dispose modal to delete a material - '"+materialName+"' from card view page.");
		getWebElement("Inventory.CardView.DisposeMaterial").click();
		Thread.sleep(3000);
		
		//Verify dispose modal exist or not
		VerifyDisposeModal_CardView(materialName);
			
		//Deletes a material
		getWebElement("Inventory.CardView.DisposeModal.DeleteRadioButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.DisposeButton").click();
		impliciteWait(2);
			
		//Verification of Success message after deletion of material.
		String ActualMaterialDeletionSuccessMessage = getWebElement("Inventory.DeleteMaterialSuccessMessage").getText();
		String ExpectedMaterialDeletionSuccessMessage = "Material Deleted";
		if(ActualMaterialDeletionSuccessMessage.equals(ExpectedMaterialDeletionSuccessMessage))
			Reporter.log("After material deletion, the Success Message displayed successfully as - "+ActualMaterialDeletionSuccessMessage);
		else 
			softAssertion.fail("After material deletion, the Success Message not displayed.");
		Utills.captureScreenshot("Material_Delete_Success_Message_F_"+TodayDate.Date());
		
		//Verify materials page exists or not after disposal
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After material deletion, the materials page not displayed", materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Get the materials count after deletion of a material.
		int materialsCountAfterDelete = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//Verify the materials count after deletion of a material
		Assert.assertTrue("After deletion of material - '"+materialName+"' the materials count is not decreased.",materialsCountAfterDelete == (materialsCountBefore-1));
		Reporter.log("Material - '"+materialName+"' deleted successfully from materials Card view Page.");		
	}
	
	public void Detail_Page_Delete_Material(String materialName) throws Exception
	{
		//Navigation to dispose modal to delete a material
		Reporter.log("Navigation to dispose modal to delete a material - '"+materialName+"' from material detail page.");
		getWebElement("Inventory.Detail.Dispose").click();
		Thread.sleep(3000);
		
		//Verify dispose modal exist or not
		VerifyDisposeModal_DetailPage(materialName);
		
		//Deletion of a material
		getWebElement("Inventory.DeleteRadioButton").click();
		Thread.sleep(1000);
		getWebElement("Inventory.DisposeButton").click();
		impliciteWait(2);
		
		//Verification of Success message after deletion of material.
		Utills.captureScreenshot("Material_Delete_Success_Message_"+TodayDate.Date());
		String ActualMaterialDeletionSuccessMessage = getWebElement("Inventory.DeleteMaterialSuccessMessage").getText();
		String ExpectedMaterialDeletionSuccessMessage = "Material Deleted";
		if(ActualMaterialDeletionSuccessMessage.equals(ExpectedMaterialDeletionSuccessMessage))
			Reporter.log("After material deletion, the Success Message displayed as - "+ActualMaterialDeletionSuccessMessage);
		else 
			softAssertion.fail("After material deletion, the message displayed as - "+ActualMaterialDeletionSuccessMessage);
		
		//Verify materials page exists or not after material deletion
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After material deletion, the materials page not displayed", materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
	}
	
	public void VerifySDS(String catalogNumber) throws Exception
	{
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Reporter.log("A new tab opened successfully when clicked on SDS icon for Catalog Number - "+catalogNumber);
				
		//Verify the tab name
		Reporter.log("-------------------------------------------------------------------------------------------------------------------------");
		Reporter.log("Verify Page title contains the Catalog Number");
		String pageTitle = driver.getTitle();
		if(pageTitle.equalsIgnoreCase("MSDS - "+catalogNumber))
			Reporter.log("Page title displayed successfully as - '"+pageTitle+"'.");
		else
			softAssertion.fail("Page title not displayed with expected Catalog Number - "+catalogNumber+". The Page title displayed as -"+pageTitle);
		
		//verify the URL contains the catalog number
		Reporter.log("-------------------------------------------------------------------------------------------------------------------------");
		Reporter.log("Verify URL contains the Catalog Number");
		String expectedURLCatalogNumber = "productNumber="+catalogNumber;
		String actualPageURL = driver.getCurrentUrl();
		if(actualPageURL.contains(expectedURLCatalogNumber))
			Reporter.log("URL contains the catalog number - "+catalogNumber);
		else
			softAssertion.fail("URL doesnot contains Catalog Number. The URL displayed as - "+actualPageURL);
		
		//Verify bread-crumb displayed with catalog number.
		Reporter.log("-------------------------------------------------------------------------------------------------------------------------");
		Reporter.log("Verify Breadcrumb contains the Catalog Number");
		String actualBreadcrumb = getWebElement("SigmaAldrich.SafetyDataSheet.BreadCrumb").getText().trim();
		if(actualBreadcrumb.equalsIgnoreCase(catalogNumber))
			Reporter.log("catalog Number - '"+catalogNumber+"' displayed in the Breadcrumb of the Page.");
		else
			softAssertion.fail("catalog Number - '"+catalogNumber+"' not displayed in the Breadcrumb of the Page.");
		
		//Verify the Safety Data page displayed.
		String actualPageName = getWebElement("SigmaAldrich.SafetyDataSheet.PageName").getText().trim();
		String expectedPageName = "Safety Data Sheet";
		if(actualPageName.equalsIgnoreCase(expectedPageName))
			Reporter.log("Safety Data Sheet page displayed successfully for Product - "+catalogNumber);
		else
			softAssertion.fail("Safety Data Sheet page not displayed.");
		
		Thread.sleep(2000);
		Utills.captureScreenshot("Safety_Data_Sheet_"+TodayDate.Date());
		driver.close();
		Reporter.log("-------------------------------------------------------------------------------------------------------------------------");
		
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
		Thread.sleep(1000);
	}
	
	public void VerifySDSNotFound(String catalogNumber) throws Exception
	{
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Reporter.log("A new tab opened successfully when clicked on SDS icon for Catalog Number - "+catalogNumber);
				
		//Verify the tab name
		Reporter.log("--------------------------------------------------------------------------------------------------------------------------------------------");
		Reporter.log("Verify the Page title for the product which does not have SDS file.");
		String pageTitle = driver.getTitle().trim();
		if(pageTitle.equalsIgnoreCase("MSDS Request | Sigma-Aldrich"))
			Reporter.log("Page title displayed successfully as - '"+pageTitle+"'.");
		else
			softAssertion.fail("Page title not displayed with expected Catalog Number - "+catalogNumber+". The Page title displayed as -"+pageTitle);
		
		//verify the URL contains the catalog number
		Reporter.log("--------------------------------------------------------------------------------------------------------------------------------------------");
		Reporter.log("Verify the page directs to msds-request.html when safety data sheet does not exists for the product on Sigma-Aldrich Site.");
		String expectedURL = "http://www.sigmaaldrich.com/site-level/special-forms/msds-request.html";
		String actualPageURL = driver.getCurrentUrl().trim();
		if(actualPageURL.equalsIgnoreCase(expectedURL))
			Reporter.log("The page is redirected to msds-request.html when there is no Safety Data Sheet.");
		else
			softAssertion.fail("URL displayed is not proper. The URL displayed as - "+actualPageURL);
		
		//Verify bread-crumb displayed with catalog number.
		Reporter.log("--------------------------------------------------------------------------------------------------------------------------------------------");
		Reporter.log("Verify the message when there is no safety data sheet on Sigma Aldrich Site for the product.");
		String actualSDSNotFoundMessage = getWebElement("SigmaAldrich.SafetyDataSheet.SDSNotFound").getText().trim();
		String expectedSDSNotFoundMessage = "SDS Not Found";
		if(actualSDSNotFoundMessage.equalsIgnoreCase(expectedSDSNotFoundMessage))
			Reporter.log("'"+actualSDSNotFoundMessage+"' message displayed Succesfully for the product - "+catalogNumber);
		else
			softAssertion.fail("Expected message not displayed. The actual message displayed as - "+actualSDSNotFoundMessage);
		
		Thread.sleep(2000);
		Utills.captureScreenshot("Safety_Data_Sheet_"+TodayDate.Date());
		driver.close();
		Reporter.log("--------------------------------------------------------------------------------------------------------------------------------------------");
		
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
		Thread.sleep(1000);
	}
	
	public void Add_Third_Party_Vendor_Material_WithSDS(int rowNumber) throws Exception
	{
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
		String casNumber = ExcelUtils.getCellData(rowNumber, 6);
		String lotNumber = ExcelUtils.getCellData(rowNumber, 7);
		String manufactredDate = ExcelUtils.getCellData(rowNumber, 8);
		String expirationDate = ExcelUtils.getCellData(rowNumber, 9);
		String brand = ExcelUtils.getCellData(rowNumber, 10);
		String url = ExcelUtils.getCellData(rowNumber, 11);
		String boilingPoint = ExcelUtils.getCellData(rowNumber, 12);
		String meltingPoint = ExcelUtils.getCellData(rowNumber, 13);
		String flashingPoint = ExcelUtils.getCellData(rowNumber, 14);
		String minimumCount = ExcelUtils.getCellData(rowNumber, 15);
		String maximumCount = ExcelUtils.getCellData(rowNumber, 16);
		String materialType = ExcelUtils.getCellData(rowNumber, 17);
		String description = ExcelUtils.getCellData(rowNumber, 18);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		
		if(fileName.contains("PDF"))
			fileType = "PDF";
		else if(fileName.contains("Image"))
			fileType = "Image";
		else if(fileName.contains("Excel"))
			fileType = "Excel";
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Entering the material details in Add Material Modal
		Reporter.log("Enter the material details and add it to Inventory");
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMateriName").sendKeys(materialName);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		getWebElement("Inventory.Quantity").click();
		Thread.sleep(1000);
		getWebElement("Inventory.Quantity").sendKeys(quantity);
		
		//Verify the provided UOM exists in list
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.UOM",uom) == true)
			SelectList.SelectByValue("Inventory.UOM",uom);
		else
			Assert.fail("The UOM - "+uom+" does not exist");
		Thread.sleep(1000);
		
		//Check whether the vendor exists or not.
		if(SelectList.VerifySelectList("Inventory.Vendor",vendorName) == true)
		{
			SelectList.SelectByVisibleText("Inventory.Vendor",vendorName);
			Thread.sleep(1000);
		}
		
		//Creates a new vendor
		else
		{
			Reporter.log("The Vendor - "+vendorName+" provided in test data does not exist. A new vendor is created");
			SelectList.SelectByVisibleText("Inventory.Vendor","Add Vendor");
			Thread.sleep(1000);
			getWebElement("Inventory.AddVendor").sendKeys(vendorName);
			Thread.sleep(1000);
		}
		
		//Verify the provided material type exists in list
		if(SelectList.VerifySelectList("Inventory.MaterialType",materialType) == true)
			SelectList.SelectByVisibleText("Inventory.MaterialType",materialType);
		else
			softAssertion.fail("The Material Type - "+materialType+" does not exist");
		
		
		Reporter.log("Upload the "+fileType+" SDS file from Add Material Modal");
		
		//Clicks on upload button to upload SDS file
		getWebElement("Inventory.AddMaterial.UploadSDS").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("SDS_File_Upload_Modal_"+TodayDate.Date());
		
		//Upload the SDS file
		Add_Material_Page_Upload_SDS(fileName, fileType);
		
		//Clicks on "Other Details" tab.
		Thread.sleep(3000);
		getWebElement("Inventory.OtherDetailsTab").click();
		impliciteWait(2);
		
		getWebElement("Inventory.AddCasNumber").click();
		getWebElement("Inventory.AddCasNumber").sendKeys(casNumber);
		getWebElement("Inventory.AddLotNumber").sendKeys(lotNumber);
		getWebElement("Inventory.AddManufacturedDate").sendKeys(manufactredDate);
		getWebElement("Inventory.AddExpiredDate").sendKeys(expirationDate);
		getWebElement("Inventory.AddBrand").sendKeys(brand);
		getWebElement("Inventory.AddURL").sendKeys(url);
		getWebElement("Inventory.AddBoilingPoint").sendKeys(boilingPoint);
		getWebElement("Inventory.AddMeltingPoint").sendKeys(meltingPoint);
		getWebElement("Inventory.FlashingPoint").sendKeys(flashingPoint);
		getWebElement("Inventory.MinimumCount").sendKeys(minimumCount);
		getWebElement("Inventory.MaximumCount").sendKeys(maximumCount);
		Thread.sleep(1000);
		getWebElement("Inventory.Description").click();
		getWebElement("Inventory.Description").sendKeys(description);
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(3000);
		impliciteWait(3);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.", addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		
		//Clicks on Ok button.
		getWebElement("Inventory.OkButton").click();
		impliciteWait(5);
		Thread.sleep(1000);
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());

		//Verification of Success message after addition of new material.
		Thread.sleep(2000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			System.out.println("Success Message displayed as - "+ActualSuccessMessage+" after addition of new material");
		else 
			softAssertion.fail("Success Message not displayed after addition of new material.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material, the materials page not displayed", materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
			
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(MaterialsCountAfter == (materialsCountBefore+1))
			System.out.println("After addition of new material the material count is increased by "+(MaterialsCountAfter-materialsCountBefore));
		else 
			softAssertion.fail("After addition of new material the material count is not increased.");
		
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equals(materialName));
		Reporter.log("Material - '"+materialName+"' created and displayed successfully in materials page.");
	}
	
	public void Add_Material_Page_Upload_SDS(String fileName, String fileType) throws Exception
	{
		String fileUploadPath = System.getProperty("user.dir")+"\\src\\testData\\Inventory_Attachments\\"+fileName;
		Runtime.getRuntime().exec(fileUploadPath);
		Thread.sleep(5000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Screen_"+TodayDate.Date());
		
		String expectedSDSSuccessMessage = "Success! File uploaded Successfully!";
		String actualSDSSuccessMessage = getWebElement("Inventory.AddMaterial.SDSUpload.Message").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Success message displayed successfully after SDS file is uploaded in Add Material Modal");
		else
			softAssertion.fail("Success Message not displayed as expected. The message displayed as - "+actualSDSSuccessMessage);
		
		//Verify the added file uploaded successfully
		Thread.sleep(5000);
		String actualSDSUploadedFileName = getWebElement("Inventory.AddMaterial.SDSUpload.FileName").getText().trim();
		String expectedSDSUploadedFileName = fileName;
		if(fileName.contains("PDF"))
		{	
			expectedSDSUploadedFileName = expectedSDSUploadedFileName+".pdf";
			if(expectedSDSUploadedFileName.equalsIgnoreCase(actualSDSUploadedFileName))
				Reporter.log(fileType+" File uploaded and displayed successfully in Add Material Modal.");
			else
				softAssertion.fail("Invalid file uploaded. The file name uploaded is - "+actualSDSUploadedFileName);
		}
		else if(fileName.contains("Image"))
		{
			expectedSDSUploadedFileName = expectedSDSUploadedFileName+".png";
			if(expectedSDSUploadedFileName.equalsIgnoreCase(actualSDSUploadedFileName))
				Reporter.log(fileType+" File uploaded and displayed successfully in Add Material Modal.");
			else
				softAssertion.fail("Invalid file uploaded. The file name uploaded is - "+actualSDSUploadedFileName);
		}
		else if(fileName.contains("Excel"))
		{
			expectedSDSUploadedFileName = expectedSDSUploadedFileName+".xls";
			if(expectedSDSUploadedFileName.equalsIgnoreCase(actualSDSUploadedFileName))
				Reporter.log(fileType+" File uploaded and displayed successfully in Add Material Modal.");
			else
				softAssertion.fail("Invalid file uploaded. The file name uploaded is - "+actualSDSUploadedFileName);
		}
	}
	
	public void ThirdParty_Vendor_Material_Verify_PDF_Image_Type_SDS(String materialName, String pageName, String fileType) throws Exception
	{
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		try
		{
			driver.switchTo().window(tabs.get(1));
			Reporter.log("A new tab opened successfully when clicked on SDS icon for material - "+materialName);
				
			//verify the URL contains of an SDS tab
			Reporter.log("Verify URL When "+fileType+" type SDS file is opened for third party vendor material");
			String expectedURL = null;
			String actualPageURL = driver.getCurrentUrl();
			if(pageName.equalsIgnoreCase("materials page"))
				expectedURL = "https://ae1c-bioinfocloud-slb01/devtest/faces/private/inventory/material/material.xhtml";
			else if(pageName.equalsIgnoreCase("material detail page"))
				expectedURL = "https://ae1c-bioinfocloud-slb01/devtest/faces/private/inventory/material/material-detail.xhtml";	
		
			if(actualPageURL.equalsIgnoreCase(expectedURL))
				Reporter.log("Proper URL displayed when "+fileType+" type SDS file opened for third party vendor material.");
			else
				softAssertion.fail("Invalid URL displayed when "+fileType+" type SDS file opened for third party vendor material.");

			//Verify the Safety Data page displayed.
			Reporter.log("Safety Data Sheet page displayed successfully for material - "+materialName);
			Thread.sleep(2000);
			Utills.captureScreenshot("Safety_Data_Sheet_"+TodayDate.Date());
			driver.close();
			
			ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tab.get(0));
			Thread.sleep(1000);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			softAssertion.fail("Safety Data Sheet page not displayed.");
		}
	}
	
	public void ThirdParty_Vendor_Material_Verify_Download_File_Type_SDS(String materialName, String fileName, String fileType) throws Exception
	{
		//Verify the Safety Data page displayed in the path.
		String filePath = System.getProperty("user.home")+"\\Downloads";
		File file = new File(filePath+"\\"+fileName);
		if(file.exists()) 
            Reporter.log("Safety Data Sheet downloaded successfully When "+fileType+" type SDS file is viewed of material - "+materialName);
		else
            softAssertion.fail("Safety Data Sheet not downloaded When "+fileType+" type SDS file is viewed of material - "+materialName);
	}
	
	public void Delete_File(String fileName) throws Exception
	{
        String filePath = System.getProperty("user.home")+"\\Downloads";
        //Delete the file
        File file = new File(filePath+"\\"+fileName);
        try 
        {
            if (file.exists()) 
                file.delete();
        } 
        catch (Exception e)  
        {
            e.printStackTrace();
        }
    }
	
	public void Material_Detail_Upload_SDS_File(String fileName) throws Exception
	{
		//Click on upload icon
		Thread.sleep(2000);
		getWebElement("Inventory.DetailPage.UploadSDS").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("SDS_File_Upload_Modal_"+TodayDate.Date());
		
		String fileUploadPath = System.getProperty("user.dir")+"\\src\\testData\\Inventory_Attachments\\"+fileName;
		Runtime.getRuntime().exec(fileUploadPath);
		Thread.sleep(4000);
	}
	
	public void Add_Material_Update_Existing_Material_SDS_File(String materialName, String catalogNumber, String fileName) throws Exception
	{
		String fileType = null;
		
		if(fileName.contains("PDF"))
			fileType = "PDF";
		else if(fileName.contains("Image"))
			fileType = "Image";
		else if(fileName.contains("Excel"))
			fileType = "Excel";
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Click on Add Material to add existing third party vendor material for updating existing SDS file.");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material page displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
 
		//Verify Auto Complete modal exist
		Reporter.log("Enter the existing third party vendor material details and add it to Inventory");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		Thread.sleep(5000);
			
		WebElement verifyAutoCompleteTable = getWebElement("Inventory.AddMaterial.VerifyAutoCompleteTable");
		Assert.assertTrue("Auto complete modal not displayed for the entered catalog number -"+catalogNumber,verifyAutoCompleteTable.isDisplayed());
		Utills.captureScreenshot("Add_Material_Auto_Complete_Modal_"+TodayDate.Date());
		
		//Entering the material details
		WebElement materialFound = null;
		try
		{
			materialFound = driver.findElement(By.xpath("//tr[@data-item-group='Lab Catalog'][1]/td[contains(text(),'"+catalogNumber+"')]"));
			materialFound.isDisplayed();
			Reporter.log("Material - "+materialName+" displayed in auto suggestion list.");
		}
		catch(NoSuchElementException e) 
		{
			Assert.fail("Material - "+materialName+" does not exist in auto complete modal");
			Utills.captureScreenshot("Add_Material_Auto_Complete_Modal_Fail"+TodayDate.Date());	
		}
	
		materialFound.click();
		Thread.sleep(5000);
		
		Reporter.log("Upload the "+fileType+" SDS file from Add Material Modal to update SDS of an existing material");
		
		//Clicks on upload button to upload SDS file
		getWebElement("Inventory.AddMaterial.UploadSDS").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("SDS_File_Upload_Modal_"+TodayDate.Date());
		
		String fileUploadPath = System.getProperty("user.dir")+"\\src\\testData\\Inventory_Attachments\\"+fileName;
		Runtime.getRuntime().exec(fileUploadPath);
		Thread.sleep(4000);
		
		//Verify the success message after SDS file upload
		Utills.captureScreenshot("SDS_File_Uploaded_Screen_"+TodayDate.Date());
		
		String expectedSDSSuccessMessage = "Success! File uploaded Successfully!";
		String actualSDSSuccessMessage = getWebElement("Inventory.AddMaterial.SDSUpload.Message").getText().trim();
		if(expectedSDSSuccessMessage.equalsIgnoreCase(actualSDSSuccessMessage))
			Reporter.log("Success message displayed successfully after SDS file is uploaded in Add Material Modal");
		else
			softAssertion.fail("Success Message not displayed as expected. The message displayed as - "+actualSDSSuccessMessage);
		
		//Verify the added file uploaded successfully
		Thread.sleep(5000);
		String actualSDSUploadedFileName = getWebElement("Inventory.AddMaterial.SDSUpload.FileName").getText().trim();
		String expectedSDSUploadedFileName = fileName;
		if(fileName.contains("PDF"))
		{	
			expectedSDSUploadedFileName = expectedSDSUploadedFileName+".pdf";
			if(expectedSDSUploadedFileName.equalsIgnoreCase(actualSDSUploadedFileName))
				Reporter.log(fileType+" File uploaded and displayed successfully in Add Material Modal.");
			else
				softAssertion.fail("Invalid file uploaded. The file name uploaded is - "+actualSDSUploadedFileName);
		}
		else if(fileName.contains("Image"))
		{
			expectedSDSUploadedFileName = expectedSDSUploadedFileName+".png";
			if(expectedSDSUploadedFileName.equalsIgnoreCase(actualSDSUploadedFileName))
				Reporter.log(fileType+" File uploaded and displayed successfully in Add Material Modal.");
			else
				softAssertion.fail("Invalid file uploaded. The file name uploaded is - "+actualSDSUploadedFileName);
		}
		else if(fileName.contains("Excel"))
		{
			expectedSDSUploadedFileName = expectedSDSUploadedFileName+".xls";
			if(expectedSDSUploadedFileName.equalsIgnoreCase(actualSDSUploadedFileName))
				Reporter.log(fileType+" File uploaded and displayed successfully in Add Material Modal.");
			else
				softAssertion.fail("Invalid file uploaded. The file name uploaded is - "+actualSDSUploadedFileName);
		}
		
		//Adding material to Inventory
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(3000);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.",addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());
		getWebElement("Inventory.OkButton").click();
		impliciteWait(5);
	
		//Verification of Success message after addition of new material.
		Thread.sleep(2000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		softAssertion.assertTrue(ActualSuccessMessage.equals(ExpectedSuccessMessage), "Success Message not displayed after addition of new material.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
				
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material, the materials page not displayed", materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		softAssertion.assertTrue(MaterialsCountAfter == (materialsCountBefore+1),"After addition of new material the material count is not increased.");
		
		//Verify the material displayed at the top of the page.
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equals(materialName));
		Reporter.log("Material - '"+materialName+"' created and displayed successfully in materials page.");
	}
	
	public void Add_Material_Page_Delete_SDS_File() throws Exception
	{
		//Delete the SDS file
		getWebElement("Inventory.AddMaterial.SDS.Delete.Icon").click();
		Thread.sleep(4000);
		
		//Verify the message after deletion
		String actualSDSDeleteInfoMessage = getWebElement("Inventory.AddMaterial.SDS.Delete.Info.Message").getText().trim();
		String expectedSDSDeleteInfoMessage = "Info! File deleted";
		if(expectedSDSDeleteInfoMessage.equalsIgnoreCase(actualSDSDeleteInfoMessage))
			Reporter.log("SDS file deletion message displayed successfully after deletion of SDS file in Add Material Modal.");
		else
			softAssertion.fail("SDS file deletion message not displayed. The message displayed as - "+actualSDSDeleteInfoMessage);
	
		//Close the info message
		getWebElement("Inventory.AddMaterial.SDS.Delete.Info.Message.Close.Icon").click();
	}
	
	public void Add_Third_Party_Vendor_Material_By_Deleting_Attached_SDS(int rowNumber) throws Exception
	{
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
		String fileName = ExcelUtils.getCellData(rowNumber, 20);
		String fileType = null;
		
		if(fileName.contains("PDF"))
			fileType = "PDF";
		else if(fileName.contains("Image"))
			fileType = "Image";
		else if(fileName.contains("Excel"))
			fileType = "Excel";
		
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Entering the material details in Add Material Modal
		Reporter.log("Enter the material details and add it to Inventory");
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMateriName").sendKeys(materialName);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		getWebElement("Inventory.Quantity").click();
		Thread.sleep(1000);
		getWebElement("Inventory.Quantity").sendKeys(quantity);
		
		//Verify the provided UOM exists in list
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.UOM",uom) == true)
			SelectList.SelectByValue("Inventory.UOM",uom);
		else
			Assert.fail("The UOM - "+uom+" does not exist");
		Thread.sleep(1000);
		
		//Check whether the vendor exists or not.
		if(SelectList.VerifySelectList("Inventory.Vendor",vendorName) == true)
		{
			SelectList.SelectByVisibleText("Inventory.Vendor",vendorName);
			Thread.sleep(1000);
		}
		
		//Creates a new vendor
		else
		{
			Reporter.log("The Vendor - "+vendorName+" provided in test data does not exist. A new vendor is created");
			SelectList.SelectByVisibleText("Inventory.Vendor","Add Vendor");
			Thread.sleep(1000);
			getWebElement("Inventory.AddVendor").sendKeys(vendorName);
			Thread.sleep(1000);
		}
		
		Reporter.log("Upload the "+fileType+" SDS file from Add Material Modal");
		
		//Clicks on upload button to upload SDS file
		getWebElement("Inventory.AddMaterial.UploadSDS").click();
		Thread.sleep(1000);
		Utills.captureScreenshot("SDS_File_Upload_Modal_"+TodayDate.Date());
		
		//Upload the SDS file
		Add_Material_Page_Upload_SDS(fileName, fileType);
		
		//Delete the SDS file
		Add_Material_Page_Delete_SDS_File();
		
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(3000);
		impliciteWait(3);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.", addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		
		//Clicks on Ok button.
		getWebElement("Inventory.OkButton").click();
		impliciteWait(5);
		Thread.sleep(1000);
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());

		//Verification of Success message after addition of new material.
		Thread.sleep(2000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			System.out.println("Success Message displayed as - "+ActualSuccessMessage+" after addition of new material");
		else 
			softAssertion.fail("Success Message not displayed after addition of new material.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material, the materials page not displayed", materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
			
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(MaterialsCountAfter == (materialsCountBefore+1))
			System.out.println("After addition of new material the material count is increased by "+(MaterialsCountAfter-materialsCountBefore));
		else 
			softAssertion.fail("After addition of new material the material count is not increased.");
		
		String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
		Assert.assertTrue(materialName+" not displayed at the top of the page. Actual Material Displayed is - "+actualMaterialName, actualMaterialName.equals(materialName));
		Reporter.log("Material - '"+materialName+"' created and displayed successfully in materials page.");
	}
	
	public String Material_Detail_Page_File_Upload(String fileName) throws Exception
	{
		//Click on upload icon
		getWebElement("Inventory.DetailPage.Attach.File.Icon").click();
		Thread.sleep(2000);
		Utills.captureScreenshot("File_Upload_Modal_"+TodayDate.Date());
		
		String fileUploadPath = System.getProperty("user.dir")+"\\src\\testData\\Inventory_Attachments\\"+fileName;
		System.out.println("fileUploadPath"+fileUploadPath);
		Runtime.getRuntime().exec(fileUploadPath);
		Thread.sleep(5000);
		
		String fileUploadedDateAndTime = Date_Material_File_Attachment();
		return fileUploadedDateAndTime;
	}
	
	public void Material_Detail_Page_Delete_Attached_File() throws Exception
	{
		//Clicks on delete icon
		getWebElement("Inventory.DetailPage.Attachments.Section.Delete.Attachment.Icon").click();
		Thread.sleep(3000);
		
		//Verify the confirmation modal
		if(getWebElement("Inventory.DetailPage.Delete.Attachment.Confirmation.Modal.Title").isDisplayed())
		{
			String actualDeleteFileConfirmationMessage = getWebElement("Inventory.DetailPage.Delete.Attachment.Confirmation.Message").getText().trim();
			String expectedDeleteFileConfirmationMessage = "Are you sure you wish to delete this File?";
			if(expectedDeleteFileConfirmationMessage.equalsIgnoreCase(actualDeleteFileConfirmationMessage))
				Reporter.log("File Deletion confirmation modal displayed successfully.");
			else
				softAssertion.fail("The deletion message displayed is not proper in File deletion confirmation modal.");
		}
		else
			Assert.fail("File Deletion Confirmation modal not displayed.");
		
		//Click on Yes button
		getWebElement("Inventory.DetailPage.Delete.Attachment.Confirmation.Modal.Yes.Button").click();
		Thread.sleep(5000);
		
		//Verification of file deletion success message
		Utills.captureScreenshot("Detail_Page_File_Deletion_Success_Message_"+TodayDate.Date());
		String expectedFileDeletionSuccessMessage = "Success! File Deleted Successfully!";
		String actualFileDeletionSuccessMessage = getWebElement("Inventory.DetailPage.UpdateProduct.SDS.SuccessMessage").getText().trim();
		if(expectedFileDeletionSuccessMessage.equalsIgnoreCase(actualFileDeletionSuccessMessage))
			Reporter.log("'"+actualFileDeletionSuccessMessage+"' message displayed successfully when a attached file is deleted.");
		else
			softAssertion.fail("The expected message not displayed when a attached file is deleted.. The actual message displayed is - "+actualFileDeletionSuccessMessage);
	}
	
	public void Material_Detail_Page_File_Upload_Success_Message_Verification() throws Exception
	{
		Utills.captureScreenshot("Detail_Page_File_Upload_Message_"+TodayDate.Date());
		String expectedFileUploadSuccessMessage = "Success!Attachment Uploaded Successfully!";
		String actualFileUploadSuccessMessage = getWebElement("Inventory.DetailPage.UpdateProduct.SDS.SuccessMessage").getText().trim();
		if(expectedFileUploadSuccessMessage.equalsIgnoreCase(actualFileUploadSuccessMessage))
			Reporter.log("'"+actualFileUploadSuccessMessage+"' message displayed successfully when a file attached to a material");
		else
			softAssertion.fail("The expected message not displayed when a file attached to a material. The actual message displayed is - "+actualFileUploadSuccessMessage);
	}
	
	public void Material_Detail_Page_Verify_Uploaded_FileName(String fileName, String userName, String dateAndTime, String materialName, String fileType) throws Exception
	{
		String actualUploadedFileName = getWebElement("Inventory.DetailPage.Attachments.FileName").getAttribute("title").trim();
		String actualFileUploadedUserName = getWebElement("Inventory.DetailPage.Attachments.FileUploaded.UserName").getText().trim();
		String actualFileUploadedDateAndTime = getWebElement("Inventory.DetailPage.Attachments.FileUploaded.DateAndTime").getText().trim();
		String expectedDateAndTime = Date_Material_File_Attachment();
		String extension = null;
		
		if(fileType.equalsIgnoreCase("PDF"))
			extension = ".pdf";
		else if(fileType.equalsIgnoreCase("Image"))
			extension = ".png";
		else if(fileType.equalsIgnoreCase("Excel"))
			extension = ".xls";
		
		String expectedFileName = fileName+extension;
		
		if(expectedFileName.equalsIgnoreCase(actualUploadedFileName)
				&& userName.equalsIgnoreCase(actualFileUploadedUserName)
				&& dateAndTime.equalsIgnoreCase(actualFileUploadedDateAndTime))
					Reporter.log("File uploaded and displayed successfully under Attachments Section of material - "+materialName);
		
		else if(expectedFileName.equalsIgnoreCase(actualUploadedFileName)
				&& userName.equalsIgnoreCase(actualFileUploadedUserName)
				&& expectedDateAndTime.equalsIgnoreCase(actualFileUploadedDateAndTime))
			Reporter.log("File uploaded and displayed successfully under Attachments Section of material - "+materialName);
		
		else
			Assert.fail("File not attached to a material. "
					+"\n The file name displayed as - "+actualUploadedFileName
					+"\n The file uploaded user name displayed as - "+actualFileUploadedUserName
					+"\n The file uploaded date displayed as - "+actualFileUploadedDateAndTime
					+"\n The expected file name is - "+expectedFileName
					+"\n The expected logged in user name is - "+userName
					+"\n The expected uploaded data and time is - "+dateAndTime);
	}
	
	public String Date_Material_File_Attachment() throws Exception
	{
		DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
		LocalDateTime now = LocalDateTime.now();
		String Date = DateFormat.format(now);
        
        return Date;
	}
	
	public void Material_Detail_View_PDF_Image_Type_Attachments(String materialName, String fileType) throws Exception
	{
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		try
		{
			driver.switchTo().window(tabs.get(1));
			Reporter.log("A new tab opened successfully when attached "+fileType+" type file is viewed from material detail page of material - "+materialName);
				
			//verify the URL When PDF/Image type file is opened.
			Reporter.log("Verify the file opened properly when "+fileType+" type file is viewed.");
			 
			String actualPageURL = driver.getCurrentUrl();
			String expectedURL = "https://ae1c-bioinfocloud-slb01/devtest/faces/private/inventory/material/material-detail.xhtml";	
				
			//Verify the attached file displayed
			if(actualPageURL.equalsIgnoreCase(expectedURL))
				Reporter.log("Attached - "+fileType+" type file displayed successfully in the browser for material - "+materialName);
			else
				softAssertion.fail("Invalid URL displayed when attached file viewd from material detail page.");

			Thread.sleep(2000);
			Utills.captureScreenshot("Attached_File_"+TodayDate.Date());
			driver.close();
			
			ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tab.get(0));
			Thread.sleep(1000);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			softAssertion.fail("Atatched file not displayed.");
		}
	}
	
	public void Material_Detail_View_Excel_Type_Attachments(String materialName, String fileName, String fileType) throws Exception
	{
		//Verify the Safety Data page displayed in the path.
		String filePath = System.getProperty("user.home")+"\\Downloads";
		File file = new File(filePath+"\\"+fileName);
		if(file.exists()) 
            Reporter.log("File downloaded successfully When "+fileType+" type attached file is viewed of material - "+materialName);
		else
            softAssertion.fail("File not downloaded When "+fileType+" type attached file is viewed of material - "+materialName);
	}
	
	public void Material_Detail_Verify_Downloaded_Attachment_File(String fileName, String fileType) throws Exception
	{
		//Verify the Safety Data page displayed in the path.
		String filePath = System.getProperty("user.home")+"\\Downloads";
		File file = new File(filePath+"\\"+fileName);
		if(file.exists()) 
            Reporter.log("Attached "+fileType+" type file downloaded successfully.");
		else
            softAssertion.fail("Attached "+fileType+" type file not downloaded.");
	}
	
	public void Add_Material_Page_Add_New_UOM(String uom) throws Exception
	{
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.UOM","Add Unit of Measure") == true)
		{
			SelectList.SelectByVisibleText("Inventory.UOM","Add Unit of Measure");
			getWebElement("Inventory.AddMateriaPage.AddUOM").click();
			getWebElement("Inventory.AddMateriaPage.AddUOM").sendKeys(uom);
			Thread.sleep(1000);
			
			//Verify the newly added UOM displayed in the list.
			if(SelectList.VerifySelectList("Inventory.UOM",uom) == true)
				Reporter.log("The Unit Of Measure (UOM) - "+uom+" created and displayed in the list.");
			else
				Assert.fail("The newly created UOM - "+uom+" not displayed in the UOM list.");
		}
		else
			Assert.fail("'Add Unit of Measure' option does not exist.Unable to add new Unit of Measure.");
	}
	
	public void Add_Material_Page_Add_New_Vendor(String vendorName) throws Exception
	{
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.Vendor","Add Vendor") == true)
		{
			SelectList.SelectByVisibleText("Inventory.Vendor","Add Vendor");
			getWebElement("Inventory.AddVendor").click();
			getWebElement("Inventory.AddVendor").sendKeys(vendorName);
			getWebElement("Inventory.AddMaterial.SaveVendor.Icon").click();
			Thread.sleep(1000);
			
			//Verify the newly added vendor displayed in the list.
			if(SelectList.VerifySelectList("Inventory.Vendor",vendorName) == true)
				Reporter.log("The Vendor - "+vendorName+" created and displayed in the list.");
			else
				Assert.fail("The newly created Vendor - "+vendorName+" not displayed in the Vendor list.");
		}
		else
			Assert.fail("'Add Vendor' option does not exist.Unable to add new Vendor.");
	}
	
	public void Add_Material_Page_Add_New_Type(String materialType) throws Exception
	{
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.MaterialType","Add Type") == true)
		{
			SelectList.SelectByVisibleText("Inventory.MaterialType","Add Type");
			getWebElement("Inventory.AddMateriaPage.AddType").click();
			getWebElement("Inventory.AddMateriaPage.AddType").sendKeys(materialType);
			Thread.sleep(1000);
			
			//Verify the newly added type displayed in the list.
			if(SelectList.VerifySelectList("Inventory.MaterialType",materialType) == true)
				Reporter.log("The type - '"+materialType+"' created and displayed in the list.");
			else
				Assert.fail("The newly created type - '"+materialType+"' not displayed in the Type list.");
		}
		else
			Assert.fail("'Add Type' option does not exist.Unable to add new Type.");
	}
	
	public void Add_Materials_In_Bulk(int rowNumber) throws Exception
	{
		//Takes the input from excel and stores it in a variable.	
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData 
				+ InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String catalogNumber = ExcelUtils.getCellData(rowNumber, 1);
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
		String casNumber = ExcelUtils.getCellData(rowNumber, 6);
		String lotNumber = ExcelUtils.getCellData(rowNumber, 7);
		String manufactredDate = ExcelUtils.getCellData(rowNumber, 8);
		String expirationDate = ExcelUtils.getCellData(rowNumber, 9);
		String brand = ExcelUtils.getCellData(rowNumber, 10);
		String url = ExcelUtils.getCellData(rowNumber, 11);
		String boilingPoint = ExcelUtils.getCellData(rowNumber, 12);
		String meltingPoint = ExcelUtils.getCellData(rowNumber, 13);
		String flashingPoint = ExcelUtils.getCellData(rowNumber, 14);
		String minimumCount = ExcelUtils.getCellData(rowNumber, 15);
		String maximumCount = ExcelUtils.getCellData(rowNumber, 16);
		String materialType = ExcelUtils.getCellData(rowNumber, 17);
		String description = ExcelUtils.getCellData(rowNumber, 18);
		String count = ExcelUtils.getCellData(rowNumber, 19);
		
		//Navigation to Materials Page. Verify materials page exists or not.
		MaterialPageNavigation();
		
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Click on Add Material");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material modal not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material modal displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
		
		//Entering the material details in Add Material Modal
		Reporter.log("Enter the material details and add it to Inventory");
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMateriName").sendKeys(materialName);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		getWebElement("Inventory.Quantity").click();
		Thread.sleep(1000);
		getWebElement("Inventory.Quantity").sendKeys(quantity);
		
		//Verify the provided UOM exists in list. If not found, creates a new UOM
		Library SelectList = new Library();
		if(SelectList.VerifySelectList("Inventory.UOM",uom) == true)
			SelectList.SelectByValue("Inventory.UOM",uom);
		else
			Add_Material_Page_Add_New_UOM(uom);
		
		//Check whether the vendor exists or not. If not found, Creates a new vendor
		if(SelectList.VerifySelectList("Inventory.Vendor",vendorName) == true)
			SelectList.SelectByVisibleText("Inventory.Vendor",vendorName);
		else
			Add_Material_Page_Add_New_Vendor(vendorName);
			
		//Verify the provided material type exists in list. If not found, Creates a new type
		if(SelectList.VerifySelectList("Inventory.MaterialType",materialType) == true)
			SelectList.SelectByVisibleText("Inventory.MaterialType",materialType);
		else
			Add_Material_Page_Add_New_Type(materialType);
				
		//Add the count of materials
		getWebElement("Inventory.AddMaterial.Count").click();
		getWebElement("Inventory.AddMaterial.Count").clear();
		getWebElement("Inventory.AddMaterial.Count").sendKeys(count);
		
		//Clicks on "Other Details" tab.
		Thread.sleep(1000);
		getWebElement("Inventory.OtherDetailsTab").click();
		impliciteWait(2);
		
		getWebElement("Inventory.AddCasNumber").click();
		getWebElement("Inventory.AddCasNumber").sendKeys(casNumber);
		getWebElement("Inventory.AddLotNumber").sendKeys(lotNumber);
		getWebElement("Inventory.AddManufacturedDate").sendKeys(manufactredDate);
		getWebElement("Inventory.AddExpiredDate").sendKeys(expirationDate);
		getWebElement("Inventory.AddBrand").sendKeys(brand);
		getWebElement("Inventory.AddURL").sendKeys(url);
		getWebElement("Inventory.AddBoilingPoint").sendKeys(boilingPoint);
		getWebElement("Inventory.AddMeltingPoint").sendKeys(meltingPoint);
		getWebElement("Inventory.FlashingPoint").sendKeys(flashingPoint);
		getWebElement("Inventory.MinimumCount").sendKeys(minimumCount);
		getWebElement("Inventory.MaximumCount").sendKeys(maximumCount);
		getWebElement("Inventory.Description").click();
		getWebElement("Inventory.Description").sendKeys(description);
		Thread.sleep(1000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(2000);
		impliciteWait(3);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.", addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		Reporter.log("Add Material confirmation modal displayed successfully");
		getWebElement("Inventory.OkButton").click();
		impliciteWait(10);
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());
	
		//Verification of Success message after addition of new material.
		Thread.sleep(6000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			Reporter.log("Success Message displayed as - "+ActualSuccessMessage+" after addition of new material");
		else 
			softAssertion.fail("Success Message not displayed after addition of new material.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
		
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After addition of new material, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());	
		
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		int materialsCount = Integer.parseInt(count);
		if(MaterialsCountAfter == (materialsCountBefore + materialsCount))
			Reporter.log(count+" materials added successfully to materials list");
		else 
			softAssertion.fail("After addition of materials the materials count is not increased.");
	}
	
	public void Add_Material_Using_Existing_Product_Details(String materialName, String catalogNumber) throws Exception
	{
		//Navigation to "Add Material" modal
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Click on Add Material to add existing third party material.");
		getWebElement("Inventory.AddMaterial").click();
		Thread.sleep(1000);
		
		//Verify add material page exists
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed",materialDetailsTab.equals("Step 1 | Material details"));
		Reporter.log("Add Material page displayed successfully.");
		Utills.captureScreenshot("Add_Material_Page_"+TodayDate.Date());
 
		//Verify Auto Complete modal exist
		Reporter.log("Enter the existing third party vendor material details and add it to Inventory");
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);
		Thread.sleep(3000);
			
		WebElement verifyAutoCompleteTable = getWebElement("Inventory.AddMaterial.VerifyAutoCompleteTable");
		Assert.assertTrue("Auto complete modal not displayed for the entered catalog number -"+catalogNumber,verifyAutoCompleteTable.isDisplayed());
		Utills.captureScreenshot("Add_Material_Auto_Complete_Modal_"+TodayDate.Date());
		
		//Entering the material details
		WebElement materialFound = null;
		try
		{
			materialFound = driver.findElement(By.xpath("//tr[@data-item-group='Lab Catalog'][1]/td[contains(text(),'"+catalogNumber+"')]"));
			materialFound.isDisplayed();
			Reporter.log("Material - "+materialName+" displayed in auto suggestion list.");
		}
		catch(NoSuchElementException e) 
		{
			Assert.fail("Material - "+materialName+" does not exist in auto complete modal");
			Utills.captureScreenshot("Add_Material_Auto_Complete_Modal_Fail"+TodayDate.Date());	
		}
	
		materialFound.click();
		Thread.sleep(5000);
		getWebElement("Inventory.AddToInventoryButton").click();
		Thread.sleep(3000);
			
		//Verify add material confirmation modal
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.",addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		Reporter.log("Add Material confirmation modal displayed successfully");
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_"+TodayDate.Date());
		getWebElement("Inventory.OkButton").click();
		impliciteWait(5);
	
		//Verification of Success message after addition of new material.
		Thread.sleep(3000);
		String ActualSuccessMessage = getWebElement("Inventory.AddMaterialSuccessMessage").getText();
		String ExpectedSuccessMessage = "Success! Material Added.";
		if(ActualSuccessMessage.equals(ExpectedSuccessMessage))
			Reporter.log("Success Message displayed successfully after addition of new material as - "+ActualSuccessMessage);
		else 
			softAssertion.fail("Success Message not displayed after addition of new material.");
		Utills.captureScreenshot("Add_Material_Success_Message_"+TodayDate.Date());
				
		//Verify materials page exists or not after addition of new material
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material, the materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("After addition of new material, the materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		//Verify the count of materials when a new material is added.
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if(MaterialsCountAfter == (materialsCountBefore+1))
			Reporter.log("After addition of new material the material count is increased by "+(MaterialsCountAfter-materialsCountBefore));
		else 
			softAssertion.fail("After addition of new material the material count is not increased.");
	}
}
