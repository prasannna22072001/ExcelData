package DataTestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	DataFormatter formatter = new DataFormatter();

	
	@BeforeMethod(alwaysRun=true)
	public WebDriver startDriver() {
		
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().window().maximize();
		return driver;
	}
	
	
	
	@DataProvider(name="driveTest")
	public Object[][] getData() throws IOException {
		
		//every row of excel should be sent to one array
		
		FileInputStream fis = new FileInputStream("D:\\Data.xlsx");
		XSSFWorkbook wb =new XSSFWorkbook(fis); 
		XSSFSheet sheet =  wb.getSheetAt(1);
		int rowCount = sheet.getPhysicalNumberOfRows();		
		XSSFRow row= sheet.getRow(0);
		int columnCount = row.getLastCellNum();
	
		Object data[][] = new Object[rowCount-1][columnCount];
		
		for (int i =0; i<rowCount-1; i++) {
			
			row = sheet.getRow(i+1);
			for(int j=0;j<columnCount;j++) {
				
				XSSFCell cell = row.getCell(j);
				
				data[i][j] = formatter.formatCellValue(cell);
			}
			
		}
		
		return data;
	
	
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file );
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	
}
	
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		
		//string to hashmap - jackson Databind
		
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	
		return data;
	}
	
	
	
	
	
	
	
}
