package com.byob.rest.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

public abstract class AuthenticationFilter implements Filter {

	private static final String OPTIONS = "OPTIONS";

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		// OPTIONS methods is used for CORS, no auth is required with this
		// method
		if (!OPTIONS.equals(httpRequest.getMethod())) {
			final String authHeader = httpRequest.getHeader("authorization");

			final User headerUser = User.newUser(authHeader);
			if (headerUser == null) {
				log().warn("Login failed : no  auth header!");
				sendError(httpResponse,"Login failed: no authorization header");
				return;
			}
			try {
				authorize(headerUser,httpRequest);
			} catch (AuthenticationException e) {
				log().info("Login failed for " + headerUser.getLogin());
				sendError(httpResponse,e.getMessage());
				return;
			}
			log().info("Login success for " + headerUser.getLogin());
		}
		chain.doFilter(request, response);
 
	}

	
	private static void sendError (final HttpServletResponse httpResponse, final String message) throws IOException{
		httpResponse.setContentType("text/plain");
		httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		httpResponse.getWriter().println(message);
	}
	
	protected abstract void authorize(final User user, final HttpServletRequest request)
			throws AuthenticationException;

	protected abstract Logger log();
	
	@Override
	public void destroy() {

	}
}
