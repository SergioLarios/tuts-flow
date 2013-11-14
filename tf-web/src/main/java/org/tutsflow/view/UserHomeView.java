package org.tutsflow.view;

import org.tutsflow.model.User;

public class UserHomeView extends AbstractView {

	/* *******************************
	 ********* View fields ***********
	 ****************************** */
	
	private User userProfile;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public User getUserProfile() { return userProfile; }
	public void setUserProfile(User userProfile) { this.userProfile = userProfile; }
	
}
