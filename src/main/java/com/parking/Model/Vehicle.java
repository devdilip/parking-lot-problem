package com.parking.Model;


public class Vehicle {
	private String registrationNo;
	private String color;
	
	public Vehicle() {
		super();
	}

	public Vehicle(String registrationNo, String color) {
		super();
		this.registrationNo = registrationNo;
		this.color = color;
	}

	@Override
	public String toString() {
		return "registrationNo=" + registrationNo + ", color=" + color;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public String getColor() {
		return color;
	}
}
