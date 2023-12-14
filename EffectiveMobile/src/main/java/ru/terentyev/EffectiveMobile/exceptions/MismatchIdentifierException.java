package ru.terentyev.EffectiveMobile.exceptions;

public class MismatchIdentifierException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1940887760253777099L;

	private String message;

	public MismatchIdentifierException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
