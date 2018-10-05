package labdrive;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Labdrive_Constants;

public class CreateFolder_Action extends TestBase {
	
	@Test
	public void createFolderInResearchHomePage() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//Initialization of lab drive functions object
		LabDriveRegularFunctions LabDriveFunc = new LabDriveRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData, "Labdrive_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		Reporter.log("Login to application as Lab owner");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Lab drive dashboard page");
		LabDriveFunc.navigateToLabdriveDashboard();
		
		Reporter.log("Navigate to Research Lab drive home page");
		LabDriveFunc.navigateToLabdriveHomePage("Research");
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData, "Create_Folder");
		String folderName = ExcelUtils.getCellData(1, 0)+uniqueNumber;
		
		Reporter.log("Create new folder");
		LabDriveFunc.createFolder(folderName);
		
		Reporter.log("Logout from application");
		lib.Logout();
	}
	
	@Test
	public void createFolderInInventoryHomePage() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//Initialization of lab drive functions object
		LabDriveRegularFunctions LabDriveFunc = new LabDriveRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData, "Labdrive_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		Reporter.log("Login to application as Lab owner");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Lab drive dashboard page");
		LabDriveFunc.navigateToLabdriveDashboard();
		
		Reporter.log("Navigate to Inventory Lab drive home page");
		LabDriveFunc.navigateToLabdriveHomePage("Inventory");
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData, "Create_Folder");
		String folderName = ExcelUtils.getCellData(2, 0)+uniqueNumber;
		
		Reporter.log("Create new folder");
		LabDriveFunc.createFolder(folderName);
		
		Reporter.log("Logout from application");
		lib.Logout();
	}
	
	@Test
	public void createFolderInUnassociatedGroupHomePage() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//Initialization of lab drive functions object
		LabDriveRegularFunctions LabDriveFunc = new LabDriveRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData, "Labdrive_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		Reporter.log("Login to application as Lab owner");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Lab drive dashboard page");
		LabDriveFunc.navigateToLabdriveDashboard();
		
		Reporter.log("Navigate to Unassociated Group Lab drive home page");
		LabDriveFunc.navigateToLabdriveHomePage("Unassociated");
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData, "Create_Folder");
		String folderName = ExcelUtils.getCellData(1, 0)+uniqueNumber;
		
		Reporter.log("Create new folder");
		LabDriveFunc.createFolder(folderName);
		
		Reporter.log("Logout from application");
		lib.Logout();
	}
}