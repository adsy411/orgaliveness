package research;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Delete_Project_Action  extends TestBase {
	
	@Test
	public void deleteProjectFromProjectDetailPage_WithExperiment() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		//PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = "Project"+uniqueNumber;
		String projectVisibility = ExcelUtils.getCellData(1, 2);
		
		//EXPERIMENT TEST DATA 
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "labExp"+uniqueNumber;
		String experimentDescription = ExcelUtils.getCellData(1, 1)+uniqueNumber;
		String visibility = ExcelUtils.getCellData(1, 2);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Create new project");
		ResearchFunc.createProject(projectName, "", "Lab");
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Add new experiment with scope org");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);
		
		Reporter.log("Delete project from project detail page with experiment present");
		getWebElement("ProjectSettings").click();
		Thread.sleep(2000);
		getWebElement("DeleteProject").click();
		Thread.sleep(3000);
		
		Reporter.log("Validate the warning message \"Project can not be deleted if it has Experiments\"");
		String getWarningMsg = getWebElement("DeleteProjectWarning").getText().trim();
		Assert.assertEquals(getWarningMsg, "Warning! Project can not be deleted if it has Experiments", "Warning message did not appear");
		getWebElement("CloseDeleteProjectWarningModal").click();
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test
	public void deleteProjectFromProjectDetailPage_WithOutExperiment() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		//PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = "Project"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Create new project");
		ResearchFunc.createProject(projectName, "", "Restricted");
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Delete project from project detail page with experiment present");
		try {
			getWebElement("ProjectSettings").click();
			Thread.sleep(2000);
			getWebElement("DeleteProject").click();
			Thread.sleep(2000);
			getWebElement("Link_Delete").click();
			Thread.sleep(4000);
		} catch (Exception e) {
			driver.navigate().refresh();
			Thread.sleep(4000);
			getWebElement("ProjectSettings").click();
			Thread.sleep(2000);
			getWebElement("DeleteProject").click();
			Thread.sleep(2000);
			getWebElement("Link_Delete").click();
			Thread.sleep(4000);
		}
		
		Reporter.log("Verify project in project list page");
		driver.navigate().refresh();
		Thread.sleep(3000);
		getWebElement("List_Search_TextBox").click();
		Thread.sleep(2000);
		getWebElement("List_Search_TextBox").sendKeys(projectName);
		Thread.sleep(3000);
		if(driver.findElements(By.xpath("//a[@title='" + projectName + "']")).size() > 0) {
			Assert.assertFalse(true, "Project still existis in project list after deleting");
		}
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

}