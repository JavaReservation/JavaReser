package group42.hotel.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;

public class ObjectSerializedList implements ListPersistenceObject {

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public List<Room> getRoomDatabase() throws ClassNotFoundException, IOException {

		List<Room> rooms = null;

		List<Room> meh = null;
		meh.toArray(HotelFileLoader.getRoomListFromSequentialFile("ReservationSys\\datafiles\\sorted\\rooms.txt"));
		
		System.out.println(meh.toString());

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("arrayFile"));
			rooms = (List<Room>) ois.readObject();

			ois.close();

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File was not found");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Class miss match trying to assign the file content into the variable");
		} catch (IOException e) {
			throw new IOException("IO exception");
		}

		return meh;
	}

	@Override
	public List<Customer> getCustomerDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customerTesting"));

			oos.writeObject(custs);
			oos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}


	}

	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("r"));

			oos.writeObject(reservs);
			oos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		
	}

}
