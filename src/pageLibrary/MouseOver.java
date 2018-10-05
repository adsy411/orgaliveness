package pageLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import testBase.TestBase;

public class MouseOver extends TestBase{
	
	public void InventoryMouseOver() throws Exception{
		
		WebElement hoverElement = getWebElement("Inventory.mouseover");
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElement).perform();
		
		/*WebElement mouseover = getWebElement("Inventory.mouseover");
		mouseover.click();*/
	
	}
	
public void OrdersMouseOver() throws Exception{
		
		WebElement hoverElement = getWebElement("Orders.mouseover");
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElement).perform();
	}

public void ResearchMouseOver() throws Exception{
	
	WebElement hoverElement = getWebElement("ResearchMouseOver");
	Actions builder = new Actions(driver);
	builder.moveToElement(hoverElement).perform();
}

public void SampleMouseOver() throws Exception{
	
	WebElement hoverElement = getWebElement("Sample.mouseover");
	Actions builder = new Actions(driver);
	builder.moveToElement(hoverElement).perform();
}

public void SampleAssayMouseOver() throws Exception{
	
	WebElement hoverElement = getWebElement("SampleAssay.mouseover");
	Actions builder = new Actions(driver);
	builder.moveToElement(hoverElement).perform();
}

public void ScientificDataMgmtMouseOver() throws Exception{
	
	WebElement hoverElement = getWebElement("ScientificDataMgmtMouseOver");
	Actions builder = new Actions(driver);
	builder.moveToElement(hoverElement).perform();
}

public void EHSInventoryMouseOver() throws Exception{
	
	WebElement hoverElement = getWebElement("EHSInvenMouseOver");
	Actions builder = new Actions(driver);
	builder.moveToElement(hoverElement).perform();
}


}
