package group42.hotel.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group42.hotel.data.ReservationListDB;
import group42.hotel.data.SequentialTextFileList;

public class DawsonHotelAllocationPolicyTest {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {

		LocalDate checkin = LocalDate.of(2016, 9, 25);
		LocalDate checkout = LocalDate.of(2016, 9, 27);
		RoomType roomType = RoomType.PENTHOUSE;
		System.out.println("----------Testing----------");
		System.out.println("----------case 1----------\ndate being used for testng " + checkin + " to " + checkout
				+ " and with room type: " + roomType);
		TestingDawsonHotelAllocationPolicy(checkin, checkout, roomType);

		checkin = LocalDate.of(2009, 5, 13);
		checkout = LocalDate.of(2009, 5, 14);
		roomType = RoomType.PENTHOUSE;
		System.out.println("----------case 2----------\ndate being used for testng " + checkin + " to " + checkout
				+ " and with room type: " + roomType);
		TestingDawsonHotelAllocationPolicy(checkin, checkout, roomType);

		checkin = LocalDate.of(2016, 9, 25);
		checkout = LocalDate.of(2016, 9, 27);
		roomType = RoomType.NORMAL;
		System.out.println("----------case 3----------\ndate being used for testng " + checkin + " to " + checkout
				+ " and with room type: " + roomType);
		TestingDawsonHotelAllocationPolicy(checkin, checkout, roomType);
		
		checkin = LocalDate.of(2017, 6, 25);
		checkout = LocalDate.of(2017, 7, 4);
		roomType = RoomType.SUITE;
		System.out.println("----------case 3----------\ndate being used for testng " + checkin + " to " + checkout
				+ " and with room type: " + roomType);
		TestingDawsonHotelAllocationPolicy(checkin, checkout, roomType);

	}

	public static void TestingDawsonHotelAllocationPolicy(LocalDate checkin, LocalDate checkout, RoomType roomType)
			throws FileNotFoundException, ClassNotFoundException, IOException {

		String rooms = "ReservationSys\\datafiles\\sorted\\rooms.txt";
		String cus = "ReservationSys\\datafiles\\database\\customers.txt";
		String res = "ReservationSys\\datafiles\\database\\reservations.txt";

		ListPersistenceObject lpo = new SequentialTextFileList(rooms, cus, res);
		ReservationListDB resDB = new ReservationListDB(lpo);

		// 2009*4*12*2009*4*15*

		DawsonHotelAllocationPolicy a = new DawsonHotelAllocationPolicy(resDB);
		Optional<Room> freeRoom = null;

		try {

			freeRoom = a.freeRoom(checkin, checkout, roomType);

			if (!freeRoom.equals(Optional.empty())) {
				System.out.println("      " + freeRoom.get().toString());
			}

		} catch (IndexOutOfBoundsException ai) {
			System.out.println(ai.getMessage());
		}

	}

}
