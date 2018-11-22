package beans;

import java.io.Serializable;

public class MsgBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message="null";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;  //comment
	}
	
}
