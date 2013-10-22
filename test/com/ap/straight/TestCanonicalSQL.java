/*
 * @author: Jean Lazarou
 * @date: 1 mars 04
 */
package com.ap.straight;

import junit.framework.TestCase;

public class TestCanonicalSQL extends TestCase {

	public TestCanonicalSQL(String name) {
		super(name);
	}

	public void testLowerCase() {
		assertEquals("select * from friends where x=34", 
			SQL.normalize("SELECT * FROM friends WHERE x = 34"));
	}

	public void testRemoveSpaces() {
		assertEquals("select * from friends where x=34", 
			SQL.normalize(" SELECT   *   FROM    friends  where x  =  34  "));
	}

	public void testLeaveStringsUnchanged() {
		assertEquals("select * from friends where x=' My Name is NOBODY'", 
			SQL.normalize(" SELECT * FROM friends WHERE x  = ' My Name is NOBODY'"));

		assertEquals("select * from friends where x='I don''t know'", 
			SQL.normalize(" SELECT * FROM friends WHERE x  = 'I don''t know'"));

		assertEquals("select * from friends where x='Don''t' and y=1", 
			SQL.normalize(" SELECT * FROM friends WHERE x  = 'Don''t' AND y=1"));

		assertEquals("select * from friends where x='  a + 34' and y=1", 
			SQL.normalize(" SELECT * FROM friends WHERE x  = '  a + 34' AND y=1"));
	}

	public void testSeveralLines() {
		assertEquals("select * from friends where x=34", 
			SQL.normalize("SELECT * FROM\nfriends\nwhere x = 34"));
	}
}
