package org.tutsflow.view;

import org.tutsflow.util.BeanToString;

public class AbstractView extends BeanToString {

	/* *******************************
	 ********* View fields ***********
	 ****************************** */
	
	private String title = DEFAULT_TITLE;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = BASE_TITLE + title; }
	
	/* *******************************
	 ****** Private constants ********
	 ******************************* */
	
	private static final String DEFAULT_TITLE = "TutsFlow";
	private static final String BASE_TITLE = "TutsFlow | ";

}
