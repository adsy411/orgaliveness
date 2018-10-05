package research;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import inventory.InventoryRegularFunctions;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Add_Material_To_Experiment_Action extends TestBase {

	// @Test
	public void addSigmaAldrichMaterialToExperiment() throws Exception {

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
		String materialName = "M" + uniqueNumber;
		String catalogNumber = "C" + uniqueNumber;
		String expiryDate = ExcelUtils.getCellData(1, 2);
		String vendor = ExcelUtils.getCellData(1, 3);
		String CAS = "CAS" + uniqueNumber;
		String QtyAvailable = ExcelUtils.getCellData(1, 5);
		String QtyAvailableUnit = ExcelUtils.getCellData(1, 6);
		String LOT = "LOT" + uniqueNumber;
		String QtyRequired = ExcelUtils.getCellData(1, 8);
		String QtyRequiredUnit = ExcelUtils.getCellData(1, 9);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Add Sigma Aldrich material to Experiment");
		ResearchFunc.AddNewMaterialToExperiment(materialName, catalogNumber, expiryDate, vendor, CAS, QtyAvailable,
				QtyAvailableUnit, LOT, QtyRequired, QtyRequiredUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test
	public void addThirdPartyMaterialToExperiment() throws Exception {

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
		String materialName = "M" + uniqueNumber;
		String catalogNumber = "C" + uniqueNumber;
		String expiryDate = ExcelUtils.getCellData(2, 2);
		String vendor = ExcelUtils.getCellData(2, 3);
		String CAS = "CAS" + uniqueNumber;
		String QtyAvailable = ExcelUtils.getCellData(2, 5);
		String QtyAvailableUnit = ExcelUtils.getCellData(2, 6);
		String LOT = "LOT" + uniqueNumber;
		String QtyRequired = ExcelUtils.getCellData(2, 8);
		String QtyRequiredUnit = ExcelUtils.getCellData(2, 9);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Add Sigma Aldrich material to Experiment");
		ResearchFunc.AddNewMaterialToExperiment(materialName, catalogNumber, expiryDate, vendor, CAS, QtyAvailable,
				QtyAvailableUnit, LOT, QtyRequired, QtyRequiredUnit);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test
	public void addSigmaAldrichMaterialToExperimentWhichIsPresentInInventory() throws Exception {
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

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Add unique sigma aldrich material to Inventory which is not already present in Lab catalog");
		ResearchFunc.Research_AddMaterial(1);

		// EXPERIMENT MATERIALS DETAILS PULLED FROM TEST DATA AFTER ADDING MATERIAL TO
		// INVENTORY
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ResearchMaterialsAndProducts");
		String materialName = ExcelUtils.getCellData(1, 0);
		String catalogNumber = ExcelUtils.getCellData(1, 1);
		String vendor = ExcelUtils.getCellData(1, 2);
		String QtyAvailable = ExcelUtils.getCellData(1, 3);
		String QtyAvailableUnit = ExcelUtils.getCellData(1, 4);
		String CAS = ExcelUtils.getCellData(1, 5);
		String LOT = ExcelUtils.getCellData(1, 6);
		String expiryDate = null;

		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String QtyRequired = ExcelUtils.getCellData(4, 8);
		String QtyRequiredUnit = ExcelUtils.getCellData(4, 9);
		String availableInInventory = ExcelUtils.getCellData(4, 11);
		String availableInCatalog = ExcelUtils.getCellData(4, 12);
		String editExpiry = ExcelUtils.getCellData(4, 13);
		String editLOT = null;

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log(
				"Add Sigma Aldrich material to experiment which is not already present in Lab catalog but present in Inventory");
		ResearchFunc.AddMaterialToExperimentFromLab(availableInInventory, availableInCatalog, materialName,
				catalogNumber, vendor, expiryDate, CAS, QtyAvailable, QtyAvailableUnit, LOT, QtyRequired,
				QtyRequiredUnit, editExpiry, editLOT);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test
	public void addThirdPartyMaterialToExperimentWhichIsPresentInInventory() throws Exception {

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

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Add unique Third party material to Inventory which is not already present in Lab catalog");
		ResearchFunc.Research_AddMaterial(2);

		// EXPERIMENT MATERIALS DETAILS PULLED FROM TEST DATA AFTER ADDING MATERIAL TO
		// INVENTORY
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ResearchMaterialsAndProducts");
		String materialName = ExcelUtils.getCellData(2, 0);
		String catalogNumber = ExcelUtils.getCellData(2, 1);
		String vendor = ExcelUtils.getCellData(2, 2);
		String QtyAvailable = ExcelUtils.getCellData(2, 3);
		String QtyAvailableUnit = ExcelUtils.getCellData(2, 4);
		String CAS = ExcelUtils.getCellData(2, 5);
		String LOT = ExcelUtils.getCellData(2, 6);
		String expiryDate = null;

		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String QtyRequired = ExcelUtils.getCellData(5, 8);
		String QtyRequiredUnit = ExcelUtils.getCellData(5, 9);
		String availableInInventory = ExcelUtils.getCellData(5, 11);
		String availableInCatalog = ExcelUtils.getCellData(5, 12);
		String editExpiry = ExcelUtils.getCellData(5, 13);
		String editLOT = null;

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log(
				"Add Third party material to experiment which is not already present in Lab catalog but present in Inventory");
		ResearchFunc.AddMaterialToExperimentFromLab(availableInInventory, availableInCatalog, materialName,
				catalogNumber, vendor, expiryDate, CAS, QtyAvailable, QtyAvailableUnit, LOT, QtyRequired,
				QtyRequiredUnit, editExpiry, editLOT);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test
	public void addSigmaAldrichMaterialToExperimentWhichIsPresentInCatalogButNotInInventory() throws Exception {

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

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Add unique sigma aldrich product to Lab catalog which is not present in Inventory");
		ResearchFunc.Research_AddLabCatalogProduct(3);

		// EXPERIMENT MATERIALS DETAILS PULLED FROM TEST DATA AFTER ADDING MATERIAL TO
		// INVENTORY
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ResearchMaterialsAndProducts");
		String materialName = ExcelUtils.getCellData(3, 0);
		String catalogNumber = ExcelUtils.getCellData(3, 1);
		String vendor = ExcelUtils.getCellData(3, 2);
		String QtyAvailable = ExcelUtils.getCellData(3, 3);
		String QtyAvailableUnit = ExcelUtils.getCellData(3, 4);
		String CAS = ExcelUtils.getCellData(3, 5);
		String LOT = ExcelUtils.getCellData(3, 6);
		String expiryDate = null;

		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String QtyRequired = ExcelUtils.getCellData(6, 8);
		String QtyRequiredUnit = ExcelUtils.getCellData(6, 9);
		String availableInInventory = ExcelUtils.getCellData(6, 11);
		String availableInCatalog = ExcelUtils.getCellData(6, 12);
		String editExpiry = ExcelUtils.getCellData(6, 13);
		String editLOT = null;

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log(
				"Add unique sigma aldrich product to experiment which is present in Lab catalog but not in Inventory");
		ResearchFunc.AddMaterialToExperimentFromLab(availableInInventory, availableInCatalog, materialName,
				catalogNumber, vendor, expiryDate, CAS, QtyAvailable, QtyAvailableUnit, LOT, QtyRequired,
				QtyRequiredUnit, editExpiry, editLOT);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test
	public void addThirdPartyMaterialToExperimentWhichIsPresentInCatalogButNotInInventory() throws Exception {

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

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Add unique Third party product to Lab catalog which is not present in Inventory");
		ResearchFunc.Research_AddLabCatalogProduct(4);

		// EXPERIMENT MATERIALS DETAILS PULLED FROM TEST DATA AFTER ADDING MATERIAL TO
		// INVENTORY
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ResearchMaterialsAndProducts");
		String materialName = ExcelUtils.getCellData(4, 0);
		String catalogNumber = ExcelUtils.getCellData(4, 1);
		String vendor = ExcelUtils.getCellData(4, 2);
		String QtyAvailable = ExcelUtils.getCellData(4, 3);
		String QtyAvailableUnit = ExcelUtils.getCellData(4, 4);
		String CAS = ExcelUtils.getCellData(4, 5);
		String LOT = ExcelUtils.getCellData(4, 6);
		String expiryDate = null;

		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String QtyRequired = ExcelUtils.getCellData(7, 8);
		String QtyRequiredUnit = ExcelUtils.getCellData(7, 9);
		String availableInInventory = ExcelUtils.getCellData(7, 11);
		String availableInCatalog = ExcelUtils.getCellData(7, 12);
		String editExpiry = ExcelUtils.getCellData(7, 13);
		String editLOT = null;

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Add Third party product to experiment which is present in Lab catalog but not in Inventory");
		ResearchFunc.AddMaterialToExperimentFromLab(availableInInventory, availableInCatalog, materialName,
				catalogNumber, vendor, expiryDate, CAS, QtyAvailable, QtyAvailableUnit, LOT, QtyRequired,
				QtyRequiredUnit, editExpiry, editLOT);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test
	public void addMaterialToExperimentFromSigmaAldrichDataBase() throws Exception {
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

		// EXPERIMENT MATERIAL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"AddExperimentMaterials");
		String addFromDataBase = ExcelUtils.getCellData(3, 10);
		String expiryDate = ExcelUtils.getCellData(3, 13);
		String LOT = "LOT" + uniqueNumber;
		String QtyRequired = ExcelUtils.getCellData(3, 8);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Add Third party product to experiment which is present in Lab catalog but not in Inventory");
		ResearchFunc.AddMaterialToExperimentFromSigmaDataBase(addFromDataBase, expiryDate, LOT, QtyRequired);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	@Test
	public void consumeMaterialInInventoryWhichIsAddedToExperiment() throws Exception {
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
		String materialName = "M" + uniqueNumber;
		String catalogNumber = "C" + uniqueNumber;
		String expiryDate = ExcelUtils.getCellData(2, 2);
		String vendor = ExcelUtils.getCellData(2, 3);
		String CAS = "CAS" + uniqueNumber;
		String QtyAvailable = ExcelUtils.getCellData(2, 5);
		String QtyAvailableUnit = ExcelUtils.getCellData(2, 6);
		String LOT = "LOT" + uniqueNumber;
		String QtyRequired = ExcelUtils.getCellData(2, 8);
		String QtyRequiredUnit = ExcelUtils.getCellData(2, 9);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Add Sigma Aldrich material to Experiment");
		ResearchFunc.AddNewMaterialToExperiment(materialName, catalogNumber, expiryDate, vendor, CAS, QtyAvailable,
				QtyAvailableUnit, LOT, QtyRequired, QtyRequiredUnit);

		Reporter.log("Navigate to Material list");
		ResearchFunc.MaterialPageNavigation();

		Reporter.log("Delete material from inventory which is added to Experiment");
		InventoryRegularFunctions deleteMaterial = new InventoryRegularFunctions();
		deleteMaterial.Card_View_Delete_Material(materialName);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Verify Sigma Aldrich material added to Experiment after consuming from Inventory");
		boolean bVerifyMaterial = false;
		List<WebElement> inActiveMaterials = driver
				.findElements(By.xpath("//div[@class='panel panel-default experiment-material-box']//p"));
		for (int i = 0; i < inActiveMaterials.size(); i++) {
			String getNameAndReqQtyAndUnit = inActiveMaterials.get(i).getText().trim();
			if (getNameAndReqQtyAndUnit.contains(materialName) & getNameAndReqQtyAndUnit.contains(QtyRequired)
					& getNameAndReqQtyAndUnit.contains(QtyRequiredUnit)) {
				int getFlag = driver.findElements(By.xpath("(//div[@class='panel panel-default experiment-material-box'])["+(i+1)+"]//a[@class='text-danger' and @title='Material is not Present in Inventory.']")).size();
				if(getFlag == 1) {
					bVerifyMaterial = true;
					break;
				}
			}
		}
		Assert.assertTrue(bVerifyMaterial, "Material not changed to Inactive in material section");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}