package org.tutsflow.view;

import java.util.List;

import org.tutsflow.form.LoginForm;

public class LoginView extends AbstractView {
	
	/* *******************************
	 ********* View fields ***********
	 ****************************** */
	
	private LoginForm loginForm;
	private List<String> errors;
	private boolean valid;
	private boolean home;
	private boolean loggedIn;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public LoginForm getLoginForm() { return loginForm; }
	public void setLoginForm(LoginForm form) { this.loginForm = form; }
	
	public List<String> getErrors() { return errors; }
	public void setErrors(List<String> errors) { this.errors = errors; }
	
	public boolean isValid() { return valid; }
	public void setValid(boolean valid) { this.valid = valid; }
	
	public boolean isHome() { return home; }
	public void setHome(boolean home) { this.home = home; }
	
	public boolean isLoggedIn() { return loggedIn; }
	public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
	
}
