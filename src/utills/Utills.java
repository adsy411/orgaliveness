package utills;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import testBase.TestBase;



public class Utills {
	
	public static WebDriver driver;
	
	@Parameters("browsers")
	public static WebDriver SelectBrowser(String Browser){
		if (Browser.equals("Firefox") || (Browser.equals("FIREFOX"))){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			//driver.manage().window().maximize();
			return driver;
		}
		/*else if (Browser.equals("Mozilla") || (Browser.equals("MOZILLA"))){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\drivers\\geckodriver.exe");
			driver = new MozillaDriver();
			driver.manage().window().maximize();
			return driver;
		}*/
		else if (Browser.equals("Chrome") || (Browser.equals("CHROME"))){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		}
		else if (Browser.equals("ie") || (Browser.equals("IE"))){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			return driver;
		}
		return null;
	}
	
	public void implicitwait(int timeInsec){
		try{
			driver.manage().timeouts().implicitlyWait(timeInsec, TimeUnit.SECONDS);
		}
		catch(Throwable error){
			Assert.assertTrue(false, "Timeout for page load request after "+timeInsec+" seconds");
		}
	}
	
	
	
	public void impliciteWait(int timeToWaitInSec) {
		  driver.manage().timeouts().implicitlyWait(timeToWaitInSec, TimeUnit.SECONDS);
		 }
	
	public void expliciteWait(By by, int timeToWaitInSec) {
		  WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		  wait.until(ExpectedConditions.presenceOfElementLocated(by));
		 }
	
	public static void captureScreenshot(String screenshotName){
		
		try {
			TakesScreenshot ts = (TakesScreenshot)driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"));
			
			System.out.println("Screenshot Taken");
		} 
		catch (Exception e) {
			
			System.out.println("Exception while taking screenshot "+e.getMessage());
		} 
		
	}
	
	public void waitForXpathPresent(String wbxpath){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(wbxpath)));
	}
	
	public boolean verifyText(String textXpath , String expectedtext){
		boolean flag = false;
       String actText = driver.findElement(By.xpath
    		                               (textXpath)).getText();
		
		if(expectedtext.equals(actText)){
			flag = true;
			System.out.println(expectedtext + " is verified ==>PASS");
		}else{
			System.out.println(expectedtext + " is not verified ==>FAIL");

		}
		return flag;
	}
	
	public static void ScrollUp(){
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, -250);");
	}
	
	public static void ScrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 250);");
	}
	
	/*public static void InnerScroll(){
	JavascriptExecutor je = (JavascriptExecutor) driver;
	WebElement element = driver
			.findElement(By
					.xpath(".//*[@id='materialPageForm:vendorFilters']/tbody/tr[13]/td/label"));
	je.executeScript("arguments[0].scrollIntoView(true);", element);

	}*/
	
	public static WebDriver getHandleToWindow(String title){

        //parentWindowHandle = WebDriverInitialize.getDriver().getWindowHandle(); // save the current window handle.
        WebDriver popup = null;
        Set<String> windowIterator = driver.getWindowHandles();
        System.err.println("No of windows :  " + windowIterator.size());
        for (String s : windowIterator) {
          String windowHandle = s; 
          popup = driver.switchTo().window(windowHandle);
          System.out.println("Window Title : " + popup.getTitle());
          System.out.println("Window Url : " + popup.getCurrentUrl());
          if (popup.getTitle().equals(title) ){
              System.out.println("Selected Window Title : " + popup.getTitle());
              return popup;
          }

        }
                System.out.println("Window Title :" + popup.getTitle());
                System.out.println();
            return popup;
        }

	
	public void explicitWaitForElement(String locator) throws Exception
	{
		try
		{
			TestBase tb = new TestBase();
			WebElement element = tb.getWebElement(locator);	
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void explicitWaitForElementUntilClickable(String locator) throws Exception
	{
		try
		{
			TestBase tb = new TestBase();
			WebElement element = tb.getWebElement(locator);	
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static boolean explicitWaitUntilElementIsInvisible(String locator) throws Exception 
	{
		TestBase tb = new TestBase();
	    try 
	    {
	    	WebElement element = tb.getWebElement(locator);
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.invisibilityOf(element));
	        return element.isDisplayed();
	    } 
	    catch (org.openqa.selenium.NoSuchElementException
	            | org.openqa.selenium.StaleElementReferenceException
	            | org.openqa.selenium.TimeoutException e) 
	    {
	        return false;
	    }
	}
}
