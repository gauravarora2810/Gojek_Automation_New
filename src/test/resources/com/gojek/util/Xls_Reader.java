package test.resources.com.gojek.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Xls_Reader {
	
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private Workbook workbook = null;
	private Sheet sheet = null;
	private Row row   =null;
	private Cell cell = null;
	
	public Xls_Reader(String path) {
		
		this.path=path;
		try {
//			fis = new File(path);
			workbook = WorkbookFactory.create(new File(path));
			sheet = workbook.getSheetAt(0);
			//fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
		
	}
	
	// returns the data from a cell
	public String getCellData(String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);
		int col_Num=-1;
		if(index==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
		}
		if(col_Num==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
		//System.out.println(cell.getCellType());
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			  return cell.getStringCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell)) {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cal.get(Calendar.MONTH)+1 + "/" + 
		                      cellText;
		           
		           //System.out.println(cellText);

		         }

			  
			  
			  return cellText;
		  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}
	
	// returns the data from a cell
	public String getCellData(String sheetName,int colNum,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);

		if(index==-1)
			return "";
		
	
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(colNum);
		if(cell==null)
			return "";
		
	  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		  return cell.getStringCellValue();
	  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
		  
		  String cellText  = String.valueOf(cell.getNumericCellValue());
		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
	           // format in form of M/D/YY
			  double d = cell.getNumericCellValue();

			  Calendar cal =Calendar.getInstance();
			  cal.setTime(HSSFDateUtil.getJavaDate(d));
	            cellText =
	             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	           cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                      cellText;
	           
	          // System.out.println(cellText);

	         }

		  
		  
		  return cellText;
	  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
	      return "";
	  else 
		  return String.valueOf(cell.getBooleanCellValue());
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}
	
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
		if(colNum==-1)
			return false;

		sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    // cell style
	    //CellStyle cs = workbook.createCellStyle();
	    //cs.setWrapText(true);
	    //cell.setCellStyle(cs);
	    cell.setCellValue(data);

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
		//System.out.println("setCellData setCellData******************");
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		//System.out.println("A");
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
				colNum=i;
		}
		
		if(colNum==-1)
			return false;
		sheet.autoSizeColumn(colNum); //ashish
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);
			
	    cell.setCellValue(data);
	    CreationHelper createHelper = workbook.getCreationHelper();

	    //cell style for hyperlinks
	    //by default hypelrinks are blue and underlined
	    CellStyle hlink_style = workbook.createCellStyle();
	    Font hlink_font = workbook.createFont();
	    hlink_font.setUnderline(XSSFFont.U_SINGLE);
	    hlink_font.setColor(IndexedColors.BLUE.getIndex());
	    hlink_style.setFont(hlink_font);
	    //hlink_style.setWrapText(true);

	    Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_FILE);
	    link.setAddress(url);
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);
	      
	    fileOut = new FileOutputStream(path);
		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
  // find whether sheets exists	
	public boolean isSheetExist(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			index=workbook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
		}
		else
			return true;
	}
	
	// returns number of columns in a sheet	
	public int getColumnCount(String sheetName){
		// check if sheet exists
		if(!isSheetExist(sheetName))
		 return -1;
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		
		if(row==null)
			return -1;
		
		return row.getLastCellNum();
		
		
		
	}
	
	
	
}
