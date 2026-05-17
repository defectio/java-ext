package poi.part02_template;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import poi.ExcelUtils;
import poi.Repository;

public class templateExample {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) {
		/**
		 * DB 생성 및 초기화
		 *   - 실제 DB connection을 대체함
		 */
		new Repository();
		
		XSSFWorkbook wb = null;
		try {
			
			long startTime = System.currentTimeMillis();
			
			/**
			 * Workbook 생성
			 */
			// wb = (XSSFWorkbook) ExcelUtils.generateWorkBook(templateExample.class, "ExcelTemplate.xls");
			wb = (XSSFWorkbook) ExcelUtils.generateWorkBook(templateExample.class, "ProductTemplate.xlsx");
			Sheet sheet = wb.getSheetAt(0);
			CellStyle styleCenter = wb.createCellStyle();
			ExcelUtils.setDefaultCellStyle(wb, styleCenter, HorizontalAlignment.CENTER, false);
			
			/**
			 *	data입력
			 */
			int i = 0;
			List<Product> list = Repository.getDbStorage();
			for (Product prdt : list) {
				
				int rowNum = i + 1;
				Row row = sheet.getRow(rowNum);
				boolean isNew = (row == null);
				if (isNew)
				{
					row = sheet.createRow(rowNum);
				}

				int pos = 0;
				
				//  품번
				Cell cell = isNew ? row.createCell(pos++) : row.getCell(pos++);
				cell.setCellStyle(styleCenter);
				String id = prdt.getId();
				cell.setCellValue(id);
				
				// 제품명
				cell = isNew ? row.createCell(pos++) : row.getCell(pos++);
				cell.setCellStyle(styleCenter);
				String name = prdt.getName();
				cell.setCellValue(name);
				
				// 수량
				cell = isNew ? row.createCell(pos++) : row.getCell(pos++);
				cell.setCellStyle(styleCenter);
				int cnt = prdt.getCnt();
				cell.setCellValue(cnt);
				
				// 제조사
				cell = isNew ? row.createCell(pos++) : row.getCell(pos++);
				cell.setCellStyle(styleCenter);
				String company = prdt.getCompany();
				cell.setCellValue(company);
				
				
				// 제조일자
				cell = isNew ? row.createCell(pos++) : row.getCell(pos++);
				cell.setCellStyle(styleCenter);
				Date madeDate = prdt.getMadeDate();
				cell.setCellValue(sdf.format(madeDate));
				
				// 유통기한
				cell = isNew ? row.createCell(pos++) : row.getCell(pos++);
				cell.setCellStyle(styleCenter);
				Date limitDate = prdt.getLimitDate();
				cell.setCellValue(sdf.format(limitDate));
				
				i++;
			}
			
			// 셀 너비 조정
			Row header = sheet.getRow(0);
			for (int w = 0; w < header.getLastCellNum(); w++)
			{
				sheet.autoSizeColumn(w);
				sheet.setColumnWidth(w, (sheet.getColumnWidth(w) + 1024));
			}
						
			// 다운로드(저장)
			String fileName = ExcelUtils.getFileName(null, wb, true);
			FileOutputStream fos = new FileOutputStream(new File("E:\\utils\\poi\\excelTest", fileName));
			wb.write(fos);

			long endTime = System.currentTimeMillis();
			System.out.println("[10000 row]");
			System.out.println("startTime : " + startTime);
			System.out.println("endTime : " + endTime);
			System.out.println("milliSeconds : " + (endTime - startTime));
			System.out.println("seconds : " + (endTime - startTime) / 1000);
			
			// resource close
			if (fos != null) {
				fos.close();
			}
			
			if (wb != null) {
				wb.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
