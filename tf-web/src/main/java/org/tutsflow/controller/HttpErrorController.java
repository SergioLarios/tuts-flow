package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.helper.HttpErrorControllerHelper;
import org.tutsflow.view.HttpErrorView;
import org.tutsflow.web.SpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HttpErrorController {

	/* *******************************************************
	 * **************** Error 404 : Not found **************** 
	 * *******************************************************/
	@RequestMapping(value = Mappings.HTTP_404, method = RequestMethod.GET)  
	public ModelAndView handle404(HttpServletRequest request, HttpServletResponse response) {
		
		HttpErrorView view = new HttpErrorView();
		
		return SpringUtils.createMv(Constants.TMPL_404, Constants.VIEW, view);
	}
	
	/* *******************************************************
	 * ************** Error 403 : Forbidden path ************* 
	 * *******************************************************/
	@RequestMapping(value = Mappings.HTTP_403, method = RequestMethod.GET)  
	public ModelAndView handle403(HttpServletRequest request, HttpServletResponse response) {
		
		HttpErrorView view = HttpErrorControllerHelper.createForbiddenPathView();
		
		return SpringUtils.createMv(Constants.TMPL_403, Constants.VIEW, view);
	}
	
}
