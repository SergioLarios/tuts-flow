package org.tutsflow.form;

import org.tutsflow.util.BeanToString;
import org.tutsflow.web.FormUtils.Param;

public class CreateAccountForm extends BeanToString {
	
	/* *******************************
	 ********* Form Fields ***********
	 ****************************** */
	
	@Param(name = USER_MAIL)
	private String userMail;
	
	@Param(name = USER_NAME)
	private String userName;
	
	@Param(name = USER_PASSWORD)
	private String password;
	
	@Param(name = USER_PASSWORD_REPEAT)
	private String passwordRepeat;
	
	/* *******************************
	 ********* Form Names ************
	 ****************************** */
	
	public static final String USER_MAIL = "create-account.usermail";
	public static final String USER_NAME = "create-account.name";
	public static final String USER_PASSWORD = "create-account.password";
	public static final String USER_PASSWORD_REPEAT = "create-account.password-repeat";
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public String getUserMail() { return userMail; }
	public void setUserMail(String userMail) { this.userMail = userMail; }

	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getPasswordRepeat() { return passwordRepeat; }
	public void setPasswordRepeat(String passwordRepeat) { this.passwordRepeat = passwordRepeat; }
	
}
