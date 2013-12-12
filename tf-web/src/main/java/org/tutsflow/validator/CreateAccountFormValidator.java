package org.tutsflow.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tutsflow.form.CreateAccountForm;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.util.AbstractFormValidator;
import org.tutsflow.util.Validator;
import org.tutsflow.web.UrlUtils;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateAccountFormValidator extends AbstractFormValidator<CreateAccountForm> {

	@Override
	public void validate(CreateAccountForm form, HttpServletRequest req) {
		
		if (Validator.isBlank(form.getUserMail())) {
			addError("caf.mail.null");
		}
		
		if (Validator.isBlank(form.getUserName())) {
			addError("caf.name.null");
		}
		
		if (Validator.isBlank(form.getPassword()) || Validator.isBlank(form.getPasswordRepeat())) {
			addError("caf.pssw.blank");
		}
		
		if (isValid() && !form.getPassword().equals(form.getPasswordRepeat())) {
			addError("caf.pssw.not-equal");
		}
		
		if (isValid() && userLocalService.existsUserMail(form.getUserMail())) {
			addError("caf.user.exists");
		}
		
		String userNameS = UrlUtils.simplifyStringToUrl(form.getUserName());
		if (isValid() && userLocalService.existsUserNameSimple(userNameS)) {
			addError("caf.user.exists");
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
	public void setUserLocalService(UserLocalService userLocalService) { this.userLocalService = userLocalService; }
	
}
