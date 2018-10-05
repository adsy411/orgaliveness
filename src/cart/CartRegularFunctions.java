package cart;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.taskdefs.Length;
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

public class CartRegularFunctions extends TestBase
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
		List<WebElement> allLinksInForm = driver.findElements(By.xpath("//div[@class='portlet light bordered']"));
		int RequestedCount = allLinksInForm.size();
		Reporter.log("Request Count:"+RequestedCount);
		Utills.captureScreenshot("RequestinitialCount"+TodayDate.Date());
		Thread.sleep(3000);
		return RequestedCount;
	}
	
	//Searching and adding product to cart
	public boolean SearchAndAddProducttoCart() throws Exception
	{	
		Thread.sleep(3000);
		if(getWebElement("Cart_Addproducttocart_ModalTitle").isDisplayed()){
			Reporter.log("Add Products to cart Modal is displayed successfully.");
			Utills.captureScreenshot("AddProductstocartmodal"+TodayDate.Date());
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"QuickOrder_RequestNewProduct");
			String CartProductName = ExcelUtils.getCellData(9, 0);
			getWebElement("Cart_SearchBox").click();
			getWebElement("Cart_SearchBox").clear();
			getWebElement("Cart_SearchBox").sendKeys(CartProductName);
			Thread.sleep(15000);
			//if(driver.findElements(By.xpath("//span[@id='requestAutoComplete:request-search-box_panel']")).size()!= 0){
			if(driver.findElements(By.xpath("//ul[@class='ui-autocomplete-items ui-autocomplete-list ui-widget-content ui-widget ui-corner-all ui-helper-reset']")).size()!= 0){	
				System.out.println("Element is Present");
				getWebElement("CartAutoSuggestion").click();
				Thread.sleep(25000);
				//driver.findElement(By.xpath("(//span[@id='requestAutoComplete:addToCartList']//i[@class='fa fa-cart-plus'])[1]")).click();
				//driver.findElement(By.xpath("//div[@class='col-xs-12 col-md-12 col-lg-12 product-search-result product-search-result-scroll']//i[@class='fa fa-cart-plus']")).click();
				getWebElement("CartIconProductSearc3").click();
				Thread.sleep(5000);
				Utills.captureScreenshot("searchProduct"+TodayDate.Date());
			}
			else{
				ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"QuickOrder_RequestNewProduct");
				String CartProductName1 = ExcelUtils.getCellData(9, 0);
				getWebElement("Cart_SearchBox").click();
				getWebElement("Cart_SearchBox").clear();
				getWebElement("Cart_SearchBox").sendKeys(CartProductName1);
				Thread.sleep(15000);
				if(driver.findElements(By.xpath("//span[@id='requestAutoComplete:request-search-box_panel']")).size()!= 0){
					System.out.println("Element is Present");
					getWebElement("CartAutoSuggestion").click();
					Thread.sleep(25000);
					//driver.findElement(By.xpath("(//span[@id='requestAutoComplete:addToCartList']//i[@class='fa fa-cart-plus'])[1]")).click();
					//driver.findElement(By.xpath("//div[@class='col-xs-12 col-md-12 col-lg-12 product-search-result product-search-result-scroll']//i[@class='fa fa-cart-plus']")).click();
					getWebElement("CartIconProductSearc3").click();
					Thread.sleep(5000);
					Utills.captureScreenshot("searchProduct"+TodayDate.Date());
				}	
			}
		}
		else {
			getWebElement("CartIconAddproducttocartRequestPage").click();
			Thread.sleep(5000);
			if(getWebElement("Cart_Addproducttocart_ModalTitle").isDisplayed()){
			Reporter.log("Add Products to cart Modal is displayed successfully.");
			Utills.captureScreenshot("AddProductstocartmodal"+TodayDate.Date());
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"QuickOrder_RequestNewProduct");
			String CartProductName = ExcelUtils.getCellData(9, 0);
			getWebElement("Cart_SearchBox").click();
			getWebElement("Cart_SearchBox").clear();
			getWebElement("Cart_SearchBox").sendKeys(CartProductName);
			Thread.sleep(13000);
			if(driver.findElements(By.xpath("//span[@id='requestAutoComplete:request-search-box_panel']")).size()!= 0){
				System.out.println("Element is Present");
				getWebElement("CartAutoSuggestion").click();
				Thread.sleep(25000);
				//driver.findElement(By.xpath("(//span[@id='requestAutoComplete:addToCartList']//i[@class='fa fa-cart-plus'])[1]")).click();
				//driver.findElement(By.xpath("//div[@class='col-xs-12 col-md-12 col-lg-12 product-search-result product-search-result-scroll']//i[@class='fa fa-cart-plus']")).click();
				getWebElement("CartIconProductSearc3").click();
				Thread.sleep(5000);
				Utills.captureScreenshot("searchProduct"+TodayDate.Date());
			}
			else{
				ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"QuickOrder_RequestNewProduct");
				String CartProductName1 = ExcelUtils.getCellData(3, 0);
				getWebElement("Cart_SearchBox").click();
				getWebElement("Cart_SearchBox").clear();
				getWebElement("Cart_SearchBox").sendKeys(CartProductName1);
				Thread.sleep(13000);
				if(driver.findElements(By.xpath("//span[@id='requestAutoComplete:request-search-box_panel']")).size()!= 0){
					System.out.println("Element is Present");
					getWebElement("CartAutoSuggestion").click();
					Thread.sleep(25000);
					//driver.findElement(By.xpath("(//span[@id='requestAutoComplete:addToCartList']//i[@class='fa fa-cart-plus'])[1]")).click();
					//driver.findElement(By.xpath("//div[@class='col-xs-12 col-md-12 col-lg-12 product-search-result product-search-result-scroll']//i[@class='fa fa-cart-plus']")).click();
					getWebElement("CartIconProductSearc3").click();
					Thread.sleep(5000);
					Utills.captureScreenshot("searchProduct"+TodayDate.Date());
				}	
			}
		}
	}
			
	return true;
	}
	//Navigate to Cart Checkout page
	public boolean Cartcheckoutpagenavigation() throws Exception
	{	
		Thread.sleep(10000);
		//Click on View cart Checkout button
		getWebElement("CartIconNotificationArea").click();
		Thread.sleep(35000);
		if(getWebElement("ViewCheckoutbutton").isDisplayed())
		getWebElement("ViewCheckoutbutton").click();
		else {
			getWebElement("CartIconNotificationArea").click();
			Thread.sleep(35000);
			if(getWebElement("ViewCheckoutbutton").isDisplayed())
			getWebElement("ViewCheckoutbutton").click();
		}
		Thread.sleep(30000);
		return true;
		
	}
	
	//Check if cart is empty will return True if cart is Empty, false if cart is not Empty
	public boolean isCartEmpty() throws Exception
	{	
		Thread.sleep(10000);
		//Click on View cart Checkout button
		String cartcountstring = getWebElement("CartItemsCountontop").getText();
		int CartCount = Integer.parseInt(cartcountstring);
		Thread.sleep(10000);
		if(CartCount == 0){
			Reporter.log("Empty Cart");
			Utills.captureScreenshot("EmptyCart_"+TodayDate.Date());
			return true;
		}
		else {
			return false;
		}
	}
	//Navigate to Cart Checkout page NOTE CALL THIS FROM THE REQUEST PAGE
	public boolean EmptyCart() throws Exception
	{	
		String cartcountstring = getWebElement("CartItemsCountontop").getText();
		int CartCount = Integer.parseInt(cartcountstring);
		Thread.sleep(10000);
		
			getWebElement("CartIconNotificationArea").click();
			Thread.sleep(30000);
			getWebElement("ViewCheckoutbutton").click();
			Thread.sleep(30000);
			String Part1 = "//html//div[";
			String Part2 = "2";
			String Part3 = "]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/a[1]";
			for(int i=1;i<=CartCount;i++) {
				//int count = i+1;
				//String Part2 = Integer.toString(count);
				driver.findElement(By.xpath(Part1+Part2+Part3)).click();
				Thread.sleep(5000);
				getWebElement("CartItemDeleteConfirmationbutton").click();
				Thread.sleep(5000);
			}
		//}
		RequestPageNavigation();
		return true;
	}
