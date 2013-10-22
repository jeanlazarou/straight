/*
 * @author Jean Lazarou
 * Date: June 3, 2004
 */
package com.ap.straight.types;

import com.ap.straight.util.SelectTestCase;


public class TestMultiConditionWhere extends SelectTestCase {

	public void testOR() throws Exception {
		
		String[] values = new String[] {"'A'", "'B'", "'C'", "'D'", "'C'"};

		addTable(String.class, values);
		
		selectOr("'C'", "'A'");

		verify("A", "C", "C");
		
	}

	public void testAND() throws Exception {
		
		String[] values1 = new String[] {"'A'", "'B'", "'C'", "'D'", "'C'"};
		String[] values2 = new String[] {"'0'", "'0'", "'A'", "'0'", "'A'"};

		addTable(String.class, String.class, values1, values2);
		
		selectAnd("'C'", "'A'");

		verify(new String[] {"C", "A"}, new String[] {"C", "A"});
		
	}
}
