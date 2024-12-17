/**
 * @author Andrew Maddox
 */
package edu.jhuep.maddox;

/**
 * This class contains a collection of static methods for input validation of Strings converted to integers and integer input. 
 */
public class Validation {

	/**
	 * This method is called to get an integer value from a String passed as input. The input will be further validated using the overloaded
	 * version of this method to ensure the integer value returned is within a valid range.
	 * 
	 * @param input The String value which will be parsed as an integer.
	 * @param defaultValue The default value to return in the event that the String is not a valid integer.
	 * @param minimum The minimum integer value the input may take.
	 * @param maximum The maximum integer value the input may take. 
	 * @param fieldName The name of the field being verified by the calling method. Used to output the error message. 
	 */
	public static int getValidInt(String input, int defaultValue, int minimum, int maximum, String fieldName) {
		try {	

			int inputAsInt = Integer.parseInt(input);
			return getValidInt(inputAsInt, defaultValue, minimum, maximum, fieldName);

		}
		catch(NumberFormatException e) {

			System.out.println("ERROR: Invalid input {" + input + "} for " + fieldName + ".");
			System.out.println("Setting " + fieldName + " to default value of " + defaultValue + ".");
			System.out.println();
			return defaultValue;

		}
	}


	/**
	 * This method is called to validate whether an integer passed as input is within a desired range.
	 * 
	 * @param input The String value which will be parsed as an integer.
	 * @param defaultValue The default value to return in the event that the String is not a valid integer.
	 * @param minimum The minimum integer value the input may take.
	 * @param maximum The maximum integer value the input may take. 
	 * @param fieldName The name of the field being verified by the calling method. Used to output the error message.
	 */
	public static int getValidInt(int input, int defaultValue, int minimum, int maximum, String fieldName) {

		if(input >= minimum && input <= maximum) {
			return input;
		}
		else {
			System.out.println("ERROR: Invalid input {" + input + "} for " + fieldName + ".");
			System.out.println("Setting " + fieldName + " to default value of " + defaultValue + ".");
			System.out.println();
			return defaultValue;
		}
	}
}
