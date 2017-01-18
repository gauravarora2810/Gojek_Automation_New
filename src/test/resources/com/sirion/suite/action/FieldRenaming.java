package test.resources.com.sirion.suite.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FieldRenaming extends TestSuiteBase


{
	
	XSSFWorkbook workbook;
	XSSFSheet workbooksheet;
	// To open a file, we need a File Class object
	File file;
	// To read a file, we need a FileInputStream Class Object
	FileInputStream fis;
	FileOutputStream fos;
	WebElement fieldLabels;
	WebElement selectList;
	
	
	@Test
	 public FieldRenaming(WebDriver driver, WebDriverWait wait) throws Exception
	{
		
		System.out.println("Hello");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.navigate().to("http://tcl.k2.office:9090");

		driver.manage().window().maximize();
	

		// 1. Enter user id on login page
		driver.findElement(By.xpath(".//*[@id='user']")).clear();
		driver.findElement(By.xpath(".//*[@id='user']")).sendKeys(
				"tech_admin1");
		// driver.findElement(By.xpath(".//*[@id='user']")).sendKeys("gaurav.arora");

		// 2. Enter password on login page
		driver.findElement(By.xpath(".//*[@id='p1']")).clear();
		driver.findElement(By.xpath(".//*[@id='p1']")).sendKeys("admin12345");
		// driver.findElement(By.xpath(".//*[@id='p1']")).sendKeys("admin123");

		// 3. Click on go button to enter in menu/home page
		driver.findElement(By.xpath(".//*[@id='loginform']/div/div/div[2]/div[4]/input[2]")).click();

		// 4. click on field label link of client admin
		fieldLabels=driver.findElement(By.xpath(".//*[@id='mainContainer']/div/div[2]/div[1]/ul/li[15]/a"));
		fieldLabels.click();
		
		// 5. Take all options of drop down into a list 
		selectList=driver.findElement(By.xpath(".//*[@id='supplier']"));
		Select list = new Select(selectList);
		List<WebElement> listOptions = list.getOptions();
		
		workbook = new XSSFWorkbook();
		int sheetNumber = 1;
		
		// 6. take each option one by one and then take each field of every option then put into the auto created excel file 
		for (WebElement option : listOptions) 
		{
			
			if (!option.getText().equalsIgnoreCase("Select")) 
			{
				System.out.println("Changing the Values of Option: "+ option.getText());
				list.selectByValue(option.getAttribute("value"));
				List<WebElement> listOfFields = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//input[@type='text']")));
			
				//	text=wait.until(ExpectedConditions.visibilityOfAllElements(text));
				
				workbooksheet = workbook.createSheet(option.getText()+ ""+sheetNumber);
				sheetNumber++;
				int rownum = 0;								
					 for(int i=1; i<=listOfFields.size();i++)
					 {
					 String value = listOfFields.get(i-1).getAttribute("value");
					 Map<String, Object[]> data = new HashMap<String, Object[]>();
					 System.out.println("Map object  contains "+data);
					 Integer iobj=new Integer(i);
					 System.out.println("Integer object contains "+iobj);
					 String s=iobj.toString();
					 System.out.println("String object conatins "+s);
					 data.put(s, new Object[] {value+" "+"change"});
						
					 Set<String> keyset = data.keySet();
					 System.out.println("Set Object contains "+keyset);
					 for (String key : keyset) {
						 System.out.println("String Object conatins "+key);
					     Row row = workbooksheet.createRow(rownum++);
					     Object [] objArr = data.get(key);
					     int cellnum = 0;
					     for (Object obj : objArr) {
					         Cell cell = row.createCell(cellnum++);
					         
					         if(obj instanceof Boolean)
					             cell.setCellValue((Boolean)obj);
					         else if(obj instanceof String)
					             cell.setCellValue((String)obj);
					         else if(obj instanceof Double)
					             cell.setCellValue((Double)obj);
					     }
					 }
						}
				try {
					file=new File("E:\\NewGaurav.xlsx");
				    
				            fos= new FileOutputStream(file);
				     workbook.write(fos);
				     fos.close();
				     System.out.println("Excel written successfully..");
				      
				 } catch (FileNotFoundException e) {
				     e.printStackTrace();
				 } catch (IOException e) {
				     e.printStackTrace();
				 }
					

			} 
			
			
			else {
				System.out.println("I will iterate with next value: "
						+ option.getText());
					}
		}
	
		Thread.sleep(10000);
		
		// 7. Click on the administrator 
		driver.findElement(By.xpath(".//*[@id='h-adminstration']/a")).click();
		
		// 8. Click on the field label link
		driver.findElement(By.xpath(".//*[@id='mainContainer']/div/div[2]/div[1]/ul/li[15]/a")).click();
		
		// 9. take each field value(cell data value)  from excel sheet the insert each field back to the application
		selectList=driver.findElement(By.xpath(".//*[@id='supplier']"));
		
		
		list = new Select(selectList);
		listOptions = list.getOptions();
		workbook = new XSSFWorkbook();
		
		int k=1;
		for (WebElement option : listOptions){
			if (!option.getText().equalsIgnoreCase("Select")) {
				System.out.println("Changing the Values of Option: "+ option.getText());
				list.selectByValue(option.getAttribute("value"));
				file=new File("E:\\NewGaurav.xlsx");
				
				fis=new FileInputStream(file);
				
				workbook=new XSSFWorkbook(fis);
				System.out.println("value of k is "+k);
				
				workbooksheet=workbook.getSheet(option.getText()+""+k);
					k++;	
				int rownum=workbooksheet.getLastRowNum()+1;
						
				int colnum=workbooksheet.getRow(0).getLastCellNum();
				int i=0;
				List<WebElement> text1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//input[@type='text']")));
				
					
				for (WebElement field1 : text1) {	// inserting the values in the text field.
					
				
				XSSFRow row=workbooksheet.getRow(i);
			
				String[][] data=new String[rownum][colnum];
				for(int j=0; j<colnum;j++)
				{
					
				XSSFCell cell=row.getCell(j);
				String valueOfCell=cell.getStringCellValue();
				data[i][j]=valueOfCell;
				System.out.println(valueOfCell);
						field1.clear();
						field1.sendKeys(valueOfCell);
						i++;
				}	
				}
				
				// 10. Click on update button
				driver.findElement(By.xpath(".//*[@id='updateBtn']")).click();
				// 11. Click on ok button
				driver.findElement(By.xpath(".//*[@id='data-ng-app']/div[12]/div[3]/div/button")).click();
	
	}
		}
	}
}