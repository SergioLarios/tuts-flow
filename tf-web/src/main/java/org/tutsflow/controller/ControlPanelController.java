package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.ControlPanel;
import org.tutsflow.constant.Mappings;
import org.tutsflow.helper.ControlPanelControllerHelper;
import org.tutsflow.view.ControlPanelView;
import org.tutsflow.web.SpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		ControlPanelView view = ControlPanelControllerHelper.createCpView(ControlPanel.DEFAULT);
		
		return SpringUtils.createMv(Constants.TMPL_CP_HOME, Constants.VIEW, view);
	}
	
	/* *******************************************************
	 * ***** Control Panel : "/control-panel/{section}" ******
	 * *******************************************************/
	
	@RequestMapping(value = Mappings.CONTROL_PANEL_SECTION, method = RequestMethod.GET)  
	public ModelAndView tables(@PathVariable String section,
			HttpServletRequest request, HttpServletResponse response) {
		
		ControlPanelView view = ControlPanelControllerHelper.createCpView(section);
		
		return SpringUtils.createMv(Constants.TMPL_CP_HOME, Constants.VIEW, view);
	}
	
}
