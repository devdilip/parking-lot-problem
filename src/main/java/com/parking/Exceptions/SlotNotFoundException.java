package com.parking.Exceptions;

public class SlotNotFoundException extends Exception {

	public SlotNotFoundException() {
		super();
	}

	public SlotNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SlotNotFoundException(String message) {
		super(message);
	}
}
