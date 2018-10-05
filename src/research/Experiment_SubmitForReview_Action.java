package research;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Experiment_SubmitForReview_Action extends TestBase {
	
	@Test
	public void experimentSubmitForReview_Approve() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		String reviewer_userID = ExcelUtils.getCellData(1, 6);
		String reviewer_pwd = ExcelUtils.getCellData(1, 7);
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		String projectVisibility = ExcelUtils.getCellData(1, 2);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "LabExp"+uniqueNumber;
		String experimentDescription = ExcelUtils.getCellData(1, 1);
		String visibility = ExcelUtils.getCellData(1, 2);
		
		Reporter.log("Login to application as submitter");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Add new experiment with scope Public");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);
		
		Reporter.log("Validate visiblity scope for public experiment");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Submit experiment for review");
		getWebElement("SubmitForReview").click();
		Thread.sleep(4000);
		getWebElement("Review_SearchMember").click();
		Thread.sleep(2000);
		getWebElement("Review_SearchMember").sendKeys(reviewer_userID);
		Thread.sleep(9000);
		String part3 = "//*[contains(@data-item-value,'";
		String part4 = "')]";
		driver.findElement(By.xpath(part3+reviewer_userID+part4)).click();
		Thread.sleep(5000);
		
		Reporter.log("Verify if reviewer is added or not in reviewer modal");
		String getReviewer_1 = driver.findElement(By.xpath("//span[@id='reviewForm:reviewLoop:0:memberCode']")).getAttribute("title").trim();
		String[] getReviewer_2 = getReviewer_1.split("\\(");
		String[] getReviewer_3 = getReviewer_2[1].split("\\)");
		String getReviewer = getReviewer_3[0];
		Assert.assertEquals(getReviewer,reviewer_userID,"Reviewer not added successfully");
		
		Reporter.log("Confirm reviewer and enter authentication password and submit experiment");
		getWebElement("ConfirmReviewer_Check").click();
		Thread.sleep(3000);
		getWebElement("ConfirmOwnerPwd").click();
		getWebElement("ConfirmOwnerPwd").sendKeys(labOwnerPwd);
		getWebElement("SubmitForReview_Button").click();
		Thread.sleep(1000);
		
		Reporter.log("Verify submit for review success message");
		String getSubmitReviewSuccMsg = getWebElement("SubmitForReview_SuccMsg").getText().trim();
		Assert.assertEquals(getSubmitReviewSuccMsg,"Success! Experiment submitted for review Successfully!","Submit for review failed!");
		Thread.sleep(3000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
		
		Reporter.log("Login to application as Reviewer");
		init();
		lib.loginToApplication(reviewer_userID, reviewer_pwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Verify project in reviewer lab");
		ResearchFunc.verifyProjectInProjectList(projectName);
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Verify experiment in reviewer lab");
		ResearchFunc.verifyExperimentInProjectDetailPage(experimentName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Review the experiment with accepted status");
		getWebElement("Reviewer_Approve").click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn dottedBtn-complete']")));
		
		// REFRESH
		ResearchFunc.refreshPage();
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
		
		Reporter.log("Login to application as submitter");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Verify experiment accepted status");
		boolean bVerifyAcceptedStatus = getWebElement("ExpCompletedStatus").isDisplayed();
		Assert.assertEquals(bVerifyAcceptedStatus,true,"Review experiment with approve status as accepted failed!");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(3000);
		login.Logout();
	}

	@Test
	public void experimentSubmitForReview_Reject() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		String reviewer_userID = ExcelUtils.getCellData(1, 6);
		String reviewer_pwd = ExcelUtils.getCellData(1, 7);
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		String projectVisibility = ExcelUtils.getCellData(1, 2);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "LabExp"+uniqueNumber;
		String experimentDescription = ExcelUtils.getCellData(1, 1);
		String visibility = ExcelUtils.getCellData(1, 2);
		
		Reporter.log("Login to application as submitter");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Add new experiment with scope Public");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);
		
		Reporter.log("Validate visiblity scope for public experiment");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Submit experiment for review");
		getWebElement("SubmitForReview").click();
		Thread.sleep(4000);
		getWebElement("Review_SearchMember").click();
		Thread.sleep(2000);
		getWebElement("Review_SearchMember").sendKeys(reviewer_userID);
		Thread.sleep(8000);
		String part3 = "//*[contains(@data-item-value,'";
		String part4 = "')]";
		driver.findElement(By.xpath(part3+reviewer_userID+part4)).click();
		Thread.sleep(3000);
		
		Reporter.log("Verify if reviewer is added or not in reviewer modal");
		String getReviewer_1 = driver.findElement(By.xpath("//span[@id='reviewForm:reviewLoop:0:memberCode']")).getAttribute("title").trim();
		String[] getReviewer_2 = getReviewer_1.split("\\(");
		String[] getReviewer_3 = getReviewer_2[1].split("\\)");
		String getReviewer = getReviewer_3[0];
		Assert.assertEquals(getReviewer,reviewer_userID,"Reviewer not added successfully");
		
		Reporter.log("Confirm reviewer and enter authentication password and submit experiment");
		getWebElement("ConfirmReviewer_Check").click();
		Thread.sleep(3000);
		getWebElement("ConfirmOwnerPwd").click();
		getWebElement("ConfirmOwnerPwd").sendKeys(labOwnerPwd);
		getWebElement("SubmitForReview_Button").click();
		Thread.sleep(1000);
		
		Reporter.log("Verify submit for review success message");
		String getSubmitReviewSuccMsg = getWebElement("SubmitForReview_SuccMsg").getText().trim();
		Assert.assertEquals(getSubmitReviewSuccMsg,"Success! Experiment submitted for review Successfully!","Submit for review failed!");
		Thread.sleep(3000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
		
		Reporter.log("Login to application as Reviewer");
		init();
		lib.loginToApplication(reviewer_userID, reviewer_pwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Verify project in reviewer lab");
		ResearchFunc.verifyProjectInProjectList(projectName);
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Verify experiment in reviewer lab");
		ResearchFunc.verifyExperimentInProjectDetailPage(experimentName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Review the experiment with rejected status");
		getWebElement("Reviewer_Reject").click();
		Thread.sleep(6000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
		
		Reporter.log("Login to application as submitter");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Verify experiment status after experiment is rejected by reviewer");
		boolean bVerifyExpStatus = getWebElement("SubmitForReview").isDisplayed();
		Assert.assertEquals(bVerifyExpStatus,true,"Review experiment with reject status failed!");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}