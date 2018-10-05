package research;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class ProjectListPageFunctionalities_Action extends TestBase {

	//@Test
	public void switchProjectListView() throws Exception {

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list page");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Verify project list default view");
		boolean bDefaultCardView = getWebElement("CardView").isDisplayed();
		Assert.assertTrue("Default card view verification failed", bDefaultCardView);

		Reporter.log("Switch to List view");
		getWebElement("SwitchToListView").click();
		Thread.sleep(1000);

		Reporter.log("Validate project list view after switching to list view");
		boolean bProjectListView = getWebElement("ListView").isDisplayed();
		Assert.assertFalse("Project list not switched to list view", bProjectListView);

		Reporter.log("Switch to Card view");
		getWebElement("SwitchToCardView").click();
		Thread.sleep(1000);

		Reporter.log("Validate project list view after switching to card view");
		boolean bProjectCardView = getWebElement("CardView").isDisplayed();
		Assert.assertTrue("Project list not switch to Card view", bProjectCardView);

		Reporter.log("Logout from application");
		login.Logout();
	}

	@Test
	public void projectListFilters() throws Exception {

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// Filter criteria date
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ProjectListPageFunctionality");
		String filterBy1 = ExcelUtils.getCellData(1, 0);
		String filterBy2 = ExcelUtils.getCellData(2, 0);
		String filterBy3 = ExcelUtils.getCellData(3, 0);
		String projectCreator = ExcelUtils.getCellData(1, 1);

		init();
		Reporter.log("Login to application");
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list page");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Filter project list by project creator");
		ResearchFunc.filterProjectList(filterBy1, projectCreator);

		Reporter.log("Filter project list by date range");
		ResearchFunc.filterProjectList(filterBy2, "");

		Reporter.log("Filter project list by project status");
		ResearchFunc.filterProjectList(filterBy3, "");

		Reporter.log("Logout from application");
		login.Logout();
	}

	//@Test
	public void downloadProjectList() throws Exception {

		// DECLARATION
		String projectItems[];
		int pCount = 0;

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		Reporter.log("Get download path of the system");
		final String homedirectory = System.getProperty("user.home");
		final String downloadPath = homedirectory + "\\Downloads";
		File projectListDownloadfile = new File(downloadPath + "\\Project_list.xlsx");
		
		Reporter.log("Delete Old file from the system");
		if (projectListDownloadfile.exists()) {
			projectListDownloadfile.delete();
		}
		
		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list page");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Download project list");
		getWebElement("Download_Link").click();
		for (int k=0;k<300;k++) {
			if (projectListDownloadfile.exists()) {
				break;
			}
		}

		// REFRESH PAGE
		driver.navigate().refresh();

		Reporter.log("Verify downloaded project list");
		int paginationCount = ResearchFunc.paginationCount();
		projectItems = new String[paginationCount];
		if (paginationCount > 10) {
			int noOfClicks = (paginationCount / 10);
			for (int m = 0; m <= noOfClicks; m++) {
				// NEED TO HANDLE FOR STALE ELEMENT REFERENCE IF OCCURS
				List<WebElement> project = driver.findElements(By.xpath(
						"//a[starts-with(@id, 'cardProjectForm:projectCardRepeat:') and @data-toggle='tooltip']"));
				for (int i = 0; i < project.size(); i++) {
					projectItems[pCount] = project.get(i).getText().trim();
					pCount = pCount + 1;
				}

				if (m != noOfClicks) {
					// CLICK ON NEXT PAGE
					driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-play text-primary']")).click();
					Thread.sleep(2000);
				}
			}
		} else {
			List<WebElement> project = driver.findElements(
					By.xpath("//a[starts-with(@id, 'cardProjectForm:projectCardRepeat:') and @data-toggle='tooltip']"));
			for (int j = 0; j < project.size(); j++) {
				projectItems[j] = project.get(j).getText().trim();
			}
		}
		
		// SORT ARRAY FROM LAST TO FIRST
		String projectItemsT[];
		projectItemsT = new String[paginationCount];
		for (int n = 0; n <= (projectItems.length - 1); n++) {
			projectItemsT[n] = projectItems[(projectItems.length - 1) - n];
		}

		// GETTING EXCEL DATA
		ExcelUtils.setExcelFile(downloadPath + "\\Project_list.xlsx", "Project_list");
		String[] projectItem_xl;
		projectItem_xl = new String[paginationCount];
		for (int l = 1; l <= paginationCount; l++) {
			projectItem_xl[l - 1] = ExcelUtils.getCellData(l, 0);
		}

		Reporter.log("Verify projects with downloaded project list excel file");
		@SuppressWarnings("unused")
		boolean projectContains = false;
		for (int p = 0; p < paginationCount; p++) {
			projectContains = Arrays.asList(projectItem_xl).contains(projectItemsT[p]);
			if (projectContains = false) {
				Assert.assertTrue("Project data mismatch at position " + p + " expected: " + projectItemsT[p], false);
				break;
			}
		}

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	//@Test
	public void search_project() throws Exception {
		
		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		SoftAssert s_assert = new SoftAssert();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		//PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list page");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Search project name and verify project");
		boolean verifyProject = ResearchFunc.navigateToProjectDetailPage(projectName);
		s_assert.assertTrue(verifyProject, "Search project in project list failed");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}