package edu.ncsu.csc316.compression.ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.compression.manager.ReportManager;

/**
 * The user interface for CompressionManager.
 * @author Jamel Clarke
 */
public class CompressionManagerUI {

	/** ReportManager used to compress and decompress input files. */
	private static ReportManager rm;
	
	/**
	 * Runs the user interface for CompressionManager.
	 * @param args command line arguments
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public static void main (String[] args) throws FileNotFoundException {
		Scanner scnr = new Scanner(System.in);
		System.out.println("What's the name of the file you would like to load in?");
		while ( rm == null ) {
			try {
				rm = new ReportManager(scnr.next());
			} catch ( IllegalArgumentException iae ) {
				System.out.println("The file you entered doesn't exist. Enter another:");
				rm = new ReportManager(scnr.next());
			}
		}
		System.out.println("Would you like to compress or decompress the file?");
		System.out.println("Enter '1' to compress. Enter '2' to decompress. Enter 'Q' to quit.");
		String input = scnr.next();
		if ("1".equals(input)) {
			System.out.println(rm.compress());
		} else if ("1".equals(input)) {
			System.out.println(rm.decompress());
		} else if ("Q".equals(input)) {
			System.exit(0);
		}
		scnr.close();
		System.exit(0);
	}
}
