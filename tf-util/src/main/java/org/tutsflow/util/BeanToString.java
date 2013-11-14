package org.tutsflow.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import static org.tutsflow.constant.StringPool.*;


@SuppressWarnings(value = {"rawtypes", "unchecked"})
public abstract class BeanToString {

	/* *******************************
	 ****** Overridden toString ******
	 ******************************* */
	
	@Override
	public String toString() {
		String ret = toStringPrivate(getClass(), this, null, 0);
		return ret.substring(0, ret.length() -1);
	}
	
	public String toStringLog(Logger log) {
		return toStringPrivate(getClass(), this, log, 0);
	}
	
	public String toStringLite() {
		return toStringLite(getClass(), this, false);
	}
	
	public String toStringLite(boolean isRecursive) {
		return toStringLite(getClass(), this, isRecursive);
	}
	
	public String toJSONString() {
		return toStringLite(getClass(), this, false);
	}
	
	/* *******************************
	 ******** Private methods ********
	 ******************************* */
	
	private static String toStringPrivate(Class clazz, Object obj, Logger log, int tabNum) {
		
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		String tabs = BLANK;
		for (int i = 0; i < tabNum; i++) {
			tabs = tabs + TAB;
		}
		Object[] logObjs = {sb, log, tabs};
		
		// Bean header
		append(logObjs, SEPARATOR + clazz.getSimpleName() + SEPARATOR + NEW_LINE);
		
		for (Field field : fields) {

			logObjs = recursiveString(clazz, obj, field, logObjs);
			
		}
		
		return sb.toString();
		
	}

	private String toStringLite(Class clazz, Object obj, boolean isRecursive) {
		Field[] fields = clazz.getDeclaredFields();
		Object fieldObject = null;
		JSONObject jso = new JSONObject();
		StringBuffer sb = new StringBuffer();

		for (Field field : fields) {
			
			if (!Modifier.isFinal(field.getModifiers())) {
			
				fieldObject = getFieldObject(clazz, field, obj);
				if (isSonOfBeanToString(fieldObject) && field != null) {
					sb.append(QUOTE + field.getName() + QUOTE + COLON);
					try {
						Method meth = clazz.getMethod(TO_STRING_LITE, boolean.class);
						sb.append(meth.invoke(fieldObject, true));
					} catch (Exception e) {e.printStackTrace();
						sb.append(fieldObject);
					}
				}
				else {
					jso.put(field.getName(), fieldObject);
				}
			}
			
		}

		if (sb.length() <= 0) {
			return jso.toJSONString();
		}
		else {
			String prev = jso.toJSONString().substring(0, jso.toJSONString().length() -1) + COMMA;
			String mid = sb.toString();
			mid = mid.substring(0, mid.length() -1) + CLOSE_CURLY_BRACE;
			return prev + mid + CLOSE_CURLY_BRACE;
		}
		
	}
	
	
	private static Object[] recursiveString(Class clazz, Object obj, Field field, Object[] logObjs) {
		
		NoString noString = field.getAnnotation(NoString.class);
		if (noString == null && !Modifier.isFinal(field.getModifiers())) {
			
			String fieldName = upperCaseFirstLetter(field.getName()) + SPACE + COLON + SPACE;
			
			if (isCollection(field)) {
				
				Collection col = getCollection(clazz, field, obj);
				
				if (col != null) {
					
					String lsStr = fieldName + OPEN_CURLY_BRACE;
					
					int cont = 1;
					int size = col.size();
					
					for (Object object : col) {
						if (cont >= size) {
							lsStr = lsStr + object.toString();
						}
						else {
							lsStr = lsStr + object.toString() + COMMA + SPACE;
						}
						
						cont ++;
					}
					
					append(logObjs, lsStr + CLOSE_CURLY_BRACE + NEW_LINE);
				}
				else {
					append(logObjs, fieldName + NULL +  NEW_LINE);
				}
				
			}
			else {
				String fieldValue = BLANK;
				Object fieldObject = getFieldObject(clazz, field, obj);
				if (isSonOfBeanToString(fieldObject)) {
					Class subClass = fieldObject.getClass();
					Field[] fields = subClass.getDeclaredFields();
					
					append(logObjs, fieldName + fieldValue + NEW_LINE);
					
					String tabs = (String) logObjs[2];
					logObjs[2] = tabs + TAB;
					
					for (Field subField : fields) {
						logObjs = recursiveString(subClass, fieldObject, subField, logObjs);
					}
					
				}
				else {
					fieldValue = getFieldValue(clazz, field, obj);
					append(logObjs, fieldName + fieldValue + NEW_LINE);
				}
				
			}
			
		}
		
		return logObjs;
	}
	
