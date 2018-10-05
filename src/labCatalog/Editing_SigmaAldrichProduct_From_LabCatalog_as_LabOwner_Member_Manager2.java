package labCatalog;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Utills;

public class Editing_SigmaAldrichProduct_From_LabCatalog_as_LabOwner_Member_Manager2 extends TestBase {
	
	@Test
	public void Edit_Lab_Catalog_Product_Created_By_Lab_Owner() throws Exception {
	
		//Generate a Random number to search in the Product Name
		Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String ProductName = randomNumber;
		
		//LOGIN TEST DATA
		String ExpectedSuccessMessage = "Success! Catalog added successfully!";
		String EditDescriptionConfirmation = "Success! Catalog description updated successfully!";
		String ProdQuantity = "50";
		String UpdatedProdQuantity = "23.0";
		String UpdatedProdName = "New"+ProductName;
		String UoM = "kg";
		String Updated_UoM = "mg";
		String Type = "Chemical";
		String Updated_Type = "Labware";
		String CAS = randomNumber+"97";
		String Updated_CAS = randomNumber+"99";
		String URL = "www.nonsa.com";
		String Updated_URL = "www.Editednonsa.com";
		String Brand = "MERCK";
		String Updated_Brand = "MERCK - Non SA";
		String BoilingPoint = "500";
		String Updated_BoilingPoint = "900.0";
		String FlashingPoint = "50";
		String Updated_FlashingPoint = "30.0";
		String MeltingPoint = "1000";
		String Updated_MeltingPoint = "1200.0";
		String MinimumCount = "1";
		String Updated_MinimumCount = "1";
		String MaximumCount = "2";
		String Updated_MaximumCount = "1";
		String Description = "Non Sigma Aldrich Product";
		String EditDescription = "Edited Description of the Product!                 ";
		
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
		
		getWebElement("EditCatalogProduct_Description").click();
		impliciteWait(5);
		getWebElement("EditCatalogProduct_EditDescription").click();
		getWebElement("EditCatalogProduct_EditDescription").clear();
		getWebElement("EditCatalogProduct_EditDescription").sendKeys(EditDescription);
		getWebElement("EditCatalogProduct_SaveEditedDescription").click();
		String EditProdDescription = getWebElement("RequestedProdcut_SuccessConfirmation").getText();
		System.out.println("Edit Prod Description"+EditProdDescription);
		System.out.println("Edit Description Confirmation"+EditDescriptionConfirmation);
		Assert.assertEquals(EditDescriptionConfirmation,EditProdDescription,"Product Description is Not edited successfully");
		Utills.captureScreenshot("EditNONSAProductConfirmation"+randomNumber);
		impliciteWait(40);
		
		//Edit and Update all the Product details in the Application
		getWebElement("EditCatalogProduct_Name").clear();
		getWebElement("EditCatalogProduct_Name").sendKeys(UpdatedProdName);
		getWebElement("EditCatalogProduct_Quantity").clear();
		getWebElement("EditCatalogProduct_Quantity").sendKeys(UpdatedProdQuantity);
		getWebElement("EditCatalogProduct_UoM").sendKeys(Updated_UoM);
		getWebElement("EditCatalogProduct_Type").sendKeys(Updated_Type);
		getWebElement("EditCatalogProduct_OtherdetailsTab").click();
		getWebElement("EditCatalogProduct_CASno").clear();
		getWebElement("EditCatalogProduct_CASno").sendKeys(Updated_CAS);
		getWebElement("EditCatalogProduct_URL").clear();
		getWebElement("EditCatalogProduct_URL").sendKeys(Updated_URL);
		getWebElement("EditCatalogProduct_FlashingPoint").clear();
		getWebElement("EditCatalogProduct_FlashingPoint").sendKeys(Updated_FlashingPoint);
		getWebElement("EditCatalogProduct_MinimumCount").clear();
		getWebElement("EditCatalogProduct_MinimumCount").sendKeys(Updated_MinimumCount);
		getWebElement("EditCatalogProduct_Brand").clear();
		getWebElement("EditCatalogProduct_Brand").sendKeys(Updated_Brand);
		getWebElement("EditCatalogProduct_BoilingPoint").clear();
		getWebElement("EditCatalogProduct_BoilingPoint").sendKeys(Updated_BoilingPoint);
		getWebElement("EditCatalogProduct_MeltingPoint").clear();
		getWebElement("EditCatalogProduct_MeltingPoint").sendKeys(Updated_MeltingPoint);
		getWebElement("EditCatalogProduct_MaximumCount").clear();
		getWebElement("EditCatalogProduct_MaximumCount").sendKeys(Updated_MaximumCount);
		getWebElement("EditCatalogProduct_UpdateProductDetailsButton").click();
		Utills.captureScreenshot("EditAllFieldsNONSAProductConfirmation"+randomNumber);
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab owner");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(UpdatedProdName);
		impliciteWait(40);
		
		//Navigate to edit lab catalog page
		driver.findElement(By.linkText(UpdatedProdName)).click();
		impliciteWait(40);
				
		String New_UpdatedProdName = getWebElement("EditCatalogProduct_Name").getAttribute("value");
		System.out.println("UpdatedProdName : "+UpdatedProdName);
		System.out.println("New_UpdatedProdName : "+New_UpdatedProdName);
		Assert.assertEquals(UpdatedProdName,New_UpdatedProdName,"Product Name is Not edited successfully");
		
		String New_UpdatedProdQuantity = getWebElement("EditCatalogProduct_Quantity").getAttribute("value");
		System.out.println("UpdatedProdQuantity : "+UpdatedProdQuantity);
		System.out.println("New_UpdatedProdQuantity : "+New_UpdatedProdQuantity);
		Assert.assertEquals(UpdatedProdQuantity,New_UpdatedProdQuantity,"Product Quantity is Not edited successfully");
		String New_Updated_UoM = getWebElement("EditCatalogProduct_UoM").getAttribute("value");
		System.out.println("Updated_UoM : "+Updated_UoM);
		System.out.println("New_Updated_UoM : "+New_Updated_UoM);
		Assert.assertEquals(Updated_UoM,New_Updated_UoM,"Product Unit is Not edited successfully");
		
		Select TypeDropDown = new Select(getWebElement("EditCatalogProduct_Type"));
		String New_Updated_Type = TypeDropDown.getFirstSelectedOption().getText();
		
		System.out.println("Updated_Type : "+Updated_Type);
		System.out.println("New_Updated_Type : "+New_Updated_Type);
		Assert.assertEquals(Updated_Type,New_Updated_Type,"Product Type is Not edited successfully");
		getWebElement("EditCatalogProduct_OtherdetailsTab").click();
		String New_Updated_CAS = getWebElement("EditCatalogProduct_CASno").getAttribute("value");
		Assert.assertEquals(Updated_CAS,New_Updated_CAS,"Product CAS is Not edited successfully");
		String New_Updated_URL = getWebElement("EditCatalogProduct_URL").getAttribute("value");
		Assert.assertEquals(Updated_URL,New_Updated_URL,"Product URL is Not edited successfully");
		String New_Updated_FlashingPoint = getWebElement("EditCatalogProduct_FlashingPoint").getAttribute("value");
		Assert.assertEquals(Updated_FlashingPoint,New_Updated_FlashingPoint,"Product FlashingPoint is Not edited successfully");
		String New_Updated_MinimumCount = getWebElement("EditCatalogProduct_MinimumCount").getAttribute("value");
		Assert.assertEquals(Updated_MinimumCount,New_Updated_MinimumCount,"Product MinimumCount is Not edited successfully");
		String New_Updated_Brand = getWebElement("EditCatalogProduct_Brand").getAttribute("value");
		Assert.assertEquals(Updated_Brand,New_Updated_Brand,"Product Brand is Not edited successfully");
		String New_Updated_BoilingPoint = getWebElement("EditCatalogProduct_BoilingPoint").getAttribute("value");
		Assert.assertEquals(Updated_BoilingPoint,New_Updated_BoilingPoint,"Product BoilingPoint is Not edited successfully");
		String New_Updated_MeltingPoint = getWebElement("EditCatalogProduct_MeltingPoint").getAttribute("value");
		Assert.assertEquals(Updated_MeltingPoint,New_Updated_MeltingPoint,"Product MeltingPoint is Not edited successfully");
		String New_Updated_MaximumCount = getWebElement("EditCatalogProduct_MaximumCount").getAttribute("value");
		Assert.assertEquals(Updated_MaximumCount,New_Updated_MaximumCount,"Product MaximumCount is Not edited successfully");
		
		Reporter.log("Editing the Lab Catalog product as Lab Owner is Successful");
		//Logout
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
	
	@Test
	public void Edit_Lab_Catalog_Product_Created_By_Lab_Manager() throws Exception {
	
		//Generate a Random number to search in the Product Name
		Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String ProductName = randomNumber;
		
		//LOGIN TEST DATA
		String ExpectedSuccessMessage = "Success! Catalog added successfully!";
		String EditDescriptionConfirmation = "Success! Catalog description updated successfully!";
		String ProdQuantity = "50";
		String UpdatedProdQuantity = "23.0";
		String UpdatedProdName = "New"+ProductName;
		String UoM = "kg";
		String Updated_UoM = "mg";
		String Type = "Chemical";
		String Updated_Type = "Labware";
		String CAS = randomNumber+"97";
		String Updated_CAS = randomNumber+"99";
		String URL = "www.nonsa.com";
		String Updated_URL = "www.Editednonsa.com";
		String Brand = "MERCK";
		String Updated_Brand = "MERCK - Non SA";
		String BoilingPoint = "500";
		String Updated_BoilingPoint = "900.0";
		String FlashingPoint = "50";
		String Updated_FlashingPoint = "30.0";
		String MeltingPoint = "1000";
		String Updated_MeltingPoint = "1200.0";
		String MinimumCount = "1";
		String Updated_MinimumCount = "1";
		String MaximumCount = "2";
		String Updated_MaximumCount = "1";
		String Description = "Non Sigma Aldrich Product";
		String EditDescription = "Edited Description of the Product!                 ";
		
		//Login to application as lab Owner
		init();
		Reporter.log("Login to Application as lab Manager");
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
		Reporter.log("Select the lab catalog product created by Lab Manager");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(ProductName);
		impliciteWait(40);
		
		//Navigate to edit lab catalog page
		driver.findElement(By.linkText(ProductName)).click();
		impliciteWait(40);
		
		getWebElement("EditCatalogProduct_Description").click();
		impliciteWait(5);
		getWebElement("EditCatalogProduct_EditDescription").click();
		getWebElement("EditCatalogProduct_EditDescription").clear();
		getWebElement("EditCatalogProduct_EditDescription").sendKeys(EditDescription);
		getWebElement("EditCatalogProduct_SaveEditedDescription").click();
		String EditProdDescription = getWebElement("RequestedProdcut_SuccessConfirmation").getText();
		System.out.println("Edit Prod Description"+EditProdDescription);
		System.out.println("Edit Description Confirmation"+EditDescriptionConfirmation);
		Assert.assertEquals(EditDescriptionConfirmation,EditProdDescription,"Product Description is Not edited successfully");
		Utills.captureScreenshot("EditNONSAProductConfirmation"+randomNumber);
		impliciteWait(40);
		
		//Edit and Update all the Product details in the Application
		getWebElement("EditCatalogProduct_Name").clear();
		getWebElement("EditCatalogProduct_Name").sendKeys(UpdatedProdName);
		getWebElement("EditCatalogProduct_Quantity").clear();
		getWebElement("EditCatalogProduct_Quantity").sendKeys(UpdatedProdQuantity);
		getWebElement("EditCatalogProduct_UoM").sendKeys(Updated_UoM);
		getWebElement("EditCatalogProduct_Type").sendKeys(Updated_Type);
		getWebElement("EditCatalogProduct_OtherdetailsTab").click();
		getWebElement("EditCatalogProduct_CASno").clear();
		getWebElement("EditCatalogProduct_CASno").sendKeys(Updated_CAS);
		getWebElement("EditCatalogProduct_URL").clear();
		getWebElement("EditCatalogProduct_URL").sendKeys(Updated_URL);
		getWebElement("EditCatalogProduct_FlashingPoint").clear();
		getWebElement("EditCatalogProduct_FlashingPoint").sendKeys(Updated_FlashingPoint);
		getWebElement("EditCatalogProduct_MinimumCount").clear();
		getWebElement("EditCatalogProduct_MinimumCount").sendKeys(Updated_MinimumCount);
		getWebElement("EditCatalogProduct_Brand").clear();
		getWebElement("EditCatalogProduct_Brand").sendKeys(Updated_Brand);
		getWebElement("EditCatalogProduct_BoilingPoint").clear();
		getWebElement("EditCatalogProduct_BoilingPoint").sendKeys(Updated_BoilingPoint);
		getWebElement("EditCatalogProduct_MeltingPoint").clear();
		getWebElement("EditCatalogProduct_MeltingPoint").sendKeys(Updated_MeltingPoint);
		getWebElement("EditCatalogProduct_MaximumCount").clear();
		getWebElement("EditCatalogProduct_MaximumCount").sendKeys(Updated_MaximumCount);
		getWebElement("EditCatalogProduct_UpdateProductDetailsButton").click();
		Utills.captureScreenshot("EditAllFieldsNONSAProductConfirmation"+randomNumber);
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab Manager");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(UpdatedProdName);
		impliciteWait(40);
		
		//Navigate to edit lab catalog page
		driver.findElement(By.linkText(UpdatedProdName)).click();
		impliciteWait(40);
				
		String New_UpdatedProdName = getWebElement("EditCatalogProduct_Name").getAttribute("value");
		System.out.println("UpdatedProdName : "+UpdatedProdName);
		System.out.println("New_UpdatedProdName : "+New_UpdatedProdName);
		Assert.assertEquals(UpdatedProdName,New_UpdatedProdName,"Product Name is Not edited successfully");
		
		String New_UpdatedProdQuantity = getWebElement("EditCatalogProduct_Quantity").getAttribute("value");
		System.out.println("UpdatedProdQuantity : "+UpdatedProdQuantity);
		System.out.println("New_UpdatedProdQuantity : "+New_UpdatedProdQuantity);
		Assert.assertEquals(UpdatedProdQuantity,New_UpdatedProdQuantity,"Product Quantity is Not edited successfully");
		String New_Updated_UoM = getWebElement("EditCatalogProduct_UoM").getAttribute("value");
		System.out.println("Updated_UoM : "+Updated_UoM);
		System.out.println("New_Updated_UoM : "+New_Updated_UoM);
		Assert.assertEquals(Updated_UoM,New_Updated_UoM,"Product Unit is Not edited successfully");
		
		Select TypeDropDown = new Select(getWebElement("EditCatalogProduct_Type"));
		String New_Updated_Type = TypeDropDown.getFirstSelectedOption().getText();
		
		System.out.println("Updated_Type : "+Updated_Type);
		System.out.println("New_Updated_Type : "+New_Updated_Type);
		Assert.assertEquals(Updated_Type,New_Updated_Type,"Product Type is Not edited successfully");
		getWebElement("EditCatalogProduct_OtherdetailsTab").click();
		String New_Updated_CAS = getWebElement("EditCatalogProduct_CASno").getAttribute("value");
		Assert.assertEquals(Updated_CAS,New_Updated_CAS,"Product CAS is Not edited successfully");
		String New_Updated_URL = getWebElement("EditCatalogProduct_URL").getAttribute("value");
		Assert.assertEquals(Updated_URL,New_Updated_URL,"Product URL is Not edited successfully");
		String New_Updated_FlashingPoint = getWebElement("EditCatalogProduct_FlashingPoint").getAttribute("value");
		Assert.assertEquals(Updated_FlashingPoint,New_Updated_FlashingPoint,"Product FlashingPoint is Not edited successfully");
		String New_Updated_MinimumCount = getWebElement("EditCatalogProduct_MinimumCount").getAttribute("value");
		Assert.assertEquals(Updated_MinimumCount,New_Updated_MinimumCount,"Product MinimumCount is Not edited successfully");
		String New_Updated_Brand = getWebElement("EditCatalogProduct_Brand").getAttribute("value");
		Assert.assertEquals(Updated_Brand,New_Updated_Brand,"Product Brand is Not edited successfully");
		String New_Updated_BoilingPoint = getWebElement("EditCatalogProduct_BoilingPoint").getAttribute("value");
		Assert.assertEquals(Updated_BoilingPoint,New_Updated_BoilingPoint,"Product BoilingPoint is Not edited successfully");
		String New_Updated_MeltingPoint = getWebElement("EditCatalogProduct_MeltingPoint").getAttribute("value");
		Assert.assertEquals(Updated_MeltingPoint,New_Updated_MeltingPoint,"Product MeltingPoint is Not edited successfully");
		String New_Updated_MaximumCount = getWebElement("EditCatalogProduct_MaximumCount").getAttribute("value");
		Assert.assertEquals(Updated_MaximumCount,New_Updated_MaximumCount,"Product MaximumCount is Not edited successfully");
		
		Reporter.log("Editing the Lab Catalog product as Lab Manager is Successful");
		//Logout
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
	
	@Test
	public void Edit_Lab_Catalog_Product_Created_By_Lab_Member() throws Exception {
	
		//Generate a Random number to search in the Product Name
		Library TodayDate = new Library();
		String randomNumber = TodayDate.Date();
		String ProductName = randomNumber;
		
		//LOGIN TEST DATA
		String ExpectedSuccessMessage = "Success! Catalog added successfully!";
		String EditDescriptionConfirmation = "Success! Catalog description updated successfully!";
		String ProdQuantity = "50";
		String UpdatedProdQuantity = "23.0";
		String UpdatedProdName = "New"+ProductName;
		String UoM = "kg";
		String Updated_UoM = "mg";
		String Type = "Chemical";
		String Updated_Type = "Labware";
		String CAS = randomNumber+"97";
		String Updated_CAS = randomNumber+"99";
		String URL = "www.nonsa.com";
		String Updated_URL = "www.Editednonsa.com";
		String Brand = "MERCK";
		String Updated_Brand = "MERCK - Non SA";
		String BoilingPoint = "500";
		String Updated_BoilingPoint = "900.0";
		String FlashingPoint = "50";
		String Updated_FlashingPoint = "30.0";
		String MeltingPoint = "1000";
		String Updated_MeltingPoint = "1200.0";
		String MinimumCount = "1";
		String Updated_MinimumCount = "1";
		String MaximumCount = "2";
		String Updated_MaximumCount = "1";
		String Description = "Non Sigma Aldrich Product";
		String EditDescription = "Edited Description of the Product!                 ";
		
		//Login to application as lab Owner
		init();
		Reporter.log("Login to Application as lab Member");
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
		Reporter.log("Select the lab catalog product created by Lab Member");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(ProductName);
		impliciteWait(40);
		
		//Navigate to edit lab catalog page
		driver.findElement(By.linkText(ProductName)).click();
		impliciteWait(40);
		
		getWebElement("EditCatalogProduct_Description").click();
		impliciteWait(5);
		getWebElement("EditCatalogProduct_EditDescription").click();
		getWebElement("EditCatalogProduct_EditDescription").clear();
		getWebElement("EditCatalogProduct_EditDescription").sendKeys(EditDescription);
		getWebElement("EditCatalogProduct_SaveEditedDescription").click();
		String EditProdDescription = getWebElement("RequestedProdcut_SuccessConfirmation").getText();
		System.out.println("Edit Prod Description"+EditProdDescription);
		System.out.println("Edit Description Confirmation"+EditDescriptionConfirmation);
		Assert.assertEquals(EditDescriptionConfirmation,EditProdDescription,"Product Description is Not edited successfully");
		Utills.captureScreenshot("EditNONSAProductConfirmation"+randomNumber);
		impliciteWait(40);
		
		//Edit and Update all the Product details in the Application
		getWebElement("EditCatalogProduct_Name").clear();
		getWebElement("EditCatalogProduct_Name").sendKeys(UpdatedProdName);
		getWebElement("EditCatalogProduct_Quantity").clear();
		getWebElement("EditCatalogProduct_Quantity").sendKeys(UpdatedProdQuantity);
		getWebElement("EditCatalogProduct_UoM").sendKeys(Updated_UoM);
		getWebElement("EditCatalogProduct_Type").sendKeys(Updated_Type);
		getWebElement("EditCatalogProduct_OtherdetailsTab").click();
		getWebElement("EditCatalogProduct_CASno").clear();
		getWebElement("EditCatalogProduct_CASno").sendKeys(Updated_CAS);
		getWebElement("EditCatalogProduct_URL").clear();
		getWebElement("EditCatalogProduct_URL").sendKeys(Updated_URL);
		getWebElement("EditCatalogProduct_FlashingPoint").clear();
		getWebElement("EditCatalogProduct_FlashingPoint").sendKeys(Updated_FlashingPoint);
		getWebElement("EditCatalogProduct_MinimumCount").clear();
		getWebElement("EditCatalogProduct_MinimumCount").sendKeys(Updated_MinimumCount);
		getWebElement("EditCatalogProduct_Brand").clear();
		getWebElement("EditCatalogProduct_Brand").sendKeys(Updated_Brand);
		getWebElement("EditCatalogProduct_BoilingPoint").clear();
		getWebElement("EditCatalogProduct_BoilingPoint").sendKeys(Updated_BoilingPoint);
		getWebElement("EditCatalogProduct_MeltingPoint").clear();
		getWebElement("EditCatalogProduct_MeltingPoint").sendKeys(Updated_MeltingPoint);
		getWebElement("EditCatalogProduct_MaximumCount").clear();
		getWebElement("EditCatalogProduct_MaximumCount").sendKeys(Updated_MaximumCount);
		getWebElement("EditCatalogProduct_UpdateProductDetailsButton").click();
		Utills.captureScreenshot("EditAllFieldsNONSAProductConfirmation"+randomNumber);
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab Member");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(UpdatedProdName);
		impliciteWait(40);
		
		//Navigate to edit lab catalog page
		driver.findElement(By.linkText(UpdatedProdName)).click();
		impliciteWait(40);
				
		String New_UpdatedProdName = getWebElement("EditCatalogProduct_Name").getAttribute("value");
		System.out.println("UpdatedProdName : "+UpdatedProdName);
		System.out.println("New_UpdatedProdName : "+New_UpdatedProdName);
		Assert.assertEquals(UpdatedProdName,New_UpdatedProdName,"Product Name is Not edited successfully");
		
		String New_UpdatedProdQuantity = getWebElement("EditCatalogProduct_Quantity").getAttribute("value");
		System.out.println("UpdatedProdQuantity : "+UpdatedProdQuantity);
		System.out.println("New_UpdatedProdQuantity : "+New_UpdatedProdQuantity);
		Assert.assertEquals(UpdatedProdQuantity,New_UpdatedProdQuantity,"Product Quantity is Not edited successfully");
		String New_Updated_UoM = getWebElement("EditCatalogProduct_UoM").getAttribute("value");
		System.out.println("Updated_UoM : "+Updated_UoM);
		System.out.println("New_Updated_UoM : "+New_Updated_UoM);
		Assert.assertEquals(Updated_UoM,New_Updated_UoM,"Product Unit is Not edited successfully");
		
		Select TypeDropDown = new Select(getWebElement("EditCatalogProduct_Type"));
		String New_Updated_Type = TypeDropDown.getFirstSelectedOption().getText();
		
		System.out.println("Updated_Type : "+Updated_Type);
		System.out.println("New_Updated_Type : "+New_Updated_Type);
		Assert.assertEquals(Updated_Type,New_Updated_Type,"Product Type is Not edited successfully");
		getWebElement("EditCatalogProduct_OtherdetailsTab").click();
		String New_Updated_CAS = getWebElement("EditCatalogProduct_CASno").getAttribute("value");
		Assert.assertEquals(Updated_CAS,New_Updated_CAS,"Product CAS is Not edited successfully");
		String New_Updated_URL = getWebElement("EditCatalogProduct_URL").getAttribute("value");
		Assert.assertEquals(Updated_URL,New_Updated_URL,"Product URL is Not edited successfully");
		String New_Updated_FlashingPoint = getWebElement("EditCatalogProduct_FlashingPoint").getAttribute("value");
		Assert.assertEquals(Updated_FlashingPoint,New_Updated_FlashingPoint,"Product FlashingPoint is Not edited successfully");
		String New_Updated_MinimumCount = getWebElement("EditCatalogProduct_MinimumCount").getAttribute("value");
		Assert.assertEquals(Updated_MinimumCount,New_Updated_MinimumCount,"Product MinimumCount is Not edited successfully");
		String New_Updated_Brand = getWebElement("EditCatalogProduct_Brand").getAttribute("value");
		Assert.assertEquals(Updated_Brand,New_Updated_Brand,"Product Brand is Not edited successfully");
		String New_Updated_BoilingPoint = getWebElement("EditCatalogProduct_BoilingPoint").getAttribute("value");
		Assert.assertEquals(Updated_BoilingPoint,New_Updated_BoilingPoint,"Product BoilingPoint is Not edited successfully");
		String New_Updated_MeltingPoint = getWebElement("EditCatalogProduct_MeltingPoint").getAttribute("value");
		Assert.assertEquals(Updated_MeltingPoint,New_Updated_MeltingPoint,"Product MeltingPoint is Not edited successfully");
		String New_Updated_MaximumCount = getWebElement("EditCatalogProduct_MaximumCount").getAttribute("value");
		Assert.assertEquals(Updated_MaximumCount,New_Updated_MaximumCount,"Product MaximumCount is Not edited successfully");
		
		Reporter.log("Editing the Lab Catalog product as Lab Member is Successful");
		//Logout
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
}