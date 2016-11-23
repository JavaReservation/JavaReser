package group42.hotel.business;

import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.data.CustomerListDB;
import group42.hotel.data.ReservationListDB;
import group42.hotel.data.SequentialTextFileList;

public class HotelTest {

	public static void main(String[] args) throws NonExistingReservationException {
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
		

		testCancelReservation(hotelInstance, reservesList, reserA);
		//testCloseHotel(hotelInstance, reserA);
		//testCreateReservation(hotelInstance, reserA);
		//testFindCustomer(hotelInstance, reserA);
		//testFindReservations(hotelInstance, reserA);
		//testRegisterCustomer(hotelInstance, reserA);
		//testUpdateCreditCard(hotelInstance, reserA);
		
	}
	
	public static void testCancelReservation(Hotel hotelInstance, ReservationDAO reservesList, Reservation reser) 
			throws NonExistingReservationException
	{
		try{
			System.out.print(reservesList);
			System.out.println("\n------------------After--------------\n");
			hotelInstance.cancelReservation(reser);
			} catch(NonExistingReservationException e){
				System.out.print("\n\tThat reservation does not exist");
			}
		System.out.print(reservesList);
		
	}
	
	public static void testCloseHotel(Hotel hotelInstance, Reservation reser)
	{
		try{
			hotelInstance.closeHotel();
			} catch(IOException e){
				System.out.print("Error closing the hotel");
			}
	}
	
	public static void testCreateReservation(Hotel hotelInstance, Reservation reser)
	{
		
	}
	

}
