package labCatalog;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.RequestConstants;
import utills.Utills;
import pageLibrary.Library;
public class Add_SigmaAldrich_ProducttoCatalog extends TestBase {

	@Test
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner() throws Exception {
		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
			
		//Login in to application
		init();
		LabCatalogRegularFunctions login = new LabCatalogRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Quick Order Add New SA Product DeleteProduct
		LabCatalogRegularFunctions AddNewProd = new LabCatalogRegularFunctions();
		AddNewProd.AddSigmaAldrichProduct();
		
		//Quick Order Add New SA Product DeleteProduct
		LabCatalogRegularFunctions DeleteProd = new LabCatalogRegularFunctions();
		DeleteProd.DeleteProduct();
				
		//Logout from an application.
		LabCatalogRegularFunctions logout = new LabCatalogRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();	
}

	@Test
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager() throws Exception {

		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 3);
		String password = ExcelUtils.getCellData(2, 4);
			
		//Login in to application
		init();
		LabCatalogRegularFunctions login = new LabCatalogRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Quick Order Add New SA Product DeleteProduct
		LabCatalogRegularFunctions AddNewProd = new LabCatalogRegularFunctions();
		AddNewProd.AddSigmaAldrichProduct();
		
		//Quick Order Add New SA Product DeleteProduct
		LabCatalogRegularFunctions DeleteProd = new LabCatalogRegularFunctions();
		DeleteProd.DeleteProduct();
				
		//Logout from an application.
		LabCatalogRegularFunctions logout = new LabCatalogRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}

	@Test
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember() throws Exception {

		SoftAssert softAssertion= new SoftAssert();
		//Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(RequestConstants.Path_TestData + RequestConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 6);
		String password = ExcelUtils.getCellData(2, 7);
			
		//Login in to application
		init();
		LabCatalogRegularFunctions login = new LabCatalogRegularFunctions();
		login.UserLogin(userName,password);			
		
		//Quick Order Add New SA Product DeleteProduct
		LabCatalogRegularFunctions AddNewProd = new LabCatalogRegularFunctions();
		AddNewProd.AddSigmaAldrichProduct();
		
		//Quick Order Add New SA Product DeleteProduct
		LabCatalogRegularFunctions DeleteProd = new LabCatalogRegularFunctions();
		DeleteProd.DeleteProduct();
				
		//Logout from an application.
		LabCatalogRegularFunctions logout = new LabCatalogRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}