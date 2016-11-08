package dw317.lib;
/**
 * 
 * This test class will validate the email class to ensure it is a valid email acording to the specifations given
 * @author Werner Castanaza
 * Date: 27/09/2016
 *
 */

public class EmailTest {

	public static void main(String[] args) {
		
		
		System.out.println("------Testing the emails------");
		
		test("Test case 1 -- " , "Pepe_el_lovo@gmail.com", true);
		test("Test case 2 -- " , null, false);
		test("Test case 3 -- " , "pepe@gmail.co..m.", false);
		test("Test case 4 -- " , "jose@gmail.co#m", false);
		test("Test case 5 -- " , "Pepe_el_lovo@gmail.com", true);
		test("Test case 6 -- " , "ba5hhh@sad.sa", true);
		test("Test case 7 -- " , "      a      ", false);
		test("Test case 8 -- " , "jose@gmail.co#m", false);

		System.out.println("------\nTesting the emails equal method ------");
		equalsEmailTesting ();
	}
	
	public static void test (String caseNum, String email, boolean validation){
		
		
		System.out.println(caseNum + "\n");

		try {
			
			Email e = new Email(email);
			if (validation){
			System.out.println("valid email ==>  " + e.toString());
			}

		} catch (Exception iae) {
			System.out.println(iae.getMessage());
		}
		System.out.println("END OF THAT TEST CASE "+"\n");
	}
	
	public static void equalsEmailTesting (){
		
		
		System.out.println("-----Testing the overriden equals method -----");

		System.out.println("Case 1 two names they do not match ");
		Email a = new Email ("Meow_cat@meow.com");
		Email b = new Email ("dog_Woof@k9.cas");
		
		if (a.equals(b)){
			System.out.println("Something went wrong cause they are not ment to match ");
		}else{
			System.out.println("they dont match ====>  " + a.toString() + "\t not the same as \t" + b.toString());
		}
		
		System.out.println("Case 2  two names they do match ");
		
		Email c = new Email ("dmx@gmail.ca");
		Email d = new Email ("dmx@gmail.ca");
		
		if (c.equals(d)){
			System.out.println("they do match ====>  " + c.toString() + "\t is the same as \t" + d.toString());
		}else{
			System.out.println("Something went wrong they are the same email ");
		}
		
			
	}
	
}
