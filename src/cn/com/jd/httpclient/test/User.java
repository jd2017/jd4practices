package cn.com.jd.httpclient.test;

public class User {
	
	public User(){}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	private String countryCode;
	private String username;
	private String password;
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
