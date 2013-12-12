package org.tutsflow.controlpanel.view;

import java.util.List;

import org.tutsflow.controlpanel.form.UserEditForm;

public class UserEditView {
	
	/* *******************************
	 ********* View fields ***********
	 ****************************** */
	
	private UserEditForm userEditForm;
	
	private String actionUrl;
	
	private boolean executed;
	
	private boolean valid;
	
	private List<String> errors;

	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public UserEditForm getUserEditForm() { return userEditForm; }
	public void setUserEditForm(UserEditForm userEditForm) { this.userEditForm = userEditForm; }
	
	public String getActionUrl() { return actionUrl; }
	public void setActionUrl(String actionUrl) { this.actionUrl = actionUrl; }
	
	public boolean isExecuted() { return executed; }
	public void setExecuted(boolean executed) { this.executed = executed; }
	
	public boolean isValid() { return valid; }
	public void setValid(boolean valid) { this.valid = valid; }
	
	public List<String> getErrors() { return errors; }
	public void setErrors(List<String> errors) { this.errors = errors; }
	
}
