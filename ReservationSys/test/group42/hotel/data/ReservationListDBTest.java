package group42.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group42.hotel.business.DawsonCustomer;
import group42.hotel.business.DawsonHotelFactory;
import group42.hotel.business.DawsonReservation;

/**
 * Testing the ReservationListDB methods individually
 * 
 * @author Werner
 *
 */
public class ReservationListDBTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// These three String variables are used to locate the text files with
		// the information about our customers
		String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
		String cus = "ReservationSys\\datafiles\\database\\customers.txt";
		String res = "ReservationSys\\datafiles\\database\\reservations.txt";

		ListPersistenceObject a = new SequentialTextFileList(rooms, cus, res);
		ReservationListDB b = new ReservationListDB(a);

		// for (int i = 0; i < 5; i++)
	//	testingToString(b);

		// testing the add method ====
		//testAddMethod(b);

		// testing getReservations method
	//	testGetReservations(b);

		// testing the canle method ====
	//	testCancle(b);

		// testing get free rooms method ====
		testGetFreeRoom(b);

		// testing getFreeRooms with roomtype param ===
	//	testGetFreeRoomsType(b);

		// teasting the clearAllPast method
		//testClearAllPast(b);

		// testing the disconnect method
		//testDisconnect(b);
	}

	private static void testingToString(ReservationListDB b) {

		System.out.println("-------testing the to string -------");
		System.out.println(b.toString());
		System.out.println("end of toString testing \n");

	}
