package org.tutsflow.form;

import org.tutsflow.util.BeanToString;
import org.tutsflow.web.FormUtils.Param;

public class LoginForm extends BeanToString {

	/* *******************************
	 ********* Form Fields ***********
	 ****************************** */
	
	@Param(name = USER_MAIL)
	private String userMail;
	
	@Param(name = USER_PASSWORD)
	private String password;
	
	/* *******************************
	 ********* Form Names ************
	 ****************************** */
	
	public static final String USER_MAIL = "login.usermail";
	public static final String USER_PASSWORD = "login.password";
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public String getUserMail() { return userMail; }
	public void setUserMail(String userMail) { this.userMail = userMail; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
}
