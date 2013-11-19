package org.tutsflow.helper;

import org.tutsflow.form.LoginForm;
import org.tutsflow.view.HttpErrorView;

public class HttpErrorControllerHelper {

	public static HttpErrorView createForbiddenPathView() {			
		
		LoginForm form = new LoginForm();
		form.setRedirect(true);

		HttpErrorView view = new HttpErrorView();
		view.setLoginForm(form);
		
		return view;
	}
	
}
