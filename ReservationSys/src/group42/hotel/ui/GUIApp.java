package group42.hotel.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.business.DawsonHotelFactory;
import group42.hotel.business.*;
import group42.hotel.data.*;

public class GUIApp {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customerDb = new CustomerListDB(new ObjectSerializedList("datafiles/database/rooms.ser",
				"datafiles/database/customers.ser", "datafiles/database/reservations.ser"));
		ReservationDAO reservationDb = new ReservationListDB(new ObjectSerializedList("datafiles/database/rooms.ser",
				"datafiles/database/customers.ser", "datafiles/database/reservations.ser"));

		Hotel model = new Hotel(factory, customerDb, reservationDb);
		GUIViewController app = new GUIViewController(model);
	}
}
