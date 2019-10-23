package com.parking.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import com.parking.Exceptions.ParkingLotsNotAvailableException;
import com.parking.Exceptions.SlotNotFoundException;
import com.parking.Model.Vehicle;

public class ParkingDAOImpl implements ParkingDAO{
	public static Map<Integer, Vehicle> parkingDetailsMap = new HashMap<>();
	public static TreeSet<Integer> availableParkingLots = new TreeSet<>();
	
	@Override
	public void createParkinglots(Integer parkinglots) {
		for (int i = 0; i < parkinglots; i++) {
			parkingDetailsMap.put(i+1, null);
			availableParkingLots.add(i+1);
		}
	}

	@Override
	public Integer parkVehicle(Vehicle vehicle) throws ParkingLotsNotAvailableException {
		if(availableParkingLots.isEmpty()) {
			throw new ParkingLotsNotAvailableException("Sorry, parking lot is full");
		}else {
			final int emptyParking = availableParkingLots.pollFirst();
			parkingDetailsMap.put(emptyParking, vehicle);
			return emptyParking;
		}
	}

	@Override
	public Integer findSlotNumberFromRegistrationNo(String registrationNo) {
		int slotNumber = 0;
		for (Map.Entry<Integer, Vehicle> entry : parkingDetailsMap.entrySet()) {
			if (entry.getValue() != null && entry.getValue().getRegistrationNo().equalsIgnoreCase(registrationNo)) {
				slotNumber = entry.getKey();
				break;
			}
		}
		return slotNumber;
	}

	@Override
	public List<Integer> findAllSlotNumberForCarsWithColor(String color) {
		List<Integer> slotList = new ArrayList<>();
		for (Map.Entry<Integer, Vehicle> entry : parkingDetailsMap.entrySet()) {
			if (entry.getValue() != null && entry.getValue().getColor().equalsIgnoreCase(color)) {
				slotList.add(entry.getKey());
			}
		}
		return slotList;
	}

	@Override
	public List<String> findAllRegistrationNumberForCarsWithColor(String color) {
		List<String> registrationNoList = new ArrayList<>();
		for (Map.Entry<Integer, Vehicle> entry : parkingDetailsMap.entrySet()) {
			if (entry.getValue() 
					!= null && entry.getValue().getColor().equalsIgnoreCase(color)) {
				registrationNoList.add(entry.getValue().getRegistrationNo());
			}
		}
		return registrationNoList;
	}

	@Override
	public String leaveVehicle(Integer slotNumber) throws SlotNotFoundException {
		for (Map.Entry<Integer, Vehicle> entry : parkingDetailsMap.entrySet()) {
			if(entry.getKey() == slotNumber) {
				parkingDetailsMap.put(entry.getKey(), null);
				availableParkingLots.add(slotNumber);
				return "Slot " + slotNumber + " is free";
			}
		}
		throw new SlotNotFoundException(slotNumber + " Slot not found!");
	}

	@Override
	public Set<Entry<Integer, Vehicle>> parkingStatus() {
		Set<Entry<Integer, Vehicle>> entry =  parkingDetailsMap.entrySet();
		return entry;
	}	
}
