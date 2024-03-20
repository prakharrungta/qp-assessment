package com.prgroceries.config;

public class InventoryInsufficientException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9208732380535922713L;

	public InventoryInsufficientException(String message) {
        super(message);
    }

}
