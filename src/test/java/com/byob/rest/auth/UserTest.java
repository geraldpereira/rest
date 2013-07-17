package com.byob.rest.auth;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.base.Optional;
import com.sun.jersey.core.util.Base64;

public class UserTest {

	private static final String PASSWORD = "BlueBridge";
	private static final String LOGIN = "neotion";
	private static final String HEADER = "Basic bmVvdGlvbjpCbHVlQnJpZGdl";

	@Test
	public void test() {

		final User user = User.newUser(HEADER);
		assertEquals(LOGIN, user.getLogin());
		assertEquals(Optional.of(PASSWORD), user.getPassword());

		final String header = User.getHeaderFromUser(user);
		assertEquals(HEADER, header);
		
		// No ":" => only a login/id is provided
		final byte[] pwd2 = Base64.encode(user.getLogin() + "" + user.getPassword().get());
		final String value2 = "Basic "+new String(pwd2);
		final User notNullUser = User.newUser(value2);
		assertNotNull(notNullUser);
		
		// No "Basic "
		final byte[] pwd = Base64.encode(user.getLogin() + ":" + user.getPassword().get());
		final String value = new String(pwd);
		final User nullUser = User.newUser(value);
		assertNull(nullUser);
		
		// Many ":" , only the first one is kept
		final byte[] pwd3 = Base64.encode(user.getLogin() + ":" + user.getPassword().get() + ":" + "test");
		final String value3 = "Basic "+new String(pwd3);
		final User notNullUser2 = User.newUser(value3);
		assertEquals(notNullUser2.getPassword().get(), user.getPassword().get() + ":" + "test");
		assertNotNull(notNullUser2);
	}

}
