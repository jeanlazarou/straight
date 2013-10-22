/*
 * Created on June 4, 2004
 */
package com.ap.straight.util;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DateFormatException extends IllegalArgumentException {

	public DateFormatException() {
		super();
	}

	public DateFormatException(String message) {
		super(message);
	}

	public DateFormatException(Throwable cause, String message) {
		super(message);
		
		this.cause = cause;		
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (cause != null) cause.printStackTrace();
	}

	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
		if (cause != null) cause.printStackTrace(ps);
	}

	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		if (cause != null) cause.printStackTrace(pw);
	}
	
	Throwable cause;

}
