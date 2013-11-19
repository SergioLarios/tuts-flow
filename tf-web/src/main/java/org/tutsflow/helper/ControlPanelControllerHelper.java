package org.tutsflow.helper;

import org.tutsflow.view.ControlPanelView;

public class ControlPanelControllerHelper {

	
	/* *******************************
	 ******** Public Methods *********
	 ****************************** */
	
	/**
	 * ### Control Panel ###
	 */
	public static ControlPanelView createCpView(String section) {
		
		ControlPanelView view = new ControlPanelView();
		
		view.setSection(section);
		
		return view;
	}
}
