/**
 * @author Andrew Maddox
 */
package edu.jhuep.maddox;

/**
 * This fulfills the requirements for Step 1 of homework 2.
 * This interface is used to get/set the length, speed, name, and type of ships or aircraft.
 */
public interface Contact {

	public int getLength();
	public void setLength(int length);

	public int getSpeed();
	public void setSpeed(int speed);
	public void setSpeed(String speed);

	public String getName();
	public void setName(String name);

	public String getType();
	public void setType(String platform);
}
