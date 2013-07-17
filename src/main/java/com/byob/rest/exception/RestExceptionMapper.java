package com.byob.rest.exception;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * Maps an IllegalRequestException to an HTTP response
 * 
 * @author Gerald PEREIRA
 *
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<WSException> {

	@Override
	public Response toResponse(final WSException e) {
		return Response.status(e.getStatus()).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}

}