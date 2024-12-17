/**
 * Principles of Enterprise Web Development
 * @author Andrew Maddox
 */
package edu.jhuep.maddox.driver;

import java.util.ArrayList;
import java.util.List;

import edu.jhuep.maddox.Contact;
import edu.jhuep.maddox.aircraft.P8;
import edu.jhuep.maddox.ship.Destroyer;
import edu.jhuep.maddox.ship.Ship;
import edu.jhuep.maddox.ship.Submarine;

/**
 * Executes the code specified in homework assignment 2 Steps 7 and 8. Step 7 sub-parts are commented for clarity, as is step 8.
 */
public class Test {

	/**
	 * @param args Always NULL in this project.
	 */
	public static void main(String[] args) {
		
		//Step 7.1, creating 2 destroyer objects
		Destroyer d1 = new Destroyer(15, 20, 3, "Big Fella", "Destroyer");
		Destroyer d2 = new Destroyer("10", 30, 15, "Lil Guy", "Destroyer");
		
		//Step 7.2, creating 2 submarine objects
		Submarine s1 = new Submarine(50, "45", 18, "Foot Long", "Submarine");
		//Step 7.8, using "Foo" as an argument for torpedo number
		Submarine s2 = new Submarine(30, 28, "Foo", "Snubnose", "Submarine");
		
		//Step 7.3, creating 2 P8 objects
		P8 p1 = new P8(20, 400, 10000, 500, "P8ton Manning", "P8");
		P8 p2 = new P8(5, 1888, 10, 1, "ToP8to", "P8");
		
		//Step 7.4, collection of destroyers
		List<Destroyer> destroyerList = new ArrayList<Destroyer>();
		destroyerList.add(d1);
		destroyerList.add(d2);
		
		//Step 7.5, collection of submarines
		List<Submarine> submarineList = new ArrayList<Submarine>();
		submarineList.add(s1);
		submarineList.add(s2);
		
		//Step 7.6, collection of ships
		List<Ship> shipList = new ArrayList<Ship>();
		shipList.addAll(destroyerList);
		shipList.addAll(submarineList);
		
		//Step 7.7, collection of contacts
		List<Contact> contactList = new ArrayList<Contact>();
		contactList.add(p1);
		contactList.add(p2);
		contactList.addAll(shipList);
		
		//Step 8, printing with println()
		for (Contact c: contactList) {
			System.out.println(c);
			System.out.println();
		}
	}

}
