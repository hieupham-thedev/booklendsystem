package com.fsoft.hieupnm.booklendsystem.utils.validation;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class InputValidator {

	private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

	/*
	 * Check if ID is number or not
	 */
	public static boolean isValidId(String id) {
		try {
			Integer.parseInt(id);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * Check if name length <= 20 characters
	 */
	public static boolean isValidLength(String string) {
		if (string != null && string.length() <= 20) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Check if Date have "YYYY-MM-DD" or not and year, month, day is valid
	 */
	public static boolean isValidDate(String date) {
		if (DATE_PATTERN.matcher(date).matches()) {
			try {
				LocalDate.parse(date);
				return true;
			} catch (DateTimeException e) {
				return false;
			}
		} else {
			return false;
		}
	}
}
