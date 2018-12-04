package beans;

import java.io.Serializable;

public class UserBean implements Serializable {
	/**
	 * 
	 */
	/* private static final long serialVersionUID = 1L; */
	
	public String getName() {
		return userName;
	}
	public void setName(String name) {
		this.userName = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String userName="null";
	private String password="null";
}