/**
	public static void testAddMethod(ReservationListDB resList) {

		System.out.println("------Testing the add method------");

		System.out.println("case 1 valid it will not overlap and no exceptio will be thrown");
		Room room = DawsonHotelFactory.DAWSON.getRoomInstance(202, "penthouse");
		Customer customer = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");
		Reservation res = new DawsonReservation(customer, room, 2035, 9, 27, 2036, 9, 30);

		try {
			resList.add(res);
		} catch (DuplicateReservationException e) {
			System.out.println(e.getMessage());
		} // end of case 1

		String resInfo = "knitting_fan_37@aol.com*2014*5*23*2014*5*30*201";
		String cusInfo = "knitting_fan_37@aol.com*Gertrude*Powell*visa*4539744292446098";

		System.out.println("case 2 reservation already exits. \nthis is the data trying to be added \nReservation: "
				+ resInfo + "\nCustomer :" + cusInfo);
		room = DawsonHotelFactory.DAWSON.getRoomInstance(202, "penthouse");
		customer = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");
		res = new DawsonReservation(customer, room, 2035, 9, 27, 2036, 9, 30);

		try {
			resList.add(res);
		} catch (DuplicateReservationException e) {
			System.out.println(e.getMessage());
		} // end of case 2

		System.out.println("end of testAddMethod testing \n");
	}
	**/

	private static void testDisconnect(ReservationListDB list) throws IOException {

		System.out.println("------Testing the disconnect method------");
		try {
			System.out.println(
					"case 1: accesing and using the ReservationListDB toString after it will disconnect and try to print the toString again");
			System.out.println(list.toString());
			System.out.println("trying to disconnect");
			list.disconnect();
			System.out.println("trying to print the to string again after disconnecting this will throw an exception");
			System.out.println(list.toString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException a) {
			System.out.println("Cannot acces the to string due to disconnect");
		}

		System.out.println("end of testDisconnect testing \n");
	}

	private static void testGetReservations(ReservationListDB b) {

		System.out.println("------testing the getReservations method------");

		Customer customer = new DawsonCustomer("Johny-Laurence", "Smith", "john.bussiness@gmail.ca");
		System.out.println("case 1: valid customer entered \ncustomer information used :" + customer.toString());
		List<Reservation> res = new ArrayList<Reservation>();

		res.addAll(b.getReservations(customer));

		// A for loop is used if there are several reservations found
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).toString());
			if (res.size() == 0) {
				System.out.println("no reservation was found");
			}
		}

		customer = new DawsonCustomer("Not-A", "REal-customer", "fake_email@gmail.ca");
		System.out.println("case 2: invalid information used :" + customer.toString());
		res = new ArrayList<Reservation>();

		res.addAll(b.getReservations(customer));

		if (res.size() == 0) {
			System.out.println("no reservation was found");
		}

		System.out.println("end of testGetReservations testing \n");
	}
	/**
	private static void testCancle(ReservationListDB b) {

		System.out.println("------Testing the cancle method------");

		Room notARoom = DawsonHotelFactory.DAWSON.getRoomInstance(202, "normal");
		Customer notACustomer = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");
		Reservation notARes = new DawsonReservation(notACustomer, notARoom, 2016, 9, 1, 2016, 11, 5);
		System.out.println("case 1 : invalid reservation\nreservation used : " + notARes.toString());

		try {
			b.cancel(notARes);
		} catch (NonExistingReservationException e) {
			System.out.println(e.getMessage());
		}

		// this is a valid Reservation
		// this is the customer information I used to test is:
		// sterling.archer@isis.ca*2016*1*1*2016*1*5*108
		// sterling.archer@isis.ca*Archer*Sterling*mastercard*5214227392892824
		Customer customer = DawsonHotelFactory.DAWSON.getCustomerInstance("Archer", "Sterling",
				"sterling.archer@isis.ca");
		Room room = DawsonHotelFactory.DAWSON.getRoomInstance(108, "normal");
		Reservation res = new DawsonReservation(customer, room, 2016, 1, 1, 2016, 1, 5);

		System.out.println("case 2 : valid Reservation\nReservatoin used : " + res.toString());

		try {
			b.cancel(res);
		} catch (NonExistingReservationException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("end of testCancle testing \n");

	}
*/
	private static void testGetFreeRoom(ReservationListDB b) {

		System.out.println("testing the get free rooms method");

		System.out.println("case 1 when checkin date is 2015, 9, 27 untill 2015, 9, 30");
		List<Room> roomList = new ArrayList<Room>();
		roomList = b.getFreeRooms(LocalDate.of(2015, 9, 27), LocalDate.of(2015, 9, 30));

		for (int i = 0; i < roomList.size(); i++) {
			//System.out.println(roomList.get(i).toString());
		}

		roomList = new ArrayList<Room>();
		/**
		System.out.println("case 2 all rooms available between 2012, 9, 27 and 2014, 9, 30 with room type normal");
		roomList = b.getFreeRooms(LocalDate.of(2012, 9, 27), LocalDate.of(2014, 9, 30));

		for (int i = 0; i < roomList.size(); i++) {

			System.out.println(roomList.get(i).toString());
			}
			
			*/
		
		System.out.println("end of testGetFreeRoom testing \n");
	}

	private static void testGetFreeRoomsType(ReservationListDB b) {
		System.out.println("-------testing the get free rooms method with the room type param-------");
		List<Room> roomList = new ArrayList<Room>();
		System.out.println("case 1 all rooms available between 2012, 9, 27 and 2014, 9, 30 with room type normal");
		roomList = b.getFreeRooms(LocalDate.of(2012, 9, 27), LocalDate.of(2014, 9, 30), RoomType.NORMAL);

		for (int i = 0; i < roomList.size(); i++) {

			System.out.println(roomList.get(i).toString());
		}
		System.out.println("case 2 all rooms available between 2016 9 27 and 2016 9 30");
		roomList = b.getFreeRooms(LocalDate.of(2016, 9, 27), LocalDate.of(2016, 9, 30), RoomType.NORMAL);

		if (roomList.size() <= 0) {
			System.out.println("no rooms are currentlly available");
		} else {
			for (int i = 0; i < roomList.size(); i++) {

				System.out.println(roomList.get(i).toString());
			}
		}
		System.out.println("end of testGetFreeRoomsType testing \n");

	}

	private static void testClearAllPast(ReservationListDB b) {
		System.out.println("-------testing the clear all method-------");

		System.out.println("Printing to string first : \n" + b.toString());

		try {
			b.clearAllPast();
		} catch (Exception a) {
			System.out.println(a.getMessage());
		}

		System.out.println("Printing the toString after the clearAll method was called : \n");
		System.out.println(b.toString());
	}

}
