package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class VerifyHomeDashboardLinks_Action extends TestBase{
	
	@Test
	public void VerifyHomeDashboardLinks() throws Exception{
		
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();

		Reporter.log("Verify Start Research Project Link");
		impliciteWait(5);
		getWebElement("StartResearchProjLink").click();
		Thread.sleep(3000);
		verifyText("//*[@id='addProjectFlowModal:researchCreateForm']/div/div[1]/h4", "Create Project");
		Thread.sleep(3000);
		getWebElement("CreateProjectCancelButton").click();
		
		Reporter.log("Verify Increase Storage Link");
		Thread.sleep(3000);
		getWebElement("IncreaseStorageLink").click();
		Thread.sleep(3000);
		verifyText("//*[@id='contactForm']/div/div/div/div/div/div/label", "Describe Feedback");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
		
		Reporter.log("Verify Add New Material Icon");
		Thread.sleep(3000);
		getWebElement("AddNewMaterialIcon").click();
		Thread.sleep(3000);
		verifyText("//*[@id='materialPageForm']/div/div/div[1]/button", "Add New Material");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
		
		Reporter.log("Verify View in Inventory Link");
		Thread.sleep(3000);
		getWebElement("ViewInInventoryLink").click();
		Thread.sleep(3000);
		verifyText("//*[@id='materialPageForm']/div/div/div[1]/button", "Add New Material");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
		
		Reporter.log("Verify Lab Activity Icon");
		Thread.sleep(3000);
		getWebElement("LabActivityIcon").click();
		Thread.sleep(3000);
		verifyText("//*[@id='new_project_link']", "Create New");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
		
		Reporter.log("Verify View Experiments Link");
		Thread.sleep(3000);
		getWebElement("ViewExperimentsLink").click();
		Thread.sleep(3000);
		verifyText("/html/body/div[1]/nav/div/div[2]/div/ol/li[2]/a", "My Profile");
		Thread.sleep(3000);
		getWebElement("HomepageFromBreadCrumb").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
	}
}
