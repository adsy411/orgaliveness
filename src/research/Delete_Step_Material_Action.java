package research;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Delete_Step_Material_Action extends TestBase {

	@Test
	public void deleteStepMaterialFromExperiment() throws Exception {
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

		// EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);

		// EXPERIMENT STEP TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ExperimentStepMaterials");
		String stepTitle = ExcelUtils.getCellData(1, 2);

		// EXPERIMENT STEP MATERIAL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String stepMaterialName = ExcelUtils.getCellData(1, 0);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Delete step materials from experiment");
		ResearchFunc.deleteStepMaterials(stepTitle, stepMaterialName);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}