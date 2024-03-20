package com.prgroceries.config;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ControllerAdvice
public class PRExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<PRExceptionHandler.MyException> handleResourceNotFoundException(
			ResourceNotFoundException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new MyException(ex.getMessage(), ex.getClass().getName()));
	}

	@ExceptionHandler(BadInputException.class)
	public ResponseEntity<PRExceptionHandler.MyException> handleBadInputException(BadInputException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MyException(ex.getMessage(), ex.getClass().getName()));
	}

	@ExceptionHandler(InventoryInsufficientException.class)
	public ResponseEntity<PRExceptionHandler.MyException> handleInsufficientInventoryException(
			InventoryInsufficientException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new MyException(ex.getMessage(), ex.getClass().getName()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<PRExceptionHandler.MyException> handleAllException(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new MyException(ex.getMessage(), ex.getClass().getName()));
	}

	@AllArgsConstructor
	@Getter
	private class MyException implements Serializable {
		/**
		* 
		*/
		private static final long serialVersionUID = -6463811542308424620L;
		private String errorMessage;
		private String errorType;

	}
}
