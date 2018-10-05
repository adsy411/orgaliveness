package inventory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class Materials_Page_Filter_Search_Expiration_Date extends TestBase
{
	@Test(priority = 1)
	public void Filter_Search_Expired() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 54;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String expirationDate = ExcelUtils.getCellData(rowNumber, 9);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Verify the expiration date filter section in filter modal
		InventoryRegularFunctions verifyExpirationDateFilterSection = new InventoryRegularFunctions();
		verifyExpirationDateFilterSection.Verify_Expire_Date_Filter_Search();
		
		//Select the expired - expiration date filter
		getWebElement("Inventory.FilterSearch.ExpirationDate.Expired").click();
		Thread.sleep(1000);
		
		//Clicks on Apply button
		getWebElement("Inventory.FilterSearch.ApplyButton").click();
		Thread.sleep(7000);
		
		//Verify materials page displayed after filter search
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After filter search, Materials page not displayed", materialsPageName.equals("Materials"));
		//Reporter.log("After filter search, Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = sdf.format(date);
		
		/*int expireDate = Integer.parseInt(expirationDate.replaceAll("/",""));
		int todayDate = Integer.parseInt(dateNow.replaceAll("/",""));*/
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//verify the material displayed based on filter search
		/*if(expireDate <= todayDate)
		{
			String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
			Assert.assertTrue("After filter search for expiratin date - 'expired' type, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equals(materialName));
			Reporter.log("After filter search for expiratin date - 'expired' type, the material - '"+materialName+"' displayed in searched materials page.");
		}
		else if(materialsCountAfter > 0)
		{
			//Navigation to material detail page to check the expiration date
			getWebElement("Inventory.MaterialDetailNavigation").click();
			impliciteWait(5);
			Thread.sleep(1000);
				
			//Navigation to other details modal
			getWebElement("Inventory.OtherDetailsTab").click();
			Thread.sleep(2000);
			String detailPageExpirationDate = getWebElement("Inventory.MaterialDetail.ExpirationDate").getAttribute("Value").trim();
			String monthSplit = detailPageExpirationDate.split("/")[0];
			String daySplit = detailPageExpirationDate.split("/")[1];
			String yearSplit = detailPageExpirationDate.split("/")[2];
				
			char month = monthSplit.charAt(0);
			char day = daySplit.charAt(0);
			if(month == '0')
				monthSplit =  monthSplit.replaceFirst("0","");
			if(day == '0')
				daySplit =  daySplit.replaceFirst("0","");
			
			detailPageExpirationDate = 	monthSplit + daySplit + yearSplit;
			System.out.println("detailPageExpirationDate after replace 1st = "+detailPageExpirationDate);
			int detailPageExpireDate = Integer.parseInt(detailPageExpirationDate);
			Assert.assertTrue("Material displayed is not proper for searched expiration date scenario.", (detailPageExpireDate <= todayDate));
			Reporter.log("Proper material displayed for searched expiration date scenario.");
		}
		else
			Reporter.log("Materials not exist for searched expiration date.");*/
		
		if(materialsCountAfter > 0)
		{
			String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
			Assert.assertTrue("After filter search for expiratin date - 'expired' type, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equals(materialName));
			Reporter.log("After filter search for expiratin date - 'expired' type, the material - '"+materialName+"' displayed in searched materials page.");
		}
		else
			Reporter.log("Materials not exist for searched expiration date.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 2)
	public void Filter_Search_NotExpired() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 55;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String expirationDate = ExcelUtils.getCellData(rowNumber, 9);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Verify the expiration date filter section in filter modal
		InventoryRegularFunctions verifyExpirationDateFilterSection = new InventoryRegularFunctions();
		verifyExpirationDateFilterSection.Verify_Expire_Date_Filter_Search();
		
		//Select the Not Expired - expiration date filter
		getWebElement("Inventory.FilterSearch.ExpirationDate.NotExpired").click();
		Thread.sleep(1000);
		
		//Clicks on Apply button
		getWebElement("Inventory.FilterSearch.ApplyButton").click();
		Thread.sleep(7000);
		
		//Verify materials page displayed after filter search
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After filter search, Materials page not displayed", materialsPageName.equals("Materials"));
		//Reporter.log("After filter search, Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = sdf.format(date);
		
		/*int expireDate = Integer.parseInt(expirationDate.replaceAll("/",""));
		int todayDate = Integer.parseInt(dateNow.replaceAll("/",""));*/
		
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//verify the material displayed based on filter search
		/*if(expireDate > todayDate)
		{
			String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
			Assert.assertTrue("After filter search for expiratin date - 'Not Expired' type, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equals(materialName));
			Reporter.log("After filter search for expiratin date - 'Not Expired' type, the material - '"+materialName+"' displayed in searched materials page.");
		}
		else if(materialsCountAfter > 0)
		{
			//Navigation to material detail page to check the expiration date
			getWebElement("Inventory.MaterialDetailNavigation").click();
			impliciteWait(5);
			Thread.sleep(1000);
				
			//Navigation to other details modal
			getWebElement("Inventory.OtherDetailsTab").click();
			Thread.sleep(2000);
			String detailPageExpirationDate = getWebElement("Inventory.MaterialDetail.ExpirationDate").getAttribute("Value").trim();
			String monthSplit = detailPageExpirationDate.split("/")[0];
			String daySplit = detailPageExpirationDate.split("/")[1];
			String yearSplit = detailPageExpirationDate.split("/")[2];
				
			char month = monthSplit.charAt(0);
			char day = daySplit.charAt(0);
			if(month == '0')
				monthSplit =  monthSplit.replaceFirst("0","");
			if(day == '0')
				daySplit =  daySplit.replaceFirst("0","");
			
			detailPageExpirationDate = 	monthSplit + daySplit + yearSplit;
			System.out.println("detailPageExpirationDate after replace 1st = "+detailPageExpirationDate);
			int detailPageExpireDate = Integer.parseInt(detailPageExpirationDate);
			Assert.assertTrue("Material displayed is not proper for searched expiration date scenario.", (detailPageExpireDate > todayDate));
			Reporter.log("Proper material displayed for searched expiration date scenario.");
		}
		else
			Reporter.log("Materials not exist for searched expiration date.");
		*/
		if(materialsCountAfter > 0)
		{
			String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
			Assert.assertTrue("After filter search for expiratin date - 'Not Expired' type, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equals(materialName));
			Reporter.log("After filter search for expiratin date - 'Not Expired' type, the material - '"+materialName+"' displayed in searched materials page.");
		
		}
		else
			Reporter.log("Materials not exist for searched expiration date.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
	
	@Test(priority = 3)
	public void Filter_Search_ExpiresInNext30Days() throws Exception
	{
		SoftAssert softAssertion= new SoftAssert();
		Library TodayDate = new Library();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		int rowNumber = 56;
		
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			

		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Add_ThirdPartyMaterial");
		String materialName = ExcelUtils.getCellData(rowNumber, 0);
		String expirationDate = ExcelUtils.getCellData(rowNumber, 9);
		
		//Adding third party Vendor material
		AddThirdPartyMaterial AddMaterial = new AddThirdPartyMaterial();
		AddMaterial.AddThirdPartyVendorMaterial(rowNumber);
		Thread.sleep(1000);
		
		//Verify the expiration date filter section in filter modal
		InventoryRegularFunctions verifyExpirationDateFilterSection = new InventoryRegularFunctions();
		verifyExpirationDateFilterSection.Verify_Expire_Date_Filter_Search();
		
		//Select the Expires In 30 Days - expiration date filter
		getWebElement("Inventory.FilterSearch.ExpirationDate.ExpiresInNext30Days").click();
		Thread.sleep(1000);
		
		//Clicks on Apply button
		getWebElement("Inventory.FilterSearch.ApplyButton").click();
		Thread.sleep(7000);
		
		//Verify materials page displayed after filter search
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After filter search, Materials page not displayed", materialsPageName.equals("Materials"));
		//Reporter.log("After filter search, Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_"+TodayDate.Date());
		
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = sdf.format(date);
		Calendar cal = Calendar.getInstance();
		
		//Adding 30 days to current date
		cal.add(Calendar.DAY_OF_MONTH, 30); 
		String newDate = sdf.format(cal.getTime());  
		/*int next30DaysDate = Integer.parseInt(newDate.replaceAll("/",""));
		
		int expireDate = Integer.parseInt(expirationDate.replaceAll("/",""));
		int todayDate = Integer.parseInt(dateNow.replaceAll("/",""));*/
				
		//Take the material count after material search
		int materialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		//verify the material displayed based on filter search
		/*if(expireDate > todayDate && expireDate <= next30DaysDate)
		{
			String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
			Assert.assertTrue("After filter search for expiratin date - 'Expires In Next 30 days' type, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equals(materialName));
			Reporter.log("After filter search for expiratin date - 'Expires In Next 30 days' type, the material - '"+materialName+"' displayed in searched materials page.");
		}
		else if(materialsCountAfter > 0)
		{
			//Navigation to material detail page to check the expiration date
			getWebElement("Inventory.MaterialDetailNavigation").click();
			impliciteWait(5);
			Thread.sleep(1000);
				
			//Navigation to other details modal
			getWebElement("Inventory.OtherDetailsTab").click();
			Thread.sleep(2000);
			String detailPageExpirationDate = getWebElement("Inventory.MaterialDetail.ExpirationDate").getAttribute("Value").trim();
			String monthSplit = detailPageExpirationDate.split("/")[0];
			String daySplit = detailPageExpirationDate.split("/")[1];
			String yearSplit = detailPageExpirationDate.split("/")[2];
				
			char month = monthSplit.charAt(0);
			char day = daySplit.charAt(0);
			if(month == '0')
				monthSplit =  monthSplit.replaceFirst("0","");
			if(day == '0')
				daySplit =  daySplit.replaceFirst("0","");
			
			detailPageExpirationDate = 	monthSplit + daySplit + yearSplit;
			System.out.println("detailPageExpirationDate after replace 1st = "+detailPageExpirationDate);
			int detailPageExpireDate = Integer.parseInt(detailPageExpirationDate);
			Assert.assertTrue("Material displayed is not proper for searched expiration date scenario.", (detailPageExpireDate > todayDate && detailPageExpireDate <= next30DaysDate));
			Reporter.log("Proper material displayed for searched expiration date scenario.");
		}
		else
			Reporter.log("Materials not exist for searched expiration date.");*/
		
		if(materialsCountAfter > 0)
		{
			String searchedListMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText().trim();
			Assert.assertTrue("After filter search for expiratin date - 'Expires In Next 30 days' type, the material - "+materialName+" not displayed in searched materials page.",searchedListMaterialName.equals(materialName));
			Reporter.log("After filter search for expiratin date - 'Expires In Next 30 days' type, the material - '"+materialName+"' displayed in searched materials page.");
		}
		else
			Reporter.log("Materials not exist for searched expiration date.");
		
		//Logout from an application.
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
		softAssertion.assertAll();
	}
}
