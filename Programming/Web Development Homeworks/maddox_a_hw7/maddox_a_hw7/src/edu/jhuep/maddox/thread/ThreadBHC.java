/**
 * @author Andrew Maddox
 * @since Mar 8, 2024
 */
package edu.jhuep.maddox.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;

/**
 * This class handles the connections accepted by the BHC server in a multi-threaded fashion.
 * A single line of input is accepted from the socket connection and it is checked for errors using the GetErrors method. 
 * Any error messages will be compiled into a comma separated list with no spaces between messages.
 * General output is in the format "rate:message" where the rate is -0.01 in the event of errors, or it is a rate calculated according to a valid input message.
 * The "message" portion of the output may be error messages or, in the event that a valid rate is generated, it will be "Quoted Rate".
 */
public class ThreadBHC extends Thread {

	private int numHikers;
	private int duration;
	private int day;
	private int month;
	private int year;
	private HikeType hikeType;
	private double rate;
	private String errorMessage;
	private String returnMessage;

	private final String noErrorMessage;
	private final String errorDelim;
	private final String messageDelim;
	private final Socket socket;

	public ThreadBHC(Socket s) {
		super();

		//LOGGING
		System.out.println("Thread " + this.hashCode() +" started");

		//set final values
		socket = s;
		errorDelim = ",";
		messageDelim = ":";
		noErrorMessage = "Quoted Rate";
	}

	public void run() {

		//using a try with resources so things close automatically upon error
		try (	
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				){
			String input = in.readLine();
			errorMessage = getErrors(input);

			//Check if errors occurred
			if (rate < 0) {
				returnMessage = rate + messageDelim + errorMessage;
			}
			else {
				returnMessage = rate + messageDelim + noErrorMessage;
			}

			out.println(returnMessage);

		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}

		//socket is closed
		//LOGGING
		System.out.println("Thread " + this.hashCode() +" ended");
	}

	//This method is only called in safe places that have already passed validation, so there is no validation here
	//Returns a Rates object using the values parsed from the client message
	private Rates getRatesObject() {
		BookingDay startBookingDay = new BookingDay(this.year, this.month, this.day);

		Rates rates = new Rates(hikeType);
		rates.setBeginDate(startBookingDay);
		rates.setDuration(this.duration);
		rates.setNumberHikers(this.numHikers);

		return rates;
	}

	//This monolithic method is used to generate the error messages for the user information sent to the server.
	//Some error messages are given by BHCUtils BookingDay, some are given by BHCUtils Rates, and all errors about the message format are custom
	private String getErrors(String input) {
		String[] args = input.strip().split(messageDelim);

		//hikeID is the string, hikeNum is the parsed integer from hikeID
		String hikeID;
		int hikeNum;

		String month;
		String day;
		String year;
		String duration;
		String numHikers;
		String message = "";
		Rates r;
		int failedChecks = 0;


		//If there are any blank values then append the appropriate error message(s) and exit validation
		//Any arguments over the limit of 6 will result in an invalid request message
		if (hasBlanks(args) == true) {
			for (int i = 0; i < args.length; i++) {
				switch(i) {
				case 0: message = message + (args[i]=="" ? "A hike must be selected" + errorDelim : "");
				case 1: message = message + (args[i]=="" ? "Start month cannot be blank" + errorDelim : "");
				case 2: message = message + (args[i]=="" ? "Start day cannot be blank" + errorDelim : "");
				case 3: message = message + (args[i]=="" ? "Start year cannot be blank" + errorDelim : "");
				case 4: message = message + (args[i]=="" ? "A hike duration must be selected" + errorDelim : "");
				case 5: message = message + (args[i]=="" ? "Number of hikers cannot be blank" + errorDelim : "");
				default: message = message + "Invalid request sent to server" + errorDelim;
				}
			}
		}
		else {
			//no blank values sent to server

			//set values
			hikeID = args[0];
			month = args[1];
			day = args[2];
			year = args[3];
			duration = args[4];
			numHikers = args[5];


			//check number of hikers for int parsing
			try {
				this.numHikers = Integer.parseInt(numHikers);
			}
			catch(NumberFormatException nfe) {
				message = message + "Number of hikers must be an integer" + errorDelim;
				failedChecks++;
			}

			//check duration for int parsing
			try {
				this.duration = Integer.parseInt(duration);
			}
			catch(NumberFormatException nfe) {
				message = message + "Duration must be an integer" + errorDelim;
				failedChecks++;
			}

			//check day for int parsing
			try {
				this.day = Integer.parseInt(day);
			}
			catch(NumberFormatException nfe) {
				message = message + "The start day must be an integer" + errorDelim;
				failedChecks++;
			}

			//check month for int parsing
			try {
				this.month = Integer.parseInt(month);
			}
			catch(NumberFormatException nfe) {
				message = message + "The start month must be an integer" + errorDelim;
				failedChecks++;
			}

			//check year for int parsing
			try {
				this.year = Integer.parseInt(year);
			}
			catch(NumberFormatException nfe) {
				message = message + "The start year must be an integer" + errorDelim;
				failedChecks++;
			}

			//check hikeID for int parsing
			try {
				hikeNum = Integer.parseInt(hikeID);
			}
			catch(NumberFormatException nfe) {
				//prevents duplication of the hike selection message
				hikeNum = -1;
				failedChecks++;
			}

			//check if hike selected is valid
			HikeType[] hikeTypes = HikeType.values();

			if (hikeNum < 0 | hikeNum >= hikeTypes.length) {
				message = message + "Invalid hike selection" + errorDelim;
				failedChecks++;
			}
			else {
				//set hike type
				this.hikeType = hikeTypes[hikeNum];
			}

			//check if number of hikers is not zero or less
			if(this.numHikers < 1) {
				message = message + "Number of hikers must be greater than zero" + errorDelim;
				failedChecks++;
			}

			//check if values given for the date are 1 or higher
			if (this.day < 1 | this.month < 1 | this.year < 1) {
				message = message + "Date values must be greater than zero" + errorDelim;
				failedChecks++;
			}

			//check if duration is greater than zero
			if(this.duration < 1) {
				message = message + "Hike duration must be greater than zero" + errorDelim;
				failedChecks++;
			}

			//If all checks pass then make a BookingDay object
			if (failedChecks == 0) {
				BookingDay bd = new BookingDay(this.year, this.month, this.day);

				if (!bd.isValidDate()) {
					List<String> messages = bd.getDetails();
					if (messages.size() != 0) {
						for (String m : messages) {
							message = message + m + errorDelim;
						}
					}
					else {
						message = message + "Invalid start date entry" + errorDelim;
					}
					failedChecks++;
				}
			}

			//hooray all checks were passed for now
			if(failedChecks == 0) {

				r = getRatesObject();

				this.rate = r.getCost();

				//If error messages for the rate object exist then return them to the client
				List<String> messages = r.getDetails();
				if (messages.size() != 0) {
					for (String m : messages) {
						message = message + m + errorDelim;

					}
				}
			}
			else {
				this.rate = -0.01;
			}
		}
		return message;
	}
	

//Checks if the array provided has any blank values
//Returns true if blanks are found, false if no blanks are found
private boolean hasBlanks(String[] args) {

	int blanks = 0;
	for(String arg:args) {
		if (arg=="") {
			blanks++;
		}
	}

	return (blanks>0);
}
}
