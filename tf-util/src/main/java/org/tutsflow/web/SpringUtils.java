package org.tutsflow.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class with util methods to improve Spring integration
 * @author Yaden
 */
public class SpringUtils {

	private static Logger log = Logger.getLogger(SpringUtils.class);
	
	/**
	 * Model and Vie factory
	 * @param jspPath
	 * @param viewName
	 * @param viewObject
	 * @return
	 */
	public static ModelAndView createMv(String jspPath, String viewName, Object viewObject) {
		return new ModelAndView(jspPath, viewName, viewObject);
	}

	/**
	 * Makes an HTTP 301 redirection
	 * @param response
	 * @param path
	 */
	public static ModelAndView createRedirect(String path) {
		return new ModelAndView(REDIRECT + path);
	}

	/**
	 * Sends an error HTTP 403 : Forbidden
	 * @param response
	 */
	public static void sendError(HttpServletResponse response) {
		try {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	 * Sends an error HTTP 403 : Forbidden with a message
	 * @param response
	 */
	public static void sendError(HttpServletResponse response, String message) {
		try {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, message);
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	
	/* *****************************
	 * ***** Private constants *****
	 ***************************** */
	
	private static final String REDIRECT = "redirect:";
	
}
