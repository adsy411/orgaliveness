package labdrive;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import testBase.TestBase;

public class LabDriveRegularFunctions extends TestBase {
		
	//Lab drive dashboard navigation
	public boolean navigateToLabdriveDashboard() throws Exception {
		getWebElement("Navigate_LabDriveDashboard").click();
		Thread.sleep(3000);
		return true;
	}
	
	//Navigate to lab drive home page
	public boolean navigateToLabdriveHomePage(String groupName) throws Exception {
		switch(groupName)
		{
		case "Research" :
			getWebElement("View_Research_Files").click();
			Thread.sleep(2000);
			break;
			
		case "Inventory" :
			getWebElement("View_Inventory_Files").click();
			Thread.sleep(2000);
			break;
			
		case "Unassociated" :
			getWebElement("View_Lab_Files").click();
			Thread.sleep(2000);
			break;
			
		default : 
			System.out.println("Unknown lab drive group");
			break;
		}
		return true;
	}
	
	//Pagination count
	public int paginationCount() throws Exception {
		String getPaginationCount = getWebElement("Pagination").getText();
		String[] count_1 = getPaginationCount.split("of");
		String[] count_2 = count_1[1].split("Page");
		int count = Integer.parseInt(count_2[0].trim());
		return count;
	}
	
	//Create folder in lab drive home page
	public String createFolder(String folderName) throws Exception {
		
		//Initial pagination count
		int initialCount = paginationCount();
		
		getWebElement("New_Folder_Link").click();
		Thread.sleep(2000);
		getWebElement("Edit_Folder_Name").click();
		impliciteWait(2);
		getWebElement("Edit_Folder_Name").sendKeys(folderName);
		getWebElement("CreateFolder_Link").click();
		Thread.sleep(3000);
		
		//Final pagination count
		int finalCount = paginationCount();
		
		Reporter.log("Verify created folder");
		List<WebElement> getItems = driver.findElements(By.xpath("//span[@id='labDriveGridForm:driveGridPanel']//div[@class='file-name-wrapper text-center']"));
		
		String folderFound = null;
		for (int i=0;i<getItems.size();i++) {
			String getFolderName = getItems.get(i).findElement(By.tagName("a")).getText().trim();
			if (getFolderName == folderName) {
				folderFound = "YES";
				break;
			}
			else {
				folderFound = "NO";
			}
		}
		
		Assert.assertEquals(folderFound,"YES","Folder creation failed");
		Assert.assertEquals("Pagination count not updated successfully",(initialCount+1),finalCount);
		return folderName;
	}
	
	//Upload file
	public void uploadFile(String ExecfilePath) throws Exception {
	    Runtime.getRuntime().exec(ExecfilePath);
	    Thread.sleep(2000);
	}
}