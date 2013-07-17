package com.byob.rest.auth;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.sun.jersey.core.util.Base64;

/**
 * User bean for authentication 
 * 
 * @author Gerald PEREIRA
 *
 */
public final class User {

	private final String login;
	private final Optional<String> password;

	/**
	 * Constructor
	 * @param login
	 * @param password
	 */
	public User(String login, Optional<String> password) {
		super();
		Preconditions.checkNotNull(login);
		Preconditions.checkNotNull(password);
		this.login = login;
		this.password = password;
	}

	/**
	 * Returns the user login
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Returns the user password
	 * @return
	 */
	public Optional<String> getPassword() {
		return password;
	}
	
	/**
	 * Parse the header and return a user, or null if failed to parse
	 * @param authHeader
	 * @return
	 */
	public static User newUser(final String authHeader) {
		if (authHeader == null) {
			return null;
		}

		final String[] splittedHeader = authHeader.split(" ");
		if (splittedHeader.length != 2) {
			return null;
		}
		
		final String encodedValue = splittedHeader[1];
		final String[] decodedValue = Base64.base64Decode(encodedValue).split(":", 2);

		if (decodedValue.length == 2) {
			final String login = decodedValue[0];
			final String password = decodedValue[1];

			return new User(login, Optional.of(password));
		} else {
			// Only one string, it's an id 
			final String id = decodedValue[0];
			Optional<String> password = Optional.absent(); 
			return new User(id, password);
		}
	}

	/**
	 * Creates a header content from a user
	 * @param user
	 * @return
	 */
	public static String getHeaderFromUser(final User user) {
		final byte[] pwd = Base64.encode(user.getLogin() + ":" + user.getPassword().get());
		final String value = new String(pwd);
		return "Basic " + value;
	}
	
	/**
	 * Creates a header content from a user
	 * @param user
	 * @return
	 */
	public static String getHeaderFromUserId(final String userId) {
		final byte[] pwd = Base64.encode(userId);
		final String value = new String(pwd);
		return "Basic " + value;
	}
	
}
