package dw317.lib.creditcard;
/**
 * The Visa class extends The AbstractCreditCard class so
 * it receives all the basic credit card functions and variables
 * 
 * @author Keylen, Werner
 * @version 06/10/16
 *
 */
public class Visa extends AbstractCreditCard {

	private static final long serialVersionUID = 42031768871L;
	protected String number;

	/**
	 * This constructor takes a string number which is a credit 
	 * card number, giving it a cardtype and validating the 
	 * credit card number it receives. 
	 * 
	 * @param number
	 */
	public Visa(String number) {

		super(CardType.VISA, validateNumber(number));
		this.number = validateNumber(number);

	}

	/**
	 * This method validates the credit card number it receives 
	 * as a parameter, making sure it meets the specifications
	 * of an Visa card type.
	 * 
	 * @author Keylen, Werner
	 * @throws IllegalArgumentException if the card number is not 16 characters long
	 * @throws IllegalArgumentException if the card number does not start with a 4 
	 * @param number
	 * @return number
	 */
	private static String validateNumber(String number) {

		validateLuhnAlgorithm(number);
		if (number.length() != 16) {
			throw new IllegalArgumentException("The visa card doesn't meet standereds it must be 16 digits long");

		}
		if (Integer.parseInt(number.substring(0, 1)) != 4) {

			throw new IllegalArgumentException("The visa card number entered does not start with a 4 pleas try again");

		}
		return number;
	}
}
