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

public class ReservationListDB implements ReservationDAO {

	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	public ReservationListDB(ListPersistenceObject listPersistenceObject) {

		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		this.database = listPersistenceObject.getReservationDatabase();
	}

	public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {

		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		this.database = listPersistenceObject.getReservationDatabase();
	}

	@Override
	public void add(Reservation reserv) throws DuplicateReservationException {
		// check if there is an overlap with the reservation we are trying to
		// add to the database
		for (int i = 0; i < this.database.size(); i++) {

			if (this.listPersistenceObject.getReservationDatabase().get(i).overlap(reserv)) {
				throw new DuplicateReservationException("The reservation you are trying to add,"
						+ " overlaps with an existing reservation in our database.");

			}
		}

		// binary search and add the reserv paramater into the sorted
		// reservation file
		int index = ReservationListDB.search(this.database, 0, this.database.size(), reserv);

		if (index != -1) {

			this.database.add(index, reserv);
		}

	}

	private static int search(List<Reservation> database2, int first, int last, Reservation reserv) {
		int result = 0; // to keep the compiler happy.

		if (first > last)
			result = -1;
		else {
			int mid = (first + last) / 2;

			if (reserv.compareTo(database2.get(mid)) == 0)
				result = mid;

			else if (reserv.compareTo(database2.get(mid)) < 0)
				result = search(database2, first, mid - 1, reserv);

			else if (reserv.compareTo(database2.get(mid)) > 0)
				result = search(database2, mid + 1, last, reserv);
		}

		return result;

	}

	@Override
	public void disconnect() throws IOException {

		try {
			this.listPersistenceObject.saveReservationDatabase(this.database);

		} catch (IOException a) {
			throw new IOException("Could not disconect pleas fix >.<");
		}
		this.database = null;
	}

	@Override
	public List<Reservation> getReservations(Customer cust) {
		List<Reservation> res = new ArrayList<Reservation>();

		for (int i = 0; i < this.database.size(); i++) {

			if (this.database.get(i).equals(cust)) {
				res.add(this.database.get(i));
			}
		}

		if (res.size() > 0) {
			return res;
		}
		return res;
	}

	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		int found = 0;

		for (int i = 0; i < this.database.size(); i++) {
			if (this.database.get(i).equals(reserv)) {
				this.database.remove(i);
				found++;
			}
		}
		if (found > 0) {
			throw new NonExistingReservationException("the Reaservation is not in our database");
		}

	}

	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {

		List<Room> res = new ArrayList<Room>();

		for (int i = 0; i < this.database.size(); i++) {
			if (database.get(i).getCheckInDate().isAfter(checkin)
					&& database.get(i).getCheckOutDate().isBefore(checkout)) {

				res.add(database.get(i).getRoom());

			}
		}

		return res;
	}

	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		
		List<Room> res = new ArrayList<Room>();

		for (int i = 0; i < this.database.size(); i++) {
			if (database.get(i).getCheckInDate().isAfter(checkin)
					&& database.get(i).getCheckOutDate().isBefore(checkout)) {

				res.add(database.get(i).getRoom());

			}
		}

		return res;
	}

	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAllPast() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return this.listPersistenceObject.getReservationDatabase().toString();
	}
}
