package research;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Update_Material_In_Experiment_Action extends TestBase {

	@Test
	public void updateMaterialInExperiment() throws Exception {
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

		// EXPERIMENT MATERIALS DETAILS PULLED FROM TEST DATA AFTER ADDING MATERIAL TO
		// INVENTORY
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String materialName = ExcelUtils.getCellData(1, 0);

		// EDIT EXPERIMENT MATERIALS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"EditExperimentMaterials");
		String editExpiry = ExcelUtils.getCellData(1, 0);
		String editLOT = ExcelUtils.getCellData(1, 1);
		String editQtyRequired = ExcelUtils.getCellData(1, 2);
		String editQtyRequiredUnit = ExcelUtils.getCellData(1, 3);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Update materials from experiment material section");
		ResearchFunc.editMaterialFromExperiment(materialName, editExpiry, editLOT, editQtyRequired,
				editQtyRequiredUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test
	public void updateMaterialInExperimentWhichIsConsumedInInventory() throws Exception {
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

		// EXPERIMENT MATERIALS DETAILS PULLED FROM TEST DATA AFTER ADDING MATERIAL TO
		// INVENTORY
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String materialName = ExcelUtils.getCellData(2, 0);
		String QtyRequired = ExcelUtils.getCellData(2, 8);
		String QtyRequiredUnit = ExcelUtils.getCellData(2, 9);

		// EDIT EXPERIMENT MATERIALS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"EditExperimentMaterials");
		String editExpiry = ExcelUtils.getCellData(2, 0);
		String editLOT = ExcelUtils.getCellData(2, 1);
		String editQtyRequired = ExcelUtils.getCellData(2, 2);
		String editQtyRequiredUnit = ExcelUtils.getCellData(2, 3);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Update materials from experiment material section");
		ResearchFunc.editMaterialFromExperimentWhichIsConsumedFromInventory(materialName, QtyRequired, QtyRequiredUnit, editExpiry, editLOT, editQtyRequired,
				editQtyRequiredUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}