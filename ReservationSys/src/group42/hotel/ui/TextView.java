/**
 * 
 */
package group42.hotel.ui;

import java.util.Observer;

import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.creditcard.AbstractCreditCard;
import dw317.lib.creditcard.CreditCard;
import group42.hotel.business.Hotel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * @author werner
 *
 */
public class TextView implements Observer{
	
	public TextView (Hotel model){
		
		model.addObserver(this);
		
	}

	@Override
	public void update(java.util.Observable obs, Object obj) {

		String str = obs.toString();
		
		if (obs instanceof CreditCard){
			
		}
		
		
		
	}

}
