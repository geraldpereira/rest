package com.byob.rest;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class CORSHeadersFilterTest {

	@Test
	public void filterTest() throws ServletException, IOException{
		final CORSHeadersFilter filter = new CORSHeadersFilter();
		filter.init(null);
		final HttpServletRequest request = mock(HttpServletRequest.class);
		final HttpServletResponse response = mock(HttpServletResponse.class);
		final FilterChain chain = mock(FilterChain.class);
		filter.doFilter(request, response, chain);
		verify(response,times(5)).setHeader(anyString(), anyString());
		filter.destroy();
	}
	
}
