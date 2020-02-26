package com.jqmart.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utils for the exercise.
 * 
 * @author Marco Vaca
 */
public abstract class Utils {

	// Round util
	public static double roundToTwoDecimals(double numberToRound) {
		NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);

		// System.out.println(nf.format(numberToRound));

		return new Double(nf.format(numberToRound)).doubleValue();
	}

	// Left Pad util
	public static String leftPadString(String originalString, int lenghtToPad) {
		String paddedString = String.format("%1$" + lenghtToPad + "s", originalString); // .replace(' ', '0');
		return paddedString;
	}

	// Right Pad util
	public static String rightPadString(String originalString, int lenghtToPad) {
		String paddedString = String.format("%1$-" + lenghtToPad + "s", originalString); // .replace(' ', '0');
		return paddedString;
	}

	// Zero Pad util
	public static String padWithZeros(String originalString, int lenghtToPad) {
		String paddedString = String.format("%1$" + lenghtToPad + "s", originalString).replace(' ', '0');
		return paddedString;
	}

	// Today's Date, formatted
	public static String getDateOfTodayFormatted(String pattern) {
		return formatDate(new Date(), pattern);
	}

	// Format a Date
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(date);
	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println(Utils.getDateOfTodayFormatted()); }
	 */

}
