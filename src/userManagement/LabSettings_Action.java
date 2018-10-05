package userManagement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class LabSettings_Action extends TestBase{
	
	@Test
	public void LabSettings() throws Exception{
		
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to Lab Settings");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabSettings").click();
		
		Reporter.log("Edit Labname and Save then Verify the Lab Name in Lab Settings page and Header Drop Down");
		Thread.sleep(3000);
		getWebElement("EditLabNameicon").click();
		Thread.sleep(3000);
		getWebElement("EditLabName").clear();
		Thread.sleep(2000);
		getWebElement("EditLabName").sendKeys("DevtestOwner1 Lab");
		Thread.sleep(2000);
		getWebElement("SaveEditLabName").click();
		Thread.sleep(5000);
		String LabNameFromLabSettings = driver.findElement(By.id("lab-details-form:fullName")).getText();
    	
    	Thread.sleep(3000);
    	getWebElement("LogoutRightButton").click();
    	Thread.sleep(2000);
    	String LabNameFromHeaderDropDown = driver.findElement(By.id("labNameInHeader")).getText();
    	
    	Thread.sleep(2000);
    	Assert.assertEquals(LabNameFromLabSettings, LabNameFromHeaderDropDown);
    	getWebElement("LogoutRightButton").click();
		
		Reporter.log("Revert Previous Labname and Save");
		Thread.sleep(5000);
		getWebElement("EditLabNameicon").click();
		Thread.sleep(3000);
		getWebElement("EditLabName").clear();
		Thread.sleep(2000);
		getWebElement("EditLabName").sendKeys("April03owner1 Lab");
		Thread.sleep(2000);
		getWebElement("SaveEditLabName").click();
		
		Reporter.log("Add description and Save");
		Thread.sleep(8000);
		getWebElement("AddDescription").click();
		Thread.sleep(2000);
		getWebElement("TextArea").click();
		Thread.sleep(2000);
		getWebElement("TextArea").sendKeys("TextArea");
		Thread.sleep(2000);
		getWebElement("SaveText").click();
		
		Reporter.log("Select different Timezone");
		Thread.sleep(8000);
		Library lib = new Library();
		lib.Select("SelectTimezone", 23);
		
		Reporter.log("Enable Manager and Member as Approver");
		Thread.sleep(7000);
		getWebElement("ManagerApprover").click();
		Thread.sleep(7000);
		getWebElement("MemberApprover").click();
		
		Reporter.log("Enable Manager and Member as Orderer");
		Thread.sleep(7000);
		getWebElement("ManagerOrderer").click();
		Thread.sleep(7000);
		getWebElement("MemberOrderer").click();
		
		Reporter.log("Disable and Enable Research Module");
		Thread.sleep(8000);
		getWebElement("ResearchModules").click();
		Thread.sleep(8000);
		getWebElement("ResearchModules").click();
		
		Reporter.log("Clear the Description and Save");
		Thread.sleep(5000);
		getWebElement("EditDescription").click();
		Thread.sleep(2000);
		getWebElement("TextArea").clear();
		Thread.sleep(2000);
		getWebElement("SaveText").click();
		
		Reporter.log("Select previous Timezone");
		Thread.sleep(5000);
		lib.Select("SelectTimezone", 7);
		
		Reporter.log("Disable Manager and Member as Approver");
		Thread.sleep(7000);
		getWebElement("ManagerApprover").click();
		Thread.sleep(7000);
		getWebElement("MemberApprover").click();
		
		Reporter.log("Disable Manager and Member as Orderer");
		Thread.sleep(7000);
		getWebElement("ManagerOrderer").click();
		Thread.sleep(7000);
		getWebElement("MemberOrderer").click();
		
		Reporter.log("Disable and Enable Inventory Module");
		Thread.sleep(8000);
		getWebElement("InventoryModules").click();
		Thread.sleep(8000);
		getWebElement("InventoryModules").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();

	}
}
