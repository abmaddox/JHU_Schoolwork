/**
 * @author Andrew Maddox
 */
package edu.jhuep.maddox.aircraft;

import edu.jhuep.maddox.Validation;

/**
 * This class satisfies the requirements of Step 6 of homework 2. 
 * Additional fields defaultNumberEngines, minimumNumberEngines, and maximumNumberEngines were added as logical extensions to the assignment.
 */
public class P8 extends Aircraft {

	//number of engines the P8 has available
	private int numberEngines;

	//arbitrary values for the following variables
	private int defaultNumberEngines = 2;
	private int minimumNumberEngines = 0;
	private int maximumNumberEngines = 2;

	/*
	 * The constructors are overloaded to accept String values for speed, length, numberEngines, or some combination of the three fields as Strings and ints. 
	 * Setter methods are called to validate the input.
	 */
	public P8() {
		super();
		setNumberEngines(defaultNumberEngines);
	}

	public P8(int length, int speed, int altitude, int numberEngines, String name, String type) {
		super(length, speed, altitude, name, type);
		setNumberEngines(numberEngines);
	}

	public P8(String length, int speed, int altitude, int numberEngines, String name, String type) {
		super(length, speed, altitude, name, type);
		setNumberEngines(numberEngines);
	}

	public P8(int length, String speed, int altitude, int numberEngines, String name, String type) {
		super(length, speed, altitude, name, type);
		setNumberEngines(numberEngines);
	}

	public P8(int length, int speed, int altitude, String numberEngines, String name, String type) {
		super(length, speed, altitude, name, type);
		setNumberEngines(numberEngines);
	}

	public P8(String length, String speed, int altitude, int numberEngines, String name, String type) {
		super(length, speed, altitude, name, type);
		setNumberEngines(numberEngines);
	}

	public P8(String length, int speed, int altitude, String numberEngines, String name, String type) {
		super(length, speed, altitude, name, type);
		setNumberEngines(numberEngines);
	}

	public P8(int length, String speed, int altitude, String numberEngines, String name, String type) {
		super(length, speed, altitude, name, type);
		setNumberEngines(numberEngines);
	}

	public P8(String length, String speed, int altitude, String numberEngines, String name, String type) {
		super(length, speed, altitude, name, type);
		setNumberEngines(numberEngines);
	}

	public int getNumberEngines() {
		return numberEngines;
	}

	public void setNumberEngines(int numberEngines) {
		this.numberEngines = Validation.getValidInt(numberEngines, defaultNumberEngines, minimumNumberEngines, maximumNumberEngines, "number of engines");
	}

	public void setNumberEngines(String numberEngines) {
		this.numberEngines = Validation.getValidInt(numberEngines, defaultNumberEngines, minimumNumberEngines, maximumNumberEngines, "number of engines");
	}

	@Override
	public String toString() {
		return "name=" + super.getName() + ", type=" + super.getType() + 
				"\nlength=" + super.getLength() + ", speed=" + super.getSpeed() + ", altitude=" + super.getAltitude() +", numberEngines=" + numberEngines; 
	}
}
