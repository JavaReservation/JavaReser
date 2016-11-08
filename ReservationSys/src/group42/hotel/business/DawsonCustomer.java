package group42.hotel.business;

/**
 * @author Werenr Castanaza
 * Date: 30/09/2016
 * The DawsonCustomer class makes a customer object composed of a name email and an optional credit card
 */
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;

public class DawsonCustomer implements Customer {

	public Name name;
	public Email email;
	public CreditCard card;
	private static final long serialVersionUID = 42031768871L;

	/**
	 * Three param constructor that takes in three strings and assigns them to
	 * first name last name and email respectively.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public DawsonCustomer(String firstName, String lastName, String email) {

		this.name = new Name(firstName, lastName);
		this.email = new Email(email);
		this.card = null;

	}

	/**
	 * Returns a deep copy of the the name object
	 * 
	 * @return name
	 */
	public Name getName() {
		Name returnName = new Name(name.getfirstName(), name.getlastName());
		return returnName;
	}

	/**
	 * returns the email object
	 * 
	 * @return Email
	 */
	public Email getEmail() {
		return this.email;
	}

	/**
	 * returns the optional credit card object
	 * 
	 * @return card
	 */
	public Optional<CreditCard> getCreditCard() {
		return Optional.ofNullable(card);
	}

	/**
	 * The overridden equals method checks if two customer objects are equal if
	 * their email's are the same
	 * 
	 * @param customer
	 * @return true/false
	 */
	public boolean equals(DawsonCustomer customer) {
		if (this == customer) 
			return true;
		
		if (customer == null) 
			return false;
		
		if (customer instanceof Customer){
		Customer cust = (Customer) customer;
		
		if (!this.getEmail().getAddress().equalsIgnoreCase((cust.getEmail().getAddress())))
			return false;

		return true;
		
		}
		
		return true;
	}

	/**
	 * Overridden toString returns a string representation of the customer object
	 * 
	 * @return customer.toString
	 */
	public String toString() {

		if (this.card == null)
			return this.email + "*" + this.name.getfirstName() + "*" + this.name.getlastName() + "*" + "" + "*" + "";

		return this.email + "*" + this.name.getfirstName() + "*" + this.name.getlastName() + "*" + this.card.getType()
				+ "*" + this.card.getNumber();

	}

	/**
	 * overriden hashcode will re assign the hashcode of two objects given the
	 * standereds set by the overriden equals method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * overriden compareTo will compare two objects if their emails are equal
	 */
	@Override
	public int compareTo(Customer o) {

		if (this == o) {
			return 0;
		}
		if (this.email.getHost().equals(o.getEmail().getHost())) {
			return this.email.getUserId().compareToIgnoreCase(o.getEmail().getUserId());
		}
		return this.email.getHost().compareToIgnoreCase(o.getEmail().getHost());

	}

	/**
	 * sets the credit card optional
	 */
	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		this.card = card.orElse(null);
	}
}