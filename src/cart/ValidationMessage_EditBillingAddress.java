package cart;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.Reporter;
//import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
//import utills.Utills;
import utills.RequestConstants;
import utills.Utills;

public class ValidationMessage_EditBillingAddress extends TestBase
{
	@Test
	public void ValidationMessage_EditBillingInfo() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(3, 0);
		String password = ExcelUtils.getCellData(3, 1);
			
		//Login in to application
		init();
		CartRegularFunctions login = new CartRegularFunctions();
		login.UserLogin(userName,password);			

		//Navigate to Request Page
		CartRegularFunctions NavigatetoRequestPage = new CartRegularFunctions();
		NavigatetoRequestPage.RequestPageNavigation();
		Thread.sleep(5000);
		
		//Check if cart is empty
		CartRegularFunctions isCartempty = new CartRegularFunctions();
		Boolean Res =  isCartempty.isCartEmpty();
		if(Res == false) {
			//Navigate to cart Checkout Page
			CartRegularFunctions GotoCheckoutpage = new CartRegularFunctions();
			GotoCheckoutpage.Cartcheckoutpagenavigation();
			
			//Verify Cart Page
			CartRegularFunctions VerifyCartPage = new CartRegularFunctions();
			VerifyCartPage.VerifyCartpageisdisplayed();
			
			//Edit Contact Info
			CartRegularFunctions EditContactInfo = new CartRegularFunctions();
			EditContactInfo.Validation_EditBillingAddress();
		}
		else {
			//Search products in Request page and add them to cart
			CartRegularFunctions SearchandAddProducttoCart = new CartRegularFunctions();
			SearchandAddProducttoCart.SearchAndAddProducttoCart();
			
			//Navigate to cart Checkout Page
			CartRegularFunctions GotoCheckoutpage = new CartRegularFunctions();
			GotoCheckoutpage.Cartcheckoutpagenavigation();
			
			//Verify Cart Page
			CartRegularFunctions VerifyCartPage = new CartRegularFunctions();
			VerifyCartPage.VerifyCartpageisdisplayed();
			
			//Edit Contact Info
			CartRegularFunctions EditContactInfo = new CartRegularFunctions();
			EditContactInfo.Validation_EditBillingAddress();
		}
		//Logout from an application.
		CartRegularFunctions logout = new CartRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
	}
}