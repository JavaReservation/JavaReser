package dw317.lib.creditcard;
/**
 * The MasterCard class extends the AbstractCreditCard class so
 * it receives all the basic credit card functions and variables
 * 
 * @author Keylen, Werner
 * @version 06/10/16
 *
 */
public class MasterCard extends AbstractCreditCard {
	
	private static final long serialVersionUID = 42031768871L;
	protected String number;
	
	/**
	 * This constructor takes a string number which is a credit 
	 * card number, giving it a cardtype and validating the 
	 * credit card number it receives. 
	 * 
	 * @param number
	 */
	public MasterCard(String number) {

		super(CardType.MASTERCARD, validateNumber(number));
		this.number = validateNumber(number);

	}

	/**
	 * This method validates the credit card number it receives 
	 * as a parameter, making sure it meets the specifications
	 * of an MasterCard type.
	 * 
	 * @author Keylen, Werner
	 * @throws IllegalArgumentException if the card number is not 16 characters long
	 * @throws IllegalArgumentException if the card number does not start with a number
	 * 		between the range of 51 and 55. 
	 * @param number
	 * @return number
	 */
	private static String validateNumber(String number) {
		
		validateLuhnAlgorithm(number);
		if (number.length() != 16) {
			throw new IllegalArgumentException("The master card doesn't meet standereds it must be 16 digits long");

		}
		if (Integer.parseInt(number.substring(0, 2)) != 51 && Integer.parseInt(number.substring(0, 2)) != 52
				&& Integer.parseInt(number.substring(0, 2)) != 53 && Integer.parseInt(number.substring(0, 2)) != 54
				&& Integer.parseInt(number.substring(0, 2)) != 55) {

			throw new IllegalArgumentException(
					"The master card number doesn't meet standereds, must start with 51 to 55 range");
		}
		return number;

	}
}
