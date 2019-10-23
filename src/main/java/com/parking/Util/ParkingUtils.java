package com.parking.Util;

import java.util.List;

public class ParkingUtils {
	public static boolean vaidateCommand(String[] vehicleDetailsArr, int arrLength) {
		if (vehicleDetailsArr == null || vehicleDetailsArr.length < arrLength) {
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
	
	public static String convertListToString(List<?> list) {
		return list.toString().replace("[", "").replace("]", "");
	}
}
