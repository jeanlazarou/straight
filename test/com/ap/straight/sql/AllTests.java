/*
 * @author Jean Lazarou
 * Date: April 27, 2002
 */
package com.ap.straight.sql;

import junit.framework.*;

public class AllTests
{
    public static Test suite ()
    {
		TestSuite suite = new TestSuite("SQL Parser Tests");

		suite.addTest(new TestSuite(ConditionIteratorTest.class));
		suite.addTest(new TestSuite(TestConstantCondition.class));

        return suite;
	}
}
