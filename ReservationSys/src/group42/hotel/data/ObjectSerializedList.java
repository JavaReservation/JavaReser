package group42.hotel.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group42.util.ListUtilities;


public class ObjectSerializedList implements ListPersistenceObject {
	
	private String custFilename;
	private String resFilename;
	private String roomFilename;

	public ObjectSerializedList(String custFilename, String resFilename, String roomFilename){
		this.custFilename = custFilename;
		this.resFilename = resFilename;
		this.roomFilename = roomFilename;
	}

	
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Room> getRoomDatabase() {
		List<Room> listRooms = null;
		try{
			listRooms = (List<Room>) ListUtilities.deserializeObject(roomFilename);
		}
		catch(IOException ioe){
			return new ArrayList<Room>();
		}
		catch(ClassNotFoundException cnf){
			System.out.println(cnf.getMessage());			
		}
		return listRooms;
	}//end of getRoomDatabase
	
	public void saveRoomDatabase(List<Room> roomList) throws IOException {
		ListUtilities.serializeObject(roomList, roomFilename);
	}//end of saveRoomDatabase	
	
	
	@SuppressWarnings({"unchecked"})
	@Override
	public List<Customer> getCustomerDatabase() {
		List<Customer> custList = null;
		try{
			custList = (List<Customer>) ListUtilities.deserializeObject(custFilename);
		}
		catch(IOException ioe){
			return new ArrayList<Customer>();
		}
		catch(ClassNotFoundException cnf){
			System.out.println(cnf.getMessage());			
		}
		return custList;
	}//end of getCustomerDatabase

	@Override
	public void saveCustomerDatabase(List<Customer> custList) throws IOException {
		ListUtilities.serializeObject(custList, custFilename);
	}//end of saveCustomerDatabase

	
	@SuppressWarnings({"unchecked"})
	@Override
	public List<Reservation> getReservationDatabase() {
		List<Reservation> reservationList = null;
		try{
			reservationList = (List<Reservation>) ListUtilities.deserializeObject(resFilename);
		}
		catch(IOException ioe){
			return new ArrayList<Reservation>();
		}
		catch(ClassNotFoundException cnf){
			System.out.println(cnf.getMessage());			
		}
		return reservationList;
	}//end of getReservationDatabase

	@Override
	public void saveReservationDatabase(List<Reservation> resList) throws IOException {
		ListUtilities.serializeObject(resList, resFilename);
	}//end of saveReservationDatabase
	
	
	
	
	

	


}
