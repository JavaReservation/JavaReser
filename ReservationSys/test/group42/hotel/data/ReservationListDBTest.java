package group42.hotel.data;

import dw317.clinic.data.interfaces.ReservationDAO;
import dw317.hotel.business.interfaces.DawsonHotelFactory;
import dw317.hotel.data.interfaces.ListPersistenceObject;

public class ReservationListDBTest {

	public static void main(String[] args) {

		
		ReservationDAO a = new ReservationListDB(null);
		
		System.out.println(a.toString());
		
		
	}

}
