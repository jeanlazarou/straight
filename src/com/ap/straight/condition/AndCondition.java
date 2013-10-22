/*
 * @author Jean Lazarou
 * Date: June 16, 2002
 */
package com.ap.straight.condition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AndCondition implements CompositeCondition {

	public AndCondition() {
		super();
	}

	public void add(Condition cond) {
		conditions.add(cond);
	}
	
	public Condition takeLast() {
		
		if (conditions.size() == 0) {
			throw new NoSuchElementException(); 
		}
		
		return (Condition) conditions.remove(conditions.size() - 1);
		
	}
	
	public boolean isConstant() {
		
		if (conditions.size() == 0) return true;
		
		for (Iterator it = conditions.iterator(); it.hasNext();) {
			if (!((Condition) it.next()).isConstant()) return false; 			
		}
		
		return true;
		
	}

	public boolean isTrue() {
		
		if (conditions.size() == 0) return true;
		
		for (Iterator it = conditions.iterator(); it.hasNext();) {
			if (!((Condition) it.next()).isTrue()) return false; 			
		}
		
		return true;
		
	}

	public boolean accept(int rowIndex) {
		
		if (conditions.size() == 0) return true;
		
		for (Iterator it = conditions.iterator(); it.hasNext();) {
			if (!((Condition) it.next()).accept(rowIndex)) return false; 			
		}
		
		return true;
		
	}

	List conditions = new ArrayList();
}
