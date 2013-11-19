package org.tutsflow.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.UserConstants;
import org.tutsflow.form.CreateAccountForm;
import org.tutsflow.form.LoginForm;
import org.tutsflow.model.User;
import org.tutsflow.view.HomeView;

public class HomeControllerHelper {

	public static HomeView createHomeView(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute(Constants.SESSION_USER);
		
		HomeView view = new HomeView();
		view.setLoginForm(new LoginForm());
		view.setRegisterForm(new CreateAccountForm());
		if (user.getType() == UserConstants.GUEST) {
			view.setLoggedIn(false);
		}
		else {
			view.setLoggedIn(true);
		}
		
		return view;
	}
	
}
