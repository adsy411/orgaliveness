package research;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Add_Product_To_Template_Steps_Action extends TestBase {

	@Test
	public void addProductToTemplateSteps_WhichIsPresentInTemplate() throws Exception {
		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();

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
		String productName = ExcelUtils.getCellData(1, 0);
		String catalog = ExcelUtils.getCellData(1, 2);
		String vendor = ExcelUtils.getCellData(1, 1);
		String CAS = ExcelUtils.getCellData(1, 5);
		String requiredQty = ExcelUtils.getCellData(1, 6);
		String requiredQtyUnit = ExcelUtils.getCellData(1, 7);

		// PRODUCT STEP TEST DATA
		String stepTitle = "STEP" + uniqueNumber;
		String stepDescription = "STEP DESC" + uniqueNumber;

		// STEP PRODUCT DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"TemplateStepProducts");
		String stepRequiredQty = ExcelUtils.getCellData(1, 0);
		String stepRequiredQtyUnit = ExcelUtils.getCellData(1, 1);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);

		Reporter.log("Add new step to the protocol");
		ResearchFunc.addStep(stepTitle, stepDescription);
		
		// UPDATE STEP DETAILS TO TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"TemplateStepProducts");
		ExcelUtils.setCellData(stepTitle, 1, 2, Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"TemplateStepProducts");

		Reporter.log("Add product to protocol step which is added in protocol");
		ResearchFunc.addProductToProtocolSteps(stepTitle, productName, catalog, requiredQty, requiredQtyUnit, stepRequiredQty,
				stepRequiredQtyUnit, CAS, vendor);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		ResearchFunc.Logout();
	}
	
	@Test
	public void addProductToTemplateSteps_WhichIsPartiallyUsedInAnotherStep() throws Exception {
		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();

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
		String productName = ExcelUtils.getCellData(1, 0);
		String catalog = ExcelUtils.getCellData(1, 2);
		String vendor = ExcelUtils.getCellData(1, 1);
		String CAS = ExcelUtils.getCellData(1, 5);
		String requiredQty = ExcelUtils.getCellData(1, 6);
		String requiredQtyUnit = ExcelUtils.getCellData(1, 7);

		// PRODUCT STEP TEST DATA
		String stepTitle1 = "STEP1" + uniqueNumber;
		String stepDescription1 = "STEP DESC1" + uniqueNumber;
		String stepTitle2 = "STEP2" + uniqueNumber;
		String stepDescription2 = "STEP DESC2" + uniqueNumber;

		// STEP PRODUCT DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"TemplateStepProducts");
		String stepRequiredQty = ExcelUtils.getCellData(1, 0);
		String stepRequiredQtyUnit = ExcelUtils.getCellData(1, 1);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);

		Reporter.log("Add new step to the protocol");
		ResearchFunc.addStep(stepTitle1, stepDescription1);
		
		Reporter.log("Add product to first step which is added in protocol");
		ResearchFunc.addProductToProtocolSteps(stepTitle1, productName, catalog, requiredQty, requiredQtyUnit, stepRequiredQty,
				stepRequiredQtyUnit, CAS, vendor);
		
		Reporter.log("Add new step to the protocol");
		ResearchFunc.addStep(stepTitle2, stepDescription2);
		
		Reporter.log("Add product to second step which is added in protocol");
		ResearchFunc.addProductToProtocolSteps(stepTitle2, productName, catalog, requiredQty, requiredQtyUnit, stepRequiredQty,
				stepRequiredQtyUnit, CAS, vendor);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		ResearchFunc.Logout();
	}

}