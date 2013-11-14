package org.tutsflow.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class BeanToStringTest {

	private Logger log = Logger.getLogger(BeanToStringTest.class);
	
	@Test
	public void utilTest() {
		
		// TB Padre
		TestBean tb =  new TestBean();
		List<Long> tbl = new ArrayList<Long>();
		tbl.add(0L);tbl.add(1L);tbl.add(2L);tbl.add(3L);
		tb.setList1(tbl);
		
		// TB Hijo 1
		TestBean tbs =  new TestBean();
		tbs.setString1("SON");
		List<Long> tbsl = new ArrayList<Long>();
		tbsl.add(4L);tbsl.add(5L);tbsl.add(6L);tbsl.add(7L);
		tb.setList1(tbsl);
		
		// TB Nieto 1
		TestBean tbg =  new TestBean();
		tbg.setString1("GRANDSON");
		List<Long> tbgl = new ArrayList<Long>();
		tbgl.add(8L);tbgl.add(9L);tbgl.add(10L);tbgl.add(11L);
		tb.setList1(tbgl);
		 
		// Asignamiento
		
		tbs.setTb(tbg);
		tb.setTb(tbs);
		
		tb.toStringLog(log);
		System.out.println(tb.toString());
		System.out.println(tb.toStringLite());
	}
	
}
