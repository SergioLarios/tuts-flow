package org.tutsflow.form;

import org.tutsflow.constant.Constants;
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
	
	@Param(name = Constants.SESSION_PREVIOUS_URI)
	private String prevUri;
	
	@Param(name = REDIRECT)
	private boolean redirect;
	
	/* *******************************
	 ********* Form Names ************
	 ****************************** */
	
	public static final String USER_MAIL = "login.usermail";
	public static final String USER_PASSWORD = "login.password";
	public static final String REDIRECT = "login.redirect";
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public String getUserMail() { return userMail; }
	public void setUserMail(String userMail) { this.userMail = userMail; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getPrevUri() { return prevUri; }
	public void setPrevUri(String prevUri) { this.prevUri = prevUri; }
	
	public boolean isRedirect() { return redirect; }
	public void setRedirect(boolean redirect) { this.redirect = redirect; }
	
}
