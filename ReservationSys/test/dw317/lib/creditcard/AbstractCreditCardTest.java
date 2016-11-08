package dw317.lib.creditcard;

import dw317.lib.creditcard.CreditCard.CardType;

public class AbstractCreditCardTest {

	public static void main(String[] args) {
		
		
		//Test cases for the abstract credit card class
		System.out.println("--------- Testing AbstractCreditCard ----------");
		testAbstract ("Case 1 -- valid input of an amex card \t",CardType.AMEX, "374603469549637", true);
		testAbstract ("Case 2 -- invalid, card number empty  \t ",CardType.AMEX, "", false);
		testAbstract ("Case 3 -- invalid input card type is visa but not a valid number for visa \t",CardType.VISA , "374603469549637", false);
		testAbstract ("Case 4 -- invalid card type entered \t",null, "374603469549637", false);
		testAbstract ("Case 5 -- invalid the card number is to long \t",CardType.VISA, "123456789101112131415", false);
		testAbstract ("Case 6 -- Valid master card card entered \t",CardType.MASTERCARD, "5546477798743407", true);
		testAbstract ("Case 7 -- invalide white space entered in card number \t",CardType.AMEX, "      ", false);
		testAbstract ("Case 8 -- invalid unknow chareters entered in card number \t",CardType.VISA, "374603####469549637", false);
		
		//void method that will test the overridden equals method in the abstract credit card class 
		testingAbstractEquals ();
	}
	public static void testAbstract (String caseNum ,CardType type, String number, boolean valid){
		
		try{
			AbstractCreditCard c = (AbstractCreditCard) CreditCard.getInstance (type , number);
			if (valid){
				System.out.println(caseNum + "\n"+ c);
			}
			
		}catch(NullPointerException n){
			System.out.println(caseNum + "\n" +"\n null value entered for card type  ");
		}
		
		catch (Exception ai){
			
			System.out.println(caseNum + "\n"+ ai.getMessage());
		}
		
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testingAbstractEquals (){
		
		
		System.out.println("-----Testing the overriden equals method -----");

		System.out.println("Case 1 two credit caards they do not match ");
		AbstractCreditCard a = (AbstractCreditCard) CreditCard.getInstance(CardType.AMEX, "374603469549637");
		AbstractCreditCard b = (AbstractCreditCard) CreditCard.getInstance(CardType.MASTERCARD, "5546477798743407");
		
		if (a.equals(b)){
			System.out.println("they do match ====>  " + a.toString() + "\t is the same as \t" + b.toString());
		}else{
			System.out.println("they dont match ====>  " + a.toString() + "\t not the same as \t" + b.toString());
		}
		
		System.out.println("Case 2  two credit caards they do match ");
		
		AbstractCreditCard c = (AbstractCreditCard) CreditCard.getInstance(CardType.AMEX, "374603469549637");
		AbstractCreditCard d = (AbstractCreditCard) CreditCard.getInstance(CardType.AMEX, "374603469549637");
		
		if (c.equals(d)){
			System.out.println("they do match ====>  " + c.toString() + "\t is the same as \t" + d.toString());
		}else{
			System.out.println("they dont match ====>  " + c.toString() + "\t not the same as \t" + d.toString());
		}
		
			
	}

}
