package Form;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataTestComponents.BaseTest;

public class FormTest extends BaseTest {

	
	@Test(dataProvider="driveTest")
	public void formfill(String name, String email, String password, String date) throws InterruptedException {

		
		
		String expected = "Success!";
		
		driver.findElement(By.xpath("(//input)[1]")).sendKeys(name);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys(date);
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String message = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']/strong")).getText();

		Assert.assertEquals(message, expected);
	}
}
