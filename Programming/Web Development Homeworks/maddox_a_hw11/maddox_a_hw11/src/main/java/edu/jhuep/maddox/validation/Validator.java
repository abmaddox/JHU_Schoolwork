/**
 * @author Andrew Maddox
 * @since Apr 13, 2024
 */
package edu.jhuep.maddox.validation;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.jhuep.maddox.bean.Bean;
import edu.jhuep.maddox.constants.Constants;

/**
 * Date validation from the Validator class commonly seen in my assignments. This code was taken from my assignment 10 submission in the Validator class and slightly adapted to return a date object.
 */
public class Validator {
	private int day;
	private int month;
	private int year;
	
	private boolean isValidDate;
	
	private boolean validDay;
	private boolean validMonth;
	private boolean validYear;

	private boolean nullDay;
	private boolean nullMonth;
	private boolean nullYear;

	//date is returned from form in the format YYYY-MM-DD
	//these positions represent their position in an array after splitting at Constants.DATE_DELIM
	private static final int YEAR_POSITION = 0;
	private static final int MONTH_POSITION = 1;
	private static final int DAY_POSITION = 2;

	private List<String> errors = new ArrayList<String>();
	
	public Validator(Bean bean) {
		validateDate(bean.getsInputDate());
		
		if(validDay && validMonth && validYear) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy" + Constants.DATE_DELIM + "MM" + Constants.DATE_DELIM + "dd", Locale.ENGLISH);
			try {
				//only parsed as a final check of validity for the date
				@SuppressWarnings("unused")
				Date date = formatter.parse(bean.getsInputDate());
				isValidDate = true;
			} catch (ParseException e) {
				errors.add("[ " + bean.getsInputDate() +" ] is an invalid date");
				e.printStackTrace();
				isValidDate = false;
			}
		}
	}
	
	public boolean isValidDate() {
		
		return isValidDate;
	}
	
	public List<String> getErrors(){
		return errors;
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


}
