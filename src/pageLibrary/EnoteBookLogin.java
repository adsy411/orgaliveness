package pageLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import utills.Constants;
import utills.ExcelUtils;

public class EnoteBookLogin {
	
static WebDriver driver;
	
	
	@BeforeTest
	public static void Login() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_TestData,"Sheet1");
		
		driver = new FirefoxDriver();

	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    driver.get("http://stlpochdp06:8081/jsf_frontend_evaluation/faces/loginMarketing.xhtml");
		
	    //SignIn_Action.ExecuteLogin(driver);
		
		
	}

}
