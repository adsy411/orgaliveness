package utills;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelSheet {
	
	public static void main(String[] args) throws Exception{
		
		File src = new File("");
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		
		
		
		
	}

}
