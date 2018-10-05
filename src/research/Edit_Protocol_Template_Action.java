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

public class Edit_Protocol_Template_Action extends TestBase {
	
	@Test(priority = 1)
	public void editProtocolTemplateDescription() throws Exception {
		
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
		String protocolName = ExcelUtils.getCellData(1, 0);
		
		//EDIT PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Protocol");
		String editProtocolDescription = "Editing of protocol template description_"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Edit protocol template description");
		try {
			getWebElement("EditProtocolTemplate_DescriptionIcon").click();
			Thread.sleep(7000);
		} catch (Exception e) {
			getWebElement("EditProtocolTemplate_ProtocolDescription").click();
			Thread.sleep(1000);
			getWebElement("EditProtocolDescription_PencilIcon").click();
			Thread.sleep(7000);
		}
		WebElement EditProtocolDescriptionFrame = getWebElement("EditProtocolTemplate_DescriptionFrame");
		driver.switchTo().frame(EditProtocolDescriptionFrame);
		WebElement editProtocolDescBody = driver.findElement(By.tagName("body"));
		editProtocolDescBody.click();
		Thread.sleep(2000);
		editProtocolDescBody.clear();
		editProtocolDescBody.sendKeys(editProtocolDescription);
		driver.switchTo().defaultContent();
		getWebElement("SaveDescription").click();
		Thread.sleep(6000);

		Reporter.log("Validate edited protocol template description");
		String getEditedProtocolDescription = getWebElement("EditProtocolTemplate_ProtocolDescription").getText();
		Assert.assertTrue(getEditedProtocolDescription.equals(editProtocolDescription), "Edit protocol template description failed!");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 2)
	public void addProtocolTemplateStep() throws Exception {
		
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
		String protocolName = ExcelUtils.getCellData(1, 0);
		
		//EDIT PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Protocol");
		String addStepName = "NEW STEP_"+uniqueNumber;
		String addStepDescription = "NEW STEP DESCRIPTION_"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Add protocol template step");
		ResearchFunc.addStep(addStepName, addStepDescription);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 3)
	public void editProtocolTemplateStep() throws Exception {
		
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
		String protocolName = ExcelUtils.getCellData(1, 0);
		
		//EDIT PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Protocol");
		String addStepName = "New Step_"+uniqueNumber;
		String addStepDescription = "New Step_Description"+uniqueNumber;
		String editStepTitle = "Edit Step_"+uniqueNumber;
		String editStepDescription = "Edit Step_Description"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Add protocol template step");
		ResearchFunc.addStep(addStepName, addStepDescription);
		
		Reporter.log("Edit protocol template step");
		ResearchFunc.editStep(addStepName, editStepTitle, editStepDescription);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 4)
	public void deleteProtocolTemplateStep() throws Exception {
		
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
		String protocolName = ExcelUtils.getCellData(1, 0);
		
		//EDIT PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Protocol");
		String addStepName = "New Step_"+uniqueNumber;
		String addStepDescription = "New Step_Description"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Add protocol template step");
		ResearchFunc.addStep(addStepName, addStepDescription);
		
		Reporter.log("Delete protocol template step");
		ResearchFunc.deleteStep(addStepName);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 5)
	public void addProtocolTemplateNote() throws Exception {
		
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
		String protocolName = ExcelUtils.getCellData(1, 0);
		
		//EDIT PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Protocol");
		String noteTitle = "New Note "+uniqueNumber;
		String noteDescription = "New Note_Description"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Add protocol template note");
		ResearchFunc.addNote(noteTitle, noteDescription);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 6)
	public void editProtocolTemplateNote() throws Exception {
		
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
		String protocolName = ExcelUtils.getCellData(1, 0);
		
		//EDIT PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Protocol");
		String noteTitle = "New Note "+uniqueNumber;
		String noteDescription = "New Note_Description"+uniqueNumber;
		String editNoteTitle = "Edit Note "+uniqueNumber;
		String editNoteDescription = "Edit Note description"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Add protocol template note");
		ResearchFunc.addNote(noteTitle, noteDescription);
		
		Reporter.log("Edit protocol template note");
		ResearchFunc.editNote(noteTitle, editNoteTitle, editNoteDescription);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 7)
	public void deleteProtocolTemplateNote() throws Exception {
		
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
		String protocolName = ExcelUtils.getCellData(1, 0);
		
		//EDIT PROTOCOL TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Protocol");
		String noteTitle = "New Note "+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Add protocol template note");
		ResearchFunc.addNote(noteTitle, "");
		
		Reporter.log("Delete protocol template note");
		ResearchFunc.deleteNote(noteTitle);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	@Test(priority = 8)
	public void addProtocolTemplateAttachment() throws Exception {
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		SoftAssert softAssert = new SoftAssert();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolName = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);

		Reporter.log("Add attachment to protocol template");
		getWebElement("Upload_Attachment").click();
		Thread.sleep(2000);
		String currentDir = System.getProperty("user.dir");
		Runtime.getRuntime()
				.exec(currentDir + "//src//testData//Labdrive_Attachments//pdf_upload");
		Thread.sleep(8000);

		Reporter.log("Validation of added attachment");
		List<WebElement> allProjectAttachments = driver
				.findElements(By.xpath("//a[@title='View Attachment']//span"));
		boolean bAttachmentFound = false;
		for (int i = 0; i < allProjectAttachments.size(); i++) {
			String allProjectAttachmentsText = allProjectAttachments.get(i).getText().trim();
			bAttachmentFound = allProjectAttachmentsText.equals("pdf_upload");
		}
		softAssert.assertTrue(bAttachmentFound, "Adding attachment to protocol template failed");

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	/*
	@Test(priority = 9)
	public void downloadProtocolTemplateAttachment() throws Exception {
		
	}
	
	@Test(priority = 10)
	public void deleteProtocolTemplateAttachment() throws Exception {
		
	}*/

}