//Update Quantity in Checkout Page
	public boolean UpdateProductCount() throws Exception
	{
		String Str_updatedcount;
		int quantitycount;
		Thread.sleep(5000); 
		driver.findElement(By.xpath("(//form[@id='reviewcartform']//div[@class='portlet light bordered review-cart']//div[@class='portlet light bordered']//i[@class='fa fa-pencil'])[1]")).click();
		driver.findElement(By.xpath("(//input[starts-with(@id,'quantity')])[1]")).click();
		driver.findElement(By.xpath("(//input[starts-with(@id,'quantity')])[1]")).clear();
		driver.findElement(By.xpath("(//input[starts-with(@id,'quantity')])[1]")).sendKeys("5");
		driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-ok']")).click();
		Thread.sleep(15000); 
		Str_updatedcount = getWebElement("CartProduct_Quantity").getText();
		quantitycount = Integer.parseInt(Str_updatedcount);
		System.out.println(Str_updatedcount);
		if(quantitycount == 5) {
		//if(getWebElement("CountUpdateSuccessmessage").isDisplayed()) {
			Reporter.log("Product Count Updated successfully.");
			System.out.println("Count Edit Pass");
			Utills.captureScreenshot("ProductCountUpdatePass"+TodayDate.Date());
		}
		else {
			Reporter.log("Product Count Update failed.");
			System.out.println("Count Edit Fail");
			Utills.captureScreenshot("ProductCountUpdateFail"+TodayDate.Date());
		}
		//getWebElement("CartBreadcrum_HomeNavigation").click();;
		Thread.sleep(20000);
		return true;
	}		

	//Update Packsize in Checkout Page 
	public boolean UpdateProductPacksize() throws Exception
	{
		getWebElement("CheckoutPagePacksizeDropdown").click();
		Utills.captureScreenshot("ProductPacksizeBeforEdit"+TodayDate.Date());
		getWebElement("CheckoutPagePacksizeDropdown").sendKeys(Keys.ARROW_DOWN);
		getWebElement("CheckoutPagePacksizeDropdown").sendKeys(Keys.ARROW_DOWN);
		getWebElement("CheckoutPagePacksizeDropdown").sendKeys(Keys.ENTER);
		Reporter.log("Packsize Updated successfully.");
		System.out.println("Count Edit Pass");
		Utills.captureScreenshot("ProductPacksizeAfterEdit"+TodayDate.Date());
		Thread.sleep(20000);
		//getWebElement("CartBreadcrum_HomeNavigation").click();
		Thread.sleep(3000);
		return true;
	}		
	//Verify Total Price in Checkout Page 
		public boolean VerifyTotalPrice() throws Exception
		{
			
			String str_SubTotal = driver.findElement(By.xpath("//div[@class='col-lg-12 text-right total-wrapper']//span[1]")).getText();
			String str_Shipping = driver.findElement(By.xpath("//div[@class='col-lg-12 text-right total-wrapper']//span[2]")).getText();
			String str_Tax = driver.findElement(By.xpath("//div[@class='col-lg-12 text-right total-wrapper']//span[3]")).getText();
			String str_Total = driver.findElement(By.xpath("//div[@class='col-lg-12 text-right total-wrapper']//span[4]")).getText();
			/*System.out.println("str_SubTotal :"+str_SubTotal);
			System.out.println("str_Shipping :"+str_Shipping);
			System.out.println("str_Tax :"+str_Tax);
			System.out.println("str_Total :"+str_Total);*/
			float SubTotal = Float.parseFloat(str_SubTotal);
			float Shipping = Float.parseFloat(str_Shipping);
			float Tax = Float.parseFloat(str_Tax);
			float Total = Float.parseFloat(str_Total);
			/*System.out.println("SubTotal :"+SubTotal);
			System.out.println("Shipping :"+Shipping);
			System.out.println("Tax :"+Tax);
			System.out.println("Total :"+Total);*/
			float validat_Total = SubTotal+Shipping+Tax;
			if(validat_Total==Total) {
				System.out.println("Verify Total Price in Checkout Page Pass");
				Utills.captureScreenshot("VerifyTotalPricePass"+TodayDate.Date());
				Thread.sleep(20000);
			}
			else {
				System.out.println("Verify Total Price in Checkout Page Fail");
				Utills.captureScreenshot("VerifyTotalPriceFail"+TodayDate.Date());
				Thread.sleep(20000);
			}
			
			return true;
		}	
	
	//Verify if cart page is displayed
	public boolean VerifyCartpageisdisplayed() throws Exception
	{	
		Thread.sleep(3000);
		List<WebElement> cartItems = driver.findElements(By.xpath("//form[@id='reviewcartform']//div[@class='portlet light bordered review-cart']//div[@class='portlet light bordered']"));
		int CartItemscount = cartItems.size();
		Thread.sleep(10000);
		
		if(getWebElement("CartPage_ReviewCartTitle").isDisplayed() && CartItemscount!=0) {
			Reporter.log("Checkout page and Products in the Cart is displayed successfully.");
			Utills.captureScreenshot("Cart_Page_Pass_"+TodayDate.Date());
		}
		else {
			Reporter.log("Checkout page Navigation Failed");
			Utills.captureScreenshot("Cart_Page_Fail_"+TodayDate.Date());
		}
			
		return true;
	}
	
	//Edit contact Details
	public boolean EditandverifyContactInfo() throws Exception
	{	
		Thread.sleep(3000);
		getWebElement("CartEditContactInfoPenIcon").click();
		Utills.captureScreenshot("Before_EditContactInfo_"+TodayDate.Date());
		//if(getWebElement("EditContactinfoModalTitle").isDisplayed()) {
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
			String EditFirstName = ExcelUtils.getCellData(1, 0);
			String EditLastName = ExcelUtils.getCellData(1, 1);
			String EditMobileNumber = ExcelUtils.getCellData(1, 2);
			String EditEmailID = ExcelUtils.getCellData(1, 3);
			Thread.sleep(3000);
			getWebElement("EditContactinfoModalmobile").click();
			getWebElement("EditContactinfoModalmobile").clear();
			getWebElement("EditContactinfoModalmobile").sendKeys(EditMobileNumber);
			Thread.sleep(3000);
			getWebElement("EditContactinfoModalFirstname").click();
			getWebElement("EditContactinfoModalFirstname").clear();
			getWebElement("EditContactinfoModalFirstname").sendKeys(EditFirstName);
			Thread.sleep(3000);
			getWebElement("EditContactinfoModalLastname").click();
			getWebElement("EditContactinfoModalLastname").clear();
			getWebElement("EditContactinfoModalLastname").sendKeys(EditLastName);
			Thread.sleep(3000);
			getWebElement("EditContactinfoModalemail").click();
			Thread.sleep(3000);
			getWebElement("EditContactinfoModalemail").clear();
			getWebElement("EditContactinfoModalemail").sendKeys(EditEmailID);
			Thread.sleep(3000);
			Utills.captureScreenshot("Updated_Info"+TodayDate.Date());
			getWebElement("EditContactinfoModalSaveButton").click();
			Utills.captureScreenshot("EditCartUpdatedSuccessMessage"+TodayDate.Date());
			
			Thread.sleep(3000);
			driver.navigate().refresh();
			Utills.captureScreenshot("EditCartUpdatedInfo"+TodayDate.Date());
			Thread.sleep(3000);
			String EditedFirstLastname = getWebElement("VerifyEditedFirstLastName").getText();
			String Editedmobileemail = getWebElement("VerifyEditedmobileemail").getText();
			String ExpectedFirstLastname = EditFirstName+" "+EditLastName;
			String Expectedmobileemail = EditMobileNumber+", "+EditEmailID;
			
			Assert.assertTrue("First and Last Nmae Contact Details Edit successful ",ExpectedFirstLastname.equals(EditedFirstLastname));
			Assert.assertTrue("Mobile and EmailID Contact Details Edit successful ",Expectedmobileemail.equals(Editedmobileemail));
			
			//Revert Edit
			getWebElement("CartEditContactInfoPenIcon").click();
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
			String EditFirstNameold = ExcelUtils.getCellData(2, 0);
			String EditLastNameold = ExcelUtils.getCellData(2, 1);
			String EditMobileNumberold = ExcelUtils.getCellData(2, 2);
			String EditEmailIDold = ExcelUtils.getCellData(2, 3);
			Thread.sleep(3000);
			getWebElement("EditContactinfoModalmobile").click();
			getWebElement("EditContactinfoModalmobile").clear();
			getWebElement("EditContactinfoModalmobile").sendKeys(EditMobileNumberold);
			//Thread.sleep(3000);
			getWebElement("EditContactinfoModalFirstname").click();
			getWebElement("EditContactinfoModalFirstname").clear();
			getWebElement("EditContactinfoModalFirstname").sendKeys(EditFirstNameold);
			//Thread.sleep(3000);
			getWebElement("EditContactinfoModalLastname").click();
			getWebElement("EditContactinfoModalLastname").clear();
			getWebElement("EditContactinfoModalLastname").sendKeys(EditLastNameold);
			//Thread.sleep(3000);
			getWebElement("EditContactinfoModalemail").click();
			//Thread.sleep(3000);
			getWebElement("EditContactinfoModalemail").clear();
			getWebElement("EditContactinfoModalemail").sendKeys(EditEmailIDold);
			//Thread.sleep(3000);
			//Utills.captureScreenshot("Updated_Info"+TodayDate.Date());
			getWebElement("EditContactinfoModalSaveButton").click();
			
		//}
		
		return true;
}
	//Validation Edit contact Details
		public boolean Validation_EditandverifyContactInfo() throws Exception
		{	
			Utills.captureScreenshot("Before_EditContactInfo_"+TodayDate.Date());
			//if(getWebElement("EditContactinfoModalTitle").isDisplayed()) {
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
			String EditFirstName = ExcelUtils.getCellData(1, 0);
			String EditLastName = ExcelUtils.getCellData(1, 1);
			String EditMobileNumber = ExcelUtils.getCellData(1, 2);
			String EditEmailID = ExcelUtils.getCellData(1, 3);
				
			getWebElement("CartEditContactInfoPenIcon").click();
			Utills.captureScreenshot("Validation_Before_EditContactInfo_"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditContactinfoModalFirstname").click();
				getWebElement("EditContactinfoModalFirstname").clear();
				getWebElement("EditContactinfoModalSaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditContactinfoModalFirstname").isDisplayed();
				Utills.captureScreenshot("Validation_Firstname_Info"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditContactinfoModalFirstname").sendKeys(EditFirstName);
				getWebElement("EditContactinfoModalLastname").click();
				getWebElement("EditContactinfoModalLastname").clear();
				getWebElement("EditContactinfoModalSaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditContactinfoModalLastname").isDisplayed();
				Utills.captureScreenshot("Validation_Lastname_Info"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditContactinfoModalLastname").sendKeys(EditLastName);
				getWebElement("EditContactinfoModalemail").click();
				getWebElement("EditContactinfoModalemail").clear();
				getWebElement("EditContactinfoModalSaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditContactinfoModalemail").isDisplayed();
				Utills.captureScreenshot("Validation_email_Info"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditContactinfoModalemail").sendKeys(EditEmailID);
				Thread.sleep(3000);
				Utills.captureScreenshot("Validation_Updated_Info"+TodayDate.Date());
				getWebElement("EditContactinfoModalCancelButton").click();
				return true;
	}
	
	//Edit BillingAddress
	public boolean EditBillingAddress() throws Exception
	{	
		Thread.sleep(3000);
		getWebElement("CartEditBillingAddressPenIcon").click();
		Utills.captureScreenshot("Before_EditBillingInfo_"+TodayDate.Date());
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
			String BOrganization = ExcelUtils.getCellData(2, 5);
			String BAddressLine1  = ExcelUtils.getCellData(2, 6);
			String BAddressLine2  = ExcelUtils.getCellData(2, 7);
			String BCity  = ExcelUtils.getCellData(2, 8);
			String BState  = ExcelUtils.getCellData(2, 9);
			String BCountry  = ExcelUtils.getCellData(2, 10);
			String BZipCode  = ExcelUtils.getCellData(2, 11);
			
			Thread.sleep(3000);
			getWebElement("EditBillingAddress_Organization").click();
			getWebElement("EditBillingAddress_Organization").clear();
			getWebElement("EditBillingAddress_Organization").sendKeys(BOrganization);
			Thread.sleep(3000);
			getWebElement("EditBillingAddress_AddressLine1").click();
			getWebElement("EditBillingAddress_AddressLine1").clear();
			getWebElement("EditBillingAddress_AddressLine1").sendKeys(BAddressLine1);
			Thread.sleep(3000);
			getWebElement("EditBillingAddress_AddressLine2").click();
			getWebElement("EditBillingAddress_AddressLine2").clear();
			getWebElement("EditBillingAddress_AddressLine2").sendKeys(BAddressLine2);
			Thread.sleep(3000);
			getWebElement("EditBillingAddress_City").click();
			getWebElement("EditBillingAddress_City").clear();
			getWebElement("EditBillingAddress_City").sendKeys(BCity);
			
			getWebElement("EditBillingAddress_ZipCode").click();
			getWebElement("EditBillingAddress_ZipCode").clear();
			getWebElement("EditBillingAddress_ZipCode").sendKeys(BZipCode);
			Thread.sleep(3000);
			Utills.captureScreenshot("Updated_Info"+TodayDate.Date());
			getWebElement("EditBillingAddress_SaveButton").click();
			Thread.sleep(1000);
			if(getWebElement("AddressEditSuccessmessgae").isDisplayed()) {
				Reporter.log("Billing Address Edited successfully.");
				Utills.captureScreenshot("EditCartBillingAddressSuccessMessage"+TodayDate.Date());
			}
			else {
				Reporter.log("Billing Address Edited Failed.");
				Utills.captureScreenshot("EditCartBillingAddressFailMessage"+TodayDate.Date());
			}
		//Resetting the Original values		
			Thread.sleep(3000);
			getWebElement("CartEditBillingAddressPenIcon").click();
			Utills.captureScreenshot("Before_EditBillingInfo_"+TodayDate.Date());
				ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
				String BOrganizationold = ExcelUtils.getCellData(1, 5);
				String BAddressLine1old  = ExcelUtils.getCellData(1, 6);
				String BAddressLine2old  = ExcelUtils.getCellData(1, 7);
				String BCityold  = ExcelUtils.getCellData(1, 8);
				String BStateold  = ExcelUtils.getCellData(1, 9);
				String BCountryold  = ExcelUtils.getCellData(1, 10);
				String BZipCodeold  = ExcelUtils.getCellData(1, 11);
				
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_Organization").click();
				getWebElement("EditBillingAddress_Organization").clear();
				getWebElement("EditBillingAddress_Organization").sendKeys(BOrganizationold);
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_AddressLine1").click();
				getWebElement("EditBillingAddress_AddressLine1").clear();
				getWebElement("EditBillingAddress_AddressLine1").sendKeys(BAddressLine1old);
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_AddressLine2").click();
				getWebElement("EditBillingAddress_AddressLine2").clear();
				getWebElement("EditBillingAddress_AddressLine2").sendKeys(BAddressLine2old);
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_City").click();
				getWebElement("EditBillingAddress_City").clear();
				getWebElement("EditBillingAddress_City").sendKeys(BCityold);
				getWebElement("EditBillingAddress_ZipCode").click();
				getWebElement("EditBillingAddress_ZipCode").clear();
				getWebElement("EditBillingAddress_ZipCode").sendKeys(BZipCodeold);
				Thread.sleep(3000);
				Utills.captureScreenshot("Updated_Info"+TodayDate.Date());
				getWebElement("EditBillingAddress_SaveButton").click();
				Thread.sleep(1000);
	
		return true;
	}	

	//Validation Edit BillingAddress
		public boolean Validation_EditBillingAddress() throws Exception
		{	
			Thread.sleep(3000);
			getWebElement("CartEditBillingAddressPenIcon").click();
			Utills.captureScreenshot("Before_EditBillingInfo_"+TodayDate.Date());
				ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
				String BOrganization = ExcelUtils.getCellData(2, 5);
				String BAddressLine1  = ExcelUtils.getCellData(2, 6);
				String BAddressLine2  = ExcelUtils.getCellData(2, 7);
				String BCity  = ExcelUtils.getCellData(2, 8);
				String BState  = ExcelUtils.getCellData(2, 9);
				String BCountry  = ExcelUtils.getCellData(2, 10);
				String BZipCode  = ExcelUtils.getCellData(2, 11);
				
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_Organization").click();
				getWebElement("EditBillingAddress_Organization").clear();
				getWebElement("EditBillingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditBillingAddress_Organization").isDisplayed();
				Utills.captureScreenshot("Validation_Organization"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_Organization").sendKeys(BOrganization);
				
				getWebElement("EditBillingAddress_AddressLine1").click();
				getWebElement("EditBillingAddress_AddressLine1").clear();
				getWebElement("EditBillingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditBillingAddress_AddressLine1").isDisplayed();
				Utills.captureScreenshot("Validation_AddressLine1"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_AddressLine1").sendKeys(BAddressLine1);
				
				getWebElement("EditBillingAddress_City").click();
				getWebElement("EditBillingAddress_City").clear();
				getWebElement("EditBillingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditBillingAddress_City").isDisplayed();
				Utills.captureScreenshot("Validation_City"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_City").sendKeys(BCity);
				
				getWebElement("EditBillingAddress_ZipCode").click();
				getWebElement("EditBillingAddress_ZipCode").clear();
				getWebElement("EditBillingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditBillingAddress_ZipCode").isDisplayed();
				Utills.captureScreenshot("Validation_Zipcode"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditBillingAddress_ZipCode").sendKeys(BZipCode);
				Thread.sleep(3000);
			
				getWebElement("EditBillingAddress_CancelButton").click();
				
			return true;
		}	

//Edit ShippingAddress
	public boolean EditShippingAddress() throws Exception
	{	
		Thread.sleep(3000);
		getWebElement("CartEditShippingAddressPenIcon").click();
		Utills.captureScreenshot("Before_EditShippingInfo_"+TodayDate.Date());
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
			String SOrganization = ExcelUtils.getCellData(2, 13);
			String SAttentiontoName = ExcelUtils.getCellData(2, 14);
			String SAddressLine1  = ExcelUtils.getCellData(2, 15);
			String SAddressLine2  = ExcelUtils.getCellData(2, 16);
			String SCity  = ExcelUtils.getCellData(2, 17);
			String SState  = ExcelUtils.getCellData(2, 18);
			String SCountry  = ExcelUtils.getCellData(2, 19);
			String SZipCode  = ExcelUtils.getCellData(2, 20);
			
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_Organization").click();
			getWebElement("EditShippingAddress_Organization").clear();
			getWebElement("EditShippingAddress_Organization").sendKeys(SOrganization);
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_NameAttentionTo").click();
			getWebElement("EditShippingAddress_NameAttentionTo").clear();
			getWebElement("EditShippingAddress_NameAttentionTo").sendKeys(SAttentiontoName);
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_AddressLine1").click();
			getWebElement("EditShippingAddress_AddressLine1").clear();
			getWebElement("EditShippingAddress_AddressLine1").sendKeys(SAddressLine1);
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_AddressLine2").click();
			getWebElement("EditShippingAddress_AddressLine2").clear();
			getWebElement("EditShippingAddress_AddressLine2").sendKeys(SAddressLine2);
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_City").click();
			getWebElement("EditShippingAddress_City").clear();
			getWebElement("EditShippingAddress_City").sendKeys(SCity);
			
			getWebElement("EditShippingAddress_ZipCode").click();
			getWebElement("EditShippingAddress_ZipCode").clear();
			getWebElement("EditShippingAddress_ZipCode").sendKeys(SZipCode);
			Thread.sleep(3000);
			Utills.captureScreenshot("Updated_Info"+TodayDate.Date());
			getWebElement("EditShippingAddress_SaveButton").click();
			Thread.sleep(1000);
			if(getWebElement("AddressEditSuccessmessgae").isDisplayed()) {
				Reporter.log("Shipping Address Edited successfully.");
				Utills.captureScreenshot("EditCartShippingAddressSuccessMessage"+TodayDate.Date());
			}
			else {
				Reporter.log("Shipping Address Edited Failed.");
				Utills.captureScreenshot("EditCartShippingAddressFailMessage"+TodayDate.Date());
			}
		//Resetting the Original values		
			Thread.sleep(3000);
			getWebElement("CartEditShippingAddressPenIcon").click();
			ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
			String SOrganizationOld = ExcelUtils.getCellData(1, 13);
			String SAttentiontoNameOld = ExcelUtils.getCellData(1, 14);
			String SAddressLine1Old  = ExcelUtils.getCellData(1, 15);
			String SAddressLine2Old  = ExcelUtils.getCellData(1, 16);
			String SCityOld  = ExcelUtils.getCellData(1, 17);
			String SStateOld  = ExcelUtils.getCellData(1, 18);
			String SCountryOld  = ExcelUtils.getCellData(1, 19);
			String SZipCodeOld  = ExcelUtils.getCellData(1, 20);
			
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_Organization").click();
			getWebElement("EditShippingAddress_Organization").clear();
			getWebElement("EditShippingAddress_Organization").sendKeys(SOrganizationOld);
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_NameAttentionTo").click();
			getWebElement("EditShippingAddress_NameAttentionTo").clear();
			getWebElement("EditShippingAddress_NameAttentionTo").sendKeys(SAttentiontoNameOld);
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_AddressLine1").click();
			getWebElement("EditShippingAddress_AddressLine1").clear();
			getWebElement("EditShippingAddress_AddressLine1").sendKeys(SAddressLine1Old);
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_AddressLine2").click();
			getWebElement("EditShippingAddress_AddressLine2").clear();
			getWebElement("EditShippingAddress_AddressLine2").sendKeys(SAddressLine2Old);
			Thread.sleep(3000);
			getWebElement("EditShippingAddress_City").click();
			getWebElement("EditShippingAddress_City").clear();
			getWebElement("EditShippingAddress_City").sendKeys(SCityOld);
			
			getWebElement("EditShippingAddress_ZipCode").click();
			getWebElement("EditShippingAddress_ZipCode").clear();
			getWebElement("EditShippingAddress_ZipCode").sendKeys(SZipCodeOld);
			Thread.sleep(3000);
			Utills.captureScreenshot("Updated_Info"+TodayDate.Date());
			getWebElement("EditShippingAddress_SaveButton").click();
			Thread.sleep(1000);
	
		return true;
	}	

	//Validation Edit ShippingAddress
		public boolean Validation_EditShippingAddress() throws Exception
		{	
			Thread.sleep(3000);
			getWebElement("CartEditShippingAddressPenIcon").click();
			Utills.captureScreenshot("Before_EditShippingInfo_"+TodayDate.Date());
				ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Edit_Info");
				String SOrganization = ExcelUtils.getCellData(2, 13);
				String SAttentiontoName = ExcelUtils.getCellData(2, 14);
				String SAddressLine1  = ExcelUtils.getCellData(2, 15);
				String SAddressLine2  = ExcelUtils.getCellData(2, 16);
				String SCity  = ExcelUtils.getCellData(2, 17);
				String SState  = ExcelUtils.getCellData(2, 18);
				String SCountry  = ExcelUtils.getCellData(2, 19);
				String SZipCode  = ExcelUtils.getCellData(2, 20);
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_Organization").click();
				getWebElement("EditShippingAddress_Organization").clear();
				getWebElement("EditShippingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditShippingAddress_Organization").isDisplayed();
				Utills.captureScreenshot("Validation1_Organization"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_Organization").sendKeys(SOrganization);
				
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_NameAttentionTo").click();
				getWebElement("EditShippingAddress_NameAttentionTo").clear();
				getWebElement("EditShippingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditShippingAddress_NameAttentionTo").isDisplayed();
				Utills.captureScreenshot("Validation1_NameAttentionTo"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_NameAttentionTo").sendKeys(SAttentiontoName);
				
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_AddressLine1").click();
				getWebElement("EditShippingAddress_AddressLine1").clear();
				getWebElement("EditShippingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditShippingAddress_AddressLine1").isDisplayed();
				Utills.captureScreenshot("Validation1_AddressLine1"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_AddressLine1").sendKeys(SAddressLine1);
				
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_City").click();
				getWebElement("EditShippingAddress_City").clear();
				getWebElement("EditShippingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditShippingAddress_City").isDisplayed();
				Utills.captureScreenshot("Validation1_City"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_City").sendKeys(SCity);
				
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_ZipCode").click();
				getWebElement("EditShippingAddress_ZipCode").clear();
				getWebElement("EditShippingAddress_SaveButton").click();
				Thread.sleep(1000);
				getWebElement("ValidationMessageEditShippingAddress_ZipCode").isDisplayed();
				Utills.captureScreenshot("Validation1_Zipcode"+TodayDate.Date());
				Thread.sleep(3000);
				getWebElement("EditShippingAddress_ZipCode").sendKeys(SZipCode);
				Thread.sleep(3000);
				
				getWebElement("EditShippingAddress_CancelButton").click();
				
				return true;
		}	
	
//Purchase and Checkout with Visa, Master card and American Express Credit card 
public void CreditCardPurchase(String CardName) throws Exception
{
	String StartNumber;
	String EndNumber;
	String CreditCardNumber;
	String CVV;
	String Name;
	String ExpiryDate;
	
	 switch (CardName) 
        {
            case "Visa":  StartNumber = "4";
	            //Thread.sleep(3000); 
	    		EndNumber = "123456789101234";
	    		CreditCardNumber = StartNumber+EndNumber;
	    		CVV = "456";
	    		Name = "Ram Visa Card";
	    		ExpiryDate = "3112"; 
	    		getWebElement("CreditCardNumber").click();
	    		getWebElement("CreditCardNumber").sendKeys(CreditCardNumber);
	    		getWebElement("CreditCardholderName").click();
	    		getWebElement("CreditCardholderName").sendKeys(Name);
	    		getWebElement("CreditCardCVV").sendKeys(CVV);
	    		for(int i=0;i<ExpiryDate.length();i++) {
	    			char ExpDate = ExpiryDate.charAt(i);
	    			String ExpyDate = String.valueOf(ExpDate);
	    			getWebElement("CreditCardExpiry").sendKeys(ExpyDate);
	    			Thread.sleep(5000);
	    		}
	    		if(getWebElement("VisaCardImage").isDisplayed()){
	    			Reporter.log("Visa card Logo is getting Displayed successfully.");
					Utills.captureScreenshot("VisaCreditCard"+TodayDate.Date());
	    		}
	    		else {
	    			Reporter.log("Visa card Logo is Not getting Displayed successfully.");
					Utills.captureScreenshot("NoVisaCreditCard"+TodayDate.Date());
	    		}
	    		getWebElement("PurchaseButton").click();
	    		Thread.sleep(5000); 
	    		if(getWebElement("OrderCompleteMessage").isDisplayed()) {
	    			Reporter.log("Purchase Completed through Visa Credit card successfully.");
					Utills.captureScreenshot("VisaCreditCardSuccess"+TodayDate.Date());
	    		}
	    		else {
	    			Reporter.log("Purchase unsuccessful through Visa Credit card .");
					Utills.captureScreenshot("VisaCreditCardFail"+TodayDate.Date());
	    		}
	    		break;
	    		
	    		
            case "MasterCard":  StartNumber = "5";
	            Thread.sleep(3000); 
	    		EndNumber = "123456789101234";
	    		CreditCardNumber = StartNumber+EndNumber;
	    		CVV = "456";
	    		Name = "Ram Master Card";
	    		ExpiryDate = "3112"; 
	    		getWebElement("CreditCardNumber").click();
	    		getWebElement("CreditCardNumber").sendKeys(CreditCardNumber);
	    		getWebElement("CreditCardholderName").click();
	    		getWebElement("CreditCardholderName").sendKeys(Name);
	    		getWebElement("CreditCardCVV").sendKeys(CVV);
	    		for(int i=0;i<ExpiryDate.length();i++) {
	    			char ExpDate = ExpiryDate.charAt(i);
	    			String ExpyDate = String.valueOf(ExpDate);
	    			getWebElement("CreditCardExpiry").sendKeys(ExpyDate);
	    			Thread.sleep(5000);
	    		}
	    		if(getWebElement("MasterCardImage").isDisplayed()){
	    			Reporter.log("Master card Logo is getting Displayed successfully.");
					Utills.captureScreenshot("MasterCreditCard"+TodayDate.Date());
	    		}
	    		else {
	    			Reporter.log("Master card Logo is Not getting Displayed successfully.");
					Utills.captureScreenshot("NoMasterCreditCard"+TodayDate.Date());
	    		}
	    		getWebElement("PurchaseButton").click();
	    		Thread.sleep(5000); 
	    		if(getWebElement("OrderCompleteMessage").isDisplayed()) {
	    			Reporter.log("Purchase Completed through Master Credit card successfully.");
					Utills.captureScreenshot("MasterCreditCardSuccess"+TodayDate.Date());
	    		}
	    		else {
	    			Reporter.log("Purchase unsuccessful through Master Credit card .");
					Utills.captureScreenshot("MasterCreditCardFail"+TodayDate.Date());
	    		}
	    		break;
	    		
            case "AmericanExpress":  StartNumber = "3";
	            Thread.sleep(3000); 
	    		EndNumber = "12345678910123";
	    		CreditCardNumber = StartNumber+EndNumber;
	    		CVV = "4567";
	    		Name = "Ram AmericanExpress Card";
	    		ExpiryDate = "3112"; 
	    		getWebElement("CreditCardNumber").click();
	    		getWebElement("CreditCardNumber").sendKeys(CreditCardNumber);
	    		getWebElement("CreditCardholderName").click();
	    		getWebElement("CreditCardholderName").sendKeys(Name);
	    		getWebElement("CreditCardCVV").sendKeys(CVV);
	    		for(int i=0;i<ExpiryDate.length();i++) {
	    			char ExpDate = ExpiryDate.charAt(i);
	    			String ExpyDate = String.valueOf(ExpDate);
	    			getWebElement("CreditCardExpiry").sendKeys(ExpyDate);
	    			Thread.sleep(5000);
	    		}
	    		if(getWebElement("AmericanExpressCardImage").isDisplayed()){
	    			Reporter.log("AmericanExpress card Logo is getting Displayed successfully.");
					Utills.captureScreenshot("AmericanExpressCreditCard"+TodayDate.Date());
	    		}
	    		else {
	    			Reporter.log("AmericanExpress card Logo is Not getting Displayed successfully.");
					Utills.captureScreenshot("NoAmericanExpressCreditCard"+TodayDate.Date());
	    		}
	    		getWebElement("PurchaseButton").click();
	    		Thread.sleep(5000); 
	    		if(getWebElement("OrderCompleteMessage").isDisplayed()) {
	    			Reporter.log("Purchase Completed through AmericanExpress Credit card successfully.");
					Utills.captureScreenshot("AmericanExpressCreditCardSuccess"+TodayDate.Date());
	    		}
	    		else {
	    			Reporter.log("Purchase unsuccessful through AmericanExpress Credit card .");
					Utills.captureScreenshot("AmericanExpressCreditCardFail"+TodayDate.Date());
	    		}
	    		break;
        }
}	

//Purchase and checkout with Purchase Order
	public boolean CheckoutwithPurchaseOrder() throws Exception
	{
		String PONumber;
		String RequestionNo;
		PONumber = "1234567890";
		RequestionNo = "3112"; 
		getWebElement("PurchaseOrderNumber").click();
		getWebElement("PurchaseOrderNumber").sendKeys(PONumber);
		getWebElement("RequistionNumber").click();
		getWebElement("RequistionNumber").sendKeys(RequestionNo);
		Utills.captureScreenshot("PODetailsEntered_"+TodayDate.Date());
		getWebElement("PurchaseButton").click();
		Thread.sleep(5000); 
		if(getWebElement("OrderCompleteMessage").isDisplayed()) {
			Reporter.log("Purchase Completed through Purchase Order successfully.");
			Utills.captureScreenshot("PurchaseOrderSuccess"+TodayDate.Date());
		}
		else {
			Reporter.log("Purchase unsuccessful through Purchase Order.");
			Utills.captureScreenshot("PurchaseOrderFail"+TodayDate.Date());
		}
		return true;
	}
	
	//Open the SigmaAldrich website by clicking on the Product name Link
	public void VerifySigmaAldrichPage() throws Exception
	{
		String Namefield = getWebElement("SingleProductintheCart_Details").getText();
		String[] Splitthename = Namefield.split(" "); 
		String ProductName = null;
		
		/*for (int i=0;i<Splitthename.length;i++) {
			if(Splitthename[i].equals("|"))
				break;
			else
			ProductName = ProductName+" "+Splitthename[i];
			//System.out.println("Splitthename["+i+"]"+Splitthename[i]);
		}*/
		String catalogNumber = Splitthename[Splitthename.length-1];
		
		System.out.println("Namefield"+Namefield);
		System.out.println("ProductName"+ProductName);
		System.out.println("catalogNumber"+catalogNumber);
		getWebElement("Checkoutpage_ProductNamelink").click();
		Thread.sleep(5000);
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Reporter.log("A new tab opened successfully when clicked on Clicking on Product name with Catalog Number - "+catalogNumber);
				
		//Verify the tab name
		Reporter.log("--------------------------------------------------------------------------------------------------");
		Reporter.log("Verify Page title contains the text SigmaAldrich");
		String pageTitle = driver.getTitle();
		if(pageTitle.contains("Sigma-Aldrich"))
			Reporter.log("Page title displayed successfully as - '"+pageTitle+"'.");
		/*
			else
			softAssertion.fail("Page title not displayed with expected Catalog Number - "+catalogNumber+". The Page title displayed as -"+pageTitle);
		
		//verify the URL contains the catalog number
		Reporter.log("--------------------------------------------------------------------------------------------------");
		Reporter.log("Verify URL contains the Catalog Number");
		String expectedURLCatalogNumber = "productNumber="+catalogNumber;
		String actualPageURL = driver.getCurrentUrl();
		if(actualPageURL.contains(expectedURLCatalogNumber))
			Reporter.log("URL contains the catalog number - "+catalogNumber);
		else
			softAssertion.fail("URL doesnot contains Catalog Number. The URL displayed as - "+actualPageURL);
		
		//Verify bread-crumb displayed with catalog number.
		Reporter.log("--------------------------------------------------------------------------------------------------");
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
			*/
		Thread.sleep(2000);
		Utills.captureScreenshot("Product_Details_Page_is_Displayed"+TodayDate.Date());
		driver.close();
		Reporter.log("--------------------------------------------------------------------------------------------------");
	
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
		Thread.sleep(2000);
		
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
		Thread.sleep(2000);
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
	
	
}
