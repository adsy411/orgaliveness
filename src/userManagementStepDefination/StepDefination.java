package userManagementStepDefination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class StepDefination extends TestBase{
	
	
	@Given("^Login to Application with Specific User One$")
	public void Login_to_Application_with_Specific_User_One() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtest2218@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}

	@When("^Click on User Settings and go to My Profile$")
	public void click_on_User_Settings_and_go_to_My_Profile() throws Throwable {
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
	    
	}

	@When("^Click on Profile Settings and Select Change Password$")
	public void click_on_Profile_Settings_and_Select_Change_Password() throws Throwable {
		getWebElement("ProfileSettings").click();
		getWebElement("ChangePassword").click();
	    
	}

	@When("^Enter the required field and click on Save$")
	public void enter_the_required_field_and_click_on_Save() throws Throwable {
		Thread.sleep(2000);
		getWebElement("CurrentPassword").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("NewPassword").sendKeys("admin12345");
		Thread.sleep(2000);
		getWebElement("ConfirmPassword").sendKeys("admin12345");
		Thread.sleep(2000);
		getWebElement("SavePassword").click();
		Thread.sleep(10000);
		getWebElement("ChangePasswordCancelButton").click();
	}

	@When("^Click on User Settings and logout$")
	public void click_on_User_Settings_and_logout() throws Throwable {
		Thread.sleep(5000);
		getWebElement("UserSettings").click();
		getWebElement("Logout").click();
	    
	}

	@When("^Verify the Password change by logging to Application$")
	public void verify_the_Password_change_by_logging_to_Application() throws Throwable {
		Thread.sleep(2000);
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtest2218@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin12345");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	    
	}

	@When("^Revert Previous Password$")
	public void revert_Previous_Password() throws Throwable {
		Thread.sleep(2000);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		Thread.sleep(2000);
		getWebElement("ProfileSettings").click();
		Thread.sleep(2000);
		getWebElement("ChangePassword").click();
		Thread.sleep(2000);
		getWebElement("CurrentPassword").sendKeys("admin12345");
		Thread.sleep(1000);
		getWebElement("NewPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("ConfirmPassword").sendKeys("admin123");
		getWebElement("SavePassword").click();
		Thread.sleep(10000);
		getWebElement("ChangePasswordCancelButton").click();
	}

	@Then("^Click on User Settings->logout and Close the application$")
	public void click_on_User_Settings_and_logout1() throws Throwable {
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
	
	@Given("^Open Application$")
	public void open_Application() throws Throwable {
		init();
		impliciteWait(5);
	}
	
	@When("^Click on Home page Contact Us$")
	public void click_on_Home_page_Contact_Us() throws Throwable {
		getWebElement("HomePageContactUsAuth").click();
		impliciteWait(5);
	}

	@Then("^Enter the required fields and Click on Send$")
	public void enter_the_required_fields_and_Click_on_Send() throws Throwable {
		getWebElement("ContactUsEnterName").sendKeys("Test");
		Thread.sleep(2000);
		getWebElement("ContactUsEmailAddress").sendKeys("devtest03april@mailinator.com");
		getWebElement("ContactUsMessage").sendKeys("Test Message");
		Thread.sleep(3000);
		getWebElement("ContactUsSendButton").click();
		Thread.sleep(2000);
		getWebElement("CloseAuthentication").click();
	}
	
	@When("^Click on Login page Contact Us$")
	public void click_on_Login_page_Contact_Us() throws Throwable {
		Thread.sleep(3000);
		getWebElement("HomePageLoginButton").click();
		impliciteWait(5);
		getWebElement("LoginPageContactUs").click();
	}
	
	@When("^Login to Application and click on Contact Us$")
	public void login_to_Application_and_click_on_Contact_Us() throws Throwable {
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys("devtest03april@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		getWebElement("Enotebook.login.loginButton").click();
		impliciteWait(5);
		getWebElement("DashboardContactUs").click();
	}
	
	@Then("^Enter the Feedback and click on Submit Feedback$")
	public void enter_the_Feedback_and_click_on_Submit_Feedback() throws Throwable {
		impliciteWait(5);
		getWebElement("DescribeFeedback").sendKeys("Test Message");
		getWebElement("SendFeedbackButton").click();
	}
	
	@Given("^Login to Application$")
	public void Login_to_Application() throws Throwable {
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
	}
	
	@Then("^Edit the Username and Save then Verify the Username from My Profile page and Header Drop Down$")
	public void edit_the_Username_and_Save_then_Verify_the_Username_from_My_Profile_page_and_Header_Drop_Down() throws Throwable {
		Thread.sleep(5000);
    	getWebElement("EditName").click();
    	Thread.sleep(3000);
    	getWebElement("UserEditFirstName").sendKeys("1");
    	impliciteWait(5);
    	getWebElement("UserEditLastName").sendKeys("1");
    	impliciteWait(5);
    	getWebElement("EditNameSave").click();
    	Thread.sleep(5000);
    	String UserNameFromMyProfile = driver.findElement(By.id("user-name-popover:fullName")).getText();
    	
    	Thread.sleep(3000);
    	getWebElement("LogoutRightButton").click();
    	Thread.sleep(2000);
    	String UserNameFromHeaderDropDown = driver.findElement(By.id("header-drop-down-name")).getText();
    	
    	Thread.sleep(2000);
    	Assert.assertEquals(UserNameFromMyProfile, UserNameFromHeaderDropDown);
    	getWebElement("LogoutRightButton").click();
	}

	@Then("^Edit the Profile and Save$")
	public void edit_the_Profile_and_Save() throws Throwable {
		Thread.sleep(7000);
    	getWebElement("ProfileSettings").click();
    	Thread.sleep(3000);
    	getWebElement("EditProfile").click();
    	Thread.sleep(3000);
    	getWebElement("UserProfileEditFirstName").sendKeys(Keys.BACK_SPACE);
    	getWebElement("UserProfileEditLastName").sendKeys(Keys.BACK_SPACE);
    	Thread.sleep(2000);
    	getWebElement("UserProfileEditPhone").clear();
    	Thread.sleep(2000);
    	getWebElement("UserProfileEditPhone").sendKeys("111.111.1111");
    	Library lib = new Library();
    	lib.Select("UserProfileEditTimezone", 24);
    	impliciteWait(5);
    	getWebElement("EditProfileSave").click();
    	Thread.sleep(5000);
    	driver.getWindowHandle();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id='user-profile-form']/div[1]/h4")));
		Thread.sleep(2000);
    	getWebElement("EditProfileCancel").click();
	}
	//---------------------------------------------------------------------------------------------
	//=============================================================================================
	@When("^Click on Login page->Support Site$")
	public void click_on_Login_page_Support_Site() throws Throwable {
		impliciteWait(5);
		getWebElement("HomePageLoginButton").click();
		impliciteWait(5);
		getWebElement("SupportSite").click();
	}

	@Then("^User should be navigate to a new tab->M Connected Lab:Home$")
	public void user_should_be_navigate_to_a_new_tab_M_Connected_Lab_Home() throws Throwable {
		Thread.sleep(8000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		//newTab.remove(oldTab);
		driver.switchTo().window(newTab.get(0));
	}
	
	@Then("^Close the Application$")
	public void close_the_Application() throws Throwable {
		Thread.sleep(3000);
		driver.quit();
	}
	
	@When("^Click on Profile Settings$")
	public void click_on_Profile_Settings() throws Throwable {
		Thread.sleep(3000);
		getWebElement("ProfileSettings").click();
	}
	
	@Then("^Uncheck all the Notification and click on Save$")
	public void uncheck_all_the_Notification_and_click_on_Save() throws Throwable {
		getWebElement("EmailNotificationSettings").click();
		Thread.sleep(3000);
		getWebElement("ProdReqStaUpdate").click();
		getWebElement("ResearchPermissions").click();
		getWebElement("ResearchRevSub").click();
		getWebElement("UserProUpdate").click();
		getWebElement("DisposedMatStaUpdate").click();
		Thread.sleep(3000);
		getWebElement("EmailNotiSetSave").click();
		Thread.sleep(5000);
		getWebElement("EmailNotiSetCancel").click();
	}
	
	@Then("^Check all the Notification and click on Save$")
	public void check_all_the_Notification_and_click_on_Save() throws Throwable {
		getWebElement("EmailNotificationSettings").click();
		Thread.sleep(3000);
		getWebElement("ProdReqStaUpdate").click();
		getWebElement("ResearchPermissions").click();
		getWebElement("ResearchRevSub").click();
		getWebElement("UserProUpdate").click();
		getWebElement("DisposedMatStaUpdate").click();
		Thread.sleep(3000);
		getWebElement("EmailNotiSetSave").click();
		Thread.sleep(5000);
		getWebElement("EmailNotiSetCancel").click();
	}
	
	//=================================================================================================
	//-------------------------------------------------------------------------------------------------
	
	@Given("^Click on Login page -> Forgot Password$")
	public void click_on_Login_page_Forgot_Password() throws Throwable {
		init();
		impliciteWait(5);
		getWebElement("HomePageLoginButton").click();
		Thread.sleep(3000);
		getWebElement("ForgotPassword").click();
	}
	
	@When("^Enter Email Address and Click on Submit then verify the information message in Login page$")
	public void enter_Email_Address_and_Click_on_Submit_then_verify_the_information_message_in_Login_page() throws Throwable {
		impliciteWait(5);
		getWebElement("EmailAddress").sendKeys("devtest03april@mailinator.com");
		getWebElement("SubmitButton").click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Password reset instructions has been sent to your registered mail id."));
	}
	
	@Then("^Close the Browser$")
	public void close_the_Browser() throws Throwable {
		Thread.sleep(3000);
		driver.quit();
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	//==================================================================================================================================
	@Given("^Click on Profile Settings and Select Payment Settings$")
	public void click_on_Profile_Settings_and_Select_Payment_Settings() throws Throwable {
		Thread.sleep(5000);
		getWebElement("ProfileSettings").click();
		Thread.sleep(2000);
    	getWebElement("PaymentSettings").click();
	}
	
	@Given("^Add New PI and PO and click on Save$")
	public void add_New_PI_and_PO_and_click_on_Save() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_TestData, "UserManagement");

		String PI = ExcelUtils.getCellData(1, 12);
		String PO = ExcelUtils.getCellData(1, 13);
		
		Thread.sleep(3000);
    	Library lib = new Library();
    	lib.Select("PIfield", 1);
    	Thread.sleep(2000);
    	getWebElement("PITextField").sendKeys(PI);
    	Thread.sleep(3000);
    	lib.Select("POfield", 1);
    	Thread.sleep(2000);
    	getWebElement("POTextField").sendKeys(PO);
    	Thread.sleep(2000);
    	getWebElement("SavePaymentSettings").click();
    	Thread.sleep(5000);
    	getWebElement("CancelPaymentSettings").click();
	}
	//============================================================================================================================
	//----------------------------------------------------------------------------------------------------------------------------
	
	@When("^Verify Start Research Project Link$")
	public void verify_Start_Research_Project_Link() throws Throwable {
		impliciteWait(5);
		getWebElement("StartResearchProjLink").click();
		Thread.sleep(3000);
		verifyText("//*[@id='addProjectFlowModal:researchCreateForm']/div/div[1]/h4", "Create Project");
		Thread.sleep(3000);
		getWebElement("CreateProjectCancelButton").click();
	}
	
	@When("^Verify Increase Storage Link$")
	public void verify_Increase_Storage_Link() throws Throwable {
		Thread.sleep(3000);
		getWebElement("IncreaseStorageLink").click();
		Thread.sleep(3000);
		verifyText("//*[@id='contactForm']/div/div/div/div/div/label", "Describe Feedback*");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
	}
	
	@When("^Verify Add New Material Icon$")
	public void verify_Add_New_Material_Icon() throws Throwable {
		Thread.sleep(3000);
		getWebElement("AddNewMaterialIcon").click();
		Thread.sleep(3000);
		verifyText("//*[@id='materialPageForm']/div/div/div[1]/button", "Add New Material");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
	}
	
	@When("^Verify View in Inventory Link$")
	public void verify_View_in_Inventory_Link() throws Throwable {
		Thread.sleep(3000);
		getWebElement("ViewInInventoryLink").click();
		Thread.sleep(3000);
		verifyText("//*[@id='materialPageForm']/div/div/div[1]/button", "Add New Material");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
	}
	
	@When("^Verify Lab Activity Icon$")
	public void verify_Lab_Activity_Icon() throws Throwable {
		Thread.sleep(3000);
		getWebElement("LabActivityIcon").click();
		Thread.sleep(3000);
		verifyText("//*[@id='new_project_link']", "Create New");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
	}

	@When("^Verify View Experiments Link$")
	public void verify_View_Experiments_Link() throws Throwable {
		Thread.sleep(3000);
		getWebElement("ViewExperimentsLink").click();
		Thread.sleep(3000);
		verifyText("/html/body/div[1]/nav/div/div[2]/div/ol/li[2]/a", "My Profile");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
	}
	
	@When("^Click on User Settings and go to Lab Settings$")
	public void click_on_User_Settings_and_go_to_Lab_Settings() throws Throwable {
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabSettings").click();
	}

	@Then("^Edit Labname and Save then Verify the Lab Name in Lab Settings page and Header Drop Down$")
	public void edit_Labname_and_Save_then_Verify_the_Lab_Name_in_Lab_Settings_page_and_Header_Drop_Down() throws Throwable {
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
	}

	@Then("^Revert Previous Labname and Save$")
	public void revert_Previous_Labname_and_Save() throws Throwable {
		Thread.sleep(5000);
		getWebElement("EditLabNameicon").click();
		impliciteWait(5);
		getWebElement("EditLabName").clear();
		Thread.sleep(2000);
		getWebElement("EditLabName").sendKeys("April03owner Lab");
		Thread.sleep(2000);
		getWebElement("SaveEditLabName").click();
	}

	@Then("^Add description and Save$")
	public void add_description_and_Save() throws Throwable {
		Thread.sleep(5000);
		getWebElement("AddDescription").click();
		Thread.sleep(2000);
		getWebElement("TextArea").click();
		Thread.sleep(2000);
		getWebElement("TextArea").sendKeys("TextArea");
		Thread.sleep(2000);
		getWebElement("SaveText").click();
	}

	@When("^Select different Timezone$")
	public void select_different_Timezone() throws Throwable {
		Thread.sleep(5000);
		Library lib = new Library();
		lib.Select("SelectTimezone", 23);
	}

	@When("^Enable Manager and Member as Approver$")
	public void enable_Manager_and_Member_as_Approver() throws Throwable {
		Thread.sleep(7000);
		getWebElement("ManagerApprover").click();
		Thread.sleep(7000);
		getWebElement("MemberApprover").click();
	}

	@When("^Enable Manager and Member as Orderer$")
	public void enable_Manager_and_Member_as_Orderer() throws Throwable {
		Thread.sleep(7000);
		getWebElement("ManagerOrderer").click();
		Thread.sleep(7000);
		getWebElement("MemberOrderer").click();
	}

	@When("^Disable and Enable Research Module$")
	public void disable_and_Enable_Research_Module() throws Throwable {
		Thread.sleep(8000);
		getWebElement("ResearchModules").click();
		Thread.sleep(8000);
		getWebElement("ResearchModules").click();
	}

	@When("^Clear the Description and Save$")
	public void clear_the_Description_and_Save() throws Throwable {
		Thread.sleep(5000);
		getWebElement("EditDescription").click();
		Thread.sleep(2000);
		getWebElement("TextArea").clear();
		Thread.sleep(2000);
		getWebElement("SaveText").click();
	}

	@When("^Select previous Timezone$")
	public void select_previous_Timezone() throws Throwable {
		Thread.sleep(5000);
		Library lib = new Library();
		lib.Select("SelectTimezone", 7);
	}

	@When("^Disable Manager and Member as Approver$")
	public void disable_Manager_and_Member_as_Approver() throws Throwable {
		Thread.sleep(7000);
		getWebElement("ManagerApprover").click();
		Thread.sleep(7000);
		getWebElement("MemberApprover").click();
	}

	@When("^Disable Manager and Member as Orderer$")
	public void disable_Manager_and_Member_as_Orderer() throws Throwable {
		Thread.sleep(7000);
		getWebElement("ManagerOrderer").click();
		Thread.sleep(7000);
		getWebElement("MemberOrderer").click();
	}

	@When("^Disable and Enable Inventory Module$")
	public void disable_and_Enable_Inventory_Module() throws Throwable {
		Thread.sleep(8000);
		getWebElement("InventoryModules").click();
		Thread.sleep(8000);
		getWebElement("InventoryModules").click();
	}
	
	@When("^Click on User Settings and go to Lab Members$")
	public void click_on_User_Settings_and_go_to_Lab_Members() throws Throwable {
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
	}

	@When("^Click Add New Member$")
	public void click_Add_New_Member() throws Throwable {
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
	}
	
	@Then("^Invite existing Manager and Verify the alert message$")
	public void invite_existing_Manager_and_Verify_the_alert_message() throws Throwable {
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("dt03aprilman@mailinator.com");
		Thread.sleep(2000);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		verifyText("//*[@id='addMemberForm:emailList:0:emailerror']", "LogonId already exists");
		Thread.sleep(2000);
		getWebElement("CancelButton").click();
	}

	@Then("^Invite existing Member and Verify the alert message$")
	public void invite_existing_Member_and_Verify_the_alert_message() throws Throwable {
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("dt03aprilmem@mailinator.com");
		Thread.sleep(2000);
		Library lib = new Library();
		lib.Select("SelectRole", 0);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		verifyText("//*[@id='addMemberForm:emailList:0:emailerror']", "LogonId already exists");
		Thread.sleep(2000);
		getWebElement("CancelButton").click();
	}
	
	@Given("^Login to Application with specific user Two$")
	public void login_to_Application_with_specific_user_Two() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtestmsadmin1dec@20mm.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}

	@When("^Register the Manager and Verify First and Last Name$")
	public void Register_the_Manager_and_Verify_First_and_Last_Name() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 0);
		String FirstName = ExcelUtils.getCellData(1, 1);
		String LastName = ExcelUtils.getCellData(1, 2);
		
		Reporter.log("Open Nadamail in New tab and create new email ID then invite as Manager in Mconnecetd Lab");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(MailIdCreation);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(2000);
		String EmailId = driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).getText();
		
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Register newly added Manager");
		Thread.sleep(20000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(8000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
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
		driver.switchTo().window(tabs.get(0));
		
		Reporter.log("Open Mconnected Lab and verify the First and Last Name");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		impliciteWait(5);
		Assert.assertTrue(driver.getPageSource().contains(FirstName+" "+LastName));
	}
	
	@Then("^Register the Member from Owner Login and Verify First and Last Name$")
	public void register_the_Member_from_Owner_Login_and_Verify_First_and_Last_Name() throws Throwable {
	    
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 4);
		String FirstName = ExcelUtils.getCellData(1, 5);
		String LastName = ExcelUtils.getCellData(1, 6);
		
		Reporter.log("Open Nadamail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(MailIdCreation);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(2000);
		String EmailId = driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).getText();
		
		Reporter.log("Navigate to Mconnected Lab and Invite the newly added Member");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 0);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Register newly added Member");
		Thread.sleep(20000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(8000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
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
		driver.switchTo().window(tabs.get(0));
		
		Reporter.log("Open Mconnected Lab and verify the First and Last Name");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		impliciteWait(5);
		Assert.assertTrue(driver.getPageSource().contains(FirstName+" "+LastName));
	}
	
	@Given("^Login to Application with specific user Three$")
	public void login_to_Application_with_specific_user_Three() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("dt03aprilman@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}
	
	@Then("^Register the Member from Manager Login and Verify First and Last Name$")
	public void register_the_Member_from_Manager_Login_and_Verify_First_and_Last_Name() throws Throwable {
	    
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 8);
		String FirstName = ExcelUtils.getCellData(1, 9);
		String LastName = ExcelUtils.getCellData(1, 10);
		
		Reporter.log("Open Nadamail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(MailIdCreation);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(2000);
		String EmailId = driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).getText();
		
		Reporter.log("Navigate to Mconnected Lab and Invite the newly added Manager");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 0);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Register newly added Member");
		Thread.sleep(20000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(8000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
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
		driver.switchTo().window(tabs.get(0));
		
		Reporter.log("Open Mconnected Lab and verify the First and Last Name");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		impliciteWait(5);
		Assert.assertTrue(driver.getPageSource().contains(FirstName+" "+LastName));	
	}
	//-------------------------------------------------------------------------------
	//===============================================================================
	
	@Given("^Login to Application with specific Manager user$")
	public void login_to_Application_with_specific_Manager_user() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtestmandec20@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}
	//=================================================================================
	//---------------------------------------------------------------------------------
	
	@Then("^Delete Lab Member then Click Add New Member and Enter the deleted mail ID$")
	public void Delete_Lab_Member_then_Click_Add_New_Member_and_Enter_the_deleted_mail_ID() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String LastName = ExcelUtils.getCellData(1, 6);
		
		impliciteWait(5);
		getWebElement("LabMembersSearch").sendKeys(LastName);
		Thread.sleep(2000);
		String mailid = getWebElement("EmailTitle").getText();
		Thread.sleep(4000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(2000);
		getWebElement("DeleteUser").click();
		Thread.sleep(3000);
		getWebElement("AddNewMembersButton").click();
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys(mailid);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
	}

	@Then("^Click on the Pending Status to resend the email verification$")
	public void click_on_the_Pending_Status_to_resend_the_email_verification() throws Throwable {
		Thread.sleep(3000);
		getWebElement("PendingStatus").click();
	}

	@When("^Click on User Settings and go to Switch Context$")
	public void click_on_User_Settings_and_go_to_Switch_Context() throws Throwable {
		impliciteWait(5);
		getWebElement("UserSettings").click();
		impliciteWait(5);
		getWebElement("SwitchContext").click();
	}

	@When("^Create New Lab then Go to Newly created Lab and Verify the Lab Name in User Settings$")
	public void create_New_Lab_then_Go_to_Newly_created_Lab_and_Verify_the_Lab_Name_in_User_Settings() throws Throwable {
		Thread.sleep(3000);
		getWebElement("CreateNewLab").click();
		Thread.sleep(2000);
		getWebElement("CreateNewLabYesButton").click();
		
		Thread.sleep(5000);
		String NewLab = getWebElement("NewLabSuccessfulMessage").getText();
		System.out.println(NewLab);
		
		StringBuffer sb = new StringBuffer(NewLab);
		sb.delete(19, 41);
		System.out.println(sb);
		
		Thread.sleep(3000);
		getWebElement("NewLabSuccessfulConfirmationOkButton").click();
		
		Reporter.log("Go to Newly created Lab");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='switchLabForm:usersListTable']/div[1]/div/div[2]/div[1]/table/tbody/tr[last()]/td[3]/div/a")).click();
	
		Reporter.log("Verify the Lab Name in User Settings");
		Thread.sleep(2000);
		getWebElement("UserSettings").click();
		
		Thread.sleep(5000);
		String VerifyLabName = getWebElement("VerifyLabNameFromUserSettings").getText();
		System.out.println(VerifyLabName);
		
		Assert.assertEquals(sb.toString(), VerifyLabName);
		getWebElement("UserSettings").click();
	}
	
	@Then("^Click Batchupload from Owner Login and upload the Lab Members$")
	public void click_Batchupload_from_Owner_Login_and_upload_the_Lab_Members() throws Throwable {
		Thread.sleep(2000);
		getWebElement("BatchUpload").click();
		Thread.sleep(3000);
		getWebElement("FileUpload").click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\dprakash\\Desktop\\Files\\LabMembersBatchUpload.exe");
	}
	
	@Then("^Register the uploaded Lab Members and Verify First and Last Name$")
	public void register_the_uploaded_Lab_Members_and_Verify_First_and_Last_Name() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String FirstName1 = ExcelUtils.getCellData(1, 16);
		String LastName1 = ExcelUtils.getCellData(1, 17);
		String FirstName2 = ExcelUtils.getCellData(2, 16);
		String LastName2 = ExcelUtils.getCellData(2, 17);
		String FirstName3 = ExcelUtils.getCellData(3, 16);
		String LastName3 = ExcelUtils.getCellData(3, 17);
		String NewEmail1 = ExcelUtils.getCellData(1, 15);
		String NewEmail2 = ExcelUtils.getCellData(2, 15);
		String NewEmail3 = ExcelUtils.getCellData(3, 15);
		
		Thread.sleep(10000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(NewEmail1);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
		getWebElement("RegFirstName").sendKeys(FirstName1);
		getWebElement("RegLastName").sendKeys(LastName1);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		Library lib = new Library();
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
		Thread.sleep(2000);
		driver.close();
		
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
		getWebElement("AddInbox1").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(NewEmail2);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
		getWebElement("RegFirstName").sendKeys(FirstName2);
		getWebElement("RegLastName").sendKeys(LastName2);
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
		Thread.sleep(2000);
		driver.close();
		
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
		getWebElement("AddInbox2").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(NewEmail3);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles2 = driver.getWindowHandles();
	    String currentHandle2 = driver.getWindowHandle();
	    for (String handle : handles2) {

	     if (!handle .equals(currentHandle2))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
		getWebElement("RegFirstName").sendKeys(FirstName3);
		getWebElement("RegLastName").sendKeys(LastName3);
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
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
		
		Reporter.log("Click on User Settings and go to Lab Members");
		Thread.sleep(5000);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Thread.sleep(4000);
		verifyText("//*[@id='memberForm:memberTable:2:openpay']", FirstName1+" "+LastName1);
		Thread.sleep(4000);
		verifyText("//*[@id='memberForm:memberTable:1:openpay']", FirstName2+" "+LastName2);
		Thread.sleep(4000);
		verifyText("//*[@id='memberForm:memberTable:0:openpay']", FirstName3+" "+LastName3);
	}
	
	@Given("^Login to Application with specific User Four$")
	public void login_to_Application_with_specific_User_Four() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("09jan@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}
	
	@Given("^Login to Application with Specific User Five$")
	public void login_to_Application_with_specific_User_Five() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("28feb2144@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}
	
	@Then("^Register a New Manager and complete the onboarding then Logout$")
	public void register_a_New_Manager_and_complete_the_onboarding_then_Logout() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 24);
		String FirstName = ExcelUtils.getCellData(1, 25);
		String LastName = ExcelUtils.getCellData(1, 26);
		
		Reporter.log("Open Nadamail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(MailIdCreation);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(2000);
		String EmailId = driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).getText();
		
		Reporter.log("Navigate to Mconnected Lab and Invite the newly added Manager");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Register newly added Manager");
		Thread.sleep(20000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(8000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
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
		
		Reporter.log("Login to the registered EmailID and Logout");
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys(EmailId);
		//getWebElement("Enotebook.login.username").sendKeys("newman3301@amail.club");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		getWebElement("DefaultLabName").click();
		impliciteWait(5);
		getWebElement("SetupYourProfilePageNextButton").click();
		Thread.sleep(25000);
		getWebElement("TutorialTipsGotoMyLabButton").click();
		Thread.sleep(15000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(2000);
		getWebElement("Enotebook.logout").click();
		
	}
	
	@Given("^Login to Application with Specific User Six$")
	public void login_to_Application_with_specific_User_Six() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("01mar1526@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}
	
	@Then("^Register a New Manager and Invite the same Manager in different Lab then Verify the Join Lab button$")
	public void register_a_New_Manager_and_Invite_the_same_Manager_in_different_Lab_then_Verify_the_Join_Lab_button() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 24);
		String FirstName = ExcelUtils.getCellData(1, 25);
		String LastName = ExcelUtils.getCellData(1, 26);
		
		Reporter.log("Open Nadamail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(MailIdCreation);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(2000);
		String EmailId = driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).getText();
		
		Reporter.log("Navigate to Mconnected Lab and Invite the newly added Manager");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Register newly added Manager");
		Thread.sleep(20000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(8000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
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
		
		Reporter.log("Login to the registered EmailID and Logout");
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys(EmailId);
		//getWebElement("Enotebook.login.username").sendKeys("newman3301@amail.club");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		getWebElement("DefaultLabName").click();
		impliciteWait(5);
		getWebElement("SetupYourProfilePageNextButton").click();
		Thread.sleep(25000);
		getWebElement("TutorialTipsGotoMyLabButton").click();
		Thread.sleep(15000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(2000);
		getWebElement("Enotebook.logout").click();
		
		Reporter.log("Login to Application with Specific User Six");
		Thread.sleep(3000);
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("01mar1526@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		Reporter.log("Invite the Existing Manager");
		Thread.sleep(3000);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Confirm to Join");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(20000);
		getWebElement("InboxEmailID").click();
		Thread.sleep(8000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaConfirmToJoinButton").click();
		
		Reporter.log("Signin as Manager Login and Click on Join Lab");
		Thread.sleep(7000);
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
	    getWebElement("Enotebook.login.password").sendKeys("admin123");
	    Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		getWebElement("JoinLabButton").click();
	}
	
	@When("^Click on Save and Verify Validation and Error messages in Change Password popup$")
	public void click_on_Save_and_Verify_Validation_and_Error_messages_in_Change_Password_popup() throws Throwable {
		Thread.sleep(2000);
		getWebElement("SavePassword").click();
		
		Thread.sleep(2000);
		String Message  = getWebElement("ChangePasswordCurrPassValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='change-password-form:currentPwd-error']", Message);
		
		Thread.sleep(2000);
		String Message1  = getWebElement("ChangePasswordNewPassValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='change-password-form:newPwd-error']", Message1);
		
		Thread.sleep(2000);
		String Message2  = getWebElement("ChangePasswordConPassValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='change-password-form:repeatPwd-error']", Message2);
		
		Thread.sleep(2000);
		getWebElement("NewPassword").sendKeys("123");
		
		Thread.sleep(2000);
		String Message3  = getWebElement("ChangePasswordNewPassValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='change-password-form:newPwd-error']", Message3);
		
		getWebElement("ChangePasswordCancelButton").click();
	}
	
	@When("^Verify Validation and Error messages in Add New Member$")
	public void verify_Validation_and_Error_messages_in_Add_New_Member() throws Throwable {
		Thread.sleep(1000);
		getWebElement("InviteButton").click();
		Thread.sleep(2000);
		String Message  = getWebElement("AddMemberInviteValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='addMemberForm:emailList:0:emailerror']", Message);

		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("123@");
		Thread.sleep(1000);
		getWebElement("InviteButton").click();
		
		Thread.sleep(2000);
		String Message1  = getWebElement("AddMemberInviteErrorMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='addMemberForm:emailList:0:emailerror']", Message1);
	}
	
	@Given("^Login to Application with specific user2$")
	public void login_to_Application_with_specific_user2() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("09jan@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}

	@When("^Click Batchupload and upload Invalid File then verify the Error message$")
	public void click_Batchupload_and_upload_Invalid_File_then_verify_the_Error_message() throws Throwable {
		Thread.sleep(2000);
		getWebElement("BatchUpload").click();
		Thread.sleep(3000);
		getWebElement("FileUpload").click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\dprakash\\Desktop\\Files\\LabMembersBatchUploadInvalidFile.exe");

		Thread.sleep(3000);
		String Message  = getWebElement("InvalidFileTypeMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='addFileModal']/div/div[1]/div/div/ul/li[1]", Message);
	}
	
	@When("^Click on Send and Verify the Validation messages in Contact us popup$")
	public void click_on_Send_and_Verify_the_Validation_messages_in_Contact_us_popup() throws Throwable {
		Thread.sleep(2000);
		getWebElement("ContactUsSendButton").click();
		
		Thread.sleep(2000);
		String Message  = getWebElement("ContactUsNameValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='contactForm:name-error']", Message);
		
		Thread.sleep(2000);
		String Message1  = getWebElement("ContactUsEmailValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='contactForm:email-error']", Message1);
		
		Thread.sleep(2000);
		String Message2  = getWebElement("ContactUsMessageValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='contactForm:contactSubmit-error']", Message2);
	}

	@When("^Enter Invalid Email address and Verify the Error message in Contact us popup$")
	public void enter_Invalid_Email_address_and_Verify_the_Error_message_in_Contact_us_popup() throws Throwable {
		Thread.sleep(2000);
		getWebElement("ContactUsEmailAddress").sendKeys("123");
		
		Thread.sleep(2000);
		String Message3  = getWebElement("ContactUsEmailValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='contactForm:email-error']", Message3);
		
		getWebElement("ContactUsCancelButton").click();
	}
	
	@When("^Clear First and Last Name field and click on Save then Verify Validation message$")
	public void clear_First_and_Last_Name_field_and_click_on_Save_then_Verify_Validation_message() throws Throwable {
		Thread.sleep(5000);
    	getWebElement("EditName").click();
    	Thread.sleep(3000);
    	getWebElement("UserEditFirstName").clear();
    	impliciteWait(5);
    	getWebElement("UserEditLastName").clear();
    	impliciteWait(5);
    	getWebElement("EditNameSave").click();
    	
    	Thread.sleep(2000);
    	String Message  = getWebElement("EditNameFirstNameValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='fTitleError']", Message);
		
		Thread.sleep(2000);
		getWebElement("UserEditFirstName").sendKeys("123");
		Thread.sleep(1000);
		getWebElement("EditNameSave").click();
		
		Thread.sleep(2000);
    	String Message1  = getWebElement("EditNameLastNameValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='lTitleError']", Message1);
		
		Thread.sleep(2000);
		getWebElement("EditNameCancelButton").click();
	}
	
	@When("^Click on Profile Settings and Select Payment Settings then verify the Validation messages$")
	public void click_on_Profile_Settings_and_Select_Payment_Settings_then_verify_the_Validation_messages() throws Throwable {
		Thread.sleep(5000);
		getWebElement("ProfileSettings").click();
		Thread.sleep(2000);
    	getWebElement("PaymentSettings").click();

    	Thread.sleep(3000);
    	driver.getWindowHandle();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id='user-payment-info-form']/div[1]/h4")));
    	Library lib = new Library();
    	lib.Select("PIfield", 0);
    	Thread.sleep(1000);
    	lib.Select("POfield", 0);
    	Thread.sleep(1000);
    	getWebElement("SavePaymentSettings").click();
    	
    	Thread.sleep(2000);
		String Message  = getWebElement("PaymentSettingsPIValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='user-payment-info-form:pi-error']", Message);
		
		Thread.sleep(2000);
		String Message1  = getWebElement("PaymentSettingsPOValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='user-payment-info-form:po-error']", Message1);
		
		Thread.sleep(2000);
		getWebElement("CancelPaymentSettings").click();
	}
	
	@When("^Clear Labname and Click on Save then verify Validation message$")
	public void clear_Labname_and_Click_on_Save_then_verify_Validation_message() throws Throwable {
		impliciteWait(5);
		getWebElement("EditLabNameicon").click();
		impliciteWait(5);
		getWebElement("EditLabName").clear();
		Thread.sleep(2000);
		getWebElement("SaveEditLabName").click();

		Thread.sleep(2000);
		String Message  = getWebElement("EditLabValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='labTitleError']", Message);
		
		Thread.sleep(2000);
		getWebElement("EditLabCloseButton").click();
	}
	
	@When("^Enter Invalid Email Address and Click on Submit then verify the message$")
	public void enter_Invalid_Email_Address_and_Click_on_Submit_then_verify_the_message() throws Throwable {
		impliciteWait(5);
		getWebElement("EmailAddress").sendKeys("123@login.com");
		getWebElement("SubmitButton").click();
		
		String Message  = getWebElement("ForgotPasswordInvalidEmailIDMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='resetForm']/ul/li", Message);
		
		Thread.sleep(2000);
		getWebElement("SubmitButton").click();
		
		String Message1  = getWebElement("ForgotPasswordValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='resetForm:forgotpass-error']", Message1);
	}
	
	@Given("^Open Login Page$")
	public void open_Login_Page() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
	}

	@When("^Enter Invalid Email ID and Password then Click Signin then verify messages$")
	public void enter_Invalid_Email_ID_and_Password_then_Click_Signin_then_verify_messages() throws Throwable {
		getWebElement("Enotebook.login.username").sendKeys("123@login.com");
		getWebElement("Enotebook.login.password").sendKeys("admin");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(2000);
		getWebElement("HomePageLoginButton").click();
		getWebElement("Enotebook.login.username").sendKeys("123@login.com");
		getWebElement("Enotebook.login.password").sendKeys("admin");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		String Message  = getWebElement("InvalidLoginMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='loginForm']/ul/li", Message);
		
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		String Message1  = getWebElement("LoginValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='loginInfo-error']", Message1);
	}
	
	@Given("^Login as Stockroom Admin$")
	public void login_as_MSAdmin_and_verify_the_Stockroom_List_count() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("stkroomadmin@20email.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}

	@When("^Verify the Stockroom List count$")
	public void verify_the_Stockroom_List_count() throws Throwable {
		Thread.sleep(5000);
		String stkroomcount = getWebElement("StockroomCount").getText();
		System.out.println(stkroomcount);
		
		StringBuffer sb = new StringBuffer(stkroomcount);
		sb.delete(3, 19);
		System.out.println(sb);
	}
	
	@When("^Click on Searched Stockroom$")
	public void click_on_Searched_Stockroom() throws Throwable {
		Thread.sleep(3000);
		getWebElement("LabMembersSearch").sendKeys("23Jan");
		Thread.sleep(1000);
		getWebElement("FirstStockroomNameclick").click();
	}

	@When("^Navigate to Stockroom list from the Side Bar$")
	public void navigate_to_Stockroom_list_from_the_Side_Bar() throws Throwable {
		Thread.sleep(3000);
		getWebElement("SideBarStkroomList").click();
	}
	
	@When("^Click on Contact Us from the Side Bar$")
	public void click_on_Contact_Us_from_the_Side_Bar() throws Throwable {
		Thread.sleep(3000);
		getWebElement("SideBarContactUs").click();
	}

	@When("^Enter Feedback and Click on Submit$")
	public void enter_Feedback_and_Click_on_Submit() throws Throwable {
		impliciteWait(5);
		getWebElement("DescribeFeedback").sendKeys("Test Feedback");
		Thread.sleep(1000);
		getWebElement("ContactUsMessage").click();
	}
	
	@When("^Enter New Email Address and Click on Submit$")
	public void enter_New_Email_Address_and_Click_on_Submit() throws Throwable {
		impliciteWait(5);
		getWebElement("EmailAddress").sendKeys("Newuser2201@mailinator.com");
		getWebElement("SubmitButton").click();
	}

	@When("^Open Mailinator in new tab and verify Reset Password Email Template then Reset Password and login$")
	public void open_Mailinator_in_new_tab_and_verify_Reset_Password_Email_Template_then_Reset_Password_and_login() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");
	
		String NewPassword = ExcelUtils.getCellData(1, 19);
		String ConfirmPassword = ExcelUtils.getCellData(1, 20);
		
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys("Newuser2201@mailinator.com");
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("To reset your password, click on the button"));
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("For your security, click the link below within 24 hours to reset your password. If the link expires, just reset it again."));
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("If you don't want to reset your password, you can ignore this message - someone probably typed in your email address by mistake."));
		Thread.sleep(3000);
		driver.findElement(By.linkText("Reset password")).click();
		Thread.sleep(3000);
		ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
		Thread.sleep(3000);
		getWebElement("ResetPasswordNewPassword").sendKeys(NewPassword);
		Thread.sleep(1000);
		getWebElement("ResetPasswordConfirmPassword").sendKeys(ConfirmPassword);
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		driver.getPageSource().contains("Your password changed successfully. Please try to log in.");
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys("Newuser2201@mailinator.com");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.password").sendKeys(NewPassword);
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
	}

	@Then("^Enter the required field in Change Password popup and click on Save$")
	public void enter_the_required_field_in_Change_Password_popup_and_click_on_Save() throws Throwable {
		getWebElement("CurrentPassword").sendKeys("admin1234");
		getWebElement("NewPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("ConfirmPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("SavePassword").click();
		Thread.sleep(10000);
		getWebElement("ChangePasswordCancelButton").click();
	}
	
	@Given("^Click on Forgot Password link$")
	public void click_on_Forgot_Password_link() throws Throwable {
		init();
		impliciteWait(5);
		getWebElement("HomePageLoginButton").click();
		Thread.sleep(3000);
		getWebElement("ForgotPassword").click();
	}
	
	@Then("^Enter Required fields and Invite$")
	public void enter_Required_fields_and_Invite() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 22);
		
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys(MailIdCreation);
		Thread.sleep(1000);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(1000);
		getWebElement("InviteButton").click();
	}

	@Then("^Open Mailinator in new tab and verify Invite New User Email Template$")
	public void open_Mailinator_in_new_tab_and_verify_Invite_New_User_Email_Template() throws Throwable {
	    
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 22);
		
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		Thread.sleep(3000);
		getWebElement("MailinatorEmailField").sendKeys(MailIdCreation);
		Thread.sleep(5000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("You have been Invited by"));
		
		Thread.sleep(3000);
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
	}

	@Then("^Delete the Invited Member$")
	public void delete_the_Invited_Member() throws Throwable {
		Thread.sleep(3000);
		getWebElement("LabMemberDeleteIcon1").click();
	}
	
	@Given("^Login to Application with specific user3$")
	public void login_to_Application_with_specific_user3() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("Newuser2202@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}
	
	@Given("^Login to Application with specific user4$")
	public void login_to_Application_with_specific_user4() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("Newuser2201@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}

	@When("^Change the Role to Member$")
	public void change_the_Role_to_Member() throws Throwable {
		impliciteWait(5);
		Library lib = new Library();
		lib.Select("LabMemberRole1Dropdown", 1);
	}

	@Then("^Open Mailinator in new tab and verify User Role Change Email Template$")
	public void open_Mailinator_in_new_tab_and_verify_User_Role_Change_Email_Template() throws Throwable {
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys("2201manager@mailinator.com");
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(" Your role has been updated to "));
		
		Thread.sleep(3000);
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
	}

	@Then("^Change the Role to Manager$")
	public void change_the_Role_to_Manager() throws Throwable {
		impliciteWait(5);
		Library lib = new Library();
		lib.Select("LabMemberRole1Dropdown", 0);
	}
	
	@When("^Edit the First Name and Save$")
	public void edit_the_First_Name_and_Save() throws Throwable {
		Thread.sleep(5000);
		getWebElement("EditName").click();
		Thread.sleep(3000);
		getWebElement("UserEditFirstName").sendKeys("1");
		Thread.sleep(1000);
		getWebElement("SaveEditLabName").click();
	}

	@Then("^Open Mailinator in new tab and verify User Profile Update Email Template$")
	public void open_Mailinator_in_new_tab_and_verify_User_Profile_Update_Email_Template() throws Throwable {
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys("Newuser2201@mailinator.com");
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(" User Profile has been Updated "));
		
		Thread.sleep(3000);
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
	}

	@Then("^Edit the First Name same as before and Save$")
	public void edit_the_First_Name_same_as_before_and_Save() throws Throwable {
		Thread.sleep(5000);
		getWebElement("EditName").click();
		Thread.sleep(3000);
		getWebElement("UserEditFirstName").sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		getWebElement("SaveEditLabName").click();
	}
	
	@When("^Change the Member Status to Inactive then change to Active again$")
	public void change_the_Member_Status_to_Inactive_then_change_to_Active_again() throws Throwable {
		Thread.sleep(2000);
		getWebElement("StockroomUserStatusChange").click();
		Thread.sleep(5000);
		getWebElement("StockroomUserStatusChange").click();
	}

	@Then("^Open Mailinator in new tab and verify User Activated Email Template$")
	public void open_Mailinator_in_new_tab_and_verify_User_Activated_Email_Template() throws Throwable {
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys("2201manager@mailinator.com");
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Your account is approved"));
		
		Thread.sleep(3000);
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
	}
	
	@When("^Click on Inventory and Request then navigate to Material$")
	public void click_on_Inventory_and_Request_then_navigate_to_Material() throws Throwable {
		impliciteWait(5);
		getWebElement("SideBarInventoryAndRequest").click();
		Thread.sleep(1000);
		getWebElement("NavigationMaterial").click();
	}

	@When("^Click on Add New Material$")
	public void click_on_Add_New_Material() throws Throwable {
		Thread.sleep(1000);
		getWebElement("AddNewMaterial").click();
	}

	@When("^Enter the Material Name then Add to Inventory$")
	public void enter_the_Material_Name_then_Add_to_Inventory() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "TestClass");

		String MaterialName = ExcelUtils.getCellData(1, 0);
		
		Thread.sleep(1000);
		getWebElement("MaterialNameField").sendKeys(MaterialName);
		Thread.sleep(8000);
		getWebElement("MaterialNameField").sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		getWebElement("MaterialNameField").sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		getWebElement("MaterialNameField").sendKeys(Keys.ENTER);
		/*Library lib = new Library();
		lib.Select("MaterialNameField", 2);*/
		Thread.sleep(1000);
		getWebElement("AddToInventory").click();
		Thread.sleep(1000);
		getWebElement("OKButton").click();
	}

	@Then("^Verify the Material Name$")
	public void verify_the_Material_Name() throws Throwable {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "TestClass");

		String MaterialName = ExcelUtils.getCellData(1, 0);
		
		Assert.assertTrue(driver.getPageSource().contains(MaterialName));
	}
	
	@Given("^Login to Application with specific user5$")
	public void login_to_Application_with_specific_user5() throws Throwable {
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("28feb2144@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}

	@Then("^Open Nadamail in New tab and create new email ID$")
	public void open_Nadamail_in_New_tab_and_create_new_email_ID() throws Throwable {
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 24);
		String FirstName = ExcelUtils.getCellData(1, 25);
		String LastName = ExcelUtils.getCellData(1, 26);
		
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(MailIdCreation);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(2000);
		EmailId = driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).getText();
	}
	
	private String EmailId;
	
	private List<String> tabs;

	@Then("^Navigate to Mconnected Lab and Invite the newly added Manager$")
	public void navigate_to_Mconnected_Lab_and_Invite_the_newly_added_Manager() throws Throwable {
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
	}

	@Then("^Navigate to Nadamail and Register newly added Manager$")
	public void navigate_to_Nadamail_and_Register_newly_added_Manager() throws Throwable {
		Thread.sleep(15000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(3000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
	}

	@Then("^Enter the required fields in the Register page and Signup$")
	public void enter_the_required_fields_in_the_Register_page_and_Signup() throws Throwable {
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 24);
		String FirstName = ExcelUtils.getCellData(1, 25);
		String LastName = ExcelUtils.getCellData(1, 26);
		
		Thread.sleep(7000);
		
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
		getWebElement("RegFirstName").sendKeys(FirstName);
		getWebElement("RegLastName").sendKeys(LastName);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		Library lib = new Library();
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
	}
	
	@Then("^Login to the registered EmailID and Logout$")
	public void login_to_the_registered_EmailID() throws Throwable {
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys(EmailId);
		Thread.sleep(1000);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		getWebElement("DefaultLabName").click();
		impliciteWait(5);
		getWebElement("SetupYourProfilePageNextButton").click();
		Thread.sleep(15000);
		getWebElement("TutorialTipsGotoMyLabButton").click();
		Thread.sleep(3000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(2000);
		getWebElement("Enotebook.logout").click();
	}
	
	@Then("^Login to Application with specific user6$")
	public void login_to_Application_with_specific_user6() throws Throwable {
		Thread.sleep(3000);
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("01mar1526@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}

	@When("^Invite the Existing Manager$")
	public void invite_the_Existing_Manager() throws Throwable {
		Thread.sleep(3000);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
	}

	@Then("^Navigate to Nadamail and Confirm to Join$")
	public void navigate_to_Nadamail_and_Confirm_to_Join() throws Throwable {
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
		getWebElement("InboxEmailID").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaConfirmToJoinButton").click();
	}

	@Then("^Signin as Manager Login and Click on Join Lab$")
	public void signin_as_Manager_Login_and_Click_on_Join_Lab() throws Throwable {
		Thread.sleep(7000);
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
	    getWebElement("Enotebook.login.password").sendKeys("admin123");
	    Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		getWebElement("JoinLabButton").click();
	}
	
	@Given("^Lab member is logged in to the application And is in lab catalog list$")
	public void lab_member_is_logged_in_to_the_application_And_is_in_lab_catalog_list() throws Throwable {
		init();
		Reporter.log("Login to Application as lab member");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("labmember@20mm.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to lab catalog list
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group").click();
		impliciteWait(3);
		getWebElement("SubMenu_LabCatalog").click();
	}
	
	@When("^Lab member clicks on product name link of any product created by lab member$")
	public void lab_member_clicks_on_product_name_link_of_any_product_created_by_lab_member() throws Throwable {
		//LOGIN TEST DATA
		String productName = "Test_Material_2";
		
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab member");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(productName);
		impliciteWait(5);
		
		//Navigate to edit lab catalog page
		getWebElement("LabCatalogCreatedByMember").click();
		impliciteWait(5);
	}
	
	@When("^Lab member clicks on product name link of any product created by lab manager$")
	public void lab_member_clicks_on_product_name_link_of_any_product_created_by_lab_manager() throws Throwable {
		//LOGIN TEST DATA
		String productName = "Test_Material_3";
		
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab member");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(productName);
		impliciteWait(5);
		
		//Navigate to edit lab catalog page
		getWebElement("LabCatalogCreatedByMember").click();
		impliciteWait(5);
	}
	
	@When("^Lab member clicks on product name link of any product created by lab owner$")
	public void lab_member_clicks_on_product_name_link_of_any_product_created_by_lab_owner() throws Throwable {
		//LOGIN TEST DATA
		String productName = "Test_Material_1";
		
		//Search product in lab catalog
		Reporter.log("Select the lab catalog product created by Lab member");
		getWebElement("LabCatalog_SearchBox").click();
		impliciteWait(5);
		getWebElement("LabCatalog_SearchBox").sendKeys(productName);
		impliciteWait(5);
		
		//Navigate to edit lab catalog page
		getWebElement("LabCatalogCreatedByMember").click();
		impliciteWait(5);
	}
	
	@When("^Edits the product details except Catalog SKU and Vendor field$")
	public void edits_the_product_details_except_Catalog_SKU_and_Vendor_field() throws Throwable {
		//Update lab catalog product
		Random rand = new Random();
		String randomNumber = String.valueOf(rand.nextInt(100));
		getWebElement("EditLabCatalog.Qty").clear();
		impliciteWait(2);
		getWebElement("EditLabCatalog.Qty").sendKeys(randomNumber);
	}
	
	@When("^Clicks on update product details button$")
	public void clicks_on_update_product_details_button() throws Throwable {
		getWebElement("EditLabCatalog.SaveBtn").click();
		impliciteWait(2);
	}
	
	@Then("^Success message \"([^\"]*)\" should display$")
	public void success_message_should_display(String arg1) throws Throwable {
		String actualSuccessMessage = getWebElement("EditLabCatalogSuccessMsg").getText();
		Assert.assertEquals(actualSuccessMessage, "Success! Catalog updated successfully!","Lab member failed to update lab catalog created by lab member!");
	}
	
	@Given("^Lab catalog product is created by lab member$")
	public void lab_catalog_product_is_created_by_lab_member() throws Throwable {
		// Lab catalog product should be created by automation user as part of test data
		// Test data
		// Create a lab catalog product with name as Test_Material_2 by logging as lab member of a particular lab
	}
	
	@Given("^Lab catalog product is created by lab manager$")
	public void lab_catalog_product_is_created_by_lab_manager() throws Throwable {
		// Lab catalog product should be created by automation user as part of test data 
		// Test data
		// Create a lab catalog product with name as Test_Material_3 by logging as lab member of a particular lab
	}

	@Given("^Lab catalog product is created by lab owner$")
	public void lab_catalog_product_is_created_by_lab_owner() throws Throwable {
		// Lab catalog product should be created by automation user as part of test data
		// Test data
		// Create a lab catalog product with name as Test_Material_1 by logging as lab member of a particular lab
	}
	
	@Given("^User is logged in as Lab Owner and is in Lab members page$")
	public void user_is_logged_in_as_Lab_Owner_and_is_in_Lab_members_page() throws Throwable {
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
		Thread.sleep(2000);
	}
	
	//Creating a shared property file for complete commerce module
	Properties commerceTestsSharedDataRepository = new Properties();
	
	@When("^User invites a new lab manager to lab and registers to lab$")
	public void user_invites_a_new_lab_manager_to_lab_and_registers_to_lab() throws Throwable {
		//Create unique number
		Date date = new Date();
		Long lDateTimeStamp = date.getTime();
		String dateTimeStamp = lDateTimeStamp.toString();
		
		//LOGIN TEST DATA
		String MailIdCreation = "mgr"+dateTimeStamp;
	    String FirstName = "lb"+dateTimeStamp;
		String LastName = "mgr"+dateTimeStamp;
		
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
		Thread.sleep(2000);
				
		//Shared registered member in shared data repository
		commerceTestsSharedDataRepository.setProperty("FirstName", FirstName);
		commerceTestsSharedDataRepository.setProperty("LastName", LastName);
		commerceTestsSharedDataRepository.setProperty("UserID", createdUserId);
	}
	
	@When("^User navigates to lab settings Page$")
	public void user_navigates_to_lab_settings_Page() throws Throwable {
		//Navigate to lab settings
		Reporter.log("Navigate to lab settings");
		getWebElement("SideBar_Lab_Settings_Group").click();
		impliciteWait(5);
	}
	
	@Then("^Lab manager approver and procurer status should be active and toggle set to ON$")
	public void lab_manager_approver_and_procurer_status_should_be_active_and_toggle_set_to_ON() throws Throwable {
		//Retrieve data from commerce tests shared data repository
		String FirstName = commerceTestsSharedDataRepository.getProperty("FirstName");
		String LastName = commerceTestsSharedDataRepository.getProperty("LastName");
		
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
	}
	
	@When("^User navigates to Lab members page and changes member role from Lab manager to Lab member$")
	public void user_navigates_to_Lab_members_page_and_changes_member_role_from_Lab_manager_to_Lab_member() throws Throwable {
		//Retrieve data from commerce shared data repository
		String createdUserId = commerceTestsSharedDataRepository.getProperty("UserID");
		
		//Navigate to lab members page and change role to Lab member from Lab manager
		Reporter.log("Click on User Settings and go to Lab Members page and change member role from Lab manager to Lab member");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		impliciteWait(5);
		getWebElement("LabMembersSearch").click();
		Thread.sleep(2000);
		getWebElement("LabMembersSearch").sendKeys(createdUserId);
		Thread.sleep(5000);
		WebElement memberRow = driver.findElement(By.xpath("//span[@title='"+createdUserId.toLowerCase()+"']/../.."));
		String memberRowNo = memberRow.getAttribute("data-ri");
		Select labMemberRoleDropDown = new Select(driver.findElement(By.id("memberForm:memberTable:"+memberRowNo+":roleLabOwnerDisable")));
		labMemberRoleDropDown.selectByVisibleText("Lab Member");  // Inconsistency in drop down order in members list and Add lab member modal
		Thread.sleep(5000);
	}
	
	@Then("^Default Approver and procurer status for lab manager should reset back to normal and toggle should be OFF$")
	public void default_Approver_and_procurer_status_for_lab_manager_should_reset_back_to_normal_and_toggle_should_be_OFF() throws Throwable {
		//Retrieve data from commerce tests shared data repository
		String FirstName = commerceTestsSharedDataRepository.getProperty("FirstName");
		String LastName = commerceTestsSharedDataRepository.getProperty("LastName");
		
		Reporter.log("Verify default approver and procurer settings for lab user after changing role from lab manager to lab member");
		try {
			WebElement labMemberRow = driver.findElement(By.xpath("//td[contains(text(),'"+FirstName+"  "+LastName+"')]/.."));
			String labMemberRowNo = labMemberRow.getAttribute("data-ri");
			String approverStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[3]")).getText();
			String procurerStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[4]")).getText();
			Assert.assertEquals(approverStatus, "Inactive", "Lab manager after changing role to Lab member shows approver status as Active");
			Assert.assertEquals(procurerStatus, "Inacitve", "Lab manager after changing role to Lab member shows procurer status as Active");
			// for procurer role inactive label refer CL-2795 and change accordingly
		}
		catch(Exception expn) {
			 System.out.println("Exception occurred");
			 expn.printStackTrace();
		}
		Thread.sleep(2000);
	}
	
	@When("^User invites a new lab member to lab and registers to lab$")
	public void user_invites_a_new_lab_member_to_lab_and_registers_to_lab() throws Throwable {
		//Create unique number
		Date date = new Date();
		Long dateTimeStamp = date.getTime();
		
		//LOGIN TEST DATA
		String MailIdCreation = "Mem-Mgr"+dateTimeStamp;
	    String FirstName = "Lb"+dateTimeStamp;
		String LastName = "Mem-Mgr"+dateTimeStamp;
		
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
		
		//Shared registered member in shared data repository
		commerceTestsSharedDataRepository.setProperty("FirstName", FirstName);
		commerceTestsSharedDataRepository.setProperty("LastName", LastName);
		commerceTestsSharedDataRepository.setProperty("UserID", createdUserId);
		
		Thread.sleep(2000);
	}
	
	@Then("^Lab member approver and procurer status should not be active and toggle set to OFF$")
	public void lab_member_approver_and_procurer_status_should_not_be_active_and_toggle_set_to_OFF() throws Throwable {
		//Retrieve data from commerce shared tests data repository
		String FirstName = commerceTestsSharedDataRepository.getProperty("FirstName");
		String LastName = commerceTestsSharedDataRepository.getProperty("LastName");
		
		//Verify the default approver and procurer role status to Lab member and privileges
		Reporter.log("Verify default approver and procurer settings for lab member");
		try {
			WebElement labMemberRow = driver.findElement(By.xpath("//td[contains(text(),'"+FirstName+"  "+LastName+"')]/.."));
			String labMemberRowNo = labMemberRow.getAttribute("data-ri");
			String approverStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[3]")).getText();
			String procurerStatus =  driver.findElement(By.xpath("//html//tr[@data-ri='"+labMemberRowNo+"']/td[4]")).getText();
			Assert.assertEquals(approverStatus, "Inactive", "Lab member approver status shows as active by default!");
			Assert.assertEquals(procurerStatus, "Inacitve", "Lab member procurer status shows as active by default!"); 
			//CL-2795 Change accordingly
		}
		catch(Exception expn) {
			 System.out.println("Exception occurred");
			 expn.printStackTrace();
		}
		Thread.sleep(2000);
	}
	
	@When("^User navigates to Lab members page and changes member role from Lab member to Lab manager$")
	public void user_navigates_to_Lab_members_page_and_changes_member_role_from_Lab_member_to_Lab_manager() throws Throwable {
		//Retrieve data from commerce tests shared data repository
		String createdUserId = commerceTestsSharedDataRepository.getProperty("UserID");
		
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
	}
	
	@Then("^Default Approver and procurer status for lab manager should be active and toggle should be ON$")
	public void default_Approver_and_procurer_status_for_lab_manager_should_be_active_and_toggle_should_be_ON() throws Throwable {
		//Retrieve data from commerce tests shared data repository
		String FirstName = commerceTestsSharedDataRepository.getProperty("FirstName");
		String LastName = commerceTestsSharedDataRepository.getProperty("LastName");
		
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
		Thread.sleep(2000);
	}
	
	//// Research script development
	
	@Given("^User is logged in to the application as lab owner and is in protocol templates list page$")
	public void user_is_logged_in_to_the_application_as_lab_owner_and_is_in_protocol_templates_list_page() throws Throwable {
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_TestData, "Research_New");
		String Org1_Lab1_User_Name = ExcelUtils.getCellData(1,6);
		String Org1_Lab1_Pwd = ExcelUtils.getCellData(1,7);
		
		//Login to application as lab owner
		init();
		Reporter.log("Login to Application as lab owner under one organisation");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(Org1_Lab1_User_Name);
		getWebElement("Enotebook.login.password").sendKeys(Org1_Lab1_Pwd);
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to protocol template list
		Reporter.log("Click on Research navigation module");
		getWebElement("Research.mouseover").click();
		getWebElement("ProtocolTemplates.link").click();
		Thread.sleep(3000);
	}
	
	@When("^User clicks on Create new button and enters protocol details$")
	public void user_clicks_on_Create_new_button_and_enters_protocol_details() throws Throwable {
		
		//LOGIN TEST DATA
		//ExcelUtils.setCellData(Constants.Path_TestData+Constants.File_TestData,"Research_New","P",1,4);
		String protocolName_org = ExcelUtils.getCellData(1,4);
		Library TodayDate = new Library();
		String protocolName = protocolName_org+TodayDate.Date();
		//ExcelUtils.setCellData(Constants.Path_TestData+Constants.File_TestData,"Research_New",protocolName,1,4);
		String protocolDescription = ExcelUtils.getCellData(1,5);
		
		//Get starting protocol template count
		String startTemplateCount = getWebElement("Count").getText();
		System.out.println("Starting template count is "+startTemplateCount);
		String templateCountBefore = startTemplateCount.split(" ")[0];
		
		//Enter protocol details
		Reporter.log("Create new protocol");
		getWebElement("Protocol.CreateNewButton").click();
		Thread.sleep(3000);
		getWebElement("Protocol.Title").sendKeys(protocolName);
		getWebElement("Protocol.Description").click();
		getWebElement("Protocol.Description").sendKeys(protocolDescription);
		
		//Set initial template count in test data excel
		//ExcelUtils.setCellData(Constants.Path_TestData+Constants.File_TestData,"Research_New",templateCountBefore,1,10);
	}
	
	@When("^Clicks on Create button$")
	public void clicks_on_Create_button() throws Throwable {
		getWebElement("Protocol.Create").click();
		Thread.sleep(4000);
	}
	
	@Then("^Protocol should be created successfully$")
	public void protocol_should_be_created_successfully() throws Throwable {
		//LOGIN TEST DATA
		String templateCountBefore = ExcelUtils.getCellData(1, 10);
		int iTemplateCountBefore = Integer.parseInt(templateCountBefore);
		
		//Verify protocol in protocol template list
		Reporter.log("Verify whether public protocol is created in lab 1");
		String PCOunt1 = getWebElement("Count").getText();
		System.out.println("count is "+PCOunt1);
		String templateCountAfter = PCOunt1.split(" ")[0];
		int AfterPrtclCounti = Integer.parseInt(templateCountAfter);
		int AfterPrtclCounti1 = (AfterPrtclCounti-1);
		Assert.assertEquals(AfterPrtclCounti1, iTemplateCountBefore);
		System.out.println("Protocol created successfully");
	}
	
	@Then("^User logs into another lab of same organisation$")
	public void user_logs_into_another_lab_of_same_organisation() throws Throwable {
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_TestData, "Research_New");
		String Org1_Lab2_User_Name = ExcelUtils.getCellData(1,8);
		String Org1_Lab2_Pwd = ExcelUtils.getCellData(1,9);
		
		//Login to another lab under same organization
		init();
		Reporter.log("Login to Application as lab owner to another lab under same organisation");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(Org1_Lab2_User_Name);
		getWebElement("Enotebook.login.password").sendKeys(Org1_Lab2_Pwd);
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
	}
	
	@Then("^Navigates to protocol templates list page$")
	public void navigates_to_protocol_templates_list_page() throws Throwable {
		//Navigate to protocol template list
		Reporter.log("Click on Research navigation module");
		getWebElement("Research.mouseover").click();
		getWebElement("ProtocolTemplates.link").click();
		Thread.sleep(3000);
	}
	
	@Then("^Protocol created from original lab should be displayed in the protocol templates list$")
	public void protocol_created_from_original_lab_should_be_displayed_in_the_protocol_templates_list() throws Throwable {
		//LOGIN TEST DATA
		String protocolName = ExcelUtils.getCellData(1,4);
		
		//Verify the protocol in protocol template list
		Reporter.log("Verify the protocol in lab 2 of same organisation");
		boolean bProtocolExists = driver.findElement(By.linkText(protocolName)).isDisplayed();
		Assert.assertEquals(bProtocolExists,true,"protocol template not visible in different lab under same organisation");
	}
	
	@Given("^User is logged in to the application and is in project main page$")
	public void user_is_logged_in_to_the_application_and_is_in_project_main_page() throws Throwable {
		//Library TodayDate = new Library();
		//String uniqueNumber = TodayDate.Date();
		//String projectName = "Project"+uniqueNumber;
		String projectName = "Test project 1";
		String LabOwner_User_Name = "VinayakaUserMng5@mailinator.com";
		String LabOwner_Pwd = "admin123";
		String projectDescription = "Testing of scenario,Assign code to project";
		
		//Login to application as lab owner
		init();
		Reporter.log("Login to Application as lab owner under one organisation");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(LabOwner_User_Name);
		getWebElement("Enotebook.login.password").sendKeys(LabOwner_Pwd);
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		//Navigate to project list page
		Reporter.log("Click on Research navigation module");
		impliciteWait(5);
		getWebElement("SideBar_ResearchHub_Group").click();
		Thread.sleep(1000);
		getWebElement("SubMenu_Projects").click();
		Thread.sleep(4000);
		
		//Create project
		getWebElement("CreateNewProjectButton").click();
		Thread.sleep(5000);
		getWebElement("ProjectName").click();
		getWebElement("ProjectName").sendKeys(projectName);
		getWebElement("ProjectDescription").sendKeys(projectDescription);
		getWebElement("SaveProjectButton").click();
		Thread.sleep(4000);
		
		//Verify if project is created or not
		boolean bCreateProject = driver.findElement(By.linkText(projectName)).isDisplayed();
		Assert.assertEquals(bCreateProject,true,"Project created successfully");
		
		//Navigate to edit project page
		driver.findElement(By.linkText(projectName)).click();
		impliciteWait(3);
	}
	
	@When("^User clicks on project settings$")
	public void user_clicks_on_project_settings() throws Throwable {
		getWebElement("ProjectSettings").click();
		Thread.sleep(2000);
	}
	
	@When("^Selects the assign a code option$")
	public void selects_the_assign_a_code_option() throws Throwable {
		getWebElement("ProjectSettings_AssignCode").click();
		Thread.sleep(3000);
	}
	
	@When("^Enters project code$")
	public void enters_project_code() throws Throwable {
		//LOGIN TEST DATA
		String projectCode = "P1~`!@#$%^&*()-_+={}[]|\\:;<>,.?/'\"";
		
		getWebElement("AddProjectCode").click();
		impliciteWait(2);
		getWebElement("AddProjectCode").sendKeys(projectCode);
	}
	
	@When("^Clicks on Save button$")
	public void clicks_on_Save_button() throws Throwable {
		getWebElement("SaveProjectCode").click();
		Thread.sleep(3000);
	}
	
	@Then("^Success message should display$")
	public void success_message_should_display() throws Throwable {
		//Verify if project code is assigned to project or not
		String getSuccessMsg = getWebElement("ProjectCodeSuccMsg").getText();
		Assert.assertEquals(getSuccessMsg,"Success! Project Code Updated!","Code not assigned to project successfully");
		Thread.sleep(2000);
	}
	
	@Then("^Project code should be added to the project$")
	public void project_code_should_be_added_to_the_project() throws Throwable {
		//LOGIN TEST DATA
		String projectCode = "P1~`!@#$%^&*()-_+={}[]|\\:;<>,.?/'\"";
		
		//Verify if project code is assigned to project or not
		String getProjectCode = getWebElement("ProjectCode").getText();
		Assert.assertEquals(getProjectCode,projectCode,"Code not assigned to project successfully");
	}
	
	
	
}
