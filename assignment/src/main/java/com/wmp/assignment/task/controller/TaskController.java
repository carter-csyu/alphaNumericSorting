package com.wmp.assignment.task.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wmp.assignment.common.ErrorResponse;
import com.wmp.assignment.common.WMPException;
import com.wmp.assignment.task.model.Result;
import com.wmp.assignment.task.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Resource(name="taskService")
	private TaskService taskService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String task() {
		return "task";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Result getAlpahNumericSortedString(@RequestParam("inputString") String inputString, @RequestParam("combindedUnit") String combindedUnit) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			int unit = Integer.parseInt(combindedUnit.trim());
			
			if (Integer.signum(unit) != 1) {
				throw new ArithmeticException();
			}
			
			param.put("inputString", inputString);
			param.put("combindedUnit", unit);
			
			return taskService.getAlpahNumericSortedString(param);
		} catch (NumberFormatException nfe) {
			throw new WMPException("출력묶음단위에는 자연수만 입력 가능합니다.");
		} catch (ArithmeticException ae) {
			throw new WMPException("출력묶음단위에는 자연수만 입력 가능합니다.");
		} 
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WMPException.class)
	public @ResponseBody ErrorResponse errorHandler(HttpServletRequest request, Exception e) {
		StringWriter error = new StringWriter();
		e.printStackTrace(new PrintWriter(error));
		
		logger.error(error.toString());
		
		return new ErrorResponse(e.getMessage());		
	}
}
