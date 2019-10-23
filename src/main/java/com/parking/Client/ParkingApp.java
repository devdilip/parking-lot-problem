package com.parking.Client;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.parking.Model.Vehicle;
import com.parking.Services.ParkingService;
import com.parking.Services.ParkingServiceImpl;
import com.parking.Util.ParkingConstants;
import com.parking.Util.ParkingUtils;

/**
 * Author: Dilip Kumar
 **/

public class ParkingApp {

	static ParkingService parkingService = new ParkingServiceImpl();
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int noOfParkingSlots = 0;
		while (true) {
			try {
				System.out.println("create_parking_lot : ");
				noOfParkingSlots = in.nextInt();
				break;
			} catch (Exception e) {
				in.next();
				System.out.println("Please input correct number.");
			}
		}
		parkingService.createParkinglots(noOfParkingSlots);
		System.out.println("Created a parking lot with " + noOfParkingSlots + " slots");
		in.nextLine();
		do {
			getParkingMenuInput(in.nextLine());
		} while (true);
	}

	private static void getParkingMenuInput(String input) {
		String[] inputArr = input.split(" ");
		switch (inputArr[0]) {
		case ParkingConstants.PARK: // Park Vechile
			parkVehicle(inputArr);
			break;
		case ParkingConstants.STATUS: // Check Vechile Status
			checkParkingStatus();
			break;
		case ParkingConstants.LEAVE: // Leave Parking
			if (ParkingUtils.vaidateCommand(inputArr, 2) && ParkingUtils.integerInputValidation(inputArr[1])) {
				String leaveParkingStatus = parkingService.leaveVehicle(Integer.parseInt(inputArr[1]));
				System.out.println(leaveParkingStatus);
			}
			break;
		case ParkingConstants.REGIATRATION_NUMBER_FOR_CARS_WITH_COLOR: // Search Registration Number From Vechile Color
			if (ParkingUtils.vaidateCommand(inputArr, 2)) {
				String allDetails = parkingService.findAllRegistrationNumberForCarsWithColor(inputArr[1]);
				System.out.println(allDetails);
			}
			break;
		case ParkingConstants.SLOT_NUMBERS_FOR_CARS_COLOR: // Search Slot Number From Vechile Color
			if (ParkingUtils.vaidateCommand(inputArr, 2)) {
				String allSlotNumber = parkingService.findAllSlotNumberForCarsWithColor(inputArr[1]);
				System.out.println(allSlotNumber);
			}
			break;
		case ParkingConstants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER: // Search Slot Number From Registration Number
			if (ParkingUtils.vaidateCommand(inputArr, 2)) {
				String slotNoStatusOfRegNo = parkingService.findSlotNumberFromRegistrationNo(inputArr[1]);
				System.out.println(slotNoStatusOfRegNo);
			}
			break;
		case ParkingConstants.HELP: // Help
			displayCommands();
			break;
		case ParkingConstants.EXIT: // Exit
			System.out.println("Application Closed.");
			System.exit(0);
			break;

		default:
			System.out.println("The entered value is unrecognized! Please enter help for all commands");
			break;
		}
	}

	private static void displayCommands() {
		System.out.println("\n          Vechile Parking Command Help");
		System.out.println("--------------------------------------");
		System.out.println("park <RegistrationNo> <Color>");
		System.out.println("status");
		System.out.println("leave <Parking lot Number>");
		System.out.println("registration_numbers_for_cars_with_colour <color>");
		System.out.println("slot_numbers_for_cars_with_colour <color>");
		System.out.println("slot_number_for_registration_number <Registration Number>");
		System.out.println("exit\n");
	}

	public static void parkVehicle(String[] vehicleDetailsArr) {
		if (ParkingUtils.vaidateCommand(vehicleDetailsArr, 3)) {
			Vehicle vehicle = new Vehicle(vehicleDetailsArr[1], vehicleDetailsArr[2]);
			String parkVehicleStatus = parkingService.parkVehicle(vehicle);
			System.out.println(parkVehicleStatus);
		}
	}

	public static void checkParkingStatus() {
		Set<Entry<Integer, Vehicle>> parkingDetails = parkingService.parkingStatus();
		System.out.println("Slot No." + "  " + " Registration No " + "\t" + " Color");
		for (Entry<Integer, Vehicle> e : parkingDetails) {
			if (e.getValue() != null)
				System.out.println(
						e.getKey() + "\t    " + e.getValue().getRegistrationNo() + "\t\t  " + e.getValue().getColor());
		}
	}
}
