/*
 * @author Jean Lazarou
 * Date: June 3, 2004
 */
package com.ap.straight.types;

import java.math.BigDecimal;

import com.ap.straight.util.DateFactory;
import com.ap.straight.util.SelectTestCase;


public class TestSelectWhereLower extends SelectTestCase {

	public void testStringField() throws Exception {
		
		addStringTable();
		
		select("'C'", '<');
		verify("A", "B");
		
	}

	public void testIntegerField() throws Exception {
		
		addIntegerTable();
		
		select("3", '<');
		verify(new Integer(1));
		
	}

	public void testIntField() throws Exception {
		
		addIntTable();
		
		select("3", '<');
		verify(1);
	}

	public void testDateField() throws Exception {
		
		addDateTable();
		
		select("'2002-08-02'", '<');
		verify(DateFactory.newDate(2000, 12, 3), DateFactory.newDate(2001, 1, 8));
	}

	public void testBigDecimalField() throws Exception {
		
		addBigDecimalTable();
		
		select("17.573", '<');
		verify(new BigDecimal("11.0"), new BigDecimal("3.876"));
	}
}
