package poi.part01_basic;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class ex01_hssf {

	public static void main(String[] args) {
		/**
		 * .xls 확장자 지원 -> HSSF
		 *  - 엑셀 97 ~ 2003 버전
		 * 
		 * poi-5.2.3.jar 라이브러리 필요
		 */
		
		/**
		 * java.lang.ClassNotFoundException: org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream 발생
		 *   -> E:\poi\poi-bin-5.2.3\lib\commons-io-2.11.0.jar 라이브러리 추가
		 *   
		 * java.lang.ClassNotFoundException: org.apache.logging.log4j.LogManager 발생
		 *   -> E:\poi\poi-bin-5.2.3\lib\log4j-api-2.18.0.jar 라이브러리 추가
		 */
		HSSFWorkbook wb = new HSSFWorkbook();  // HSSFWorkbook 객체 생성
		
		HSSFSheet sheet = wb.createSheet("first_sheet");  // sheet 생성
		// sheet = wb.getSheetAt(0);  // 0번째 sheet를 가져옴(workbook 생성 시 기본으로 생성되는 sheet를 가져온다. sheet 이름도 기본값)

		sheet.setColumnWidth(0, (sheet.getColumnWidth(0) + 1024)); // 0번째 컬럼(열) 너비 조정
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 4)); // 셀 병합 -> 첫행, 마지막행, 첫열, 마지막열
		
		// 제목 폰트
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontName("맑은 고딕"); // 폰트 명
		titleFont.setFontHeightInPoints((short) 20); // 폰트 크기
		titleFont.setBold(true); // bold 설정
		titleFont.setColor(Font.COLOR_RED); // 폰트색 지정
//		 titleFont.setColor(IndexedColors.GREEN.getIndex());  // 폰트색 지정
		//  titleFont.setColor(new HSSFColor(Color.decode("#457ba2")));  // 폰트색 지정
		
		// 제목 스타일
		CellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFont(titleFont);
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		int rowNo = 0; // 행 인덱스
		Row row = sheet.createRow(rowNo++); // Row 객체 추가
		
		Cell cell = row.createCell(0); // 행으로부터 Cell 객체 생성
		cell.setCellStyle(titleStyle); // Cell에 스타일 지정
		cell.setCellValue("타이틀 입니다."); // 데이터 입력
		sheet.createRow(rowNo++); // 빈행 추가

		row = sheet.createRow(rowNo++);

		// 테이블 스타일 생성
		CellStyle tableCellStyle = wb.createCellStyle();
		tableCellStyle.setBorderTop(BorderStyle.THICK); // 테두리 위쪽
		tableCellStyle.setBorderBottom(BorderStyle.THICK); // 테두리 아래쪽
		tableCellStyle.setBorderLeft(BorderStyle.THICK); // 테두리 외쪽
		tableCellStyle.setBorderRight(BorderStyle.THICK); // 테두리 오른쪽

		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(tableCellStyle);
		cell.setCellValue("셀11");

		cell = row.createCell(1);
		cell.setCellStyle(tableCellStyle);
		cell.setCellValue("셀22");

		cell = row.createCell(2);
		cell.setCellStyle(tableCellStyle);
		cell.setCellValue("셀33");

		cell = row.createCell(3);
		cell.setCellStyle(tableCellStyle);
		cell.setCellValue("셀44");

		cell = row.createCell(4);
		cell.setCellStyle(tableCellStyle);
		cell.setCellValue("셀55");

		try {
			FileOutputStream fos = new FileOutputStream(new File("E:\\utils\\poi\\excelTest", "hssfTest.xls"));
			wb.write(fos);

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
