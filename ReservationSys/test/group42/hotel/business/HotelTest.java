package group42.hotel.business;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.data.CustomerListDB;
import group42.hotel.data.ReservationListDB;
import group42.hotel.data.RoomListDB;
import group42.hotel.data.SequentialTextFileList;

public class HotelTest {

	public static void main(String[] args) {
		String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
		String cus = "ReservationSys\\datafiles\\database\\customers.txt";
		String res = "ReservationSys\\datafiles\\database\\reservations.txt";

		ListPersistenceObject a = new SequentialTextFileList(rooms, cus, res);
		CustomerDAO customerList = null;
		ReservationDAO reservesList = null;
		try{
		
		customerList = new CustomerListDB(a);
		reservesList = new ReservationListDB(a);
		}
		catch(Exception e){}
		//Room, customer, and reser don't exist in the list
		Room room = DawsonHotelFactory.DAWSON.getRoomInstance(202, "penthouse");
		Customer customer = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");
		Reservation reser = new DawsonReservation(customer, room, 2035, 9, 27, 2036, 9, 30);
		
		//Room, customer, and reser that exists in the list
		Room roomA = DawsonHotelFactory.DAWSON.getRoomInstance(101, "normal");
		Customer customerA = DawsonHotelFactory.DAWSON.getCustomerInstance("Fernando", "Alonso", "alonso@mclaren.com");
		Reservation reserA = new DawsonReservation(customerA, roomA, 2016, 9, 13, 2016, 9, 27);
		
		//alonso@mclaren.com*Fernando*Alonso*visa*4556054416014030
		//alonso@mclaren.com*2016*9*13*2016*9*27*101

		
		Hotel hotelInstance = new Hotel(DawsonHotelFactory.DAWSON, customerList, reservesList);
		System.out.println("\n--------------Testing on a non exsitent reservation----------");
		testCancelReservation(hotelInstance, reser);
		System.out.println("\n--------------Testing on a reservation that exists----------");

		testCancelReservation(hotelInstance, reserA);
		//testCloseHotel();
		//testCreateReservation();
		//testFindCustomer();
		//testFindReservations();
		//testRegisterCustomer();
		//testUpdateCreditCard();
		
	}
	
	public static void testCancelReservation(Hotel hotelInstance, Reservation reser)
	{
		try{
			hotelInstance.cancelReservation(reser);
			} catch(Exception e){
				System.out.print("Error");
			}
		System.out.print("After: ");
	}

}
