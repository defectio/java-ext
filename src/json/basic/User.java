package json.basic;

public class User {

	private String user_id;
	private String userName;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String user_id, String userName) {
		this.user_id = user_id;
		this.userName = userName;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
