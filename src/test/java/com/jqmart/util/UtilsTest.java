package com.jqmart.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

/**
 * Unit tests for Utils.
 * 
 * @author Marco Vaca
 */
public class UtilsTest {

	@Test
	public void roundMethodTest() {

		// Test the round method
		double firstNumber = 1.3975;
		double secondNumber = 0.3386;
		double thirdNumber = 9.9954;
		double fourthNumber = 2.1111;
		double fifthNumber = 7.3333;

		// Verify proper Subtotal
		assertEquals(1.40, Utils.roundToTwoDecimals(firstNumber), 0.0);
		assertEquals(0.34, Utils.roundToTwoDecimals(secondNumber), 0.0);
		assertEquals(10.0, Utils.roundToTwoDecimals(thirdNumber), 0.0);
		assertEquals(2.11, Utils.roundToTwoDecimals(fourthNumber), 0.0);
		assertEquals(7.33, Utils.roundToTwoDecimals(fifthNumber), 0.0);
	}

	@Test
	public void padMethodTest() {

		// Test the pad method
		String ketchup = "Ketchup";

		// Verify proper padded length
		long length = (Utils.rightPadString(ketchup, 20)).length();

		// System.out.println(Utils.padString(ketchup, 20) + "5");

		assertEquals(20, length);
	}

	@Test
	public void zeroPadMethodTest() {

		// Test the pad method
		String thirteen = "13";

		// Verify proper padded length
		long length = (Utils.padWithZeros(thirteen, 6)).length();

		// System.out.println(Utils.padWithZeros(thirteen, 6));

		assertEquals(6, length);
	}

	@Test
	public void dateFormatTest() {
		// Long value for "Feb the 26th, 2020"
		long feb26of2020 = 1582740209945l;

		Date d = new Date(feb26of2020);

		// Test the date formatter method
		String fd = Utils.formatDate(d, "ddMMyyyy");

		assertEquals("26022020", fd);
	}

}
