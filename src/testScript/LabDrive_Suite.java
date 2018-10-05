package testScript;

import org.testng.annotations.Test;
import labdrive.CreateFolder_Action;
import labdrive.UploadFile_Action;

public class LabDrive_Suite {

	@Test(priority = 1)
	public void CreateFolderInResearchHomePage() throws Exception {
		CreateFolder_Action CreateFol = new CreateFolder_Action();
		CreateFol.createFolderInResearchHomePage();
	}
	
	@Test(priority = 2)
	public void CreateFolderInInventoryHomePage() throws Exception {
		CreateFolder_Action CreateFol = new CreateFolder_Action();
		CreateFol.createFolderInInventoryHomePage();
	}
	
	@Test(priority = 3)
	public void CreateFolderInUnassociatedHomePage() throws Exception {
		CreateFolder_Action CreateFol = new CreateFolder_Action();
		CreateFol.createFolderInUnassociatedGroupHomePage();
	}
	
	@Test(priority = 4)
	public void UploadFileInResearchHomePage() throws Exception {
		UploadFile_Action UploadFile = new UploadFile_Action();
		UploadFile.UploadFileInResearchHomePage();
	}
	
	@Test(priority = 5)
	public void UploadFileInInventoryHomePage() throws Exception {
		UploadFile_Action UploadFile = new UploadFile_Action();
		UploadFile.UploadFileInInventoryHomePage();
	}
	
	@Test(priority = 6)
	public void UploadFileInUnassociatedHomePage() throws Exception {
		UploadFile_Action UploadFile = new UploadFile_Action();
		UploadFile.UploadFileInUnassociatedHomePage();
	}
}