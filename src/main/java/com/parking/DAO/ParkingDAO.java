package com.parking.DAO;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.parking.Exceptions.ParkingLotsNotAvailableException;
import com.parking.Exceptions.SlotNotFoundException;
import com.parking.Model.Vehicle;

public interface ParkingDAO {
	void createParkinglots(Integer parkinglots);
	Integer parkVehicle(Vehicle vehicle) throws ParkingLotsNotAvailableException;
	Integer findSlotNumberFromRegistrationNo(String registrationNo);
	List<Integer> findAllSlotNumberForCarsWithColor(String color);
	List<String> findAllRegistrationNumberForCarsWithColor(String color);
	String leaveVehicle(Integer slotNumber) throws SlotNotFoundException;
	Set<Entry<Integer, Vehicle>> parkingStatus();
}

