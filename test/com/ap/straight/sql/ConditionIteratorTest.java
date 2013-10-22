/*
 * @author Jean Lazarou
 * Date: June 21, 2004
 */
package com.ap.straight.sql;

import junit.framework.TestCase;

public class ConditionIteratorTest extends TestCase {

	public void testOneTerm() throws Exception {

		ConditionIterator it = new ConditionIterator("A = 12");		
		
		assertTrue(it.hasNext());
		assertEquals("A = 12", it.next());
		
		assertTrue(!it.hasNext());
		 
	}

	public void testOneTermWithSpaces() throws Exception {

		ConditionIterator it = new ConditionIterator("  A = 12 ");		
		
		assertTrue(it.hasNext());
		assertEquals("A = 12", it.next());
		
		assertTrue(!it.hasNext());
		 
	}

	public void testAnd() throws Exception {

		ConditionIterator it = new ConditionIterator("A = 12 AND B=44");		
		
		assertTrue(it.hasNext());
		assertEquals("A = 12", it.next());

		assertTrue(it.hasNext());
		assertEquals("and", it.next());

		assertTrue(it.hasNext());
		assertEquals("B=44", it.next());

		assertTrue(!it.hasNext());
		 
	}

	public void testOr() throws Exception {

		ConditionIterator it = new ConditionIterator("A = 12 or B=44");		
		
		assertTrue(it.hasNext());
		assertEquals("A = 12", it.next());

		assertTrue(it.hasNext());
		assertEquals("or", it.next());

		assertTrue(it.hasNext());
		assertEquals("B=44", it.next());

		assertTrue(!it.hasNext());
		 
	}

	public void testBoth() throws Exception {

		ConditionIterator it = new ConditionIterator("A = 12 or B=44 and X < 25");		
		
		assertTrue(it.hasNext());
		assertEquals("A = 12", it.next());

		assertTrue(it.hasNext());
		assertEquals("or", it.next());

		assertTrue(it.hasNext());
		assertEquals("B=44", it.next());

		assertTrue(it.hasNext());
		assertEquals("and", it.next());

		assertTrue(it.hasNext());
		assertEquals("X < 25", it.next());

		assertTrue(!it.hasNext());
		 
	}

	public void testBothReversOrder() throws Exception {

		ConditionIterator it = new ConditionIterator("A = 12 and B=44 or X < 25");		
		
		assertTrue(it.hasNext());
		assertEquals("A = 12", it.next());

		assertTrue(it.hasNext());
		assertEquals("and", it.next());

		assertTrue(it.hasNext());
		assertEquals("B=44", it.next());

		assertTrue(it.hasNext());
		assertEquals("or", it.next());

		assertTrue(it.hasNext());
		assertEquals("X < 25", it.next());

		assertTrue(!it.hasNext());
		 
	}

	public void testWrongScripts() throws Exception {

		ConditionIterator it = new ConditionIterator("or B=44 and or and X < 25 or");		
		
		assertTrue(it.hasNext());
		assertEquals("or", it.next());

		assertTrue(it.hasNext());
		assertEquals("B=44", it.next());

		assertTrue(it.hasNext());
		assertEquals("and", it.next());

		assertTrue(it.hasNext());
		assertEquals("or", it.next());

		assertTrue(it.hasNext());
		assertEquals("and", it.next());

		assertTrue(it.hasNext());
		assertEquals("X < 25", it.next());

		assertTrue(it.hasNext());
		assertEquals("or", it.next());

		assertTrue(!it.hasNext());
		 
	}

	public void testAmbiguous() throws Exception {

		ConditionIterator it = new ConditionIterator("B=44 and orand < 25");		
		
		assertTrue(it.hasNext());
		assertEquals("B=44", it.next());

		assertTrue(it.hasNext());
		assertEquals("and", it.next());

		assertTrue(it.hasNext());
		assertEquals("or", it.next());

		assertTrue(it.hasNext());
		assertEquals("and", it.next());

		assertTrue(it.hasNext());
		assertEquals("< 25", it.next());

		assertTrue(!it.hasNext());
		 
	}
	
	public ConditionIteratorTest() {
		super();
	}

	public ConditionIteratorTest(String name) {
		super(name);
	}

}
