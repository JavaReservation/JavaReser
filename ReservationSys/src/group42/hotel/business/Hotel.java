package group42.hotel.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.hotel.data.interfaces.RoomDAO;

public class Hotel {

	private final HotelFactory factory;
	private final CustomerDAO customers;
	private final ReservationDAO reservations;
	private static final long serialVersionUID = 42031768871L;

	public Hotel (HotelFactory factory, RoomDAO rooms, CustomerDAO customers,
			ReservationDAO reservations)
	{
		
	}
	
	/**
	* Cancels a given reservation
	* @param reservation
	* @throws NonExistingReservationException
	*/
	public void cancelReservation(Reservation reservation)
	throws NonExistingReservationException 
	{
		
	}
	
	/**
	* Saves all data, clears expired reservations and closes the hotel.
	*
	* @throws IOException
	* If there is a problem closing the hotel files.
	*/
	public void closeHotel() throws IOException
	{
		
	}
	
	/**
	* Creates and adds a reservation for a customer.
	* @param customer The given customer
	* @param checkin The requested check in date
	* @param checkout The requested check out date
	* @param roomType The requested room type
	* @return The Reservation if possible
	*/
	public Optional<Reservation> createReservation(Customer customer, LocalDate
	checkin, LocalDate checkout, RoomType roomType)
	{
		return null;
	}
	
	/**
	* Finds and returns a customer record.
	* @param email The customer's e-mail address
	* @return Customer object
	* @throws NonExistingCustomerException
	* if the customer with the given e-mail cannot be found
	*/
	public Customer findCustomer(String email)
	throws NonExistingCustomerException
	{
		return null;
		
	}
	
	/**
	* Finds all reservations made by a customer
	* @param customer
	* @return List of Reservations. Returns empty list if no reservations
	can be found.
	*/
	public List<Reservation> findReservations(Customer customer)
	{
		return null;
	}
	
	/**
	* Registers a new Customer
	* @param firstName
	* @param lastName
	* @param email
	* @return The Customer object
	* @throws DuplicateCustomerException is a customer with same e-mail
	address exists
	*/
	public Customer registerCustomer(String firstName, String lastName, String
	email)
	throws DuplicateCustomerException
	{
		return null;
	}
	
	/**
	* Adds or updates the credit card associate with a customer.
	* @param email The email address of the customer
	* @param cardType
	* @param cardnumber
	* @return the updated Customer
	* @throws NonExistingCustomerException
	* if the customer with the given e-mail cannot be found
	*/
	public Customer updateCreditCard(String email, String cardType,
	String cardnumber)
	throws NonExistingCustomerException
	{
		return null;
	}
}
