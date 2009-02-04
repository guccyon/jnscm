package net.nanoflops.jnscm.exception;

public class ParseException extends RuntimeException{

	public ParseException(String message) {
		super(message);
	}

	public ParseException(String message, Throwable th) {
		super(message, th);
	}
}
