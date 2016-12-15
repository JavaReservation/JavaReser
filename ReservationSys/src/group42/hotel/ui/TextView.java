/**
 * 
 */
package group42.hotel.ui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.creditcard.CreditCard;
import group42.hotel.business.DawsonReservation;
import group42.hotel.business.Hotel;
import group42.hotel.data.ReservationListDB;

public class TextView implements Observer {

	public TextView(Observable model) {
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Customer)
			displayCustomerInfo(o, arg);
		if (arg instanceof ArrayList<?>)
			displayReservationInfo(o, arg);

	}

	private void displayCustomerInfo(Observable o, Object arg) {
		Customer cus = (Customer) arg;
		System.out.println("Customer information");
		System.out.println("Name: " + cus.getName().getFullName());
		System.out.println("Email: " + cus.getEmail());
		if (cus.getCreditCard().isPresent())
			System.out.println("Credit Card: " + cus.getCreditCard().get().getType().toString() + " "
					+ cus.getCreditCard().get().getNumber());
	}

	private void displayReservationInfo(Observable o, Object arg) {
		displayCustomerInfo(o, arg);
		System.out.println("You have  reservations");
		@SuppressWarnings("unchecked")
		ArrayList<Reservation> reserList = (ArrayList<Reservation>) arg;
		
		if(reserList.size() != 0){
			System.out.println("\nReservations: ");
			
			for(Reservation reserv : reserList)
				System.out.println("Room: " + reserv.getRoom().getRoomNumber() 
				+ "\nCheck in date: " + reserv.getCheckInDate() 
				+ "\nCheck out date: " + reserv.getCheckOutDate());
		}
		o.notifyObservers();
		}

	}


