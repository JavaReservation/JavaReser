package group42.hotel.data;

import java.io.File;
import java.io.IOException;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group42.util.ListUtilities;

/**
 * This main application that will write to all text files, it will call the
 * ListUtiles class in order to sort and merge them
 * 
 * @author Werner
 * @version 01/11/2016
 */
public class SortMergeApp {

	public static void main(String[] args) {
		// Every entry that does not meet standards or is caught by the catch exceptions will be displayed onto the console
		System.out.println("These are all the data entreis which do not meet standerds and were rejected ");
		
		
		System.out.println("-----------invalid data from rooms-----------");
		roomSort();
		System.out.println("\n-----------Invalid data from customer-----------");
		customerSortMergeFile();
		System.out.println("\n-----------Invalid data from reservations-----------");
		sortMergeReservation();
	}

	private static void roomSort() {

		File database = new File("ReservationSys/datafiles/sorted");
		if (!database.exists())
			database.mkdir();

		// helper variables
		String filePath = "ReservationSys\\datafiles";
		String fileName = "rooms.txt";
		String file = filePath + File.separator + fileName;

		Room[] rooms = null;
		String fileStored = "ReservationSys\\datafiles\\sorted\\rooms.txt";

		try {
			rooms = HotelFileLoader.getRoomListFromSequentialFile(file);
			ListUtilities.sort(rooms);

			ListUtilities.saveListToTextFile(rooms, fileStored);
		} catch (IOException e) {
			System.out.print("Error! Could not create file..\n" + e.getMessage());
		}

	}

	@SuppressWarnings({ "rawtypes" })
	public static void customerSortMergeFile() {

		File database = new File("ReservationSys/datafiles/database");
		if (!database.exists())
			database.mkdir();
		
		File duplicates = new File("ReservationSys/datafiles/duplicates");
		if (!duplicates.exists())
			duplicates.mkdir();

		Customer[][] customer = new Customer[10][];
		String fileLocation = "ReservationSys\\datafiles\\";
		Comparable[] finalList = null;

		try {
			for (int i = 0; i < customer.length; i++) {
				// loading the data onto the array
				customer[i] = HotelFileLoader
						.getCustomerListFromSequentialFile(fileLocation + "customers" + (i + 1) + ".txt");

				ListUtilities.sort(customer[i]);
				// writing it into sorted txt files
				ListUtilities.saveListToTextFile(customer[i],
						fileLocation + "sorted" + "\\sortedCustomers" + (i + 1) + ".txt");

			}

			// this will hold the merge files and write the duplicates onto the
			// path given by the fileLocation variable
			finalList = customer[0];
			for (int i = 1; i < customer.length; i++) {

				finalList = ListUtilities.merge(finalList, customer[i],
						"ReservationSys\\datafiles\\duplicates\\duplicateCustomers.txt");

			}

			ListUtilities.saveListToTextFile(finalList, "ReservationSys\\datafiles\\database\\customers.txt");

		} catch (IOException e) {

			System.out.println("\n" +e.getMessage());
		}

	}

	// this is whole nother problem :(
	@SuppressWarnings("rawtypes")
	public static void sortMergeReservation() {

		File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
		if (!database.exists())
			database.mkdir();

		// these are helper variables
		Reservation[][] res = new Reservation[10][];
		String fileLocation = "ReservationSys\\datafiles\\";
		Customer[] cus = null;
		Room[] room = null;

		try {
			room = HotelFileLoader.getRoomListFromSequentialFile("ReservationSys\\datafiles\\sorted\\rooms.txt");
			cus = HotelFileLoader
					.getCustomerListFromSequentialFile("ReservationSys\\datafiles\\database\\customers.txt");

			for (int i = 0; i < res.length; i++) {
				// loading the data onto the array
				res[i] = HotelFileLoader.getReservationListFromSequentialFile(
						(fileLocation + "reservations" + (i + 1) + ".txt"), cus, room);

			}

			for (int i = 0; i < res.length; i++) {
				ListUtilities.sort(res[i]);

				// writing it into sorted text files in the folder sorted.
				ListUtilities.saveListToTextFile(res[i],
						fileLocation + "sorted" + "\\sortedReservations" + (i + 1) + ".txt");

			}

			// this will hold the merge files and write the duplicates onto the
			// path given by the fileLocation variable
			Comparable[] finalList = res[0];
			for (int i = 1; i < res.length; i++) {

				finalList = ListUtilities.merge(finalList, res[i],
						"ReservationSys\\datafiles\\duplicates\\duplicateReservations.txt");

			}
			// this will end up writing all reservations into the path given.
			ListUtilities.saveListToTextFile(finalList, "ReservationSys\\datafiles\\database\\reservations.txt");

		} catch (IOException e) {

			System.out.println("\n"+e.getMessage());

		}

	}
}