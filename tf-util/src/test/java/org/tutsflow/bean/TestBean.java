package org.tutsflow.bean;

import java.util.List;

import org.tutsflow.util.BeanToString;

public class TestBean extends BeanToString{

	private String string1;
	private boolean boolean1;
//	@NoString
	private List<Long> list1;
	private TestBean tb;
	
	
	public String getString1() {
		return string1;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}

	public boolean getBoolean1() {
		return boolean1;
	}

	public void setBoolean1(boolean boolean1) {
		this.boolean1 = boolean1;
	}

	public List<Long> getList1() {
		return list1;
	}

	public void setList1(List<Long> list1) {
		this.list1 = list1;
	}

	public TestBean getTb() {
		return tb;
	}

	public void setTb(TestBean tb) {
		this.tb = tb;
	}
	
}
