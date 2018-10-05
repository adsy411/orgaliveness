package testScript;

import java.io.File;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import utills.ExcelUtils;
import org.jfree.chart.renderer.category.BarRenderer;
import java.awt.Color;

public class Test_Coverage extends ExcelUtils 
{
	public static void main(String[] args)
	{
		String userdir = System.getProperty("user.dir")+"//src//testScenarios";
		String testCoveragedir = System.getProperty("user.dir")+"//src//testCoverageReport";
		 try 
		 {
			 //Get the row count
			 double userMgmtAutomationScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//User_Management_Test_Scenarios.xlsx","UserMgmt_AUT_Scenarios");
			 double userMgmtManualScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//User_Management_Test_Scenarios.xlsx","UserMgmt_Manual_Scenarios");
			 double userMgmtTotalScenarios = userMgmtAutomationScenariosCount + userMgmtManualScenariosCount;
			 double userMgmtAutomationCoverage = (userMgmtAutomationScenariosCount/userMgmtTotalScenarios)*100;
			 double userMgmtManualCoverage = (userMgmtManualScenariosCount/userMgmtTotalScenarios)*100;
		
			 double inventoryAutomationScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Inventory_Test_Scenarios.xlsx","Inventory_AUT_Scenarios");
			 double inventoryManualScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Inventory_Test_Scenarios.xlsx","Inventory_Manual_Scenarios");
			 double inventoryTotalScenarios = inventoryAutomationScenariosCount + inventoryManualScenariosCount;
			 double inventoryAutomationCoverage = (inventoryAutomationScenariosCount/inventoryTotalScenarios)*100;
			 double inventoryManualCoverage = (inventoryManualScenariosCount/inventoryTotalScenarios)*100;
		
			 double researchAutomationScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Research_Test_Scenarios.xlsx","Research_AUT_Scenarios");
			 double researchManualScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Research_Test_Scenarios.xlsx","Research_Manual_Scenarios");
			 double researchTotalScenarios = researchAutomationScenariosCount + researchManualScenariosCount;
			 double researchAutomationCoverage = (researchAutomationScenariosCount/researchTotalScenarios)*100;
			 double researchManualCoverage = (researchManualScenariosCount/researchTotalScenarios)*100;
		
			 double commerceAutomationScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Commerce_Test_Scenarios.xlsx","Commerce_AUT_Scenarios");
			 double commerceManualScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Commerce_Test_Scenarios.xlsx","Commerce_Manual_Scenarios");
			 double commerceTotalScenarios = commerceAutomationScenariosCount + commerceManualScenariosCount;
			 double commerceAutomationCoverage = (commerceAutomationScenariosCount/commerceTotalScenarios)*100;
			 double commerceManualCoverage = (commerceManualScenariosCount/commerceTotalScenarios)*100;
		
			 double cartAutomationScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Cart_Test_Scenarios.xlsx","Cart_AUT_Scenarios");
			 double cartManualScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Cart_Test_Scenarios.xlsx","Cart_Manual_Scenarios");
			 double cartTotalScenarios = cartAutomationScenariosCount + cartManualScenariosCount;
			 double cartAutomationCoverage = (cartAutomationScenariosCount/cartTotalScenarios)*100;
			 double cartManualCoverage = (cartManualScenariosCount/cartTotalScenarios)*100;
		
			 double emailNotificationAutomationScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Email_Notification_Test_Scenarios.xlsx","Email_AUT_Scenarios");
			 double emailNotificationManualScenariosCount = ExcelUtils.getRowCountExcelFile(userdir+"//Email_Notification_Test_Scenarios.xlsx","Email_Manual_Scenarios");
			 double emailNotificationTotalScenarios = emailNotificationAutomationScenariosCount + emailNotificationManualScenariosCount;
			 double emailNotificationAutomationCoverage = (emailNotificationAutomationScenariosCount/emailNotificationTotalScenarios)*100;
			 double emailNotificationManualCoverage = (emailNotificationManualScenariosCount/emailNotificationTotalScenarios)*100;
		
			 // Creating a simple pie chart with 
			 DefaultCategoryDataset myPiedataset = new DefaultCategoryDataset( ); 
			 myPiedataset.addValue(userMgmtAutomationCoverage,"Automation Coverage", "User Mgmt");
			 myPiedataset.addValue(userMgmtManualCoverage,"Manual Coverage", "User Mgmt");
			 myPiedataset.addValue(inventoryAutomationCoverage,"Automation Coverage", "Inventory");
			 myPiedataset.addValue(inventoryManualCoverage,"Manual Coverage", "Inventory");
			 myPiedataset.addValue(researchAutomationCoverage,"Automation Coverage", "Research");
			 myPiedataset.addValue(researchManualCoverage,"Manual Coverage", "Research");
			 myPiedataset.addValue(commerceAutomationCoverage,"Automation Coverage", "Commerce");
			 myPiedataset.addValue(commerceManualCoverage,"Manual Coverage", "Commerce");
			 myPiedataset.addValue(cartAutomationCoverage,"Automation Coverage", "Cart");
			 myPiedataset.addValue(cartManualCoverage,"Manual Coverage", "Cart");
			 myPiedataset.addValue(emailNotificationAutomationCoverage,"Automation Coverage", "Email");
			 myPiedataset.addValue(emailNotificationManualCoverage,"Manual Coverage", "Email");
			 JFreeChart barChart = ChartFactory.createBarChart3D(
        		 "Release 1.18 Testing Coverage Report",
        		 "Module", 
        		 "Percentage",  
        		 myPiedataset, 
        		 PlotOrientation.VERTICAL, 
        		 true, 
        		 true, 
        		 false);
			
			//sbarChart.getPlot().setBackgroundPaint(Color.decode("#f0fff0"));
			CategoryPlot plot = barChart.getCategoryPlot();
			BarRenderer renderer = (BarRenderer) plot.getRenderer();

			renderer.setSeriesPaint(0, Color.decode("#00688B"));
			renderer.setSeriesPaint(1, Color.decode("#8B0000"));
			renderer.setDrawBarOutline(false);
			renderer.setItemMargin(0.1);
			
			int width = 700; // Width of the image               
			int height = 490;  //Height of the image 
			File barChart3D = new File( testCoveragedir+"//Release 1.18 Test Coverage Report.jpeg" );                            
			ChartUtilities.saveChartAsJPEG( barChart3D, barChart, width, height);
			 
			//Automation Coverage
			System.out.println("userMgmtAutomationCoverage - \n"+userMgmtAutomationCoverage);
			System.out.println("inventoryAutomationCoverage - \n"+inventoryAutomationCoverage);
			System.out.println("researchAutomationCoverage - \n"+researchAutomationCoverage);
			System.out.println("commerceAutomationCoverage - \n"+commerceAutomationCoverage);
			System.out.println("cartAutomationCoverage - \n"+cartAutomationCoverage);
			System.out.println("emailNotificationAutomationCoverage - \n"+emailNotificationAutomationCoverage);
			
			//Manual Coverage
			System.out.println("userMgmtManualCoverage - \n"+userMgmtManualCoverage);
			System.out.println("inventoryManualCoverage - \n"+inventoryManualCoverage);
			System.out.println("researchManualCoverage - \n"+researchManualCoverage);
			System.out.println("commerceManualCoverage - \n"+commerceManualCoverage);
			System.out.println("cartManualCoverage - \n"+cartManualCoverage);
			System.out.println("emailNotificationManualCoverage - \n"+emailNotificationManualCoverage);
		}    
         
		catch (Exception i)
        {
            System.out.println(i);
        }
	}		
}