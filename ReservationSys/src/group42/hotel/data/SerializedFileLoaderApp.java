package group42.hotel.data;

import java.io.IOException;
import group42.util.ListUtilities;

public class SerializedFileLoaderApp {

	public static void main(String[] args) throws IOException {
		SequentialTextFileList stfl = new SequentialTextFileList("ReservationSys\\datafiles\\database\\rooms.txt",
				"ReservationSys\\datafiles\\database\\customers.txt",
				"ReservationSys\\datafiles\\database\\reservations.txt");

		String roomFilename = "ReservationSys\\datafiles\\database\\rooms.ser";
		String customerFilename = "ReservationSys\\datafiles\\database\\customers.ser";
		String reservationFilename = "ReservationSys\\datafiles\\database\\reservations.ser";

		saveRoomObject(stfl, roomFilename);
		saveCustomerObject(stfl, customerFilename);
		saveReservationObject(stfl, reservationFilename);

	}// end of main method

	private static void saveRoomObject(SequentialTextFileList stfl, String fileLocation) {
		try {
			ListUtilities.serializeObject(stfl.getRoomDatabase(), fileLocation);
		} catch (IOException ioe) {
			System.out.println("ioexception  has been thrown saveRoomObject    ====>" + ioe.getCause());
		}
	}// end of saveRoomObject

	private static void saveCustomerObject(SequentialTextFileList stfl, String fileLocation) {
		try {
			ListUtilities.serializeObject(stfl.getCustomerDatabase(), fileLocation);
		} catch (IOException ioe) {
			System.out.println("ioexception has been thrown saveCustomerObject    ====>" + ioe.getLocalizedMessage());
		}
	}// end of saveCustomerObject

	private static void saveReservationObject(SequentialTextFileList stfl, String fileLocation) {
		try {
			ListUtilities.serializeObject(stfl.getReservationDatabase(), fileLocation);
		} catch (IOException ioe) {
			System.out.println(
					"ioexception has been thrown with saveReservationObject    ====>" + ioe.getLocalizedMessage());
		}

	}// end of saveReservationObject
}// end of class
