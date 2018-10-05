package research;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Create_Project_Action extends TestBase {

	@Test
	public void createLabProject() throws Exception {

		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		String projectName = "labProject" + uniqueNumber;
		String projectDescription = "Creation of Project with scope as Lab";
		String visibility = "Lab";

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		Reporter.log("Login to application as Lab owner");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Create new project");
		projectName = ResearchFunc.createProject(projectName, projectDescription, visibility);

		Reporter.log("Click on User Settings and logout");
		login.Logout();
	}

	@Test
	public void createRestrictedProject() throws Exception {

		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		String projectName = "restrictedProject" + uniqueNumber;
		String projectDescription = "Creation of Project with scope as Restricted";
		String visibility = "Restricted";

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		Reporter.log("Login to application as Lab owner");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Create new project");
		projectName = ResearchFunc.createProject(projectName, projectDescription, visibility);

		Reporter.log("Click on User Settings and logout");
		login.Logout();
	}
}