package com.parking.Util;

public class ParkingUtils {
	public static boolean vaidateCommand(String[] vehicleDetailsArr, int length) {
		if (vehicleDetailsArr == null || vehicleDetailsArr.length < length) {
			System.out.println("Please provide proper formate. Enter help");
			return false;
		}
		return true;
	}

	public static boolean integerInputValidation(String convertToNumber) {
		try {
			Integer.parseInt(convertToNumber);
			return true;
		} catch (Exception e) {
			System.out.println("Please provide proper formate. Enter help");
			return false;
		}
	}
}
