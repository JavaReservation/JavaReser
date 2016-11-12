package group42.hotel.business;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

public class DawsonReservationTest {

	public static void main (String [] args){
		
		//constants that will be used from the past test cases for simplicity sake
		
		
		test("Case 1 --- Valid data entered --- ",202, "normal","PEPE", "Escovar", "pepe_love@gmail.com",2016,9,27,2016,9,30, true);
		test("Case 2 --- Invalid name entered --- ",202, "normal","PE$%PE", "Escovar", "pepe_love@gmail.com",2016,9,27,2016,9,30, false);
		test("Case 3 --- Invalid date entered --- ",202, "normal","PEPE", "Escovar", "pepe_love@gmail.com",2016,9,27,2016,42,30, false);
		test("Case 4 --- invalid email entered --- ",202, "normal","PEPE", "Escovar", "",2016,9,27,2016,9,30, false);
		test("Case 5 --- Invalid room entered  --- ",42, "normal","PEPE", "Escovar", "pepe_love@gmail.com",2016,9,27,2016,9,30, false);
		test("Case 6 --- Invalid data empty last name entered --- ",202, "normal","PEPE", "", "pepe_love@gmail.com",2016,9,27,2016,9,30, false);
		test("Case 7 --- Valid data entered --- ",202, "normal","Kson", "Kdot", "keylen69@gmail.com",2016,9,27,2016,9,30, true);
		
}
	public static void test (String testCase, int roomNum, String roomType, String fName, String lName, String email, int inYear,
			int inMonth, int inDay, int outYear, int outMonth, int outDay, boolean validation) {

		try {
			
			Room room = DawsonHotelFactory.DAWSON.getRoomInstance(roomNum, roomType);
			Customer customer = DawsonHotelFactory.DAWSON.getCustomerInstance( fName, lName, email);
			
			Reservation r = new DawsonReservation (customer, room, inYear, inMonth, inDay,outYear, outMonth, outDay);
		
			if (validation) {
				System.out.println("valid ==>  " + r.toString());
			}
		} catch (NullPointerException ai) {
			System.out.println("Error something went wrong == > " + ai.getMessage());
		} catch (Exception a) {

			System.out.println("Error something went wrong ===> " + a.getMessage());

		}
		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}

}