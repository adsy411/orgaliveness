package testScript;

import org.testng.annotations.Test;
import research.AssignCodeToProject_Action;
import research.Create_Project_Action;
import research.Edit_Project_Action;
import research.ProjectListPageFunctionalities_Action;

public class Project_Scenarios {

	@Test(priority = 1)
	public void CreateProjectWithScopeAsLab() throws Exception {
		Create_Project_Action NewProj = new Create_Project_Action();
		NewProj.createLabProject();
	}

	@Test(priority = 2)
	public void CreateProjectWithScopeAsRestricted() throws Exception {
		Create_Project_Action NewProj = new Create_Project_Action();
		NewProj.createRestrictedProject();
	}

	@Test(priority = 3)
	public void EditProjectDescription() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.editProjectDescription();
	}

	@Test(priority = 4)
	public void AddProjectNotes() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.addProjectNotes();
	}

	@Test(priority = 5)
	public void EditProjectNotes() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.editProjectNotes();
	}

	@Test(priority = 6)
	public void DeleteProjectNotes() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.deleteProjectNotes();
	}

	@Test(priority = 7)
	public void AddProjectAttachment() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.addProjectAttachment();
	}

	@Test(priority = 8)
	public void AssignCodeToProject() throws Exception {
		AssignCodeToProject_Action AssignCodeToProj = new AssignCodeToProject_Action();
		AssignCodeToProj.assignCodeToProject();
	}

	@Test(priority = 9)
	public void ProjectList_SwitchView() throws Exception {
		ProjectListPageFunctionalities_Action projListFunc = new ProjectListPageFunctionalities_Action();
		projListFunc.switchProjectListView();
	}

	@Test(priority = 10)
	public void FilterProjectList_ByCreator_ByDateRange_ByStatus() throws Exception {
		ProjectListPageFunctionalities_Action projListFunc = new ProjectListPageFunctionalities_Action();
		projListFunc.projectListFilters();
	}

	@Test(priority = 11)
	public void DownloadProjectList() throws Exception {
		ProjectListPageFunctionalities_Action download = new ProjectListPageFunctionalities_Action();
		download.downloadProjectList();
	}
}