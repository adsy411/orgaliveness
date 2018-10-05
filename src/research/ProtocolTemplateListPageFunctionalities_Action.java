package research;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class ProtocolTemplateListPageFunctionalities_Action extends TestBase {

	@Test
	public void switchProtocolTemplateListView() throws Exception {

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

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Verify protocol template list default view");
		boolean bDefaultCardView = getWebElement("CardView").isDisplayed();
		Assert.assertTrue("Default card view verification failed", bDefaultCardView);

		Reporter.log("Switch to List view");
		getWebElement("SwitchToListView").click();
		Thread.sleep(1000);

		Reporter.log("Validate protocol template list view after switching to list view");
		boolean bProtocolTemplateListView = getWebElement("ListView").isDisplayed();
		Assert.assertFalse("Protocol template list not switched to list view", bProtocolTemplateListView);

		Reporter.log("Switch to Card view");
		getWebElement("SwitchToCardView").click();
		Thread.sleep(1000);

		Reporter.log("Validate protocol template list view after switching to card view");
		boolean bProtocolTemplateCardView = getWebElement("CardView").isDisplayed();
		Assert.assertTrue("Protocol template list not switch to Card view", bProtocolTemplateCardView);

		Reporter.log("Logout from application");
		login.Logout();
	}

	@Test
	public void protocolTemplateListFilters() throws Exception {

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ProtocolTemListPgFunctionality");
		String filterBy1 = ExcelUtils.getCellData(1, 0);
		String filterBy2 = ExcelUtils.getCellData(2, 0);
		String filterBy3 = ExcelUtils.getCellData(3, 0);
		String protocolCreator = ExcelUtils.getCellData(1, 1);
		String labName = ExcelUtils.getCellData(3, 2);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Filter protocol template list by creator");
		ResearchFunc.filterProtocolTemplateList(filterBy1, protocolCreator, labName);

		Reporter.log("Filter protocol template list by date range");
		ResearchFunc.filterProtocolTemplateList(filterBy2, protocolCreator, labName);

		Reporter.log("Filter protocol template list by lab name");
		ResearchFunc.filterProtocolTemplateList(filterBy3, protocolCreator, labName);

		Reporter.log("Logout from application");
		login.Logout();
	}

	@Test
	public void downloadProtocolTemplateList() throws Exception {
		// DECLARATION
		String templateItems[];
		int pCount = 0;

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

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Get download path of the system");
		final String homedirectory = System.getProperty("user.home");
		final String downloadPath = homedirectory + "\\Downloads";
		File TemplateListDownloadfile = new File(downloadPath + "\\Template_list.xlsx");

		Reporter.log("Delete Old file from the system");
		if (TemplateListDownloadfile.exists()) {
			TemplateListDownloadfile.delete();
		}

		Reporter.log("Download protocol template list");
		getWebElement("ExportToExcel_Link").click();
		for (int k = 0; k < 300; k++) {
			if (TemplateListDownloadfile.exists()) {
				break;
			}
		}

		// REFRESH PAGE
		driver.navigate().refresh();

		Reporter.log("Verify downloaded Template list");
		int paginationCount = ResearchFunc.paginationCount();
		templateItems = new String[paginationCount];
		if (paginationCount > 10) {
			int noOfClicks = (paginationCount / 10);
			for (int m = 1; m < noOfClicks; m++) {
				// NEED TO HANDLE FOR STALE ELEMENT REFERENCE IF OCCURS
				List<WebElement> protocol = driver.findElements(By.xpath(
						"//a[starts-with(@id, 'cardTemplateForm:templateCardRepeat:') and @class='text-primary']"));
				for (int i = 0; i < protocol.size(); i++) {
					templateItems[pCount] = protocol.get(i).getText().trim();
					pCount = pCount + 1;
				}

				if (m != noOfClicks) {
					// CLICK ON NEXT PAGE
					driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-play text-primary']")).click();
					Thread.sleep(2000);
				}
			}
		} else {
			List<WebElement> template = driver.findElements(By
					.xpath("//a[starts-with(@id, 'cardTemplateForm:templateCardRepeat:') and @class='text-primary']"));
			for (int j = 0; j < template.size(); j++) {
				templateItems[j] = template.get(j).getText().trim();
			}
		}

		// SORT ARRAY FROM LAST TO FIRST
		String templateItemsT[];
		templateItemsT = new String[paginationCount];
		for (int n = 0; n <= (templateItems.length - 1); n++) {
			templateItemsT[n] = templateItems[(templateItems.length - 1) - n];
		}

		// GETTING EXCEL DATA
		ExcelUtils.setExcelFile(downloadPath + "\\Template_list.xlsx", "Template_list");
		String[] templateItem_xl;
		templateItem_xl = new String[paginationCount];
		for (int l = 1; l <= paginationCount; l++) {
			templateItem_xl[l - 1] = ExcelUtils.getCellData(l, 0);
		}

		Reporter.log("Verify templates with downloaded template list excel file");
		@SuppressWarnings("unused")
		boolean templateContains = false;
		for (int p = 0; p < paginationCount; p++) {
			templateContains = Arrays.asList(templateItem_xl).contains(templateItemsT[p]);
			if (templateContains = false) {
				Assert.assertTrue("Template data mismatch at position " + p + " expected: " + templateItemsT[p], false);
				break;
			}
		}

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	@Test
	public void search_protocol() throws Exception {

		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		SoftAssert s_assert = new SoftAssert();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"Protocol_Creation");
		String protocolName = ExcelUtils.getCellData(1, 0);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Search project template name and verify template");
		boolean verifyTemplate = ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		s_assert.assertTrue(verifyTemplate, "Search protocol template in template list failed");

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}