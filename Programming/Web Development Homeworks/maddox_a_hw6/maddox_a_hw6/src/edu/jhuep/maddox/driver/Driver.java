package edu.jhuep.maddox.driver;

import javax.swing.SwingUtilities;

import edu.jhuep.maddox.display.Display;

//basic driver class to start the display window running
//format for the runnable function was taken from Oracle docs
public class Driver {

	public static void main(String[] args) {

		final Display display = new Display("Enter hike information to get a rate");
		Runnable showFrame = new Runnable() {
			@Override
			public void run() {
				display.launch();
			}
		};

		SwingUtilities.invokeLater(showFrame);
	}

}
