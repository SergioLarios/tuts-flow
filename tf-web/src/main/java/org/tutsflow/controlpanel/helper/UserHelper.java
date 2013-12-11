package org.tutsflow.controlpanel.helper;

import org.tutsflow.controlpanel.form.UserEditForm;
import org.tutsflow.controlpanel.view.UserEditView;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;

public class UserHelper {

	/**
	 * ### CP User Edit View ###
	 */
	public static UserEditView createUserEditView(long userId, UserLocalService userLocalService) {
		
		UserEditView view = new UserEditView();
		
		User user = userLocalService.findById(userId);
		
		UserEditForm form = new UserEditForm();
		form.setMail(user.getMail());
		form.setName(user.getUserName());
		form.setNameSimple(user.getUserNameSimple());
		form.setType(user.getType());
		
		view.setUserEditForm(form);
		
		return view;
	}
	
}
