package com.wmp.assignment.common;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("[START] Request URI :: " + request.getRequestURI());
			
			Enumeration<?> enumeration = request.getParameterNames();
			String key = null;
			String[] values = null;
			
			logger.debug("Request Parameters: ");
			while (enumeration.hasMoreElements()) {
				key = (String) enumeration.nextElement();
				values = request.getParameterValues(key);
				logger.debug("key=" + key + ", value=" + Arrays.toString(values));
			}
			
		}
		
		return super.preHandle(request, response, handler);
	}
}
