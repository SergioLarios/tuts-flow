package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.web.SpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlPanelController {

	/* *******************************************************
	 * *********** Control Panel : "/control-panel" **********
	 * *******************************************************/
	
	@RequestMapping(value = Mappings.CONTROL_PANEL, method = RequestMethod.GET)  
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		
		return SpringUtils.createMv(Constants.JSP_HOME, "text", new String("TESTTtTing!!!"));
	}
	
}
