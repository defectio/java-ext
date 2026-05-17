package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class HttpURLConnectionExample {

	private static final String SERVICE_URL = "http://apis.data.go.kr/1360000/EqkInfoService";
	private static final String REQUEST_URL = "/getEqkMsg";

	private static final String NORMAL = "00";
	private static final String NODATA = "03";

	private static final HashMap<String, String> REQUEST_PARAMS = new HashMap<>(); // 요청 params
	private static final HashMap<Long, String> MSG_TYPE = new HashMap<>(); // 메시지 타입
	private static final HashMap<String, String> RES_CODE = new HashMap<>(); // 응답 코드

	static {
		// decoding된 serviceKey
		REQUEST_PARAMS.put("serviceKey",
				"lFG95EhS/f/dAfnhwhVGZMulMOTCsuytKwzq8xlBGETJvqy+rL33Hf7Dr1OZbacSrudBUkLxiJPS0PxCPPHzZA=="); 
		REQUEST_PARAMS.put("dataType", "JSON"); // XML(default) or JSON
		REQUEST_PARAMS.put("fromTmFc", getStringDate(3)); // 조회 시작일시 : 오늘 - 3일
		REQUEST_PARAMS.put("toTmFc", getStringDate(0)); // 조회 종료일시 : 오늘

		MSG_TYPE.put(new Long(2), "국외 지진정보");
		MSG_TYPE.put(new Long(3), "국내 지진정보");
		MSG_TYPE.put(new Long(5), "국내 지진정보(재통보)");
		MSG_TYPE.put(new Long(11), "국내 지진초기경보");
		MSG_TYPE.put(new Long(12), "국외 지진초기경보");
		MSG_TYPE.put(new Long(13), "초기경보 정밀분석");
		MSG_TYPE.put(new Long(14), "지진속보(초기분석)");

		RES_CODE.put("00", "NORMAL_SERVICE"); // 정상
		RES_CODE.put("01", "APPLICATION_ERROR"); // 어플리케이션 에러
		RES_CODE.put("02", "DB_ERROR"); // 데이터베이스 에러
		RES_CODE.put("03", "NODATA_ERROR"); // 데이터없음 에러
		RES_CODE.put("04", "HTTP_ERROR"); // HTTP 에러
		RES_CODE.put("05", "SERVICETIME_OUT"); // 서비스 연결실패 에러
		RES_CODE.put("10", "INVALID_REQUEST_PARAMETER_ERROR"); // 잘못된 요청 파라메터 에러
		RES_CODE.put("11", "NO_MANDATORY_REQUEST_PARAMETERS_ERROR"); // 필수요청 파라메터가 없음
		RES_CODE.put("12", "NO_OPENAPI_SERVICE_ERROR"); // 해당 오픈API서비스가 없거나 폐기됨
		RES_CODE.put("20", "SERVICE_ACCESS_DENIED_ERROR"); // 서비스 접근거부
		RES_CODE.put("21", "TEMPORARILY_DISABLE_THE_SERVICEKEY_ERROR"); // 일시적으로 사용할 수 없는 서비스 키
		RES_CODE.put("22", "LIMITED_NUMBER_OF_SERVICE_REQUESTS_EXCEEDS_ERROR"); // 서비스 요청제한횟수 초과에러
		RES_CODE.put("30", "SERVICE_KEY_IS_NOT_REGISTERED_ERROR"); // 등록되지 않은 서비스키
		RES_CODE.put("31", "DEADLINE_HAS_EXPIRED_ERROR"); // 기한만료된 서비스키
		RES_CODE.put("32", "UNREGISTERED_IP_ERROR"); // 등록되지 않은 IP
		RES_CODE.put("33", "UNSIGNED_CALL_ERROR"); // 서명되지 않은 호출
		RES_CODE.put("99", "UNKNOWN_ERROR"); // 기타에러
	}

	public static void main(String[] args) {

		try {
			URL url = new URL(SERVICE_URL + REQUEST_URL + getRequestParams(REQUEST_PARAMS));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// Request Header
			conn.setRequestProperty("content-type", "application/json; charset=utf-8");

			conn.setRequestMethod("GET"); // HTTP method
			conn.setConnectTimeout(5000); // 서버에 연결되는 timeout 설정
			conn.setReadTimeout(5000); // InputStream을 읽어오는 timeout 설정

//			System.out.println("request header : " + conn.getRequestProperties().toString());  // request header에 매핑된 values 리턴
//			System.out.println("응답콘텐츠유형 : " + conn.getContentType());
//			System.out.println("응답코드 : " + conn.getResponseCode());
//			System.out.println("응답메시지 : " + conn.getResponseMessage());

			// 응답 코드 체크 -> 정상적인 응답일 경우 200
			if (conn.getResponseCode() == 200) {
				// 응답 결과를 inputStream으로 받아옴
				InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
				BufferedReader reader = new BufferedReader(isr);
				StringBuffer result = new StringBuffer();
				String line;
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}

				// org.json 라이브러리 import
				// Ohttps://code.google.com/archive/p/json-simple/downloads
				// String to JSONObject
				JSONObject jsonObj = new JSONObject(result.toString());

				JSONObject response = (JSONObject) jsonObj.get("response");
				JSONObject header = (JSONObject) response.get("header");
				if (header.get("resultCode").equals(NORMAL)) { // 정상
					JSONObject body = (JSONObject) response.get("body");
					JSONObject items = (JSONObject) body.get("items");

					JSONArray item = (JSONArray) items.get("item");
					for (int i = 0; i < item.length(); i++) {
						JSONObject eqItem = (JSONObject) item.get(i);

						System.out.println("num : " + (i + 1));
						System.out.println("통보종류 : " + MSG_TYPE.get(eqItem.get("fcTp")));
						System.out.println("진앙위치 : " + eqItem.get("loc"));
						System.out.println("규모 : " + eqItem.get("mt"));
						System.out.println("위도 : " + eqItem.get("lat"));
						System.out.println("경도 : " + eqItem.get("lon"));
						System.out.println("진도 : " + eqItem.get("inT"));
						System.out.println("깊이 : " + eqItem.get("dep"));
						System.out.println("진앙시 : " + eqItem.get("tmEqk"));
						System.out.println("발표시각 : " + eqItem.get("tmFc"));
						System.out.println("참고사항 : " + eqItem.get("rem"));
						System.out.println();
					}
				} else if (header.get("resultCode").equals(NODATA)) { // no_data
					System.out.println("조회기간 동안 발생한 지진이 없습니다.");
				} else { // 에러 메시지 출력
					System.out.println(RES_CODE.get(header.get("resultCode")));
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 조회날짜를 request 규격에 맞는 String으로 리턴한다.
	 * 
	 * @param day
	 * @return
	 */
	public static String getStringDate(long day) {
		LocalDate date = LocalDate.now().minusDays(day);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		return date.format(formatter);
	}

	/**
	 * request시에 필요한 parameter를 queryString으로 리턴한다.
	 * 
	 * @param map
	 * @return
	 */
	public static String getRequestParams(HashMap<String, String> map) {
		StringBuilder queryStr = new StringBuilder();

		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();

		int i = 0;
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			String value = map.get(key);
			if (i == 0) {
				queryStr.append("?");
			} else {
				queryStr.append("&");
			}

			try {
				queryStr.append(URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			i++;
		}

		return queryStr.toString();
	}

}
