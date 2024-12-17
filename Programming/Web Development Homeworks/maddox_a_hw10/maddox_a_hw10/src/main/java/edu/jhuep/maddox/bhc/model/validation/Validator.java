/**
 * @author Andrew Maddox
 * @since Mar 30, 2024
 */
package edu.jhuep.maddox.bhc.model.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;
import edu.jhuep.maddox.bhc.constants.Constants;

/**
 * This class provides the validation for information submitted to BHCUtils apps. It relies partially upon the BHcUtils.jar file.
 * The errors and the rate are available for return from this class. This is similar to the class from hw8, but it has been customized slightly. 
 * Only the rate and errors are available for return because they are not part of the request parameters.
 */
public class Validator {

	private int hikers;
	private int duration;
	private int day;
	private int month;
	private int year;
	private HikeType hike;
	private double rate;
	private List<String> errors = new ArrayList<String>();

	//date is returned from form in the format YYYY-MM-DD
	//these positions represent their position in an array after splitting at Constants.DATE_DELIM
	private static final int YEAR_POSITION = 0;
	private static final int MONTH_POSITION = 1;
	private static final int DAY_POSITION = 2;

	private boolean validHike;
	private boolean validDay;
	private boolean validMonth;
	private boolean validYear;
	private boolean validDuration;
	private boolean validHikers;
	
	private boolean nullHike;
	private boolean nullDay;
	private boolean nullMonth;
	private boolean nullYear;
	private boolean nullDuration;
	private boolean nullHikers;

	//https://stackoverflow.com/questions/189559/how-do-i-join-two-lists-in-java
	//author username Mark
	//List<String> newList = Stream.of(listOne, listTwo).flatMap(Collection::stream).collect(Collectors.toList());

	public Validator(String hike, String startDate, String duration, String hikers) {
		
		validHike = false;
		validDay = false;
		validMonth = false;
		validYear = false;
		validDuration = false;
		validHikers = false;
		
		nullHike = false;
		nullDay = false;
		nullMonth = false;
		nullYear = false;
		nullDuration = false;
		nullHikers = false;
		
		validateHike(hike);
		validateDate(startDate);
		validateDuration(duration);
		validateHikers(hikers);

		if(validHike && validDay && validMonth && validYear && validDuration && validHikers) {
			BookingDay bd = new BookingDay(this.year, this.month, this.day);
			Rates r = new Rates(this.hike);
			r.setBeginDate(bd);
			r.setDuration(this.duration);
			
			if(!r.isDurationValid()) {
				r.setEndDate(null);
			}
			
			r.setNumberHikers(this.hikers);
			this.rate = r.getCost();

			//one line method to join lists found at
			//https://stackoverflow.com/questions/189559/how-do-i-join-two-lists-in-java
			//author username Mark
			this.errors = Stream.of(this.errors, r.getDetails()).flatMap(Collection::stream).collect(Collectors.toList());
		}
		else {
			this.rate = Constants.ERROR_RATE;
		}
	}

	//number of hikers validation
	private void validateHikers(String hikers) {
		nullHikers = (hikers == null);
		if(isNotBlank(hikers, "number of hikers") && !nullHikers) {
			this.hikers = getValidInt(hikers, "number of hikers", Constants.INT_MIN);
			this.validHikers = (this.hikers >= Constants.INT_MIN);
		}
	}

	//duration validation
	private void validateDuration(String duration) {
		nullDuration = (duration == null);
		if(isNotBlank(duration, "duration") && !nullDuration) {
			this.duration = getValidInt(duration, "duration", Constants.INT_MIN);
			this.validDuration = (this.duration >= Constants.INT_MIN);
		}
	}

	//date validation
	private void validateDate(String startDate) {
		if(startDate!=null && isDateFormatValid(startDate)) {
			String[] dateSplit = startDate.split(Constants.DATE_DELIM);

			//day validation
			nullDay = (dateSplit[DAY_POSITION] == null);
			if(isNotBlank(dateSplit[DAY_POSITION], "day") && !nullDay) {
				this.day = getValidInt(dateSplit[DAY_POSITION], "day", Constants.INT_MIN);
				this.validDay = (day >= Constants.INT_MIN);
			}

			//month validation
			nullMonth = (dateSplit[MONTH_POSITION] == null);
			if(isNotBlank(dateSplit[MONTH_POSITION], "month") && !nullMonth) {
				this.month = getValidInt(dateSplit[MONTH_POSITION], "month", Constants.INT_MIN);
				this.validMonth = (month >= Constants.INT_MIN);
			}

			//year validation
			nullYear = (dateSplit[YEAR_POSITION] == null);
			if(isNotBlank(dateSplit[YEAR_POSITION], "year") && !nullYear) {
				this.year = getValidInt(dateSplit[YEAR_POSITION], "year", Constants.INT_MIN);
				this.validYear = (year >= Constants.INT_MIN);
			}
		}
	}

	//hike name validation
	private void validateHike(String hike) {
		nullHike = (hike == null);
		if(isNotBlank(hike, "hike name") && !nullHike) {
			this.hike = getValidHike(hike);
			if (this.hike == null) {
				this.validHike = false;
			}
			else {
				this.validHike = true;
			}
		}
	}

	//checks if the given String can be converted into an integer and if it is less than the minimum value given. returns minimum-1 if there is a failure
	private int getValidInt(String fieldValue, String fieldName, int minimum) {
		int i;
		try {
			i = Integer.parseInt(fieldValue);

			if (i < minimum) {
				errors.add(fieldName + " [" + fieldValue + "] cannot be less than " + minimum);
				return i;
			}else {
				return i;
			}
		}
		catch(NumberFormatException nfe) {
			errors.add(fieldName + " must be an integer"); 
			return minimum-1;
		}
	}

	//checks if the given hike name is a valid hike name. returns the hike name if valid or an empty string if invalid
	private HikeType getValidHike(String hikeName) {
		for (HikeType hike: HikeType.values()) {
			if (hike.equalsName(hikeName)) {
				return hike;
			}
		}
		
		//only reached if there is no such hike with name hikeName
		errors.add(hikeName + " is an invalid hike name"); 
		return null;

	}

	//checks if the date is in the form YYYY + DATE_DELIM + MM + DATE_DELIM + DD
	private boolean isDateFormatValid(String date) {
		if(date.split(Constants.DATE_DELIM).length == 3) {
			return true;
		}
		else if (date == ""){
			errors.add("date cannot be blank");
			return false;
		}
		else {
			errors.add("The date [" + date + "] is invalid");
			return false;
		}
	}

	//checks if a field is blank before further processing
	private boolean isNotBlank(String fieldValue, String fieldName) {
		if (fieldValue == "") {
			errors.add(fieldName + " cannot be blank");
			return false;
		}
		else {
			return true;
		}
	}


	/*
	 * rate is safe for return because it is internally calculated
	 */
	public double getRate() {
		return rate;
	}

	/*
	 * errors is safe for return because it is internally composed
	 */

	public List<String> getErrors() {
		return errors;
	}

}
