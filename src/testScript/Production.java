package testScript;

import org.testng.annotations.Test;

import production.ProdtestScenarios_Action;
import testBase.TestBase;
import userManagement.ChangePassword_Action;

public class Production extends TestBase{
	
	@Test(priority = 1)
	public void ProdtestScenarios() throws Exception{
		ProdtestScenarios_Action cpa = new ProdtestScenarios_Action();
		cpa.ProdtestScenarios();	
	}

}
