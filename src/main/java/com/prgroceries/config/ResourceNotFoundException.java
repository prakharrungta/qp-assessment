package com.prgroceries.config;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6373110453708894735L;
	public ResourceNotFoundException(String message) {
        super(message);
    }

}
