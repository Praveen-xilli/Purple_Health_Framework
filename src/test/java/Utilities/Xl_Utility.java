package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xl_Utility {
	public String path;
	public FileInputStream fi;
	public FileOutputStream fo;
	public Workbook wb;
	public Sheet sheet;
	public Row row;
	public Cell cell;
	public CellStyle style;
	
	public Xl_Utility(String path)
	{
		this.path = path;
	}
	
	public int getrowcount(String Sheetname) throws IOException
	{
		fi = new FileInputStream(path);
		if(path.indexOf(".xlsx")>0)
		{
			wb = new XSSFWorkbook(fi);
		}
		else if(path.indexOf(".xls")>0)
		{
			wb = new HSSFWorkbook(fi);
		}
		sheet = wb.getSheet(Sheetname);
		int rowcount = sheet.getPhysicalNumberOfRows();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public int getcellcount(String Sheetname,int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		if(path.indexOf(".xlsx")>0)
		{
			wb = new XSSFWorkbook(fi);
		}
		else if(path.indexOf(".xls")>0)
		{
			wb = new HSSFWorkbook(fi);
		}
		sheet = wb.getSheet(Sheetname);
		row = sheet.getRow(rownum);
		int cellcount = row.getPhysicalNumberOfCells();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public String getCelldata(String Sheetname,int rownum,int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		if(path.indexOf(".xlsx")>0)
		{
			wb = new XSSFWorkbook(fi);
		}
		else if(path.indexOf(".xls")>0)
		{
			wb = new HSSFWorkbook(fi);
		}
		sheet = wb.getSheet(Sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public void setCelldata(String Sheetname,int rownum,int colnum,String data) throws IOException
	{
		File xlfile = new File(path);
		if(!xlfile.exists())
		{
			fo = new FileOutputStream(xlfile);
			if(path.indexOf(".xlsx")>0)
			{
				wb = new XSSFWorkbook();
			}
			else if(path.indexOf(".xls")>0)
			{
				wb = new HSSFWorkbook();
			}
			fo = new FileOutputStream(xlfile);
			wb.write(fo);
		}
		fi = new FileInputStream(path);
		if(path.indexOf(".xlsx")>0)
		{
			wb = new XSSFWorkbook(fi);
		}
		else if(path.indexOf(".xls")>0)
		{
			wb = new HSSFWorkbook(fi);
		}
		if(wb.getSheetIndex(Sheetname) == -1)
		{
			wb.createSheet(Sheetname);
		}
		sheet = wb.getSheet(Sheetname);
		if(sheet.getRow(rownum)== null)
		{
			sheet.createRow(rownum);
		}
		row = sheet.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillGreencolor(String sheetname,int rownum,int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		if(path.indexOf(".xlsx")>0)
		{
			wb = new XSSFWorkbook(fi);
		}
		else if(path.indexOf(".xls")>0)
		{
			wb = new HSSFWorkbook(fi);
		}
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		wb.write(fo);
		fi.close();
		fo.close();
	}
//	public String readDataFromExcel(String colName, String fnName, String excelSheetName) throws IOException {
//        String colValue = "";
//        int colPos = 0;
//        int rowPos = 0;
//
//        FileInputStream fis = new FileInputStream(new File(path));
//        Workbook wb = null;
//
//        // Check file extension and create workbook accordingly
//        if (path.endsWith(".xlsx")) {
//            wb = new XSSFWorkbook(fis);  // For .xlsx files
//        } else if (path.endsWith(".xls")) {
//            wb = new HSSFWorkbook(fis);  // For .xls files
//        }
//
//        // Default to the first sheet if no sheet name is provided
//        Sheet sheet = wb.getSheet(excelSheetName != null && !excelSheetName.isEmpty() ? excelSheetName : wb.getSheetName(0));
//        Iterator<Row> rowIterator = sheet.iterator();
//
//        while (rowIterator.hasNext()) 
//        {
//            Row row = rowIterator.next();
//            int cellCount = row.getLastCellNum();
//            
//            // Add columns if necessary
//            if (rowPos == 0) 
//            {
//                // Add columns to DataTable (for testing purposes, you may implement a DataTable equivalent here)
//            }
//
//            // Iterate through the cells in each row
//            for (int i = 0; i < cellCount; i++) 
//            {
//                Cell cell = row.getCell(i);
//                if (cell != null) 
//                {
//                    String value = cell.toString();
//                    if (value.equals(colName)) 
//                    {
//                        colPos = i;
//                    }
//                    if (value.equals(fnName)) 
//                    {
//                        rowPos = row.getRowNum();
//                    }
//                }
//            }
//
//            // If both column and row positions are found, break the loop
//            if (colPos != 0 && rowPos != 0) 
//            {
//                break;
//            }
//        }
//
//        // Get the value at the intersection of the specified column and row
//        if (colPos != 0 && rowPos != 0) 
//        {
//            Row row = sheet.getRow(rowPos);
//            Cell cell = row.getCell(colPos);
//            colValue = cell != null ? cell.toString() : "";
//        }
//
//        fis.close();
//        return colValue;
//    }
//
}
