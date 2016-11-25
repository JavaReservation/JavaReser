package group42.hotel.business;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
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
		
		CustomerDAO customerList = null;
		ReservationDAO reservesList = null;
		try{
		
		customerList = new CustomerListDB(a);
		reservesList = new ReservationListDB(a);
		
		}
		catch(Exception e){}
		//Room, customer, and reser don't exist in the list
		Room roomFalse = DawsonHotelFactory.DAWSON.getRoomInstance(202, "penthouse");
		Customer customerFalse = DawsonHotelFactory.DAWSON.getCustomerInstance("PEPE", "Escovar", "pepe_love@gmail.com");
		Reservation reserFalse = new DawsonReservation(customerFalse, roomFalse, 2035, 9, 27, 2036, 9, 30);
		
		//Room, customer, and reser that exists in the list
		Room roomA = DawsonHotelFactory.DAWSON.getRoomInstance(801, "penthouse");
		Customer customerA = DawsonHotelFactory.DAWSON.getCustomerInstance("Gangee", "Umila", "Umila-Gangee@local.ca");
		Reservation reserA = new DawsonReservation(customerA, roomA, 2017, 5, 5, 2017, 7, 8);
		

		
		Hotel hotelInstance = new Hotel(DawsonHotelFactory.DAWSON, customerList, reservesList);
		
		//testFindCustomer(hotelInstance, reserA, reserFalse);
		//testCancelReservation(hotelInstance, reservesList, reserA);
		//testCloseHotel(hotelInstance, reservesList, reserA, customerList, customerA);
		testCreateReservation(hotelInstance, reserA, reserFalse);
		//testFindReservations(hotelInstance, customerA, customerFalse);
		//testRegisterCustomer(hotelInstance, reserA, customerList);
		//testUpdateCreditCard(hotelInstance, customerFalse);
		
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
	
	public static void testCloseHotel(Hotel hotelInstance, ReservationDAO reservesList, 
			Reservation reser, CustomerDAO customerList, Customer customerA) 
			throws NonExistingCustomerException, NonExistingReservationException
	{
		
		
		
		customerA = new DawsonCustomer("Trev", "Beams", "reallyC00L@gmail.com");
		
		try{
			List<Reservation> resv = hotelInstance.findReservations(customerA);
			hotelInstance.cancelReservation(resv.get(0));
			hotelInstance.closeHotel();
			
			String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
			String cus = "ReservationSys\\datafiles\\database\\customers.txt";
			String res = "ReservationSys\\datafiles\\database\\reservations.txt";
			
			ListPersistenceObject a = new SequentialTextFileList(rooms, cus, res);
			
			CustomerDAO customerListb = null;
			ReservationDAO reservesListb = null;
			
			try{
			
			customerListb = new CustomerListDB(a);
			reservesListb = new ReservationListDB(a);
			
			}
			catch(Exception e){}
			Hotel hotelInstance2 = new Hotel(DawsonHotelFactory.DAWSON, customerListb, reservesListb);
			System.out.println(hotelInstance2.findReservations(customerA));
			
			
			
			
			} catch(IOException ioe){
				System.out.print("Error closing the hotel");
			}
	}
	
	public static void testCreateReservation(Hotel hotelInstance, Reservation reser, Reservation reserFalse)
	{
		System.out.println("\nThis reservation being tested is already taken");
		hotelInstance.createReservation(reser.getCustomer(), reser.getCheckInDate(), reser.getCheckOutDate(),
				reser.getRoom().getRoomType());
		
		System.out.println("\nThis reservation being tested is available");
		
			hotelInstance.createReservation(reserFalse.getCustomer(), reserFalse.getCheckInDate(), reserFalse.getCheckOutDate(),
					reserFalse.getRoom().getRoomType());
		
					
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
		
		System.out.println("\nThis customer should NOT be found");
		try {
			hotelInstance.findCustomer(reserFalse.getCustomer().getEmail().getAddress());
		}
		catch(NonExistingCustomerException nec){
		System.out.println(nec.getMessage());
		}
	}
	
	public static void testFindReservations(Hotel hotelInstance,Customer customerA, Customer customerFalse)
	{
		System.out.println("---------------Found-------------------");	
		System.out.println(hotelInstance.findReservations(customerA));
		
		System.out.println("---------------NotFound-------------------");
		System.out.println(hotelInstance.findReservations(customerFalse));
	}
	
	public static void testRegisterCustomer(Hotel hotelInstance,Reservation reserA, CustomerDAO customerList)
	{
		try {
			System.out.println(customerList);
			System.out.println("-------------Does Not Exist--------------");
			System.out.println(hotelInstance.registerCustomer("PEPE", "Escovar", "pepe_love@gmail.com"));
			
			System.out.println(customerList);
			
			System.out.println("-------------Exists--------------");
			System.out.println(hotelInstance.registerCustomer("Gangee", "Umila", "Umila-Gangee@local.ca"));
			
		}catch(DuplicateCustomerException dce) {
			System.out.println("The customer already exists");
		}
	}
	
	public static void testUpdateCreditCard(Hotel hotelInstance, Customer customerFalse) throws NonExistingCustomerException
	{
		try{
			System.out.println("-------------Adding a non-exsistent credit---------");
			hotelInstance.updateCreditCard("Umila-Gangee@local.ca", "MASTERCARD", "5546477798743407");
			System.out.println(hotelInstance.findCustomer("Umila-Gangee@local.ca"));
			
			System.out.println("--------------Updating an already exsisting credit card---------");
			hotelInstance.updateCreditCard("Umila-Gangee@local.ca","AMEX","374603469549637");
			System.out.println(hotelInstance.findCustomer("Umila-Gangee@local.ca"));
			
			System.out.println("--------------Non-exsisting customer---------");
			hotelInstance.updateCreditCard(customerFalse.getEmail().getAddress(),"AMEX","374603469549637");
			System.out.println(hotelInstance.findCustomer("Umila-Gangee@local.ca"));
			
		}catch(NonExistingCustomerException nec){System.out.println("This customer does not exsist");}
	}
}
