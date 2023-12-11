package Form;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DataTestComponents.BaseTest;

public class FormTest2 extends BaseTest {

	
	@Test(dataProvider="getData")
	public void formfill(HashMap<String,String>input) throws InterruptedException {

		
		
		String expected = "Success!";
		
		driver.findElement(By.xpath("(//input)[1]")).sendKeys(input.get("Name"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(input.get("Email"));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(input.get("Password"));
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys(input.get("Bday"));
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String message = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']/strong")).getText();

		Assert.assertEquals(message, expected);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//JsonData//DataJson.json");
 	
		return new Object[][] 	{{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}};
	
	
	
	}
	
}
