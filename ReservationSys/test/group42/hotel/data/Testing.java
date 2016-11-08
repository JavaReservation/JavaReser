package group42.hotel.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import group42.util.ListUtilities;

public class Testing {

	public static void main(String[] args) {
		// System.out.print("Testing the HotelFileLoader class\n");

		String filePath = "ReservationSys\\datafiles";
		String fileName = "rooms.txt";
		String fullNamePath = filePath + File.separator + fileName;

		// testRoomRead("Case 1 : Valid data : rooms.txt", fullNamePath, true);
		customerSortMergeFile();
	}

	private static void testRoomRead(String testCase, String file, boolean expectedValue) {

		Room[] rooms = null;
		String fileLocation = "ReservationSys\\datafiles\\rooms.txt";

		try {
			rooms = HotelFileLoader.getRoomListFromSequentialFile(file);
			ListUtilities.sort(rooms);
			if (!expectedValue) {
				System.out.println("Not the expectedValue" + testCase);
			} else {
				for (int i = 0; i < rooms.length; i++) {
					System.out.println(rooms[i]);
					writeToFile(rooms[i], fileLocation);
				}
			}

		} catch (IOException ioe) {
			System.out.println("\nCatching the general I/O exception");
		}

		catch (NullPointerException npe) {
			System.out.print("There is a null pointer exception");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	@SuppressWarnings({ "rawtypes" })
	public static void customerSortMergeFile() {

		Customer[][] customer = new Customer[10][];
		// String fileLocation = "ReservationSys\\datafiles\\customers";
		String fileLocation = "ReservationSys\\datafiles\\customers1.txr";
		String filePath = "ReservationSys\\datafiles";
		String fileName = "customers";
		String file = filePath + File.separator + fileName;

		try {
			for (int i = 0; i < customer.length; i++){
				customer[i] = HotelFileLoader.getCustomerListFromSequentialFile(file + (i + 1) + ".txt");
				
			}
			writeToCustomer(customer, fileLocation);
			Comparable[] finalList = customer[0];

			for (int i = 1; i < customer.length; i++) {

				finalList = ListUtilities.merge(finalList, customer[i],
						"ReservationSys\\datafiles\\duplicateCustomers.txt");
			
			}
			

		} catch (IOException e) {

			System.out.println(e.getMessage());

		} catch (IllegalArgumentException ai) {

			System.out.println(ai.getMessage());

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

	private static void writeToCustomer(Customer[][] customer, String fileLocation) {
		PrintWriter outputStream = null;

		fileLocation = "";

		try {

			for (int i = 0; i < customer.length; i++) {

				fileLocation = "ReservationSys\\datafiles\\" + "customers" + (i + 1) + ".txt";
				outputStream = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(fileLocation, true), StandardCharsets.UTF_8)));

				for (int j = 0; j < customer[i].length; j++) {
					ListUtilities.sort(customer[i]);
					outputStream.println(customer[i][j]);
				}
			}

		} catch (FileNotFoundException o) {
			throw new IllegalArgumentException("The text file was not found ");
		}

		finally {
			if (outputStream != null)
				outputStream.close();
		}

	}// end of method

	private static void writeToFile(Object data, String fileLocation) {
		// (Comparable<?> comparable, String duplicateFileName) {

		PrintWriter outputStream = null;

		try {

			outputStream = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(fileLocation, true), StandardCharsets.UTF_8)));

			outputStream.println(data);

		} catch (FileNotFoundException o) {
			throw new IllegalArgumentException("The text file was not found ");
		}

		finally {
			if (outputStream != null)
				outputStream.close();
		}

	}// end of method

}