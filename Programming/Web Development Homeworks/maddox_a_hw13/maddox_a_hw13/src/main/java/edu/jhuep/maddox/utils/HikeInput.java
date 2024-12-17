/**
 * @author Andrew Maddox
 * @since Apr 26, 2024
 */
package edu.jhuep.maddox.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.jhuep.maddox.constants.Constants;

/**
 * This object will be created through the deserialized JSON response from the index.html page. Tags indicate the names of the fields in JSON
 */
public class HikeInput {
	@JsonProperty(Constants.HIKE)
	private String hike;
	@JsonProperty(Constants.DATE)
	private String date;
	@JsonProperty(Constants.DURATION)
	private String duration;
	@JsonProperty(Constants.HIKERS)
	private String hikers;
	
	public String getHike() {
		return hike;
	}
	public void setHike(String hike) {
		this.hike = hike;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getHikers() {
		return hikers;
	}
	public void setHikers(String hikers) {
		this.hikers = hikers;
	}
	
	
}
