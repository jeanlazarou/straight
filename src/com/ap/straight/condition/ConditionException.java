/*
 * @author Jean Lazarou
 * Date: June 17, 2004
 */
package com.ap.straight.condition;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ConditionException extends RuntimeException {

	public ConditionException() {
		super();
	}

	public ConditionException(String msg) {
		super(msg);
	}

	public ConditionException(Throwable cause) {
		super();
		
		this.cause = cause;
	}

	public ConditionException(String msg, Throwable cause) {
		super(msg);
		
		this.cause = cause;
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream pw) {
		super.printStackTrace(pw);
		
		if (cause != null) cause.printStackTrace(pw);
	}

	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		
		if (cause != null) cause.printStackTrace(pw);
	}

	Throwable cause;
}
