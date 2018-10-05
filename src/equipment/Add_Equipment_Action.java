package equipment;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import testBase.TestBase;
import utills.Equipment_Constants;
import utills.ExcelUtils;
import utills.Labdrive_Constants;

public class Add_Equipment_Action extends TestBase {
	
	@Test
	public void addSigmaAldrichEquipment() throws Exception {
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
		
		Reporter.log("Add sigma aldrich equipment");
		EquipmentFunc.addNewEquipment(equipmentName, manufacturer, type, vendor, description);
		
		Reporter.log("Logout from application");
		//lib.Logout();
	}
	
	@Test
	public void addThirdPartyEquipment() throws Exception {
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
		String equipmentName = ExcelUtils.getCellData(2, 0)+uniqueNumber;
		String manufacturer = ExcelUtils.getCellData(2, 1);
		String type = ExcelUtils.getCellData(2, 2);
		String vendor = ExcelUtils.getCellData(2, 3);
		String description = ExcelUtils.getCellData(2, 4);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to equipment list page");
		EquipmentFunc.navigateToEquipmentList();
		
		Reporter.log("Add third party equipment");
		EquipmentFunc.addNewEquipment(equipmentName, manufacturer, type, vendor, description);
		
		Reporter.log("Logout from application");
		//lib.Logout();
	}
	
	@Test
	public void add_NewManufacturer_NewType() throws Exception {
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
		String newManufacturer = ExcelUtils.getCellData(3, 1)+uniqueNumber;
		String newType = ExcelUtils.getCellData(3, 2)+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to equipment list page");
		EquipmentFunc.navigateToEquipmentList();
		
		Reporter.log("Add new manufacturer in add equipment modal");
		getWebElement("Add_Equipment").click();
		Thread.sleep(3000);
		lib.SelectByVisibleText("Add_Equipment_Manufacturer", "Add Manufacturer");
		getWebElement("Add_Other_Manufacturer").sendKeys(newManufacturer);
		getWebElement("Add_Other_Manufacturer").sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement manufacturerDropdown = getWebElement("Add_Equipment_Manufacturer");
		Select selectManufacturer = new Select(manufacturerDropdown);
		String getManfValue = selectManufacturer.getFirstSelectedOption().getText();
		Assert.assertTrue(getManfValue.equals(newManufacturer),"New manufacturer addition failed");
		
		Reporter.log("Add new type from in equipment modal");
		lib.SelectByVisibleText("Add_Equipment_Type", "Add Equipment Type");
		getWebElement("Add_Other_Type").sendKeys(newType);
		getWebElement("Add_Other_Type").sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement typeDropdown = getWebElement("Add_Equipment_Type");
		Select selectType = new Select(typeDropdown);
		String getTypeValue = selectType.getFirstSelectedOption().getText();
		Assert.assertTrue(getTypeValue.equals(newType),"New equipment type addition failed");
		
		//Cancel modal
		getWebElement("Button_Cancel").click();
		
		Reporter.log("Logout from application");
		//lib.Logout();
	}
	
	@Test
	public void val_DuplicateManufacturer_DuplicateType() throws Exception {
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
		String newManufacturer = ExcelUtils.getCellData(3, 1)+uniqueNumber;
		String newType = ExcelUtils.getCellData(3, 2)+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to equipment list page");
		EquipmentFunc.navigateToEquipmentList();
		
		Reporter.log("Add new manufacturer in add equipment modal");
		getWebElement("Add_Equipment").click();
		Thread.sleep(3000);
		
		for(int i=1;i<=2;i++) {
		lib.SelectByVisibleText("Add_Equipment_Manufacturer","Add Manufacturer");
		getWebElement("Add_Other_Manufacturer").sendKeys(newManufacturer);
		getWebElement("Add_Other_Manufacturer").sendKeys(Keys.TAB);
		Thread.sleep(2000);
		}
		String getValidationMsg_1 = getWebElement("Duplicate_Manufacturer_Msg").getText().trim();
		Assert.assertTrue(getValidationMsg_1.equals("Equipment Manufacturer already present"),"Duplicate manufacturer validation failed");
		
		Reporter.log("Add new type from in equipment modal");
		for(int i=1;i<=2;i++) {
		lib.SelectByVisibleText("Add_Equipment_Type", "Add Equipment Type");
		getWebElement("Add_Other_Type").sendKeys(newType);
		getWebElement("Add_Other_Type").sendKeys(Keys.TAB);
		Thread.sleep(2000);
		}
		String getValidationMsg_2 = getWebElement("Duplicate_Type_Msg").getText().trim();
		Assert.assertTrue(getValidationMsg_2.equals("Equipment type already present"),"Duplicate type validation failed");
		
		//Cancel modal
		getWebElement("Button_Cancel").click();
		
		Reporter.log("Logout from application");
		//lib.Logout();
	}
	
	@Test
	public void validationOfPredefinedManufacturerAndTypeLists() throws Exception {
		//Initialization of lab drive functions object
		EquipmentRegularFunctions EquipmentFunc = new EquipmentRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Equipment_Constants.File_TestData, "Equipment_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 0);
		String labOwnerPwd = ExcelUtils.getCellData(1, 1);
		
		ExcelUtils.setExcelFile(Labdrive_Constants.Path_TestData+ Equipment_Constants.File_TestData, "Create_Equipment");
		String manufacturerList = ExcelUtils.getCellData(4, 5);
		String typeList = ExcelUtils.getCellData(4, 6);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to equipment list page");
		EquipmentFunc.navigateToEquipmentList();
		
		Reporter.log("Add new manufacturer in add equipment modal");
		getWebElement("Add_Equipment").click();
		Thread.sleep(3000);
		
		String[] mfr = manufacturerList.split("<");
		String[] typ = typeList.split("<");
		
		Select manufacturerDropdown = new Select(getWebElement("Add_Equipment_Manufacturer"));
		List<WebElement> manufacturerDropDownList = manufacturerDropdown.getOptions();
		
		boolean mfr_match_found = false;
		for (int i=0; i < mfr.length; i++) {
			if (manufacturerDropDownList.get(i).getText().equals(mfr[i])) {
				mfr_match_found = true;
			}
			else {
				mfr_match_found = false;
			}
        }
		
		Assert.assertEquals(mfr_match_found, true, "Manufacturer dropdown mismatching w.r.t predefined list");
		
		Select typeDropdown = new Select(getWebElement("Add_Equipment_Type"));
		List<WebElement> typeDropDownList = typeDropdown.getOptions();
		
		boolean typ_match_found = false;
		for (int i=0; i < typ.length; i++) {
			if (typeDropDownList.get(i).getText().equals(typ[i])) {
				typ_match_found = true;
			}
			else {
				typ_match_found = false;
			}
        }
		
		Assert.assertEquals(typ_match_found, true, "Equipment types dropdown mismatching w.r.t predefined list");
		
		//Cancel modal
		getWebElement("Button_Cancel").click();
		
		Reporter.log("Logout from application");
		//lib.Logout();
	}
}