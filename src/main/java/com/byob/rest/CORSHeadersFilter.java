package com.byob.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

@Singleton
public class CORSHeadersFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletResponse httpReponse = (HttpServletResponse) response;
		httpReponse.setHeader("Access-Control-Allow-Origin", "*");
		httpReponse.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, UPDATE, OPTIONS");
		httpReponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpReponse.setHeader("Access-Control-Expose-Headers", "Authorization, Content-type, x-requested-with, authorization, content-type, x-http-method-override");
		httpReponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-type, x-requested-with, authorization, content-type, x-http-method-override");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
