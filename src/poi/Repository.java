package poi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import poi.part02_template.Product;

/**
 * DB를 대신한 Repository
 */
public class Repository {
	private static List<Product> dbStorage = new ArrayList<Product>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String[] names = {"포테토칩", "새우깡", "아몬드빼빼로", "포카칩", "썬칩", "꽃게랑", "바나나킥", "사이다", "콜라", "드래프트생맥주"};
	private String[] companys = {"에스미푸드", "그라미", "동화푸드존", "농심", "빙그레", "해태"};
	private int limit = 10000;
	Random ran = new Random();
	
	public Repository() {
		init();
	}
	
	public static List<Product> getDbStorage() {
		return dbStorage;
	}

	public static void setDbStorage(List<Product> dbStorage) {
		Repository.dbStorage = dbStorage;
	}

	private void init() {
		Product prdt = null;
		
		for (int i = 0; i < limit; i++) {
			int id = limit + i + 1;
			String name = names[ran.nextInt(names.length)];
			int cnt = ran.nextInt(100) + 1;
			String company = companys[ran.nextInt(companys.length)];
			
			Date madeDate = null;
			Date limitDate = null;
			try {
				madeDate = sdf.parse(makeRandomDate("2024"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			try {
				limitDate = sdf.parse(makeRandomDate("2025"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			prdt = new Product("P_"+Integer.toString(id), name, cnt, company, madeDate, limitDate);
			dbStorage.add(prdt);
		}
	}
	
	/**
	 * random날짜 생성
	 * @return
	 */
	private String makeRandomDate(String year) {
		String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int ranMonth = ran.nextInt(12);
		
		StringBuffer date = new StringBuffer();
		date.append(year);
		date.append("-");
		date.append(months[ranMonth]);
		date.append("-");
		date.append(ran.nextInt(days[ranMonth]));
		
		return date.toString();
	}
}
