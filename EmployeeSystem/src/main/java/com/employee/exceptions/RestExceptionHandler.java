package com.employee.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class RestExceptionHandler {
	
		@ExceptionHandler(DepartmentNotFoundException.class)
		public ResponseEntity<Object> handleDepartmentNotFoundExceptions(DepartmentNotFoundException ex ){
			Map<String, Object> errors=new HashMap<>();
			//errors.put("timeStamp", new Date());
			errors.put("status", HttpStatus.BAD_REQUEST);
			errors.put("error", HttpStatus.NOT_FOUND);
			errors.put("message", ex.getMessage());	
			return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
			
		}

	}


