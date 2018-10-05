package research;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class AssignCodeToExperiment_Action extends TestBase {
	@Test
	public void assignCodeToExperiment() throws Exception {
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		//PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		//EXPERIMENT CODE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Experiment_AssignCode");
		String experimentCode = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Assign code to experiment");
		ResearchFunc.assignCodeToExperiment(experimentCode);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}