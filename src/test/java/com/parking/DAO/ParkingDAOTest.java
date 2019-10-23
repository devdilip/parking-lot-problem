package com.parking.DAO;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.parking.Exceptions.ParkingLotsNotAvailableException;
import com.parking.Exceptions.SlotNotFoundException;
import com.parking.Model.Vehicle;

public class ParkingDAOTest {

	private ParkingDAO parkingDAO = new ParkingDAOImpl();
	private String vehicleColorWhite = "White";
	private String vehicleColorBlack = "Black";
	private String VehicleRegistrationNo = "KR-AVS-123";

	@Test
	public void createParkingLotsUsingNoOfParking() {
		parkingDAO.createParkinglots(1);
	}

	@Test
	public void getStoredVehicleWhenParkingSlotsisEmpty() throws ParkingLotsNotAvailableException {
		parkingDAO.createParkinglots(1);
		Integer actualValue = 1;
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		Integer expectedValue = parkingDAO.parkVehicle(vehicle);
		assertEquals(expectedValue, actualValue);
	}

	@Test(expected = ParkingLotsNotAvailableException.class)
	public void getExceptionWhenParkingLotsIsNotEmpty() throws ParkingLotsNotAvailableException {
		parkingDAO.createParkinglots(1);
		Vehicle vehicle1 = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		Vehicle vehicle2 = new Vehicle("REG-BLR-456", vehicleColorBlack);
		Integer expectedValue1 = parkingDAO.parkVehicle(vehicle1);
		Integer expectedValue2 = parkingDAO.parkVehicle(vehicle2);
	}

	@Test
	public void getSlotNumberWhenVechicleIsInPark_findSlotNumberFromRegistrationNo()
			throws ParkingLotsNotAvailableException {
		parkingDAO.createParkinglots(1);
		Integer actualValue = 1;
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		Integer slotNumber = parkingDAO.findSlotNumberFromRegistrationNo(VehicleRegistrationNo);
		assertEquals(slotNumber, actualValue);
	}

	@Test
	public void getZeroSlotNumberWhenVechileIsNotInPark_findSlotNumberFromRegistrationNo() {
		Integer actualValue = 0;
		String registrationNo = "BLR-REG-123";
		Integer slotNumber = parkingDAO.findSlotNumberFromRegistrationNo(registrationNo);
		assertEquals(slotNumber, actualValue);
	}

	@Test
	public void getAllSlotsNumberForCarColor_findAllSlotNumberForCarsWithColor()
			throws ParkingLotsNotAvailableException {
		parkingDAO.createParkinglots(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		List<Integer> actualValue = new ArrayList<Integer>();
		actualValue.add(1);
		List<Integer> expectedValue = parkingDAO.findAllSlotNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void getEmptySlotsNumberForCarColor_WhenVeichleColorNotFound_findAllSlotNumberForCarsWithColor()
			throws ParkingLotsNotAvailableException {
		parkingDAO.createParkinglots(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorBlack);
		parkingDAO.parkVehicle(vehicle);
		List<Integer> actualValue = new ArrayList<Integer>();
		List<Integer> expectedValue = parkingDAO.findAllSlotNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void getAllRegistrationNoForCarFromColor_findAllRegistrationNumberForCarsWithColor()
			throws ParkingLotsNotAvailableException {
		parkingDAO.createParkinglots(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		List<String> actualValue = new ArrayList<>();
		actualValue.add(VehicleRegistrationNo);
		List<String> expectedValue = parkingDAO.findAllRegistrationNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void getEmptyRegistrationNoForCarFromColor_whenCarNotFound_findAllRegistrationNumberForCarsWithColor()
			throws ParkingLotsNotAvailableException {
		parkingDAO.createParkinglots(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		List<String> actualValue = new ArrayList<>();
		List<String> expectedValue = parkingDAO.findAllRegistrationNumberForCarsWithColor(vehicleColorBlack);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void leaveVehicle_whenCarIsAvailable() throws ParkingLotsNotAvailableException, SlotNotFoundException {
		parkingDAO.createParkinglots(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		String actualValue = "Slot 1 is free";
		String expectedValue =  parkingDAO.leaveVehicle(1);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test(expected = SlotNotFoundException.class)
	public void getMessageNotFoundAtleaveVehicle_whenCarIsNotAvailable() throws SlotNotFoundException {
		String actualValue = "3 Slot not found!";
		String expectedValue =  parkingDAO.leaveVehicle(3);
		assertEquals(expectedValue, actualValue);
	}
}

