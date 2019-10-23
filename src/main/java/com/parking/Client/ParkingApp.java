package com.parking.Client;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.parking.Model.Vehicle;
import com.parking.Services.ParkingService;
import com.parking.Services.ParkingServiceImpl;

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
		switch (input) {
		case "park": // Park Vechile
			try {
				parkVehicle();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "status": // Check Vechile Status
			checkParkingStatus();
			break;
		case "leave": // Leave Parking
			System.out.println("Enter parking lot no : ");
			int leave = in.nextInt();
			String leaveParkingStatus = parkingService.leaveVehicle(leave);
			in.nextLine();
			System.out.println(leaveParkingStatus);
			break;
		case "registration_numbers_for_cars_with_colour": // Search Registration Number From Vechile Color
			System.out.println("search registration number from Vechile color : ");
			String vehicilColorForCheckRegistrationNo = in.nextLine();
			String allDetails = parkingService
					.findAllRegistrationNumberForCarsWithColor(vehicilColorForCheckRegistrationNo);
			System.out.println(allDetails);
			break;
		case "slot_numbers_for_cars_with_colour": // Search Slot Number From Vechile Color
			System.out.println("Search slot number from vechlie color : ");
			String colorForCheckSlotNumber = in.nextLine();
			String allSlotNumber = parkingService.findAllSlotNumberForCarsWithColor(colorForCheckSlotNumber);
			System.out.println(allSlotNumber);
			break;
		case "slot_number_for_registration_number": // Search Slot Number From Registration Number
			System.out.println("Search slot number from Vechile registration number : ");
			String regNo = in.nextLine();
			String slotNoStatusOfRegNo = parkingService.findSlotNumberFromRegistrationNo(regNo);
			System.out.println(slotNoStatusOfRegNo);
			break;
		case "help": // Exit
			System.out.println("\n          Vechile Parking Command Help");
			System.out.println("--------------------------------------");
			System.out.println("park <RegistrationNo> <Color>");
			System.out.println("status");
			System.out.println("leave <Parking lot Number>");
			System.out.println("registration_numbers_for_cars_with_colour <color>");
			System.out.println("slot_numbers_for_cars_with_colour <color>");
			System.out.println("slot_number_for_registration_number <Registration Number>");
			System.out.println("exit\n");
			break;
		case "exit": // Exit
			System.out.println("Application Closed.");
			System.exit(0);
			break;

		default:
			System.out.println("The entered value is unrecognized! Please enter help for all commands");
			break;
		}
	}

	public static void parkVehicle() {
		System.out.print("Enter RegistrationNo and Color : ");
		String veichleDetails = in.nextLine();
		String[] veichleDetailsArr = veichleDetails.split(" ");
		if (veichleDetailsArr == null || veichleDetailsArr.length < 2) {
			throw new IllegalArgumentException("Please provide proper formate (RegistrationNo Color)");
		}
		Vehicle vehicle = new Vehicle(veichleDetailsArr[0], veichleDetailsArr[1]);
		String parkVehicleStatus = parkingService.parkVehicle(vehicle);
		System.out.println(parkVehicleStatus);
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
