/**
 * @author Andrew Maddox
 */
package edu.jhuep.maddox.ship;

import edu.jhuep.maddox.Validation;

/**
 * Fulfills the requirements for Step 4 of Homework 2. Submarine extends the abstract class Ship with the addition of the field numberTorpedos.
 * 
 * I added the fields defaultNumberTorpedos, minimumNumberTorpedos, and maximumNumberTorpedos because it seemed like a logical addition.
 */
public class Submarine extends Ship {

	//number of torpedoes the submarine has available
	private int numberTorpedos;

	//arbitrary values for the following variables
	private int defaultNumberTorpedos = 2;
	private int minimumNumberTorpedos = 0;
	private int maximumNumberTorpedos = 50;

	/**
	 * The constructors are overloaded to accept String values for speed, length, numberTorpedos, or any combination of the three.
	 * Setter methods are called to validate the input, as in the superclass.
	 */
	public Submarine() {
		super();
		setNumberTorpedos(defaultNumberTorpedos);
	}
	public Submarine(int length, int speed, int numberTorpedos, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numberTorpedos);
	}

	public Submarine(String length, int speed, int numberTorpedos, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numberTorpedos);
	}

	public Submarine(int length, String speed, int numberTorpedos, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numberTorpedos);
	}

	public Submarine(int length, int speed, String numberTorpedos, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numberTorpedos);
	}

	public Submarine(String length, String speed, int numberTorpedos, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numberTorpedos);
	}

	public Submarine(int length, String speed, String numberTorpedos, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numberTorpedos);
	}

	public Submarine(String length, int speed, String numberTorpedos, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numberTorpedos);
	}

	public Submarine(String length, String speed, String numberTorpedos, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numberTorpedos);
	}

	public int getNumberTorpedos() {
		return numberTorpedos;
	}

	public void setNumberTorpedos(int numberTorpedos) {
		this.numberTorpedos = Validation.getValidInt(numberTorpedos, defaultNumberTorpedos, minimumNumberTorpedos, maximumNumberTorpedos, "number of torpedos");
	}

	public void setNumberTorpedos(String numberTorpedos) {
		this.numberTorpedos = Validation.getValidInt(numberTorpedos, defaultNumberTorpedos, minimumNumberTorpedos, maximumNumberTorpedos, "number of torpedos");
	}
	
	@Override
	public String toString() {
		return "name=" + super.getName() + ", type=" + super.getType() + 
				"\nlength=" + super.getLength() + ", speed=" + super.getSpeed() + ", numberTorpedos=" + numberTorpedos; 
	}
}
