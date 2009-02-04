package net.nanoflops.jnscm.exception;

public class EvalException extends RuntimeException {

	public EvalException(String message) {
		super(message);
	}

	public EvalException(String message, Throwable th) {
		super(message, th);
	}

}
