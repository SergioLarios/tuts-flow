package org.tutsflow.util;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.tutsflow.web.FormUtils.AutomaticFillingForm;
import org.tutsflow.web.FormUtils.Param;
import org.tutsflow.web.ParamUtil;

public class TestForm extends BeanToString implements AutomaticFillingForm {

	/* *******************************
	 ********* Form Fields ***********
	 ****************************** */
	
	@Param(name = STRING)
	private String stringO;
	
	@Param(name = BOOLEAN_P)
	private boolean booleanP;
	
	@Param(name = BOOLEAN_O)
	private Boolean booleanO;
	
	@Param(name = INT_P)
	private int intP;
	
	@Param(name = INT_O)
	private Integer intO;
	
	@Param(name = LONG_P)
	private long longP;
	
	@Param(name = LONG_O)
	private Long longO;

	@Param(name = DOUBLE_P)
	private double doubleP;
	
	@Param(name = DOUBLE_O)
	private Double doubleO;
	
	@Param(name = FLOAT_P)
	private float floatP;
	
	@Param(name = FLOAT_O)
	private Float floatO;
	
	@Param(name = CHAR_P)
	private char charP;
	
	@Param(name = CHAR_O)
	private Character charO;

	@Param(name = LIST_STRING)
	private List<String> listString;
	
	@Param(name = LIST_BOOLEAN)
	private List<Boolean> listBoolean;
	
	@Param(name = LIST_INT)
	private List<Integer> listInteger;
	
	@Param(name = LIST_LONG)
	private List<Long> listLong;
	
	@Param(name = LIST_DOUBLE)
	private List<Double> listDouble;
	
	@Param(name = LIST_FLOAT)
	private List<Float> listFloat;
	
	@Param(name = LIST_CHAR)
	private List<Character> listChar;
	
	private Date date;
	
	private String noParam;
	
	/* *******************************
	 ********* Form Names ************
	 ****************************** */
	
	public static final String STRING = "string";
	
	public static final String BOOLEAN_P = "boolean.p";
	public static final String BOOLEAN_O = "boolean.o";
	
	public static final String INT_P = "int.p";
	public static final String INT_O = "int.o";
	
	public static final String LONG_P = "long.p";
	public static final String LONG_O = "long.o";
	
	public static final String DOUBLE_P = "double.p";
	public static final String DOUBLE_O = "double.o";
	
	public static final String FLOAT_P = "float.p";
	public static final String FLOAT_O = "float.o";
	
	public static final String CHAR_P = "char.p";
	public static final String CHAR_O = "char.o";
	
	public static final String LIST_STRING = "list.string";
	public static final String LIST_BOOLEAN = "list.boolean";
	public static final String LIST_INT = "list.int";
	public static final String LIST_LONG = "list.long";
	public static final String LIST_DOUBLE = "list.double";
	public static final String LIST_FLOAT = "list.float";
	public static final String LIST_CHAR = "list.char";
	
	public static final String DATE_YEAR = "date.year";
	public static final String DATE_MONTH = "date.month";
	public static final String DATE_DAY = "date.day";
	
	public static final String NO_PARAM = "noParam";
	
	/* *******************************
	 ********** Post filling *********
	 ******************************* */
	
	@Override
	public void postFilling(HttpServletRequest req) {
		
		int year = ParamUtil.getInteger(req, DATE_YEAR);
		int month = ParamUtil.getInteger(req, DATE_MONTH);
		int day = ParamUtil.getInteger(req, DATE_DAY);
		
		DateTime cal = new DateTime(year, month, day, 0, 0, 0);
		date = cal.toDate();
		
	}
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public String getStringO() { return stringO; }
	public void setStringO(String stringO) { this.stringO = stringO; }
	
	public boolean isBooleanP() { return booleanP; }
	public void setBooleanP(boolean booleanP) { this.booleanP = booleanP; }
	
	public Boolean getBooleanO() { return booleanO; }
	public void setBooleanO(Boolean booleanO) { this.booleanO = booleanO; }
	
	public int getIntP() { return intP; }
	public void setIntP(int intP) { this.intP = intP; }
	
	public Integer getIntO() { return intO; }
	public void setIntO(Integer intO) { this.intO = intO; }
	
	public long getLongP() { return longP; }
	public void setLongP(long longP) { this.longP = longP; }
	
	public Long getLongO() { return longO; }
	public void setLongO(Long longO) { this.longO = longO; }
	
	public double getDoubleP() { return doubleP; }
	public void setDoubleP(double doubleP) { this.doubleP = doubleP; }
	
	public Double getDoubleO() { return doubleO; }
	public void setDoubleO(Double doubleO) { this.doubleO = doubleO; }
	
	public float getFloatP() { return floatP; }
	public void setFloatP(float floatP) { this.floatP = floatP; }
	
	public Float getFloatO() { return floatO; }
	public void setFloatO(Float floatO) { this.floatO = floatO; }
	
	public char getCharP() { return charP; }
	public void setCharP(char charP) { this.charP = charP; }
	
	public Character getCharO() { return charO; }
	public void setCharO(Character charO) { this.charO = charO; }
	
	public List<String> getListString() { return listString; }
	public void setListString(List<String> listString) { this.listString = listString; }
	
	public List<Boolean> getListBoolean() { return listBoolean; }
	public void setListBoolean(List<Boolean> listBoolean) { this.listBoolean = listBoolean; }
	
	public List<Integer> getListInteger() { return listInteger; }
	public void setListInteger(List<Integer> listInteger) { this.listInteger = listInteger; }
	
	public List<Long> getListLong() { return listLong; }
	public void setListLong(List<Long> listLong) { this.listLong = listLong; }
	
	public List<Double> getListDouble() { return listDouble; }
	public void setListDouble(List<Double> listDouble) { this.listDouble = listDouble; }
	
	public List<Float> getListFloat() { return listFloat; }
	public void setListFloat(List<Float> listFloat) { this.listFloat = listFloat; }
	
	public List<Character> getListChar() { return listChar; }
	public void setListChar(List<Character> listChar) { this.listChar = listChar; }

	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }

	public String getNoParam() { return noParam; }
	public void setNoParam(String noParam) { this.noParam = noParam; }
	
}
