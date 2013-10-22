/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight.types;

import junit.framework.*;

public class AllTests
{
    public static Test suite ()
    {
		TestSuite suite= new TestSuite("SupportedTypesTests");

		suite.addTest(new TestSuite(TestCreateTable.class));
		suite.addTest(new TestSuite(TestInsert.class));		
		suite.addTest(new TestSuite(TestSelectWhereEqual.class));		
		suite.addTest(new TestSuite(TestSelectWhereLower.class));		
		suite.addTest(new TestSuite(TestSelectWhereGreater.class));		
		suite.addTest(new TestSuite(TestMultiConditionWhere.class));
		
        return suite;
	}
}
