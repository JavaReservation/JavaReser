package group42.hotel.ui;

import java.util.Observable;
import java.util.Observer;

import group42.hotel.business.Hotel;

public class GUIViewController implements Observer {
	
	private Hotel model;
	
	public GUIViewController (Hotel model){
		this.model = model;
		model.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		int ctr = ((Hotel) model).countObservers();
		for ( int i =0; i < ctr; i++)
			System.out.print("*");
		System.out.println();


	}

}
