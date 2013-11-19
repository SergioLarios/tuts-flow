package org.tutsflow.view;

import org.tutsflow.form.LoginForm;

public class HttpErrorView extends AbstractView {

	/* *******************************
	 ********* View fields ***********
	 ****************************** */
	
	private LoginForm loginForm;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public LoginForm getLoginForm() { return loginForm; }
	public void setLoginForm(LoginForm loginForm) { this.loginForm = loginForm; }
	
}
