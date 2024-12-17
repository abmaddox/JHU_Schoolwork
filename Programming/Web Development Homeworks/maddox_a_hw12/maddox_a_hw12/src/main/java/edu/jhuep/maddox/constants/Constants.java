/**
 * @author Andrew Maddox
 * @since Apr 13, 2024
 */
package edu.jhuep.maddox.constants;

/**
 * This class contains constants used throughout the BHC projects.
 */
public class Constants {
	public static final String DATE_DELIM = "-";
	public static final int INT_MIN = 1;
	public static final double ERROR_RATE = -0.01;
	
	public static final String HIKE = "hike";
	public static final String DURATION = "duration";
	public static final String DATE = "date";
	public static final String HIKERS = "hikers";
	
	public static enum ColumnOrder{
		START_DAY,
		DURATION,
		LOCATION,
		GUIDE_FIRST,
		GUIDE_LAST,
		HIKER_FIRST,
		HIKER_LAST;
		
		//for referring to column index in SQL result table
		public int toInt() {
			return this.ordinal()+1;
		}
		
		public static String getName(ColumnOrder co){
			switch (co) {
			case DURATION:
				return "Duration";
			case GUIDE_FIRST:
				return "Guide First Name";
			case GUIDE_LAST:
				return "Guide Last Name";
			case HIKER_FIRST:
				return "Hiker First Name";
			case HIKER_LAST:
				return "Hiker Last Name";
			case LOCATION:
				return "Location";
			case START_DAY:
				return "Start Date";
			default:
				return null;
			}
		}
	}
	
	
}
