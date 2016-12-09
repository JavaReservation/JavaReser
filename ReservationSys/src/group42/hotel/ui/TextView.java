/**
 * 
 */
package group42.hotel.ui;

import java.util.Observable;
import java.util.Observer;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.creditcard.CreditCard;

public class TextView implements Observer{
	
	public TextView (Observable  model){
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof Customer){
			Customer cus = (Customer) arg;
			System.out.println("Customer information");
			System.out.println("Name: " + cus.getName());
			System.out.println("Email: " + cus.getEmail());
			if (cus.getCreditCard().isPresent())
			System.out.println("Credit Card: " + cus.getCreditCard());
		}
		if (arg instanceof Reservation){
			Reservation res = (Reservation) arg;
			System.out.println();
		}
		if (arg instanceof CreditCard){
			CreditCard card = (CreditCard) arg;
			System.out.println("Credit card :"  + card);
		}
		
		o.notifyAll();
		
		
	}

}
