package org.tutsflow.web;

import java.text.Normalizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import static org.tutsflow.constant.StringPool.*;

import org.tutsflow.util.Validator;

public class UrlUtils {

	/* *******************************
	 ******* Publics Methods *********
	 ****************************** */
	
	/**
	 * Removes special characters and accents(keeping the letter) from the string
	 * @param str
	 * @return
	 */
	public static String simplifyString(String str) {
		
		if (!StringUtils.isBlank(str)) {
			String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
			nfdNormalizedString = nfdNormalizedString
					.replaceAll(ASCII_REGEX, BLANK)
					.replaceAll(LETTER_NUMBERS_SPACES_REGEX,BLANK).trim();
			
			while (nfdNormalizedString.indexOf(SPACE + SPACE) > -1) {
				nfdNormalizedString = nfdNormalizedString.replaceAll(SPACE + SPACE, SPACE);
			}
			
		    return nfdNormalizedString;
		}
		else {
			return str;
		}
	    
	}
	
	/**
	 * Removes special characters and accents(keeping the letter) from the string, converts
	 * the string to lower case and replaces " " with "-"
	 * @param str
	 * @return
	 */
	public static String simplifyStringToUrl(String str) {
		if (!StringUtils.isBlank(str)) {
			String simplifiedString = simplifyString(str).toLowerCase().replaceAll(SPACE, DASH);
			
			while (simplifiedString.indexOf(DASH + DASH) > -1) {
				simplifiedString = simplifiedString.replaceAll(DASH + DASH, DASH);
			}
		    return simplifiedString;
		}
		else {
			return str;
		}
		
	}
	
	/**
	 * Gets the URI from the requests and deletes the context path if 
	 * it exists
	 * Ex : /app1/home => /home/
	 * @param request
	 * @return
	 */
	public static String getUri(HttpServletRequest request) {
		
		String uri = BLANK;
		
		if (Validator.isNotNull(request)) {
			
			String conPath = request.getContextPath();
			uri = request.getRequestURI();
			
			if (conPath != SLASH) {
				uri = uri.replace(conPath, BLANK);
			}
			
			if (!uri.endsWith(SLASH)) {
				uri = uri + SLASH;
			}

		}
		
		return uri;
	}
	
	/**
	 * Makes an URL-Like string readable. Ej: test-test => Test test
	 * @param input
	 * @return
	 */
	public static String makeReadable(String input)  {
		String output = BLANK;
		
		if (Validator.isNotNull(input)) {
			
			input = input.replace(DASH, SPACE);
			output = input.substring(0, 1).toUpperCase() + input.substring(1);
			
		}
		
		return output;
	}
	
	/* *******************************
	 ****** Private constants ********
	 ****************************** */
	
	private static final String ASCII_REGEX ="[^\\p{ASCII}]";
	private static final String LETTER_NUMBERS_SPACES_REGEX = "[^a-zA-Z0-9 ]+";
	
}
