package com.wmp.assignment.common.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlphaNumericStringAspect {
	private static final Logger logger = LoggerFactory.getLogger(AlphaNumericStringAspect.class);
	
	public AlphaNumericStringAspect() {}
	
	public void beforeSorting(JoinPoint joinPoint) {
		Object[] argus = joinPoint.getArgs();
		
		for (Object arg : argus) {
			logger.debug("inputString: " + arg.toString());
		}
	}
	
	public void afterSorting(JoinPoint joinPoint, Object sortedStr) {
		logger.debug("sortedString: " + sortedStr.toString());
	}
}
