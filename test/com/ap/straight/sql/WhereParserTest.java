/*
 * @author Jean Lazarou
 * Date: June 21, 2004
 */
package com.ap.straight.sql;

import com.ap.straight.condition.Condition;

import junit.framework.TestCase;

public class WhereParserTest extends TestCase {

	public WhereParserTest() {
		super();
	}

	public WhereParserTest(String name) {
		super(name);
	}

	public void testOneTerm() throws Exception {
		
		WhereParser parser = new WhereParser("40 > 33");
		
		Condition condition = parser.extractCondition();
		
		assertTrue(condition.isTrue());
		 
	}
	
}
