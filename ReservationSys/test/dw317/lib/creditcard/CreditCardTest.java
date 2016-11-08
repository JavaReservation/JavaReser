/**
this class main function is to test the CreditCardTest.java
 * 
 */
package dw317.lib.creditcard;

/**
 * @author Werner
 *
 */
public class CreditCardTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CreditCard.CardType type1 = CreditCard.CardType.MASTERCARD;
		CreditCard.CardType type2 = CreditCard.CardType.VISA;
		CreditCard.CardType type3 = CreditCard.CardType.AMEX;
		System.out.println("Testing creditcard.java !!! \n");
		System.out.print("this should be a mastercard: " + type1 + "\n" + "this should be a visa: " + type2 + 
							"\n" + "this should be an amex: " +  type3);
		
		
	}

}
