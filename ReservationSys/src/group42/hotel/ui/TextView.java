/**
 * 
 */
package group42.hotel.ui;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.creditcard.CreditCard;
import group42.hotel.business.DawsonReservation;
import group42.hotel.business.Hotel;

public class TextView implements Observer {

	public TextView(Observable model) {
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof Customer)

		{
			Customer cus = (Customer) arg;
			System.out.println("Customer information");
			System.out.println("Na      me: " + cus.getName().getFullName());
			System.out.println("Email: " + cus.getEmail());
			if (cus.getCreditCard().isPresent()) {
				System.out.println("Credit Card: " + cus.getCreditCard().get().getNumber());
			}

		}

		else if (arg instanceof CreditCard) {
			CreditCard card = (CreditCard) arg;
			System.out.println("Credit card :" + card);
		}

		o.notifyAll();

	}

}
