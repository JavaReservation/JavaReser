package group42.hotel.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.data.ReservationListDB;
import group42.hotel.data.SequentialTextFileList;

public class DawsonHotelAllocationPolicyTest {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {

		// These three String variables are used to locate the text files with
		// the information about our customers
		String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
		String cus = "ReservationSys\\datafiles\\database\\customers.txt";
		String res = "ReservationSys\\datafiles\\database\\reservations.txt";

		ListPersistenceObject a = new SequentialTextFileList(rooms, cus, res);
		ReservationListDB b = new ReservationListDB(a);

		DawsonHotelAllocationPolicy meh = new DawsonHotelAllocationPolicy(b);

		// 2016, 1, 1, 2016, 1, 5

		LocalDate checkin = null;

		checkin = checkin.of(2016, 1, 1);
		LocalDate checkout = null;

		checkout = checkout.of(2016, 1, 5);

		System.out.println(checkin);

		meh.freeRoom(checkin, checkout, )

	}

}
