package org.tutsflow.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.UserConstants;
import org.tutsflow.form.CreateAccountForm;
import org.tutsflow.form.LoginForm;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;
import org.tutsflow.util.Validator;
import org.tutsflow.validator.CreateAccountFormValidator;
import org.tutsflow.validator.LoginFormValidator;
import org.tutsflow.view.CreateAccountView;
import org.tutsflow.view.LoginView;
import org.tutsflow.web.UrlUtils;

public class CredentialsControllerHelper {

	
	/* *******************************
	 ******** Public Methods *********
	 ****************************** */
	
	/**
	 * ### Create account ###
	 */
	public static CreateAccountView createCreateAccountView(CreateAccountForm form, CreateAccountFormValidator validator,
			HttpServletRequest request, UserLocalService userLocalService) {
		
		CreateAccountView view = new CreateAccountView();
		
		if (Validator.isNull(form)) {
			
			view.setValid(true);
			view.setRegisterForm(new CreateAccountForm());
			
		}
		else {
			
			validator.clean();
			validator.validate(form, request);
			
			if (validator.isValid()) {
				
				String userNameS = UrlUtils.simplifyStringToUrl(form.getUserName());
				
				User user = new User();
				user.setType(UserConstants.NORMAL);
				user.setUserName(form.getUserName());
				user.setUserNameSimple(userNameS);
				user.setMail(form.getUserMail());
				
				userLocalService.updatePassword(user, form.getPassword());
				
				HttpSession session = request.getSession();
				User sUser = userLocalService.loginUser(user.getMail());
				session.setAttribute(Constants.SESSION_USER, sUser);
				
				view.setLoggedIn(true);
				
			}
			else {
				view.setValid(validator.isValid());
				view.setErrors(validator.getErrors());
			}
			
		}
		
		return view;
	}
	
	/**
	 * ### Login ###
	 */
	public static LoginView createLoginView(LoginForm form, LoginFormValidator validator, 
			HttpServletRequest request, UserLocalService userLocalService) {
		
		LoginView view = new LoginView();
		
		if (Validator.isNull(form)) {
			
			view.setValid(true);
			view.setLoginForm(new LoginForm());
			
		}
		else {
			
			validator.clean();
			validator.validate(form, request);
			
			if (validator.isValid()) {
				
				HttpSession session = request.getSession();
				User sUser = userLocalService.loginUser(form.getUserMail());
				session.setAttribute(Constants.SESSION_USER, sUser);
				
				view.setLoggedIn(true);
				
			}
			else {
				view.setValid(validator.isValid());
				view.setErrors(validator.getErrors());
			}
			
		}
		
		return view;
	}
	
}
