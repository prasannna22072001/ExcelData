package Form;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {

	DataFormatter formatter = new DataFormatter();
	
	@Test(dataProvider="driveTest")
	public void testCaseData(String greeting, String communication,String id)
	{
	
		System.out.println(greeting + communication +id);
	
	}
	
	
	
	
	@DataProvider(name="driveTest")
	public Object[][] getData() throws IOException {
		
		//every row of excel should be sent to one array
		
		FileInputStream fis = new FileInputStream("D:\\Data.xlsx");
		XSSFWorkbook wb =new XSSFWorkbook(fis); 
		XSSFSheet sheet =  wb.getSheetAt(0);
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
	
}
