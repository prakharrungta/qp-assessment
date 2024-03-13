package com.prgroceries.config;

public class BadInputException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8543718591131795055L;

	public BadInputException(String message) {
        super(message);
    }

}
