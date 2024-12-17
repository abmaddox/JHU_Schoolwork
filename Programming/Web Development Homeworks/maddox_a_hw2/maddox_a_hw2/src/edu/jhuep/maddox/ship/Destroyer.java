/**
 * @author Andrew Maddox
 */
package edu.jhuep.maddox.ship;

import edu.jhuep.maddox.Validation;

/**
 * Fulfills the requirements for Step 3 of Homework 2. Destroyer extends the abstract class Ship with the addition of the field numberMissile.
 * 
 * I added the fields defaultNumberMissile, minimumNumberMissile, and maximumNumberMissile because it seemed like a logical addition.
 */
public class Destroyer extends Ship {
	
	//number of missiles the destroyer has available
	private int numberMissile;
	
	//arbitrary values for the following variables
	private int defaultNumberMissile = 2;
	private int minimumNumberMissile = 0;
	private int maximumNumberMissile = 50;
	
	/**
	 * The constructors are overloaded to accept String values for speed, length, numberMissile, or any combination of the three.
	 * Setter methods are called to validate the input, as in the superclass.
	 */
	public Destroyer() {
		super();
		setNumberMissile(defaultNumberMissile);
	}
	public Destroyer(int length, int speed, int numberMissile, String name, String type) {
		super(length, speed, name, type);
		setNumberMissile(numberMissile);
	}
	
	public Destroyer(String length, int speed, int numberMissile, String name, String type) {
		super(length, speed, name, type);
		setNumberMissile(numberMissile);
	}
	
	public Destroyer(int length, String speed, int numberMissile, String name, String type) {
		super(length, speed, name, type);
		setNumberMissile(numberMissile);
	}
	
	public Destroyer(int length, int speed, String numberMissile, String name, String type) {
		super(length, speed, name, type);
		setNumberMissile(numberMissile);
	}
	
	public Destroyer(String length, String speed, int numberMissile, String name, String type) {
		super(length, speed, name, type);
		setNumberMissile(numberMissile);
	}
	
	public Destroyer(int length, String speed, String numberMissile, String name, String type) {
		super(length, speed, name, type);
		setNumberMissile(numberMissile);
	}
	
	public Destroyer(String length, int speed, String numberMissile, String name, String type) {
		super(length, speed, name, type);
		setNumberMissile(numberMissile);
	}
	
	public Destroyer(String length, String speed, String numberMissile, String name, String type) {
		super(length, speed, name, type);
		setNumberMissile(numberMissile);
	}

	public int getNumberMissile() {
		return numberMissile;
	}

	public void setNumberMissile(int numberMissile) {
		this.numberMissile = Validation.getValidInt(numberMissile, defaultNumberMissile, minimumNumberMissile, maximumNumberMissile, "number of missiles");
	}
	
	public void setNumberMissile(String numberMissile) {
		this.numberMissile = Validation.getValidInt(numberMissile, defaultNumberMissile, minimumNumberMissile, maximumNumberMissile, "number of missiles");
	}
	
	@Override
	public String toString() {
		return "name=" + super.getName() + ", type=" + super.getType() + 
				"\nlength=" + super.getLength() + ", speed=" + super.getSpeed() + ", numberMissile=" + numberMissile; 
	}
}
