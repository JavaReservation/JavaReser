package dw317.hotel.data;
/**
 * 
 * @author RoanWC
 * this exception will be thrown if there is no customer.
 *
 */

public class NonExistingCustomerException extends Exception {
	
	
	private static final long serialVersionUID = 42031768871L;
	//this will be the default contructor
	public NonExistingCustomerException(){
		super("This Name does not exist");
	}
	//this constructor will allow the programer to enter a custom message to display 
	public NonExistingCustomerException(String message){
		super(message);
	}

}
