package com.prgroceries.config;

public class InsufficientInventoryException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9208732380535922713L;

	public InsufficientInventoryException(String message) {
        super(message);
    }

}
