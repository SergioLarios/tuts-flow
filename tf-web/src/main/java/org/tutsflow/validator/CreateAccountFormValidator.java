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
public class CreateAccountFormValidator extends AbstractFormValidator{

	@Override
	public void validate(Object form, HttpServletRequest req) {
		CreateAccountForm caForm = (CreateAccountForm)form;
		
		if (Validator.isBlank(caForm.getUserMail())) {
			addError("caf.mail.null");
		}
		
		if (Validator.isBlank(caForm.getUserName())) {
			addError("caf.name.null");
		}
		
		if (Validator.isBlank(caForm.getPassword()) || Validator.isBlank(caForm.getPasswordRepeat())) {
			addError("caf.pssw.blank");
		}
		
		if (isValid() && !caForm.getPassword().equals(caForm.getPasswordRepeat())) {
			addError("caf.pssw.not-equal");
		}
		
		if (isValid() && userLocalService.existsUserMail(caForm.getUserMail())) {
			addError("caf.user.exists");
		}
		
		String userNameS = UrlUtils.simplifyStringToUrl(caForm.getUserName());
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
