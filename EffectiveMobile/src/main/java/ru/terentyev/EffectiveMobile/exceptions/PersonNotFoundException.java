package ru.terentyev.EffectiveMobile.exceptions;

public class PersonNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6680648809572620136L;
	private String message;

	public PersonNotFoundException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
