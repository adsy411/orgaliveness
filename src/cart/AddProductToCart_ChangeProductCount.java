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

public class AddProductToCart_ChangeProductCount extends TestBase
{
	@Test
	public void AddProducttocartchangeQuantity() throws Exception
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
//Check if cart is empty
CartRegularFunctions isCartempty = new CartRegularFunctions();
Boolean Res =  isCartempty.isCartEmpty();
if(Res == true) {
	//System.out.println("isEmpty() res is TRUE");

		//Navigate to Request Page
		CartRegularFunctions NavigatetoRequestPage = new CartRegularFunctions();
		NavigatetoRequestPage.RequestPageNavigation();
		Thread.sleep(5000);
						
		//Search products in Request page and add them to cart
		CartRegularFunctions SearchandAddProducttoCart = new CartRegularFunctions();
		SearchandAddProducttoCart.SearchAndAddProducttoCart();
		
		//Navigate to cart Checkout Page
		CartRegularFunctions GotoCheckoutpage = new CartRegularFunctions();
		GotoCheckoutpage.Cartcheckoutpagenavigation();
		
		//Verify Cart Page
		CartRegularFunctions VerifyCartPage = new CartRegularFunctions();
		VerifyCartPage.VerifyCartpageisdisplayed();
		
		//Update product count and Save
		CartRegularFunctions UpdateProductCount = new CartRegularFunctions();
		UpdateProductCount.UpdateProductCount();
}
else {
	//System.out.println("isEmpty() res is FALSE");
	/*//Navigate to cart Checkout Page No need to Do this, it will be handled in the Empty cart function
			CartRegularFunctions GotoCheckoutpage = new CartRegularFunctions();
			GotoCheckoutpage.Cartcheckoutpagenavigation();*/
			
			//Clear Cart by deleting cart Items
			CartRegularFunctions DeleteCartItems = new CartRegularFunctions();
			DeleteCartItems.EmptyCart();
			
			//Navigate to Request Page
			CartRegularFunctions NavigatetoRequestPage = new CartRegularFunctions();
			NavigatetoRequestPage.RequestPageNavigation();
			Thread.sleep(5000);
							
			//Search products in Request page and add them to cart
			CartRegularFunctions SearchandAddProducttoCart = new CartRegularFunctions();
			SearchandAddProducttoCart.SearchAndAddProducttoCart();
			
			//Navigate to cart Checkout Page
			CartRegularFunctions GotoCheckoutpagen = new CartRegularFunctions();
			GotoCheckoutpagen.Cartcheckoutpagenavigation();
			
			//Verify Cart Page
			CartRegularFunctions VerifyCartPage = new CartRegularFunctions();
			VerifyCartPage.VerifyCartpageisdisplayed();
			
			//Update product count and Save
			CartRegularFunctions UpdateProductCount = new CartRegularFunctions();
			UpdateProductCount.UpdateProductCount();
}			
		//Logout from an application.
		CartRegularFunctions logout = new CartRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
		
	}
}