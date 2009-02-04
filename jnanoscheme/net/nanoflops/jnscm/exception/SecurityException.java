package net.nanoflops.jnscm.exception;

public class SecurityException extends RuntimeException {

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(String message, Throwable th) {
		super(message, th);
	}
}
