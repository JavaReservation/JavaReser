/**
 * 
 */
package dw317.lib;


/**
 * @author pepe
 *
 */
public class AddressTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		testTheThreeParameterConstructor();
		
		//Address pe = new Address ("514", "antonio", "MONTREAL");
		
		//System.out.println("\t" + pe.toString());
		
		testSetCivicNumber();
		
		Address addressRef = new Address();
		
		System.out.println( "  testing no perametter cunstructer, the getCivicNumber length     =======>     " + addressRef.getCivicNumber().length());
		
	}
	

	private static void testTheThreeParameterConstructor() {
		
		System.out.println("\nTesting the three parameter constructor.");
		
		testTheThreeParameterConstructor ("Case 1 - Valid data (3040 Sherbrooke Westmount)", "3040", "Sherbrooke", "Westmount", true);
	
		//Step 2 added code page 5/14
			
		testTheThreeParameterConstructor("Case 2 - Invalid data – empty civicNumber ( Sherbrooke Westmount)","","Sherbrooke","Westmount", false);
		
		//adding five more test cases pg6
		
		
		testTheThreeParameterConstructor ("Case 3 - Invalid data - civcNumber is all spaces" , "    ", "Sherbrooke" , "Westmount", false);
		
		testTheThreeParameterConstructor ("Case 4 - Invalid data - empty streetName", "3040" , "" , "Westmount" , false);
		
		testTheThreeParameterConstructor ("Case 5 - Invalid data - empty city", "3040" , "Sherbrooke" , "" , false);
		
		testTheThreeParameterConstructor ("Case 6 - Invalid data - spaces in streetName", "3040" , "   " , "Westmount" , false);
		
		testTheThreeParameterConstructor ("Case 7 - Invalid data - spaces in  city", "3040" , "Sherbrooke" , "   " , false);
		
		//if the arguments are null we still want IllegalArgumentException to be thrown, since a null argument is still a illegal argument 
		testTheThreeParameterConstructor("Case 8 - Invalid data – null civicNumber (null Sherbrooke Westmount)", null,"Sherbrooke","Westmount",false);
		
		//testing the rest of the perams with nulls
		testTheThreeParameterConstructor("Case 9 - Invalid data – null streetName ", "3040", null ,"Saint Leo",false);
		testTheThreeParameterConstructor("Case 10 - Invalid data – null city ", "3040","Sherbrooke",null,false);
		
		//test getCivcNumber method
		testGetCivicNumber();
	}





  private static void testTheThreeParameterConstructor(String testCase, String civicNumber, String streetName, String city, boolean expectValid) {

	System.out.println("   " + testCase);

	try {
		Address theAddress = new Address(civicNumber, streetName, city);
		System.out.print("\tThe Address instance was created: " + theAddress);
		
		if (!expectValid)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
	}
	catch (IllegalArgumentException iae)	{
		System.out.print("\t"+ iae.getMessage());
		if (expectValid)
			System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
	}
	catch (Exception e) {
		System.out.print("\t UNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " "  + e.getMessage() + " ==== FAILED TEST ====");
		if (expectValid)
			System.out.print(" Expected Valid.");
	}

	System.out.println("\n");
}
  //two methods added page 7
  private static void testGetCivicNumber()
	{
		System.out.println("\nTesting the getCivicNumber method.");
		
		testGetCivicNumber("Case 1: 3040 without leading/trailing spaces", "3040","3040");
		testGetCivicNumber("Case 2: 3040 with leading/trailing spaces", "    3040    ","3040");
	}
	
	private static void testGetCivicNumber(String testCase,  String civicNumber, String expectedCivicNumber)
	{
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address (civicNumber, "Sherbrooke","Westmount");
		
		System.out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
			
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}

	//page 8
	private static void testSetCivicNumber() {
		
		System.out.println("\nTesting the setCivicNumber method.");
		
		testSetCivicNumber("Case 1: Valid - 2086 without leading/trailing spaces", "2086","2086",true);
		
		testSetCivicNumber("Case 2: Invalid null civic number", null,"",false);
		
		testSetCivicNumber("Case 3: Invalid null expectedCivicNumber", "2086",null,false);
		
		testSetCivicNumber("Case 4: Invalid null civic number and expectedCivicNumber", null ,null,false);
		
		
	}
	
	private static void testSetCivicNumber(String testCase,  String civicNumber, String expectedCivicNumber,boolean expectValid){
		
		System.out.println("   " + testCase);
		
		Address theAddress = 
				new Address ("3040", "Sherbrooke","Westmount");
		try {
			theAddress.setCivicNumber(civicNumber);
			
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}


	
	
}
