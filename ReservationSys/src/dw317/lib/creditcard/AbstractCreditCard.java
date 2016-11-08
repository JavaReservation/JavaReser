
package dw317.lib.creditcard;

/**
 * The AbstractCreditCard abstract class implements CreditCard and serves as the
 * basic functionality for all card classes.
 * 
 * @author Keylen & Werner
 * @version 06/10/16
 *
 */
public abstract class AbstractCreditCard implements CreditCard {
	protected static final long serialVersionUID = 42031768871L;
	protected final CardType cardType; // {read only}
	protected final String number; // {read only}

	/**
	 * This constructor takes cardtype and the card number as parameters, having
	 * them validated by the validateCardType method, and the
	 * validateluhnAlogorithm method.
	 * 
	 * @author Keylen, Werner
	 * @param cardType
	 * @param number
	 */
	public AbstractCreditCard(CardType cardType, String number) {

		try {
			this.cardType = validateCardType(cardType);
			this.number = validateLuhnAlgorithm(number);

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * This method validates the card type, throwing an IllegalArgumentException
	 * if the card type is null
	 * 
	 * @author Keylen, Werner
	 * @param c
	 * @return c
	 * @throws IllegalArgumentException
	 *             if the card type is null
	 */
	private CardType validateCardType(CardType c) {

		if (c == null) {
			throw new IllegalArgumentException("card type is null pleas input a valid type ");
		}

		return c;
	}

	/**
	 * This method overrides the hashcode, we must override the hashcode if we
	 * override the equals method.
	 * 
	 * @author Keylen, Werner
	 * @return result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	/**
	 * This method overrides the equals method to give capabilities to be able
	 * to compare credit cards.
	 *
	 * @author Keylen, Werner
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (obj instanceof CreditCard) {
			CreditCard card = (CreditCard) obj;

			if (!this.cardType.equals(card.getType()))
				return false;

			if (!this.number.equals(card.getNumber()))
				return false;

			return true;

		}

		return true;

	}

	/**
	 * This method gets the cartype
	 * 
	 * @return the cardType
	 */
	public CardType getCardType() {
		return this.cardType;
	}

	/**
	 * This method gets the card number
	 * 
	 * @return the number
	 */
	public String getNumber() {

		return this.number;
	}

	/**
	 * this method overrides the toString method
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return cardType + "*" + number;
	}

	/**
	 * This method gets the CardType
	 * 
	 * @return cardType
	 */
	public CardType getType() {

		return this.cardType;
	}

	/**
	 * This method validates the card number to make sure it is valid according
	 * to the Luhn algorithm
	 * 
	 * @author Keylen, Werner
	 * @param number
	 * @return number The credit card number validated
	 * @throws IllegalArgumentException
	 */
	public static String validateLuhnAlgorithm(String number) {

		if (number.indexOf(" ") != -1)
			number = number.replaceAll(" ", "");

		if (number.isEmpty() || number == null || number.matches(".*[a-zA-Z].*") || number.length() > 18)
			throw new IllegalArgumentException("The card is invalid ");

		int temp = 0;

		int total = 0;

		for (int i = number.length() - 1; i >= 0; i--) {

			temp = Character.getNumericValue(number.charAt(i));

			if (((number.length() - 1) - i) % 2 == 1)
				temp *= 2;

			if (temp > 9)
				temp -= 9;

			total += temp;

		} // end of loop

		if (total % 10 != 0)
			throw new IllegalArgumentException("Invalid card!" + temp + "\n" + number.toString());
		else
			return number;
	}
}