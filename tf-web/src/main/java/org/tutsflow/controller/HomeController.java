package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.helper.HomeControllerHelper;
import org.tutsflow.view.HomeView;
import org.tutsflow.web.SpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	/* *******************************************************
	 * ********************** Home : "/" *********************
	 * *******************************************************/
	@RequestMapping(value = Mappings.HOME, method = RequestMethod.GET)  
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		
		HomeView view = HomeControllerHelper.createHomeView(request);
		
		return SpringUtils.createMv(Constants.TMPL_HOME, Constants.VIEW, view);
	}
	
	
}
