package org.tutsflow.view;

import java.util.List;

import org.tutsflow.form.CreateAccountForm;

public class CreateAccountView extends AbstractView {

	/* *******************************
	 ********* View fields ***********
	 ****************************** */
	
	private CreateAccountForm registerForm;
	private List<String> errors;
	private boolean valid;
	private boolean home;
	private boolean loggedIn;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public CreateAccountForm getRegisterForm() { return registerForm; }
	public void setRegisterForm(CreateAccountForm form) { this.registerForm = form; }
	
	public List<String> getErrors() { return errors; }
	public void setErrors(List<String> errors) { this.errors = errors; }
	
	public boolean isValid() { return valid; }
	public void setValid(boolean valid) { this.valid = valid; }
	
	public boolean isHome() { return home; }
	public void setHome(boolean home) { this.home = home; }
	
	public boolean isLoggedIn() { return loggedIn; }
	public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
	
}
