package dw317.lib.creditcard;
/**
 * @author Werner
 *
 */
public class AmexTest {

	public static void main(String[] args) {
		
		System.out.println("Testing the Amex.java clas \n ");

		testMasterCard("Case 1  - Valid data entered \n ", "374603469549637", true);
		
		
		/**
		testMasterCard("Case 2  - empty string entered \n ", "", false);
		testMasterCard("Case 3  - null string entered \n ", null, false);
		testMasterCard("Case 4  - random string entered \n ", "423456789112345", false);
		testMasterCard("Case 5  - valid master card entered \n ", "5502345200878347", false);
		testMasterCard("Case 6  - data entered does not meet the luhn algorithim standereds \n ", "343456789112345", false);
		testMasterCard("Case 7  - data entered contains letters \n ", "55a2345200878348", false);
		testMasterCard("Case 8  - data is a valid visa card with spaces \n ", "  411 111 11111 11111", false);
*/
	}
	private static void testMasterCard(String testCase, String number, boolean expectValid) {

		System.out.println(testCase + "\n");

		try {
			
			// what is the difference between having AbstractCreditCard in front rather then Amex
			AbstractCreditCard cardNum = new Amex(number);
			//cardNum.Steer("MEOOOW!!!!");
			
			if (expectValid){
			System.out.println("valid card number ==>  " + cardNum.toString());
			}

		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
		System.out.println("END OF THAT TEST CASE " + testCase.substring(0,8) + "\n");
	}// end method

}// end of main
