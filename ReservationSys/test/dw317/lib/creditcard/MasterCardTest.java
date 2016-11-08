package dw317.lib.creditcard;
/**
 * @author Werner
 *
 */
public class MasterCardTest {

	public static void main(String[] args) {

		System.out.println("Testing the MasterCard.java class \n ");

		//cases 1 and 5 are valid master card numbers but the luhn algorithm i made says they are not :\ 
		
		
		testMasterCard("Case 1  - Valid data entered \n ", "5546477798743407", true);
		testMasterCard("Case 2  - empty string entered \n ", "", false);
		testMasterCard("Case 3  - null string entered \n ", null, false);
		testMasterCard("Case 4  - random string entered \n ", "42", false);
		testMasterCard("Case 5  - valid string entered \n ", "5502345200878347", true);
		testMasterCard("Case 6  - data entered does not meet the luhn algorithim standereds \n ", "5502345200878348", false);
		testMasterCard("Case 7  - data entered contains letters \n ", "55a2345200878348", false);
		testMasterCard("Case 8  - data is a valid visa cards \n ", "   4111111111111111", false);
	}

	private static void testMasterCard(String testCase, String number, boolean expectValid) {

		System.out.println(testCase);

		try {
			MasterCard cardNum = new MasterCard(number);
			if (expectValid){
			System.out.println("valid card number ==>  " + cardNum.toString());
			}

		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
		System.out.println("\t-----END OF TEST " + testCase.toUpperCase().substring(0,8) + "-----\n");
	}// end method

}// end of main
