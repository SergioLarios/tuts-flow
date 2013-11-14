package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.constant.UserConstants;
import org.tutsflow.form.CreateAccountForm;
import org.tutsflow.form.LoginForm;
import org.tutsflow.helper.CredentialsControllerHelper;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;
import org.tutsflow.validator.CreateAccountFormValidator;
import org.tutsflow.validator.LoginFormValidator;
import org.tutsflow.view.CreateAccountView;
import org.tutsflow.view.LoginView;
import org.tutsflow.web.FormUtils;
import org.tutsflow.web.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CredentialsController {
	
	/* *******************************************************
	 * ******************** Login : /login ******************* 
	 * *******************************************************/
	
	@RequestMapping(value = Mappings.LOGIN, method = RequestMethod.GET)  
	public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response) {
		
		LoginView view = CredentialsControllerHelper.createLoginView(null, null, request, null);
		
		return SpringUtils.createMv(Constants.JSP_LOGIN, Constants.VIEW, view);
	}
	
	@RequestMapping(value = Mappings.LOGIN, method = RequestMethod.POST)  
	public ModelAndView processLogin(HttpServletRequest request, HttpServletResponse response) {

		LoginForm loginForm = FormUtils.fillForm(request, LoginForm.class);
		LoginView view = CredentialsControllerHelper.createLoginView(
				loginForm, loginFormValidator, request, userLocalService);
		
		return SpringUtils.createMv(Constants.JSP_LOGIN, Constants.VIEW, view);
	}
	
	/* *******************************************************
	 * *********** Create account : /create-account **********
	 * *******************************************************/
	
	@RequestMapping(value = Mappings.CREATE_ACCOUNT, method = RequestMethod.GET)  
	public ModelAndView viewCreate(HttpServletRequest request, HttpServletResponse response) {
		
		CreateAccountView view = CredentialsControllerHelper.createCreateAccountView(
				null, null, request, null);
		
		return SpringUtils.createMv(Constants.JSP_CREATE_ACCOUNT, Constants.VIEW, view);
	}
	
	@RequestMapping(value = Mappings.CREATE_ACCOUNT, method = RequestMethod.POST)  
	public ModelAndView processCreate(HttpServletRequest request, HttpServletResponse response) {
		
		CreateAccountForm form = FormUtils.fillForm(request, CreateAccountForm.class);
		CreateAccountView view = CredentialsControllerHelper.createCreateAccountView(
				form, createAccountFormValidator,request, userLocalService);
		
		return SpringUtils.createMv(Constants.JSP_CREATE_ACCOUNT, Constants.VIEW, view);
	}
	
	/* *******************************************************
	 * ****************** Logout : /logout *****************
	 * *******************************************************/
	
	@RequestMapping(value = Mappings.LOGOUT)  
	public ModelAndView processLogout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		User user = new User();
		user.setType(UserConstants.GUEST);
		session.setAttribute(Constants.SESSION_USER, user);
		
		return SpringUtils.createRedirect(Mappings.HOME);
	}
	
	/* *******************************
	 ******* Injected objects ********
	 ****************************** */
	
	@Autowired
	private CreateAccountFormValidator createAccountFormValidator;
	
	@Autowired
	private LoginFormValidator loginFormValidator;

	@Autowired
	private UserLocalService userLocalService;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public CreateAccountFormValidator getCreateAccountFormValidator() { return createAccountFormValidator; }
	public void setCreateAccountFormValidator(CreateAccountFormValidator createAccountFormValidator) { 
		this.createAccountFormValidator = createAccountFormValidator;
	}

	public LoginFormValidator getLoginFormValidator() { return loginFormValidator; }
	public void setLoginFormValidator(LoginFormValidator loginFormValidator) {
		this.loginFormValidator = loginFormValidator;
	}

	public UserLocalService getUserLocalService() { return userLocalService; }
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
}
