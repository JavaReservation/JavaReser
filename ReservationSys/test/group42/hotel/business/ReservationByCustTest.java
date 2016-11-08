package group42.hotel.business;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

public class ReservationByCustTest {

	public static void main(String []args) {
		Customer c1 = new DawsonCustomer("Roan","Chamberlain","Roan@gmail.com");
		Customer c2 = new DawsonCustomer("Pepe", "Smith","pepe@gmail.com");
		

		
		Room room1 = new DawsonRoom(401,RoomType.NORMAL);
		Reservation r1 = new DawsonReservation(c1,room1,2016,9,25,2016,9,27);
		Reservation r2 = new DawsonReservation(c2,room1,2016,9,25,2016,9,27);
		
		
		testCompareByCustomer("Same Reservation",r1,r1,0);
		testCompareByCustomer("1st greater then 2nd",r2,r1,-2);
		testCompareByCustomer("2nd Greater then 1st",r1,r2,2);
		
	}
	
	private static void testCompareByCustomer(String testCase, Reservation r1, Reservation r2,int expected){
		try{
		System.out.println(testCase);
		int result = ReservationByCustSorted.compareByCust(r1,r2);
		if (result == expected)
			System.out.println("worked");
		else
			System.out.println("didnt Work");
		System.out.println(result);
	   }catch(Exception e){
		   System.out.println(e.getMessage());
	   }
		
	
		
	}
	
}//end


