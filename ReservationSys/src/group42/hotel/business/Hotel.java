package group42.hotel.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.lib.Email;
import dw317.lib.creditcard.AbstractCreditCard;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;

/**
 * This class will allow
 * 
 * @author Keylen
 *
 */
public class Hotel extends java.util.Observable {

	private final HotelFactory factory;
	private final CustomerDAO customers;
	private final ReservationDAO reservations;
	private static final long serialVersionUID = 42031768871L;

	public Hotel(HotelFactory factory, CustomerDAO customers, ReservationDAO reservations) {
		this.factory = factory;
		this.customers = customers;
		this.reservations = reservations;
	}

	/**
	 * Cancels a given reservation
	 * 
	 * @param reservation
	 * @throws NonExistingReservationException
	 */
	public void cancelReservation(Reservation reservation) throws NonExistingReservationException {
		try {
			reservations.cancel(reservation);
		} catch (NonExistingReservationException ner) {
			System.out.println("The reservation does not exist.");
		}
	}

	/**
	 * Saves all data, clears expired reservations and closes the hotel.
	 *
	 * @throws IOException
	 *             If there is a problem closing the hotel files.
	 */
	public void closeHotel() throws IOException {
		reservations.clearAllPast();// Clears all past reservations
		reservations.disconnect();
	}

	/**
	 * Creates and adds a reservation for a customer.
	 * 
	 * @param customer
	 *            The given customer
	 * @param checkin
	 *            The requested check in date
	 * @param checkout
	 *            The requested check out date
	 * @param roomType
	 *            The requested room type
	 * @return The Reservation if possible
	 */
	public Optional<Reservation> createReservation(Customer customer, LocalDate checkin, LocalDate checkout,
			RoomType roomType) {
		DawsonReservation reserv = null;

		Optional<Room> freeRoom = factory.getAllocationPolicy(reservations).getAvailableRoom(checkin, checkout,roomType);

		if (freeRoom.isPresent()) {
			Room room = freeRoom.get();
			 reserv = new DawsonReservation(customer, room, checkin.getYear(), checkin.getMonthValue(),
					checkin.getDayOfMonth(), checkout.getYear(), checkout.getMonthValue(), checkout.getDayOfMonth());

			try {
				reservations.add(reserv);
			}
			 catch (DuplicateReservationException e) {// If the message does
														// not
														// work, change the e
				System.out.println(e.getMessage());
			}
			return Optional.ofNullable(reserv);}
		return Optional.ofNullable(reserv);}
	
		//Optional<Reservation> res = reserv;
		//return ;

	

	/**
	 * Finds and returns a customer record.
	 * 
	 * @param email
	 *            The customer's e-mail address
	 * @return Customer object
	 * @throws NonExistingCustomerException
	 *             if the customer with the given e-mail cannot be found
	 */
	public Customer findCustomer(String email) throws NonExistingCustomerException {
		Email e = new Email(email);
		try {
			return customers.getCustomer(e);
		} catch (NonExistingCustomerException nec) {
			System.out.println("\n" + nec.getMessage());
		}
		return null;// This is risky, I dont know if it will work Keylen
	}

	/**
	 * Finds all reservations made by a customer
	 * 
	 * @param customer
	 * @return List of Reservations. Returns empty list if no reservations can
	 *         be found.
	 */
	public List<Reservation> findReservations(Customer customer) {
		return reservations.getReservations(customer);
	}

	/**
	 * Registers a new Customer
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @return The Customer object
	 * @throws DuplicateCustomerException
	 *             is a customer with same e-mail address exists
	 */
	public Customer registerCustomer(String firstName, String lastName, String email)
			throws DuplicateCustomerException {
		DawsonCustomer customer = new DawsonCustomer(firstName, lastName, email);
		customers.add(customer);
		return customer;
	}

	/**
	 * Adds or updates the credit card associate with a customer.
	 * 
	 * @param email
	 *            The email address of the customer
	 * @param cardType
	 * @param cardnumber
	 * @return the updated Customer
	 * @throws NonExistingCustomerException
	 *             if the customer with the given e-mail cannot be found
	 */
	public Customer updateCreditCard(String email, String cardType, String cardnumber)
			throws NonExistingCustomerException {
		CardType cType = CardType.valueOf(cardType);

		AbstractCreditCard creditCard = (AbstractCreditCard) CreditCard.getInstance(cType, cardnumber);

		Email mail = new Email(email);

		Customer cus = customers.getCustomer(mail);

		customers.update(cus.getEmail(), creditCard);

		return cus;

	}
}
