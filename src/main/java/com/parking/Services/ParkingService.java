package com.parking.Services;

import java.util.Map.Entry;
import java.util.Set;

import com.parking.Model.Vehicle;

public interface ParkingService {
	void createParkinglots(Integer parkinglots);
	String parkVehicle(Vehicle vehicle);
	String findSlotNumberFromRegistrationNo(String registrationNo);
	String findAllSlotNumberForCarsWithColor(String color);
	String findAllRegistrationNumberForCarsWithColor(String color);
	String leaveVehicle(Integer slotNumber);
	Set<Entry<Integer, Vehicle>> parkingStatus();
}

