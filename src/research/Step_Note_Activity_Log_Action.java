package research;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Step_Note_Activity_Log_Action extends TestBase {
	
	@Test
	public void experimentStepNoteActivityLogValidation() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "StepNoteActivityLog");
		String step_1_name = "Step1"+uniqueNumber;
		String step_1_desc = "Step1Descr"+uniqueNumber;
		String step_2_name = "Step2"+uniqueNumber;
		String step_2_desc = "Step2Descr"+uniqueNumber;
		String step_3_name = "Step3"+uniqueNumber;
		String step_3_desc = "Step3Descr"+uniqueNumber;
		String step_1_note = "Step1Note"+uniqueNumber;
		String step_1_note_desc = "Step1NoteDesc"+uniqueNumber;
		String explevelnote = "ExperimentLevelNote"+uniqueNumber;
		String explevelnotedesc = "ExperimentLevelNoteDescription"+uniqueNumber;
		String edit_step_3_note = "EditedStep3"+uniqueNumber;
		String edit_step_3_note_desc = "EditedStep3NoteDescription"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Adding multiple steps to the experiment");
		ResearchFunc.addStep(step_1_name, step_1_desc);
		ResearchFunc.addStep(step_2_name, step_2_desc);
		ResearchFunc.addStep(step_3_name, step_3_desc);
		
		Reporter.log("Add note to the step 1");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Verify step note title and description after creating step note in experiment log");
		getWebElement("ExperimentLogButton").click();
		Thread.sleep(4000);
		String getlogText = driver.findElements(By.xpath("//div[@class='timeline-body']")).get(0).getText();
		boolean bVerifyCreateStepNote1 = getlogText.contains(step_1_note+" has been created inside STEP "+step_1_name);
		Assert.assertTrue(bVerifyCreateStepNote1,"Step note title mismatching in activity log after doing action \"Create step note\"");
		boolean bVerifyCreateStepNote2 = getlogText.contains(step_1_note_desc);
		Assert.assertTrue(bVerifyCreateStepNote2,"Step note description mismatching in activity log after doing action \"Create step note\"");
		
		Reporter.log("Close experiment activity log");
		getWebElement("CloseExpLogsModal").click();
		Thread.sleep(2000);
		
		Reporter.log("Link step note 1 to step 3");
		getWebElement("LinkStepNote").click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'"+step_3_name+"')]")).click();
		Thread.sleep(8000);
		
		Reporter.log("View step notes for step 3");
		int stepIndex3 = 0;
		String part9 = "(//a[contains(text(),'View notes')]/../../../..)[";
		String part10 = "]//span";
		java.util.List<WebElement> viewNoteLink3 = driver.findElements(By.xpath("//a[contains(text(),'View notes')]"));
		for(int k=0;k<viewNoteLink3.size();k++) {
			java.util.List<WebElement> step_elements = driver.findElements(By.xpath(part9+(k+1)+part10));
			String stepName =  step_elements.get(0).getText().trim();
			if (stepName.equals(step_3_name)) {
				viewNoteLink3.get(k).click();
				Thread.sleep(3000);
				break;
			}
		}
		
		//To get step 3 index
		java.util.List<WebElement> allStepsInExp2 = driver.findElements(By.xpath("//div[@class='step-content']"));
		for(int i=0;i<allStepsInExp2.size();i++) {
			String getStepContents = allStepsInExp2.get(i).getText();
			String[] stepsTitle = getStepContents.split("Expand");
			String getStepName = stepsTitle[0].trim();
			if (getStepName.equals(step_3_name)) {
				stepIndex3 = i;
				break;
			}
		}
		
		Reporter.log("Verify step note 1 title and description after linking to step 3");
		String sStepIndex3 = Integer.toString(stepIndex3);
		String part3 = "//div[@id='step-view";
		String part4 = "']//div[@class='notesOutput step-note-new-wrapper']//ul[@class='list-inline']//li[1]";
		String part5 = "(//div[@id='step-view";
		String part6 = "']//div[@class='note-step-description div-scroll wordBreak']//p)[2]";
		java.util.List<WebElement> stepNoteArray3 = driver.findElements(By.xpath(part3+sStepIndex3+part4));
		java.util.List<WebElement> stepNoteArray4 = driver.findElements(By.xpath(part5+sStepIndex3+part6));
		String recentStepNoteTitle2 = stepNoteArray3.get(0).getText().toString();
		String recentStepNoteDescription2 = stepNoteArray4.get(0).getText().trim();
		Assert.assertTrue(recentStepNoteTitle2.equals(step_1_note),"Step note title mismatching in step 3");
		Assert.assertTrue(recentStepNoteDescription2.equals(step_1_note_desc),"Step note description mismatching in step 3");
		
		Reporter.log("Verify update step note after linking step note to another step in experiment log");
		getWebElement("ExperimentLogButton").click();
		Thread.sleep(4000);
		String getlogText2 = driver.findElements(By.xpath("//div[@class='timeline-body']")).get(0).getText();
		boolean bVerifyLinkStepNote = getlogText2.contains("NOTE "+step_1_note+" moved to STEP "+step_3_name);
		Assert.assertTrue(bVerifyLinkStepNote,"Activity log mismatching after doing action \"Link step note to another step\"");
		
		Reporter.log("Close experiment activity log");
		getWebElement("CloseExpLogsModal").click();
		Thread.sleep(2000);
		
		Reporter.log("Add experiment level notes to the experiment");
		getWebElement("AddNotesToExperiment").click();
		Thread.sleep(3000);
		getWebElement("AddNoteToExp_Title").click();
		Thread.sleep(2000);
		getWebElement("AddNoteToExp_Title").sendKeys(explevelnote);
		getWebElement("AddNoteToExp_Title").sendKeys(Keys.TAB);
		WebElement addNoteDescFrame = getWebElement("AddNoteToExp_Description");
		driver.switchTo().frame(addNoteDescFrame);
		WebElement addNoteDescBody = driver.findElement(By.tagName("body"));
		addNoteDescBody.click();
		Thread.sleep(2000);
		addNoteDescBody.sendKeys(explevelnotedesc);
		driver.switchTo().defaultContent();
		getWebElement("SaveExperimentNotes_Link").click();
		Thread.sleep(5000);
		
		Reporter.log("Capture added note title and description details from experiment");
		driver.findElements(By.xpath("//span[@data-title='Enter Note Name']"));
		driver.findElements(By.xpath("//div[@class='step-description wordBreak']"));
		
		Reporter.log("Verify added note in experiment");
