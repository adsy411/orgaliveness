package utills;

public class RequestConstants {
	static String currentDir = System.getProperty("user.dir");
	static String currentDir2 = currentDir.replace("\\", "//");
	static String systemTestDataPath = currentDir2+"//src//testData//";
	
	public static final String Path_TestData = systemTestDataPath;
    public static final String File_TestData = "Request_TestData.xlsx";
    
	/*public static final String Path_TestData = "C://Users//adharmar//git//brightlab-qa-scripts//src//testData//";
    public static final String File_TestData = "Request_TestData.xlsx";*/

}