package com.wmp.assignment.common.aop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlphaNumericStringAspect {
	private static final Logger logger = LoggerFactory.getLogger(AlphaNumericStringAspect.class);
	Map<String, String> resultMap = new HashMap<String, String>();
	
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
	
	public String sorting(ProceedingJoinPoint jp, String str) throws Throwable {
		long start = 0;
		long end = 0;
		
		logger.debug("inputString: " + str);
		
		start = System.currentTimeMillis();
		String sortedStr = resultMap.get(str);
		if (resultMap.get(str) == null) {
			sortedStr = String.valueOf(jp.proceed());
			resultMap.put(str, sortedStr);
		}
		end = System.currentTimeMillis();
		
		logger.debug("sortedString: " + sortedStr);		
		logger.debug("Usage time(millis): " + (end - start));
		
		return sortedStr;
	}
	
	public void printResultMap() {
		Set<Entry<String, String>> entry = resultMap.entrySet();
		Iterator<Entry<String, String>> it = entry.iterator();
		
		logger.info("printResultMap");
		while(it.hasNext()) {
			Map.Entry<String, String> e = it.next();
			logger.info("inputString: " + e.getKey());
			logger.info("sortedString: " + e.getValue());
		}
	}
}
