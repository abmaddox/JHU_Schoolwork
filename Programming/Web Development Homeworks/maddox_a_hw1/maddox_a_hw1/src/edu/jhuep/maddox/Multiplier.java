package edu.jhuep.maddox;

	class Multiplier {
		
		
		/**
		 * @param args This array contains the integers 2 and 5 as Strings in the first run/debug configuration and 
		 * the integers -1 and 10 in the second configuration. 
		 * 
		 * The main function parses the Strings to Integers, then passes them to the "multiply" method of the Multiplier class for processing.
		 * Output is captured from "multiply" and output to the console. If the output is a positive value then it is
		 * output without modification, but if it is a negative integer then the absolute value is output within parentheses.
		 */
		public static void main(String[] args) {
			
			int int1 = Integer.parseInt(args[0]);
			int int2 = Integer.parseInt(args[1]);
		
			
			int product = Multiplier.multiply(int1, int2);
			
			if (product < 0) {
				System.out.println("(" + (product*-1) + ")");
			}
			else {
				System.out.println(product);
			}
		}
		
		
		/**
		 * @param i This is an integer received from the main method as user input via system args.
		 * 
		 * @param j This is an integer received from the main method as user input via system args.
		 * 
		 * This method receives two validated integers then returns their product. 
		 */
		public static int multiply(int i, int j) {
			return i * j;
		}

	}

