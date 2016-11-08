package dw317.hotel.business.interfaces;

import java.time.LocalDate;

/**
 * This interface extends from Dawson reservation and 
 * gets room and customer interface which refer to 
 * Dawson customer and Dawson room.
 * 
 * @author RoanWC,Keylen
 * @version 06/10/16
 */
public interface Reservation extends Comparable<Reservation> {
	
	/**
	 * Gets the customer 
	 * 
	 */
	Customer getCustomer();

	/**
	 * Gets the room. 
	 * 
	 */
	Room getRoom();

	/**
	 * Gets the CheckInDate 
	 * 
	 */
	LocalDate getCheckInDate();

	/**
	 * Gets the checkOutDate 
	 * 
	 */
	LocalDate getCheckOutDate();

	/**
	 * Gets the numberDays 
	 * 
	 */
	int getNumberDays();

	boolean overlap(Reservation other);

}
