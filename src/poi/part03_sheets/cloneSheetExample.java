package poi.part03_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import poi.part02_template.Product;

public class cloneSheetExample {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static Calendar calendar = Calendar.getInstance();
	
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
			// wb = (HSSFWorkbook) ExcelUtils.generateWorkBook(cloneSheetExample.class, "ExcelTemplate.xls");
			wb = (XSSFWorkbook) ExcelUtils.generateWorkBook(cloneSheetExample.class, "StorageTemplate.xlsx");
			
			// 월별 Sheet 생성
			Sheet[] sheets = ExcelUtils.copySheet(wb, 12, "월");
			CellStyle styleCenter = wb.createCellStyle();
			ExcelUtils.setDefaultCellStyle(wb, styleCenter, HorizontalAlignment.CENTER, false);
			
			/**
			 *	data입력 - 제조월에 따라 sheet 분리하여 저장
			 */
			int[] idx = new int[12];
			
			List<Product> list = Repository.getDbStorage();
			// 제조일자의 월에 해당하는 sheet에 데이터 저장
			for (Product prdt : list) {
				Date madeDate = prdt.getMadeDate();
				calendar.setTime(madeDate);
				int month = calendar.get(Calendar.MONTH);
				
				int i = idx[month];
				Sheet sheet = sheets[month];
				
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
				cell.setCellValue(sdf.format(madeDate));
				
				// 유통기한
				cell = isNew ? row.createCell(pos++) : row.getCell(pos++);
				cell.setCellStyle(styleCenter);
				Date limitDate = prdt.getLimitDate();
				cell.setCellValue(sdf.format(limitDate));
				
				i++;
				idx[month] = i;
			}
			
			// 셀 너비 조정
			for (Sheet sheet : sheets) {
				Row header = sheet.getRow(0);
				for (int w = 0; w < header.getLastCellNum(); w++)
				{
					sheet.autoSizeColumn(w);
					sheet.setColumnWidth(w, (sheet.getColumnWidth(w) + 1024));
				}
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
