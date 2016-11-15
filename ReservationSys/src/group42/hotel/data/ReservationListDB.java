package group42.hotel.data;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.business.DawsonHotelFactory;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.HotelFactory;

/**
 * Concrete class that provides ReservationDAO functionality
 * 
 * @author Werner
 */
public class ReservationListDB implements ReservationDAO {

	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	/**
	 * Constructer that takes in the interface ListPersistenceObject as a
	 * parameter
	 * 
	 * @param listPersistenceObject
	 */
	public ReservationListDB(ListPersistenceObject listPersistenceObject) {

		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		this.database = listPersistenceObject.getReservationDatabase();
	}

	/**
	 * Overloaded constructer that takes in the ListPersistenceObject interface
	 * and the HotelFactory interface
	 * 
	 * @param listPersistenceObject
	 * @param factory
	 */
	public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {

		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		this.database = listPersistenceObject.getReservationDatabase();
	}

	/**
	 * Adds a reservation to the database. If the reservation overlaps with an
	 * existing reservation, a DuplicationReservationException is thrown.
	 * Otherwise, the reservation is added based on room number and the check-in
	 * date.
	 * 
	 * @param reserv
	 *            The reservation to add to the database.
	 * @throws DuplicateReservationException
	 */
	@Override
	public void add(Reservation reserv) throws DuplicateReservationException { // issues
		// check if there is an overlap with the reservation we are trying to
		// add to the database

		Reservation copyReserv = factory.getReservationInstance(reserv);
		// binary search and add the reserv paramater into the sorted
		// reservation file
		int index = search(this.database, copyReserv);
		index = -(index);

		if (index > 0) {
			this.database.add(index, reserv);
			System.out.println("Reservation succesfully added ==>" + this.database.get(index).toString());
		} else {
			throw new DuplicateReservationException("The reservation is already in our system");
		}

	}

	private static int search(List<? extends Reservation> reservationList, Reservation res) {
		int low = 0;
		int high = reservationList.size() - 1;
		int mid = (low + high) / 2;
		int result;

		while (low <= high) {

			mid = (low + high) / 2;
			result = reservationList.get(mid).compareTo(res);

			if (result == 0) {
				return mid;
			} else if (result < 0) {
				low = mid + 1;
			} else
				high = mid - 1;
		}
		return -(high + 1);

	}

	/**
	 * Saves the reservations and disconnects from the database.
	 * 
	 * @throws IOException
	 *             Problems saving or disconnecting from database.
	 */
	@Override
	public void disconnect() throws IOException {

		try {
			this.listPersistenceObject.saveReservationDatabase(this.database);

		} catch (IOException a) {
			throw new IOException("Could not disconect pleas fix >.<");
		}
		this.database = null;
	}

	/**
	 * Returns the reservations for a given customer.
	 * 
	 * @param cust
	 *            The given customer.
	 * 
	 * @return The reservations for the customer (empty list if none).
	 */
	@Override
	public List<Reservation> getReservations(Customer cust) {

		List<Reservation> res = new ArrayList<Reservation>();

		for (int i = 0; i < this.database.size(); i++) {

			if (this.database.get(i).getCustomer().compareTo(cust) == 0) {
				res.add(this.database.get(i));
			}
		}

		if (res.size() > 0) {
			return res;
		}
		return res;
	}

	/**
	 * Cancels a reservation.
	 * 
	 * @param reserv
	 *            The given reservation
	 * @throws NonExistingReservationException
	 *             Could not find the reservation
	 */
	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		int found = 0;

		for (int i = 0; i < this.database.size(); i++) {
			// System.out.println(this.database.get(i).toString());

			if (this.database.get(i).compareTo(reserv) == 0) {
				// this.database.remove(i);
				found++;
				System.out.println("Found Reservaiton " + i);
				System.out.println(this.database.get(i));
			}
		}
		if (found == 0) {
			throw new NonExistingReservationException("the Reaservation is not in our database");
		}

	}

	/**
	 * Gets a list of all rooms reserved at some point between the supplied
	 * check-in and check-out dates. Note that rooms are reserved for any
	 * overlapping period, not necessarily the entirety of the period
	 * 
	 * @param checkin
	 *            Start date of period
	 * @param checkout
	 *            End date of the period
	 * @return list of all occupied rooms. Empty list is returned if no rooms
	 *         are occupied
	 */
	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {

		List<Room> res = new ArrayList<Room>();

		for (int i = 0; i < this.database.size(); i++) {

			System.out.println(this.database.get(i).toString());

			if (database.get(i).getCheckInDate().isAfter(checkin)
					|| database.get(i).getCheckOutDate().isBefore(checkout)) {

				System.out.println(this.database.get(i).toString());
				// res.add(database.get(i).getRoom());

			}
		}

		return res;
	}

	/**
	 * Gets a list of all rooms NOT reserved at some point between the supplied
	 * check-in and check-out dates. Note that only rooms that are not reserved
	 * for any period between the given dates are returned
	 * 
	 * @param checkin
	 *            Start date of period
	 * @param checkout
	 *            End date of the period
	 * @return list of all unoccupied rooms. Empty list is returned if no rooms
	 *         are unoccupied
	 */
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {

		List<Room> res = new ArrayList<Room>();

		for (int i = 0; i < this.database.size(); i++) {
			if (!(this.database.get(i).getCheckInDate().isBefore(checkin))
					&& !(this.database.get(i).getCheckOutDate().isAfter(checkout))) {

				res.add(this.database.get(i).getRoom());

			}
		}

		return res;
	}

	/**
	 * Gets a list of all rooms of given room type NOT reserved between the
	 * supplied check-in and check-out dates. Note that only rooms that are not
	 * reserved for any period between the given dates are returned
	 * 
	 * @param checkin
	 *            Start date of period
	 * @param checkout
	 *            End date of the period
	 * @param roomType
	 *            The type of room requested
	 * @return list of all unoccupied rooms. Empty list is returned if no rooms
	 *         are unoccupied
	 */
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {

		List<Room> rooms = new ArrayList<Room>();

		for (int i = 0; i < this.database.size(); i++) {
			if (!(this.database.get(i).getCheckInDate().isBefore(checkin))
					&& !(this.database.get(i).getCheckOutDate().isAfter(checkout))
					&& (this.database.get(i).getRoom().getRoomType().equals(roomType))) {

				if (this.database.get(i).getRoom().equals(this.allRooms.get(i)))
					rooms.add(this.database.get(i).getRoom());

			}
		}

		return rooms;
	}

	/**
	 * Removes all reservations with the checkout date prior to the current
	 * date.
	 */
	@Override
	public void clearAllPast() {
		
		for (int i = 0; i < this.database.size(); i++) {
			if (this.database.get(i).getCheckOutDate().isBefore(LocalDate.now())) {
				this.database.remove(i);
			}
		}

	}

	@Override
	public String toString() {
		int num = database.size();
		StringBuilder str = new StringBuilder("Number of reservations in database: " + num);
		for (Reservation r : this.database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}
}