	/**
	 * Determinate if the property is an Object which extends from BeanToString
	 * @param fieldObject
	 * @param clazz
	 * @return
	 */
	private static boolean isSonOfBeanToString(Object fieldObject) {
		
		try {
			
			if (fieldObject != null) {
				BeanToString.class.cast(fieldObject);
				return true;
			}
			else {
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
		
		
		
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
	 * Checks if the class is Boolean or boolean
	 * @param clazz
	 * @return
	 */
	private static boolean isBoolean(Class clazz) {
		return (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) ? true : false;
	}
	
	/**
	 * Checks the method name exists in the given class
	 * @param methodName
	 * @param clazz
	 * @return
	 */
	private static Method checkMethod(String methodName, Class clazz) {
		try {
			return clazz.getMethod(methodName);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Gets the field value as String given: its class, the desired field and an instance
	 * @param clazz
	 * @param field
	 * @param obj
	 * @return
	 */
	private static String getFieldValue(Class clazz, Field field, Object obj) {
		Object fieldValue =  getFieldObject(clazz, field, obj);
		if (fieldValue == null) {
			return NULL;
		}
		else {
			return fieldValue.toString();
		}
		
	}
	
	
	/**
	 * Gets the field value as String given: its class, the desired field and an instance
	 * @param clazz
	 * @param field
	 * @param obj
	 * @return
	 */
	private static Object getFieldObject(Class clazz, Field field, Object obj) {
		Object fieldObject = null;
		try {
			
			String fieldName = upperCaseFirstLetter(field.getName());
			
			if (isBoolean(field.getType())) {
				Method fieldGetter = checkMethod(IS + fieldName, clazz); 
				if (fieldGetter == null) {
					fieldGetter = checkMethod(GET + fieldName, clazz);
				}
				Object returnObj = fieldGetter.invoke(obj);
				return (returnObj == null) ? null : returnObj;
			}
			else {
				Method fieldGetter = clazz.getDeclaredMethod(GET + fieldName);
				Object returnObj = fieldGetter.invoke(obj);
				return (returnObj == null) ? null : returnObj;
			}
		} catch (Exception e) {
			fieldObject = null;
		}
		
		return fieldObject;
	}
	
	/**
	 * Checks if the given object is iterable
	 * @param field
	 * @return
	 */
	private static boolean isCollection(Field field) {
		Class clazz = field.getType();
		return Collection.class.isAssignableFrom(clazz);
	}
	
	/**
	 * Gets the collection given the field
	 * @param clazz
	 * @param field
	 * @param obj
	 * @return
	 */
	private static Collection getCollection(Class clazz, Field field, Object obj) {
		Collection col = null;
		
		try {
			
			String fieldName = upperCaseFirstLetter(field.getName());
			
			Method fieldGetter = clazz.getDeclaredMethod(GET + fieldName);
			
			Object returnObj = fieldGetter.invoke(obj);
			return (Collection)returnObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return col;
	}
	
	/**
	 * Appends the string to the sb and will try to log it at info level.
	 * @param logObjs
	 * @param msg
	 * @return
	 */
	private static StringBuilder append(Object[] logObjs, String msg) {
		
		StringBuilder sb = (StringBuilder) logObjs[0];
		Logger log = (Logger) logObjs[1];
		String tabs = (String) logObjs[2];
		
		sb.append(tabs + msg);
		if (log !=  null) {
			log.info(tabs + msg);
		}
		
		return sb;
	}
	
	/* *******************************
	 ********* Annotations ***********
	 ****************************** */
	
	@Retention(RetentionPolicy.RUNTIME)
	/**
	 * ********************************
	 * ** Annotation created to skip **
	 * **** non desired properties **** 
	 * ********************************
	 * @author java
	 */
	public @interface NoString {}
	
	
	/* *******************************
	 ******* Private Constants *******
	 ******************************* */
	
	private static final String GET = "get";
	private static final String IS = "is";
	private static final String NULL = "null";
	private static final String SEPARATOR = "_________";
	private static final String TO_STRING_LITE = "toStringLite";
	
}
