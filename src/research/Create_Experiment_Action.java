package research;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Create_Experiment_Action extends TestBase {

	@Test
	public void createLabExperimentInLabProject() throws Exception {
		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		String projectVisibility = ExcelUtils.getCellData(1, 2);

		// EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "labExp" + uniqueNumber;
		String experimentDescription = ExcelUtils.getCellData(1, 1) + uniqueNumber;
		String visibility = ExcelUtils.getCellData(1, 2);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Add new experiment with scope Lab");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);

		Reporter.log("Verify created experiment in project");
		ResearchFunc.verifyExperimentInProjectDetailPage(experimentName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Validate visiblity scope for lab experiment");
		boolean expVisiblityPublic = getWebElement("ExpVisiblityScope_Lab").isDisplayed();
		Assert.assertEquals(expVisiblityPublic, true, "Experiment visiblity not set as Lab after creation");

		Reporter.log("Click on User Settings and logout");
		login.Logout();
	}

	@Test
	public void createRestrictedExperimentInLabProject() throws Exception {
		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		String projectVisibility = ExcelUtils.getCellData(1, 2);

		// EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "restrictedExp" + uniqueNumber;
		String experimentDescription = ExcelUtils.getCellData(2, 1) + uniqueNumber;
		String visibility = ExcelUtils.getCellData(2, 2);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Add new experiment with scope Private");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);

		Reporter.log("Verify created experiment in project detail page");
		ResearchFunc.verifyExperimentInProjectDetailPage(experimentName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Validate visiblity scope for Private experiment");
		boolean expVisiblityPublic = getWebElement("ExpVisiblityScope_Private").isDisplayed();
		Assert.assertEquals(expVisiblityPublic, true, "Experiment visiblity not set as Private after creation");

		Reporter.log("Click on User Settings and logout");
		login.Logout();
	}

	@Test
	public void createProjectExperimentInRestrictedProject() throws Exception {
		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(2, 0);
		String projectVisibility = ExcelUtils.getCellData(2, 2);

		// EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "projectExperiment" + uniqueNumber;
		String experimentDescription = "creating project level experiment in Restricted project" + uniqueNumber;
		String visibility = ExcelUtils.getCellData(3, 2);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Add new experiment with scope Lab");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);

		Reporter.log("Verify created experiment in project");
		ResearchFunc.verifyExperimentInProjectDetailPage(experimentName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Validate visiblity scope for project scope experiment");
		boolean expVisiblityPublic = getWebElement("ExpVisiblityScope_Lab").isDisplayed();
		Assert.assertEquals(expVisiblityPublic, true, "Experiment visiblity not set as Lab after creation");

		Reporter.log("Click on User Settings and logout");
		login.Logout();
	}

	@Test
	public void createRestrictedExperimentInRestrictedProject() throws Exception {
		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(2, 0);
		String projectVisibility = ExcelUtils.getCellData(2, 2);

		// EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "restrictedExperiment" + uniqueNumber;
		String experimentDescription = "creating restricted level experiment in Restricted project" + uniqueNumber;
		String experimentVisibility = ExcelUtils.getCellData(4, 2);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Add new experiment with scope Private");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, experimentVisibility, projectVisibility);

		Reporter.log("Verify created experiment in project detail page");
		ResearchFunc.verifyExperimentInProjectDetailPage(experimentName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Validate visiblity scope for Restricted experiment");
		boolean expVisiblityPublic = getWebElement("ExpVisiblityScope_Private").isDisplayed();
		Assert.assertEquals(expVisiblityPublic, true, "Experiment visiblity not set as Restricted after creation");

		Reporter.log("Click on User Settings and logout");
		login.Logout();
	}
}
