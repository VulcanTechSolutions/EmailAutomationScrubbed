package ramsales;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import unsub.XLSXStreamCrossException;

public class ParseExcel {
	
	public void writeUpdatedOptOutList(String excelFilePath, ArrayList<String> valuesToWrite) throws IOException {

		File file = new File(excelFilePath);
		
		FileInputStream inputStream = new FileInputStream(file);

	    Workbook workbook = new XSSFWorkbook(inputStream);
	    
	    try {	//catches "sheet already exists at "1"
	    workbook.createSheet("1");}catch (IllegalArgumentException e) {}
	    
	    Sheet sheet = workbook.getSheetAt(1);
	    Row row;
	    Cell cell;
	    	   
	    for(int i = 0; i<valuesToWrite.toArray().length; i++) {
	    	
	    	row = sheet.createRow(i);
	    	
	    	cell = row.createCell(0);
	    	
	    	cell.setCellValue(" ");
	    	cell.setCellValue(valuesToWrite.toArray()[i].toString());
	    }
	    try {inputStream.close();} catch(IOException e) {workbook.close(); throw new XLSXStreamCrossException();}
	    
	    
	    FileOutputStream outputWriter = new FileOutputStream(excelFilePath);
		workbook.write(outputWriter);
		try {
        workbook.close();
        outputWriter.close();
		}catch (IOException e) {throw new XLSXStreamCrossException();}
}
	    
	public ArrayList<String> getMasterFromExcel(String excelFilePath) throws IOException {
	
	//find .xlsx file and set it to the object "workbook"
	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

    Workbook workbook = new XSSFWorkbook(inputStream);

    //the file will always have a sheet, and there should only be 1 at index 0
    Sheet sheet = workbook.getSheetAt(0);
	
    
    //first we need a row iterator for our current sheet:
    Iterator<Row> rowIterator = sheet.iterator();

    
    //this is setup for our return values:
    String currentCellString =  "intialize";
    //ArrayList<String> rowList = new ArrayList<String>();
    ArrayList<String> addressList = new ArrayList<>();
    //this is the outer try-catch, it'll catch no element exception when there are no rows left
    try {
        while(true) {
    	Row row = rowIterator.next();
   
    	Iterator<Cell> cellIterator = row.cellIterator();
    	Cell currentCellObject = cellIterator.next();	      
    	try {
    		while (true) {	
    			String cellAddressString = currentCellObject.getAddress().toString();
    			if(cellAddressString.contains("F") && !cellAddressString.contains("F1")) {
    				currentCellString = getCellValue(currentCellObject);
    				addressList.add(currentCellString);
    			}
    			currentCellObject = cellIterator.next(); 
    		}  
    	}catch(NoSuchElementException e) {	//no cells left in row 
    		}
        }//main while loop block 
    }catch(NoSuchElementException e) {
    	workbook.close();
    	return addressList;
    }
}

	
	
	
	public ArrayList<String> getOptOutFromExcel(String excelFilePath) throws IOException{
//There isn't a header, which means we don't need to omit cell A1. 
	
		//find .xlsx file and set it to the object "workbook"
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

	    Workbook workbook = new XSSFWorkbook(inputStream);

	    Sheet sheet = workbook.getSheetAt(1);
		
	    
	    //row iterator for our current sheet:
	    Iterator<Row> rowIterator = sheet.iterator();

	    
	    //setup for return values:
	    String currentCellString =  "intialize";
	    ArrayList<String> addressList = new ArrayList<>();
	   
	    //this is the outer try-catch, it'll catch no element exception when there are no rows left
	    
	    try {
	    
	    //this while loop is the loop for iterating rows	
	    while(true) {
	    	Row row = rowIterator.next();
	  
	   //this is the cell iterator, we need to make a new one for each row which is why it's in the while loop
	   
	   Iterator<Cell> cellIterator = row.cellIterator();
	    Cell currentCellObject = cellIterator.next();	    
	   
	    
	    try {		//this is where we get the cells in a row 
	    			//RAM here is where we're going to get the address of cells, check their address against the address value we need, and 
	    			//if the check passes we get the string value of the cell which will be one singular client email address. 
	   while (true) {	
	    	
		   //if(cellAddressString.contains("A") && !cellAddressString.contains("A1")) {
	   		currentCellString = getCellValue(currentCellObject);
	   		addressList.add(currentCellString);
	   	//}
	    currentCellObject = cellIterator.next(); 
	   }
	    
	  }catch(NoSuchElementException e) {	//no cells left in row
		  
	  }
	 }//main while loop block 
	    }catch(NoSuchElementException e) { //this is the return part of the method as well as teardown
	    	workbook.close();

	    	return addressList;
	    }
	}

private static String getCellValue(Cell currentCell ) {

	
if(currentCell.getAddress().toString().contains("I") && !currentCell.getAddress().toString().contains("I1")) {
return  currentCell.getDateCellValue().toString();
}		
	
if((currentCell.getCellType().toString()).equals("BOOLEAN")) {
boolean boolIMP = currentCell.getBooleanCellValue();
String boolReturn = "" + boolIMP;
return boolReturn;
}

if(currentCell.getCellType().toString().equals("NUMERIC")){
double temp = currentCell.getNumericCellValue();
String doubleToString = "" + temp;
return doubleToString;
}
if(currentCell.getCellType().toString().equals("STRING")) {
String cellValue = currentCell.getStringCellValue();
return cellValue;
}
//error handler
String oops = "Oopsies on:\t" + currentCell.getAddress().toString();
System.out.println(oops);
return oops;
	
}

}//Class block