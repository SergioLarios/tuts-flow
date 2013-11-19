package org.tutsflow.constant;

public class Mappings {

	// Menu
	public static final String HOME = "/";
	public static final String EXPLORE = "/explore";
	public static final String HOW_TO = "/how-to";
	
	// HTTP Errors
	public static final String HTTP_404 = "/errors/404";
	public static final String HTTP_403 = "/errors/403";
	
	// Resource
	public static final String RESURCE = "/resource";
	
	// Control panel
	public static final String CONTROL_PANEL = "/control-panel";
	public static final String CONTROL_PANEL_SECTION = CONTROL_PANEL + "/{section}";
	
	// User credentials
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/log-out";
	public static final String CREATE_ACCOUNT = "/create-account";
	
	// User
	public static final String USER = "/user";
	public static final String USER_HOME = USER +"/{userName}";
	
	
}
 