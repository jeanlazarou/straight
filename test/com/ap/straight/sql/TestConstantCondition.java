/*
 * @author Jean Lazarou
 * Date: June 21, 2004
 */
package com.ap.straight.sql;

import com.ap.straight.HashTable;
import com.ap.straight.condition.Condition;

import junit.framework.TestCase;

public class TestConstantCondition extends TestCase {

	public void testTrue() throws Exception {
		
		WhereParser parser = new WhereParser("40 > 33", table);
		
		Condition condition = parser.extractCondition();
		
		assertTrue(condition.isTrue());
		 
	}

	public void testFalse() throws Exception {
		
		WhereParser parser = new WhereParser("40 < 33", table);
		
		Condition condition = parser.extractCondition();
		
		assertTrue(!condition.isTrue());
		 
	}
	
	public void testTrueWithAnd() throws Exception {
		
		WhereParser parser = new WhereParser("40 > 33 AND 20 < 120", table);
		
		Condition condition = parser.extractCondition();
		
		assertTrue(condition.isTrue());
		 
	}
	
	public void testTrueWithOr() throws Exception {
		
		WhereParser parser = new WhereParser("40 < 33 OR 20 < 120", table);
		
		Condition condition = parser.extractCondition();
		
		assertTrue(condition.isTrue());
		 
	}
	
	public void testAnd() throws Exception {
		
		WhereParser parser = new WhereParser("40 < 33 AND 20 > 120", table);
		
		Condition condition = parser.extractCondition();
		
		assertTrue(!condition.isTrue());
		
		parser = new WhereParser("40 < 33 AND 20 < 120", table);
		
		condition = parser.extractCondition();
		
		assertTrue(!condition.isTrue());
		
		parser = new WhereParser("40 > 33 AND 20 > 120", table);
		
		condition = parser.extractCondition();
		
		assertTrue(!condition.isTrue());
		 
	}
	
	public void testOr() throws Exception {
		
		WhereParser parser = new WhereParser("40 < 33 OR 20 > 120", table);
		
		Condition condition = parser.extractCondition();
		
		assertTrue(!condition.isTrue());
		 
		parser = new WhereParser("40 < 33 OR 20 < 120", table);
		
		condition = parser.extractCondition();
		
		assertTrue(condition.isTrue());
		 
		parser = new WhereParser("40 > 33 OR 20 > 120", table);
		
		condition = parser.extractCondition();
		
		assertTrue(condition.isTrue());
		 
	}
	
	public void testAndAndOr() throws Exception {
		
		WhereParser parser = new WhereParser("40 > 33 OR 20 > 120 AND 10 = 12", table);
		
		Condition condition = parser.extractCondition();
		
		assertTrue(condition.isTrue());

	}
	
	public void testLong() throws Exception {
		
		WhereParser parser = new WhereParser("40 > 33 OR 20 > 120 AND 10 = 12 OR 15 <= 10 AND 10 >= 10", table);
		
		Condition condition = parser.extractCondition();
		
		assertTrue(condition.isTrue());

	}
	
	protected void setUp() throws Exception {
		table = new HashTable();
	}

	public TestConstantCondition() {
		super();
	}

	public TestConstantCondition(String name) {
		super(name);
	}

	HashTable table;
}
