package labdrive;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Labdrive_Constants;

public class UploadFile_Action extends TestBase {
	
	@Test
	public void UploadFileInResearchHomePage() throws Exception {
		
		//Initialization of lab drive functions object
		LabDriveRegularFunctions LabDriveFunc = new LabDriveRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData,"Labdrive_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData,"UploadFiles");
		String ExecfileName = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application as Lab owner");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Lab drive dashboard page");
		LabDriveFunc.navigateToLabdriveDashboard();
		
		Reporter.log("Navigate to Research Lab drive home page");
		LabDriveFunc.navigateToLabdriveHomePage("Research");
		
		//Initial pagination count
		int initialCount = LabDriveFunc.paginationCount();
		
		Reporter.log("Upload file in Research Lab drive home page");
		driver.findElement(By.id("attachmentForm:addFileModal:partFile")).click();
		Thread.sleep(2000);
		
		/*String currentDirPath = System.getProperty("user.dir");
		String ExecfilePath = currentDirPath+"\\src\\testData\\Labdrive_Attachments\\"+ExecfileName;
		LabDriveFunc.uploadFile(ExecfilePath);
		*/
		Runtime.getRuntime().exec("C:\\Users\\vshetty\\git\\brightlab-qa-scripts\\src\\testData\\Labdrive_Attachments\\pdf_upload");
		Thread.sleep(8000);
		
		//Final pagination count
		int finalCount = LabDriveFunc.paginationCount();
		int countIncrease = finalCount-initialCount ;
		Reporter.log("Validated uploaded file in Research Lab drive home page");
		Assert.assertTrue(countIncrease==1, "Lab drive upload failed!");
	}
	
	@Test
	public void UploadFileInInventoryHomePage() throws Exception {
		
		//Initialization of lab drive functions object
		LabDriveRegularFunctions LabDriveFunc = new LabDriveRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData,"Labdrive_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData,"UploadFiles");
		String ExecfileName = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application as Lab owner");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Lab drive dashboard page");
		LabDriveFunc.navigateToLabdriveDashboard();
		
		Reporter.log("Navigate to Inventory Lab drive home page");
		LabDriveFunc.navigateToLabdriveHomePage("Research");
		
		//Initial pagination count
		int initialCount = LabDriveFunc.paginationCount();
		
		Reporter.log("Upload file in Inventory Lab drive home page");
		driver.findElement(By.id("attachmentForm:addFileModal:partFile")).click();
		Thread.sleep(2000);
		
		/*String currentDirPath = System.getProperty("user.dir");
		String ExecfilePath = currentDirPath+"\\src\\testData\\Labdrive_Attachments\\"+ExecfileName;
		LabDriveFunc.uploadFile(ExecfilePath);
		*/
		Runtime.getRuntime().exec("C:\\Users\\vshetty\\git\\brightlab-qa-scripts\\src\\testData\\Labdrive_Attachments\\pdf_upload");
		Thread.sleep(8000);
		
		//Final pagination count
		int finalCount = LabDriveFunc.paginationCount();
		int countIncrease = finalCount-initialCount ;
		Reporter.log("Validated uploaded file in Inventory Lab drive home page");
		Assert.assertTrue(countIncrease==1, "Lab drive upload failed!");
	}
	
	@Test
	public void UploadFileInUnassociatedHomePage() throws Exception {
		
		//Initialization of lab drive functions object
		LabDriveRegularFunctions LabDriveFunc = new LabDriveRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData,"Labdrive_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Labdrive_Constants.File_TestData,"UploadFiles");
		String ExecfileName = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application as Lab owner");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Lab drive dashboard page");
		LabDriveFunc.navigateToLabdriveDashboard();
		
		Reporter.log("Navigate to Unassociated Lab drive home page");
		LabDriveFunc.navigateToLabdriveHomePage("Research");
		
		//Initial pagination count
		int initialCount = LabDriveFunc.paginationCount();
		
		Reporter.log("Upload file in Unassociated Lab drive home page");
		driver.findElement(By.id("attachmentForm:addFileModal:partFile")).click();
		Thread.sleep(2000);
		
		/*String currentDirPath = System.getProperty("user.dir");
		String ExecfilePath = currentDirPath+"\\src\\testData\\Labdrive_Attachments\\"+ExecfileName;
		LabDriveFunc.uploadFile(ExecfilePath);
		*/
		Runtime.getRuntime().exec("C:\\Users\\vshetty\\git\\brightlab-qa-scripts\\src\\testData\\Labdrive_Attachments\\pdf_upload");
		Thread.sleep(8000);
		
		//Final pagination count
		int finalCount = LabDriveFunc.paginationCount();
		int countIncrease = finalCount-initialCount ;
		Reporter.log("Validated uploaded file in Unassociated Lab drive home page");
		Assert.assertTrue(countIncrease==1, "Lab drive upload failed!");
	}
}