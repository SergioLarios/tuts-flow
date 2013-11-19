package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.helper.UserControllerHelper;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;
import org.tutsflow.view.UserHomeView;
import org.tutsflow.web.SpringUtils;

@Controller
public class UserController {

	/* *******************************************************
	 * ************* User Home : /user/{userName} ************ 
	 * *******************************************************/
	
	@RequestMapping(value = Mappings.USER_HOME, method = RequestMethod.GET) 
	public ModelAndView userHome(@PathVariable String userName, 
			HttpServletRequest request, HttpServletResponse response) {
		
		User userProfile = userLocalService.findByUserNameSimple(userName);
		UserHomeView view = UserControllerHelper.createUserHomeView(request, userProfile);
		
		return SpringUtils.createMv(Constants.TMPL_USER_HOME, Constants.VIEW, view);
	}
	
	/* *******************************
	 ******* Injected objects ********
	 ****************************** */
	
	@Autowired
	private UserLocalService userLocalService;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */

	public UserLocalService getUserLocalService() { return userLocalService; }
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
}
