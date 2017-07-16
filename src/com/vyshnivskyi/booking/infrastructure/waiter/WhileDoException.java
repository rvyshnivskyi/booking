package com.vyshnivskyi.booking.infrastructure.waiter;

public class WhileDoException extends RuntimeException {
	public WhileDoException(String message, Throwable cause) {
		super(message, cause);
	}

	public WhileDoException(String message) {
		super(message);
	}
}
