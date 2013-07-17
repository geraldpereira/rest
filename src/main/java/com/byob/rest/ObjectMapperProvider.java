package com.byob.rest;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * http://jersey.576304.n2.nabble.com/Customizing-ObjectMapper-td6234597.html
 * 
 * @author Gerald PEREIRA
 * 
 */
@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
	private final ObjectMapper mapper;

	/**
	 * ObjectMapperProvider Constructor
	 * 
	 * avoids "field":null in the JSON responses
	 */
	public ObjectMapperProvider() {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}
}
