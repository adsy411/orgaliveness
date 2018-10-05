package labCatalog;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class LabManagerDefaultApproverProcurer_Action extends TestBase {
	
	@Test
	public void verifying_Default_Approver_And_Orderer_Role_As_Lab_Manager() throws Exception {
		
		//Create unique number
		Date date = new Date();
		Long dateTimeStamp = date.getTime();
		
		//LOGIN TEST DATA
		String MailIdCreation = "mgr"+dateTimeStamp;
	    String FirstName = "lb"+dateTimeStamp;
		String LastName = "mgr"+dateTimeStamp;
		
		//Login to application
		init();
		Reporter.log("Login to Application as lab owner");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("VinayakaUserMng2@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab members page
		Reporter.log("Click on User Settings and go to Lab Members page");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		//Add new member
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		//Open 20minutemail in New tab and create new email ID
		Reporter.log("Open 20minutemail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.20minutemail.com/");
	    Thread.sleep(3000);
	    getWebElement("CreateMailField").clear();
	    getWebElement("CreateMailField").sendKeys(MailIdCreation);
	    Thread.sleep(3000);
		getWebElement("CreateMailButton").click();
		Thread.sleep(5000);
		getWebElement("CopyEmailid").click();
		String createdUserId = getWebElement("CopyEmailid").getText();
		
		//Navigate to main tab to invite lab manager
		Reporter.log("Navigate to Bright Lab and Invite the newly added Lab Manager");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		
		//Enter user id and invite lab manager
		getWebElement("EnterEmailId").sendKeys(Keys.CONTROL + "V");
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		//Find the invite mail and register the lab manager
		Reporter.log("Navigate to 20minutemail and Register newly added Manager by invite mail");
		Thread.sleep(8000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		getWebElement("EmailList").click();
		Thread.sleep(6000);
		getWebElement("RegisterToLabButton").click();
		
		//Enter registration details
		Reporter.log("Enter the required fields in the Register page and Signup");
		impliciteWait(5);
		getWebElement("RegFirstName").sendKeys(FirstName);
		getWebElement("RegLastName").sendKeys(LastName);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		
		//Logout from application
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
				
		//Login to application
		init();
		Reporter.log("Login to Application as lab owner");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("VinayakaUserMng2@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab members page
		Reporter.log("Click on User Settings and go to Lab Members page");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		// Verify the invited lab manager in lab members page
		Reporter.log("Search lab manager from the lab member list and verify lab manager registration");
		getWebElement("LabMembersSearch").click();
		impliciteWait(2);
		getWebElement("LabMembersSearch").sendKeys(createdUserId);
		impliciteWait(5);
		boolean bLabMemberExists = driver.findElement(By.linkText(FirstName+" "+LastName)).isDisplayed();
		Assert.assertEquals(bLabMemberExists, true, "Lab manager registration failed");
		
		//Navigate to lab settings
		Reporter.log("Navigate to lab settings");
		getWebElement("SideBar_Lab_Settings_Group").click();
		impliciteWait(5);
		
		//Verify the default approver and procurer role assigned to Lab manager and privileges
		Reporter.log("Verify default approver and procurer settings for lab manager");
		try {
			WebElement labMemberRow = driver.findElement(By.xpath("//td[contains(text(),'"+FirstName+"  "+LastName+"')]/.."));
			String labMemberRowNo = labMemberRow.getAttribute("data-ri");
			String approverStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[3]")).getText();
			String procurerStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[4]")).getText();
			Assert.assertEquals(approverStatus, "Active", "Lab manager approver status is not active by default! Test case failed!");
			Assert.assertEquals(procurerStatus, "Active", "Lab manager procurer status is not active by default! Test case failed!");
		}
		catch(Exception expn) {
			 System.out.println("Exception occurred");
			 expn.printStackTrace();
		}
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		login.Logout();
	}
	
	@Test
	public void Verifying_Lab_Manager_Default_Approver_And_Orderer_Status_When_User_Changes_Role_From_Lab_Manager_To_Lab_Member() throws Exception {
		
		//Create unique number
		Date date = new Date();
		Long dateTimeStamp = date.getTime();
		
		//LOGIN TEST DATA
		String MailIdCreation = "mgr-mem"+dateTimeStamp;
	    String FirstName = "lb"+dateTimeStamp;
		String LastName = "mgr-mem"+dateTimeStamp;
		
		//Login to application
		init();
		Reporter.log("Login to Application as lab owner");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("VinayakaUserMng2@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab members page
		Reporter.log("Click on User Settings and go to Lab Members page");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		//Add new member
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		//Open 20minutemail in New tab and create new email ID
		Reporter.log("Open 20minutemail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.20minutemail.com/");
	    Thread.sleep(3000);
	    getWebElement("CreateMailField").clear();
	    getWebElement("CreateMailField").sendKeys(MailIdCreation);
	    Thread.sleep(3000);
		getWebElement("CreateMailButton").click();
		Thread.sleep(5000);
		getWebElement("CopyEmailid").click();
		String createdUserId = getWebElement("CopyEmailid").getText();
		
		//Navigate to main tab to invite lab manager
		Reporter.log("Navigate to Bright Lab and Invite the newly added Lab Manager");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		
		//Enter user id and invite lab manager
		getWebElement("EnterEmailId").sendKeys(Keys.CONTROL + "V");
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		//Find the invite mail and register the lab manager
		Reporter.log("Navigate to 20minutemail and Register newly added Manager by invite mail");
		Thread.sleep(8000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		getWebElement("EmailList").click();
		Thread.sleep(6000);
		getWebElement("RegisterToLabButton").click();
		
		//Enter registration details
		Reporter.log("Enter the required fields in the Register page and Sign up");
		impliciteWait(5);
		getWebElement("RegFirstName").sendKeys(FirstName);
		getWebElement("RegLastName").sendKeys(LastName);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		
		//Logout from application
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
				
		//Login to application
		init();
		Reporter.log("Login to Application as lab owner");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("VinayakaUserMng2@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab members page
		Reporter.log("Click on User Settings and go to Lab Members page");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		// Verify the invited lab manager in lab members page
		Reporter.log("Search lab manager from the lab member list and verify lab manager registration");
		getWebElement("LabMembersSearch").click();
		Thread.sleep(3000);
		getWebElement("LabMembersSearch").sendKeys(createdUserId);
		Thread.sleep(5000);
		boolean bLabMemberExists = driver.findElement(By.linkText(FirstName+" "+LastName)).isDisplayed();
		Assert.assertEquals(bLabMemberExists, true, "Lab manager registration failed");
		
		//Navigate to lab settings
		Reporter.log("Navigate to lab settings");
		getWebElement("SideBar_Lab_Settings_Group").click();
		impliciteWait(5);
		
		//Verify the default approver and procurer role assigned to Lab manager and privileges
		Reporter.log("Verify default approver and procurer settings for lab manager");
		try {
			WebElement labMemberRow = driver.findElement(By.xpath("//td[contains(text(),'"+FirstName+"  "+LastName+"')]/.."));
			String labMemberRowNo = labMemberRow.getAttribute("data-ri");
			String approverStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[3]")).getText();
			String procurerStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[4]")).getText();
			Assert.assertEquals(approverStatus, "Active", "Lab manager approver status is not active by default!");
			Assert.assertEquals(procurerStatus, "Active", "Lab manager procurer status is not active by default!");
		}
		catch(Exception expn) {
			 System.out.println("Exception occurred");
			 expn.printStackTrace();
		}
		
		//Navigate to lab members page and change role to Lab member from Lab manager
		Reporter.log("Click on User Settings and go to Lab Members page and change member role from Lab manager to Lab member");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		impliciteWait(5);
		getWebElement("LabMembersSearch").click();
		Thread.sleep(2000);
		getWebElement("LabMembersSearch").sendKeys(createdUserId);
		Thread.sleep(6000);
		WebElement memberRow = driver.findElement(By.xpath("//span[@title='"+createdUserId.toLowerCase()+"']/../.."));
		String memberRowNo = memberRow.getAttribute("data-ri");
		try {
			Select labMemberRoleDropDown = new Select(driver.findElement(By.id("memberForm:memberTable:"+memberRowNo+":roleLabOwnerDisable")));
			labMemberRoleDropDown.selectByVisibleText("Lab Member");  // Inconsistency in drop down order in members list and Add lab member modal
			Thread.sleep(5000);
		}
		catch(Exception expn) {
			Thread.sleep(5000);
			Select labMemberRoleDropDown = new Select(driver.findElement(By.id("memberForm:memberTable:"+memberRowNo+":roleLabOwnerDisable")));
			labMemberRoleDropDown.selectByVisibleText("Lab Member");  // Inconsistency in drop down order in members list and Add lab member modal
			Thread.sleep(5000);
		}
		
		//Navigate to lab settings page
		Reporter.log("Navigate to lab settings");
		getWebElement("SideBar_Lab_Settings_Group").click();
		impliciteWait(5);
		
		//Verify the lab member approver and procurer status for changed role
		Reporter.log("Verify default approver and procurer settings for lab user after changing role from lab manager to lab member");
		try {
			WebElement labMemberRow = driver.findElement(By.xpath("//td[contains(text(),'"+FirstName+"  "+LastName+"')]/.."));
			String labMemberRowNo = labMemberRow.getAttribute("data-ri");
			String approverStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[3]")).getText();
			String procurerStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[4]")).getText();
			Assert.assertEquals(approverStatus, "Inactive", "Lab manager after changing role to Lab member shows approver status as Active");
			Assert.assertEquals(procurerStatus, "Inactive", "Lab manager after changing role to Lab member shows procurer status as Active");
		}
		catch(Exception expn) {
			 System.out.println("Exception occurred");
			 expn.printStackTrace();
		}
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		login.Logout();
	}
	
	@Test
	public void Verifying_Lab_Manager_Default_Approver_And_Orderer_Status_When_User_Changes_Role_From_Lab_Member_To_Lab_Manager() throws Exception {
		
		//Create unique number
		Date date = new Date();
		Long dateTimeStamp = date.getTime();
		
		//LOGIN TEST DATA
		String MailIdCreation = "Mem-Mgr"+dateTimeStamp;
	    String FirstName = "Lb"+dateTimeStamp;
		String LastName = "Mem-Mgr"+dateTimeStamp;
		
		//Login to application
		init();
		Reporter.log("Login to Application as lab owner");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("VinayakaUserMng2@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab members page
		Reporter.log("Click on User Settings and go to Lab members page");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		//Add new member
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		//Open 20minutemail in New tab and create new email ID
		Reporter.log("Open 20minutemail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.20minutemail.com/");
	    Thread.sleep(3000);
	    getWebElement("CreateMailField").clear();
	    getWebElement("CreateMailField").sendKeys(MailIdCreation);
	    Thread.sleep(3000);
		getWebElement("CreateMailButton").click();
		Thread.sleep(5000);
		getWebElement("CopyEmailid").click();
		String createdUserId = getWebElement("CopyEmailid").getText();
		
		//Navigate to main tab to invite lab member
		Reporter.log("Navigate to Bright Lab and Invite the new Lab member");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		
		//Enter user id and invite lab member
		getWebElement("EnterEmailId").sendKeys(Keys.CONTROL + "V");
		Library lib = new Library();
		lib.Select("SelectRole", 0);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		//Find the invite mail and register the lab member
		Reporter.log("Navigate to 20minutemail and Register newly added member");
		Thread.sleep(8000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		getWebElement("EmailList").click();
		Thread.sleep(6000);
		getWebElement("RegisterToLabButton").click();
		
		//Enter registration details
		Reporter.log("Enter the required fields in the Register page and Sign up");
		impliciteWait(5);
		getWebElement("RegFirstName").sendKeys(FirstName);
		getWebElement("RegLastName").sendKeys(LastName);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		
		//Logout from application
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
				
		//Login to application
		init();
		Reporter.log("Login to Application as lab owner");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("VinayakaUserMng2@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab members page
		Reporter.log("Click on User Settings and go to Lab Members page");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		// Verify the invited lab member in lab members page
		Reporter.log("Search lab manager from the lab member list and verify lab manager registration");
		getWebElement("LabMembersSearch").click();
		impliciteWait(2);
		getWebElement("LabMembersSearch").sendKeys(createdUserId);
		impliciteWait(5);
		boolean bLabMemberExists = driver.findElement(By.linkText(FirstName+" "+LastName)).isDisplayed();
		Assert.assertEquals(bLabMemberExists, true, "Lab member registration failed");
		
		//Navigate to lab settings
		Reporter.log("Navigate to lab settings");
		getWebElement("SideBar_Lab_Settings_Group").click();
		impliciteWait(5);
		
		//Verify the default approver and procurer role status to Lab member and privileges
		Reporter.log("Verify default approver and procurer settings for lab member");
		try {
			WebElement labMemberRow = driver.findElement(By.xpath("//td[contains(text(),'"+FirstName+"  "+LastName+"')]/.."));
			String labMemberRowNo = labMemberRow.getAttribute("data-ri");
			String approverStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[3]")).getText();
			String procurerStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[4]")).getText();
			Assert.assertEquals(approverStatus, "Inactive", "Lab member approver status shows as active by default!");
			Assert.assertEquals(procurerStatus, "Inactive", "Lab member procurer status shows as active by default!"); 
		}
		catch(Exception expn) {
			 System.out.println("Exception occurred");
			 expn.printStackTrace();
		}
		
		//Navigate to lab members page and change role from lab member to lab manager
		Reporter.log("Click on User Settings and go to lab members page");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		impliciteWait(5);
		getWebElement("LabMembersSearch").click();
		impliciteWait(2);
		getWebElement("LabMembersSearch").sendKeys(createdUserId);
		Thread.sleep(5000);
		WebElement memberRow = driver.findElement(By.xpath("//span[@title='"+createdUserId.toLowerCase()+"']/../.."));
		String memberRowNo = memberRow.getAttribute("data-ri");
		Select labMemberRoleDropDown = new Select(driver.findElement(By.id("memberForm:memberTable:"+memberRowNo+":roleLabOwnerDisable")));
		labMemberRoleDropDown.selectByVisibleText("Lab Manager");  // Inconsistency in drop down order in members list and Add lab member modal
		Thread.sleep(5000);
		
		//Navigate to lab settings page
		Reporter.log("Navigate to lab settings");
		getWebElement("SideBar_Lab_Settings_Group").click();
		impliciteWait(5);
		
		//Verify the lab member approver and procurer status for changed role
		try {
			Reporter.log("Verify default approver and procurer settings for lab user after changing role from lab member to lab manager");
			WebElement labMemberRow = driver.findElement(By.xpath("//td[contains(text(),'"+FirstName+"  "+LastName+"')]/.."));
			String labMemberRowNo = labMemberRow.getAttribute("data-ri");
			String approverStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[3]")).getText();
			String procurerStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[4]")).getText();
			Assert.assertEquals(approverStatus, "Active", "Lab member after changing role to Lab manager shows approver status as In active");
			Assert.assertEquals(procurerStatus, "Active", "Lab member after changing role to Lab manager shows procurer status as In active");
		}
		catch(Exception expn) {
			 System.out.println("Exception occurred");
			 expn.printStackTrace();
		}
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		login.Logout();
	}
	
}