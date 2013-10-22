/*
 * @author Jean Lazarou
 * Date: June 21, 2004
 */
package com.ap.straight.sql;

import java.sql.SQLException;

import com.ap.straight.HashTable;

import com.ap.straight.condition.Condition;
import com.ap.straight.condition.OrCondition;
import com.ap.straight.condition.AndCondition;
import com.ap.straight.condition.CompositeCondition;

public class WhereParser {

	public WhereParser(String where){
		this.where = where;
	}

	public WhereParser(String where, HashTable table){
		this.where = where;
		this.table = table;
	}
	
	public void setTable(HashTable table) {
		this.table = table;
	}
	
	public Condition extractCondition() throws SQLException {

		ConditionIterator it = new ConditionIterator(where);

		OrCondition or = new OrCondition();
		
		CompositeCondition current = or;
		
		boolean expectCondition = true;
		
		while (it.hasNext()) { 
			
			String condition = (String) it.next();
			
			if ("AND".equalsIgnoreCase(condition)){					
				
				if (expectCondition) {
					throw new SQLException("Unexpected 'and'");
				}
	
				expectCondition = true;
				
				if (current instanceof OrCondition) {

					AndCondition and = new AndCondition();

					and.add(or.takeLast());
					or.add(and);
					
					current = and;

				}

			} else if ("OR".equalsIgnoreCase(condition)){					
				
				if (expectCondition) {
					throw new SQLException("Unexpected 'or'");
				}
				
				expectCondition = true;
				
				if (current instanceof AndCondition) {
					current = or;
				}
				
			} else {
				
				String[] triplet = Parser.splitCondition(condition);

				current.add(table.createCondition(triplet));
				
				expectCondition = false;
			}
		}
		
		return or;

	}
	
	HashTable table;
	String where;
}
