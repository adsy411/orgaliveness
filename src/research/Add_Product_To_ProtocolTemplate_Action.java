package research;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Add_Product_To_ProtocolTemplate_Action extends TestBase {

	@Test
	public void addSigmaAldrichProductToTemplate() throws Exception {
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
		String productName = "P" + uniqueNumber;
		String catalog = "CLG" + uniqueNumber;
		String vendor = ExcelUtils.getCellData(1, 1);
		String qtyAdded = ExcelUtils.getCellData(1, 3);
		String qtyAddedUnit = ExcelUtils.getCellData(1, 4);
		String CAS = "CAS" + uniqueNumber;
		String qtyRequired = ExcelUtils.getCellData(1, 6);
		String qtyRequiredUnit = ExcelUtils.getCellData(1, 7);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);

		Reporter.log("Add sigma aldrich product to template protocol detail page");
		ResearchFunc.addNewProductToProtocolTemplate(productName, catalog, vendor, qtyAdded, qtyAddedUnit, CAS,
				qtyRequired, qtyRequiredUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		ResearchFunc.Logout();
	}

	@Test
	public void addThirdPartyProductToTemplate() throws Exception {
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
		String productName = "P" + uniqueNumber;
		String catalog = "CLG" + uniqueNumber;
		String vendor = ExcelUtils.getCellData(2, 1);
		String qtyAdded = ExcelUtils.getCellData(2, 3);
		String qtyAddedUnit = ExcelUtils.getCellData(2, 4);
		String CAS = "CAS" + uniqueNumber;
		String qtyRequired = ExcelUtils.getCellData(2, 6);
		String qtyRequiredUnit = ExcelUtils.getCellData(2, 7);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);

		Reporter.log("Add third party product to template protocol detail page");
		ResearchFunc.addNewProductToProtocolTemplate(productName, catalog, vendor, qtyAdded, qtyAddedUnit, CAS,
				qtyRequired, qtyRequiredUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		ResearchFunc.Logout();
	}

	@Test
	public void addProductToTemplateFromSigmaDatabase() throws Exception {
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
		String addFromDataBase = ExcelUtils.getCellData(3, 8);
		String qtyAdded = ExcelUtils.getCellData(3, 3);
		String qtyAddedUnit = ExcelUtils.getCellData(3, 4);
		String qtyRequired = ExcelUtils.getCellData(3, 6);
		String qtyRequiredUnit = ExcelUtils.getCellData(3, 7);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);

		Reporter.log("Add product to template from sigma database in protocol detail page");
		ResearchFunc.addProductToTemplateFromSigmaDataBase(addFromDataBase, qtyAdded, qtyAddedUnit, qtyRequired,
				qtyRequiredUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		ResearchFunc.Logout();
	}

	@Test
	public void addProductToTemplateFromCatalog() throws Exception {
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
				"ResearchMaterialsAndProducts");
		String productName = ExcelUtils.getCellData(3, 0);
		String catalogNumber = ExcelUtils.getCellData(3, 1);
		String vendor = ExcelUtils.getCellData(3, 2);
		String CAS = ExcelUtils.getCellData(3, 5);
		String qtyAdded = ExcelUtils.getCellData(3, 3);
		String qtyAddedUnit = ExcelUtils.getCellData(3, 4);

		// PRODUCT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddTemplateProducts");
		String qtyRequired = ExcelUtils.getCellData(4, 6);
		String qtyRequiredUnit = ExcelUtils.getCellData(4, 7);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);

		Reporter.log("Add product to template from sigma database in protocol detail page");
		ResearchFunc.AddProductToTemplateFromLab(productName, catalogNumber, vendor, CAS, qtyAdded, qtyAddedUnit,
				qtyRequired, qtyRequiredUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		ResearchFunc.Logout();
	}

}