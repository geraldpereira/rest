package com.byob.rest.exception;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse.Status;

public class ExceptionTest {

	@Test
	public void testException() {
		final WSException exception = new WSException(new Exception("test"));
		assertEquals(Status.INTERNAL_SERVER_ERROR, exception.getStatus());
		assertEquals("test", exception.getMessage());
		
		final RestExceptionMapper mapper = new RestExceptionMapper();
		final Response response = mapper.toResponse(exception);
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
		assertEquals("test", response.getEntity().toString());
	}

	@Test
	public void test() {
		final WSException exception = new WSException("test",Status.BAD_REQUEST);
		assertEquals(Status.BAD_REQUEST, exception.getStatus());
		assertEquals("test", exception.getMessage());
		
		final RestExceptionMapper mapper = new RestExceptionMapper();
		final Response response = mapper.toResponse(exception);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals("test", response.getEntity().toString());
	}
}
