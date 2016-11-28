
package group42.hotel.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import dw317.hotel.business.interfaces.*;
import group42.hotel.business.DawsonHotelFactory;

/**
 * This class is responsible for loading the rooms text file and the customer
 * text file. After reading from the files it will create an array for each of
 * them.
 * 
 * @author Keylen
 * @version 24/10/2016
 */
public class HotelFileLoader {

	private HotelFileLoader() {
	}

	/**
	 * This class is responsible for reading from the rooms.txt file, splitting
	 * the strings inside and placing the room number and room type in an object
	 * inside of an array.
	 * 
	 * @param filename
	 * @return arr This is the array containing the array of rooms
	 * @throws IOException
	 *             If
	 */
	public static Room[] getRoomListFromSequentialFile(String filename) throws IOException {
		Scanner inputFile = null;
		String record = null;
		DawsonHotelFactory dhf = DawsonHotelFactory.DAWSON;
		Room[] arr = new Room[2];
		String[] fields = null;
		try {
			BufferedReader b = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

			inputFile = new Scanner(b);
			int i = 0;
			while (inputFile.hasNext()) {
				record = inputFile.nextLine();

				fields = record.split("\\*");

				try {
					arr[i] = dhf.getRoomInstance(Integer.parseInt(fields[0]), fields[1]);
				}

				catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage() + "\nat record: " + record + "\nin file:" + filename);
					continue;
				}
				i++;
				if (i >= arr.length)
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);
			}

			arr = Arrays.copyOf(arr, i);
		} catch (IOException ioe) {
			System.out.println("General input output catch: " + ioe.getMessage());
		}

		finally {
			if (inputFile != null)
			inputFile.close();
		}
		return arr;
	}

	/**
	 * This class is responsible for reading from the customer.txt files,
	 * splitting the strings inside and placing the room number and room type in
	 * an object inside of an array.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Customer[] getCustomerListFromSequentialFile(String filename) throws IOException {
		Scanner inputFile = null;
		String record = "";
		DawsonHotelFactory dhf = DawsonHotelFactory.DAWSON;
		Customer[] arr = new Customer[3];
		String[] fields = null;
		try {
			BufferedReader b = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
			inputFile = new Scanner(b);
			int i = 0;
			while (inputFile.hasNext()) {

				record = inputFile.nextLine().trim();
				if (record.isEmpty())
					continue;

				if (record.length() - record.replace("*", "").length() != 4) {
					System.out.println("\nError: the record contain less than four *" + "\nFileName: " + filename
							+ "\nRecord: " + record);
					continue;
				}

				fields = record.split("\\*");

				try {
					arr[i] = dhf.getCustomerInstance(fields[1], fields[2], fields[0]);
					if (fields.length > 3)
						arr[i].setCreditCard(Optional.of(dhf.getCard(fields[3], fields[4])));
				}

				catch (IllegalArgumentException iae) {
					System.out.println("\n" + iae.getMessage() + "\nat record: " + record + "\nin file:" + filename);
					continue;
				}
				i++;
				if (i >= arr.length)
					arr = Arrays.copyOf(arr, arr.length * 2);
			}

			arr = Arrays.copyOf(arr, i);
		}

		catch (IOException ioe) {
			System.out.println("General input output catch :" + ioe.getMessage());
		}

		finally {
			if (inputFile != null)
			inputFile.close();
		}
		return arr;
	}

	private static boolean lengthVerification(String[] arr) {
		final int maxSize = 5;

		// If the size is not equal that mean the array does not contain a Card
		if (maxSize != arr.length)
			return false;

		// If the array is loaded, BUT there is blank spaces or nulls there is
		// no Card
		if (maxSize == arr.length)
			for (int i = 3; i < arr.length; i++)
				if (arr[i] == null || arr[i].isEmpty())
					return false;

		// Else there is a Card
		return true;
	}

	public static Reservation[] getReservationListFromSequentialFile(String filename, Customer[] customerList,
			Room[] roomList) throws IOException, IllegalArgumentException {

		Reservation[] arr = new Reservation[2];
		Scanner inputStream = null;
		String record = null;
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;

		try {

			BufferedReader outStream = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

			inputStream = new Scanner(outStream);

			int i = 0;

			while (inputStream.hasNext()) {
				Boolean customerExists = false;
				Boolean roomExists = false;
				int customerPosition = -1;
				int roomPosition = -1;
				record = inputStream.nextLine();
				String[] fields = record.split("\\*");

				// look for blank line
				if (fields.length == 1)
					continue;

				try {
					for (int c = 0; c < customerList.length; c++) {
						if (customerList[c].getEmail().getAddress().equals(fields[0])) {
							customerExists = true;
							customerPosition = c;

							for (int r = 0; r < roomList.length; r++) {
								if (roomList[r].getRoomNumber() == (Integer.parseInt(fields[7]))) {
									roomExists = true;
									roomPosition = r;
									break;
								} else
									roomExists = false;
							}
							break;
						} else
							customerExists = false;
					}

					if (customerExists && roomExists)
						arr[i] = dHF.getReservationInstance(customerList[customerPosition], roomList[roomPosition],
								Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]),
								Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
					else {
						if (!customerExists)
							throw new IllegalArgumentException("Customer does not exist");
						else if (!roomExists)
							throw new IllegalArgumentException("Room does not exist");
					}

					// added
				} catch (NumberFormatException nfe) {
					System.out.println(nfe.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
					continue;
				} catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
					continue;
				}

				i++;
				if (i >= arr.length) // resize
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);

			} // end of the while loop

			// shrink
			arr = Arrays.copyOf(arr, i);

		} catch (IOException io) {
			System.out.println(io.getMessage());
		}

		// Close Scanner
		finally {
			if (inputStream != null)
			inputStream.close();
		}

		return arr;
	}
}
