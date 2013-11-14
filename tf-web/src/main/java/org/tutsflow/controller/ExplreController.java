package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.view.ExploreView;
import org.tutsflow.web.SpringUtils;

@Controller
public class ExplreController {

	/* *******************************************************
	 * ***************** Explore : /explore ****************** 
	 * *******************************************************/

	@RequestMapping(value = Mappings.EXPLORE, method = RequestMethod.GET)  
	public ModelAndView explore(HttpServletRequest request, HttpServletResponse response) {
	
		ExploreView view = new ExploreView();
		view.setExplore(true);
		
		return SpringUtils.createMv(Constants.JSP_EXPLORE, Constants.VIEW, view);
	}
	
}
