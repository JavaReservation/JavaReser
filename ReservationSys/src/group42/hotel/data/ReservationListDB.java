package group42.hotel.data;

import java.io.FileNotFoundException;
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
import group42.hotel.business.DawsonReservation;
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
	 * Constructor that takes in the interface ListPersistenceObject as a
	 * parameter
	 * 
	 * @param listPersistenceObject
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public ReservationListDB(ListPersistenceObject listPersistenceObject)
			throws FileNotFoundException, ClassNotFoundException, IOException {

		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		this.database = listPersistenceObject.getReservationDatabase();
	}

	/**
	 * Overloaded constructor that takes in the ListPersistenceObject interface
	 * and the HotelFactory interface
	 * 
	 * @param listPersistenceObject
	 * @param factory
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory)
			throws FileNotFoundException, ClassNotFoundException, IOException {

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

		Reservation res = factory.getReservationInstance(reserv);
		int index = search(this.database, res);
		index = -(index);

		if (index > 0) {
			this.database.add(index, reserv);
			System.out.println("Reservation added ==>" + this.database.get(index).toString());

		} else {
			// System.out.println(" binary search ==> " + index);
			throw new DuplicateReservationException("The reservation is already in our system");
		}

	}

	private static int search(List<? extends Reservation> reservationList, Reservation res) {
		int start = 0;
		int end = reservationList.size() - 1;
		int mid = (start + end) / 2;
		int result;

		while (start <= end) {

			mid = (start + end) / 2;
			result = reservationList.get(mid).compareTo(res);

			if (result == 0) {
				return mid;
			} else if (result < 0) {
				start = mid + 1;
			} else
				end = mid - 1;
		}

		// System.out.println(" binary search ==> " + (-(start + 1)));
		return -(start + 1);

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
				this.database.remove(i);
				found++;
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

		if (checkin.isAfter(checkout)) {
			throw new IllegalArgumentException("The check in date you have entered is befor the check out date");
		}

		List<Room> res = new ArrayList<Room>();

		for (int i = 0; i < this.database.size(); i++) {

			if (this.database.get(i).getCheckInDate().isBefore(checkout)
					&& this.database.get(i).getCheckOutDate().isAfter(checkin)) {
				//System.out.println(this.database.get(i));
				res.add(database.get(i).getRoom());

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

		List<Room> rooms = new ArrayList<Room>();

		List<Room> reservedRooms = this.getReservedRooms(checkin, checkout);
		int skip = 1;
		for (int i = 0; i < this.allRooms.size(); i++) {
			for (int j = 0; j < reservedRooms.size(); j++) {

				if (this.allRooms.get(i).compareTo(reservedRooms.get(j)) == 0) {
					skip = -1;
					break;
				} else {
					skip = 1;
				}
			} // end of j

			if (skip != -1) {
				rooms.add(this.allRooms.get(i));
				//System.out.println(this.allRooms.get(i));
			}

		} // end of i

		return rooms;

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

		List<Room> rooms = this.getFreeRooms(checkin, checkout);
		for (int i = 0; i < rooms.size(); i++) {
			if (!rooms.get(i).getRoomType().equals(roomType)) {
				// System.out.println(rooms.get(i));
				rooms.remove(i);

				i--;
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
				i--;
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
