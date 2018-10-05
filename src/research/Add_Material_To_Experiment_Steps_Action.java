package research;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Add_Material_To_Experiment_Steps_Action extends TestBase {

	@Test
	public void addMaterialToExperimentSteps_WhichIsPresentInExperiment() throws Exception {
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

		// EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);

		// EXPERIMENT MATERIALS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String materialName = ExcelUtils.getCellData(1, 0);
		String expiryDate = ExcelUtils.getCellData(1, 2);
		String CAS = ExcelUtils.getCellData(1, 4);
		String LOT = ExcelUtils.getCellData(1, 7);
		String QtyRequired = ExcelUtils.getCellData(1, 8);

		// EXPERIMENT STEP TEST DATA
		String stepTitle = "STEP" + uniqueNumber;
		String stepDescription = "STEP DESC" + uniqueNumber;

		// EXPERIMENT STEP MATERIALS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ExperimentStepMaterials");
		String stepMaterialReqQty = ExcelUtils.getCellData(1, 0);
		String stepMaterialReqQtyUnit = ExcelUtils.getCellData(1, 1);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Add new step to the experiment");
		ResearchFunc.addStep(stepTitle, stepDescription);

		// UPDATE STEP DETAILS TO TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ExperimentStepMaterials");
		ExcelUtils.setCellData(stepTitle, 1, 2, Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ExperimentStepMaterials");

		Reporter.log("Add material to experiment step");
		ResearchFunc.addMaterialToExperimentStep(stepTitle, materialName, QtyRequired, CAS, LOT, expiryDate,
				stepMaterialReqQty, stepMaterialReqQtyUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	@Test
	public void addMaterialToExperimentSteps_WhichIsPartiallyUsedInAnotherStep() throws Exception {
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

		// EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);

		// EXPERIMENT MATERIALS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String materialName = ExcelUtils.getCellData(1, 0);
		String expiryDate = ExcelUtils.getCellData(1, 2);
		String CAS = ExcelUtils.getCellData(1, 4);
		String LOT = ExcelUtils.getCellData(1, 7);
		String QtyRequired = ExcelUtils.getCellData(1, 8);

		// EXPERIMENT STEP TEST DATA
		String stepTitle1 = "STEP1" + uniqueNumber;
		String stepDescription1 = "STEP DESC1" + uniqueNumber;
		String stepTitle2 = "STEP2" + uniqueNumber;
		String stepDescription2 = "STEP DESC2" + uniqueNumber;

		// EXPERIMENT STEP MATERIALS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ExperimentStepMaterials");
		String stepMaterialReqQty = ExcelUtils.getCellData(1, 0);
		String stepMaterialReqQtyUnit = ExcelUtils.getCellData(1, 1);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Add a new step to the experiment");
		ResearchFunc.addStep(stepTitle1, stepDescription1);

		Reporter.log("Add material to first experiment step");
		ResearchFunc.addMaterialToExperimentStep(stepTitle1, materialName, QtyRequired, CAS, LOT, expiryDate,
				stepMaterialReqQty, stepMaterialReqQtyUnit);

		Reporter.log("Add new step to the experiment");
		ResearchFunc.addStep(stepTitle2, stepDescription2);

		Reporter.log("Add material to second experiment step");
		ResearchFunc.addMaterialToExperimentStep(stepTitle2, materialName, QtyRequired, CAS, LOT, expiryDate,
				stepMaterialReqQty, stepMaterialReqQtyUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

}