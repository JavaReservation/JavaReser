package group42.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.business.interfaces.RoomType;
import dw317.hotel.data.interfaces.DuplicateReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.NonExistingReservationException;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.hotel.business.interfaces.Reservation;




public class ReservationListDB<Reservation> implements ReservationDAO {

	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	// cunstructers
	// my random default constructor
	public ReservationListDB() {
		this.listPersistenceObject = null;
		this.factory = null;
	}

	@SuppressWarnings("unchecked")
	public ReservationListDB(ListPersistenceObject listPersistenceObject) {

		this.listPersistenceObject = listPersistenceObject;
		this.allRooms = listPersistenceObject.getRoomDatabase();
		this.factory = null;
		this.database = (List<Reservation>) listPersistenceObject.getReservationDatabase();
	}

	public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {

		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;

	}


	@Override
	public void add(Reservation reserv) throws DuplicateReservationException {
		// check if there is an overlap with the reservation we are trying to
		// add to the database
		for (int i = 0; i < this.database.size(); i++) {

			if (this.listPersistenceObject.getReservationDatabase().get(i).overlap(reserv)) {
				throw new DuplicateReservationExcption("The reservation you are trying to add,"
						+ " overlaps with an existing reservation in our database.");

			}
		}

		// binary search and add the reserv paramater into the sorted
		// reservation file
		int index = ReservationListDB.search(this.database,
				0, this.listPersistenceObject.getReservationDatabase().size(), reserv);
		
		if (index != -1){
			
			this.listPersistenceObject.getReservationDatabase().add(index, reserv);
		}
		
		
	}

	private static int search(List<?> database2, int first, int last, Reservation reserv) {
		int result = 0; //to keep the compiler happy.
		
 		if (first > last)
 			result = -1;
 		else
 		{
 			int mid = (first + last)/2;
 			
 		if (reserv.compareTo(database2.get(mid)) == 0)
 			result = mid;
 		
 		else if (reserv.compareTo(database2.get(mid)) < 0)
 			result = search( database2, first, mid - 1, reserv);
 		
 		else if (reserv.compareTo(database2.get(mid)) > 0)
 			result = search(database2, mid + 1, last, reserv);
}
 			return result;
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<dw317.hotel.business.interfaces.Reservation> getReservations(Customer cust) {

		ReservationListDB.search();
		
		
		return null;
	}

	@Override
	public void cancel(dw317.hotel.business.interfaces.Reservation reserv) throws NonExistingReservationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
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
	public String toString (){
		return this.listPersistenceObject.getReservationDatabase().toString();
	}


	

}