package org.tutsflow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.ControlPanel;
import org.tutsflow.constant.Mappings;
import org.tutsflow.controlpanel.helper.UserHelper;
import org.tutsflow.controlpanel.view.UserEditView;
import org.tutsflow.helper.ControlPanelControllerHelper;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.view.ControlPanelView;
import org.tutsflow.web.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	/* *******************************************************
	 *  Control Panel : "/control-panel/edit/{section}/{id}" *
	 * *******************************************************/
	
	@RequestMapping(value = Mappings.CP_SECTION_EDIT_SECTION, method = RequestMethod.GET)  
	public ModelAndView edit(@PathVariable String section, @PathVariable Long id,
			HttpServletRequest request, HttpServletResponse response) {
		
		// User
		if (section.equals(ControlPanel.USER)) {
			UserEditView view = UserHelper.createUserEditView(id, userLocalService);
			return SpringUtils.createMv(ControlPanel.TMPL_USER_EDIT, Constants.VIEW, view);
		}
		// Not found
		else {
			return SpringUtils.createRedirect(Mappings.HTTP_404);
		}
		
	}
	
	/* *******************************************************
	 *  Control Panel : "/control-panel/edit/{section}/{id}" *
	 * *******************************************************/
	
	@RequestMapping(value = Mappings.CP_SECTION_EDIT_SECTION, method = RequestMethod.POST)  
	public ModelAndView saveEdit(@PathVariable String section, @PathVariable Long id,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		return SpringUtils.createMv(ControlPanel.TMPL_USER_EDIT, Constants.VIEW, null);
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
		this.userLocalService = userLocalService; }
	
}
