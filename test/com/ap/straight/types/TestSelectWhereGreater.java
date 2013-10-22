/*
 * @author Jean Lazarou
 * Date: June 3, 2004
 */
package com.ap.straight.types;

import java.math.BigDecimal;

import com.ap.straight.util.DateFactory;
import com.ap.straight.util.SelectTestCase;


public class TestSelectWhereGreater extends SelectTestCase {

	public void testStringField() throws Exception {
		
		addStringTable();
		
		select("'C'", '>');
		verify("D");
		
	}

	public void testIntegerField() throws Exception {
		
		addIntegerTable();
		
		select("3", '>');
		verify(new Integer(11), new Integer(14));
		
	}

	public void testIntField() throws Exception {
		
		addIntTable();
		
		select("3", '>');
		verify(11, 14);
	}

	public void testDateField() throws Exception {
		
		addDateTable();
		
		select("'2002-08-02'", '>');
		verify(DateFactory.newDate(2004, 2, 9));
	}

	public void testBigDecimalField() throws Exception {
		
		addBigDecimalTable();
		
		select("17.573", '>');
		verify(new BigDecimal("23"));
	}
}
