/**
 * @author Andrew Maddox
 */
 
package edu.jhuep.maddox.aircraft;

import edu.jhuep.maddox.Contact;
import edu.jhuep.maddox.Validation;

/**
 * This fulfills the requirements for Step 5 of homework 2.
 * This class is a base for all concrete aircraft subclasses to extend. It implements the Contact interface.
 * The class implements defaults for the fields length, speed, altitude, name, and type. 
 * Minimums and maximums are implemented for the fields length, speed, and altitude.
 */
public abstract class Aircraft implements Contact {

	//length of the aircraft
	private int length;
	//arbitrary values for the following variables
	private int defaultLength = 0;
	private int minimumLength = 0;
	private int maximumLength = 10000;

	//speed of the aircraft
	private int speed;
	//arbitrary values for the following variables
	private int defaultSpeed = 0;
	private int minimumSpeed = 0;
	private int maximumSpeed = 10000;

	//altitude of the aircraft
	private int altitude;
	//arbitrary values for the following variables
	private int defaultAltitude = 0;
	private int minimumAltitude = 0;
	private int maximumAltitude = 10000;

	//name of the aircraft
	private String name;
	private String defaultName = "Aircraft";

	//type of the aircraft
	private String type;
	private String defaultType = "Aircraft";

	/*
	 * The constructors are overloaded to accept String values for speed, length, or both. 
	 * Setter methods are called to validate the input.
	 */
	protected Aircraft(){
		setLength(defaultLength);
		setSpeed(defaultSpeed);
		setAltitude(defaultAltitude);
		setName(defaultName);
		setType(defaultType);
	}

	protected Aircraft(int length, int speed, int altitude, String name, String type) {
		setLength(length);
		setSpeed(speed);
		setAltitude(altitude);
		setName(name);
		setType(type);
	}

	protected Aircraft(int length, String speed, int altitude, String name, String type) {
		setLength(length);
		setSpeed(speed);
		setAltitude(altitude);
		setName(name);
		setType(type);
	}

	protected Aircraft(String length, int speed, int altitude, String name, String type) {
		setLength(length);
		setSpeed(speed);
		setAltitude(altitude);
		setName(name);
		setType(type);
	}

	protected Aircraft(String length, String speed, int altitude, String name, String type) {
		setLength(length);
		setSpeed(speed);
		setAltitude(altitude);
		setName(name);
		setType(type);
	}

	@Override
	public int getLength() {
		return this.length;
	}

	@Override
	public void setLength(int length) {
		this.length = Validation.getValidInt(length, defaultLength, minimumLength, maximumLength, "length");
	}

	public void setLength(String length) {
		this.length = Validation.getValidInt(length, defaultLength, minimumLength, maximumLength, "length");
	}

	public int getDefaultLength() {
		return defaultLength;
	}

	public void setDefaultLength(int defaultLength) {
		this.defaultLength = Validation.getValidInt(defaultLength, this.defaultLength, minimumLength, maximumLength, "default length");
	}

	public int getMinimumLength() {
		return minimumLength;
	}

	public void setMinimumLength(int minimumLength) {
		this.minimumLength = Validation.getValidInt(minimumLength, this.minimumLength, this.minimumLength, maximumLength, "minimum length");
	}

	public int getMaximumLength() {
		return maximumLength;
	}

	public void setMaximumLength(int maximumLength) {
		this.maximumLength = Validation.getValidInt(maximumLength, this.maximumLength, minimumLength, this.maximumLength, "maximum length");
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = Validation.getValidInt(speed, defaultSpeed, minimumSpeed, maximumSpeed, "speed");
	}

	@Override
	public void setSpeed(String speed) {
		this.speed = Validation.getValidInt(speed, defaultSpeed, minimumSpeed, maximumSpeed, "speed");

	}

	public int getDefaultSpeed() {
		return defaultSpeed;
	}

	public void setDefaultSpeed(int defaultSpeed) {
		this.defaultSpeed = Validation.getValidInt(defaultSpeed, this.defaultSpeed, minimumSpeed, maximumSpeed, "default speed");
	}

	public int getMinimumSpeed() {
		return minimumSpeed;
	}

	public void setMinimumSpeed(int minimumSpeed) {
		this.minimumSpeed = Validation.getValidInt(minimumSpeed, this.minimumSpeed, this.minimumSpeed, maximumSpeed, "minimum speed");
	}

	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = Validation.getValidInt(maximumSpeed, this.maximumSpeed, minimumSpeed, this.maximumSpeed, "maximum speed");
	}

	public int getAltitude() {
		return this.altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = Validation.getValidInt(altitude, defaultAltitude, minimumAltitude, maximumAltitude, "altitude");;
	}

	public int getDefaultAltitude() {
		return defaultAltitude;
	}

	public void setDefaultAltitude(int defaultAltitude) {
		this.defaultAltitude = Validation.getValidInt(defaultAltitude, this.defaultAltitude, minimumAltitude, maximumAltitude, "default altitude");
	}

	public int getMinimumAltitude() {
		return minimumAltitude;
	}

	public void setMinimumAltitude(int minimumAltitude) {
		this.minimumAltitude = Validation.getValidInt(minimumAltitude, this.minimumAltitude, this.minimumAltitude, maximumAltitude, "minimum altitude");
	}

	public int getMaximumAltitude() {
		return maximumAltitude;
	}

	public void setMaximumAltitude(int maximumAltitude) {
		this.maximumAltitude = Validation.getValidInt(maximumAltitude, this.maximumAltitude, minimumAltitude, this.maximumAltitude, "maximum altitude");
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String platform) {
		this.type = platform;
	}
}
