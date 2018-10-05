package testScript;

import org.testng.annotations.Test;
import research.Delete_Experiment_Action;
import research.Delete_Project_Action;
import research.Delete_Protocol_Template_Action;

public class Research_General_Scenarios {

	@Test(priority = 1)
	public void DeleteProjectWithExperiment() throws Exception {
		Delete_Project_Action deleteProject = new Delete_Project_Action();
		deleteProject.deleteProjectFromProjectDetailPage_WithExperiment();
	}

	@Test(priority = 2)
	public void DeleteExperiment() throws Exception {
		Delete_Experiment_Action deleteExp = new Delete_Experiment_Action();
		deleteExp.deleteExperimentFromExperimentDetailPage();
	}

	@Test(priority = 3)
	public void DeleteProjectWithOutExperiment() throws Exception {
		Delete_Project_Action deleteProject = new Delete_Project_Action();
		deleteProject.deleteProjectFromProjectDetailPage_WithOutExperiment();
	}

	@Test(priority = 4)
	public void DeleteProtocolTemplate() throws Exception {
		Delete_Protocol_Template_Action deleteTemplate = new Delete_Protocol_Template_Action();
		deleteTemplate.deleteProtocolTemplate();
	}
}