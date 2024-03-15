package com.prgroceries.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@ControllerAdvice
public class PRExceptionHandler {
	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		 ex.printStackTrace();
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	 }
	 
	 @ExceptionHandler(BadInputException.class)
	 public ResponseEntity<String> handleBadInputException(BadInputException ex) {
		 ex.printStackTrace();
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	 }
	 
	 @ExceptionHandler(InsufficientInventoryException.class)
	 public ResponseEntity<String> handleInsufficientInventoryException(InsufficientInventoryException ex) {
		 ex.printStackTrace();
	     return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	 }
	 
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<String> handleAllException(Exception ex) {
		 ex.printStackTrace();
	     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	 }
}
