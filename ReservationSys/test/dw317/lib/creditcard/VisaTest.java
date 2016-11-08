package dw317.lib.creditcard;
/**
 * @author Werner
 *
 */
public class VisaTest {

	public static void main(String[] args) {
		
		System.out.println("Testing the Visa.java clas \n ");
	

		testMasterCard("Case 1  - Valid data entered \n ", "4716540135986737", true);
		testMasterCard("Case 2  - empty string entered \n ", "", false);
		testMasterCard("Case 3  - null string entered \n ", null, false);
		testMasterCard("Case 4  - random string entered \n ", "42", false);
		testMasterCard("Case 5  - valid string entered \n ", "4111111111111111", true);
		testMasterCard("Case 6  - data entered does not meet the luhn algorithim standereds \n ", "4211111111111111", false);
		testMasterCard("Case 7  - data entered contains letters \n ", "37763SoS4667132121", false);
		testMasterCard("Case 8  - data entered has blank spaces \n ", "   411111111111111      ", false);
		
		



	}

	private static void testMasterCard(String testCase, String number, boolean expectValid) {

		System.out.println(testCase );

		try {
			Visa cardNum = new Visa(number);
			if (expectValid){
				
			System.out.println("valid card number ==>  " + cardNum.toString());
			}

		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
		System.out.println("\t-----END OF TEST " + testCase.toUpperCase().substring(0,8) + "-----\n");
	}// end method

}// end of main
