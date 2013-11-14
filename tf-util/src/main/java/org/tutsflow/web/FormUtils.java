package org.tutsflow.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import static org.tutsflow.constant.StringPool.*;

import javax.servlet.http.HttpServletRequest;

import org.tutsflow.util.ListUtils;

/**
 * Class created to help with "backing bean" forms
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class FormUtils {

	/* *******************************
	 ******* Public Methods **********
	 ****************************** */
	
	public static <T> T fillForm(HttpServletRequest req, Class<T> clazz) {
		
		T returnForm = null;
		try {
			
			returnForm = clazz.newInstance();
			
			Field[] fields = clazz.getDeclaredFields();
			
			for (Field field : fields) {
				
				Param pn = field.getAnnotation(Param.class);
				if (pn != null) {
					
					String paramName = pn.name();
					String[] paramVals = req.getParameterValues(paramName);
					Class fieldClass = field.getType();

					try {
						
						// String
						if (fieldClass.equals(String.class)) {
							setField(clazz, returnForm, field, paramVals[0]);
						}
						// Boolean
						else if (fieldClass.equals(boolean.class) || fieldClass.equals(Boolean.class)) {
							setField(clazz, returnForm, field, Boolean.valueOf(paramVals[0]));
						}
						// Integer
						else if (fieldClass.equals(int.class) || fieldClass.equals(Integer.class)) {
							setField(clazz, returnForm, field, Integer.valueOf(paramVals[0]));
						}
						// Long
						else if (fieldClass.equals(long.class) || fieldClass.equals(Long.class)) {
							setField(clazz, returnForm, field, Long.valueOf(paramVals[0]));
						}
						// Double
						else if (fieldClass.equals(double.class) || fieldClass.equals(Double.class)) {
							setField(clazz, returnForm, field, Double.valueOf(paramVals[0].replace(COMMA, PERIOD)));
						}
						// Float
						else if (fieldClass.equals(float.class) || fieldClass.equals(Float.class)) {
							setField(clazz, returnForm, field, Float.valueOf(paramVals[0].replace(COMMA, PERIOD)));
						}
						// Char
						else if (fieldClass.equals(char.class) || fieldClass.equals(Character.class)) {
							setField(clazz, returnForm, field, paramVals[0].charAt(0));
						}
						// List String
						else if (isListFormDesiredType(field, String.class)) {
							setField(clazz, returnForm, field, ListUtils.getStringList(paramVals));
						}
						// List Boolean
						else if (isListFormDesiredType(field, Boolean.class)) {
							setField(clazz, returnForm, field, ListUtils.getBooleanList(paramVals));
						}
						// List Integer
						else if (isListFormDesiredType(field, Integer.class)) {
							setField(clazz, returnForm, field, ListUtils.getIntegerList(paramVals));
						}
						// List Long
						else if (isListFormDesiredType(field, Long.class)) {
							setField(clazz, returnForm, field, ListUtils.getLongList(paramVals));
						}
						// List Double
						else if (isListFormDesiredType(field, Double.class)) {
							setField(clazz, returnForm, field, ListUtils.getDoubleList(paramVals));
						}
						// List Float
						else if (isListFormDesiredType(field, Float.class)) {
							setField(clazz, returnForm, field, ListUtils.getFloatList(paramVals));
						}
						// List Character
						else if (isListFormDesiredType(field, Character.class)) {
							setField(clazz, returnForm, field, ListUtils.getCharacterList(paramVals));
						}
						
					} catch (Exception e) {}
				}
				
			}
			
			// Implements the interface AutomaticFillingForm
			if (AutomaticFillingForm.class.isAssignableFrom(clazz)) {
				AutomaticFillingForm form = (AutomaticFillingForm)returnForm;
				form.postFilling(req);
			}
			
		} catch (Exception e) {}
		
		
		return returnForm;
	}
	
	/* *******************************
	 ******* Private Methods *********
	 ****************************** */
	
	/**
	 * Sets the field value
	 * @param clazz
	 * @param form
	 * @param field
	 * @param val
	 * @throws Exception
	 */
	private static void setField(Class clazz, Object form, Field field, Object val) {
		
		try {
			String setter = SET + upperCaseFirstLetter(field.getName());
			clazz.getMethod(setter, field.getType()).invoke(form, val);
		} catch (Exception e) {}
		
	}
	
	/**
	 * Uppercases first letter 
	 * @param s
	 * @return
	 */
	private static String upperCaseFirstLetter(String s) {
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	/**
	 * Given List of ? returns true if ? = type
	 * @param field
	 * @param type
	 * @return
	 */
	private static boolean isListFormDesiredType(Field field, Class type) {
		try {
			
			if (field.getType().isAssignableFrom(List.class)) {
				ParameterizedType listType = (ParameterizedType) field.getGenericType();
		        Class listClass = (Class) listType.getActualTypeArguments()[0];
		        
		        if (listClass.equals(type)) {
		        	return true;
		        }
			}
			
	        return false;
	        
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/* *******************************
	 ********* Annotations ***********
	 ****************************** */
	
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Param { public String name();	}
	
	/* *******************************
	 ********** Interfaces ***********
	 ****************************** */
	
	public static interface AutomaticFillingForm {
		public void postFilling(HttpServletRequest req);
	}
	
	/* *******************************
	 ******* Private Constants *******
	 ******************************* */
	
	private static final String SET = "set";
}
