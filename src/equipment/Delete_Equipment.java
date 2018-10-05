package equipment;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import testBase.TestBase;
import utills.Equipment_Constants;
import utills.ExcelUtils;
import utills.Labdrive_Constants;

public class Delete_Equipment extends TestBase {
	
	@Test
	public void deleteEquipment_EquipmentList() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//Initialization of lab drive functions object
		EquipmentRegularFunctions EquipmentFunc = new EquipmentRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Equipment_Constants.File_TestData, "Equipment_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Equipment_Constants.File_TestData, "Create_Equipment");
		String equipmentName = ExcelUtils.getCellData(1, 0)+uniqueNumber;
		String manufacturer = ExcelUtils.getCellData(1, 1);
		String type = ExcelUtils.getCellData(1, 2);
		String vendor = ExcelUtils.getCellData(1, 3);
		String description = ExcelUtils.getCellData(1, 4);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to equipment list page");
		EquipmentFunc.navigateToEquipmentList();
		
		Reporter.log("Add new equipment");
		EquipmentFunc.addNewEquipment(equipmentName, manufacturer, type, vendor, description);
		
		//Initial pagination count
		int initialCount = EquipmentFunc.paginationCount();
		
		Reporter.log("Delete equipment from equipment list");
		driver.findElement(By.xpath("//a[@href='#'][contains(text(),'"+equipmentName+"')]/../../../..//i[@class='fa fa-trash']")).click();
		Thread.sleep(2000);
		getWebElement("Button_Yes").click();
		Thread.sleep(6000);
		
		//Final pagination count
		int finalCount = EquipmentFunc.paginationCount();
		
		Reporter.log("Verify delete equipment from equipment list");
		Assert.assertEquals("Delete equipment failed", (initialCount-1), finalCount);
	}
	
	@Test
	public void deleteEquipment_EquipmentDetailPage() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//Initialization of lab drive functions object
		EquipmentRegularFunctions EquipmentFunc = new EquipmentRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Equipment_Constants.File_TestData, "Equipment_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Equipment_Constants.File_TestData, "Create_Equipment");
		String equipmentName = ExcelUtils.getCellData(1, 0)+uniqueNumber;
		String manufacturer = ExcelUtils.getCellData(1, 1);
		String type = ExcelUtils.getCellData(1, 2);
		String vendor = ExcelUtils.getCellData(1, 3);
		String description = ExcelUtils.getCellData(1, 4);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to equipment list page");
		EquipmentFunc.navigateToEquipmentList();
		
		Reporter.log("Add new equipment");
		EquipmentFunc.addNewEquipment(equipmentName, manufacturer, type, vendor, description);
		
		//Initial pagination count
		int initialCount = EquipmentFunc.paginationCount();
		
		Reporter.log("Navigate to equipment detial page");
		driver.findElement(By.linkText(equipmentName)).click();
		Thread.sleep(5000);
		
		Reporter.log("Delete equipment from equipment detail page");
		getWebElement("Button_Delete").click();
		Thread.sleep(2000);
		getWebElement("Button_Yes").click();
		Thread.sleep(6000);
		
		//Final pagination count
		int finalCount = EquipmentFunc.paginationCount();
		
		Reporter.log("Verify delete equipment from equipment list");
		Assert.assertEquals("Delete equipment failed", (initialCount-1), finalCount);
	}
}