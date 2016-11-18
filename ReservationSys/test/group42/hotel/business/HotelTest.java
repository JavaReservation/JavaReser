package group42.hotel.business;

import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group42.hotel.data.ReservationListDB;
import group42.hotel.data.SequentialTextFileList;

public class HotelTest {

	public static void main(String[] args) {
		testCancelReservation();
		testCloseHotel();
		testCreateReservation();
		testFindCustomer();
		testFindReservations();
		testRegisterCustomer();
		testUpdateCreditCard();
		
	}
	
	public void testCancelReservation()
	{
		String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
		String cus = "ReservationSys\\datafiles\\database\\customers.txt";
		String res = "ReservationSys\\datafiles\\database\\reservations.txt";

		ListPersistenceObject a = new SequentialTextFileList(rooms, cus, res);
		ReservationListDB b = new ReservationListDB(a);
		
		
		Hotel.cancelReservation(reservation);
	}

}
