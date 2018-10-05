package research;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class LoadTemplateInExperiment_Action extends TestBase {
	
	@Test
	public void loadTemplateInExperiment() throws Exception {
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
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolName = "OrgProtocol"+uniqueNumber;
		String protocolDescription = ExcelUtils.getCellData(1, 1);
		String visibility = ExcelUtils.getCellData(1, 2);
		
		//PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Create new protocol template with scope as Organisation");
		protocolName = ResearchFunc.createProtocolTemplate(protocolName, protocolDescription, visibility);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Open Load protocol template");
		getWebElement("Button_Create").click();
		Thread.sleep(1000);
		getWebElement("LoadProtocolTemplate").click();
		Thread.sleep(4000);
		
		Reporter.log("Verify protocol template in load template modal");
		getWebElement("LoadProtocolTemplate_Search").click();
		getWebElement("LoadProtocolTemplate_Search").sendKeys(protocolName);
		Thread.sleep(5000);
		
		Reporter.log("Load protocol template from load protocol template modal");
		int attempts = 0;
		Actions action = new Actions(driver);
		WebElement loadProtocol = driver.findElement(By.xpath("//html//div[@id='selectTemplate']//td[1]"));
		while (attempts < 2) {
			try {
				action.moveToElement(driver.findElement(By.xpath("//html//div[@id='selectTemplate']//td[1]"))).build().perform();
				action.doubleClick(loadProtocol).perform();
				break;
			}
			catch(Exception e) {
				getWebElement("LoadProtocolTemplate_Search").click();
				getWebElement("LoadProtocolTemplate_Search").clear();
				Thread.sleep(4000);
				getWebElement("LoadProtocolTemplate_Search").sendKeys(protocolName);
			}
			attempts = attempts + 1;
		}
		
		getWebElement("LoadProtocolTemplate_Save").click();
		Thread.sleep(6000);
		
		Reporter.log("Verify protocol template is loaded in project or not");
		boolean bExperiment = driver.findElement(By.xpath("//a[@title='"+protocolName+"']")).isDisplayed();
		Assert.assertEquals(bExperiment,true,"Load protocol template into project failed! ");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}	
}