package dw317.hotel.data;

public class NonExistingReservationException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NonExistingReservationException(){
		super("The Reservation does not exist");
	}
	//this constructor will allow the programer to enter a custom message.
	public NonExistingReservationException(String message){
		super(message);
	}

}//End of class
