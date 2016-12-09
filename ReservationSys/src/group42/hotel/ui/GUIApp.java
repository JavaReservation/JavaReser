package group42.hotel.ui;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.business.DawsonHotelFactory;
import group42.hotel.business.*;
import group42.hotel.data.*;


public class GUIApp {

	public static void main(String[] args) {
		HotelFactory factory = 
				DawsonHotelFactory.DAWSON;
		CustomerDAO customerDb =
				new CustomerListDB(new ObjectSerializedList
						("ReservationSys\\datafiles\\database\\customers.ser" ,
							"ReservationSys\\datafiles\\database\\reservations.ser",
							"ReservationSys\\datafiles\\database\\rooms.ser"));
		ReservationDAO reservationDb = 
				new ReservationListDB(new ObjectSerializedList
						("ReservationSys\\datafiles\\database\\customers.ser" ,
							"ReservationSys\\datafiles\\database\\reservations.ser",
							"ReservationSys\\datafiles\\database\\rooms.ser"));
	
		Hotel model = new Hotel(factory, customerDb, reservationDb);
		GUIViewController app = new GUIViewController(model);
		
		
	}
}

