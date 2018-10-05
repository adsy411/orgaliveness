package utills;

public class Equipment_Constants {
	
	static String currentDir = System.getProperty("user.dir");
	static String currentDir2 = currentDir.replace("\\", "//");
	static String systemTestDataPath = currentDir2+"//src//testData//";
	
	public static final String Path_TestData = systemTestDataPath;
    public static final String File_TestData = "Equipment_TestData.xlsx";
	
}