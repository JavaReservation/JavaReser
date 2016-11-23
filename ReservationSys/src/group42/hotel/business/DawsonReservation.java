package group42.hotel.business;

/**
 * @auther Werner Castanaza
 * Date: 28/09/2016
 * The dawson reservation class will creat an instance of a reservation, taking in as input a 
 * customer object a room  and a chin date and check out date. 
 */
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

public class DawsonReservation implements Reservation {

	private final Customer aCustomer;
	private final Room aRoom;
	private LocalDate checkIn;
	private LocalDate checkOut;

	/**
	 * the constructer will set the dates and customer and room objects. The
	 * assignment will go through validation methods to determine if the data
	 * inputed meets standereds.
	 * 
	 * @param aCustomer
	 * @param aRoom
	 * @param inYear
	 * @param inMonth
	 * @param inDay
	 * @param outYear
	 * @param outMonth
	 * @param outDay
	 * @throws IllegalArgumentException
	 *             if the data inputed is valid.
	 */
	public DawsonReservation(Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay) {

		this.aCustomer = aCustomer;
		this.aRoom = aRoom;

		try {
			checkIn = LocalDate.of(inYear, inMonth, inDay);
			checkOut = LocalDate.of(outYear, outMonth, outDay);

			if (!checkIn.isBefore(checkOut))
				throw new DateTimeException("");
		} catch (DateTimeException dte) {
			throw new IllegalArgumentException("Date given is invalid");
		}
	}

	/**
	 * Will return a deep copy of the customer object
	 * 
	 * @return customer object
	 */
	public Customer getCustomer() {
		Customer c;
		try {
			c = new DawsonCustomer(aCustomer.getName().getfirstName(), aCustomer.getName().getlastName(),
					aCustomer.getEmail().getAddress());
		} catch (NullPointerException nc) {

			throw new IllegalArgumentException("The customer is not entered properlly  === null cought");
		}

		catch (Exception ab) {
			throw new IllegalArgumentException("The customer is not entered properlly");
		}

		return c;

	}

	/**
	 * Will return a deep copy of the Room object
	 * 
	 * @return room object
	 */
	public Room getRoom() {

		return new DawsonRoom(aRoom.getRoomNumber(), aRoom.getRoomType());

	}

	public LocalDate getCheckInDate() {

		return this.checkIn;

	}

	public LocalDate getCheckOutDate() {

		return this.checkOut;

	}

	public int getNumberDays() {

		int days = Period.between(this.checkIn, this.checkOut).getDays();
		return days;

	}

	/**
	 * This method will check to see if the check in date and check out date
	 * over lap or intersect
	 * 
	 * @return true/false boolean.
	 */
	public boolean overlap(Reservation o) {

		if (this.equals(o))
			return true;

		if (this.checkIn.isBefore(o.getCheckOutDate()) && this.checkOut.isAfter(o.getCheckInDate()))
			return true;

		return false;
	}

	/**
	 * This method overrides the default toString method to display all the
	 * relevant information relating to customer
	 * 
	 * @return String
	 */
	public String toString() {

		return aCustomer.getEmail().getAddress() + "*" + this.checkIn.getYear() + "*" + this.checkIn.getMonthValue()
				+ "*" + this.checkIn.getDayOfMonth() + "*" + this.checkOut.getYear() + "*"
				+ this.checkOut.getMonthValue() + "*" + this.checkOut.getDayOfMonth() + "*" + aRoom.getRoomNumber();

	}

	/**
	 * This method overrides the compareTo method to enable comparison between
	 * customers, rooms, checkInDates and reservation
	 * 
	 * @return int
	 */
	@Override
	public int compareTo(Reservation o) {

		if (this.aRoom.getRoomNumber() == o.getRoom().getRoomNumber())
			return checkIn.compareTo(o.getCheckInDate());
		return this.aRoom.compareTo(o.getRoom());

	}

	/**
	 * This is a hash code override, because if the equals method is overridden
	 * then the hash code must also be overridden
	 * 
	 * @return result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aCustomer == null) ? 0 : aCustomer.hashCode());
		result = prime * result + ((aRoom == null) ? 0 : aRoom.hashCode());
		result = prime * result + ((checkIn == null) ? 0 : checkIn.hashCode());
		result = prime * result + ((checkOut == null) ? 0 : checkOut.hashCode());
		return result;
	}

	/**
	 * This method overrides the equals method so it can now compare all aspects
	 * of a reservation.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof DawsonReservation))
			return false;

		DawsonReservation data = (DawsonReservation) obj;

		if (!(this.getCustomer().equals(data.getCustomer())))
			return false;

		if (!(this.getRoom().equals(data.getRoom())))
			return false;

		if (!(this.checkIn.isEqual(data.checkIn)))
			return false;

		if (this.checkOut.isEqual(data.checkOut))
			return true;

		return false;
	}

}
