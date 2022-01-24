package com.LockedMe;

import static com.LockedMe.Constants.ERROR_MESSAGE;
import static com.LockedMe.Constants.PROJECT_FILES_PATH;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

class LockedMe {
	private static int count = 0;

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		int options;
		String choice;
		do {
			if (count == 0) {
				welcomeScreen();
			} else {
				System.out.println("Back to Main Menu (Y/N): ");
				choice = userInput.nextLine();
				if (choice.equalsIgnoreCase("Y")) {
					welcomeScreen();
				} else {
					System.exit(0);
				}
			}

			System.out.println("Enter your option :");
			options = Integer.parseInt(userInput.nextLine());
			switch (options) {
			case 1:
				displayFiles();
				break;
			case 2:
				createFiles(userInput);
				break;
			case 3:
				deleteFiles(userInput);
				break;
			case 4:
				searchFiles(userInput);
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option!");
				break;
			}
		} while (options > 0);
		userInput.close();
	}

	public static void welcomeScreen() {
		count++;
		System.out.println("===============================================================");
		System.out.println("\n\t\t   Welcome to Lockers Pvt. Ltd. ");
		System.out.println("\n\t\t\t  -LockedMe.com- ");
		System.out.println("\n\t\t -Developed by:Chiranjiv Mohanty");
		System.out.println("\n===============================================================");
		System.out.println("\n");
		System.out.print("\t1. Display all the files");
		System.out.println("\t2. Create a New file");
		System.out.println("\n");
		System.out.print("\t3. Delete a file");
		System.out.println("\t\t4. Search a file");
		System.out.println("\n");
		System.out.println("\t5. Exit\n");
	}

	/**
	 * This function will return all the file names present in the project directory
	 */
	public static void displayFiles() {
		try {
			File folder = new File(PROJECT_FILES_PATH);
			File[] listOfFiles = folder.listFiles();
			if (listOfFiles.length == 0)
				System.out.println("\nNo files exist in the directory!");
			else {
				for (var l : listOfFiles) {
					System.out.println(l.getName());
				}
			}
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
		}
	}

	/**
	 * This function will search for an user specified file name present in the
	 * project directory
	 * 
	 * @param input
	 */
	public static void searchFiles(Scanner input) {
		try {
			String fileName;
			System.out.println("\nEnter the name of the file to be searched :\n");
			fileName = input.nextLine();
			File folder = new File(PROJECT_FILES_PATH);
			File[] listOfFiles = folder.listFiles();
			LinkedList<String> filenames = new LinkedList<String>();
			for (var l : listOfFiles)
				filenames.add(l.getName());
			if (filenames.contains(fileName))
				System.out.println("\nFile is available!\n");
			else
				System.out.println("\nFile not found!");
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
		}
	}

	/**
	 * This function will delete a user specified file from the project directory
	 * list.
	 * 
	 * @param input
	 */
	public static void deleteFiles(Scanner input) {
		try {
			String fileName;
			System.out.println("\nEnter the name of the file to be deleted :\n");
			fileName = input.nextLine();
			File file = new File(PROJECT_FILES_PATH + "\\" + fileName);
			if (file.exists()) {
				file.delete();
				System.out.println("\n*File " + fileName + " deleted successfully!*\n");
			} else {
				System.out.println("Error: File not found!");
			}
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
		}
	}

	/**
	 * This function will Create user specified files in the project directory and
	 * will allow users to write in it
	 * 
	 * @param input
	 */
	public static void createFiles(Scanner input) {
		try {
			String fileName;
			System.out.println("\nEnter the name of file you want to create :\n");
			fileName = input.nextLine();
			int linesCount;
			System.out.println("\nEnter the number of lines you want to write:\n ");
			linesCount = Integer.parseInt(input.nextLine());
			PrintWriter printWriter = new PrintWriter(PROJECT_FILES_PATH + "\\" + fileName);
			for (int i = 1; i <= linesCount; i++) {
				System.out.println("\nWrite here :\n");
				printWriter.write(input.nextLine());
				printWriter.println();
			}
			System.out.println("\nFile has been created successfully!");
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
		}
	}
}