package com.wmp.assignment.task.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wmp.assignment.task.model.Result;
import com.wmp.assignment.util.AlphaNumericString;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public Result getAlpahNumericSortedString(Map<String, Object> param) {
		Result result = new Result();
		
		String inputString = (String) param.get("inputString");
		int combindedUnit = (Integer) param.get("combindedUnit");
		
		String sorted = AlphaNumericString.sort(inputString);
		
		result.setQuotient(sorted.substring(0, sorted.length() - sorted.length() % combindedUnit));
		result.setRemainder(sorted.substring(sorted.length() - sorted.length() % combindedUnit, sorted.length()));
		return result;
	}

}
