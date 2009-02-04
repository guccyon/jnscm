package net.nanoflops.jnscm.exception;

public class IOException extends RuntimeException {

	public IOException(String message) {
		super(message);
	}

	public IOException(String message, Throwable th) {
		super(message, th);
	}
}
