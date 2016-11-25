package group42.hotel.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group42.hotel.data.ReservationListDB;
import group42.hotel.data.SequentialTextFileList;

public class DawsonHotelAllocationPolicyTest {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {

		String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
		String cus = "ReservationSys\\datafiles\\database\\customers.txt";
		String res = "ReservationSys\\datafiles\\database\\reservations.txt";

		ListPersistenceObject lpo = new SequentialTextFileList(rooms, cus, res);
		ReservationListDB resDB = new ReservationListDB(lpo);
		
		LocalDate checkin = LocalDate.of(2016, 9, 25);
		LocalDate checkout = LocalDate.of(2016, 9, 27);
	
		DawsonHotelAllocationPolicy a =new DawsonHotelAllocationPolicy(resDB);
		
		Room freeRoom = a.freeRoom(checkin, checkout, RoomType.NORMAL);
		
		System.out.println("      " +freeRoom);
	
	
	
	
	
	
	}

}
