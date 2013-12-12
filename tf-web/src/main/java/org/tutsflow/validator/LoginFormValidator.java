package org.tutsflow.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tutsflow.form.LoginForm;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.util.AbstractFormValidator;
import org.tutsflow.util.Validator;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginFormValidator extends AbstractFormValidator<LoginForm> {

	@Override
	public void validate(LoginForm form, HttpServletRequest req) {
		
		if (Validator.isBlank(form.getUserMail())) {
			addError("lf.mail.blank");
		}
		
		if (Validator.isBlank(form.getPassword())) {
			addError("lf.pssw.blank");
		}
		
		if (isValid() && !userLocalService.checkPssw(form.getUserMail(), form.getPassword()))  {
			addError("lf.not-match");
		}
		
	}	
	
	/* *******************************
	 ************* Spring ************
	 ******************************* */
	
	@Autowired
	private UserLocalService userLocalService;

	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public UserLocalService getUserLocalService() { return userLocalService; }
	public void setUserLocalService(UserLocalService userLocalService) { 
		this.userLocalService = userLocalService; }
}
