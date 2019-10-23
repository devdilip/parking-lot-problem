package com.parking.Services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.parking.Model.Vehicle;

public class ParkingServiceTest {
	private ParkingService parkingService = new ParkingServiceImpl() ;
	private String vehicleColorWhite = "White";
	private String VehicleRegistrationNo = "KR-AVS-123";
	private Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
	
	@Test
	public void getParkingDetails_whenVehicleGotPark_parkVehicle() {
		parkingService.createParkinglots(1);
		String actualValue = "Allocated Slot Number: 1";
		String expectedValue = parkingService.parkVehicle(vehicle);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test   
	public void getParkingDetailsNotFound_whenLotsNotAvailable_parkVehicle() {
		parkingService.createParkinglots(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "Sorry, parking lot is full";
		String expectedValue = parkingService.parkVehicle(vehicle);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getSlotNumberFromRegistrationNo_findSlotNumberFromRegistrationNo() {
		parkingService.createParkinglots(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "1";
		String expectedValue = parkingService.findSlotNumberFromRegistrationNo(VehicleRegistrationNo);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getNotFoundMessageFromRegistrationNo_whenVeichleNotFound_findSlotNumberFromRegistrationNo() {
		String actualValue = "Not Found!";
		String expectedValue = parkingService.findSlotNumberFromRegistrationNo("REG-BLR-987");
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getEmptySlotNumber_leaveVehicle_leaveVehicle() {
		parkingService.createParkinglots(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "Slot 1 is free";
		String expectedValue = parkingService.leaveVehicle(1);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getMessageWhenSlotNumberNotFound_leaveVehicle() {
		String actualValue = "2 Slot not found!";
		String expectedValue = parkingService.leaveVehicle(2);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getRegistrationNumberWithVeichleColor_findAllRegistrationNumberForCarsWithColor() {
		parkingService.createParkinglots(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = VehicleRegistrationNo;
		String expectedValue = parkingService.findAllRegistrationNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getRegistrationNumberAsEmpty_whenVeichleColorNotFound_findAllRegistrationNumberForCarsWithColor() {
		parkingService.createParkinglots(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "Not Found!";
		String expectedValue = parkingService.findAllRegistrationNumberForCarsWithColor("Black");
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getSlotsNumberWithVeichleColor_findAllSlotNumberForCarsWithColor() {
		parkingService.createParkinglots(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "1";
		String expectedValue = parkingService.findAllSlotNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getSlotsNumberIsEmpty_whenVeichleColorNotFound_findAllSlotNumberForCarsWithColor() {
		parkingService.createParkinglots(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "Not Found!";
		String expectedValue = parkingService.findAllSlotNumberForCarsWithColor("Black");
		assertEquals(expectedValue, actualValue);
	}
}
