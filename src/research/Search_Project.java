package research;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Search_Project extends TestBase {
	
	@Test
	public void search_project() throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		String currentDate = dateFormat.format(date).toString();
		Thread.sleep(5000);
	}
}