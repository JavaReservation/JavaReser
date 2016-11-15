package group42.hotel.data;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import group42.hotel.business.DawsonHotelFactory;

/**
 * This class represents the customer database as an internal list.
 * Implementing the CustomerDAO interface.
 * @author Keylen
 * @version 14/11/2016
 */
public class CustomerListDB implements CustomerDAO {
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	public CustomerListDB(ListPersistenceObject listPersistenceObject) {
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
		this.database = listPersistenceObject.getCustomerDatabase();
	}

	public CustomerListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		this.database = listPersistenceObject.getCustomerDatabase();
	}
	/**
	 *This method makes the database transactions persistent.
	 *Saves the database to disk then assigns null to the database field.
	 *
	 * @throws IOException A problem with saving a file
	 * @author Keylen
	 * @version 14/11/2016
	 */
	@Override
	public void disconnect() throws IOException 
	{
		try {
			this.listPersistenceObject.saveCustomerDatabase(database);
		} catch (IOException e) {
			throw new IOException("Saving to customer to database error");
		}
		
		this.database = null;
	}

	/**
	 * This method is responsible for returning a reference 
	 * to the customer with the given email address.
	 * 
	 * @throws NonExistingCustomerException If a customer does not exist
	 * @return database.get(index) The reference of a customer
	 * @param email
	 * @author Keylen
	 */
	@Override
	public Customer getCustomer(Email email) 
			throws NonExistingCustomerException
	{
		int index = search(database, email);
		if(index < 0)
			throw new NonExistingCustomerException();
				return database.get(index);
	}

	
	/**
	 * This method will update a customer in the database if it exists,
	 * with a credit card.
	 * 
	 * @param email
	 * @param card
	 * @throws NonExistingCustomerException If the customer does no exist
	 * @author Keylen
	 */
	@Override
	public void update(Email email, CreditCard card) 
			throws NonExistingCustomerException {
		int index = search(database, email);
		if(index < 0)
			throw new NonExistingCustomerException();
		database.get(index).setCreditCard(Optional.ofNullable(card));
	}

	/**
	 * Override the toString method to return the number of customers with the
	 * customer toString appended on to it.
	 * 
	 * @return the string builded string
	 */

	@Override
	public String toString() {
		int num = database.size();
		StringBuilder str = new StringBuilder("\nNumber of customers in database: " + num);
		for (Customer r : database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}

	/**
	 * This method is responsible for adding a customer to the database.
	 * 
	 * @throws DuplicateCustomerException
	 * @param cust A customer
	 * @author Keylen, Roan
	 */
	@Override
	public void add(Customer cust) throws DuplicateCustomerException {
		int index = search(database, cust.getEmail());
		if( index > 0)
			throw new DuplicateCustomerException();

		index = -(index);
		
		database.add(index, factory.getCustomerInstance
				(cust.getName().getfirstName(),cust.getName().getlastName(),cust.getEmail().getAddress()));
		
		database.get(index).setCreditCard(cust.getCreditCard());
	}
	
	/**
	 * This is a binary search
	 * 
	 * @param list
	 * @param key
	 * @return the index of the key inside of the list
	 */
	private static int search(List<Customer> list, Email key) {
		int result;
		int low = 0;
		int middle;
		int high = list.size() - 1;
		while (low <= high) {
			middle = (low + high)/2;
			result = list.get(middle).getEmail().compareTo(key);
			if (result == 0)
				return middle;
			if(result < 0){
				low = middle + 1;
			}else{
				high = middle -1;
			}
		}
		return -(high + 1);

	} 
}
