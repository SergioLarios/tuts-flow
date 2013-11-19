package org.tutsflow.view;

import org.tutsflow.form.CreateAccountForm;
import org.tutsflow.form.LoginForm;

public class HomeView  extends AbstractView {

	/* *******************************
	 ********* View fields ***********
	 ****************************** */
	
	private CreateAccountForm registerForm;
	private LoginForm loginForm;
	private boolean loggedIn;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public CreateAccountForm getRegisterForm() { return registerForm; }
	public void setRegisterForm(CreateAccountForm registerForm) { 
		this.registerForm = registerForm; }
	
	public LoginForm getLoginForm() { return loginForm; }
	public void setLoginForm(LoginForm loginForm) { this.loginForm = loginForm; }
	
	public boolean isLoggedIn() { return loggedIn; }
	public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
	
}
