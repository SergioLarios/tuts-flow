package org.tutsflow.controlpanel.helper;

import javax.servlet.http.HttpServletRequest;

import org.tutsflow.constant.ControlPanel;
import org.tutsflow.constant.Mappings;

import static org.tutsflow.constant.StringPool.*;

import org.tutsflow.controlpanel.form.UserEditForm;
import org.tutsflow.controlpanel.validator.UserEditValidator;
import org.tutsflow.controlpanel.view.UserEditView;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;
import org.tutsflow.util.Validator;
import org.tutsflow.web.FormUtils;

public class UserHelper {

	/**
	 * ### CP User Edit View ###
	 */
	public static UserEditView createUserEditView(long userId, UserLocalService userLocalService, 
			UserEditValidator userEditValidator, HttpServletRequest request) {
		
		UserEditView view = new UserEditView();
		UserEditForm form = null; 
		
		String actionUrl = Mappings.CP_SECTION_EDIT + SLASH + ControlPanel.USER + SLASH + userId;
		
		// Executed
		
		if (Validator.isNotNull(userEditValidator)) {
			
			form = FormUtils.fillForm(request, UserEditForm.class);
			
			userEditValidator.validate(form, request);
			
			view.setExecuted(true);
			view.setValid(userEditValidator.isValid());
			view.setErrors(userEditValidator.getErrors());
			
			// Saving edited user
			
			if (userEditValidator.isValid()) {
				
				User user = userLocalService.findById(userId);
				user.setMail(form.getMail());
				user.setType(form.getType());
				user.setUserName(form.getName());
				user.setUserNameSimple(form.getNameSimple());
								
				// New password
				
				if (!Validator.isBlank(form.getPssw())) {
					userLocalService.updatePassword(user, form.getPssw());
				}
				
				// Keep password
				
				else {
					userLocalService.update(user);
				}
				
			}
			
		}
		
		// Gathering info
		
		else {
			
			User user = userLocalService.findById(userId);
			
			form = new UserEditForm();
			form.setId(userId);
			form.setMail(user.getMail());
			form.setName(user.getUserName());
			form.setNameSimple(user.getUserNameSimple());
			form.setType(user.getType());
			
		}
		
		// View setting
		
		view.setUserEditForm(form);
		view.setActionUrl(actionUrl);
		
		return view;
	}
	
}
