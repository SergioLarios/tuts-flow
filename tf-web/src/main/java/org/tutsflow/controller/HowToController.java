package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.view.HowToView;
import org.tutsflow.web.SpringUtils;

@Controller
public class HowToController {

	/* *******************************************************
	 * ****************** How to : /how-to ******************* 
	 * *******************************************************/

	@RequestMapping(value = Mappings.HOW_TO, method = RequestMethod.GET)  
	public ModelAndView explore(HttpServletRequest request, HttpServletResponse response) {
		
		HowToView view = new HowToView();
		
		return SpringUtils.createMv(Constants.TMPL_HOW_TO, Constants.VIEW, view);
	}
}