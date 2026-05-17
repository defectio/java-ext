package poi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 엑셀파일 관련 utils
 */
public class ExcelUtils {

	public static final String MAKER_NAME = "defectio";
	public static final String FILENAME = "product";
	public static final String FILE_DIVIDER = "_";
	public static final String XLS_EXTENSION = ".xls";
	public static final String XLSX_EXTENSION = ".xlsx";
	
	/**
	 * 기준 class와 템플릿 파일이름으로 workbook을 생성한다.
	 * @param clazz
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Workbook generateWorkBook(Class<?> clazz, String fileName) throws Exception
	{
		Workbook wb = getTemplateWorkBook(clazz, fileName);
		if (wb instanceof XSSFWorkbook) {
			return (XSSFWorkbook) wb;
		} else {
			return (HSSFWorkbook) wb;
		}
	}
	
	/**
	 * classpath에 있는 엑셀 템플릿을 가져온다.
	 * @param clazz
	 * @param template
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static Workbook getTemplate(Class<?> clazz, String template) throws Exception {
		File fileInSamePackage = new File(clazz.getResource("").getPath() + "/" + template);
		XSSFWorkbook wb = new XSSFWorkbook(fileInSamePackage);
		
		return wb;
	}
	
	/**
	 * 패키지 경로에 있는 엑셀 템플릿으로 Workbook을 생성 및 리턴한다.
	 * @param clazz : 패키지 탐색의 기준이 되는 클래스
	 * @param fileName : 템플릿파일 이름
	 * @return
	 * @throws Exception
	 */
	private static Workbook getTemplateWorkBook(Class<?> clazz, String fileName) throws Exception {
		String pkgName = null != clazz && null != clazz.getPackage() && null != clazz.getPackage().getName()
				? clazz.getPackage().getName()
				: "";
		// System.out.println("clazz : "+clazz);  // class utils.sec01_poi.part02_template.templateExample
		// System.out.println("clazz.getPackage() : "+clazz.getPackage());  // package utils.sec01_poi.part02_template
		// System.out.println("clazz.getPackage().getName() : "+clazz.getPackage().getName());  //  utils.sec01_poi.part02_template
		// System.out.println("pkgName : " + pkgName);  // utils.sec01_poi.part02_template
		String pkgStr = StringUtils.isNotEmpty(pkgName) ? pkgName.replace('.', '/') : pkgName;
		// System.out.println("pkgStr : " + pkgStr);  // utils/sec01_poi/part02_template

		InputStream is = null;
		Workbook wb;
		try {
			is = clazz.getClassLoader().getResourceAsStream(pkgStr + "/" + fileName);
			Workbook temp = WorkbookFactory.create(is); // workbook 생성 시 XSSFWorkbook 타입으로 생성됨(파일확장자가 .xlsx기 때문)
			workbookSetUp(temp);
			wb = temp;
		} finally {
			try {
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}

		return wb;
	}
	
	/**
	 * workbook setting
	 *   - setForceFormulaRecalculation(boolean) : 통합 문서를 열 때, 수식 값을 강제로 다시 계산 한다.
	 *   - 엑셀 파일의 metadata를 설정한다.
	 * @param wb
	 */
	private static void workbookSetUp(Workbook wb) {
		wb.setForceFormulaRecalculation(true);
		setMetaData(wb);
	}
	
	
	/**
	 * 엑셀 문서의 메타데이터를 저장한다.
	 * @param wb
	 */
	private static void setMetaData(Workbook wb) {
		if (wb instanceof XSSFWorkbook) {
			((XSSFWorkbook) wb).getProperties().getCoreProperties().setTitle("Storage");  // 제목
			((XSSFWorkbook) wb).getProperties().getCoreProperties().setCreator(MAKER_NAME);  // 작성자
			((XSSFWorkbook) wb).getProperties().getCoreProperties().setDescription("set Metadata using Apache POI / Java");  //메모(설명)
			((XSSFWorkbook) wb).getProperties().getCoreProperties().setCategory("Programming"); //category
			((XSSFWorkbook) wb).getProperties().getCoreProperties().setVersion("v0.1");  //버전
			// ((XSSFWorkbook) wb).getProperties().getCoreProperties().setLastModifiedByUser("defectio2");  // 최종 수정자
			// ((XSSFWorkbook) wb).getProperties().getCoreProperties().setModified(sdf.format(new Date()));  // 최종 수정일자
		} else if (wb instanceof HSSFWorkbook) {
			((HSSFWorkbook) wb).createInformationProperties();
			((HSSFWorkbook) wb).getSummaryInformation().setTitle("Storage");  // 제목
			((HSSFWorkbook) wb).getSummaryInformation().setAuthor(MAKER_NAME);  // 작성자
			((HSSFWorkbook) wb).getSummaryInformation().setComments("set Metadata using Apache POI / Java");
		}
		
		/* custom Properies 
		POIXMLProperties.CustomProperties custProp = ((XSSFWorkbook) wb).getProperties().getCustomProperties();
		custProp.addProperty("Author", "Thinktibits");// String
		custProp.addProperty("Year", 2014);     // Number Property
		custProp.addProperty("Published", true); //Yes No Property
		custProp.addProperty("Typist", "tika");
		*/
	}
	
	/**
	 * 저장할 파일명을 돌려준다.
	 * @param prefix 
	 * @param isIncludeDate : 파일명에 저장날짜 포함 여부
	 * @return
	 */
	public static String getFileName(String prefix, Workbook wb, boolean isIncludeDate) {
		StringBuffer sb = new StringBuffer();
		if (prefix != null) {
			sb.append(prefix);
			sb.append(FILE_DIVIDER);
		} 
		sb.append(FILENAME);
		
		if (isIncludeDate) {
			sb.append(FILE_DIVIDER);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			sb.append(sdf.format(new Date()));
		}
		
		if (wb instanceof XSSFWorkbook) {
			sb.append(XLSX_EXTENSION);
		} else {
			sb.append(XLS_EXTENSION);
		}
		
		return sb.toString();
	}
	
	/**
	 * 월별 sheet를 복사한다.
	 * @param wb : 복사하려는 wb
	 * @param size : sheet 개수
	 * @param sheetNames : sheet에 포함되야할 name
	 * @return
	 */
	public static Sheet[] copySheet(Workbook wb, int size, String sheetNames)
	{
		Sheet[] sheets = new Sheet[size];
		
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				// 0번째 시트일 경우, 이름을 변경한다.
				wb.setSheetName(i, (i+1) + sheetNames);
			} else {
				if (wb instanceof XSSFWorkbook) {
					((XSSFWorkbook) wb).cloneSheet(0, (i+1) + sheetNames);
				} else {
					wb.cloneSheet(0);
					wb.setSheetName(i, (i+1) + sheetNames);
				}
			}
			sheets[i] = wb.getSheetAt(i);
		}
		
		return sheets;
	}
	
	/**
	 * 가운데 정렬 default CellStyle
	 * @param cellStyle
	 * @param align
	 * @param isWeight
	 */
	public static void setDefaultCellStyle(Workbook wb, CellStyle cellStyle, HorizontalAlignment align, boolean isWeight) {
		Font baseFont = wb.createFont();
		baseFont.setFontName("맑은 고딕");
		baseFont.setFontHeightInPoints((short) 11);
		baseFont.setBold(isWeight);

		cellStyle.setFont(baseFont);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //수직 가운데 정렬
		cellStyle.setAlignment(align);  // 수평 가운데 정렬
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
	}
	
}
