/**
 * @author Andrew Maddox
 * @since Apr 5, 2024
 */
package edu.jhuep.maddox.bhc.model.bean;

import java.io.Serializable;
import java.util.List;

import edu.jhuep.maddox.bhc.constants.Constants;

/**
 * This class serves as a Java Bean. It is used to communicate information about the hike selection form between components. 
 */
public class Fields implements Serializable{

	private static final long serialVersionUID = 1235528388642984754L;
	private String hikers = Constants.DEFAULT_HIKERS;
	private String duration = Constants.DEFAULT_DURATION;
	private String date = Constants.DEFAULT_DATE;
	private String hike = Constants.DEFAULT_HIKE;
	private double rate = Constants.DEFAULT_RATE;
	private List<String> errors = null;
	
	//generic getters and setters
	public Long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getHikers() {
		return hikers;
	}

	public void setHikers(String hikers) {
		this.hikers = hikers;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHike() {
		return hike;
	}

	public void setHike(String hike) {
		this.hike = hike;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
