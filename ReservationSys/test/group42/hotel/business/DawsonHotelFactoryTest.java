/**
 * 
 */
package group42.hotel.business;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.creditcard.CreditCard;
import group42.hotel.business.DawsonHotelFactory;

/**
 * @author Werner 
 *
 */
public class DawsonHotelFactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Credit card
		System.out.println("----------- Testing Credit card method from DawsonHotelFactory ----------- \n");
		testCreditCard("Case 1 --- Valid data of a visa card entered --- ", "VISA", "4716540135986737", true);
		testCreditCard("Case 2 --- Invalid data entered the credit card type is invalid -- ", "blah","4716540135986737", false);
		testCreditCard("Case 3 -- Invalid number entered -- ", "VISA", "42", false);
		testCreditCard("Case 4 --- empty card type and number entered -- ", "", null, false);
		testCreditCard("Case 5 --- Random charecters entered for card number  -- ", "VISA", "#@@$$!561#@!#", false);
		System.out.println("-------------------------------------------------------------------------------------- \n");

		// customer
		System.out.println("----------- Testing the Customer method from DawsonHotelFactory ----------- \n");
		testCustomer("Case 1 --- Valid data entered --- ", "PEPE", "Escovar", "pepe_love@gmail.com", true);
		testCustomer("Case 2 --- Invalid data a number in the last name -- ", "Dota", "Fabi0", "bleh@ge.ca", false);
		testCustomer("Case 3 -- Invalid empty name -- ", "", "", "gg_ez@lol.ca", false);
		testCustomer("Case 4 --- empty email -- ", "James", "Bonda", null, false);
		testCustomer("Case 5 --- Random charecters entered for fname  -- ", "Joey", "Bada$$", "jb@nyu.us", false);
		System.out.println("-------------------------------------------------------------------------------------- \n");

		// room

		// testRoom (String testCase, int roomNum, String roomType, boolean
		// validation)
		System.out.println("----------- Testing the Room method from DawsonHotelFactory ----------- \n");
		testRoom("Case 1 --- Valid data entered --- ", 101, "normal", true);
		testRoom("Case 2 --- Invalid room number -- ", 42, "normal", false);
		testRoom("Case 3 -- Invalid empty room type -- ", 101, null, false);
		testRoom("Case 4 --- empty email -- ", 0, "", false);
		testRoom("Case 5 --- valid data entered -- ", 801, "PENTHOUSE", true);
		System.out.println("-------------------------------------------------------------------------------------- \n");
//reservation
		//constants that will be used from the past test cases for simplicity sake
		Room room = DawsonHotelFactory.DAWSON.getRoomInstance(101, "normal");
		Customer customer = DawsonHotelFactory.DAWSON.getCustomerInstance( "PEPE", "Escovar", "pepe_love@gmail.com");
		
		System.out.println("----------- Testing the Reservation method from DawsonHotelFactory ----------- \n");
		
		getReservationInstanceTest("Case 1 --- Valid data entered --- ", customer , room,2016,9,27,2016,9,30, true);
		getReservationInstanceTest("Case 2 --- Invalid room number -- ", customer , room,2020,9,27,2016,9,30, false);
		getReservationInstanceTest("Case 3 -- Invalid month is way bigger thn 12  -- ", customer , room,2016,55,27,2016,9,30, false);
		getReservationInstanceTest("Case 4 --- invalid outYear is way to big -- ", customer , room,2016,9,27,2015555556,9,30, false);
		getReservationInstanceTest("Case 5 --- invalid the check in date is after check out -- ", customer , room,2016,9,27,2016,9,26, false);
		System.out.println("-------------------------------------------------------------------------------------- \n");
		
//reservation copy method
		//this variable will be used to test the method for simplicity sake
		Reservation r = DawsonHotelFactory.DAWSON.getReservationInstance(customer , room,2016,9,27,2016,9,30);
		System.out.println("----------- Testing the Reservation Copy method from DawsonHotelFactory ----------- \n");
		testgetReservationInstance("Case 1 --- Valid data entered --- ",r, true);
		System.out.println("-------------------------------------------------------------------------------------- \n");
		

	}

	private static void testCreditCard(String testCase, String cardType, String cardNum, boolean validation) {

		try {
			CreditCard c = DawsonHotelFactory.DAWSON.getCard(cardType, cardNum);

			if (validation) {
				System.out.println(testCase + "  valid ==>  " + c.toString());
			}
		} catch (IllegalArgumentException ai) {
			System.out.println(testCase + "  Error something went wrong == > " + ai.getMessage());
		} catch (Exception a) {

			System.out.println(testCase + "  Error something went wrong ===> " + a.getMessage());

		}
		System.out.println("END OF THAT TEST CASE\n");

	}// end method

	private static void testCustomer(String testCase, String fName, String lName, String email, boolean validation) {

		try {
			Customer cus = DawsonHotelFactory.DAWSON.getCustomerInstance(fName, lName, email);

			if (validation) {
				System.out.println("valid ==>  " + cus.toString());
			}
		} catch (IllegalArgumentException ai) {
			System.out.println("Error something went wrong == > " + ai.getMessage());
		} catch (Exception a) {

			System.out.println("Error something went wrong ===> " + a.getMessage());

		}
		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}

	private static void testRoom(String testCase, int roomNum, String roomType, boolean validation) {

		try {
			Room r = DawsonHotelFactory.DAWSON.getRoomInstance(roomNum, roomType);

			if (validation) {
				System.out.println("valid ==>  " + r.toString());
			}
		} catch (IllegalArgumentException ai) {
			System.out.println("Error something went wrong == > " + ai.getMessage());
		} catch (Exception a) {

			System.out.println("Error something went wrong ===> " + a.getMessage());

		}
		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}
	
	
	public static void getReservationInstanceTest(String testCase, Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay,
												int outYear, int outMonth, int outDay, boolean validation){
		
		
		try {
			Reservation r = DawsonHotelFactory.DAWSON.getReservationInstance(aCustomer,aRoom,inYear,inMonth,inDay,outYear,outMonth,outDay);

			if (validation) {
				System.out.println("valid ==>  " + r.toString());
			}
		} catch (IllegalArgumentException ai) {
			System.out.println("Error something went wrong == > " + ai.getMessage());
		} catch (Exception a) {

			System.out.println("Error something went wrong ===> " + a.getMessage());

		}
		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");
		
		
	}
	
	public static void testgetReservationInstance (String testCase,Reservation copy, boolean validation){
		
		try {
			Reservation r = DawsonHotelFactory.DAWSON.getReservationInstance(copy);

			if (validation) {
				System.out.println("valid ==>  " + r.toString());
			}
		} catch (IllegalArgumentException ai) {
			System.out.println("Error something went wrong == > " + ai.getMessage());
		} catch (Exception a) {

			System.out.println("Error something went wrong ===> " + a.getMessage());

		}
		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");
		
		
	}

}
