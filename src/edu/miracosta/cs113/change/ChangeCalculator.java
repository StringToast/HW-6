package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange,
 * which determines and prints all possible coin combinations representing a
 * given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and
 * Design Using Java (2nd ed.): Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for
 * the equivalent tester file to verify that all given coin combinations are
 * unique.
 */
public class ChangeCalculator {

	/**
	 * Wrapper method for determining all possible unique combinations of quarters,
	 * dimes, nickels, and pennies that equal the given monetary value in cents.
	 *
	 * In addition to returning the number of unique combinations, this method will
	 * print out each combination to the console. The format of naming each
	 * combination is up to the user, as long as they adhere to the expectation that
	 * the coins are listed in descending order of their value (quarters, dimes,
	 * nickels, then pennies). Examples include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
	 *
	 * @param cents a monetary value in cents
	 * @return the total number of unique combinations of coins of which the given
	 *         value is comprised
	 */
	// Declarations and instantiations
	static int[] coinTotal = new int[4];
	static ArrayList<String> combos = new ArrayList<String>();

	// CalculateChange method, Sorts and combines amounts recursively and then adds
	// the total of the coins to an Array list.
	public static int calculateChange(int cents) {
		combos.clear();
		calculateChange(cents, 0, 0, 0, cents);
		return combos.size();
	}

	public static void calculateChange(int cents, int numQuarters, int numDimes, int numNickels, int numPennies) {
//    	final int Quarters = coinTotal[0], Dimes = coinTotal[1], Nickels = coinTotal[2], Pennies = coinTotal[3];
		String change = cents + " total cents, " + numQuarters + " Quarters, " + numDimes + " Dimes, " + numNickels
				+ " Nickels, " + numPennies + " Pennies, ";

		if (!combos.contains(change)) {
			System.out.println(change);
			combos.add(change);
			// Adds change based on amount of pennies
			if (numPennies >= 5) {
				calculateChange(cents, numQuarters, numDimes, numNickels + 1, numPennies - 5);
				
			}
			if (numPennies >= 10) {
				calculateChange(cents, numQuarters, numDimes + 1, numNickels, numPennies - 10);
				
			}
			if (numPennies >= 25) {
				calculateChange(cents, numQuarters + 1, numDimes, numNickels, numPennies - 25);
				
			}
			// add change based on amount of nickels
//			if (numNickels >= 2) {
//				calculateChange(cents, numQuarters, numDimes + 1, numNickels - 2, numPennies);
//				
//			}
//			if (numNickels >= 5) {
//				calculateChange(cents, numQuarters + 1, numDimes, numNickels - 5, numPennies);
//				
//			}
//			// Calculate Change based on dimes
//			if (numDimes >= 5) {
//				calculateChange(cents, numQuarters + 2, numDimes - 5, numNickels, numPennies);
//				
//			}
//			// Calculate weird combos of cents
//			if (numDimes >= 2 && numNickels >= 1) {
//				calculateChange(cents, numQuarters + 1, numDimes - 2, numNickels - 1, numPennies);
//				
//			}
		}
	}

	/**
	 * Calls upon calculateChange(int) to calculate and print all possible unique
	 * combinations of quarters, dimes, nickels, and pennies that equal the given
	 * value in cents.
	 *
	 * Similar to calculateChange's function in printing each combination to the
	 * console, this method will also produce a text file named
	 * "CoinCombinations.txt", writing each combination to separate lines.
	 *
	 * @param cents a monetary value in cents
	 */
	public static void printCombinationsToFile(int cents) {
		PrintWriter out;
		try {
			out = new PrintWriter("src/edu.miracosta.cs113/change/CoinCombinations.txt");
			for(int i = 0; i< combos.size(); i++) {
				out.println(combos.get(i));
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

} // End of class ChangeCalculator