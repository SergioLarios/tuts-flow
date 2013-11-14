package org.tutsflow.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractFormValidator extends BeanToString {

	/* *******************************
	 ********* Form Fields ***********
	 ****************************** */
	
	private boolean valid = true;
	private List<String> errors = new ArrayList<String>();
	
	/* *******************************
	 ************ Validate ***********
	 ****************************** */
	
	public void validate(Object form, HttpServletRequest req) {
		
	}
	
	public void addError(String key) {
		errors.add(key);
		valid = false;
	}
	
	public void clean() {
		valid = true;
		errors = new ArrayList<String>();
	}
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public boolean isValid() { return valid; }
	public void setValid(boolean valid) { this.valid = valid; }
	
	public List<String> getErrors() { return errors; }
	public void setErrors(List<String> errors) { this.errors = errors; }
	
}
