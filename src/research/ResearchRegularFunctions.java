package research;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import inventory.InventoryRegularFunctions;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;
import utills.Utills;

public class ResearchRegularFunctions extends TestBase {
	Library TodayDate = new Library();

	// PROJECT LIST NAVIGATION
	public boolean navigateToProjectList() throws Exception {
		// HOVER ON RESEARCH ICON IN THE SIDER BAR AND NAVIGATE TO PROJECT LIST
		WebElement ResearchSideBar = getWebElement("SideBar_ResearchHub_Group");
		ResearchSideBar.click();
		explicitWaitForElementUntilClickable("SubMenu_Projects");
		getWebElement("SubMenu_Projects").click();
		explicitWaitForElementUntilClickable("BreadCrumb_Heading");

		// VERIFY IF APPLICATION NAVIGATED TO PROJECT LIST PAGE OR NOT
		String getPageTitle = getWebElement("BreadCrumb_Heading").getText().trim();
		boolean bProjectListNav = getPageTitle.equals("Projects");
		Assert.assertTrue("Application not navigated to project list page", bProjectListNav);
		return bProjectListNav;
	}

	// PROTOCOL TEMPLATE LIST NAVIGATION
	public boolean navigateToProtocolTemplateList() throws Exception {
		// RELOAD PAGE
		refreshPage();

		// HOVER MOUSE ON RESEARCH SIDEBAR AND SELECT PROJECTS
		getWebElement("Research.mouseover").click();
		explicitWaitForElementUntilClickable("SubGroup_ProtocolList");
		getWebElement("SubGroup_ProtocolList").click();
		explicitWaitForElementUntilClickable("BreadCrumb_Heading");

		// VERIFY IF APPLICATION NAVIGATED TO PROJECT LIST PAGE OR NOT
		String getPageTitle = getWebElement("BreadCrumb_Heading").getText().trim();
		boolean bProtocolListNav = getPageTitle.equals("Protocols");
		Assert.assertTrue("Application not navigated to protocol template list page", bProtocolListNav);
		return bProtocolListNav;
	}

	// PAGINATION COUNT
	public int paginationCount() throws Exception {
		String getPaginationCount = getWebElement("Research_Pagination").getText();
		String[] count_1 = getPaginationCount.split("Results found");
		int count = Integer.parseInt(count_1[0].trim());
		return count;
	}

	// CREATE PROTOCOL TEMPLATE
	public String createProtocolTemplate(String protocolName, String protocolDescription, String visibility)
			throws Exception {

		// INITIAL PAGINATION COUNT
		int initialCount = paginationCount();

		// ENTER PROTOCOL TITLE
		getWebElement("Protocol.CreateNewButton").click();
		explicitWaitForElementUntilClickable("Protocol.Title");
		getWebElement("Protocol.Title").sendKeys(protocolName);

		// ADDING PROTOCOL DESCRIPTION
		if (protocolDescription == "" || (protocolDescription == null)) {
			// SKIP ADDING DESCRIPTION
		} else {
			WebElement protocolDescriptionFrame = getWebElement("Protocol.Description");
			driver.switchTo().frame(protocolDescriptionFrame);
			WebElement protocolDescriptionBody = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(protocolDescriptionBody));
			protocolDescriptionBody.click();
			protocolDescriptionBody.sendKeys(protocolDescription);
			driver.switchTo().defaultContent();
		}

		// SELECT VISIBILITY
		switch (visibility) {
		case "Organisation":
			getWebElement("ProtocolVisbilityOrganisation").click();
			break;

		case "Private":
			getWebElement("ProtocolVisbilityPrivate").click();
			break;

		case "LabOnly":
			getWebElement("ProtocolVisbilityLabOnly").click();
			break;

		default:
			System.out.println("Wrong protocol visibility");
		}

		// SAVE PROTOCOL TEMPLATE
		getWebElement("Protocol.Create").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// FINAL PAGINATION COUNT
		int finalCount = paginationCount();

		// VERIFY PROTOCOL TEMPLATE COUNT IN PROTOCOL TEMPLATE LIST PAGE
		Assert.assertEquals("Protocol creation with scope as " + visibility + " failed", finalCount,
				(initialCount + 1));

		Reporter.log("Verify protocol in protocol list");
		verifyProtocolInProtocolList(protocolName);

		// UPDATE PROTOCOL TEMPLATE TO TEST DATA BASED ON VISIBILITY
		switch (visibility) {
		case "Organisation":
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"Protocol_Creation");
			ExcelUtils.setCellData(protocolName, 1, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "Protocol_Creation");
			break;

