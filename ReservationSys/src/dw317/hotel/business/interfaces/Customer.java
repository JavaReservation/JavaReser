/**
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.util.Optional;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;

/**
 * The interface Customer extends from Comparable<Customer>
 * and serializable.
 * 
 * @author RoanWC,Keylen
 */
public interface Customer extends Comparable<Customer>, Serializable {

	/**
	 * Gets the name class
	 * 
	 */
	Name getName();
	
	/**
	 * Gets the email class
	 * 
	 */
	Email getEmail();
	
	/**
	 * This abstract method must be overridden by DawsonCustomer
	 * 
	 */
	Optional<CreditCard> getCreditCard();
	
	/**
	 * This sets CreditCard in the DawsonCustomer
	 * 
	 */
	void setCreditCard(Optional<CreditCard> card);
}


