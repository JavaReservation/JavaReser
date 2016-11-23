package group42.hotel.business;

import java.io.IOException;
import java.time.LocalDate;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.data.CustomerListDB;
import group42.hotel.data.ReservationListDB;
import group42.hotel.data.SequentialTextFileList;

public class HotelTest {

	public static void main(String[] args) throws NonExistingReservationException, NonExistingCustomerException {
		String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
		String cus = "ReservationSys\\datafiles\\database\\customers.txt";
		String res = "ReservationSys\\datafiles\\database\\reservations.txt";

		ListPersistenceObject a = new SequentialTextFileList(rooms, cus, res);
		ListPersistenceObject b = new SequentialTextFileList(rooms, cus, res);
		CustomerDAO customerList = null;
		ReservationDAO reservesList = null;
		ReservationDAO reservesListb = null;
		try{
		
		customerList = new CustomerListDB(a);
		reservesList = new ReservationListDB(a);
		reservesListb = new ReservationListDB(b);
		}
		catch(Exception e){}
		//Room, customer, and reser don't exist in the list
		Room roomFalse = DawsonHotelFactory.DAWSON.getRoomInstance(202, "penthouse");
		Customer customerFalse = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");
		Reservation reserFalse = new DawsonReservation(customerFalse, roomFalse, 2035, 9, 27, 2036, 9, 30);
		
		//Room, customer, and reser that exists in the list
		Room roomA = DawsonHotelFactory.DAWSON.getRoomInstance(101, "normal");
		Customer customerA = DawsonHotelFactory.DAWSON.getCustomerInstance("Fernando", "Alonso", "alonso@mclaren.com");
		Reservation reserA = new DawsonReservation(customerA, roomA, 2016, 9, 13, 2016, 9, 27);
		
		//alonso@mclaren.com*Fernando*Alonso*visa*4556054416014030
		//alonso@mclaren.com*2016*9*13*2016*9*27*101

		
		Hotel hotelInstance = new Hotel(DawsonHotelFactory.DAWSON, customerList, reservesList);
		
		testFindCustomer(hotelInstance, reserA, reserFalse);
		//testCancelReservation(hotelInstance, reservesList, reserA);
		//testCloseHotel(hotelInstance, reservesList, reservesListb, reserA, customerList);
		//testCreateReservation(hotelInstance, reserA, reserFalse);
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
	
	public static void testCloseHotel(Hotel hotelInstance, ReservationDAO reservesList, ReservationDAO reservesListb, Reservation reser, CustomerDAO customerList) throws NonExistingCustomerException
	{
		//need help from jaya, everytime i close the databases it is a whole new list so i cant test if it saved.
		try{
			//Cancel a reservation to make a change to test persistence
			hotelInstance.cancelReservation(reser);
			
			//Reservation is no longer locatable, so we know we canceled it
			System.out.print(hotelInstance.findReservations(hotelInstance.findCustomer("alonso@mclaren.com")));
			hotelInstance.closeHotel();
			
			//Instantiating a new hotel with a new reservesListb
			Hotel hotelInstance2 = new Hotel(DawsonHotelFactory.DAWSON, customerList, reservesListb);
			
			//We can still find the reservation in this new hotel, but we can only find it on the first run through.
			System.out.print(hotelInstance2.findReservations(hotelInstance2.findCustomer("alonso@mclaren.com")));
			
			} catch(Exception e){
				System.out.print("Error closing the hotel");
			}
	}
	
	public static void testCreateReservation(Hotel hotelInstance, Reservation reser, Reservation reserFalse)
	{
		System.out.println("\nThis reservation being tested is already taken");
		try{
			hotelInstance.createReservation(reser.getCustomer(), reser.getCheckInDate(), reser.getCheckOutDate(),
					reser.getRoom().getRoomType());
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		System.out.println("\nThis reservation being tested is already taken");
		try{
			hotelInstance.createReservation(reserFalse.getCustomer(), reserFalse.getCheckInDate(), reserFalse.getCheckOutDate(),
					reserFalse.getRoom().getRoomType());
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	}
	
	public static void testFindCustomer(Hotel hotelInstance, Reservation reser, Reservation reserFalse)
	{
		System.out.println("--------------Testing the findCustomer method----------------\n");
		System.out.println("Valid data");
		try {
			hotelInstance.findCustomer(reser.getCustomer().getEmail().getAddress());
		}
		catch(NonExistingCustomerException nec){
		System.out.println(nec.getMessage());
		}
		
		System.out.println("This customer should NOT be found");
		try {
			hotelInstance.findCustomer(reserFalse.getCustomer().getEmail().getAddress());
		}
		catch(NonExistingCustomerException nec){
		System.out.println(nec.getMessage());
		}
	}
}
