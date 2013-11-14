package org.tutsflow.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.tutsflow.web.FormUtils;

public class FormUtilsTest {

	@Test
	public void main() {
		
		// First we create a mocked request
		MockHttpServletRequest mockReq = new MockHttpServletRequest();
		
		// Then we emulate a request with all the parameters
		mockReq.setParameter(TestForm.STRING, "test string");

		mockReq.setParameter(TestForm.BOOLEAN_P, "true");
		mockReq.setParameter(TestForm.BOOLEAN_O, "true");
		
		mockReq.setParameter(TestForm.INT_P, "11");
		mockReq.setParameter(TestForm.INT_O, "12");
		
		mockReq.setParameter(TestForm.LONG_P, "21");
		mockReq.setParameter(TestForm.LONG_O, "22");
		
		mockReq.setParameter(TestForm.DOUBLE_P, "3.1");
		mockReq.setParameter(TestForm.DOUBLE_O, "3,2");
		
		mockReq.setParameter(TestForm.FLOAT_P, "4.1");
		mockReq.setParameter(TestForm.FLOAT_O, "4,2");
		
		mockReq.setParameter(TestForm.CHAR_P, "a");
		mockReq.setParameter(TestForm.CHAR_O, "b");
		
		String[] stringL = {"String1", "String1"};
		mockReq.setParameter(TestForm.LIST_STRING, stringL);
		
		String[] booleanL = {"true", "false"};
		mockReq.setParameter(TestForm.LIST_BOOLEAN, booleanL);
		
		String[] intL = {"111", "112"};
		mockReq.setParameter(TestForm.LIST_INT, intL);
		
		String[] longL = {"211", "212"};
		mockReq.setParameter(TestForm.LIST_LONG, longL);
		
		String[] doubleL = {"31.1", "31,2"};
		mockReq.setParameter(TestForm.LIST_DOUBLE, doubleL);
		
		String[] floatL = {"41.1", "41,2"};
		mockReq.setParameter(TestForm.LIST_FLOAT, floatL);
		
		String[] charL = {"x", "y"};
		mockReq.setParameter(TestForm.LIST_CHAR, charL);
		
		mockReq.setParameter(TestForm.DATE_YEAR, "1991");
		mockReq.setParameter(TestForm.DATE_MONTH, "08");
		mockReq.setParameter(TestForm.DATE_DAY, "21");
		
		mockReq.setParameter(TestForm.NO_PARAM, "This field is not annotated then it won't "
				+ " be filled even if the parametter is the same than the field");
		
		// Then we run the "magic"
		TestForm tb = FormUtils.fillForm(mockReq, TestForm.class);
		
		// finally we check the output
		System.out.println(tb.toString());
		
		// Test cases
		Assert.assertEquals(tb.getStringO(), "test string");
		
		Assert.assertTrue(tb.isBooleanP());
		Assert.assertTrue(tb.getBooleanO());
		
		Assert.assertTrue(tb.getIntP() == 11);
		Assert.assertTrue(tb.getIntO() == 12);
		
		Assert.assertTrue(tb.getLongP() == 21L);
		Assert.assertTrue(tb.getLongO() == 22L);
		
		Assert.assertTrue(tb.getDoubleP() == 3.1);
		Assert.assertTrue(tb.getDoubleO() == 3.2);
		
		Assert.assertTrue(tb.getFloatP() == 4.1F);
		Assert.assertTrue(tb.getFloatO() == 4.2F);
		
		Assert.assertTrue(tb.getCharP() == 'a');
		Assert.assertTrue(tb.getCharO() == 'b');
		
		Assert.assertEquals(tb.getListString(), ListUtils.getStringList(stringL));
		Assert.assertEquals(tb.getListBoolean(), ListUtils.getBooleanList(booleanL));
		Assert.assertEquals(tb.getListInteger(), ListUtils.getIntegerList(intL));
		Assert.assertEquals(tb.getListLong(), ListUtils.getLongList(longL));
		Assert.assertEquals(tb.getListDouble(), ListUtils.getDoubleList(doubleL));
		Assert.assertEquals(tb.getListFloat(), ListUtils.getFloatList(floatL));
		Assert.assertEquals(tb.getListChar(), ListUtils.getCharacterList(charL));
		
	}
	
}
