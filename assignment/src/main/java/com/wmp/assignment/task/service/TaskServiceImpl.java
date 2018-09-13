package com.wmp.assignment.task.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmp.assignment.task.model.Result;
import com.wmp.assignment.util.AlphaNumericString;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	AlphaNumericString alphaNumericString;
	
	@Override
	public Result getAlpahNumericSortedString(Map<String, Object> param) {
		Result result = new Result();
		
		String inputString = (String) param.get("inputString");
		int combindedUnit = (Integer) param.get("combindedUnit");
		
		String sorted = alphaNumericString.sort(inputString);
		
		result.setQuotient(alphaNumericString.getQuotient(sorted, combindedUnit));
		result.setRemainder(alphaNumericString.getRemainder(sorted, combindedUnit));
		
		return result;
	}

}
