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
		displayParkingMenu();
	}

	private static void displayParkingMenu() {
		do {
			System.out.println("\n          Vechile Parking Menu");
			System.out.println("--------------------------------------");
			System.out.println("1 - Park Vechile");
			System.out.println("2 - Check Vechile Status");
			System.out.println("3 - Leave Parking");
			System.out.println("4 - Search Registration Number From Vechile Color");
			System.out.println("5 - Search Slot Number From Vechile Color");
			System.out.println("6 - Search Slot Number From Registration Number");
			System.out.println("7 - Exit");
			System.out.print("\nSelect a Menu Option: ");
			getParkingMenuInput(in.nextInt());
		} while (true);
	}

	private static void getParkingMenuInput(Integer input) {
		in.nextLine();
		switch (input) {
		case 1: // Park Vechile
			try {
				parkVehicle();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2: // Check Vechile Status
			checkParkingStatus();
			break;
		case 3: // Leave Parking
			System.out.println("Enter parking lot no : ");
			int leave = in.nextInt();
			String leaveParkingStatus = parkingService.leaveVehicle(leave);
			in.nextLine();
			System.out.println(leaveParkingStatus);
			break;
		case 4: // Search Registration Number From Vechile Color
			System.out.println("search registration number from Vechile color : ");
			String vehicilColorForCheckRegistrationNo = in.nextLine();
			String allDetails = parkingService
					.findAllRegistrationNumberForCarsWithColor(vehicilColorForCheckRegistrationNo);
			System.out.println(allDetails);
			break;
		case 5: // Search Slot Number From Vechile Color
			System.out.println("Search slot number from vechlie color : ");
			String colorForCheckSlotNumber = in.nextLine();
			String allSlotNumber = parkingService.findAllSlotNumberForCarsWithColor(colorForCheckSlotNumber);
			System.out.println(allSlotNumber);
			break;
		case 6: // Search Slot Number From Registration Number
			System.out.println("Search slot number from Vechile registration number : ");
			String regNo = in.nextLine();
			String slotNoStatusOfRegNo = parkingService.findSlotNumberFromRegistrationNo(regNo);
			System.out.println(slotNoStatusOfRegNo);
			break;
		case 7: // Exit
			System.out.println("Application Closed.");
			System.exit(0);
			break;

		default:
			System.out.print("The entered value is unrecognized!");
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
