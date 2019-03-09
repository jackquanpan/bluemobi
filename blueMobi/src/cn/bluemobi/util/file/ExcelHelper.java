package cn.bluemobi.util.file;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import cn.bluemobi.util.helper.ValidateHelper;

/**
 * excel导出工具类
 * @author Chenwq
 *
 */
public class ExcelHelper {

	/**
	 * 导出excel
	 * @param headers
	 * @param bodyContent
	 * @param fileName
	 * @param response
	 */
	public static void exportExcel(List<String> headers, List<Object[]> bodyContent, String fileName, HttpServletResponse response){
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 产生工作表对象
			HSSFSheet sheet = workbook.createSheet();
			
			// sheet表、单元格样式、数据集合
			createExcelHeader(workbook, sheet, headers);
			
			// sheet表、body的数据集合
			createExcelBody(workbook, sheet, bodyContent);
			
			OutputStream out = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setCharacterEncoding("UTF-8");
			
			// 设置返回的头字段:http协议inline采用浏览器方式打开、attachment采用本地EXCEL方式打开
			response.setHeader("Content-disposition", "inline;filename = "+ java.net.URLEncoder.encode(fileName, "UTF-8") + ".xls");
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建excel表头
	 * @param workbook
	 * @param sheet
	 * @param lists
	 */
	private static void createExcelHeader(HSSFWorkbook workbook, HSSFSheet sheet,
			List<String> lists) {
		// 设置第一个工作表的名称为firstSheet
		workbook.setSheetName(0, "sheet");
		sheet.setDefaultColumnWidth(25);// 设置默认每一列的宽度
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;
		for (int i = 0; i < lists.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(lists.get(i));
			cell.setCellStyle(cellStyle);
		}
	}
	
	/**
	 * 创建excel表格数据
	 * @param workbook
	 * @param sheet
	 * @param list
	 */
	private static void createExcelBody(HSSFWorkbook workbook, HSSFSheet sheet,
			List<Object[]> list) {
		// 定义单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFCellStyle cs = workbook.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 对齐方式
		
		HSSFDataFormat dataFormat = workbook.createDataFormat();
		cellStyle.setDataFormat(dataFormat.getFormat("yyyy-mm-dd hh:mm"));// 时间格式
		
		HSSFRow row;
		HSSFCell cell;
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = list.get(i);
				
				row = sheet.createRow(i + 1);
				
				int index = 0;
				for (Object o : arr) {
					cell = row.createCell(index++ );
					cell.setCellType(Cell.CELL_TYPE_STRING); 
					cell.setCellStyle(cs);
					cell.setCellValue(ValidateHelper.isNullOrEmpty(o)?"":o.toString());
				}
			}
		}
	}
}
