package dw317.lib;
/**
 * 
 * The test name class will test the the name class to ensure the name meets the standers given
 * @author Werner Castanaza
 * Date : 27/09/2016
 *
 */

public class NameTest {

	public static void main(String[] args) {
		
		
		System.out.println("-----Testing the if names are valid or NAAHH  -----");
	
		test("Test case 1 -- " , "Pepe", "Escovar", true);
		test("Test case 2 -- " , null,null, false);
		test("Test case 3 -- " , "pe.2pe@gmail.co..m","EscovarrS", false);
		test("Test case 4 -- " , "Tom%Ford", "Smith", false);
		test("Test case 5 -- " , "", "Escovar", false);
		test("Test case 6 -- " , "el","lovo", true);
		test("Test case 7 -- " , "@sad.sa", "Jammes", false);
		test("Test case 8 -- " , "      a      ","Meow",  true);
		test("Test case 9 -- " , "jos&e","Woof", false);
	
		
		
		
		equalsNameTesting ();
		
		
	}
	
	public static void equalsNameTesting (){
		
		
		System.out.println("-----Testing the overriden equals method -----");

		System.out.println("Case 1 two names they do not match ");
		Name a = new Name ("Bob","Smith");
		Name b = new Name ("Bob","Jones");
		
		if (a.equals(b)){
			System.out.println("they do match ====>  " + a.toString() + "\t is the same as \t" + b.toString());
		}else{
			System.out.println("they dont match ====>  " + a.toString() + "\t not the same as \t" + b.toString());
		}
		
		System.out.println("Case 2  two names they do match ");
		
		Name c = new Name ("Juan","Jordan");
		Name d = new Name ("Juan","Jordan");
		
		if (c.equals(d)){
			System.out.println("they do match ====>  " + c.toString() + "\t is the same as \t" + d.toString());
		}else{
			System.out.println("they dont match ====>  " + c.toString() + "\t not the same as \t" + d.toString());
		}
		
			
	}
	
	public static void test (String caseNum, String fName,String lName, boolean validation){
		
		
		System.out.println(caseNum + "\n");

		try {
			
			Name n = new Name(fName, lName);
			if (validation){
			System.out.println("valid name ==>  " + n.toString());
			}

		} catch (Exception iae) {
			System.out.println(iae.getMessage());
		}
		System.out.println("\n"+"END OF THAT TEST CASE "+"----------------------------------------------\n");
	}
}

