package org.tutsflow.web;

import java.text.Normalizer;

import org.apache.commons.lang.StringUtils;
import org.tutsflow.constant.StringPool;

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
			String simplifiedString = simplifyString(str).toLowerCase().replaceAll(SPACE, MINUS);
			
			while (simplifiedString.indexOf(MINUS + MINUS) > -1) {
				simplifiedString = simplifiedString.replaceAll(MINUS + MINUS, MINUS);
			}
		    return simplifiedString;
		}
		else {
			return str;
		}
		
	}
	
	
	/* *******************************
	 ****** Private constants ********
	 ****************************** */
	
	private static final String SPACE = StringPool.SPACE;
	private static final String MINUS = StringPool.DASH;
	private static final String BLANK = StringPool.BLANK;
	private static final String ASCII_REGEX ="[^\\p{ASCII}]";
	private static final String LETTER_NUMBERS_SPACES_REGEX = "[^a-zA-Z0-9 ]+";
	
}
