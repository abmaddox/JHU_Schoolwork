/**
 * @author Andrew Maddox
 */
package edu.jhuep.maddox.ship;

import edu.jhuep.maddox.Contact;
import edu.jhuep.maddox.Validation;

/**
 * This class fulfills the requirements of Step 2 in homework 2. It is a base for all concrete ship subclasses to extend. 
 * It implements the Contact interface.
 * The class implements defaults for the fields length, speed, name, and type. 
 * Minimums and maximums are implemented for the fields length and speed.
 */
public abstract class Ship implements Contact {
	
	//length of the ship
	private int length;
	
	//arbitrary values for the following variables
	private int defaultLength = 0;
	private int minimumLength = 0;
	private int maximumLength = 10000;
	
	//speed of the ship
	private int speed;
	
	//arbitrary values for the following variables
	private int defaultSpeed = 0;
	private int minimumSpeed = 0;
	private int maximumSpeed = 10000;
	
	//name of the ship
	private String name;
	private String defaultName = "Ship";
	
	//type of the ship
	private String type;
	private String defaultType = "Ship";
	
	/*
	 * The constructors are overloaded to accept String values for speed, length, or both.
	 * Setter methods are called to validate the input.
	 */
	protected Ship(){
		setLength(defaultLength);
		setSpeed(defaultSpeed);
		setName(defaultName);
		setType(defaultType);
	}
	
	protected Ship(int length, int speed, String name, String type) {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
	}
	
	protected Ship(int length, String speed, String name, String type) {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
	}
	
	protected Ship(String length, int speed, String name, String type) {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
	}
	
	protected Ship(String length, String speed, String name, String type) {
		setLength(length);
		setSpeed(speed);
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


