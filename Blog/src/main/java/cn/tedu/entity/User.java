package cn.tedu.entity;

public class User {
	private int oId;
	private String userName;
	private String password;
	private String email;
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(int oId, String userName, String password, String email) {
		super();
		this.oId = oId;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [oId=" + oId + ", userName=" + userName + ", password=" + password + ", email=" + email + "]";
	}
	
	
}
