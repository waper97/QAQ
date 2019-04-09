package com.yj.hqbz.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**  
 * @Title: XlsReadUtil.java
 * @Package com.xjt.util.tools
 * @Description: TODO
 * @author xx
 * @date 2016-4-25
 */
public class XlsReadUtil {
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String EMPTY = "";
	public static final String POINT = ".";
	
	private int DATA_BEGIN_CNT = 1;  //导入数据的起始行信息
	
	  /**
	   * 根据文件名获取文件名后缀
	   * @param path
	   * @return
	   */
	  private String getFileExt(String path){
		  if (path == null || "".equals(path.trim())) {
	          return "";
	      }
	      if (path.contains(".")) {
	          return path.substring(path.lastIndexOf(".") + 1, path.length());
	      }
	      return "";
	  }

	  /**
	   * 导入XLS文件
	   * @param fileName
	   * @param in
	   * @return
	   * @throws IOException
	   */
	  public List readExcel(String fileName,InputStream in) throws IOException{
	    	DATA_BEGIN_CNT = 2;
	    	String postfix = getFileExt(fileName);
	    	if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                return readXls(in,false);
            } else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                return readXlsx(in,false);
            }
			return null;
	    }
	  
	  
	  public List readExcel(String fileName,InputStream in,int beginCnt) throws IOException{
	    	DATA_BEGIN_CNT = beginCnt;
	    	String postfix = getFileExt(fileName);
	    	if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
            return readXls(in,false);
        } else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
            return readXlsx(in,false);
        }
			return null;
	    }
	    
	  //用于导入成绩时使用,从第二行开始
	  public List readExcel(String fileName,InputStream in,int beginCnt,boolean isScore) throws IOException{
	    	DATA_BEGIN_CNT = beginCnt;
	    	String postfix = getFileExt(fileName);
	    	if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
              return readXls(in,isScore);
          } else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
              return readXlsx(in,isScore);
          }
			return null;
	    }
	    

	    private List readXlsx(InputStream is,boolean isScore) throws IOException {
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

	        List list = new ArrayList();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            //Read the title	            
	            
	            // Read the Row，从第二行开始
	            for (int rowNum = DATA_BEGIN_CNT-1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null) {
	                	String no = getValue(xssfRow.getCell(0),false);
	                	if(no!=null && !"".equals(no) && no.indexOf("例")<0 && no.indexOf("勿删")<0){
	                		Map map = new LinkedHashMap();
		                	for( int columns=0;columns<xssfRow.getLastCellNum();columns++){
		                		map.put("columns_"+columns, getValue(xssfRow.getCell(columns),isScore));
		                	}
		                    list.add(map);
	                	}
//	                	else{  //序号为空，表示结束
//	                		 break;
//	                	}	                	
	                }
	            }
	            break;
	        }
	        is.close();
	        return list;
	    }	    
	    

	    
	    private List readXls(InputStream is,boolean isScore) throws IOException {
	        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	        List list = new ArrayList();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            // Read the Row
	            for (int rowNum = DATA_BEGIN_CNT-1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow != null) {
	                	String no = getValue(hssfRow.getCell(0),false);
	                	if(no!=null && !"".equals(no) && no.indexOf("例")<0 && no.indexOf("勿删")<0){
	                		Map map = new LinkedHashMap();
		                	for( int columns=0;columns<hssfRow.getLastCellNum();columns++){
		                		map.put("columns_"+columns, getValue(hssfRow.getCell(columns),isScore));
		                	}
		                	list.add(map);
	                	}
//	                	else {
//	                		//over
//	                		break;
//	                	}	                	
	                }
	            }
	            break;
	        }
	        is.close();
	        return list;
	    }

	    @SuppressWarnings("static-access")
	    private String getValue(XSSFCell xssfRow,boolean isScore) {
	    	
	    	if(xssfRow!=null){
	    		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
		            return String.valueOf(xssfRow.getBooleanCellValue());
		        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
		        	if(HSSFDateUtil.isCellDateFormatted(xssfRow)){
		        		//是日期格式
		        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
		        		if(xssfRow.getDateCellValue()!=null)
		        			return sdf.format(xssfRow.getDateCellValue());
		        		else 
		        			return "";
		        	}
		        	else { //是数字格式
		        		double value = xssfRow.getNumericCellValue();  
		        		CellStyle style = xssfRow.getCellStyle();  
		        		DecimalFormat format = new DecimalFormat();  
		        		String temp = style.getDataFormatString();  
		        		// 单元格设置成常规  
		        		if (temp.equals("General")) { 
		        			if(isScore)
		        				format.applyPattern("#.0");
		        			else 
		        				format.applyPattern("#");
		        		}  
		        		String result = format.format(value);
		        		result = result.replaceAll(" ", "");
		        		return result;
		        	}
		        }
		        else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_FORMULA){
		        	String cellValue;
		        	try {  
		                cellValue = xssfRow.getStringCellValue();  
		            } catch (IllegalStateException e) {  
		                cellValue = String.valueOf(xssfRow.getNumericCellValue());  
		            }
		        	return cellValue;
		        }
		        else {
		        	try{
			            String res =  xssfRow.getStringCellValue().trim();
			            if(res!=null && !res.equals("")){
			            	res = res.replaceAll(" ", "");
			            }
			        	return res;
		        	}
		        	catch(Exception ex){
		        		return "";
		        	}
		        }
	    	}
	    	else {
	    		return "";
	    	}	    	
	    }

	    @SuppressWarnings("static-access")
	    private String getValue(HSSFCell hssfCell,boolean isScore) {
	    	if(hssfCell!=null){
	    		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
		            return String.valueOf(hssfCell.getBooleanCellValue());
		        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
		        	if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
		        		//是日期格式
		        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
		        		if(hssfCell.getDateCellValue()!=null)
		        			return sdf.format(hssfCell.getDateCellValue());
		        		else 
		        			return "";
		        	}
		        	else { //是数字格式
		        		double value = hssfCell.getNumericCellValue();  
		        		CellStyle style = hssfCell.getCellStyle();  
		        		DecimalFormat format = new DecimalFormat();  
		        		String temp = style.getDataFormatString();  
		        		// 单元格设置成常规  
		        		if (temp.equals("General")) {  
		        			if(isScore)
		        				format.applyPattern("#.0");
		        			else 
		        				format.applyPattern("#");
		        		}  
		        		String result = format.format(value);
		        		result = result.replaceAll(" ", "");
		        		return result;
		        	}
		        } 
		        else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA){
		        	String cellValue;
		        	try {  
		                cellValue = hssfCell.getStringCellValue();  
		            } catch (IllegalStateException e) {  
		                cellValue = String.valueOf(hssfCell.getNumericCellValue());  
		            }
		        	return cellValue;
		        }
		        else {
		        	try{
			        	String res =  hssfCell.getStringCellValue().trim();
			            if(res!=null && !res.equals("")){
			            	res = res.replaceAll(" ","");
			            }
			        	return res;
		        	}
		        	catch(Exception ex){
		        		return "";
		        	}
		        }
	    	}
	    	else {
	    		return "";
	    	}
	        
	    }
	    
	    
	    public static void main(String args[]){

	    }
}
