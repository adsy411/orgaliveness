package research;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Request_Material_From_Experiment_Action extends TestBase {

	@Test
	public void requestMaterialFromExperiment() throws Exception {
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
		
		// EXPERIMENT MATERIALS DETAILS PULLED FROM TEST DATA AFTER ADDING MATERIAL TO INVENTORY
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String materialName = ExcelUtils.getCellData(1, 0);
		
		// REQUEST EXPERIMENT MATERIALS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"RequestMaterials");
		String requestQty = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Request materials from experiment material section");
		ResearchFunc.requestMaterialFromExperiment(projectName, experimentName, materialName,
				requestQty);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}