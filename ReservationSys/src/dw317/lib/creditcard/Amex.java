package dw317.lib.creditcard;

/**
 * The Amex class extends The AbstractCreditCard class so
 * it receives all the basic credit card functions and variables
 * 
 * @author Keylen, Werner
 * @version 06/10/16
 */
public class Amex extends AbstractCreditCard {

	private static final long serialVersionUID = 42031768871L;
	protected String number;

	/**
	 * This constructor takes a string number which is a credit 
	 * card number, giving it a cardtype and validating the 
	 * credit card number it receives. 
	 * 
	 * @param number
	 */
	public Amex(String number) {

		super(CardType.AMEX, validateNumber(number));
		this.number = validateNumber(number);
	}

	/**
	 * This method validates the credit card number it receives 
	 * as a parameter, making sure it meets the specifications
	 * of an AMEX card type.
	 * 
	 * @author Keylen, Werner
	 * @throws IllegalArgumentException if the card number is not 15 characters long
	 * @throws IllegalArgumentException if the card number does not start with a 34 or 37
	 * @param number
	 * @return number
	 */
	private static String validateNumber(String number) {
		
		validateLuhnAlgorithm(number);
		
		//check if number meets AMEX card standards
		if (number.length() != 15) {

			throw new IllegalArgumentException("invalid Amex card number it must be 15 charecters long");
		} else if (Integer.parseInt(number.substring(0, 2)) != 34 && Integer.parseInt(number.substring(0, 2)) != 37) {

			throw new IllegalArgumentException("invalid Amex card number it must start with 34 or 37");

		}

		return number;
	}
	/**

	@Override
	public void Steer(String dgrs) {
		System.out.println(dgrs);
		
		public abstract void Steer (String dgrs);
		
	}
	*/

}
