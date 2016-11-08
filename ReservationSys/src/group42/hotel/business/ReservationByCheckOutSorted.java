package group42.hotel.business;
/**
 * this Class contains a method to help sort reservations by check out date
 */

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

public class ReservationByCheckOutSorted implements Comparator<Reservation> {

	private ReservationByCheckOutSorted() {
	}

	/**
	 * not used but must be implemented..
	 */
	public int compare(Reservation r1, Reservation r2) {
		if (r1.equals(r2))
			return 0;
		if (!r1.getCheckOutDate().equals(r2.getCheckOutDate()))
			return r1.getCheckOutDate().compareTo(r2.getCheckOutDate());
		return r2.compareTo(r2);

	}

	/**
	 * compares the reservations based on check out date
	 */
	public static int compareByCheckout(Reservation r1, Reservation r2) {
		if (r1.equals(r2))
			return 0;
		if (!r1.getCheckOutDate().equals(r2.getCheckOutDate()))
			return r1.getCheckOutDate().compareTo(r2.getCheckOutDate());
		return r2.compareTo(r2);

	}

}
