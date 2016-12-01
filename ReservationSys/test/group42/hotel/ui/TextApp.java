package group42.hotel.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.interfaces.*;
import group42.hotel.business.*;
import group42.hotel.data.*;
import javafx.beans.Observable;

public class TextApp {
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customerDb = new CustomerListDB(new ObjectSerializedList("datafiles/database/rooms.ser",
				"datafiles/database/customers.ser", "datafiles/database/reservations.ser"));
		ReservationDAO reservationDb = new ReservationListDB(new ObjectSerializedList("datafiles/database/rooms.ser",
				"datafiles/database/customers.ser", "datafiles/database/reservations.ser"));

		Hotel model = new Hotel(factory, customerDb, reservationDb);
		TextView view = new TextView((Observable) model);
		TextController controller = new TextController((HotelManager) model);
		controller.run();
	}
}