//		String experimentNotes = allNotesInExp.get(0).getText();
//		String experimentNoteDesc = allNotesDescInExp.get(0).getText();
//		Assert.assertEquals(experimentNotes,explevelnote,"Experiment notes title validation failed!");
//		Assert.assertEquals(experimentNoteDesc,explevelnotedesc,"Experiment notes description validation failed!");
		
		//Scroll down to the end of page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		
		Reporter.log("Link experiment level notes to step 1");
		getWebElement("LinkExperimentLevelNote").click();
		Thread.sleep(2000);
		java.util.List<WebElement> getSteps = driver.findElement(By.xpath("//span[@id='notesForm:notePanel']//ul[@aria-labelledby='dropdownMenu2']")).findElements(By.tagName("a"));
		for(int step=0;step<getSteps.size();step++) {
			String getStepName = getSteps.get(step).getText().trim();
			if (getStepName.equals(step_1_name)) {
				getSteps.get(step).click();
				Thread.sleep(5000);
				break;
			}
		}
		
		//Scroll up
		js.executeScript("scroll(0, -250);");
		Thread.sleep(1000);
		
		Reporter.log("View Step note for step 1");
		int stepIndex_3 = 0;
		java.util.List<WebElement> viewNoteLink1 = driver.findElements(By.xpath("//a[contains(text(),'View notes')]"));
		for(int k=0;k<viewNoteLink1.size();k++) {
			java.util.List<WebElement> step_elements = driver.findElements(By.xpath(part9+(k+1)+part10));
			String stepName =  step_elements.get(0).getText().trim();
			if (stepName.equals(step_1_name)) {
				viewNoteLink1.get(k).click();
				stepIndex_3 = k;
				Thread.sleep(3000);
				break;
			}
		}
		
		Reporter.log("Verify step note title and description");
		String sStepIndex_3 = Integer.toString(stepIndex_3);
		java.util.List<WebElement> stepNoteArray_3 = driver.findElements(By.xpath(part3+sStepIndex_3+part4));
		java.util.List<WebElement> stepNoteArray_4 = driver.findElements(By.xpath(part5+sStepIndex_3+part6));
		String recentStepNoteTitle_2 = stepNoteArray_3.get(0).getText().toString();
		String recentStepNoteDescription_2 = stepNoteArray_4.get(0).getText().toString();
		Assert.assertTrue(recentStepNoteTitle_2.equals(explevelnote),"Experiment level note title mismatching in step 1");
		Assert.assertTrue(recentStepNoteDescription_2.equals(explevelnotedesc),"Experiment level note description mismatching in step 1");
		
		Reporter.log("Verify update step note after linking experiment level note to experiment step in experiment log");
		getWebElement("ExperimentLogButton").click();
		Thread.sleep(4000);
		String getlogText3 = driver.findElements(By.xpath("//div[@class='timeline-body']")).get(0).getText();
		boolean bVerifyLinkStepNote2 = getlogText3.contains("NOTE "+explevelnote+" moved to STEP "+step_1_name);
		Assert.assertTrue(bVerifyLinkStepNote2,"Activity log mismatching after doing action \"Link experiment level note to step\"");
		
		Reporter.log("Close experiment activity log");
		getWebElement("CloseExpLogsModal").click();
		Thread.sleep(2000);
		
		//Refresh browser
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		Reporter.log("View Step 3 notes");
		java.util.List<WebElement> viewNoteLink4 = driver.findElements(By.xpath("//a[contains(text(),'View notes')]"));
		for(int k=0;k<viewNoteLink4.size();k++) {
			java.util.List<WebElement> step_elements = driver.findElements(By.xpath(part9+(k+1)+part10));
			String stepName =  step_elements.get(0).getText().trim();
			if (stepName.equals(step_3_name)) {
				viewNoteLink4.get(k).click();
				Thread.sleep(3000);
				break;
			}
		}
		
		Reporter.log("Edit step 3 note title and description");
		java.util.List<WebElement> editStepNotelinks = driver.findElements(By.xpath("//a[contains(text(),'Edit')]"));
		for(int l=0;l<editStepNotelinks.size();l++) {
			String editNoteTitle = driver.findElement(By.xpath("(//a[contains(text(),'Edit')]/../..)["+(l+1)+"]//li[1]")).getText().trim();
			if(editNoteTitle.equals(step_1_note)) {
				editStepNotelinks.get(l).click();
				Thread.sleep(4000);
				getWebElement("AddStepNote").click();
				getWebElement("AddStepNote").clear();
				Thread.sleep(1000);
				getWebElement("AddStepNote").sendKeys(edit_step_3_note); // Step 1 note is linked to step 3
				WebElement addStepNoteDescFrame = getWebElement("AddStepNoteDescription");
				driver.switchTo().frame(addStepNoteDescFrame);
				WebElement addStepNoteDescBody = driver.findElement(By.tagName("body"));
				addStepNoteDescBody.click();
				addStepNoteDescBody.clear();
				Thread.sleep(2000);
				addStepNoteDescBody.sendKeys(edit_step_3_note_desc);
				driver.switchTo().defaultContent();
				getWebElement("SaveStepNote").click();
				Thread.sleep(6000);
				break;
			}
		}
		
		Reporter.log("Verify edited step note");
		java.util.List<WebElement> viewNoteLink_2 = driver.findElements(By.xpath("//a[contains(text(),'View notes')]"));
		for(int k=0;k<viewNoteLink_2.size();k++) {
			java.util.List<WebElement> step_elements = driver.findElements(By.xpath(part9+(k+1)+part10));
			String stepName =  step_elements.get(0).getText().trim();
			if (stepName.equals(step_3_name)) {
				viewNoteLink_2.get(k).click();
				Thread.sleep(3000);
				break;
			}
		}
		
		Reporter.log("Verify step note title and description");
