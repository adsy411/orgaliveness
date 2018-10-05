package research;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Edit_Project_Action extends TestBase {

	// @Test(priority = 1)
	public void editProjectDescription() throws Exception {
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
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Edit_Project");
		String editProjectDesc = ExcelUtils.getCellData(1, 1) + uniqueNumber;

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Edit project description");
		try {
			getWebElement("EditProject_DescriptionIcon").click();
		} catch (Exception e) {
			getWebElement("EditProject_ProjectDescription").click();
			Thread.sleep(1000);
			getWebElement("EditProjectDescription_PencilIcon").click();
		}
		Thread.sleep(5000);
		WebElement EditProjectDescriptionFrame = getWebElement("EditProject_DescriptionFrame");
		driver.switchTo().frame(EditProjectDescriptionFrame);
		WebElement editProjectDescBody = driver.findElement(By.tagName("body"));
		editProjectDescBody.clear();
		editProjectDescBody.click();
		Thread.sleep(2000);
		editProjectDescBody.sendKeys(editProjectDesc);
		driver.switchTo().defaultContent();
		getWebElement("SaveDescription").click();
		Thread.sleep(3000);

		Reporter.log("Validate edited project description");
		String getEditedProjectDescription = getWebElement("EditProject_ProjectDescription").getText();
		Assert.assertEquals(getEditedProjectDescription, editProjectDesc, "Edit project description failed!");

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test(priority = 2)
	public void addProjectNotes() throws Exception {
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
		String addProjectNotes = "Note" + uniqueNumber;
		String addProjectNotesDesc = "Note description" + uniqueNumber;

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Add notes to project with title and description");
		ResearchFunc.addNote(addProjectNotes, addProjectNotesDesc);

		// UPDATING NOTE TITLE AND DESCRIPTION TO TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Edit_Project");
		ExcelUtils.setCellData(addProjectNotes, 1, 3,
				Research_Constants.Path_TestData + Research_Constants.File_TestData, "Edit_Project");
		ExcelUtils.setCellData(addProjectNotesDesc, 1, 4,
				Research_Constants.Path_TestData + Research_Constants.File_TestData, "Edit_Project");

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test(priority = 3)
	public void editProjectNotes() throws Exception {
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

		// PROJECT NOTE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Edit_Project");
		String noteTitle = ExcelUtils.getCellData(1, 3);
		String editNoteTitle = "Edited" + uniqueNumber;
		String editNoteDescription = "EditedDescription" + uniqueNumber;

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Edit project notes from project detail page");
		ResearchFunc.editNote(noteTitle, editNoteTitle, editNoteDescription);

		// UPDATING NOTE TITLE AND DESCRIPTION TO TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Edit_Project");
		ExcelUtils.setCellData(editNoteTitle, 1, 3, Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"Edit_Project");
		ExcelUtils.setCellData(editNoteDescription, 1, 4,
				Research_Constants.Path_TestData + Research_Constants.File_TestData, "Edit_Project");

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	// @Test(priority = 4)
	public void deleteProjectNotes() throws Exception {
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

		// PROJECT NOTE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Edit_Project");
		String noteTitle = ExcelUtils.getCellData(1, 3);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Delete project notes from project detail page");
		ResearchFunc.deleteNote(noteTitle);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test(priority = 5)
	public void addProjectAttachment() throws Exception {
		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		SoftAssert softAssert = new SoftAssert();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);

		Reporter.log("Add attachment to project");
		getWebElement("Upload_Attachment").click();
		Thread.sleep(2000);
		String currentDir = System.getProperty("user.dir");
		Runtime.getRuntime()
				.exec(currentDir + "//src//testData//Labdrive_Attachments//pdf_upload");
		Thread.sleep(8000);

		Reporter.log("Validation of added attachment");
		List<WebElement> allProjectAttachments = driver
				.findElements(By.xpath("//a[@title='View Attachment' and @class='text-muted file-length']//span"));
		boolean bAttachmentFound = false;
		for (int i = 0; i < allProjectAttachments.size(); i++) {
			bAttachmentFound = allProjectAttachments.equals("pdf_upload");
		}
		softAssert.assertTrue(bAttachmentFound, "Adding attachment to project failed");

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	/*@Test(priority = 6)
	public void exportProjectToPdf() throws Exception {
		
	}*/
	
	/*@Test
	public void downloadProjectAttachment() throws Exception {
		
	}
	
	@Test
	public void deleteProjectAttachment() throws Exception {
		
	}*/
}