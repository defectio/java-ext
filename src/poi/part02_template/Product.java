package poi.part02_template;

import java.util.Date;

/**
 * Storage DTO
 */
public class Product {

	private String id;  // 품번
	private String name;  //제품명
	private int cnt;	// 수량
	private String company; // 제조사
	private Date madeDate;  // 제조일자
	private Date limitDate;  // 유통기한
	
	public Product() {
	}
	
	public Product(String id, String name, int cnt, String company, Date madeDate, Date limitDate) {
		this.id = id;
		this.name = name;
		this.cnt = cnt;
		this.company = company;
		this.madeDate = madeDate;
		this.limitDate = limitDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	
	@Override
	public String toString() {
		String str = "[id=" + this.id + ", name=" + this.name + ", cnt=" + this.cnt + ", company=" + this.company
				+ ", madeDate=" + this.madeDate + ", limitDate=" + this.limitDate + "]";
		return str;
	}
	
}
