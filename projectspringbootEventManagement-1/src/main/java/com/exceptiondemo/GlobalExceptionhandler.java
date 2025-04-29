package com.exceptiondemo;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionhandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	/*
	  @ExceptionHandler(MethodArgumentNotValidException.class) ➔ 
	 		- This method catches all MethodArgumentNotValidException.

			Method name: handleValidationException.
			returns
			-  a ResponseEntity with a Map of field names and error messages.
	 */
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		
		//Create an empty HashMap to store field names and their validation error messages.
		Map<String, String> errors = new HashMap<String, String>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMsg = error.getDefaultMessage();
			errors.put(fieldName, errorMsg);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

}