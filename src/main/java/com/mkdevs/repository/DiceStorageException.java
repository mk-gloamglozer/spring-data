package com.mkdevs.repository;

public class DiceStorageException extends RuntimeException {

	private static final long serialVersionUID = 677393358210403044L;

	public DiceStorageException() {
		super();
	}

	public DiceStorageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DiceStorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public DiceStorageException(String message) {
		super(message);
	}

	public DiceStorageException(Throwable cause) {
		super(cause);
	}
	
	
	
}
