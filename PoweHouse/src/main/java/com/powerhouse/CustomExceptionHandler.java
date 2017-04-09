package com.powerhouse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends Exception{
	private static final long serialVersionUID = 1L;
	
	public static String SQL_MESSAGE ="Duplicate Entry: The profle for this meter exist. Please check the meter name and id";
	
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public ResponseEntity<Object> handleGeneralException(
	    		DataIntegrityViolationException e){
	    	//using object for simplicity, we can create a class for error respose and replace object with that class
					return new ResponseEntity<Object>(SQL_MESSAGE , HttpStatus.BAD_REQUEST);
	    }

}
