package pageLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Lib {
	
	static WebDriver driver;
	
	public static void InventoryMouseOver(){
		
		WebElement hoverElement = driver.findElement(By
				.xpath(".//*[@id='sidebar_inventory']/a"));
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElement).perform();
		
	}

}
