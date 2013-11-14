package org.tutsflow.helper;

import javax.servlet.http.HttpServletRequest;

import org.tutsflow.model.User;
import org.tutsflow.view.UserHomeView;

public class UserControllerHelper {

	public static UserHomeView createUserHomeView(HttpServletRequest req, User userProfile) {
		
		UserHomeView view = new UserHomeView();
		view.setUserProfile(userProfile);
		
		return view;
	}
	
}
