package dw317.hotel.data;

public class DuplicateReservationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateReservationException(){
		super("This Room has been booked already!");
	}
	
	public DuplicateReservationException(String message){ 
		super(message);
	}
	 
	

}
