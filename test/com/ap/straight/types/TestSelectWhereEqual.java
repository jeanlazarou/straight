/*
 * @author Jean Lazarou
 * Date: June 3, 2004
 */
package com.ap.straight.types;

import java.math.BigDecimal;

import com.ap.straight.util.DateFactory;
import com.ap.straight.util.SelectTestCase;


public class TestSelectWhereEqual extends SelectTestCase {

	public void testStringField() throws Exception {
		
		addStringTable();
		
		select("'C'");
		verify("C", "C");
		
	}

	public void testIntegerField() throws Exception {

		addIntegerTable();		

		select("3");
		verify(new Integer(3), new Integer(3));
		
	}

	public void testIntField() throws Exception {

		addIntTable();		
		
		select("3");
		verify(3, 3);
	}

	public void testDateField() throws Exception {

		addDateTable();		
		
		select("'2002-08-02'");
		verify(DateFactory.newDate(2002, 8, 2), DateFactory.newDate(2002, 8, 2));
	}

	public void testBigDecimalField() throws Exception {

		addBigDecimalTable();
		
		select("17.573");
		verify(new BigDecimal("17.573"), new BigDecimal("17.573"));
	}

}
