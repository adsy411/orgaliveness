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

public class Delete_Protocol_Template_Action extends TestBase {

	@Test
	public void deleteProtocolTemplate() throws Exception {
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

		// PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"Protocol_Creation");
		String protocolName = "OrgProtocol" + uniqueNumber;
		String protocolDescription = ExcelUtils.getCellData(1, 1) + uniqueNumber;
		String visibility = ExcelUtils.getCellData(1, 2);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();

		Reporter.log("Create new protocol template with scope Organisation");
		protocolName = ResearchFunc.createProtocolTemplate(protocolName, protocolDescription, visibility);

		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);

		Reporter.log("Delete protocol from protocol detail page");
		getWebElement("ProtocolTemplateSettings").click();
		Thread.sleep(2000);
		getWebElement("DeleteTemplate_Link_1").click();
		Thread.sleep(2000);
		getWebElement("Link_Delete").click();
		Thread.sleep(3000);

		Reporter.log("Verify deleted protocol from protocol list page");
		driver.navigate().refresh();
		Thread.sleep(3000);
		getWebElement("List_Search_TextBox").click();
		Thread.sleep(2000);
		getWebElement("List_Search_TextBox").sendKeys(protocolName);
		Thread.sleep(3000);
		if (driver.findElements(By.xpath("//a[@title='" + protocolName + "']")).size() > 0) {
			Assert.assertTrue(false, "Protocol Template still exists in list page after deleting");
		}

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}