//		String sStepIndex2 = Integer.toString((stepIndex_new+1));
//		java.util.List<WebElement> stepNoteArray5 = driver.findElements(By.xpath(part3+sStepIndex2+part4));
//		java.util.List<WebElement> stepNoteArray6 = driver.findElements(By.xpath(part5+sStepIndex2+part6));
//		String recentStepNoteTitle_edit = stepNoteArray5.get(0).getText().toString();
//		String recentStepNoteDescription_edit = stepNoteArray6.get(1).getText().toString();
//		Assert.assertTrue(recentStepNoteTitle_edit.equals(edit_step_3_note),"After editing step note title mismatching");
//		Assert.assertTrue(recentStepNoteDescription_edit.equals(edit_step_3_note_desc),"After editing step note description mismatching");
		
		//Scroll up
//		js.executeScript("scroll(0, -300);");
//		Thread.sleep(1000);
				
		Reporter.log("Verify edit step note activity in experiment log");
		getWebElement("ExperimentLogButton").click();
		Thread.sleep(4000);
		String getlogText4 = driver.findElements(By.xpath("//div[@class='timeline-body']")).get(0).getText();
		boolean bVerifyEditStepNoteTitle = getlogText4.contains("NOTE "+step_1_note+" title has been updated to "+edit_step_3_note);
		Assert.assertTrue(bVerifyEditStepNoteTitle,"Edit step note title update Activity mismatching after doing action \"Edit step note\"");
		boolean bVerifyEditStepNoteDesc1 = getlogText4.contains("content has been updated to");
		boolean bVerifyEditStepNoteDesc2 = getlogText4.contains(edit_step_3_note_desc);
		boolean bVerifyEditStepNoteDesc3 = getlogText4.contains("inside STEP "+step_3_name);
		boolean bVerifyEditStepNoteDesc = (bVerifyEditStepNoteDesc1 && bVerifyEditStepNoteDesc2 && bVerifyEditStepNoteDesc3);
		Assert.assertTrue(bVerifyEditStepNoteDesc,"Edit step note description update Activity mismatching after doing action \"Edit step note\"");
		
		Reporter.log("Close experiment activity log");
		getWebElement("CloseExpLogsModal").click();
		Thread.sleep(2000);
		
		Reporter.log("View Step 3 notes");
		java.util.List<WebElement> viewNoteLink5 = driver.findElements(By.xpath("//a[contains(text(),'View notes')]"));
		for(int k=0;k<viewNoteLink5.size();k++) {
			java.util.List<WebElement> step_elements = driver.findElements(By.xpath(part9+(k+1)+part10));
			String stepName =  step_elements.get(0).getText().trim();
			if (stepName.equals(step_3_name)) {
				viewNoteLink5.get(k).click();
				Thread.sleep(3000);
				break;
			}
		}
		
		Reporter.log("Delete step note from the experiment");
		//This step (Line number 474 needs to be generalized so that delete icon should clicked dynamically,This is a temporary solution)
		driver.findElement(By.xpath("(//div[@class='notesOutput step-note-new-wrapper'])[2]//i[@class='fa fa-trash']")).click();
		Thread.sleep(1000);
		getWebElement("Button_Ok2").click();
		Thread.sleep(3000);
	
		//int noOfStepDeletesIcons = driver.findElements(By.xpath("(//div[@class='notesOutput step-note-new-wrapper'])//i[@class='fa fa-trash']")).size();
		
		/* logic for the delete step note functionality pending
		for(int stepNoteInd=1;stepNoteInd<=noOfStepDeletesIcons;stepNoteInd++) {
			String part11 = "((//div[@class='notesOutput step-note-new-wrapper'])//i[@class='fa fa-trash']/../..)[";
			String part12 = "]//ul[@aria-labelledby='dropdownMenu2']//li[2]//a";
			String getStepNoteName = driver.findElement(By.xpath(part11+stepNoteInd+part12)).getText().trim().toString();
			if (getStepNoteName.equals(step_1_note)) {
				driver.findElement(By.xpath("(//div[@class='notesOutput step-note-new-wrapper'])["+stepNoteInd+"]//i[@class='fa fa-trash']")).click();
				Thread.sleep(3000);
				break;
			}
		}
		logic for the delete step note functionality pending */
		
		/*
		Reporter.log("Validate success message after deleting step note");
		String deleteSuccMsg = getWebElement("DeleteStepNoteSuccessMessage").getText().toString();
		Assert.assertEquals(deleteSuccMsg, "Success!Note Deleted Successfully!", "Step note not deleted successfully");
		*/
		//Scroll up
		js.executeScript("scroll(0, -250);");
		Thread.sleep(1000);
		
		Reporter.log("Verify delete step note activity in experiment log");
		getWebElement("ExperimentLogButton").click();
    	Thread.sleep(4000);
//		String getlogText5 = driver.findElements(By.xpath("//div[@class='timeline-body']")).get(0).getText();
//		boolean bVerifyDeleteStepNote = getlogText5.contains("NOTE "+edit_step_3_note+" is deleted from STEP "+step_3_name);
//		Assert.assertTrue(bVerifyDeleteStepNote,"Delete step note activity mismatching after doing action \"Delete step note\"");
		
		Reporter.log("Close experiment activity log");
		getWebElement("CloseExpLogsModal").click();
		Thread.sleep(2000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
}