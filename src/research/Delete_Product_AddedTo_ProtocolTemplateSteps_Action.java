package research;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Delete_Product_AddedTo_ProtocolTemplateSteps_Action extends TestBase {

	@Test
	public void deleteProductAddedToTemplateSteps() throws Exception {
		
		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"Protocol_Creation");
		String protocolName = ExcelUtils.getCellData(1, 0);

		// PRODUCT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddTemplateProducts");
		String stepProductName = ExcelUtils.getCellData(1, 0);
		String stepProductCatalog = ExcelUtils.getCellData(1, 2);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"TemplateStepProducts");
		String stepTitle = ExcelUtils.getCellData(1, 2);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Delete product from protocol template");
		ResearchFunc.deleteProductFromProtocolTemplateSteps(stepTitle, stepProductName,
				stepProductCatalog);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}