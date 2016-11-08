package group42.hotel.data;

import java.io.File;
import java.io.IOException;
import dw317.hotel.business.interfaces.*;
/**
 * Testing the hotelFileLoader class
 * 
 * @author Keylen
 *
 */
public class HotelFileLoaderTest {

	public static void main(String[] args) {
		//testRoomRead();
		//testCustomerRead();
		testReservationRead();
	}
	
	private static void testReservationRead(){
		System.out.println("Testing the hotelFileLoader reservation class");
		
		
		
			String fileNameCus = "ReservationSys\\datafiles" + File.separator+"customers5.txt";
			String fileNameRoom = "ReservationSys\\datafiles" + File.separator+"rooms.txt";
			String fileNameReservation = "ReservationSys\\datafiles" + File.separator+"reservations5.txt";
			Customer customer[]= null;
			Room rooms [] = null;
			try{
				customer = HotelFileLoader.getCustomerListFromSequentialFile(fileNameCus);
				rooms= HotelFileLoader.getRoomListFromSequentialFile(fileNameRoom);
			}catch(IOException ioe){
				System.out.println("General input output catch");
				
			}
			testReservationRead("Case 1 : Valid data : reservations5.txt", fileNameReservation, customer, rooms, true);

			//fileNameReservation = "ReservationSys\\datafiles" + File.separator+"";
			//testReservationRead("Case 2 : Invalid data : no text file", fileNameReservation, customer, rooms, false);
		}
	
	
	private static void testReservationRead(String testCase, String fileName,
			Customer [] customerList, Room[] roomList, boolean expectedValue) {
		Reservation[] reserves = null;
		try {
			reserves = HotelFileLoader.getReservationListFromSequentialFile(fileName, customerList, roomList);

			for (int i = 0; i < reserves.length; i++) {
				System.out.println(reserves[i]);
			}
			if (!expectedValue)
				System.out.println("Not the expectedValue" + testCase);
		} catch (IOException ioe) {
			System.out.println("\nCatching the general I/O exception");
		}

		catch (NullPointerException npe) {
			System.out.print("Cannot find the file specified\n");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("There is an illegal argument in the reservation from sequential file\n" + iae.getMessage());
		}
		
	}

	private static void testRoomRead() {
		System.out.print("Testing the HotelFileLoader class\n");

		String filePath = "ReservationSys\\datafiles";
		String fileName = "rooms.txt";
		String fullNamePath = filePath + File.separator + fileName;

		testRoomRead("Case 1 : Valid data : rooms.txt\n", fullNamePath, true);
		
		//fileName="";	fullNamePath = filePath + File.separator + fileName;
		//testRoomRead("Case 2 : Invalid data : no text file\t", fullNamePath, false);
	}

	private static void testRoomRead(String testCase, String file, boolean expectedValue) {

		System.out.print("\n" +testCase);
		Room[] rooms = null;

		try {
			rooms = HotelFileLoader.getRoomListFromSequentialFile(file);
			if (!expectedValue)
				System.out.println("Not the expectedValue" + testCase);
		}

		catch (IOException ioe) {
			System.out.println("\nCatching the general I/O exception");
		}

		catch (NullPointerException npe) {
			System.out.print("There is a null pointer exception");
			
		}

		if(!(rooms == null))
		for (int i = 0; i < rooms.length; i++) {
			if(rooms[i] == null)
			
			System.out.println(rooms[i]);
		}
	}

	private static void testCustomerRead() {

		String filePath = "ReservationSys\\datafiles";
		for (int i = 1; i <= 10; i++) {
			String fileName = "customers" + i + ".txt";
			String fullNamePath = filePath + File.separator + fileName;
			testCustomerRead("Case 1 : Valid data : customer" + i + ".txt", fullNamePath, true);

			fileName=""; fullNamePath = filePath+File.separator+fileName;
			testCustomerRead("Case 2 : Invalid data : no text file",fullNamePath, false);
		}
	}

	private static void testCustomerRead(String testCase, String fileName, boolean expectedValue) {
		Customer[] customers = null;
		try {
			customers = HotelFileLoader.getCustomerListFromSequentialFile(fileName);

			for (int i = 0; i < customers.length; i++) {
				System.out.println(customers[i]);
			}
			if (!expectedValue)
				System.out.println("Not the expectedValue" + testCase);
		} catch (IOException ioe) {
			System.out.println("\nCatching the general I/O exception");
		}

		catch (NullPointerException npe) {
			System.out.print("Cannot find the file specified\n");
		}
	}
}