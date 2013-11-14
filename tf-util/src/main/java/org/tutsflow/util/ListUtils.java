package org.tutsflow.util;

import java.util.ArrayList;
import java.util.List;

import static org.tutsflow.constant.StringPool.*;

/**
 * Class created to convert String[] to the desired List Type
 */
public class ListUtils {

	/**
	 * Returns a List of String given a String[]
	 * @param vals
	 * @return
	 */
	public static List<String> getStringList(String[] vals) {
		
		List<String> list = new ArrayList<String>();

		for (String string : vals) {
			list.add(string);
		}
		
		return list;
	}
	
	/**
	 * Returns a List of Boolean given a String[]
	 * @param vals
	 * @return
	 */
	public static List<Boolean> getBooleanList(String[] vals) {
		
		List<Boolean> list = new ArrayList<Boolean>();

		for (String string : vals) {
			try {
				list.add(Boolean.valueOf(string));
			} catch (Exception e) {}
		}
		
		return list;
	}
	
	/**
	 * Returns a List of Integer given a String[]
	 * @param vals
	 * @return
	 */
	public static List<Integer> getIntegerList(String[] vals) {
		
		List<Integer> list = new ArrayList<Integer>();

		for (String string : vals) {
			try {
				list.add(Integer.valueOf(string));
			} catch (Exception e) {}
		}
		
		return list;
	}
	
	/**
	 * Returns a List of Long given a String[]
	 * @param vals
	 * @return
	 */
	public static List<Long> getLongList(String[] vals) {
		
		List<Long> list = new ArrayList<Long>();

		for (String string : vals) {
			try {
				list.add(Long.valueOf(string));
			} catch (Exception e) {}
		}
		
		return list;
	}
	
	/**
	 * Returns a List of Double given a String[]
	 * @param vals
	 * @return
	 */
	public static List<Double> getDoubleList(String[] vals) {
		
		List<Double> list = new ArrayList<Double>();

		for (String string : vals) {
			try {
				list.add(Double.valueOf(string.replace(COMMA, PERIOD)));
			} catch (Exception e) {}
		}
		
		return list;
	}
	
	/**
	 * Returns a List of Float given a String[]
	 * @param vals
	 * @return
	 */
	public static List<Float> getFloatList(String[] vals) {
		
		List<Float> list = new ArrayList<Float>();

		for (String string : vals) {
			try {
				list.add(Float.valueOf(string.replace(COMMA, PERIOD)));
			} catch (Exception e) {}
		}
		
		return list;
	}
	
	/**
	 * Returns a List of Character given a String[]
	 * @param vals
	 * @return
	 */
	public static List<Character> getCharacterList(String[] vals) {
		
		List<Character> list = new ArrayList<Character>();

		for (String string : vals) {
			try {
				list.add(string.charAt(0));
			} catch (Exception e) {}
		}
		
		return list;
	}
	
}
