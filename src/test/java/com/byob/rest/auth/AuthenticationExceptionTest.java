package com.byob.rest.auth;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuthenticationExceptionTest {

	@Test
	public void test() {
		final AuthenticationException exception = new AuthenticationException("test");
		assertEquals("test", exception.getMessage());
		
		
		Throwable t = new Throwable("tada");
		final AuthenticationException exception2 = new AuthenticationException("test",t);
		assertEquals("test", exception2.getMessage());
		assertEquals(t, exception2.getCause());
	}

}
