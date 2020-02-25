package com.ensis.sample.utils;

import java.util.Calendar;
import java.util.Random;

import org.apache.log4j.Logger;


	public class GenerateGUID {
		private static Logger log = Logger.getLogger( GenerateGUID.class.getName(  ) );
		
		//GenerateGUID is used to generate uuid based on current date and time
		public static String generateGuidValue(  ) {
			log.info( "Start DAO.GenerateGUID.generateGuidValue(  ) method" );
			
			Calendar cal = Calendar.getInstance(  );
			int milliSeconds = cal.get( Calendar.MILLISECOND );
			int seconds = cal.get( Calendar.SECOND );
			String milliSecondsHex = Integer.toHexString( milliSeconds* 1000000 );
			 /* returns the string representation of the unsigned integer value
		     represented by the argument in hexadecimal (base 16) */
			String secondsHex = Integer.toHexString( seconds );
			ensureLength( milliSecondsHex, 5 );
			ensureLength( secondsHex, 6 );
			String uuid = milliSecondsHex + "";
			uuid += createGuidSection( 3 );
			uuid += '-';
			uuid += createGuidSection( 4 );
			uuid += '-';
			uuid += createGuidSection( 4 );
			uuid += '-';
			uuid += createGuidSection( 4 );
			uuid += '-';
			uuid += secondsHex;
			uuid += createGuidSection( 6 );
			log.info( "End DAO.GenerateGUID.generateGuidValue(  ) method" );
			return uuid;
		}
		
		/**
		 * This method is used to set the given string length based on passing length value.
		 * 
		 * @param original
		 * @param length
		 */
		public static void ensureLength( String original, int length ) {
			log.info( "Start DAO.GenerateGUID.ensureLength(  ) method" );
			int diff = original.length(  ) - length;
			if ( diff > 0 ) {
				// String is too long; trim it down to the proper side
				original = original.substring( 0, length );
			} else if ( diff < 0  ) {
				// String is too short; pad it with trailing zeroes
				for ( int i = 0; i < diff; i ++ ) {
					original += "0";
				}
			}
			log.info( "End DAO.GenerateGUID.generateGuidValue(  ) method" );
			return;
			
		}
		
		/**
		 * This method is used to generate the random vaulue of specified length
		 * @param characters
		 * @uuidurn
		 */
		public static String createGuidSection ( int characters ) {
			log.info( "Start DAO.GenerateGUID.createGuidSection(  ) method" );
			String uuid = "";		
			Random random = new Random(  );
			for ( int i=0; i < characters; i++ ) {
				 /* returns the string representation of the unsigned integer value
			     represented by the argument in hexadecimal (base 16) */
				uuid += Integer.toHexString( random.nextInt( 15 ) );
	        }
			log.info( "End DAO.GenerateGUID.createGuidSection(  ) method" );
			return uuid;
		}

}
