package inventory;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.InventoryConstants;
import utills.Utills;

public class TestClass extends TestBase
{
	/*@Test
	public void CardViewDeleteMaterial() throws Exception
	{
		String userName, password;
		for(int i=75;i<=100;i++)
		{
			init();
			
			
			//Fetch the user name and password
			ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Sheet1");
			userName = ExcelUtils.getCellData(i, 0);
			password = ExcelUtils.getCellData(i, 1);
			
			getWebElement("Enotebook.login.username").click();
			getWebElement("Enotebook.login.username").sendKeys(userName);
			getWebElement("Enotebook.login.password").click();
			getWebElement("Enotebook.login.password").sendKeys(password);
			Thread.sleep(2000);
			getWebElement("Enotebook.login.loginButton").click();
			impliciteWait(5);
			
			getWebElement("Inventory.UserIcon").click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@class='switch-menu']//a[@data-toggle='modal']")).click();
			Thread.sleep(4000);
			if(driver.findElement(By.xpath("//p[@class='text-primary'][contains(text(),'merck qa')]")).isDisplayed())
			{
				driver.findElement(By.xpath("//p[contains(text(),'merck qa')]//ancestor::tr//i[@class='fa fa-circle-o fa-2x']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//p[@class='text-primary'][contains(text(),'merck qa')]//ancestor::tr//a[contains(text(),'Go To Lab')]")).click();
				Thread.sleep(3000);
			}
			
			//Logout from an application.
			InventoryRegularFunctions logout = new InventoryRegularFunctions();
			logout.UserLogout();
			driver.quit();
		}
		
		*/
		
		/*//Navigation to Materials Page. Verify materials page exists or not.
		InvenoryRegularFunctions materialsPageNavigation = new InvenoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();
		
				
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		
		for(int i =0;i<materialsCountBefore;i++)
		{
		//Navigation to dispose modal
				getWebElement("Inventory.CardView.DisposeMaterial").click();
				Thread.sleep(3000);
					
				//Deletes a material
				getWebElement("Inventory.CardView.DisposeModal.DeleteRadioButton").click();
				Thread.sleep(1000);
				getWebElement("Inventory.DisposeButton").click();
				impliciteWait(2);
		}
	}*/
	@Test	
	public void test1() throws Exception
	{
		//Navigation to Materials Page. Verify materials page exists or not.
		InventoryRegularFunctions inventoryRegularFunctions = new InventoryRegularFunctions();
		
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
		
		init();
		inventoryRegularFunctions.UserLogin(userName,password);
		inventoryRegularFunctions.MaterialPageNavigation();
		//inventoryRegularFunctions.DisposedMaterialsPageNavigation();
		//Thread.sleep(5000);
		
		for(int i=0;i<=575;i++)
		{
			Thread.sleep(2000);
			inventoryRegularFunctions.DisposedMaterialsPageNavigation();
			Thread.sleep(5000);
			//Re-activation of disposed material
			getWebElement("Inventory.CardView.EditQuantityIcon").click();
			Thread.sleep(1000);
			getWebElement("Inventory.CardView.EditQuantity").click();
			getWebElement("Inventory.CardView.EditQuantity").clear();
			getWebElement("Inventory.CardView.EditQuantity").sendKeys("3");
			Thread.sleep(1000);
			getWebElement("Inventory.CardView.EditQuantity.OkButton").click();
			Thread.sleep(5000);
			driver.navigate().refresh();
		}
	}
}


