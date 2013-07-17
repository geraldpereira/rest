package com.byob.rest.exception;


import com.sun.jersey.api.client.ClientResponse.Status;

/**
 * IllegalRequestException REST exception
 * 
 * @author Gerald PEREIRA
 *
 */
public class WSException extends RuntimeException {

	private static final long serialVersionUID = -7421895058638572651L;
	
	private final Status status;

	/**
	 * Constructor
	 * @param exception
	 */
	public WSException(final Exception exception) {
		this(exception.getMessage(), Status.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Constructor
	 * @param message
	 * @param status
	 */
	public WSException(final String message, final Status status) {
		super(message);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
}