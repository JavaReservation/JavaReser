package group42.hotel.business;

import group42.hotel.business.DawsonCustomer;
import dw317.hotel.data.interfaces.ReservationDAO;
import group42.hotel.business.DawsonReservation;
import group42.hotel.business.DawsonRoom;
import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.creditcard.CreditCard;
import dw317.hotel.business.RoomType;

/**
 * This Enum gets all the instances and classes of the abstract method
 * 
 * @author Teacher
 */
public enum DawsonHotelFactory implements HotelFactory {
	DAWSON;

	@Override
	public Customer getCustomerInstance(String firstName, String lastName, String email) {
		return new DawsonCustomer(firstName, lastName, email);
	}

	@Override
	public CreditCard getCard(String cardtype, String number) {
		return CreditCard.getInstance(CreditCard.CardType.valueOf(cardtype.toUpperCase()), number);
	}

	@Override
	public Room getRoomInstance(int roomNumber, String roomtype) {
		return new DawsonRoom(roomNumber, RoomType.valueOf(roomtype.toUpperCase()));
	}

	@Override
	public Reservation getReservationInstance(Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay,
			int outYear, int outMonth, int outDay) {
		return new DawsonReservation(aCustomer, aRoom, inYear, inMonth, inDay, outYear, outMonth, outDay);
	}

	@Override
	public Reservation getReservationInstance(Reservation toCopy) {
		return new DawsonReservation(toCopy.getCustomer(), toCopy.getRoom(), toCopy.getCheckInDate().getYear(),
				toCopy.getCheckInDate().getMonthValue(), toCopy.getCheckInDate().getDayOfMonth(),
				toCopy.getCheckOutDate().getYear(), toCopy.getCheckOutDate().getMonthValue(),
				toCopy.getCheckOutDate().getDayOfMonth());
	}

	@Override
	public AllocationPolicy getAllocationPolicy(ReservationDAO reservations) {
	return null;//return new DawsonHotelAllocationPolicy(reservations);	I had to comment this out to test my stuff
	}

}
