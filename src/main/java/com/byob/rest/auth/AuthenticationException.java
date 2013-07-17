package com.byob.rest.auth;


/**
 * Athentication exception
 * 
 * @author Gerald PEREIRA
 *
 */
public final class AuthenticationException extends Exception {

	private static final long serialVersionUID = -7421895058638572651L;
	
	/**
	 * Constructor
	 * @param exception
	 */
	public AuthenticationException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * @param exception
	 */
	public AuthenticationException(final String message, final Throwable cause) {
		super(message,cause);
	}
}