package equipment;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pageLibrary.Library;
import testBase.TestBase;

public class EquipmentRegularFunctions extends TestBase {
	
	//Equipment list navigation
	public boolean navigateToEquipmentList() throws Exception {
		//getWebElement("Side_Bar").findElements(By.tagName("li")).get(6).click();
		getWebElement("Navigate_EquipmentList").click();
		Thread.sleep(3000);
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
	
	//Add equipment
	public boolean addNewEquipment(String equipmentName, String manufacturerName, String equipmentType, String equipmentVendor, String equipmentDesc) throws Exception {

		//Objects initialization
		Library lib = new Library();
		
		//Initial pagination count
		int initialCount = paginationCount();
		
		//Add Equipment
		getWebElement("Add_Equipment").click();
		Thread.sleep(3000);
		getWebElement("Add_Equipment_Name").sendKeys(equipmentName);
		lib.SelectByVisibleText("Add_Equipment_Manufacturer", manufacturerName);
		lib.SelectByVisibleText("Add_Equipment_Type", equipmentType);
		lib.SelectByVisibleText("Add_Equipment_Vendor", equipmentVendor);
		getWebElement("Add_Equipment_Description").sendKeys(equipmentDesc);
		getWebElement("Save_Equipment").click();
		Thread.sleep(3000);
		
		//Final pagination count
		int finalCount = paginationCount();
		
		//Verify equipment in equipment list
		Assert.assertEquals("Equipment addition failed", finalCount, (initialCount+1));
		return true;
	}
}