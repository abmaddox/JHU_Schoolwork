/**
 * @author Andrew Maddox
 * @since Apr 12, 2024
 */
package edu.jhuep.maddox.bean;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Used to pass around the start date input by the user, errors resulting from validation, and the results from the database query
 */
public class Bean {
	
	//input date as string
	private String sInputDate;
	private List<String> errors;
	private List<Bean> beans;
	private String startDay;
	private String duration;
	private String location;
	private String gFName;
	private String gLName;
	private String hFName;
	private String hLName;

	
	public Bean() {
		this.sInputDate = "";
		this.errors = null;
		this.beans = null;
		this.duration = null;
		this.gFName = null;
		this.gLName = null;
		this.startDay = null;
		this.location = null;
		this.hFName = null;
		this.hLName = null;
	}
	
	public Bean(Bean bean) {
		this.sInputDate = bean.getsInputDate();
		this.errors = bean.getErrors();
		this.beans = bean.getBeans();
		this.duration = bean.getDuration();
		this.gFName = bean.getgFName();
		this.gLName = bean.getgLName();
		this.startDay = bean.getStartDay();
		this.location = bean.getLocation();
		this.hFName = bean.gethFName();
		this.hLName = bean.gethLName();
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	//if there are no current errors then errors is set simply, otherwise the list of errors is combined
	public void setErrors(List<String> errors) {
		if (this.errors == null || this.errors.size() == 0) {
			this.errors = errors;
		}
		else {
			//one line method to join lists found at
			//https://stackoverflow.com/questions/189559/how-do-i-join-two-lists-in-java
			//author username Mark
			this.errors = Stream.of(this.errors, errors).flatMap(Collection::stream).collect(Collectors.toList());
		}
	}
	
	public String getsInputDate() {
		return sInputDate;
	}
	public void setsInputDate(String sInputDate) {
		this.sInputDate = sInputDate;
	}

	public List<Bean> getBeans() {
		return beans;
	}

	public void setBeans(List<Bean> beans) {
		this.beans = beans;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getgFName() {
		return gFName;
	}

	public void setgFName(String gFName) {
		this.gFName = gFName;
	}

	public String getgLName() {
		return gLName;
	}

	public void setgLName(String gLName) {
		this.gLName = gLName;
	}

	public String gethFName() {
		return hFName;
	}

	public void sethFName(String hFName) {
		this.hFName = hFName;
	}

	public String gethLName() {
		return hLName;
	}

	public void sethLName(String hLName) {
		this.hLName = hLName;
	}
}
