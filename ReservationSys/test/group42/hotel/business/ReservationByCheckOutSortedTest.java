package group42.hotel.business;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

public class ReservationByCheckOutSortedTest{

	public static void main(String[] args){
		Customer c1 = new DawsonCustomer("Roan","Chamberlain","Roan@Gmail.com");
		Customer c2 = new DawsonCustomer("Pepe", "Smith","Pepe@Gmail.com");
		Room room1 = new DawsonRoom(401,RoomType.NORMAL);
		Reservation r1 = new DawsonReservation(c1,room1,2016,9,25,2016,9,27);
		Reservation r2 = new DawsonReservation(c2,room1,2016,9,26,2016,9,28);
		
		
		testCompareByCheckout("same reservation",r1,r1,0);
		testCompareByCheckout("different Reservation",r1,r2,-1);
		testCompareByCheckout("different Reservation",r2,r1,1);
	}
	private static void testCompareByCheckout(String testCase, Reservation r1, Reservation r2, int expected){
		System.out.println(testCase);
		int result = ReservationByCheckOutSorted.compareByCheckout(r1,r2);
		if (result == expected)
			System.out.println("worked");
		if (result != expected)
			System.out.println("didnt work");
		System.out.println(result);
	}
	

}//end
