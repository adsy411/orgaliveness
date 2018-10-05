package research;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Update_Step_Product_In_Template_Action extends TestBase {

	@Test
	public void updateProductInTemplateStep() throws Exception {
		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"Protocol_Creation");
		String protocolTemplateName = ExcelUtils.getCellData(1, 0);

		// PROTOCOL STEP PRODUCTS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddTemplateProducts");
		String stepProductName = ExcelUtils.getCellData(1, 0);
		String stepProductCatalog = ExcelUtils.getCellData(1, 2);
		String stepProductRequiredQty = ExcelUtils.getCellData(1, 6);
		String stepProductRequiredQtyUnit = ExcelUtils.getCellData(1, 7);

		// PROTOCOL STEP PRODUCTS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"TemplateStepProducts");
		String stepTitle = ExcelUtils.getCellData(1, 2);

		// UPDATE PROTOCOL STEP PRODUCTS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"UpdateTemplateStepProducts");
		String updateStepProductReqQty = ExcelUtils.getCellData(1, 0);
		String updateStepProductReqQtyUnit = ExcelUtils.getCellData(1, 1);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol template detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolTemplateName);

		Reporter.log("Update step product from protocol template steps");
		ResearchFunc.updateProductAddedToProtocolTemplateStep(stepTitle, stepProductName, stepProductCatalog,
				stepProductRequiredQty, stepProductRequiredQtyUnit, updateStepProductReqQty,
				updateStepProductReqQtyUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}