/**
 * 
 */
package group42.hotel.data;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import group42.hotel.business.DawsonHotelFactory;

/**
 * 
 * @author Keylen
 * @version
 */
public class CustomerListDB implements CustomerDAO {
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	public CustomerListDB(ListPersistenceObject listPersistenceObject) {
		this.listPersistenceObject = listPersistenceObject;
	}

	public CustomerListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
		this.database = listPersistenceObject.getCustomerDatabase();
	}

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

	@Override
	public Customer getCustomer(Email email) 
	{
		
		return customer;
	}

	@Override
	public void update(Email email, CreditCard card) {

	}

	/**
	 * Override the toString method to return the number of customers with the
	 * customer toString appended on to it.
	 * 
	 * @return the string builded string
	 */

	@Override
	public String toString() {
		String after = listPersistenceObject.getCustomerDatabase().toString();
		StringBuilder before = new StringBuilder("Number of customers in database: " + after.split("*").length);
		// If the statement below doesn't work, use this: before.append(after);
		return before.append(after).toString();
	}

	@Override
	public void add(Customer cust) throws DuplicateCustomerException {
		// check if the customer already exists
		for (int i = 0; i < this.database.size(); i++) {

			if (this.listPersistenceObject.getCustomerDatabase().get(i).overlap(cust)) {
				throw new DuplicateCustomerException();

			}
		}

		// binary search and add the cust paramater into the sorted
		// reservation file
		int index = CustomerListDB.search(this.database, 0, this.database.size(), cust);

		if (index != -1) {

			this.database.add(index, cust);
		}

	}
	
	//public static <E>

	/**private static int search(List<Customer> database3, int first, int last, Customer cust) {
		int result = 0; // to keep the compiler happy.

		if (first > last)
			result = -1;
		else {
			int mid = (first + last) / 2;

			if (cust.compareTo(database3.get(mid)) == 0)
				result = mid;

			else if (cust.compareTo(database3.get(mid)) < 0)
				result = search(database3, first, mid - 1, cust);

			else if (cust.compareTo(database3.get(mid)) > 0)
				result = search(database3, mid + 1, last, cust);
		}

		return result;

	} */
}
