/**
 * 
 */
package com.ensis.sample.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Venu
 *
 */
public class Utilities {

	public static int getRandomNumber() {

		int randomNumber = 0;
		randomNumber = (int) ((Math.random() * 9000000) + 1000000);
		return randomNumber;
	}

	/**
	 * 
	 * @param stringValue
	 * @return
	 */
	public static int getInteger(String stringValue) {

		return Integer.parseInt(stringValue);
	}

	/**
	 * 
	 * @param dateAndTimeString
	 * @return
	 */
	public static Date getDateAndTimeFormat(String dateAndTimeString) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			Date date = simpleDateFormat.parse(dateAndTimeString);
			return date;
		} catch (ParseException ex) {
			System.out.println("Exception " + ex);
			return null;
		}
	}
	
	public static Date getDateAndTime(String dateAndTimeString) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			Date date = simpleDateFormat.parse(dateAndTimeString);
			return date;
		} catch (ParseException ex) {
			System.out.println("Exception " + ex);
			return null;
		}
	}


	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateFormat(Date date) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		System.out.println("Format 1:   " + dateFormatter.format(date));
		return dateFormatter.format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToDateFormat(Date date) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Format 1:   " + dateFormatter.format(date));
		return dateFormatter.format(date);
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentDate() {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date();
			String formattedTime = sdf.format(d);
			return formattedTime;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param rating
	 * @return
	 */
	public static double getTwoDigitDoubleValue(String rating) {

		if (rating != null) {

			double value = Double.parseDouble(rating);
			String result = String.format("%.2f", value);
			return Double.parseDouble(result);
		}
		return 0;

	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public static Date getMedicineDateFormat(String param) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(param);
			String formattedTime = output.format(d);
			Date opDate = output.parse(formattedTime);
			return opDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
