package research;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Update_Step_Material_In_Experiment_Action extends TestBase {

	@Test
	public void updateMaterialInExperimentStep() throws Exception {
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
		
		// EXPERIMENT STEP MATERIALS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String stepMaterialName = ExcelUtils.getCellData(1, 0);
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ExperimentStepMaterials");
		String stepMaterialReqQty = ExcelUtils.getCellData(1, 0);
		String stepMaterialReqQtyUnit = ExcelUtils.getCellData(1, 1);
		String stepTitle = ExcelUtils.getCellData(1, 2);

		// UPDATE EXPERIMENT STEP MATERIALS
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"UpdateExperimentStepMaterials");
		String updateStepMaterialReqQty = ExcelUtils.getCellData(1, 0);
		String updateStepMaterialReqQtyUnit = ExcelUtils.getCellData(1, 1);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Update step materials in experiment detail page");
		ResearchFunc.updateStepMaterials(stepTitle, stepMaterialName, stepMaterialReqQty, stepMaterialReqQtyUnit,
				updateStepMaterialReqQty, updateStepMaterialReqQtyUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}