package org.tutsflow.controlpanel.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tutsflow.controlpanel.form.UserEditForm;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;
import org.tutsflow.util.AbstractFormValidator;
import org.tutsflow.util.Validator;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserEditValidator extends AbstractFormValidator<UserEditForm> {
	
	@Override
	public void validate(UserEditForm form, HttpServletRequest req) {
		
		User oldUser = userLocalService.findById(form.getId());
		
		// Clean
		
		clean();
		
		// User mail
		
		if (Validator.isBlank(form.getMail()) || !Validator.isEmailAddress(form.getMail()) ||
			(!oldUser.getMail().equals(form.getMail()) &&  
			 userLocalService.existsUserMail(form.getMail()))) {
			
			addError("cp.user.error.mail");
			
		}
		
		// Name simple
		
		if (Validator.isBlank(form.getNameSimple()) || 
			(!oldUser.getUserNameSimple().equals(form.getNameSimple()) && 
			userLocalService.existsUserNameSimple(form.getNameSimple()))) {
			
			addError("cp.user.error.name-simple");
			
		}
		
		// Name
		
		if (Validator.isBlank(form.getName())) {
			
			addError("cp.user.error.name");
			
		}
		
		// Password
		
		if (!Validator.isBlank(form.getPssw()) && !form.getPssw().equals(form.getPsswRepeat())) {
			
			addError("cp.user.error.pssw");
			
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
