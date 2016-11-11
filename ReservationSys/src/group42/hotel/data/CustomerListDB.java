/**
 * 
 */
package group42.hotel.data;

import java.util.List;

import dw317.clinic.data.interfaces.CustomerDAO;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

/**
 * 
 * @author Keylen
 * @version 
 */
public class CustomerListDB implements CustomerDAO {
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
	
	
	public CustomerListDB (ListPersistenceObject listPersistenceObject)
	{ 
		this.listPersistenceObject = listPersistenceObject;
	}
	
	public CustomerListDB (ListPersistenceObject listPersistenceObject,
	HotelFactory factory)
	{
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
	}
	
	@Override
	public void add(Customer cust)
	{
		
	}
	
	//@Override
	//public void disconnect
	{
		
	}
	
	@Override
	public void getCustomer(Email email)
	{
		
	}
	
	@Override
	public void update(Email email, CreditCard card)
	{
		
	}
	
	/** 
	 * Override the toString method to return the
	 * number of customers with the customer 
	 * toString appended on to it.
	 * @return the string builded string
	 */
	@Override
	public String toString() {
		String after = listPersistenceObject.getCustomerDatabase().toString();
		StringBuilder before = new StringBuilder ("Number of customers in database: "+
		after.split("*").length);
		//If the statement below doesn't work, use this: before.append(after);
		return before.append(after).toString();
	}
}

