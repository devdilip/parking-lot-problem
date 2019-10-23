package com.parking.Exceptions;

public class ParkingLotsNotAvailableException extends Exception{

	public ParkingLotsNotAvailableException() {
		super();
	}

	public ParkingLotsNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParkingLotsNotAvailableException(String message) {
		super(message);
	}
}
