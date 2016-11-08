package group42.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.creditcard.Amex;

public class DawsonCustomerTest {

	public static void main(String[] args) {

	
		test("Case 1 --- Valid data entered --- ","pepe","lovo","pepe_lovo@gmail.com", true);
		
		
		
		

		
		test("Case 1 --- Valid data entered --- ","pepe","lovo","pepe_lovo@gmail.com", true);
		test("Case 2 --- Invalid data entered --- ","pe%pe","lovo","pepe_lovo@gmail.com", false);
		test("Case 3 --- Invalid data entered --- ","pepe","lov$$o","pepe_lovo@gmail.com", false);
		test("Case 4 --- Valid data entered --- ","Keylen","Escovarr","kEsc0varr@gm--ail.com", true);
		test("Case 5 --- Valid data entered --- ","","lovo","meow@homail.com", false);
		
		
}
	public static void test (String testCase,String fName,String lName, String email, boolean validation) {

		try {
			
			Customer c = new DawsonCustomer (fName, lName,email);
			
			c.setCreditCard(Optional.of(new Amex("37460346954955637"))); 
			// <======= that sht is optional ... go take a look at lecture 3.2 (nas slides fak him ) 
			// slide 13/19  .... is jayas slides but he put his name on that shizz.
		
			if (validation) {
				System.out.println("valid ==>  " + c.toString());
			}
		} catch (NullPointerException ai) {
			System.out.println("Error something went wrong == > " + ai.getMessage());
		} catch (Exception a) {

			System.out.println("Error something went wrong ===> " + a.getMessage());

		}
		System.out.println("END OF THAT TEST CASE " + testCase.substring(0, 8) + "\n");

	}
}
