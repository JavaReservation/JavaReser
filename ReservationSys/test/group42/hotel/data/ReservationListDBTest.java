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
		// System.out.println(b.toString());

			// testing the add method  ====
		// testAddMethod(b);

		// testing the disconnect method
		// testDisconnect(b);

		// testing getReservations method
		// testGetReservations(b);

			// testing the canle method  ====
		//testCancle(b);
		
			//testing getReservedRooms method  ====
		//testGetReservedRooms(b);
		
			//testing getFreeRooms with roomtype param ===
		//testGetFreeRoomsType(b);
		
		//teasting the clearAllPast method
		testClearAllPast(b);
		
		

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
		
		Room room = DawsonHotelFactory.DAWSON.getRoomInstance(202, "normal");
		Customer customer = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");

		Reservation res = new DawsonReservation(customer, room, 2016, 9, 27, 2016, 9, 30);

		try{
			b.cancel(res);
		}catch(NonExistingReservationException e){
			System.out.println(e.getMessage());
		}
			
	}
	private static void testGetReservedRooms(ReservationListDB b) {

		
		int size = b.getFreeRooms(LocalDate.of(2016, 9, 27), LocalDate.of(2016, 9, 30)).size();
		
		
		System.out.println(b.getFreeRooms(LocalDate.of(2016, 9, 27), LocalDate.of(2016, 9, 30)).size());
		
		for(int i = 0; i < size; i++){
			System.out.println(b.getFreeRooms(LocalDate.of(2000, 9, 27), LocalDate.of(2030, 9, 30)));
		}
	}
	
	private static void testGetFreeRoomsType(ReservationListDB b) {
		
		System.out.println(b.getFreeRooms(LocalDate.of(2000, 9, 27), LocalDate.of(2030, 9, 30), RoomType.NORMAL).toString());
		
	}
	
	private static void testClearAllPast(ReservationListDB b) {

			b.clearAllPast();
			
			System.out.println(b.toString());
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
