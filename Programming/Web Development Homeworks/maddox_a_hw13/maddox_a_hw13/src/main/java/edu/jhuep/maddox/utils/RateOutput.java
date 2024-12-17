/**
 * @author Andrew Maddox
 * @since Apr 26, 2024
 */
package edu.jhuep.maddox.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object will be serialized into a JSON response containing the rate and error messages.
 */
public class RateOutput {
	@JsonProperty("rate")
	String rate;
	@JsonProperty("errors")
	String[] errors;
	
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String[] getErrors() {
		return errors;
	}
	public void setErrors(String[] errors) {
		this.errors = errors;
	}
}