		case "Private":
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"Protocol_Creation");
			ExcelUtils.setCellData(protocolName, 2, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "Protocol_Creation");
			break;

		case "LabOnly":
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"Protocol_Creation");
			ExcelUtils.setCellData(protocolName, 3, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "Protocol_Creation");
			break;

		default:
			System.out.println("Wrong protocol visibility");
		}

		return protocolName;
	}

	// Verify protocol template in protocol template list
	public void verifyProtocolInProtocolList(String protocolTemplateName) throws Exception {
		boolean bProtocol = false;
		String getProtocolName;
		int attempts = 0;
		while (attempts < 3) {
			try {
				// SEARCH PROTOCOL TEMPLATE
				explicitWaitUntilElementIsInvisible("List_Search_TextBox");
				getWebElement("List_Search_TextBox").clear();
				getWebElement("List_Search_TextBox").click();
				getWebElement("List_Search_TextBox").sendKeys(protocolTemplateName);
				Thread.sleep(4000);

				List<WebElement> protocolNameLinks = driver.findElements(By.xpath(
						"//a[starts-with(@id,'cardTemplateForm:templateCardRepeat:') and @class='text-primary']"));
				for (int i = 0; i < protocolNameLinks.size(); i++) {
					getProtocolName = protocolNameLinks.get(i).getAttribute("title").trim();
					if (getProtocolName.equals(protocolTemplateName)) {
						bProtocol = true;
						break;
					}
				}
			} catch (Exception E) {
				// REFRESH
				refreshPage();
			}
			attempts = attempts + 1;
		}
		Assert.assertTrue("Protocol template not found in list", bProtocol);
	}

	// Verify protocol template in protocol template list
	public boolean navigateToProtocolTemplateDetailPage(String protocolTemplateName) throws Exception {
		// REFRESH
		driver.navigate().refresh();

		// CLICK ON PROTOCOL TEMPLATE NAME LINK
		WebElement protocolNameLink = driver.findElement(By.xpath("//a[@title='" + protocolTemplateName + "']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		expliciteWait(By.xpath("//a[@title='" + protocolTemplateName + "']"), 5);
		wait.until(ExpectedConditions.elementToBeClickable(protocolNameLink));
		protocolNameLink.click();

		// VERIFY IF APPLICATION NAVIGATED TO PROTOCOL TEMPLATE DETAIL PAGE OR NOT
		explicitWaitForElementUntilClickable("BreadCrumb_Heading");
		String getPageTitle = getWebElement("BreadCrumb_Heading").getText().trim();
		boolean bProtocolDetailPage = getPageTitle.equals(protocolTemplateName);
		Assert.assertTrue("Application not navigated to protocol template detail page", bProtocolDetailPage);
		return bProtocolDetailPage;
	}

	// Logout
	public void Logout() throws Exception {
		getWebElement("LogoutRightButton").click();
		explicitWaitForElementUntilClickable("Enotebook.logout");
		getWebElement("Enotebook.logout").click();
		driver.quit();
	}

	// Verify material from material list
	public void verifyMaterialInMaterialListPage(String materialName) throws Exception {
		boolean bMaterial = false;
		int attempts = 0;
		while (attempts < 3) {
			try {
				// SEARCH MATERIAL
				explicitWaitForElementUntilClickable("List_Search_TextBox");
				getWebElement("List_Search_TextBox").clear();
				getWebElement("List_Search_TextBox").click();
				getWebElement("List_Search_TextBox").sendKeys(materialName);
				Thread.sleep(3000);

				int allLabCatalogProductNames = driver
						.findElements(By.xpath("(//span[@title='" + materialName + "'])[1]")).size();
				if (allLabCatalogProductNames > 0) {
					bMaterial = true;
					break;
				}
			} catch (Exception e) {
				// CATCH EXCEPTION
			}
		}
		Assert.assertTrue("Material not found in material list", bMaterial);
	}

	// Create project
	public String createProject(String projectName, String projectDescription, String visibility) throws Exception {

		// Enter project name
		getWebElement("CreateNewProjectButton").click();
		explicitWaitForElementUntilClickable("ProjectName");
		getWebElement("ProjectName").click();
		getWebElement("ProjectName").sendKeys(projectName);

		// Enter project description
		if (projectDescription == "" || (projectDescription == null)) {
			// SKIP ADDING DESCRIPTION
		} else {
			WebElement projectDescriptionFrame = getWebElement("ProjectDescription");
			driver.switchTo().frame(projectDescriptionFrame);
			WebElement projectDescriptionBody = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(projectDescriptionBody));
			projectDescriptionBody.click();
			projectDescriptionBody.sendKeys(projectDescription);
			driver.switchTo().defaultContent();
		}

		// SELECT VISIBILITY
		if (visibility.equalsIgnoreCase("Restricted")) {
			getWebElement("Visiblity_Restricted").click();
		} else if (visibility.equalsIgnoreCase("Lab")) {
			getWebElement("Visiblity_Lab_2").click();
		}

		getWebElement("SaveProjectButton").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		Reporter.log("Verify created project in project list");
		verifyProjectInProjectList(projectName);

		// UPDATE PROJECT NAME TO TEST DATA
		if (visibility.equalsIgnoreCase("Restricted")) {
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"Create_Project");
			ExcelUtils.setCellData(projectName, 2, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		} else if (visibility.equalsIgnoreCase("Lab")) {
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"Create_Project");
			ExcelUtils.setCellData(projectName, 1, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		}

		return projectName;
	}

	// Verify project in project list
	public void verifyProjectInProjectList(String projectName) throws Exception {

		// DECLARATION
		boolean bProject = false;
		String getProjectName;

		// SEARCH PROTOCOL TEMPLATE
		refreshPage();

		// VERIFY PROJECT NAME IN LIST
		List<WebElement> allProjects = driver.findElements(
				By.xpath("//a[starts-with(@id,'cardProjectForm:projectCardRepeat:') and @class='text-primary']"));
		for (int i = 0; i < allProjects.size(); i++) {
			getProjectName = allProjects.get(i).getAttribute("title").trim();
			if (getProjectName.equals(projectName)) {
				bProject = true;
				break;
			}
		}

		Assert.assertTrue("Project not found in project list page", bProject);
	}

	// Navigate to project detail page
	public boolean navigateToProjectDetailPage(String projectName) throws Exception {

		// REFRESH
		driver.navigate().refresh();

		// CLICK ON PROJECT NAME LINK
		WebElement projectLink = driver.findElement(By.xpath("//a[@title='" + projectName + "']"));
		expliciteWait(By.xpath("//a[@title='" + projectName + "']"), 5);
		projectLink.click();

		// VERIFY IF APPLICATION NAVIGATED TO PROJECT DETAIL PAGE OR NOT
		String getPageTitle = getWebElement("BreadCrumb_Heading").getText().trim();
		boolean bProjectTitleVal = getPageTitle.equals(projectName);
		Assert.assertTrue("Application not navigated to project detail page", bProjectTitleVal);
		return bProjectTitleVal;
	}

	// CREATE EXPERIMENT
	public String createNewExperiment(String experimentName, String experimentDescription, String experimentVisibility, String projectVisibility)
			throws Exception {
		getWebElement("Button_Create").click();
		explicitWaitForElementUntilClickable("Add_Experiment");
		getWebElement("Add_Experiment").click();
		explicitWaitForElementUntilClickable("CreateExperiment_ExpName");
		getWebElement("CreateExperiment_ExpName").click();
		getWebElement("CreateExperiment_ExpName").sendKeys(experimentName);

		// Adding experiment description
		if (experimentDescription == "" || (experimentDescription == null)) {
			// SKIP ADDING DESCRIPTION
		} else {
			WebElement experimentDescriptionFrame = getWebElement("CreateExperiment_ExpDesc");
			driver.switchTo().frame(experimentDescriptionFrame);
			WebElement experimentDescriptionBody = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(experimentDescriptionBody));
			experimentDescriptionBody.click();
			experimentDescriptionBody.sendKeys(experimentDescription);
			driver.switchTo().defaultContent();
		}

		// Select visibility
		switch (experimentVisibility) {
		case "LAB":
			getWebElement("Visiblity_Lab_2").click();
			break;

		case "RESTRICTED":
			getWebElement("Visiblity_Restricted").click();
			break;
			
		case "PROJECT":
			getWebElement("Visiblity_Project").click();
			break;

		default:
			System.out.println("Wrong project visibility");
		}

		getWebElement("SaveExperiment").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		Reporter.log("Verify created experiment in project detail page");
		verifyExperimentInProjectDetailPage(experimentName);

		// UPDATE CREATED EXPERIMENT NAME TO TEST DATA
		int row = 0;
		if(projectVisibility.equals("LAB") && experimentVisibility.equals("LAB")) {
			row = 1;
		} else if (projectVisibility.equals("LAB") && experimentVisibility.equals("RESTRICTED")) {
			row = 2;
		} else if (projectVisibility.equals("RESTRICTED") && experimentVisibility.equals("PROJECT")) {
			row = 3;
		} else if (projectVisibility.equals("RESTRICTED") && experimentVisibility.equals("RESTRICTED")) {
			row = 4;
		}
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		ExcelUtils.setCellData(experimentName, row, 0,
				Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Exp");
		return experimentName;
	}

	// VERIFY EXPERIMENT IN PROJECT DETAIL PAGE
	public boolean verifyExperimentInProjectDetailPage(String experimentName) throws Exception {
		boolean bExperiment = false;
		if (driver.findElements(By.xpath("//a[@title='" + experimentName + "']")).size() > 0) {
			bExperiment = true;
		} else {
			bExperiment = false;
		}
		Assert.assertTrue("Experiment not found in project detail page", bExperiment);
		return bExperiment;
	}

	// VERIFY EXPERIMENT IN PROJECT DETAIL PAGE
	public boolean navigateToExperimentDetailPage(String experimentName) throws Exception {
		Actions action = new Actions(driver);
		WebElement experimentNameLink = driver.findElement(By.xpath("//a[@title='" + experimentName + "']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(experimentNameLink));
		action.click(experimentNameLink).perform();

		// Verify if application navigated to project detail page or not
		explicitWaitForElementUntilClickable("BreadCrumb_Heading");
		String getPageTitle = getWebElement("BreadCrumb_Heading").getText().trim();
		boolean bExperimentTitleVal = getPageTitle.equals(experimentName);
		Assert.assertTrue("Application not navigated to project detail page", bExperimentTitleVal);
		return bExperimentTitleVal;
	}

	// Add steps in protocol template
	public void addProtocolSteps(String protocolName, String stepName, String stepDescription) throws Exception {
		getWebElement("AddSteps_Button").click();
		explicitWaitForElementUntilClickable("AddSteps_Title");
		getWebElement("AddSteps_Title").click();
		getWebElement("AddSteps_Title").sendKeys(stepName);

		if (stepDescription == "" || (stepDescription == null)) {
			// SKIP ADDING DESCRIPTION
		} else {
			WebElement addStepDescFrame = getWebElement("AddSteps_Description");
			driver.switchTo().frame(addStepDescFrame);
			WebElement addStepDescBody = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(addStepDescBody));
			addStepDescBody.click();
			addStepDescBody.sendKeys(stepDescription);
			driver.switchTo().defaultContent();
		}

		getWebElement("Button_Save").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");
	}

	// Verify Step title and description
	public void verifyProtocolStep(String stepTitle, String stepDescription) {
		List<WebElement> allStepsTitlesInProtocol = driver
				.findElements(By.xpath("//span[@data-title='Enter Step Name']"));
		String addedStepTitle = allStepsTitlesInProtocol.get(allStepsTitlesInProtocol.size() - 1).getText().trim();
		List<WebElement> allStepsDescInProtocol = driver
				.findElements(By.xpath("//div[@class='row']//div[@class='col-lg-12 wordBreak div-scroll']//p"));
		String addedStepDesc = allStepsDescInProtocol.get(allStepsTitlesInProtocol.size() - 1).getText().trim();
		boolean getTitleStatus = (addedStepTitle.equalsIgnoreCase(stepTitle));
		boolean getDescriptionStatus = addedStepDesc.equals(stepDescription);
		Assert.assertTrue("Protocol template steps title verification failed!", getTitleStatus);
		Assert.assertTrue("Protocol template steps description verification failed!", getDescriptionStatus);
	}

	// Add notes in protocol template
	public void addProtocolNotes(String noteTitle, String noteDescription) throws Exception {
		getWebElement("AddNotes_Button").click();
		explicitWaitForElementUntilClickable("EditProtocolTemplate_AddNotesTitle");
		getWebElement("EditProtocolTemplate_AddNotesTitle").click();
		getWebElement("EditProtocolTemplate_AddNotesTitle").sendKeys(noteTitle);

		if (noteDescription == "" || (noteDescription == null)) {
			// SKIP ADDING DESCRIPTION
		} else {
			WebElement addNoteDescFrame = getWebElement("EditProtocolTemplate_AddNotes_Description");
			driver.switchTo().frame(addNoteDescFrame);
			WebElement addNoteDescBody = driver.findElement(By.tagName("body"));
			explicitWaitForElementUntilClickable("addNoteDescBody");
			addNoteDescBody.click();
			addNoteDescBody.sendKeys(noteDescription);
			driver.switchTo().defaultContent();
		}

		getWebElement("EditProtocol_SaveNotes").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");
	}

	// Verify Step title and description
	public void verifyProtocolNotes(String noteTitle, String noteDescription) throws Exception {
		java.util.List<WebElement> allNotesInprotocol = driver
				.findElements(By.xpath("//span[@data-title='Enter Note Name']"));
		int noOfNotes = allNotesInprotocol.size();
		String getProtocolNotes, getNoteDescription;

		// GET PROTOCOL NOTES TITLE
		try {
			getProtocolNotes = allNotesInprotocol.get(noOfNotes).getText();
		} catch (Exception e) {
			getProtocolNotes = allNotesInprotocol.get(noOfNotes - 1).getText();
		}

		// GET PROTOCOL NOTES DESCRIPTION
		try {
			getNoteDescription = driver.findElement(By.xpath(
					"(//span[@id='notesForm:notePanel']//div[@id='note_" + (noOfNotes) + "']/following-sibling::p)[2]"))
					.getText().trim();
		} catch (Exception e) {
			getNoteDescription = driver.findElement(By.xpath("(//span[@id='notesForm:notePanel']//div[@id='note_"
					+ (noOfNotes - 1) + "']/following-sibling::p)[2]")).getText().trim();
		}

		boolean getTitleStatus = (getProtocolNotes.equals(noteTitle));
		boolean getDescriptionStatus = getNoteDescription.equals(noteDescription);
		Assert.assertTrue("Protocol template notes title verification failed!", getTitleStatus);
		Assert.assertTrue("Protocol template notes description verification failed!", getDescriptionStatus);
	}

	public void filterProjectList(String filterBy, String projectCreator) throws Exception {
		// REFRESH BROWSER
		refreshPage();

		switch (filterBy) {
		case "Project Creator":
			Reporter.log("Enter project list filter criteria and apply filter");
			getWebElement("Filter_List").click();
			explicitWaitForElementUntilClickable("ProjectFilter_CreatorName");
			getWebElement("ProjectFilter_CreatorName").click();
			Thread.sleep(1000);
			getWebElement("ProjectFilter_CreatorName").sendKeys(projectCreator);
			Thread.sleep(2000);
			List<WebElement> allLabMembers = driver
					.findElements(By.xpath("//span[@id='filterForm:filterName_panel']//li"));
			for (int i = 0; i < allLabMembers.size(); i++) {
				if (allLabMembers.get(i).getText().contains(projectCreator)) {
					allLabMembers.get(i).click();
					break;
				}
			}
			getWebElement("Apply_Filter").click();
			Thread.sleep(1000);
			
			Reporter.log("Validation of project list based on applied filter \"Filter by creator\"");
			List<WebElement> creatorListFromAllProjecsInPage = driver.findElements(By.xpath(
					"//div[@class='custom-col-toggle-child bs-callout bs-callout-warning card-view-wrapper ']//ul[@class='list-inline']//li[1]"));
			if ((creatorListFromAllProjecsInPage.size() == 0)) {
				Assert.assertTrue("No records found", false);
			} else {
				for (int j = 0; j < creatorListFromAllProjecsInPage.size(); j++) {
					Assert.assertTrue("Filter by project creator validation failed",
							creatorListFromAllProjecsInPage.get(j).getText().equals(projectCreator));
				}
			}
			break;

		case "Date Range":
			getWebElement("Filter_List").click();
			explicitWaitForElementUntilClickable("ProjectFilter_FromDate");
			getWebElement("ProjectFilter_FromDate").click();
			getWebElement("ProjectFilter_FromDate").sendKeys(Keys.ENTER);
			explicitWaitForElementUntilClickable("ProjectFilter_ToDate");
			getWebElement("ProjectFilter_ToDate").click();
			getWebElement("ProjectFilter_ToDate").sendKeys(Keys.ENTER);
			getWebElement("Apply_Filter").click();
			Thread.sleep(1000);

			List<WebElement> projectCreatedDateList = driver.findElements(By.xpath("//span[@title='Created date']"));
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
			String currentDate = dateFormat.format(date).toString();
			if ((projectCreatedDateList.size() == 0)) {
				Assert.assertTrue("No records found", false);
			} else {
				for (int j = 0; j < projectCreatedDateList.size(); j++) {
					Assert.assertTrue("Filter by project created date validation failed",
							projectCreatedDateList.get(j).getText().equals(currentDate));
				}
			}
			break;

		case "Status":
			getWebElement("Filter_List").click();
			explicitWaitForElementUntilClickable("ProjectFilter_Status_Open");
			getWebElement("ProjectFilter_Status_Open").click();
			getWebElement("Apply_Filter").click();
			Thread.sleep(1000);
			List<WebElement> projectStateList1 = driver.findElements(By.xpath("//p[@class='progress-started-state']"));
			for (int j = 0; j < projectStateList1.size(); j++) {
				Assert.assertTrue("Filter by project status OPEN validation failed",
						projectStateList1.get(j).getText().equals("OPEN"));
			}

			// REFRESH
			refreshPage();

			getWebElement("Filter_List").click();
			explicitWaitForElementUntilClickable("ProjectFilter_Status_InProgress");
			getWebElement("ProjectFilter_Status_InProgress").click();
			getWebElement("Apply_Filter").click();
			Thread.sleep(1000);
			List<WebElement> projectStateList2 = driver.findElements(By.xpath("//p[@class='inprogress-state']"));
			if ((projectStateList2.size() == 0)) {
				Assert.assertTrue("No records found", false);
			} else {
				for (int j = 0; j < projectStateList2.size(); j++) {
					Assert.assertTrue("Filter by project status IN-PROGRESS validation failed",
							projectStateList2.get(j).getText().equals("IN-PROGRESS"));
				}
			}
			break;

		default:
			Reporter.log("Invalid filter");
		}
	}

	public void filterProtocolTemplateList(String filterBy, String protocolCreator, String labName) throws Exception {
		// INITIALIZE LIBRARY OBJECT
		Library lib = new Library();

		// REFRESH BROWSER
		refreshPage();

		switch (filterBy) {
		case "Protocol Creator":
			Reporter.log("Enter protocol list filter criteria and apply filter");
			getWebElement("Filter_List").click();
			explicitWaitForElementUntilClickable("ProtocolFilter_CreatorName");
			getWebElement("ProtocolFilter_CreatorName").click();
			Thread.sleep(1000);
			getWebElement("ProtocolFilter_CreatorName").sendKeys(protocolCreator);
			Thread.sleep(2000);
			List<WebElement> allLabMembers = driver
					.findElements(By.xpath("//span[@id='filterForm:filterName_panel']//li"));
			for (int i = 0; i < allLabMembers.size(); i++) {
				if (allLabMembers.get(i).getText().contains(protocolCreator)) {
					allLabMembers.get(i).click();
					break;
				}
			}
			getWebElement("Apply_Filter").click();
			Thread.sleep(1000);
			Reporter.log("Validation of project list based on applied filter \"Filter by creator\"");
			List<WebElement> creatorListFromAllProtocolInPage = driver.findElements(By.xpath(
					"//div[@class='custom-col-toggle-child bs-callout bs-callout-success card-view-wrapper ']//ul[@class='list-inline']//li[1]"));

			if ((creatorListFromAllProtocolInPage.size() == 0)) {
				Assert.assertTrue("No records found", false);
			} else {
				for (int j = 0; j < creatorListFromAllProtocolInPage.size(); j++) {
					Assert.assertTrue("Filter by project creator validation failed",
							creatorListFromAllProtocolInPage.get(j).getText().equals(protocolCreator));
				}
			}
			break;

		case "Date Range":
			getWebElement("Filter_List").click();
			explicitWaitForElementUntilClickable("ProtocolFilter_FromDate");
			getWebElement("ProtocolFilter_FromDate").click();
			getWebElement("ProtocolFilter_FromDate").sendKeys(Keys.ENTER);
			explicitWaitForElementUntilClickable("ProtocolFilter_ToDate");
			getWebElement("ProtocolFilter_ToDate").click();
			getWebElement("ProtocolFilter_ToDate").sendKeys(Keys.ENTER);
			getWebElement("Apply_Filter").click();
			Thread.sleep(1000);

			List<WebElement> protocolCreatedDateList = driver.findElements(By.xpath("//span[@title='createdDate']"));
			Assert.assertTrue("No records found", (protocolCreatedDateList.size() > 0));

			if ((protocolCreatedDateList.size() == 0)) {
				Assert.assertTrue("No records found", false);
			} else {
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
				String currentDate = dateFormat.format(date).toString();

				for (int j = 0; j < protocolCreatedDateList.size(); j++) {
					Assert.assertTrue("Filter by protocol created date validation failed",
							protocolCreatedDateList.get(j).getText().equals(currentDate));
				}
			}
			break;

		case "Lab Name":
			getWebElement("Filter_List").click();
			Thread.sleep(2000);
			lib.SelectByVisibleText("ProtocolFilter_LabName", labName);
			getWebElement("Apply_Filter").click();
			Thread.sleep(1000);
			List<WebElement> lessMoreToggle = driver.findElements(By.xpath("//span[@class='lessMoreToggle']"));
			if ((lessMoreToggle.size() == 0)) {
				Assert.assertTrue("No records found", false);
			} else {
				for (int k = 0; k < lessMoreToggle.size(); k++) {
					lessMoreToggle.get(k).click();
					Thread.sleep(1500);
				}
				Thread.sleep(2000);
				List<WebElement> protocolCardLabList = driver
						.findElements(By.xpath("//lable[contains(text(),'LabName :')]/following-sibling::p"));
				for (int j = 0; j < protocolCardLabList.size(); j++) {
					Assert.assertTrue("Protocol template filter by lab name validation failed",
							protocolCardLabList.get(j).getText().equals(labName));
				}
			}
			break;

		default:
			Reporter.log("Invalid filter");
		}
	}

	// Assign code to experiment
	public String assignCodeToExperiment(String experimentCode) throws Exception {
		getWebElement("ExperimentSettings").click();
		explicitWaitForElementUntilClickable("Experiment_AssignCode");
		getWebElement("Experiment_AssignCode").click();
		explicitWaitForElementUntilClickable("AddExperimentCode");
		getWebElement("AddExperimentCode").click();
		getWebElement("AddExperimentCode").sendKeys(experimentCode);
		getWebElement("SaveExperimentCode").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		Reporter.log("Verify experiment code assigned to experiment");
		String getExperimentCode = getWebElement("ExperimentCode").getText();
		Assert.assertTrue("Assigned code to experiment failed!", getExperimentCode.equals(experimentCode));
		return experimentCode;
	}

	// Assign code to project
	public String assignCodeToProject(String projectCode) throws Exception {
		getWebElement("ProjectSettings").click();
		explicitWaitForElementUntilClickable("ProjectSettings_AssignCode");
		getWebElement("ProjectSettings_AssignCode").click();
		explicitWaitForElementUntilClickable("AddProjectCode");
		getWebElement("AddProjectCode").click();
		getWebElement("AddProjectCode").sendKeys(projectCode);
		getWebElement("SaveProjectCode").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		Reporter.log("Verify code assigned to the project");
		String getProjectCode = getWebElement("ProjectCode").getText();
		Assert.assertTrue("Code not assigned to project successfully", getProjectCode.equals(projectCode));
		return projectCode;
	}

	public void addStep(String stepTitle, String stepDescription) throws Exception {
		WebElement AddStepButton = getWebElement("AddSteps_Button");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", AddStepButton);
		js.executeScript("window.scrollTo(0, document.body.scrollTop)");
		explicitWaitForElementUntilClickable("AddStepButton");
		AddStepButton.click();
		explicitWaitForElementUntilClickable("AddSteps_Title");
		getWebElement("AddSteps_Title").click();
		getWebElement("AddSteps_Title").sendKeys(stepTitle);

		if (stepDescription == "" || (stepDescription == null)) {
			// SKIP ADDING DESCRIPTION
		} else {
			WebElement addStepDescFrame = getWebElement("AddSteps_Description");
			driver.switchTo().frame(addStepDescFrame);
			WebElement addStepDescBody = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(addStepDescBody));
			addStepDescBody.click();			
			addStepDescBody.sendKeys(stepDescription);
			driver.switchTo().defaultContent();
		}

		getWebElement("Button_Save").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		Reporter.log("Verify step title and description");
		int noOfSteps = driver.findElements(By.xpath("//div[@class='step-accordian-box ui-sortable-handle']")).size();

		// VERIFICATION OF STEP TITLE
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));
		String getStepTitle = allStepTitle.get(noOfSteps - 1).getText().trim();
		Assert.assertTrue("Step title mismatch", getStepTitle.equals(stepTitle));

		// VERIFICATION OF STEP DESCRIPTION
		String getStepDescription = null;
		if (stepDescription == "" || (stepDescription == null)) {
			// SKIP VERIFICATION OF DESCRIPTION
		} else {
			getStepDescription = driver.findElement(By.xpath("//div[@class='step-accordian-box ui-sortable-handle']["
					+ (noOfSteps) + "]//p[contains(text(),'" + stepDescription + "')]")).getText().trim();
			Assert.assertTrue("Step description mismatch", getStepDescription.equals(stepDescription));
		}
	}

	public void editStep(String stepTitle, String editStepTitle, String editStepDescription) throws Exception {
		// DECLARATION
		int stepNumber;

		// GET STEP INDEX
		stepNumber = getStepPositionNumber(stepTitle);

		// EDIT STEP TITLE AND DESCRIPTION
		driver.findElement(By.xpath("//div[@class='step-accordian-box ui-sortable-handle'][" + stepNumber
				+ "]//i[@class='fa fa-pencil icon-custom-size']")).click();
		explicitWaitForElementUntilClickable("AddSteps_Title");
		getWebElement("AddSteps_Title").click();
		getWebElement("AddSteps_Title").clear();
		getWebElement("AddSteps_Title").sendKeys(editStepTitle);

		if (editStepDescription == "" || (editStepDescription == null)) {
			// SKIP EDITING DESCRIPTION
		} else {
			WebElement addStepDescFrame = getWebElement("AddSteps_Description");
			driver.switchTo().frame(addStepDescFrame);
			WebElement addStepDescBody = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(addStepDescBody));
			addStepDescBody.click();
			addStepDescBody.clear();
			addStepDescBody.sendKeys(editStepDescription);
			driver.switchTo().defaultContent();
		}

		getWebElement("Button_Save").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// VERIFICATION OF STEP TITLE
		Reporter.log("Verify step title and description");
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));
		String getStepTitle = allStepTitle.get(stepNumber - 1).getText().trim();
		Assert.assertTrue("Step title mismatch", getStepTitle.equals(editStepTitle));

		// VERIFICATION OF STEP DESCRIPTION
		String getStepDescription = null;
		int noOfSteps = allStepTitle.size();
		if (editStepDescription == "" || (editStepDescription == null)) {
			// SKIP DESCRIPTION CHECK
		} else {
			getStepDescription = driver.findElement(By.xpath("//div[@class='step-accordian-box ui-sortable-handle']["
					+ (noOfSteps) + "]//p[contains(text(),'" + editStepDescription + "')]")).getText().trim();
			Assert.assertTrue("Step description mismatch", getStepDescription.equals(editStepDescription));
		}
	}

	public void deleteStep(String stepTitle) throws Exception {
		// DECLARATION
		int stepNumber;

		// GET STEP INDEX
		stepNumber = getStepPositionNumber(stepTitle);

		// EDIT STEP TITLE AND DESCRIPTION
		driver.findElement(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle'][" + stepNumber + "]//i[@class='fa fa-trash']"))
				.click();

		// CLICK ON YES FROM CONFIRMATION MODAL
		WebElement button_Yes = driver.findElement(By.xpath(
				"//a[@id='templateStepForm:stepRepeat:" + (stepNumber - 1) + ":deleteProjectLink:savePermissions']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(button_Yes));
		button_Yes.click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// VERIFICATION OF STEP TITLE
		Reporter.log("Verify step title after deleting step");
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));
		for (int i = 0; i < allStepTitle.size() - 1; i++) {
			String getStepTitle = allStepTitle.get(i).getText().trim();
			if (getStepTitle.equals(stepTitle)) {
				Assert.assertFalse("Step not deleted successfully", getStepTitle.equals(stepTitle));
			}
		}
	}

	public void addNote(String noteTitle, String noteDescription) throws Exception {
		getWebElement("AddNotes_Button").click();
		explicitWaitForElementUntilClickable("AddNote_Title");
		getWebElement("AddNote_Title").click();
		getWebElement("AddNote_Title").sendKeys(noteTitle);

		if (noteDescription == "" || (noteDescription == null)) {
			// SKIP ADDING DESCRIPTION
		} else {
			WebElement addNoteDescFrame = getWebElement("NoteDescription");
			driver.switchTo().frame(addNoteDescFrame);
			WebElement addNoteDescBody = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(addNoteDescBody));
			addNoteDescBody.click();
			addNoteDescBody.sendKeys(noteDescription);
			driver.switchTo().defaultContent();
		}

		getWebElement("Save_Link_2").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// Scroll down to end of the page
		scrollDownPage();

		Reporter.log("Verify note title and description");
		int noOfNotes = driver
				.findElements(By.xpath(
						"//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']"))
				.size();

		// VERIFICATION OF NOTE TITLE
		String getNoteTitle;
		try {
			List<WebElement> allNoteTitle = driver.findElements(By.xpath(
					"//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']//span"));
			getNoteTitle = allNoteTitle.get(noOfNotes - 1).getText().trim();
		} catch (Exception E) {
			List<WebElement> allNoteTitle = driver.findElements(
					By.xpath("//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']"));
			getNoteTitle = allNoteTitle.get(noOfNotes - 1).getText().trim();
		}
		Assert.assertTrue("Note title mismatch", getNoteTitle.equals(noteTitle));

		// VERIFICATION OF NOTE DESCRIPTION
		String getNoteDescription;
		if (noteDescription == "" || (noteDescription == null)) {
			// SKIP VERIFICATION OF NOTE DESCRIPTION
		} else {
			try {
				getNoteDescription = driver.findElement(By.xpath("(//span[@id='notesForm:notePanel']//div[@id='note_"
						+ noOfNotes + "']/following-sibling::p)[2]")).getText().trim();
			} catch (Exception e) {
				getNoteDescription = driver.findElement(By.xpath("(//span[@id='notesForm:notePanel']//div[@id='note_"
						+ (noOfNotes - 1) + "']/following-sibling::p)[2]")).getText().trim();
			}
			Assert.assertTrue("Note description mismatch", getNoteDescription.equals(noteDescription));
		}
	}

	public void addStepNote(String stepTitle, String stepNoteTitle, String stepNoteDescription) throws Exception {
		int stepNo = 0;

		// GET STEP DETAILS
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));

		// GET STEP NUMBER
		for (int i = 0; i < allStepTitle.size(); i++) {
			String getStepTitle = allStepTitle.get(i).getText().trim();
			if (getStepTitle.equals(stepTitle)) {
				stepNo = i + 1;
				break;
			}
		}

		// ADD STEP NOTE TO THAT PARTICULAR STEP
		try {
			driver.findElement(
					By.xpath("(//a[@data-target='#addStepNoteModal']//i[@class='fa fa-sticky-note'])[" + stepNo + "]"))
					.click();
			explicitWaitForElementUntilClickable("AddStepNote");
			getWebElement("AddStepNote").click();
			getWebElement("AddStepNote").sendKeys(stepNoteTitle);
			
			if (stepNoteDescription == "" || (stepNoteDescription == null)) {
				// SKIP ADDING DESCRIPTION
			} else {
				WebElement addStepNoteDescFrame = getWebElement("AddStepNoteDescription");
				driver.switchTo().frame(addStepNoteDescFrame);
				WebElement addStepNoteDescBody = driver.findElement(By.tagName("body"));
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(addStepNoteDescBody));
				addStepNoteDescBody.click();
				addStepNoteDescBody.sendKeys(stepNoteDescription);
				driver.switchTo().defaultContent();
			}
			getWebElement("SaveStepNote").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");
		} catch (Exception e) {
			getWebElement("Cancel_StepNoteModal").click();
			Thread.sleep(500);
			refreshPage();
			driver.findElement(
					By.xpath("(//a[@data-target='#addStepNoteModal']//i[@class='fa fa-sticky-note'])[" + stepNo + "]"))
					.click();
			explicitWaitForElementUntilClickable("AddStepNote");
			getWebElement("AddStepNote").click();
			getWebElement("AddStepNote").sendKeys(stepNoteTitle);
			if (stepNoteDescription == "" || (stepNoteDescription == null)) {
				// SKIP ADDING DESCRIPTION
			} else {
				WebElement addStepNoteDescFrame = getWebElement("AddStepNoteDescription");
				driver.switchTo().frame(addStepNoteDescFrame);
				WebElement addStepNoteDescBody = driver.findElement(By.tagName("body"));
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(addStepNoteDescBody));
				addStepNoteDescBody.click();
				addStepNoteDescBody.sendKeys(stepNoteDescription);
				driver.switchTo().defaultContent();
			}
			getWebElement("SaveStepNote").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");
		}

		Reporter.log("Verify step note title and description");
		int noOfNotesInStep = driver
				.findElements(By.xpath(
						"//div[@id='step-view" + (stepNo - 1) + "']//div[@class='notesOutput step-note-new-wrapper']"))
				.size();

		int stepNoteNoInStep = 0;
		boolean bNoteTitleVal = false;
		boolean bNoteDescVal = false;

		// VERIFICATION OF STEP NOTE TITLE
		for (int j = 0; j < noOfNotesInStep; j++) {
			String getNoteTitle = driver.findElement(By.xpath("(//div[@id='step-view" + (stepNo - 1)
					+ "']//div[@class='notesOutput step-note-new-wrapper']//div[@class='col-lg-10']//ul[@class='list-inline'])["
					+ (j + 1) + "]//li[1]//b")).getText().trim();
			if (getNoteTitle.equals(stepNoteTitle)) {
				bNoteTitleVal = true;
				stepNoteNoInStep = j + 1;
				break;
			}
		}
		Assert.assertTrue("Step note title validation failed", bNoteTitleVal);

		// VERIFICATION OF STEP NOTE DESCRIPTION
		if (stepNoteDescription == "" || (stepNoteDescription == null)) {
			// SKIP VERIFICATION OF STEP NOTE DESCRIPTION
		} else {
			for (int j = 0; j < noOfNotesInStep; j++) {
				String getNoteDesc = driver.findElement(By.xpath(
						"(//div[@class='notesOutput step-note-new-wrapper']//div[@class='note-step-description div-scroll wordBreak']//div[@id='step_"
								+ (stepNo - 1) + "_note_" + (stepNoteNoInStep - 1) + "']/following-sibling::p)[2]"))
						.getText().trim();
				if (getNoteDesc.equals(stepNoteDescription)) {
					bNoteDescVal = true;
					break;
				}
			}
			Assert.assertTrue("Step note description validation failed", bNoteDescVal);
		}
	}

	public void editStepNote(String stepTitle, String stepNoteTitle, String editStepNoteTitle,
			String editStepNoteDescription) throws Exception {
		// GET STEP INDEX
		int stepNo = getStepPositionNumber(stepTitle);

		// GET STEP NOTE INDEX
		int stepNoteNo = getStepNotePositionNumber(stepTitle, stepNoteTitle);

		// CLICK ON EDIT LINK
		driver.findElement(By.xpath("//a[@id='templateStepForm:stepRepeat:" + (stepNo - 1) + ":noteList:"
				+ (stepNoteNo - 1) + ":setNote']")).click();
		explicitWaitForElementUntilClickable("AddStepNote");
		getWebElement("AddStepNote").click();
		getWebElement("AddStepNote").clear();
		getWebElement("AddStepNote").sendKeys(editStepNoteTitle);
		if (editStepNoteDescription == "" || (editStepNoteDescription == null)) {
			// SKIP ADDING DESCRIPTION
		} else {
			WebElement addStepNoteDescFrame = getWebElement("AddStepNoteDescription");
			driver.switchTo().frame(addStepNoteDescFrame);
			WebElement addStepNoteDescBody = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(addStepNoteDescBody));
			addStepNoteDescBody.click();
			addStepNoteDescBody.clear();
			addStepNoteDescBody.sendKeys(editStepNoteDescription);
			driver.switchTo().defaultContent();
		}
		getWebElement("SaveStepNote").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		Reporter.log("Verify step note title and description");
		int noOfNotesInStep = driver
				.findElements(By.xpath(
						"//div[@id='step-view" + (stepNo - 1) + "']//div[@class='notesOutput step-note-new-wrapper']"))
				.size();

		int stepNoteNoInStep = 0;
		boolean bNoteTitleVal = false;
		boolean bNoteDescVal = false;

		// VERIFICATION OF STEP NOTE TITLE
		for (int j = 0; j < noOfNotesInStep; j++) {
			String getNoteTitle = driver.findElement(By.xpath("(//div[@id='step-view" + (stepNo - 1)
					+ "']//div[@class='notesOutput step-note-new-wrapper']//div[@class='col-lg-10']//ul[@class='list-inline'])["
					+ (j + 1) + "]//li[1]//b")).getText().trim();
			if (getNoteTitle.equals(editStepNoteTitle)) {
				bNoteTitleVal = true;
				stepNoteNoInStep = j + 1;
				break;
			}
		}
		Assert.assertTrue("Edited Step note title validation failed", bNoteTitleVal);

		// VERIFICATION OF STEP NOTE DESCRIPTION
		if (editStepNoteDescription == "" || (editStepNoteDescription == null)) {
			// SKIP VERIFICATION OF STEP NOTE DESCRIPTION
		} else {
			for (int j = 0; j < noOfNotesInStep; j++) {
				String getNoteDesc = driver.findElement(By.xpath(
						"(//div[@class='notesOutput step-note-new-wrapper']//div[@class='note-step-description div-scroll wordBreak']//div[@id='step_"
								+ (stepNo - 1) + "_note_" + (stepNoteNoInStep - 1) + "']/following-sibling::p)[2]"))
						.getText().trim();
				if (getNoteDesc.equals(editStepNoteDescription)) {
					bNoteDescVal = true;
					break;
				}
			}
			Assert.assertTrue("Edited Step note description validation failed", bNoteDescVal);
		}
	}

	// SCROLL DOWN
	public void scrollDownPage() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
	}

	// DELETE STEP NOTE
	public boolean deleteStepNote(String stepTitle, String stepNoteTitle) throws Exception {
		int stepNo = 0;
		int noOfNotesInsideStep = 0;
		int stepNoteInd = 0;
		int initialStepNotesCount = 0;

		// GET STEP DETAILS
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));

		// GET STEP NOTE INDEX
		for (int i = 0; i < allStepTitle.size(); i++) {
			String getStepTitle = allStepTitle.get(i).getText().trim();
			noOfNotesInsideStep = driver
					.findElements(By.xpath(
							"//div[@id='step-view" + (i) + "']//div[@class='notesOutput step-note-new-wrapper']"))
					.size();
			stepNoteInd = stepNoteInd + noOfNotesInsideStep;
			if (getStepTitle.equals(stepTitle)) {
				stepNo = i + 1;
				for (int j = 0; j < noOfNotesInsideStep; j++) {
					String getNoteTitle = driver.findElement(By.xpath("(//div[@id='step-view" + (stepNo - 1)
							+ "']//div[@class='notesOutput step-note-new-wrapper']//div[@class='col-lg-10']//ul[@class='list-inline'])["
							+ (j + 1) + "]//li[1]//b")).getText().trim();
					if (getNoteTitle.equals(stepNoteTitle)) {
						stepNoteInd = stepNoteInd + j - 1;
						initialStepNotesCount = noOfNotesInsideStep;
						break;
					}
				}
				break;
			}
		}

		// DELETE STEP NOTE
		WebElement deleteStepNote = driver.findElements(By.xpath("//a[@title='Delete Note']//i[@class='fa fa-trash']")).get(stepNoteInd);
		deleteStepNote.click();
		explicitWaitForElementUntilClickable("Button_Ok2");
		getWebElement("Button_Ok2").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		Reporter.log("Verify step note after delete action");
		int noOfNotesInStepFinal = driver
				.findElements(By.xpath(
						"//div[@id='step-view" + (stepNo - 1) + "']//div[@class='notesOutput step-note-new-wrapper']"))
				.size();
		boolean stepNameValidation = false;
		if (noOfNotesInStepFinal == 0 & initialStepNotesCount == 1) {
			stepNameValidation = false;
		} else {
			for (int j = 0; j < noOfNotesInStepFinal; j++) {
				String getNoteTitle = driver.findElement(By.xpath("(//div[@id='step-view" + (stepNo - 1)
						+ "']//div[@class='notesOutput step-note-new-wrapper']//div[@class='col-lg-10']//ul[@class='list-inline'])["
						+ (j + 1) + "]//li[1]//b")).getText().trim();
				if (getNoteTitle.equals(stepNoteTitle)) {
					stepNameValidation = true;
					break;
				}
			}
		}

		Assert.assertFalse("Step note not deleted successfully", stepNameValidation);
		return stepNameValidation;
	}

	public void breadCrumbNavigation(String breadCrumbLinkName) throws Exception {
		try {
			WebElement element = driver.findElement(
					By.xpath("//ol[@class='breadcrumb']//a[contains(text(),'" + breadCrumbLinkName + "')]"));
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//ol[@class='breadcrumb']//a[contains(text(),'" + breadCrumbLinkName + "')]")));
			wait.until(ExpectedConditions.visibilityOf(element));
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
		} catch (Exception e) {
			driver.navigate().refresh();
			WebElement element = driver.findElement(
					By.xpath("//ol[@class='breadcrumb']//a[contains(text(),'" + breadCrumbLinkName + "')]"));
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//ol[@class='breadcrumb']//a[contains(text(),'" + breadCrumbLinkName + "')]")));
			wait.until(ExpectedConditions.visibilityOf(element));
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
		}
	}

	// NAVIGATION TO MATERIALS PAGE
	public boolean MaterialPageNavigation() throws Exception {
		// NAVIGATION TO MATERIALS PAGE
		Reporter.log("Navigate to Materials Page.");
		impliciteWait(5);
		getWebElement("Inventory.NavigationBarInventoryAndRequest").click();
		impliciteWait(2);
		getWebElement("Inventory.NavigationMaterials").click();

		// VERIFY MATERIALS PAGE EXISTS OR NOT.
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("Materials page not displayed", materialsPageName.equals("Materials"));
		Reporter.log("Materials page displayed successfully.");
		Utills.captureScreenshot("Materials_Page_Pass_" + TodayDate.Date());
		return true;
	}

	public void Research_AddMaterial(int rowNumber) throws Exception {
		SoftAssert softAssertion = new SoftAssert();
		Library TodayDate = new Library();

		// DECLARATION
		String otherVendorName = null;

		// GENERATE UNIQUE NUMBER
		String uniqueNumber = TodayDate.Date();

		// TAKES THE INPUT FROM EXCEL AND STORES IT IN A VARIABLE.
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ResearchMaterialsAndProducts");
		String materialName = "M" + uniqueNumber;
		String catalogNumber = "C" + uniqueNumber;
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
		String casNumber = "CAS" + uniqueNumber;
		String lotNumber = "LOT" + uniqueNumber;
		String description = ExcelUtils.getCellData(rowNumber, 7);

		// CREATING UNIQUE VENDOR
		if (vendorName == "" || (vendorName == null)) {
			otherVendorName = "V" + uniqueNumber;
		}

		// NAVIGATION TO MATERIALS PAGE. VERIFY MATERIALS PAGE EXISTS OR NOT.
		InventoryRegularFunctions materialsPageNavigation = new InventoryRegularFunctions();
		materialsPageNavigation.MaterialPageNavigation();

		// CLICK ON "ADD MATERIAL"
		int materialsCountBefore = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		Reporter.log("Navigate to Add Material modal, enter the material details and add it to Inventory");
		getWebElement("Inventory.AddMaterial").click();

		// VERIFY ADD MATERIAL PAGE EXISTS
		explicitWaitForElementUntilClickable("Inventory.MaterialDetailTab");
		String materialDetailsTab = getWebElement("Inventory.MaterialDetailTab").getText();
		Assert.assertTrue("Add Material page not displayed", materialDetailsTab.equals("Step 1 | Material details"));
		Utills.captureScreenshot("Add_Material_Page_" + TodayDate.Date());

		// ENTERING THE MATERIAL DETAILS IN ADD MATERIAL MODAL
		getWebElement("Inventory.AddMateriName").click();
		getWebElement("Inventory.AddMateriName").sendKeys(materialName);
		getWebElement("Inventory.AddCatalogNumber").click();
		getWebElement("Inventory.AddCatalogNumber").sendKeys(catalogNumber);

		getWebElement("Inventory.Quantity").click();
		Thread.sleep(1000);
		getWebElement("Inventory.Quantity").sendKeys(quantity);

		// VERIFY THE PROVIDED UOM EXISTS IN LIST
		Library SelectList = new Library();
		SelectList.SelectByValue("Inventory_AddMaterial_UOM", uom);
		if (SelectList.VerifySelectList("Inventory_AddMaterial_UOM", uom) == true) {
			SelectList.SelectByValue("Inventory_AddMaterial_UOM", uom);
		} else {
			Assert.fail("The UOM - " + uom + " does not exist");
		}
		
		// CHECK WHETHER THE VENDOR EXISTS OR NOT.		
		InventoryRegularFunctions InvRegFunc = new InventoryRegularFunctions();
		InvRegFunc.Add_Material_Page_Add_New_Vendor(vendorName);

		// CLICKS ON "OTHER DETAILS" TAB.
		getWebElement("Inventory.OtherDetailsTab").click();
		explicitWaitForElementUntilClickable("Inventory.AddCasNumber");
		getWebElement("Inventory.AddCasNumber").click();
		getWebElement("Inventory.AddCasNumber").sendKeys(casNumber);
		getWebElement("Inventory.AddLotNumber").sendKeys(lotNumber);
		getWebElement("Inventory.Description").click();
		getWebElement("Inventory.Description").sendKeys(description);
		getWebElement("Inventory.AddToInventoryButton").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// VERIFY ADD MATERIAL CONFIRMATION MODAL
		String addMaterialConfirmationModal = getWebElement("Inventory.AddMaterial.ConfirmationModal").getText();
		Assert.assertTrue("Add Material confirmation modal not displayed.",
				addMaterialConfirmationModal.equals("Do you want to Add Material?"));
		getWebElement("Inventory.OkButton").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");
		Utills.captureScreenshot("Add_Material_Confirmation_Modal_" + TodayDate.Date());

		// VERIFY MATERIALS PAGE EXISTS OR NOT AFTER ADDITION OF NEW MATERIAL
		String materialsPageName = getWebElement("Inventory.PageHeading").getText();
		Assert.assertTrue("After addition of new material, the materials page not displayed",
				materialsPageName.equals("Materials"));
		Utills.captureScreenshot("Materials_Page_" + TodayDate.Date());

		// VERIFY THE COUNT OF MATERIALS WHEN A NEW MATERIAL IS ADDED.
		boolean bMaterialAdded = false;
		int MaterialsCountAfter = Integer.parseInt(getWebElement("Inventory.MaterialsCount").getText());
		if (MaterialsCountAfter == (materialsCountBefore + 1)) {
			bMaterialAdded = true;
			System.out.println("After addition of new material the material count is increased by "
					+ (MaterialsCountAfter - materialsCountBefore));
		} else {
			bMaterialAdded = false;
			softAssertion.fail("After addition of new material the material count is not increased.");
			String actualMaterialName = getWebElement("Inventory.MaterialDetailNavigation").getText();
			Assert.assertTrue("Newly created material - " + materialName
					+ " not displayed at the top of the page. Actual Material Displayed is - " + actualMaterialName,
					actualMaterialName.equals(materialName));
			Reporter.log("Newly created material - " + materialName + " displayed at the top of the page.");
		}

		// UPDATE MATERIAL NAME TO TEST DATA SHEET
		if (bMaterialAdded) {
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			ExcelUtils.setCellData(materialName, rowNumber, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			ExcelUtils.setCellData(catalogNumber, rowNumber, 1,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			if ((vendorName == "") || (vendorName == null)) {
				ExcelUtils.setCellData(otherVendorName, rowNumber, 2,
						Research_Constants.Path_TestData + Research_Constants.File_TestData,
						"ResearchMaterialsAndProducts");
			}
			ExcelUtils.setCellData(casNumber, rowNumber, 5,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			ExcelUtils.setCellData(lotNumber, rowNumber, 6,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			ExcelUtils.setCellData(description, rowNumber, 7,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
		}
	}

	public void NavigateToLabCatalogList() throws Exception {
		Reporter.log("Navigate to lab catalog list");
		getWebElement("SideBar_Inventory_EHS_Group2").click();
		explicitWaitForElementUntilClickable("SubMenu_LabCatalog2");
		getWebElement("SubMenu_LabCatalog2").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");
	}

	public void Research_AddLabCatalogProduct(int rowNumber) throws Exception {

		// GENERATE UNIQUE NUMBER
		String uniqueNumber = TodayDate.Date();

		// TAKES THE INPUT FROM EXCEL AND STORES IT IN A VARIABLE.
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
				"ResearchMaterialsAndProducts");
		String productName = "L" + uniqueNumber;
		String catalogNumber = "C" + uniqueNumber;
		String vendorName = ExcelUtils.getCellData(rowNumber, 2);
		String quantity = ExcelUtils.getCellData(rowNumber, 3);
		String uom = ExcelUtils.getCellData(rowNumber, 4);
		String casNumber = "CAS" + uniqueNumber;
		String description = ExcelUtils.getCellData(rowNumber, 7);

		// CREATING UNIQUE VENDOR
		String newVendorName = null;
		if (vendorName == "" || (vendorName == null)) {
			newVendorName = "V" + uniqueNumber;
		}

		// NAVIGATE TO LAB CATALOG LIST
		NavigateToLabCatalogList();

		// GET INITIAL PRODUCT COUNT
		String count = getWebElement("LabCatalog_ProductsCount").getText();
		String count2 = count.split(" ")[0];
		int intialCount = Integer.parseInt(count2);

		// CLICK ON ADD NEW PRODUCT BUTTON
		getWebElement("Add_New_Product").click();
		explicitWaitForElementUntilClickable("AddNewProdcut_ProductName");

		// ENTER PRODUCT DETAILS
		getWebElement("AddNewProdcut_ProductName").sendKeys(productName);
		getWebElement("AddNewProdcut_CatalogNumber").sendKeys(catalogNumber);
		getWebElement("AddNewProdcut_Quantity").sendKeys(quantity);
		getWebElement("AddNewProdcut_UoM").sendKeys(uom);

		// CHECK WHETHER THE VENDOR EXISTS OR NOT.
		Library SelectList = new Library();
		if (SelectList.VerifySelectList("LabCatalog_AddNewProdcut_VendorDropdown", vendorName) == true) {
			SelectList.SelectByVisibleText("LabCatalog_AddNewProdcut_VendorDropdown", vendorName);
		} else {
			// CREATE A NEW UNIQUE VENDOR
			SelectList.SelectByVisibleText("AddNewProdcut_VendorDropdown", "Add Vendor");
			SelectList.SelectByValue("LabCatalog.AddOtherVendor", newVendorName);
			getWebElement("NewVendorSaveCheck_2").click();
			Thread.sleep(1000);
		}
		getWebElement("AddNewProdcut_NextStep").click();
		explicitWaitForElementUntilClickable("AddNewProdcut_CAS_Number");
		getWebElement("AddNewProdcut_CAS_Number").sendKeys(casNumber);
		getWebElement("AddNewProdcut_Description").sendKeys(description);
		getWebElement("AddNewProdcut_AddtoCatalog").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// CLICK ON THE OK BUTTON IN CONFIRMATION MODAL
		getWebElement("Button_Ok").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// GET FINAL PRODUCT COUNT AFTER ADDING NEW PRODUCT TO LAB CATALOG
		String count3 = getWebElement("LabCatalog_ProductsCountPagination").getText();
		String count4 = count3.split(" ")[0];
		int finalCount = Integer.parseInt(count4);

		// LAB CATALOG VERIFICATION
		boolean bProductAdded = false;
		if ((finalCount - intialCount) == 1) {
			bProductAdded = true;
			Reporter.log("New Non Sigma-Aldrich Product Added to Catalog Successfully");
		} else {
			bProductAdded = false;
			Reporter.log("Adding New Non Sigma-Aldrich Product to Catalog Failed");
		}

		// UPDATE MATERIAL NAME TO TEST DATA SHEET
		if (bProductAdded) {
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			ExcelUtils.setCellData(productName, rowNumber, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			ExcelUtils.setCellData(catalogNumber, rowNumber, 1,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			if (vendorName == "" || (vendorName == null)) {
				ExcelUtils.setCellData(newVendorName, rowNumber, 2,
						Research_Constants.Path_TestData + Research_Constants.File_TestData,
						"ResearchMaterialsAndProducts");
			}
			ExcelUtils.setCellData(casNumber, rowNumber, 5,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
			ExcelUtils.setCellData(description, rowNumber, 7,
					Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"ResearchMaterialsAndProducts");
		}
	}

	public void AddNewMaterialToExperiment(String materialName, String catalogNumber, String expiryDate, String vendor,
			String CAS, String QtyAvailable, String QtyAvailableUnit, String LOT, String QtyRequired,
			String QtyRequiredUnit) throws Exception {

		// DECLARATION
		String newVendor = null;

		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// CLICK ON ADD MATERIAL TO EXPERIMENT
		getWebElement("Button_AddMaterialToExperiment").click();
		explicitWaitForElementUntilClickable("AddMaterialToExperiment_Name");

		// ENTER PRODUCT NAME
		getWebElement("AddMaterialToExperiment_Name").click();
		getWebElement("AddMaterialToExperiment_Name").sendKeys(materialName);

		// ENTER CATALOG NUMBER
		getWebElement("AddMaterialToExperiment_Catalog").click();
		getWebElement("AddMaterialToExperiment_Catalog").sendKeys(catalogNumber);

		// ENTER EXPIRY
		if (expiryDate == "" || (expiryDate == null)) {
			// SKIP EXPIRY
		} else {
			getWebElement("AddMaterialToExperiment_Expiry").click();
			getWebElement("AddMaterialToExperiment_Expiry").sendKeys(expiryDate);
		}

		// CHECK WHETHER THE VENDOR EXISTS OR NOT.
		Library SelectList = new Library();
		if (SelectList.VerifySelectList("AddMaterialToExperiment_Vendor", vendor) == true) {
			SelectList.SelectByVisibleText("AddMaterialToExperiment_Vendor", vendor);
		} else {
			// CREATING UNIQUE VENDOR NAME
			newVendor = "V" + uniqueNumber;

			// CREATE A NEW VENDOR
			SelectList.SelectByVisibleText("AddMaterialToExperiment_Vendor", "Add Vendor");
			getWebElement("AddMaterialToExperiment_OtherVendor").sendKeys(newVendor);
			getWebElement("NewVendorSaveCheck").click();
			Thread.sleep(1000);
		}

		// ENTER CAS NUMBER
		getWebElement("AddMaterialToExperiment_CAS").click();
		getWebElement("AddMaterialToExperiment_CAS").sendKeys(CAS);

		// ENTER QUANTITY AVAILABLE
		getWebElement("AddMaterialToExperiment_QtyAvailable").clear();
		getWebElement("AddMaterialToExperiment_QtyAvailable").click();
		getWebElement("AddMaterialToExperiment_QtyAvailable").sendKeys(QtyAvailable);

		// SELECT QUANTITY AVAILABLE UNIT
		if (SelectList.VerifySelectList("AddMaterialToExperiment_QtyAvailableUnit", QtyAvailableUnit) == true) {
			SelectList.SelectByVisibleText("AddMaterialToExperiment_QtyAvailableUnit", QtyAvailableUnit);
		} else {
			Assert.assertTrue("Quantity available unit not found", false);
		}

		// ENTER MATERIAL LOT
		if (LOT == "" || (LOT == null)) {
			// SKIP MATERIAL LOT
		} else {
			getWebElement("AddMaterialToExperiment_LOT").click();
			getWebElement("AddMaterialToExperiment_LOT").sendKeys(LOT);
		}

		// ENTER REQUIRED QUANTITY
		getWebElement("AddMaterialToExperiment_QtyRequired").clear();
		getWebElement("AddMaterialToExperiment_QtyRequired").click();
		getWebElement("AddMaterialToExperiment_QtyRequired").sendKeys(QtyRequired);

		// SELECT REQUIRED QUANTITY UNIT
		if (SelectList.VerifySelectList("AddMaterialToExperiment_QtyRequiredUnit", QtyRequiredUnit) == true) {
			SelectList.SelectByVisibleText("AddMaterialToExperiment_QtyRequiredUnit", QtyRequiredUnit);
			Thread.sleep(1000);
		} else {
			Assert.assertTrue("Quantity required unit not found", false);
		}

		// SAVE MATERIAL DETAILS
		getWebElement("Link_Add").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// REFRESH PAGE
		refreshPage();

		// VERIFY ADDED MATERIAL IN EXPERIMENT MATERIAL LIST SECTION
		boolean bVerifyMatName = false;
		boolean bVerifyMatCatalog = false;
		boolean bVerifyMatRecQty = false;
		boolean bVerifyMatRecQtyUnit = false;
		boolean bVerifyMatLot = false;
		List<WebElement> allActiveMaterialsInExperiment = driver
				.findElements(By.xpath("//span[@id='materialCard:materialPanel']//div[@class='panel panel-default']"));

		// GET ACTIVE MATERIAL INDEX
		int getActiveMaterialIndex = 0;
		for (int j = 0; j < allActiveMaterialsInExperiment.size(); j++) {
			String getInfo1 = driver.findElement(
					By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])[" + (j + 1)
							+ "]//div[@class='container-fluid filter-header']//p"))
					.getText().trim();
			String[] getInfo2 = getInfo1.split(" ");
			String getMaterialName = getInfo2[2].trim();

			if (getMaterialName.equals(materialName)) {
				bVerifyMatName = true;
				getActiveMaterialIndex = j + 1;
				break;
			}

		}

		// VERIFY MATERIAL DETAILS FROM EXPERIMENT MATERIALS SECTION
		if (bVerifyMatName) {
			String getInfo1 = driver
					.findElement(
							By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
									+ (getActiveMaterialIndex) + "]//div[@class='container-fluid filter-header']//p"))
					.getText().trim();
			String[] getInfo2 = getInfo1.split(" ");
			String getCatalogNumber = getInfo2[0].trim();
			String getMaterialRecievedQty = getInfo2[3].trim();
			String getMaterialRecievedQtyUnit = getInfo2[4].trim();

			// VERIFICATION OF MATERIAL CATALOG NUMBER AFTER ADDING
			if (getCatalogNumber.equals(catalogNumber)) {
				bVerifyMatCatalog = true;
				Assert.assertTrue("Material catalog details mismatch", bVerifyMatCatalog);
			}

			// VERIFICATION OF MATERIAL QTY AND UNIT AFTER ADDING
			if (getMaterialRecievedQty.contains(QtyRequired)) {
				bVerifyMatRecQty = true;
				Assert.assertTrue("Material recieved qty details mismatch", bVerifyMatRecQty);
			}

			// VERIFICATION OF MATERIAL QTY UNIT AFTER ADDING
			if (getMaterialRecievedQtyUnit.equals(QtyRequiredUnit)) {
				bVerifyMatRecQtyUnit = true;
				Assert.assertTrue("Material recieved qty unit details mismatch", bVerifyMatRecQtyUnit);
			}

			// VERIFICATION OF MATERIAL LOT AFTER ADDING
			if (LOT == "" || (LOT == null)) {
				// SKIP MATERIAL LOT VERIFICATION
			} else {
				String getMaterialLOT = driver
						.findElement(By
								.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
										+ (getActiveMaterialIndex) + "]//div[@class='form-group no-margin']//p//span"))
						.getAttribute("title");
				bVerifyMatLot = getMaterialLOT.equals(LOT);
				Assert.assertTrue("Material LOT details mismatch", bVerifyMatLot);
			}
		} else {
			Assert.assertTrue("Material not found in eperiment materials section", bVerifyMatName);
		}

		// VERIFICATION OF ADED MATERIAL IN INVENTORY
		Reporter.log("Navigate to Material list page");
		MaterialPageNavigation();

		Reporter.log("Verify added Material in Material list page");
		verifyMaterialInMaterialListPage(materialName);

		// UPDATE MATERIALS DETAILS TO DATA SHEET
		{
			int dataSheetrow;
			switch (vendor) {
			case "SIGMA-ALDRICH":
				dataSheetrow = 1;
				break;

			default:
				dataSheetrow = 2;
			}
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"AddExperimentMaterials");
			ExcelUtils.setCellData(materialName, dataSheetrow, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
			ExcelUtils.setCellData(catalogNumber, dataSheetrow, 1,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
			if (vendor == "" || (vendor == null)) {
				ExcelUtils.setCellData(newVendor, dataSheetrow, 3,
						Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
			} else {
				// SKIP UPDATING VENDOR NAME
			}
			ExcelUtils.setCellData(CAS, dataSheetrow, 4,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
			ExcelUtils.setCellData(LOT, dataSheetrow, 7,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
		}
	}

	public void AddMaterialToExperimentFromLab(String availableInInventory, String availableInCatalog,
			String materialOrProductName, String catalogNumber, String vendor, String expiryDate, String CAS,
			String QtyAvailable, String QtyAvailableUnit, String LOT, String QtyRequired, String QtyRequiredUnit,
			String editExpiry, String editLOT) throws Exception {

		// CLICK ON ADD MATERIAL TO EXPERIMENT
		getWebElement("Button_AddMaterialToExperiment").click();
		explicitWaitForElementUntilClickable("AddMaterialToExperiment_Name");

		// ENTER MATERIAL OR PRODUCT NAME
		getWebElement("AddMaterialToExperiment_Name").click();
		getWebElement("AddMaterialToExperiment_Name").sendKeys(materialOrProductName);
		Thread.sleep(10000);

		// SELECT THE MATERIAL FROM AUTO COMPLETE DROP DOWN
		if (availableInInventory.equals("YES")) {
			try {
				driver.findElement(By.xpath("//tr[@data-item-group='Inventory Materials']//td[contains(text(),'"
						+ materialOrProductName + "')]")).click();
				Reporter.log("Material loaded successfully in auto complete drop down");
			} catch (Exception e) {
				Assert.assertTrue("Material not found in inventory", false);
			}
		} else if (availableInCatalog.equals("YES")) {
			try {
				driver.findElement(By.xpath(
						"//tr[@data-item-group='Lab Catalog']//td[contains(text(),'" + materialOrProductName + "')]"))
						.click();
				Reporter.log("Product loaded successfully in auto complete drop down");
			} catch (Exception e) {
				Assert.assertTrue("Product not found in lab catalog", false);
			}
		}
		Thread.sleep(4000);

		// VERIFY AUTO LOADED MATERIAL DETAILS
		Reporter.log(
				"Verify the Material or product details in Add material modal after loading from auto complete drop down");
		{
			String getMaterialName = getWebElement("AddMaterialToExperiment_Name").getAttribute("value");
			Assert.assertTrue("Material or product name mismatch", getMaterialName.equals(materialOrProductName));

			String getMaterialCatalog = getWebElement("AddMaterialToExperiment_Catalog").getAttribute("value");
			Assert.assertTrue("Material or product catalog mismatch", getMaterialCatalog.equals(catalogNumber));

			if (expiryDate == "" || (expiryDate == null)) {
				// SKIP MATERIAL EXPIRY VERIFICATION
			} else {
				String getMaterialExpiry = getWebElement("AddMaterialToExperiment_Expiry").getAttribute("value");
				Assert.assertTrue("Material or product expiry mismatch", getMaterialExpiry.equals(expiryDate));
			}

			Select VendorDropDown = new Select(driver.findElement(By.xpath("//select[@id='addMaterialToExp:vendor']")));
			String getMaterialVendor = VendorDropDown.getFirstSelectedOption().getText().trim();
			Assert.assertTrue("Material or product vendor mismatch", getMaterialVendor.equals(vendor));

			if ((CAS == "") || (CAS == null) || (availableInCatalog.equals("YES"))) {
				// SKIP MATERIAL CAS VERIFICATION
			} else {
				String getMaterialCAS = getWebElement("AddMaterialToExperiment_CAS").getAttribute("value");
				Assert.assertTrue("Material or product CAS mismatch", getMaterialCAS.equals(CAS));
			}

			String getMaterialQtyAvailable = getWebElement("AddMaterialToExperiment_QtyAvailable")
					.getAttribute("value");
			Assert.assertTrue("Material or product qty available mismatch",
					getMaterialQtyAvailable.contains(QtyAvailable));

			Select QtyAvailableUnitDropDown = new Select(
					driver.findElement(By.xpath("//select[@id='addMaterialToExp:unit']")));
			String getMaterialQtyAvailableUnit = QtyAvailableUnitDropDown.getFirstSelectedOption().getText();
			Assert.assertTrue("Material or product qty available unit mismatch",
					getMaterialQtyAvailableUnit.equals(QtyAvailableUnit));

			if ((LOT == "") || (LOT == null) || (availableInCatalog.equals("YES"))) {
				// SKIP MATERIAL LOT VERIFICATION
			} else {
				String getMaterialLOT = getWebElement("AddMaterialToExperiment_LOT").getAttribute("value");
				Assert.assertTrue("Material or product LOT mismatch", getMaterialLOT.equals(LOT));
			}

			// VERIFICATION OF INFO MESSAGE ON ADDING LAB CATALOG PRODUCT
			if (availableInCatalog.equals("YES")) {
				Reporter.log("Verify Lab catalog product message info in add material to experiment modal");
				String getAddMaterialInfoMsg = getWebElement("AddMaterialInfoMsg").getText().trim();
				Assert.assertTrue("Lab catalog product alert info message mismatch", getAddMaterialInfoMsg.equals(
						"Info! This action will add this material to research and Inventory. Do you want to proceed?"));
			}

		}

		// EDIT EXPIRY DATE IF NECESSARY
		if (editExpiry.equals("YES")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 1); // Adding 1 days
			String newExpiryDate = sdf.format(c.getTime());

			getWebElement("AddMaterialToExperiment_Expiry").click();
			getWebElement("AddMaterialToExperiment_Expiry").clear();
			getWebElement("AddMaterialToExperiment_Expiry").sendKeys(newExpiryDate);
		}

		// EDIT LOT IF NECESSARY
		if ((editLOT != "") || (editLOT != null)) {
			getWebElement("AddMaterialToExperiment_LOT").click();
			getWebElement("AddMaterialToExperiment_LOT").clear();
			getWebElement("AddMaterialToExperiment_LOT").sendKeys(LOT);
		}

		// ENTER REQUIRED QUANTITY
		getWebElement("AddMaterialToExperiment_QtyRequired").clear();
		getWebElement("AddMaterialToExperiment_QtyRequired").click();
		getWebElement("AddMaterialToExperiment_QtyRequired").sendKeys(QtyRequired);

		// SELECT REQUIRED QUANTITY UNIT
		Library SelectList = new Library();
		if (SelectList.VerifySelectList("AddMaterialToExperiment_QtyRequiredUnit", QtyRequiredUnit) == true) {
			SelectList.SelectByVisibleText("AddMaterialToExperiment_QtyRequiredUnit", QtyRequiredUnit);
			Thread.sleep(1000);
		} else {
			Assert.assertTrue("Quantity required unit not found", false);
		}

		// SAVE MATERIAL DETAILS
		getWebElement("Link_Add").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// CLOSE MODAL
		try {
			getWebElement("AddMaterialToExperiment_CloseModal").click();
		} catch (Exception e) {
			refreshPage();
		}

		// REFRESH PAGE
		refreshPage();

		// VERIFY ADDED MATERIAL IN EXPERIMENT MATERIAL LIST SECTION
		Reporter.log("Verify material details in Experiment material section");
		boolean bVerifyMatName = false;
		boolean bVerifyMatCatalog = false;
		boolean bVerifyMatReqQty = false;
		boolean bVerifyMatReqQtyUnit = false;
		boolean bVerifyMatLot = false;
		List<WebElement> allActiveMaterialsInExperiment = driver
				.findElements(By.xpath("//span[@id='materialCard:materialPanel']//div[@class='panel panel-default']"));

		// GET ACTIVE MATERIAL INDEX
		int getActiveMaterialIndex = 0;
		for (int j = 0; j < allActiveMaterialsInExperiment.size(); j++) {
			String getInfo1 = driver.findElement(
					By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])[" + (j + 1)
							+ "]//div[@class='container-fluid filter-header']//p"))
					.getText().trim();
			String[] getInfo2 = getInfo1.split(" ");
			String getMaterialName = getInfo2[2].trim();

			if (getMaterialName.equals(materialOrProductName)) {
				bVerifyMatName = true;
				getActiveMaterialIndex = j + 1;
				break;
			}

		}

		// VERIFY MATERIAL DETAILS FROM EXPERIMENT MATERIALS SECTION
		if (bVerifyMatName) {
			String getInfo1 = driver
					.findElement(
							By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
									+ (getActiveMaterialIndex) + "]//div[@class='container-fluid filter-header']//p"))
					.getText().trim();
			String[] getInfo2 = getInfo1.split(" ");
			String getCatalogNumber = getInfo2[0].trim();
			String getMaterialRequiredQty = getInfo2[3].trim();
			String getMaterialRequiredQtyUnit = getInfo2[4].trim();

			// VERIFICATION OF MATERIAL CATALOG NUMBER AFTER ADDING
			if (getCatalogNumber.equals(catalogNumber)) {
				bVerifyMatCatalog = true;
				Assert.assertTrue("Material catalog details mismatch", bVerifyMatCatalog);
			}

			// VERIFICATION OF MATERIAL QTY AND UNIT AFTER ADDING
			if (getMaterialRequiredQty.contains(QtyRequired)) {
				bVerifyMatReqQty = true;
				Assert.assertTrue("Material recieved qty details mismatch", bVerifyMatReqQty);
			}

			// VERIFICATION OF MATERIAL QTY UNIT AFTER ADDING
			if (getMaterialRequiredQtyUnit.equals(QtyRequiredUnit)) {
				bVerifyMatReqQtyUnit = true;
				Assert.assertTrue("Material recieved qty unit details mismatch", bVerifyMatReqQtyUnit);
			}

			// VERIFICATION OF MATERIAL LOT AFTER ADDING
			if (((LOT == "") | (LOT == null)) & ((editLOT == "") | (editLOT == null))) {
				// SKIP MATERIAL LOT VERIFICATION
			} else {
				String getMaterialLOT = driver
						.findElement(By
								.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
										+ (getActiveMaterialIndex) + "]//div[@class='form-group no-margin']//p//span"))
						.getAttribute("title");
				bVerifyMatLot = getMaterialLOT.equals(LOT);
				Assert.assertTrue("Material LOT details mismatch", bVerifyMatLot);
			}

			// VERIFY MATERIAL IN INVENTORY IF LAB CATALOG PRODUCT IS ADDED INTO EXPERIMENT
			if (availableInCatalog.equals("YES")) {

				// NAVIGATE TO MATERIAL LIST
				MaterialPageNavigation();

				// SEARCH MATERIAL IN MATERIAL LIST
				Reporter.log(
						"Verify material added to inventory as a result of adding lab catalog product into experiment");
				verifyMaterialInMaterialListPage(materialOrProductName);
			}

		} else {
			Assert.assertTrue("Material not found in eperiment materials section", bVerifyMatName);
		}
	}

	public void AddMaterialToExperimentFromSigmaDataBase(String addFromDataBase, String expiryDate, String LOT,
			String QtyRequired) throws Exception {

		// DECLARATION
		int tryCount = 0;
		String catalogNumber = null;
		boolean bVerifyMatCatalog = false;
		boolean bVerifyMatLot = false;
		String actualCatalogNumber = null;

		// GET INITIAL MATERIALS COUNT
		int initialMaterialCount = driver
				.findElements(By.xpath(
						"//span[@id='materialCard:materialPanel']//div[starts-with(@class,'panel panel-default')]"))
				.size();

		// INITIALIZE RANDOM NUMBER
		Random randomGenerator = new Random();

		// LOOP
		for (tryCount = 1; tryCount <= 10; tryCount++) {
			catalogNumber = String.valueOf(randomGenerator.nextInt(99999));

			// CHECK FOR MAX TRY
			if (tryCount == 5) {
				Assert.assertTrue("Maximum tries exceeded", false);
			}

			// REFRESH PAGE
			refreshPage();

			// CLICK ON ADD MATERIAL TO EXPERIMENT
			getWebElement("Button_AddMaterialToExperiment").click();
			explicitWaitForElementUntilClickable("AddMaterialToExperiment_Name");

			// ENTER MATERIAL OR PRODUCT NAME
			getWebElement("AddMaterialToExperiment_Name").click();
			getWebElement("AddMaterialToExperiment_Name").sendKeys(catalogNumber);
			Thread.sleep(10000);

			// TRY TO SELECT FROM SIGMA ALDRICH DATA BASE
			try {
				List<WebElement> sigmaMaterial = driver.findElements(By
						.xpath("//tr[@data-item-group='Sigma-Aldrich']//td[contains(text(),'" + catalogNumber + "')]"));
				if (sigmaMaterial.size() > 0) {
					sigmaMaterial.get(0).click();
					Thread.sleep(3000);

					// GET ACTUAL CATALOG NUMBER LOADED
					actualCatalogNumber = getWebElement("AddMaterialToExperiment_Catalog").getAttribute("value").trim();

					// ENTER EXPIRY
					if (expiryDate == "" || (expiryDate == null)) {
						// SKIP EXPIRY
					} else {
						getWebElement("AddMaterialToExperiment_Expiry").click();
						getWebElement("AddMaterialToExperiment_Expiry").sendKeys(expiryDate);
					}

					// ENTER MATERIAL LOT
					if (LOT == "" || (LOT == null)) {
						// SKIP MATERIAL LOT
					} else {
						getWebElement("AddMaterialToExperiment_LOT").click();
						getWebElement("AddMaterialToExperiment_LOT").sendKeys(LOT);
					}

					// ENTER REQUIRED QUANTITY
					getWebElement("AddMaterialToExperiment_QtyRequired").clear();
					getWebElement("AddMaterialToExperiment_QtyRequired").click();
					getWebElement("AddMaterialToExperiment_QtyRequired").sendKeys(QtyRequired);

					// SELECT REQUIRED QUANTITY UNIT
					Library SelectList = new Library();
					SelectList.Select("AddMaterialToExperiment_QtyRequiredUnit", 1);

					// SAVE MATERIAL DETAILS
					getWebElement("Link_Add").click();
					explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

					// CHECK FOR DUPLICATE PRODUCT PRESENT IN CATALOG
					int getDuplicateErrorMsg = driver
							.findElements(By.xpath(
									"//div[@class='add-material-message in']//div[@class='alert alert-danger']//li[2]"))
							.size();
					if (getDuplicateErrorMsg > 0) {
						// CLOSE MODAL
						getWebElement("AddMaterialToExperiment_CloseModal").click();
					} else {
						// HEAD FOR VERIFICATION
						break;
					}
				}
			} catch (Exception E) {
				// CLOSE MODAL
				try {
					getWebElement("AddMaterialToExperiment_CloseModal").click();
				} catch (Exception e) {
					refreshPage();
				}
			}
		}

		// REFRESH PAGE
		refreshPage();

		// GET FINAL MATERIALS COUNT
		int finalMaterialCount = driver
				.findElements(By.xpath(
						"//span[@id='materialCard:materialPanel']//div[starts-with(@class,'panel panel-default')]"))
				.size();

		// MATERIAL DETAILS VERIFICATION IN EXPERIMENT MATERIAL SECTION
		List<WebElement> allActiveMaterialsInExperiment = driver
				.findElements(By.xpath("//span[@id='materialCard:materialPanel']//div[@class='panel panel-default']"));
		int lastActiveMaterialIndex = allActiveMaterialsInExperiment.size();
		String getInfo1 = driver
				.findElement(By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
						+ (lastActiveMaterialIndex) + "]//div[@class='container-fluid filter-header']//p"))
				.getText().trim();

		// VERIFY ADDED MATERIAL IN EXPERIMENT MATERIAL SECTION
		bVerifyMatCatalog = getInfo1.contains(actualCatalogNumber);
		if (LOT == "" || (LOT == null)) {
			// SKIP MATERIAL LOT VERIFICATION
		} else {
			String getMaterialLOT = driver
					.findElement(
							By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
									+ (lastActiveMaterialIndex) + "]//div[@class='form-group no-margin']//p//span"))
					.getAttribute("title");
			bVerifyMatLot = getMaterialLOT.equals(LOT);
		}

		if (LOT == "" || (LOT == null)) {
			Assert.assertTrue("Material details mismatch in Experiment material section",
					(bVerifyMatCatalog & (finalMaterialCount - initialMaterialCount == 1)));
		} else {
			Assert.assertTrue("Material details mismatch in Experiment material section",
					(bVerifyMatLot & bVerifyMatCatalog & (finalMaterialCount - initialMaterialCount == 1)));
		}
	}

	public void editMaterialFromExperiment(String materialName, String editExpiry, String editLOT,
			String editQtyRequired, String editQtyRequiredUnit) throws Exception {

		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// DECLARATION
		boolean bVerifyMatName = false;
		boolean bVerifyExpiry = false;
		boolean bVerifyMatReqQty = false;
		boolean bVerifyMatReqQtyUnit = false;
		boolean bVerifyMatLot = false;
		String getInfo1;
		String newExpiryDate = null;
		String newLOT = "LOT" + uniqueNumber;

		// GET ACTIVE MATERIAL INDEX
		List<WebElement> allActiveMaterialsInExperiment = driver
				.findElements(By.xpath("//span[@id='materialCard:materialPanel']//div[@class='panel panel-default']"));
		int ActiveMaterialIndex = 0;
		for (int j = 0; j < allActiveMaterialsInExperiment.size(); j++) {
			getInfo1 = driver.findElement(
					By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])[" + (j + 1)
							+ "]//div[@class='container-fluid filter-header']//p"))
					.getText().trim();
			String[] getInfo2 = getInfo1.split(" ");
			String getMaterialName = getInfo2[2].trim();

			if (getMaterialName.equals(materialName)) {
				bVerifyMatName = true;
				ActiveMaterialIndex = j + 1;
				break;
			}
		}

		// VERIFY MATERIAL DETAILS FROM EXPERIMENT MATERIALS SECTION
		if (bVerifyMatName) {
			// CLICK ON EDIT BUTTON
			driver.findElement(By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
					+ ActiveMaterialIndex + "]//i[@class='fa fa-pencil text-muted']")).click();
			Thread.sleep(4000);

			// EDIT EXPIRY
			if (editExpiry == "YES") {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Now use today date.
				c.add(Calendar.DATE, 1); // Adding 1 days
				newExpiryDate = sdf.format(c.getTime());
				getWebElement("EditMaterialInExperiment_Expiry").click();
				getWebElement("EditMaterialInExperiment_Expiry").clear();
				getWebElement("EditMaterialInExperiment_Expiry").sendKeys(newExpiryDate);
				getWebElement("EditMaterialInExperiment_LOT").click();
			} else {
				// SKIP EXPIRY
			}

			// EDIT MATERIAL LOT
			if (editLOT == "YES") {
				getWebElement("EditMaterialInExperiment_LOT").click();
				getWebElement("EditMaterialInExperiment_LOT").clear();
				getWebElement("EditMaterialInExperiment_LOT").sendKeys(newLOT);
			} else {
				// SKIP MATERIAL LOT
			}

			// EDIT MATERIAL RECIEVED QTY
			if (editQtyRequired != "" || editQtyRequired != null) {
				getWebElement("EditMaterialInExperiment_QtyRequired").clear();
				getWebElement("EditMaterialInExperiment_QtyRequired").click();
				getWebElement("EditMaterialInExperiment_QtyRequired").sendKeys(editQtyRequired);
			} else {
				// SKIP MATERIAL RECIEVED QTY
			}

			// EDIT REQUIRED QUANTITY UNIT
			Library SelectList = new Library();
			if (editQtyRequiredUnit != "") {
				if (SelectList.VerifySelectList("EditMaterialInExperiment_QtyRequiredUnit",
						editQtyRequiredUnit) == true) {
					SelectList.SelectByVisibleText("EditMaterialInExperiment_QtyRequiredUnit", editQtyRequiredUnit);
					Thread.sleep(1000);
				} else {
					Assert.assertTrue("Quantity required unit not found", false);
				}
			} else {
				// SKIP MATERIAL RECIEVED QTY UNIT
			}

			// UPDATE MATERISL
			getWebElement("Update_Link").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

			// VERIFY UPDATED MATERIAL DETAILS IN MODAL
			{
				if (editExpiry == "YES") {
					String getExpiry = getWebElement("EditMaterialInExperiment_Expiry").getAttribute("value").trim();
					bVerifyExpiry = getExpiry.equals(newExpiryDate);
					Assert.assertTrue("Edited expiry details mismatch", bVerifyExpiry);
				} else {
					// SKIP EXPIRY VERIFICATION
				}

				if (editLOT == "YES") {
					String getLOT = getWebElement("EditMaterialInExperiment_LOT").getAttribute("value").trim();
					bVerifyMatLot = getLOT.equals(newLOT);
					Assert.assertTrue("Edited LOT details mismatch", bVerifyMatLot);
				} else {
					// SKIP MATERIAL LOT VERIFICATION
				}

				if (editQtyRequired != "" || editQtyRequired != null) {
					String getQtyReq = getWebElement("EditMaterialInExperiment_QtyRequired").getAttribute("value")
							.trim();
					bVerifyMatReqQty = getQtyReq.equals(editQtyRequired);
					Assert.assertTrue("Edited qty required details mismatch", bVerifyMatReqQty);
				} else {
					// SKIP MATERIAL RECIEVED QTY VERIFICATION
				}

				if (editQtyRequiredUnit != "" || editQtyRequiredUnit != null) {
					String getQtyReqUnit = getWebElement("EditMaterialInExperiment_QtyRequiredUnit")
							.getAttribute("value").trim();
					bVerifyMatReqQtyUnit = getQtyReqUnit.equals(editQtyRequiredUnit);
					Assert.assertTrue("Edited qty required unit details mismatch", bVerifyMatReqQtyUnit);
				} else {
					// SKIP MATERIAL RECIEVED QTY UNIT VERIFICATION
				}
			}

			// CLOSE MODAL
			refreshPage();

			// UPDATE TO TEST DATA
			{
				ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
						"AddExperimentMaterials");
				if ((editExpiry == "YES") & (bVerifyExpiry == true)) {
					ExcelUtils.setCellData(newExpiryDate, 1, 2,
							Research_Constants.Path_TestData + Research_Constants.File_TestData,
							"AddExperimentMaterials");
				}

				if ((editLOT == "YES") & (bVerifyMatLot == true)) {
					ExcelUtils.setCellData(newLOT, 1, 7,
							Research_Constants.Path_TestData + Research_Constants.File_TestData,
							"AddExperimentMaterials");
				}

				if ((editQtyRequired != "") & (editQtyRequired != null) & (bVerifyMatReqQty == true)) {
					ExcelUtils.setCellData(editQtyRequired, 1, 8,
							Research_Constants.Path_TestData + Research_Constants.File_TestData,
							"AddExperimentMaterials");
				}

				if ((editQtyRequiredUnit != "") & (editQtyRequiredUnit != null) & (bVerifyMatReqQtyUnit == true)) {
					ExcelUtils.setCellData(editQtyRequiredUnit, 1, 9,
							Research_Constants.Path_TestData + Research_Constants.File_TestData,
							"AddExperimentMaterials");
				}
			}
		} else {
			Assert.assertTrue("Material not found in eperiment materials section", bVerifyMatName);
		}
	}

	public void deleteMaterialFromExperiment(String materialName) throws Exception {

		// DECLARATION
		boolean bVerifyMatName = false;
		boolean bVerifyMatNameAfter = false;
		int activeMaterialRow = 0;

		// GET ACTIVE MATERIAL INDEX
		List<WebElement> allActiveMaterialsInExperimentBefore = driver
				.findElements(By.xpath("//span[@id='materialCard:materialPanel']//div[@class='panel panel-default']"));

		// INITIAL COUNT OF ACTIVE MATERIALS
		int activeMaterialCountBefore = allActiveMaterialsInExperimentBefore.size();

		for (int j = 0; j < allActiveMaterialsInExperimentBefore.size(); j++) {
			String getInfo1 = driver.findElement(
					By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])[" + (j + 1)
							+ "]//div[@class='container-fluid filter-header']//p"))
					.getText().trim();
			String[] getInfo2 = getInfo1.split(" ");
			String getMaterialName = getInfo2[2].trim();
			if (getMaterialName.equals(materialName)) {
				bVerifyMatName = true;
				activeMaterialRow = j + 1;
				break;
			}
		}

		// DELETE MATERIAL IF EXISTS
		if (bVerifyMatName) {
			WebElement deleteMaterialIcon = driver.findElement(
					By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
							+ activeMaterialRow + "]//i[@class='fa fa-trash']"));
			deleteMaterialIcon.click();
			Thread.sleep(4000);

			// VALIDATION OF CONFIRMATION MODAL
			boolean bConfModal = getWebElement("DeleteMaterialFromExperiment_Conf_Modal").isDisplayed();
			Assert.assertTrue("Delete material confirmation modal does not appear", bConfModal);
			getWebElement("Button_YES").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

			List<WebElement> allActiveMaterialsInExperimentAfter = driver.findElements(
					By.xpath("//span[@id='materialCard:materialPanel']//div[@class='panel panel-default']"));

			int activeMaterialCountAfter = allActiveMaterialsInExperimentAfter.size();

			for (int j = 0; j < allActiveMaterialsInExperimentAfter.size(); j++) {
				String getInfo1 = driver
						.findElement(By
								.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
										+ (j + 1) + "]//div[@class='container-fluid filter-header']//p"))
						.getText().trim();
				String[] getInfo2 = getInfo1.split(" ");
				String getMaterialName = getInfo2[2].trim();

				if (getMaterialName.equals(materialName)) {
					bVerifyMatNameAfter = false;
					break;
				}
			}

			// VALIDATION AFTER DELETE STEP
			Assert.assertTrue("Material not deleted from eperiment materials section",
					((bVerifyMatNameAfter == false) & ((activeMaterialCountBefore - activeMaterialCountAfter) == 1)));
		} else {
			Assert.assertTrue("Material not found in eperiment materials section", bVerifyMatName);
		}
	}

	public boolean navigateToRequestPage() throws Exception {
		// RELOAD PAGE
		refreshPage();

		// NAVIGATION TO REQUEST PAGE
		Reporter.log("Navigate to Requests Page");
		getWebElement("SideBar_Inventory_EHS_Group2").click();
		explicitWaitForElementUntilClickable("SubMenu_Request");
		getWebElement("SubMenu_Request").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// VERIFY MATERIALS PAGE EXISTS OR NOT
		String materialsPageName = getWebElement("BreadCrumb_Heading").getText();
		Assert.assertTrue("Application not navigated to Requests page", materialsPageName.equals("Requests"));
		Utills.captureScreenshot("Requests_Page_NavigationPass_" + TodayDate.Date());
		return true;
	}

	public void requestMaterialFromExperiment(String projectName, String experimentName, String materialName,
			String requestQty) throws Exception {
		// DECLARATION
		boolean bVerifyMatName = false;
		int activeMaterialRow = 0;

		Reporter.log("Navigate to request page");
		navigateToRequestPage();

		// GET INITIAL COUNT OF REQUESTED PRODUCTS
		int initialRequestsCount = driver.findElements(By.xpath("//div[@class='portlet light bordered']")).size();

		Reporter.log("Navigate to project list");
		navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		navigateToProjectDetailPage(projectName);

		Reporter.log("Navigate to experiment detail page");
		navigateToExperimentDetailPage(experimentName);

		// GET ACTIVE MATERIAL INDEX
		List<WebElement> allActiveMaterialsInExperiment = driver
				.findElements(By.xpath("//span[@id='materialCard:materialPanel']//div[@class='panel panel-default']"));

		for (int j = 0; j < allActiveMaterialsInExperiment.size(); j++) {
			String getInfo1 = driver.findElement(
					By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])[" + (j + 1)
							+ "]//div[@class='container-fluid filter-header']//p"))
					.getText().trim();
			String[] getInfo2 = getInfo1.split(" ");
			String getMaterialName = getInfo2[2].trim();

			if (getMaterialName.equals(materialName)) {
				bVerifyMatName = true;
				activeMaterialRow = j + 1;
				break;
			}
		}

		// REQUEST MATERIAL IF EXISTS
		if (bVerifyMatName) {
			Reporter.log("Request material from experiment detail page");
			driver.findElement(By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
					+ activeMaterialRow + "]//a[@title='Request Item']//i")).click();
			explicitWaitForElementUntilClickable("Experiment_RequestCount");

			// ENTER REQUESTED QTY
			if (requestQty != "" || requestQty != null) {
				getWebElement("Experiment_RequestCount").click();
				getWebElement("Experiment_RequestCount").clear();
				getWebElement("Experiment_RequestCount").sendKeys(requestQty);
			}

			// REQUEST MATERIAL
			getWebElement("Button_Request").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

			// NAVIGATE TO REQUEST PAGE
			navigateToRequestPage();

			// GET FINAL COUNT OF REQUESTED PRODUCTS
			int finalRequestsCount = driver.findElements(By.xpath("//div[@class='portlet light bordered']")).size();

			// GET FIRST REQUESTED ITEM NAME
			String getRecentRequestItemName = driver
					.findElement(By.xpath("//div[@class='portlet light bordered'][1]//a")).getText().trim();

			// VALIDATION OF REQUEST EXPERIMENT MATERIAL
			Assert.assertTrue("Material request from experiment failed",
					((finalRequestsCount - initialRequestsCount == 1) & getRecentRequestItemName.equals(materialName)));
		}
	}

	public void refreshPage() throws Exception {
		driver.navigate().refresh();
		Thread.sleep(5000);
	}

	public void addMaterialToExperimentStep(String stepTitle, String materialName, String QtyRequired, String CAS,
			String LOT, String expiryDate, String stepMaterialReqQty, String stepMaterialReqQtyUnit) throws Exception {
		// DECLARATION
		int stepNo = 0;
		int totalNumberOfExistingMaterials = 0;
		boolean bVerifyMatName = false;
		boolean bVerifyMatReqQty = false;
		boolean bVerifyMatCAS = false;
		boolean bVerifyMatLOT = false;
		boolean bVerifyMatExpiry = false;
		int materialRowNo = 0;

		// GET STEP DETAILS
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));

		// GET STEP NUMBER
		for (int i = 0; i < allStepTitle.size(); i++) {
			String getStepTitle = allStepTitle.get(i).getText().trim();
			if (getStepTitle.equals(stepTitle)) {
				stepNo = i + 1;
				break;
			}
		}

		// CLICK ON ADD STEP MATERIAL LINK
		driver.findElement(By.xpath(
				"(//a[@data-target='#ExistingSuggested']//span[contains(text(),'Add Materials')])[" + stepNo + "]"))
				.click();
		Thread.sleep(3000);

		// TOTAL NUMBER OF SUGGESTED MATERIALS
		totalNumberOfExistingMaterials = driver
				.findElements(By.xpath("//div[@class='portlet light bordered' and @data-toggle='collapse']")).size();

		// VERIFY IF ADDED EXPERIMENT MATERIAL IS PRESENT IN SUGGESTED STEP MATERIALS
		for (int j = 1; j <= totalNumberOfExistingMaterials; j++) {

			String getSuggestedMatName = driver
					.findElement(By.xpath("(//div[@class='portlet light bordered'])[" + j + "]//p")).getText().trim();
			if (getSuggestedMatName.contains(materialName)) {
				bVerifyMatName = true;
				materialRowNo = j;

				String getSuggestedMatReqQty = driver
						.findElement(By.xpath("(//div[@class='portlet light bordered'])[" + j + "]//p//span")).getText()
						.trim();
				if (getSuggestedMatReqQty.contains(QtyRequired)) {
					bVerifyMatReqQty = true;
				} else {
					Assert.assertTrue("Material required qty mismatch in suggested list", bVerifyMatReqQty);
				}

				if (CAS != "" || CAS != null) {
					String getSuggestedMatCAS = driver
							.findElement(By.xpath("(//div[@class='portlet light bordered'])[" + j + "]//li[1]//span"))
							.getText().trim();
					if (getSuggestedMatCAS.equals(CAS)) {
						bVerifyMatCAS = true;
					} else {
						Assert.assertTrue("Material CAS mismatch in suggested list", bVerifyMatCAS);
					}
				}

				if (LOT != "" || LOT != null) {
					String getSuggestedMatLOT = driver
							.findElement(By.xpath("(//div[@class='portlet light bordered'])[" + j + "]//li[2]//span"))
							.getText().trim();
					if (getSuggestedMatLOT.equals(LOT)) {
						bVerifyMatLOT = true;
					} else {
						Assert.assertTrue("Material LOT mismatch in suggested list", bVerifyMatLOT);
					}
				}

				if (expiryDate != "" || expiryDate != null) {

					String getSuggestedMatExpiry = driver
							.findElement(By.xpath("(//div[@class='portlet light bordered'])[" + j + "]//li[3]//span"))
							.getText().trim();
					Date getSuggestedMatExpiryS = new SimpleDateFormat("dd MMM yyyy").parse(getSuggestedMatExpiry);
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM'/'dd'/'yyyy");
					String getSuggestedMatExpiryC = dateFormat.format(getSuggestedMatExpiryS).toString();

					if (getSuggestedMatExpiryC.equals(expiryDate)) {
						bVerifyMatExpiry = true;
					} else {
						Assert.assertTrue("Material expiry mismatch in suggested list", bVerifyMatExpiry);
					}
				}
				break;
			}
		}

		// SELECT MATERIAL AND ADD TO EXPERIMENT STEP
		if (bVerifyMatName) {
			driver.findElement(
					By.xpath("(//span[@id='existingMaterial:materiallist']//div[@class='portlet light bordered'])["
							+ materialRowNo + "]//p"))
					.click();
			Thread.sleep(2000);

			if (stepMaterialReqQty != "" || stepMaterialReqQty != null) {
				getWebElement("Step_MaterialReqQty").click();
				getWebElement("Step_MaterialReqQty").clear();
				getWebElement("Step_MaterialReqQty").sendKeys(stepMaterialReqQty);
			}

			if (stepMaterialReqQtyUnit != "" || stepMaterialReqQtyUnit != null) {
				Library SelectList = new Library();
				if (SelectList.VerifySelectList("Step_MaterialReqQtyUnit", stepMaterialReqQtyUnit) == true) {
					SelectList.SelectByVisibleText("Step_MaterialReqQtyUnit", stepMaterialReqQtyUnit);
					Thread.sleep(1000);
				} else {
					Assert.assertTrue("Step material Quantity required unit not found in drop down", false);
				}
			}

			// SAVE
			getWebElement("Link_Add").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

			// REFRESH PAGE
			refreshPage();
		} else {
			Assert.assertTrue("Experiment material not found in suggested list", bVerifyMatName);
		}

		// EXPAND STEP
		expandStep(stepTitle);

		// VERIFICATION OF ADDED MATERIAL IN STEP MATERIAL SECTION
		int getStepMaterial = driver
				.findElements(By.xpath("//div[@id='step-info" + (stepNo - 1) + "']//a[@title='" + materialName + "']"))
				.size();
		Assert.assertTrue("Material not added to experiment step", (getStepMaterial == 1));

		// UPDATE TEST DATA SHEET
		{
			if ((getStepMaterial == 1)) {
				ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
						"ExperimentStepMaterials");
				ExcelUtils.setCellData(stepTitle, 1, 2,
						Research_Constants.Path_TestData + Research_Constants.File_TestData, "ExperimentStepMaterials");
			}
		}
	}

	public void expandStep(String stepTitle) throws Exception {

		// REFRESH
		refreshPage();

		// DECLARATION
		int stepNo = 0;

		// GET STEP DETAILS
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));

		// GET STEP NUMBER
		for (int i = 0; i < allStepTitle.size(); i++) {
			String getStepTitle = allStepTitle.get(i).getText().trim();
			if (getStepTitle.equals(stepTitle)) {
				stepNo = i + 1;
				// GET STEP INTO THE SCREEN VIEW
				WebElement step = driver.findElement(
						By.xpath("//div[@class='step-content']//span[contains(text(),'" + stepTitle + "')]"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", step);
				Thread.sleep(1000);
				break;
			}
		}

		// EXPAND STEP
		WebElement expandLink = driver
				.findElement(By.xpath("//a[@id='moreNote" + (stepNo - 1) + "' and @data-toggle='collapse']"));
		expandLink.sendKeys(Keys.RETURN);
		Thread.sleep(2000);
	}

	public void updateStepMaterials(String stepTitle, String stepMaterialName, String stepMaterialReqQty,
			String stepMaterialReqQtyUnit, String updateStepMaterialReqQty, String updateStepMaterialReqQtyUnit)
			throws Exception {

		// DECLARATION
		int stepNo = 0;

		// GET STEP DETAILS
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));

		// GET STEP NUMBER
		for (int i = 0; i < allStepTitle.size(); i++) {
			String getStepTitle = allStepTitle.get(i).getText().trim();
			if (getStepTitle.equals(stepTitle)) {
				stepNo = i + 1;
				break;
			}
		}

		// EXPAND STEP
		expandStep(stepTitle);

		// VERIFICATION OF STEP MATERIAL
		int getStepMaterial = driver
				.findElements(
						By.xpath("//div[@id='step-info" + (stepNo - 1) + "']//a[@title='" + stepMaterialName + "']"))
				.size();
		Assert.assertTrue("Material not present in experiment step", (getStepMaterial == 1));

		// CLICK ON EDIT STEP MATERIAL
		driver.findElement(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle'][" + stepNo + "]//i[@class='fa fa-pencil']"))
				.click();
		explicitWaitForElementUntilClickable("EditStepMaterial_ReqQty");

		// VERIFY STEP MATERIAL QTY AND UNIT DETAILS
		String getReqQty = getWebElement("EditStepMaterial_ReqQty").getAttribute("value").trim();
		Assert.assertTrue("Step material requested qty mismatch", getReqQty.contains(stepMaterialReqQty));

		Select reqQtyUnitDropDown = new Select(
				driver.findElement(By.xpath("//select[@id='editStepMaterialForm:requestedUnit']")));
		String getReqQtyUnit = reqQtyUnitDropDown.getFirstSelectedOption().getText().trim();
		Assert.assertTrue("Step material requested qty unit mismatch", getReqQtyUnit.equals(stepMaterialReqQtyUnit));

		// EDIT STEP MATERIAL DETAILS
		if (updateStepMaterialReqQty != "" || updateStepMaterialReqQty != null) {
			getWebElement("EditStepMaterial_ReqQty").click();
			getWebElement("EditStepMaterial_ReqQty").clear();
			getWebElement("EditStepMaterial_ReqQty").sendKeys(updateStepMaterialReqQty);
		}

		if (updateStepMaterialReqQtyUnit != "" || updateStepMaterialReqQtyUnit != null) {
			Library SelectList = new Library();
			if (SelectList.VerifySelectList("EditStepMaterial_ReqQtyUnit", updateStepMaterialReqQtyUnit) == true) {
				SelectList.SelectByVisibleText("EditStepMaterial_ReqQtyUnit", updateStepMaterialReqQtyUnit);
				Thread.sleep(1000);
			} else {
				Assert.assertTrue("Step material Quantity required unit not found in drop down", false);
			}
		}

		getWebElement("Update_StepMaterial").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// EXPAND STEP
		expandStep(stepTitle);

		// VERIFY IF REQUESTED STEP MATERIAL QTY AND UNIT UPDATED OR NOT
		{
			String getUpdatedReqQtyAndUnit = driver
					.findElement(By.xpath("//div[@class='step-accordian-box ui-sortable-handle'][" + stepNo
							+ "]//ul[@class='list-inline row']//li[2]//i//span[1]"))
					.getText().trim();
			if (updateStepMaterialReqQty != "" || updateStepMaterialReqQty != null) {
				Assert.assertTrue("Step material Quantity required qty not updated",
						getUpdatedReqQtyAndUnit.contains(updateStepMaterialReqQty));
			}
			if (updateStepMaterialReqQtyUnit != "" || updateStepMaterialReqQtyUnit != null) {
				Assert.assertTrue("Step material Quantity required qty unit not updated",
						getUpdatedReqQtyAndUnit.contains(updateStepMaterialReqQtyUnit));
			}
		}
	}

	public int getStepPositionNumber(String stepTitle) throws Exception {
		int stepNo = 0;

		// GET STEP DETAILS
		List<WebElement> allStepTitle = driver.findElements(By.xpath(
				"//div[@class='step-accordian-box ui-sortable-handle']//div[@class='step-content']//span[@data-title='Enter Step Name']"));

		// GET STEP NUMBER
		for (int i = 0; i < allStepTitle.size(); i++) {
			String getStepTitle = allStepTitle.get(i).getText().trim();
			if (getStepTitle.equals(stepTitle)) {
				stepNo = i + 1;
				break;
			}
		}

		// RETURN VALUE
		if (stepNo == 0) {
			Assert.assertTrue("Step does not exists", false);
		}

		return stepNo;
	}

	public int getStepNotePositionNumber(String stepTitle, String stepNoteTitle) throws Exception {
		// DECLARATION
		String getStepTitle;
		int stepNo, numberOfStepNotes, i, stepNoteIndex = 0;

		// GET STEP POSITION NUMBER
		stepNo = getStepPositionNumber(stepTitle);

		// GET ALL STEP NOTES TITLES IN STEP
		List<WebElement> stepNotesInStep = driver
				.findElements(By.xpath("//div[@class='step-accordian-box ui-sortable-handle'][" + stepNo
						+ "]//div[@class='notesOutput step-note-new-wrapper']//ul[@class='list-inline']//li[1]//b"));
		numberOfStepNotes = stepNotesInStep.size();
		for (i = 0; i < numberOfStepNotes; i++) {
			getStepTitle = stepNotesInStep.get(i).getText().trim();
			if (getStepTitle.equals(stepNoteTitle)) {
				stepNoteIndex = i + 1;
				break;
			}
		}

		return stepNoteIndex;
	}

	public int getStepMaterialPositionNumber(String stepTitle, String stepMaterialName) throws Exception {

		// DECLARATION
		int stepNo = 0;
		int stepMaterialIndex = 0;

		// GET STEP NUMBER
		stepNo = getStepPositionNumber(stepTitle);

		// EXPAND STEP
		expandStep(stepTitle);

		// GET STEP MATERIAL DETAILS
		List<WebElement> getAllStepMaterials = driver.findElements(By.xpath("//div[@id='step-info" + (stepNo - 1)
				+ "']//a[@title='Delete Material']/../..//a[@href='javascript:void(0)']"));
		for (int i = 0; i < getAllStepMaterials.size(); i++) {
			String materialName = getAllStepMaterials.get(i).getText().trim();
			if (materialName.equals(stepMaterialName)) {
				stepMaterialIndex = i + 1;
				break;
			}
		}

		// VERIFICATION
		if (stepMaterialIndex <= 0) {
			Assert.assertTrue("Step material " + stepMaterialName + " do not exist in step " + stepTitle, false);
		}
		return stepMaterialIndex;
	}

	public void deleteStepMaterials(String stepTitle, String stepMaterialName) throws Exception {
		// DECLARATION
		int stepNo = 0;
		int stepMaterialNo = 0;

		// GET STEP NUMBER
		stepNo = getStepPositionNumber(stepTitle);

		// GET STEP MATERIAL NUMBER
		stepMaterialNo = getStepMaterialPositionNumber(stepTitle, stepMaterialName);

		// DELETE STEP MATERIAL
		if (!(stepMaterialNo <= 0)) {
			List<WebElement> stepMaterialsDelete = driver.findElements(By.xpath("//div[@id='step-info" + (stepNo - 1)
					+ "']//a[@title='Delete Material']//i[@class='fa fa-trash']"));
			stepMaterialsDelete.get(stepMaterialNo - 1).click();
			explicitWaitForElementUntilClickable("Button_YES");
			getWebElement("Button_YES").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");
		}

		// EXPAND STEP
		expandStep(stepTitle);

		// GET ALL STEP MATERIALS
		List<WebElement> getAllStepMaterials = driver.findElements(By.xpath("//div[@id='step-info" + (stepNo - 1)
				+ "']//a[@title='Delete Material']/../..//a[@href='javascript:void(0)']"));

		// VERIFY IF STEP MATERIAL IS DELETED OR NOT
		for (int i = 0; i < getAllStepMaterials.size(); i++) {
			String getStepMaterialName = getAllStepMaterials.get(i).getText().trim();
			if (getStepMaterialName.equals(stepMaterialName)) {
				Assert.assertTrue("Step material " + stepMaterialName + " delete failed", false);
			}
		}
	}

	public void addNewProductToProtocolTemplate(String productName, String catalog, String vendor, String qtyAdded,
			String qtyAddedUnit, String CAS, String qtyRequired, String qtyRequiredUnit) throws Exception {

		// DECLARATION
		String newVendor = null;
		int dataSheetrow = 0;

		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// CLICK ON ADD PRODUCT
		getWebElement("Button_AddProductToProtocol").click();
		explicitWaitForElementUntilClickable("AddProductToTemplate_Name");

		// ENTER PRODUCT NAME
		getWebElement("AddProductToTemplate_Name").click();
		getWebElement("AddProductToTemplate_Name").sendKeys(productName);

		// SELECT VENDOR
		Library SelectList = new Library();
		if (SelectList.VerifySelectList("AddProductToTemplate_Vendor", vendor) == true) {
			SelectList.SelectByVisibleText("AddProductToTemplate_Vendor", vendor);
			Thread.sleep(1000);
		} else {
			// CREATING UNIQUE VENDOR NAME
			newVendor = "V" + uniqueNumber;

			// CREATE A NEW VENDOR
			SelectList.SelectByVisibleText("AddProductToTemplate_Vendor", "Add Vendor");
			Thread.sleep(1000);
			getWebElement("AddProductToTemplate_OtherVendor").sendKeys(newVendor);
			Thread.sleep(1000);
			getWebElement("NewVendorSaveCheck").click();
			Thread.sleep(1000);
		}

		// ENTER CATALOG NUMBER
		getWebElement("AddProductToTemplate_Catalog").click();
		getWebElement("AddProductToTemplate_Catalog").sendKeys(catalog);

		// ENTER PRODUCT QUANTITY
		getWebElement("AddProductToTemplate_ProdQty").click();
		getWebElement("AddProductToTemplate_ProdQty").clear();
		getWebElement("AddProductToTemplate_ProdQty").sendKeys(qtyAdded);

		// SELECT PRODUCT QUANTITY UNIT
		getWebElement("AddProductToTemplate_ProdQtyUnit").click();
		if (SelectList.VerifySelectList("AddProductToTemplate_ProdQtyUnit", qtyAddedUnit) == true) {
			SelectList.SelectByVisibleText("AddProductToTemplate_ProdQtyUnit", qtyAddedUnit);
			Thread.sleep(1000);
		} else {
			Assert.assertTrue("Quantity added unit not found", false);
		}

		// ENTER PRODUCT CAS
		if (CAS == "" || (CAS == null)) {
			// SKIP MATERIAL CAS
		} else {
			getWebElement("AddProductToTemplate_CAS").click();
			getWebElement("AddProductToTemplate_CAS").sendKeys(CAS);
		}

		// ENTER PRODUCT REQUIRED QUANTITY
		getWebElement("AddProductToTemplate_ProdReqQty").click();
		getWebElement("AddProductToTemplate_ProdReqQty").clear();
		getWebElement("AddProductToTemplate_ProdReqQty").sendKeys(qtyRequired);

		// SELECT PRODUCT REQUIRED QUANTITY UNIT
		if (SelectList.VerifySelectList("AddProductToTemplate_ProdReqQtyUnit", qtyRequiredUnit) == true) {
			SelectList.SelectByVisibleText("AddProductToTemplate_ProdReqQtyUnit", qtyRequiredUnit);
			Thread.sleep(1000);
		} else {
			Assert.assertTrue("Quantity required unit not found", false);
		}

		// SAVE PRODUCT DETAILS
		getWebElement("Link_Add").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// REFRESH
		refreshPage();

		boolean bVerifyProductCatalog = false;
		boolean bVerifyProductCAS = false;
		boolean bVerifyProductReQQty = false;
		boolean bVerifyProductReqQtyUnit = false;
		String getProductName = null;
		String getProductReqQty = null;
		String getProductReqQtyUnit = null;
		String getProductCatalog = null;
		int productRow = 0;

		// GET ALL PRODUCTS IN TEMPLATE
		List<WebElement> Products = driver
				.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']"));
		for (int i = 0; i < Products.size(); i++) {
			getProductName = Products.get(i).getText().trim();
			if (getProductName.contains(productName)) {
				productRow = i + 1;
				break;
			}
		}

		// VERIFY PRODUCT DETAILS
		if (productRow > 0) {
			List<WebElement> ProductsReqQtyAndUnit = driver.findElements(
					By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']//span"));
			getProductReqQty = ProductsReqQtyAndUnit.get(productRow - 1).getText().trim().split(" ")[0];
			getProductReqQtyUnit = ProductsReqQtyAndUnit.get(productRow - 1).getText().trim().split(" ")[1];
			bVerifyProductReQQty = getProductReqQty.contains(qtyRequired);
			bVerifyProductReqQtyUnit = getProductReqQtyUnit.equals(qtyRequiredUnit);
			Assert.assertTrue("Product required qty do not match in template", bVerifyProductReQQty);
			Assert.assertTrue("Product required qty unit do not match in template", bVerifyProductReqQtyUnit);

			List<WebElement> ProductsCatalog = driver.findElements(By.xpath(
					"//div[@class='panel panel-default']//label[contains(text(),'Catalog#')]/following-sibling::p"));
			getProductCatalog = ProductsCatalog.get(productRow - 1).getText().trim();
			bVerifyProductCatalog = getProductCatalog.equals(catalog);
			Assert.assertTrue("Product catalog do not match in template", bVerifyProductCatalog);

			List<WebElement> ProductsCAS = driver.findElements(By.xpath(
					"//div[@class='dropdown-menu pull-right cart-notification-wrapper materials-notification-container']//label[contains(text(),'CAS #')]"));
			ProductsCAS.get(productRow - 1).getText().trim();
			int CASMatch = driver
					.findElements(By.xpath(
							"//label[contains(text(),'CAS #')]/following-sibling::p[contains(text(),'" + CAS + "')]"))
					.size();
			bVerifyProductCAS = (CASMatch == 1);
			Assert.assertTrue("Product CAS do not match in template", bVerifyProductCAS);
		} else {
			Assert.assertTrue("Product do not exists in template", false);
		}

		// UPDATE TO TEST DATA SHEET
		{
			switch (vendor) {
			case "SIGMA-ALDRICH":
				dataSheetrow = 1;
				break;

			default:
				dataSheetrow = 2;
			}
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"AddTemplateProducts");
			ExcelUtils.setCellData(productName, dataSheetrow, 0,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddTemplateProducts");
			if (newVendor != "" || newVendor != null) {
				ExcelUtils.setCellData(vendor, dataSheetrow, 1,
						Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddTemplateProducts");
			}
			ExcelUtils.setCellData(catalog, dataSheetrow, 2,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddTemplateProducts");
			ExcelUtils.setCellData(CAS, dataSheetrow, 5,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddTemplateProducts");
		}
	}

	public void addProductToTemplateFromSigmaDataBase(String addFromDataBase, String qtyAdded, String qtyAddedUnit,
			String qtyRequired, String qtyRequiredUnit) throws Exception {

		// DECLARATION
		int tryCount = 0;
		String catalogNumber = null;
		boolean bVerifyProductCatalog = false;
		boolean bVerifyProductName = false;
		String actualCatalogNumber = null;
		String actualProductName = null;

		// GET INITIAL MATERIALS COUNT
		int initialProductCount = driver
				.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']")).size();

		// INITIALIZE RANDOM NUMBER
		Random randomGenerator = new Random();

		// LOOP
		for (tryCount = 1; tryCount <= 10; tryCount++) {
			catalogNumber = String.valueOf(randomGenerator.nextInt(99999));

			// CHECK FOR MAX TRY
			if (tryCount == 10) {
				Assert.assertTrue("Maximum tries exceeded", false);
			}

			// REFRESH PAGE
			refreshPage();

			// CLICK ON ADD PRODUCT
			getWebElement("Button_AddProductToProtocol").click();
			explicitWaitForElementUntilClickable("AddProductToTemplate_Name");

			// ENTER PRODUCT NAME
			getWebElement("AddProductToTemplate_Name").click();
			getWebElement("AddProductToTemplate_Name").sendKeys(catalogNumber);
			Thread.sleep(10000);

			// TRY TO SELECT FROM SIGMA ALDRICH DATA BASE
			try {
				List<WebElement> sigmaMaterial = driver.findElements(By
						.xpath("//tr[@data-item-group='Sigma-Aldrich']//td[contains(text(),'" + catalogNumber + "')]"));
				if (sigmaMaterial.size() > 0) {
					sigmaMaterial.get(0).click();
					Thread.sleep(6000);

					// GET ACTUAL PRODUCT AND CATALOG NUMBER LOADED
					actualCatalogNumber = getWebElement("AddProductToTemplate_Catalog").getAttribute("value").trim();
					actualProductName = getWebElement("AddProductToTemplate_Name").getAttribute("value").trim();

					// ENTER PRODUCT QUANTITY
					if (qtyAdded != "" || qtyAdded != null) {
						getWebElement("AddProductToTemplate_ProdQty").click();
						getWebElement("AddProductToTemplate_ProdQty").clear();
						getWebElement("AddProductToTemplate_ProdQty").sendKeys(qtyAdded);
					}

					// SELECT PRODUCT QUANTITY UNIT
					Library SelectList = new Library();
					if (qtyAddedUnit != "" || qtyAddedUnit != null) {
						getWebElement("AddProductToTemplate_ProdQtyUnit").click();
						if (SelectList.VerifySelectList("AddProductToTemplate_ProdQtyUnit", qtyAddedUnit) == true) {
							SelectList.SelectByVisibleText("AddProductToTemplate_ProdQtyUnit", qtyAddedUnit);
							Thread.sleep(1000);
						} else {
							Assert.assertTrue("Quantity added unit not found", false);
						}
					}

					// ENTER PRODUCT REQUIRED QUANTITY
					getWebElement("AddProductToTemplate_ProdReqQty").click();
					getWebElement("AddProductToTemplate_ProdReqQty").clear();
					getWebElement("AddProductToTemplate_ProdReqQty").sendKeys(qtyRequired);

					// SELECT PRODUCT REQUIRED QUANTITY UNIT
					if (SelectList.VerifySelectList("AddProductToTemplate_ProdReqQtyUnit", qtyRequiredUnit) == true) {
						SelectList.SelectByVisibleText("AddProductToTemplate_ProdReqQtyUnit", qtyRequiredUnit);
						Thread.sleep(1000);
					} else {
						Assert.assertTrue("Quantity required unit not found", false);
					}

					// SAVE PRODUCT DETAILS
					getWebElement("Link_Add").click();
					explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

					// REFRESH
					refreshPage();

					// CHECK FOR DUPLICATE PRODUCT PRESENT IN CATALOG
					int getDuplicateErrorMsg = driver
							.findElements(By.xpath("//div[@class='alert alert-danger']//li[2]")).size();
					if (getDuplicateErrorMsg > 0) {
						// REFRESH
						refreshPage();
					} else {
						// HEAD FOR VERIFICATION
						break;
					}
				}
			} catch (Exception E) {
				// REFRESH
				refreshPage();
			}
		}

		// REFRESH PAGE
		refreshPage();

		// GET FINAL MATERIALS COUNT
		int finalProductCount = driver
				.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']")).size();

		String getProductName = null;
		String getProductCatalog = null;

		// GET ALL PRODUCTS IN TEMPLATE
		List<WebElement> Products = driver
				.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']"));
		List<WebElement> ProductsCatalog = driver.findElements(By
				.xpath("//div[@class='panel panel-default']//label[contains(text(),'Catalog#')]/following-sibling::p"));
		for (int i = 0; i < Products.size(); i++) {
			getProductName = Products.get(i).getText().trim();
			getProductCatalog = ProductsCatalog.get(i).getText().trim();
			if (getProductName.contains(actualProductName) & getProductCatalog.equals(actualCatalogNumber)) {
				bVerifyProductName = true;
				bVerifyProductCatalog = true;
				break;
			}
		}

		// VERIFICATION
		Assert.assertTrue("Sigma Product from data base did not add to template",
				(bVerifyProductName & bVerifyProductCatalog & ((finalProductCount - initialProductCount) == 1)));
	}

	public void verifyProductAddedToTemplate(String productName, String catalogNumber, String CAS, String qtyRequired,
			String qtyRequiredUnit) throws Exception {

		// DECLARATION
		boolean bVerifyProductName = false;
		boolean bVerifyProductCatalog = false;
		boolean bVerifyProductReQQty = false;
		boolean bVerifyProductReqQtyUnit = false;
		String getProductName = null;
		String getProductReqQty = null;
		String getProductReqQtyUnit = null;
		String getProductCatalog = null;
		int productRow = 0;

		// GET ALL PRODUCTS IN TEMPLATE
		List<WebElement> Products = driver
				.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']"));
		for (int i = 0; i < Products.size(); i++) {
			getProductName = Products.get(i).getText().trim();
			if (getProductName.contains(productName)) {
				productRow = i + 1;
				break;
			}
		}

		// VERIFY PRODUCT DETAILS
		if (productRow > 0) {
			List<WebElement> ProductsReqQtyAndUnit = driver.findElements(
					By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']//span"));
			getProductReqQty = ProductsReqQtyAndUnit.get(productRow - 1).getText().trim().split(" ")[0];
			getProductReqQtyUnit = ProductsReqQtyAndUnit.get(productRow - 1).getText().trim().split(" ")[1];
			bVerifyProductReQQty = getProductReqQty.contains(qtyRequired);
			bVerifyProductReqQtyUnit = getProductReqQtyUnit.equals(qtyRequiredUnit);
			Assert.assertTrue("Product required qty do not match in template", bVerifyProductReQQty);
			Assert.assertTrue("Product required qty unit do not match in template", bVerifyProductReqQtyUnit);

			List<WebElement> ProductsCatalog = driver.findElements(By.xpath(
					"//div[@class='panel panel-default']//label[contains(text(),'Catalog#')]/following-sibling::p"));
			getProductCatalog = ProductsCatalog.get(productRow - 1).getText().trim();
			bVerifyProductCatalog = getProductCatalog.equals(catalogNumber);
			Assert.assertTrue("Product catalog do not match in template", bVerifyProductCatalog);
		} else {
			Assert.assertTrue("Product do not exists in template", bVerifyProductName);
		}
	}

	public void AddProductToTemplateFromLab(String productName, String catalogNumber, String vendor, String CAS,
			String qtyAdded, String qtyAddedUnit, String qtyRequired, String qtyRequiredUnit) throws Exception {

		// DECLARATION
		String getProductName, getCatalogNumber, getProdAvQty, getProdAvQtyUnit, getProdCAS;

		// CLICK ON ADD PRODUCT
		getWebElement("Button_AddProductToProtocol").click();
		explicitWaitForElementUntilClickable("AddProductToTemplate_Name");

		// ENTER PRODUCT NAME
		getWebElement("AddProductToTemplate_Name").click();
		getWebElement("AddProductToTemplate_Name").sendKeys(productName);
		Thread.sleep(10000);

		// TRY TO SELECT FROM LAB CATALOG
		try {
			List<WebElement> sigmaProduct = driver.findElements(
					By.xpath("//tr[@data-item-group='Lab Catalog']//td[contains(text(),'" + productName + "')]"));
			if (sigmaProduct.size() > 0) {
				sigmaProduct.get(0).click();
				Thread.sleep(6000);
			}
		} catch (Exception e) {
			Assert.assertTrue("Product not found in catalog", false);
		}
		Thread.sleep(8000);

		// VERIFY AUTO LOADED PRODUCT DETAILS
		Reporter.log("Verify the product details in Add product modal after loading from auto complete drop down");
		{
			getProductName = getWebElement("AddProductToTemplate_Name").getAttribute("value").trim();
			Assert.assertTrue("Product name mismatch after loading", getProductName.equals(productName));

			getCatalogNumber = getWebElement("AddProductToTemplate_Catalog").getAttribute("value").trim();
			Assert.assertTrue("Product catalog mismatch after loading", getCatalogNumber.equals(catalogNumber));

			getProdAvQty = getWebElement("AddProductToTemplate_ProdQty").getAttribute("value").trim();
			Assert.assertTrue("Product added qty mismatch after loading", getProdAvQty.contains(qtyAdded));

			Select QtyAddedUnitDropDown = new Select(
					driver.findElement(By.xpath("//select[@id='addMaterialToTemplate:unit']")));
			getProdAvQtyUnit = QtyAddedUnitDropDown.getFirstSelectedOption().getText().trim();
			Assert.assertTrue("Product added qty unit mismatch after loading", getProdAvQtyUnit.equals(qtyAddedUnit));

			if (CAS != "" || (CAS != null)) {
				getProdCAS = getWebElement("AddProductToTemplate_CAS").getAttribute("value").trim();
				Assert.assertTrue("Product CAS mismatch after loading", getProdCAS.equals(CAS));
			}
		}

		// ENTER PRODUCT REQUIRED QTY
		getWebElement("AddProductToTemplate_ProdReqQty").click();
		getWebElement("AddProductToTemplate_ProdReqQty").clear();
		getWebElement("AddProductToTemplate_ProdReqQty").sendKeys(qtyRequired);

		// SELECT PRODUCT REQUIRED QUANTITY UNIT
		Library SelectList = new Library();
		if (SelectList.VerifySelectList("AddProductToTemplate_ProdReqQtyUnit", qtyRequiredUnit) == true) {
			SelectList.SelectByVisibleText("AddProductToTemplate_ProdReqQtyUnit", qtyRequiredUnit);
			Thread.sleep(1000);
		} else {
			Assert.assertTrue("Quantity required unit not found", false);
		}

		// SAVE PRODUCT DETAILS
		getWebElement("Link_Add").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// REFRESH
		refreshPage();

		// VERIFY PRODUCT DETAILS IN ADD PRODUCT SECTION IN DETAIL PAGE
		verifyProductAddedToTemplate(productName, catalogNumber, CAS, qtyRequired, qtyRequiredUnit);
	}

	public void requestProductAddedToTemplate(String protocolName, String productName, String requestQty)
			throws Exception {

		Reporter.log("Navigate to request page");
		navigateToRequestPage();

		String getProductName;
		int productRow = 0;
		int initialRequestsCount = 0;

		// GET INITIAL COUNT OF REQUESTED PRODUCTS
		initialRequestsCount = driver.findElements(By.xpath("//div[@class='portlet light bordered']")).size();

		Reporter.log("Navigate to protocol template list page");
		navigateToProtocolTemplateList();

		Reporter.log("Navigate to protocol detail page");
		navigateToProtocolTemplateDetailPage(protocolName);

		// GET ALL PRODUCTS IN TEMPLATE
		List<WebElement> Products = driver
				.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']"));
		for (int i = 0; i < Products.size(); i++) {
			getProductName = Products.get(i).getText().trim();
			if (getProductName.contains(productName)) {
				productRow = i + 1;
				break;
			}
		}

		// REQUEST PRODUCT
		if (productRow > 0) {
			Reporter.log("Request product from protocol detail page");
			driver.findElement(By.xpath("(//span[@id='materialCard:materialPanel']//div[@class='panel panel-default'])["
					+ productRow + "]//a[@title='Request Item']")).click();
			explicitWaitForElementUntilClickable("Button_Request");

			// ENTER REQUESTED QTY
			if (requestQty != "" || requestQty != null) {
				getWebElement("Experiment_RequestCount").click();
				getWebElement("Experiment_RequestCount").clear();
				getWebElement("Experiment_RequestCount").sendKeys(requestQty);
			}

			// REQUEST MATERIAL
			getWebElement("Button_Request").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

			// NAVIGATE TO REQUEST PAGE
			navigateToRequestPage();

			// GET FINAL COUNT OF REQUESTED PRODUCTS
			int finalRequestsCount = driver.findElements(By.xpath("//div[@class='portlet light bordered']")).size();

			// GET FIRST REQUESTED ITEM NAME
			String getRecentRequestItemName = driver
					.findElement(By.xpath("//div[@class='portlet light bordered'][1]//a")).getText().trim();

			// VALIDATION OF REQUESTED PRODUCT
			Assert.assertTrue("Product request from template failed",
					((finalRequestsCount - initialRequestsCount == 1) & getRecentRequestItemName.equals(productName)));
		}
	}

	public void editProductAddedToTemplate(String productName, String editProductReqQty, String editProductReqQtyUnit)
			throws Exception {

		// DECLARATION
		boolean bVerifyProductName = false;
		String getProductName = null;
		String getProductReqQty = null;
		String getProductReqQtyUnit = null;
		int productRow = 0;

		// GET ALL PRODUCTS IN TEMPLATE
		List<WebElement> Products = driver
				.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']"));
		for (int i = 0; i < Products.size(); i++) {
			getProductName = Products.get(i).getText().trim();
			if (getProductName.contains(productName)) {
				productRow = i + 1;
				bVerifyProductName = true;
				break;
			}
		}

		if (bVerifyProductName) {
			List<WebElement> ProductsEditIcons = driver
					.findElements(By.xpath("//div[@class='panel panel-default']//i[@class='fa fa-pencil text-muted']"));

			// CLICK ON EDIT PRODUCT ICON FROM PROTOCOL TEMPLATE
			ProductsEditIcons.get(productRow).click();
			Thread.sleep(3000);

			// ENTER PRODUCT REQUIRED QUANTITY
			if (editProductReqQty != "" || editProductReqQty != null) {
				getWebElement("EditProductInTemplate_ProdReqQty").click();
				getWebElement("EditProductInTemplate_ProdReqQty").clear();
				getWebElement("EditProductInTemplate_ProdReqQty").sendKeys(editProductReqQty);
			}

			// SELECT PRODUCT REQUIRED QUANTITY UNIT
			Library SelectList = new Library();
			if (editProductReqQtyUnit != "" || editProductReqQtyUnit != null) {
				if (SelectList.VerifySelectList("EditProductInTemplate_ProdReqQtyUnit",
						editProductReqQtyUnit) == true) {
					SelectList.SelectByVisibleText("EditProductInTemplate_ProdReqQtyUnit", editProductReqQtyUnit);
					Thread.sleep(1000);
				} else {
					Assert.assertTrue("Quantity required unit not found", false);
				}
			}

			// SAVE PRODUCT DETAILS
			getWebElement("Update_Link").click();
			explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

			// REFRESH
			refreshPage();

			// VERIFICATION OF EDITED DATA
			List<WebElement> ProductsReqQtyAndUnit = driver.findElements(
					By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']//span"));
			getProductReqQty = ProductsReqQtyAndUnit.get(productRow - 1).getText().trim().split(" ")[0];
			getProductReqQtyUnit = ProductsReqQtyAndUnit.get(productRow - 1).getText().trim().split(" ")[1];
			getProductReqQty.contains(editProductReqQty);
			getProductReqQtyUnit.equals(editProductReqQtyUnit);

			// UPDATE EDITED TEST DATA TO DATA SHEET
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"AddTemplateProducts");
			ExcelUtils.setCellData(editProductReqQty, 1, 6,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddTemplateProducts");
			ExcelUtils.setCellData(editProductReqQtyUnit, 1, 7,
					Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddTemplateProducts");
		}
	}

	public void deleteProductFromProtocolTemplate(String productName) throws Exception {

		// DECLARATION
		boolean bVerifyProductName = false;
		String getProductName = null;
		int productRow = 0;
		int initialProductCount = 0;
		int finalProductCount;
		boolean bVerifyProdNameAfter = false;

		// GET ALL PRODUCTS IN TEMPLATE
		List<WebElement> Products = driver
				.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']"));
		initialProductCount = Products.size();
		for (int i = 0; i < Products.size(); i++) {
			getProductName = Products.get(i).getText().trim();
			if (getProductName.contains(productName)) {
				productRow = i + 1;
				bVerifyProductName = true;
				break;
			}
		}

		if (bVerifyProductName) {
			List<WebElement> ProductsDeleteIcons = driver
					.findElements(By.xpath("//div[@class='panel panel-default']//i[@class='fa fa-trash']"));

			// CLICK ON EDIT PRODUCT ICON FROM PROTOCOL TEMPLATE
			ProductsDeleteIcons.get(productRow - 1).click();
			Thread.sleep(3000);

			// VALIDATION OF CONFIRMATION MODAL
			boolean bConfModal = getWebElement("DeleteProductFromTemplate_Conf_Modal").isDisplayed();
			Assert.assertTrue("Delete product confirmation modal does not appear", bConfModal);
			getWebElement("Button_YES").click();
			Thread.sleep(4000);

			// REFRESH PAGE
			refreshPage();

			List<WebElement> Products2 = driver
					.findElements(By.xpath("//div[@class='panel panel-default']//p[@style='margin-bottom: 5px;']"));
			finalProductCount = Products2.size();
			for (int j = 0; j < Products2.size(); j++) {
				getProductName = Products2.get(j).getText().trim();
				if (getProductName.contains(productName)) {
					bVerifyProdNameAfter = true;
					break;
				}
			}

			// VALIDATION AFTER DELETE STEP
			Assert.assertTrue("Product not deleted from protocol template section",
					((bVerifyProdNameAfter == false) & ((initialProductCount - finalProductCount) == 1)));
		} else {
			Assert.assertTrue("Product does not exist in protocol template", false);
		}
	}

	public void addProductToProtocolSteps(String stepTitle, String productName, String catalogNumber,
			String requiredQty, String requiredQtyUnit, String stepRequiredQty, String stepRequiredQtyUnit, String CAS,
			String vendor) throws Exception {

		// DECLARATION
		int stepNo = 0;
		int suggestedMatRow = 0;

		// GET STEP NUMBER
		stepNo = getStepPositionNumber(stepTitle);

		// CLICK ON ADD PRODUCT
		List<WebElement> addProductLinks = driver.findElements(By.xpath("//a[@id='addStepMaterial']"));
		addProductLinks.get(stepNo - 1).click();
		Thread.sleep(3000);

		List<WebElement> existingProductsFromTemplate = driver
				.findElements(By.xpath("//div[@class='portlet light bordered' and @data-toggle='collapse']"));
		for (int i = 0; i < existingProductsFromTemplate.size(); i++) {
			String getNameAndCatalog = driver.findElement(By.xpath("//div[@id='stepMaterial" + i + "']//p")).getText()
					.trim();
			String getSuggProductName = getNameAndCatalog.split(" ")[2].trim();
			if (getSuggProductName.equals(productName)) {
				suggestedMatRow = i;

				String getSuggCatalog = getNameAndCatalog.split(" ")[0].trim();
				if (getSuggCatalog.equals(catalogNumber)) {
					Assert.assertTrue("Catalog Mismatch", catalogNumber.equals(getSuggCatalog));
				}

				String getReqQtyAndUnit = driver.findElement(By.xpath("//div[@id='stepMaterial" + i + "']//p//span"))
						.getText().trim();
				if (getReqQtyAndUnit.contains(requiredQty)) {
					Assert.assertTrue("Required qty Mismatch", getReqQtyAndUnit.contains(requiredQty));
				}

				if (getReqQtyAndUnit.contains(requiredQtyUnit)) {
					Assert.assertTrue("Required qty unit Mismatch", getReqQtyAndUnit.contains(requiredQtyUnit));
				}

				List<WebElement> getCASandVendor = driver
						.findElements(By.xpath("//div[@id='stepMaterial" + i + "']//ul//li//span"));
				String getSuggCAS = getCASandVendor.get(0).getText().trim();
				String getSuggVendor = getCASandVendor.get(1).getText().trim();

				if (getSuggVendor.equals(vendor)) {
					Assert.assertTrue("Vendor name Mismatch", vendor.equals(getSuggVendor));
				}

				if (!(CAS == "") & !(CAS == null)) {
					Assert.assertTrue("CAS number Mismatch", CAS.equals(getSuggCAS));
				}

				existingProductsFromTemplate.get(i).click();
				Thread.sleep(3000);
				break;
			}
		}

		// ENTER STEP REQUIRED QTY
		if (!(stepRequiredQty == "") & !(stepRequiredQty == null)) {
			driver.findElement(By.xpath("//input[@class='form-control stopCollapse" + suggestedMatRow + "']")).click();
			driver.findElement(By.xpath("//input[@class='form-control stopCollapse" + suggestedMatRow + "']")).clear();
			driver.findElement(By.xpath("//input[@class='form-control stopCollapse" + suggestedMatRow + "']"))
					.sendKeys(stepRequiredQty);
		}

		// ENTER STEP REQUIRED QTY UNIT
		if (!(stepRequiredQtyUnit == "") & !(stepRequiredQtyUnit == null)) {
			Select stepRequiredQtyUnitDropdown = new Select(driver
					.findElement(By.xpath("//select[@class='form-control stopCollapse" + suggestedMatRow + "']")));
			stepRequiredQtyUnitDropdown.selectByVisibleText(stepRequiredQtyUnit);
			Thread.sleep(2000);
		}

		// SAVE DETAILS
		getWebElement("Link_Add").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// REFRESH
		refreshPage();

		// EXPAND STEP
		expandStep(stepTitle);

		// VERIFY IF PRODUCT ADDED TO STEP OR NOT
		String getStepProductName;
		boolean bVerifyProd = false;
		List<WebElement> stepProducts = driver
				.findElements(By.xpath("//div[@id='step-material" + (stepNo - 1) + "']//a[@style='cursor: default;']"));
		for (int j = 0; j < stepProducts.size(); j++) {
			getStepProductName = stepProducts.get(j).getAttribute("title").trim();
			if (getStepProductName.equals(productName)) {
				bVerifyProd = true;
				break;
			}
		}
		Assert.assertTrue("Product did not add to step", bVerifyProd);

		// UPDATE TEST DATA SHEET
		{
			if (bVerifyProd) {
				ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
						"TemplateStepProducts");
				ExcelUtils.setCellData(stepTitle, 1, 2,
						Research_Constants.Path_TestData + Research_Constants.File_TestData, "TemplateStepProducts");
			}
		}
	}

	// EDIT PROTOCOL TEMPLATE STEP PRODUCT
	public void updateProductAddedToProtocolTemplateStep(String stepTitle, String stepProductName,
			String stepProductCatalog, String stepProductRequiredQty, String stepProductRequiredQtyUnit,
			String updateStepProductReqQty, String updateStepProductReqQtyUnit) throws Exception {

		// DECLARATION
		int stepNo = 0;
		int bStepProductNo = 0;
		boolean bStepProduct = false;

		// GET STEP NUMBER
		stepNo = getStepPositionNumber(stepTitle);

		// EXPAND STEP
		expandStep(stepTitle);

		// VERIFICATION OF STEP PRODUCT
		List<WebElement> getStepProduct = driver
				.findElements(By.xpath("//div[@id='step-material" + (stepNo - 1) + "']//a[@style='cursor: default;']"));
		for (int i = 0; i < getStepProduct.size(); i++) {
			String getName = getStepProduct.get(i).getAttribute("title").trim();
			String getCatalog = driver
					.findElement(By.xpath("(((//div[@id='step-material" + (stepNo - 1)
							+ "']//a[@style='cursor: default;']/../..)[" + (i + 1) + "]//li)[2]//span)[2]//span"))
					.getAttribute("title").trim();
			if ((getName.equals(stepProductName)) & (getCatalog.equals(stepProductCatalog))) {
				bStepProduct = true;
				bStepProductNo = i + 1;
			}
		}
		Assert.assertTrue("Product " + stepProductName + " does not exists in step" + stepTitle, bStepProduct);

		// CLICK ON EDIT STEP PRODUCT
		driver.findElement(By.xpath("(//div[@id='step-material" + (stepNo - 1)
				+ "']//a[@style='cursor: default;']/../..//i[@class='fa fa-pencil'])[" + (bStepProductNo) + "]"))
				.click();
		Thread.sleep(3000);

		// EDIT STEP MATERIAL DETAILS
		if (updateStepProductReqQty != "" || updateStepProductReqQty != null) {
			getWebElement("UpdateStepProductReqQty").click();
			getWebElement("UpdateStepProductReqQty").clear();
			getWebElement("UpdateStepProductReqQty").sendKeys(updateStepProductReqQty);
		}

		if (updateStepProductReqQtyUnit != "" || updateStepProductReqQtyUnit != null) {
			Library SelectList = new Library();
			if (SelectList.VerifySelectList("UpdateStepProductReqQtyUnit", updateStepProductReqQtyUnit) == true) {
				SelectList.SelectByVisibleText("UpdateStepProductReqQtyUnit", updateStepProductReqQtyUnit);
				Thread.sleep(1000);
			} else {
				Assert.assertTrue("Step product Quantity required unit not found in drop down", false);
			}
		}

		getWebElement("UpdateStepProduct_Link").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		refreshPage();

		// EXPAND STEP
		expandStep(stepTitle);

		// VERIFY UPDATED DETAILS
		String getUpdatedQtyAndUnit = driver
				.findElement(By.xpath("(((//div[@id='step-material" + (stepNo - 1)
						+ "']//a[@style='cursor: default;']/../..)[" + bStepProductNo + "]//li)[2]//span)[1]"))
				.getText().trim();
		boolean bVerifyStepProductUpdate = (getUpdatedQtyAndUnit.contains(updateStepProductReqQty)
				& getUpdatedQtyAndUnit.contains(updateStepProductReqQtyUnit));
		Assert.assertTrue("Step product update failed", bVerifyStepProductUpdate);
	}

	public void deleteProductFromProtocolTemplateSteps(String stepTitle, String stepProductName,
			String stepProductCatalog) throws Exception {
		// DECLARATION
		int stepNo = 0;
		int bStepProductNo = 0;
		boolean bStepProduct = false;

		// GET STEP NUMBER
		stepNo = getStepPositionNumber(stepTitle);

		// EXPAND STEP
		expandStep(stepTitle);

		// VERIFICATION OF STEP PRODUCT BEFORE DELETING
		List<WebElement> getStepProduct = driver
				.findElements(By.xpath("//div[@id='step-material" + (stepNo - 1) + "']//a[@style='cursor: default;']"));
		for (int i = 0; i < getStepProduct.size(); i++) {
			String getName = getStepProduct.get(i).getAttribute("title").trim();
			String getCatalog = driver
					.findElement(By.xpath("(((//div[@id='step-material" + (stepNo - 1)
							+ "']//a[@style='cursor: default;']/../..)[" + (i + 1) + "]//li)[2]//span)[2]//span"))
					.getAttribute("title").trim();
			if ((getName.equals(stepProductName)) & (getCatalog.equals(stepProductCatalog))) {
				bStepProduct = true;
				bStepProductNo = i + 1;
			}
		}
		Assert.assertTrue("Product " + stepProductName + " does not exists in step" + stepTitle, bStepProduct);

		// CLICK ON DELETE STEP PRODUCT
		driver.findElement(By.xpath("(//div[@id='step-material" + (stepNo - 1)
				+ "']//a[@style='cursor: default;']/../..//i[@class='fa fa-trash'])[" + (bStepProductNo) + "]"))
				.click();
		Thread.sleep(3000);
		getWebElement("Button_YES").click();
		Thread.sleep(4000);

		// REFRESH
		refreshPage();

		// EXPAND
		expandStep(stepTitle);

		// VERIFICATION OF STEP PRODUCT BEFORE DELETING
		boolean bStepProductAfter = false;
		List<WebElement> getStepProduct2 = driver
				.findElements(By.xpath("//div[@id='step-material" + (stepNo - 1) + "']//a[@style='cursor: default;']"));
		for (int i = 0; i < getStepProduct2.size(); i++) {
			String getName = getStepProduct2.get(i).getAttribute("title").trim();
			String getCatalog = driver
					.findElement(By.xpath("(((//div[@id='step-material" + (stepNo - 1)
							+ "']//a[@style='cursor: default;']/../..)[" + (i + 1) + "]//li)[2]//span)[2]//span"))
					.getAttribute("title").trim();
			if ((getName.equals(stepProductName)) & (getCatalog.equals(stepProductCatalog))) {
				bStepProductAfter = true;
			}
		}
		Assert.assertFalse("Product " + stepProductName + " did not get deleted from step" + stepTitle,
				bStepProductAfter);
	}

	public void deleteMaterialFromExperimentWhichIsConsumedInInventory(String materialName, String QtyRequired,
			String QtyRequiredUnit) throws Exception {
		// GET INACTIVE MATERIAL INDEX
		List<WebElement> allInActiveMaterialsInExperimentBefore = driver
				.findElements(By.xpath("//div[@class='panel panel-default experiment-material-box']"));

		boolean bVerifyMaterial = false;
		List<WebElement> inActiveMaterials = driver
				.findElements(By.xpath("//div[@class='panel panel-default experiment-material-box']//p"));
		for (int i = 0; i < allInActiveMaterialsInExperimentBefore.size(); i++) {
			String getNameAndReqQtyAndUnit = inActiveMaterials.get(i).getText().trim();
			if (getNameAndReqQtyAndUnit.contains(materialName) & getNameAndReqQtyAndUnit.contains(QtyRequired)
					& getNameAndReqQtyAndUnit.contains(QtyRequiredUnit)) {
				int getFlag = driver
						.findElements(By.xpath("(//div[@class='panel panel-default experiment-material-box'])["
								+ (i + 1)
								+ "]//a[@class='text-danger' and @title='Material is not Present in Inventory.']"))
						.size();
				if (getFlag == 1) {
					bVerifyMaterial = true;
					// DELETE MATERIAL
					driver.findElement(By.xpath(
							"(//div[@class='panel panel-default experiment-material-box']//a[@title='Remove' and @class='text-muted'])["
									+ i + "]"))
							.click();
					Thread.sleep(3000);
					getWebElement("Button_YES").click();
					Thread.sleep(4000);
					break;
				}
			}
		}
		Assert.assertTrue("Material not changed to Inactive in material section", bVerifyMaterial);

		// REFRESH
		refreshPage();

		// GET INACTIVE MATERIAL INDEX
		List<WebElement> allInActiveMaterialsInExperimentAfter = driver
				.findElements(By.xpath("//div[@class='panel panel-default experiment-material-box']"));

		// VERIFY MATERIAL AFTER DELETING
		boolean bVerifyMaterialAfter = false;
		for (int i = 0; i < allInActiveMaterialsInExperimentAfter.size(); i++) {
			String getNameAndReqQtyAndUnit = allInActiveMaterialsInExperimentAfter.get(i).getText().trim();
			if (getNameAndReqQtyAndUnit.contains(materialName) & getNameAndReqQtyAndUnit.contains(QtyRequired)
					& getNameAndReqQtyAndUnit.contains(QtyRequiredUnit)) {
				int getFlag = driver
						.findElements(By.xpath("(//div[@class='panel panel-default experiment-material-box'])["
								+ (i + 1)
								+ "]//a[@class='text-danger' and @title='Material is not Present in Inventory.']"))
						.size();
				if (getFlag == 1) {
					bVerifyMaterialAfter = true;
					break;
				}
			}
		}
		Assert.assertFalse("Material not deleted in material section", bVerifyMaterialAfter);
	}

	public void editMaterialFromExperimentWhichIsConsumedFromInventory(String materialName, String QtyRequired,
			String QtyRequiredUnit, String editExpiry, String editLOT, String editQtyRequired,
			String editQtyRequiredUnit) throws Exception {

		// GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();

		// GET INACTIVE MATERIAL INDEX
		List<WebElement> allInActiveMaterialsInExperimentBefore = driver
				.findElements(By.xpath("//div[@class='panel panel-default experiment-material-box']"));

		boolean bVerifyMaterial = false;
		int inactiveMaterialIndex = 0;
		List<WebElement> inActiveMaterials = driver
				.findElements(By.xpath("//div[@class='panel panel-default experiment-material-box']//p"));
		for (int i = 0; i < allInActiveMaterialsInExperimentBefore.size(); i++) {
			String getNameAndReqQtyAndUnit = inActiveMaterials.get(i).getText().trim();
			if (getNameAndReqQtyAndUnit.contains(materialName) & getNameAndReqQtyAndUnit.contains(QtyRequired)
					& getNameAndReqQtyAndUnit.contains(QtyRequiredUnit)) {
				int getFlag = driver
						.findElements(By.xpath("(//div[@class='panel panel-default experiment-material-box'])["
								+ (i + 1)
								+ "]//a[@class='text-danger' and @title='Material is not Present in Inventory.']"))
						.size();
				if (getFlag == 1) {
					bVerifyMaterial = true;
					inactiveMaterialIndex = i;
					break;
				}
			}
		}
		Assert.assertTrue("Material not changed to Inactive in material section", bVerifyMaterial);

		// EDIT MATERIAL DETAILS
		driver.findElement(
				By.xpath("(//div[@class='panel panel-default experiment-material-box']//i[@class='fa fa-pencil'])["
						+ inactiveMaterialIndex + "]"))
				.click();
		Thread.sleep(3000);

		// EDIT EXPIRY
		String newExpiryDate = null;
		if (editExpiry == "YES") {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 1); // Adding 1 days
			newExpiryDate = sdf.format(c.getTime());
			getWebElement("EditMaterialInExperiment_Expiry").click();
			getWebElement("EditMaterialInExperiment_Expiry").clear();
			getWebElement("EditMaterialInExperiment_Expiry").sendKeys(newExpiryDate);
			getWebElement("EditMaterialInExperiment_LOT").click();
		} else {
			// SKIP EXPIRY
		}

		// EDIT MATERIAL LOT
		String newLOT = "LOT" + uniqueNumber;
		if (editLOT == "YES") {
			getWebElement("EditMaterialInExperiment_LOT").click();
			getWebElement("EditMaterialInExperiment_LOT").clear();
			getWebElement("EditMaterialInExperiment_LOT").sendKeys(newLOT);
		} else {
			// SKIP MATERIAL LOT
		}

		// EDIT MATERIAL RECIEVED QTY
		if (editQtyRequired != "" || editQtyRequired != null) {
			getWebElement("EditMaterialInExperiment_QtyRequired").clear();
			getWebElement("EditMaterialInExperiment_QtyRequired").click();
			getWebElement("EditMaterialInExperiment_QtyRequired").sendKeys(editQtyRequired);
		} else {
			// SKIP MATERIAL RECIEVED QTY
		}

		// EDIT REQUIRED QUANTITY UNIT
		Library SelectList = new Library();
		if (editQtyRequiredUnit != "") {
			if (SelectList.VerifySelectList("EditMaterialInExperiment_QtyRequiredUnit", editQtyRequiredUnit) == true) {
				SelectList.SelectByVisibleText("EditMaterialInExperiment_QtyRequiredUnit", editQtyRequiredUnit);
				Thread.sleep(1000);
			} else {
				Assert.assertTrue("Quantity required unit not found", false);
			}
		} else {
			// SKIP MATERIAL RECIEVED QTY UNIT
		}

		// UPDATE MATERISL
		getWebElement("Update_Link").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// VERIFY UPDATED MATERIAL DETAILS IN MODAL
		boolean bVerifyExpiry = false;
		boolean bVerifyMatLot = false;
		boolean bVerifyMatReqQty = false;
		boolean bVerifyMatReqQtyUnit = false;
		{
			if (editExpiry == "YES") {
				String getExpiry = getWebElement("EditMaterialInExperiment_Expiry").getAttribute("value").trim();
				bVerifyExpiry = getExpiry.equals(newExpiryDate);
				Assert.assertTrue("Edited expiry details mismatch", bVerifyExpiry);
			} else {
				// SKIP EXPIRY VERIFICATION
			}

			if (editLOT == "YES") {
				String getLOT = getWebElement("EditMaterialInExperiment_LOT").getAttribute("value").trim();
				bVerifyMatLot = getLOT.equals(newLOT);
				Assert.assertTrue("Edited LOT details mismatch", bVerifyMatLot);
			} else {
				// SKIP MATERIAL LOT VERIFICATION
			}

			if (editQtyRequired != "" || editQtyRequired != null) {
				String getQtyReq = getWebElement("EditMaterialInExperiment_QtyRequired").getAttribute("value").trim();
				bVerifyMatReqQty = getQtyReq.equals(editQtyRequired);
				Assert.assertTrue("Edited qty required details mismatch", bVerifyMatReqQty);
			} else {
				// SKIP MATERIAL RECIEVED QTY VERIFICATION
			}

			if (editQtyRequiredUnit != "" || editQtyRequiredUnit != null) {
				String getQtyReqUnit = getWebElement("EditMaterialInExperiment_QtyRequiredUnit").getAttribute("value")
						.trim();
				bVerifyMatReqQtyUnit = getQtyReqUnit.equals(editQtyRequiredUnit);
				Assert.assertTrue("Edited qty required unit details mismatch", bVerifyMatReqQtyUnit);
			} else {
				// SKIP MATERIAL RECIEVED QTY UNIT VERIFICATION
			}
		}

		// CLOSE MODAL
		refreshPage();

		// UPDATE TO TEST DATA
		{
			ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData,
					"AddExperimentMaterials");
			if ((editExpiry == "YES") & (bVerifyExpiry == true)) {
				ExcelUtils.setCellData(newExpiryDate, 2, 2,
						Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
			}

			if ((editLOT == "YES") & (bVerifyMatLot == true)) {
				ExcelUtils.setCellData(newLOT, 2, 7,
						Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
			}

			if ((editQtyRequired != "") & (editQtyRequired != null) & (bVerifyMatReqQty == true)) {
				ExcelUtils.setCellData(editQtyRequired, 2, 8,
						Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
			}

			if ((editQtyRequiredUnit != "") & (editQtyRequiredUnit != null) & (bVerifyMatReqQtyUnit == true)) {
				ExcelUtils.setCellData(editQtyRequiredUnit, 2, 9,
						Research_Constants.Path_TestData + Research_Constants.File_TestData, "AddExperimentMaterials");
			}
		}
	}

	public int getNoteIndex(String noteTitle) throws Exception {
		// DECLARATION
		int getNoteIndex = 0;
		String getNoteTitle;
		int i;
		List<WebElement> allNoteTitle;

		// OBJECT CREATION
		SoftAssert softAssert = new SoftAssert();

		// GET ALL NOTES
		allNoteTitle = driver.findElements(By
				.xpath("//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']//span"));
		if (allNoteTitle.size() == 0) {
			allNoteTitle = driver.findElements(
					By.xpath("//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']"));
		}

		// VALIDATION OF NOTE TITLE
		for (i = 0; i < allNoteTitle.size(); i++) {
			getNoteTitle = allNoteTitle.get(i).getText().trim();
			if (getNoteTitle.equals(noteTitle)) {
				getNoteIndex = i + 1;
				break;
			}
		}

		softAssert.assertTrue((!(getNoteIndex == 0)), "Note not found");
		return getNoteIndex;
	}

	public void editNote(String noteTitle, String editNoteTitle, String editNoteDescription) throws Exception {
		// DECLARATION
		int noteIndex = 0;
		List<WebElement> allNoteTitle;
		@SuppressWarnings("unused")
		SoftAssert softAssert = new SoftAssert();
		String getNoteDescription;
		List<WebElement> allProjectNoteActionButtons;

		// SCROLL DOWN TO END OF THE PAGE
		scrollDownPage();

		// GET NOTE INDEX
		noteIndex = getNoteIndex(noteTitle);

		// CLICK ON NOTE ACTIONS
		allProjectNoteActionButtons = driver.findElements(By.xpath(
				"//span[@id='notesForm:notePanel']//a[@id='noteActions']//i[@class='material-icons dropdown-margin actionColor editPullDown text-primary']"));
		if (allProjectNoteActionButtons.size() == 0) {
			allProjectNoteActionButtons = driver.findElements(
					By.xpath("//span[@id='notesForm:notePanel']//i[@class='fa fa-ellipsis-h icon-custom-size']"));
		}
		allProjectNoteActionButtons.get(noteIndex - 1).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='notesForm:noteList:" + (noteIndex - 1) + ":setNote']")).click();
		Thread.sleep(2000);

		// EDIT NOTE TITLE
		getWebElement("AddNote_Title").click();
		getWebElement("AddNote_Title").clear();
		getWebElement("AddNote_Title").sendKeys(editNoteTitle);

		// EDIT NOTE DESCRIPTION
		if (editNoteDescription == "" || (editNoteDescription == null)) {
			// SKIP EDITING NOTE DESCRIPTION
		} else {
			WebElement addNoteDescFrame = getWebElement("NoteDescription");
			driver.switchTo().frame(addNoteDescFrame);
			WebElement addNoteDescBody = driver.findElement(By.tagName("body"));
			addNoteDescBody.click();
			addNoteDescBody.clear();
			addNoteDescBody.sendKeys(editNoteDescription);
			driver.switchTo().defaultContent();
		}

		getWebElement("Save_Link_2").click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// SCROLL DOWN TO END OF THE PAGE
		scrollDownPage();

		// GET ALL NOTES
		allNoteTitle = driver.findElements(By
				.xpath("//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']//span"));
		if (allNoteTitle.size() == 0) {
			allNoteTitle = driver.findElements(
					By.xpath("//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']"));
		}
		String getTitle = allNoteTitle.get(noteIndex - 1).getText().trim();
		Assert.assertTrue("Edit experiment title failed!", (getTitle.equals(editNoteTitle)));

		if (editNoteDescription == "" || (editNoteDescription == null)) {
			// SKIP VERIFICATION OF NOTE DESCRIPTION
		} else {
			getNoteDescription = driver.findElement(By.xpath("(//span[@id='notesForm:notePanel']//div[@id='note_"
					+ (noteIndex - 1) + "']/following-sibling::p)[2]")).getText().trim();
			Assert.assertTrue("Edit experiment description failed!", (getNoteDescription.equals(editNoteDescription)));
		}
	}

	public void deleteNote(String noteTitle) throws Exception {
		// DECLARATION
		int noteIndex = 0;
		List<WebElement> allNoteTitle;
		List<WebElement> allNoteActionButtons;
		boolean bVerifyNote = false;
		SoftAssert softAssert = new SoftAssert();

		// SCROLL DOWN TO END OF THE PAGE
		scrollDownPage();

		// GET NOTE INDEX
		noteIndex = getNoteIndex(noteTitle);

		// CLICK ON PROJECT NOTE ACTIONS
		allNoteActionButtons = driver.findElements(By.xpath(
				"//span[@id='notesForm:notePanel']//a[@id='noteActions']//i[@class='material-icons dropdown-margin actionColor editPullDown text-primary']"));
		if (allNoteActionButtons.size() == 0) {
			allNoteActionButtons = driver.findElements(
					By.xpath("//span[@id='notesForm:notePanel']//i[@class='fa fa-ellipsis-h icon-custom-size']"));
		}
		allNoteActionButtons.get(noteIndex - 1).click();
		Thread.sleep(2000);

		// SELECT DELETE NOTE OPTION
		try {
			driver.findElement(By.xpath(
					"(//span[@id='notesForm:notePanel']//ul[@class='dropdown-menu pull-right']//a[@data-toggle='modal' and @title='Delete'])["
							+ noteIndex + "]"))
					.click();
			Thread.sleep(2000);
		} catch (Exception E) {
			driver.findElement(By.xpath(
					"(//span[@id='notesForm:notePanel']//ul[@class='dropdown-menu editBorder']//a[@data-toggle='modal' and @title='Delete'])["
							+ noteIndex + "]"))
					.click();
			Thread.sleep(2000);
		}

		// CLICK ON YES BUTTON
		driver.findElement(
				By.xpath("//a[@id='notesForm:noteList:" + (noteIndex - 1) + ":deleteProjectLink:deleteNote']")).click();
		Thread.sleep(3000);

		// VERIFY DELETE NOTE
		allNoteTitle = driver.findElements(By
				.xpath("//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']//span"));
		if (allNoteTitle.size() == 0) {
			allNoteTitle = driver.findElements(
					By.xpath("//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']"));
		}
		for (int i = 0; i < allNoteTitle.size(); i++) {
			String getTitle = allNoteTitle.get(i).getText().trim();
			if (getTitle.equals(noteTitle)) {
				bVerifyNote = true;
			}
		}
		softAssert.assertFalse(bVerifyNote, "Delete note failed");
	}

	public void moveStepNoteToAnotherStep(String sourceStepTitle, String destinationStepTitle, String stepNoteTitle,
			String stepNoteDescription) throws Exception {

		// DECLARATION
		int stepIndex, stepNoteIndex, stepIndex2;
		boolean bNoteTitleVal = false;
		boolean bNoteDescVal = false;

		// GET SOURCE STEP POSITION
		stepIndex = getStepPositionNumber(sourceStepTitle);

		// GET STEP NOTE POSITION
		stepNoteIndex = getStepNotePositionNumber(sourceStepTitle, stepNoteTitle);

		// MOVE TO ANOTHER STEP
		driver.findElement(By.xpath(
				"(//div[@id='step-view" + (stepIndex - 1) + "']//a[@title='Move note'])[" + (stepNoteIndex) + "]"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@id='step-view" + (stepIndex - 1)
				+ "']//ul[@class='dropdown-menu pull-right']//a[contains(text(),'" + destinationStepTitle + "')])["
				+ stepNoteIndex + "]")).click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// GET DESTINATION STEP POSITION
		stepIndex2 = getStepPositionNumber(destinationStepTitle);

		// GET STEP NOTE POSITION
		stepNoteIndex = getStepNotePositionNumber(destinationStepTitle, stepNoteTitle);

		Reporter.log("Verify title and description after moving the step note " + stepNoteTitle + " from step "
				+ sourceStepTitle + " to step " + destinationStepTitle);
		int noOfNotesInDestStep = driver.findElements(By.xpath(
				"//div[@id='step-view" + (stepIndex2 - 1) + "']//div[@class='notesOutput step-note-new-wrapper']"))
				.size();

		// VERIFICATION OF STEP NOTE TITLE
		for (int j = 0; j < noOfNotesInDestStep; j++) {
			String getNoteTitle = driver.findElement(By.xpath("(//div[@id='step-view" + (stepIndex2 - 1)
					+ "']//div[@class='notesOutput step-note-new-wrapper']//div[@class='col-lg-10']//ul[@class='list-inline'])["
					+ (j + 1) + "]//li[1]//b")).getText().trim();
			if (getNoteTitle.equals(stepNoteTitle)) {
				bNoteTitleVal = true;
				stepNoteIndex = j + 1;
				break;
			}
		}
		Assert.assertTrue("Step note title validation failed after moving", bNoteTitleVal);

		// VERIFICATION OF STEP NOTE DESCRIPTION
		if (stepNoteDescription == "" || (stepNoteDescription == null)) {
			// SKIP VERIFICATION OF STEP NOTE DESCRIPTION
		} else {
			for (int j = 0; j < noOfNotesInDestStep; j++) {
				String getNoteDesc = driver.findElement(By.xpath(
						"(//div[@class='notesOutput step-note-new-wrapper']//div[@class='note-step-description div-scroll wordBreak']//div[@id='step_"
								+ (stepIndex2 - 1) + "_note_" + (stepNoteIndex - 1) + "']/following-sibling::p)[2]"))
						.getText().trim();
				if (getNoteDesc.equals(stepNoteDescription)) {
					bNoteDescVal = true;
					break;
				}
			}
			Assert.assertTrue("Step note description validation failed after moving", bNoteDescVal);
		}
	}

	public void moveTemplateLevelOrExperimentLevelNotesToStep(String stepTitle, String noteTitle,
			String noteDescription) throws Exception {

		// DECLARATION
		int stepIndex, noteIndex, stepNoteIndex;
		boolean bNoteTitleVal = false;
		boolean bNoteDescVal = false;

		// GET NOTE POSITION
		noteIndex = getNoteIndex(noteTitle);

		// MOVE TO ANOTHER STEP
		driver.findElement(By.xpath("(//form[@id='notesForm']//a[@title='Move note'])[" + noteIndex + "]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//form[@id='notesForm']//ul[@class='dropdown-menu pull-right'])["
				+ ((2 * noteIndex) - 1) + "]//a[contains(text(),'" + stepTitle + "')]")).click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		// GET STEP POSITION
		stepIndex = getStepPositionNumber(stepTitle);

		Reporter.log(
				"Verify title and description after moving the experiment note " + noteTitle + " to step " + stepTitle);
		int noOfNotesInDestStep = driver.findElements(By.xpath(
				"//div[@id='step-view" + (stepIndex - 1) + "']//div[@class='notesOutput step-note-new-wrapper']"))
				.size();

		// GET STEP NOTE POSITION
		stepNoteIndex = getStepNotePositionNumber(stepTitle, noteTitle);

		// VERIFICATION OF STEP NOTE TITLE
		for (int j = 0; j < noOfNotesInDestStep; j++) {
			String getNoteTitle = driver.findElement(By.xpath("(//div[@id='step-view" + (stepIndex - 1)
					+ "']//div[@class='notesOutput step-note-new-wrapper']//div[@class='col-lg-10']//ul[@class='list-inline'])["
					+ (j + 1) + "]//li[1]//b")).getText().trim();
			if (getNoteTitle.equals(noteTitle)) {
				bNoteTitleVal = true;
				break;
			}
		}
		Assert.assertTrue("Step note title validation failed after moving", bNoteTitleVal);

		// VERIFICATION OF STEP NOTE DESCRIPTION
		if (noteDescription == "" || (noteDescription == null)) {
			// SKIP VERIFICATION OF STEP NOTE DESCRIPTION
		} else {
			for (int j = 0; j < noOfNotesInDestStep; j++) {
				String getNoteDesc = driver.findElement(By.xpath(
						"(//div[@class='notesOutput step-note-new-wrapper']//div[@class='note-step-description div-scroll wordBreak']//div[@id='step_"
								+ (stepIndex - 1) + "_note_" + (stepNoteIndex - 1) + "']/following-sibling::p)[2]"))
						.getText().trim();
				if (getNoteDesc.equals(noteDescription)) {
					bNoteDescVal = true;
					break;
				}
			}
			Assert.assertTrue("Step note description validation failed after moving", bNoteDescVal);
		}
	}

	public void moveStepNotesAsTemplateLevelNotesOrExperimentLevelNotes(String stepTitle, String stepNoteTitle,
			String stepNoteDescription) throws Exception {

		// DECLARATION
		int stepNoteIndex = 0, stepIndex = 0;

		// GET STEP POSITION
		stepIndex = getStepPositionNumber(stepTitle);

		// GET STEP NOTE POSITION
		stepNoteIndex = getStepNotePositionNumber(stepTitle, stepNoteTitle);

		Reporter.log("Move step note " + stepNoteTitle + " as experiment or protocol note ");
		driver.findElement(By
				.xpath("(//div[@id='step-view" + (stepIndex - 1) + "']//a[@title='Move note'])[" + stepNoteIndex + "]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@id='step-view" + (stepIndex - 1)
				+ "']//div[@class='notesOutput step-note-new-wrapper']//ul[@class='dropdown-menu pull-right'])["
				+ stepNoteIndex + "]//li//a[contains(text(),'Add as experiment note')]")).click();
		explicitWaitUntilElementIsInvisible("Research.LoadingSymbol");

		Reporter.log("Verify step note " + stepNoteTitle + " after moving from experiment or protocol step " + stepTitle
				+ " to experiment or protocol level");
		int noOfNotes = driver
				.findElements(By.xpath(
						"//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']"))
				.size();

		// VERIFICATION OF NOTE TITLE
		String getNoteTitle;
		try {
			List<WebElement> allNoteTitle = driver.findElements(By.xpath(
					"//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']//span"));
			getNoteTitle = allNoteTitle.get(noOfNotes - 1).getText().trim();
		} catch (Exception E) {
			List<WebElement> allNoteTitle = driver.findElements(
					By.xpath("//span[@id='notesForm:notePanel']//span[@data-placement='right' and @data-type='text']"));
			getNoteTitle = allNoteTitle.get(noOfNotes - 1).getText().trim();
		}
		Assert.assertTrue("Note title mismatch after moving from step", getNoteTitle.equals(stepNoteTitle));

		// VERIFICATION OF NOTE DESCRIPTION
		String getNoteDescription;
		if (stepNoteDescription == "" || (stepNoteDescription == null)) {
			// SKIP VERIFICATION OF NOTE DESCRIPTION
		} else {
			try {
				getNoteDescription = driver.findElement(By.xpath("(//span[@id='notesForm:notePanel']//div[@id='note_"
						+ noOfNotes + "']/following-sibling::p)[2]")).getText().trim();
			} catch (Exception e) {
				getNoteDescription = driver.findElement(By.xpath("(//span[@id='notesForm:notePanel']//div[@id='note_"
						+ (noOfNotes - 1) + "']/following-sibling::p)[2]")).getText().trim();
			}
			Assert.assertTrue("Note description mismatch after moving from step",
					getNoteDescription.equals(stepNoteDescription));
		}
	}
	
	// VERIFY PROJECT OR EXPERIMENT OR PROTOCOL TEMPLATE LOG
	public void verifyLog(String logTitle, String logDescription) throws Exception {
		
	}
}