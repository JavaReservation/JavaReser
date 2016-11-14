package group42.hotel.data;

import java.io.IOException;
import java.sql.Date;
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
import group42.hotel.business.ReservationByCheckOutSorted;

public class ReservationListDBTest {

	public static void main(String[] args) throws IOException {

		// ReservationDAO a = new ReservationListDB(null);

		String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
		String cus = "ReservationSys\\datafiles\\database\\customers.txt";
		String res = "ReservationSys\\datafiles\\database\\reservations.txt";

		ListPersistenceObject a = new SequentialTextFileList(rooms, cus, res);
		ReservationListDB b = new ReservationListDB(a);
		// HotelFactory c =new HotelFactory();

		// for (int i = 0; i < 5; i++)
		// testingToString (b);

		// testing the add method ====
		// testAddMethod(b);

		// testing the disconnect method
		// testDisconnect(b);

		// testing getReservations method
		// testGetReservations(b);

		// testing the canle method ====
		// testCancle(b);

		// testing getReservedRooms method ====
		// testGetFreeRoom(b);

		// testing getFreeRooms with roomtype param ===
		testGetFreeRoomsType(b);

		// teasting the clearAllPast method
		// testClearAllPast(b);

	}

	private static void testingToString(ReservationListDB b) {

		System.out.println(b.toString());

	}

	public static void testAddMethod(ReservationListDB o) {

		Room room = DawsonHotelFactory.DAWSON.getRoomInstance(202, "normal");
		Customer customer = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");

		Reservation res = new DawsonReservation(customer, room, 2016, 9, 27, 2016, 9, 30);

		try {
			o.add(res);
		} catch (DuplicateReservationException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void testDisconnect(ReservationListDB b) throws IOException {

		try {
			b.disconnect();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void testGetReservations(ReservationListDB b) {

		Customer customer = new DawsonCustomer("Johny-Laurence", "Smith", "john.bussiness@gmail.ca");
		List<Reservation> res = new ArrayList<Reservation>();

		res.addAll(b.getReservations(customer));

		// System.out.println(res.toString());

		res.toString();

		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).toString());

			if (res.size() == 0) {
				System.out.println("Meow!!");
			}
		}
	}

	private static void testCancle(ReservationListDB b) {

		// This is not a reservation and will throw a not found exception
		Room notARoom = DawsonHotelFactory.DAWSON.getRoomInstance(202, "normal");
		Customer notACustomer = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");
		Reservation notARes = new DawsonReservation(notACustomer, notARoom, 2016, 9, 1, 2016, 11, 5);

		// this is a valid Reservation
		// this is the customer information I used to test it
		// sterling.archer@isis.ca*2016*1*1*2016*1*5*108
		// sterling.archer@isis.ca*Archer*Sterling*mastercard*5214227392892824
		Customer customer = DawsonHotelFactory.DAWSON.getCustomerInstance("Archer", "Sterling",
				"sterling.archer@isis.ca");
		Room room = DawsonHotelFactory.DAWSON.getRoomInstance(108, "normal");

		Reservation res = new DawsonReservation(customer, room, 2016, 1, 1, 2016, 1, 5);

		try {
			System.out.println("This is not a reservation and will throw a not found exception");
			b.cancel(notARes);
			System.out.println("this is a valid Reservation");
			b.cancel(res);
		} catch (NonExistingReservationException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void testGetFreeRoom(ReservationListDB b) {

		// int size = b.getFreeRooms(LocalDate.of(2016, 9, 27),
		// LocalDate.of(2016, 9, 30)).size();

		// System.out.println(b.getFreeRooms(LocalDate.of(2016, 9, 27),
		// LocalDate.of(2016, 9, 30)).size());

		List<Room> roomList = new ArrayList<Room>();
		roomList = b.getFreeRooms(LocalDate.of(2000, 9, 27), LocalDate.of(2030, 9, 30));

		for (int i = 0; i < roomList.size(); i++) {
			System.out.println(roomList.get(i).toString());
		}
	}

	private static void testGetFreeRoomsType(ReservationListDB b) {

		List<Room> roomList = new ArrayList<Room>();
		roomList = b.getFreeRooms(LocalDate.of(2000, 9, 27), LocalDate.of(2030, 9, 30), RoomType.NORMAL);

		for (int i = 0; i < roomList.size(); i++){
		
			System.out.println(roomList.get(i).toString());
		}

	}

	private static void testClearAllPast(ReservationListDB b) {

		b.clearAllPast();

		// System.out.println(b.toString());

		// ReservationByCheckOutSorted.compareByCheckout(b, b);
	}

}
