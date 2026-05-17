package jsoup.crawling.basic;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//https://jsoup.org/apidocs/
public class CrawlingExample {

	private static final int IT = 105;
	
	public static void main(String[] args) {
		String url = "https://news.naver.com/section/";
		
		Document doc;
			try {
				doc = Jsoup.connect(url + IT).get();
				
				 // 현재 날짜/시간 구하기        
				LocalDateTime now = LocalDateTime.now();             
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");            
				String formatedNow = now.format(formatter);
				
				System.out.println("["+ formatedNow + "]" + " 네이버 IT/과학 세션 헤드라인");
				Elements headLineTitle = doc.select(".section_article.as_headline._TEMPLATE .sa_text_strong");
				for (Element e : headLineTitle) {
					System.out.println(e.text());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

}
