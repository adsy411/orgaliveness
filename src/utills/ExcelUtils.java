package utills;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;

public class ExcelUtils {
	
	private static XSSFSheet ExcelWSheet;
	 
	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile(String Path,String SheetName) throws Exception {

		try {

			// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(Path);

		// Access the required test data sheet

		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e){

			throw (e);

		}

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public static String getCellData(int RowNum, int ColNum) throws Exception
{
		String CellData = null;
		try
		{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			
			//Check whether the value is string
			if(Cell.getCellType()== Cell.CELL_TYPE_STRING)
			{
				CellData = Cell.getStringCellValue();		
			}
			
			//Check whether the value is numeric
			else if(Cell.getCellType()== Cell.CELL_TYPE_NUMERIC  || Cell.getCellType()== Cell.CELL_TYPE_FORMULA)
			{
				DataFormatter fmt = new DataFormatter();
				CellData = fmt.formatCellValue(Cell);
				
				//Check the value is double
				if(CellData.contains("."))
				{
					CellData = String.valueOf(Cell.getNumericCellValue());
				}
				
				//Check the value is date
				else if (HSSFDateUtil.isCellDateFormatted(Cell))
                {
                    DateFormat df = new SimpleDateFormat("dd MMM YYYY");
                    Date date = Cell.getDateCellValue();
                    CellData = df.format(date);
                }
				else 
				{
					int intValue = (int) Cell.getNumericCellValue();
					CellData = String.valueOf(intValue);       
				}
			}
			else if(Cell.getCellType()== Cell.CELL_TYPE_BLANK)
			{
				CellData ="";
			}
			else if(Cell.getCellType()== Cell.CELL_TYPE_BOOLEAN)
			{
				CellData = String.valueOf(Cell.getBooleanCellValue());	
			}
		}
		
		catch(Exception e)
        {
            e.printStackTrace();
        }
		
		return CellData;
		
}

//This method is to write in the Excel cell, Row num and Col num are the parameters

public static void setCellData(String Result, int RowNum, int ColNum, String Path, String SheetName) throws Exception
{
	ExcelUtils.setExcelFile(Path, SheetName);
		try
		{
			Row  = ExcelWSheet.getRow(RowNum);
			if(Row ==null)
			{
				Row = ExcelWSheet.createRow(RowNum);
			}
			Cell = Row.getCell(ColNum);    
			//Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

			if (Cell == null) 
			{
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} 
			else 
			{
				Cell.setCellValue(Result);
			}

// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(Path);
				//FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData + Constants.File_TestData);

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();

			}catch(Exception e){

				throw (e);

		}

	}

	public static int getRowCountExcelFile(String Path,String SheetName) throws Exception 
	{
		ExcelUtils.setExcelFile(Path, SheetName);

		int rownum = ExcelWSheet.getLastRowNum();
		return rownum;
	}
}
