package group42.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

public class ReservationByCustSorted implements Comparator<Reservation> {

	/**
	 * compares the reservation based on Customers
	 */

	public static int compareByCust(Reservation r1, Reservation r2) {

		if (r1.equals(r2))
			return 0;
		if (!r1.getCustomer().equals(r2.getCustomer()))
			return r1.getCustomer().compareTo(r2.getCustomer());
		// default
		return r1.compareTo(r2);
	}

	public int compare(Reservation r1, Reservation r2) {
		return 1;

	}
}
