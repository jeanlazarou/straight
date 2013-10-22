/*
 * @author Jean Lazarou
 * Date: April 27, 2002
 */
package com.ap.straight;

import junit.framework.*;

public class AllTests
{
    public static Test suite ()
    {
		TestSuite suite= new TestSuite("HashDatabase Tests");

        suite.addTest(new TestSuite(BasicTest.class));
        suite.addTest(new TestSuite(InsertRowsTest.class));
		suite.addTest(new TestSuite(SelectTest.class));
		suite.addTest(new TestSuite(DeleteTest.class));
		suite.addTest(new TestSuite(PreparedSelectTest.class));		
		suite.addTest(new TestSuite(PreparedInsertRowsTest.class));
		suite.addTest(new TestSuite(TestCanonicalSQL.class));

		suite.addTest(com.ap.straight.types.AllTests.suite());

        return suite;
	}
}
